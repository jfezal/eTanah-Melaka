/**
 *
 * @author muhammad.amin
 * Modified By Rohan
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodBangsaDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSukuDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.KodBank;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.KodNegeri;
import etanah.model.KodSuku;
import etanah.model.KodWarganegara;
import etanah.model.PemohonHubungan;
import etanah.model.PemohonTanah;
import etanah.model.PermohonanKertas;
import etanah.model.PihakPengarah;
import etanah.service.PelupusanService;
import etanah.service.common.CawanganService;
import etanah.view.ListUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;

@UrlBinding("/pelupusan/maklumat_pemohon1")
public class MaklumatPemohon1ActionBean extends AbleActionBean {

    @Inject
    KodBangsaDAO kodBangsaDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodBankDAO kodBankDAO;
    @Inject
    CawanganService cawanganService;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PemohonHubunganDAO pemohonHubunganDAO;
    @Inject
    PihakPengarahDAO pihakPengarahDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ListUtil listUtil;
    @Inject
    KodSukuDAO kodSukuDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private PemohonHubungan pemohonHbgn;
    private KodCawangan kodCawangan;
    private Permohonan permohonan;
    private Pihak pihak;
    private Pihak pihak1;
    private PihakPengarah pihakPengarah;
    private PihakCawangan pihakCawangan;
    private PemohonTanah pemohonTanah;
    private PermohonanPihak permohonanPihak;
    private List<Pemohon> pemohonList;
    private List<PihakPengarah> pihakPengarahList;
    private List<PihakCawangan> cawanganList;
    private List<PemohonHubungan> pemohonHubunganList;
    private List<PemohonHubungan> pemohonHubunganList1;
    private List<PemohonHubungan> pemohonHubunganList2;
    private List<PemohonHubungan> pemohonHubunganSaudara;
    private List<PemohonTanah> pemohonLuarNegeri1;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentingan;
    private List<KodBangsa> senaraiKodBangsa;
    private List<KodJenisPengenalan> senaraiJenisPengenalan;
    private List<KodJenisPengenalan> senaraiJenisPengenalanPelupusan;
    private List<KodJenisPengenalan> senaraiJenisPengenalanPihakPengarah;
    private ArrayList<Hakmilik> hakmilikList = new ArrayList<Hakmilik>();
    private String idPermohonan;
    private String idPihak;
    private String idPemohon;
    private Long idHubungan;
    private Permohonan p;
    private String idPengarah;
    private Pengguna pguna;
    private Pemohon pemohon;
    String tarikhLahir;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    boolean tambahCawFlag;
    private String stageId;
    private String idCawangan;
    private String kod;
    private String kod1;
    private String kodW;
    private String kodBan;
    private Date tarik;
    private Long hbgn;
    private static final Logger LOG = Logger.getLogger(MaklumatPemohon1ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String kodBangsa;
    private String kodNegeri;
    private String suratNegeri;
    private String institusiNegeri;
    private String add1;
    private String check;
    private String kodS;
    private List<HakmilikPermohonan> hakmilikPermohonanList;

    @DefaultHandler
    public Resolution showForm() {
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_pemohon1.jsp").addParameter("tab", "true");
    }

    public Resolution viewPemohon() {
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        if (conf.getProperty("kodNegeri").equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
                LOG.info("------BMBT,PTBTC,PTBTS------");
                if (stageId.equals("01Kemasukan")) {
                    getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                } else {
                    getContext().getRequest().setAttribute("edit", Boolean.FALSE);
                }
            }
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        }
        return new JSP("pelupusan/maklumat_pemohon1.jsp").addParameter("tab", "true");
    }

    public Resolution showTambahPemohon() {
        pemohonHbgn = new PemohonHubungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        LOG.info("PERMOHONAN :" + permohonan.getKodUrusan().getKod());
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_pemohon1.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPemohonBatuan() {
        pemohonHbgn = new PemohonHubungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        LOG.info("PERMOHONAN :" + permohonan.getKodUrusan().getKod());
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/batuan/tambah_pemohonBatuan.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPemohonTanah() {
        pemohonTanah = new PemohonTanah();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_TanahLuarNegeri.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahLatarbelakangPemohon() {

        pemohonHbgn = new PemohonHubungan();
        pemohonHbgn.setWarganegara(kodWarganegaraDAO.findById("MAL"));
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_latarbelakang_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showMaklumatIbuBapa() {
        pemohonHbgn = new PemohonHubungan();
        pemohonHbgn.setWarganegara(kodWarganegaraDAO.findById("MAL"));
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_ibubapa.jsp").addParameter("tab", "true");
    }

    public Resolution showLatarBelakangPemohon() {
        return new JSP("pelupusan/latarbelakang_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showMaklumatAnak() {
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_maklumat_anak.jsp").addParameter("tab", "true");
    }

    public Resolution showMaklumatAhliLembaga() {
        pihakPengarah = new PihakPengarah();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findById(idPermohonan);
        if (pemohon != null) {
            pihak1 = pelupusanService.findByIdPihak(String.valueOf(pemohon.getPihak().getIdPihak()));
        }
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        BPelService serviceBpel = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Task t = null;
        t = serviceBpel.getTaskFromBPel(taskId, pguna);
       

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = (String) getContext().getRequest().getSession().getAttribute("idPihak");
        idPengarah = (String) getContext().getRequest().getSession().getAttribute("idPengarah");
        p = pelupusanService.findById(idPermohonan);
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        
        if (permohonan.getKodUrusan().getKod().equals("PHLP")){
            stageId = "01Kmskn";        
        }
        else{
            stageId = t.getSystemAttributes().getStage();
        }        
        
        String sub = idPermohonan.substring(0, 2);
        if (sub.equals("04")) {
            getContext().getRequest().setAttribute("Anak", Boolean.TRUE);
        } else if (sub.equals("05")) {
            getContext().getRequest().setAttribute("Anak", Boolean.FALSE);
        }

        if (p != null) {
            idPermohonan = p.getIdPermohonan();
            pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
//            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
//            if (pemohon != null) {
//                pihak = pemohon.getPihak();
//            }

            if (!pemohonList.isEmpty()) {
                for (Pemohon pemohon:pemohonList) {
                    pemohonHubunganList = pelupusanService.findHubunganByIDPemohon(pemohon.getIdPemohon());
                    pemohonHubunganList1 = pelupusanService.findHubunganByIDANAK(pemohon.getIdPemohon());
                    pemohonHubunganList2 = pelupusanService.findHubunganByIDPemohonBAPA(pemohon.getIdPemohon());
                    pemohonLuarNegeri1 = pelupusanService.findPemohonTanahByIDPemohon(String.valueOf(pemohon.getIdPemohon()));
                    pemohonHubunganSaudara = pelupusanService.findHubunganByIDPemohonSaudara(pemohon.getIdPemohon());
                    if (pemohon.getPihak() != null) {
                        pihakPengarahList = pelupusanService.findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
                    }

                    String[] tmohon = {"permohonan"};
                    Object[] tmodel = {permohonan};
                    hakmilikPermohonanList = hakmilikPermohonanDAO.findByEqualCriterias(tmohon, tmodel, null);
                }
            }

            if (!pemohonList.isEmpty()) {
//                hakmilikList.clear();
                for (int i = 0; i < pemohonList.size(); i++) {
                    Pihak phk = new Pihak();
                    phk = pemohonList.get(i).getPihak();
//                    String[] tname = {"pihak"};
//                    Object[] model = {phk};
                    List<HakmilikPihakBerkepentingan> pbList;
//                    pbList = hakmilikPihakBerkepentinganDAO.findByEqualCriterias(tname, model, null);
                    pbList = pelupusanService.findHakmilikPihak(phk.getIdPihak());
                    LOG.info("THIS IS PBLIST->" + pbList.size());
                    if (!pbList.isEmpty()) {
                        for (HakmilikPihakBerkepentingan hpb : pbList) {
                            Hakmilik hak = new Hakmilik();
                            hak = hakmilikDAO.findById(hpb.getHakmilik().getIdHakmilik());
                            hakmilikList.add(hak);
                        }
                    }
                }
            }

        }
        if ((pemohonList == null) || (pemohonList != null && pemohonList.size() == 0)) {
            getContext().getRequest().setAttribute("showTambah", Boolean.TRUE);
        }
        if (conf.getProperty("kodNegeri").equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
                if (stageId.equals("01Kemasukan")) {
                    viewPemohon();
                }
            }
        }
    }

    public Resolution refreshMaklumatPemohon() {
        rehydrate();
        return new RedirectResolution(MaklumatPemohon1ActionBean.class, "locate");
    }

    public Resolution cariPihakPemohonBatuan() {

        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        System.out.println("pihak" + pihak.getIdPihak());
        System.out.println("pihak" + pihak);
        LOG.info("PERMOHONAN2 :" + permohonan.getKodUrusan().getKod());
        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {

                Pihak pihakSearch = new Pihak();
                pihakSearch = pelupusanService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null) {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = pelupusanService.findById(idPermohonan);

                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                        addSimpleMessage("Maklumat Dijumpai");
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {

                if ((((pihak.getJenisPengenalan() == null) || (pihak.getJenisPengenalan().getKod().trim().equals("0")))) || (pihak.getNoPengenalan() == null)) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
                } else if ((pihak.getJenisPengenalan() == null) || (pihak.getJenisPengenalan().getKod().trim().equals("0"))) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        }

        return new JSP("pelupusan/batuan/tambah_pemohonBatuan.jsp").addParameter("popup", "true");
    }

    public Resolution cariSyarikat() {
        if (pihak != null) {
            if (pihak.getJenisPengenalan().equals("L") && pihak.getNoPengenalan() != null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pelupusanService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
                boolean duplicateFlag = false;
                if (pihakSearch != null) {
                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = pelupusanService.findById(idPermohonan);
                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {
                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
        }

        return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("popup", "true");
    }

    public Resolution cariPihakPemohon() {

        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        System.out.println("pihak" + pihak.getIdPihak());
        System.out.println("pihak" + pihak);
        LOG.info("PERMOHONAN2 :" + permohonan.getKodUrusan().getKod());
        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {

                Pihak pihakSearch = new Pihak();
                pihakSearch = pelupusanService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());


                boolean duplicateFlag = false;

                if (pihakSearch != null) {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = pelupusanService.findById(idPermohonan);

                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                        addSimpleMessage("Maklumat Dijumpai");
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
                pihak.setWargaNegara(kodWarganegaraDAO.findById("MAL"));
            } else {

                if ((((pihak.getJenisPengenalan() == null) || (pihak.getJenisPengenalan().getKod().trim().equals("0")))) || (pihak.getNoPengenalan() == null)) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
                } else if ((pihak.getJenisPengenalan() == null) || (pihak.getJenisPengenalan().getKod().trim().equals("0"))) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        }

        return new JSP("pelupusan/tambah_pemohon1.jsp").addParameter("popup", "true");
    }

    public Resolution cariLatarbelakangPemohon() {

        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pelupusanService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
                boolean duplicateFlag = false;
                if (pihakSearch != null) {
                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = pelupusanService.findById(idPermohonan);
                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {
                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        } else {
            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
        }
        return new JSP("pelupusan/tambah_latarbelakang_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPemohonSuamiIsteri1() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findById(idPermohonan);
        if (validationIsteri()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_latarbelakang_pemohon.jsp").addParameter("tab", "true");
        }

        if (pemohonHbgn != null) {
            if (tarikhLahir != null) {
                try {
                    pemohonHbgn.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                }
            }

            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            pemohonHbgn.setPemohon(pemohon);
            pemohonHbgn.setInfoAudit(info);
            pemohonHbgn.setCawangan(permohonan.getCawangan());
            if (pemohonHbgn.getPekerjaan() == null) {
                pemohonHbgn.setPekerjaan("Tidak Bekerja");
                pemohonHbgn.setGaji(BigDecimal.ZERO);
            }
//                pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        }


        if ((add1 != null) && (!add1.equals(""))) {
            String idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
            Pihak pihak1 = new Pihak();
            pihak1 = pemohon.getPihak();
            pemohonHbgn.setAlamat1(pihak1.getAlamat1());
            pemohonHbgn.setAlamat2(pihak1.getAlamat2());
            pemohonHbgn.setAlamat3(pihak1.getAlamat3());
            pemohonHbgn.setAlamat4(pihak1.getAlamat4());
            pemohonHbgn.setPoskod(pihak1.getPoskod());
            pemohonHbgn.setNegeri(pihak1.getNegeri());
            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        } else {
            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        }

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tambah_latarbelakang_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPemohonIbuBapa() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findById(idPermohonan);
        if (validationIbuBapa()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_ibubapa.jsp").addParameter("tab", "true");
        }
        if (pemohonHbgn != null) {
            if (tarikhLahir != null) {
                try {
                    pemohonHbgn.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                }
            }
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            pemohonHbgn.setPemohon(pemohon);
            pemohonHbgn.setInfoAudit(info);
            pemohonHbgn.setCawangan(permohonan.getCawangan());
//            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        }

        if ((add1 != null) && (!add1.equals(""))) {
            String idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
            Pihak pihak1 = new Pihak();
            pihak1 = pemohon.getPihak();
            pemohonHbgn.setAlamat1(pihak1.getAlamat1());
            pemohonHbgn.setAlamat2(pihak1.getAlamat2());
            pemohonHbgn.setAlamat3(pihak1.getAlamat3());
            pemohonHbgn.setAlamat4(pihak1.getAlamat4());
            pemohonHbgn.setPoskod(pihak1.getPoskod());
            pemohonHbgn.setNegeri(pihak1.getNegeri());
            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        } else {
            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        }

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tambah_ibubapa.jsp").addParameter("tab", "true");
    }

    public boolean validation() {
        boolean flag = false;
        if ((pemohonHbgn.getNama() == null) || (pemohonHbgn.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pemohonHbgn.getNoPengenalan() == null) {
            flag = true;
        } else if (pemohonHbgn.getKodJantina().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Jantina ");
        } else if ((pemohonHbgn.getUmur() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Umur");
        }

        return flag;
    }
    //CATER FOR BATUAN

    public boolean PemohonvalidationBatuan(String kodUrusan) {
        boolean flag = false;
        String sub = idPermohonan.substring(0, 2);
        //SYARIKAT VALIDATION
        if (pihak.getJenisPengenalan().getKod().equals("U") || pihak.getJenisPengenalan().getKod().equals("D") || pihak.getJenisPengenalan().getKod().equals("S")) {
            if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama Syarikat");
            } else if (pihak.getBangsa() == null) {
                flag = true;
                addSimpleError("Sila Pilih Jenis Syarikat");
            } else if (pihak.getTarikhLahir() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Tarikh Penubuhan");
            } else if ((pihak.getSuratAlamat1() == null) || (pihak.getSuratAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Alamat");
            } else if (pihak.getSuratPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Poskod");
            } else if (pihak.getSuratNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Surat Negeri");

            } else if (pihak.getNoTelefon1() == null) {
                flag = true;
                addSimpleError("Sila Masukkan No Telefon (Office)");

            } else if (pihak.getAktivitiPerniagaan() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Aktiviti Perniagaan");

            }
        } //END OF SYARIKAT
        //INDIVIDU
        else if ((pihak.getJenisPengenalan().getKod().equals("B")) || (pihak.getJenisPengenalan().getKod().equals("N")) || (pihak.getJenisPengenalan().getKod().equals("L")) || (pihak.getJenisPengenalan().getKod().equals("P")) || (pihak.getJenisPengenalan().getKod().equals("T")) || (pihak.getJenisPengenalan().getKod().equals("I"))) {
            if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama");
            } else if (pemohon.getUmur() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Umur");
            } else if ((pemohon.getPekerjaan() == null) || (pemohon.getPekerjaan().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Pekerjaan");
            } else if (pemohon.getPendapatan() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Pendapatan");
            } else if ((pihak.getAlamat1() == null) || (pihak.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pihak.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pihak.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");

            } else if ((pihak.getSuratAlamat1() == null) || (pihak.getSuratAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Alamat");
            } else if (pihak.getSuratPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Poskod");
            } else if (pihak.getSuratNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Surat Negeri");

            } else if (pihak.getNoTelefonBimbit() == null) {
                flag = true;
                addSimpleError(" Sila Masukkan Telefon Bimbit");
            }
        }
        return flag;
    }
    //END OF BATUAN

    public boolean Pemohonvalidation(String kodUrusan) {
        boolean flag = false;
        String sub = idPermohonan.substring(0, 2);
        if (pihak.getJenisPengenalan().getKod().equals("U") || pihak.getJenisPengenalan().getKod().equals("D") || pihak.getJenisPengenalan().getKod().equals("S")) {
            if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama Syarikat");
            }
//            else if (pihak.getBangsa() == null) {
//                flag = true;
//                addSimpleError("Sila Pilih Jenis Syarikat");
//            }
//            else if (pihak.getTarikhLahir() == null) {
//                flag = true;
//                addSimpleError("Sila Masukkan Tarikh Penubuhan");
//            }
            //         else if (pihak.getNoTelefon1() == null) {
            //            flag = true;
            //            addSimpleError("Sila Masukkan Nombor Telefon");
            //        }
            else if ((pihak.getSuratAlamat1() == null) || (pihak.getSuratAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Alamat");
            } else if (pihak.getSuratPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Poskod");
            } else if (pihak.getSuratNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Surat Negeri");

            }
        } else if ((pihak.getJenisPengenalan().getKod().equals("B")) || (pihak.getJenisPengenalan().getKod().equals("N")) || (pihak.getJenisPengenalan().getKod().equals("L")) || (pihak.getJenisPengenalan().getKod().equals("P")) || (pihak.getJenisPengenalan().getKod().equals("T")) || (pihak.getJenisPengenalan().getKod().equals("I"))) {
            if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama");
            }
//            else if (pihak.getBangsa() == null) {
//                flag = true;
//                addSimpleError("Sila Pilih Bangsa");
//            }
//            else if (pihak.getKodJantina() == null) {
//                flag = true;
//                addSimpleError("Sila Pilih Jantina");
//            }
//            else if (pemohon.getUmur() == null) {
//                flag = true;
//                addSimpleError("Sila Masukkan Umur");
//            }
//            else if (pihak.getWargaNegara().getKod().equals("0")) {
//                flag = true;
//                addSimpleError("Sila Masukkan Warganegara");
//            }
            if (!kodUrusan.equals("PRMP")) {
                if ((pemohon.getPekerjaan() != null)) {
//                     if ((!pemohon.getPekerjaan().trim().equals(""))) {
//                    if (pemohon.getPendapatan() == null) {
//                        flag = true;
//                        addSimpleError("Sila Masukkan Pendapatan Jika Pekerjaan Ada");
//                    }
//                     }
                }
//                else if (pemohon.getStatusKahwin().equals("0")) {
//                    flag = true;
//                    addSimpleError("Sila Masukkan Status Perkahwinan");
//                }
//                else if (pemohon.getTanggungan() == null) {
//                    flag = true;
//                    addSimpleError("Sila Masukkan Tanggungan");
//                }
                else if (sub.equals("04")) {
//                    if (pihak.getAnakTempatan() == null) {
//                        flag = true;
//                        addSimpleError("Sila Pilih Anak Tempatan");
//                    } else if (pemohon.getTempohMastautin() == null) {
//                        flag = true;
//                        addSimpleError("Sila Masukkan Tempoh tinggal di Melaka");
//                    }
                }
            } else if ((pihak.getAlamat1() == null) || (pihak.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pihak.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pihak.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");

            } else if ((pihak.getSuratAlamat1() == null) || (pihak.getSuratAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Alamat");
            } else if (pihak.getSuratPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Poskod");
            } else if (pihak.getSuratNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Surat Negeri");

            } //        else if (pihak.getNoTelefon1() == null) {
            //            flag = true;
            //            addSimpleError(" Sila Masukkan Nombor Telefon");
            //        }
            //         else if(pihak.getEmail() == null){
            //            flag = true;
            //            addSimpleError(" Sila Masukkan Emel");
            //        }
//            else if (pihak.getNoTelefonBimbit() == null && pihak.getNoTelefon1() == null) {
//                flag = true;
//                addSimpleError(" Sila Masukkan no Telefon");
//            }


        }

//        if(kodUrusan.equals("PPBB")||kodUrusan.equals("PBPTD")||kodUrusan.equals("PBPTG")){
//            if(pihak.getBank()==null||pihak.getBank().getKod().trim().equals("")||pihak.getBank().getKod().trim().equals("0")){
//                flag = true;
//                addSimpleError("Sila Pilih Jenis Bank");
//            }
//            if(pihak.getNoAkaunBank()==null||pihak.getNoAkaunBank().trim().equals("")){
//               flag = true;
//               addSimpleError("Sila Masukkan No Akaun Bank");
//            }
//        }
        return flag;
    }

    public boolean EditPemohonValidation(String kodUrusan) {
        boolean flag = false;
        String sub = idPermohonan.substring(0, 2);

        if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } //            if(kodUrusan.equals("PPBB")||kodUrusan.equals("PBPTD")||kodUrusan.equals("PBPTG")){
        //            if(kodBan==null||kodBan.trim().equals("")||kodBan.trim().equals("0")){
        //                flag = true;
        //                addSimpleError("Sila Pilih Jenis Bank");
        //            }
        //            if(pihak.getNoAkaunBank()==null||pihak.getNoAkaunBank().trim().equals("")){
        //               flag = true;
        //               addSimpleError("Sila Masukkan No Akaun Bank");
        //            }
        //        }
//        else if (pihak.getBangsa() == null) {
//            flag = true;
//            addSimpleError("Sila Pilih Bangsa");
//        }
        else if ((pihak.getSuratAlamat1() == null) || (pihak.getSuratAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Surat Alamat");
        } else if (pihak.getSuratPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Surat Poskod");
        } else if (pihak.getSuratNegeri().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Surat Negeri");

        } //        else if (pihak.getNoTelefon1() == null) {
        //            flag = true;
        //            addSimpleError("Sila Masukkan Nombor Telefon");
        //        }
        else if ((pihak.getJenisPengenalan().getKod().equals("B")) || (pihak.getJenisPengenalan().getKod().equals("N")) || (pihak.getJenisPengenalan().getKod().equals("L")) || (pihak.getJenisPengenalan().getKod().equals("P")) || (pihak.getJenisPengenalan().getKod().equals("T")) || (pihak.getJenisPengenalan().getKod().equals("I"))) {
//            if (pihak.getKodJantina() == null) {
//                flag = true;
//                addSimpleError("Sila Pilih Jantina");
//            }
//            else if (pemohon.getUmur() == null) {
//                flag = true;
//                addSimpleError("Sila Masukkan Umur");
//            }
//            else if (pihak.getWargaNegara().getKod().equals("0")) {
//                flag = true;
//                addSimpleError("Sila Masukkan Warganegara");
//            }
//            if (!kodUrusan.equals("PRMP")) {
//                if (pemohon.getPekerjaan() != null) {
//                    BigDecimal zero = new BigDecimal(0.00);
//
//                    if (pemohon.getPendapatan().equals(zero) && !pemohon.getPekerjaan().equalsIgnoreCase("Tidak Bekerja")) {
//                        flag = true;
//                        addSimpleError("Sila Masukkan Pendapatan Jika Pekerjaan Ada");
//                    }
//                } else if (pemohon.getStatusKahwin().equals("0")) {
//                    flag = true;
//                    addSimpleError("Sila Masukkan Status Perkahwinan");
//                } else if (pemohon.getTanggungan() == null) {
//                    flag = true;
//                    addSimpleError("Sila Masukkan Tanggungan");
//                } else if (sub.equals("04")) {
//                    if (pihak.getAnakTempatan() == null) {
//                        flag = true;
//                        addSimpleError("Sila Pilih Anak");
//                    } else if (pemohon.getTempohMastautin() == null) {
//                        flag = true;
//                        addSimpleError("Sila Masukkan Tempoh tinggal di Melaka");
//                    }
//                }
//            }
            if ((pihak.getAlamat1() == null) || (pihak.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pihak.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pihak.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");

            }
//            else if (pihak.getNoTelefonBimbit() == null && pihak.getNoTelefon1() == null) {
//                flag = true;
//                addSimpleError(" Sila Masukkan No Telefon");
//            }
//             else if(pihak.getEmail() == null){
//            flag = true;
//            addSimpleError(" Sila Masukkan Emel");
//        }


        }
        return flag;
    }

    public boolean EditPemohonBatuanValidation(String kodUrusan) {
        boolean flag = false;
        String sub = idPermohonan.substring(0, 2);

        if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } //            if(kodUrusan.equals("PPBB")||kodUrusan.equals("PBPTD")||kodUrusan.equals("PBPTG")){
        //            if(kodBan==null||kodBan.trim().equals("")||kodBan.trim().equals("0")){
        //                flag = true;
        //                addSimpleError("Sila Pilih Jenis Bank");
        //            }
        //            if(pihak.getNoAkaunBank()==null||pihak.getNoAkaunBank().trim().equals("")){
        //               flag = true;
        //               addSimpleError("Sila Masukkan No Akaun Bank");
        //            }
        //        }
        //        else if (pihak.getBangsa() == null) {
        //            flag = true;
        //            addSimpleError("Sila Pilih Bangsa");
        //        }
        else if ((pihak.getSuratAlamat1() == null) || (pihak.getSuratAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Surat Alamat");
        } else if (pihak.getSuratPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Surat Poskod");
        } else if (pihak.getSuratNegeri().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Surat Negeri");

        } //        else if (pihak.getNoTelefon1() == null) {
        //            flag = true;
        //            addSimpleError("Sila Masukkan Nombor Telefon");
        //        }
        else if ((pihak.getJenisPengenalan().getKod().equals("B")) || (pihak.getJenisPengenalan().getKod().equals("N")) || (pihak.getJenisPengenalan().getKod().equals("L")) || (pihak.getJenisPengenalan().getKod().equals("P")) || (pihak.getJenisPengenalan().getKod().equals("T")) || (pihak.getJenisPengenalan().getKod().equals("I"))) {
//            if (pihak.getKodJantina()==null) {
//                flag = true;
//                addSimpleError("Sila Pilih Jantina");
//            }
            if ((pihak.getJenisPengenalan().getKod().equals("S")) || (pihak.getJenisPengenalan().getKod().equals("D")) || (pihak.getJenisPengenalan().getKod().equals("U"))) {
                if (pihak.getAktivitiPerniagaan() == null) {
                    flag = true;
                    addSimpleError("Sila Aktiviti Perniagaan");
                }
            }
            if (pemohon.getUmur() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Umur");
            } //            else if (pihak.getWargaNegara().getKod().equals("0")) {
            //                flag = true;
            //                addSimpleError("Sila Masukkan Warganegara");
            //            }
            else if ((pemohon.getPekerjaan() == null) || (pemohon.getPekerjaan().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Pekerjaan");
            } else if (pemohon.getPendapatan() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Pendapatan");
            } //            else if (pemohon.getStatusKahwin().equals("0")){
            //                flag = true;
            //                addSimpleError("Sila Masukkan Status Perkahwinan");
            //            }
            //            else if (pemohon.getTanggungan() == null) {
            //                flag = true;
            //                addSimpleError("Sila Masukkan Tanggungan");
            //            }
            else if ((pihak.getAlamat1() == null) || (pihak.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pihak.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pihak.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");

            } else if (pihak.getNoTelefonBimbit() == null) {
                flag = true;
                addSimpleError(" Sila Masukkan Telefon Bimbit");
            }
//             else if(pihak.getEmail() == null){
//            flag = true;
//            addSimpleError(" Sila Masukkan Emel");
//        }

//            else if(sub.equals("04")){
//            if (pihak.getAnakTempatan()==null){
//                flag = true;
//                addSimpleError("Sila Pilih Anak");
//            }
//            else if (pemohon.getTempohMastautin() == null) {
//                flag = true;
//                addSimpleError("Sila Masukkan Tempoh tinggal di Melaka");
//            }
//            }
        }
        return flag;
    }

    public boolean validateAhilLembangaPengarah() {
        boolean flag = false;
        System.out.println("nama" + pihakPengarah.getNama());

        if ((pihakPengarah.getNama() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pihakPengarah.getNoPengenalan() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Nombor Pengenalan");
        } else if ((pihakPengarah.getAlamat1() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");
        } else if (pihakPengarah.getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        } else if (pihakPengarah.getKodNegeri().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");
        }
//        else if (pihakPengarah.getJumlahSaham() == null) {
//            flag = true;
//            addSimpleError("Sila Masukkan Jumlah Saham");
//        }
        return flag;
    }

    public boolean validatePemohonTanah() {
        boolean flag = false;
        if (pemohonTanah.getNoLot() == null) {
            flag = true;
            addSimpleError("Sila Masukkan No Lot");
        } else if (pemohonTanah.getDaerah() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Daerah");
        } else if (pemohonTanah.getNegeri() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Daerah");
        } else if (pemohonTanah.getBandarPekanMukim() == null) {
            flag = true;
            addSimpleError("Sila masukkan Mukim");
        }
        return flag;
    }

    public boolean validateEditAhilLembagaPengarah() {
        boolean flag = false;
        if ((pihakPengarah.getNama() == null) || (pihakPengarah.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if ((pihakPengarah.getAlamat1() == null) || (pihakPengarah.getAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");
        } else if (pihakPengarah.getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        } else if (kod.equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");
        }
//        else if (pihakPengarah.getJumlahSaham() == null) {
//            flag = true;
//            addSimpleError("Sila Masukkan Jumlah Saham");
//        }
        return flag;

    }

    public boolean validationIsteri() {

        boolean flag = false;

        if ((pemohonHbgn.getNama() == null) || (pemohonHbgn.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pemohonHbgn.getJenisPengenalan().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Jenis Pengenalan");

        } else if ((pemohonHbgn.getNoPengenalan() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Nombor Pengenalan");
        } else if (pemohonHbgn.getKaitan().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Kaitan");

        } else if (pemohonHbgn.getTarikhLahir() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Lahir");
        } else if (pemohonHbgn.getUmur() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Umur");
        }
//         else if (pemohonHbgn.getWarganegara().getKod().equals("0")) {
//            flag = true;
//            addSimpleError("Sila Masukkan Warganegara");
//        }
//         if("05".equals(conf.getProperty("kodNegeri"))){
//            if(permohonan.getKodUrusan().getKod().equals("PBMT")){
//                if ((pemohonHbgn.getPekerjaan() == null) || (pemohonHbgn.getPekerjaan().trim().equals(""))) {
//                    flag = true;
//                    addSimpleError("Sila Masukkan Pekerjaan");
//                }
//            }
//        }
        if (pemohonHbgn.getPekerjaan() != null) {
            if (pemohonHbgn.getGaji() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Pendapatan Bulanan (RM) Jika Pekerjaan Ada");
            }
        } else if ((add1 == null) || (add1.equals(""))) {

            if ((pemohonHbgn.getAlamat1() == null) || (pemohonHbgn.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if ((pemohonHbgn.getPoskod() == null) || (pemohonHbgn.getPoskod().trim().equals(""))) {

                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pemohonHbgn.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");
            }
        }

        return flag;

    }

    public boolean validationEditIsteri() {

        boolean flag = false;
        if ((pemohonHbgn.getNama() == null) || (pemohonHbgn.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pemohonHbgn.getTarikhLahir() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Lahir");
        } else if (pemohonHbgn.getUmur() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Umur");
        } else if (pemohonHbgn.getWarganegara().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Masukkan Warganegara");

        } else if ((add1 == null) || (add1.equals(""))) {
            if ((pemohonHbgn.getAlamat1() == null) || (pemohonHbgn.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if ((pemohonHbgn.getPoskod() == null) || (pemohonHbgn.getPoskod().trim().equals(""))) {

                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pemohonHbgn.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");
            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                if ((pemohonHbgn.getPekerjaan() == null) || (pemohonHbgn.getPekerjaan().trim().equals(""))) {
                    flag = true;
                    addSimpleError("Sila Masukkan Pekerjaan");
                    return flag;
                }
                if (pemohonHbgn.getGaji() == null) {
                    flag = true;
                    addSimpleError("Sila Masukkan Pendapatan Bulanan (RM)");
                    return flag;
                }
            }
        }
        if (conf.getProperty("kodNegeri").equals("04")) {
            if (pemohonHbgn.getPekerjaan() != null) {
                if (pemohonHbgn.getGaji().equals(BigDecimal.ZERO) && !pemohonHbgn.getPekerjaan().equalsIgnoreCase("TIDAK BEKERJA")) {
                    flag = true;
                    addSimpleError("Sila Masukkan Pendapatan Bulanan (RM) Jika Pekerjaan Ada");
                    return flag;
                }
            }
        }
        return flag;
    }

    public boolean validationIbuBapa() {

        boolean flag = false;
        if ((pemohonHbgn.getNama() == null) || (pemohonHbgn.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pemohonHbgn.getJenisPengenalan().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Jenis Pengenalan");

        } else if ((pemohonHbgn.getNoPengenalan() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Nombor Pengenalan");
        } else if (pemohonHbgn.getKaitan().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Kaitan");

        } else if (pemohonHbgn.getTarikhLahir() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Lahir");
        } else if (pemohonHbgn.getUmur() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Umur");
        } else if (pemohonHbgn.getWarganegara().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Masukkan Warganegara");
        } //        else if ((pemohonHbgn.getPekerjaan() == null) || (pemohonHbgn.getPekerjaan().trim().equals(""))) {
        //            flag = true;
        //            addSimpleError("Sila Masukkan Pekerjaan");
        //        }
        //         else if (pemohonHbgn.getGaji() == null) {
        //            flag = true;
        //            addSimpleError("Sila Masukkan Pendapatan Bulanan (RM)");
        //        }
        else if ((add1 == null) || (add1.equals(""))) {

            if ((pemohonHbgn.getAlamat1() == null) || (pemohonHbgn.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");

            } else if (pemohonHbgn.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pemohonHbgn.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");
            }
        }

        return flag;
    }

    public boolean validationEditIbuBapa() {

        boolean flag = false;
        if ((pemohonHbgn.getNama() == null) || (pemohonHbgn.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pemohonHbgn.getTarikhLahir() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Lahir");
        } else if (pemohonHbgn.getUmur() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Umur");
        } else if (pemohonHbgn.getWarganegara().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Masukkan Warganegara");

        }
//        else if ((pemohonHbgn.getPekerjaan() == null) || (pemohonHbgn.getPekerjaan().trim().equals(""))) {
//            flag = true;
//            addSimpleError("Sila Masukkan Pekerjaan");
//        }
//         else if (pemohonHbgn.getGaji() == null) {
//            flag = true;
//            addSimpleError("Sila Masukkan Pendapatan Bulanan (RM)");
//        }
        if ((add1 == null) || (add1.equals(""))) {
            if ((pemohonHbgn.getAlamat1() == null) || (pemohonHbgn.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pemohonHbgn.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pemohonHbgn.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");
            }
        }
        return flag;
    }

    public Resolution simpanPemohonAnak() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findById(idPermohonan);

        if (validation()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_maklumat_anak.jsp").addParameter("tab", "true");
        }
        if (pemohonHbgn != null) {

            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            pemohonHbgn.setKaitan("ANAK");
            pemohonHbgn.setPemohon(pemohon);
            pemohonHbgn.setInfoAudit(info);
            pemohonHbgn.setCawangan(permohonan.getCawangan());
            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tambah_maklumat_anak.jsp").addParameter("tab", "true");

    }

    public Resolution simpanMaklumatAhliLembaga() {
        if (validateAhilLembangaPengarah()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
        }

        if (pihakPengarah != null) {
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());

            pihakPengarah.setPihak(pemohon.getPihak());
            pihakPengarah.setInfoAudit(info);
            pihakPengarah.setAktif('Y');

            Pihak pihakTemp = pihakDAO.findById(pihak1.getIdPihak());

            pihakTemp.setAlamat1(!StringUtils.isEmpty(pihakPengarah.getAlamat1()) ? pihakPengarah.getAlamat1() : new String());
            pihakTemp.setAlamat2(!StringUtils.isEmpty(pihakPengarah.getAlamat2()) ? pihakPengarah.getAlamat2() : new String());
            pihakTemp.setAlamat3(!StringUtils.isEmpty(pihakPengarah.getAlamat3()) ? pihakPengarah.getAlamat3() : new String());
            pihakTemp.setAlamat4(!StringUtils.isEmpty(pihakPengarah.getAlamat4()) ? pihakPengarah.getAlamat4() : new String());
            pihakTemp.setPoskod(!StringUtils.isEmpty(pihakPengarah.getPoskod()) ? pihakPengarah.getPoskod() : new String());
            pihakTemp.setNegeri(pihakPengarah.getKodNegeri() != null ? pihakPengarah.getKodNegeri() : new KodNegeri());
            pihakPengarah.setPihak(pihakTemp);

            pelupusanService.simpanPemohonHbgn(pihakPengarah, pihakTemp);
        }
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Disimpan");
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
    }
    //POP UP FOR BATUAN

    public Resolution simpanPemohonTanah() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findById(idPermohonan);
        if (validatePemohonTanah()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_TanahLuarNegeri.jsp").addParameter("tab", "true");
        }
        if (pemohonTanah != null) {
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            pemohonTanah.setInfoAudit(info);
            pemohonTanah.setPemohon(pemohon);
            pelupusanService.simpanPemohonTanah(pemohonTanah);
        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tambah_TanahLuarNegeri.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPemohonPopupBatuan() throws ParseException {
        cariFlag = true;
        tiadaDataFlag = true;

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        LOG.info("THIS IS KODURUSAN:" + permohonan.getKodUrusan().getKod());
        if (permohonan == null) {
            permohonan = p;
        }
        if (PemohonvalidationBatuan(permohonan.getKodUrusan().getKod())) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/batuan/tambah_pemohonBatuan.jsp").addParameter("popup", "true");
        }

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = pelupusanService.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        Pihak pihakTemp = new Pihak();
        String idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
        if (idPihak != null && !idPihak.equals("0")) {
            pihakTemp = pihakDAO.findById(Long.parseLong(idPihak));
            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }



        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }


        pihakTemp.setInfoAudit(infoAudit);
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihakTemp);
        pmohon.setInfoAudit(infoAudit);
        pmohon.setCawangan(permohonan.getCawangan());

        if (idCawangan != null) {
            pihakCawangan = new PihakCawangan();
            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
            pmohon.setPihakCawangan(pihakCawangan);

        }

        if (pmohon != null) {

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());

            String sub = idPermohonan.substring(0, 2);
            if (sub.equals("04")) {
                pihakTemp.setAnakTempatan(pihak.getAnakTempatan());
            }
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setEmail(pihak.getEmail());
            pihakTemp.setTarikhLahir(pihak.getTarikhLahir());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());
            if (pihak.getBank() != null && (pihak.getBank().getKod().equals("PBPTD") || (pihak.getBank().getKod().equals("PBPTG") || (pihak.getBank().getKod().equals("PPBB"))))) {
                pihakTemp.setBank(pihak.getBank());
                if (pihak.getNoAkaunBank() != null && !("").equals(pihak.getNoAkaunBank())) {
                    pihakTemp.setNoAkaunBank(pihak.getNoAkaunBank());
                }
            }

            if ((pihak.getJenisPengenalan().getKod().equals("B"))) {
                pihakTemp.setWarnaKP(pihak.getWarnaKP());
            }


            if ((pihak.getJenisPengenalan().getKod().equals("B")) || (pihak.getJenisPengenalan().getKod().equals("L")) || (pihak.getJenisPengenalan().getKod().equals("P")) || (pihak.getJenisPengenalan().getKod().equals("T")) || (pihak.getJenisPengenalan().getKod().equals("I")) || (pihak.getJenisPengenalan().getKod().equals("F")) || (pihak.getJenisPengenalan().getKod().equals("O")) || (pihak.getJenisPengenalan().getKod().equals("N"))) {
                pmohon.setPekerjaan(pemohon.getPekerjaan());
                pmohon.setUmur(pemohon.getUmur());
                pmohon.setPendapatan(pemohon.getPendapatan());
                if (sub.equals("04")) {
                    pmohon.setTempohMastautin(pemohon.getTempohMastautin());
                }
                pmohon.setTanggungan(pemohon.getTanggungan());
                pmohon.setInstitusi(pemohon.getInstitusi());
                pmohon.setInstitusiAlamat1(pemohon.getInstitusiAlamat1());
                pmohon.setInstitusiAlamat2(pemohon.getInstitusiAlamat2());
                pmohon.setInstitusiAlamat3(pemohon.getInstitusiAlamat3());
                pmohon.setInstitusiAlamat4(pemohon.getInstitusiAlamat4());
                pmohon.setInstitusiPoskod(pemohon.getInstitusiPoskod());
                pmohon.setInstitusiNegeri(pemohon.getInstitusiNegeri());

            }

            if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S")) {
                pihakTemp.setAktivitiPerniagaan(pihak.getAktivitiPerniagaan());
            } else {
                pmohon.setStatusKahwin(pemohon.getStatusKahwin());
            }
            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
            pelupusanService.simpanPihakPemohon(pihakTemp, pmohon);
            rehydrate();
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);

            return new JSP("pelupusan/batuan/tambah_pemohonBatuan.jsp").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }
    //END OF BATUAN

    public Resolution simpanPemohonPopup() throws ParseException {
        cariFlag = true;
        tiadaDataFlag = true;

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        LOG.info("THIS IS KODURUSAN:" + permohonan.getKodUrusan().getKod());
        if (Pemohonvalidation(permohonan.getKodUrusan().getKod())) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_pemohon1.jsp").addParameter("popup", "true");
        }

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = pelupusanService.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        Pihak pihakTemp = new Pihak();
        String idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
        if (idPihak != null && !idPihak.equals("0")) {
            pihakTemp = pihakDAO.findById(Long.parseLong(idPihak));
            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }


        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }

        pihakTemp.setInfoAudit(infoAudit);
        pelupusanService.saveOnly(pihakTemp);

        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihakTemp);
        pmohon.setInfoAudit(infoAudit);
        pmohon.setCawangan(permohonan.getCawangan());

        if (idCawangan != null) {
            pihakCawangan = new PihakCawangan();
            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
            pmohon.setPihakCawangan(pihakCawangan);

        }
        pelupusanService.simpanPemohon(pmohon);

        if (pmohon != null) {

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());

            String sub = idPermohonan.substring(0, 2);
            if (sub.equals("04")) {
                pihakTemp.setAnakTempatan(pihak.getAnakTempatan());
            }
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefon1());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            pihakTemp.setAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setPoskod(pihak.getSuratPoskod());
            pihakTemp.setNegeri(pihak.getSuratNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setEmail(pihak.getEmail());
            pihakTemp.setTarikhLahir(pihak.getTarikhLahir());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());
            if (pihak.getBank() != null && (pihak.getBank().getKod().equals("PBPTD") || (pihak.getBank().getKod().equals("PBPTG") || (pihak.getBank().getKod().equals("PPBB"))))) {
                pihakTemp.setBank(pihak.getBank());
                if (pihak.getNoAkaunBank() != null && !("").equals(pihak.getNoAkaunBank())) {
                    pihakTemp.setNoAkaunBank(pihak.getNoAkaunBank());
                }
            }

            if ((pihak.getJenisPengenalan().getKod().equals("B"))) {
                pihakTemp.setWarnaKP(pihak.getWarnaKP());
            }


            if ((pihak.getJenisPengenalan().getKod().equals("B")) || (pihak.getJenisPengenalan().getKod().equals("L")) || (pihak.getJenisPengenalan().getKod().equals("P")) || (pihak.getJenisPengenalan().getKod().equals("T")) || (pihak.getJenisPengenalan().getKod().equals("I")) || (pihak.getJenisPengenalan().getKod().equals("F")) || (pihak.getJenisPengenalan().getKod().equals("O")) || (pihak.getJenisPengenalan().getKod().equals("N"))) {
                if (pemohon.getPekerjaan() == null) {
//                    String tB = "Tidak Bekerja" ;
                    pmohon.setPekerjaan("TIDAK BEKERJA");
                    pmohon.setPendapatan(BigDecimal.ZERO);
                } else {
                    pmohon.setPekerjaan(pemohon.getPekerjaan());
                    pmohon.setPendapatan(pemohon.getPendapatan());
                }
                pmohon.setUmur(pemohon.getUmur());


                if (sub.equals("04")) {
                    pmohon.setTempohMastautin(pemohon.getTempohMastautin());
                }
                pmohon.setTanggungan(pemohon.getTanggungan());
                pmohon.setInstitusi(pemohon.getInstitusi());
                pmohon.setInstitusiAlamat1(pemohon.getInstitusiAlamat1());
                pmohon.setInstitusiAlamat2(pemohon.getInstitusiAlamat2());
                pmohon.setInstitusiAlamat3(pemohon.getInstitusiAlamat3());
                pmohon.setInstitusiAlamat4(pemohon.getInstitusiAlamat4());
                pmohon.setInstitusiPoskod(pemohon.getInstitusiPoskod());
                pmohon.setInstitusiNegeri(pemohon.getInstitusiNegeri());

            }

            if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S")) {
            } else {
                pmohon.setStatusKahwin(pemohon.getStatusKahwin());
            }
            pelupusanService.simpanPemohon(pmohon);
            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
            pelupusanService.saveOnly(pihakTemp);
//            pelupusanService.simpanPihakPemohon(pihakTemp, pmohon);
            rehydrate();
            addSimpleMessage("Maklumat Berjaya Disimpan");
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);

            return new JSP("pelupusan/tambah_pemohon1.jsp").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpaneditLembaga() {
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        if (validateEditAhilLembagaPengarah()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/edit_ahil_lembaga_pengarah.jsp").addParameter("tab", "true");
        }

        PihakPengarah pihakPengarahTemp = pihakPengarahDAO.findById(pihakPengarah.getIdPengarah());

        Pihak pihakTemp = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());

        pihakPengarahTemp.setNama(pihakPengarah.getNama());
        pihakPengarahTemp.setJenisPengenalan(pihakPengarah.getJenisPengenalan());
        pihakPengarahTemp.setNoPengenalan(pihakPengarah.getNoPengenalan());
        pihakPengarahTemp.setJumlahSaham(pihakPengarah.getJumlahSaham());
        pihakPengarahTemp.setAlamat1(pihakPengarah.getAlamat1());
        pihakPengarahTemp.setAlamat2(pihakPengarah.getAlamat2());
        pihakPengarahTemp.setAlamat3(pihakPengarah.getAlamat3());
        pihakPengarahTemp.setAlamat4(pihakPengarah.getAlamat4());
        pihakPengarahTemp.setPoskod(pihakPengarah.getPoskod());

        KodNegeri kn = kodNegeriDAO.findById(kod);
        pihakPengarahTemp.setKodNegeri(kn);

        pelupusanService.saveOrUpdate(pihakPengarahTemp, pihakTemp);

        rehydrate();
        addSimpleMessage("Maklumat Berjaya Disimpan");
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/edit_ahil_lembaga_pengarah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEditSuamiIsteri() {

        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (validationEditIsteri()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/edit_suami.jsp").addParameter("tab", "true");
        }
        if (pemohonHbgn != null) {
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            PemohonHubungan pemohonHubunganTemp = pemohonHubunganDAO.findById(pemohonHbgn.getIdHubungan());
            pemohonHubunganTemp.setNama(pemohonHbgn.getNama());
            pemohonHubunganTemp.setTarikhLahir(pemohonHbgn.getTarikhLahir());
            pemohonHubunganTemp.setTempatLahir(pemohonHbgn.getTempatLahir());
            if (pemohonHbgn.getPekerjaan().equalsIgnoreCase("TIDAK BEKERJA")) {
                pemohonHubunganTemp.setPekerjaan("TIDAK BEKERJA");
                pemohonHubunganTemp.setGaji(BigDecimal.ZERO);
            } else {
                pemohonHubunganTemp.setPekerjaan(pemohonHbgn.getPekerjaan());
                pemohonHubunganTemp.setGaji(pemohonHbgn.getGaji());
            }
            pemohonHubunganTemp.setUmur(pemohonHbgn.getUmur());
//            pemohonHubunganTemp.setKaitan(pemohonHbgn.getKaitan());
            pemohonHubunganTemp.setAlamat1(pemohonHbgn.getAlamat1());
            pemohonHubunganTemp.setAlamat2(pemohonHbgn.getAlamat2());
            pemohonHubunganTemp.setAlamat3(pemohonHbgn.getAlamat3());
            pemohonHubunganTemp.setAlamat4(pemohonHbgn.getAlamat4());
            pemohonHubunganTemp.setPoskod(pemohonHbgn.getPoskod());
            pemohonHubunganTemp.setWarganegara(pemohonHbgn.getWarganegara());
            KodNegeri kn = kodNegeriDAO.findById(pemohonHbgn.getNegeri().getKod());
            pemohonHubunganTemp.setNegeri(kn);
            pelupusanService.saveOrUpdateHbgn(pemohonHubunganTemp);


        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/edit_suami.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEditIbuBapa() {

        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (validationEditIbuBapa()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/edit_Ibu_Bapa.jsp").addParameter("tab", "true");
        }
        if (pemohonHbgn != null) {
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            PemohonHubungan pemohonHubunganTemp = pemohonHubunganDAO.findById(pemohonHbgn.getIdHubungan());
            pemohonHubunganTemp.setNama(pemohonHbgn.getNama());
            pemohonHubunganTemp.setTarikhLahir(pemohonHbgn.getTarikhLahir());
            pemohonHubunganTemp.setTempatLahir(pemohonHbgn.getTempatLahir());
            pemohonHubunganTemp.setPekerjaan(pemohonHbgn.getPekerjaan());
            pemohonHubunganTemp.setUmur(pemohonHbgn.getUmur());
            pemohonHubunganTemp.setTelahMeninggal(pemohonHbgn.getTelahMeninggal());
            pemohonHubunganTemp.setTarikhMati(pemohonHbgn.getTarikhMati());
            pemohonHubunganTemp.setGaji(pemohonHbgn.getGaji());
            pemohonHubunganTemp.setAlamat1(pemohonHbgn.getAlamat1());
            pemohonHubunganTemp.setAlamat2(pemohonHbgn.getAlamat2());
            pemohonHubunganTemp.setAlamat3(pemohonHbgn.getAlamat3());
            pemohonHubunganTemp.setAlamat4(pemohonHbgn.getAlamat4());
            pemohonHubunganTemp.setPoskod(pemohonHbgn.getPoskod());
            pemohonHubunganTemp.setWarganegara(pemohonHbgn.getWarganegara());
            KodNegeri kn = kodNegeriDAO.findById(pemohonHbgn.getNegeri().getKod());
            pemohonHubunganTemp.setNegeri(kn);
            pelupusanService.saveOrUpdateHbgn(pemohonHubunganTemp);

        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/edit_Ibu_Bapa.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEditAnak() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findById(idPermohonan);

        if (validation()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/edit_Anak.jsp").addParameter("tab", "true");
        }

        if (pemohonHbgn != null) {
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pguna);
            pemohonHbgn.setKaitan("ANAK");
            pemohonHbgn.setPemohon(pemohon);
            pemohonHbgn.setInfoAudit(infoAudit);
            pemohonHbgn.setCawangan(permohonan.getCawangan());
            infoAudit.setTarikhKemaskini(new java.util.Date());
            PemohonHubungan pemohonHubunganTemp = pemohonHubunganDAO.findById(pemohonHbgn.getIdHubungan());
            pemohonHubunganTemp.setKodJantina(pemohonHbgn.getKodJantina());
            pemohonHubunganTemp.setNama(pemohonHbgn.getNama());
            pemohonHubunganTemp.setUmur(pemohonHbgn.getUmur());
            pemohonHubunganTemp.setNoPengenalan(pemohonHbgn.getNoPengenalan());

            pemohonHubunganTemp.setInstitusi(pemohonHbgn.getInstitusi());
            pelupusanService.saveOrUpdateHbgn(pemohonHubunganTemp);
        }

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/edit_Anak.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEditPemohon() {
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        if (!permohonan.getKodUrusan().getKod().equals("BMBT")) {

            if (EditPemohonValidation(permohonan.getKodUrusan().getKod())) {
                idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

                getContext().getRequest().setAttribute("flag", Boolean.TRUE);
                return new JSP("pelupusan/edit_pemohon1.jsp").addParameter("tab", "true");
            }
        }

        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
        KodBangsa kodBangsa1 = kodBangsaDAO.findById(kodBangsa);
        pihakTemp.setBangsa(kodBangsa1);

        KodNegeri kodn = kodNegeriDAO.findById(suratNegeri);
        pihakTemp.setSuratNegeri(kodn);

        if (pihakTemp.getJenisPengenalan().getKod().equals("B")) {
            pihakTemp.setWarnaKP(pihak.getWarnaKP());
        }

        String sub = idPermohonan.substring(0, 2);
        if (sub.equals("04")) {
            pihakTemp.setAnakTempatan(pihak.getAnakTempatan());
        }

        if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S")) {
        } else {
            KodNegeri kodIn = kodNegeriDAO.findById(institusiNegeri);
            if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                KodBank kodBank = kodBankDAO.findById(kodBan);
                pihakTemp.setBank(kodBank);
            }


            pemohon.setInstitusiNegeri(kodIn);
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefon1());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            pihakTemp.setAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setPoskod(pihak.getSuratPoskod());
            pihakTemp.setNegeri(pihak.getSuratNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            KodNegeri kn = kodNegeriDAO.findById(kodNegeri);
            pihakTemp.setNegeri(kn);
            KodWarganegara kodWarganegara = kodWarganegaraDAO.findById(kodW);
            pihakTemp.setWargaNegara(kodWarganegara);

        }
        pelupusanService.saveOrUpdate(pihakTemp);

        Pemohon pmohon = pelupusanService.findPemohonByPermohonanPihak(p, pihakTemp);
        infoAudit = new InfoAudit();
        infoAudit = pmohon.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        pmohon.setInfoAudit(infoAudit);
        pmohon.setKaitan(pemohon.getKaitan());
        if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S")) {
        } else {
            pmohon.setStatusKahwin(pemohon.getStatusKahwin());
        }

        if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {
            if (pemohon.getPekerjaan() == null || pemohon.getPekerjaan().equalsIgnoreCase("TIDAK BEKERJA")) {
                pemohon.setPekerjaan("TIDAK BEKERJA");
                pemohon.setPendapatan(BigDecimal.ZERO);
            } else {
                pmohon.setPekerjaan(pemohon.getPekerjaan());
                pmohon.setPendapatan(pemohon.getPendapatan());
            }
            pmohon.setUmur(pemohon.getUmur());
            pmohon.setTanggungan(pemohon.getTanggungan());
            if (sub.equals("04")) {
                pmohon.setTempohMastautin(pemohon.getTempohMastautin());
            }
            pmohon.setInstitusiNegeri(pemohon.getInstitusiNegeri());
        }
        pelupusanService.saveOrUpdate(pmohon);

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/edit_pemohon1.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEditPemohonBatuan() {
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        if (permohonan == null) {
            permohonan = p;
        }
        if (EditPemohonBatuanValidation(permohonan.getKodUrusan().getKod())) {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/batuan/edit_pemohonBatuan.jsp").addParameter("tab", "true");
        }


        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
        KodBangsa kodBangsa1 = kodBangsaDAO.findById(kodBangsa);
        pihakTemp.setBangsa(kodBangsa1);

        KodNegeri kodn = kodNegeriDAO.findById(suratNegeri);
        pihakTemp.setSuratNegeri(kodn);

        if (pihakTemp.getJenisPengenalan().getKod().equals("B")) {
            pihakTemp.setWarnaKP(pihak.getWarnaKP());
        }

        String sub = idPermohonan.substring(0, 2);
        if (sub.equals("04")) {
            pihakTemp.setAnakTempatan(pihak.getAnakTempatan());
        }

        if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S")) {
        } else {
            KodNegeri kodIn = kodNegeriDAO.findById(institusiNegeri);
            if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                KodBank kodBank;
                if (kodBan != null) {
                    kodBank = kodBankDAO.findById(kodBan);
                    pihakTemp.setBank(kodBank);
                }
            }


            pemohon.setInstitusiNegeri(kodIn);
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            KodNegeri kn = kodNegeriDAO.findById(kodNegeri);
            pihakTemp.setNegeri(kn);
            KodWarganegara kodWarganegara = kodWarganegaraDAO.findById(kodW);
            pihakTemp.setWargaNegara(kodWarganegara);

        }
        pelupusanService.saveOrUpdate(pihakTemp);

        Pemohon pmohon = pelupusanService.findPemohonByPermohonanPihak(p, pihakTemp);
        infoAudit = new InfoAudit();
        infoAudit = pmohon.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        pmohon.setInfoAudit(infoAudit);
        pmohon.setKaitan(pemohon.getKaitan());
        if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S")) {
        } else {
            pmohon.setStatusKahwin(pemohon.getStatusKahwin());
        }

        if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {
            pmohon.setPekerjaan(pemohon.getPekerjaan());
            pmohon.setUmur(pemohon.getUmur());
            pmohon.setPendapatan(pemohon.getPendapatan());
            pmohon.setTanggungan(pemohon.getTanggungan());
            if (sub.equals("04")) {
                pmohon.setTempohMastautin(pemohon.getTempohMastautin());
            }
            pmohon.setInstitusiNegeri(pemohon.getInstitusiNegeri());
        }
        pelupusanService.saveOrUpdate(pmohon);

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/batuan/edit_pemohonBatuan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanCawanganPemohon() {

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pelupusanService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/pelupusan/maklumat_pemohon1?getTambahCawanganPemohon&idPihak=" + id).addParameter("popup", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        cawanganList = pihak.getSenaraiCawangan();
        return new JSP("pelupusan/tambah_pemohon1.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganPemohon() {
        String idCaw = getContext().getRequest().getParameter("idCawangan");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());
        return new JSP("pelupusan/tambah_pemohon1.jsp").addParameter("popup", "true");
    }

    public Resolution deletePemohonTanah() {
        String idTanah = getContext().getRequest().getParameter("idTanah");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");

        if (idTanah != null) {
            PemohonTanah pmohon = pelupusanService.findByIdPemohonTanah(idTanah);
            if (idTanah != null) {
                pelupusanService.deletePemohonTanah(pmohon);
                pemohonLuarNegeri1 = pelupusanService.findPemohonTanahByIDPemohon(idPemohon);
            }
        }
        rehydrate();
        return showForm();
    }

    public Resolution deletePemohon() {
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pmohon = pelupusanService.findByIdPemohon(idPemohon);
            if (pmohon != null) {
                pelupusanService.deletePemohon(pmohon);
                pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
        rehydrate();
        return showForm();
    }

    public Resolution deletePemohonHbgn() {

        String idPemohonHbgn = getContext().getRequest().getParameter("idPemohonHbgn");
        if (idPemohonHbgn != null) {
            PemohonHubungan pemohonHubungan = pemohonHubunganDAO.findById(Long.parseLong(idPemohonHbgn));
            if (pemohonHubungan != null) {
                pelupusanService.deletePemohonHbgn(pemohonHubungan);

            }
        }
        rehydrate();
        return showForm();
    }

    public Resolution deletePihakPengarah() {
        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        if (idPengarah != null) {
            PihakPengarah pihakPengarah1 = pihakPengarahDAO.findById(Long.parseLong(idPengarah));
            if (pihakPengarah1 != null) {
                pelupusanService.deletePihakPengarah(pihakPengarah1);
            }
        }
        rehydrate();
        return showForm();
    }

    public Resolution deleteCawanganPemohon() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        cariFlag = true;

        if (idCaw != null) {

            PihakCawangan pihakCaw = cawanganService.findById(idCaw);
            Long id = pihakCaw.getIbupejabat().getIdPihak();

            if (pihakCaw != null) {
                cawanganService.delete(pihakCaw);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
            }
        }
        return new JSP("pelupusan/tambah_pemohon1.jsp").addParameter("popup", "true");
    }

    public Resolution backCawanganPemohon() {

        pihak = pelupusanService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        cawanganList = pihak.getSenaraiCawangan();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        cariFlag = true;
        tiadaDataFlag = false;
        return new JSP("pelupusan/tambah_pemohon1.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohonBatuan() {
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pelupusanService.findPemohonByPermohonanPihak(p, pihak);
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        LOG.info("idPermohonan :" + idPermohonan);
        LOG.info("kodUrusan :" + permohonan.getKodUrusan().getKod());
        if (pihak.getWargaNegara() != null) {
            kodW = pihak.getWargaNegara().getKod();
        }

        if (pihak.getSuratNegeri() != null) {
            suratNegeri = pihak.getSuratNegeri().getKod();
        }

        if (pihak.getNegeri() != null) {
            kodNegeri = pihak.getNegeri().getKod();
        }

        if (pihak.getBangsa() != null) {
            kodBangsa = pihak.getBangsa().getKod();
        }
        kod = "";
        if (pihak.getNegeri() != null) {
            kod = pihak.getNegeri().getKod();
        }

        return new JSP("pelupusan/batuan/edit_pemohonBatuan.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pelupusanService.findPemohonByPermohonanPihak(p, pihak);
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        LOG.info("idPermohonan :" + idPermohonan);
        LOG.info("kodUrusan :" + permohonan.getKodUrusan().getKod());
        if (pihak.getWargaNegara() != null) {
            kodW = pihak.getWargaNegara().getKod();
        }

        if (pihak.getSuratNegeri() != null) {
            suratNegeri = pihak.getSuratNegeri().getKod();
        }

        if (pihak.getNegeri() != null) {
            kodNegeri = pihak.getNegeri().getKod();
        }

        if (pihak.getBangsa() != null) {
            kodBangsa = pihak.getBangsa().getKod();
        }
        kod = "";
        if (pihak.getNegeri() != null) {
            kod = pihak.getNegeri().getKod();
        }

        return new JSP("pelupusan/edit_pemohon1.jsp").addParameter("popup", "true");
    }

    public Resolution showEditAnak() {

        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        pemohonHbgn = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/edit_Anak.jsp").addParameter("popup", "true");
    }

    public Resolution showEditSuamiIsteri() {

        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        pemohonHbgn = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));

        kodW = "";
        if (pemohonHbgn.getWarganegara().getKod() != null) {
            kodW = pemohonHbgn.getWarganegara().getKod();
        }

        return new JSP("pelupusan/edit_suami.jsp").addParameter("popup", "true");
    }

    public Resolution showEditIbuBapa() {

        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        pemohonHbgn = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));

        kodW = "";
        if (pemohonHbgn.getWarganegara().getKod() != null) {
            kodW = pemohonHbgn.getWarganegara().getKod();
        }

        return new JSP("pelupusan/edit_Ibu_Bapa.jsp").addParameter("popup", "true");
    }

    public Resolution showEditlembaga() {

        String idPengarah = getContext().getRequest().getParameter("idPengarah");

        pihakPengarah = pihakPengarahDAO.findById(Long.parseLong(idPengarah));

        kod = "";
        if (pihakPengarah.getPihak() != null) {
            if (pihakPengarah.getPihak().getNegeri() != null) {
                if (pihakPengarah.getPihak().getNegeri().getKod() != null) {
                    kod = pihakPengarah.getKodNegeri().getKod();
                }
            }
        }
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/edit_ahil_lembaga_pengarah.jsp").addParameter("popup", "true");
    }
    //Popup Pemohon Saudara

    public Resolution showMaklumatSaudara() {
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tanah_adat/tambah_saudara.jsp").addParameter("tab", "true");
    }
    //Kemaskini Pemohon Saudara

    public Resolution showEditSaudara() {

        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        pemohonHbgn = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));

        kodW = "";
        if (pemohonHbgn.getWarganegara().getKod() != null) {
            kodW = pemohonHbgn.getWarganegara().getKod();
        }

        return new JSP("pelupusan/tanah_adat/kemaskini_saudara.jsp").addParameter("popup", "true");
    }
    //Add Pemohon Saudara

    public Resolution simpanPemohonSaudara() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findById(idPermohonan);
        if (validationIbuBapa()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tanah_adat/tambah_saudara.jsp").addParameter("tab", "true");
        }
        if (pemohonHbgn != null) {
            if (tarikhLahir != null) {
                try {
                    pemohonHbgn.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                }
            }
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            pemohonHbgn.setPemohon(pemohon);
            pemohonHbgn.setInfoAudit(info);
            pemohonHbgn.setCawangan(permohonan.getCawangan());
            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        }

        if ((add1 != null) && (!add1.equals(""))) {
            String idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
            Pihak pihak1 = new Pihak();
            pihak1 = pemohon.getPihak();
            LOG.info("alamt 1 " + pihak1.getAlamat1().toString());
            LOG.info("alamt 1 " + pihak1.getAlamat2().toString());
            LOG.info("alamt 1 " + pihak1.getAlamat3().toString());
            pemohonHbgn.setAlamat1(pihak1.getAlamat1());
            pemohonHbgn.setAlamat2(pihak1.getAlamat2());
            pemohonHbgn.setAlamat3(pihak1.getAlamat3());
            pemohonHbgn.setAlamat4(pihak1.getAlamat4());
            pemohonHbgn.setPoskod(pihak1.getPoskod());
            pemohonHbgn.setNegeri(pihak1.getNegeri());
            pelupusanService.simpanPemohonHbgn(pemohonHbgn);
        }

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tanah_adat/tambah_saudara.jsp").addParameter("tab", "true");
    }
    //Edit Pemohon Saudara

    public Resolution simpanEditSaudara() {

        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (validationEditIbuBapa()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tanah_adat/kemaskini_saudara.jsp").addParameter("tab", "true");
        }
        if (pemohonHbgn != null) {
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            PemohonHubungan pemohonHubunganTemp = pemohonHubunganDAO.findById(pemohonHbgn.getIdHubungan());
            pemohonHubunganTemp.setNama(pemohonHbgn.getNama());
            pemohonHubunganTemp.setTarikhLahir(pemohonHbgn.getTarikhLahir());
            pemohonHubunganTemp.setTempatLahir(pemohonHbgn.getTempatLahir());
            pemohonHubunganTemp.setPekerjaan(pemohonHbgn.getPekerjaan());
            pemohonHubunganTemp.setUmur(pemohonHbgn.getUmur());
            pemohonHubunganTemp.setTelahMeninggal(pemohonHbgn.getTelahMeninggal());
            pemohonHubunganTemp.setTarikhMati(pemohonHbgn.getTarikhMati());
            pemohonHubunganTemp.setGaji(pemohonHbgn.getGaji());
            pemohonHubunganTemp.setAlamat1(pemohonHbgn.getAlamat1());
            pemohonHubunganTemp.setAlamat2(pemohonHbgn.getAlamat2());
            pemohonHubunganTemp.setAlamat3(pemohonHbgn.getAlamat3());
            pemohonHubunganTemp.setAlamat4(pemohonHbgn.getAlamat4());
            pemohonHubunganTemp.setPoskod(pemohonHbgn.getPoskod());
            pemohonHubunganTemp.setWarganegara(pemohonHbgn.getWarganegara());
            KodNegeri kn = kodNegeriDAO.findById(pemohonHbgn.getNegeri().getKod());
            pemohonHubunganTemp.setNegeri(kn);
            pelupusanService.saveOrUpdateHbgn(pemohonHubunganTemp);

        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tanah_adat/kemaskini_saudara.jsp").addParameter("tab", "true");
    }
    //Popup tanah adat pemohon

    public Resolution showTambahPemohonTanahAdat() {
        pemohonHbgn = new PemohonHubungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        LOG.info("PERMOHONAN :" + permohonan.getKodUrusan().getKod());
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tanah_adat/tambahPemohonTanahAdat.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohonTanahAdat() {
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pelupusanService.findPemohonByPermohonanPihak(p, pihak);
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        LOG.info("idPermohonan :" + idPermohonan);
        LOG.info("kodUrusan :" + permohonan.getKodUrusan().getKod());
        if (pihak.getWargaNegara() != null) {
            kodW = pihak.getWargaNegara().getKod();
        }

        if (pihak.getSuratNegeri() != null) {
            suratNegeri = pihak.getSuratNegeri().getKod();
        }

        if (pihak.getNegeri() != null) {
            kodNegeri = pihak.getNegeri().getKod();
        }

        if (pihak.getBangsa() != null) {
            kodBangsa = pihak.getBangsa().getKod();
        }
        if (pihak.getSuku() != null) {
            kodS = pihak.getSuku().getKod();
        }
        kod = "";
        if (pihak.getNegeri() != null) {
            kod = pihak.getNegeri().getKod();
        }

        return new JSP("pelupusan/tanah_adat/editPemohonTanahAdat.jsp").addParameter("popup", "true");
    }
    //Cari Pemohon Tanah Adat

    public Resolution cariPihakPemohonTanahAdat() {

        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        System.out.println("pihak" + pihak.getIdPihak());
        System.out.println("pihak" + pihak);
        LOG.info("PERMOHONAN2 :" + permohonan.getKodUrusan().getKod());
        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {

                Pihak pihakSearch = new Pihak();
                pihakSearch = pelupusanService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null) {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = pelupusanService.findById(idPermohonan);

                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                        addSimpleMessage("Maklumat Dijumpai");
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {

                if ((((pihak.getJenisPengenalan() == null) || (pihak.getJenisPengenalan().getKod().trim().equals("0")))) || (pihak.getNoPengenalan() == null)) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
                } else if ((pihak.getJenisPengenalan() == null) || (pihak.getJenisPengenalan().getKod().trim().equals("0"))) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        }

        return new JSP("pelupusan/tanah_adat/tambahPemohonTanahAdat.jsp").addParameter("popup", "true");
    }

    //Simpan Pemohon Tanah Adat
    public Resolution simpanPemohonTanahAdatPopup() throws ParseException {
        cariFlag = true;
        tiadaDataFlag = true;

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        LOG.info("THIS IS KODURUSAN:" + permohonan.getKodUrusan().getKod());
        if (Pemohonvalidation(permohonan.getKodUrusan().getKod())) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tanah_adat/tambahPemohonTanahAdat.jsp").addParameter("popup", "true");
        }

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = pelupusanService.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        Pihak pihakTemp = new Pihak();
        String idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
        if (idPihak != null && !idPihak.equals("0")) {
            pihakTemp = pihakDAO.findById(Long.parseLong(idPihak));
            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }


        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }


        pihakTemp.setInfoAudit(infoAudit);
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihakTemp);
        pmohon.setInfoAudit(infoAudit);
        pmohon.setCawangan(permohonan.getCawangan());

        if (idCawangan != null) {
            pihakCawangan = new PihakCawangan();
            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
            pmohon.setPihakCawangan(pihakCawangan);

        }

        if (pmohon != null) {

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());

            String sub = idPermohonan.substring(0, 2);
            if (sub.equals("04")) {
                pihakTemp.setAnakTempatan(pihak.getAnakTempatan());
            }
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setEmail(pihak.getEmail());
            pihakTemp.setTarikhLahir(pihak.getTarikhLahir());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());

            if ((pihak.getJenisPengenalan().getKod().equals("B"))) {
                pihakTemp.setWarnaKP(pihak.getWarnaKP());
            }


            if ((pihak.getJenisPengenalan().getKod().equals("B")) || (pihak.getJenisPengenalan().getKod().equals("L")) || (pihak.getJenisPengenalan().getKod().equals("P")) || (pihak.getJenisPengenalan().getKod().equals("T")) || (pihak.getJenisPengenalan().getKod().equals("I")) || (pihak.getJenisPengenalan().getKod().equals("F")) || (pihak.getJenisPengenalan().getKod().equals("O")) || (pihak.getJenisPengenalan().getKod().equals("N"))) {
                pmohon.setPekerjaan(pemohon.getPekerjaan());
                pmohon.setUmur(pemohon.getUmur());
                pmohon.setPendapatan(pemohon.getPendapatan());
                if (sub.equals("04")) {
                    pmohon.setTempohMastautin(pemohon.getTempohMastautin());
                }
                pmohon.setTanggungan(pemohon.getTanggungan());
                pmohon.setInstitusi(pemohon.getInstitusi());
                pmohon.setInstitusiAlamat1(pemohon.getInstitusiAlamat1());
                pmohon.setInstitusiAlamat2(pemohon.getInstitusiAlamat2());
                pmohon.setInstitusiAlamat3(pemohon.getInstitusiAlamat3());
                pmohon.setInstitusiAlamat4(pemohon.getInstitusiAlamat4());
                pmohon.setInstitusiPoskod(pemohon.getInstitusiPoskod());
                pmohon.setInstitusiNegeri(pemohon.getInstitusiNegeri());

            }

            if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S")) {
            } else {
                pmohon.setStatusKahwin(pemohon.getStatusKahwin());
            }
            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
            pelupusanService.simpanPihakPemohon(pihakTemp, pmohon);
            rehydrate();
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);

            return new JSP("pelupusan/tanah_adat/tambahPemohonTanahAdat.jsp").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    //Edit Pemohon Tanah Adat
    public Resolution simpanEditPemohonTanahAdat() {
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        if (EditPemohonValidation(permohonan.getKodUrusan().getKod())) {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tanah_adat/editpemohonTanahAdat.jsp").addParameter("tab", "true");
        }


        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
        KodBangsa kodBangsa1 = kodBangsaDAO.findById(kodBangsa);
        pihakTemp.setBangsa(kodBangsa1);

        KodNegeri kodn = kodNegeriDAO.findById(suratNegeri);
        pihakTemp.setSuratNegeri(kodn);

        if (pihakTemp.getJenisPengenalan().getKod().equals("B")) {
            pihakTemp.setWarnaKP(pihak.getWarnaKP());
        }

        String sub = idPermohonan.substring(0, 2);
        if (sub.equals("04")) {
            pihakTemp.setAnakTempatan(pihak.getAnakTempatan());
        }

        if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S")) {
        } else {
            KodNegeri kodIn = kodNegeriDAO.findById(institusiNegeri);
            if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                KodBank kodBank = kodBankDAO.findById(kodBan);
                pihakTemp.setBank(kodBank);
            }


            pemohon.setInstitusiNegeri(kodIn);
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            KodNegeri kn = kodNegeriDAO.findById(kodNegeri);
            pihakTemp.setNegeri(kn);
            KodWarganegara kodWarganegara = kodWarganegaraDAO.findById(kodW);
            pihakTemp.setWargaNegara(kodWarganegara);
            KodSuku kodSuku = kodSukuDAO.findById(kodS);
            pihakTemp.setSuku(kodSuku);

        }
        pelupusanService.saveOrUpdate(pihakTemp);

        Pemohon pmohon = pelupusanService.findPemohonByPermohonanPihak(p, pihakTemp);
        infoAudit = new InfoAudit();
        infoAudit = pmohon.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        pmohon.setInfoAudit(infoAudit);
        pmohon.setKaitan(pemohon.getKaitan());
        if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S")) {
        } else {
            pmohon.setStatusKahwin(pemohon.getStatusKahwin());
        }

        if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {
            pmohon.setPekerjaan(pemohon.getPekerjaan());
            pmohon.setUmur(pemohon.getUmur());
            pmohon.setPendapatan(pemohon.getPendapatan());
            pmohon.setTanggungan(pemohon.getTanggungan());
            if (sub.equals("04")) {
                pmohon.setTempohMastautin(pemohon.getTempohMastautin());
            }
            pmohon.setInstitusiNegeri(pemohon.getInstitusiNegeri());
        }
        pelupusanService.saveOrUpdate(pmohon);

        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/tanah_adat/editPemohonTanahAdat.jsp").addParameter("tab", "true");
    }

    public String stageId(String taskId, Pengguna pengguna) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public PihakCawangan getPihakCawangan() {
        return pihakCawangan;
    }

    public void setPihakCawangan(PihakCawangan pihakCawangan) {
        this.pihakCawangan = pihakCawangan;
    }

    public String getIdCawangan() {
        return idCawangan;
    }

    public void setIdCawangan(String idCawangan) {
        this.idCawangan = idCawangan;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public ArrayList getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(ArrayList hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<PemohonHubungan> getPemohonHubunganList() {
        return pemohonHubunganList;
    }

    public void setPemohonHubunganList(List<PemohonHubungan> pemohonHubunganList) {
        this.pemohonHubunganList = pemohonHubunganList;
    }

    public List<PemohonHubungan> getPemohonHubunganList1() {
        return pemohonHubunganList1;
    }

    public void setPemohonHubunganList1(List<PemohonHubungan> pemohonHubunganList1) {
        this.pemohonHubunganList1 = pemohonHubunganList1;
    }

    public List<PemohonHubungan> getPemohonHubunganList2() {
        return pemohonHubunganList2;
    }

    public void setPemohonHubunganList2(List<PemohonHubungan> pemohonHubunganList2) {
        this.pemohonHubunganList2 = pemohonHubunganList2;
    }

    public PemohonHubungan getPemohonHbgn() {
        return pemohonHbgn;
    }

    public void setPemohonHbgn(PemohonHubungan pemohonHbgn) {
        this.pemohonHbgn = pemohonHbgn;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PihakPengarah> getPihakPengarahList() {
        return pihakPengarahList;
    }

    public void setPihakPengarahList(List<PihakPengarah> pihakPengarahList) {
        this.pihakPengarahList = pihakPengarahList;
    }

    public PihakPengarah getPihakPengarah() {
        return pihakPengarah;
    }

    public void setPihakPengarah(PihakPengarah pihakPengarah) {
        this.pihakPengarah = pihakPengarah;
    }

    public Pihak getPihak1() {
        return pihak1;
    }

    public void setPihak1(Pihak pihak1) {
        this.pihak1 = pihak1;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getIdPengarah() {
        return idPengarah;
    }

    public void setIdPengarah(String idPengarah) {
        this.idPengarah = idPengarah;
    }

    public String getKodW() {
        return kodW;
    }

    public void setKodW(String kodW) {
        this.kodW = kodW;
    }

    public String getKodBan() {
        return kodBan;
    }

    public void setKodBan(String kodBan) {
        this.kodBan = kodBan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public Long getIdHubungan() {
        return idHubungan;
    }

    public void setIdHubungan(Long idHubungan) {
        this.idHubungan = idHubungan;
    }

    public Date getTarik() {
        return tarik;
    }

    public void setTarik(Date tarik) {
        this.tarik = tarik;
    }

    public String getKod1() {
        return kod1;
    }

    public void setKod1(String kod1) {
        this.kod1 = kod1;
    }

    public String getKodBangsa() {
        return kodBangsa;
    }

    public void setKodBangsa(String kodBangsa) {
        this.kodBangsa = kodBangsa;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalan() {
        return senaraiJenisPengenalan;
    }

    public void setSenaraiJenisPengenalan(List<KodJenisPengenalan> senaraiJenisPengenalan) {
        this.senaraiJenisPengenalan = senaraiJenisPengenalan;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public Long getHbgn() {
        return hbgn;
    }

    public void setHbgn(Long hbgn) {
        this.hbgn = hbgn;
    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalanPelupusan() {
        return senaraiJenisPengenalanPelupusan;
    }

    public void setSenaraiJenisPengenalanPelupusan(List<KodJenisPengenalan> senaraiJenisPengenalanPelupusan) {
        this.senaraiJenisPengenalanPelupusan = senaraiJenisPengenalanPelupusan;
    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalanPihakPengarah() {
        return senaraiJenisPengenalanPihakPengarah;
    }

    public void setSenaraiJenisPengenalanPihakPengarah(List<KodJenisPengenalan> senaraiJenisPengenalanPihakPengarah) {
        this.senaraiJenisPengenalanPihakPengarah = senaraiJenisPengenalanPihakPengarah;
    }

    public String getInstitusiNegeri() {
        return institusiNegeri;
    }

    public void setInstitusiNegeri(String institusiNegeri) {
        this.institusiNegeri = institusiNegeri;
    }

    public List<PemohonTanah> getPemohonLuarNegeri1() {
        return pemohonLuarNegeri1;
    }

    public void setPemohonLuarNegeri1(List<PemohonTanah> pemohonLuarNegeri1) {
        this.pemohonLuarNegeri1 = pemohonLuarNegeri1;
    }

    public PemohonTanah getPemohonTanah() {
        return pemohonTanah;
    }

    public void setPemohonTanah(PemohonTanah pemohonTanah) {
        this.pemohonTanah = pemohonTanah;
    }

    public List<PemohonHubungan> getPemohonHubunganSaudara() {
        return pemohonHubunganSaudara;
    }

    public void setPemohonHubunganSaudara(List<PemohonHubungan> pemohonHubunganSaudara) {
        this.pemohonHubunganSaudara = pemohonHubunganSaudara;
    }

    public String getKodS() {
        return kodS;
    }

    public void setKodS(String kodS) {
        this.kodS = kodS;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }
}

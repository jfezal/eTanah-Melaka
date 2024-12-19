/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodTanah;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodRizab;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodNegeri;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.TanahRizabPermohonan;
import etanah.service.LaporanPelukisPelanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.JupemService;
import org.apache.commons.lang.StringUtils;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.FasaPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.model.IntegrasiPermohonan;
import etanah.model.KodUrusan;
import etanah.model.PermohonanManual;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Query;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikService;
import etanah.view.penguatkuasaan.BayaranKompaunActionBean;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/maklumat_tambahan")
public class MaklumatTambahanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanLaporanPelanDAO permohonanlaporanpelanDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    JupemService service;
    @Inject
    private etanah.Configuration conf;
    @Inject
    IntegrasiPermohonanDAO integrasiPermohonanDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private KodRizab tanahrizab;
    private KodHakmilik kodHakmilik;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private String noLot;
    private String noLitho;
    private String projekKerajaan;
    private String noWarta;
    private String noWartaPP;
    private String catatan;
    private Date tarikhWarta;
    private List<KodBandarPekanMukim> senaraiBPM;
    private List<TanahRizabPermohonan> senaraiTanahAA;
    private TanahRizabPermohonan tanahrizabpermohonan;
    private String noHakmilik;
    private String noLPS;
    private String idHakmilik;
    private String idPermohonanSebelum;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Long id;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Permohonan> permohonanTerdahuluList;
    private List<Permohonan> permohonanSebelumList = new ArrayList<Permohonan>();
    Long idTanahRizabPermohonan;
    private int size = 0;
    private List<TanahRizabPermohonan> tanahRizabList;
    private List<Permohonan> permohonanList;
    private IntegrasiPermohonan integrasiPermohonan;
    private List<IntegrasiPermohonanButir> senaraiButiran;
    private IntegrasiPermohonanButir integrasiPermohonanButir;
    private KodUrusan mohonKodUrusan;
    private String stageId;
    private String noFail;
    private String keseluruhan;
    private PermohonanManual permohonanManual;
    private Pengguna peng;
    private List<PermohonanManual> permohonanManualList;
    private List<String> diambilHakmilikPermohonan = new ArrayList<String>();
    private List<String> catatanHakmilikPermohonan = new ArrayList<String>();
    private List<String> diambilTanahRizab = new ArrayList<String>();
    private List<String> catatanTanahRizab = new ArrayList<String>();
    private List<String> diambilTanahAA = new ArrayList<String>();
    private List<String> catatanTanahAA = new ArrayList<String>();
    private String kodNegeri;
    private String namaPihakBerkuasa;
    private String noWartaPihakBerkuasa;
    private String kodtanah;
    private List<PermohonanLaporanPelan> lithoList;
    private TanahRizabPermohonan rizab;
    private static final Logger LOG = Logger.getLogger(MaklumatTambahanActionBean.class);
    String idTanahRizab;
    private boolean showSimpan = true;
    private boolean showEdit = false;
    private String bPekanMukim;
    @Inject
    HakmilikService hakmilikservice;

    public String getBPekanMukim() {
        return bPekanMukim;
    }

    public void setBPekanMukim(String bPekanMukim) {
        this.bPekanMukim = bPekanMukim;
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("formPP", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("viewPP", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("viewPP", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/maklumat_tambahan2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("viewPP", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/maklumat_tambahan3.jsp").addParameter("tab", "true");
    }

    public Resolution tanahRizabPopup() {
        return new JSP("pengambilan/melaka/seksyen4/tanah_rizab_add.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution tanahKRPopup() {
        rizab = new TanahRizabPermohonan();
        return new JSP("pengambilan/melaka/seksyen4/tanah_rizab_add.jsp").addParameter("popup", "true").addParameter("showForm", "true");
    }

    public Resolution tanahMilikPopup() {
        return new JSP("pengambilan/melaka/seksyen4/tanah_milik_add.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution permohonanTerdahuluPopup() {
        return new JSP("pengambilan/melaka/seksyen4/permohonan_terdahulu_add.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution showEdittanahAA() {
        String idTanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = tanahrizabPermohonanDAO.findById(Long.parseLong(idTanahRizabPermohonan));
        tanahrizab = tanahrizabpermohonan.getRizab();
        cawangan = tanahrizabpermohonan.getCawangan();
        daerah = tanahrizabpermohonan.getDaerah();
        bandarPekanMukim = tanahrizabpermohonan.getBandarPekanMukim();
        noLot = tanahrizabpermohonan.getNoLot();
        noWarta = tanahrizabpermohonan.getNoWarta();
        tarikhWarta = tanahrizabpermohonan.getTarikhWarta();
        return new JSP("pengambilan/tanahAAppl.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        keseluruhan = "";
        stageId(taskId);
        List<HakmilikPermohonan> keseluruhanList;
        List<HakmilikPermohonan> sebahagianList;

        kodNegeri = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            tanahRizabList = hakmilikpermohonanservice.findIdHakmilikTanahXAA(idPermohonan);
            senaraiTanahAA = hakmilikpermohonanservice.findIdHakmilikTanahAA(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            permohonanLaporanPelan = laporanPelukisPelanService.findByidP(idPermohonan);
            permohonanManualList = laporanPelukisPelanService.findPermohonanManualByidPermohonan(idPermohonan);
            lithoList = laporanPelukisPelanService.findNolitho(idPermohonan);
//            System.out.println("permohonanLaporanPelan"+permohonanLaporanPelan.getPermohonan().getIdPermohonan());
            sebahagianList = hakmilikservice.findMHSebahagian(permohonan.getIdPermohonan());
            keseluruhanList = hakmilikservice.findMHKeseluruhan(permohonan.getIdPermohonan());
            if (keseluruhanList.size() > 0) {
                keseluruhan = "Y";
            }
            if (permohonanLaporanPelan != null) {
//                    noLitho = permohonanLaporanPelan.getNoLitho();`
                catatan = permohonanLaporanPelan.getCatatan();
                projekKerajaan = permohonanLaporanPelan.getProjekKerajaan();
                noWartaPP = permohonanLaporanPelan.getNoWarta();
                namaPihakBerkuasa = permohonanLaporanPelan.getNamaPihakBerkuasa();
                noWartaPihakBerkuasa = permohonanLaporanPelan.getNoWartaPihakBerkuasa();
                kodtanah = permohonanLaporanPelan.getKodTanah().getKod();
                projekKerajaan = permohonanLaporanPelan.getProjekKerajaan();
            }

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hakmilikPermohonan = hakmilikPermohonanList.get(i);
                if (hakmilikPermohonan.getTanahDiambil() == null) {
                    diambilHakmilikPermohonan.add("0");
                } else {
                    diambilHakmilikPermohonan.add(hakmilikPermohonan.getTanahDiambil());
                }
                if (hakmilikPermohonan.getCatatan() == null) {
                    catatanHakmilikPermohonan.add("");
                } else {
                    catatanHakmilikPermohonan.add(hakmilikPermohonan.getCatatan());
                }
            }

            for (int i = 0; i < tanahRizabList.size(); i++) {
                TanahRizabPermohonan tanahRizabPermohonan = tanahRizabList.get(i);
                if (tanahRizabPermohonan.getTanahDiambil() == null) {
                    diambilTanahRizab.add("0");
                } else {
                    diambilTanahRizab.add(tanahRizabPermohonan.getTanahDiambil());
                }
                if (tanahRizabPermohonan.getCatatan() == null) {
                    catatanTanahRizab.add("");
                } else {
                    catatanTanahRizab.add(tanahRizabPermohonan.getCatatan());
                }
            }

            for (int i = 0; i < senaraiTanahAA.size(); i++) {
                TanahRizabPermohonan tanahRizabPermohonan = senaraiTanahAA.get(i);
                if (tanahRizabPermohonan.getTanahDiambil() == null) {
                    diambilTanahAA.add("0");
                } else {
                    diambilTanahAA.add(tanahRizabPermohonan.getTanahDiambil());
                }
                if (tanahRizabPermohonan.getCatatan() == null) {
                    catatanTanahAA.add("");
                } else {
                    catatanTanahAA.add(tanahRizabPermohonan.getCatatan());
                }
            }

        }
    }

    public Resolution penyukatanBPMAA() {
        String kodDaerah = getKodDaerah();
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        senaraiBPM = q.list();
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/tanahAAppl.jsp").addParameter("popup", "true");

    }

    public Resolution penyukatanBPM() {
        LOG.info("masuk sini");
        String kodDaerah = getKodDaerah();
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        senaraiBPM = q.list();
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/tanah_rizab_add.jsp").addParameter("popup", "true");

    }

//      public Resolution penyukatanBPM() {
//        String kodDaerah = getKodDaerah();
//        String sql = null;
//        Session s = sessionProvider.get();
//        Query q = null;
//        if (kodDaerah == null || kodDaerah.equals("")) {
//            sql = "select bpm from KodBandarPekanMukim bpm ";
//            q = s.createQuery(sql);
//        } else {
//            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
//            q = s.createQuery(sql);
//            q.setString("kod", kodDaerah);
//        }
//        senaraiBPM = q.list();
//        return new ForwardResolution("pengambilan/melaka/seksyen4/tanah_rizab_add.jsp").addParameter("popup", "true");
//
//    }
//    public Resolution showEditTanahRizab()  {
//        String idTanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
//        tanahrizabpermohonan=tanahrizabPermohonanDAO.findById(Long.parseLong(idTanahRizabPermohonan));
//        rizab = tanahrizabpermohonan.getRizab();
//        cawangan = tanahrizabpermohonan.getCawangan();
//        daerah = tanahrizabpermohonan.getDaerah();
//        bandarPekanMukim = tanahrizabpermohonan.getBandarPekanMukim();
//        noLot = tanahrizabpermohonan.getNoLot();
//        noWarta = tanahrizabpermohonan.getNoWarta();
//        tarikhWarta = tanahrizabpermohonan.getTarikhWarta();
//        return new JSP("pengambilan/melaka/seksyen4/tanah_rizab_edit.jsp").addParameter("popup", "true");
//    }
    public Resolution showEdittanahKR() {
        String idTanahRizabPermohonan2 = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        rizab = tanahrizabPermohonanDAO.findById(Long.parseLong(idTanahRizabPermohonan2));
        if (rizab.getDaerah() != null) {
            senaraiBPM = laporanPelukisPelanService.cariBPM(rizab.getDaerah().getKod());
        }
//        bPekanMukim = rizab.getBandarPekanMukim().getNama();
        showSimpan = false;
        showEdit = true;
        return new JSP("pengambilan/melaka/seksyen4/tanah_rizab_add.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPermohonanTerdahulu() {
        String idMohonManual = getContext().getRequest().getParameter("idMohonManual");
        permohonanManual = permohonanManualDAO.findById(Long.parseLong(idMohonManual));
        if (permohonanManual != null) {
            noFail = permohonanManual.getNoFail();
        }
        return new JSP("pengambilan/melaka/seksyen4/permohonan_terdahulu_edit.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(MaklumatTambahanActionBean.class, "locate");
    }

    public Resolution simpanTanahMilik() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikDAO.findById(idHakmilik);

        if (idPermohonan != null) {
            hakmilikPermohonan = new HakmilikPermohonan();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            hakmilikPermohonan.setInfoAudit(info);
            hakmilikPermohonan.setPermohonan(permohonan);
            hakmilikPermohonan.setHakmilik(hakmilik);
            //hakmilikPermohonanList.add(hakmilikPermohonan);
            laporanPelukisPelanService.saveOrUpdateTanahMilik(hakmilikPermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTanahMilik() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idhp = getContext().getRequest().getParameter("id");
        hakmilikPermohonan = laporanPelukisPelanService.findTanahMilikByIdHakmilikPermohonan(idhp);

        if (hakmilikPermohonan != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPermohonan.setInfoAudit(ia);
            //hakmilikPermohonan.setHakmilik(hakmilik);
            laporanPelukisPelanService.deleteTanahMilik(hakmilikPermohonan);
        }
        return new RedirectResolution(MaklumatTambahanActionBean.class, "locate");
    }

//     public Resolution simpanTanahRizab() throws ParseException  {
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        InfoAudit info =  new InfoAudit();
//
//        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
//        if (idPermohonan != null) {
//            tanahrizabpermohonan = new TanahRizabPermohonan();
//            info.setDimasukOleh(peng);
//            info.setTarikhMasuk(new java.util.Date());
//
//            tanahrizabpermohonan.setInfoAudit(info);
//            tanahrizabpermohonan.setPermohonan(permohonan);
//            tanahrizabpermohonan.setRizab(tanahrizab);
//            tanahrizabpermohonan.setCawangan(cawangan);
//            tanahrizabpermohonan.setDaerah(daerah);
//            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
//            tanahrizabpermohonan.setNoLot(noLot);
//            tanahrizabpermohonan.setNoWarta(noWarta);
//            tanahrizabpermohonan.setTarikhWarta(tarikhWarta);
//
//            tanahRizabList.add(tanahrizabpermohonan);
//            laporanPelukisPelanService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
//               addSimpleMessage("Maklumat telah berjaya disimpan");
//        }
//
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
//    }
    public Resolution simpanTanahRizab() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        rizab.setInfoAudit(info);
        rizab.setPermohonan(permohonan);
        rizab.setCawangan(permohonan.getCawangan());
        laporanPelukisPelanService.save(rizab);
        addSimpleMessage("Maklumat telah berjaya disimpan");

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution editTanahRizab() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit info = new InfoAudit();

        String idR = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        LOG.info("id tanah rizab" + idR);
        rizab = tanahrizabPermohonanDAO.findById(Long.parseLong(idR));

        if (rizab != null) {
            info = rizab.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            rizab.setInfoAudit(info);
            rizab.setPermohonan(permohonan);
            rizab.setCawangan(permohonan.getCawangan());
            laporanPelukisPelanService.save(rizab);

        } else {

            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            rizab.setInfoAudit(info);
            rizab.setPermohonan(permohonan);
            rizab.setCawangan(permohonan.getCawangan());
            laporanPelukisPelanService.save(rizab);
        }
        addSimpleMessage("Maklumat telah berjaya dikemaskini");

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");


    }

    public Resolution deleteTanahRizab() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        tanahrizabpermohonan = new TanahRizabPermohonan();
        String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = laporanPelukisPelanService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));

        if (tanahrizabpermohonan != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(ia);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setRizab(tanahrizab);
            //tanahrizabpermohonan.setCatatan(catatan);
            laporanPelukisPelanService.deleteAll(tanahrizabpermohonan);
        }
        return new RedirectResolution(MaklumatTambahanActionBean.class, "locate");
    }

    public Resolution simpanPermohonanTerdahulu() throws ParseException {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        InfoAudit info = new InfoAudit();
        PermohonanManual permohonanManual = new PermohonanManual();

        if (idPermohonan != null) {
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanManual.setInfoAudit(info);
            permohonanManual.setNoFail(noFail);
            permohonanManual.setIdPermohonan(permohonan);
            permohonanManual.setCawangan(permohonan.getCawangan());
            laporanPelukisPelanService.saveOrUpdatePermohonanManual(permohonanManual);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanNoLitho() throws ParseException {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        lithoList = laporanPelukisPelanService.findNolitho(idPermohonan);
        System.out.println("noLithoList" + lithoList.size());

        InfoAudit info = new InfoAudit();
        PermohonanManual permohonanManual = new PermohonanManual();
        if (lithoList.size() >= 0) {
            permohonanLaporanPelan = new PermohonanLaporanPelan();
            KodTanah kodTanah = new KodTanah();
            kodTanah.setKod(kodtanah);
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanLaporanPelan.setInfoAudit(info);
            permohonanLaporanPelan.setPermohonan(permohonan);
            permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
            permohonanLaporanPelan.setNoLitho(noLitho);
            permohonanLaporanPelan.setKodTanah(kodTanah);
            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonanList.get(0));
            lithoList.add(permohonanLaporanPelan);
            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonanList.get(0));
            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }

        rehydrate();
        getContext().getRequest().setAttribute("formPP", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTanahRizabUlasan() throws ParseException {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        lithoList = laporanPelukisPelanService.findNolitho(idPermohonan);
        System.out.println("noLithoList" + lithoList.size());

        InfoAudit info = new InfoAudit();
        tanahRizabList = hakmilikpermohonanservice.findIdHakmilikTanahXAA(idPermohonan);
        if (!tanahRizabList.isEmpty()) {
            for (int i = 0; i < tanahRizabList.size(); i++) {
                TanahRizabPermohonan tanahRizabPermohonan = tanahRizabList.get(i);
                tanahRizabPermohonan.setTanahDiambil(diambilTanahRizab.get(i));
                tanahRizabPermohonan.setCatatan(catatanTanahRizab.get(i));
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                tanahRizabPermohonan.setInfoAudit(info);
                laporanPelukisPelanService.saveOrUpdatetanahRizab(tanahRizabPermohonan);
                addSimpleMessage("Maklumat telah berjaya disimpan");
            }

        }

        rehydrate();
        getContext().getRequest().setAttribute("formPP", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution editPermohonanTerdahulu() throws ParseException {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String idMohonManual = getContext().getRequest().getParameter("idMohonManual");
        permohonanManual = permohonanManualDAO.findById(Long.parseLong(idMohonManual));
        if (permohonanManual != null) {
            ia = permohonanManual.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanManual.setInfoAudit(ia);
            permohonanManual.setNoFail(noFail);
            laporanPelukisPelanService.saveOrUpdatePermohonanManual(permohonanManual);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");


    }

    public Resolution deletePermohonanTerdahulu() {
        String idMohonManual = getContext().getRequest().getParameter("idMohonManual");
        permohonanManual = permohonanManualDAO.findById(Long.parseLong(idMohonManual));

        if (permohonanManual != null) {
            laporanPelukisPelanService.deletePermohonanManual(permohonanManual);
        }
        return new RedirectResolution(MaklumatTambahanActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
            HakmilikPermohonan hakmilikPermohonan = hakmilikPermohonanList.get(i);
            hakmilikPermohonan.setTanahDiambil(diambilHakmilikPermohonan.get(i));
            hakmilikPermohonan.setCatatan(catatanHakmilikPermohonan.get(i));
            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonanList.get(0));
            laporanPelukisPelanService.saveOrUpdateTanahMilik(hakmilikPermohonan);
        }


        tanahRizabList = hakmilikpermohonanservice.findIdHakmilikTanahXAA(idPermohonan);
        for (int i = 0; i < tanahRizabList.size(); i++) {
            TanahRizabPermohonan tanahRizabPermohonan = tanahRizabList.get(i);
            tanahRizabPermohonan.setTanahDiambil(diambilTanahRizab.get(i));
            tanahRizabPermohonan.setCatatan(catatanTanahRizab.get(i));
            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonanList.get(0));
            laporanPelukisPelanService.saveOrUpdatetanahRizab(tanahRizabPermohonan);
        }


        if (permohonanLaporanPelan != null) {
            KodTanah kodTanah = new KodTanah();
            kodTanah.setKod(kodtanah);
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanLaporanPelan.setInfoAudit(info);
            permohonanLaporanPelan.setPermohonan(permohonan);
            permohonanLaporanPelan.setCawangan(cawangan);
//            permohonanLaporanPelan.setNoLitho(noLitho);
            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonanList.get(0));
            permohonanLaporanPelan.setProjekKerajaan(projekKerajaan);
            permohonanLaporanPelan.setCatatan(catatan);
            permohonanLaporanPelan.setNoWarta(noWartaPP);
            permohonanLaporanPelan.setKodTanah(kodTanah);
            permohonanLaporanPelan.setNoWartaPihakBerkuasa(noWartaPihakBerkuasa);
            permohonanLaporanPelan.setNamaPihakBerkuasa(namaPihakBerkuasa);
            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            permohonanLaporanPelan = new PermohonanLaporanPelan();
            KodTanah kodTanah = new KodTanah();
            kodTanah.setKod(kodtanah);
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanLaporanPelan.setInfoAudit(info);
            permohonanLaporanPelan.setPermohonan(permohonan);
            permohonanLaporanPelan.setCawangan(cawangan);
//            permohonanLaporanPelan.setNoLitho(noLitho);
            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonanList.get(0));
            permohonanLaporanPelan.setProjekKerajaan(projekKerajaan);
            permohonanLaporanPelan.setCatatan(catatan);
            permohonanLaporanPelan.setNoWarta(noWartaPP);
            permohonanLaporanPelan.setKodTanah(kodTanah);
            permohonanLaporanPelan.setNoWartaPihakBerkuasa(noWartaPihakBerkuasa);
            permohonanLaporanPelan.setNamaPihakBerkuasa(namaPihakBerkuasa);
            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        getContext().getRequest().setAttribute("formPP", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTanahAA() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        tanahrizabpermohonan.setInfoAudit(info);
        tanahrizabpermohonan.setPermohonan(permohonan);
        tanahrizabpermohonan.setCawangan(permohonan.getCawangan());
        KodRizab kd = new KodRizab();
        kd.setKod(88);
        tanahrizabpermohonan.setRizab(kd);
        laporanPelukisPelanService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public String stageId(String taskId) {
        BPelService service1 = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service1.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("stageId:" + stageId);
        }

        return stageId;
    }

    public Resolution deleteLitho() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonanLaporanPelan = new PermohonanLaporanPelan();
        String idLaporan = getContext().getRequest().getParameter("idLaporan");
        permohonanLaporanPelan = laporanPelukisPelanService.findLaporPelanByIdlapor(Long.parseLong(idLaporan));
        System.out.println("permohonan" + idLaporan);
        if (permohonanLaporanPelan != null) {
            laporanPelukisPelanService.deleteAllLitho(permohonanLaporanPelan);
        }
        rehydrate();
        getContext().getRequest().setAttribute("formPP", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/seksyen4/maklumat_tambahan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

//
//    public Long getIdTanahRizab() {
//        return idTanahRizabPermohonan;
//    }
//
//    public void setIdTanahRizab(Long idTanahRizabPermohonan) {
//        this.idTanahRizabPermohonan = idTanahRizabPermohonan;
//    }
    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public List<TanahRizabPermohonan> gettanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabPermohonanList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

//   public String getTarikhWarta() {
//        return tarikhWarta;
//    }
//
//    public void setTarikhWarta(String tarikhWarta) {
//        this.tarikhWarta = tarikhWarta;
//    }
    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public TanahRizabPermohonan getTanahrizabpermohonan() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizabpermohonan(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getNoLPS() {
        return noLPS;
    }

    public void setNoLPS(String noLPS) {
        this.noLPS = noLPS;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public Long getIdTanahRizabPermohonan() {
        return idTanahRizabPermohonan;
    }

    public void setIdTanahRizabPermohonan(Long idTanahRizabPermohonan) {
        this.idTanahRizabPermohonan = idTanahRizabPermohonan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public List<Permohonan> getPermohonanSebelumList() {
        return permohonanSebelumList;
    }

    public void setPermohonanSebelumList(List<Permohonan> permohonanSebelumList) {
        this.permohonanSebelumList = permohonanSebelumList;
    }

    public List<Permohonan> getPermohonanTerdahuluList() {
        return permohonanTerdahuluList;
    }

    public void setPermohonanTerdahuluList(List<Permohonan> permohonanTerdahuluList) {
        this.permohonanTerdahuluList = permohonanTerdahuluList;
    }

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public String getProjekKerajaan() {
        return projekKerajaan;
    }

    public void setProjekKerajaan(String projekKerajaan) {
        this.projekKerajaan = projekKerajaan;
    }

    public IntegrasiPermohonanButir getIntegrasiPermohonanButir() {
        return integrasiPermohonanButir;
    }

    public void setIntegrasiPermohonanButir(IntegrasiPermohonanButir integrasiPermohonanButir) {
        this.integrasiPermohonanButir = integrasiPermohonanButir;
    }

    public IntegrasiPermohonan getIntegrasiPermohonan() {
        return integrasiPermohonan;
    }

    public void setIntegrasiPermohonan(IntegrasiPermohonan integrasiPermohonan) {
        this.integrasiPermohonan = integrasiPermohonan;
    }

    public KodUrusan getMohonKodUrusan() {
        return mohonKodUrusan;
    }

    public void setMohonKodUrusan(KodUrusan mohonKodUrusan) {
        this.mohonKodUrusan = mohonKodUrusan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public List<PermohonanManual> getPermohonanManualList() {
        return permohonanManualList;
    }

    public void setPermohonanManualList(List<PermohonanManual> permohonanManualList) {
        this.permohonanManualList = permohonanManualList;
    }

    public List<String> getCatatanHakmilikPermohonan() {
        return catatanHakmilikPermohonan;
    }

    public void setCatatanHakmilikPermohonan(List<String> catatanHakmilikPermohonan) {
        this.catatanHakmilikPermohonan = catatanHakmilikPermohonan;
    }

    public List<String> getDiambilHakmilikPermohonan() {
        return diambilHakmilikPermohonan;
    }

    public void setDiambilHakmilikPermohonan(List<String> diambilHakmilikPermohonan) {
        this.diambilHakmilikPermohonan = diambilHakmilikPermohonan;
    }

    public PermohonanManual getPermohonanManual() {
        return permohonanManual;
    }

    public void setPermohonanManual(PermohonanManual permohonanManual) {
        this.permohonanManual = permohonanManual;
    }

    public List<String> getCatatanTanahRizab() {
        return catatanTanahRizab;
    }

    public void setCatatanTanahRizab(List<String> catatanTanahRizab) {
        this.catatanTanahRizab = catatanTanahRizab;
    }

    public List<String> getDiambilTanahRizab() {
        return diambilTanahRizab;
    }

    public void setDiambilTanahRizab(List<String> diambilTanahRizab) {
        this.diambilTanahRizab = diambilTanahRizab;
    }

    public List<String> getCatatanTanahAA() {
        return catatanTanahAA;
    }

    public void setCatatanTanahAA(List<String> catatanTanahAA) {
        this.catatanTanahAA = catatanTanahAA;
    }

    public List<String> getDiambilTanahAA() {
        return diambilTanahAA;
    }

    public void setDiambilTanahAA(List<String> diambilTanahAA) {
        this.diambilTanahAA = diambilTanahAA;
    }

    public List<TanahRizabPermohonan> getSenaraiTanahAA() {
        return senaraiTanahAA;
    }

    public void setSenaraiTanahAA(List<TanahRizabPermohonan> senaraiTanahAA) {
        this.senaraiTanahAA = senaraiTanahAA;
    }
    private String kodDaerah;

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public Date getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(Date tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNoWartaPP() {
        return noWartaPP;
    }

    public void setNoWartaPP(String noWartaPP) {
        this.noWartaPP = noWartaPP;
    }

    public String getNamaPihakBerkuasa() {
        return namaPihakBerkuasa;
    }

    public void setNamaPihakBerkuasa(String namaPihakBerkuasa) {
        this.namaPihakBerkuasa = namaPihakBerkuasa;
    }

    public String getNoWartaPihakBerkuasa() {
        return noWartaPihakBerkuasa;
    }

    public void setNoWartaPihakBerkuasa(String noWartaPihakBerkuasa) {
        this.noWartaPihakBerkuasa = noWartaPihakBerkuasa;
    }

    public String getKodtanah() {
        return kodtanah;
    }

    public void setKodtanah(String kodtanah) {
        this.kodtanah = kodtanah;
    }

    public List<PermohonanLaporanPelan> getLithoList() {
        return lithoList;
    }

    public void setLithoList(List<PermohonanLaporanPelan> lithoList) {
        this.lithoList = lithoList;
    }

    public String getIdTanahRizab() {
        return idTanahRizab;
    }

    public void setIdTanahRizab(String idTanahRizab) {
        this.idTanahRizab = idTanahRizab;
    }

    public TanahRizabPermohonan getRizab() {
        return rizab;
    }

    public void setRizab(TanahRizabPermohonan rizab) {
        this.rizab = rizab;
    }

    public KodRizab getTanahrizab() {
        return tanahrizab;
    }

    public void setTanahrizab(KodRizab tanahrizab) {
        this.tanahrizab = tanahrizab;
    }

    public boolean isShowEdit() {
        return showEdit;
    }

    public void setShowEdit(boolean showEdit) {
        this.showEdit = showEdit;
    }

    public boolean isShowSimpan() {
        return showSimpan;
    }

    public void setShowSimpan(boolean showSimpan) {
        this.showSimpan = showSimpan;
    }

    public String getKeseluruhan() {
        return keseluruhan;
    }

    public void setKeseluruhan(String keseluruhan) {
        this.keseluruhan = keseluruhan;
    }
}

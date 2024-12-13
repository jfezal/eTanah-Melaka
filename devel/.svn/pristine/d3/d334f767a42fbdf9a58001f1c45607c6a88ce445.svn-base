/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodRizab;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.TanahRizabPermohonan;
import etanah.service.BPelService;
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
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.dao.KodTanahDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.model.KodDUN;
import etanah.model.KodKawasanParlimen;
import etanah.model.NoPt;
import etanah.model.PermohonanManual;
import etanah.model.PermohonanPlotPelan;
import etanah.service.PembangunanService;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/laporan_pelukisPelan")
public class LaporanPelukisPelanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LaporanPelukisPelanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    private TanahRizabPermohonan tanahrizabpermohonan;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodTanahDAO ktDao;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodDUNDAO kodDUNDAO;
    @Inject
    KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    private Permohonan permohonan, permohonanTerdahulu;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private KodRizab rizab;
    private KodHakmilik kodHakmilik;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private String noLot;
    private String noLitho;
    private String projekKerajaan;
    private String noWarta;
    private String catatan;
    private String tarikhWarta;
    private String noHakmilik;
    private String noLPS;
    private String idHakmilik;
    private String idPermohonanSebelum;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pengguna pguna;
    private String idPermohonan;
    private String stageId;
    Long id;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Permohonan> permohonanTerdahuluList;
    private List<Permohonan> permohonanSebelumList = new ArrayList<Permohonan>();
    Long idTanahRizabPermohonan;
    private int size = 0;
    private List<TanahRizabPermohonan> tanahRizabList;
    private String negeri;
    private String kodD;
    private String kodPar;
    private Long noPTSementara = 0L;
    private List<String> noLotList = new ArrayList<String>();
    private PermohonanManual permohonanManual;
    private PermohonanManual permohonanManualRehy;
    private String noFail;
    private List<PermohonanManual> mohonManualList;
    private PermohonanManual pm;
    private String idMohonManual;
    private String noFailBaru;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_laporan_pelukis_pelan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/pecahSempadan/dev_laporan_pelukis_pelan.jsp").addParameter("tab", "true");
    }

    public Resolution tanahRizabPopup() {
        return new JSP("pembangunan/pecahSempadan/tanah_rizab_add.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution tanahMilikPopup() {
        return new JSP("pembangunan/pecahSempadan/tanah_milik_add.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution permohonanTerdahuluPopup() {
        return new JSP("pembangunan/pecahSempadan/permohonan_terdahulu_add.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("idMOhon: " + idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = currentStageId(taskId);
        negeri = conf.getProperty("kodNegeri");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (permohonan.getSenaraiHakmilik().isEmpty()) ? "" : permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        LOG.info("idHakmilik: " + idHakmilik);
        if (!idHakmilik.isEmpty()) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
//            noLot = hakmilik.getNoLot().replaceAll("^0*", "");
        }
//        LOG.info("noLot: " + noLot);

        //idTanahRizabPermohonan = (String) getContext().getRequest().getParameter("idTanahRizabPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            tanahRizabList = permohonan.getSenaraiTanahRizab();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            noLotList = new ArrayList<String>();
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                noLotList.add(hp.getHakmilik().getNoLot().replaceAll("^0*", ""));
                //noLotLnoLotListist.add(hp.getHakmilik().getNoLot().replace("0", ""));
            }
            System.out.println("--noLotList--:" + noLotList);
//            permohonanLaporanPelan= laporanPelukisPelanService.findByidP(idPermohonan);
            permohonanLaporanPelan = pembangunanService.findLaporanPelanByidPermohonan(idPermohonan);

//           tanahrizabpermohonan=tanahRizabService.findByidPermohonan(idPermohonan);
            // tanahrizabpermohonan=laporanPelukisPelanService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));

            if (permohonanLaporanPelan != null) {
                //noLitho = permohonanLaporanPelan.getNoLitho();
                catatan = permohonanLaporanPelan.getCatatan();
                projekKerajaan = permohonanLaporanPelan.getProjekKerajaan();
            }

            if (hakmilikPermohonanList == null || hakmilikPermohonanList.isEmpty()) {
                hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan.setPermohonan(permohonan);
            } else {
                hakmilikPermohonan = hakmilikPermohonanList.get(0);
                if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodDUN() != null)) {
                    kodD = hakmilikPermohonan.getKodDUN().getKod();
                }
                if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKawasanParlimen() != null)) {
                    kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
                }

                if ((hakmilikPermohonan != null)) {
                    noLitho = hakmilikPermohonan.getHakmilik().getNoLitho();
                }
                LOG.info("noLitho:-" + noLitho);

                List<NoPt> noPtList = new ArrayList<NoPt>();
                noPtList = pembangunanService.senaraiNoPTByIdPermohonan(idPermohonan);
                if (!noPtList.isEmpty() && noPtList != null) {
                    NoPt noPt = noPtList.get(0);
                    noPTSementara = noPt.getNoPtSementara();
                    if (noPTSementara == null) {
                        noPTSementara = 0L;
                    }
                }

                LOG.info("-----rehydrate------kodD---:" + kodD);
                LOG.info("-----rehydrate------kodPar---:" + kodPar);
                LOG.info("-----rehydrate------noPTSementara---:" + noPTSementara);
            }

            //findNoFail
            mohonManualList = pembangunanService.findPermohonanManualByIdPermohonan(idPermohonan);
            for (PermohonanManual mp : mohonManualList) {
                noFail = mp.getNoFail();
           }

        }
    }

    public Resolution showEditTanahRizab() {
        String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = tanahrizabPermohonanDAO.findById(Long.valueOf(idtanahRizabPermohonan));
        return new JSP("pembangunan/pecahSempadan/tanah_rizab_edit.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPermohonanTerdahulu() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null && permohonan.getPermohonanSebelum() != null) {
            idPermohonanSebelum = permohonan.getPermohonanSebelum().getIdPermohonan();
        }
        return new JSP("pembangunan/pecahSempadan/permohonan_terdahulu_edit.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPermohonanTerdahulu() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");    
        return new JSP("pembangunan/pecahSempadan/tambah_permohonan_terdahulu.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(LaporanPelukisPelanActionBean.class, "locate");
    }

    public Resolution simpanTanahMilik() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        LOG.info("---------------" + hakmilik);

        if (idPermohonan != null && hakmilik != null) {
            hakmilikPermohonan = new HakmilikPermohonan();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            hakmilikPermohonan.setInfoAudit(info);
            hakmilikPermohonan.setPermohonan(permohonan);
            hakmilikPermohonan.setHakmilik(hakmilik);
            //hakmilikPermohonanList.add(hakmilikPermohonan);
            laporanPelukisPelanService.saveOrUpdateTanahMilik(hakmilikPermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            addSimpleError("Id Hakmilik Tidak Wujud.");
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_laporan_pelukis_pelan.jsp").addParameter("tab", "true");
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
        return new RedirectResolution(LaporanPelukisPelanActionBean.class, "locate");
    }

    public Resolution simpanTanahRizab() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        tanahrizabpermohonan=tanahRizabService.findByidPermohonan(idPermohonan);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
            }
            tanahrizabpermohonan.setInfoAudit(info);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
//            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            // tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahrizabpermohonan.setAktif('Y');
            tanahRizabList.add(tanahrizabpermohonan);
            laporanPelukisPelanService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
            }
            tanahrizabpermohonan.setInfoAudit(info);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
//            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            // tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahrizabpermohonan.setAktif('Y');
            tanahRizabList.add(tanahrizabpermohonan);
            laporanPelukisPelanService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_laporan_pelukis_pelan.jsp").addParameter("tab", "true");
    }

    // Newly Modified
    public Resolution editTanahRizab() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();

        String idTanahRizab = getContext().getRequest().getParameter("tanahrizabpermohonan.idTanahRizabPermohonan");
        TanahRizabPermohonan tanahrizabpermohonanTemp = new TanahRizabPermohonan();
        tanahrizabpermohonanTemp = tanahrizabPermohonanDAO.findById(Long.parseLong(idTanahRizab));
        if (tanahrizabpermohonanTemp != null) {
            info = tanahrizabpermohonanTemp.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            tanahrizabpermohonanTemp.setInfoAudit(info);
            String kodRizab = getContext().getRequest().getParameter("tanahrizabpermohonan.rizab.kod");
//            LOG.info("-----kodRizab----:"+kodRizab);
            if (kodRizab != null && !kodRizab.equals("")) {
                rizab = new KodRizab();
                rizab.setKod(Integer.parseInt(kodRizab));
                tanahrizabpermohonanTemp.setRizab(rizab);
            }
            String kodCawangan = getContext().getRequest().getParameter("tanahrizabpermohonan.cawangan.kod");
//            LOG.info("-----kodCawangan----:"+kodCawangan);
            if (kodCawangan != null && !kodCawangan.equals("")) {
                cawangan = new KodCawangan();
                cawangan.setKod(kodCawangan);
                tanahrizabpermohonanTemp.setCawangan(cawangan);
            }
            String kodDaerah = getContext().getRequest().getParameter("tanahrizabpermohonan.daerah.kod");
//            LOG.info("-----kodDaerah----:"+kodDaerah);
            if (kodDaerah != null && !kodDaerah.equals("")) {
                daerah = new KodDaerah();
                daerah.setKod(kodDaerah);
                tanahrizabpermohonanTemp.setDaerah(daerah);
            }
            String kodBpm = getContext().getRequest().getParameter("tanahrizabpermohonan.bandarPekanMukim.kod");
//            LOG.info("-----kodBpm----:"+kodBpm);
            if (kodBpm != null && !kodBpm.equals("")) {
                bandarPekanMukim = new KodBandarPekanMukim();
                bandarPekanMukim.setKod(Integer.parseInt(kodBpm));
                tanahrizabpermohonanTemp.setBandarPekanMukim(bandarPekanMukim);
            }
            tanahrizabpermohonanTemp.setNoLot(tanahrizabpermohonan.getNoLot());
            tanahrizabpermohonanTemp.setNoWarta(tanahrizabpermohonan.getNoWarta());
            tanahrizabpermohonanTemp.setTarikhWarta(tanahrizabpermohonan.getTarikhWarta());
            tanahrizabpermohonanTemp.setAktif('Y');
            laporanPelukisPelanService.saveOrUpdatetanahRizab(tanahrizabpermohonanTemp);
        }

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_laporan_pelukis_pelan.jsp").addParameter("tab", "true");
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
            tanahrizabpermohonan.setRizab(rizab);
            //tanahrizabpermohonan.setCatatan(catatan);
            laporanPelukisPelanService.deleteAll(tanahrizabpermohonan);
        }
        return new RedirectResolution(LaporanPelukisPelanActionBean.class, "locate");
    }

    public Resolution simpanPermohonanTerdahulu() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Permohonan permohonanSebelum = permohonanDAO.findById(idPermohonanSebelum);

        if (idPermohonan != null) {
            info.setDimasukOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(info);
            permohonan.setPermohonanSebelum(permohonanSebelum);
            laporanPelukisPelanService.saveOrUpdatePermohonan(permohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_laporan_pelukis_pelan.jsp").addParameter("tab", "true");
    }

    public Resolution deletePermohonanTerdahulu() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
            permohonan.setPermohonanSebelum(null);
            laporanPelukisPelanService.saveOrUpdatePermohonan(permohonan);
        }
        return new RedirectResolution(LaporanPelukisPelanActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
//        String kodTanah = mohon.getSenaraiHakmilik().get(0).getHakmilik().getKodTanah().getKod();
        LOG.info("-----simpan------kodD---:" + kodD);
        LOG.info("-----simpan------kodPar---:" + kodPar);
        List<HakmilikPermohonan> hpList = new ArrayList<HakmilikPermohonan>();
        hpList = mohon.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : hpList) {
            infoAudit = hp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            if (kodD != null) {
                KodDUN kd = kodDUNDAO.findById(kodD);
                hp.setKodDUN(kd);
            }
            if (kodPar != null) {
                KodKawasanParlimen kw = kodKawasanParlimenDAO.findById(kodPar);
                hp.setKodKawasanParlimen(kw);
            }
            hp.setInfoAudit(infoAudit);
            hp.setNoLot(hp.getHakmilik().getNoLot());
            hp.setLot(hp.getHakmilik().getLot());
            hp.setLuasTerlibat(hp.getHakmilik().getLuas());
            hp.setKodUnitLuas(hp.getHakmilik().getKodUnitLuas());

            if (hp.getBandarPekanMukimBaru() == null) {
                hp.setBandarPekanMukimBaru(kodBandarPekanMukimDAO.findById(hp.getHakmilik().getBandarPekanMukim().getKod()));
            }
            pembangunanService.simpanHakmilikPermohonan(hp);
        }

        if (permohonanLaporanPelan != null) {
            infoAudit = permohonanLaporanPelan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            permohonanLaporanPelan.setInfoAudit(infoAudit);
            permohonanLaporanPelan.setPermohonan(mohon);
            permohonanLaporanPelan.setCawangan(cawangan);
            permohonanLaporanPelan.setNoLitho(noLitho);
            permohonanLaporanPelan.setProjekKerajaan(projekKerajaan);
            permohonanLaporanPelan.setCatatan(catatan);
//            permohonanLaporanPelan.setKodTanah(ktDao.findById("01"));
            //hakmilikPermohonanList.add(hakmilikPermohonan);
            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
            addSimpleMessage("Maklumat telah berjaya disimpan");

        } else {
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            permohonanLaporanPelan = new PermohonanLaporanPelan();
            permohonanLaporanPelan.setInfoAudit(infoAudit);
            permohonanLaporanPelan.setPermohonan(mohon);
            permohonanLaporanPelan.setCawangan(cawangan);
            permohonanLaporanPelan.setNoLitho(noLitho);
            permohonanLaporanPelan.setProjekKerajaan(projekKerajaan);
            permohonanLaporanPelan.setCatatan(catatan);
            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonanList.get(0));
//            permohonanLaporanPelan.setKodTanah(ktDao.findById("01"));
            //hakmilikPermohonanList.add(hakmilikPermohonan);
            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }

//        if (idPermohonan != null) {
//            permohonanLaporanPelan = new PermohonanLaporanPelan();
//            infoAudit.setDimasukOleh(peng);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            permohonanLaporanPelan.setInfoAudit(infoAudit);
//            permohonanLaporanPelan.setPermohonan(mohon);
//            permohonanLaporanPelan.setCawangan(cawangan);
//            permohonanLaporanPelan.setNoLitho(noLitho);
//            permohonanLaporanPelan.setProjekKerajaan(projekKerajaan);
//            permohonanLaporanPelan.setCatatan(catatan);
//            //hakmilikPermohonanList.add(hakmilikPermohonan);
//            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
//            addSimpleMessage("Maklumat telah berjaya disimpan");
//        }

        List<NoPt> noPtList = new ArrayList<NoPt>();
        noPtList = pembangunanService.senaraiNoPTByIdPermohonan(idPermohonan);
        if (!noPtList.isEmpty() && noPtList != null) {
            for (NoPt noPt : noPtList) {
                LOG.info("------noPTSementara--simpan------" + noPTSementara);
                infoAudit = noPt.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                noPt.setNoPtSementara(noPTSementara);
                noPt.setInfoAudit(infoAudit);
                pembangunanService.simpanNoPt(noPt);
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_laporan_pelukis_pelan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanFail() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idMohonManual = getContext().getRequest().getParameter("idMohonManual");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit = permohonan.getInfoAudit();

        permohonanManual = pembangunanService.findPermohonanManualByIdPermohonanIdManual(idPermohonan, idMohonManual);
        if (permohonanManual != null) {
            permohonanManual.setNoFail(noFail);
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pembangunanService.simpanPermohonanManual(permohonanManual);
        } else {
            permohonanManual = new PermohonanManual();
            permohonanManual.setIdPermohonan(permohonan);
            permohonanManual.setCawangan(peng.getKodCawangan());
            permohonanManual.setNoFail(noFailBaru);
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            pembangunanService.simpanPermohonanManual(permohonanManual);
        }

        addSimpleMessage(
                "No. Fail Telah Berjaya Disimpan.");
        getContext()
                .getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution(
                "/WEB-INF/jsp/pembangunan/pecahSempadan/tambah_permohonan_terdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution deleteNoFail() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        noFail = getContext().getRequest().getParameter("idMohonManual");
        LOG.info("NoFail" + noFail);   
        
        pm = permohonanManualDAO.findById(Long.valueOf(noFail));
        pembangunanService.deletePermohonanManualByNoFail(pm);
        rehydrate();        
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("No.Fail Telah Berjaya Dipadamkan.");  
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_laporan_pelukis_pelan.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniNoFail() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idMohonManual = getContext().getRequest().getParameter("idMohonManual");
        LOG.info("NoFail" + noFail);
        
        pm = permohonanManualDAO.findById(Long.valueOf(idMohonManual));
        noFail = pm.getNoFail();
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/kemaskini_permohonan_terdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution permohonanSebelumDetail() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("Id Permohonan dulu" + permohonan.getPermohonanSebelum().getIdPermohonan());
        permohonanTerdahulu = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/dev_maklumat_mohon_terdahulu_detail.jsp").addParameter("popup", "true");
    }

    public String currentStageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }

        return stageId;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Long getIdTanahRizab() {
        return idTanahRizabPermohonan;
    }

    public void setIdTanahRizab(Long idTanahRizabPermohonan) {
        this.idTanahRizabPermohonan = idTanahRizabPermohonan;
    }

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

    public TanahRizabPermohonan getTanahrizab() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizab(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
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

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Permohonan getPermohonanTerdahulu() {
        return permohonanTerdahulu;
    }

    public void setPermohonanTerdahulu(Permohonan permohonanTerdahulu) {
        this.permohonanTerdahulu = permohonanTerdahulu;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getKodD() {
        return kodD;
    }

    public void setKodD(String kodD) {
        this.kodD = kodD;
    }

    public String getKodPar() {
        return kodPar;
    }

    public void setKodPar(String kodPar) {
        this.kodPar = kodPar;
    }

    public Long getNoPTSementara() {
        return noPTSementara;
    }

    public void setNoPTSementara(Long noPTSementara) {
        this.noPTSementara = noPTSementara;
    }

    public List<String> getNoLotList() {
        return noLotList;
    }

    public void setNoLotList(List<String> noLotList) {
        this.noLotList = noLotList;
    }

    public PermohonanManual getPermohonanManual() {
        return permohonanManual;
    }

    public void setPermohonanManual(PermohonanManual permohonanManual) {
        this.permohonanManual = permohonanManual;
    }

    public PermohonanManual getPermohonanManualRehy() {
        return permohonanManualRehy;
    }

    public void setPermohonanManualRehy(PermohonanManual permohonanManualRehy) {
        this.permohonanManualRehy = permohonanManualRehy;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public List<PermohonanManual> getMohonManualList() {
        return mohonManualList;
    }

    public void setMohonManualList(List<PermohonanManual> mohonManualList) {
        this.mohonManualList = mohonManualList;
    }

    public PermohonanManual getPm() {
        return pm;
    }

    public void setPm(PermohonanManual pm) {
        this.pm = pm;
    }

    public String getIdMohonManual() {
        return idMohonManual;
    }

    public void setIdMohonManual(String idMohonManual) {
        this.idMohonManual = idMohonManual;
    }

    public String getNoFailBaru() {
        return noFailBaru;
    }

    public void setNoFailBaru(String noFailBaru) {
        this.noFailBaru = noFailBaru;
    }

    
    
   
}

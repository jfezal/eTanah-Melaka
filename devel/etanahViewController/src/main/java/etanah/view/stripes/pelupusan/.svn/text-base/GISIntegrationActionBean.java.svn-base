/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.InfoAudit;
import etanah.service.EnforceService;
import etanah.service.BPelService;
import etanah.view.etanahActionBeanContext;
import etanah.view.utility.JupemInIntegration;
import java.io.IOException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.dao.gis.PelanGISDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodBandarPekanMukim;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.model.TanahRizabPermohonan;
import etanah.service.PelupusanService;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.NoPt;
import etanah.model.gis.GISPelan;
import etanah.model.gis.GISPelanPK;
import etanah.model.gis.PelanGIS;
import etanah.model.gis.PelanGISPK;
import etanah.util.FileUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zabedah.zainal
 */
@UrlBinding("/pelupusan/gisIntegration")
public class GISIntegrationActionBean extends AbleActionBean {

    @Inject
    JupemInIntegration jup;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    PelanGISDAO pelanGISDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    private static final Logger LOG = Logger.getLogger(GISIntegrationActionBean.class);
    private Permohonan permohonan;
    private Pengguna pguna;
    private String idPermohonan;
    private String idAliran;
    private String stageId;
    private String taskId;
    private TanahRizabPermohonan trizabPermohonan;
    private String noPW;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private Pengguna pengguna;
    private InfoAudit infoAudit;
    private HakmilikPermohonan hakmilikPermohonan;
    private PelanGIS pelanGIS;
    private NoPt noPT;
    private List<PelanGIS> pelanGISList;
    private FileBean file;
    private FasaPermohonan fasaMohon;
    private HakmilikPermohonan hp;
    private PelanGIS pelan;
    private PelanGISPK pk;
    private String path;
    private String result;
    private Boolean resultPelan;
    private boolean resultPelan1;
//    private BigDecimal totalKira;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit3", Boolean.TRUE);
        stageId = "";
        return new JSP("pelupusan/gis/hantarDMS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm10() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/hantar_jupem.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/sedia_gazette.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/semak_charting.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/semak_PU.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/sahkan_PU.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/kemaskini_hakmilik.jsp").addParameter("tab", "true");
    }
    //PrimaInfo - Add for Terima PA/B1 

    public Resolution showForm8() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
//            if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasPelanB1() == null) {
//                updateGISPelan();
//            }
//
//        }
        return new JSP("pelupusan/gis/terima_pa_b1.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/sedia_PU.jsp").addParameter("tab", "true");
    }

    public Resolution showForm11() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/hantar_pu_jupem.jsp").addParameter("tab", "true");
    }

    public Resolution showForm12() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/g_charting_kemaskini.jsp").addParameter("tab", "true");
    }

    public Resolution showForm13() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/penyediaan_permit.jsp").addParameter("tab", "true");
    }

    public Resolution showForm14() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/semak_permit.jsp").addParameter("tab", "true");
    }

    public Resolution showForm15() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/g_charting_ft.jsp").addParameter("tab", "true");
    }

    public Resolution showForm16() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pelupusan/gis/semak_pw.jsp").addParameter("tab", "true");
    }

    // GIS Inbound Integration
    public Resolution getInboundGIS() throws IOException, Exception {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String idAliran = getContext().getRequest().getParameter("idAliran");
        InfoAudit info = new InfoAudit();
        info.setTarikhMasuk(new java.util.Date());
        info.setDimasukOleh(peng);

        LOG.info("------idPermohonan : " + idPermohonan);
        LOG.info("------idAliran : " + idAliran);
        if (peng != null && idPermohonan != null && idAliran != null) {
            jup.setIa(info);
            jup.setIdAliran(idAliran);
            jup.setIdPermohonan(idPermohonan);
            String msg = jup.inboundGIS(true);
        }
        getContext().getRequest().setAttribute("edit3", Boolean.TRUE);
        stageId = "";
        addSimpleMessage("Data telah berjaya diterima.");
        return new JSP("pelupusan/gis/hantarDMS.jsp").addParameter("tab", "true");
    }

    public Resolution simpanWartaRizabTanah() {
        TanahRizabPermohonan trizabPermohonan = null;
        trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
        if (trizabPermohonan == null) {
            trizabPermohonan = new TanahRizabPermohonan();
        }
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        infoAudit = pengguna.getInfoAudit();
        HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        LOG.info("------getIdHakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
        Hakmilik hak = new Hakmilik();
        hak = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        LOG.info("------getIdPermohonan : " + permohonan.getIdPermohonan());
        LOG.info("------getCawangan : " + permohonan.getCawangan().getKod());
        LOG.info("--noPW-  :" + noPW);
        KodCawangan cawangan = new KodCawangan();
        cawangan = permohonan.getCawangan();
        KodBandarPekanMukim bandarPekanMukim = new KodBandarPekanMukim();
        bandarPekanMukim = hak.getBandarPekanMukim();
        KodDaerah daerah = new KodDaerah();
        daerah = hak.getDaerah();
        LOG.info("------cawangan.getKod : " + cawangan.getKod());
        trizabPermohonan.setPermohonan(permohonan);
        trizabPermohonan.setCawangan(cawangan);
        trizabPermohonan.setDaerah(daerah);
        trizabPermohonan.setBandarPekanMukim(bandarPekanMukim);
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        if (trizabPermohonan == null) {
            trizabPermohonan.setAktif('Y');
        }
        trizabPermohonan.setAktif('Y');
        trizabPermohonan.setNoLot(hak.getNoLot());
        trizabPermohonan.setInfoAudit(infoAudit);
        trizabPermohonan.setNoPW(noPW);
        pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        LOG.info("-----------------Simpan berjaya------------------ ");
        return new JSP("pelupusan/gis/terima_pa_b1.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPW() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        Permohonan p = new Permohonan();
        Permohonan p = pelupusanService.findById(idPermohonan);
        LOG.info("________________________value of p :::: " + p);
//        HakmilikPermohonan mh = new HakmilikPermohonan();
        HakmilikPermohonan mh = pelupusanService.findHakmilikPermohonan(idPermohonan);
        LOG.info("________________________value of mh :::: " + mh);
//        TanahRizabPermohonan trp = new TanahRizabPermohonan();
        TanahRizabPermohonan trp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
        LOG.info("________________________value of trp :::: " + trp);

        if (trp != null) {
            trp.setPermohonan(p);
            trp.setCawangan(p.getCawangan());
            trp.setDaerah(p.getCawangan().getDaerah());
            trp.setBandarPekanMukim(mh.getBandarPekanMukimBaru() != null ? mh.getBandarPekanMukimBaru() : mh.getHakmilik().getBandarPekanMukim());
            trp.setAktif('Y');
            trp.setNoPW(noPW);
            trp.setInfoAudit(disLaporanTanahService.findAudit(trp, "update", peng));
            pelupusanService.simpanTanahRizabPermohonan(trp);
            addSimpleMessage("No PW berjaya dikemaskini.");
        } else {
            trp = new TanahRizabPermohonan();
            trp.setPermohonan(p);
            trp.setCawangan(p.getCawangan());
            trp.setDaerah(p.getCawangan().getDaerah());
            trp.setBandarPekanMukim(mh.getBandarPekanMukimBaru() != null ? mh.getBandarPekanMukimBaru() : mh.getHakmilik().getBandarPekanMukim());
            trp.setAktif('Y');
            trp.setNoPW(noPW);
            trp.setInfoAudit(disLaporanTanahService.findAudit(trp, "new", peng));
            pelupusanService.simpanTanahRizabPermohonan(trp);
            addSimpleMessage("No PW berjaya disimpan.");
        }
        return new JSP("pelupusan/gis/terima_pa_b1.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniData() {
        return new JSP("pelupusan/gis/kemaskini_data.jsp").addParameter("popup", "true");
    }

//    public Resolution getInboundGIS() throws IOException, Exception {
//        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
//        idAliran = "g_terima_pw";
//
//
//        LOG.info("idPermohonan : " + idPermohonan);
//        LOG.info("idAliran : " + idAliran);
//        if (peng != null && idPermohonan != null && idAliran != null) {
//            jup.setIa(enforceService.getInfo(peng));
//            jup.setIdAliran(idAliran);
//            jup.setIdPermohonan(idPermohonan);
//            String msg = jup.inboundGIS(false);
//            System.out.println("msg :" + msg);
//        }
//        addSimpleMessage("Data telah berjaya diterima. Sila semak di tab dokumen");
//        return new JSP("pelupusan/hantarDMS.jsp").addParameter("tab", "true");
//    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        idAliran = "g_terima_pw";
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = currentStageId(taskId);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0); //Testing temporary
            if (hakmilikPermohonan != null) {
                noPT = disLaporanTanahService.getPelupusanService().findNoPTByIdMH(hakmilikPermohonan.getId());
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("PWGSA")) {
            HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
//            LOG.info("------getIdHakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
//            Hakmilik hak = new Hakmilik();
//            hak = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
//            LOG.info("------getCawangan : " + hak.getCawangan().getKod());
//            LOG.info("------getDaerah : " + hak.getDaerah().getKod());
//            LOG.info("------kod_BPM : " + hak.getBandarPekanMukim().getKod());
//            LOG.info("------getNoLot : " + hak.getNoLot());
            trizabPermohonan = new TanahRizabPermohonan();
            trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            if (trizabPermohonan != null) {
                noPW = trizabPermohonan.getNoPW();
            }
            LOG.info("--noPW-q  :" + noPW);
        } else if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
            String jenisPelan = "P1";
            path = pelupusanService.cariPathPelan(idPermohonan, jenisPelan);
            if (StringUtils.isNotBlank(path)) {
                resultPelan1 = true;
            } else {
                resultPelan1 = false;
            }
            //pelan = pelupusanService.getfileFromDMS(idPermohonan, jenisPelan);
        }
    }

    public void updateGISPelan() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
////                pelanGISList = new ArrayList<PelanGIS>();
//                pelanGISList = disLaporanTanahService.getPelupusanService().findListPelanGISPKByIdPermohonan(idPermohonan);
//                if (pelanGISList.size() > 0) {
////                    pelanGIS = new PelanGIS();
//                    for (PelanGIS pl : pelanGISList) {
//                        if (StringUtils.isNotEmpty(pl.getJenisPelan())) {
//                            if (pl.getJenisPelan().equals("B1")) {
//                                pelanGIS = pl;
//                            }
//                        }
//                    }
//                }
                pelanGIS = new PelanGIS();
                pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB1GISByIdPermohonan(idPermohonan);
            }

            if (pelanGIS != null) {
                hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0); //Temporary for testing
                if (hakmilikPermohonan != null) {
                    noPT = disLaporanTanahService.getPelupusanService().findNoPTByIdMH(hakmilikPermohonan.getId());
                    if (noPT != null && pelanGIS != null) {
                        String no = String.valueOf(noPT.getNoPt());
                        if (pelanGIS.getNoPt() != null) {
                            if (pelanGIS.getNoPt().equals(no)) {
                                if (pelanGIS.getPelanGISPK() != null) {
                                    hakmilikPermohonan.setNoLot(pelanGIS.getPelanGISPK().getNoLot());
                                    hakmilikPermohonan.setLot(kodLotDAO.findById("1"));
                                }
                            }
                        }
                    }
                    hakmilikPermohonan.setLuasPelanB1(pelanGIS.getLuas());
                    if (hakmilikPermohonan.getLuasLulusUom() != null) {
                        hakmilikPermohonan.setLuasPelanB1Uom(hakmilikPermohonan.getLuasLulusUom());
                    }
                    InfoAudit ia = hakmilikPermohonan.getInfoAudit();
                    if (ia == null) {
                        ia = new InfoAudit();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new Date());
                        hakmilikPermohonan.setInfoAudit(ia);
                    } else {
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        hakmilikPermohonan.setInfoAudit(ia);
                    }
                }
                disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(hakmilikPermohonan);
            }
        }

    }

    public Resolution updateGISPelanB1() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idGRP = new String();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                if (permohonan.getPermohonanSebelum() != null) {
                    idGRP = permohonan.getPermohonanSebelum().getIdPermohonan();
                }
////                pelanGISList = new ArrayList<PelanGIS>();
//                pelanGISList = disLaporanTanahService.getPelupusanService().findListPelanGISPKByIdPermohonan(idPermohonan);
//                if (pelanGISList.size() > 0) {
////                    pelanGIS = new PelanGIS();
//                    for (PelanGIS pl : pelanGISList) {
//                        if (StringUtils.isNotEmpty(pl.getJenisPelan())) {
//                            if (pl.getJenisPelan().equals("B1")) {
//                                pelanGIS = pl;
//                            }
//                        }
//                    }
//                }
                if (idGRP != null) {
                    pelanGISList = new ArrayList<PelanGIS>();
                    pelanGISList = disLaporanTanahService.getPelupusanService().findPelanB1GISByIdKelompok(idGRP);
                } else {
                    pelanGIS = new PelanGIS();
                    pelanGIS = disLaporanTanahService.getPelupusanService().findPelanB1GISByIdPermohonan(idPermohonan);
                }
            }
            if (pelanGIS != null) {
                hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0); //Temporary for testing
                if (hakmilikPermohonan != null) {
                    noPT = disLaporanTanahService.getPelupusanService().findNoPTByIdMH(hakmilikPermohonan.getId());
                    if (noPT != null && pelanGIS != null) {
//                        String no = String.valueOf(noPT.getNoPt());
//                        if (pelanGIS.getNoPt() != null) {
//                            if (pelanGIS.getNoPt().equals(no)) {
                        if (pelanGIS.getPelanGISPK() != null) {
                            hakmilikPermohonan.setNoLot(pelanGIS.getPelanGISPK().getNoLot());
                            hakmilikPermohonan.setLot(kodLotDAO.findById("1"));
                        }
//                            }
//                        }
                    }
                    hakmilikPermohonan.setLuasPelanB1(pelanGIS.getLuas());
                    if (hakmilikPermohonan.getLuasLulusUom() != null) {
                        hakmilikPermohonan.setLuasPelanB1Uom(hakmilikPermohonan.getLuasLulusUom());
                    }
                    InfoAudit ia = hakmilikPermohonan.getInfoAudit();
                    if (ia == null) {
                        ia = new InfoAudit();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new Date());
                        hakmilikPermohonan.setInfoAudit(ia);
                    } else {
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        hakmilikPermohonan.setInfoAudit(ia);
                    }
                }
                disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(hakmilikPermohonan);
//                if(permohonan.getKodUrusan().getKod().equals("PBMT")){
//                    MathContext mc = new MathContext(2);
//                    BigDecimal bg100 = new BigDecimal("100.00");
//                    BigDecimal luasB1 = pelanGIS.getLuas();
//                    BigDecimal luasAsal = hakmilikPermohonan.getLuasDiluluskan();
//                    BigDecimal luasDitolak = luasB1.subtract(luasAsal);
//                    BigDecimal totalKiraan = luasDitolak.divide(luasB1, mc);
//                    totalKira = totalKiraan.multiply(bg100);    
//                    LOG.info("totalKira= "+totalKira);
//                }    
            }
            //tambahan untuk permohonan id kelompok GRP
            if (!pelanGISList.isEmpty()) {
                hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0); //Temporary for testing
                if (hakmilikPermohonan != null) {
                    noPT = disLaporanTanahService.getPelupusanService().findNoPTByIdMH(hakmilikPermohonan.getId());
                    if (noPT != null && !pelanGISList.isEmpty()) {
                        for (PelanGIS gis : pelanGISList) {
                            String no = String.valueOf(noPT.getNoPt());
                            if (gis.getNoPt() != null) {
                                if (gis.getNoPt().equals(no)) {
                                    if (gis.getPelanGISPK() != null) {
                                        hakmilikPermohonan.setNoLot(gis.getPelanGISPK().getNoLot());
                                        hakmilikPermohonan.setLot(kodLotDAO.findById("1"));
                                        hakmilikPermohonan.setLuasPelanB1(gis.getLuas());
                                        break;
                                    }

                                }
                            }

                        }

//                            }
//                        }
                    }

                    if (hakmilikPermohonan.getLuasLulusUom() != null) {
                        hakmilikPermohonan.setLuasPelanB1Uom(hakmilikPermohonan.getLuasLulusUom());
                    }
                    InfoAudit ia = hakmilikPermohonan.getInfoAudit();
                    if (ia == null) {
                        ia = new InfoAudit();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new Date());
                        hakmilikPermohonan.setInfoAudit(ia);
                    } else {
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        hakmilikPermohonan.setInfoAudit(ia);
                    }
                }
                disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(hakmilikPermohonan);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gis/terima_pa_b1.jsp").addParameter("tab", "true");

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

    //simpan upload file
    public Resolution simpanP1() throws IOException, Exception {
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hp = pelupusanService.findByIdHakmilikIdPermohonanList(idPermohonan);
            //String jenisPelan = "P1";
            //pelan = pelupusanService.findPelanByIdPermohonanAndJenisPelanP1(idPermohonan, jenisPelan);
            //path = pelupusanService.cariPathPelan(idPermohonan, jenisPelan);
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();

            if (hp.getHakmilik() != null) {
                pelan = new PelanGIS();
                pk = new PelanGISPK();

                KodNegeri kn = kodNegeriDAO.findById(conf.getKodNegeri());
                KodDaerah kd = hp.getBandarPekanMukimBaru().getDaerah();

                pk.setKodNegeri(kn);
                pk.setKodDaerah(kd);
                pk.setKodMukim(String.valueOf(hp.getBandarPekanMukimBaru().getKod()));
                pk.setPermohonan(permohonan);

                //save into Pelan table
                pelan.setPelanGISPK(pk);
                pelan.setJenisPelan("P1");
                pelan.setLuas(hp.getLuasDiluluskan());
                pelan.setUnitUkur(hp.getKodUnitLuas().getKod());
                pelan.setTarikhKemaskini(new Date());

                pelan.setPelanTif(File.separator + kd.getKod() + File.separator + hp.getBandarPekanMukimBaru().getKod() + File.separator + file.getFileName());
                pelanGISDAO.saveOrUpdate(pelan);
                String loc = conf.getPelanPath() + File.separator + pelan.getJenisPelan() + File.separator + kd.getKod() + File.separator + hp.getBandarPekanMukimBaru().getKod();
                FileUtil fileUtil = new FileUtil(loc);
                System.out.print("nama file>>>" + file.getFileName().toLowerCase());
                fileUtil.saveFile(file.getFileName().toLowerCase(), file.getInputStream());
                tx.commit();
                resultPelan1 = true;
            }
        }    
        return new JSP("pelupusan/gis/muat_naik_p1.jsp").addParameter("tab", "true");
    }
    
     public Resolution deleteP1(){
         
        permohonan = permohonanDAO.findById(idPermohonan);       
        pelupusanService.deleteP1(idPermohonan);
        
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        resultPelan1 = false;
       return new JSP("pelupusan/gis/muat_naik_p1.jsp").addParameter("tab", "true");
    }

    public Resolution papar() {
        return new JSP("pelupusan/gis/muat_naik_p1.jsp").addParameter("popup", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getNoPW() {
        return noPW;
    }

    public void setNoPW(String noPW) {
        this.noPW = noPW;
    }

    public Boolean isResultPelan() {
        return resultPelan;
    }

    public void setResultPelan(Boolean resultPelan) {
        this.resultPelan = resultPelan;
    }

//    public Pengguna getPengguna() {
//        return pguna;
//    }
//
//    public void setPengguna(Pengguna pguna) {
//        this.pguna = pguna;
//    }
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
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

    public TanahRizabPermohonan getTrizabPermohonan() {
        return trizabPermohonan;
    }

    public void setTrizabPermohonan(TanahRizabPermohonan trizabPermohonan) {
        this.trizabPermohonan = trizabPermohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PelanGIS getPelanGIS() {
        return pelanGIS;
    }

    public void setPelanGIS(PelanGIS pelanGIS) {
        this.pelanGIS = pelanGIS;
    }

    public List<PelanGIS> getPelanGISList() {
        return pelanGISList;
    }

    public void setPelanGISList(List<PelanGIS> pelanGISList) {
        this.pelanGISList = pelanGISList;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public PelanGIS getPelan() {
        return pelan;
    }

    public void setPelan(PelanGIS pelan) {
        this.pelan = pelan;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isResultPelan1() {
        return resultPelan1;
    }

    public void setResultPelan1(boolean resultPelan1) {
        this.resultPelan1 = resultPelan1;
    }

}

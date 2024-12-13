/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import org.apache.commons.lang.StringUtils;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PihakDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanTingkat;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.Pihak;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanStrata;
import etanah.service.BPelService;
import org.apache.log4j.Logger;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/kertasMLKPHPP")
public class KertasPertimbanganMLKPHPPActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    StrataPtService strService;
    @Inject
    private Permohonan permohonan;
    @Inject
    PermohonanBangunanDAO mohonBgnDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    CommonService comm;
    @Inject
    BPelService service;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private PermohonanBangunan mohonbngn;
    private List<PermohonanBangunan> pBangunanL;
    private List<HakmilikUrusan> hakmilikUrusanL;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private Pemohon pemohon;
    private Pihak pihak;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private int bil_petak = 0;
    private int bil_tgkt;
    private int bil_bgnn;
    private int bil_ptkAksr;
    private int jumlah_syer = 0;
    private int jumlah = 0;
    private PermohonanKertasKandungan kertasKandungan;
    String tujuan;
    String ulasanJabatanUkurdanPemetaan;
    String asaspertimbangan;
    String syorTP;
    private static final Logger LOG = Logger.getLogger(KertasPertimbanganMLKPHPPActionBean.class);
    private Pengguna pengguna;
    private String taskId;
    boolean rayuan = false;
    private PermohonanStrata permohonanStrata;
    private Date tarikhMasuk = new Date();
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan1;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    List<HakmilikPihakBerkepentingan> listHakmilikPihak;
    private int rowCount1;
    private int rowCount2;
    private int rowCount3;
    private String butiranhakmilik;
    private String harta;
    private String syorTPTG;
    private String kptg1 = "KPTG";
    private String noruj;
    private int yr;
    @Inject
    private etanah.Configuration conf;
    private HakmilikPermohonan hp;
    private int lpps;
    List<PermohonanBangunan> lps;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private String idMh;
    private String ulasan;
    private String kandungan;
    private String kandungan1;
    private String nLot;
    private String nHakmilik;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("--Rehydrate()--::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        tarikhMasuk = permohonan.getInfoAudit().getTarikhMasuk();

        if (permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")) {
            for (HakmilikPermohonan hkm : permohonan.getSenaraiHakmilik()) {
                if (hkm.getHakmilik().getKodStatusHakmilik().getKod().equals("D")) {
                    listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hkm.getHakmilik(), "PM");
                    LOG.info("PEMILIK =========>" + listHakmilikPihak.size());
                    LOG.info("PEMILIK =========>" + listHakmilikPihak.toString());
                }
            }
        }

        Long idMh1 = 0L;
        idMh = getContext().getRequest().getParameter("idMh");
        LOG.info("---------------strIdMh:--------" + idMh);
        if (idMh == null) {
            idMh1 = permohonan.getSenaraiHakmilik().get(0).getId();
        } else {
            idMh1 = Long.parseLong(idMh);
        }
        LOG.info("---------------idMh1:--------" + idMh1);

        if (idPermohonan != null) {

            hp = strService.findHKByIdMh(idPermohonan, idMh1);
            LOG.info("--Mohon Hakmilik--::" + hp);
            LOG.info("--Hakmilik--::" + hp.getHakmilik().getIdHakmilik());
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            hakmilikUrusanL = strService.findByID(idHakmilik);

            List<Pemohon> listPemohon;
            listPemohon = strService.findByPermohonan(permohonan.getIdPermohonan());
            LOG.info("-----------listPemohon----------" + listPemohon);
            if (!(listPemohon.isEmpty())) {
                if (!(permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC"))) {
                    pemohon = listPemohon.get(0);
                    pihak = pihakDAO.findById(pemohon.getPihak().getIdPihak());
                    LOG.info("-----------pihak----------" + pihak);
                    tujuan = " Tujuan kertas ini disediakan bagi mempertimbangkan " + permohonan.getKodUrusan().getNama() + " daripada " + pihak.getNama();
                } else {
                    String nama = "";
                    int count = 1;
                    for (HakmilikPihakBerkepentingan hp : listHakmilikPihak) {
                        nama = nama + hp.getNama();
                        if (count < listHakmilikPihak.size()) {
                            nama = nama + " dan ";
                            count++;
                        }
                    }
                    tujuan = " Tujuan kertas ini disediakan bagi mempertimbangkan " + permohonan.getKodUrusan().getNama() + " daripada " + nama;
                }
            }
            LOG.info("-----------List From Rehydrate()----------");
            if (!(permohonan.getSenaraiKertas().isEmpty())) {
                for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {
                    PermohonanKertas perKertas = new PermohonanKertas();
                    perKertas = permohonan.getSenaraiKertas().get(i);
                    for (int j = 0; j < perKertas.getSenaraiKandungan().size(); j++) {
                        kertasKandungan = perKertas.getSenaraiKandungan().get(j);
                        if (kertasKandungan.getBil() == 1) {
                            tujuan = kertasKandungan.getKandungan();

                            //added
                            ulasanJabatanUkurdanPemetaan = kertasKandungan.getKandungan();
                        }
                        //added
                        if (kertasKandungan.getBil() == 2) {
                            asaspertimbangan = kertasKandungan.getKandungan();
                        }
                    }
                    senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan2 = strService.findByIdLapor(perKertas.getIdKertas(), 3);
                    LOG.info("--------senaraiKandungan2---List From Rehydrate()----------" + senaraiKandungan2);
                    senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan1 = strService.findByIdLapor(perKertas.getIdKertas(), 4);
                    LOG.info("--------senaraiKandungan1---List From Rehydrate()----------" + senaraiKandungan1);
                    senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan3 = strService.findByIdLapor(perKertas.getIdKertas(), 5);
                    LOG.info("--------senaraiKandungan3---List From Rehydrate()----------" + senaraiKandungan3);
                    rowCount1 = senaraiKandungan1.size();
                    LOG.info("--------rowCount1---From Rehydrate()----------" + rowCount1);
                    rowCount2 = senaraiKandungan2.size();
                    LOG.info("--------rowCount2---From Rehydrate()----------" + rowCount2);
                    rowCount3 = senaraiKandungan3.size();
                    LOG.info("--------rowCount3---From Rehydrate()----------" + rowCount3);
                }
            }
        }

        nLot = hakmilik.getNoLot().replaceAll("^0*", "");
        nHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");

        //get stage id
        stageId = getBPLStageId();
        LOG.info("--stageId--:" + stageId);

        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        LOG.info("----------HakmilikPermohonan List size--------------: " + senaraiHakmilikPermohonan.size());
    }

    public String getBPLStageId() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        service = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit3", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit4", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit5", Boolean.FALSE);
        return new JSP("strata/pbbm/kertas_pertimbanganPHPP_MLK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit3", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit4", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit5", Boolean.TRUE);
        return new JSP("strata/pbbm/kertas_pertimbanganPHPP_MLK.jsp").addParameter("tab", "true");
    }

    public Resolution showFormReadOnly() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit3", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit4", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit5", Boolean.FALSE);
        return new JSP("strata/pbbm/kertas_pertimbanganPHPP_MLK.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PERMOHONAN PECAH BAHAGI BANGUNAN");
        strService.simpanPermohonanKertas(permohonanKertas);

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();
        ArrayList listBill = new ArrayList();


        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }

        listUlasan.add(tujuan);
        listSubtajuk.add("");
        listBill.add(new Integer(1));

        if (kertasKandungan != null) {

            if (!kertasKandungan.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKdgn.getBil() == 1) {
                        kertasKdgn.setKandungan(tujuan);
                    }
                    kertasKdgn.setInfoAudit(infoAudit);
                    strService.simpanPermohonanKertasKandungan(kertasKdgn);
                }
            }
        } else {

            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                Integer bil = (Integer) listBill.get(i);
                infoAudit.setTarikhMasuk(new java.util.Date());
                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(bil);
                kertasKdgn.setInfoAudit(infoAudit);
                kertasKdgn.setKandungan(ulasan);
                kertasKdgn.setSubtajuk(subtajuk);
                kertasKdgn.setCawangan(permohonan.getCawangan());
                strService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }
        LOG.info("--------Saving in 3-------:");
        senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan2 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
        LOG.info("--------senaraiKandungan2-------:" + senaraiKandungan2);
        LOG.info("--------senaraiKandungan2---size----:" + senaraiKandungan2.size());
        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("------senaraiKandungan2--kira-------:" + kira);
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan2.size() != 0 && i <= senaraiKandungan2.size()) {
                Long id = senaraiKandungan2.get(i - 1).getIdKandungan();
                if (id != null) {
                    LOG.info("--------senaraiKandungan2--if-----:");
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(peng);
                }
            } else {
                LOG.info("--------senaraiKandungan2--else-----:");
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(3);
            //String kandungan = getContext().getRequest().getParameter("ulasan" + i);
            ulasan = getContext().getRequest().getParameter("ulasan" + i);
            LOG.info("--------ulasan-------:" + ulasan);
            if (ulasan == null || ulasan.equals("")) {
                ulasan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(ulasan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        msg = "Maklumat telah berjaya disimpan.";

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new RedirectResolution(KertasPertimbanganMLKPHPPActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution simpanSemakKertasptg() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PERMOHONAN PECAH BAHAGI BANGUNAN");

        strService.simpanPermohonanKertas(permohonanKertas);

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();
        ArrayList listBill = new ArrayList();

        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }

        listUlasan.add(tujuan);
        listSubtajuk.add("");
        listBill.add(new Integer(1));

        if (kertasKandungan != null) {

            if (!kertasKandungan.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKdgn.getBil() == 1) {
                        kertasKdgn.setKandungan(tujuan);
                    }
                    kertasKdgn.setInfoAudit(infoAudit);
                    strService.simpanPermohonanKertasKandungan(kertasKdgn);
                }
            }
        } else {

            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                Integer bil = (Integer) listBill.get(i);
                infoAudit.setTarikhMasuk(new java.util.Date());
                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(bil);
                kertasKdgn.setInfoAudit(infoAudit);
                kertasKdgn.setKandungan(ulasan);
                kertasKdgn.setSubtajuk(subtajuk);
                kertasKdgn.setCawangan(permohonan.getCawangan());
                strService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }

        LOG.info("--------Saving in 4-------:");
        senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan1 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
        LOG.info("--------senaraiKandungan1-------:" + senaraiKandungan1);
        LOG.info("--------senaraiKandungan1---size----:" + senaraiKandungan1.size());
        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        LOG.info("------senaraiKandungan1--kira-------:" + kira);
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan1.size() != 0 && i <= senaraiKandungan1.size()) {
                Long id = senaraiKandungan1.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(peng);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(4);
            kandungan = getContext().getRequest().getParameter("asaspertimbangan" + i);
            LOG.info("--------kandungan-------:" + kandungan);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("4." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        LOG.info("--------Saving in 5-------:");
        senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan3 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
        LOG.info("--------senaraiKandungan3-------:" + senaraiKandungan3);
        LOG.info("--------senaraiKandungan3---size----:" + senaraiKandungan3.size());
        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        LOG.info("------senaraiKandungan3--kira-------:" + kira);
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan3.size() != 0 && i <= senaraiKandungan3.size()) {
                Long id = senaraiKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(peng);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(5);
            kandungan1 = getContext().getRequest().getParameter("syorTP" + i);
            LOG.info("--------kandungan-------:" + kandungan1);
            if (kandungan1 == null || kandungan1.equals("")) {
                kandungan1 = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan1);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("5." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        msg = "Maklumat telah berjaya disimpan.";

        getContext().getRequest().setAttribute("display", Boolean.TRUE);

        return new RedirectResolution(KertasPertimbanganMLKPHPPActionBean.class, "showForm2").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteSingle() {

        LOG.info("--------Deleting Rekod----------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("--------idKandungan----------" + idKandungan);
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            LOG.debug("--------Deleting Record--------" + permohonanKertasKandungan1);
        } catch (Exception e) {
            LOG.debug("--------Hapus empty record--------");
        }
        if (permohonanKertasKandungan1 != null) {
            strService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("---------Record deleted Successfully---------");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(KertasPertimbanganMLKPHPPActionBean.class, "locate");
    }

    public Resolution deleteSingleSemakKertasptg() {

        LOG.info("--------Deleting Rekod----------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("--------idKandungan----------" + idKandungan);
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            LOG.debug("--------Deleting Record--------" + permohonanKertasKandungan1);
        } catch (Exception e) {
            LOG.debug("--------Hapus empty record--------");
        }
        if (permohonanKertasKandungan1 != null) {
            strService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("---------Record deleted Successfully---------");
        }
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(KertasPertimbanganMLKPHPPActionBean.class, "showForm2");
    }

    public Resolution hakdetails() {
        LOG.info("--hakdetails--:");
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("--idMh1--:" + idMh1);
        rehydrate();

        if (stageId.equals("sediakertasptg")) {
            LOG.info("--stageId--:" + stageId);

            String tujuanTemp = getContext().getRequest().getParameter("tujuan");
            LOG.info("--tujuanTemp--:" + tujuanTemp);
            if (tujuanTemp != null) {
                tujuan = tujuanTemp;
            }

            int rowCount2Temp = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
            LOG.info("--rowCount2Temp--:" + rowCount2Temp);
            for (int i = 1; i <= rowCount2Temp; i++) {
                String ulasanTemp = getContext().getRequest().getParameter("ulasan" + i);
                LOG.info("--ulasanTemp--:" + ulasanTemp);
                if (ulasanTemp != null) {
                    ulasan = ulasanTemp;
                }
            }
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            getContext().getRequest().setAttribute("edit3", Boolean.TRUE);
            getContext().getRequest().setAttribute("edit4", Boolean.FALSE);
            getContext().getRequest().setAttribute("edit5", Boolean.FALSE);
        }

        if (stageId.equals("semakkertasptg") || stageId.equals("perakuan")) {
            LOG.info("--stageId--:" + stageId);

            int rowCount1Temp = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
            LOG.info("--rowCount1Temp--:" + rowCount1Temp);
            for (int i = 1; i <= rowCount1Temp; i++) {
                String asaspertimbanganTemp = getContext().getRequest().getParameter("asaspertimbangan" + i);
                LOG.info("--asaspertimbanganTemp--:" + asaspertimbanganTemp);
                if (asaspertimbanganTemp != null) {
                    kandungan = asaspertimbanganTemp;
                }
            }

            int rowCount3Temp = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
            LOG.info("--rowCount3Temp--:" + rowCount3Temp);
            for (int i = 1; i <= rowCount3Temp; i++) {
                String syorTPTemp = getContext().getRequest().getParameter("syorTP" + i);
                LOG.info("--syorTPTemp--:" + syorTPTemp);
                if (syorTPTemp != null) {
                    kandungan1 = syorTPTemp;
                }
            }
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            getContext().getRequest().setAttribute("edit3", Boolean.FALSE);
            getContext().getRequest().setAttribute("edit4", Boolean.TRUE);
            getContext().getRequest().setAttribute("edit5", Boolean.TRUE);
        }
        if (stageId.equals("keputusan1")) {
            LOG.info("--stageId--:" + stageId);
            getContext().getRequest().setAttribute("edit", Boolean.FALSE);
            getContext().getRequest().setAttribute("edit3", Boolean.FALSE);
            getContext().getRequest().setAttribute("edit4", Boolean.FALSE);
            getContext().getRequest().setAttribute("edit5", Boolean.FALSE);
        }
        return new JSP("strata/pbbm/kertas_pertimbanganPHPP_MLK.jsp").addParameter("tab", "true");
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getJumlah_syer() {
        return jumlah_syer;
    }

    public void setJumlah_syer(int jumlah_syer) {
        this.jumlah_syer = jumlah_syer;
    }

    public int getBil_ptkAksr() {
        return bil_ptkAksr;
    }

    public void setBil_ptkAksr(int bil_ptkAksr) {
        this.bil_ptkAksr = bil_ptkAksr;
    }

    public PermohonanBangunan getMohonbngn() {
        return mohonbngn;
    }

    public void setMohonbngn(PermohonanBangunan mohonbngn) {
        this.mohonbngn = mohonbngn;
    }

    public List<HakmilikUrusan> getHakmilikUrusanL() {
        return hakmilikUrusanL;
    }

    public void setHakmilikUrusanL(List<HakmilikUrusan> hakmilikUrusanL) {
        this.hakmilikUrusanL = hakmilikUrusanL;
    }

    public String getAsaspertimbangan() {
        return asaspertimbangan;
    }

    public void setAsaspertimbangan(String asaspertimbangan) {
        this.asaspertimbangan = asaspertimbangan;
    }

    public String getSyorTP() {
        return syorTP;
    }

    public void setSyorTP(String syorTP) {
        this.syorTP = syorTP;
    }

    public String getUlasanJabatanUkurdanPemetaan() {
        return ulasanJabatanUkurdanPemetaan;
    }

    public void setUlasanJabatanUkurdanPemetaan(String ulasanJabatanUkurdanPemetaan) {
        this.ulasanJabatanUkurdanPemetaan = ulasanJabatanUkurdanPemetaan;
    }

    public int getBil_bgnn() {
        return bil_bgnn;
    }

    public void setBil_bgnn(int bil_bgnn) {
        this.bil_bgnn = bil_bgnn;
    }

    public int getBil_petak() {
        return bil_petak;
    }

    public void setBil_petak(int bil_petak) {
        this.bil_petak = bil_petak;
    }

    public int getBil_tgkt() {
        return bil_tgkt;
    }

    public void setBil_tgkt(int bil_tgkt) {
        this.bil_tgkt = bil_tgkt;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public boolean isRayuan() {
        return rayuan;
    }

    public void setRayuan(boolean rayuan) {
        this.rayuan = rayuan;
    }

    public PermohonanStrata getPermohonanStrata() {
        return permohonanStrata;
    }

    public void setPermohonanStrata(PermohonanStrata permohonanStrata) {
        this.permohonanStrata = permohonanStrata;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan1() {
        return senaraiKandungan1;
    }

    public void setSenaraiKandungan1(List<PermohonanKertasKandungan> senaraiKandungan1) {
        this.senaraiKandungan1 = senaraiKandungan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan3() {
        return senaraiKandungan3;
    }

    public void setSenaraiKandungan3(List<PermohonanKertasKandungan> senaraiKandungan3) {
        this.senaraiKandungan3 = senaraiKandungan3;
    }

    public int getRowCount1() {
        return rowCount1;
    }

    public void setRowCount1(int rowCount1) {
        this.rowCount1 = rowCount1;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public String getButiranhakmilik() {
        return butiranhakmilik;
    }

    public void setButiranhakmilik(String butiranhakmilik) {
        this.butiranhakmilik = butiranhakmilik;
    }

    public String getHarta() {
        return harta;
    }

    public void setHarta(String harta) {
        this.harta = harta;
    }

    public String getSyorTPTG() {
        return syorTPTG;
    }

    public void setSyorTPTG(String syorTPTG) {
        this.syorTPTG = syorTPTG;
    }

    public String getKptg1() {
        return kptg1;
    }

    public void setKptg1(String kptg1) {
        this.kptg1 = kptg1;
    }

    public String getNoruj() {
        return noruj;
    }

    public void setNoruj(String noruj) {
        this.noruj = noruj;
    }

    public int getYr() {
        return yr;
    }

    public void setYr(int yr) {
        this.yr = yr;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public int getLpps() {
        return lpps;
    }

    public void setLpps(int lpps) {
        this.lpps = lpps;
    }

    public List<PermohonanBangunan> getLps() {
        return lps;
    }

    public void setLps(List<PermohonanBangunan> lps) {
        this.lps = lps;
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

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getKandungan1() {
        return kandungan1;
    }

    public void setKandungan1(String kandungan1) {
        this.kandungan1 = kandungan1;
    }

    public String getnHakmilik() {
        return nHakmilik;
    }

    public void setnHakmilik(String nHakmilik) {
        this.nHakmilik = nHakmilik;
    }

    public String getnLot() {
        return nLot;
    }

    public void setnLot(String nLot) {
        this.nLot = nLot;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
        return listHakmilikPihak;
    }

    public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
        this.listHakmilikPihak = listHakmilikPihak;
    }
}

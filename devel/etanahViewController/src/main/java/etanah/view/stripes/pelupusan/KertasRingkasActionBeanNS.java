/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.model.BangunanTingkat;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.Pihak;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/kertas_ringkasNS")
public class KertasRingkasActionBeanNS extends AbleActionBean {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PelupusanService plpService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    private etanah.Configuration conf;
    private List<PermohonanKertasKandungan> listKertasHuraianPTD = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> listKertasSyorPTD = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> listKertasPerakuanPTG = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> listKertasKeputusanPTG = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> latarBelakangList = new ArrayList<PermohonanKertasKandungan>();
    private static final Logger LOG = Logger.getLogger(KertasRingkasActionBeanNS.class);
    private PermohonanKertas mohonKertas;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pemohon pemohon;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonanSebelum;
    private String tajuk;
    private String tujuan;
    private String latarBelakang;
    private String perihalPemohon;
    private String perihalTanah;
    private String stageId;
    private Pihak pihak;
    private Date tarikhSidang;
    private String bilMesy;
    private Pengguna pguna;
    private PelupusanUtiliti pelupusanUtiliti = new PelupusanUtiliti();
    private FasaPermohonan mohonFasa;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
//        stageId = "01KemasukanKertasRingkas";
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormPTD() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution showFormMMKN() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/keputusanMMK_NS.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormMMKN() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/rayuan/keputusanMMK_NS.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPTG() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormPTG() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution editKeputusanPTG() {
        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPTD() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow() {
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        stageId = stageId(taskId);
////        stageId = "01KemasukanKertasRingkas";
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;
            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 2);
                latarBelakangList.add(pkk);
                break;
            case 3:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 3);
                listKertasHuraianPTD.add(pkk);
                break;
            case 4:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                listKertasSyorPTD.add(pkk);
                break;
            case 5:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 5);
                listKertasPerakuanPTG.add(pkk);
                break;
            case 6:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 6);
                listKertasKeputusanPTG.add(pkk);
                break;
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
//            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
            getContext().getRequest().setAttribute("keputusanPTD", getContext().getRequest().getParameter("keputusanPTD"));
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", getContext().getRequest().getParameter("keputusanPTD"));
        }
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 2:
                updateKandungan(2, kand);

                break;
            case 3:

                updateKandungan(3, kand);

                break;
            case 4:

                updateKandungan(4, kand);

                break;
            case 5:

                updateKandungan(5, kand);

                break;
            case 6:

                updateKandungan(6, kand);
                break;
            default:
                LOG.info("alamak!! tiada index");
        }
        rehydrate();

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskini() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand + "idKandungan" + idKand);
        InfoAudit ia = new InfoAudit();
        if (idKand != null) {
            PermohonanKertasKandungan plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {
                ia = plk.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                plk.setInfoAudit(ia);
                plk.setKandungan(kand);
                permohonanKertasKandDAO.saveOrUpdate(plk);
            }
        }


        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Dikemaskini");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMMKN() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        mohonKertas = strService.findKertasByKod(idPermohonan, "MMK");
        if (mohonKertas != null) {
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            mohonKertas.setPermohonan(permohonan);
            mohonKertas.setTarikhSidang(tarikhSidang);
            mohonKertas.setTempatSidang(bilMesy);
            strService.simpanPermohonanKertas(mohonKertas);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/rayuan/keputusanMMK_NS.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRow() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    strService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRowPTG() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;
            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 2);
                latarBelakangList.add(pkk);
                break;
            case 3:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 3);
                listKertasHuraianPTD.add(pkk);
                break;
            case 4:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                listKertasSyorPTD.add(pkk);
                break;
            case 5:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 5);
                listKertasPerakuanPTG.add(pkk);
                break;
            case 6:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 6);
                listKertasKeputusanPTG.add(pkk);
                break;
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniPTG() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand + "idKandungan" + idKand);
        InfoAudit ia = new InfoAudit();
        if (idKand != null) {
            PermohonanKertasKandungan plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {
                ia = plk.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                plk.setInfoAudit(ia);
                plk.setKandungan(kand);
                permohonanKertasKandDAO.saveOrUpdate(plk);
            }
        }


        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Dikemaskini");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPTG() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 2:
                updateKandungan(2, kand);

                break;
            case 3:

                updateKandungan(3, kand);

                break;
            case 4:

                updateKandungan(4, kand);

                break;
            case 5:

                updateKandungan(5, kand);

                break;
            case 6:

                updateKandungan(6, kand);
                break;
            default:
                LOG.info("alamak!! tiada index");
        }
        rehydrate();

        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRowPTG() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    strService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
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
    //Keputusan PTG

    public Resolution tambahKeputusanRowPTG() {
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        stageId = stageId(taskId);
//        stageId = "01KemasukanKertasRingkas";
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 6:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 6);
                listKertasKeputusanPTG.add(pkk);
                break;
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniKeputusanPTG() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand + "idKandungan" + idKand);
        InfoAudit ia = new InfoAudit();
        if (idKand != null) {
            PermohonanKertasKandungan plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {
                ia = plk.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                plk.setInfoAudit(ia);
                plk.setKandungan(kand);
                permohonanKertasKandDAO.saveOrUpdate(plk);
            }
        }


        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Dikemaskini");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKeputusanPTG() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 6:

                updateKandungan(6, kand);
                break;
            default:
                LOG.info("alamak!! tiada index");
        }
        rehydrate();

        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRowKeputusanPTG() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    strService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    //Keputusan PTD - RAYL - start
    public Resolution tambahKeputusanRowPTD() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 4:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                listKertasSyorPTD.add(pkk);
                break;
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKeputusanPTD() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 4:

                updateKandungan(4, kand);

                break;
            default:
                LOG.info("alamak!! tiada index");
        }
        rehydrate();

        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniKeputusanPTD() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand + "idKandungan" + idKand);
        InfoAudit ia = new InfoAudit();
        if (idKand != null) {
            PermohonanKertasKandungan plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {
                ia = plk.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                plk.setInfoAudit(ia);
                plk.setKandungan(kand);
                permohonanKertasKandDAO.saveOrUpdate(plk);
            }
        }


        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Dikemaskini");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRowKeputusanPTD() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    strService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }
    //Finish - keputusan PTD

    public void updateKandungan(int i, String kand) {


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());


        if (mohonKertas != null) {
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            mohonKertas.setTajuk("Kertas Ringkas Rayuan PTD");
            KodDokumen kod = kodDokumenDAO.findById("KRPTD");
            mohonKertas.setKodDokumen(kod);
        } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
            mohonKertas.setTajuk("Kertas Ringkas Rayuan PTD");
            KodDokumen kod = kodDokumenDAO.findById("KRPTG");
            mohonKertas.setKodDokumen(kod);
        } else if (permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RYKN")) {
            mohonKertas.setTajuk("Kertas MMK");
            KodDokumen kod = kodDokumenDAO.findById("MMK");
            mohonKertas.setKodDokumen(kod);
        }
        mohonKertas.setCawangan(cawangan);
        mohonKertas.setInfoAudit(infoAudit);
        mohonKertas.setPermohonan(permohonan);
        strService.simpanPermohonanKertas(mohonKertas);

        long a = mohonKertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = strService.findByIdLapor(a, i);

        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
        LOG.info("index :" + i + " kand :" + kand + " id_lapor :" + a);



        if (plk.isEmpty()) {
            pLK.setSubtajuk("1");
            LOG.info("PLK" + pLK.getSubtajuk());
        } else {
            int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;
            //    int test = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk().substring(2, 3)) + 1;

            pLK.setSubtajuk(String.valueOf(n));
        }
        pLK.setBil((short) i);
        pLK.setKandungan(kand);
        pLK.setKertas(mohonKertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        strService.simpanPermohonanKertasKandungan(pLK);

    }

    public Resolution kemaskiniTajuk() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "KRPTD");
        } else if (permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RYKN")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "MMK");
        } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "KRPTG");
        }
        List<PermohonanKertasKandungan> plk0 = strService.findByIdLapor(mohonKertas.getIdKertas(), 0);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan kertasK0 = plk0.get(0);
        infoAudit = kertasK0.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        kertasK0.setCawangan(cawangan);
        kertasK0.setInfoAudit(infoAudit);
        kertasK0.setKertas(mohonKertas);
        kertasK0.setBil(0);
        kertasK0.setSubtajuk("1");
        kertasK0.setKandungan(tajuk.toUpperCase());
        plpService.simpanPermohonanKertasKandungan(kertasK0);

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniLatarBelakang() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "KRPTD");
        } else if (permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RYKN")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "MMK");
        } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "KRPTG");
        }
//        List<PermohonanKertasKandungan> plk = strService.findByIdLapor(mohonKertas.getIdKertas(), 1);
//         PermohonanKertasKandungan plk = plpService.findByBilNIdKertasNSubtajuk(2, mohonKertas.getIdKertas(), "1");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
//         PermohonanKertasKandungan kertasK0 = plk.get(0);
        PermohonanKertasKandungan kertasK0 = plpService.findByBilNIdKertasNSubtajuk(2, mohonKertas.getIdKertas(), "0");
        if (kertasK0 != null) {
            infoAudit = kertasK0.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            kertasK0.setCawangan(cawangan);
            kertasK0.setInfoAudit(infoAudit);
            kertasK0.setKertas(mohonKertas);
            kertasK0.setBil(2);
            kertasK0.setSubtajuk("1");
            kertasK0.setKandungan(latarBelakang);
            plpService.simpanPermohonanKertasKandungan(kertasK0);
        } else {
            //List<PermohonanKertasKandungan> plk0 = strService.findByIdLapor(mohonKertas.getIdKertas(), 2);
            //PermohonanKertasKandungan kertasK1 = plk0.get(0);
            PermohonanKertasKandungan kertasK1 = plpService.findByBilNIdKertasNSubtajuk(2, mohonKertas.getIdKertas(), "0");
            if (kertasK1 == null) {
                kertasK1 = new PermohonanKertasKandungan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                kertasK1.setCawangan(cawangan);
                kertasK1.setInfoAudit(infoAudit);
                kertasK1.setKertas(mohonKertas);
                kertasK1.setBil(2);
                kertasK1.setSubtajuk("0");
                kertasK1.setKandungan(latarBelakang);
                plpService.simpanPermohonanKertasKandungan(kertasK1);
            }

        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniTujuan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "KRPTD");
        } else if (permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RYKN")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "MMK");
        } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "KRPTG");
        }
        List<PermohonanKertasKandungan> plk = strService.findByIdLapor(mohonKertas.getIdKertas(), 1);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan kertasK0 = plk.get(0);
        infoAudit = kertasK0.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        kertasK0.setCawangan(cawangan);
        kertasK0.setInfoAudit(infoAudit);
        kertasK0.setKertas(mohonKertas);
        kertasK0.setBil(1);
        kertasK0.setSubtajuk("1");
        kertasK0.setKandungan(tujuan);
        plpService.simpanPermohonanKertasKandungan(kertasK0);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_ns.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String idAliran = new String();
        if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
            idAliran = "08Keputusan";
        } else if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            idAliran = "04Keputusan";
        }
        if (!StringUtils.isEmpty(idAliran)) {
            mohonFasa = plpService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliran);
        }
        if (permohonan.getPermohonanSebelum() != null) {
            idPermohonanSebelum = permohonan.getPermohonanSebelum().getIdPermohonan();
            if (idPermohonanSebelum != null) {
                hakmilikPermohonan = plpService.findByIdPermohonan(idPermohonanSebelum);
                pemohon = plpService.findPemohonByIdPemohon(idPermohonanSebelum);
                if (pemohon != null) {
                    pihak = pemohon.getPihak();
                }
            }

            if (("05").equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPTG")) {
                    Permohonan idPermohonanRAYL = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                    System.out.println("::::::::::id RAYL" + idPermohonanRAYL.getIdPermohonan());
                    if (idPermohonanRAYL != null) {
                        if (idPermohonanRAYL.getPermohonanSebelum() != null) {
                            Permohonan idPermohonanPBMT = permohonanDAO.findById(idPermohonanRAYL.getPermohonanSebelum().getIdPermohonan());
                            hakmilikPermohonan = plpService.findByIdPermohonan(idPermohonanPBMT.getIdPermohonan());
                            pemohon = plpService.findPemohonByIdPemohon(idPermohonanPBMT.getIdPermohonan());
                            pihak = pemohon.getPihak();
                        }
                    }
                }
            }

        }
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "KRPTD");
        } else if (permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RYKN")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "MMK");
        } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "KRPTG");
        } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "MMK");
        } else if (permohonan.getKodUrusan().getKod().equals("PTPBP")) {
            mohonKertas = plpService.findKertasByKod(idPermohonan, "MMK");
        }
        if (mohonKertas != null) {
            latarBelakangList = strService.findByIdLapor(mohonKertas.getIdKertas(), 2);
            listKertasHuraianPTD = strService.findByIdLapor(mohonKertas.getIdKertas(), 3);
            listKertasSyorPTD = strService.findByIdLapor(mohonKertas.getIdKertas(), 4);
            listKertasPerakuanPTG = strService.findByIdLapor(mohonKertas.getIdKertas(), 5);
            listKertasKeputusanPTG = strService.findByIdLapor(mohonKertas.getIdKertas(), 6);
            tarikhSidang = mohonKertas.getTarikhSidang();
            bilMesy = mohonKertas.getTempatSidang();
        }

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());


        if (mohonKertas != null) {
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            mohonKertas.setTajuk("Kertas Ringkas Rayuan PTD");
            KodDokumen kod = kodDokumenDAO.findById("KRPTD");
            mohonKertas.setKodDokumen(kod);
        } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
            mohonKertas.setTajuk("Kertas Ringkas Rayuan PTG");
            KodDokumen kod = kodDokumenDAO.findById("KRPTG");
            mohonKertas.setKodDokumen(kod);
        } else if (permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RYKN")) {
            mohonKertas.setTajuk("Kertas MMK");
            KodDokumen kod = kodDokumenDAO.findById("MMK");
            mohonKertas.setKodDokumen(kod);
        } else if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
            mohonKertas.setTajuk("Kertas MMK");
            KodDokumen kod = kodDokumenDAO.findById("MMK");
            mohonKertas.setKodDokumen(kod);
        } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
            mohonKertas.setTajuk("Kertas MMK");
            KodDokumen kod = kodDokumenDAO.findById("MMK");
            mohonKertas.setKodDokumen(kod);
            hakmilikPermohonan = plpService.findByIdPermohonan(idPermohonan);
            pemohon = plpService.findPemohonByIdPemohon(idPermohonan);
            pihak = pemohon.getPihak();
        } else if (permohonan.getKodUrusan().getKod().equals("PTPBP")) {
            mohonKertas.setTajuk("Kertas MMK");
            KodDokumen kod = kodDokumenDAO.findById("MMK");
            mohonKertas.setKodDokumen(kod);
            hakmilikPermohonan = plpService.findByIdPermohonan(idPermohonan);
            pemohon = plpService.findPemohonByIdPemohon(idPermohonan);
            pihak = pemohon.getPihak();
        }
        mohonKertas.setCawangan(cawangan);
        mohonKertas.setInfoAudit(infoAudit);
        mohonKertas.setPermohonan(permohonan);
        strService.simpanPermohonanKertas(mohonKertas);

        //Untuk tujuan
        String noLot = "";
        String luas = "";
        String koduom = "";
        String bpm = "";
        String daerah = "";
        String ktanah = "";
        tujuan = " ";
        if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getNoLot() != null) {
                noLot = " Lot " + hakmilikPermohonan.getNoLot();
            }
            if (hakmilikPermohonan.getLuasTerlibat() != null) {
                luas = "seluas " + hakmilikPermohonan.getLuasTerlibat().toString() + " " + hakmilikPermohonan.getKodUnitLuas().getNama();
            }
            if (hakmilikPermohonan.getKodUnitLuas() != null) {
                koduom = hakmilikPermohonan.getKodUnitLuas().getNama();
            }
            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = pelupusanUtiliti.convertStringtoInitCap(hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
            }
            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                ktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
            }

        }

        if (permohonan.getCawangan() != null) {
            daerah = pelupusanUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama());
        }
        //Untuk Tanah
        String kodMilik = "";
        String lokasi = "";
        String and = "";
        perihalTanah = " ";
        if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getKodMilik() != null) {
                kodMilik = " tanah " + hakmilikPermohonan.getKodMilik().getNama();
            }
            if (hakmilikPermohonan.getLokasi() != null) {
                lokasi = " terletak di " + pelupusanUtiliti.convertStringtoInitCap(hakmilikPermohonan.getLokasi());
            }
        }
        if (!kodMilik.equals("") && !lokasi.equals("")) {
            and = " dan ";
        }
        //Untuk Pemohon
        String kerja = "";
        String nama = "";
        String noP = "";
        String warganegara = "";
        String umurTangungan = "";
        perihalPemohon = " ";
        if (pihak != null) {
            if (pihak.getNama() != null) {
                nama = pelupusanUtiliti.convertStringtoInitCap(pihak.getNama());
            }
            if (pihak.getNoPengenalan() != null) {
                noP = " No. KP: " + pihak.getNoPengenalan();
            }
            if (pihak.getWargaNegara() != null) {
                warganegara = " adalah seorang warganegara " + pelupusanUtiliti.convertStringtoInitCap(pihak.getWargaNegara().getNama());
            }

        }

        if (pemohon.getUmur() != null && pemohon.getTanggungan() != null) {
            umurTangungan = ".Pemohon berumur " + pemohon.getUmur()
                    + " tahun dan mempunyai " + pemohon.getTanggungan() + " tanggungan.";
        }
        if (pemohon.getPekerjaan() != null && pemohon.getPendapatan() != null) {
            kerja = ".Pekerjaan pemohon adalah sebagai " + pelupusanUtiliti.convertStringtoInitCap(pemohon.getPekerjaan()) + " dengan pendapatan sebanyak RM "
                    + pemohon.getPendapatan() + " sebulan ";
        }

        List<PermohonanKertasKandungan> plk0 = strService.findByIdLapor(mohonKertas.getIdKertas(), 0);
        List<PermohonanKertasKandungan> plk = strService.findByIdLapor(mohonKertas.getIdKertas(), 1);
        //GET PERMOHONAN SEBELUM UNTUK LATAR BELAKANG
        PermohonanKertas mohonKertasSblm = new PermohonanKertas();
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            mohonKertasSblm = plpService.findMohonanKertasByIdPermohonanNKodDok(permohonan.getPermohonanSebelum().getIdPermohonan(), "MMK");
            if (mohonKertasSblm == null) {
                mohonKertasSblm = new PermohonanKertas();
                mohonKertasSblm = plpService.findMohonanKertasByIdPermohonanNKodDok(permohonan.getPermohonanSebelum().getIdPermohonan(), "RMN");
            }
        } else {
            if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                mohonKertasSblm = plpService.findMohonanKertasByIdPermohonanNKodDok(idPermohonan, "JKBB");
            } else {
                mohonKertasSblm = plpService.findMohonanKertasByIdPermohonanNKodDok(permohonan.getPermohonanSebelum().getIdPermohonan(), "MMK");
            }
        }
        PermohonanKertasKandungan plk2 = new PermohonanKertasKandungan();
        if (mohonKertasSblm != null) {
            plk2 = plpService.findByBilNIdKertasNSubtajuk(2, mohonKertasSblm.getIdKertas(), "1");
        }
        if (plk2 != null) {
            latarBelakang = plk2.getKandungan();
        } else {
            HakmilikPermohonan mohonHM = new HakmilikPermohonan();
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("LPSP")) {
                mohonHM = plpService.findHakmilikPermohonan(idPermohonan);
                tujuan = "Kertas ini disediakan adalah untuk mendapat pertimbangan Pentadbir Tanah Daerah terhadap permohonan daripada " + nama + " untuk "
                        + permohonan.getKodUrusan().getNama() + " ke atas kelulusan LPS dan Permit " + luas + lokasi + ", " + bpm + ", " + daerah + " untuk tujuan " + ktanah;

                tajuk = "PENANGGUHAN PERMOHONAN LPS DAN PERMIT " + luas.toUpperCase() + lokasi.toUpperCase()
                        + ", " + bpm.toUpperCase() + ", " + daerah.toUpperCase() + " UNTUK TUJUAN " + ktanah.toUpperCase();
            } else {
                mohonHM = plpService.findHakmilikPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            }
            latarBelakang = "Majlis Mesyuarat Kerajaan, Negeri Sembilan yang bersidang pada " + mohonKertasSblm.getTarikhSidang() + " telah menimbangkan Kertas Mesyuarat No."
                    + mohonKertasSblm.getNomborRujukan() + " dan membuat keputusan meluluskan pemberimilikan tanah secara hakmilik tetap mengikut Seksyen 76 Kanun Tanah Negara seluas lebih kurang "
                    + mohonHM.getLuasTerlibat() + " " + mohonHM.getKodUnitLuas().getNama() + " di " + mohonHM.getLokasi() + ", " + mohonHM.getBandarPekanMukimBaru().getNama() + " Daerah " + permohonan.getPermohonanSebelum().getCawangan().getDaerah().getNama() + " untuk kegunaan " + mohonHM.getKategoriTanahBaru().getNama()
                    + " dengan syarat-syarat kelulusan seperti berikut:-";
        }
        //Rayuan PTDif (permohonan.getKodUrusan().getKod().equals("RAYL")) {
        if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
            if (plk0.isEmpty() && plk.isEmpty()) {

                tujuan = "Kertas ini disediakan adalah untuk mendapat pertimbangan Pentadbir Tanah Daerah terhadap permohonan daripada " + nama + " untuk "
                        + permohonan.getKodUrusan().getNama() + " ke atas kelulusan pemberimilikan tanah kerajaan " + luas + lokasi + ", " + bpm + ", " + daerah + " untuk tujuan " + ktanah;

                tajuk = "RAYUAN UNTUK MELANJUTKAN TEMPOH BAYARAN PREMIUM DI ATAS KELULUSAN PEMBERIMILIKAN TANAH KERAJAAN " + luas.toUpperCase() + lokasi.toUpperCase()
                        + ", " + bpm.toUpperCase() + ", " + daerah.toUpperCase() + " UNTUK TUJUAN " + ktanah.toUpperCase();
            } else {
                tajuk = plk0.get(0).getKandungan();
                tujuan = plk.get(0).getKandungan();
            }
        } //Rayuan PTG
        else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
            if (plk0.isEmpty() && plk.isEmpty()) {
                tujuan = "Kertas ini disediakan adalah untuk mendapat pertimbangan Pentadbir Tanah dan Galian terhadap permohonan daripada " + nama + " untuk "
                        + permohonan.getKodUrusan().getNama() + " ke atas kelulusan pemberimilikan tanah kerajaan " + luas + lokasi + ", " + bpm + ", " + daerah + " untuk tujuan " + ktanah;

                tajuk = "RAYUAN UNTUK MELANJUTKAN TEMPOH BAYARAN PREMIUM DI ATAS KELULUSAN PEMBERIMILIKAN TANAH KERAJAAN " + luas.toUpperCase() + lokasi.toUpperCase()
                        + ", " + bpm.toUpperCase() + ", " + daerah.toUpperCase() + " UNTUK TUJUAN " + ktanah.toUpperCase();
            } else {
                tajuk = plk0.get(0).getKandungan();
                tujuan = plk.get(0).getKandungan();
            }
        } //Rayuan Premium
        else if (permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RYKN")) {
            if (plk0.isEmpty() && plk.isEmpty()) {
                tujuan = "Kertas ini disediakan adalah untuk mendapat pertimbangan Pentadbir Tanah dan Galian terhadap permohonan daripada " + nama + " untuk "
                        + permohonan.getKodUrusan().getNama() + " ke atas kelulusan pemberimilikan tanah kerajaan " + luas + lokasi + ", " + bpm + ", " + daerah + " untuk tujuan " + ktanah;

                tajuk = "RAYUAN DARIPADA " + nama + " UNTUK MENDAPAT PENGURANGAN BAYARAN PREMIUM DI ATAS KELULUSAN PEMBERIMILIKAN TANAH KERAJAAN SECARA HAKMILIK TETAP DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA SELUAS " + luas.toUpperCase() + " " + lokasi.toUpperCase()
                        + ", " + bpm.toUpperCase() + ", " + daerah.toUpperCase() + " UNTUK TUJUAN " + ktanah.toUpperCase();
            } else {
                tajuk = plk0.get(0).getKandungan();
                tujuan = plk.get(0).getKandungan();
            }
        }


        // make kandungan tajuk and tujuan
        if (plk0.isEmpty()) {
            PermohonanKertasKandungan kertasK0 = new PermohonanKertasKandungan();
            kertasK0.setCawangan(cawangan);
            kertasK0.setInfoAudit(infoAudit);
            kertasK0.setKertas(mohonKertas);
            kertasK0.setBil(0);
            kertasK0.setSubtajuk("1");
            kertasK0.setKandungan(tajuk);
            plpService.simpanPermohonanKertasKandungan(kertasK0);
        }
        if (plk.isEmpty()) {
            PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
            kertasK1.setCawangan(cawangan);
            kertasK1.setInfoAudit(infoAudit);
            kertasK1.setKertas(mohonKertas);
            kertasK1.setBil(1);
            kertasK1.setSubtajuk("1");
            kertasK1.setKandungan(tujuan);
            plpService.simpanPermohonanKertasKandungan(kertasK1);
        }
    }

    public List<PermohonanKertasKandungan> getListKertasHuraianPTD() {
        return listKertasHuraianPTD;
    }

    public void setListKertasHuraianPTD(List<PermohonanKertasKandungan> listKertasHuraianPTD) {
        this.listKertasHuraianPTD = listKertasHuraianPTD;
    }

    public List<PermohonanKertasKandungan> getListKertasSyorPTD() {
        return listKertasSyorPTD;
    }

    public void setListKertasSyorPTD(List<PermohonanKertasKandungan> listKertasSyorPTD) {
        this.listKertasSyorPTD = listKertasSyorPTD;
    }

    public List<PermohonanKertasKandungan> getListKertasPerakuanPTG() {
        return listKertasPerakuanPTG;
    }

    public void setListKertasPerakuanPTG(List<PermohonanKertasKandungan> listKertasPerakuanPTG) {
        this.listKertasPerakuanPTG = listKertasPerakuanPTG;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getPerihalPemohon() {
        return perihalPemohon;
    }

    public void setPerihalPemohon(String perihalPemohon) {
        this.perihalPemohon = perihalPemohon;
    }

    public String getPerihalTanah() {
        return perihalTanah;
    }

    public void setPerihalTanah(String perihalTanah) {
        this.perihalTanah = perihalTanah;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<PermohonanKertasKandungan> getLatarBelakangList() {
        return latarBelakangList;
    }

    public void setLatarBelakangList(List<PermohonanKertasKandungan> latarBelakangList) {
        this.latarBelakangList = latarBelakangList;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public List<PermohonanKertasKandungan> getListKertasKeputusanPTG() {
        return listKertasKeputusanPTG;
    }

    public void setListKertasKeputusanPTG(List<PermohonanKertasKandungan> listKertasKeputusanPTG) {
        this.listKertasKeputusanPTG = listKertasKeputusanPTG;
    }

    public String getBilMesy() {
        return bilMesy;
    }

    public void setBilMesy(String bilMesy) {
        this.bilMesy = bilMesy;
    }

    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public PelupusanUtiliti getPelupusanUtiliti() {
        return pelupusanUtiliti;
    }

    public void setPelupusanUtiliti(PelupusanUtiliti pelupusanUtiliti) {
        this.pelupusanUtiliti = pelupusanUtiliti;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }
}

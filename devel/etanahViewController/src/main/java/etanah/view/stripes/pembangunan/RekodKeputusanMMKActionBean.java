/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

/**
 *
 * @author NageswaraRao
 */
import etanah.dao.KodRujukanDAO;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import java.util.logging.Level;
import java.util.logging.Logger;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.KodDokumenDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodUOM;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanRujukanLuar;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.SimpleDateFormat;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.service.BPelService;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@UrlBinding("/pembangunan/melaka/rekod_keputusan_MMK")
public class RekodKeputusanMMKActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PembangunanService devService;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String kodNegeri;
    String tarikhMesyuarat;
    String tarikhSidang;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private String keputusan;
    private String jam;
    private String minit;
    private String saat;
    private String ampm;
    private String ampmDisplay;
    private String keputusanDisplay;
    private String negeri;
    private String nomborRujukan;
    private PermohonanKertas Pkertas;
    private PermohonanKertas permohonanKertas;
    private NoPt noPt;
    private HakmilikPermohonan hakmilikPermohonan;
    private KodKeputusan kodKeputusan;
    private KodUOM kodLuasLulus;
    private String stageId;
    Task task = null;
    private Pengguna pguna;
    private BPelService service;
    private Boolean edit;
    private Boolean button;
    private String idPermohonan;
    private PermohonanKertas kertas;
    private String masasidang;
    private String kpsn;
    private String kpsnnama;
    private FasaPermohonan fp;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("button", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanTiadaResult", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanDate", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("displayTarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan2", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showOnlyForTerimaKeputusan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuaratTerima", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution viewKeputusan() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("button2", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution viewKeputusan2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("button2", Boolean.FALSE);
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    private Resolution showFormGSA() {
        kodNegeri = conf.getProperty("kodNegeri");
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }

        if (idPermohonan != null) {
            kodNegeri = conf.getProperty("kodNegeri");
            permohonan = permohonanDAO.findById(idPermohonan);
            List<PermohonanKertas> listPermohonanKertas = new ArrayList<PermohonanKertas>();

//            listPermohonanKertas = devService.findMohonanKertasByIdPermohonan(idPermohonan);

//            permohonanKertas = listPermohonanKertas.get(0);

//            permohonanKertas = devService.findKertasByKod(idPermohonan, "MMKT");
            permohonanKertas = devService.findKertasByKod(idPermohonan, "MMKN");
//            if (permohonanKertas == null) {
//                permohonanKertas = new PermohonanKertas();
//                permohonanKertas.getPermohonan();
//            }
            if (permohonan.getKeputusan() != null) {
                kpsn = permohonan.getKeputusan().getKod();
                kpsnnama = permohonan.getKeputusan().getNama();
            }
            hakmilikPermohonan = devService.findByIdPermohonan(idPermohonan);
            permohonanRujukanLuar = devService.findUlasanPerRujLuar(idPermohonan, "MMKT");
            noPt = devService.findNoPtByIdHakmilikPermohonan(hakmilikPermohonan.getId());
            System.out.println("noPt:-----------:" + noPt);

        }
    }

    public Resolution simpanMesyuarat() {
        KodDokumen kd = kodDokumenDAO.findById("MMKT");
        KodDokumen kd1 = kodDokumenDAO.findById("MMKN");
        InfoAudit info = new InfoAudit();
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (permohonanKertas != null) {
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            permohonanKertas.setInfoAudit(info);
            permohonanKertas.setKodDokumen(kd1);
            permohonanKertas.setCawangan(pguna.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);

            String tempatSidang = getContext().getRequest().getParameter("permohonanKertas.tempatSidang");
            permohonanKertas.setTempatSidang(tempatSidang);
            devService.simpanPermohonanKertas(permohonanKertas);
        }


        if (idPermohonan != null) {
            info = permohonan.getInfoAudit();
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(info);
            permohonan.setKeputusanOleh(pguna);
            if (kpsn != null) {
                permohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
            }
            permohonan.setTarikhKeputusan(new java.util.Date());
            devService.simpanPermohonan(permohonan);
        }

        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            if (noPt == null) {
                System.out.println("No PT --- null");
                noPt = new NoPt();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                noPt.setHakmilikPermohonan(hakmilikPermohonan);
                noPt.setInfoAudit(info);
                devService.simpanNoPt(noPt);
            } else {
                System.out.println("No PT --- not null");
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(permohonan.getInfoAudit().getTarikhKemaskini());
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
                noPt.setInfoAudit(info);
                noPt.setHakmilikPermohonan(hakmilikPermohonan);
                devService.simpanNoPt(noPt);
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            if (hakmilikPermohonan != null) {
                info = hakmilikPermohonan.getInfoAudit();
            }
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            String luas = getContext().getRequest().getParameter("luasLulusUom.kod");
            System.out.println("-------------" + luas);
            if (StringUtils.isNotBlank(luas)) {
                kodLuasLulus = new KodUOM();
                kodLuasLulus.setKod(luas);
                hakmilikPermohonan.setLuasLulusUom(kodLuasLulus);
            }
            devService.simpanHakmilikPermohonan(hakmilikPermohonan);
        }


        if (permohonanRujukanLuar == null) {
            System.out.println("-----if--------" + permohonanRujukanLuar);
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            info = new InfoAudit();
            info.setTarikhMasuk(new java.util.Date());
            info.setDimasukOleh(pguna);
            permohonanRujukanLuar.setInfoAudit(info);
        } else {
            System.out.println("-----else--------" + permohonanRujukanLuar);
            permohonanRujukanLuar.setInfoAudit(info);
        }
        permohonanRujukanLuar.setTarikhTerima(new java.util.Date());
        permohonanRujukanLuar.setCawangan(pguna.getKodCawangan());
        permohonanRujukanLuar.setKodDokumenRujukan(kd);
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("FL"));
        conService.simpanRujukanLuar(permohonanRujukanLuar);

        if (!permohonan.getKodUrusan().getKod().equals("425") && !permohonan.getKodUrusan().getKod().equals("425A")) {
            if (permohonan.getKeputusan().getKod() != null) {
                permohonanRujukanLuar = devService.findUlasanPerRujLuar(idPermohonan, "MMKT");
                fp = devService.findFasaPermohonanByIdAliran(idPermohonan, "rekodkpsnmmkncetaksurat");
                fp.setPermohonan(permohonan);
                fp.setKeputusan(permohonan.getKeputusan());
                fp.setUlasan(permohonanRujukanLuar.getUlasan());
                info = permohonan.getInfoAudit();
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
                fp.setInfoAudit(info);
                devService.simpanFasaPermohonan(fp);
            }
        }

        getContext().getRequest().setAttribute("button", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pembangunan/melaka/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTiadaResult() {
        if (tarikhMesyuarat == null || permohonanRujukanLuar == null || permohonanRujukanLuar.getNoSidang() == null || jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {

            if (tarikhMesyuarat == null && permohonanRujukanLuar == null && jam.equals("0")) {
                addSimpleError("Sila Masukkan Data Berikut.");
            } else if (permohonanRujukanLuar.getNoSidang() == null) {
                addSimpleError("Sila Masukkan Bilangan Mesyuarat.");
            } else if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
            } else if (jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {
                addSimpleError("Sila Masukkan Masa Dengan Lengkap.");
            }

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            if (saat.equals("0")) {
                saat = "00";
            }

            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":" + saat + " " + ampm;

            try {

                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(RekodKeputusanMMKActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setInfoAudit(infoAudit);

            conService.simpanRujukanLuar(permohonanRujukanLuar);

            tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanTiadaResult", Boolean.TRUE);

        return new JSP("pelupusan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");

    }

    public Resolution simpanDate() {
        if (tarikhMesyuarat == null || jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {

            if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
            } else if (jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {
                addSimpleError("Sila Masukkan Masa Dengan Lengkap.");
            }

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            if (saat.equals("0")) {
                saat = "00";
            }

            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":" + saat + " " + ampm;

            permohonanRujukanLuar = new PermohonanRujukanLuar();

            try {

                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(RekodKeputusanMMKActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setInfoAudit(infoAudit);

            conService.simpanRujukanLuar(permohonanRujukanLuar);

            tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanDate", Boolean.TRUE);

        return new JSP("pelupusan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");

    }

    public Resolution simpanUlasan() {
        if (keputusan == null) {
            addSimpleError("Sila Masukkan Keputusan.");

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit = permohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);

            permohonan.setInfoAudit(infoAudit);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            conService.simpanPermohonan(permohonan);

            infoAudit = new InfoAudit();
            infoAudit = permohonanRujukanLuar.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            permohonanRujukanLuar.setInfoAudit(infoAudit);
            conService.simpanRujukanLuar(permohonanRujukanLuar);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("displayTarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan", Boolean.TRUE);

        return new JSP("pelupusan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution simpanUlasan2() {
        if (tarikhMesyuarat == null || keputusan == null || jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {

            if (tarikhMesyuarat == null && jam.equals("0") && minit.equals("0") && saat.equals("0") && ampm.equals("0") && keputusan == null) {
                addSimpleError("Sila Masukkan Data Berikut.");
            } else if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
            } else if (jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {
                addSimpleError("Sila Masukkan Masa Dengan Lengkap.");
            } else if (keputusan == null) {
                addSimpleError("Sila Masukkan Keputusan.");
            }

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            conService.simpanPermohonan(permohonan);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            if (saat.equals("0")) {
                saat = "00";
            }

            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":" + saat + " " + ampm;

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<PermohonanRujukanLuar> listRujLuar;
            listRujLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();

            if (!(listRujLuar.isEmpty())) {
                permohonanRujLuarTemp = listRujLuar.get(0);
                infoAudit = new InfoAudit();
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }

            try {
                permohonanRujLuarTemp.setTarikhSidang(sdf.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(RekodKeputusanMMKActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanRujLuarTemp.setCawangan(peng.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            if (permohonanRujukanLuar != null) {
                permohonanRujLuarTemp.setUlasan(permohonanRujukanLuar.getUlasan());

            }

            conService.simpanRujukanLuar(permohonanRujLuarTemp);

            tarikhMesyuarat = sdf.format(permohonanRujLuarTemp.getTarikhSidang()).substring(0, 10);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan2", Boolean.TRUE);

        return new JSP("pelupusan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getAmpmDisplay() {
        return ampmDisplay;
    }

    public void setAmpmDisplay(String ampmDisplay) {
        this.ampmDisplay = ampmDisplay;
    }

    public String getKeputusanDisplay() {
        return keputusanDisplay;
    }

    public void setKeputusanDisplay(String keputusanDisplay) {
        this.keputusanDisplay = keputusanDisplay;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Boolean getButton() {
        return button;
    }

    public void setButton(Boolean button) {
        this.button = button;
    }

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
    }

    public String getKpsnnama() {
        return kpsnnama;
    }

    public void setKpsnnama(String kpsnnama) {
        this.kpsnnama = kpsnnama;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public KodUOM getKodLuasLulus() {
        return kodLuasLulus;
    }

    public void setKodLuasLulus(KodUOM kodLuasLulus) {
        this.kodLuasLulus = kodLuasLulus;
    }

    public FasaPermohonan getFp() {
        return fp;
    }

    public void setFp(FasaPermohonan fp) {
        this.fp = fp;
    }
}

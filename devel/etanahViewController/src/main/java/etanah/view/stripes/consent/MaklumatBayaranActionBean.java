/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author muhammad.amin
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodTuntut;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/consent/maklumat_bayaran")
public class MaklumatBayaranActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService consentService;
    etanahActionBeanContext ctx;
    private static final Logger LOG = Logger.getLogger(MaklumatBayaranActionBean.class);
    private String idHakmilik;
    private String stageId;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private PermohonanTuntutanKos permohonanTuntutanKosAsal;
    private PermohonanTuntutanKos permohonanTuntutanKosAsal2;
    private PermohonanTuntutanKos permohonanTuntutanKosAsal3;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PermohonanTuntutanKos permohonanTuntutanKos2;
    private PermohonanTuntutanKos permohonanTuntutanKos3;
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        ctx = (etanahActionBeanContext) getContext();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (StringUtils.isBlank(idHakmilik)) {
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        }

        HakmilikPermohonan hakmilikPermohonanTemp = consentService.findMohonHakmilikByIdH(idPermohonan, idHakmilik);
        senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        LOG.info(":: ID HAKMILIK : " + idHakmilik);
        LOG.info(":: Harga Hartanah 1 : " + hakmilikPermohonanTemp.getKeteranganKadarDaftar());
        if (hakmilikPermohonanTemp.getKeteranganKadarDaftar() == null) {
            addSimpleError("Sila masukkan maklumat harga hartanah terlebih dahulu pada kertas ringkas");
        }

        return new JSP("consent/maklumat_bayaran.jsp").addParameter("tab", "true");
    }

    public Resolution showFormDisplay() {
        return new JSP("consent/maklumat_bayaran.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        ctx = (etanahActionBeanContext) getContext();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (StringUtils.isBlank(idHakmilik)) {
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            }

            HakmilikPermohonan hakmilikPermohonanTemp = consentService.findMohonHakmilikByIdH(idPermohonan, idHakmilik);

            if (hakmilikPermohonanTemp != null) {

                LOG.info(":: Harga Hartanah : " + hakmilikPermohonanTemp.getKeteranganKadarDaftar());

                if (hakmilikPermohonanTemp.getKeteranganKadarDaftar() != null) {

                    BigDecimal yuran = new BigDecimal("0.00");
                    yuran = new BigDecimal(hakmilikPermohonanTemp.getKeteranganKadarDaftar()).multiply(new BigDecimal("0.02")); //HARGA HARTANAH * 2%
                    LOG.info(":: YURAN : " + yuran);

                    BPelService service = new BPelService();
                    String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

                    if (StringUtils.isNotBlank(taskId)) {
                        Task task = service.getTaskFromBPel(taskId, pengguna);
                        stageId = task.getSystemAttributes().getStage();
                    }

                    LOG.info(":: ID Hakmilik : " + idHakmilik);

                    senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
                    HakmilikPermohonan hakmilikPermohonan = consentService.findMohonHakmilikByIdH(idPermohonan, idHakmilik);
                    fasaPermohonan = consentService.findMohonFasaByStage(idPermohonan, "Stage9");

                    if (stageId.equals("Stage2") && fasaPermohonan != null) {

                        for (HakmilikPermohonan mohonHakmilik : permohonan.getSenaraiHakmilik()) {

                            List<PermohonanTuntutanKos> mohonTuntutKosAsalList = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON01", mohonHakmilik.getId(), "R");
                            List<PermohonanTuntutanKos> mohonTuntutKosAsalList2 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON02", mohonHakmilik.getId(), "R");
                            List<PermohonanTuntutanKos> mohonTuntutKosAsalList3 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON03", mohonHakmilik.getId(), "R");

                            if (mohonTuntutKosAsalList.isEmpty()) {
                                List<PermohonanTuntutanKos> mohonTuntutKosList = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON01", mohonHakmilik.getId(), null);
                                if (!mohonTuntutKosList.isEmpty()) {
                                    permohonanTuntutanKosAsal = mohonTuntutKosList.get(0);
                                    InfoAudit infoAudit = permohonanTuntutanKosAsal.getInfoAudit();
                                    infoAudit.setDiKemaskiniOleh(pengguna);
                                    infoAudit.setTarikhKemaskini(new java.util.Date());
                                    permohonanTuntutanKosAsal.setCatatan("RAYUAN");
                                    consentService.simpanMohonTuntutKos(permohonanTuntutanKosAsal);

                                }
                            }
                            if (mohonTuntutKosAsalList2.isEmpty()) {
                                List<PermohonanTuntutanKos> mohonTuntutKosList2 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON02", mohonHakmilik.getId(), null);

                                if (!mohonTuntutKosList2.isEmpty()) {
                                    permohonanTuntutanKosAsal2 = mohonTuntutKosList2.get(0);
                                    InfoAudit infoAudit = permohonanTuntutanKosAsal2.getInfoAudit();
                                    infoAudit.setDiKemaskiniOleh(pengguna);
                                    infoAudit.setTarikhKemaskini(new java.util.Date());
                                    permohonanTuntutanKosAsal2.setCatatan("RAYUAN");
                                    consentService.simpanMohonTuntutKos(permohonanTuntutanKosAsal2);
                                }
                            }
                            if (mohonTuntutKosAsalList3.isEmpty()) {
                                List<PermohonanTuntutanKos> mohonTuntutKosList3 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON03", mohonHakmilik.getId(), null);

                                if (!mohonTuntutKosList3.isEmpty()) {
                                    permohonanTuntutanKosAsal3 = mohonTuntutKosList3.get(0);
                                    InfoAudit infoAudit = permohonanTuntutanKosAsal3.getInfoAudit();
                                    infoAudit.setDiKemaskiniOleh(pengguna);
                                    infoAudit.setTarikhKemaskini(new java.util.Date());
                                    permohonanTuntutanKosAsal3.setCatatan("RAYUAN");
                                    consentService.simpanMohonTuntutKos(permohonanTuntutanKosAsal3);
                                }
                            }
                        }
                    }

                    List<PermohonanTuntutanKos> mohonTuntutKosAsalList = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON01", hakmilikPermohonan.getId(), "R");
                    List<PermohonanTuntutanKos> mohonTuntutKosAsalList2 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON02", hakmilikPermohonan.getId(), "R");
                    List<PermohonanTuntutanKos> mohonTuntutKosAsalList3 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON03", hakmilikPermohonan.getId(), "R");

                    if (!mohonTuntutKosAsalList.isEmpty()) {
                        permohonanTuntutanKosAsal = mohonTuntutKosAsalList.get(0);

                    }
                    if (!mohonTuntutKosAsalList2.isEmpty()) {
                        permohonanTuntutanKosAsal2 = mohonTuntutKosAsalList2.get(0);
                    }
                    if (!mohonTuntutKosAsalList3.isEmpty()) {
                        permohonanTuntutanKosAsal3 = mohonTuntutKosAsalList3.get(0);
                    }

                    List<PermohonanTuntutanKos> mohonTuntutKosList = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON01", hakmilikPermohonan.getId(), null);
                    List<PermohonanTuntutanKos> mohonTuntutKosList2 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON02", hakmilikPermohonan.getId(), null);
                    List<PermohonanTuntutanKos> mohonTuntutKosList3 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON03", hakmilikPermohonan.getId(), null);

                    if (!mohonTuntutKosList.isEmpty()) {
                        permohonanTuntutanKos = mohonTuntutKosList.get(0);
                    }
                    if (!mohonTuntutKosList2.isEmpty()) {
                        permohonanTuntutanKos2 = mohonTuntutKosList2.get(0);
                    }
                    if (!mohonTuntutKosList3.isEmpty()) {
                        permohonanTuntutanKos3 = mohonTuntutKosList3.get(0);
                    }

                    if (permohonanTuntutanKos == null) {
                        permohonanTuntutanKos = new PermohonanTuntutanKos();
                        permohonanTuntutanKos.setAmaunTuntutan(yuran);
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setDimasukOleh(pengguna);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        KodTuntut kodTuntut = new KodTuntut();
                        kodTuntut.setKod("CON01");
                        permohonanTuntutanKos.setCawangan(permohonan.getCawangan());
                        permohonanTuntutanKos.setPermohonan(permohonan);
                        permohonanTuntutanKos.setKodTuntut(kodTuntut);

                        permohonanTuntutanKos.setItem("Yuran Notis Kelulusan - " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                        permohonanTuntutanKos.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
                        permohonanTuntutanKos.setHakmilikPermohonan(hakmilikPermohonan);
                        permohonanTuntutanKos.setInfoAudit(infoAudit);
                        LOG.info("----Simpan mohon tuntut kos urusan MMKN Melaka (PREMIUM)----");
                        consentService.simpanMohonTuntutKos(permohonanTuntutanKos);
                    }
                }
            }
        }
    }

    public Resolution simpan() {

        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        LOG.info("ID Hakmilik Saving : " + idHakmilik);
        HakmilikPermohonan hakmilikPermohonan = consentService.findMohonHakmilikByIdH(permohonan.getIdPermohonan(), idHakmilik);

        List<PermohonanTuntutanKos> mohonTuntutKosList = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON01", hakmilikPermohonan.getId(), null);
        List<PermohonanTuntutanKos> mohonTuntutKosList2 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON02", hakmilikPermohonan.getId(), null);
        List<PermohonanTuntutanKos> mohonTuntutKosList3 = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON03", hakmilikPermohonan.getId(), null);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();

        if (permohonanTuntutanKos != null) {
            if (mohonTuntutKosList.isEmpty()) {

                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodTuntut kodTuntut = new KodTuntut();
                kodTuntut.setKod("CON01");
                permohonanTuntutanKos.setCawangan(permohonan.getCawangan());
                permohonanTuntutanKos.setPermohonan(permohonan);
                permohonanTuntutanKos.setKodTuntut(kodTuntut);

                permohonanTuntutanKos.setItem("Yuran Notis Kelulusan - " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                permohonanTuntutanKos.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
                permohonanTuntutanKos.setHakmilikPermohonan(hakmilikPermohonan);
                permohonanTuntutanKos.setInfoAudit(infoAudit);
                LOG.info("----Simpan mohon tuntut kos urusan MMKN Melaka (PREMIUM)----");
                consentService.simpanMohonTuntutKos(permohonanTuntutanKos);
            } else {

                mohonTuntutKos = mohonTuntutKosList.get(0);
                infoAudit = mohonTuntutKos.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                mohonTuntutKos.setAmaunTuntutan(permohonanTuntutanKos.getAmaunTuntutan());
                LOG.info("----Update mohon tuntut kos urusan MMKN Melaka (PREMIUM)----");
                consentService.simpanMohonTuntutKos(mohonTuntutKos);
            }
        }
        if (permohonanTuntutanKos2 != null) {
            if (mohonTuntutKosList2.isEmpty()) {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodTuntut kodTuntut = new KodTuntut();
                kodTuntut.setKod("CON02");
                permohonanTuntutanKos2.setCawangan(permohonan.getCawangan());
                permohonanTuntutanKos2.setPermohonan(permohonan);
                permohonanTuntutanKos2.setKodTuntut(kodTuntut);

                permohonanTuntutanKos2.setItem("Sumbangan Tabung Amanah Rumah Kos Rendah - Lebih Keluasan - " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                permohonanTuntutanKos2.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
                permohonanTuntutanKos2.setHakmilikPermohonan(hakmilikPermohonan);
                permohonanTuntutanKos2.setInfoAudit(infoAudit);
                LOG.info("----Simpan mohon tuntut kos urusan MMKN Melaka (TABUNG AMANAH) - Lebih Keluasan ----");
                consentService.simpanMohonTuntutKos(permohonanTuntutanKos2);
            } else {

                mohonTuntutKos = mohonTuntutKosList2.get(0);
                infoAudit = mohonTuntutKos.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                mohonTuntutKos.setAmaunTuntutan(permohonanTuntutanKos2.getAmaunTuntutan());
                LOG.info("----Update mohon tuntut kos urusan MMKN Melaka (TABUNG AMANAH) - Lebih Keluasan ----");
                consentService.simpanMohonTuntutKos(mohonTuntutKos);
            }
        }
        if (permohonanTuntutanKos3 != null) {
            if (mohonTuntutKosList3.isEmpty()) {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodTuntut kodTuntut = new KodTuntut();
                kodTuntut.setKod("CON03");
                permohonanTuntutanKos3.setCawangan(permohonan.getCawangan());
                permohonanTuntutanKos3.setPermohonan(permohonan);
                permohonanTuntutanKos3.setKodTuntut(kodTuntut);

                permohonanTuntutanKos3.setItem("Sumbangan Tabung Amanah Rumah Kos Rendah - Lebih Unit Maksima - " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                permohonanTuntutanKos3.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
                permohonanTuntutanKos3.setHakmilikPermohonan(hakmilikPermohonan);
                permohonanTuntutanKos3.setInfoAudit(infoAudit);
                LOG.info("----Simpan mohon tuntut kos urusan MMKN Melaka (TABUNG AMANAH) - Lebih Unit Maksima ----");
                consentService.simpanMohonTuntutKos(permohonanTuntutanKos3);
            } else {

                mohonTuntutKos = mohonTuntutKosList3.get(0);
                infoAudit = mohonTuntutKos.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                mohonTuntutKos.setAmaunTuntutan(permohonanTuntutanKos3.getAmaunTuntutan());
                LOG.info("----Update mohon tuntut kos urusan MMKN Melaka (TABUNG AMANAH) - Lebih Unit Maksima ----");
                consentService.simpanMohonTuntutKos(mohonTuntutKos);
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat berjaya disimpan.");

        return new JSP("consent/maklumat_bayaran.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution reloadEdit() {
        return showForm();
    }

    public Resolution reload() {
        return showFormDisplay();
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos2() {
        return permohonanTuntutanKos2;
    }

    public void setPermohonanTuntutanKos2(PermohonanTuntutanKos permohonanTuntutanKos2) {
        this.permohonanTuntutanKos2 = permohonanTuntutanKos2;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKosAsal() {
        return permohonanTuntutanKosAsal;
    }

    public void setPermohonanTuntutanKosAsal(PermohonanTuntutanKos permohonanTuntutanKosAsal) {
        this.permohonanTuntutanKosAsal = permohonanTuntutanKosAsal;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKosAsal2() {
        return permohonanTuntutanKosAsal2;
    }

    public void setPermohonanTuntutanKosAsal2(PermohonanTuntutanKos permohonanTuntutanKosAsal2) {
        this.permohonanTuntutanKosAsal2 = permohonanTuntutanKosAsal2;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKosAsal3() {
        return permohonanTuntutanKosAsal3;
    }

    public void setPermohonanTuntutanKosAsal3(PermohonanTuntutanKos permohonanTuntutanKosAsal3) {
        this.permohonanTuntutanKosAsal3 = permohonanTuntutanKosAsal3;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos3() {
        return permohonanTuntutanKos3;
    }

    public void setPermohonanTuntutanKos3(PermohonanTuntutanKos permohonanTuntutanKos3) {
        this.permohonanTuntutanKos3 = permohonanTuntutanKos3;
    }
}

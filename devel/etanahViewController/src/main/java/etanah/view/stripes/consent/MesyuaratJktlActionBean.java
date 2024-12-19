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
import etanah.model.InfoAudit;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.ParseException;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/consent/mesyuarat_jktl")
public class MesyuaratJktlActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService consentService;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> listMesyuaratTangguh;
    private String tarikhMesyuarat;
    private String jam;
    private String minit;
    private String ampm;
    private String stageId = "";
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution show() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/mesyuarat_jktl.jsp").addParameter("tab", "true");
    }

    public Resolution showMmk() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/mesyuarat_jktl.jsp").addParameter("tab", "true");
    }

    public Resolution showTangguh() {
        return new JSP("consent/mesyuarat_jktl.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparan() {
        return new JSP("consent/mesyuarat_jktl.jsp").addParameter("tab", "true");
    }

    public Resolution showMmkPaparan() {
        return new JSP("consent/mesyuarat_jktl.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

            if (StringUtils.isNotBlank(taskId)) {
                Task task = null;
                task = service.getTaskFromBPel(taskId, pengguna);
                stageId = task.getSystemAttributes().getStage();
            }
            //FOR URUSAN TANAH LADANG DENGAN SEKATAN
            if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {

                if (stageId.equals("Stage6") || stageId.equals("Stage7")) {
                    permohonanRujukanLuar = consentService.findMohonRujukanByNamaNotTangguh(idPermohonan, "MESYUARAT JKTL");

                    if (permohonanRujukanLuar != null) {
                        if (permohonanRujukanLuar.getTarikhSidang() != null) {
                            tarikhMesyuarat = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
                            jam = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(11, 13);
                            minit = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(14, 16);
                            ampm = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(20, 22);
                        }
                    }
                } else {
                    if (!stageId.equals("Stage12")) {
                        if (permohonan.getKeputusan() != null) {
                            if (permohonan.getKeputusan().getKod().equals("TQ") || permohonan.getKeputusan().getKod().equals("TF")) {

                                listMesyuaratTangguh = consentService.findSenaraiMohonRujukanByNamaSidang(idPermohonan, "MESYUARAT MMK");
                                int counter = 1;
                                PermohonanRujukanLuar rujukanLuarTangguh = new PermohonanRujukanLuar();

                                for (int i = 0; i < listMesyuaratTangguh.size(); i++) {
                                    PermohonanRujukanLuar rujukanLuar = new PermohonanRujukanLuar();
                                    rujukanLuar = listMesyuaratTangguh.get(i);

                                    if (rujukanLuar.getCatatan() != null) {
                                        counter++;
                                    } else {
                                        rujukanLuarTangguh = listMesyuaratTangguh.get(i);
                                    }
                                }

                                rujukanLuarTangguh.setCatatan("TANGGUH" + counter);
                                consentService.simpanRujukanLuar(rujukanLuarTangguh);

                                permohonan.setKeputusan(null);
                                consentService.simpanPermohonan(permohonan);
                            }
                        }
                    }
                    listMesyuaratTangguh = consentService.findSenaraiMohonRujukanByNamaTangguh(idPermohonan, "MESYUARAT MMK", "TANGGUH");
                    permohonanRujukanLuar = consentService.findMohonRujukanByNamaSidangNotTangguh(idPermohonan, "MESYUARAT MMK");

                    if (permohonanRujukanLuar != null) {
                        if (permohonanRujukanLuar.getTarikhSidang() != null) {
                            tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang());
                        }
                    }
                }
            } else {
                //FOR URUSAN TANAH LADANG TANPA SEKATAN
                permohonanRujukanLuar = consentService.findMohonRujukanByNamaNotTangguh(idPermohonan, "MESYUARAT JKTL");

                if (permohonanRujukanLuar != null) {
                    if (permohonanRujukanLuar.getTarikhSidang() != null) {
                        tarikhMesyuarat = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
                        jam = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(11, 13);
                        minit = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(14, 16);
                        ampm = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(20, 22);
                    }
                }
            }
        }
    }

    public Resolution simpan() {

        if (tarikhMesyuarat == null || permohonanRujukanLuar == null) {
            if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat");
            } else if (permohonanRujukanLuar == null) {
                addSimpleError("Sila Masukkan Bilangan Mesyuarat Dan Tempat Mesyuarat");
            }
        } else {

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();
            infoAudit = new InfoAudit();

            permohonanRujLuarTemp = consentService.findMohonRujukanByNamaNotTangguh(idPermohonan, "MESYUARAT JKTL");

            if (permohonanRujLuarTemp != null) {
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanRujLuarTemp = new PermohonanRujukanLuar();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

            }

            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":00 " + ampm;

            try {
                permohonanRujLuarTemp.setTarikhSidang(sdf2.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(MesyuaratJktlActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanRujLuarTemp.setNoSidang(permohonanRujukanLuar.getNoSidang());
            permohonanRujLuarTemp.setLokasiAgensi(permohonanRujukanLuar.getLokasiAgensi());
            permohonanRujLuarTemp.setCawangan(pengguna.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);
            permohonanRujLuarTemp.setNamaSidang("MESYUARAT JKTL");
            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);

            consentService.simpanRujukanLuar(permohonanRujLuarTemp);
            tarikhMesyuarat = sdf.format(permohonanRujLuarTemp.getTarikhSidang()).substring(0, 10);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("consent/mesyuarat_jktl.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMmk() {

        if (tarikhMesyuarat == null || permohonanRujukanLuar == null) {
            if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat");
            } else if (permohonanRujukanLuar == null) {
                addSimpleError("Sila Masukkan Bilangan Mesyuarat");
            }
        } else {

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();
            infoAudit = new InfoAudit();

            permohonanRujLuarTemp = consentService.findMohonRujukanByNamaNotTangguh(idPermohonan, "MESYUARAT MMK");

            if (permohonanRujLuarTemp != null) {
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanRujLuarTemp = new PermohonanRujukanLuar();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            try {
                permohonanRujLuarTemp.setTarikhSidang(sdf.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(MesyuaratJktlActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanRujLuarTemp.setNoSidang(permohonanRujukanLuar.getNoSidang());
            permohonanRujLuarTemp.setCawangan(pengguna.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);
            permohonanRujLuarTemp.setNamaSidang("MESYUARAT MMK");
            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);

            consentService.simpanRujukanLuar(permohonanRujLuarTemp);
            tarikhMesyuarat = sdf.format(permohonanRujLuarTemp.getTarikhSidang());
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("consent/mesyuarat_jktl.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<PermohonanRujukanLuar> getListMesyuaratTangguh() {
        return listMesyuaratTangguh;
    }

    public void setListMesyuaratTangguh(List<PermohonanRujukanLuar> listMesyuaratTangguh) {
        this.listMesyuaratTangguh = listMesyuaratTangguh;
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

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}



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

@UrlBinding("/consent/maklumat_mesyuarat")
public class MaklumatMesyuaratActionBean extends AbleActionBean {

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
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution show() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_mesyuarat.jsp").addParameter("tab", "true");
    }

    public Resolution showTangguh() {
        return new JSP("consent/maklumat_mesyuarat.jsp").addParameter("tab", "true");
    }

    public Resolution showFormDisplay() {
        return new JSP("consent/maklumat_mesyuarat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            String stageId = "";

            if (StringUtils.isNotBlank(taskId)) {
                Task task = null;
                task = service.getTaskFromBPel(taskId, pengguna);
                stageId = task.getSystemAttributes().getStage();
            }

            //FOR URUSAN TANAH LADANG NEGERI MELAKA
            if (permohonan.getKodUrusan().getIdWorkflow().endsWith("JKTL_melaka")) {

                if (stageId.equals("Stage6")) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("TQ")) {
                            listMesyuaratTangguh = consentService.findSenaraiMohonRujukanByAgensiIsNull(idPermohonan);

                            int counter = 1;
                            PermohonanRujukanLuar mesyuaratTangguh = new PermohonanRujukanLuar();

                            for (int i = 0; i < listMesyuaratTangguh.size(); i++) {
                                PermohonanRujukanLuar perRujLuar = new PermohonanRujukanLuar();
                                perRujLuar = listMesyuaratTangguh.get(i);

                                if (perRujLuar.getCatatan() != null) {
                                    counter++;
                                } else {
                                    mesyuaratTangguh = listMesyuaratTangguh.get(i);
                                }
                            }

                            mesyuaratTangguh.setCatatan("TANGGUH" + counter);
                            consentService.simpanRujukanLuar(mesyuaratTangguh);
                            permohonan.setKeputusan(null);
                            consentService.simpanPermohonan(permohonan);
                        }
                    }
                }

                listMesyuaratTangguh = consentService.findSenaraiMohonRujukanTangguh(idPermohonan, "TANGGUH");
                permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh2(idPermohonan);


                if (permohonanRujukanLuar != null) {
                    if (permohonanRujukanLuar.getTarikhSidang() != null) {
                        tarikhMesyuarat = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
                        jam = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(11, 13);
                        minit = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(14, 16);
                        ampm = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(20, 22);
                    }
                }

            } else {
                //FOR URUSAN MMK NEGERI SEMBILAN
                //FOR URUSAN TANAH ADAT MMK NEGERI SEMBILAN
                //FOR URUSAN RAYUAN TANAH LADANG NEGERI SEMBILAN
                String stageA;
                String stageB;

                if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                    stageA = "Stage14";
                    stageB = "Stage8";
                } else if (permohonan.getKodUrusan().getKod().equals("RJKTL")) {
                    stageA = "Stage16";
                    stageB = "Stage10";
                } else if (permohonan.getKodUrusan().getKod().equals("PMWNA") || permohonan.getKodUrusan().getKod().equals("PMWWA")) {
                    stageA = "Stage23";
                    stageB = "Stage9";
                } else if (permohonan.getKodUrusan().getKod().equals("KPTG1")) {
                    stageA = "Stage4";
                    stageB = "";
                } else {//for MMK NS
                    stageA = "Stage13";
                    stageB = "Stage9";
                }

                if (stageId.equals(stageA) || stageId.equals(stageB)) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("TQ") || permohonan.getKeputusan().getKod().equals("TF")) {
                            listMesyuaratTangguh = permohonan.getSenaraiRujukanLuar();
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
                listMesyuaratTangguh = consentService.findSenaraiMohonRujukanTangguh(idPermohonan, "TANGGUH");
                permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh(idPermohonan);

                if (permohonanRujukanLuar != null) {
                    if (permohonanRujukanLuar.getTarikhSidang() != null) {
                        tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
                    }
                }
            }
        }
    }

    public Resolution simpan() {

        //FOR URUSAN TANAH LADANG NEGERI MELAKA
        if (permohonan.getKodUrusan().getIdWorkflow().endsWith("JKTL_melaka")) {

            if (tarikhMesyuarat == null || permohonanRujukanLuar == null) {

                addSimpleError("Sila Masukkan Maklumat Persidangan Dengan Lengkap.");


//                if (tarikhMesyuarat == null) {
//                    addSimpleError("Sila Masukkan Tarikh Mesyuarat");
//                }
//                if (permohonanRujukanLuar == null) {
//                    addSimpleError("Sila Masukkan Maklumat Pesidangan");
//                }
            } else {

                if (jam == null || minit == null || ampm == null || permohonanRujukanLuar.getLokasiAgensi() == null || permohonanRujukanLuar.getNoSidang() == null) {
                    addSimpleError("Sila Masukkan Maklumat Persidangan Dengan Lengkap.");
                } else {
                    String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    permohonan = permohonanDAO.findById(idPermohonan);

                    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    InfoAudit infoAudit = new InfoAudit();

                    PermohonanRujukanLuar rujukanLuarTemp = new PermohonanRujukanLuar();
                    rujukanLuarTemp = consentService.findMohonRujukanNotTangguh2(idPermohonan);
                    infoAudit = new InfoAudit();

                    if (rujukanLuarTemp != null) {
                        infoAudit = rujukanLuarTemp.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(pengguna);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    } else {
                        rujukanLuarTemp = new PermohonanRujukanLuar();
                        infoAudit.setDimasukOleh(pengguna);
                        infoAudit.setTarikhMasuk(new java.util.Date());

                    }

                    tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":00 " + ampm;

                    try {
                        rujukanLuarTemp.setTarikhSidang(sdf2.parse(tarikhMesyuarat));
                    } catch (ParseException ex) {
                        Logger.getLogger(UlasanTanahLadangMelakaActionBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!listMesyuaratTangguh.isEmpty()) {
                        if (rujukanLuarTemp.getNoRujukan() == null) {
                            rujukanLuarTemp.setNoRujukan(listMesyuaratTangguh.get(0).getNoRujukan());
                        }
                    }

                    rujukanLuarTemp.setNoSidang(permohonanRujukanLuar.getNoSidang());
                    rujukanLuarTemp.setLokasiAgensi(permohonanRujukanLuar.getLokasiAgensi());

                    rujukanLuarTemp.setCawangan(permohonan.getCawangan());
                    KodRujukan kodRujukan = new KodRujukan();
                    kodRujukan.setKod("FL");
                    rujukanLuarTemp.setKodRujukan(kodRujukan);
                    rujukanLuarTemp.setPermohonan(permohonan);
                    rujukanLuarTemp.setInfoAudit(infoAudit);

                    consentService.simpanRujukanLuar(rujukanLuarTemp);
                    tarikhMesyuarat = sdf.format(rujukanLuarTemp.getTarikhSidang()).substring(0, 10);
                    jam = sdf2.format(rujukanLuarTemp.getTarikhSidang()).substring(11, 13);
                    minit = sdf2.format(rujukanLuarTemp.getTarikhSidang()).substring(14, 16);
                    ampm = sdf2.format(rujukanLuarTemp.getTarikhSidang()).substring(20, 22);

                    addSimpleMessage("Maklumat telah berjaya disimpan.");
                }
            }
        } else {
            //FOR URUSAN MMK NEGERI SEMBILAN
            //FOR URUSAN RAYUAN TANAH LADANG NEGERI SEMBILAN
            if (tarikhMesyuarat == null || permohonanRujukanLuar == null) {
                if (tarikhMesyuarat == null) {
                    addSimpleError("Sila Masukkan Tarikh Mesyuarat");
                } else if (permohonanRujukanLuar == null) {
                    addSimpleError("Sila Masukkan Maklumat Mesyuarat Dengan Lengkap");
                }
            } else {

                String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                permohonan = permohonanDAO.findById(idPermohonan);

                Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                InfoAudit infoAudit = new InfoAudit();

                PermohonanRujukanLuar rujukanLuarTemp = new PermohonanRujukanLuar();
                rujukanLuarTemp = consentService.findMohonRujukanNotTangguh(idPermohonan);
                infoAudit = new InfoAudit();

                if (rujukanLuarTemp != null) {
                    infoAudit = rujukanLuarTemp.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    rujukanLuarTemp = new PermohonanRujukanLuar();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                }

                try {
                    rujukanLuarTemp.setTarikhSidang(sdf.parse(tarikhMesyuarat));
                } catch (ParseException ex) {
                    Logger.getLogger(MaklumatMesyuaratActionBean.class.getName()).log(Level.SEVERE, null, ex);
                }

                rujukanLuarTemp.setNoSidang(permohonanRujukanLuar.getNoSidang());
                rujukanLuarTemp.setCawangan(permohonan.getCawangan());
                KodRujukan kodRujukan = new KodRujukan();
                kodRujukan.setKod("FL");
                rujukanLuarTemp.setKodRujukan(kodRujukan);
                rujukanLuarTemp.setPermohonan(permohonan);
                rujukanLuarTemp.setInfoAudit(infoAudit);

                consentService.simpanRujukanLuar(rujukanLuarTemp);
                tarikhMesyuarat = sdf.format(rujukanLuarTemp.getTarikhSidang()).substring(0, 10);

                addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("consent/maklumat_mesyuarat.jsp").addParameter("tab", "true");
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
}

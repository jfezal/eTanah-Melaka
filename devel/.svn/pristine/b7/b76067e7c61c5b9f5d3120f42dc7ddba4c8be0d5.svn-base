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
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPerbicaraan;
import etanah.model.PermohonanPerbicaraanKehadiran;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanUrusan;
import etanah.model.Pihak;
import etanah.model.SenaraiRujukan;
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
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/consent/maklumat_bicara_tanah_adat")
public class MaklumatBicaraTanahAdatActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService consentService;
    private Permohonan permohonan;
    private PermohonanPerbicaraan permohonanPerbicaraan;
    private List<PermohonanPerbicaraan> listPerbicaraanTangguh;
    private String idHakmilik;
    String keputusan;
    String keputusanDisplay;
    String tarikhMesyuarat;
    String jam;
    String minit;
    String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution show() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_bicara_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparan() {
        return new JSP("consent/maklumat_bicara_tanah_adat.jsp").addParameter("tab", "true");
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
            String stageIdCheck;

            if (StringUtils.isNotBlank(taskId)) {
                Task task = null;
                task = service.getTaskFromBPel(taskId, pengguna);
                stageId = task.getSystemAttributes().getStage();
            }

            if (permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/BANTAHAN_ADAT")) {
                stageIdCheck = "Stage11";
            } else if (permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/pmwna")) {
                stageIdCheck = "Stage16";
            } else {//all urusan adat
                stageIdCheck = "Stage3";
            }

            if (stageId.equals(stageIdCheck)) {
                if (permohonan.getKeputusan() != null) {
                    if (permohonan.getKeputusan().getKod().equals("TQ")) {
                        listPerbicaraanTangguh = consentService.findSenaraiMohonBicara(idPermohonan);

                        int counter = 1;
                        PermohonanPerbicaraan perBicaraTangguh = new PermohonanPerbicaraan();

                        for (int i = 0; i < listPerbicaraanTangguh.size(); i++) {
                            PermohonanPerbicaraan perBicara = new PermohonanPerbicaraan();
                            perBicara = listPerbicaraanTangguh.get(i);

                            if (perBicara.getCatatan() != null) {
                                counter++;
                            } else {
                                perBicaraTangguh = listPerbicaraanTangguh.get(i);
                            }
                        }

                        perBicaraTangguh.setCatatan("TANGGUH" + counter);
                        consentService.simpanPermohonanPerbicaraan(perBicaraTangguh);
                        permohonan.setKeputusan(null);
                        consentService.simpanPermohonan(permohonan);
                    }
                }
            }

            listPerbicaraanTangguh = consentService.findSenaraiMohonBicaraTangguh(idPermohonan, "TANGGUH");
            permohonanPerbicaraan = consentService.findMohonBicaraNotTangguh(idPermohonan);

            if (permohonanPerbicaraan != null) {
                if (permohonanPerbicaraan.getTarikhBicara() != null) {
                    tarikhMesyuarat = sdf2.format(permohonanPerbicaraan.getTarikhBicara()).substring(0, 10);
                    jam = sdf2.format(permohonanPerbicaraan.getTarikhBicara()).substring(11, 13);
                    minit = sdf2.format(permohonanPerbicaraan.getTarikhBicara()).substring(14, 16);
                    ampm = sdf2.format(permohonanPerbicaraan.getTarikhBicara()).substring(20, 22);
                }
            }
        }
    }

    public Resolution simpan() {
        if (tarikhMesyuarat == null || permohonanPerbicaraan == null) {
            if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat");
            }
            if (permohonanPerbicaraan == null) {
                addSimpleError("Sila Masukkan Tempat Mesyuarat");
            }
        } else {

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            PermohonanPerbicaraan permohonanPerbicaraanTemp = new PermohonanPerbicaraan();
            permohonanPerbicaraanTemp = consentService.findMohonBicaraNotTangguh(idPermohonan);
            infoAudit = new InfoAudit();

            if (permohonanPerbicaraanTemp != null) {
                infoAudit = permohonanPerbicaraanTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanPerbicaraanTemp = new PermohonanPerbicaraan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

            }

            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":00 " + ampm;

            try {
                permohonanPerbicaraanTemp.setTarikhBicara(sdf2.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(MaklumatBicaraTanahAdatActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanPerbicaraanTemp.setLokasiBicara(permohonanPerbicaraan.getLokasiBicara());
            permohonanPerbicaraanTemp.setCawangan(permohonan.getCawangan());
            permohonanPerbicaraanTemp.setPermohonan(permohonan);
            permohonanPerbicaraanTemp.setInfoAudit(infoAudit);

            consentService.simpanPermohonanPerbicaraan(permohonanPerbicaraanTemp);
            tarikhMesyuarat = sdf2.format(permohonanPerbicaraanTemp.getTarikhBicara()).substring(0, 10);

            //SAVE MOHON BICARA HADIR
            List<PermohonanPerbicaraanKehadiran> listPerbicaraanKehadiran = new ArrayList<PermohonanPerbicaraanKehadiran>();
            listPerbicaraanKehadiran = consentService.getSenaraiPerbicaraanKehadiranByIdBicara(permohonanPerbicaraanTemp.getIdPerbicaraan());

            List<PermohonanPerbicaraan> listPerbicaraan = consentService.findSenaraiMohonBicara(idPermohonan);

            if (listPerbicaraanKehadiran.isEmpty()) {

                PermohonanPerbicaraanKehadiran perbicaraanKehadiran = new PermohonanPerbicaraanKehadiran();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

                if (listPerbicaraan.size() > 1) { // SAVE UNTUK MAKLUMAT SURAT TANGGUH

                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        perbicaraanKehadiran = new PermohonanPerbicaraanKehadiran();
                        perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                        perbicaraanKehadiran.setPerbicaraan(permohonanPerbicaraanTemp);
                        perbicaraanKehadiran.setNama(pemohon.getPihak().getNama());
                        perbicaraanKehadiran.setJawatan("PEMOHON");
                        perbicaraanKehadiran.setInfoAudit(infoAudit);
                        consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
                    }

                } else { // SAVE UNTUK MAKLUMAT NOTIS BICARA

                    List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                    senaraiPermohonanPihak = permohonan.getSenaraiPihak();

                    boolean pemohonIsPenerima = false;
                    boolean pemohonIsPemilik = false;
                    List<Pihak> pemohonIsPenerimaList = new ArrayList<Pihak>();

                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        for (PermohonanPihak perPihak : senaraiPermohonanPihak) {
                            if (pemohon.getPihak().getIdPihak() == perPihak.getPihak().getIdPihak()) {
                                if (perPihak.getJenis().getKod().equals("TER")) {
                                    pemohonIsPemilik = true;
                                } else {
                                    pemohonIsPenerima = true;
                                }
                                pemohonIsPenerimaList.add(pemohon.getPihak());
                            }
                        }
                    }

                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        perbicaraanKehadiran = new PermohonanPerbicaraanKehadiran();
                        perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                        perbicaraanKehadiran.setPerbicaraan(permohonanPerbicaraanTemp);
                        perbicaraanKehadiran.setNama(pemohon.getPihak().getNama());
                        if (pemohonIsPenerima) {
                            perbicaraanKehadiran.setJawatan("PEMOHON/PENERIMA");
                        } else if (pemohonIsPemilik) {
                            perbicaraanKehadiran.setJawatan("PEMOHON/PEMILIK");
                        } else {
                            perbicaraanKehadiran.setJawatan("PEMOHON");
                        }
                        perbicaraanKehadiran.setInfoAudit(infoAudit);
                        consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
                    }

                    for (PermohonanPihak perPihak : senaraiPermohonanPihak) {
                        if (!perPihak.getJenis().getKod().equals("TER") && !perPihak.getJenis().getKod().equals("PGA")) {

                            boolean foundData = false;

                            List<PermohonanPerbicaraanKehadiran> senaraiBicaraHadir = new ArrayList<PermohonanPerbicaraanKehadiran>();
                            senaraiBicaraHadir = consentService.getSenaraiPerbicaraanKehadiranByIdBicara(permohonanPerbicaraanTemp.getIdPerbicaraan());

                            for (PermohonanPerbicaraanKehadiran bicaraHadir : senaraiBicaraHadir) {
                                if (bicaraHadir.getPihak() != null) {
                                    if (bicaraHadir.getPihak().getPihak().getIdPihak() == perPihak.getPihak().getIdPihak()) {
                                        foundData = true;
                                    }
                                }
                            }
                            boolean dataPemohon = false;
                            if (pemohonIsPenerima) {
                                for (Pihak pihak : pemohonIsPenerimaList) {
                                    if (pihak.getIdPihak() == perPihak.getPihak().getIdPihak()) {
                                        dataPemohon = true;
                                    }
                                }
                            }
                            if (!foundData && !dataPemohon) {
                                perbicaraanKehadiran = new PermohonanPerbicaraanKehadiran();
                                perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                                perbicaraanKehadiran.setPerbicaraan(permohonanPerbicaraanTemp);
                                perbicaraanKehadiran.setInfoAudit(infoAudit);
                                perbicaraanKehadiran.setPihak(perPihak);
                                consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
                            }
                        }
                    }
                }

                //SAVE DATOK LEMBAGA
                SenaraiRujukan senaraiRujukan = new SenaraiRujukan();

                PermohonanUrusan permohonanUrusanLembaga = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "LEMBAGA");

                if (permohonanUrusanLembaga != null && permohonanUrusanLembaga.getCatatan() != null) {
                    senaraiRujukan = consentService.findSenaraiRujukan(permohonanUrusanLembaga.getCatatan());

                }

                perbicaraanKehadiran = new PermohonanPerbicaraanKehadiran();
                perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                perbicaraanKehadiran.setPerbicaraan(permohonanPerbicaraanTemp);
                perbicaraanKehadiran.setNama(senaraiRujukan.getPerihal() + " " + senaraiRujukan.getNama());
                perbicaraanKehadiran.setJawatan("DATOK LEMBAGA");
                perbicaraanKehadiran.setInfoAudit(infoAudit);
                consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);

                //SAVE UNDANG LUAK
                if (permohonan.getKodUrusan().getKod().equals("BTADT")) {
                    PermohonanUrusan permohonanUrusan = new PermohonanUrusan();
                    permohonanUrusan = consentService.findMohonUrusanByPerihal(idPermohonan, "UNDANG LUAK");

                    SenaraiRujukan senaraiRujukan2 = new SenaraiRujukan();
                    senaraiRujukan2 = consentService.findSenaraiRujukan(permohonanUrusan.getCatatan());

                    perbicaraanKehadiran = new PermohonanPerbicaraanKehadiran();
                    perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                    perbicaraanKehadiran.setPerbicaraan(permohonanPerbicaraanTemp);
                    perbicaraanKehadiran.setNama(senaraiRujukan2.getPerihal() + " " + senaraiRujukan2.getNama());
                    perbicaraanKehadiran.setJawatan("UNDANG LUAK");
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
                }
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("consent/maklumat_bicara_tanah_adat.jsp").addParameter("tab", "true");
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

    public String getKeputusanDisplay() {
        return keputusanDisplay;
    }

    public void setKeputusanDisplay(String keputusanDisplay) {
        this.keputusanDisplay = keputusanDisplay;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
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

    public PermohonanPerbicaraan getPermohonanPerbicaraan() {
        return permohonanPerbicaraan;
    }

    public void setPermohonanPerbicaraan(PermohonanPerbicaraan permohonanPerbicaraan) {
        this.permohonanPerbicaraan = permohonanPerbicaraan;
    }

    public List<PermohonanPerbicaraan> getListPerbicaraanTangguh() {
        return listPerbicaraanTangguh;
    }

    public void setListPerbicaraanTangguh(List<PermohonanPerbicaraan> listPerbicaraanTangguh) {
        this.listPerbicaraanTangguh = listPerbicaraanTangguh;
    }
}

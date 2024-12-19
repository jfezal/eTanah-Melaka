/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.view.kaunter.*;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.LelonganDAO;

import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.AduanLokasi;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.Permohonan;

import etanah.model.Lelongan;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanNota;
import etanah.service.EnforceService;
import etanah.service.PengambilanService;
import etanah.service.common.EnforcementService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.service.common.PerbicaraanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author asri
 */
@UrlBinding("/pengambilan/status_permohonan")
public class UtilitiStatusPermohonanActionBean extends AbleActionBean {

    private Permohonan permohonan;
    private Enkuiri enkuiri;
    private String stage;
    private String status;
    private String participant;
    private String idHakmilik;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<HakmilikPermohonan> hakmilikMohonList;
    private List<Lelongan> senaraiLelongan;
    private List<Enkuiri> senaraiEnkuiri;
    private List<Permohonan> bpmList;
    private List<AduanLokasi> lokasiList;
    private List<Permohonan> jenisKesalahanList;
    private List<AduanLokasi> daerahList;
    private String daerah;
    private String bpm;
    private String jenisKesalahan;
    private String lokasi;
    private List<FasaPermohonan> senaraiFasaPermohonan;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    LelonganDAO lelongDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    private PembetulanService pService;
    @Inject
    EnforceService eService;
    @Inject
    PengambilanService service;
    @Inject
    private etanah.Configuration conf;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private TabManager tm;
    private String stageLabel;
    private List<PermohonanNota> listNota;
    private static final Logger log = Logger.getLogger(UtilitiStatusPermohonanActionBean.class);
    private Boolean semakanDokumen = Boolean.FALSE;
    private String fromPage;
    private Pengguna pengguna;
    private List<KodBandarPekanMukim> senaraiKodBPM = new ArrayList<KodBandarPekanMukim>();
    private Permohonan secondLayerPermohonan;
    private Permohonan thirdLayerPermohonan;
    private List<PermohonanNota> listNotaPermohonanSebelum;
    private List<PermohonanNota> listNotaPermohonanHD; //HD = sebahagian kompaun dakwa

    @DefaultHandler
    public Resolution insertIdPermohonan() {
        fromPage = "statusPermohonan";
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/semakanPermohonan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        senaraiKodBPM = eService.getSenaraiKodBPMByCawangan(pengguna.getKodCawangan().getDaerah().getKod());
        log.info("::::: size senaraiKodBPM :" + senaraiKodBPM.size());
    }

    public Resolution checkPermohonan() throws WorkflowException {
        fromPage = "statusPermohonan";
        log.info("kod negeri : " + conf.getProperty("kodNegeri"));
        if (bpm != null) {
            System.out.println("search bpm : " + bpm);
            bpmList = new ArrayList<Permohonan>();
          //  bpmList.addAll(eService.searchBpm(bpm.toLowerCase(), conf.getProperty("kodNegeri")));
            bpmList.addAll(service.searchBpm(bpm.toLowerCase(), conf.getProperty("kodNegeri")));
            System.out.println("size search result(bpm) : " + bpmList.size());
            getContext().getRequest().setAttribute("bpm", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/keputusan_carian.jsp");
        }
        if (daerah != null) {
            log.info("search daerah : " + daerah);
            daerahList = new ArrayList<AduanLokasi>();
            daerahList.addAll(eService.searchDaerah(daerah.toLowerCase(), conf.getProperty("kodNegeri")));
            System.out.println("size search result(daerah) : " + daerahList.size());
            getContext().getRequest().setAttribute("daerah", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/keputusan_carian.jsp");
        }
        if (jenisKesalahan != null) {
            log.info("search jenisKesalahan : " + jenisKesalahan);
            jenisKesalahanList = new ArrayList<Permohonan>();
            jenisKesalahanList.addAll(eService.searchJenisKesalahan(jenisKesalahan.toLowerCase(), conf.getProperty("kodNegeri")));
            System.out.println("size search result(jenis kesalahan) : " + jenisKesalahanList.size());
            getContext().getRequest().setAttribute("jenisKesalahan", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/keputusan_carian.jsp");
        }
        if (lokasi != null) {
            log.info("search lokasi : " + lokasi);
            lokasiList = new ArrayList<AduanLokasi>();
            lokasiList.addAll(eService.searchLokasi(lokasi.toLowerCase(), conf.getProperty("kodNegeri")));
            System.out.println("size search result(lokasi) : " + lokasiList.size());
            getContext().getRequest().setAttribute("lokasi", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/keputusan_carian.jsp");
        }
        if (idHakmilik != null) {
            hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            hakmilikUrusanList.addAll(pService.findUrusanByidHY(idHakmilik));
            return new ForwardResolution("/WEB-INF/jsp/common/maklumat_urusan.jsp");
        }
        if ((permohonan == null) && (idHakmilik == null) && (daerah == null)) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan atau Bandar/Pekan/Mukim atau Daerah atau Lokasi atau Jenis Kesalahan");
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/semakanPermohonan.jsp");
        }
        idPermohonan = permohonan.getIdPermohonan();
        if (idPermohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/semakanPermohonan.jsp");
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan == null) {
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/semakanPermohonan.jsp");
        } else {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            for (Task t : l) {
                stage = t.getSystemAttributes().getStage();
                participant = t.getSystemAttributes().getAcquiredBy();
                stageLabel = tm.getCurrentAction(permohonan.getKodUrusan().getIdWorkflow(), t.getSystemAttributes().getStage());
            }

            if (permohonan.getPermohonanSebelum() != null) {
                //1) find mohon nota based on id permohonan sebelum (IP create new id permohonan)
                secondLayerPermohonan = permohonan.getPermohonanSebelum();
                log.info(":: secondLayerPermohonan : " + secondLayerPermohonan.getIdPermohonan());

                listNotaPermohonanSebelum = eService.findListNotaByIdMohonSebelum(permohonan.getPermohonanSebelum().getIdPermohonan());
                log.info("size listNotaPermohonanSebelum : " + listNotaPermohonanSebelum.size());

                //2) find mohon kertas for sebahagian kompaun dakwa (create new permohonan) 3 layer permohonan
                Permohonan permohonanIP = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (permohonanIP != null) {
                    if (permohonanIP.getPermohonanSebelum() != null) {
                        thirdLayerPermohonan = permohonanIP.getPermohonanSebelum();
                        log.info(":: thirdLayerPermohonan : " + thirdLayerPermohonan.getIdPermohonan());

                        listNotaPermohonanHD = eService.findListNotaByIdMohonSebelum(permohonanIP.getPermohonanSebelum().getIdPermohonan());
                    }

                }
            }
        }
        listNota = eService.findListNotaByIdMohonSebelum(permohonan.getIdPermohonan());
        log.info(":::: size list nota : " + listNota.size());

        status = permohonan.getStatus();
        if (status == null) {
            addSimpleMessage("Urusan ini sedang diproses");
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/permohonan_status_pengambilan.jsp");
        } else if ("TS".equals(status)) {
            addSimpleMessage("Menunggu Permohonan/Perserahan sebelum untuk bermula");
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/permohonan_status_pengambilan.jsp");
        } else if ("TA".equals(status)) {
            addSimpleMessage("Tugasan ini belum diambil oleh sesiapa");
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/permohonan_status_pengambilan.jsp");
        } else if ("AK".equals(status)) {
            String msg = "Urusan ini belum selesai (Peringkat " + stageLabel;
            if (StringUtils.isNotBlank(participant)) {
                msg += " - " + participant;
            }
            msg += ")";
            addSimpleMessage(msg);
            hakmilikMohonList = service.findByIdPermohonan1(idPermohonan);
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/permohonan_status_pengambilan.jsp");
        } else if ("GB".equals(status)) { // Gantung
            // TODO: check if task own by SPOC
            addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit "
                    + permohonan.getKodUrusan().getJabatanNama());
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/permohonan_status_pengambilan.jsp");
        } else if ("TK".equals(status)) {//TK - Tidak Aktif - Urusan telah dibatalkan
            addSimpleMessage("Urusan telah dibatalkan");
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/permohonan_status_pengambilan.jsp");
        } else if ("TP".equals(status)) {
            addSimpleMessage("Menunggu tindakan selanjut daripada pemohon untuk meneruskan aliran kerja. Tugasan Berada Di kaunter");
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/permohonan_status_pengambilan.jsp");
        } else if ("SL".equals(status)) {
            addSimpleMessage("Urusan ini telah selesai diproses. Keputusan telah dikeluarkan");
            hakmilikMohonList = service.findByIdPermohonan1(idPermohonan);
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/permohonan_status_pengambilan.jsp");
        } else if ("SS".equals(status)) {
            addSimpleMessage("Urusan ini telah disemak semula");
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/permohonan_status_pengambilan.jsp");
        } else {

            return null;

        }



    }

    public Resolution viewhakmilikDetail() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = pService.findHakmilik(idHakmilik);
        return new JSP("daftar/utiliti/paparan_hakmilik_single.jsp").addParameter("popup", "true");
    }

    public Resolution viewDetailPermohonan() {
        String idMohon = (String) getContext().getRequest().getParameter("idMohon");
        permohonan = permohonanDAO.findById(idMohon);
        listNota = eService.findListNotaByIdMohonSebelum(permohonan.getIdPermohonan());
        log.info(":::: size list nota : " + listNota.size());
        return new JSP("penguatkuasaan/view_permohonan_detail.jsp").addParameter("popup", "true");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HakmilikUrusan> getHakmilikUrusanList() {
        return hakmilikUrusanList;
    }

    public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
        this.hakmilikUrusanList = hakmilikUrusanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan() {
        return senaraiFasaPermohonan;
    }

    public void setSenaraiFasaPermohonan(List<FasaPermohonan> senaraiFasaPermohonan) {
        this.senaraiFasaPermohonan = senaraiFasaPermohonan;
    }

    public List<Permohonan> getBpmList() {
        return bpmList;
    }

    public void setBpmList(List<Permohonan> bpmList) {
        this.bpmList = bpmList;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getJenisKesalahan() {
        return jenisKesalahan;
    }

    public void setJenisKesalahan(String jenisKesalahan) {
        this.jenisKesalahan = jenisKesalahan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public List<AduanLokasi> getDaerahList() {
        return daerahList;
    }

    public void setDaerahList(List<AduanLokasi> daerahList) {
        this.daerahList = daerahList;
    }

    public List<Permohonan> getJenisKesalahanList() {
        return jenisKesalahanList;
    }

    public void setJenisKesalahanList(List<Permohonan> jenisKesalahanList) {
        this.jenisKesalahanList = jenisKesalahanList;
    }

    public List<AduanLokasi> getLokasiList() {
        return lokasiList;
    }

    public void setLokasiList(List<AduanLokasi> lokasiList) {
        this.lokasiList = lokasiList;
    }

    public String getStageLabel() {
        return stageLabel;
    }

    public void setStageLabel(String stageLabel) {
        this.stageLabel = stageLabel;
    }

    public List<PermohonanNota> getListNota() {
        return listNota;
    }

    public void setListNota(List<PermohonanNota> listNota) {
        this.listNota = listNota;
    }

    public Boolean getSemakanDokumen() {
        return semakanDokumen;
    }

    public void setSemakanDokumen(Boolean semakanDokumen) {
        this.semakanDokumen = semakanDokumen;
    }

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public Permohonan getSecondLayerPermohonan() {
        return secondLayerPermohonan;
    }

    public void setSecondLayerPermohonan(Permohonan secondLayerPermohonan) {
        this.secondLayerPermohonan = secondLayerPermohonan;
    }

    public Permohonan getThirdLayerPermohonan() {
        return thirdLayerPermohonan;
    }

    public void setThirdLayerPermohonan(Permohonan thirdLayerPermohonan) {
        this.thirdLayerPermohonan = thirdLayerPermohonan;
    }

    public List<PermohonanNota> getListNotaPermohonanSebelum() {
        return listNotaPermohonanSebelum;
    }

    public void setListNotaPermohonanSebelum(List<PermohonanNota> listNotaPermohonanSebelum) {
        this.listNotaPermohonanSebelum = listNotaPermohonanSebelum;
    }

    public List<PermohonanNota> getListNotaPermohonanHD() {
        return listNotaPermohonanHD;
    }

    public void setListNotaPermohonanHD(List<PermohonanNota> listNotaPermohonanHD) {
        this.listNotaPermohonanHD = listNotaPermohonanHD;
    }

    public List<HakmilikPermohonan> getHakmilikMohonList() {
        return hakmilikMohonList;
    }

    public void setHakmilikMohonList(List<HakmilikPermohonan> hakmilikMohonList) {
        this.hakmilikMohonList = hakmilikMohonList;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

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
import etanah.model.HakmilikUrusan;
import etanah.model.KodStatusPermohonan;
import etanah.model.Permohonan;


import etanah.model.Lelongan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanNota;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.service.daftar.PembetulanService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/strata/status_permohonan")
public class UtilitiStatusPermohonanActionBean extends PermohonanKaunter {

    private Permohonan permohonan;
    private Enkuiri enkuiri;
    private String stage;
    private String status;
    private String namastatus;
    private String participant;
    private String idHakmilik;
    private String idPermohonan;
    private KodStatusPermohonan kodstatus;
    private Hakmilik hakmilik;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<Lelongan> senaraiLelongan;
    private List<Enkuiri> senaraiEnkuiri;
    private List<AduanLokasi> bpmList;
    private List<AduanLokasi> lokasiList;
    private List<Permohonan> jenisKesalahanList;
    private List<Permohonan> senaraiPermohonanSTR = new ArrayList();
    private List<AduanLokasi> daerahList;
    private String daerah;
    private String bpm;
    private String jenisKesalahan;
    private String lokasi;
    private List<FasaPermohonan> senaraiFasaPermohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
    private StrataPtService strataPtService;
    @Inject
    EnforceService eService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private TabManager tm;
    private String stageLabel;
    private List<PermohonanNota> listNota;
    private boolean flag = false;
    private static final Logger log = Logger.getLogger(UtilitiStatusPermohonanActionBean.class);

    @DefaultHandler
    public Resolution insertIdPermohonan() {
        senaraiPermohonanSTR.clear();
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
    }

    public Resolution checkPermohonan() throws WorkflowException {
//        String idMohon = (String) getContext().getRequest().getParameter("idMohon");
         if ((permohonan == null) && (idHakmilik == null) && (daerah == null)) {
            addSimpleError("Sila masukkan ID Permohonan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        }
        
         idPermohonan = permohonan.getIdPermohonan();
        if (idPermohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        }
        
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan == null) {
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else {
            setFlag(true);
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            for (Task t : l) {
                stage = t.getSystemAttributes().getStage();
                participant = t.getSystemAttributes().getAcquiredBy();
                stageLabel = tm.getCurrentAction(permohonan.getKodUrusan().getIdWorkflow(), t.getSystemAttributes().getStage());
            }
        }
        
        String idMohon = (String) getContext().getRequest().getParameter("permohonan.idPermohonan");
        log.info("::::: idMohon :" + idMohon);
        permohonan = permohonanDAO.findById(idMohon);
        status = permohonan.getStatus();
        log.info("::::: kod status :" + status);
        
        kodstatus = strataPtService.getkodKeputusan(status);
        namastatus = kodstatus.getNama();
        log.info("::::: nama status :" + kodstatus.getNama());
        log.info("::::: nama status :" + namastatus);
        
        log.info("kod negeri : " + conf.getProperty("kodNegeri"));
        if (bpm != null) {
            System.out.println("search bpm : " + bpm);
            bpmList = new ArrayList<AduanLokasi>();
            bpmList.addAll(eService.searchBpm(bpm.toLowerCase(), conf.getProperty("kodNegeri")));
            System.out.println("size search result(bpm) : " + bpmList.size());
            getContext().getRequest().setAttribute("bpm", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/keputusan_carian.jsp");
        }

        if (idHakmilik != null) {
            hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            hakmilikUrusanList.addAll(pService.findUrusanByidHY(idHakmilik));
            return new ForwardResolution("/WEB-INF/jsp/common/maklumat_urusan.jsp");
        }
//        if ((permohonan == null) && (idHakmilik == null) && (daerah == null)) {
//            addSimpleError("Sila masukkan ID Permohonan");
//            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
//        }
//        idPermohonan = permohonan.getIdPermohonan();
//        if (idPermohonan == null) {
//            addSimpleError("Sila masukkan ID Permohonan");
//            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
//        }

//        permohonan = permohonanDAO.findById(idPermohonan);
//        if (permohonan == null) {
//            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
//            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
//        } else {
//            setFlag(true);
//            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
//            for (Task t : l) {
//                stage = t.getSystemAttributes().getStage();
//                participant = t.getSystemAttributes().getAcquiredBy();
//                stageLabel = tm.getCurrentAction(permohonan.getKodUrusan().getIdWorkflow(), t.getSystemAttributes().getStage());
//            }
//        }
        listNota = eService.findListNotaByIdMohonSebelum(permohonan.getIdPermohonan());
        log.info(":::: size list nota : " + listNota.size());

        status = permohonan.getStatus();
        if (status == null) {
            addSimpleMessage("Urusan ini sedang diproses");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else if ("TS".equals(status)) {
            addSimpleMessage("Menunggu Permohonan/Perserahan sebelum untuk bermula");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else if ("TA".equals(status)) {
            addSimpleMessage("Tugasan ini belum diambil oleh sesiapa");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else if ("AK".equals(status)) {
            String msg = "Urusan ini aktif. Urusan ini sedang diproses.";
//                    + "(Peringkat " + stageLabel;
//            if (StringUtils.isNotBlank(participant)) {
//                msg += " - " + participant;
//            }
//            msg += ")";
            addSimpleMessage(msg);
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else if ("GB".equals(status)) { // Gantung
            // TODO: check if task own by SPOC
            addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit "
                    + permohonan.getKodUrusan().getJabatanNama());
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else if ("TK".equals(status)) {//TK - Tidak Aktif - Urusan telah dibatalkan
            addSimpleMessage("Urusan tidak aktif. Urusan telah dibatalkan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else if ("TP".equals(status)) {
            addSimpleMessage("Menunggu tindakan selanjut daripada pemohon untuk meneruskan aliran kerja. Tugasan Berada Di kaunter");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else if ("SL".equals(status)) {
            addSimpleMessage("Urusan ini telah selesai diproses. Keputusan telah dikeluarkan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else if ("SS".equals(status)) {
            addSimpleMessage("Urusan ini telah disemak semula");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } 
         else if ("BP".equals(status)) {
            addSimpleMessage("Urusan ini telah dibatalkan permohonan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        } else {
            addSimpleMessage("Urusan ini belum selesai");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
        }
    }
    public Resolution checkPermohonanByDate() throws WorkflowException {
        String trh_mula = (String) getContext().getRequest().getParameter("trh_mula");
        String trh_akhir = (String) getContext().getRequest().getParameter("trh_akhir");

        senaraiPermohonanSTR = strataPtService.findPermohonanSTR(trh_mula,trh_akhir);
        log.info("trh_mula"+trh_mula + "trh_akhir"+trh_akhir);
        log.info("senaraiPermohonanSTR"+senaraiPermohonanSTR.size());

        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
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
        return new JSP("strata/utiliti/view_permohonan_detail.jsp").addParameter("popup", "true");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/permohonan_status_strata.jsp");
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

    public List<AduanLokasi> getBpmList() {
        return bpmList;
    }

    public void setBpmList(List<AduanLokasi> bpmList) {
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getNamastatus() {
        return namastatus;
    }

    public void setNamastatus(String namastatus) {
        this.namastatus = namastatus;
    }

    public List<Permohonan> getSenaraiPermohonanSTR() {
        return senaraiPermohonanSTR;
    }

    public void setSenaraiPermohonanSTR(List<Permohonan> senaraiPermohonanSTR) {
        this.senaraiPermohonanSTR = senaraiPermohonanSTR;
    }
    
}

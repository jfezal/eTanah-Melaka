package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.ConsentPtdService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PihakService;
import etanah.service.common.TaskDebugService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.IdentityType;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/semak_status")
public class SemakStatusActionBean extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    private TaskDebugService taskDebugService;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pihak pihakPemohon;
    private Pihak pihakPenerima;
    private Pihak pihakSearch;
    private String stage;
    private String tindakan;
    private String taskId;
    private String taskNumber;
    private String participant;
    private String acquiredBy;
    private String assignGroup;
    private String taskState;
    private String namaPemohon;
    private String namaPenerima;
    private String tarikhMula;
    private String tarikhTamat;
    etanahActionBeanContext ctx;
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    List<IdentityType> groupList = new ArrayList<IdentityType>();
    List<Permohonan> permohonanList = new ArrayList<Permohonan>();
    List<Pihak> pihakList = new ArrayList<Pihak>();
    List<Pemohon> pemohonList = new ArrayList<Pemohon>();
    List<PermohonanPihak> permohonanPihakList = new ArrayList<PermohonanPihak>();
    List<PermohonanPihak> permohonanPihakTerlibatList = new ArrayList<PermohonanPihak>();
    List<FasaPermohonan> fasaPermohonanList = new ArrayList<FasaPermohonan>();
    private static final Logger LOG = Logger.getLogger(SemakStatusActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/consent/semak_status.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public Resolution search() throws ParseException {

        if (permohonan == null && pihakPemohon == null && pihakPenerima == null && hakmilik == null && namaPemohon == null && namaPenerima == null && tarikhMula == null && tarikhTamat == null) {
            addSimpleError("Sila Masukkan Nombor Pengenalan, ID Permohonan, ID Hakmilik atau Nama Pemohon atau Nama Penerima atau Tarikh.");
        } else {

            if (pihakPemohon != null) {

                if (pihakPemohon.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihakPemohon.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                } else if (pihakPemohon.getJenisPengenalan() != null && pihakPemohon.getNoPengenalan() != null) {
                    LOG.info("---CARIAN BY JENIS PENGENALAN PEMOHON " + pihakPemohon.getJenisPengenalan().getNama() + " DAN NOMBOR PENGENALAN PEMOHON " + pihakPemohon.getNoPengenalan());

                    pihakList = consentService.findPihak(null, pihakPemohon.getJenisPengenalan().getKod(), pihakPemohon.getNoPengenalan());

                    if (pihakList.size() > 0) {
                        LOG.info("---LIST PIHAK  " + pihakPemohon.getNoPengenalan() + " SIZE : " + pihakList.size());
                        for (Pihak pihak : pihakList) {
                            LOG.info("---ID PIHAK : " + pihak.getIdPihak());

                            pemohonList = pemohonService.findPemohonByIdPihakAndJabatan(pihak, "22");
                            List<Pemohon> pemohonListChecked = new ArrayList<Pemohon>();

                            for (Pemohon pemohon : pemohonList) {

                                if (pemohonListChecked.isEmpty()) {
                                    pemohonListChecked.add(pemohon);
                                } else {
                                    boolean found = false;
                                    for (Pemohon pemohonChecked : pemohonListChecked) {
                                        if (pemohon.getPermohonan().getIdPermohonan().equals(pemohonChecked.getPermohonan().getIdPermohonan())) {
                                            found = true;
                                        }
                                    }
                                    if (!found) {
                                        pemohonListChecked.add(pemohon);
                                    }
                                }
                            }

                            if (!pemohonListChecked.isEmpty()) {
                                for (Pemohon pemohon : pemohonListChecked) {
                                    permohonanList.add(pemohon.getPermohonan());
                                }
                            }
                        }
                    }
                }
            } else if (pihakPenerima != null) {

                if (pihakPenerima.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihakPenerima.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                } else if (pihakPenerima.getJenisPengenalan() != null && pihakPenerima.getNoPengenalan() != null) {
                    LOG.info("---CARIAN BY JENIS PENGENALAN PENERIMA " + pihakPenerima.getJenisPengenalan().getNama() + " DAN NOMBOR PENGENALAN PENERIMA " + pihakPenerima.getNoPengenalan());
                    pihakList = consentService.findPihak(null, pihakPenerima.getJenisPengenalan().getKod(), pihakPenerima.getNoPengenalan());

                    if (pihakList.size() > 0) {

                        LOG.info("---LIST PIHAK " + pihakPenerima.getNoPengenalan() + " SIZE : " + pihakList.size());
                        for (Pihak pihak : pihakList) {
                            LOG.info("---ID PIHAK : " + pihak.getIdPihak());
                            permohonanPihakList = permohonanPihakService.findPermohonanPihakByIdPihakNotTwoKodAndJabatan(pihak, "WAR", "TER", "22");
                            List<PermohonanPihak> permohonanPihakListChecked = new ArrayList<PermohonanPihak>();

                            for (PermohonanPihak perPihak : permohonanPihakList) {

                                if (permohonanPihakListChecked.isEmpty()) {
                                    permohonanPihakListChecked.add(perPihak);
                                } else {
                                    boolean found = false;
                                    for (PermohonanPihak perPihakChecked : permohonanPihakListChecked) {
                                        if (perPihak.getPermohonan().getIdPermohonan().equals(perPihakChecked.getPermohonan().getIdPermohonan())) {
                                            found = true;
                                        }
                                    }
                                    if (!found) {
                                        permohonanPihakListChecked.add(perPihak);
                                    }
                                }
                            }

                            if (!permohonanPihakListChecked.isEmpty()) {
                                for (PermohonanPihak permohonanPihak : permohonanPihakListChecked) {
                                    permohonanList.add(permohonanPihak.getPermohonan());
                                }
                            }
                        }
                    }
                }
            } else if (permohonan != null) {
                LOG.info("---CARIAN BY ID PERMOHONAN " + permohonan.getIdPermohonan());
                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                if (permohonan != null) {
                    permohonanList.add(permohonan);
                }
            } else if (hakmilik != null) {
                LOG.info("---CARIAN BY ID HAKMILIK " + hakmilik.getIdHakmilik());
                List<HakmilikPermohonan> hakmilikMohonlist = new ArrayList<HakmilikPermohonan>();
                hakmilikMohonlist = hakmilikPermohonanService.findByIdHakmilikAndJabatan(hakmilik.getIdHakmilik(), "22");

                if (!hakmilikMohonlist.isEmpty()) {
                    for (HakmilikPermohonan hakmilikPermohonan : hakmilikMohonlist) {
                        permohonanList.add(hakmilikPermohonan.getPermohonan());
                    }
                }
            } else if (namaPemohon != null && namaPenerima == null) {

                LOG.info("---CARIAN BY NAMA PEMOHON " + namaPemohon);

                pihakList = consentService.findPihak(namaPemohon, null, null);

                if (pihakList.size() > 0) {
                    LOG.info("---LIST PIHAK " + namaPemohon + " SIZE : " + pihakList.size());
                    for (Pihak pihak : pihakList) {
                        LOG.info("---ID PIHAK : " + pihak.getIdPihak());

                        pemohonList = pemohonService.findPemohonByIdPihakAndJabatan(pihak, "22");
                        List<Pemohon> pemohonListChecked = new ArrayList<Pemohon>();

                        for (Pemohon pemohon : pemohonList) {

                            if (pemohonListChecked.isEmpty()) {
                                pemohonListChecked.add(pemohon);
                            } else {
                                boolean found = false;
                                for (Pemohon pemohonChecked : pemohonListChecked) {
                                    if (pemohon.getPermohonan().getIdPermohonan().equals(pemohonChecked.getPermohonan().getIdPermohonan())) {
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    pemohonListChecked.add(pemohon);
                                }
                            }
                        }

                        if (!pemohonListChecked.isEmpty()) {
                            for (Pemohon pemohon : pemohonListChecked) {
                                permohonanList.add(pemohon.getPermohonan());
                            }
                        }
                    }
                }
            } else if (namaPemohon == null && namaPenerima != null) {
                LOG.info("---CARIAN BY NAMA PENERIMA " + namaPenerima);
                pihakList = consentService.findPihak(namaPenerima, null, null);

                if (pihakList.size() > 0) {
                    LOG.info("---LIST PIHAK " + namaPenerima + " SIZE : " + pihakList.size());
                    for (Pihak pihak : pihakList) {
                        LOG.info("---ID PIHAK : " + pihak.getIdPihak());
                        permohonanPihakList = permohonanPihakService.findPermohonanPihakByIdPihakNotTwoKodAndJabatan(pihak, "WAR", "TER", "22");
                        List<PermohonanPihak> permohonanPihakListChecked = new ArrayList<PermohonanPihak>();

                        for (PermohonanPihak perPihak : permohonanPihakList) {

                            if (permohonanPihakListChecked.isEmpty()) {
                                permohonanPihakListChecked.add(perPihak);
                            } else {
                                boolean found = false;
                                for (PermohonanPihak perPihakChecked : permohonanPihakListChecked) {
                                    if (perPihak.getPermohonan().getIdPermohonan().equals(perPihakChecked.getPermohonan().getIdPermohonan())) {
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    permohonanPihakListChecked.add(perPihak);
                                }
                            }
                        }

                        if (!permohonanPihakListChecked.isEmpty()) {
                            for (PermohonanPihak permohonanPihak : permohonanPihakListChecked) {
                                permohonanList.add(permohonanPihak.getPermohonan());
                            }
                        }
                    }
                }
            } else if (namaPemohon != null && namaPenerima != null) {

                LOG.info("---CARIAN BY NAMA PEMOHON " + namaPemohon + " DAN NAMA PENERIMA " + namaPenerima);

                List<Permohonan> mohonPemohonList = new ArrayList<Permohonan>();
                List<Permohonan> mohonPenerimaList = new ArrayList<Permohonan>();

                //FIND NAMA PEMOHON
                pihakList = consentService.findPihak(namaPemohon, null, null);

                if (pihakList.size() > 0) {
                    LOG.info("---LIST PIHAK " + namaPemohon + " SIZE : " + pihakList.size());
                    for (Pihak pihak : pihakList) {
                        LOG.info("---ID PIHAK : " + pihak.getIdPihak());

                        pemohonList = pemohonService.findPemohonByIdPihakAndJabatan(pihak, "22");
                        List<Pemohon> pemohonListChecked = new ArrayList<Pemohon>();

                        for (Pemohon pemohon : pemohonList) {

                            if (pemohonListChecked.isEmpty()) {
                                pemohonListChecked.add(pemohon);
                            } else {
                                boolean found = false;
                                for (Pemohon pemohonChecked : pemohonListChecked) {
                                    if (pemohon.getPermohonan().getIdPermohonan().equals(pemohonChecked.getPermohonan().getIdPermohonan())) {
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    pemohonListChecked.add(pemohon);
                                }
                            }
                        }

                        if (!pemohonListChecked.isEmpty()) {
                            for (Pemohon pemohon : pemohonListChecked) {
                                mohonPemohonList.add(pemohon.getPermohonan());
                            }
                        }
                    }
                }

                //FIND NAMA PENERIMA
                pihakList = new ArrayList<Pihak>();
                pihakList = consentService.findPihak(namaPenerima, null, null);

                if (pihakList.size() > 0) {
                    LOG.info("---LIST PIHAK " + namaPenerima + " SIZE : " + pihakList.size());
                    for (Pihak pihak : pihakList) {
                        LOG.info("---ID PIHAK : " + pihak.getIdPihak());
                        permohonanPihakList = permohonanPihakService.findPermohonanPihakByIdPihakNotTwoKodAndJabatan(pihak, "WAR", "TER", "22");
                        List<PermohonanPihak> permohonanPihakListChecked = new ArrayList<PermohonanPihak>();
                        LOG.info("---Permohonan Pihak Size: " + permohonanPihakList.size());
                        for (PermohonanPihak perPihak : permohonanPihakList) {

                            if (permohonanPihakListChecked.isEmpty()) {
                                permohonanPihakListChecked.add(perPihak);
                            } else {
                                boolean found = false;
                                for (PermohonanPihak perPihakChecked : permohonanPihakListChecked) {
                                    if (perPihak.getPermohonan().getIdPermohonan().equals(perPihakChecked.getPermohonan().getIdPermohonan())) {
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    permohonanPihakListChecked.add(perPihak);
                                }
                            }
                        }

                        if (!permohonanPihakListChecked.isEmpty()) {
                            for (PermohonanPihak permohonanPihak : permohonanPihakListChecked) {
                                mohonPenerimaList.add(permohonanPihak.getPermohonan());
                            }
                        }
                    }
                }

                //ADD PENERIMA AND PEMOHON TO LIST PERMOHONAN
                for (Permohonan mohonPemohon : mohonPemohonList) {
                    for (Permohonan mohonPenerima : mohonPenerimaList) {

                        if (mohonPemohon.getIdPermohonan().equals(mohonPenerima.getIdPermohonan())) {
                            permohonanList.add(mohonPemohon);
                            break;
                        }
                    }
                }
            } else if (tarikhMula != null && tarikhTamat != null) {
                LOG.info("---CARIAN BY TARIKH MULA : " + tarikhMula + " TARIKH TAMAT : " + tarikhTamat);
                ctx = (etanahActionBeanContext) getContext();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sd.parse(tarikhTamat));
                calendar.add(Calendar.DATE, 1);
                Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                permohonanList = consentService.findSenaraiMohonByTarikh(sd.parse(tarikhMula), calendar.getTime(), "22", pengguna.getKodCawangan().getKod());
            }

            if (permohonanList.isEmpty()) {
                addSimpleError("Carian maklumat permohonan tidak dijumpai");
            } else {
                addSimpleMessage("Carian maklumat permohonan berjaya.");
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/consent/semak_status.jsp");
    }

    public Resolution viewStatus() throws WorkflowException {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        Map m = taskDebugService.traceTask(idPermohonan);
        stage = (String) m.get("stage");
        tindakan = (String) m.get("tindakan");
        taskId = (String) m.get("taskID");
        taskNumber = "" + m.get("taskNumber");
        participant = (String) m.get("participant");
        acquiredBy = (String) m.get("acquiredBy");
        groupList = (List<IdentityType>) m.get("list_group");

        if (groupList != null) {
            assignGroup = groupList.get(0).getId();
        }

        fasaPermohonanList = fasaPermohonanService.findStatus(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        List<PermohonanPihak> penerimaList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(idPermohonan, "WAR", "TER");
        
        LOG.info("---Permohonan Pihak Size : " + penerimaList.size());

        for (PermohonanPihak perPihak : penerimaList) {

            boolean flag = true;
            if (permohonanPihakList.isEmpty()) {
                permohonanPihakList.add(perPihak);
            } else {
                for (PermohonanPihak perPihak2 : permohonanPihakList) {

                    if (perPihak.getPihak().getIdPihak() == perPihak2.getPihak().getIdPihak()) {

                        if (perPihak.getJenis().getKod().equals(perPihak2.getJenis().getKod())) {
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    permohonanPihakList.add(perPihak);
                }
            }
        }

        pemohonList = permohonan.getSenaraiPemohon();
        permohonanPihakTerlibatList = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "TER");
        return new ForwardResolution("/WEB-INF/jsp/consent/semak_status_popup.jsp").addParameter("popup", "true");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        hakmilik = new Hakmilik();
        pihakPemohon = new Pihak();
        pihakPenerima = new Pihak();
        namaPemohon = null;
        namaPenerima = null;
        tarikhMula = null;
        tarikhTamat = null;
        return new ForwardResolution("/WEB-INF/jsp/consent/semak_status.jsp");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihakPemohon() {
        return pihakPemohon;
    }

    public void setPihakPemohon(Pihak pihak) {
        this.pihakPemohon = pihak;
    }

    public List<Permohonan> getPermohonanList() {
        return permohonanList;
    }

    public void setPermohonanList(List<Permohonan> permohonanList) {
        this.permohonanList = permohonanList;
    }

    public List<FasaPermohonan> getFasaPermohonanList() {
        return fasaPermohonanList;
    }

    public void setFasaPermohonanList(List<FasaPermohonan> fasaPermohonanList) {
        this.fasaPermohonanList = fasaPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pihak getPihakSearch() {
        return pihakSearch;
    }

    public void setPihakSearch(Pihak pihakSearch) {
        this.pihakSearch = pihakSearch;
    }

    public String getAcquiredBy() {
        return acquiredBy;
    }

    public void setAcquiredBy(String acquiredBy) {
        this.acquiredBy = acquiredBy;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public Pihak getPihakPenerima() {
        return pihakPenerima;
    }

    public void setPihakPenerima(Pihak pihakPenerima) {
        this.pihakPenerima = pihakPenerima;
    }

    public String getAssignGroup() {
        return assignGroup;
    }

    public void setAssignGroup(String assignGroup) {
        this.assignGroup = assignGroup;
    }

    public List<IdentityType> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<IdentityType> groupList) {
        this.groupList = groupList;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public List<PermohonanPihak> getPermohonanPihakTerlibatList() {
        return permohonanPihakTerlibatList;
    }

    public void setPermohonanPihakTerlibatList(List<PermohonanPihak> permohonanPihakTerlibatList) {
        this.permohonanPihakTerlibatList = permohonanPihakTerlibatList;
    }
}

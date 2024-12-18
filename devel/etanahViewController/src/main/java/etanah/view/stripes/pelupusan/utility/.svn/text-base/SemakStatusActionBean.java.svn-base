package etanah.view.stripes.pelupusan.utility;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.IdentityType;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/pelupusan/semak_status/{kodJbtn}")
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
    @Inject
    PelupusanService plpservice;
    @Inject
    PembangunanService pbservice;
    private String kodJbtn;
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
    private String kodUrusan;
    public String fromDate;
    public String untilDate;
    private String strKategoriPemohon;
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    List<IdentityType> groupList = new ArrayList<IdentityType>();
    List<Permohonan> permohonanList = new ArrayList<Permohonan>();
    List<Pihak> pihakList = new ArrayList<Pihak>();
    List<Pemohon> pemohonList = new ArrayList<Pemohon>();
    List<PermohonanPihak> permohonanPihakList = new ArrayList<PermohonanPihak>();
    List<FasaPermohonan> fasaPermohonanList = new ArrayList<FasaPermohonan>();
    private static final Logger LOG = Logger.getLogger(SemakStatusActionBean.class);
    private Pengguna pengguna;
    private FasaPermohonan fasaPermohonanExist;
    private FasaPermohonan lastStageOfFasaPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_status.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        kodJbtn = getContext().getRequest().getParameter("kodJbtn");

    }

    public Resolution search() throws ParseException {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodJbtn = getContext().getRequest().getParameter("kodJbtn");
        if (StringUtils.isBlank(kodJbtn)) {
            addSimpleError("Sila pastikan kod Jabatan telah dimasukkan pada URI Utiliti ini");
        } else {
            if (permohonan == null && pihakPemohon == null && pihakPenerima == null && hakmilik == null && namaPemohon == null && namaPenerima == null && untilDate == null && fromDate == null && kodUrusan == null) {
                addSimpleError("Sila Masukkan Nombor Pengenalan, ID Permohonan, ID Hakmilik,Kod Urusan, Nama Pemohon atau Tarikh Masuk.");
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

//                                pemohonList = pemohonService.findPemohonByIdPihakAndJabatan(pihak, "2");
                                pemohonList = pemohonService.findPemohonByIdPihakAndJabatan(pihak, kodJbtn);
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
                } else if (permohonan != null) {
                    LOG.info("---CARIAN BY ID PERMOHONAN " + permohonan.getIdPermohonan());
                    permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                    if (permohonan != null) {
                        permohonanList.add(permohonan);
                    }
                } else if (hakmilik != null) {
                    LOG.info("---CARIAN BY ID HAKMILIK " + hakmilik.getIdHakmilik());
                    List<HakmilikPermohonan> hakmilikMohonlist = new ArrayList<HakmilikPermohonan>();
                    hakmilikMohonlist = hakmilikPermohonanService.findByIdHakmilikAndJabatan(hakmilik.getIdHakmilik(), kodJbtn);

                    if (!hakmilikMohonlist.isEmpty()) {
                        for (HakmilikPermohonan hakmilikPermohonan : hakmilikMohonlist) {
                            permohonanList.add(hakmilikPermohonan.getPermohonan());
                        }
                    }
                } else if (namaPemohon != null) {

                    LOG.info("---CARIAN BY NAMA PEMOHON " + namaPemohon);

                    pihakList = consentService.findPihak(namaPemohon, null, null);

                    if (pihakList.size() > 0) {
                        LOG.info("---LIST PIHAK " + namaPemohon + " SIZE : " + pihakList.size());
                        for (Pihak pihak : pihakList) {
                            LOG.info("---ID PIHAK : " + pihak.getIdPihak());

                            pemohonList = pemohonService.findPemohonByIdPihakAndJabatan(pihak, kodJbtn);
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
                } else if (untilDate != null && fromDate != null) {
                    //TODO :search from date and u0ntil date
                    String dateDari = fromDate + " 00:00:00";
                    sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String dateHingga = untilDate + " 23:59:59";
                    sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    permohonanList = plpservice.findPermohonanByToFromDateByDaerah(sd.parse(dateHingga), sd.parse(dateDari), pengguna.getKodCawangan().getKod());

                } else if (fromDate != null) {
                    String dateDari = fromDate + " 00:00:00";
                    sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    permohonanList = plpservice.findPermohonanByToFromDateByDaerah(null, sd.parse(dateDari), pengguna.getKodCawangan().getKod());
                    //permohonanList = plpservice.findPermohonanByToFromDate(null, sd.parse(dateDari), pengguna.getKodCawangan().getKod());

                } else if (kodUrusan != null && StringUtils.isNotBlank(kodUrusan)) {
                    LOG.info("---CARIAN BY KOD URUSAN " + kodUrusan + "Pengguna Kod Caw ==" + pengguna.getKodCawangan().getKod());

                    permohonanList = plpservice.findPermohonanByKodUrusanByDaerah(kodUrusan, pengguna.getKodCawangan().getKod());
                    //permohonanList = plpservice.findPermohonanByKodUrusan(kodUrusan, pengguna.getKodCawangan().getKod());
                }
//            else if (untilDate != null) {
//
//                String dateHingga = untilDate + " 23:59:59";
//
//                sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//
//                Date d = sd.parse(dateHingga);
//            }

                if (permohonanList.isEmpty()) {
                    addSimpleError("Carian maklumat permohonan tidak dijumpai");
                } else {
                    addSimpleMessage("Carian maklumat permohonan berjaya.");
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_status.jsp");
    }

//    public Resolution search() {
//
//        if (permohonan == null && pihakPemohon == null && pihakPenerima == null && hakmilik == null && namaPemohon == null && namaPenerima == null) {
//            addSimpleError("Sila Masukkan Nombor Pengenalan, ID Permohonan, ID Hakmilik atau Nama Pemohon atau Nama Penerima.");
//        } else {
//
//            if (pihakPemohon != null) {
//
//                if (pihakPemohon.getJenisPengenalan() == null) {
//                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
//                } else if (pihakPemohon.getNoPengenalan() == null) {
//                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
//                } else if (pihakPemohon.getJenisPengenalan() != null && pihakPemohon.getNoPengenalan() != null) {
//
//                    pihakSearch = new Pihak();
//                    pihakSearch = pihakService.findPihak(pihakPemohon.getJenisPengenalan().getKod(), pihakPemohon.getNoPengenalan());
//                    if (pihakSearch != null) {
//
//                        pemohonList = pemohonService.findPemohonByIdPihak(pihakSearch);
//                        List<Pemohon> pemohonListChecked = new ArrayList<Pemohon>();
//
//                        for (Pemohon pemohon : pemohonList) {
//
//                            if (pemohonListChecked.isEmpty()) {
//                                pemohonListChecked.add(pemohon);
//                            } else {
//                                boolean found = false;
//                                for (Pemohon pemohonChecked : pemohonListChecked) {
//                                    if (pemohon.getPermohonan().getIdPermohonan().equals(pemohonChecked.getPermohonan().getIdPermohonan())) {
//                                        found = true;
//                                    }
//                                }
//                                if (!found) {
//                                    pemohonListChecked.add(pemohon);
//                                }
//                            }
//                        }
//
//                        if (!pemohonListChecked.isEmpty()) {
//                            for (Pemohon pemohon : pemohonListChecked) {
//                                permohonanList.add(pemohon.getPermohonan());
//                            }
//                        }
//                    }
//                }
//            } else if (pihakPenerima != null) {
//
//                if (pihakPenerima.getJenisPengenalan() == null) {
//                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
//                } else if (pihakPenerima.getNoPengenalan() == null) {
//                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
//                } else if (pihakPenerima.getJenisPengenalan() != null && pihakPenerima.getNoPengenalan() != null) {
//
//                    pihakSearch = new Pihak();
//                    pihakSearch = pihakService.findPihak(pihakPenerima.getJenisPengenalan().getKod(), pihakPenerima.getNoPengenalan());
//                    if (pihakSearch != null) {
//                        permohonanPihakList = permohonanPihakService.findPermohonanPihakByIdPihakNotTwoKod(pihakSearch, "WAR", "TER");
//                        List<PermohonanPihak> permohonanPihakListChecked = new ArrayList<PermohonanPihak>();
//
//                        for (PermohonanPihak perPihak : permohonanPihakList) {
//
//                            if (permohonanPihakListChecked.isEmpty()) {
//                                permohonanPihakListChecked.add(perPihak);
//                            } else {
//                                boolean found = false;
//                                for (PermohonanPihak perPihakChecked : permohonanPihakListChecked) {
//                                    if (perPihak.getPermohonan().getIdPermohonan().equals(perPihakChecked.getPermohonan().getIdPermohonan())) {
//                                        found = true;
//                                    }
//                                }
//                                if (!found) {
//                                    permohonanPihakListChecked.add(perPihak);
//                                }
//                            }
//                        }
//
//                        if (!permohonanPihakListChecked.isEmpty()) {
//                            for (PermohonanPihak permohonanPihak : permohonanPihakListChecked) {
//                                permohonanList.add(permohonanPihak.getPermohonan());
//                            }
//                        }
//                    }
//                }
//            } else if (permohonan != null) {
//                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
//                if (permohonan != null) {
//                    permohonanList.add(permohonan);
//                }
//            } else if (hakmilik != null) {
//                List<HakmilikPermohonan> hakmilikMohonlist = new ArrayList<HakmilikPermohonan>();
//                hakmilikMohonlist = hakmilikPermohonanService.findByIdHakmilik(hakmilik.getIdHakmilik());
//
//                if (!hakmilikMohonlist.isEmpty()) {
//                    for (HakmilikPermohonan hakmilikPermohonan : hakmilikMohonlist) {
//                        permohonanList.add(hakmilikPermohonan.getPermohonan());
//                    }
//                }
//            } else if (namaPemohon != null) {
//                pihakSearch = new Pihak();
//                pihakSearch = pihakService.findPihakByNameEqual(namaPemohon);
//                if (pihakSearch != null) {
//
//                    pemohonList = pemohonService.findPemohonByIdPihak(pihakSearch);
//                    List<Pemohon> pemohonListChecked = new ArrayList<Pemohon>();
//
//                    for (Pemohon pemohon : pemohonList) {
//
//                        if (pemohonListChecked.isEmpty()) {
//                            pemohonListChecked.add(pemohon);
//                        } else {
//                            boolean found = false;
//                            for (Pemohon pemohonChecked : pemohonListChecked) {
//                                if (pemohon.getPermohonan().getIdPermohonan().equals(pemohonChecked.getPermohonan().getIdPermohonan())) {
//                                    found = true;
//                                }
//                            }
//                            if (!found) {
//                                pemohonListChecked.add(pemohon);
//                            }
//                        }
//                    }
//
//                    if (!pemohonListChecked.isEmpty()) {
//                        for (Pemohon pemohon : pemohonListChecked) {
//                            permohonanList.add(pemohon.getPermohonan());
//                        }
//                    }
//                }
//            } else if (namaPenerima != null) {
//                pihakSearch = new Pihak();
//                pihakSearch = pihakService.findPihakByNameEqual(namaPenerima);
//                if (pihakSearch != null) {
//
//                    permohonanPihakList = permohonanPihakService.findPermohonanPihakByIdPihakNotTwoKod(pihakSearch, "WAR", "TER");
//                    List<PermohonanPihak> permohonanPihakListChecked = new ArrayList<PermohonanPihak>();
//
//                    for (PermohonanPihak perPihak : permohonanPihakList) {
//
//                        if (permohonanPihakListChecked.isEmpty()) {
//                            permohonanPihakListChecked.add(perPihak);
//                        } else {
//                            boolean found = false;
//                            for (PermohonanPihak perPihakChecked : permohonanPihakListChecked) {
//                                if (perPihak.getPermohonan().getIdPermohonan().equals(perPihakChecked.getPermohonan().getIdPermohonan())) {
//                                    found = true;
//                                }
//                            }
//                            if (!found) {
//                                permohonanPihakListChecked.add(perPihak);
//                            }
//                        }
//                    }
//
//                    if (!permohonanPihakListChecked.isEmpty()) {
//                        for (PermohonanPihak permohonanPihak : permohonanPihakListChecked) {
//                            permohonanList.add(permohonanPihak.getPermohonan());
//                        }
//                    }
//                }
//            }
//
//            if (permohonanList.isEmpty()) {
//                addSimpleError("Carian maklumat permohonan tidak dijumpai");
//            } else {
//                addSimpleMessage("Carian maklumat permohonan berjaya.");
//            }
//        }
//        return new ForwardResolution("/WEB-INF/jsp/consent/semak_status.jsp");
//    }
    public Resolution viewStatus() throws WorkflowException {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        Map m = taskDebugService.traceTask(idPermohonan);
        stage = (String) m.get("stage");
        LOG.info("stage : " + stage);
        tindakan = (String) m.get("tindakan");
        taskId = (String) m.get("taskID");
        taskNumber = "" + m.get("taskNumber");
        participant = (String) m.get("participant");
        acquiredBy = (String) m.get("acquiredBy");
        groupList = (List<IdentityType>) m.get("list_group");

        if (groupList != null) {
            assignGroup = groupList.get(0).getId();
        }



//        fasaPermohonanList = fasaPermohonanService.findStatus(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        List<PermohonanPihak> penerimaList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(idPermohonan, "WAR", "TER");
        permohonanPihakList = penerimaList;

        fasaPermohonanExist = pbservice.findFasaPermohonanExist(idPermohonan);
        if (fasaPermohonanExist != null) {
            fasaPermohonanList = fasaPermohonanService.findStatusSelesai1(idPermohonan);
            lastStageOfFasaPermohonan = fasaPermohonanService.findLastStage(idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
            pemohonList = permohonan.getSenaraiPemohon();
            strKategoriPemohon = cariKategoriPemohonByIdPermohonan(permohonan);
        } else {
            addSimpleMessage("Tugasan Masih Belum Dicapai Oleh Pengguna.");
        }
//        for (PermohonanPihak perPihak : penerimaList) {
//            LOG.info("---Permohonan Pihak Size : " + penerimaList.size());
//            boolean flag = true;
//            if (permohonanPihakList.isEmpty()) {
//                permohonanPihakList.add(perPihak);
//            } else {
//                for (PermohonanPihak perPihak2 : permohonanPihakList) {
//                    
//                    if (perPihak.getJenis().getKod().equals(perPihak2.getJenis().getKod())) {
//                        flag = false;
//                    } else if (perPihak.getPihak().getIdPihak() == perPihak2.getPihak().getIdPihak()) {
//                        flag = false;
//                    } 
////                    else if (perPihak.getJenis().getKod().equals(perPihak2.getJenis().getKod())) {
////                        flag = false;
////                    }
//                }
//                if (flag) {
//                    permohonanPihakList.add(perPihak);
//                }
//            }
//        }

        pemohonList = permohonan.getSenaraiPemohon();
        strKategoriPemohon = cariKategoriPemohonByIdPermohonan(permohonan);

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_status_popup.jsp").addParameter("popup", "true");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        hakmilik = new Hakmilik();
        pihakPemohon = new Pihak();
        pihakPenerima = new Pihak();
        namaPemohon = null;
        namaPenerima = null;
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_status.jsp");
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = untilDate;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getKodJbtn() {
        return kodJbtn;
    }

    public void setKodJbtn(String kodJbtn) {
        this.kodJbtn = kodJbtn;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getStrKategoriPemohon() {
        return strKategoriPemohon;
    }

    public void setStrKategoriPemohon(String strKategoriPemohon) {
        this.strKategoriPemohon = strKategoriPemohon;
    }

    public FasaPermohonan getFasaPermohonanExist() {
        return fasaPermohonanExist;
    }

    public void setFasaPermohonanExist(FasaPermohonan fasaPermohonanExist) {
        this.fasaPermohonanExist = fasaPermohonanExist;
    }

    public FasaPermohonan getLastStageOfFasaPermohonan() {
        return lastStageOfFasaPermohonan;
    }

    public void setLastStageOfFasaPermohonan(FasaPermohonan lastStageOfFasaPermohonan) {
        this.lastStageOfFasaPermohonan = lastStageOfFasaPermohonan;
    }

    private String cariKategoriPemohonByIdPermohonan(Permohonan permohonan) {
        List<Dokumen> listDok = plpservice.cariKategoriPemohonByIdPermohonan(permohonan.getIdPermohonan());
        List<UrusanDokumen> listUrusanDok = plpservice.cariUrusanDokumenByKodUrusan(permohonan.getKodUrusan().getKod());
        boolean foundIndividu = false;
        boolean foundSyarikat = false;
        String katPemohon = "";

        for (Dokumen dokumenD : listDok) {
            if (foundIndividu) {
                break;
            }
            if (foundSyarikat) {
                break;
            }

            if (!foundIndividu && !foundSyarikat) {
                for (UrusanDokumen uDok : listUrusanDok) {
                    if (dokumenD.getKodDokumen().equals(uDok.getKodDokumen()) && uDok.getKategoriPemohon() == 'I') {
                        foundIndividu = true;
                        katPemohon = "Individu";
                        break;
                    }
                    if (dokumenD.getKodDokumen().equals(uDok.getKodDokumen()) && uDok.getKategoriPemohon() == 'O') {
                        foundSyarikat = true;
                        katPemohon = "Organisasi/Syarikat";
                        break;
                    }

                }

            }
        }
        return katPemohon;
    }
}

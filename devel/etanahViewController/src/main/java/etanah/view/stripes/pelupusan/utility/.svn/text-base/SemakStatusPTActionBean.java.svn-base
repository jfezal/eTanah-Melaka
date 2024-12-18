package etanah.view.stripes.pelupusan.utility;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PihakService;
import etanah.service.common.TaskDebugService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
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
@UrlBinding("/pelupusan/semak_statusPT")
public class SemakStatusPTActionBean extends AbleActionBean {

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
    ListUtil listUtil;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private KodDaerah daerah;
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
    public String fromDate;
    public String untilDate;
    private String noPT;
    private String kodBPM;
    private Pengguna peng;
    private List<KodBandarPekanMukim> senaraiKodBPM;
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    List<IdentityType> groupList = new ArrayList<IdentityType>();
    List<Permohonan> permohonanList = new ArrayList<Permohonan>();
    List<Pihak> pihakList = new ArrayList<Pihak>();
    List<Pemohon> pemohonList = new ArrayList<Pemohon>();
    List<PermohonanPihak> permohonanPihakList = new ArrayList<PermohonanPihak>();
    List<FasaPermohonan> fasaPermohonanList = new ArrayList<FasaPermohonan>();
    List<DisHakmilikPermohonan> disHakmilikPermohonanList = new ArrayList<DisHakmilikPermohonan>();
    private static final Logger LOG = Logger.getLogger(SemakStatusPTActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_status_PT.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (peng != null && peng.getKodCawangan() != null && peng.getKodCawangan().getDaerah() != null) {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerah(peng.getKodCawangan().getDaerah().getKod());
        } else if (peng != null && peng.getKodCawangan() == null) {
            addSimpleError("Untuk menggunakan utiliti ini, pengguna perlu mempunyai daerah, sila kemaskini pengguna sebelum menggunakan utiliti ini.");
        }
    }

    public Resolution search() throws ParseException {
//        if (permohonan == null && pihakPemohon == null && pihakPenerima == null && hakmilik == null && namaPemohon == null && namaPenerima == null && untilDate ==null && fromDate ==null) {
        if (permohonan == null && noPT == null && kodBPM == null) {
            addSimpleError("Sila Masukkan ID Permohonan , No PT dan Jenis Bandar Pekan Mukim.");
        } else {

            peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER); //Add for charting
//            if (pihakPemohon != null) {
//
//                if (pihakPemohon.getJenisPengenalan() == null) {
//                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
//                } else if (pihakPemohon.getNoPengenalan() == null) {
//                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
//                } else if (pihakPemohon.getJenisPengenalan() != null && pihakPemohon.getNoPengenalan() != null) {
//                    LOG.info("---CARIAN BY JENIS PENGENALAN PEMOHON " + pihakPemohon.getJenisPengenalan().getNama() + " DAN NOMBOR PENGENALAN PEMOHON " + pihakPemohon.getNoPengenalan());
//
//                    pihakList = consentService.findPihak(null, pihakPemohon.getJenisPengenalan().getKod(), pihakPemohon.getNoPengenalan());
//
//                    if (pihakList.size() > 0) {
//                        LOG.info("---LIST PIHAK  " + pihakPemohon.getNoPengenalan() + " SIZE : " + pihakList.size());
//                        for (Pihak pihak : pihakList) {
//                            LOG.info("---ID PIHAK : " + pihak.getIdPihak());
//
//                            pemohonList = pemohonService.findPemohonByIdPihakAndJabatan(pihak, "2");
//                            List<Pemohon> pemohonListChecked = new ArrayList<Pemohon>();
//
//                            for (Pemohon pemohon : pemohonList) {
//
//                                if (pemohonListChecked.isEmpty()) {
//                                    pemohonListChecked.add(pemohon);
//                                } else {
//                                    boolean found = false;
//                                    for (Pemohon pemohonChecked : pemohonListChecked) {
//                                        if (pemohon.getPermohonan().getIdPermohonan().equals(pemohonChecked.getPermohonan().getIdPermohonan())) {
//                                            found = true;
//                                        }
//                                    }
//                                    if (!found) {
//                                        pemohonListChecked.add(pemohon);
//                                    }
//                                }
//                            }
//
//                            if (!pemohonListChecked.isEmpty()) {
//                                for (Pemohon pemohon : pemohonListChecked) {
//                                    permohonanList.add(pemohon.getPermohonan());
//                                }
//                            }
//                        }
//                    }
//                }
//            } 
            if (permohonan != null) {
                LOG.info("---CARIAN BY ID PERMOHONAN " + permohonan.getIdPermohonan());
                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                if (permohonan != null) {
                    permohonanList.add(permohonan);
                }
            } else if (noPT != null) {
                NoPt noPt = new NoPt();
                noPt = plpservice.findNoPtByNoPTAndKodBPM(Long.parseLong(noPT), kodBPM);
                if (noPT != null) {
                    permohonanList.add(noPt.getHakmilikPermohonan().getPermohonan());
                }
            } else if (noPT == null && kodBPM != null) {
                List<NoPt> listNoPT = new ArrayList<NoPt>();
                listNoPT = plpservice.findNoPtByKodBPM(kodBPM);
                for (NoPt noPT : listNoPT) {
                    //Try dulu
                    if (noPT.getHakmilikPermohonan() != null && noPT.getHakmilikPermohonan().getPermohonan() != null) {
                        permohonanList.add(noPT.getHakmilikPermohonan().getPermohonan());
                    }
                }
            }


//            else if (hakmilik != null) {
//                LOG.info("---CARIAN BY ID HAKMILIK " + hakmilik.getIdHakmilik());
//                List<HakmilikPermohonan> hakmilikMohonlist = new ArrayList<HakmilikPermohonan>();
//                hakmilikMohonlist = hakmilikPermohonanService.findByIdHakmilikAndJabatan(hakmilik.getIdHakmilik(), "2");
//
//                if (!hakmilikMohonlist.isEmpty()) {
//                    for (HakmilikPermohonan hakmilikPermohonan : hakmilikMohonlist) {
//                        permohonanList.add(hakmilikPermohonan.getPermohonan());
//                    }
//                }
//            } 
//            else if (namaPemohon != null) {
//
//                LOG.info("---CARIAN BY NAMA PEMOHON " + namaPemohon);
//
//                pihakList = consentService.findPihak(namaPemohon, null, null);
//
//                if (pihakList.size() > 0) {
//                    LOG.info("---LIST PIHAK " + namaPemohon + " SIZE : " + pihakList.size());
//                    for (Pihak pihak : pihakList) {
//                        LOG.info("---ID PIHAK : " + pihak.getIdPihak());
//
//                        pemohonList = pemohonService.findPemohonByIdPihakAndJabatan(pihak, "2");
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
//            } 
//            if (untilDate != null && fromDate != null) {
//                //TODO :search from date and u0ntil date
//                String dateDari = fromDate + " 00:00:00";
//                sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                String dateHingga = untilDate + " 23:59:59";
//                sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                permohonanList = plpservice.findPermohonanByToFromDate(sd.parse(dateHingga),sd.parse(dateDari));
//                
//            } 
//            else if (fromDate != null) {
//                String dateDari = fromDate + " 00:00:00";
//                sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                permohonanList = plpservice.findPermohonanByToFromDate(null,sd.parse(dateDari));
//
//            } 
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
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_status_PT.jsp");
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
        disHakmilikPermohonanList = new ArrayList<DisHakmilikPermohonan>();
        for (HakmilikPermohonan mohonHakmilik : permohonan.getSenaraiHakmilik()) {
            DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
            disHakmilikPermohonan.setHakmilikPermohonan(mohonHakmilik);
            NoPt noPtTemp = new NoPt();
            noPtTemp = plpservice.findNoPTByIdMH(mohonHakmilik.getId());
            if (noPtTemp != null) {
                disHakmilikPermohonan.setNoPT(noPtTemp.getNoPt().toString());
            }
            disHakmilikPermohonanList.add(disHakmilikPermohonan);
        }
        List<PermohonanPihak> penerimaList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(idPermohonan, "WAR", "TER");
        permohonanPihakList = penerimaList;


        pemohonList = permohonan.getSenaraiPemohon();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_status_PT_popup.jsp").addParameter("popup", "true");
    }

    public Resolution senaraiKodBPM() {
        String kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
        if (StringUtils.isNotBlank(kodDaerah)) {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerah(kodDaerah);
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/partial_kodBPM.jsp").addParameter("popup", "true");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        hakmilik = new Hakmilik();
        pihakPemohon = new Pihak();
        pihakPenerima = new Pihak();
        namaPemohon = null;
        namaPenerima = null;
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_status_PT.jsp");
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

    public String getNoPT() {
        return noPT;
    }

    public void setNoPT(String noPT) {
        this.noPT = noPT;
    }

    public String getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(String kodBPM) {
        this.kodBPM = kodBPM;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public List<DisHakmilikPermohonan> getDisHakmilikPermohonanList() {
        return disHakmilikPermohonanList;
    }

    public void setDisHakmilikPermohonanList(List<DisHakmilikPermohonan> disHakmilikPermohonanList) {
        this.disHakmilikPermohonanList = disHakmilikPermohonanList;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }
}

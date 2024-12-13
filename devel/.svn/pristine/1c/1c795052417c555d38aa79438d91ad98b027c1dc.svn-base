package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.ConsentPtdService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PihakService;
import etanah.service.common.TaskDebugService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
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
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/kmptg")
public class KmptgActionBean extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    private TaskDebugService taskDebugService;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    private Permohonan permohonan;
    private Permohonan permohonanLama;
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
    List<IdentityType> groupList = new ArrayList<IdentityType>();
    List<Permohonan> permohonanList = new ArrayList<Permohonan>();
    List<Pemohon> pemohonList = new ArrayList<Pemohon>();
    List<PermohonanPihak> permohonanPihakList = new ArrayList<PermohonanPihak>();
    List<FasaPermohonan> fasaPermohonanList = new ArrayList<FasaPermohonan>();
    private static final Logger LOG = Logger.getLogger(KmptgActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/consent/kmptg.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public Resolution search() {

        if (permohonanLama == null) {
            addSimpleError("Sila Masukkan ID Permohonan.");
        } else {
            permohonanLama = permohonanDAO.findById(permohonanLama.getIdPermohonan());
            if (permohonanLama != null) {

                if (permohonanLama.getKodUrusan().getIdWorkflow().endsWith("Negeri/Consent/MMK") || permohonanLama.getKodUrusan().getIdWorkflow().endsWith("Negeri/Consent/MB")) {

                    if (permohonanLama.getStatus().equals("SL")) {

                        if (permohonanLama.getKeputusan().getKod().equals("T")) {

                            List<Permohonan> listMohonKMPTG = new ArrayList<Permohonan>();

                            if (permohonanLama.getKodUrusan().getIdWorkflow().endsWith("Negeri/Consent/MMK")) {
                                listMohonKMPTG = consentService.findSenaraiMohonSebelumByKodUrusanAndStatus(permohonanLama.getIdPermohonan(), "KPTG1", "AK", "TA");
                            } else {
                                listMohonKMPTG = consentService.findSenaraiMohonSebelumByKodUrusanAndStatus(permohonanLama.getIdPermohonan(), "KPTG2", "AK", "TA");
                            }

                            if (listMohonKMPTG.isEmpty()) {
                                permohonanList.add(permohonanLama);
                                addSimpleMessage("Carian maklumat permohonan berjaya.");
                            } else {
                                addSimpleError("Urusan untuk permohonan ini telah dibuat dan sedang diproses.");
                            }

                        } else {
                            addSimpleError("ID Permohonan yang dimasuk tidak sah kerana permohonan ini telah diluluskan.");
                        }

                    } else {

                        addSimpleError("Permohonan ini masih belum diselesaikan.");
                    }
                } else {
                    addSimpleError("ID Permohonan yang dimasuk tidak sah untuk urusan ini.");
                }
            } else {
                addSimpleError("Carian maklumat permohonan tidak dijumpai.");
            }

        }
        return new ForwardResolution("/WEB-INF/jsp/consent/kmptg.jsp");
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
        for (PermohonanPihak perPihak : penerimaList) {
            boolean flag = true;
            if (permohonanPihakList.isEmpty()) {
                permohonanPihakList.add(perPihak);
            } else {
                for (PermohonanPihak perPihak2 : permohonanPihakList) {

                    if (perPihak.getPihak().getIdPihak() == perPihak2.getPihak().getIdPihak()) {
                        flag = false;
                    }
                }
                if (flag) {
                    permohonanPihakList.add(perPihak);
                }
            }
        }

        pemohonList = permohonan.getSenaraiPemohon();
        return new ForwardResolution("/WEB-INF/jsp/consent/semak_status_popup.jsp").addParameter("popup", "true");
    }

    public Resolution save() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        int year = now.getYear() + 1900;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        Permohonan permohonanBaru = new Permohonan();

        try {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk("-");
            fd.setInfoAudit(ia);
            folderDokumenDAO.save(fd);


            permohonanLama = permohonanDAO.findById(permohonanLama.getIdPermohonan());
            ArrayList<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();

            for (HakmilikPermohonan hakmilikMohon : permohonanLama.getSenaraiHakmilik()) {
                listHakmilik.add(hakmilikMohon.getHakmilik());
            }

            KodUrusan kodUrusan = new KodUrusan();

            if (permohonanLama.getKodUrusan().getIdWorkflow().endsWith("Negeri/Consent/MMK")) {
                kodUrusan = kodUrusanDAO.findById("KPTG1");
            } else if (permohonanLama.getKodUrusan().getIdWorkflow().endsWith("Negeri/Consent/MB")) {
                kodUrusan = kodUrusanDAO.findById("KPTG2");
            }

            LOG.debug("adding urusan : " + kodUrusan.getNama());

            permohonanBaru = savePermohonan("05", pengguna, kodUrusan, fd, permohonanLama, listHakmilik, ia);

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());

            return new ForwardResolution("/WEB-INF/jsp/consent/kmptg.jsp");
        } finally {
        }

        LOG.debug(("initiating task " + permohonanBaru.getIdPermohonan()));
        KodUrusan ku = kodUrusanDAO.findById(permohonanBaru.getKodUrusan().getKod());
        try {
            WorkFlowService.initiateTask(ku.getIdWorkflow(),
                    permohonanBaru.getIdPermohonan(), caw.getKod() + ",00", pengguna.getIdPengguna(),
                    ku.getNama());


        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        ctx.removeWorkdata();

        addSimpleMessage("Urusan telah berjaya didaftarkan. ID Permohonan baru adalah : " + permohonanBaru.getIdPermohonan());

        return new ForwardResolution("/WEB-INF/jsp/consent/kmptg.jsp");
    }

    private Permohonan savePermohonan(String kodNegeri, Pengguna pengguna, KodUrusan kodUrusan, FolderDokumen fd,
            Permohonan permohonanLama, List<Hakmilik> listHakmilik, InfoAudit ia) {

        String idPermohonan = null;
        idPermohonan = idPermohonanGenerator.generate(kodNegeri, pengguna.getKodCawangan(), kodUrusan);

        Permohonan p = new Permohonan();
        p.setStatus("TA");
        p.setIdPermohonan(idPermohonan);
        p.setCawangan(pengguna.getKodCawangan());
        p.setKodUrusan(kodUrusan);
        p.setFolderDokumen(fd);

        InfoAudit iaPermohonan = new InfoAudit();
        // need to set the exact date for Permohonan
        Date d = new Date();
        iaPermohonan.setTarikhMasuk(d);
        iaPermohonan.setDimasukOleh(pengguna);
        p.setInfoAudit(iaPermohonan);

        if (permohonanLama != null) {
            p.setPermohonanSebelum(permohonanLama);
        }

        p.setUntukTahun(d.getYear() + 1900);
        permohonanDAO.save(p);

        if (permohonanLama != null) {
            p.setPermohonanSebelum(permohonanLama);
        }

        // attach Hakmilik
        if (listHakmilik != null) {
            for (Hakmilik hm : listHakmilik) {
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);
            }
        }

        // copy data from permohonanLama
        p.setCatatan("Permohonan ini telah dibuat berdasarkan Permohonan/Perserahan " + permohonanLama.getIdPermohonan() + ".");

        //copy penyerah
        p.setKodPenyerah(permohonanLama.getKodPenyerah());
        p.setPenyerahJenisPengenalan(permohonanLama.getPenyerahJenisPengenalan());
        p.setPenyerahNoPengenalan(permohonanLama.getPenyerahNoPengenalan());
        p.setPenyerahNama(permohonanLama.getPenyerahNama());
        p.setPenyerahAlamat1(permohonanLama.getPenyerahAlamat1());
        p.setPenyerahAlamat2(permohonanLama.getPenyerahAlamat2());
        p.setPenyerahAlamat3(permohonanLama.getPenyerahAlamat3());
        p.setPenyerahAlamat4(permohonanLama.getPenyerahAlamat4());
        p.setPenyerahPoskod(permohonanLama.getPenyerahPoskod());
        p.setPenyerahNegeri(permohonanLama.getPenyerahNegeri());
        p.setPenyerahEmail(permohonanLama.getPenyerahEmail());
        p.setPenyerahNoTelefon1(permohonanLama.getPenyerahNoTelefon1());

        // copy pemohon
        List<Pemohon> senaraiPemohon = permohonanLama.getSenaraiPemohon();
        if (senaraiPemohon != null) {
            List<Pemohon> newList = new ArrayList<Pemohon>();
            for (Pemohon p1 : senaraiPemohon) {
                Pemohon p2 = new Pemohon();
                p2.setCawangan(pengguna.getKodCawangan());
                p2.setPermohonan(p);
                p2.setPihak(p1.getPihak());
                p2.setPihakCawangan(p1.getPihakCawangan());
                p2.setKaitan(p1.getKaitan());
                p2.setPekerjaan(p1.getPekerjaan());
                p2.setPendapatan(p1.getPendapatan());
                p2.setPendapatanLain(p1.getPendapatanLain());
                p2.setStatusKahwin(p1.getStatusKahwin());
                p2.setTanggungan(p1.getTanggungan());
                p2.setUmur(p1.getUmur());
                p2.setMatawang(p1.getMatawang());
                p2.setWargaNegara(p1.getWargaNegara());
                p2.setNama(p1.getNama());
                p2.setJenisPengenalan(p1.getJenisPengenalan());
                p2.setNoPengenalan(p1.getNoPengenalan());
                p2.setAlamat(p1.getAlamat());
                p2.setAlamatSurat(p1.getAlamatSurat());
                p2.setInfoAudit(ia);
                newList.add(p2);
            }
            p.setSenaraiPemohon(newList);
        }

        // copy PermohonanPihak
        List<PermohonanPihak> senaraiPihak = permohonanLama.getSenaraiPihak();
        if (senaraiPihak != null) {
            List<PermohonanPihak> newList = new ArrayList<PermohonanPihak>();
            for (PermohonanPihak p1 : senaraiPihak) {
                PermohonanPihak p2 = new PermohonanPihak();
                p2.setCawangan(pengguna.getKodCawangan());
                p2.setPermohonan(p);
                p2.setPihak(p1.getPihak());
                p2.setPihakCawangan(p1.getPihakCawangan());
                p2.setHakmilik(p1.getHakmilik());
                p2.setJenis(p1.getJenis());
                p2.setKaitan(p1.getKaitan());
                p2.setPekerjaan(p1.getPekerjaan());
                p2.setPendapatan(p1.getPendapatan());
                p2.setStatusKahwin(p1.getStatusKahwin());
                p2.setSyerPembilang(p1.getSyerPembilang());
                p2.setSyerPenyebut(p1.getSyerPenyebut());
                p2.setTangungan(p1.getTangungan());
                p2.setUmur(p1.getUmur());
                p2.setDalamanNilai1(p1.getDalamanNilai1());
                p2.setKodMataWang(p1.getKodMataWang());
                p2.setWargaNegara(p1.getWargaNegara());
                p2.setNama(p1.getNama());
                p2.setJenisPengenalan(p1.getJenisPengenalan());
                p2.setNoPengenalan(p1.getNoPengenalan());
                p2.setAlamat(p1.getAlamat());
                p2.setAlamatSurat(p1.getAlamatSurat());
                p2.setInfoAudit(ia);
                newList.add(p2);
            }
            p.setSenaraiPihak(newList);
        }

        permohonanDAO.save(p);

        return p;
    }

    public Resolution reset() {
        permohonanLama = new Permohonan();
        return new ForwardResolution("/WEB-INF/jsp/consent/kmptg.jsp");
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

    public Permohonan getPermohonanLama() {
        return permohonanLama;
    }

    public void setPermohonanLama(Permohonan permohonanLama) {
        this.permohonanLama = permohonanLama;
    }
}

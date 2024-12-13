package etanah.view.kaunter;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;

import com.google.inject.Inject;

import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodKeputusan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;

import able.stripes.AbleActionBean;
import etanah.model.CaraBayaran;
import etanah.model.FasaPermohonan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.LinkActionBean;
import etanah.view.workflow.KernelActionBean;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.ArrayUtils;

/**
 * Based on given idPermohonan/idPerserahan, suggest the next Urusan for the
 * Permohonan. The cases: 1 If the given Permohonan is NOT completed: 1.1 If the
 * Permohonan is at SPOC's senaraiTugasan, show the Workflow & required action
 * from user (e.g. Submit more documents, pay fees etc) 1.2 If not in SPOC's
 * senaraiTugasan, display the status 2 If Permohonan is completed, check the
 * next suggested Urusan for the Permohonan. E.g. after Consent's "Kelulusan
 * Pindahmilik", next is Registration's "PindahMilik Tanah". 3 If Permohonan is
 * completed but rejected, suggest Urusan for Rayuan.
 *
 * @author hishammk
 *
 */
@UrlBinding("/kaunter/kesinambungan")
public class KesinambunganUrusan extends PermohonanKaunter2 {

    private Permohonan permohonan;
    private List taskList;
    private String size = "";
    public List listValue = new ArrayList();
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    StrataPtService strataservice;
    @Inject
    etanah.Configuration conf;
    private String[] kodursns = {"PBBS", "PBBD"};
    List<BayaranValue> listBayaran = new ArrayList();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public List<BayaranValue> getListBayaran() {
        return listBayaran;
    }

    public void setListBayaran(List<BayaranValue> listBayaran) {
        this.listBayaran = listBayaran;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    @DefaultHandler
    public Resolution insertIdPermohonan() {
        return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
    }
    private static Logger LOG = Logger.getLogger(KesinambunganUrusan.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    public Resolution checkPermohonan() throws WorkflowException {
        String negeri = conf.getProperty("kodNegeri");
        if (permohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        }
        String idPermohonan = permohonan.getIdPermohonan();
        if (idPermohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan == null) {
            LOG.warn("Permohonan " + idPermohonan + " tidak dijumpai.");
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        }

        // check if waiting for public's action
        // TODO: need to check if SPOC own this task
        if (semakBayaran(permohonan.getIdPermohonan())) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            Task impl = (Task) taskList.get(0);
            LOG.debug("TASK ID: " + impl.getSystemAttributes().getTaskId());
            return new RedirectResolution(LinkActionBean.class).addParameter(
                    "taskId", impl.getSystemAttributes().getTaskId()).addParameter("idPermohonan", permohonan.getIdPermohonan());
        } else {

            String kodUrusan = permohonan.getKodUrusan().getKod();
            KodKeputusan kpsn = permohonan.getKeputusan();


            // LELONG case: permohonan is not finished but those hakmilik sold
            // can go to Pendaftaran
            if ("PPJP".equals(kodUrusan) || "PPTL".equals(kodUrusan) || "PJTA".equals(kodUrusan)) {
                // get Hakmilik sold
                Session session = sessionProvider.get();
                @SuppressWarnings("unchecked")
                String nextUrusan = null;
                String idMohonAsal = "";
                if (permohonan.getPermohonanSebelum() != null) {
                    idMohonAsal = idPermohonan;
                    List<PermohonanAsal> listPap = session.createQuery("select pa from PermohonanAsal pa "
                            + "where pa.idPermohonan.idPermohonan = :idPermohonan").setString("idPermohonan", idPermohonan).list();
                    if (listPap.isEmpty()) {
                        idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
                    } else {
                        idPermohonan = listPap.get(0).getIdPermohonanAsal().getIdPermohonan();
                    }
                }
                List<PermohonanAtasPerserahan> listPap = session.createQuery("select pap from PermohonanAtasPerserahan pap "
                        + "where pap.permohonan.idPermohonan = :idPermohonan").setString("idPermohonan", idPermohonan).list();
                if (listPap.size() > 0) {
                    PermohonanAtasPerserahan pap = listPap.get(0);
                    if (pap.getUrusan().getKodUrusan().getKod().equals("GD") || pap.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                        nextUrusan = "JPGD";
                    }
                    if (pap.getUrusan().getKodUrusan().getKod().equals("GDPJ") || pap.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
                        nextUrusan = "JPGPJ";
                    }
                    if (pap.getUrusan().getKodUrusan().getKod().equals("PMG")) {
                        String idSasar = pap.getUrusan().getIdPerserahan();
                        List<PermohonanHubungan> listHu = session.createQuery("select hu from PermohonanHubungan hu "
                                + "where hu.permohonanSasaran.idPermohonan = :idSasar").setString("idSasar", idSasar).list();
                        if (listHu.size() > 0) {
                            PermohonanHubungan ph = listHu.get(0);
                            if (ph.getPermohonanSumber().getKodUrusan().getKod().equals("GD") || pap.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                                nextUrusan = "JPGD";
                            }
                            if (ph.getPermohonanSumber().getKodUrusan().getKod().equals("GDPJ") || ph.getPermohonanSumber().getKodUrusan().getKod().equals("GDPJK")) {
                                nextUrusan = "JPGPJ";
                            }
                        }
                    }
                }
                if (permohonan.getPermohonanSebelum() != null) {
                    idPermohonan = idMohonAsal;
                }
                List<HakmilikPermohonan> listHp = session.createQuery("select hp from HakmilikPermohonan hp, Lelongan l where "
                        + "l.permohonan.id = :idPermohonan and hp.id = l.hakmilikPermohonan.id and"
                        + " l.kodStatusLelongan.kod = 'SL'").setString("idPermohonan", idPermohonan).list();
                LOG.info("nextUrusan : " + nextUrusan);
                return nextUrusanFromPrevUrusan(idPermohonan,
                        nextUrusan, /* Perintah Jual Pentadbir Sebab Gadaian ,JPGD@JPGPJ*/
                        "16", /* Jabatan Pendaftaran*/
                        listHp);
            }
            if (negeri.equals("04")) {
                List<FasaPermohonan> fp = strataservice.getSenaraiFasaPermohonan(permohonan.getIdPermohonan());
                if (ArrayUtils.contains(kodursns, permohonan.getKodUrusan().getKod()) && fp.isEmpty()) {
                    PermohonanTuntutanKos deposit = strService.findMohonTuntutKosPBBSDeposit(permohonan.getIdPermohonan());
                    if (deposit == null || deposit.getAmaunTuntutan() == null || deposit.getAmaunTuntutan().intValue() == 0) {
                        return new RedirectResolution(BayaranDepositSementaraActionBean.class, "showForm").addParameter("idPermohonan", permohonan.getIdPermohonan());
                    }
                }
            }
            // OTHER cases depend on keputusan
            if (kpsn == null) {

                addSimpleMessage("Urusan ini sedang diproses");
                return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");

            } else if ("D".equals(kpsn.getKod()) || "L".equals(kpsn.getKod())) {

                KodUrusan urusanSlps = permohonan.getKodUrusan().getKodUrusanSelepas();
                if (urusanSlps == null) {
                    return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");
                } else {
                    addSimpleMessage("Urusan ini telah selesai. Urusan yang dicadangkan berikutnya adalah "
                            + "seperti dibawah.");
                    List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
                    return nextUrusanFromPrevUrusan(idPermohonan, urusanSlps.getKod(),
                            urusanSlps.getJabatan().getKod(), listHp);
                }

            } else if ("T".equals(kpsn.getKod())) { // TOLAK

                KodUrusan urusanRayuan = permohonan.getKodUrusan().getKodUrusanRayuan();

                if (urusanRayuan == null) {

                    addSimpleMessage("Urusan telah ditolak");
                    return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");

                } else {

                    addSimpleMessage("Urusan telah ditolak. Untuk pemohonan semula atau rayuan, "
                            + "sila pilih urusan dibawah.");
                    List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
                    RedirectResolution r = nextUrusanFromPrevUrusan(idPermohonan, urusanRayuan.getKod(),
                            urusanRayuan.getJabatan().getKod(), listHp);
                    r.addParameter("tambahUrusan", "Y");
                    return r;

                }

            } else if ("G".equals(kpsn)) { // Gantung

                addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit "
                        + permohonan.getKodUrusan().getJabatanNama());
                return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");

            } else {

                return null;

            }

        }
    }

    private boolean semakBayaran(String idPermohonan) throws WorkflowException {
        boolean semak = false;
        LOG.debug("-----idPermohonan----------" + idPermohonan);
        HttpSession ses = getContext().getRequest().getSession();
        Pengguna pengguna = (Pengguna) ses.getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());

        if (idPermohonan != null) {
            taskList = WorkFlowService.queryTasksByIdMohon(ctx, idPermohonan);
            size = taskList.size() + " Tugasan";
            LOG.debug("Size----------" + size);
            ses.setAttribute("size", size);
            listValue = new ArrayList();
            LOG.debug("-----list----------" + listValue);
            LOG.debug("-----list----------" + listValue.size());
            if (taskList.size() > 0) {
                semak = true;
            }
        }
        return semak;
    }

    private RedirectResolution nextUrusanFromPrevUrusan(String idPermohonanLama, String nextUrusan,
            String kodJabatan, List<HakmilikPermohonan> listHp) {
        RedirectResolution r = new RedirectResolution(PermohonanKaunter2.class, "Step6b");
        r.addParameter("urusan.kodUrusan", nextUrusan);
        r.addParameter("urusan.kodUrusanPilih", nextUrusan);
        r.addParameter("urusan.idPermohonanSebelum", idPermohonanLama);
        r.addParameter("urusan.kodJabatan", kodJabatan);

        // adding senarai hakmilik
        for (int i = 0; i < listHp.size(); i++) {
            HakmilikPermohonan hp = listHp.get(i);
            r.addParameter("urusan.hakmilikPermohonan[" + i + "]", hp.getHakmilik().getIdHakmilik());
        }

        return r;
    }
}

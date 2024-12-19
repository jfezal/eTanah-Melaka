/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelum;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.service.BPelService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/mukim_indek")
public class MukimIndeksActionBean extends AbleActionBean {

    private static final Logger LOGGER = Logger.getLogger(MukimIndeksActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    private static Logger logger = Logger.getLogger(MukimIndeksActionBean.class);
    private Permohonan permohonan;
    private String idPermohonan;
    private String nama;
    private Pengguna pguna;
    private HakmilikPermohonan hakmilikPermohonan;
    private Hakmilik hakmilik;
    private String stageId;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Hakmilik> hakmilikList;
    private List<HakmilikAsal> senaraiHakmilikAsal;
    private List<HakmilikSebelum> senaraiHakmilikSblm;
    private long idAsal;
    private String idHakmilik;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    ArrayList listIdPermohonan = new ArrayList<String>();
    String senaraiIdPermohonan[], idPertama, idKedua;
    private Boolean statusNorujukan = Boolean.FALSE;
    private Boolean maklumatTanahSek49 = Boolean.FALSE;
    private String idPemohonan1;
    private String taskId1;
    private String stageId1;
    private Pengguna pengguna;

    public String getIdPemohonan1() {
        return idPemohonan1;
    }

    public void setIdPemohonan1(String idPemohonan1) {
        this.idPemohonan1 = idPemohonan1;
    }

    public String getTaskId1() {
        return taskId1;
    }

    public void setTaskId1(String taskId1) {
        this.taskId1 = taskId1;
    }

    public String getStageId1() {
        return stageId1;
    }

    public void setStageId1(String stageId1) {
        this.stageId1 = stageId1;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/mukim_indeks_charting_akhir.jsp").addParameter("tab", "true");

    }

    public Resolution showForm2() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/mukim_indeks_charting_akhir_1.jsp").addParameter("tab", "true");

    }

    public Resolution kemaskiniHakmilik() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPemohonan1 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        taskId1 = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId1 = getCurrentStageId(taskId1);

        LOGGER.debug("--> idpguna: " + pengguna.getIdPengguna());
        LOGGER.debug("--> nama pguna: " + pengguna.getNama());
        LOGGER.debug("--> Jawatan pguna: " + pengguna.getJawatan());
        LOGGER.debug("--> idPermohonan: " + idPemohonan1);
        LOGGER.debug("--> task id: " + taskId1);
        LOGGER.debug("--> Stage id: " + stageId1);

        return new JSP("penguatkuasaan/daftar_kemaskini_hakmilik.jsp").addParameter("tab", "true");
    }

    public String getCurrentStageId(String taskId) {
        BPelService service = new BPelService();
        stageId1 = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId1 = t.getSystemAttributes().getStage();
        }
        return stageId1;
    }
    

    public Resolution viewHakmilikAsal() {
        String id2 = getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(id2);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/view_hakmilik_asal.jsp").addParameter("popup", "true");
    }

    public Resolution viewHakmilikBaru() {
        String id = getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(id);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/view_hakmilik_baru.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId(taskId);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            logger.debug("idPermohonan :" + idPermohonan);

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    //1) After create new IP
                    permohonan = permohonan.getPermohonanSebelum();
                }
                maklumatTanahSek49 = true;
                hakmilikPermohonanList = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
            }

            Long id = null;

            if (maklumatTanahSek49) {
                if (!hakmilikPermohonanList.isEmpty()) {

                    for (int j = 0; j < hakmilikPermohonanList.size(); j++) {
                        if (hakmilikPermohonanList.get(j).getNomborRujukan() != null) {
                            statusNorujukan = true;
                            System.out.println("::::::::::: value j :" + j);
                            HakmilikPermohonan hp = hakmilikPermohonanList.get(j);
                            listIdPermohonan.add(hp.getNomborRujukan());

                            ArrayList<String> data = listIdPermohonan;


                            for (String a : data) {
                                senaraiIdPermohonan = a.split(",");
                                logger.info("length senaraiIdPermohonan : " + senaraiIdPermohonan.length);
                                if (senaraiIdPermohonan.length > 1) {
                                    idPertama = senaraiIdPermohonan[0];
                                    idKedua = senaraiIdPermohonan[1];

                                }
                            }
                            logger.info("::: idPertama : " + idPertama);
                            logger.info("::: idKedua : " + idKedua);

                            String idMohon = "";

                            if (StringUtils.isNotEmpty(idPertama) && StringUtils.isNotEmpty(idKedua)) {
                                if (idPertama.equalsIgnoreCase(idPermohonan)) {
                                    idMohon = idPertama;
                                    id = hakmilikPermohonanList.get(j).getId();
                                    System.out.println("id MH (1): " + id);
                                } else if (idKedua.equalsIgnoreCase(idPermohonan)) {
                                    idMohon = idKedua;
                                    id = hakmilikPermohonanList.get(j).getId();
                                    System.out.println("id MH (2): " + id);
                                }
                            }

                            listIdPermohonan.clear();
                            idPertama = "";
                            idKedua = "";
                        }
                    }

                    System.out.println("::: id : " + id);
                    System.out.println("::: statusNorujukan : " + statusNorujukan);

                    if (statusNorujukan == true) {
                        if (id != null) {
                            logger.info("::: using id MH");
                            hakmilikPermohonanList = enforceService.findListMohonHakmilikById(id);
                        } else {
                            logger.info("::: using id idPermohonan");
                            hakmilikPermohonanList = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan(), idPermohonan);
                        }
                    }

                    senaraiHakmilikSblm = enforceService.findByIDHakmilikSblm(permohonan.getIdPermohonan());
                    logger.debug("Size" + senaraiHakmilikSblm.size());
                }
            } else {
                logger.debug("::::: For other than sek 49 MLK");
                String[] tname = {"permohonan"};
                Object[] model = {permohonan};

                senaraiHakmilikSblm = enforceService.findByIDHakmilikSblm(idPermohonan);
                logger.debug("Size" + senaraiHakmilikSblm.size());

                hakmilikPermohonanList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);

            }

            if (senaraiHakmilikSblm.size() != 0) {
                for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                    hakmilikPermohonan = hp;
                    hakmilik = hp.getHakmilik();
                }
            }





            //permohonan.getPermohonanSebelum().


        }
    }

    public MukimIndeksActionBean() {
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }

        return stageId;
    }

    public long getIdAsal() {
        return idAsal;
    }

    public void setIdAsal(long idAsal) {
        this.idAsal = idAsal;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<HakmilikAsal> getSenaraiHakmilikAsal() {
        return senaraiHakmilikAsal;
    }

    public void setSenaraiHakmilikAsal(List<HakmilikAsal> senaraiHakmilikAsal) {
        this.senaraiHakmilikAsal = senaraiHakmilikAsal;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<Hakmilik> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<Hakmilik> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikSebelum> getSenaraiHakmilikSblm() {
        return senaraiHakmilikSblm;
    }

    public void setSenaraiHakmilikSblm(List<HakmilikSebelum> senaraiHakmilikSblm) {
        this.senaraiHakmilikSblm = senaraiHakmilikSblm;
    }
}

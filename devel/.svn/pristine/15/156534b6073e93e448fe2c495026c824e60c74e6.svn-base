/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanUrusan;
import etanah.model.Projek;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/maklumat_permohonan")
public class MaklumatPermohonanActionBean extends AbleActionBean {

    private static final Logger LOGGER = Logger.getLogger(MaklumatPermohonanActionBean.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    private etanah.Configuration conf; //insert by azwady.org 25/02/2014

    private List<Permohonan> senaraiPermohonanSebelum;
    private List<String> senaraiTahun = new ArrayList<String>();
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private PermohonanUrusan permohonanUrusan;
    private PermohonanUrusan permohonanUrusanRuj;
    private PermohonanUrusan permohonanUrusanRayuan;
    private Pengguna pengguna;
    private KodUrusan kodUrusan;
    private String kodNegeri; //insert by azwady.org 25/02/2014
    private String tarikhPerintah;
    private String stageId;
    private Long idProjek;
    private String kodPenyerah;
    private String penyerahNegeri;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showEdit() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showEditPenyerah() {
        getContext().getRequest().setAttribute("editPenyerah", Boolean.TRUE);
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showUrusanTerdahulu() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);

            BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

            if (StringUtils.isNotBlank(taskId)) {
                Task task = service.getTaskFromBPel(taskId, pengguna);
                stageId = task.getSystemAttributes().getStage();
            }

            if (permohonan.getProjek() != null) {
                idProjek = permohonan.getProjek().getIdProjek();
            }

            if (permohonan.getKodPenyerah() != null) {
                kodPenyerah = permohonan.getKodPenyerah().getKod();
            }

            if (permohonan.getPenyerahNegeri() != null) {
                penyerahNegeri = permohonan.getPenyerahNegeri().getKod();
            }

            //FOR NO RUJUKAN FAIL URUSAN TANAH ADAT
            permohonanUrusanRuj = conService.findMohonUrusanByPerihal(idPermohonan, "NOMBOR RUJUKAN");

            //FOR URUSAN MMKN MELAKA 
            /*if ("04".equals(conf.getProperty("kodNegeri"))) {
             kodNegeri = "melaka"; //insert by azwady.org 25/02/2014 - only show for melaka
             }*/
            if (permohonan.getKodUrusan().getKod().equals("PMMK1")) {
                permohonanUrusanRayuan = conService.findMohonUrusanByPerihal(idPermohonan, "RAYUAN");

            }

            //FOR URUSAN BANTAHAN TANAH ADAT CONSENT
            if (permohonan.getKodUrusan().getKod().equals("BTADT")) {
                permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "URUSAN BANTAHAN");

                if (permohonanUrusan != null) {
                    kodUrusan = kodUrusanDAO.findById(permohonanUrusan.getCatatan());
                }
            }

            //FOR URUSAN PMMAT CONSENT
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PMMAT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("DPWNA")) {

                permohonanRujukanLuar = conService.findMohonRujukanByIdMohon(idPermohonan);

                if (permohonan.getPermohonanSebelum() != null) {

                    if (!permohonan.getPermohonanSebelum().getSenaraiRujukanLuar().isEmpty() && permohonanRujukanLuar == null) {

                        PermohonanRujukanLuar rujukanLuarTemp = permohonan.getPermohonanSebelum().getSenaraiRujukanLuar().get(0);
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setDimasukOleh(pengguna);
                        infoAudit.setTarikhMasuk(new java.util.Date());

                        permohonanRujukanLuar = new PermohonanRujukanLuar();
                        permohonanRujukanLuar.setPermohonan(permohonan);
                        permohonanRujukanLuar.setCawangan(rujukanLuarTemp.getCawangan());
                        permohonanRujukanLuar.setCatatan(rujukanLuarTemp.getCatatan());
                        permohonanRujukanLuar.setTarikhRujukan(rujukanLuarTemp.getTarikhRujukan());
                        permohonanRujukanLuar.setNoRujukan(rujukanLuarTemp.getNoRujukan());
                        permohonanRujukanLuar.setKodRujukan(rujukanLuarTemp.getKodRujukan());
                        permohonanRujukanLuar.setNilai(rujukanLuarTemp.getNilai());
                        permohonanRujukanLuar.setInfoAudit(infoAudit);
                        conService.simpanRujukanLuar(permohonanRujukanLuar);
                        tarikhPerintah = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
                    }
                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("DPWNA")) {
                    //calendar for year
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    senaraiTahun.add(String.valueOf(year));
                    for (int i = 0; i < 50; i++) {
                        year--;
                        senaraiTahun.add(String.valueOf(year));
                    }

                }

                if (permohonanRujukanLuar != null) {
                    if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                        tarikhPerintah = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
                    }
                }
            }

            //FOR MAKLUMAT TAMBAHAN KES RAYUAN
            if (!permohonan.getKodUrusan().getKod().equals("BTADT") && !permohonan.getKodUrusan().getKod().equals("RMJTL")) {

                PermohonanUrusan mohonUrusanCheck = conService.findMohonUrusanByIdMohon(idPermohonan);

                if (permohonan.getSenaraiUrusan().isEmpty()) {

                    if (permohonan.getPermohonanSebelum() != null) {

                        if (mohonUrusanCheck == null) {
                            if (!permohonan.getPermohonanSebelum().getSenaraiUrusan().isEmpty()) {

                                for (PermohonanUrusan pu : permohonan.getPermohonanSebelum().getSenaraiUrusan()) {

                                    InfoAudit infoAudit = new InfoAudit();
                                    infoAudit.setDimasukOleh(pengguna);
                                    infoAudit.setTarikhMasuk(new java.util.Date());

                                    permohonanUrusan = new PermohonanUrusan();
                                    permohonanUrusan.setPerjanjianAmaun(pu.getPerjanjianAmaun());

                                    if (pu.getPerjanjianTempohTahun() != null) {
                                        permohonanUrusan.setPerjanjianTempohTahun(pu.getPerjanjianTempohTahun());
                                    }
                                    permohonanUrusan.setCatatan(pu.getCatatan());
                                    permohonanUrusan.setSebab(pu.getSebab());
                                    permohonanUrusan.setPerihal(pu.getPerihal());
                                    permohonanUrusan.setCawangan(permohonan.getCawangan());
                                    permohonanUrusan.setPermohonan(permohonan);
                                    permohonanUrusan.setInfoAudit(infoAudit);
                                    conService.simpanPermohonanUrusan(permohonanUrusan);

                                }
                            }
                        }
                    }
                }
            }

            //FIND PERMOHONAN MELIBATKAN RAYUAN
            if (permohonan.getPermohonanSebelum() != null) {

                Permohonan mohonSebelumCheck = new Permohonan();
                mohonSebelumCheck = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                senaraiPermohonanRujukanLuar = permohonanRujukanLuarService.findByidPermohonanList(permohonan.getPermohonanSebelum().getIdPermohonan());

                for (PermohonanRujukanLuar p : senaraiPermohonanRujukanLuar) {
                    if (p.getAgensi() == null && p.getCatatan() == null) {
                        senaraiPermohonanRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
                        senaraiPermohonanRujukanLuar.add(p);
                    }
                }

                senaraiPermohonanSebelum = new ArrayList<Permohonan>();

                while (mohonSebelumCheck != null) {

                    senaraiPermohonanSebelum.add(mohonSebelumCheck);
                    if (mohonSebelumCheck.getPermohonanSebelum() != null) {
                        String idMohonSebelum = mohonSebelumCheck.getPermohonanSebelum().getIdPermohonan();
                        mohonSebelumCheck = new Permohonan();
                        mohonSebelumCheck = permohonanDAO.findById(idMohonSebelum);
                    } else {
                        mohonSebelumCheck = null;
                    }
                }
            }
        }
    }

    public Resolution simpanPermohonan() {
        Projek projek = new Projek();
        projek.setIdProjek(idProjek);
        permohonan.setProjek(projek);
        conService.simpanPermohonan(permohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPenyerah() {

        KodPenyerah kodP = new KodPenyerah();
        kodP.setKod(kodPenyerah);

        KodNegeri kodN = new KodNegeri();
        kodN.setKod(penyerahNegeri);

        permohonan.setKodPenyerah(kodP);
        permohonan.setPenyerahNegeri(kodN);
        
        conService.simpanPermohonan(permohonan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("editPenyerah", Boolean.TRUE);
        
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPermohonanUrusan() {

        if (permohonanUrusan != null) {
            permohonanUrusan.setPermohonan(permohonan);
            permohonanUrusan.setCawangan(permohonan.getCawangan());
            permohonanUrusan.setPerihal("URUSAN BANTAHAN");
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanUrusan.setInfoAudit(infoAudit);
            conService.simpanPermohonanUrusan(permohonanUrusan);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } else {
            addSimpleError("Sila masukkan nama permohonan terdahulu.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPermohonanUrusanRujukan() {

        if (permohonanUrusanRuj != null) {
            permohonanUrusanRuj.setPermohonan(permohonan);
            permohonanUrusanRuj.setCawangan(permohonan.getCawangan());
            permohonanUrusanRuj.setPerihal("NOMBOR RUJUKAN");
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanUrusanRuj.setInfoAudit(infoAudit);
            conService.simpanPermohonanUrusan(permohonanUrusanRuj);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } else {
            addSimpleError("Sila masukkan nombor rujukan fail.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPermohonanUrusanRayuan() {

        if (permohonanUrusanRayuan != null) {
            permohonanUrusanRayuan.setPermohonan(permohonan);
            permohonanUrusanRayuan.setCawangan(permohonan.getCawangan());
            permohonanUrusanRayuan.setPerihal("RAYUAN");
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanUrusanRayuan.setInfoAudit(infoAudit);
            conService.simpanPermohonanUrusan(permohonanUrusanRayuan);

            //RESET KEPUTUSAN TO NULL IF KES RAYUAN BAYARAN
            if (permohonan.getKeputusan() != null) {
                permohonan.setKeputusan(null);
                conService.simpanPermohonan(permohonan);
            }

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } else {
            addSimpleError("Sila masukkan maklumat Jenis Rayuan Pengurangan Bayaran.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPermohonanRujukanLuar() {

        if (permohonanRujukanLuar != null) {

            Boolean checkedData = false;
            if (permohonan.getKodUrusan().getKod().equals("PMMAT")) {
                if (permohonanRujukanLuar.getCatatan() != null && tarikhPerintah != null && permohonanRujukanLuar.getNoRujukan() != null && permohonanRujukanLuar.getItem() != null) {
                    checkedData = true;
                }
            } else if (permohonan.getKodUrusan().getKod().equals("DPWNA")) {
                if (permohonanRujukanLuar.getCatatan() != null && tarikhPerintah != null && permohonanRujukanLuar.getNoRujukan() != null && permohonanRujukanLuar.getNilai() != null && permohonanRujukanLuar.getItem() != null) {
                    checkedData = true;
                }
            }

            if (checkedData) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                try {
                    permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhPerintah));
                } catch (ParseException ex) {
                    java.util.logging.Logger.getLogger(MaklumatPermohonanActionBean.class.getName()).log(Level.SEVERE, null, ex);
                }

                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodRujukan kodRujukan = new KodRujukan();
                kodRujukan.setKod("FL");
                permohonanRujukanLuar.setKodRujukan(kodRujukan);
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                conService.simpanRujukanLuar(permohonanRujukanLuar);
                addSimpleMessage("Maklumat telah berjaya disimpan.");

            } else {
                addSimpleError("Sila masukkan maklumat dengan lengkap.");
            }
        } else {
            addSimpleError("Sila masukkan maklumat dengan lengkap.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public String getKodNegeri() {
        kodNegeri = conf.getProperty("kodNegeri");
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public PermohonanUrusan getPermohonanUrusan() {
        return permohonanUrusan;
    }

    public void setPermohonanUrusan(PermohonanUrusan permohonanUrusan) {
        this.permohonanUrusan = permohonanUrusan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhPerintah() {
        return tarikhPerintah;
    }

    public void setTarikhPerintah(String tarikhPerintah) {
        this.tarikhPerintah = tarikhPerintah;
    }

    public List<Permohonan> getSenaraiPermohonanSebelum() {
        return senaraiPermohonanSebelum;
    }

    public void setSenaraiPermohonanSebelum(List<Permohonan> senaraiPermohonanSebelum) {
        this.senaraiPermohonanSebelum = senaraiPermohonanSebelum;
    }

    public PermohonanUrusan getPermohonanUrusanRuj() {
        return permohonanUrusanRuj;
    }

    public void setPermohonanUrusanRuj(PermohonanUrusan permohonanUrusanRuj) {
        this.permohonanUrusanRuj = permohonanUrusanRuj;
    }

    public List<String> getSenaraiTahun() {
        return senaraiTahun;
    }

    public void setSenaraiTahun(List<String> senaraiTahun) {
        this.senaraiTahun = senaraiTahun;
    }

    public List<PermohonanRujukanLuar> getSenaraiPermohonanRujukanLuar() {
        return senaraiPermohonanRujukanLuar;
    }

    public void setSenaraiPermohonanRujukanLuar(List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar) {
        this.senaraiPermohonanRujukanLuar = senaraiPermohonanRujukanLuar;
    }

    public PermohonanUrusan getPermohonanUrusanRayuan() {
        return permohonanUrusanRayuan;
    }

    public void setPermohonanUrusanRayuan(PermohonanUrusan permohonanUrusanRayuan) {
        this.permohonanUrusanRayuan = permohonanUrusanRayuan;
    }

    public Long getIdProjek() {
        return idProjek;
    }

    public void setIdProjek(Long idProjek) {
        this.idProjek = idProjek;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKodPenyerah() {
        return kodPenyerah;
    }

    public void setKodPenyerah(String kodPenyerah) {
        this.kodPenyerah = kodPenyerah;
    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }  
}

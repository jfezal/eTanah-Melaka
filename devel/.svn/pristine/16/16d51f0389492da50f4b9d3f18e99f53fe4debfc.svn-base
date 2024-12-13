/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

/**
 *
 * @author nurul.izza modified by sitifariza.hanim on 11102010 Modified By
 * Murali on 24/05/2011
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.LupusPermitDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermitItemDAO;
import etanah.dao.PermitSahLakuDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.model.DokumenKewangan;
import etanah.model.HakmilikPermohonan;
import etanah.service.PelupusanService;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPermit;
import etanah.model.LupusPermit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;
import etanah.sequence.GeneratorNoPermit;

@UrlBinding("/pelupusan/penyediaan_borang4A")
public class Borang4AActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    LupusPermitDAO lupusPermitDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    StrataPtService strService;
    @Inject
    EnforceService enforceService;
    @Inject
    PermitSahLakuDAO permitSahLakuDAO;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarAO;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    @Inject
    PermitItemDAO permitItemDAO;
    @Inject
    GeneratorNoPermit generatorNoPermit;
    @Inject
    etanah.Configuration conf;
    private PermohonanTuntutanBayar permohonanTuntutanBayar;
    private PermitSahLaku permitSahLaku;
    private String noPermit;
    private BigDecimal bayaran;
    private String bayaranPerkataan;
    private String noResit;
    private String tempohBerakhir;
    private String idPermohonan;
    private Pengguna pguna;
    private LupusPermit lupusPermit;
    private Permohonan permohonan;
    private Permit permit;
    private String peruntukanTambahan;
    private Pemohon pemohon;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPermitItem ppi;
    private PermitItem permitItem;
    private String stageId;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger log = Logger.getLogger(KeputusanPermohonanActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        return new JSP("pelupusan/penyediaan_borang4A.jsp").addParameter("tab", "true");
    }

    public Resolution showFormView() {
        getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
        return new JSP("pelupusan/penyediaan_borang4A.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
            permitSahLaku = pelupusanService.findPermitSahLakuByIdMohon(idPermohonan);
            permit = permitSahLaku.getPermit();
            System.out.println(permit.getIdPermit());
        } else {
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        }
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId, pguna);
        System.out.println(stageId);

        if (permit != null) {
            peruntukanTambahan = permit.getPeruntukanTambahan();
            if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                permitSahLaku = pelupusanService.findPermitSahLakuByIdMohon(idPermohonan);
            } else {
                permitSahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
            }
        }


        if (idPermohonan != null) {
            List<PermohonanTuntutanKos> senaraiMohonTuntutKos = strService.findMohonTuntutKos(idPermohonan);
            if (senaraiMohonTuntutKos != null) {
                for (PermohonanTuntutanKos ptk : senaraiMohonTuntutKos) {
                    if (ptk.getKodTuntut() != null && ptk.getKodTuntut().getKod().equalsIgnoreCase("DISB4A")) {
                        permohonanTuntutanBayar = pelupusanService.findMohonTuntutBayar(ptk.getIdKos());
                    }

                }
            }
        }

    }

    public Resolution simpanPermit() {


        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);

        if (Validation()) {
            return new JSP("pelupusan/penyediaan_borang4A.jsp").addParameter("tab", "true");
        }
        log.info("-------Permit SahLaku Saving--------------------");
        if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
            permitSahLaku = pelupusanService.findPermitSahLakuByIdMohon(idPermohonan);
            permit = permitSahLaku.getPermit();
            System.out.println("simpan :" + permit.getIdPermit());
        } else {
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        }
        //permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        log.info("-------permit--------------------" + permit);
        PermitSahLaku permitSahLakuTemp = null;
        if (permit != null) {
            log.info("-------permitSahLaku--------------------" + permitSahLaku);
            permit.setKodCawangan(permohonan.getCawangan());
            InfoAudit ia = permit.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            log.info("-------permit is Null--------------------");

            log.info("-------Generating No permit---------");
            permit = new Permit();
//            Permit maxpermit = pelupusanService.getMaxOfNoPermit() ;
////            if (maxpermit == null) {
////                permit.setNoPermit("1");
////            } else {
//                int maxVal = Integer.parseInt(maxpermit.getNoPermit()) + 1;
//                permit.setNoPermit(maxVal + "");
////            }

            permit.setPermohonan(permohonan);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permit.setInfoAudit(ia);
            permit.setKodCawangan(permohonan.getCawangan());
        }
        log.info("-------permit Not Null--------------------");

        if (permit.getNoPermit() == null) {
//            Permit maxpermit = pelupusanService.getMaxOfNoPermit();
//            Permit maxpermit = pelupusanService.getMaxOfNoPermitByIdMohon(permohonan.getIdPermohonan());
//            int maxVal = 0;
//            if (maxpermit == null) {
//                maxVal = maxVal + 1;
//            } else if (maxpermit != null && StringUtils.isEmpty(maxpermit.getNoPermit())) {
//                maxVal = maxVal + 1;
//            } else {
//                maxVal = Integer.parseInt(maxpermit.getNoPermit()) + 1;
//            }
//
//            permit.setNoPermit(maxVal + "");
            String sequenceNoPermit = generatorNoPermit.generate(conf.getProperty("kodNegeri"), peng.getKodCawangan(), permit);
            log.info("-----Generate No Permit ----" + sequenceNoPermit);
            permit.setNoPermit(sequenceNoPermit);
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PLPTD") || permohonan.getKodUrusan().getKod().equals("RLPS")) {
            KodJenisPermit kodJenis = kodJenisPermitDAO.findById("A");
            permit.setKodJenisPermit(kodJenis);
        } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
            KodJenisPermit kodJenis = kodJenisPermitDAO.findById("B");
            permit.setKodJenisPermit(kodJenis);
        }
        if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
            pemohon = pelupusanService.findPemohonByIdPemohon(permohonan.getPermohonanSebelum().getIdPermohonan());
        } else {
            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        }

        permit.setPihak(pemohon.getPihak());
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
//                if(hakmilikPermohonan.getCatatan() != null){
//                permit.setKeterangan(hakmilikPermohonan.getCatatan()) ;
//                }
        if (StringUtils.isNotEmpty(peruntukanTambahan)) {
            permit.setPeruntukanTambahan(peruntukanTambahan);
        }
        permit = pelupusanService.saveOrUpdate(permit);


        if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
            permitSahLakuTemp = pelupusanService.findPermitSahLakuByIdMohon(idPermohonan);
        } else {
            permitSahLakuTemp = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
        }
        InfoAudit info;
        if (permitSahLakuTemp != null) {
            log.info("-------permitSahLaku Not Null---------------::");
            info = permitSahLakuTemp.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);

        } else {
            log.info("-------permitSahLaku is Null--------::");
            permitSahLakuTemp = new PermitSahLaku();
            permitSahLakuTemp.setPermit(permit);
            permitSahLakuTemp.setPermohonan(permohonan);
            info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);
            permitSahLakuTemp.setKodCawangan(permohonan.getCawangan());
        }
        if (permitSahLakuTemp.getNoSiri() == null) {
            PermitSahLaku pTemp = pelupusanService.getMaxOfNoSiri();

            if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                int maxSiri = 0;
                if (pTemp == null) {
                    maxSiri = maxSiri + 1;
                } else if (pTemp != null && StringUtils.isEmpty(pTemp.getNoSiri())) {
                    maxSiri = maxSiri + 1;
                } else {
                    maxSiri = Integer.parseInt(pTemp.getNoSiri()) + 1;
                }

                permitSahLakuTemp.setNoSiri("2013/" + maxSiri);

            } else {
                int maxSiri = 0;
                if (pTemp == null) {
                    maxSiri = maxSiri + 1;
                } else if (pTemp != null && StringUtils.isEmpty(pTemp.getNoSiri())) {
                    maxSiri = maxSiri + 1;
                } else {
                    maxSiri = Integer.parseInt(pTemp.getNoSiri()) + 1;
                }

                permitSahLakuTemp.setNoSiri(maxSiri + "");
            }

        }
        permitSahLakuTemp.setTarikhPermit(new java.util.Date());
        permitSahLakuTemp.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());
//        permitSahLakuTemp.setTarikhPermitTamat(permitSahLaku.getTarikhPermitTamat());
        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(permitSahLaku.getTarikhPermitMula());
//        int year=calendar.get(Calendar.YEAR);
        int month = Calendar.DECEMBER;
        int day = 31;
        if (permohonan.getKodUrusan().getKod().equals("PLPTD") || (permohonan.getKodUrusan().getKod().equals("RLPS") && permohonan.getCawangan().getKod().equals("04"))) {
            permitSahLakuTemp.setTarikhPermitTamat(permitSahLaku.getTarikhPermitTamat());
        } else {
            permitSahLakuTemp.setTarikhPermitTamat(new java.util.Date(calendar.get(Calendar.YEAR) - 1900, month, day));
        }
        pelupusanService.simpanPermitSahLaku(permitSahLakuTemp);

        ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
//         log.info(ppi) ;
        permitItem = pelupusanService.findPermitItemByIdPermit2(permit.getIdPermit());

        if (permitItem == null) {
            log.info("-------permitItem is Null--------------------");
            permitItem = new PermitItem();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            permitItem.setInfoAudit(ia);
            permitItem.setKodItemPermit(ppi.getKodItemPermit());
            permitItem.setPermit(permit);
            permitItem.setKodCawangan(permohonan.getCawangan());
            pelupusanService.simpanPermitItem(permitItem);

        }
//            else{
//              info = new InfoAudit() ;
//            info.setDimasukOleh(permitItem.getInfoAudit().getDimasukOleh()) ;
//            info.setTarikhMasuk(permitItem.getInfoAudit().getTarikhMasuk()) ;
//            info.setDiKemaskiniOleh(peng) ;
//            info.setTarikhKemaskini(new java.util.Date()) ;
//            permitItem.setInfoAudit(info) ;
//            permitItemDAO.saveOrUpdate(permitItem) ;
//
//            }



        addSimpleMessage("Maklumat telah disimpan");
        return new JSP("pelupusan/penyediaan_borang4A.jsp").addParameter("tab", "true");
    }

    public boolean Validation() {
        boolean flag = false;

        if ((permitSahLaku == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Permit Mula");
        } else if ((permitSahLaku != null) && (permitSahLaku.getTarikhPermitMula() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Permit Mula");
        }
//        if ((permitSahLaku == null)) {
//            flag = true;
//            addSimpleError("Sila Masukkan Tarikh Permit Tamat");
//        } else if ((permitSahLaku != null) && (permitSahLaku.getTarikhPermitTamat() == null)) {
//            flag = true;
//            addSimpleError("Sila Masukkan Tarikh Permit Tamat");
//        }
        return flag;
    }

    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public BigDecimal getBayaran() {
        return bayaran;
    }

    public void setBayaran(BigDecimal bayaran) {
        this.bayaran = bayaran;
    }

    public String getBayaranPerkataan() {
        return bayaranPerkataan;
    }

    public void setBayaranPerkataan(String bayaranPerkataan) {
        this.bayaranPerkataan = bayaranPerkataan;
    }

    public String getNoPermit() {
        return noPermit;
    }

    public void setNoPermit(String noPermit) {
        this.noPermit = noPermit;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public String getTempohBerakhir() {
        return tempohBerakhir;
    }

    public void setTempohBerakhir(String tempohBerakhir) {
        this.tempohBerakhir = tempohBerakhir;
    }

    public LupusPermit getLupusPermit() {
        return lupusPermit;
    }

    public void setLupusPermit(LupusPermit lupusPermit) {
        this.lupusPermit = lupusPermit;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public String getPeruntukanTambahan() {
        return peruntukanTambahan;
    }

    public void setPeruntukanTambahan(String peruntukanTambahan) {
        this.peruntukanTambahan = peruntukanTambahan;
    }

    public PermitSahLaku getPermitSahLaku() {
        return permitSahLaku;
    }

    public void setPermitSahLaku(PermitSahLaku permitSahLaku) {
        this.permitSahLaku = permitSahLaku;
    }

    public PermohonanTuntutanBayar getPermohonanTuntutanBayar() {
        return permohonanTuntutanBayar;
    }

    public void setPermohonanTuntutanBayar(PermohonanTuntutanBayar permohonanTuntutanBayar) {
        this.permohonanTuntutanBayar = permohonanTuntutanBayar;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermitItem permitItem) {
        this.permitItem = permitItem;
    }

    public PermohonanPermitItem getPpi() {
        return ppi;
    }

    public void setPpi(PermohonanPermitItem ppi) {
        this.ppi = ppi;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}

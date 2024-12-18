/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sitifariza.hanim
 */
package etanah.view.stripes.pengambilan;

import etanah.dao.KodRujukanDAO;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import java.util.logging.Level;
import java.util.logging.Logger;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPermit;
import etanah.model.KodUOM;
import etanah.model.NoPt;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.KodRizab;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.TanahRizabPermohonan;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.SimpleDateFormat;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.service.BPelService;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@UrlBinding("/pengambilan/keputusanMesyuarat")
public class RekodKeputusanMMKActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String kodNegeri;
    String tarikhMesyuarat;
    String tarikhSidang;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private String keputusan;
    private String jam;
    private String minit;
    private String saat;
    private String ampm;
    private String ampmDisplay;
    private String keputusanDisplay;
    private String negeri;
    private String nomborRujukan;
    private String kodU;
    private PermohonanKertas Pkertas;
    private PermohonanKertas permohonanKertas;
    private List<PermohonanKertas> senaraiPermohonanKertas;
    private NoPt noPt;
    private HakmilikPermohonan hakmilikPermohonan;
    private KodKeputusan kodKeputusan;
    private KodUOM kodLuasLulus;
    private Permit permit;
    private String stageId;
    Task task = null;
    private Pengguna pguna;
    private BPelService service;
    private Boolean edit;
    private Boolean button;
    private String idPermohonan;
    private PermohonanKertas kertas;
    private String masasidang;
    private String kpsn;
    private String kpsnnama;
    private BigDecimal amnt = BigDecimal.ZERO;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private TanahRizabPermohonan trizabPermohonan;
    private BigDecimal luasPohon = BigDecimal.ZERO;

    

    
    /*
     * FOR LMCRG
     */
    private String noLot;
    private String bpm;
    private String daerah;
    private Date tarikhPendaftaran;
    private PermohonanTuntutanKos mohonTuntutKos;
    private PermitSahLaku permitSahLaku;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RekodKeputusanMMKActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("button", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanTiadaResult", Boolean.TRUE);
        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanDate", Boolean.TRUE);
        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("displayTarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan", Boolean.TRUE);
        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan2", Boolean.TRUE);
        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        getContext().getRequest().setAttribute("button", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasanRAYT", Boolean.TRUE);

        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution showOnlyForTerimaKeputusan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuaratTerima", Boolean.TRUE);

        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution viewKeputusan() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("button2", Boolean.TRUE);
        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution viewKeputusan2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("button2", Boolean.FALSE);
        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    private Resolution showFormGSA() {
        kodNegeri = conf.getProperty("kodNegeri");

        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        //   taskId =new String();
//         String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//           System.out.println("taskId-------------rehydrate--------------------"+taskId);
//        if (StringUtils.isBlank(taskId)) {
//            taskId = getContext().getRequest().getParameter("taskId");
//             System.out.println("taskId-------------rehydrate if--------------------"+taskId);
//        }
//            System.out.println("task---------------1------------------"+task);
//        task = service.getTaskFromBPel(taskId, pguna);
//          System.out.println("task----------------2-----------------"+task);
//        if (task != null) {
//             System.out.println("task----------------if-----------------"+task);
//            stageId = task.getSystemAttributes().getStage();
//             System.out.println("stageId----------------1-----------------"+stageId);
//        } else {
//               System.out.println("task----------------else-----------------"+task);
//            stageId = getContext().getRequest().getParameter("stageId");
//            System.out.println("stageId----------------2-----------------"+stageId);

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
//        }
        if (idPermohonan != null) {
            kodNegeri = conf.getProperty("kodNegeri");
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                permohonanKertas = pelupusanService.findMohonanKertasByIdPermohonanNKodDok(idPermohonan, "RMN");
            } else if (permohonan.getKodUrusan().getKod().equals("PBRZ")) {
                permohonanKertas = pelupusanService.findMohonanKertasByIdPermohonanNKodDok(idPermohonan, "RMN");
            } else if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                permohonanKertas = pelupusanService.findMohonanKertasByIdPermohonanNKodDok(idPermohonan, "RMN");
            } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                permohonanKertas = pelupusanService.findMohonanKertasByIdPermohonanNKodDok(idPermohonan, "RMN");
            } else if (permohonan.getKodUrusan().getKod().equals("PRMMK")) {
                permohonanKertas = pelupusanService.findMohonanKertasByIdPermohonanNKodDok(idPermohonan, "RMN");
                trizabPermohonan = new TanahRizabPermohonan();
                trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
                luasPohon = trizabPermohonan.getLuasTerlibat();
            }else {
                permohonanKertas = pelupusanService.findMohonanKertasByIdPermohonanNKodDok(idPermohonan, "MMKN");
            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("LMCRG")) {
                permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
                if (permit != null) {
                    permitSahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
                    tarikhPendaftaran = permit.getTarikhPermit();
                }
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                mohonHakmilik = pelupusanService.findByIdPermohonan(idPermohonan);
                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");
            }

            if (permohonanKertas == null) {
                permohonanKertas = new PermohonanKertas();
                permohonanKertas.getPermohonan();
            }
            if (permohonan.getKeputusan() != null) {
                kpsn = permohonan.getKeputusan().getKod();
                kpsnnama = permohonan.getKeputusan().getNama();
            }

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            if (!permohonan.getKodUrusan().getKod().equals("PJLB")) {
                if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                    hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0); //For temporary only for ptgsa
                } else {
                    hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
                }
            } else {
                hakmilikPermohonan = pelupusanService.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            }

            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLuasLulusUom() != null)) {
                kodU = hakmilikPermohonan.getLuasLulusUom().getKod();
            }
            if (hakmilikPermohonan != null) {
                if (hakmilikPermohonan.getHakmilik() != null) {
                    Hakmilik hakmilik = new Hakmilik();
                    hakmilik = hakmilikPermohonan.getHakmilik();
                    noLot = hakmilik.getNoLot();
                    bpm = hakmilik.getBandarPekanMukim().getNama();
                    daerah = hakmilik.getDaerah().getNama();
                } else {
                    noLot = hakmilikPermohonan.getNoLot();
                    bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
                    daerah = hakmilikPermohonan.getBandarPekanMukimBaru().getDaerah().getNama();
                }
            }
            LOG.info("hakmilikPermohonan.getId() : " + hakmilikPermohonan.getId());
            noPt = pelupusanService.findNoPtByIdHakmilikPermohonan(hakmilikPermohonan.getId());
//              System.out.println("noPt:-----------:" + noPt);

            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);
            if ((permohonanTuntutanKos != null) && (permohonanTuntutanKos.getAmaunTuntutan() != null)) {
                amnt = permohonanTuntutanKos.getAmaunTuntutan();
            }

            //   List<PermohonanRujukanLuar> listRujLuar;

//            if (kodNegeri.equals("04")) {
//                negeri = "Sidang MMKN";
//            } else {
//                negeri = "Sidang MMK";
//            }
//            System.out.println(negeri);



//            listRujLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
//            permohonanRujukanLuar = pelupusanService.findMohonRujukLuarIdPermohonanNamaSidang(idPermohonan, negeri);

//            if (permohonanRujukanLuar != null) {
////                permohonanRujukanLuar = listRujLuar.get(0);
//                tarikhMesyuarat = sdf1.format(permohonanRujukanLuar.getTarikhSidang());
//                if (permohonanRujukanLuar.getTarikhSidang() != null) {
//
////                    tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
////                    jam = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(11, 13);
////                    minit = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(14, 16);
////                    saat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(17, 19);
////                    ampm = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(20, 22);
////
////                    if (ampm.equals("AM")) {
////                        ampmDisplay = "Pagi";
////                    } else {
////                        ampmDisplay = "Petang";
////                    }
//
//                    if (permohonan.getKeputusan() != null) {
//                        keputusan = permohonan.getKeputusan().getKod();
//                        keputusanDisplay = permohonan.getKeputusan().getNama();
//                    }
//                }
//            }

            if (permohonan.getKodUrusan().getKod().equals("RAYT") || permohonan.getKodUrusan().getKod().equals("PBRZ")) {
                getContext().getRequest().setAttribute("button", Boolean.TRUE);
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
                getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
                getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
                getContext().getRequest().setAttribute("ulasanRAYT", Boolean.TRUE);
            }
        }
    }

//    public Resolution simpanMesyuaratTerima() {
//
//            if (permohonanKertas!=null) {
//            InfoAudit info = new InfoAudit();
//            info.setDimasukOleh(pguna);
//            info.setTarikhMasuk(new java.util.Date());
//            permohonanKertas.setInfoAudit(info);
//            permohonanKertas.setCawangan(pguna.getKodCawangan());
//            permohonanKertas.setPermohonan(permohonan);
//            permohonanKertas.setTajuk("Rekod MMKN");
//            pelupusanService.simpanPermohonanKertas(permohonanKertas);
//        }
//        permohonan.setKeputusanOleh(pguna);
//        if(kpsn != null)
//        permohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
//        permohonan.setTarikhKeputusan(new java.util.Date());
//
//        pelupusanService.simpanPermohonan(permohonan);
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
//        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
//        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
//        getContext().getRequest().setAttribute("simpanMesyuaratTerima", Boolean.TRUE);
//
//          addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new JSP("pelupusan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
//    }
    public Resolution simpanMesyuarat() {
        InfoAudit info = new InfoAudit();
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (permohonanKertas != null) {
            if (permohonanKertas.getPermohonan() != null) {
                info = permohonanKertas.getInfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanKertas.setInfoAudit(info);
                permohonanKertas.setCawangan(pguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                // permohonanKertas.setTajuk("Rekod MMKN");

                pelupusanService.simpanPermohonanKertas(permohonanKertas);
            }
        }


        if (idPermohonan != null) {
            info = permohonan.getInfoAudit();
        }
        info.setDiKemaskiniOleh(pguna);
        info.setTarikhKemaskini(new java.util.Date());
        permohonan.setInfoAudit(info);
        permohonan.setKeputusanOleh(pguna);
//         if(kpsn != null)
        permohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
        permohonan.setTarikhKeputusan(new java.util.Date());
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            permohonan.setUlasan(permohonan.getUlasan());
        }
        pelupusanService.simpanPermohonan(permohonan);
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            if (noPt == null) {
                System.out.println("No PT --- null");
                noPt = new NoPt();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                noPt.setHakmilikPermohonan(hakmilikPermohonan);
                noPt.setInfoAudit(info);
                pelupusanService.simpanNoPt(noPt);
            } else {
                System.out.println("No PT --- not null");
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(permohonan.getInfoAudit().getTarikhKemaskini());
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
                noPt.setInfoAudit(info);
                noPt.setHakmilikPermohonan(hakmilikPermohonan);
                pelupusanService.simpanNoPt(noPt);
            }
            
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            if (hakmilikPermohonan != null) {
                info = hakmilikPermohonan.getInfoAudit();
            }
            
            String luas = getContext().getRequest().getParameter("luasLulusUom.kod");
            System.out.println("-------------" + luas);
            if (StringUtils.isNotBlank(luas)) {
                kodLuasLulus = new KodUOM();
                kodLuasLulus.setKod(luas);
                hakmilikPermohonan.setLuasLulusUom(kodLuasLulus);
            }
            pelupusanService.simpanHakmilikPermohonan2(hakmilikPermohonan);
            
        }
        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB")) {
            if (kodU != null) {
                KodUOM kuom = kodUOMDAO.findById(kodU);
                hakmilikPermohonan.setLuasLulusUom(kuom);
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PPRU") || permohonan.getKodUrusan().getKod().equals("PPTR") || permohonan.getKodUrusan().getKod().equals("PRIZ") || permohonan.getKodUrusan().getKod().equals("PJBTR")) {
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            if (hakmilikPermohonan != null) {
                info = hakmilikPermohonan.getInfoAudit();
            }
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            String luas = getContext().getRequest().getParameter("luasLulusUom.kod");
            System.out.println("-------------" + luas);
            if (StringUtils.isNotBlank(luas)) {
                kodLuasLulus = new KodUOM();
                kodLuasLulus.setKod(luas);
                hakmilikPermohonan.setLuasLulusUom(kodLuasLulus);
            }
            pelupusanService.simpanHakmilikPermohonan2(hakmilikPermohonan);
        }

        //Add for PTGSA
        if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
            for (int i = 0; i < permohonan.getSenaraiHakmilik().size(); i++) {
                hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(i);
                if (hakmilikPermohonan != null) {
                    info = hakmilikPermohonan.getInfoAudit();
                }
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
                String luas = getContext().getRequest().getParameter("luasLulusUom.kod");
                String luasL = getContext().getRequest().getParameter("hakmilikPermohonan.luasDiluluskan");
                System.out.println("-------------" + luas);
                if (StringUtils.isNotBlank(luas)) {
                    kodLuasLulus = new KodUOM();
                    kodLuasLulus.setKod(luas);
                    hakmilikPermohonan.setLuasLulusUom(kodLuasLulus);
                }
                Double luasLulus = Double.parseDouble(luasL);
                hakmilikPermohonan.setLuasDiluluskan(BigDecimal.valueOf(luasLulus));
                pelupusanService.simpanHakmilikPermohonan2(hakmilikPermohonan);
            }
        }


        /*
         * SAVING TO MOHON TUNTUT KOS
         */
        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB")) {

//             LOG.info("-------Saving in MOHON TUNTUTKOS For CARIGALI && LOMBONG-------::");
            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);
//             LOG.info("-------permohonanTuntutanKos-------::"+permohonanTuntutanKos);
            info = new InfoAudit();
            if (permohonanTuntutanKos != null) {
//                 LOG.info("-------permohonanTuntutanKos NOT Null-------::");
                info = permohonanTuntutanKos.getInfoAudit();
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
            } else {
//                 LOG.info("-------permohonanTuntutanKos Is Null-------::");
                permohonanTuntutanKos = new PermohonanTuntutanKos();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
            }
            permohonanTuntutanKos.setPermohonan(permohonan);
            permohonanTuntutanKos.setInfoAudit(info);
            permohonanTuntutanKos.setCawangan(pguna.getKodCawangan());

            if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISCR").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISCR"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISCR").getKodKewTrans());
            } else if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISLB").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISLB"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISLB").getKodKewTrans());
            }
            permohonanTuntutanKos.setAmaunTuntutan(amnt);
            pelupusanService.simpanPermohonanTuntutanKos(permohonanTuntutanKos);
        }


        getContext().getRequest().setAttribute("button", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTiadaResult() {
        if (tarikhMesyuarat == null || permohonanRujukanLuar == null || permohonanRujukanLuar.getNoSidang() == null || jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {

            if (tarikhMesyuarat == null && permohonanRujukanLuar == null && jam.equals("0")) {
                addSimpleError("Sila Masukkan Data Berikut.");
            } else if (permohonanRujukanLuar.getNoSidang() == null) {
                addSimpleError("Sila Masukkan Bilangan Mesyuarat.");
            } else if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
            } else if (jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {
                addSimpleError("Sila Masukkan Masa Dengan Lengkap.");
            }

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            if (saat.equals("0")) {
                saat = "00";
            }

            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":" + saat + " " + ampm;

            try {

                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(RekodKeputusanMMKActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setInfoAudit(infoAudit);

            conService.simpanRujukanLuar(permohonanRujukanLuar);

            tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanTiadaResult", Boolean.TRUE);

        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");

    }

    public Resolution simpanDate() {
        if (tarikhMesyuarat == null || jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {

            if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
            } else if (jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {
                addSimpleError("Sila Masukkan Masa Dengan Lengkap.");
            }

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            if (saat.equals("0")) {
                saat = "00";
            }

            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":" + saat + " " + ampm;

            permohonanRujukanLuar = new PermohonanRujukanLuar();

            try {

                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(RekodKeputusanMMKActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setInfoAudit(infoAudit);

            conService.simpanRujukanLuar(permohonanRujukanLuar);

            tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanDate", Boolean.TRUE);

        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");

    }

    public Resolution simpanUlasan() {

        if (keputusan == null) {
            addSimpleError("Sila Masukkan Keputusan.");

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit = permohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);

            permohonan.setInfoAudit(infoAudit);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            conService.simpanPermohonan(permohonan);

            infoAudit = new InfoAudit();
            infoAudit = permohonanRujukanLuar.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            permohonanRujukanLuar.setInfoAudit(infoAudit);
            conService.simpanRujukanLuar(permohonanRujukanLuar);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("displayTarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan", Boolean.TRUE);

        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public void simpanLesen() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        PermitSahLaku permitSahLakuTemp = null;
        if (permit != null) {
            permit.setKodCawangan(permohonan.getCawangan());
            InfoAudit ia = permit.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            permit = new Permit();
            permit.setPermohonan(permohonan);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new Date());
            permit.setInfoAudit(ia);
            permit.setKodCawangan(permohonan.getCawangan());
            permit.setTarikhPermit(new java.util.Date());
        }

        if (permit.getNoPermit() == null) {
            Permit maxpermit = pelupusanService.getMaxOfNoPermit();
//            if (maxpermit == null) {
//                permit.setNoPermit("1");
//            } else {
            int maxVal = Integer.parseInt(maxpermit.getNoPermit()) + 1;
            permit.setNoPermit(maxVal + "");
        }
        KodJenisPermit kodJenis = kodJenisPermitDAO.findById("G");
        if (permitSahLaku.getTarikhPermitMula() != null) {
            permit.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());

        }
        if (permitSahLaku.getTarikhPermitTamat() != null) {
            permit.setTarikhpermitAkhir(permitSahLaku.getTarikhPermitTamat());
            
        }
        permit.setKodJenisPermit(kodJenis);
        Pemohon pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permit.setPihak(pemohon.getPihak());

//        permit.setPeruntukanTambahan(peruntukanTambahan);
        permit = pelupusanService.saveOrUpdate(permit);



        permitSahLakuTemp = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());

        InfoAudit info;
        if (permitSahLakuTemp != null) {
            info = permitSahLakuTemp.getInfoAudit();
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);

        } else {
            permitSahLakuTemp = new PermitSahLaku();
            permitSahLakuTemp.setPermit(permit);
            permitSahLakuTemp.setPermohonan(permohonan);
            info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);
            permitSahLakuTemp.setKodCawangan(permohonan.getCawangan());
        }
        if (permitSahLakuTemp.getNoSiri() == null) {
            PermitSahLaku pTemp = pelupusanService.getMaxOfNoSiri();
            int maxSiri = Integer.parseInt(pTemp.getNoSiri()) + 1;

            permitSahLakuTemp.setNoSiri(maxSiri + "");
        }
        permitSahLakuTemp.setTarikhPermit(new java.util.Date());
        permitSahLakuTemp.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());
        permitSahLakuTemp.setTarikhPermitTamat(permitSahLaku.getTarikhPermitTamat());
        pelupusanService.simpanPermitSahLaku(permitSahLakuTemp);
        String amaunTuntut = getContext().getRequest().getParameter("mohonTuntutKos.amaunTuntutan");
        System.out.println("amaunTuntut  :" + amaunTuntut);
        BigDecimal amaun = null;
        if (StringUtils.isNotBlank(amaunTuntut)) {
            double bayaran = Double.parseDouble(amaunTuntut);
            amaun = BigDecimal.valueOf(bayaran);
            System.out.println("amaun :" + amaun);
        }
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");

        InfoAudit infoAudit = new InfoAudit();
        if ((mohonTuntutKos != null)) {
            infoAudit = mohonTuntutKos.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonTuntutKos = new PermohonanTuntutanKos();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

//        if (permohonanTuntutanKos != null) {
//            info = permohonanTuntutanKos.getInfoAudit();
//            info.setTarikhKemaskini(new java.util.Date());
//            info.setDiKemaskiniOleh(pguna);
//            permohonanTuntutanKos.setInfoAudit(info);
//        } else {
//            permohonanTuntutanKos = new PermohonanTuntutanKos();
//            info = new InfoAudit();
//            info.setDimasukOleh(pguna);
//            info.setTarikhMasuk(new java.util.Date());
//            permohonanTuntutanKos.setInfoAudit(info);
//       
        }
        mohonTuntutKos.setCawangan(permohonan.getCawangan());
        if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
            mohonTuntutKos.setItem("Bayaran Lesen Cari Gali dan Penjelajahan");
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISCR"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISCR").getKodKewTrans());
        } else if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
            mohonTuntutKos.setItem("Bayaran Lesen Pajakan Lombong");
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISCR")); //Tukar kod tuntut (For PJLB)
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISCR").getKodKewTrans()); //Tukar kod transaksi
        }
        mohonTuntutKos.setPermohonan(permohonan);
        mohonTuntutKos.setInfoAudit(infoAudit);

        if (amaun != null) {
            mohonTuntutKos.setAmaunTuntutan(amaun);
        }
        pelupusanService.simpanPermohonanTuntutanKos1(mohonTuntutKos);
    }

    public Resolution simpanMesyuaratNLesen() {

        InfoAudit info = new InfoAudit();
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (permohonanKertas != null) {
            if (permohonanKertas.getPermohonan() != null) {
                info = permohonanKertas.getInfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanKertas.setInfoAudit(info);
                permohonanKertas.setCawangan(pguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                // permohonanKertas.setTajuk("Rekod MMKN");

                pelupusanService.simpanPermohonanKertas(permohonanKertas);
            }
        }


        if (idPermohonan != null) {
            info = permohonan.getInfoAudit();
        }
        info.setDiKemaskiniOleh(pguna);
        info.setTarikhKemaskini(new java.util.Date());
        permohonan.setInfoAudit(info);
        permohonan.setKeputusanOleh(pguna);
//         if(kpsn != null)
        permohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
        permohonan.setTarikhKeputusan(new java.util.Date());
        pelupusanService.simpanPermohonan(permohonan);
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            if (noPt == null) {
                System.out.println("No PT --- null");
                noPt = new NoPt();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                noPt.setHakmilikPermohonan(hakmilikPermohonan);
                noPt.setInfoAudit(info);
                pelupusanService.simpanNoPt(noPt);
            } else {
                System.out.println("No PT --- not null");
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(permohonan.getInfoAudit().getTarikhKemaskini());
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
                noPt.setInfoAudit(info);
                noPt.setHakmilikPermohonan(hakmilikPermohonan);
                pelupusanService.simpanNoPt(noPt);
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB")) {
            if (kodU != null) {
                KodUOM kuom = kodUOMDAO.findById(kodU);
                hakmilikPermohonan.setLuasLulusUom(kuom);
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PPRU") || permohonan.getKodUrusan().getKod().equals("PPTR") || permohonan.getKodUrusan().getKod().equals("PRIZ") || permohonan.getKodUrusan().getKod().equals("PJBTR")) {
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            if (hakmilikPermohonan != null) {
                info = hakmilikPermohonan.getInfoAudit();
            }
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            String luas = getContext().getRequest().getParameter("luasLulusUom.kod");
            System.out.println("-------------" + luas);
            if (StringUtils.isNotBlank(luas)) {
                kodLuasLulus = new KodUOM();
                kodLuasLulus.setKod(luas);
                hakmilikPermohonan.setLuasLulusUom(kodLuasLulus);
            }
            pelupusanService.simpanHakmilikPermohonan2(hakmilikPermohonan);
        }

        /*
         * SAVING TO MOHON TUNTUT KOS
         */
        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB")) {

//             LOG.info("-------Saving in MOHON TUNTUTKOS For CARIGALI && LOMBONG-------::");
            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);
//             LOG.info("-------permohonanTuntutanKos-------::"+permohonanTuntutanKos);
            info = new InfoAudit();
            if (permohonanTuntutanKos != null) {
//                 LOG.info("-------permohonanTuntutanKos NOT Null-------::");
                info = permohonanTuntutanKos.getInfoAudit();
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
            } else {
//                 LOG.info("-------permohonanTuntutanKos Is Null-------::");
                permohonanTuntutanKos = new PermohonanTuntutanKos();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
            }
            permohonanTuntutanKos.setPermohonan(permohonan);
            permohonanTuntutanKos.setInfoAudit(info);
            permohonanTuntutanKos.setCawangan(pguna.getKodCawangan());

            if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISCR").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISCR"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISCR").getKodKewTrans());
            } else if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISLB").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISLB"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISLB").getKodKewTrans());
            }
            permohonanTuntutanKos.setAmaunTuntutan(amnt);
            pelupusanService.simpanPermohonanTuntutanKos(permohonanTuntutanKos);
        }
        if (!StringUtils.isEmpty(kpsn)) {
            if (kpsn.equalsIgnoreCase("L")) {
                simpanLesen();
            }
        }

        getContext().getRequest().setAttribute("button", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");

    }

    public Resolution simpanUlasan2() {
        if (tarikhMesyuarat == null || keputusan == null || jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {

            if (tarikhMesyuarat == null && jam.equals("0") && minit.equals("0") && saat.equals("0") && ampm.equals("0") && keputusan == null) {
                addSimpleError("Sila Masukkan Data Berikut.");
            } else if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
            } else if (jam.equals("0") || minit.equals("0") || saat.equals("0") || ampm.equals("0")) {
                addSimpleError("Sila Masukkan Masa Dengan Lengkap.");
            } else if (keputusan == null) {
                addSimpleError("Sila Masukkan Keputusan.");
            }

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            conService.simpanPermohonan(permohonan);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            if (saat.equals("0")) {
                saat = "00";
            }

            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":" + saat + " " + ampm;

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<PermohonanRujukanLuar> listRujLuar;
            listRujLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();

            if (!(listRujLuar.isEmpty())) {
                permohonanRujLuarTemp = listRujLuar.get(0);
                infoAudit = new InfoAudit();
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }

            try {
                permohonanRujLuarTemp.setTarikhSidang(sdf.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(RekodKeputusanMMKActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanRujLuarTemp.setCawangan(peng.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            if (permohonanRujukanLuar != null) {
                permohonanRujLuarTemp.setUlasan(permohonanRujukanLuar.getUlasan());

            }

            conService.simpanRujukanLuar(permohonanRujLuarTemp);

            tarikhMesyuarat = sdf.format(permohonanRujLuarTemp.getTarikhSidang()).substring(0, 10);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan2", Boolean.TRUE);

        return new JSP("pengambilan/rekod_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getAmpmDisplay() {
        return ampmDisplay;
    }

    public void setAmpmDisplay(String ampmDisplay) {
        this.ampmDisplay = ampmDisplay;
    }

    public String getKeputusanDisplay() {
        return keputusanDisplay;
    }

    public void setKeputusanDisplay(String keputusanDisplay) {
        this.keputusanDisplay = keputusanDisplay;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Boolean getButton() {
        return button;
    }

    public void setButton(Boolean button) {
        this.button = button;
    }

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
    }

    public String getKpsnnama() {
        return kpsnnama;
    }

    public void setKpsnnama(String kpsnnama) {
        this.kpsnnama = kpsnnama;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public KodUOM getKodLuasLulus() {
        return kodLuasLulus;
    }

    public void setKodLuasLulus(KodUOM kodLuasLulus) {
        this.kodLuasLulus = kodLuasLulus;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public String getKodU() {
        return kodU;
    }

    public void setKodU(String kodU) {
        this.kodU = kodU;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public PermitSahLaku getPermitSahLaku() {
        return permitSahLaku;
    }

    public void setPermitSahLaku(PermitSahLaku permitSahLaku) {
        this.permitSahLaku = permitSahLaku;
    }

    public Date getTarikhPendaftaran() {
        return tarikhPendaftaran;
    }

    public void setTarikhPendaftaran(Date tarikhPendaftaran) {
        this.tarikhPendaftaran = tarikhPendaftaran;
    }
    public TanahRizabPermohonan getTrizabPermohonan() {
        return trizabPermohonan;
    }

    public void setTrizabPermohonan(TanahRizabPermohonan trizabPermohonan) {
        this.trizabPermohonan = trizabPermohonan;
    }
    public BigDecimal getLuasPohon() {
        return luasPohon;
    }

    public void setLuasPohon(BigDecimal luasPohon) {
        this.luasPohon = luasPohon;
    }

    public List<PermohonanKertas> getSenaraiPermohonanKertas() {
        return senaraiPermohonanKertas;
    }

    public void setSenaraiPermohonanKertas(List<PermohonanKertas> senaraiPermohonanKertas) {
        this.senaraiPermohonanKertas = senaraiPermohonanKertas;
    }
}

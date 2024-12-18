/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/jana")
public class pageForViewJana extends AbleActionBean {

    @Inject
    StrataReportStageName srn;
    @Inject
    CommonService comm;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    StrataPtService strService;
    private String SERVER_LOCATION = "";
    private String reportKey;
    @Inject
    private etanah.Configuration conf;
    private String iframeURL = "";
    private String reportName = "";
    private Dokumen dokumen = null;
    private Permohonan permohonan = new Permohonan();
    String idPermohonan;
    String stageId;
    String lala = "";
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(pageForViewJana.class);
    private boolean print = false;
    private boolean jana = false;
    private String ayat = "";
    private String idDokumen;
    private String ulasanJupem;
    private boolean isJanaDokumen = false;
    private String syor;

    public boolean isIsJanaDokumen() {
        return isJanaDokumen;
    }

    public void setIsJanaDokumen(boolean isJanaDokumen) {
        this.isJanaDokumen = isJanaDokumen;
    }

    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        lala = "/dokumen/view/147601";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        
        if(permohonan != null){
            for(KandunganFolder kandFold : permohonan.getFolderDokumen().getSenaraiKandungan()){
                System.out.println("kod dokumen "+permohonan.getFolderDokumen().getSenaraiKandungan());
                System.out.println("kod "+kandFold.getDokumen().getKodDokumen().getKod());
                if(kandFold.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("RBHS1")){
                    System.out.println("Jana Dokumen : True");
                    isJanaDokumen = true;
                    break;
                }else{
                    System.out.println("Jana Dokumen : False");
                    isJanaDokumen = false;
                }
            }
        } 
        getContext().getRequest().setAttribute("jana", Boolean.TRUE);
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/pbbm/gisStrata.jsp").addParameter("tab", "true");
    }

    public Resolution buttonJana() {
        return new JSP("strata/common/paparSuratCommon.jsp").addParameter("tab", "true");
    }

    public Resolution janaRBHS() {
        String error = "";
        String msg = "";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String kodNegeri = conf.getProperty("kodNegeri");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        comm.setPengguna(pengguna);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        FasaPermohonan mohonFasa = new FasaPermohonan();
        mohonFasa = strService.findMohonFasa(idPermohonan, "keputusan");
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        if (mohonFasa != null) {
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        if (kodNegeri.equals("04")) {
                            t.add("STRSLulusRayuanBayaranHMStrata_MLK.rdf");
                            t2.add("RBHS1");
                            comm.reportGen(p, t, t2);
                        } else {
                            t.add("STRSLulusRayuanBayaranHMStrata_NS.rdf");
                            t2.add("RBHS1");
                            comm.reportGen(p, t, t2);
                        }
                    } else {
                        if (kodNegeri.equals("04")) {
                            t.add("STRSTolakRayuanBayaranHMStrata_MLK.rdf");
                            t2.add("RBHS2");
                            comm.reportGen(p, t, t2);
                        } else {
                            t.add("STRSTolakRayuanBayaranHMStrata_MLK.rdf");
                            t2.add("RBHS2");
                            comm.reportGen(p, t, t2);
                        }
                    }
                    msg = "Dokumen telah dijana. Sila semak pada tab dokumen";
                    isJanaDokumen = true;
                } else {
                    error = "Sila masukkan keputusan terlebih dahulu";
//                    addSimpleError("Sila masukkan keputusan terlebih dahulu");
                }
            } else {
                error = "Sila masukkan keputusan terlebih dahulu";
//                addSimpleError("Sila masukkan keputusan terlebih dahulu");
            }
        } else {
            error = "Sila masukkan keputusan terlebih dahulu";
//            addSimpleError("Sila masukkan keputusan terlebih dahulu");
        }
        return new RedirectResolution(pageForViewJana.class, "buttonJana").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution stageKelulusan() {
        stageId = new String();

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        srn.setPermohonan(permohonan);
        if (permohonan.getKodUrusan().getKod().equals("PBBS")) {
            stageId = "keputusan";
            dokumen = srn.urusanPBBS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS1")) {
            stageId = "keputusan";
            dokumen = srn.urusanRMHS1(permohonan, stageId);
        }

        //added 25/06/2012
        if (permohonan.getKodUrusan().getKod().equals("PBBD")) {
            stageId = "keputusan";
            dokumen = srn.urusanPBBD(permohonan, stageId);
        }
        
        if (permohonan.getKodUrusan().getKod().equals("RMH1A")) {
            stageId = "keputusan";
            dokumen = srn.urusanRMH1A(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PPRUS")) {
            stageId = "keputusan";
            dokumen = srn.urusanPPRUS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PKKR")) {
            stageId = "keputusan";
            dokumen = srn.urusanPKKR(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PPPP")) {
            stageId = "keputusan";
            dokumen = srn.urusanPPPP(permohonan, stageId);

        }
        if (permohonan.getKodUrusan().getKod().equals("P8")) {
        }
        if (permohonan.getKodUrusan().getKod().equals("PWPN")) {
            stageId = "keputusan";
            dokumen = srn.urusanPWPN(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PNSS")) {
        }

        String docPath = conf.getProperty("document.path");
        StringBuilder cmd = new StringBuilder(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + dokumen.getNamaFizikal() + strService.getFormat(dokumen.getFormat()));
        iframeURL = cmd.toString();
        getContext().getRequest().setAttribute("jana", Boolean.FALSE);
        getContext().getRequest().setAttribute("lulus", Boolean.TRUE);
        return new JSP("strata/common/paparan_jana.jsp").addParameter("tab", "true");
    }

    public Resolution showJana() {

        return new JSP("strata/common/pageForView.jsp").addParameter("tab", "true");
    }

    public Resolution showJana1() {
        return new JSP("strata/common/pageForView1.jsp").addParameter("tab", "true");
    }
    
       public Resolution showJana2() {
        return new JSP("strata/common/pageForView2.jsp").addParameter("tab", "true");
    }

    public Resolution viewReport() {

        Resolution r;
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        srn.setPermohonan(permohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        if (permohonan.getKodUrusan().getKod().equals("PBBS")) {
            dokumen = srn.urusanPBBS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS1")) {
            dokumen = srn.urusanRMHS1(permohonan, stageId);
        }

        //added 25/06/2012
        if (permohonan.getKodUrusan().getKod().equals("PBBD")) {
            dokumen = srn.urusanPBBD(permohonan, stageId);
        }        
        if (permohonan.getKodUrusan().getKod().equals("RMH1A")) {
            dokumen = srn.urusanRMH1A(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PBS")) {
            dokumen = srn.urusanPBS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PSBS")) {
            dokumen = srn.urusanPSBS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PHPC")) {
            dokumen = srn.urusanPHPC(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
            dokumen = srn.urusanPHPP(permohonan, stageId);
        }  
        if (permohonan.getKodUrusan().getKod().equals("PPRUS")) {
            dokumen = srn.urusanPPRUS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PWPN")) {
            dokumen = srn.urusanPWPN(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PNB")) {
            dokumen = srn.urusanPNB(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PNSS")) {
            dokumen = srn.urusanPNSS(permohonan, stageId);
        }        
       
        if (permohonan.getKodUrusan().getKod().equals("RTPS")) {
            dokumen = srn.urusanRTPS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("RTHS")) {
            dokumen = srn.urusanRTHS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("P8")) {
            dokumen = srn.urusanP8(permohonan, stageId);
        }


        LOG.info("Dokumen : " + dokumen);
        if (dokumen == null) {
            LOG.info("print");

            r = janaReport();
            print = true;
            jana = false;
        } else {
            LOG.info("janaReport");
            print = false;
            jana = true;
            r = printReport();
        }
        return r;
    }

    public Resolution janaReport() {
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        reportName = getReportName(stageId, permohonan);
        SERVER_LOCATION = conf.getProperty("report.server.location");
        InetAddress address = null;
        String ipAdd = "";
        try {
            address = InetAddress.getByName(comm.sub(comm.removeHttp(SERVER_LOCATION)));
            ipAdd = comm.replaceDNS(address.getHostAddress(), SERVER_LOCATION);
        } catch (UnknownHostException ex) {
            java.util.logging.Logger.getLogger(pageForViewJana.class.getName()).log(Level.SEVERE, null, ex);
        }

        reportKey = conf.getProperty("report.key");
        StringBuilder cmd = new StringBuilder(ipAdd).append("?").append(reportKey).append("&").append(reportName).append("&").append("p_id_mohon").append("=").append(idPermohonan);
        LOG.info("SERVER REPORT : " + cmd);
        iframeURL = cmd.toString();
        getContext().getRequest().setAttribute("jana", Boolean.TRUE);
        getContext().getRequest().setAttribute("lulus", Boolean.FALSE);
        print = true;
        jana = false;
        return new JSP("strata/common/paparan_jana.jsp").addParameter("tab", "true");
    }

    public String getReportName(String stageId, Permohonan mohon) {
        String reportName = null;
        if (mohon.getKodUrusan().getKod().equals("PBBS")) {
            reportName = getReportNamePBBS(stageId, mohon);
        }
        if (mohon.getKodUrusan().getKod().equals("RMHS1")) {
            reportName = getReportNameRMHS1(stageId, mohon);
        }

        //added 25/06/2012
        if (mohon.getKodUrusan().getKod().equals("PBBD")) {
            reportName = getReportNamePBBD(stageId, mohon);
        }
        //added 02-08-2012
        if (mohon.getKodUrusan().getKod().equals("RMH1A")) {
            reportName = getReportNameRMH1A(stageId, mohon);
        }
        if (mohon.getKodUrusan().getKod().equals("PPRUS")) {
            reportName = getReportNamePPRUS(stageId, mohon);
        }
        if (mohon.getKodUrusan().getKod().equals("PWPN")) {
            reportName = getReportNamePWPN(stageId, mohon);
        }
        if (mohon.getKodUrusan().getKod().equals("PNSS")) {
            reportName = getReportNamePNSS(stageId, mohon);
        }
        if (mohon.getKodUrusan().getKod().equals("PNB")) {
            reportName = getReportNamePNB(stageId, mohon);
        }       
        
        if (mohon.getKodUrusan().getKod().equals("RTPS")) {
            reportName = getReportNameRTPS(stageId, mohon);
        }
        if (mohon.getKodUrusan().getKod().equals("RTHS")) {
            reportName = getReportNameRTHS(stageId, mohon);
        }
        if (mohon.getKodUrusan().getKod().equals("P8")) {
            reportName = getReportNameP8(stageId, mohon);
        }

        return reportName;
    }

    private String getReportNameRMHS1(String stageId, Permohonan mohon) {
        String report = null;
        String kodNegeri = conf.getProperty("kodNegeri");
         if (kodNegeri.equals("04")) {
        if (stageId.equals("keptusuan")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                report = "STRSLulusPindaPermohonan_MLK.rdf";
            } else {
                report = "STRSTolakPindaPermohonan_MLK.rdf";
            }
        }
         }else{
            if (stageId.equals("keptusuan")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                report = "STRSLulusPindaBorang_NS.rdf";
            } else {
                report = "STRSTolakPindaBorang_NS.rdf";
            }
        }
            
         }
        return report;
    }

    private String getReportNameRMH1A(String stageId, Permohonan mohon) {
        String report = null;
        String kodNegeri = conf.getProperty("kodNegeri");
         if (kodNegeri.equals("04")) {
        if (stageId.equals("keputusan")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                report = "STRSLulusPindaPermohonan_MLK.rdf";
            } else {
                report = "STRSTolakPindaPermohonan_MLK.rdf";
            }
        }
         }else{
            if (stageId.equals("keptusuan")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                report = "STRSLulusPindaBorang_NS.rdf";
            } else {
                report = "STRSTolakPindaBorang_NS.rdf";
            }
            }
         }

        return report;
    }

    public String getReportNamePBBS(String stageId, Permohonan mohon) {
        String report = null;
        if (stageId.equals("g_sediasuratmakluman")) {
            report = "STRSBayaranKpdJUPEM_MLK.rdf";
        }
        if (stageId.equals("sediapermit")) {
            report = "STRB4DPermitRuangUdara_MLK.rdf";
        }
        if (stageId.equals("semakpermit")) {
            report = "STRB4DPermitRuangUdara_MLK.rdf";
        }
        if (stageId.equals("g_sediakertasptg")) {
            report = "STRLaporanTanahMelaka.rdf";
        }
        if (stageId.equals("keputusan")) {
            report = "STRKertasPTG_NS.rdf";
        }

        return report;
    }

    //added 25/06/2012
    public String getReportNamePBBD(String stageId, Permohonan mohon) {
        String report = null;
        if (stageId.equals("g_sediasuratmakluman")) {
            report = "STRSBayaranKpdJUPEM_MLK.rdf";
        }
        if (stageId.equals("sediapermit")) {
            report = "STRB4DPermitRuangUdara_MLK.rdf";
        }
        if (stageId.equals("semakpermit")) {
            report = "STRB4DPermitRuangUdara_MLK.rdf";
        }
        if (stageId.equals("g_sediakertasptg")) {
            report = "STRLaporanTanahMelaka.rdf";
        }
        if (stageId.equals("keputusan")) {
            report = "STRKertasPTG_NS.rdf";
        }

        return report;
    }

    public String getReportNamePWPN(String stageId, Permohonan mohon) {
        String report = null;
        String kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            if (stageId.equals("sediawaran")) {
                report = "STRSLulusWaran_MLK.rdf";
            }
            if (stageId.equals("sediawaranrampasan")) {
                report = "STRB7AWaranPenahanan_MLK.rdf";
            }
            if (stageId.equals("semaklaporan")) {
                report = "STRB7AWaranPenahanan_MLK.rdf";
            }

            if (stageId.equals("perakuan")) {
                report = "STRKertasPertimbanganWaran_MLK.rdf";
            }
            if (stageId.equals("keputusan")) {
                report = "STRKertasPertimbanganWaran_MLK.rdf";
            }
        } else {
            if (stageId.equals("sediawaran")) {
                report = "STRSLulusWaran_NS.rdf";
            }
            if (stageId.equals("sediawaranrampasan")) {
                report = "STRB7AWaranPenahanan_NS.rdf";
            }
            if (stageId.equals("semakwaran")) {
                report = "STRB7AWaranPenahanan_NS.rdf";
            }
            if (stageId.equals("keputusan")) {
                report = "STRLaporanTanahWaran_NS.rdf";
            }

        }


        return report;
    }

    public String getReportNamePNSS(String stageId, Permohonan mohon) {
        String report = null;
        String kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {

            if (stageId.equals("sediasurat")) {
                report = "STRSLulusTamatStrataJUPEM_MLK.rdf";
            }
        } else {
            if (stageId.equals("sediasurat")) {
                report = "STRSLulusTamatStrataJUPEM_NS.rdf";
            }
        }
        return report;
    }

    public String getReportNamePPRUS(String stageId, Permohonan mohon) {
        String report = null;
//        stageId = "sediasuratmakluman";

        if (stageId.equals("sediapermit")) {
            report = "STRB4DPermitRuangUdara_MLK.rdf";
        }
        if (stageId.equals("semakpermit")) {
            report = "STRB4DPermitRuangUdara_MLK.rdf";
        }
        if (stageId.equals("semaklaporan")) {
            report = "STRLaporanTanahPermitRuangUdara_MLK.rdf";
        }

        if (stageId.equals("sediasuratmakluman")) {
            if (strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                report = "STRSBayaranPermitRuangUdara_MLK.rdf";
            } else {
                report = "STRSTolakPermitRuangUdara_MLK.rdf";
            }
        }
        return report;
    }

    public String getReportNamePNB(String stageId, Permohonan mohon) {
        String report = null;
        String kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            if (stageId.equals("sediasuratlulus")) {
                if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                    report = "STRSLulusTarikBalik_MLK.rdf";
                } else {
                    report = "STRSTolakTarikBalik_MLK.rdf";
                }
            }
            if (stageId.equals("sediasurattolak")) {
                report = "STRLaporanTarikBalik_MLK.rdf";
            }
            if (stageId.equals("keputusan")) {
                report = "STRLaporanTarikBalik_MLK.rdf";
            }
            if (stageId.equals("semaklaporan")) {
                report = "STRLaporanTanah_MLK.rdf";
            }
        } else {
            if (stageId.equals("sediasurat")) {
                if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                    report = "STRSLulusTarikBalik_NS.rdf";
                } else {
                    report = "STRSTolakTarikBalik_NS.rdf";
                }
            }
            if (stageId.equals("keputusan")) {
                report = "STRLaporanTarikBalik_NS.rdf";
            }
            if (stageId.equals("semaklaporan")) {
                report = "STRLaporanTanah_NS.rdf";
            }
        }
        return report;
    }

    private String getReportNameRTPS(String stageId, Permohonan mohon) {
        String report = null;
        String kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            if (stageId.equals("sediasurat")) {
                if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                    report = "STRSLulusRayuanPindahMilik_MLK.rdf";
                } else {
                    report = "STRSTolakRayuanPindahMilik_MLK.rdf";
                }
            }
            if (stageId.equals("semaklaporan")) {
                report = "STRLaporanTanahMelaka.rdf";
            }
            if (stageId.equals("perakuan") || stageId.equals("keputusan")) {
                report = "STRDrafRTPS_MLK.rdf";
            }
        }
        return report;
    }

    private String getReportNameRTHS(String stageId, Permohonan mohon) {
        String report = null;
        String kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            if (stageId.equals("sediasurat")) {
                if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                    report = "STRSLulusRayuanHMStrata_MLK.rdf";
                } else {
                    report = "STRSTolakRayuanHMStrata_MLK.rdf";
                }
            }
            if (stageId.equals("semaklaporan")) {
                report = "STRLaporanTanahMelaka.rdf";
            }
            if (stageId.equals("perakuan") || stageId.equals("keputusan")) {
                report = "STRDrafRTHS_MLK.rdf";
            }
        } else {
            if (stageId.equals("sediasurat")) {
                if (strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan").getKeputusan().getKod().equals("L")) {
                    report = "STRSLulusRayuanHMStrata_NS.rdf";
                } else {
                    report = "STRSTolakRayuanHMStrata_NS.rdf";
                }
            }
            if (stageId.equals("semaklaporan")) {
                report = "STRLaporanTanahMelaka.rdf";
            }
            if (stageId.equals("perakuan") || stageId.equals("keputusan")) {
                report = "STRDrafRTHS_NS.rdf";
            }
        }
        return report;
    }

    private String getReportNameP8(String stageId, Permohonan mohon) {
        String report = null;
        if (stageId.equals("semaklaporan")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "semaklaporan").getKeputusan().getKod().equals("AK")) {
                report = "STRLantiKIO_MLK.rdf";
            } else {
                report = "STRSTolakPenguatkuasaan_MLK.rdf";
            }
        }
        if (stageId.equals("signsuratlantikan")) {
            if (strService.findMohonFasa(mohon.getIdPermohonan(), "signsuratlantikan").getKeputusan().getKod().equals("AK")) {
                report = "STRLantiKIO_MLK.rdf";
            } else {
                report = "STRSTolakPenguatkuasaan_MLK.rdf";
            }
        }

        return report;
    }

    public Resolution printReport() {
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        srn.setPermohonan(permohonan);
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        if (permohonan.getKodUrusan().getKod().equals("PBBS")) {
            dokumen = srn.urusanPBBS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS1")) {
            dokumen = srn.urusanRMHS1(permohonan, stageId);
        }

        //added 25/06/2012
        if (permohonan.getKodUrusan().getKod().equals("PBBD")) {
            dokumen = srn.urusanPBBD(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMH1A")) {
            dokumen = srn.urusanRMH1A(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PPRUS")) {
            dokumen = srn.urusanPPRUS(permohonan, stageId);
        }

        if (permohonan.getKodUrusan().getKod().equals("PNB")) {
            dokumen = srn.urusanPNB(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("PWPN")) {
            dokumen = srn.urusanPWPN(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("RTPS")) {
            dokumen = srn.urusanRTPS(permohonan, stageId);
        }
        if (permohonan.getKodUrusan().getKod().equals("P8")) {
            dokumen = srn.urusanP8(permohonan, stageId);
        }
        LOG.info("Dokumen : " + dokumen);

        if (dokumen == null) {
//            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        } else {
            Dokumen d = dokumenDAO.findById(dokumen.getIdDokumen());
            idDokumen = String.valueOf(d.getIdDokumen());
        }
//        String docPath = conf.getProperty("document.path");
//        String fn = d.getNamaFizikal();
//        LOG.info("fn ::" + fn);
//        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
//        LOG.info("new FileInputStream(f) : " + f.getPath());
//        if (f != null) {
//            try {
//                return new StreamingResolution(d.getFormat(), new FileInputStream(f));
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        }
        return new JSP("strata/common/paparan_jana.jsp").addParameter("tab", "true");
    }

    public Resolution pelanJupem() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "g_terimapelan");
        if(mohonFasa!=null){
        ulasanJupem = mohonFasa.getUlasan();
        }
        return new JSP("strata/common/terima_pelan_jupem.jsp").addParameter("tab", "true");
    }

    public Resolution simpanUlasan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "g_terimapelan");
        mohonFasa.setUlasan(ulasanJupem);
        mohonFasa = strService.saveFasaMohon(mohonFasa);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("strata/common/terima_pelan_jupem.jsp").addParameter("tab", "true");
    }

    public Resolution suratLulusTolak() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        FasaPermohonan mohonFasa = new FasaPermohonan();
        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
        if ((mohonFasa != null) && (mohonFasa.getKeputusan().getKod().equals("L"))) {
            ayat = "Permohonan ini telah diluluskan. ";
        } else {
            ayat = "Permohonan ini telah ditolak";
        }
        return new JSP("strata/common/suratLulusTolak.jsp").addParameter("tab", true);
    }
    
    public Resolution suratLulusTolak1() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        FasaPermohonan mohonFasa = new FasaPermohonan();
        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
        if ((mohonFasa != null) && (mohonFasa.getKeputusan().getKod().equals("L"))) {
            getContext().getRequest().setAttribute("jana", Boolean.FALSE);
        }
        return new JSP("strata/common/suratLulusTolak.jsp").addParameter("tab", true);
    }

    public Resolution laporanTanahRuangUdara() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("P8")||permohonan.getKodUrusan().getKod().equals("PNB")||
               permohonan.getKodUrusan().getKod().equals("P22B") ||permohonan.getKodUrusan().getKod().equals("P14A")||
                permohonan.getKodUrusan().getKod().equals("P22A")||permohonan.getKodUrusan().getKod().equals("P40A")) {
            
            getContext().getRequest().setAttribute("disablePapar", Boolean.TRUE);
        }else{
            getContext().getRequest().setAttribute("disablePapar", Boolean.FALSE);
        }
        
        String kodNegeri = conf.getProperty("kodNegeri");
        if(kodNegeri.equals("04") && permohonan.getKodUrusan().getKod().equals("PPRUS")){
            FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "g_sedialaporan");
            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan() != null) {
                    syor = mohonFasa.getKeputusan().getKod();
                }
            }
        }
        
        
        return new JSP("strata/Ruang_Udara/laporanTanahRuangUdara.jsp").addParameter("tab", "true");
    }
    
        public Resolution SemakSurat() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("P8")||permohonan.getKodUrusan().getKod().equals("PNB")||
               permohonan.getKodUrusan().getKod().equals("P22B") ||permohonan.getKodUrusan().getKod().equals("P14A")||
                permohonan.getKodUrusan().getKod().equals("P22A")||permohonan.getKodUrusan().getKod().equals("P40A")) {
            
            getContext().getRequest().setAttribute("disablePapar", Boolean.TRUE);
        }else{
            getContext().getRequest().setAttribute("disablePapar", Boolean.FALSE);
        }
        return new JSP("strata/Ruang_Udara/Semak_Surat.jsp").addParameter("tab", "true");
    }

    public Resolution resetForm(){
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("----------Reset Form----------::");
        ulasanJupem = "";

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "g_terimapelanjupem");
        LOG.debug("------mohonFasa-------::"+mohonFasa);
        if(mohonFasa.getUlasan() !=null){
            LOG.debug("------mohonFasa.getUlasan()-------::"+mohonFasa.getUlasan());
        mohonFasa.setUlasan("");
        mohonFasa.setInfoAudit(infoAudit);
        mohonFasa = strService.saveFasaMohon(mohonFasa);
        }
        LOG.debug("------form reset successfull----page refresh----------::");
        addSimpleMessage("Maklumat Berjaya Di Hapuskan");
        return new RedirectResolution(pageForViewJana.class, "pelanJupem");
    }

    public String getIframeURL() {
        return iframeURL;
    }

    public void setIframeURL(String iframeURL) {
        this.iframeURL = iframeURL;
    }

    public String getLala() {
        return lala;
    }

    public void setLala(String lala) {
        this.lala = lala;
    }

    public boolean getJana() {
        return jana;
    }

    public void setJana(boolean jana) {
        this.jana = jana;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getUlasanJupem() {
        return ulasanJupem;
    }

    public void setUlasanJupem(String ulasanJupem) {
        this.ulasanJupem = ulasanJupem;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }
    
    
}

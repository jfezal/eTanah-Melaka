/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodHakmilik;
import etanah.model.KodUrusan;
import etanah.service.EnforceService;
import etanah.service.PelupusanService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import etanah.view.ListUtil;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author zabedah.zainal
 */
@UrlBinding("/pelupusan/utilitiReportNs")
public class UtilitiReportNSActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiReportNSActionBean.class);
    @Inject
    PelupusanService pelupusanService;
    @Inject
    EnforceService enforceService;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    ListUtil listUtil;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String idPermohonan;
    private String cawKod;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private KodUrusan kodUrusan;
    private List<String> listYear = new ArrayList<String>();
    private List<KodHakmilik> listHakmilik;
    private List<KodBandarPekanMukim> listBPMByKodDaerah;
    private List<KodCawangan> listKodCaw;
    private List<KodUrusan> listKodUrusan;
    private List<KodCawangan> listKodCawByPengguna;
    private List<Pengguna> listPguna;
    private List<KodBandarPekanMukim> listKodBpm;
    private List<KodBandarPekanMukim> senaraiKodBPM;
    private static String kodNegeri = null;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/utiliti_report_ns.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws NamingException {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (pengguna.getKodCawangan().getDaerah() != null) {
            if (!pengguna.getKodCawangan().getDaerah().getKod().equals("00")) {
                if(pengguna.getKodCawangan().getDaerah().getKod().equals("08")){ //Gemas
                    listBPMByKodDaerah = listUtil.getSenaraiKodBPMByDaerahAndCawangan("06", pengguna.getKodCawangan().getKod());
                }else{
                    listBPMByKodDaerah = listUtil.getSenaraiKodBPMByDaerahAndCawangan(pengguna.getKodCawangan().getDaerah().getKod(), pengguna.getKodCawangan().getKod());
                }
                
                listKodCawByPengguna = pelupusanService.getSenaraiKodCaw(pengguna.getKodCawangan().getDaerah().getKod());

                LOG.info("::: size list bpm by kod daerah: " + listBPMByKodDaerah.size());
            } else {
                KodCawangan kc = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
                listKodBpm = pelupusanService.findKodBPM();
                listKodCaw = pelupusanService.getKodCawangan();

                LOG.info("::: size list bpm: " + listKodBpm.size());
                LOG.info("::: size list kod cawangan : ..." + listKodCaw.size());
            }

        } else {
            KodCawangan kc = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
            listKodBpm = pelupusanService.findKodBPM();
            listKodCaw = pelupusanService.getKodCawangan();

            LOG.info("::: size list bpm: " + listKodBpm.size());
            LOG.info("::: size list kod cawangan : ..." + listKodCaw.size());
        }

        ArrayList kodHakmilik = new ArrayList<String>();
        if (pengguna.getKodCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
            kodHakmilik.add("GRN"); // Geran
            kodHakmilik.add("PN"); // Pajakan Negeri
            kodHakmilik.add("HSD"); //Hakmilik Sementara Daftar
        } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
            kodHakmilik.add("PM"); // Pajakan Mukim
            kodHakmilik.add("GM"); //Geran Mukim
            kodHakmilik.add("HSM"); //Hakmilik Sementara(Mukim)
        }

        listHakmilik = pelupusanService.findKodHakmilikByKodCaw(kodHakmilik);

        listKodUrusan = pelupusanService.getKodUrusan();
        LOG.info("::: size list kod urusan :" + listKodUrusan.size());

//        }
    }

    private void showReports() {
        LOG.info("showReport:start");

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
            //melaka
//            senaraiReport = new String[]{
//                "Laporan Permohonan MCL",
//                "REKOD PERMIT BAHAN BATUAN KELULUSAN KETUA MENTERI",
//                "REKOD PERMIT BAHAN BATUAN KELULUSAN PTG",
//                "LAPORAN PERMOHONAN MERIZAB",
//                "REKOD PERMIT CARIGALI",
//                "REKOD MEMPERBAHARUI PERMIT CARIGALI",
//                "REKOD PAJAKAN MELOMBONG",
//                "REKOD MEMPERBAHARUI PAJAKAN MELOMBONG",
//                "LAPORAN STATISTIK PERMOHONAN URUSAN PELUPUSAN",
//                "LAPORAN PERMOHONAN URUSAN PELUPUSAN"
//            };
            //            senaraiReportName = new String[]{
//                "UTL_MMMCL_ML.rdf", "UTL_RekodPermitKM_ML.rdf", "UTL_RekodPermitPTG_ML.rdf", "UTL_PRIZ_ML.rdf", "UTL_RekodPermitCG_ML.rdf", "UTL_RekodBRPermitCG_ML.rdf", "UTL_RekodPajakLombong_ML.rdf", "UTL_RekodBRPajakLombong_ML.rdf", "UTL_LURUSAN_NS.rdf", "REP_PERMOHONAN.rdf"
//            };
            senaraiReport = new String[]{
                "LAPORAN STATISTIK PERMOHONAN URUSAN PELUPUSAN",
                "LAPORAN PERMOHONAN URUSAN PELUPUSAN",
                "LAPORAN KEMAJUAN PERMOHONAN PEMBANGUNAN TANAH",
                "LAPORAN PEMBAYARAN PREMIUM(5A) PEMBANGUNAN TANAH"
            };

            senaraiReportName = new String[]{
                "UTL_LURUSAN_NS.rdf", "REP_PERMOHONAN.rdf", "UTL_LKEMAJUAN_DIS.rdf", "UTL_LPREMIUM_DIS.rdf"
            };
        }

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeri";
            //negeri
//            senaraiReport = new String[]{
//                "Laporan Tanah",
//                //                        "Laporan Permohonan MCL",
//                "REKOD PERMIT BAHAN BATUAN (PASIR DAN GRANIT DIATAS TANAH KERAJAAN)",
//                "REKOD PERMIT BAHAN BATUAN (PASIR DAN GRANIT DIATAS TANAH MILIK)",
//                "LAPORAN PERMOHONAN MERIZAB",
//                "REKOD PAJAKAN MELOMBONG",
//                "REKOD MEMPERBAHARUI PAJAKAN MELOMBONG",
//                "LAPORAN STATISTIK PERMOHONAN URUSAN PELUPUSAN",
//                "LAPORAN PERMOHONAN URUSAN PELUPUSAN"
//            };
//
//            senaraiReportName = new String[]{
//                "DISLTSITE_NS.rdf", "UTL_RekodPermitKerajaan_NS.rdf", "UTL_RekodPermitTMilik_NS.rdf", "UTL_PRIZ_NS.rdf", "UTL_RekodPajakLombong_NS.rdf", "UTL_RekodBRPajakLombong_NS.rdf", "UTL_LURUSAN_NS.rdf", "REP_PERMOHONAN.rdf"
//            };

            senaraiReport = new String[]{
                "LAPORAN TANAH",
                "LAPORAN STATISTIK PERMOHONAN URUSAN PELUPUSAN",
                "LAPORAN PERMOHONAN URUSAN PELUPUSAN",
                "LAPORAN KEMAJUAN PERMOHONAN PEMBANGUNAN TANAH",
                "LAPORAN PEMBAYARAN PREMIUM(5A) PEMBANGUNAN TANAH",
                "LAPORAN PERMOHONAN TAMBAH, PINDA ATAU BATAL JADUAL KEDUA DAN JADUAL KETIGA RIZAB MELAYU",
                "LAPORAN RA"    
            };

            senaraiReportName = new String[]{
                "DISLTSITE_NS.rdf", "UTL_LURUSAN_NS.rdf", "REP_PERMOHONAN.rdf", "UTL_LKEMAJUAN_DIS.rdf", "UTL_LPREMIUM_DIS.rdf", "DISLaporanMohonJMRE_NS.rdf" , "DIS_LAPORAN_RA.rdf"
            };
        }

        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new JSP("/pelupusan/utiliti/utiliti_report_param_ns.jsp").addParameter("popup", "true");
    }

    public Resolution masuk() {
        String kodCaw = getContext().getRequest().getParameter("kodCawangan");
        if (kodCaw != null) {
            cawKod = kodCaw;
        }
        String namaReport = getContext().getRequest().getParameter("reportNama");
        if (namaReport != null) {
            reportName = namaReport;
        }

        if (cawKod != null) {
            enforceService.findKodCawangan(cawKod);
            LOG.info("kodCaw :" + cawKod);
        }
        return new JSP("/pelupusan/utiliti/utiliti_report_param_ns.jsp").addParameter("popup", "true");
    }

    public List<String> getListYear() {
        //calendar for year
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        listYear.add(String.valueOf(year));
        for (int i = 0; i < 10; i++) {
            year--;
            listYear.add(String.valueOf(year));
        }
        return listYear;
    }

    public Resolution simpan() throws Exception {
        LOG.info("------------simpan--------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();

        int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
        System.out.println("row count :" + rowCount);

        for (int i = 0; i < rowCount; i++) {

            String idUrusan = getContext().getRequest().getParameter("idUrusan" + i);
            if (idUrusan != null) {
                System.out.println("id urusan : " + idUrusan + "[" + i + "]");

                kodUrusan = pelupusanService.findExistingRecord(idUrusan);
                if (kodUrusan == null) {
                    System.out.println("kod urusan null");
                    kodUrusan = new KodUrusan();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                } else {
                    System.out.println("kod urusan tak null");
                    ia = kodUrusan.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                }

                kodUrusan.setInfoAudit(ia);
                pelupusanService.simpanKodUrusan(kodUrusan);
            } else {
                System.out.println("idUrusan null, nothing to save.....");
            }
        }

        addSimpleMessage("Maklumat berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/utiliti/utiliti_report_param_ns.jsp");
    }

    public Resolution senaraiKodBPM() {
        String kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
        if (StringUtils.isNotBlank(kodDaerah)) {
            if(kodDaerah.equals("08")){
              senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerahAndCawangan("06",kodDaerah);  
            }else{
              senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerah(kodDaerah);  
            }
            
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/partial_report_kodBPM.jsp").addParameter("popup", "true");
    }

    public String[] getSenaraiReport() {
        return senaraiReport;
    }

    public void setSenaraiReport(String[] senaraiReport) {
        this.senaraiReport = senaraiReport;
    }

    public String[] getSenaraiReportName() {
        return senaraiReportName;
    }

    public void setSenaraiReportName(String[] senaraiReportName) {
        this.senaraiReportName = senaraiReportName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
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

    public String getCawKod() {
        return cawKod;
    }

    public void setCawKod(String cawKod) {
        this.cawKod = cawKod;
    }

    public List<KodBandarPekanMukim> getListBPMByKodDaerah() {
        return listBPMByKodDaerah;
    }

    public void setListBPMByKodDaerah(List<KodBandarPekanMukim> listBPMByKodDaerah) {
        this.listBPMByKodDaerah = listBPMByKodDaerah;
    }

    public List<KodHakmilik> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<KodHakmilik> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public List<KodUrusan> getListKodUrusan() {
        return listKodUrusan;
    }

    public void setListKodUrusan(List<KodUrusan> listKodUrusan) {
        this.listKodUrusan = listKodUrusan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<KodCawangan> getListKodCaw() {
        return listKodCaw;
    }

    public void setListKodCaw(List<KodCawangan> listKodCaw) {
        this.listKodCaw = listKodCaw;
    }

    public List<KodCawangan> getListKodCawByPengguna() {
        return listKodCawByPengguna;
    }

    public void setListKodCawByPengguna(List<KodCawangan> listKodCawByPengguna) {
        this.listKodCawByPengguna = listKodCawByPengguna;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Pengguna> getListPguna() {
        return listPguna;
    }

    public void setListPguna(List<Pengguna> listPguna) {
        this.listPguna = listPguna;
    }

    public List<KodBandarPekanMukim> getListKodBpm() {
        return listKodBpm;
    }

    public void setListKodBpm(List<KodBandarPekanMukim> listKodBpm) {
        this.listKodBpm = listKodBpm;
    }

//    public static String getKodNegeri() {
//        return kodNegeri;
//    }
//
//    public static void setKodNegeri(String kodNegeri) {
//        UtilitiReportNSActionBean.kodNegeri = kodNegeri;
//    }
    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

}

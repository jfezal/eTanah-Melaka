/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.AduanService;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormatSymbols;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/laporanpenguatkuasaanMLK")
public class LaporanPenguatkuasaanMLKActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LaporanPenguatkuasaanMLKActionBean.class);
    @Inject
    EnforceService enforceService;
    @Inject
    private AduanService aduanService;
    @Inject
    etanah.Configuration conf;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String idPermohonan;
    private String cawKod;
    private Permohonan permohonan;
    private List<String> listYear = new ArrayList<String>();
    private List<String> listMonth = new ArrayList<String>();
    private String kodNegeri;
    private List<KodUrusan> senaraiUrusan;
    private Pengguna pengguna;
    private String kodKeputusan;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_penguatkuasaan_MLK.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        kodNegeri = conf.getProperty("kodNegeri");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawKod = pengguna.getKodCawangan().getKod();

        if (kodNegeri.equalsIgnoreCase("04")) {
            senaraiUrusan = aduanService.getSenaraiUrusanEnfMLK();
        } else {
            senaraiUrusan = aduanService.getSenaraiUrusan();
        }

//        getListMonth();
    }

    private void showReports() {
        LOG.info("showReport:start");
        senaraiReport = new String[]{
            //            "Diari Siasatan",
            //            "Notis Kesalahan",
            //            "Laporan Penyiasatan",
            //            "Laporan Tanah",
            //            "Statistik Kemajuan Penguatkuasaan",
            //            "Laporan Tanah Kosong",
            "Aduan Mengikut Daerah",
            "Aduan Mengikut KTN",
            "Aduan Mengikut Harian",
            "Aduan Mengikut Mingguan",
            "Aduan Mengikut Bulanan",
            "Aduan Mengikut Tahunan",
            "Aduan Selesai",
            "Aduan Belum Selesai",
            "Kompaun Tahunan",
            "Statistik Kompaun",
            "Statistik Kemajuan Penguatkuasaan Belum Selesai",
            "Statistik Kemajuan Penguatkuasaan Selesai",};

        senaraiReportName = new String[]{
            //            "ENFDS_MLK.rdf",
            //            "ENFNK_MLK.rdf",
            //            "ENFLPOP_MLK.rdf",
            //            "ENFLTNH_MLK.rdf",
            //            "ENFSTMAJU_MLK.rdf",
            //            "ENFLTNH_KOSONG.rdf",
            "ENFAduanDaerah_MLK.rdf",
            "ENFAduanKtn_MLK.rdf",
            "ENFAduanHarian_MLK.rdf",
            "ENFAduanMingguan_MLK.rdf",
            "ENFAduanBulanan_MLK.rdf",
            "ENFAduanTahunan_MLK.rdf",
            "ENFAduanSelesai_MLK.rdf",
            "ENFAduanTakSelesai_MLK.rdf",
            "ENFKompaunTahunan_MLK.rdf",
            "ENFStatKompaun_MLK.rdf",
            "ENFStatTakSelesai_MLK.rdf",
            "ENFStatSelesai_MLK.rdf"
        };

        LOG.info(
                "showReport:finish");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
//        masuk();
        return new JSP("/penguatkuasaan/laporan_penguatkuasaan_param_MLK.jsp").addParameter("popup", "true");
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
        return new JSP("/penguatkuasaan/laporan_penguatkuasaan_param_MLK.jsp").addParameter("popup", "true");
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

    public List<String> getListMonth() {
//        //calendar for year
//        Calendar cal = Calendar.getInstance();
//        int month = cal.get(Calendar.MONTH);
////        for (int i = 0; i < 10; i++) {
////            year--;
////            listYear.add(String.valueOf(year));
////        }

        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < 12; i++) {
            String month = months[i];
            System.out.println("month = " + month);
            listMonth.add(months[i]);
        }

        System.out.println("list month : " + listMonth.size());
        return listMonth;
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

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(String kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }
}

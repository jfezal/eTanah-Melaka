/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop, nur.aqilah
 */
@UrlBinding("/lelong/laporanlelong")
public class LaporanLelongActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LaporanLelongActionBean.class);
    @Inject
    LelongService lelongService;
    private List<String> listYear = new ArrayList<String>();
    private static String staticKodCaw;
    private static String staticKodDaerah;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String kodCaw;
    private String kodDaerah;
    private String idPermohonan;
    private Permohonan permohonan;
    private Pengguna pengguna;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/lelong/laporan_lelong.jsp");
    }

    //added by nur.aqilah
    private void showReports() {
        LOG.info("showReport:start");
        senaraiReport = new String[]{
                    "Laporan Bulanan Perintah Jual",
                    "Statistik Permohonan Perintah Jual (16G)",
                    "Laporan Lelongan Awam",
                    "Laporan Permohonan Perintah Jual",
                    "Laporan Komisyen Jualan",
                    "Laporan Pembida Berdaftar",
                    "Laporan Senarai Pelong Berlesen"
        };
        senaraiReportName = new String[]{
                    "LLGLaporanBulananPerintahJual_NS.rdf",
                    "LLG_Stat_01_NS.rdf",
                    "LLGLaporanLelongan_NS.rdf",
                    "LLGLaporanPemohon_NS.rdf",
                    "LLGJumlahKomisyenPTD_NS.rdf",
                    "LLG_stat_pembida_NS.rdf",
                    "LLG_stat_plb_NS.rdf"
        };
        LOG.info("showReport:finish");
    }

    //added by nur.aqilah
    public Resolution masuk() {
        setPengguna((Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER));
        LOG.info("masuk :");
        LOG.info("penguna masuk :" + getPengguna().getKodCawangan().getKod());

        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if (cawKod != null) {
            setKodCaw(cawKod);
            LOG.info("ni dulu:" + cawKod);
            lelongService.findKodCawangan(cawKod);
            LOG.info("cawKod  masuk:" + cawKod);
            LOG.info("kodCaw masuk :" + getKodCaw());
        }
        if (getKodCaw() == null) {
            setKodCaw(getStaticKodCaw());
        }

        String namaReport = getContext().getRequest().getParameter("reportNama");
        if (namaReport != null) {
            reportName = namaReport;
        }

        return new JSP("/lelong/laporan_lelong.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
    public Resolution requestParam() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        reportName = getContext().getRequest().getParameter("namaReport");
        if (getPengguna().getKodCawangan().getDaerah() != null) {
            setKodDaerah(getPengguna().getKodCawangan().getDaerah().getKod());
            kodCaw = (getPengguna().getKodCawangan().getKod());
            LOG.info("(requestParam) kodDaerah :" + getKodDaerah());
            LOG.info("(requestParam) kodCaw :" + getKodCaw());
            setStaticKodDaerah(getKodDaerah());
            staticKodCaw = kodCaw;
            masuk();
        }

        return new JSP("/lelong/laporan_lelong_param.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
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

//    private void showReports() {
//        LOG.info("showReport:start");
//        senaraiReport = new String[]{
//            "Borang 16H",
//            "Borang 16I",
//            "Borang 16P",
//            "Perisytiharan Jualan",
//            //            "Surat Permohonan Jualan",
//            "Surat Siasatan",
//            "Borang 2A",
//            "Kontrak Jualan",
//            "Surat Bayaran Baki",};
//
//        senaraiReportName = new String[]{
//            "LLGBorang16HPenyerah_NS.rdf",
//            "LLGBorang16I_NS.rdf",
//            "LLGBorang16P_NS.rdf",
//            "LLGIsytiharJual_NS.rdf",
//            //            "LLGSrtPermohonanJual_NS.rdf",
//            "LLGSuratSiasatan_NS.rdf",
//            "LLGBorang2A_NS.rdf",
//            "LLGKontrakJualan_NS.rdf",
//            "LLGSuratBayarBaki_NS.rdf",};
//        LOG.info("showReport:finish");
//    }
//    public Resolution requestParam() {
//        reportName = getContext().getRequest().getParameter("namaReport");
//        return new JSP("/lelong/laporan_lelong_param.jsp").addParameter("popup", "true");
//    }
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public static String getStaticKodCaw() {
        return staticKodCaw;
    }

    public static void setStaticKodCaw(String aStaticKodCaw) {
        staticKodCaw = aStaticKodCaw;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public static String getStaticKodDaerah() {
        return staticKodDaerah;
    }

    public static void setStaticKodDaerah(String aStaticKodDaerah) {
        staticKodDaerah = aStaticKodDaerah;
    }

    public void setListYear(List<String> listYear) {
        this.listYear = listYear;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import org.apache.commons.lang.StringUtils;
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
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/laporanpembangunanMLK")
public class LaporanPembangunanMLKActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(LaporanPembangunanMLKActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String idPermohonan;
    private Permohonan permohonan;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/dev_laporan_MLK.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");
        String DiffReport = null;
         senaraiReport = new String[]{
                    "Surat Ulasan Adun",
                    "Surat Ulasan Jabatan Teknikal",
                    "Laporan Tanah",
                    "Rencana JKBB (PTD)",
                    "Rencana JKBB (PTG)",
                    "Ringkasan Permohonan",
                    "Surat Keputusan JKBB (Lulus)",
                    "Surat Keputusan JKBB (Tolak)",
                    "Surat Minta Pelan Prahitung",
                    "Surat Nilaian Tanah JPPH",
                    "Surat Lampiran A",
                    "Rencana Pertimbangan PTG",
                    "Surat Notis 7G",
                    "Borang 7C_TSPSS",
                    "Surat Peringatan",
                    "Surat Batal",
                    "Borang PU",
                    "Surat Iringan PU",
                    "Surat Minta Dokumen dan Bayaran Pelan",
                    "Lampiran A",
                    "Notis 7G",
                    "Surat Notis 7G",
                    "Surat Tolak"};

        senaraiReportName = new String[]{
                    "DEVSMUYB_MLK.rdf",
                    "DEVSUT_MLK.rdf",
                    "DEVLT_MLK.rdf",
                    "DEVRJKBBPTD_MLK.rdf",
                    "DEVRJKBBPTG_MLK.rdf",
                    "DEVRPJKBBPTG_MLK.rdf",
                    "DEVSKpsnJKBB_MLK.rdf",
                    "DEVST_MLK.rdf",
                    "DEVSrtLPra_MLK.rdf",
                    "DEVSJPPH2_MLK.rdf",
                    "DEVSLA_MLK.rdf",
                    "DEVRPtbgnPTG_MLK.rdf",
                    "DEVSN7G_MLK.rdf",
                    "DEVB7C_TSPSS_MLK.rdf",
                    "DEVSP_MLK.rdf",
                    "DEVSB_MLK.rdf",
                    "DEVBPU_MLK.rdf",
                    "DEVSIPU_MLK.rdf",
                    "DEVSMD_MLK.rdf",
                    "DEVLA_MLK.rdf",
                    "DEVN7G_MLK.rdf",
                    "DEVS7G_MLK.rdf",
                    "DEVSuratRayuanTolak_MLK.rdf",
                    "",
                    "",
                    "",
                    "",};
        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
        return new JSP("/pembangunan/melaka/dev_laporan_param_MLK.jsp").addParameter("popup", "true");
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
}

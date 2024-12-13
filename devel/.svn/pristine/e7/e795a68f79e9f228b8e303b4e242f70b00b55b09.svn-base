/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.view.etanahActionBeanContext;
import etanah.dao.PenggunaDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/laporanlelongMLK")
public class LaporanLelongMLKActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LaporanLelongMLKActionBean.class);

    /**
     * @return the staticKodDaerah
     */
    public static String getStaticKodDaerah() {
        return staticKodDaerah;
    }

    /**
     * @param aStaticKodDaerah the staticKodDaerah to set
     */
    public static void setStaticKodDaerah(String aStaticKodDaerah) {
        staticKodDaerah = aStaticKodDaerah;
    }

    /**
     * @return the staticKodCaw
     */
    public static String getStaticKodCaw() {
        return staticKodCaw;
    }

    /**
     * @param aStaticKodCaw the staticKodCaw to set
     */
    public static void setStaticKodCaw(String aStaticKodCaw) {
        staticKodCaw = aStaticKodCaw;
    }
    @Inject
    LelongService lelongService;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String idPermohonan;
//    private String cawKod;
    private String kodCaw;
    private String kodDaerah;
    private Permohonan permohonan;
    private List<String> listYear = new ArrayList<String>();
    private Pengguna pengguna;
    private static String staticKodDaerah;
    private static String staticKodCaw;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/lelong/laporan_lelong_MLK.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");
        senaraiReport = new String[]{
                    //                    "Dokumen Siasatan",
                    //                    "Borang 16H",
                    //                    "Borang 16H Warta",
                    //                    "Bayaran Baki",
                    //                    "Surat Siasatan",
                    //                    "Borang 16P",
                    //                    "Surat Rujukan Mahkamah",
                    //                    "Surat Peringatan",
                    //                    "Surat Tolak Permohonan (Tidak Hadir Siasatan)",
                    //                    "Surat Penangguhan Lelongan",
                    //                    "Kontrak Jualan",
                    //                    "Memorandum Jualan",
                    //                    "Surat Tangguh Siasatan",
                    //                    "Borang 16Q",
                    //                    "Borang 16I",
                    //                    "Borang 16O",
                    //                    "Borang 2B",
                    //                    "Surat Akuan Bersumpah Penolong Pegawai Tanah",
                    //                    "Surat Akuan Berkanun Penghantar Notis",
                    //                    "Bukti Penyampaian Notis (Am 51 - Pin 9/80)",
                    //                    "Notis Gantian",
                    //                    "Surat Lantikan Jurulelong",
                    //                    "Surat Pemberitahuan Lelongan",
                    //                    "Surat Warta Kerajaan bagi Perintah Jual",
                    //                    "Surat Permohonan Jualan",
                    //                    "Surat Lucut Hak",
                    //                    "Surat Bayar Baki",
                    //                    "Surat Memo Perjanjian Jual Beli",
                    //                    "Surat Isytihar Jual",
                    //                    "Surat Makluman 16I",
                    //                    "Surat Borang16H Penyerah",
                    //                    "Surat Sijil Rujuk",
                    //                    "Surat Tolak Permohonan",
                    //            "Dokumen Siasatan",
                    //            "Surat Bayaran Baki",
                    //            "Surat Akuan Bersumpah Penolong Pegawai Tanah",
                    //            "Surat Akuan Bersumpah Penghantar Notis",
                    //            "Borang AmPin",
                    "Laporan Bulanan Perintah Jual",
                    "Statistik Permohonan Perintah Jual (16G)",
                    //            "Prestasi Lelongan Awam Bagi Tahun .......",
                    "Statistik Kadar Bayaran Perintah Jual (16H)",
                    "Laporan Komisyen Jualan",
                    "Laporan Senarai Pelelong Berlesen",
                    //            "Laporan Kes Dibawah Pelelong Berlesen",

                    //            "Laporan Hakmilik Di Bawah Pelelong Berlesen",
                    //            "Laporan Senarai Hitam Pembida",
                    "Laporan Senarai Lelongan Untuk Pelelong Berlesen",
                    "Laporan Pembida Berdaftar",
                    "Laporan Lelongan Awam",
                    "Laporan Permohonan Perintah Jual"
                };

        senaraiReportName = new String[]{
                    //                    "LLGDokSiasatan_MLK.rdf",
                    //                    "LLGBorang16HPenyerah_MLK.rdf",
                    //                    "LLGBorang16H_MLK.rdf",
                    //                    "LLGSuratBayarBaki_MLK.rdf",
                    //                    "LLGSuratSiasatan_MLK.rdf",
                    //                    "LLGBorang16P_MLK.rdf",
                    //                    "LLGSijilRujuk_MLK.rdf",
                    //                    "",
                    //                    "LLGSuratTolakPPGTidakHadirSiasatan_MLK.rdf",
                    //                    "LLGTerimaTangguh_MLK.rdf",
                    //                    "LLGIsytiharJual.rdf",
                    //                    "LLGKontrakJualan_MLK.rdf",
                    //                    "LLGSuratKelulusanTangguhSiasatan_MLK.rdf",
                    //                    "LLGBorang16Q_MLK.rdf",
                    //                    "LLGBorang16I_MLK.rdf",
                    //                    "LLGBorang16O_MLK.rdf",
                    //                    "LLGBorang2B_MLK.rdf",
                    //                    "LLGSrtAkuanBersumpahPPT_MLK.rdf",
                    //                    "LLGPhtrNotis_MLK.rdf",
                    //                    "LLGBorangAmPin_MLK.rdf",
                    //                    "LLGNotisGantian_MLK.rdf",
                    //                    "LLGSrtLantikanJurulelong_MLK.rdf",
                    //                    "LLGPemberitahuanLelongan_MLK.rdf",
                    //                    "LLGSuratWartaPerintahJual_MLK.rdf",
                    //                    "LLGPermohonanJual_MLK.rdf",
                    //                    "LLGSuratLucuthak_MLK.rdf",
                    //                    "LLGSuratBayarBaki_MLK.rdf",
                    //                    "LLGMemoPerjanjianJualBeli_MLK.rdf",
                    //                    "LLGIsytiharJual_MLK.rdf",
                    //                    "LLGSuratMakluman16I_MLK.rdf",
                    //                    "LLGBorang16Penyerah_MLK.rdf",
                    //                    "LLGSijilRujuk_MLK.rdf",
                    //                    "LLGSuratTolakPermohonan_MLK.rdf",
                    //            "LLGDokSiasatan_MLK.rdf",
                    //            "LLGSuratBayarBaki_MLK.rdf",
                    //            "LLGSrtAkuanBersumpahPPT_MLK.rdf",
                    //            "LLGPhtrNotis_MLK.rdf",
                    //            "LLGBorangAmPin_MLK.rdf",
                    "LLGLaporanBulananPerintahJual_MLK.rdf",
                    "LLG_Stat_01.rdf",
                    //            "LLG_Stat_03.rdf",
                    "LLG_Stat_02.rdf",
                    "LLGJumlahKomisyenPTD_MLK.rdf",
                    "LLG_stat_plb_MLK.rdf",
                    //            "stat_kes_plb_MLK.rdf",

                    //            "LLG_stat_hakmilik_plb_MLK.rdf",
                    //            "LLG_stat_senarai_hitam_pembida_MLK.rdf",
                    "LLGSuratKpdPelelong_MLK.rdf",
                    "LLG_stat_pembida_MLK.rdf",
                    "LLGLaporanLelongan_MLK.rdf",
                    "LLGLaporanPemohon_MLK.rdf"
                };
        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        reportName = getContext().getRequest().getParameter("namaReport");
        if (getPengguna().getKodCawangan().getDaerah() != null) {
            kodDaerah = (getPengguna().getKodCawangan().getDaerah().getKod());
            kodCaw = (getPengguna().getKodCawangan().getKod());
            LOG.info("(requestParam) kodDaerah :" + getKodDaerah());
            LOG.info("(requestParam) kodCaw :" + getKodCaw());
            staticKodDaerah = kodDaerah;
            staticKodCaw = kodCaw;
            masuk();
        }

        return new JSP("/lelong/laporan_lelong_param_MLK.jsp").addParameter("popup", "true");
    }

    public Resolution masuk() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("masuk :" );
        LOG.info("penguna masuk :" + pengguna.getKodCawangan().getKod());
      
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if (cawKod != null) {
            kodCaw = cawKod;
              LOG.info("ni dulu:" + cawKod);
            lelongService.findKodCawangan(cawKod);
            LOG.info("cawKod  masuk:" + cawKod);
            LOG.info("kodCaw masuk :" + kodCaw);
        }
      if(kodCaw == null)
            kodCaw = staticKodCaw;

        String namaReport = getContext().getRequest().getParameter("reportNama");
        if (namaReport != null) {
            reportName = namaReport;
        }

        return new JSP("/lelong/laporan_lelong_param_MLK.jsp").addParameter("popup", "true");
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

//    public String getCawKod() {
//        return cawKod;
//    }
//
//    public void setCawKod(String cawKod) {
//        this.cawKod = cawKod;
//    }
    /**
     * @return the kodCaw
     */
    public String getKodCaw() {
        return kodCaw;
    }

    /**
     * @param kodCaw the kodCaw to set
     */
    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    /**
     * @return the kodDaerah
     */
    public String getKodDaerah() {
        return kodDaerah;
    }

    /**
     * @param kodDaerah the kodDaerah to set
     */
    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    /**
     * @return the pengguna
     */
    public Pengguna getPengguna() {
        return pengguna;
    }

    /**
     * @param pengguna the pengguna to set
     */
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}

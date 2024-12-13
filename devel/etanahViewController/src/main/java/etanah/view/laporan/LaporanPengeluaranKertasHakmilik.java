/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import electric.xml.Document;
import electric.xml.Element;
import electric.xml.Elements;
import electric.xml.XPath;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.manager.TabManager;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/laporanPengeluaranKertasHakmilik")
public class LaporanPengeluaranKertasHakmilik extends AbleActionBean {

    private List<Document> xmlList;
    private static final Logger LOG = Logger.getLogger(LaporanPengeluaranKertasHakmilik.class);
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    private etanah.Configuration conf;
    private String[] senaraiMaklumatHakmilik;
    private String[] senaraiKeluasanTanah;
    private String[] senaraiPemilikan;
    private String[] statistikKeluasanTanah;
    private String[] laporanKemajuan;
    private String[] laporanHakmilik;
    private String[] laporanTambahan;
    private String[] laporanBaru;
    private String[] senaraiMaklumatHakmilikRN;
    private String[] senaraiKeluasanTanahRN;
    private String[] senaraiPemilikanRN;
    private String[] statistikKeluasanTanahRN;
    private String[] laporanKemajuanRN;
    private String[] laporanHakmilikRN;
    private String[] laporanTambahanRN;
    private String[] laporanBaruRN;
    private String reportName;
    private String report;
    private List<String> listYear = new ArrayList<String>();
    private Pengguna peng;
    private String kodNegeri;
    private List<Pengguna> listPguna;
    private String kodCaw;
    private String kodDaerah;
    private String daerahHasil;
    private String report_p_bil_hari;
    private static String staticKodCaw;
    private List<KodBandarPekanMukim> senaraiBPM;
    private List<KodBandarPekanMukim> senaraiBPM04;
    private List<KodBandarPekanMukim> senaraiBPM05;
    private String daerah;
    private String report_p_kod_daerah;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private static String staticKodDaerah;
    @Inject
    PembetulanService pService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private String kodNeg;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "n9";
        }
//        showReports();
        reportName = "ETMIS_ADD_09.rdf";
        report = "Laporan Pengeluaran Kertas Hakmilik";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new ForwardResolution("/WEB-INF/jsp/common/laporan_pengeluarankertashakmilik.jsp");
    }
        
        public void getDaysToComplete(String workflowId, String stageId, String txncode) {
        int bilHari = 0;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='"
                    + stageId + "']/txncode[@id='" + txncode + "']"));
            while (parent.hasMoreElements()) {
                Element child = parent.next();
                String days = child.getAttributeValue("daysToComplete");
                if (StringUtils.isNotBlank(days)) {
                    bilHari = Integer.parseInt(days);
                }
            }
        }
        
        report_p_bil_hari = (String.valueOf(bilHari));
        LOG.info("-------------------" + report_p_bil_hari);
       
    }

    

//    public Resolution sukat(){
//        String kod_hakmilik = (String) getContext().getRequest().getParameter("hakmilik");
//    }
    /*
     * public Resolution doFilterDaerahBPM() { peng = (Pengguna)
     * getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
     * String cawKod = getContext().getRequest().getParameter("kodCawangan"); if
     * (cawKod != null) { kodCaw = cawKod; } LOG.info("(doFilterDaerahBPM)
     * kodCaw :" + kodCaw);
     *
     * if (kodCaw == null) { kodCaw = staticKodCaw; }
     *
     * KodCawangan kc = kodCawanganDAO.findById(kodCaw); if (kc.getDaerah() !=
     * null) { kodDaerah = null; daerahHasil = kc.getDaerah().getKod(); }
     * kodDaerah = daerahHasil; doFilterBPM(); return new
     * ForwardResolution("/WEB-INF/jsp/common/laporan_maklumaturusan_param.jsp").addParameter("popup",
     * "true");
    }
     */
    public List<Pengguna> getListPguna() {
        return listPguna;
    }

    public void setListPguna(List<Pengguna> listPguna) {
        this.listPguna = listPguna;
    }

    public String[] getLaporanHakmilik() {
        return laporanHakmilik;
    }

    public void setLaporanHakmilik(String[] laporanHakmilik) {
        this.laporanHakmilik = laporanHakmilik;
    }

    public String[] getLaporanHakmilikRN() {
        return laporanHakmilikRN;
    }

    public void setLaporanHakmilikRN(String[] laporanHakmilikRN) {
        this.laporanHakmilikRN = laporanHakmilikRN;
    }

    public String[] getLaporanKemajuan() {
        return laporanKemajuan;
    }

    public void setLaporanKemajuan(String[] laporanKemajuan) {
        this.laporanKemajuan = laporanKemajuan;
    }

    public String[] getLaporanKemajuanRN() {
        return laporanKemajuanRN;
    }

    public void setLaporanKemajuanRN(String[] laporanKemajuanRN) {
        this.laporanKemajuanRN = laporanKemajuanRN;
    }

    public String[] getSenaraiKeluasanTanah() {
        return senaraiKeluasanTanah;
    }

    public void setSenaraiKeluasanTanah(String[] senaraiKeluasanTanah) {
        this.senaraiKeluasanTanah = senaraiKeluasanTanah;
    }

    public String[] getSenaraiKeluasanTanahRN() {
        return senaraiKeluasanTanahRN;
    }

    public void setSenaraiKeluasanTanahRN(String[] senaraiKeluasanTanahRN) {
        this.senaraiKeluasanTanahRN = senaraiKeluasanTanahRN;
    }

    public String[] getSenaraiMaklumatHakmilik() {
        return senaraiMaklumatHakmilik;
    }

    public void setSenaraiMaklumatHakmilik(String[] senaraiMaklumatHakmilik) {
        this.senaraiMaklumatHakmilik = senaraiMaklumatHakmilik;
    }

    public String[] getSenaraiMaklumatHakmilikRN() {
        return senaraiMaklumatHakmilikRN;
    }

    public void setSenaraiMaklumatHakmilikRN(String[] senaraiMaklumatHakmilikRN) {
        this.senaraiMaklumatHakmilikRN = senaraiMaklumatHakmilikRN;
    }

    public String[] getSenaraiPemilikan() {
        return senaraiPemilikan;
    }

    public void setSenaraiPemilikan(String[] senaraiPemilikan) {
        this.senaraiPemilikan = senaraiPemilikan;
    }

    public String[] getSenaraiPemilikanRN() {
        return senaraiPemilikanRN;
    }

    public void setSenaraiPemilikanRN(String[] senaraiPemilikanRN) {
        this.senaraiPemilikanRN = senaraiPemilikanRN;
    }

    public String[] getStatistikKeluasanTanah() {
        return statistikKeluasanTanah;
    }

    public void setStatistikKeluasanTanah(String[] statistikKeluasanTanah) {
        this.statistikKeluasanTanah = statistikKeluasanTanah;
    }

    public String[] getStatistikKeluasanTanahRN() {
        return statistikKeluasanTanahRN;
    }

    public void setStatistikKeluasanTanahRN(String[] statistikKeluasanTanahRN) {
        this.statistikKeluasanTanahRN = statistikKeluasanTanahRN;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public List<String> getListYear() {
        //calendar for year
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        listYear.add(String.valueOf(year));
        for (int i = 0; i < 30; i++) {
            year--;
            listYear.add(String.valueOf(year));
        }
        return listYear;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String[] getLaporanTambahan() {
        return laporanTambahan;
    }

    public void setLaporanTambahan(String[] laporanTambahan) {
        this.laporanTambahan = laporanTambahan;
    }

    public String[] getLaporanTambahanRN() {
        return laporanTambahanRN;
    }

    public void setLaporanTambahanRN(String[] laporanTambahanRN) {
        this.laporanTambahanRN = laporanTambahanRN;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String[] getLaporanBaru() {
        return laporanBaru;
    }

    public void setLaporanBaru(String[] laporanBaru) {
        this.laporanBaru = laporanBaru;
    }

    public String[] getLaporanBaruRN() {
        return laporanBaruRN;
    }

    public void setLaporanBaruRN(String[] laporanBaruRN) {
        this.laporanBaruRN = laporanBaruRN;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getDaerahHasil() {
        return daerahHasil;
    }

    public void setDaerahHasil(String daerahHasil) {
        this.daerahHasil = daerahHasil;
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

    public static void setStaticKodDaerah(String staticKodDaerah) {
        LaporanPengeluaranKertasHakmilik.staticKodDaerah = staticKodDaerah;
    }

    /**
     * @return the senaraiBPM
     */
    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    /**
     * @param senaraiBPM the senaraiBPM to set
     */
    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    /**
     * @return the daerah
     */
    public String getDaerah() {
        return daerah;
    }

    /**
     * @param daerah the daerah to set
     */
    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

        /**
     * @return the senaraiBPM04
     */
    public List<KodBandarPekanMukim> getSenaraiBPM04() {
        return senaraiBPM04;
    }

    /**
     * @param senaraiBPM04 the senaraiBPM04 to set
     */
    public void setSenaraiBPM04(List<KodBandarPekanMukim> senaraiBPM04) {
        this.senaraiBPM04 = senaraiBPM04;
    }
    
    /**
     * @return the senaraiBPM05
     */
    public List<KodBandarPekanMukim> getSenaraiBPM05() {
        return senaraiBPM05;
    }

    /**
     * @param senaraiBPM05 the senaraiBPM05 to set
     */
    public void setSenaraiBPM05(List<KodBandarPekanMukim> senaraiBPM05) {
        this.senaraiBPM05 = senaraiBPM05;
    }

    /**
     * @return the report_p_kod_daerah
     */
    public String getReport_p_kod_daerah() {
        return report_p_kod_daerah;
    }

    /**
     * @param report_p_kod_daerah the report_p_kod_daerah to set
     */
    public void setReport_p_kod_daerah(String report_p_kod_daerah) {
        this.report_p_kod_daerah = report_p_kod_daerah;
    }

    /**
     * @return the report_p_bil_hari
     */
    public String getReport_p_bil_hari() {
        return report_p_bil_hari;
    }

    /**
     * @param report_p_bil_hari the report_p_bil_hari to set
     */
    public void setReport_p_bil_hari(String report_p_bil_hari) {
        this.report_p_bil_hari = report_p_bil_hari;
    }  

    public String getKodNeg() {
         if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNeg = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNeg = "n9";
        } 
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
                if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNeg = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNeg = "n9";
        }
        this.kodNeg = kodNeg;
    }
    
    
}

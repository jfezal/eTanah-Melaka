/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.KodBandarPekanMukim;
import etanah.model.Pengguna;
import etanah.view.ListUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
import etanah.view.etanahActionBeanContext;
import org.apache.log4j.Logger;

@UrlBinding("/consent/laporan")
public class LaporanActionBean extends AbleActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ListUtil listUtil;
    etanahActionBeanContext ctx;
    private List<String> yearList = new ArrayList<String>();
    private List<KodBandarPekanMukim> senaraiBPM;
    private String report_p_tahun;
    private String report_p_bulan;
    private String reportName;
    private String report_p_trh_mula;
    private String report_p_trh_tamat;
    private String report_p_tarikhMasuk;
    private String report_p_kelulusan;
    private String report_p_bangsa;
    private String report_p_pejabat;
    private String report_p_daerah;
    private String report_p_kod_bpm;
    private String report_p_katg_tanah;
    private String report_p_kod_caw;
    private String report_p_id_projek;
    private String report_p_kod_urusan;
    private String kodNegeri;
    private static final Logger LOGGER = Logger.getLogger(LaporanActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/consent/laporan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        //calendar for year
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        yearList.add(String.valueOf(year));
        for (int i = 0; i < 10; i++) {
            year--;
            yearList.add(String.valueOf(year));
        }

        kodNegeri = conf.getProperty("kodNegeri");

        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA NOT EQUAL 00
        if (!pengguna.getKodCawangan().getKod().equals("00")) {
            report_p_kod_caw = pengguna.getKodCawangan().getKod();
        }
    }

    public Resolution mengikutJenis() {
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
//        getContext().getRequest().setAttribute("tahun", Boolean.TRUE);
        if (kodNegeri.equals("05")) {
            reportName = "CONS_Stat_R3_NS.rdf";
        } else {
            reportName = "CONS_Stat_R3_MLK.rdf";
        }
        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        LOGGER.info("::: RDF " + reportName + " :::");

        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution pergerakanFail() {

        if (kodNegeri.equals("05")) {
            reportName = "CONS_Stat_R5_NS.rdf";
        } else {
            reportName = "CONS_Stat_R5_MLK.rdf";
        }
        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        LOGGER.info("::: RDF " + reportName + " :::");
//        getContext().getRequest().setAttribute("tahunBulan", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution mengikutMukim() {

        if (kodNegeri.equals("05")) {
            reportName = "CONS_Stat_R2_NS.rdf";
        } else {
            reportName = "CONS_Stat_R2_MLK.rdf";
        }

        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        LOGGER.info("::: RDF " + reportName + " :::");
//        getContext().getRequest().setAttribute("tahunBulan", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution kategoriTanah() {

        if (kodNegeri.equals("05")) {
            reportName = "CONS_Stat_R1_NS.rdf";
        } else {
            reportName = "CONS_Stat_R1_MLK.rdf";
        }

        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        LOGGER.info("::: RDF " + reportName + " :::");
//        getContext().getRequest().setAttribute("tahunBulan", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanKemajuan() {

        if (kodNegeri.equals("05")) {
            reportName = "CONS_Stat_R4_NS.rdf";
        } else {
            reportName = "CONS_Stat_R4_MLK.rdf";
        }

        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        LOGGER.info("::: RDF " + reportName + " :::");
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("kelulusan", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution mengikutDaerah() {

        if (kodNegeri.equals("05")) {
            reportName = "CONS_Stat_R6_NS.rdf";
        } else {
            reportName = "CONS_Stat_R6_MLK.rdf";
        }

        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        LOGGER.info("::: RDF " + reportName + " :::");
//        getContext().getRequest().setAttribute("tahunBulan", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution jenisKelulusan() {
        if (kodNegeri.equals("05")) {
            reportName = "CONS_Stat_R7_NS.rdf";
        } else {
            reportName = "CONS_Stat_R7_MLK.rdf";
        }

        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        LOGGER.info("::: RDF " + reportName + " :::");
//        getContext().getRequest().setAttribute("tahunBulan", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution jenisBangsa() {

        if (kodNegeri.equals("05")) {
            reportName = "CONS_Stat_R8_NS.rdf";
        } else {
            reportName = "CONS_Stat_R8_MLK.rdf";
        }

        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        LOGGER.info("::: RDF " + reportName + " :::");
//        getContext().getRequest().setAttribute("tahunBulan", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution tempohKelulusan() {
        reportName = "CONS_Stat_R9.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("kelulusan2", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution jumlahPermohonan() {
        reportName = "CONS_Stat_R10.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution unitMMK() {
        reportName = "CONS_Stat_R10_MLK.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("projek", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution kelulusanIkutTanah() {
        reportName = "CONS_Stat_R11.rdf";
        senaraiBPM = listUtil.getSenaraiKodBandarMukim();

        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
//            getContext().getRequest().setAttribute("daerah", Boolean.TRUE);
        } else {
            senaraiBPM = listUtil.getSenaraiKodBPMByDaerahOrderByBPM(pengguna.getKodCawangan().getKod());
        }

        getContext().getRequest().setAttribute("mukim", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("kategoriTanah", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution bangsaDetail() {
        reportName = "CONS_Stat_R12.rdf";
        senaraiBPM = listUtil.getSenaraiKodBandarMukim();
        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
//            getContext().getRequest().setAttribute("daerah", Boolean.TRUE);
        } else {
            senaraiBPM = listUtil.getSenaraiKodBPMByDaerahOrderByBPM(pengguna.getKodCawangan().getKod());
        }
        getContext().getRequest().setAttribute("mukim", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("bangsa", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution mohonPindahMilik() {
        reportName = "CONS_Stat_R13.rdf";
        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
        }
//        getContext().getRequest().setAttribute("tahun", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution pindahDanGadaian() {
        reportName = "CONS_Stat_R14.rdf";
        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
        }
//        getContext().getRequest().setAttribute("tahun", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporan15() {
        reportName = "CONS_Stat_R15.rdf";
        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
        }
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporan16() {
        reportName = "CONS_Stat_R16.rdf";
        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
        }
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporan17() {
        reportName = "CONS_Stat_R17.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

//-------------------LAPORAN TANAH ADAT------------------
    public Resolution laporanAdat1() {
        reportName = "CONS_Stat_A1.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat2() {
        reportName = "CONS_Stat_A2.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat3() {
        reportName = "CONS_Stat_A3.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat4() {
        reportName = "CONS_Stat_A4.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("kelulusanAdat2", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat5() {
        reportName = "CONS_Stat_A5.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat6() {
        reportName = "CONS_Stat_A6.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat7() {
        reportName = "CONS_Stat_A7.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("kelulusanAdat", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat8() {
        reportName = "CONS_Stat_A8.rdf";
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat9() {
        reportName = "CONS_Stat_A9.rdf";
        senaraiBPM = listUtil.getSenaraiKodBandarMukim();

        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
        } else {
            senaraiBPM = listUtil.getSenaraiKodBPMByDaerahOrderByBPM(pengguna.getKodCawangan().getKod());
        }

        getContext().getRequest().setAttribute("mukim", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("kategoriTanah", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat10() {
        reportName = "CONS_Stat_A10.rdf";
        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
        }
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat11() {
        reportName = "CONS_Stat_A11.rdf";
        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
        }
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanAdat12() {
        reportName = "CONS_Stat_A12.rdf";
        Pengguna pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //SET IF KOD CAW PENGGUNA EQUAL 00
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
        }
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution laporanPrestasiPindahMilik() {

        if (kodNegeri.equals("05")) {
            reportName = "CONS_Stat_R17.rdf";
        } else {
            reportName = "CONS_Stat_R9_MLK.rdf";
        }

        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        LOGGER.info("::: RDF " + reportName + " :::");
//        getContext().getRequest().setAttribute("tahunBulan", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution doFilterBPM() {
        report_p_daerah = getContext().getRequest().getParameter("kodDaerah");
        reportName = getContext().getRequest().getParameter("reportNama");
        report_p_trh_mula = getContext().getRequest().getParameter("trhMula");
        report_p_trh_tamat = getContext().getRequest().getParameter("trhTamat");
        report_p_pejabat = getContext().getRequest().getParameter("pejabat");
        report_p_katg_tanah = getContext().getRequest().getParameter("katgTanah");
        report_p_bangsa = getContext().getRequest().getParameter("bangsa");

        senaraiBPM = listUtil.getSenaraiKodBPMByDaerahOrderByBPM(report_p_pejabat);

        getContext().getRequest().setAttribute("pejabat", Boolean.TRUE);
//        getContext().getRequest().setAttribute("daerah", Boolean.TRUE);
        getContext().getRequest().setAttribute("mukim", Boolean.TRUE);
        getContext().getRequest().setAttribute("date", Boolean.TRUE);

        if (reportName.equals("CONS_Stat_R11.rdf")) {
            getContext().getRequest().setAttribute("kategoriTanah", Boolean.TRUE);
        } else if (reportName.equals("CONS_Stat_R12.rdf")) {
            getContext().getRequest().setAttribute("bangsa", Boolean.TRUE);
        }

        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution pindahMilikWNA() {
         
           reportName = "CONS_Mohon_PindahmilikWNA1.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution penerimaPindahMilikWNA() {
         
           reportName = "CONS_Ringkasan_PindahmilikWNA2.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    public Resolution prestasiUrusan() {
         
           reportName = "CONS_Stat_Pindahmilik3.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution prestasiUrusanCM() {
         
           reportName = "CONS_Stat_Pindahmilik_CM.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    public Resolution statistikPemohon() {
         
           reportName = "CONS_Stat_Pemohon.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("urusan", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution statistikPenerima() {
         
           reportName = "CONS_Stat_Penerima.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("urusan", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution statistikMaklumatHakmilik() {
         
           reportName = "CONS_Stat_Maklumat_Hakmilik.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        getContext().getRequest().setAttribute("urusan", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution ringkasanKelulusanPTG() {
         
           reportName = "CONS_RingkasanPTG_MLK.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution ringkasanKelulusanKM_PTG() {
         
           reportName = "CONS_RingkasanPTG_KM_MLKNew.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution ringkasanKelulusanPTD() {
         
           reportName = "CONS_RingkasanPTD_MLK.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution statistikTempohKelulusanPTG() {
         
           reportName = "CONS_StatistikPTG_MLK.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution statistikTempohKelulusanCM() {
         
           reportName = "CONS_StatistikKM_MLK.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution ringkasanKelulusanKM() {
         
           reportName = "CONS_RingkasanKMbyKatg_MLK.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    
    public Resolution statistikTempohKelulusanPTD() {
         
           reportName = "CONS_StatistikPTD_MLK.rdf";

        LOGGER.info("::: RDF " + reportName + " :::");

        getContext().getRequest().setAttribute("date", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_laporan.jsp").addParameter("popup", "true");
    }
    

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public String getReport_p_bulan() {
        return report_p_bulan;
    }

    public void setReport_p_bulan(String report_p_bulan) {
        this.report_p_bulan = report_p_bulan;
    }

    public String getReport_p_tahun() {
        return report_p_tahun;
    }

    public void setReport_p_tahun(String report_p_tahun) {
        this.report_p_tahun = report_p_tahun;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReport_p_trh_mula() {
        return report_p_trh_mula;
    }

    public void setReport_p_trh_mula(String report_p_trh_mula) {
        this.report_p_trh_mula = report_p_trh_mula;
    }

    public String getReport_p_trh_tamat() {
        return report_p_trh_tamat;
    }

    public void setReport_p_trh_tamat(String report_p_trh_tamat) {
        this.report_p_trh_tamat = report_p_trh_tamat;
    }

    public String getReport_p_kelulusan() {
        return report_p_kelulusan;
    }

    public void setReport_p_kelulusan(String report_p_kelulusan) {
        this.report_p_kelulusan = report_p_kelulusan;
    }

    public String getReport_p_bangsa() {
        return report_p_bangsa;
    }

    public void setReport_p_bangsa(String report_p_bangsa) {
        this.report_p_bangsa = report_p_bangsa;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getReport_p_daerah() {
        return report_p_daerah;
    }

    public void setReport_p_daerah(String report_p_daerah) {
        this.report_p_daerah = report_p_daerah;
    }

    public String getReport_p_katg_tanah() {
        return report_p_katg_tanah;
    }

    public void setReport_p_katg_tanah(String report_p_katg_tanah) {
        this.report_p_katg_tanah = report_p_katg_tanah;
    }

    public String getReport_p_kod_bpm() {
        return report_p_kod_bpm;
    }

    public void setReport_p_kod_bpm(String report_p_kod_bpm) {
        this.report_p_kod_bpm = report_p_kod_bpm;
    }

    public String getReport_p_pejabat() {
        return report_p_pejabat;
    }

    public void setReport_p_pejabat(String report_p_pejabat) {
        this.report_p_pejabat = report_p_pejabat;
    }

    public String getReport_p_tarikhMasuk() {
        return report_p_tarikhMasuk;
    }

    public void setReport_p_tarikhMasuk(String report_p_tarikhMasuk) {
        this.report_p_tarikhMasuk = report_p_tarikhMasuk;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public String getReport_p_kod_caw() {
        return report_p_kod_caw;
    }

    public void setReport_p_kod_caw(String report_p_kod_caw) {
        this.report_p_kod_caw = report_p_kod_caw;
    }

    public String getReport_p_id_projek() {
        return report_p_id_projek;
    }

    public void setReport_p_id_projek(String report_p_id_projek) {
        this.report_p_id_projek = report_p_id_projek;
    }
    
    public String getReport_p_kod_urusan() {
        return report_p_kod_urusan;
    }

    public void setReport_p_kod_urusan(String report_p_kod_urusan) {
        this.report_p_kod_urusan = report_p_kod_urusan;
    } 
    
    
}

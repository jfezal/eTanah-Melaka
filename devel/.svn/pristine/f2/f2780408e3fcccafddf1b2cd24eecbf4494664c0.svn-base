/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service.statistik;

import com.google.inject.Inject;
import com.theta.portal.service.ReportUtil;
import com.theta.portal.stripes.statistik.ReportStatistik;

/**
 *
 * @author mohd.faidzal
 */
public class StatistikService {

    @Inject
    ReportUtil reportUtil;
    String noAduan;
    String status;
    String pejabat;
    String tarikhMula;
    String tarikhAkhir;

    public byte[] generateReportStatistik(String noAduan, String status, String pejabat, String tarikhMula, String tarikhAkhir) {
        ReportStatistik rep = new ReportStatistik();
        String reportName = "Lap_stat_aduan_helpdesk.rdf";
        String values = "&p_date1=" + tarikhMula + "&p_date2=" + tarikhAkhir + "&p_ptd_id=" + pejabat;
        return reportUtil.getReports(reportName, values);
    }

    public byte[] generateReportStatistikEtanah(String noAduan, String status, String pejabat, String tarikhMula, String tarikhAkhir) {
        ReportStatistik rep = new ReportStatistik();
        String reportName = "reportetanah.rdf";
        String values = "&p_date1=" + tarikhMula + "&p_date2=" + tarikhAkhir + "&p_ptd_id=" + pejabat;
        return reportUtil.getReports(reportName, values);

    }

    public byte[] generateReportLaporanTempoh(String noAduan, String status, String pejabat, String tarikhMula, String tarikhAkhir) {
        ReportStatistik rep = new ReportStatistik();
        String reportName = "Lap_stat_tempoh_aduan.rdf";
        String values = "&p_trh_mula=" + tarikhMula + "&p_trh_tamat=" + tarikhAkhir + "&p_ptd_id=" + pejabat;
        return reportUtil.getReports(reportName, values);
//?asset&Lap_stat_tempoh_aduan.rdf&p_trh_mula=07/01/2017&p_trh_tamat=07/01/2018&p_ptd_id=
    }

    public byte[] generateReportLaporanSeluruhan(String noAduan, String status, String pejabat, String tarikhMula, String tarikhAkhir) {
        ReportStatistik rep = new ReportStatistik();
        String reportName = "HelpdeskTutupKES_ict.rdf";
        String values = "&p_date1=" + tarikhMula + "&p_date2=" + tarikhAkhir + "&p_ptd_id=" + pejabat;
        return reportUtil.getReports(reportName, values);
//HelpdeskTutupKES_ict.rdf&p_date1=07/01/2017&p_date2=07/01/2018&p_ptd_id=4&p_status=&p_report_id=&p_masalah=
    }

}

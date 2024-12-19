/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service.statistik;

import com.google.inject.Inject;
import com.theta.portal.service.ReportUtil;

/**
 *
 * @author mohd.faidzal
 */
public class ReportService {

    @Inject
    ReportUtil reportUtil;

    public byte[] generateReportAduan(String noAduan) {
        //    BorangHelpdesk_ict.rdf&p_report_id=1695037&p_ptd_id=

        String reportName = "BorangHelpdesk_ict.rdf";
        String values = "&p_report_id=" + noAduan;
        return reportUtil.getReports(reportName, values);
    }
}

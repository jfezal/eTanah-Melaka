/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.report;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.service.statistik.ReportService;
import com.theta.portal.stripes.BaseActionBean;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/helpdesk/report_viewer")
public class ReportViewerActionBean extends BaseActionBean {

    @Inject
    ReportService reportService;

    @DefaultHandler
    public Resolution reportAduan() {
        String noAduan = getContext().getRequest().getParameter("reportId");
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(reportService.generateReportAduan(noAduan)));
//        getContext().getResponse().setContentType("application/octet-stream" );
        getContext().getResponse().setContentType("application/pdf");
        getContext().getResponse().setHeader("Content-Disposition", "attachment;filename="+noAduan+";");
        return new StreamingResolution("application/octet-stream", bis).setFilename(noAduan+".pdf");
    }
}

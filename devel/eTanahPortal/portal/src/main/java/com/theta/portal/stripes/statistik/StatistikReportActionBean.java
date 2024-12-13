/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.statistik;

import able.stripes.JSP;
import com.Ostermiller.util.Base64;
import com.google.inject.Inject;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.statistik.StatistikService;
import com.theta.portal.stripes.BaseActionBean;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/helpdesk/statistik/report")
public class StatistikReportActionBean extends BaseActionBean {

    String noAduan;
    String status;
    String pejabat;
    String tarikhMula;
    String tarikhAkhir;
    @Inject
    StatistikService statistikService;
    List<ReportStatistik> listReport;

    @DefaultHandler
    public Resolution welcome() {
        UserTable pengguna = getContext().getCurrentUser();
        return new JSP("statistik/report.jsp");
    }

    public Resolution cari() {
        listReport = new ArrayList<ReportStatistik>();
//        listReport.add(statistikService.generateReportStatistik());
//        listReport.add(statistikService.generateReportStatistikEtanah());
//        listReport.add(statistikService.generateReportLaporanSeluruhan());
//        listReport.add(statistikService.generateReportLaporanTempoh());
        return new JSP("statistik/report.jsp");
    }

    public Resolution byteString() throws ParseException {
        String result = Base64.encodeToString(statistikService.generateReportStatistik(noAduan, status, pejabat, convertDate(tarikhMula), convertDate(tarikhAkhir)));
        return new StreamingResolution("text/plain", result);
    }


    public Resolution reportStatistik() throws ParseException {

        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(statistikService.generateReportStatistik(noAduan, status, pejabat, convertDate(tarikhMula), convertDate(tarikhAkhir))));
//        getContext().getResponse().setContentType("application/octet-stream" );
        getContext().getResponse().setContentType("application/pdf");
        
        getContext().getResponse().setHeader("Content-disposition", "attachment; filename=\"" + "example.pdf" + "\"");

        return new StreamingResolution("application/pdf", bis);
    }

    public Resolution reportStatistikEtanah() throws ParseException {

        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(statistikService.generateReportStatistikEtanah(noAduan, status, pejabat, convertDate(tarikhMula), convertDate(tarikhAkhir))));
//        getContext().getResponse().setContentType("application/octet-stream" );
        getContext().getResponse().setContentType("application/octet-stream");
        getContext().getResponse().setHeader("Content-disposition","attachment; filename=\"" +"example.pdf" +"\"");
        return new StreamingResolution("application/octet-stream", bis).setFilename("statistik.pdf");
    }

    public Resolution reportKeseluruhan() throws ParseException {

        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(statistikService.generateReportLaporanSeluruhan(noAduan, status, pejabat, convertDate(tarikhMula), convertDate(tarikhAkhir))));
//        getContext().getResponse().setContentType("application/octet-stream" );
        getContext().getResponse().setContentType("application/octet-stream");
        return new StreamingResolution("application/octet-stream", bis).setFilename("statistik.pdf");
    }

    public Resolution reportLaporanTempoh() throws ParseException {

        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(statistikService.generateReportLaporanTempoh(noAduan, status, pejabat, convertDate(tarikhMula), convertDate(tarikhAkhir))));
//        getContext().getResponse().setContentType("application/octet-stream" );
        getContext().getResponse().setContentType("application/octet-stream");
        return new StreamingResolution("application/octet-stream", bis).setFilename("statistik.pdf");
    }

    String convertDate(String tarikh) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
        date = sdf1.parse(tarikh);
        String newdate = new String();
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        newdate = sdf.format(date);

        return newdate;
    }

    public String getNoAduan() {
        return noAduan;
    }

    public void setNoAduan(String noAduan) {
        this.noAduan = noAduan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPejabat() {
        return pejabat;
    }

    public void setPejabat(String pejabat) {
        this.pejabat = pejabat;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhAkhir() {
        return tarikhAkhir;
    }

    public void setTarikhAkhir(String tarikhAkhir) {
        this.tarikhAkhir = tarikhAkhir;
    }

}

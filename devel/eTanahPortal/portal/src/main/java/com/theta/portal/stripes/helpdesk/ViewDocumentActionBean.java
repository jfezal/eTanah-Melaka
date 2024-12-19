/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.helpdesk;

import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskContractorDocumentsDAO;
import com.theta.portal.dao.HelpdeskReportDocumentsDAO;
import com.theta.portal.dao.HelpdeskTechnicalDocumentsDAO;
import com.theta.portal.model.HelpdeskContractorDocuments;
import com.theta.portal.model.HelpdeskReportDocuments;
import com.theta.portal.stripes.BaseActionBean;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/helpdesk/view")
public class ViewDocumentActionBean extends BaseActionBean {

    @Inject
    HelpdeskContractorDocumentsDAO helpdeskContractorDocumentsDAO;
    @Inject
    HelpdeskTechnicalDocumentsDAO helpdeskTechnicalDocumentsDAO;
    @Inject
    HelpdeskReportDocumentsDAO helpdeskReportDocumentsDAO;

    @DefaultHandler
    public Resolution welcome() {
        return new ErrorResolution(500, "No Document provided!");
    }

    public Resolution user() {
        String id = getContext().getRequest().getParameter("id");
        String reportId;
        if (StringUtils.isEmpty(id)) {
            return new ErrorResolution(500, "No Document provided!");
        }
        HelpdeskReportDocuments reportDoc = helpdeskReportDocumentsDAO.findById(Long.valueOf(id));

        if (reportDoc == null) {
            return new ErrorResolution(500, "Document no found!!");
        }

        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(reportDoc.getBinaryData()));
//        getContext().getResponse().setContentType("application/octet-stream" );
        getContext().getResponse().setContentType(reportDoc.getContentType());
        return new StreamingResolution(reportDoc.getContentType(), bis).setFilename(reportDoc.getFileName());
    }

    public Resolution teknikal() {
        String id = getContext().getRequest().getParameter("id");
        String reportId;
        if (StringUtils.isEmpty(id)) {
            return new ErrorResolution(500, "No Document provided!");
        }
        com.theta.portal.model.HelpdeskTechnicalDocuments techDoc = helpdeskTechnicalDocumentsDAO.findById(Long.valueOf(id));
        if (techDoc == null) {
            return new ErrorResolution(500, "Document no found!!");
        }

        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(techDoc.getBinaryData()));
//        getContext().getResponse().setContentType("application/octet-stream" );
        getContext().getResponse().setContentType(techDoc.getContentType());
        return new StreamingResolution(techDoc.getContentType(), bis).setFilename(techDoc.getFileName());
    }

    public Resolution kontraktor() {
        String id = getContext().getRequest().getParameter("id");
        String reportId;
        if (StringUtils.isEmpty(id)) {
            return new ErrorResolution(500, "No Document provided!");
        }
        HelpdeskContractorDocuments conDoc = helpdeskContractorDocumentsDAO.findById(Long.valueOf(id));

        if (conDoc == null) {
            return new ErrorResolution(500, "Document no found!!");
        }

        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(conDoc.getBinaryData()));
//        getContext().getResponse().setContentType("application/octet-stream" );
        getContext().getResponse().setContentType(conDoc.getContentType());
        return new StreamingResolution(conDoc.getContentType(), bis).setFilename(conDoc.getFileName());

    }
}

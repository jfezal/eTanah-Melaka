/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service.dms;

import com.google.inject.Inject;
import com.theta.portal.model.HelpdeskContractorDocuments;
import com.theta.portal.model.HelpdeskReportDocuments;
import com.theta.portal.model.HelpdeskTechnicalDocuments;
import com.theta.portal.stripes.form.DMSUploadForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.FileBean;
import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class DMSService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public byte[] convertFileToByte(FileBean fileBean) throws IOException {

        byte[] targetArray = IOUtils.toByteArray(fileBean.getInputStream());
        return targetArray;
    }

    public List<HelpdeskReportDocuments> listHelpdeskReportDocument(Long reportId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskReportDocuments p "
                + "where p.reportId.reportId = :reportId");
        query.setParameter("reportId", reportId);
        return query.list();
    }

    public List<DMSUploadForm> findDocumentByReportId(Long reportId) {
        List<DMSUploadForm> list = new ArrayList<DMSUploadForm>();
        for (HelpdeskReportDocuments a : listHelpdeskReportDocument(reportId)) {
            DMSUploadForm form = new DMSUploadForm();
            form.setFileName(a.getFileName());
            form.setId(a.getId());
            form.setUrlDownload("/helpdesk/view?user&id=" + a.getId() + "&reportId=" + reportId);
            list.add(form);
        }
        return list;
    }

    public List<HelpdeskContractorDocuments> listHelpdeskContractorDocuments(Long helpdeskContractorId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskContractorDocuments p "
                + "where p.helpdeskContractorId.helpdeskContractorId = :helpdeskContractorId");
        query.setParameter("helpdeskContractorId", helpdeskContractorId);
        return query.list();
    }

    public List<DMSUploadForm> findDocumentByContractorId(Long helpdeskContractorId) {
        List<DMSUploadForm> list = new ArrayList<DMSUploadForm>();

        for (HelpdeskContractorDocuments a : listHelpdeskContractorDocuments(helpdeskContractorId)) {
            DMSUploadForm form = new DMSUploadForm();
            form.setFileName(a.getFileName());
            form.setId(a.getId());
            form.setUrlDownload("/helpdesk/view?kontraktor&id=" + a.getId() + "&helpdeskContractorId=" + helpdeskContractorId);
            list.add(form);
        }
        return list;
    }

    public List<HelpdeskTechnicalDocuments> listHelpdeskTechnicalDocuments(Long technicalId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskTechnicalDocuments p "
                + "where p.technicalId.technicalId = :technicalId");
        query.setParameter("technicalId", technicalId);
        return query.list();
    }

    public List<DMSUploadForm> findDocumentByTechnicalId(Long technicalId) {
        List<DMSUploadForm> list = new ArrayList<DMSUploadForm>();

        for (HelpdeskTechnicalDocuments a : listHelpdeskTechnicalDocuments(technicalId)) {
            DMSUploadForm form = new DMSUploadForm();
            form.setFileName(a.getFileName());
            form.setId(a.getId());
            form.setUrlDownload("/helpdesk/view?teknikal&id=" + a.getId() + "&technicalId=" + technicalId);

            list.add(form);
        }
        return list;
    }

}

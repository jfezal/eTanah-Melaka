/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.util;

import com.google.inject.Inject;
import com.theta.portal.dao.EmployeeDAO;
import com.theta.portal.model.HelpdeskAssign;
import com.theta.portal.model.HelpdeskContractor;
import com.theta.portal.model.HelpdeskContractorDocuments;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskReportDocuments;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.HelpdeskTechnicalDocuments;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.AduanService;
import com.theta.portal.service.dms.DMSService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
public class SendEmelService {

    @Inject
    AduanService aduanService;
    @Inject
    DMSService dMSService;

    public void mail(HelpdeskReport helpdeskReport, String to) throws IOException, MessagingException {
        String empEmail = to;
//        HelpdeskAssign helpdeskAssign = new HelpdeskAssign();
        HelpdeskTechnical helpdeskTechnical = aduanService.findByReportID(helpdeskReport.getReportId());
        HelpdeskContractor helpdeskContractor = aduanService.findHelpdeskContractorByReportID("" + helpdeskReport.getReportId());
        if (StringUtils.isNotBlank(empEmail)) {
            String[] s = {empEmail};

            MailService mail = new MailService();
            //for format date to string
//					java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy h:mm:ss a");
            String strDate = sm.format(helpdeskReport.getCreateDate());

            String modul = new String();
            String item = new String();
            String perkakasan = new String();
            String phoneNo = new String();
            String email = new String();

            modul = helpdeskReport.getModulType() != null ? helpdeskReport.getModulType().getSubmodulTypeName() : "-";
            item = helpdeskReport.getItemType() != null ? helpdeskReport.getItemType().getItemTypeName() : "-";
            perkakasan = helpdeskReport.getHardwareType() != null ? helpdeskReport.getHardwareType().getHardwareTypeName() : "-";
            phoneNo = helpdeskReport.getEmpId().getEmployeePhoneNo() != null ? helpdeskReport.getEmpId().getEmployeePhoneNo() : "-";
            email = helpdeskReport.getEmpId().getEmail() != null ? helpdeskReport.getEmpId().getEmail() : "-";

            String content = "Borang Aduan Masalah Sistem e-Tanah Negeri Melaka\n\n"
                    + "Maklumat Pelapor Aduan\n"
                    + "Pelapor Aduan : " + helpdeskReport.getEmpId().getEmployeeName() + " \n"
                    + "Jawatan : " + helpdeskReport.getEmpId().getPositionId().getPositionName() + " \n"
                    + "Jabatan : " + helpdeskReport.getEmpId().getEmployeePtdOfficeId().getPtdName() + " \n"
                    + "Unit : " + helpdeskReport.getEmpId().getEmployeeDepartmentId().getDepartmentName() + " \n"
                    + "No. Telefon : " + phoneNo + " \n"
                    + "Email : " + email + " \n\n"
                    + "Maklumat Aduan\n"
                    + "Tarikh Aduan : " + strDate + " \n"
                    + "No. Aduan : " + helpdeskReport.getReportId() + " \n"
                    + "Jenis Masalah : " + helpdeskReport.getItemType().getItemTypeName() + " \n"
                    + "Sub Kategori Perkakasan : " + perkakasan + " \n"
                    + "Sub Kategori/ Modul : " + modul + " \n"
                    + "Item : " + item + " \n"
                    + "Keterangan Masalah : " + helpdeskReport.getDescription() + " \n\n"
                    + "Maklumat Pegawai Teknikal\n"
                    + "Nama Pegawai Teknikal : " + helpdeskTechnical.getTechnicalOfficerId().getEmployeeId().getEmployeeName() + " \n"
                    + "Catatan Pegawai Teknikal : " + helpdeskTechnical.getDescHdassign();

            System.out.println(s[0] + content);
            Multipart multipart = new MimeMultipart();

            List<HelpdeskReportDocuments> listHrd = dMSService.listHelpdeskReportDocument(helpdeskReport.getReportId());
            for (HelpdeskReportDocuments hrd : listHrd) {
                BodyPart messageBodyPart = new MimeBodyPart();
                ByteArrayInputStream bis = new ByteArrayInputStream(hrd.getBinaryData());
                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                String jenis_mime = mimeTypesMap.getContentType(hrd.getContentType());
                DataSource source = new ByteArrayDataSource(bis, jenis_mime);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(hrd.getFileName());
                multipart.addBodyPart(messageBodyPart);
            }

            List<HelpdeskTechnicalDocuments> listHtd = dMSService.listHelpdeskTechnicalDocuments(helpdeskTechnical.getTechnicalId());
            for (HelpdeskTechnicalDocuments htd : listHtd) {
                BodyPart messageBodyPart = new MimeBodyPart();
                ByteArrayInputStream bis = new ByteArrayInputStream(htd.getBinaryData());
                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                String jenis_mime = mimeTypesMap.getContentType(htd.getContentType());
                DataSource source = new ByteArrayDataSource(bis, jenis_mime);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(htd.getFileName());
                multipart.addBodyPart(messageBodyPart);
            }
            List<HelpdeskContractorDocuments> listHcd = dMSService.listHelpdeskContractorDocuments(helpdeskContractor.getContractorId());
            for (HelpdeskContractorDocuments hcd : listHcd) {
                BodyPart messageBodyPart = new MimeBodyPart();
                ByteArrayInputStream bis = new ByteArrayInputStream(hcd.getBinaryData());
                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                String jenis_mime = mimeTypesMap.getContentType(hcd.getContentType());
                DataSource source = new ByteArrayDataSource(bis, jenis_mime);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(hcd.getFileName());
                multipart.addBodyPart(messageBodyPart);
            }

//					BinaryTb4DTO[] files =(BinaryTb4DTO[]) hdReport.getFilesAttached().toArray();
//            Set<BinaryTb4DTO> sett = helpdeskReport.getFilesAttached();
//            Iterator<BinaryTb4DTO> iter = sett.iterator();
//            while (iter.hasNext()) {
//                BodyPart messageBodyPart = new MimeBodyPart();
//                BinaryTb4DTO attc = iter.next();
//                System.out.print(attc + " ");
//                ByteArrayInputStream bis = new ByteArrayInputStream(attc.getBinaryData());
//                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
//                String jenis_mime = mimeTypesMap.getContentType(attc.getContentType());
//                DataSource source = new ByteArrayDataSource(bis, jenis_mime);
//                messageBodyPart.setDataHandler(new DataHandler(source));
//                messageBodyPart.setFileName(attc.getFileName());
//                multipart.addBodyPart(messageBodyPart);
//            }
            mail.sendEmail(s, helpdeskReport.getTitle(), content, "04", multipart);

        }
    }

    public void sendMailConfirmation(HelpdeskReport helpdeskReport, HelpdeskTechnical helpdeskTechnical, UserTable to) {
        String empEmail = to.getEmployeeId().getEmail();
        String[] s = {empEmail};
        SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy h:mm:ss a");

        String strDate = sm.format(helpdeskReport.getCreateDate());

        String modul = new String();
        String item = new String();
        String perkakasan = new String();
        String phoneNo = new String();
        String email = new String();

        modul = helpdeskReport.getModulType() != null ? helpdeskReport.getModulType().getSubmodulTypeName() : "-";
        item = helpdeskReport.getItemType() != null ? helpdeskReport.getItemType().getItemTypeName() : "-";
        perkakasan = helpdeskReport.getHardwareType() != null ? helpdeskReport.getHardwareType().getHardwareTypeName() : "-";
        phoneNo = helpdeskReport.getEmpId().getEmployeePhoneNo() != null ? helpdeskReport.getEmpId().getEmployeePhoneNo() : "-";
        email = helpdeskReport.getEmpId().getEmail() != null ? helpdeskReport.getEmpId().getEmail() : "-";

        String content = "Borang Aduan Masalah Sistem e-Tanah Negeri Melaka\n\n"
                + "Maklumat Pelapor Aduan\n"
                + "Pelapor Aduan : " + helpdeskReport.getEmpId().getEmployeeName() + " \n"
                + "Jawatan : " + helpdeskReport.getEmpId().getPositionId().getPositionName() + " \n"
                + "Jabatan : " + helpdeskReport.getEmpId().getEmployeePtdOfficeId().getPtdName() + " \n"
                + "Unit : " + helpdeskReport.getEmpId().getEmployeeDepartmentId().getDepartmentName() + " \n"
                + "No. Telefon : " + phoneNo + " \n"
                + "Email : " + email + " \n\n"
                + "Maklumat Aduan\n"
                + "Tarikh Aduan : " + strDate + " \n"
                + "No. Aduan : " + helpdeskReport.getReportId() + " \n"
                + "Jenis Masalah : " + helpdeskReport.getItemType().getItemTypeName() + " \n"
                + "Sub Kategori Perkakasan : " + perkakasan + " \n"
                + "Sub Kategori/ Modul : " + modul + " \n"
                + "Item : " + item + " \n"
                + "Keterangan Masalah : " + helpdeskReport.getDescription() + " \n\n"
                + "Maklumat Pegawai Teknikal\n"
                + "Nama Pegawai Teknikal : " + helpdeskTechnical.getTechnicalOfficerId().getEmployeeId().getEmployeeName() + " \n"
                + "Catatan Pegawai Teknikal : " + helpdeskTechnical.getDescHdassign();

        System.out.println(s[0] + content);
        Multipart multipart = new MimeMultipart();
        
        content = "Sila Semak aduan yang telah diselesaikan seperti dibawah. \n\n\n" + content;

        MailService mail = new MailService();

        mail.sendEmail(s, "Semakan Aduan ("+helpdeskReport.getTitle()+" )", content, "04", multipart);
    }
}

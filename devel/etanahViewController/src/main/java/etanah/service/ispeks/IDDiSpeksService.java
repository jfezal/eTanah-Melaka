/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.IspeksConfig;
import etanah.dao.BilDAO;
import etanah.dao.JournalDAO;
import etanah.dao.PenyataPemungutDAO;
import etanah.dao.StatusInfoIspeksDAO;
import etanah.integration.mail.MailClientService;
import etanah.integration.mail.MailClientServiceImplService;
import etanah.integration.mail.StringArray;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.ispek.Bil;
import etanah.model.ispek.Journal;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.StatusInfoIspeks;
import etanah.model.view.IddBilContent;
import etanah.model.view.IddBilHeader;
import etanah.model.view.IddJurnalContent;
import etanah.model.view.IddJurnalHeader;
import etanah.model.view.IddPPContent;
import etanah.model.view.IddPPContentCekDraf;
import etanah.model.view.IddPPHeader;
import etanah.report.ReportUtilPath;
import etanah.view.uam.MailForm;
import etanah.view.uam.MailService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.xml.namespace.QName;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohd.faidzal
 */
public class IDDiSpeksService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    IspeksConfig ispeksConfig;
    @Inject
    PenyataPemungutDAO penyataPemungutDAO;
    @Inject
    PenyataPemungutService penyataPemungutService;
    @Inject
    JournalService journalService;
    @Inject
    BilService bilService;
    @Inject
    IspeksEncryptDecryptService ispeksEncryptDecryptService;
    @Inject
    IspeksSftpService ispeksSftpService;
    @Inject
    StatusInfoIspeksDAO statusInfoIspeksDAO;
    @Inject
    BilDAO bilDAO;
    @Inject
    JournalDAO journalDAO;
    @Inject
    MailService mailService;
    @Inject
    ReportUtilPath reportUtilPath;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

    public IspeksStatus janaIddPP(Long idPenyata, KodCawangan caw, Pengguna pengguna) throws IOException, MessagingException {
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new Date());
        PenyataPemungut pp = penyataPemungutDAO.findById(idPenyata);

        IspeksStatus sts = new IspeksStatus();
        String filepath = "";
        String filename = "";
        String encrypted = "";
        String dokumenPath = ispeksConfig.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + "PENYATA_PEMUNGUT" + File.separator + caw.getKod() + File.separator + pp.getNoPenyata() + File.separator;

        String fileName = "";
        try {
            IddPPHeader header = penyataPemungutService.findHeader(pp.getNoPenyata());
            fileName = "04" + "_" + "00030" + "_PPL" + "_" + sdf.format(new Date());
            filepath = dokumenPath + fileName;
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            filename = filepath + ".txt";
            encrypted = filepath + ".gpg";
            PrintWriter pw = new PrintWriter(new FileWriter(filename));
            pw.println(header.getIdd());
            if (pp.getCaraBayar().equals("T")) {
                List<IddPPContent> listContent = penyataPemungutService.findContent(pp.getNoPenyata());
                for (int i = 0; i < listContent.size(); i++) {
                    IddPPContent c = listContent.get(i);
                    pw.println(c.getIdd());
                }
            } else {
                List<IddPPContentCekDraf> listContentCek = penyataPemungutService.findContentCekDraf(pp.getNoPenyata());
                for (int i = 0; i < listContentCek.size(); i++) {
                    IddPPContentCekDraf c = listContentCek.get(i);
                    pw.println(c.getIdd());
                }
            }
            pw.close();
            sts.setPathIDD(filename);
            sts.setIddFileName(fileName + ".txt");
            sts.setGPGFileName(fileName + ".gpg");
            sts.setStatusIdd("Y");
        } catch (Exception ex) {
            sts.setPathIDD(filename);
            sts.setStatusIdd("T");
        }

        sts = ispeksEncryptDecryptService.encrypt(sts, filename, encrypted);
        sts = ispeksSftpService.transfer(sts);
        saveStatusInfo(sts, infoAudit, pp.getNoPenyata(), "PP", pengguna.getKodCawangan().getKod());
        if (sts.getStatusSftp().equals("Y")) {
            MailForm mail = setMailForm(pp.getNoPenyata(), fileName, dokumenPath + fileName + ".pdf", pengguna, pp.getKodCaw());
           String[] to = new String[3];
            if (pp.getKodCaw().getKod().equals("00")) {
                to[0] = "intispeks@melaka.gov.my";
                to[1] = "etanahptg_ispeks@melaka.gov.my";
                to[2] = "mdfaidzal@gmail.com";
            } else if (pp.getKodCaw().getKod().equals("01") || pp.getKodCaw().getKod().equals("04")) {
                to[0] = "intispeks@melaka.gov.my";
                to[1] = "etanahpdtmt_ispeks@melaka.gov.my";
                to[2] = "mdfaidzal@gmail.com";
            } else if (pp.getKodCaw().getKod().equals("02")) {
                to[0] = "intispeks@melaka.gov.my";
                to[1] = "etanahpdtj_ispeks@melaka.gov.my";
                to[2] = "mdfaidzal@gmail.com";
            } else if (pp.getKodCaw().getKod().equals("03")) {
                to[0] = "intispeks@melaka.gov.my";
                to[1] = "etanahpdtag_ispeks@melaka.gov.my";
                to[2] = "mdfaidzal@gmail.com";
            }else{
                to[0] = "intispeks@melaka.gov.my";
                to[1] = "etanahpdtag_ispeks@melaka.gov.my";
                to[2] = "mdfaidzal@gmail.com";
            
            }

             
 
            String subject = mail.getSubject();
            String text = mail.getContent();
            String urlClient = ispeksConfig.getProperty("mail.client");
            URL url = new URL(urlClient);
            MailClientServiceImplService client = new MailClientServiceImplService(url, new QName("http://ws.mail.etanah/", "MailClientServiceImplService"));
            MailClientService mailPort = client.getMailClientServiceImplPort();
            StringArray toA = new StringArray();
            for ( String tos : to) {
                toA.getItem().add(tos);
            }
            StringArray arg3 = new StringArray();
            arg3.getItem().add(mail.getDocPath());
            mailPort.sendMailAttachment(toA, subject, text, arg3);
//            String[] toAddress,            String subject, String[] attachFiles, String namaFail, String tarikh, String cawangan
            mailPort.sendEmailTemplate(toA, subject, arg3, mail.getFileName(), mail.getTarikh(), mail.getCawangan());
        }
        return sts;
    }

    public void saveStatusInfo(IspeksStatus sts, InfoAudit infoAudit, String noRef, String jenisFail, String kodCawangan) {
        Session s = sessionProvider.get();
        Transaction txn = s.beginTransaction();
        StatusInfoIspeks statusInfoIspeks = new StatusInfoIspeks();
        statusInfoIspeks.setGpgPath(sts.getPathGPG());
        statusInfoIspeks.setIddPath(sts.getPathIDD());
        statusInfoIspeks.setNoRef(noRef);
        statusInfoIspeks.setStsEncryptDecrypt(sts.getStatusGPG());
        statusInfoIspeks.setStsIDD(sts.getStatusIdd());
        statusInfoIspeks.setStsTransfer(sts.getStatusSftp());
        statusInfoIspeks.setInfoAudit(infoAudit);
        statusInfoIspeks.setGpgFileName(sts.getGPGFileName());
        statusInfoIspeks.setJenisFail(jenisFail);
        statusInfoIspeks.setKodCawangan(kodCawangan);
        statusInfoIspeks = saveStatusInfoIspeks(statusInfoIspeks);
        txn.commit();
    }

    public IspeksStatus janaIddBil(Long idBil, KodCawangan caw, Pengguna pengguna) throws IOException {
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new Date());
        IspeksStatus sts = new IspeksStatus();
        String filepath = "";
        String filename = "";
        String encrypted = "";
        String dokumenPath = ispeksConfig.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + "BIL" + File.separator + caw.getKod() + File.separator;

        Bil bil = bilDAO.findById(idBil);
        try {
            IddBilHeader header = bilService.findHeader(bil.getId());
            String fileName = "04" + "_" + "00030" + "_BIL" + "_" + sdf.format(new Date());
            filepath = dokumenPath + fileName;
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            filename = filepath + ".txt";
            encrypted = filepath + ".gpg";
            PrintWriter pw = new PrintWriter(new FileWriter(filename));
            pw.println(header.getIdd());

            List<IddBilContent> listContent = bilService.findContent(bil.getId());
            for (int i = 0; i < listContent.size(); i++) {
                IddBilContent c = listContent.get(i);
                pw.println(c.getIdd());
            }

            pw.close();
            sts.setIddFileName(fileName + ".txt");
            sts.setGPGFileName(fileName + ".gpg");
            sts.setPathIDD(filename);
            sts.setPathGPG(encrypted);
            sts.setStatusIdd("Y");
        } catch (Exception ex) {
            sts.setPathIDD(filename);
            sts.setStatusIdd("T");
        }

        sts = ispeksEncryptDecryptService.encrypt(sts, filename, encrypted);
        sts = ispeksSftpService.transfer(sts);
        saveStatusInfo(sts, infoAudit, bil.getNoBil(), "BL", pengguna.getKodCawangan().getKod());
        return sts;
    }

    public IspeksStatus janaIddJournal(Long idJournal, KodCawangan caw, Pengguna pengguna) throws IOException {
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new Date());
        IspeksStatus sts = new IspeksStatus();
        String filepath = "";
        String filename = "";
        String encrypted = "";
        String dokumenPath = ispeksConfig.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + "JOURNAL" + File.separator + caw.getKod() + File.separator;

        Journal journal = journalDAO.findById(idJournal);
        try {
            IddJurnalHeader header = journalService.findHeaderJurnal(journal.getNoJournal() + "");
            String fileName = "04" + "_" + "00030" + "_JRL" + "_" + sdf.format(new Date());
            filepath = dokumenPath + fileName;
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            filename = filepath + ".txt";
            encrypted = filepath + ".gpg";
            PrintWriter pw = new PrintWriter(new FileWriter(filename));
            pw.println(header.getIdd());

            List<IddJurnalContent> listContent = journalService.findContentJurnal(journal.getNoJournal() + "");
            for (int i = 0; i < listContent.size(); i++) {
                IddJurnalContent c = listContent.get(i);
                pw.println(c.getIdd());
            }

            pw.close();
            sts.setIddFileName(fileName + ".txt");
            sts.setGPGFileName(fileName + ".gpg");
            sts.setPathIDD(filename);
            sts.setPathGPG(encrypted);
            sts.setStatusIdd("Y");
        } catch (Exception ex) {
            sts.setPathIDD(filename);
            sts.setStatusIdd("T");
        }

        sts = ispeksEncryptDecryptService.encrypt(sts, filename, encrypted);
        sts = ispeksSftpService.transfer(sts);
        saveStatusInfo(sts, infoAudit, journal.getNoJournal(), "JR", pengguna.getKodCawangan().getKod());

        return sts;
    }

    @Transactional
    private StatusInfoIspeks saveStatusInfoIspeks(StatusInfoIspeks statusInfoIspeks) {
        return statusInfoIspeksDAO.saveOrUpdate(statusInfoIspeks);
    }

    private MailForm setMailForm(String noPenyata, String fileName, String dokumenPath, Pengguna pengguna, KodCawangan caw) {

        MailForm form = new MailForm();
        form.setSubject("eTanah - Laporan Penghantaran Fail Penyata Pemungut");
        form.setContent("" + fileName + "--" + noPenyata);
        form.setFileName("Laporan_" + fileName + ".pdf");
        form.setJenis_mime("application/pdf");
//        InputStream in = new ByteArrayInputStream(reportUtil.getReports("HSL_PP_EMAIL_MLK.rdf", "p_no_penyata="+noPenyata));
        String param[] = new String[1];
        param[0] = "p_no_penyata";
        String values[] = new String[1];
        values[0] = noPenyata;
//        InputStream in = new ByteArrayInputStream(reportUtil.generateReport("HSL_PP_EMAIL_MLK.rdf", param, values, dokumenPath, pengguna));
        reportUtilPath.generateReport("HSL_PP_EMAIL_MLK.rdf", param, values, dokumenPath, pengguna);
        form.setDocPath(dokumenPath);
//        form.setIn(in);
        form.setCawangan(caw.getName());
        form.setTarikh(sdf2.format(new Date()));
        return form;
    }

}

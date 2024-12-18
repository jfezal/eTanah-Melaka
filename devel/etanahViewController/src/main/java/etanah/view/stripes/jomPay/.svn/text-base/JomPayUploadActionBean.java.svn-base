/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.jomPay;
//package com.javacodegeeks.snippets.desktop;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.JomPayFailDetailsDAO;
import etanah.dao.JomPayFailMuatnaikDAO;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.jompay.JomPayFailDetails;
import etanah.model.jompay.JomPayFailMuatnaik;
import etanah.service.jompay.JomPayServices;
import etanah.view.etanahActionBeanContext;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.FileBean;
import org.hibernate.Session;
import etanah.util.DateUtil;
import etanah.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;

@UrlBinding("/jomPay/uploadCsvFail")
public class JomPayUploadActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    JomPayFailMuatnaikDAO jomPayFailMuatnaikDAO;

    @Inject
    JomPayFailDetailsDAO jomPayFailDetailsDAO;

    @Inject
    JomPayServices jomPayServices;

    FileBean fileToBeUploaded;
    private static final Logger LOG = Logger.getLogger(JomPayUploadActionBean.class);
    List<JomPayFailMuatnaik> listJompayDetails = new ArrayList<JomPayFailMuatnaik>();
    private String filename = "";
    private String idUploadFile = "";
    private String edit = "true";

    SimpleDateFormat sdfNoSlash = new SimpleDateFormat("ddMMyyyy");

    @DefaultHandler
    public Resolution showForm() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        listJompayDetails = jomPayServices.findListFailUpload(p.getKodCawangan().getKod());
        return new JSP("jomPay/upload_CsvFail.jsp");
    }

    public Resolution papar() {
        idUploadFile = getContext().getRequest().getParameter("idUploadFile");
        return new RedirectResolution(JomPayDetailsActionBean.class, "papar").addParameter("idUploadFile", idUploadFile);
    }

    public Resolution paparAllSuccessFile() {
        idUploadFile = getContext().getRequest().getParameter("idUploadFile");
        return new RedirectResolution(JomPayDetailsActionBean.class, "paparAllSucessFile").addParameter("idUploadFile", idUploadFile);
    }

    public Resolution viewAllSuccessFile() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        listJompayDetails = jomPayServices.findListFailSuccess(p.getKodCawangan().getKod());
        return new JSP("jomPay/viewAllFileSuccess.jsp");
    }

    public Resolution processUploadDoc() throws IOException {

        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = "JOMPAY";

        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        JomPayFailMuatnaik jomPayFailMuatnaik = new JomPayFailMuatnaik();
        try {
            if (fileToBeUploaded == null) {
                addSimpleError("Sila Pilih Fail untuk di Muatnaik.");
                return new JSP("jomPay/upload_CsvFail.jsp");
            } else if (!(fileToBeUploaded.getFileName().endsWith(".csv"))) {
                addSimpleError("Sila Muatnaik Fail .CSV sahaja.");
                return new JSP("jomPay/upload_CsvFail.jsp");
            }
            if (p != null && fileToBeUploaded != null) {

                String kodCawangan = p.getKodCawangan().getKod();//nfs_apps/dms/\JOMPAY\01\2022\JANUARI\26
                String fileName = fileToBeUploaded.getFileName();

                DMSPATH_WO_DMSBASE = File.separator + kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;

                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fileName, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);

                jomPayFailMuatnaik = new JomPayFailMuatnaik();
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fileName;
                jomPayFailMuatnaik.setNamaFail(fileName);
                jomPayFailMuatnaik.setPathFail(DMSPATH_WO_DMSBASE);
                jomPayFailMuatnaik.setFormat(fileToBeUploaded.getContentType());
                ia.setDimasukOleh(p);
                ia.setTarikhMasuk(new java.util.Date());
                jomPayFailMuatnaik.setInfoAudit(ia);
                jomPayFailMuatnaik.setStatus("A");
                jomPayFailMuatnaik.setTarikhMuatTurun(new java.util.Date());
                jomPayFailMuatnaik = jomPayServices.simpanFail(jomPayFailMuatnaik);

                filename = String.valueOf(jomPayFailMuatnaik.getIdJomPayUpload());

                jomPayServices.extractToStore(jomPayFailMuatnaik, fileToBeUploaded.getInputStream(), ia);
                addSimpleMessage("Muat naik fail berjaya.");

                //paparByFileId(filename);
                return new RedirectResolution(JomPayDetailsActionBean.class, "paparByFileId").addParameter("filename", filename);
                // rehydrate();
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
                return new JSP("jomPay/jompay_details.jsp");

            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
            return new JSP("jomPay/jompay_details.jsp");
        }

    }

    public Resolution paparListDelete() {
        idUploadFile = getContext().getRequest().getParameter("idUploadFile");
        return new RedirectResolution(JomPayDetailsActionBean.class, "paparListDelete").addParameter("idUploadFile", idUploadFile);
    }
    
    

    public void createDir(File f) throws IOException {
        if (f.exists()) {
            // do nothing

        } else {
            f.mkdirs();
        }
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public List<JomPayFailMuatnaik> getListJompayDetails() {
        return listJompayDetails;
    }

    public void setListJompayDetails(List<JomPayFailMuatnaik> listJompayDetails) {
        this.listJompayDetails = listJompayDetails;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/etapp/test_conn")
public class TestConnectionActionBean extends AbleActionBean {

    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    Configuration conf;
    private static final Logger LOG = Logger.getLogger(TestConnectionActionBean.class);
    String borang;
    String flag;
    String stringflag;
    String nofail;
    String datee;
    String idDokumen;

//    @DefaultHandler
//    @SuppressWarnings("empty-statement")
//    public Resolution testConn() throws IntegrationPPTeTanahtoeTappExceptionException, RemoteException {
//        borang = getContext().getRequest().getParameter("borang");
//        flag = getContext().getRequest().getParameter("flag");;
//        stringflag = getContext().getRequest().getParameter("stringflag");;
//        nofail = getContext().getRequest().getParameter("nofail");;
//        datee = getContext().getRequest().getParameter("datee");;
//        etanah.etapp.etappclient.PengambilanService service = new PengambilanService();
//        StatusInfo si = service.testConnMMk(borang, flag, stringflag, nofail, new Date());
//        LOG.info(si.getMsg());
//        return null;
//    }

//    public Resolution testFail() throws IntegrationPPTeTanahtoeTappExceptionException, RemoteException, FileNotFoundException, IOException {
//        String docPath = conf.getProperty("document.path");
//        borang = getContext().getRequest().getParameter("borang");
//        flag = getContext().getRequest().getParameter("flag");
//        stringflag = getContext().getRequest().getParameter("stringflag");
//        nofail = getContext().getRequest().getParameter("nofail");
//        datee = getContext().getRequest().getParameter("datee");
//        idDokumen = getContext().getRequest().getParameter("idDokumen");
////        etanah.etapp.etappclient.PengambilanService service = new PengambilanService();
//        Tblintpptdokumenfrmetanah[] param = new Tblintpptdokumenfrmetanah[1];
//        Tblintpptdokumenfrmetanah params = new Tblintpptdokumenfrmetanah();
//        Dokumen d = dokumenDAO.findById(Long.valueOf(idDokumen));
//        String fn = d.getNamaFizikal();
////attachment sijil ukur by hakmilik
//        System.out.println("fn ::" + fn);
//        File theFile = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
//
//        InputStream inputStream = new FileInputStream(theFile.getAbsolutePath());
//        byte[] byteArray = IOUtils.toByteArray(inputStream);
//        ByteArrayDataSource rawData = new ByteArrayDataSource(byteArray, d.getFormat());
//        DataHandler data = new DataHandler(rawData);
//
////        File theFile = new File("C:/Tulips.jpg");
//        InputStream in = new FileInputStream(theFile);
//        long fail_length = theFile.length();
//
//        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
//        String jenis_mime = mimeTypesMap.getContentType(theFile);
//        System.out.println("File  size :" + (int) fail_length);
//        System.out.println("File  type :" + jenis_mime);
//        System.out.println("File  name :" + theFile.getName());
////        byte[] dd = IOUtils.toByteArray(in);
////        ByteArrayDataSource rawData = new ByteArrayDataSource(dd, jenis_mime);
////        DataHandler data = new DataHandler(rawData);
//        params.setContent_upload(data);
//        params.setFlag_proses("BorangC");
//        params.setNama_dokumen(theFile.getName());
//        params.setJenis_format_fail(jenis_mime);
//        params.setJenis_fail(jenis_mime);
//        params.setTajuk_dokumen(jenis_mime);
//        params.setSize((int) fail_length);
//        params.setTurutan(1);
//        params.setNo_fail_jkptg("JKPTG(S).MLK/01/881/12/2012/123");
//        param[0] = params;
//        StatusInfo si = service.dokumenToMyEtapp(param);
//        LOG.info(si.getMsg());
//        return null;
//    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getBorang() {
        return borang;
    }

    public void setBorang(String borang) {
        this.borang = borang;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStringflag() {
        return stringflag;
    }

    public void setStringflag(String stringflag) {
        this.stringflag = stringflag;
    }

    public String getNofail() {
        return nofail;
    }

    public void setNofail(String nofail) {
        this.nofail = nofail;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

}

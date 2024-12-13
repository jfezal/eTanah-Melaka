/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.jcraft.jsch.JSchException;
import etanah.IspeksConfig;
import etanah.dao.IspeksFileProsesDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.ispek.IspeksFileProses;
import etanah.service.ispeks.IspeksReadFileService;
import etanah.service.ispeks.IspeksService;
import etanah.service.ispeks.IspeksSftpService;
import etanah.service.ispeks.IspeksStatus;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/ispeks/util")
public class IspeksUtilActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(IspeksUtilActionBean.class);

    @Inject
    IspeksReadFileService ispeksReadFileService;
    @Inject
    IspeksConfig ispeksConfig;
    @Inject
    IspeksSftpService ispeksSftpService;
    @Inject
    IspeksService ispeksService;
    @Inject
    IspeksFileProsesDAO ispeksFileProsesDAO;
    String[] fileDownload;
    String[] fileProcess;
//    String []id;
//    String []idChecked;
//    String []kategori;
    List<FileUtilForm> fileToDownload = new ArrayList<FileUtilForm>();
    List<FileUtilForm> fileToProcess = new ArrayList<FileUtilForm>();

    @DefaultHandler
    public Resolution showForm() throws JSchException {
        fileToDownload = new ArrayList<FileUtilForm>();
        fileToProcess = new ArrayList<FileUtilForm>();

        fileToDownload = ispeksSftpService.getListFileFromRemote();
        fileToProcess = ispeksSftpService.getListFileToProcess();
        return new JSP("ispeks/utility.jsp");
    }

    public Resolution processExtract() throws FileNotFoundException, ParseException {
        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] ids = getContext().getRequest().getParameterValues("ids");
        String[] kategori = getContext().getRequest().getParameterValues("kategori");
        String[] idChecked = getContext().getRequest().getParameterValues("idChecked");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        for (int i = 0; i < ids.length; i++) {
            IspeksFileProses fileP = ispeksFileProsesDAO.findById(Long.valueOf(ids[i]));
            fileP.setKategori(kategori[i]);
            fileP.setInfoAudit(ia);
            ispeksService.simpanIspeksFileProses(fileP);
        }

        if (idChecked != null) {
            for (int i = 0; i < idChecked.length; i++) {
                IspeksFileProses fileP = ispeksFileProsesDAO.findById(Long.valueOf(idChecked[i]));
                File file = new File(ispeksConfig.getProperty("local_folder") + File.separator + fileP.getNamaFail());
                try {
                    ispeksReadFileService.process(fileP.getKategori(), file,ia);
                } catch (Exception ex) {
                    ex.printStackTrace();
                                LOG.info("Error SFTP" + ex);

                } finally {
                    fileP.setStatus("Y");
                    ispeksService.simpanIspeksFileProses(fileP);

                }
            }
        }
        return new RedirectResolution(IspeksUtilActionBean.class, "showForm");
    }

    public Resolution download() {
//        fileDownload = ;
        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        for (int i = 0; i < fileDownload.length; i++) {
            IspeksStatus s = ispeksSftpService.download(fileDownload[i], pengguna);
        }
//        
//                    IspeksFileProses file = new IspeksFileProses();
//            file.setTarikhMuatTurun(new Date());
//            file.setNamaFail(fileName);
        return new RedirectResolution(IspeksUtilActionBean.class, "showForm");
    }

    public IspeksSftpService getIspeksSftpService() {
        return ispeksSftpService;
    }

    public void setIspeksSftpService(IspeksSftpService ispeksSftpService) {
        this.ispeksSftpService = ispeksSftpService;
    }

    public String[] getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(String[] fileDownload) {
        this.fileDownload = fileDownload;
    }

    public String[] getFileProcess() {
        return fileProcess;
    }

    public void setFileProcess(String[] fileProcess) {
        this.fileProcess = fileProcess;
    }

    public List<FileUtilForm> getFileToDownload() {
        return fileToDownload;
    }

    public void setFileToDownload(List<FileUtilForm> fileToDownload) {
        this.fileToDownload = fileToDownload;
    }

    public List<FileUtilForm> getFileToProcess() {
        return fileToProcess;
    }

    public void setFileToProcess(List<FileUtilForm> fileToProcess) {
        this.fileToProcess = fileToProcess;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/surat_Warta")
public class WartaActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(WartaActionBean.class);
    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    StrataPtService strService;
    @Inject
    etanah.Configuration conf;
    private String SERVER_LOCATION;
    private String reportKey;
    private String iframeURL;
    private String reportName;
    private Dokumen dokumen;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pengguna;
    private boolean view;
    private long idDokumen;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("semaWarta", Boolean.FALSE);
        return new JSP("lelong/surat_Warta.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("semaWarta", Boolean.TRUE);
        return new JSP("lelong/surat_Warta.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        reportName = "LLGBorang16H_MLK.rdf";
        List<KandunganFolder> listKandunganFolder2 = lelongService.getListDokumenWarta(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder2.isEmpty()) {
            for (KandunganFolder ff : listKandunganFolder2) {
                if (ff.getDokumen().getKodDokumen().getKod().equals("SRW")) {
                    dokumen = ff.getDokumen();
                    break;
                }
            }
        }
        if (dokumen != null) {
            idDokumen = dokumen.getIdDokumen();
            view = true;
            viewPdf();
        } else {
            view = false;
            genReport2();
        }
    }

    //sebelum jana
    public Resolution janaReport() {
        LOG.info("------sebelum jana------");
        String add = conf.getProperty("report.server.location");
        InetAddress address = null;
        try {
            address = InetAddress.getByName(lelongService.getIP(add));
            SERVER_LOCATION = lelongService.replaceDNS(address.getHostAddress(), add);
        } catch (UnknownHostException ex) {
            LOG.info("---error---" + ex);
        }
        LOG.info("SERVER_LOCATION : " + SERVER_LOCATION);
        reportKey = conf.getProperty("report.key");
        StringBuilder cmd = new StringBuilder(SERVER_LOCATION).append("?").append(reportKey).append("&").append(reportName).append("&").append("p_id_mohon").append("=").append(idPermohonan);
        LOG.info("SERVER REPORT : " + cmd);
        iframeURL = cmd.toString();
        getContext().getRequest().setAttribute("jana", Boolean.TRUE);
        getContext().getRequest().setAttribute("lulus", Boolean.FALSE);
        return new JSP("lelong/surat_Warta.jsp").addParameter("tab", "true");
    }

    //selepas jana
    public Resolution printReport() {
        LOG.info("------selepas jana------");
        String docPath = conf.getProperty("document.path");
        StringBuilder cmd = new StringBuilder(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + dokumen.getNamaFizikal() + strService.getFormat(dokumen.getFormat()));
        iframeURL = cmd.toString();
        LOG.info("iframeURL : " + iframeURL);
        return new JSP("lelong/surat_Warta.jsp").addParameter("tab", "true");
    }

    //selepas jana
    public Resolution viewPdf() {
        LOG.info("------selepas jana------");
         LOG.info("genReport");
        //add by nur.aqilah
        String gen = "LLGBorang16H_MLK.rdf";
        String code = "SRW";
        lelongService.reportGen(permohonan, gen, code, pengguna);
        
        LOG.info("idDokumen :" + idDokumen);
//        Dokumen d = dokumenDAO.findById(idDokumen);
//        if (d == null) {
//            return new ErrorResolution(500, "Dokumen tidak dijumpai");
//        }
//        String docPath = conf.getProperty("document.path");
//        String fn = d.getNamaFizikal();
//        LOG.info("fn ::" + fn);
//        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
//        LOG.info("new FileInputStream(f) : " + f.getPath());
//        if (f != null) {
//            try {
//                return new StreamingResolution(d.getFormat(), new FileInputStream(f));
//            } catch (FileNotFoundException ex) {
//            }
//        }
        return new JSP("lelong/surat_Warta.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "LLGBorang16H_MLK.rdf";
        String code = "SRW";

        try {
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public void genReport2() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String gen = "LLGBorang16H_MLK.rdf";
        String code = "SRW";
        try {
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            LOG.error("Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        rehydrate();
    }

    public String getSERVER_LOCATION() {
        return SERVER_LOCATION;
    }

    public void setSERVER_LOCATION(String SERVER_LOCATION) {
        this.SERVER_LOCATION = SERVER_LOCATION;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIframeURL() {
        return iframeURL;
    }

    public void setIframeURL(String iframeURL) {
        this.iframeURL = iframeURL;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }
}

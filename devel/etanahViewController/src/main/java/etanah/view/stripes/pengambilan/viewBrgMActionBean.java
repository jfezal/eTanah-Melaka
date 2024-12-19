/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodNotis;
import etanah.model.Notis;
import etanah.model.PenasihatUndang;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/view_BrgM")
public class viewBrgMActionBean extends AbleActionBean{
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
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
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
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private static final Logger LOGGER = Logger.getLogger(viewBrgIActionBean.class);
    private boolean mlk = false;


    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("semak16H", Boolean.TRUE);
        return new JSP("pengambilan/view_brgM.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
//        getContext().getRequest().setAttribute("semak16H", Boolean.TRUE);
        return new JSP("pengambilan/view_brgM.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        LOGGER.info("----Borang M----");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            reportName = "ACQDocM_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            reportName = "ACQDocM_NS.rdf";
        }
        LOGGER.info("reportName : " + reportName);
        List<KandunganFolder> listKandunganFolder = lelongService.getListDokumenM(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder.isEmpty()) {
            for (KandunganFolder ff : listKandunganFolder) {
                if (ff.getDokumen().getKodDokumen().getKod().equals("M")) {
                    dokumen = ff.getDokumen();
                    break;
                }
            }
        }

        if (dokumen != null && dokumen.getNamaFizikal() != null) {
            idDokumen = dokumen.getIdDokumen();
            view = true;
            viewPdf();
        }
    }
   //selepas jana
    public Resolution viewPdf() {
        LOGGER.info("------selepas jana------");
        LOGGER.info("idDokumen :" + idDokumen);
//        Dokumen d = dokumenDAO.findById(idDokumen);
//        if (d == null) {
//            return new ErrorResolution(500, "Dokumen tidak dijumpai");
//        }
//        String docPath = conf.getProperty("document.path");
//        String fn = d.getNamaFizikal();
//        LOGGER.info("fn ::" + fn);
//        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
//        LOGGER.info("new FileInputStream(f) : " + f.getPath());
//        iframeURL = f.getAbsolutePath().toString();
//        if (f != null) {
//            try {
//                return new StreamingResolution(d.getFormat(), new FileInputStream(f));
//            } catch (FileNotFoundException ex) {
//                LOGGER.error(ex);
//            }
//        }
        return new JSP("pengambilan/view_brgM.jsp").addParameter("tab", "true");
    }

        public void genReport2() {
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String gen = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "ACQDocM_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "ACQDocM_NS.rdf";
        }
        String code = "M";

        try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            LOGGER.error("Error : " + ex);
        }
        LOGGER.info("genReport :: finish");
        rehydrate();
    }


    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

}

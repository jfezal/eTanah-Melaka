/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/isytihar_jual")
public class PrisytiharanJualanActionBean extends AbleActionBean {

    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
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
    private String idFolder;
    private boolean view;
    private long idDokumen;
    private String perihal;
    private String perihalBI;
    private List<Lelongan> listLelongan;
    private static final Logger LOGGER = Logger.getLogger(PrisytiharanJualanActionBean.class);
    private Enkuiri enkuiri;
    private boolean negeri = false;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("semak16H", Boolean.TRUE);
        FasaPermohonan ff = lelongService.findFasaPermohonanSemakPembida(idPermohonan);
        if (ff != null) {
            List<KandunganFolder> listKD = lelongService.getListDokumenIystiharJual(permohonan.getFolderDokumen().getFolderId());
            if (listKD.isEmpty()) {
                if (enkuiri.getCaraLelong().equals("A")) {
                    List<Lelongan> listL = lelongService.getLeloganNotInAK2(idPermohonan);
                    if (!listL.isEmpty()) {
                        int bil = listL.get(0).getBil();
                        for (Lelongan ll : listL) {
                            if (ll.getBil() == bil) {
                                for (Lelongan le : listLelongan) {
                                    if (le.getHakmilikPermohonan().equals(ll.getHakmilikPermohonan())) {
                                        lelongService.saveOrUpdate(le);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                genReport();
            }
        }
        if (enkuiri.getCaraLelong().equals("B")) {
            if (enkuiri.getPerihalTanah() == null) {
                getContext().getRequest().setAttribute("B", Boolean.TRUE);
                getContext().getRequest().setAttribute("A", Boolean.FALSE);
                return new JSP("lelong/perihal_tanah.jsp").addParameter("tab", "true");
            } else {
                return new JSP("lelong/isytihar_jualan.jsp").addParameter("tab", "true");
            }
        }
        if (enkuiri.getCaraLelong().equals("A")) {
            listLelongan = lelongService.listLelonganAK(idPermohonan);
            for (Lelongan ll : listLelongan) {
                if (ll.getPerihalTanah() == null) {
                    getContext().getRequest().setAttribute("A", Boolean.TRUE);
                    getContext().getRequest().setAttribute("B", Boolean.FALSE);
                    return new JSP("lelong/perihal_tanah.jsp").addParameter("tab", "true");
                }
            }
        }
        return new JSP("lelong/isytihar_jualan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("semak16H", Boolean.FALSE);
        return new JSP("lelong/isytihar_jualan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());

        if (permohonan.getPermohonanSebelum() == null) {
            enkuiri = lelongService.findEnkuiri(idPermohonan);
        } else {
            enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (enkuiri == null) {
                List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (!list3.isEmpty()) {
                    enkuiri = list3.get(0);
                }
            }
        }

        LOGGER.info("----IsytiharJual----");

        if (enkuiri.getCaraLelong().equals("B")) {
            if (enkuiri.getPerihalTanah() == null) {
                getContext().getRequest().setAttribute("B", Boolean.TRUE);
                getContext().getRequest().setAttribute("A", Boolean.FALSE);
            }
        }
        if (enkuiri.getCaraLelong().equals("A")) {
            if (enkuiri.getPerihalTanah() == null) {
                listLelongan = lelongService.listLelonganAK(idPermohonan);
                getContext().getRequest().setAttribute("A", Boolean.TRUE);
                getContext().getRequest().setAttribute("B", Boolean.FALSE);
            }
        }

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            reportName = "LLGIsytiharJual_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //n9
            negeri = true;
            reportName = "LLGIsytiharJual_NS.rdf";
        }

        LOGGER.info("reportName : " + reportName);
        List<KandunganFolder> listKandunganFolder = lelongService.getListDokumenIystiharJual(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder.isEmpty()) {
            for (KandunganFolder ff : listKandunganFolder) {
                if (ff.getDokumen().getKodDokumen().getKod().equals("PJ")) {
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
//            janaReport();
        }
    }

    public Resolution simpanPerihal() {
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        if (enkuiri.getCaraLelong().equals("B")) {
            enkuiri.setPerihalTanah(perihal);
            enkuiri.setPerihalTanahBI(perihalBI);
            lelongService.saveOrUpdate(enkuiri);
        }
        if (enkuiri.getCaraLelong().equals("A")) {
            for (Lelongan ll : listLelongan) {
                lelongService.saveOrUpdate(ll);
            }
        }
        genReport();
        showForm();
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution genReport() {
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");

        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "LLGIsytiharJual_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "LLGIsytiharJual_NS.rdf";
        }
        String code = "PJ";
        try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            LOGGER.error("Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOGGER.info("genReport :: finish");
        rehydrate();
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution kembali() {

        if (enkuiri.getCaraLelong().equals("B")) {
            perihal = enkuiri.getPerihalTanah();
            perihalBI = enkuiri.getPerihalTanahBI();
//            enkuiri.setPerihalTanah(null);
//            enkuiri.setPerihalTanahBI(null);
//            lelongService.saveOrUpdate(enkuiri);
            getContext().getRequest().setAttribute("B", Boolean.TRUE);
            getContext().getRequest().setAttribute("A", Boolean.FALSE);
        }
        if (enkuiri.getCaraLelong().equals("A")) {
            listLelongan = lelongService.listLelonganAK(idPermohonan);
//            for (Lelongan ll : listLelongan) {
//                ll.setPerihalTanah(null);
//                ll.setPerihalTanahBahasaInggeris(null);
//                lelongService.saveOrUpdate(ll);
//            }
            getContext().getRequest().setAttribute("A", Boolean.TRUE);
            getContext().getRequest().setAttribute("B", Boolean.FALSE);
        }
        return new JSP("lelong/perihal_tanah.jsp").addParameter("tab", "true");
    }

    //sebelum jana
    public Resolution janaReport() {
        LOGGER.info("------sebelum jana------");
        String add = conf.getProperty("report.server.location");
        InetAddress address = null;
        try {
            address = InetAddress.getByName(lelongService.getIP(add));
            SERVER_LOCATION = lelongService.replaceDNS(address.getHostAddress(), add);
        } catch (UnknownHostException ex) {
            LOGGER.info("Error-" + ex);
        }
        LOGGER.info("SERVER_LOCATION : " + SERVER_LOCATION);
        reportKey = conf.getProperty("report.key");
        StringBuilder cmd = new StringBuilder(SERVER_LOCATION).append("?").append(reportKey).append("&").append(reportName).append("&").append("p_id_mohon").append("=").append(idPermohonan);
        LOGGER.info("SERVER REPORT : " + cmd);
        iframeURL = cmd.toString();
        getContext().getRequest().setAttribute("jana", Boolean.TRUE);
        getContext().getRequest().setAttribute("lulus", Boolean.FALSE);
        return new JSP("lelong/isytihar_jualan.jsp").addParameter("tab", "true");
    }

    //selepas jana
    public Resolution printReport() {
        LOGGER.info("------selepas jana------");
        String docPath = conf.getProperty("document.path");
        StringBuilder cmd = new StringBuilder(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + dokumen.getNamaFizikal() + strService.getFormat(dokumen.getFormat()));
        iframeURL = cmd.toString();
        LOGGER.info("iframeURL : " + iframeURL);
        return new JSP("lelong/isytihar_jualan.jsp").addParameter("tab", "true");
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
//        if (f != null) {
//            try {
//                return new StreamingResolution(d.getFormat(), new FileInputStream(f));
//            } catch (FileNotFoundException ex) {
//                LOGGER.error(ex);
//            }
//        }
        return new JSP("lelong/isytihar_jualan.jsp").addParameter("tab", "true");
    }

    public String getIframeURL() {
        return iframeURL;
    }

    public void setIframeURL(String iframeURL) {
        this.iframeURL = iframeURL;
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

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public List<Lelongan> getListLelongan() {
        return listLelongan;
    }

    public void setListLelongan(List<Lelongan> listLelongan) {
        this.listLelongan = listLelongan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getPerihalBI() {
        return perihalBI;
    }

    public void setPerihalBI(String perihalBI) {
        this.perihalBI = perihalBI;
    }

    public boolean isNegeri() {
        return negeri;
    }

    public void setNegeri(boolean negeri) {
        this.negeri = negeri;
    }
}

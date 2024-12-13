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
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.Lelongan;
import etanah.model.Pembida;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.service.common.EnkuiriService;
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
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/printah_jual_melaka")
public class PerintahJualMelakaActionBean extends AbleActionBean {

    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService enService;
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
    private long idLelong;
    private static final Logger LOGGER = Logger.getLogger(PerintahJualMelakaActionBean.class);
    private List<FasaPermohonan> fasaList;
    private List<FasaPermohonan> fasaListJLB;
    private FasaPermohonan fasaPermohonan;
    private List<Lelongan> listLelong;
    private List<Pembida> senaraiPembida;
    private Enkuiri enkuiri;
    private boolean show;

    @DefaultHandler
    public Resolution showForm() {
        LOGGER.info("---showForm---");
        
        
        if (!fasaList.isEmpty() || (!fasaListJLB.isEmpty())) {
            if (!fasaList.isEmpty()) {
                fasaPermohonan = fasaList.get(0);
            } else {
                if (!fasaListJLB.isEmpty()) {
                    fasaPermohonan = fasaListJLB.get(0);
                }
            }

//        if (!fasaList.isEmpty() || (!fasaListJLB.isEmpty())) {
//            fasaPermohonan = fasaList.get(0);
//            fasaPermohonan = fasaListJLB.get(0);
            if (fasaPermohonan.getKeputusan() != null) {
//                FasaPermohonan ff = lelongService.findFasaPermohonanSemak16H(idPermohonan);
                FasaPermohonan ff = null;
                if (permohonan.getPermohonanSebelum() == null) {
                    ff = lelongService.findFasaPermohonanSemak16H(idPermohonan);
                } else {
                    ff = lelongService.findFasaPermohonanSemak16H(permohonan.getPermohonanSebelum().getIdPermohonan());
                }
                if (ff.getKeputusan().getKod().equals("PH")) {
                    if (fasaPermohonan.getKeputusan().getKod().equals("AP")) {
                        show = true;
                        if (view == false) {
                            genReport2();
                        }
                    }
                    String ade = null;
                    if (fasaPermohonan.getKeputusan().getKod().equals("TB")) {
                        listLelong = lelongService.listLelonganAKAP(idPermohonan);
                        if ("05".equals(conf.getProperty("kodNegeri"))) {
                            LOGGER.info("---showForm n9---");
                            for (Lelongan ll : listLelong) {
                                if (ll.getPembida() != null) {
                                    ade = "ade";
                                    break;
                                }
                            }
                        }
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            LOGGER.info("---showForm mlk---");
                            for (Lelongan lll : listLelong) {
                                idLelong = lll.getIdLelong();
                                senaraiPembida = enService.getListPembida(idLelong);
                                for (Pembida bid : senaraiPembida) {
//                                    if (bid.getPihak() != null) {
                                     if (bid.getPihak() != null && bid.getKodStsPembida().getKod().equals("BJ")) {
                                        ade = "ade";
                                        break;
                                    }
                                }
                            }
                        }
                        if (!StringUtils.isEmpty(ade)) {
                            if (ade.equals("ade")) {
                                show = true;
                                if (view == false) {
                                    genReport2();
                                }
                            }
                        } else {
                            addSimpleMessage("Keputusan Adalah Tiada Pembida.Sila Klik Butang Selesai.");
                        }
                    }
                } else {
                    show = false;
                    if (fasaPermohonan.getKeputusan().getKod().equals("TB")) {
                        String ade = null;
                        listLelong = lelongService.listLelonganAKAP(idPermohonan);
                        if ("05".equals(conf.getProperty("kodNegeri"))) {
                            LOGGER.info("---showForm n9---");
                            for (Lelongan ll : listLelong) {
                                if (ll.getPembida() != null) {
                                    ade = "ade";
                                    break;
                                }
                            }
                        }

                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            LOGGER.info("---showForm mlk---");
                            for (Lelongan lll : listLelong) {
                                idLelong = lll.getIdLelong();
                                senaraiPembida = enService.getListPembida(idLelong);
                                for (Pembida bid : senaraiPembida) {
                                    if (bid.getPihak() != null) {
                                        ade = "ade";
                                        break;
                                    }
                                }
                            }
                        }
                        if (!StringUtils.isEmpty(ade)) {
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                //melaka
                                if (ade.equals("ade")) {
                                    show = true;
                                    if (view == false) {
                                        genReport2();
                                    }
                                }
                            }
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                //n9
                                addSimpleMessage("Jurulelong Adalah Jurulelong Berlesen. Sila Muat Naik Kontrak Jualan Dari Pelelong Berlesen");
                            }

                        } else {
                            addSimpleMessage("Keputusan Adalah Tiada Pembida.Sila Klik Butang Selesai.");
                        }
                    } else {
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            //melaka
                            show = true;
                            if (view == false) {
                                genReport2();
                            }
                        }
                        if ("05".equals(conf.getProperty("kodNegeri"))) {
                            //n9
                            addSimpleMessage("Jurulelong Adalah Jurulelong Berlesen. Sila Muat Naik Kontrak Jualan Dari Pelelong Berlesen");
                        }
                    }
                }

            } else {
                show = false;
                addSimpleError("Sila Masukkan Keputusan Terlebih Dahulu Di Tab Kemasukan Rekod Bidaan");
            }
        } else {
            show = false;
            addSimpleError("Sila Masukkan Keputusan Terlebih Dahulu Di Tab Kemasukan Rekod Bidaan");
        }
        getContext().getRequest().setAttribute("MEMO", Boolean.TRUE);
        return new JSP("lelong/printah_jualan_melaka.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("MEMO", Boolean.FALSE);
        show = true;
        return new JSP("lelong/printah_jualan_melaka.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        fasaList = lelongService.getPermonanFasaRekodBidaan(idPermohonan);
        fasaListJLB = lelongService.getPermonanFasaRekodBidaanJLB(idPermohonan);
        if (!fasaList.isEmpty() || (!fasaListJLB.isEmpty())) {

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                reportName = "LLGMemoPerjanjianJualBeli_MLK.rdf";
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                reportName = "LLGKontrakJualan_NS.rdf";
            }
            List<KandunganFolder> listKandunganFolder = lelongService.getListDokumenMEMO(permohonan.getFolderDokumen().getFolderId());
            if (!listKandunganFolder.isEmpty()) {
                for (KandunganFolder ff : listKandunganFolder) {
                    if (ff.getDokumen().getKodDokumen().getKod().equals("MEMO")) {
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
//                janaReport();
            }
        }

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
            LOGGER.info("---error---" + ex);
        }
        LOGGER.info("SERVER_LOCATION : " + SERVER_LOCATION);
        reportKey = conf.getProperty("report.key");
        StringBuilder cmd = new StringBuilder(SERVER_LOCATION).append("?").append(reportKey).append("&").append(reportName).append("&").append("p_id_mohon").append("=").append(idPermohonan);
        LOGGER.info("SERVER REPORT : " + cmd);
        iframeURL = cmd.toString();
        getContext().getRequest().setAttribute("jana", Boolean.TRUE);
        getContext().getRequest().setAttribute("lulus", Boolean.FALSE);
        return new JSP("lelong/printah_jualan_melaka.jsp").addParameter("tab", "true");
    }

    //selepas jana
    public Resolution printReport() {
        LOGGER.info("------selepas jana------");
        String docPath = conf.getProperty("document.path");
        StringBuilder cmd = new StringBuilder(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + dokumen.getNamaFizikal() + strService.getFormat(dokumen.getFormat()));
        iframeURL = cmd.toString();
        LOGGER.info("iframeURL : " + iframeURL);
        return new JSP("lelong/printah_jualan_melaka.jsp").addParameter("tab", "true");
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
        return new JSP("lelong/printah_jualan_melaka.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "LLGMemoPerjanjianJualBeli_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "LLGKontrakJualan_NS.rdf";
        }
        String code = "MEMO";
        try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOGGER.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public void genReport2() {
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String gen = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "LLGMemoPerjanjianJualBeli_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "LLGKontrakJualan_NS.rdf";
        }
        String code = "MEMO";
        try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            LOGGER.error("Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOGGER.info("genReport :: finish");
        rehydrate();
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

    public List<FasaPermohonan> getFasaList() {
        return fasaList;
    }

    public void setFasaList(List<FasaPermohonan> fasaList) {
        this.fasaList = fasaList;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<Lelongan> getListLelong() {
        return listLelong;
    }

    public void setListLelong(List<Lelongan> listLelong) {
        this.listLelong = listLelong;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public long getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(long idLelong) {
        this.idLelong = idLelong;
    }

    public List<Pembida> getSenaraiPembida() {
        return senaraiPembida;
    }

    public void setSenaraiPembida(List<Pembida> senaraiPembida) {
        this.senaraiPembida = senaraiPembida;
    }

    public List<FasaPermohonan> getFasaListJLB() {
        return fasaListJLB;
    }

    public void setFasaListJLB(List<FasaPermohonan> fasaListJLB) {
        this.fasaListJLB = fasaListJLB;
    }
}

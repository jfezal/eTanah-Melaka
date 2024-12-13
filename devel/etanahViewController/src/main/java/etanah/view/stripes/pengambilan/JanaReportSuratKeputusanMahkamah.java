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
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodKeputusan;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.PengambilanService;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.NotisPenerimaanService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
 * @author massita
 */
@UrlBinding("/pengambilan/KeputusanPerbicaraan")
public class JanaReportSuratKeputusanMahkamah extends AbleActionBean {

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
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanService pengambilanService;
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
    private List<Notis> listNotis;
    private String tarikhWarta;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private static final Logger LOGGER = Logger.getLogger(JanaReportSuratKeputusanMahkamah.class);
    private boolean mlk = false;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;

    @DefaultHandler
    public Resolution maklumKeputusanBicara() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        FasaPermohonan fasaPermohonan = pengambilanService.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "14KeputusanBicara");
        FasaPermohonan fasaPermohonan2 = pengambilanService.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "13KeputusanBicara");
        if (fasaPermohonan != null) {
            //  if (fasaPermohonan.getKeputusan().getKod() != null ) {
            //  KodKeputusan kodKeputusan = fasaPermohonan.getKeputusan();
            if (fasaPermohonan.getKeputusan() != null) {
                if (fasaPermohonan.getKeputusan().getKod().equalsIgnoreCase("L")) {
                    getContext().getRequest().setAttribute("HideJanaReport", Boolean.TRUE);
                    getContext().getRequest().setAttribute("JanaReport", Boolean.FALSE);
                } else {
                    getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                }

                if (fasaPermohonan.getKeputusan().getKod().equalsIgnoreCase("TL")) {
                    getContext().getRequest().setAttribute("JanaReport", Boolean.TRUE);
                    getContext().getRequest().setAttribute("HideJanaReport", Boolean.FALSE);
                } else {
                    getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                }
            } else {
                addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                getContext().getRequest().setAttribute("form1", Boolean.FALSE);

            }
        }


        //  }
//               else {
//                        addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
//                        getContext().getRequest().setAttribute("form1", Boolean.FALSE);
//                    }

//        FasaPermohonan fasaPermohonan2 = pengambilanService.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "14KeputusanBicara");


        if (fasaPermohonan2 != null) {
            if (fasaPermohonan2.getKeputusan() != null) {
                KodKeputusan kodKeputusan2 = fasaPermohonan2.getKeputusan();
                if (kodKeputusan2.getKod().equals("L")) {
                    getContext().getRequest().setAttribute("form2", Boolean.TRUE);
                    getContext().getRequest().setAttribute("lulus", Boolean.TRUE);
                }

                if (kodKeputusan2.getKod().equals("TL")) {
                    getContext().getRequest().setAttribute("form2", Boolean.TRUE);
                    getContext().getRequest().setAttribute("tidak_Lengkap", Boolean.TRUE);
                }
            } else {
                addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                //  getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                getContext().getRequest().setAttribute("form2", Boolean.FALSE);
            }
        }
//        else {
//            LOGGER.info("masukkkk snin");
//            addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
//            getContext().getRequest().setAttribute("form1", Boolean.FALSE);
//            getContext().getRequest().setAttribute("form2", Boolean.FALSE);
//        }

        return new JSP("pengambilan/negerisembilan/pendudukansementara/maklumKeputusanBicara.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }

    public Resolution genReport() {
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "ACQSuratKeputusanMahkamah_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "ACQSuratKeputusanMahkamah.rdf";
        }
        String code = "KMD";

        try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOGGER.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
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

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public boolean isMlk() {
        return mlk;
    }

    public void setMlk(boolean mlk) {
        this.mlk = mlk;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }
}

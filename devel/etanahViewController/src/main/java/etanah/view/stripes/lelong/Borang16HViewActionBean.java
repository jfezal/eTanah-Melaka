/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodNotis;
import etanah.model.Notis;
import etanah.model.PenasihatUndang;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
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
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/view_16H")
public class Borang16HViewActionBean extends AbleActionBean {

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
    private List<Notis> listNotis;
    private String tarikhWarta;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private static final Logger LOGGER = Logger.getLogger(Borang16HViewActionBean.class);
    private boolean mlk = false;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("semak16H", Boolean.FALSE);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            mlk = true;
            for (Notis nn : listNotis) {
                if (nn.getNoRujukan() == null) {
                    return new JSP("lelong/tarikh_warta.jsp").addParameter("tab", "true");
                }
            }
        }
        return new JSP("lelong/view_16H.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("semak16H", Boolean.TRUE);
        return new JSP("lelong/view_16H.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        List<PermohonanPihak> listHP = lelongService.getSenaraiPmohonPihak(idPermohonan);
        Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
        for (PermohonanPihak hp : listHP) {
            Long id = hp.getPihak().getIdPihak();
            if (pihakMap.containsKey(id)) {
                continue;
            } else {
                pihakMap.put(id, hp);
            }
        }
        senaraiPermohonanPihak = new ArrayList(pihakMap.values());
        listNotis = lelongService.getListNotis2(idPermohonan, "16H");
        LOGGER.info("listNotis : " + listNotis.size());
        if (listNotis.isEmpty()) {
            LOGGER.info("Belum ade lg...");
            simpanNotis();
            listNotis = lelongService.getListNotis2(idPermohonan, "16H");
        }

        LOGGER.info("----16H----");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            reportName = "LLGBorang16HPenyerah_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            reportName = "LLGBorang16HPenyerah_NS.rdf";
        }
        LOGGER.info("reportName : " + reportName);
        List<KandunganFolder> listKandunganFolder = lelongService.getListDokumen16H(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder.isEmpty()) {
            for (KandunganFolder ff : listKandunganFolder) {
                if (ff.getDokumen().getKodDokumen().getKod().equals("16H")) {
                    dokumen = ff.getDokumen();
                    break;
                }
            }
        }

        if (dokumen != null && dokumen.getNamaFizikal() != null) {
            idDokumen = dokumen.getIdDokumen();
            view = true;
            viewPdf();
        } else {
            view = false;
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                genReport2();
            }
        }
    }

    public void simpanNotis() {

        Dokumen dokumen2 = null;
        KodNotis kodNotis = kodNotisDAO.findById("16H");
        KodDokumen kd = kodDokumenDAO.findById("16H");
        List<KandunganFolder> listFD = lelongService.getListDokumen16H(permohonan.getFolderDokumen().getFolderId());
        if (!listFD.isEmpty()) {
            dokumen2 = listFD.get(0).getDokumen();
        } else {
            dokumen2 = lelongService.saveOrUpdateDokumen(permohonan.getFolderDokumen(), kd, idPermohonan, pengguna);
        }

        LOGGER.info("kodNotis : " + kodNotis.getKod());

        InfoAudit info = pengguna.getInfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());

        List<String> listIDHakmilik = new ArrayList<String>();
        for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
            listIDHakmilik.add(hp.getHakmilik().getIdHakmilik());
        }
        LOGGER.info("listIDHakmilik : " + listIDHakmilik.size());
        List<HakmilikPihakBerkepentingan> listHP = lelongService.getHakmilikPihakALL(idPermohonan, listIDHakmilik);
        Map<Long, HakmilikPihakBerkepentingan> pihakMap2 = new HashMap<Long, HakmilikPihakBerkepentingan>();
        for (HakmilikPihakBerkepentingan hp : listHP) {
            Long id = hp.getPihak().getIdPihak();
            if (pihakMap2.containsKey(id)) {
                continue;
            } else {
                pihakMap2.put(id, hp);
            }
        }
        senaraiHakmilikPihak = new ArrayList(pihakMap2.values());
        LOGGER.info("senaraiPermohonanPihak : " + senaraiHakmilikPihak.size());

//        if (conf.getProperty("kodNegeri").equals("04")) {
//            //melaka
//            PenasihatUndang penUndang = lelongService.getALLPenUndg();
//            Notis notis2 = new Notis();
//            notis2.setPermohonan(permohonan);
//            notis2.setInfoAudit(info);
//            notis2.setTarikhNotis(new java.util.Date());
//            notis2.setKodNotis(kodNotis);
//            notis2.setDokumenNotis(dokumen2);
//            notis2.setPenasihatUndang(penUndang);
//            notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
//            lelongService.saveOrUpdate(notis2);
//        }
        List<PermohonanAtasPerserahan> listPAP = lelongService.getPermohonanAtasPerserahan(idPermohonan);
        PermohonanAtasPerserahan pAP = listPAP.get(0);
        if (pAP.getUrusan().getKodUrusan().getKod().equals("GD") || pAP.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihakPG(idPermohonan);
            for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {

                if (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PM")) {
                    PermohonanPihak pPihak = null;
                    long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                    for (PermohonanPihak pp : listPP) {
                        if (pp.getPihak().getIdPihak() == idPihak) {
                            pPihak = pp;
                            break;
                        }
                    }
                    LOGGER.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    Notis notis2 = new Notis();
                    notis2.setPermohonan(permohonan);
                    notis2.setInfoAudit(info);
                    if (pPihak != null) {
                        notis2.setPihak(pPihak);
                    }
                    notis2.setTarikhNotis(new java.util.Date());
                    notis2.setKodNotis(kodNotis);
                    notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                    notis2.setDokumenNotis(dokumen2);
                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                    lelongService.saveOrUpdate(notis2);
                }
            }
            Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
            listPP = lelongService.getSenaraiPmohonPihakPM(idPermohonan);
            if (!listPP.isEmpty()) {
                for (PermohonanPihak hp : listPP) {
                    Long id = hp.getPihak().getIdPihak();
                    if (pihakMap.containsKey(id)) {
                        continue;
                    } else {
                        pihakMap.put(id, hp);
                    }
                }
                listPP = new ArrayList(pihakMap.values());
                for (PermohonanPihak pp : listPP) {
                    HakmilikPihakBerkepentingan hb = null;
                    long idPihak = pp.getPihak().getIdPihak();
                    for (int k = 0; k < senaraiHakmilikPihak.size(); k++) {
                        if (senaraiHakmilikPihak.get(k).getPihak().getIdPihak() == idPihak) {
                            hb = senaraiHakmilikPihak.get(k);
                            break;
                        }
                    }
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    Notis notis2 = new Notis();
                    notis2.setPermohonan(permohonan);
                    notis2.setInfoAudit(info);
                    notis2.setPihak(pp);
                    notis2.setTarikhNotis(new java.util.Date());
                    notis2.setKodNotis(kodNotis);
                    notis2.setHakmilikPihakBerkepentingan(hb);
                    notis2.setDokumenNotis(dokumen2);
                    notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                    lelongService.saveOrUpdate(notis2);
                }
            }
        }

        if (pAP.getUrusan().getKodUrusan().getKod().equals("GDPJ") || pAP.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(idPermohonan);
            for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {

                PermohonanPihak pPihak = null;
                long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                for (PermohonanPihak pp : listPP) {
                    if (pp.getPihak().getIdPihak() == idPihak) {
                        pPihak = pp;
                        break;
                    }
                }
                LOGGER.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                Notis notis2 = new Notis();
                notis2.setPermohonan(permohonan);
                notis2.setInfoAudit(info);
                if (pPihak != null) {
                    notis2.setPihak(pPihak);
                }
                notis2.setTarikhNotis(new java.util.Date());
                notis2.setKodNotis(kodNotis);
                notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                notis2.setDokumenNotis(dokumen2);
                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                lelongService.saveOrUpdate(notis2);
            }
        }
    }

    public Resolution simpanTarikh() {
        for (Notis nn : listNotis) {
            nn.setNoRujukan(tarikhWarta);
            lelongService.saveOrUpdate(nn);
        }
        genReport2();
        showForm();
        getContext().getRequest().setAttribute("semak16H", Boolean.FALSE);
        return new JSP("lelong/view_16H.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskini() {
        for (Notis nn : listNotis) {
            nn.setNoRujukan(null);
            lelongService.saveOrUpdate(nn);
        }
        return new JSP("lelong/tarikh_warta.jsp").addParameter("tab", "true");
    }

    //sebelum jana
    public Resolution janaReport() {
        LOGGER.info("------sebelum jana------");
        String add = conf.getProperty("report.server.location");
        LOGGER.info("report.server.location : " + add);
        InetAddress address = null;
        try {
            address = InetAddress.getByName(lelongService.getIP(add));
            LOGGER.info("host name : " + lelongService.getIP(add));
            LOGGER.info("ip address : " + address.getHostAddress());
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
        return new JSP("lelong/view_16H.jsp").addParameter("tab", "true");
    }

    //selepas jana
    public Resolution printReport() {
        LOGGER.info("------selepas jana------");
        String docPath = conf.getProperty("document.path");
        StringBuilder cmd = new StringBuilder(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + dokumen.getNamaFizikal() + strService.getFormat(dokumen.getFormat()));
        iframeURL = cmd.toString();
        LOGGER.info("iframeURL : " + iframeURL);
        return new JSP("lelong/view_16H.jsp").addParameter("tab", "true");
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
        return new JSP("lelong/view_16H.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String gen2 = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "LLGBorang16HPenyerah_MLK.rdf";
            gen2 = "LLGBorang16H_MLK.rdf";
            LOGGER.info("report = " + gen);
            LOGGER.info("report = " + gen2);
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "LLGBorang16HPenyerah_NS.rdf";
        }
        try {
            if (gen.equals("LLGBorang16HPenyerah_MLK.rdf")) {
                String code = "16H";
                LOGGER.info("genReportFromXML");
                lelongService.reportGen(permohonan, gen, code, pengguna);
            }
            if (gen2.equals("LLGBorang16H_MLK.rdf")) {
                String code1 = "16H1";
                LOGGER.info("genReportFromXML");
                lelongService.reportGen(permohonan, gen2, code1, pengguna);
            }
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
            gen = "LLGBorang16HPenyerah_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "LLGBorang16HPenyerah_NS.rdf";
        }
        String code = "16H";

        try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            LOGGER.error("Error : " + ex);
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

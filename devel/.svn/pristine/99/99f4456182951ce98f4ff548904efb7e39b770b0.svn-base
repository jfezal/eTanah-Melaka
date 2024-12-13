/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.laporan;

import able.stripes.AbleActionBean;
import etanah.view.etanahActionBeanContext;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import java.util.*;
import etanah.service.*;
import etanah.service.common.*;
import etanah.service.common.HakmilikService;
import etanah.report.ReportUtil;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import java.io.File;
import able.stripes.JSP;
import org.hibernate.Session;

@UrlBinding("/strata/Geranstrata2K3K")
public class Geran2K3KStrataActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Geran2K3KStrataActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikTukarGantiStrataDAO hakmilikTukarGantiStrataDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    DokumenCapaianService dokumenCapaianService;
    private List<DokumenCapaian> senaraiDokumenCapai;
    private List<KandunganFolder> senaraiKandunganFolder = new ArrayList<KandunganFolder>();
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    private String idHakmilikInduk;
    private String idHakmilikStrata;
    private String kodnegeri;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    StrataPtService strataPtService;
    @Inject
    PenggunaDAO penggunaDAO;
    private List<Hakmilik> senaraiHakmilik;
    private List<Hakmilik> senaraiHakmilikStrata;
    private List<Dokumen> senaraiDokumen = new ArrayList();
    private String sbb_cetakan_semula;

    @DefaultHandler
    public Resolution showForm() {
//        kodnegeri = conf.getProperty("kodNegeri");
        LOG.info("showForm");
        idHakmilikInduk = null;
        idHakmilikStrata = null;
        getContext().getRequest().setAttribute("versi", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strata2K3K.jsp");
    }

    public Resolution simpanSebab() {
        LOG.info("simpanSebab");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik------" + idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strata.jsp");
    }

    public Resolution cetakSemula() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String sbbCetakanSemula = getContext().getRequest().getParameter("sbb_cetakan_semula");
        String idDokumen = getContext().getRequest().getParameter("id_dokumen");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        Dokumen d = dokumenDAO.findById(Long.parseLong(idDokumen));

        if (d == null) {
            return new StreamingResolution("text/plain", "2");
        }


        DokumenCapaian dc = new DokumenCapaian();


        dc.setAlasan("Cetakan Dokumen");
        dc.setDokumen(d);
        dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        dc.setInfoAudit(ia);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        dokumenCapaianDAO.save(dc);

        Dokumen dk = strataPtService.findDokumen(dc.getDokumen().getIdDokumen());
        if (!idHakmilik.isEmpty()) {
            List<HakmilikTukarGantiStrata> hmTG = strataPtService.findHmStrTGbyInduk(idHakmilik);

            for (HakmilikTukarGantiStrata hmTGStr : hmTG) {
                hmTGStr.setTarikhCetak2k(new java.util.Date());
                strataPtService.simpanHmTukarGantiStrata(hmTGStr);
            }
        }
        tx.commit();

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution cari() {

        idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
        idHakmilikStrata = getContext().getRequest().getParameter("idHakmilikStrata");

        sbb_cetakan_semula = null;

        LOG.info("idHakmilikInduk" + idHakmilikInduk);
        LOG.info("idHakmilikStrata" + idHakmilikStrata);

        Hakmilik hk = hakmilikService.findById(idHakmilikInduk);

        if (hk.getKodStatusHakmilik().getKod().equals("D")) {
            if (hk.getNoVersiIndeksStrata() != null) {
                if (hk.getNoVersiIndeksStrata() != 0) {
                    idHakmilikInduk = null;
                    addSimpleError("Borang 2K Dan 3K Untuk Id Hakmilik Ini Sudah Ditukarganti.");
                    getContext().getRequest().setAttribute("versi", Boolean.TRUE);
                } else {
                    getContext().getRequest().setAttribute("versi", Boolean.TRUE);
                }
            } else {
                getContext().getRequest().setAttribute("versi", Boolean.TRUE);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strata2K3K.jsp");
    }

    public Resolution jana() {


        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        ctx.getRequest().setAttribute("tanpaBayaran", true);

        idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
        String documentPath = conf.getProperty("document.path");

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();

        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        Hakmilik master = hakmilikService.findById(idHakmilikInduk);
        senaraiHakmilikStrata = strataPtService.findIdHakmilikByIdHakmilikInduk(idHakmilikInduk);

        if (master != null) {
            master.setNoVersiIndeksStrata(1);
            master.setVersion(1);
            hakmilikService.saveHakmilik(master);
        }
        for (Hakmilik hk : senaraiHakmilikStrata) {
            LOG.info("id hakmilik update versi " + hk.getIdHakmilik());
            hk.setNoVersiIndeksStrata(1);
            hk.setVersion(1);
            hakmilikService.saveHakmilik(hk);

            HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(hk.getIdHakmilik());
            if (hmTG != null) {
                hmTG.setTarikhTukarganti2k(now);
                hmTG.setVersi2k(1);
                strataPtService.simpanHmTukarGantiStrata(hmTG);
            } else {
                HakmilikTukarGantiStrata HmSTG = new HakmilikTukarGantiStrata();
                HmSTG.setHakmilikInduk(master);
                HmSTG.setHakmilikStrata(hk.getIdHakmilik());
                HmSTG.setInfoAudit(ia);
                HmSTG.setTarikhTukarganti2k(now);
                HmSTG.setVersi2k(1);
                strataPtService.simpanHmTukarGantiStrata(HmSTG);
            }
        }

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        //  try {
        FolderDokumen fd = new FolderDokumen();
        fd.setTajuk("-");
        fd.setInfoAudit(ia);
        folderDokumenDAO.save(fd);

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            for (int i = 0; i < Report_Name.length; i++) {
                // LOG.debug("id hakmilik =" + ch.getIdHakmilik());
                LOG.debug("SIZE REPORT =" + Report_Name.length);
                LOG.debug("SIZE REPORT =" + Report_Label.length);
                LOG.debug("name REPORT =" + Report_Name[i]);
                LOG.debug("label REPORT =" + Report_Label[i]);


                //   KodUrusan kodUrusan = kodUrusanDAO.findById("CRHMR");
                KodDokumen kodDokumen = null;
                String nameOfReport = "";
                kodDokumen = kodDokumenDAO.findById(Report_Label[i]);
                LOG.debug("kod dokumen =" + kodDokumen.getKod());
                nameOfReport = Report_Name[i];



                senaraiKandunganFolder = generateReport(kodDokumen, ia, documentPath, nameOfReport, fd);

            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            for (int i = 0; i < Report_NameNS.length; i++) {
                // LOG.debug("id hakmilik =" + ch.getIdHakmilik());
                LOG.debug("SIZE REPORT =" + Report_NameNS.length);
                LOG.debug("SIZE REPORT =" + Report_Label.length);
                LOG.debug("name REPORT =" + Report_NameNS[i]);
                LOG.debug("label REPORT =" + Report_Label[i]);


                //   KodUrusan kodUrusan = kodUrusanDAO.findById("CRHMR");
                KodDokumen kodDokumen = null;
                String nameOfReport = "";
                kodDokumen = kodDokumenDAO.findById(Report_Label[i]);
                LOG.debug("kod dokumen =" + kodDokumen.getKod());
                nameOfReport = Report_NameNS[i];



                senaraiKandunganFolder = generateReportNS(kodDokumen, ia, documentPath, nameOfReport, fd);

            }
        }


        tx.commit();

        List<Dokumen> ls2 = new ArrayList<Dokumen>();
        ls2 = strataPtService.findGeranStrata2k3k(idHakmilikInduk);
        for (Dokumen dok : ls2) {
            senaraiDokumen.add(dok);
        }


        addSimpleMessage("Borang 2K dan 3K Hakmilik Ini Telah Berjaya Ditukarganti ke Versi 1.");
        getContext().getRequest().setAttribute("versi", Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strata2K3K.jsp");

    }
    public static String[] Report_Name = {
        "UTILITIB2K_MLK.rdf",
        "UTILITIB3K_MLK.rdf"
    };
    public static String[] Report_NameNS = {
        "UTILITIB2K_NS.rdf",
        "UTILITIB3K_NS.rdf"
    };
    public static String[] Report_Label = {
        "2K",
        "3K"
    };

    private List<KandunganFolder> generateReport(KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, FolderDokumen fd) {

        String parameterToPass = "";
        String parameterToPass2 = "";
        String valueToPass = "";
        String valueToPass2 = "";
        //Added by Aizuddin seems to me parameter and value not pass correctly
        String[] params = null;
        String[] values = null;

        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

        //  List<CarianHakmilik> list = pc.getSenaraiHakmilik();
        if (StringUtils.isNotBlank(idHakmilikInduk)) {
//                Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(idHakmilikInduk);
//                LOG.info("~~~~~gune id hakmilik induk sahaja~~~~~" + hmInduk.getIdHakmilikInduk());
            parameterToPass = "p_id_hakmilik";
            valueToPass = idHakmilikInduk;

            Dokumen dokumenCarian = new Dokumen();
            dokumenCarian.setFormat("application/pdf");
            dokumenCarian.setInfoAudit(ia);
            dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
            dokumenCarian.setKodDokumen(kodDokumen);
            dokumenCarian.setNoVersi("1.0");
            dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
            dokumenCarian.setHakmilik(valueToPass);
            dokumenDAO.saveOrUpdate(dokumenCarian);
            LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
            KandunganFolder kf1 = new KandunganFolder();
            kf1.setFolder(fd);
            LOG.info("id_folder : " + fd.getFolderId());
            kf1.setDokumen(dokumenCarian);
            LOG.info("id_dokumen : " + dokumenCarian);
            kf1.setInfoAudit(ia);
            kandunganFolderDAO.save(kf1);
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                    + String.valueOf(dokumenCarian.getIdDokumen());
            LOG.info("path : " + path);
            reportUtil.generateReport(reportName,
                    new String[]{parameterToPass, parameterToPass2},
                    new String[]{valueToPass, valueToPass2},
                    path, ia.getDimasukOleh());
            LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
            dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
            dokumenDAO.update(dokumenCarian);
            LOG.info("Finishh~~~~~~" + reportName);
            senaraiKF.add(kf1);

        }

        LOG.info("senarai kandungan" + senaraiKF);
        LOG.info("senarai kandungan" + senaraiKF.size());
        return senaraiKF;
    }

    private List<KandunganFolder> generateReportNS(KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, FolderDokumen fd) {

        String parameterToPass = "";
        String parameterToPass2 = "";
        String valueToPass = "";
        String valueToPass2 = "";
        //Added by Aizuddin seems to me parameter and value not pass correctly
        String[] params = null;
        String[] values = null;

        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

        //  List<CarianHakmilik> list = pc.getSenaraiHakmilik();
        if (StringUtils.isNotBlank(idHakmilikInduk)) {
//                Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(idHakmilikInduk);
//                LOG.info("~~~~~gune id hakmilik induk sahaja~~~~~" + hmInduk.getIdHakmilikInduk());
            parameterToPass = "p_id_hakmilik";
            valueToPass = idHakmilikInduk;

            Dokumen dokumenCarian = new Dokumen();
            dokumenCarian.setFormat("application/pdf");
            dokumenCarian.setInfoAudit(ia);
            dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
            dokumenCarian.setKodDokumen(kodDokumen);
            dokumenCarian.setNoVersi("1.0");
            dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
            dokumenCarian.setHakmilik(valueToPass);
            dokumenDAO.saveOrUpdate(dokumenCarian);
            LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
            KandunganFolder kf1 = new KandunganFolder();
            kf1.setFolder(fd);
            LOG.info("id_folder : " + fd.getFolderId());
            kf1.setDokumen(dokumenCarian);
            LOG.info("id_dokumen : " + dokumenCarian);
            kf1.setInfoAudit(ia);
            kandunganFolderDAO.save(kf1);
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                    + String.valueOf(dokumenCarian.getIdDokumen());
            LOG.info("path : " + path);
            reportUtil.generateReport(reportName,
                    new String[]{parameterToPass, parameterToPass2},
                    new String[]{valueToPass, valueToPass2},
                    path, ia.getDimasukOleh());
            LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
            dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
            dokumenDAO.update(dokumenCarian);
            LOG.info("Finishh~~~~~~" + reportName);
            senaraiKF.add(kf1);

        }

        LOG.info("senarai kandungan" + senaraiKF);
        LOG.info("senarai kandungan" + senaraiKF.size());
        return senaraiKF;
    }

    public Resolution viewSejarahPaparan() {
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        senaraiDokumenCapai = dokumenCapaianService.findByIdDokumenAndPD(idDokumen);
        return new JSP("daftar/utiliti/view_sejarah_paparan.jsp").addParameter("popup", "true");
    }

    public String getIdHakmilikInduk() {
        return idHakmilikInduk;
    }

    public void setIdHakmilikInduk(String idHakmilikInduk) {
        this.idHakmilikInduk = idHakmilikInduk;
    }

    public String getIdHakmilikStrata() {
        return idHakmilikStrata;
    }

    public void setIdHakmilikStrata(String idHakmilikStrata) {
        this.idHakmilikStrata = idHakmilikStrata;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getSbb_cetakan_semula() {
        return sbb_cetakan_semula;
    }

    public void setSbb_cetakan_semula(String sbb_cetakan_semula) {
        this.sbb_cetakan_semula = sbb_cetakan_semula;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<DokumenCapaian> getSenaraiDokumenCapai() {
        return senaraiDokumenCapai;
    }

    public void setSenaraiDokumenCapai(List<DokumenCapaian> senaraiDokumenCapai) {
        this.senaraiDokumenCapai = senaraiDokumenCapai;
    }

    public List<KandunganFolder> getSenaraiKandunganFolder() {
        return senaraiKandunganFolder;
    }

    public void setSenaraiKandunganFolder(List<KandunganFolder> senaraiKandunganFolder) {
        this.senaraiKandunganFolder = senaraiKandunganFolder;
    }

    public List<Hakmilik> getSenaraiHakmilikStrata() {
        return senaraiHakmilikStrata;
    }

    public void setSenaraiHakmilikStrata(List<Hakmilik> senaraiHakmilikStrata) {
        this.senaraiHakmilikStrata = senaraiHakmilikStrata;
    }

    public String getKodnegeri() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodnegeri = "04";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodnegeri = "05";
        }
        return kodnegeri;
    }

    public void setKodnegeri(String kodnegeri) {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodnegeri = "04";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodnegeri = "05";
        }
        this.kodnegeri = kodnegeri;
    }
}

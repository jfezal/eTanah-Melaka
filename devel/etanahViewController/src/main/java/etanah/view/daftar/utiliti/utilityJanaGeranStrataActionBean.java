package etanah.view.daftar.utiliti;

import java.util.*;
import java.awt.print.PrinterJob;

import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.printing.PDFPageable;
import etanah.dao.*;
import java.io.File;
import etanah.model.*;
import etanah.report.*;
import org.hibernate.Session;
import java.util.concurrent.*;
import etanah.service.common.*;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import etanah.service.RegService;
import able.stripes.AbleActionBean;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.*;
import etanah.service.SemakDokumenService;
import org.apache.commons.lang.ArrayUtils;
import etanah.view.etanahActionBeanContext;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.util.WORMUtil;
import etanah.view.daftar.validator.HsbmPTValidation;
import etanah.view.etanahContextListener;
import etanah.workflow.WorkFlowService;
import java.io.IOException;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Transaction;

import etanah.model.FasaPermohonan;
import etanah.workflow.StageContext;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

/**
 * @author wazer 27 September 2018
 */
@HttpCache(allow = false)
@Wizard(startEvents = {"Step1", "delete", "janaTukarGanti"})
@UrlBinding("/daftar/utilityJanaGeranStrataActionBean")
public class utilityJanaGeranStrataActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiTukarGantActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    public static final String KOD_JABATAN_PENDAFTARAN = "16";
    public static final String KOD_DOKUMEN_AKUAN_PENERIMAAN = "UNKN1";
    private String kodUrusan;
    private String kodSerah;
    private String idPenyerah;
    private String penyerahNoPengenalan;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private String idMohon;
    private String idHakmilik;
    private String idHakmilik2;
    private String idHakmilikFirst;
    private Hakmilik idHakmilikFirst1;
    private String idSerah;
    private String idHakmilikDari;
    private String idHakmilikHingga;
    private String listHakmilikSiri;
    private boolean flag = false;
    private int bilHakmilik = 5;
    private int bilHakmilikSiri = 0;
    private KodUrusan ku;
    private Pengguna pguna;
    private KodPenyerah penyerahKod;
    private KodNegeri penyerahNegeri;
    private KodJenisPengenalan penyerahJenisPengenalan;
    private Permohonan permohonan = new Permohonan();
    private Permohonan perserahan = new Permohonan();
    private HakmilikLama hakmilikLama = new HakmilikLama();
    private static final String[] PAPAR_PRINT_DOKUMEN = {"DHKE", "DHDE", "PB1DE", "PB1KE", "PB2DE", "PB2KE"};
    private List<HakmilikPermohonan> hakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    private List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
    private ArrayList<KodUrusan> senaraiUrusan = new ArrayList<KodUrusan>();
    private List<KandunganFolder> listKandunganFolder = new ArrayList<KandunganFolder>();
    private List<Hakmilik> senaraiHakmilikKekal = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiHakmilikSementara = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
    private List<Versi4k> senaraiHakmilikVersi4k = new ArrayList<Versi4k>();
    private List<String> senaraiTingkat = new ArrayList<String>();
    private List<String> senaraiBangunan = new ArrayList<String>();
    private List<HakmilikPermohonan> senaraiHP;
    private List<FasaPermohonan> senaraiFasaPermohonan;
    private FasaPermohonan mohonFasa;
    private String hakmilikInduk;
    private String noTingkat;
    private String noBangunan;
    private String hakmilikStrata;
    private List<Versi4k> senaraiHakmilikVersiStrata;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private HakmilikLamaDAO hakmilikLamaDAO;
    @Inject
    StrataPtService strService;
    @Inject
    private RegService regService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodPenyerahDAO kodPenyerahDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    @Inject
    private KodNegeriDAO kodNegeriDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    DokumenService dokumenService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private etanah.view.stripes.hasil.KutipanHasilManager manager;
    @Inject
    private HsbmPTValidation hsbmPTValidation;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    FasaPermohonanService FasaPermohonanservice;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution selectTransaction() {
        carianPerserahan();

        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/jana_geran_strata_main.jsp");
    }

    public List<KodUrusan> getSenaraiUrusanTukarganti() {
        Session s = sessionProvider.get();
        return s.createQuery("SELECT ku FROM KodUrusan ku WHERE ku.jabatan.id = '16' "
                + "AND ku.urusanBelakangKaunter ='Y' AND ku.aktif='Y' "
                + "AND ku.kod IN ('HSTHK','HKTHK') ORDER BY ku.kod ASC").list();
    }

    public List<String> getSenaraiTingkat(String idHakmilik) {
//        Session s = sessionProvider.get();
//        return s.createQuery("select DISTINCT m.noTingkat from etanah.model.Hakmilik m where m.idHakmilikInduk = :idHakmilik").list()
//                   + s.setString("idHakmilik", idHakmilik);
        Session s = sessionProvider.get();
        Query q = s.createQuery("select DISTINCT m.noTingkat from etanah.model.Hakmilik m where m.idHakmilikInduk = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<String> getSenaraiBanggunan(String idHakmilik) {
//        Session s = sessionProvider.get();
//        return s.createQuery("select DISTINCT m.noTingkat from etanah.model.Hakmilik m where m.idHakmilikInduk = :idHakmilik").list()
//                   + s.setString("idHakmilik", idHakmilik);
        Session s = sessionProvider.get();
        Query q = s.createQuery("select DISTINCT m.noBangunan from etanah.model.Hakmilik m where m.idHakmilikInduk = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public Versi4k getSenaraiTingkatHakmilik(String idHakmilikStrata) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Versi4k m WHERE m.idHakmilikStrata = :idHakmilikStrata";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idHakmilikStrata", idHakmilikStrata);
        return (Versi4k) q.uniqueResult();

    }

    public List<Hakmilik> getSenaraiHakmilikByNoTingkat(String noTingkat, String idHakmilikInduk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select m from etanah.model.Hakmilik m where m.noTingkat = :noTingkat "
                + "and m.idHakmilikInduk = :idHakmilikInduk");
        q.setString("noTingkat", noTingkat);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<Hakmilik> getSenaraiHakmilikByNoBanggunan(String noBangunan, String idHakmilikInduk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select m from etanah.model.Hakmilik m where m.noBangunan = :noBangunan "
                + "and m.idHakmilikInduk = :idHakmilikInduk");
        q.setString("noBangunan", noBangunan);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<Hakmilik> getSenaraiHakmilikByNoBanggunanDanTingkat(String noBangunan, String idHakmilikInduk, String noTingkat) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select m from etanah.model.Hakmilik m where m.noBangunan = :noBangunan "
                + "and m.noTingkat = :noTingkat "
                + "and m.idHakmilikInduk = :idHakmilikInduk");
        q.setString("noBangunan", noBangunan);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        q.setString("noTingkat", noTingkat);
        return q.list();
    }

    private Dokumen saveOrUpdateDokumenV2DHKK(FolderDokumen fd, String idMohon, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        String[] tmp = id.split(",");
        String idT = "";
        if (tmp.length > 0) {
            idT = tmp[0];
        } else {
            idT = id;
        }

        LOG.debug("idT :" + idT);

        Versi4k versiStrata = hakmilikService.getVersiIDHakmilikByStrata(id);
        if (kd.getKod().equals("DHKK")) {
            Dokumen dokDHKE = versiStrata.getDHKK4K();
            if (dokDHKE != null) {
                LOG.debug("doc lama");
                doc = versiStrata.getDHKK4K();
                //doc = new Dokumen();
                ia = doc.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                doc.setBaru('T');
                LOG.debug("no versi lama : " + doc.getNoVersi());
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

        doc.setTajuk(kd.getKod() + " (" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

//    public void printPDF() {
//        PrinterJob printerJob = PrinterJob.getPrinterJob();
//
//        PrintService printService = null;
//        if (printerJob.printDialog()) {
//            printService = printerJob.getPrintService();
//        }
//        DocFlavor docType = DocFlavor.INPUT_STREAM.AUTOSENSE;
//
//        for (//fetch documents to be printed)
//                   {
//            DocPrintJob printJob = printService.createPrintJob();
//            final byte[] byteStream = // fetch content in byte array;
//                    Doc
//            documentToBePrinted = new SimpleDoc(new ByteArrayInputStream(byteStream), docType, null);
//            printJob.print(documentToBePrinted, null);
//        }
//    }
    public static void printService(String[] args) throws PrintException, IOException {
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
        PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        patts.add(Sides.DUPLEX);
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);

        if (ps.length == 0) {
            throw new IllegalStateException("No Printer found");
        }
        System.out.println("Available printers: " + Arrays.asList(ps));

        PrintService myService = null;
        for (PrintService printService : ps) {
            if (printService.getName().equals("Your printer name")) {
                myService = printerJob.getPrintService();
                break;
            }
        }

        if (myService == null) {
            throw new IllegalStateException("Printer not found");
        }

        FileInputStream fis = new FileInputStream("C:/Users/John Doe/Desktop/SamplePDF.pdf");
        Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        DocPrintJob printJob = myService.createPrintJob();
        printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
        fis.close();
    }

    private Dokumen saveOrUpdateDokumenV2DHDK(FolderDokumen fd, String idMohon, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        String[] tmp = id.split(",");
        String idT = "";
        if (tmp.length > 0) {
            idT = tmp[0];
        } else {
            idT = id;
        }

        LOG.debug("idT :" + idT);

//        HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonan(id, idMohon);
        Versi4k versiStrata = hakmilikService.getVersiIDHakmilikByStrata(id);
        if (kd.getKod().equals("DHDK")) {
            Dokumen dokDHDE = versiStrata.getDHKK4K();
            LOG.info("versiStrata.getIdHakmilikStrata() = " + versiStrata.getIdHakmilikStrata());
            if (dokDHDE != null) {
                //doc = new Dokumen();
                if (versiStrata.getDHDK4K() != null) {
                    LOG.debug("versiStrata.getDHDK4K()" + versiStrata.getDHDK4K().getIdDokumen());
                    doc = new Dokumen();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    doc.setBaru('Y');
                    doc.setNoVersi("1.0");
                } else {
                    LOG.debug("doc baru");
                    doc = new Dokumen();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    doc.setBaru('Y');
                    doc.setNoVersi("1.0");
                }
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

        doc.setTajuk(kd.getKod() + " (" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc.setNamaFizikal(null);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private Dokumen saveOrUpdateDokumen3k(FolderDokumen fd, String idMohon, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        String[] tmp = id.split(",");
        String idT = "";
        if (tmp.length > 0) {
            idT = tmp[0];
        } else {
            idT = id;
        }

        LOG.debug("idT :" + idT);

//        HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonan(id, idMohon);
        Versi4k versiStrata = hakmilikService.getVersiIDHakmilikByStrata(id);
        if (kd.getKod().equals("3K")) {
            Dokumen dokDHDE = versiStrata.getId3k();
            if (dokDHDE != null) {
                //doc = new Dokumen();
                doc = versiStrata.getId3k();
                ia = doc.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                doc.setBaru('T');
                LOG.debug("no versi lama : " + doc.getNoVersi());
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

        doc.setTajuk(kd.getKod() + " (" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc.setNamaFizikal(null);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private Dokumen saveOrUpdateDokumen2k(FolderDokumen fd, String idMohon, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        String[] tmp = id.split(",");
        String idT = "";
        if (tmp.length > 0) {
            idT = tmp[0];
        } else {
            idT = id;
        }

        LOG.debug("idT :" + idT);

//        HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonan(id, idMohon);
        Versi4k versiStrata = hakmilikService.getVersiIDHakmilikByStrata(id);
        if (kd.getKod().equals("2K")) {
            Dokumen dokDHDE = versiStrata.getId2k();
            if (dokDHDE != null) {
                //doc = new Dokumen();
                doc = versiStrata.getId2k();
                ia = doc.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                doc.setBaru('T');
                LOG.debug("no versi lama : " + doc.getNoVersi());
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

        doc.setTajuk(kd.getKod() + " (" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc.setNamaFizikal(null);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private Dokumen saveOrUpdateDokumenV2(FolderDokumen fd, String idMohon, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        String[] tmp = id.split(",");
        String idT = "";
        if (tmp.length > 0) {
            idT = tmp[0];
        } else {
            idT = id;
        }

        LOG.debug("idT :" + idT);

        HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonan(id, idMohon);
        if (kd.getKod().equals("DHKE")) {
            Dokumen dokDHKE = hp.getDokumen2();
            if (dokDHKE != null) {
                LOG.debug("doc lama");
                doc = hp.getDokumen2();
                //doc = new Dokumen();
                ia = doc.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                doc.setBaru('T');
                LOG.debug("no versi lama : " + doc.getNoVersi());
                Double noVersi = Double.parseDouble(doc.getNoVersi());
                doc.setNoVersi(String.valueOf(noVersi + 1));
                LOG.debug("no versi baru : " + noVersi);
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }
        if (kd.getKod().equals("DHDE")) {
            Dokumen dokDHDE = hp.getDokumen3();
            if (dokDHDE != null) {
                LOG.debug("doc lama");
                //doc = new Dokumen();
                doc = hp.getDokumen3();
                ia = doc.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                doc.setBaru('T');
                LOG.debug("no versi lama : " + doc.getNoVersi());
                Double noVersi = Double.parseDouble(doc.getNoVersi());
                doc.setNoVersi(String.valueOf(noVersi + 1));
                LOG.debug("no versi baru : " + noVersi);
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

        doc.setTajuk(kd.getKod() + " (" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private Dokumen saveOrUpdateDokumenDHKK(FolderDokumen fd, KodDokumen kd, String id, ActionBeanContext context1) {
//        Pengguna pengguna = context.getPengguna();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
//        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod() + "(" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        KodKlasifikasi klasifikasi_SULIT = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_SULIT);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        String[] tmp = id.split(",");
        String idT = "";
        if (tmp.length > 0) {
            idT = tmp[0];
        } else {
            idT = id;
        }

        LOG.debug("idT :" + idT);
        doc = semakDokumenService.semakDokumen(kd, fd, idT);
        if (doc == null) {
            LOG.debug("doc baru");
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");

        } else {
            LOG.debug("doc lama");
            //doc = new Dokumen();
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            LOG.debug("no versi lama : " + doc.getNoVersi());
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
            LOG.debug("no versi baru : " + noVersi);
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

        doc.setTajuk(kd.getKod() + " (" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private void updatePathDokumenDHDE(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        if (d.getNamaFizikal() != null) {
            d.setNamaFizikal(null);
            dokumenService.saveOrUpdate(d);
        }
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private void updatePathDokumenDHKE(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        if (d.getNamaFizikal() != null) {
            d.setNamaFizikal(null);
            dokumenService.saveOrUpdate(d);
        }
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);

    }

    private Dokumen saveOrUpdateDokumen1(FolderDokumen fd, KodDokumen kd, String id) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod() + "(" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updateNoVersi(Permohonan permohonan, InfoAudit info) {
        LOG.info(" /* UPDATE NO VERSI */ ");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        List<HakmilikPermohonan> senaraiHakmilik = new ArrayList();
        List<Hakmilik> lhk = new ArrayList();
        List<HakmilikLama> senaraiHakmilikLama = new ArrayList();
        senaraiHakmilik = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            Hakmilik hk = hp.getHakmilik();
            hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
            hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            hk.setInfoAudit(info);
            lhk.add(hk);

            hakmilikLama = hakmilikLamaDAO.findById(hp.getHakmilik().getIdHakmilik());
            if (hakmilikLama != null) {
                hakmilikLama.setVersiDhdk(hk.getNoVersiDhde().toString());
                hakmilikLama.setVersiDhkk(hk.getNoVersiDhke().toString());
                hakmilikLama.setPegawaiJilid16(pengguna.getNama());
                hakmilikLama.setPenggunaJilid16(pengguna);
                hakmilikLama.setTarikhJilid16(info.getTarikhKemaskini());
                senaraiHakmilikLama.add(hakmilikLama);
            }
//            else{
//                hakmilikLama.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
//                hakmilikLama.setVersiDhdk(hk.getNoVersiDhde().toString());
//                hakmilikLama.setVersiDhkk(hk.getNoVersiDhke().toString());
//                hakmilikLama.setPegawaiJilid16(pengguna.getNama());
//                hakmilikLama.setPenggunaJilid16(pengguna);
//                hakmilikLama.setTarikhJilid16(info.getTarikhKemaskini());
//                    info.setDimasukOleh(pengguna);
//                    info.setTarikhMasuk(new java.util.Date());
//                hakmilikLama.setInfoAudit(info);
//                senaraiHakmilikLama.add(hakmilikLama);
//            }
        }
        regService.simpanHakmilikList(lhk);
        regService.simpanHakmilikLamaList(senaraiHakmilikLama);
    }

    @HandlesEvent("Step7a")
    public Resolution carianPerserahan() {
        LOG.info(" /* CARIANPERSERAHAN */ " + hakmilikInduk);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();

        if (hakmilikInduk != null) {

            senaraiTingkat = getSenaraiTingkat(hakmilikInduk);
            senaraiBangunan = getSenaraiBanggunan(hakmilikInduk);
            if (senaraiTingkat.size() <= 0) {
                addSimpleError("No Hakmilik " + hakmilikInduk + " Tiada Dalam Pengkalan Data");
            }
//
//            senaraiHakmilikVersiStrata = hakmilikService.getVersiIDHakmilikByInduk(hakmilikInduk);
//
//            idHakmilikFirst = senaraiHakmilikVersiStrata.get(0).getIdHakmilikStrata();

        } else if (hakmilikStrata != null) {
            Versi4k hakmilikVersi = hakmilikPermohonanService.findVersiStrataByhakmilikStrata(hakmilikStrata);
            if (hakmilikVersi != null) {
                senaraiHakmilikVersi4k.add(hakmilikVersi);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/jana_geran_strata_main.jsp");
    }

    @HandlesEvent("carianHakmilikBetween")
    public Resolution carianHakmilikBetween() {
        LOG.info(" /* CARIANPERSERAHAN */ " + noTingkat);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();
        if (noBangunan != null && noTingkat == null) {
            List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoBanggunan(noBangunan, hakmilikInduk);
            for (Hakmilik hmStrata : senaraiHakmilikStrata) {
                Versi4k versi = getSenaraiTingkatHakmilik(hmStrata.getIdHakmilik());
                senaraiHakmilikVersi4k.add(versi);
            }
        } else if (noTingkat != null && noBangunan == null) {
            List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoTingkat(noTingkat, hakmilikInduk);

            for (Hakmilik hmStrata : senaraiHakmilikStrata) {
                Versi4k versi = getSenaraiTingkatHakmilik(hmStrata.getIdHakmilik());
                senaraiHakmilikVersi4k.add(versi);
            }
        } else if (noBangunan != null && noTingkat != null) {
            List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoBanggunanDanTingkat(noBangunan, hakmilikInduk, noTingkat);
            for (Hakmilik hmStrata : senaraiHakmilikStrata) {
                Versi4k versi = getSenaraiTingkatHakmilik(hmStrata.getIdHakmilik());
                senaraiHakmilikVersi4k.add(versi);
            }
        } else {
            addSimpleError("Sila Pilih No Tingkat Atau Bangunan");
        }
        senaraiTingkat = getSenaraiTingkat(hakmilikInduk);
        senaraiBangunan = getSenaraiBanggunan(hakmilikInduk);
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/jana_geran_strata_main.jsp");
    }

    @HandlesEvent("Step11")
    public Resolution cariPerserahan() {
        LOG.info(" /* CARIAN PERSERAHAN */ ");
        perserahan = permohonanDAO.findById(idSerah);
        if (perserahan == null) {
            addSimpleError("ID Perserahan yang dimasukkan tidak dijumpai.");
        } else {
            flag = true;
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_main.jsp");
    }

    public void calculateIdhakmilik(String idHakmilikLama, String idMohon) {
//        idHakmilikFirst1 = hakmilikDAO.findById(idHakmilikLama);
//        HakmilikPermohonan hp1 = hakmilikPermohonanService.findHakmilikPermohonan(idHakmilikFirst1.getIdHakmilik(), idMohon);
        Versi4k versiStrata = hakmilikPermohonanService.findVersiStrataByhakmilikStrata(idHakmilikLama);
//        idHakmilikFirst1 = versiStrata.getHakmilikInduk();
//        if (versiStrata != null) {
//            int noHakmilik = Integer.valueOf(idHakmilikFirst1.getNoHakmilik());
//            noHakmilik = noHakmilik + 1;
//            String noHakmilik1 = StringUtils.leftPad(String.valueOf(noHakmilik), 8, '0');
//            String bpm = StringUtils.leftPad(String.valueOf(idHakmilikFirst1.getBandarPekanMukim().getKod()), 2, '0');
//            LOG.info(" /* onGenerateGeran */ " + noHakmilik);
//            idHakmilikFirst = "04" + idHakmilikFirst1.getDaerah().getKod() + bpm + idHakmilikFirst1.getKodHakmilik().getKod() + noHakmilik1;
//            idHakmilik2 = null;
//            LOG.info(" /* onGenerateGeran */ " + noHakmilik);
//            senaraiFasaPermohonan = FasaPermohonanservice.findStatusDESC(hp1.getPermohonan().getIdPermohonan());
//            mohonFasa = senaraiFasaPermohonan.get(0);
//        } else {
//            addSimpleError(idHakmilikLama + " adalah hakmilik terakhir untuk urusan ini");
//        }
    }

    @HandlesEvent("Step8e")
    public Resolution onGenerateGeran3k() {
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilikFirst);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilik2);

        String id = idMohon;
        String idHakmilikMula = idHakmilikFirst;
        String idHakmilikAkhir = idHakmilik2;
        LOG.info("id : " + id);
        LOG.info("id : " + idHakmilikAkhir);
        LOG.info("id : " + idHakmilikMula);
        List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoTingkat(noTingkat, hakmilikInduk);
        senaraiTingkat = getSenaraiTingkat(hakmilikInduk);
        if (senaraiHakmilikStrata.size() <= 0) {
            if (hakmilikStrata != null) {
                Versi4k hakmilikVersi = hakmilikPermohonanService.findVersiStrataByhakmilikStrata(hakmilikStrata);
                senaraiHakmilikVersi4k.add(hakmilikVersi);
            }
        }
        for (Hakmilik hmStrata : senaraiHakmilikStrata) {
            Versi4k versi = getSenaraiTingkatHakmilik(hmStrata.getIdHakmilik());
            senaraiHakmilikVersi4k.add(versi);
        }

//        for (Versi4k versiBaru : senaraiHakmilikVersi4k) {
        if (senaraiHakmilikVersi4k.size() > 0) {
            Versi4k versiBaru = senaraiHakmilikVersi4k.get(0);
            LOG.info("versiBaru.getIdHakmilikStrata() =" + versiBaru.getIdHakmilikStrata());
            List<HakmilikUrusan> hakmilikUrusanList = hakmilikUrusanService.findAllHakmilikUrusanByIdHakmilik(versiBaru.getIdHakmilikStrata());
            if (hakmilikUrusanList.size() > 0) {
                HakmilikUrusan hakmilikUrusan = hakmilikUrusanList.get(0);
                Hakmilik hmStrata = hakmilikService.findById(versiBaru.getIdHakmilikStrata());
                permohonan = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                ku = permohonan.getKodUrusan();
                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
                Pengguna pengguna = ctx.getUser();
                pguna = ctx.getUser();

                Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
                String dokumenPath = conf.getProperty("document.path");
                Dokumen h = null;
                String idFolder = "";

                String idPermohonan = permohonan.getIdPermohonan();
                LOG.info("idPermohonan check = " + idPermohonan);
                LOG.info("hakmilikStrata check = " + versiBaru.getIdHakmilikStrata());
                idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
                String[] params = null;
                String[] params2 = null;
                String[] values = null;
                String[] values2 = null;
                String path5 = "";
                String gen5 = "";
                KodDokumen kd5 = new KodDokumen();

                ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

                Hakmilik hm = hakmilikDAO.findById(versiBaru.getIdHakmilikStrata());
                LOG.info("hp.getId() : " + versiBaru.getIdHakmilikStrata());
//                if (hm == null) {
//                    continue;
//                }
                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                params2 = new String[]{"p_id_hakmilik"};
                values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
                values2 = new String[]{idPermohonan.trim(), hm.getIdHakmilikInduk()};

                gen5 = "REGB3Ka_MLK.rdf"; //DHDE report name
                kd5.setKod("3K");
                LOG.info("--Generating 3K--::");
                h = saveOrUpdateDokumenDHKK(fd, kd5, hm.getIdHakmilik(), context);
                path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
                reportUtil.generateReport(gen5, params, values2, dokumenPath + path5, peng);
                updatePathDokumen(reportUtil.getDMSPath(), h.getIdDokumen());
                LOG.info("--Generated 3K--::");
                Future<byte[]> report3k = executor.schedule(new CallableReport(reportUtil, gen5, params, values2, dokumenPath + path5, peng), 1, TimeUnit.MILLISECONDS);

                if (kd5.getKod().equals("3K")) {
                    for (Versi4k versiBaru1 : senaraiHakmilikVersi4k) {
                        updatePathDokumenDHDE(reportUtil.getDMSPath(), h.getIdDokumen());
                        versiBaru1.setId3k(h);
                        manager.saveOrUpdate(versiBaru1);
                    }

                }
                InfoAudit ia = new InfoAudit();

//                 ia = doc.getInfoAudit();
                if (versiBaru.getId3k() == null) {
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                versiBaru.setInfoAudit(ia);
                versiBaru.setId3k(h);
                manager.saveOrUpdate(versiBaru);
                manager.saveOrUpdate(hm);
            }
        }

//        calculateIdhakmilik(idHakmilik2, idMohon);
        addSimpleMessage(
                "Geran Hakmilik telah berjaya dijana semula.");
        return new ForwardResolution(
                "/WEB-INF/jsp/daftar/utiliti/jana_geran_strata_main.jsp");
    }

    @HandlesEvent("Step8d")
    public Resolution onGenerateGeran2K() {
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilikFirst);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilik2);

        String id = idMohon;
        String idHakmilikMula = idHakmilikFirst;
        String idHakmilikAkhir = idHakmilik2;
        LOG.info("id : " + id);
        LOG.info("id : " + idHakmilikAkhir);
        LOG.info("id : " + idHakmilikMula);
        List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoTingkat(noTingkat, hakmilikInduk);
        senaraiTingkat = getSenaraiTingkat(hakmilikInduk);
        if (senaraiHakmilikStrata.size() <= 0) {
            if (hakmilikStrata != null) {
//                Hakmilik hakmilik = hakmilikDAO.findById(hakmilikStrata);
                Versi4k hakmilikVersi = hakmilikPermohonanService.findVersiStrataByhakmilikStrata(hakmilikStrata);
                senaraiHakmilikVersi4k.add(hakmilikVersi);
            }
        }
//      List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoTingkat(noTingkat, hakmilikInduk);
        for (Hakmilik hmStrata : senaraiHakmilikStrata) {
            Versi4k versi = getSenaraiTingkatHakmilik(hmStrata.getIdHakmilik());
            senaraiHakmilikVersi4k.add(versi);
//                senaraiTingkat = getSenaraiTingkat(hakmilikInduk);
        }
//        List<Versi4k> senaraiHakmilikVersi4kBaru = hakmilikPermohonanService.carianVersiHakmilikStrataBetween(idHakmilikMula, idHakmilikAkhir);
//        for (Versi4k versiBaru : senaraiHakmilikVersi4k) {
        if (senaraiHakmilikVersi4k.size() > 0) {
            Versi4k versiBaru = senaraiHakmilikVersi4k.get(0);
            LOG.info("versiBaru.getIdHakmilikStrata() =" + versiBaru.getIdHakmilikStrata());
            List<HakmilikUrusan> hakmilikUrusanList = hakmilikUrusanService.findAllHakmilikUrusanByIdHakmilik(versiBaru.getIdHakmilikStrata());
            if (hakmilikUrusanList.size() > 0) {
                HakmilikUrusan hakmilikUrusan = hakmilikUrusanList.get(0);
                Hakmilik hmStrata = hakmilikService.findById(versiBaru.getIdHakmilikStrata());
                permohonan = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                ku = permohonan.getKodUrusan();
                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
                Pengguna pengguna = ctx.getUser();
                pguna = ctx.getUser();

                Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
                String dokumenPath = conf.getProperty("document.path");

                Dokumen g = null;
                String idFolder = "";

                String idPermohonan = permohonan.getIdPermohonan();
                LOG.info("idPermohonan check = " + idPermohonan);
                LOG.info("hakmilikStrata check = " + versiBaru.getIdHakmilikStrata());
                idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
                String[] params = null;
                String[] params2 = null;
                String[] values = null;
                String[] values2 = null;

                String path4 = "";
                String gen4 = "";
                KodDokumen kd4 = new KodDokumen();

                ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

                Hakmilik hm = hakmilikDAO.findById(versiBaru.getIdHakmilikStrata());
                LOG.info("hp.getId() : " + versiBaru.getIdHakmilikStrata());
//                if (hm == null) {
//                    continue;
//                }

                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                params2 = new String[]{"p_id_hakmilik"};
                values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
                values2 = new String[]{idPermohonan.trim(), hm.getIdHakmilikInduk()};

                gen4 = "REGB2Ka_MLK.rdf"; //DHDE report name

                kd4.setKod("2K");

                LOG.info("--Generating 2K--::");
                g = saveOrUpdateDokumenDHKK(fd, kd4, hm.getIdHakmilik(), context);
                path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                reportUtil.generateReport(gen4, params, values2, dokumenPath + path4, peng);
                updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
                Future<byte[]> report2k = executor.schedule(new CallableReport(reportUtil, gen4, params, values2, dokumenPath + path4, peng), 1, TimeUnit.MILLISECONDS);
                if (kd4.getKod().equals("2K")) {
                    for (Versi4k versiBaru1 : senaraiHakmilikVersi4k) {
                        updatePathDokumenDHDE(reportUtil.getDMSPath(), g.getIdDokumen());
                        versiBaru1.setId2k(g);
                        manager.saveOrUpdate(versiBaru1);

                    }

                }
                InfoAudit ia = new InfoAudit();

//                 ia = doc.getInfoAudit();
                if (versiBaru.getId2k() == null) {
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                manager.saveOrUpdate(versiBaru);

                manager.saveOrUpdate(hm);
            }

        }

//        calculateIdhakmilik(idHakmilik2, idMohon);
        addSimpleMessage("Geran Hakmilik telah berjaya dijana semula.");
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/jana_geran_strata_main.jsp");
    }

    @HandlesEvent("Step8c")
    public Resolution onGenerateGeran4k() {
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilikFirst);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilik2);

        String id = idMohon;
        String idHakmilikMula = idHakmilikFirst;
        String idHakmilikAkhir = idHakmilik2;
        LOG.info("id : " + id);
        LOG.info("id : " + idHakmilikAkhir);
        LOG.info("id : " + idHakmilikMula);
        List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoTingkat(noTingkat, hakmilikInduk);
        senaraiTingkat = getSenaraiTingkat(hakmilikInduk);
        if (senaraiHakmilikStrata.size() <= 0) {
            if (hakmilikStrata != null) {
//                Hakmilik hakmilik = hakmilikDAO.findById(hakmilikStrata);
                Versi4k hakmilikVersi = hakmilikPermohonanService.findVersiStrataByhakmilikStrata(hakmilikStrata);
                senaraiHakmilikVersi4k.add(hakmilikVersi);
            }
        }
//      List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoTingkat(noTingkat, hakmilikInduk);
        for (Hakmilik hmStrata : senaraiHakmilikStrata) {
            Versi4k versi = getSenaraiTingkatHakmilik(hmStrata.getIdHakmilik());
            senaraiHakmilikVersi4k.add(versi);
//                senaraiTingkat = getSenaraiTingkat(hakmilikInduk);
        }
//        List<Versi4k> senaraiHakmilikVersi4kBaru = hakmilikPermohonanService.carianVersiHakmilikStrataBetween(idHakmilikMula, idHakmilikAkhir);
        for (Versi4k versiBaru : senaraiHakmilikVersi4k) {
            LOG.info("versiBaru.getIdHakmilikStrata() =" + versiBaru.getIdHakmilikStrata());
            List<HakmilikUrusan> hakmilikUrusanList = hakmilikUrusanService.findAllHakmilikUrusanByIdHakmilik(versiBaru.getIdHakmilikStrata());
            if (hakmilikUrusanList.size() > 0) {
                HakmilikUrusan hakmilikUrusan = hakmilikUrusanList.get(0);
                Hakmilik hmStrata = hakmilikService.findById(versiBaru.getIdHakmilikStrata());
                permohonan = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                ku = permohonan.getKodUrusan();
                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
                Pengguna pengguna = ctx.getUser();
                pguna = ctx.getUser();

                Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
                String dokumenPath = conf.getProperty("document.path");
                Dokumen e = null;
                Dokumen f = null;
                Dokumen g = null;
                Dokumen h = null;
                String idFolder = "";

                String idPermohonan = permohonan.getIdPermohonan();
                LOG.info("idPermohonan check = " + idPermohonan);
                LOG.info("hakmilikStrata check = " + versiBaru.getIdHakmilikStrata());
                idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
                String[] params = null;
                String[] params2 = null;
                String[] values = null;
                String[] values2 = null;
                String path2 = "";
                String path3 = "";
                String gen2 = "";
                String gen3 = "";

                KodDokumen kd2 = new KodDokumen();
                KodDokumen kd3 = new KodDokumen();
                KodDokumen kd4 = new KodDokumen();
                KodDokumen kd5 = new KodDokumen();

                ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

                Hakmilik hm = hakmilikDAO.findById(versiBaru.getIdHakmilikStrata());
                LOG.info("hp.getId() : " + versiBaru.getIdHakmilikStrata());
                if (hm == null) {
                    continue;
                }

                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                params2 = new String[]{"p_id_hakmilik"};
                values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
                values2 = new String[]{idPermohonan.trim(), hm.getIdHakmilikInduk()};
                //                gen2 = "regBorangHMDHKE.rdf"; //DHKE report name
                gen2 = "REGB4KDHKKa_MLK.rdf"; //DHKE report name
                gen3 = "REGB4KDHDKa_MLK.rdf"; //DHDE report name

                kd2.setKod("DHKK");
                kd3.setKod("DHDK");

                LOG.info("--Generating DHKK--::");
                e = saveOrUpdateDokumenDHKK(fd, kd2, hm.getIdHakmilik(), context);
                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                reportUtil.generateReport(gen2, params, values2, dokumenPath + path2, peng);
                updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                Future<byte[]> DHKK = executor.schedule(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng), 1, TimeUnit.MILLISECONDS);
                if (kd2.getKod().equals("DHKK")) {
                    updatePathDokumenDHKE(reportUtil.getDMSPath(), e.getIdDokumen());
//                    gc();
                }
                LOG.info("--Generating DHDK--::");
                f = saveOrUpdateDokumenDHKK(fd, kd3, hm.getIdHakmilik(), context);
                path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
                updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
                LOG.info("--Generated 4K DHDK--::");
                Future<byte[]> DHDK = executor.schedule(new CallableReport(reportUtil, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.MILLISECONDS);
                if (kd3.getKod().equals("DHDK")) {
                    updatePathDokumenDHDE(reportUtil.getDMSPath(), f.getIdDokumen());
//                     gc();
                }

//                int counting = 0;
//                if (counting < 100){
//                    counting ++;
//                    gc();
//                }else {
//                    
//                }
                //save to mohon hakmilikLogic.
                InfoAudit ia = new InfoAudit();

//                 ia = doc.getInfoAudit();
                if (versiBaru.getDHDK4K() == null || versiBaru.getDHKK4K() == null) {
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
//                if (versiBaru.getDHKK4K() == null) {
//                    ia.setDiKemaskiniOleh(peng);
//                    ia.setTarikhKemaskini(new java.util.Date());
//                } else {
//                    ia.setDimasukOleh(pengguna);
//                    ia.setTarikhMasuk(new java.util.Date());
//                }
                versiBaru.setDHDK4K(f);
                versiBaru.setDHKK4K(e);
                hmStrata.setId4k(String.valueOf(f.getIdDokumen()));
                manager.saveOrUpdate(versiBaru);

                manager.saveOrUpdate(hm);
            }

        }

//        calculateIdhakmilik(idHakmilik2, idMohon);
        addSimpleMessage("Geran Hakmilik telah berjaya dijana semula.");
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/jana_geran_strata_main.jsp");
    }

   

    public static void gc() {
        Object obj = new Object();
        WeakReference ref = new WeakReference<Object>(obj);
        obj = null;
        while (ref.get() != null) {
            System.gc();
//           Runtime.getRuntime().freeMemory();
        }
    }

    @HandlesEvent("Step8b")
    public Resolution onGenerateGeran() {
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilikFirst);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilik2);

        String id = idMohon;
        String idHakmilikMula = idHakmilikFirst;
        String idHakmilikAkhir = idHakmilik2;
        LOG.info("id : " + id);
        LOG.info("id : " + idHakmilikAkhir);
        LOG.info("id : " + idHakmilikMula);
        List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoTingkat(noTingkat, hakmilikInduk);
        senaraiTingkat = getSenaraiTingkat(hakmilikInduk);
        if (senaraiHakmilikStrata.size() <= 0) {
            if (hakmilikStrata != null) {
//                Hakmilik hakmilik = hakmilikDAO.findById(hakmilikStrata);
                Versi4k hakmilikVersi = hakmilikPermohonanService.findVersiStrataByhakmilikStrata(hakmilikStrata);
                senaraiHakmilikVersi4k.add(hakmilikVersi);
            }
        }
//      List<Hakmilik> senaraiHakmilikStrata = getSenaraiHakmilikByNoTingkat(noTingkat, hakmilikInduk);
        for (Hakmilik hmStrata : senaraiHakmilikStrata) {
            Versi4k versi = getSenaraiTingkatHakmilik(hmStrata.getIdHakmilik());
            senaraiHakmilikVersi4k.add(versi);
//                senaraiTingkat = getSenaraiTingkat(hakmilikInduk);
        }
//        List<Versi4k> senaraiHakmilikVersi4kBaru = hakmilikPermohonanService.carianVersiHakmilikStrataBetween(idHakmilikMula, idHakmilikAkhir);
        for (Versi4k versiBaru : senaraiHakmilikVersi4k) {
            LOG.info("versiBaru.getIdHakmilikStrata() =" + versiBaru.getIdHakmilikStrata());
            List<HakmilikUrusan> hakmilikUrusanList = hakmilikUrusanService.findAllHakmilikUrusanByIdHakmilik(versiBaru.getIdHakmilikStrata());
            if (hakmilikUrusanList.size() > 0) {
                HakmilikUrusan hakmilikUrusan = hakmilikUrusanList.get(0);
                Hakmilik hmStrata = hakmilikService.findById(versiBaru.getIdHakmilikStrata());
                permohonan = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                ku = permohonan.getKodUrusan();
                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
                Pengguna pengguna = ctx.getUser();
                pguna = ctx.getUser();

                Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
                String dokumenPath = conf.getProperty("document.path");
                Dokumen e = null;
                Dokumen f = null;
                Dokumen g = null;
                Dokumen h = null;
                String idFolder = "";

                String idPermohonan = permohonan.getIdPermohonan();
                LOG.info("idPermohonan check = " + idPermohonan);
                LOG.info("hakmilikStrata check = " + versiBaru.getIdHakmilikStrata());
                idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
                String[] params = null;
                String[] params2 = null;
                String[] values = null;
                String[] values2 = null;
                String path2 = "";
                String path3 = "";
                String path4 = "";
                String path5 = "";
                String gen2 = "";
                String gen3 = "";
                String gen4 = "";
                String gen5 = "";
                KodDokumen kd2 = new KodDokumen();
                KodDokumen kd3 = new KodDokumen();
                KodDokumen kd4 = new KodDokumen();
                KodDokumen kd5 = new KodDokumen();

                ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

                Hakmilik hm = hakmilikDAO.findById(versiBaru.getIdHakmilikStrata());
                LOG.info("hp.getId() : " + versiBaru.getIdHakmilikStrata());
                if (hm == null) {
                    continue;
                }

                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                params2 = new String[]{"p_id_hakmilik"};
                values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
                values2 = new String[]{idPermohonan.trim(), hm.getIdHakmilikInduk()};
                //                gen2 = "regBorangHMDHKE.rdf"; //DHKE report name
                gen2 = "REGB4KDHKKa_MLK.rdf"; //DHKE report name
                gen3 = "REGB4KDHDKa_MLK.rdf"; //DHDE report name
                gen4 = "REGB2Ka_MLK.rdf"; //DHDE report name
                gen5 = "REGB3Ka_MLK.rdf"; //DHDE report name

                kd2.setKod("DHKK");
                kd3.setKod("DHDK");
                kd4.setKod("2K");
                kd5.setKod("3K");
                LOG.info("--Generating DHKK--::");
                e = saveOrUpdateDokumenDHKK(fd, kd2, hm.getIdHakmilik(), context);
                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
//                reportUtil.generateReport(gen2, params, values2, dokumenPath + path2, peng);
//                updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                Future<byte[]> DHKK = executor.schedule(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng), 1, TimeUnit.SECONDS);
                if (kd2.getKod().equals("DHKK")) {
                    updatePathDokumenDHKE(reportUtil.getDMSPath(), e.getIdDokumen());
                    gc();
                }
                LOG.info("--Generating DHDK--::");
                f = saveOrUpdateDokumenDHKK(fd, kd3, hm.getIdHakmilik(), context);
                path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
//                reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
//                updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
                LOG.info("--Generated 4K DHDK--::");
                Future<byte[]> DHDK = executor.schedule(new CallableReport(reportUtil, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
                if (kd3.getKod().equals("DHDK")) {
                    updatePathDokumenDHDE(reportUtil.getDMSPath(), f.getIdDokumen());
//                    gc();
                }

//                LOG.info("--Generating 2K--::");
//                g = saveOrUpdateDokumenDHKK(fd, kd4, hm.getIdHakmilik(), context);
//                path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
//                reportUtil.generateReport(gen4, params, values2, dokumenPath + path4, peng);
//                updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
//                Future<byte[]> report2k = executor.schedule(new CallableReport(reportUtil, gen4, params, values2, dokumenPath + path4, peng), 1, TimeUnit.MILLISECONDS);
//                if (kd4.getKod().equals("2K")) {
//                    updatePathDokumenDHDE(reportUtil.getDMSPath(), g.getIdDokumen());
//                }
//                LOG.info("--Generating 3K--::");
//                h = saveOrUpdateDokumenDHKK(fd, kd5, hm.getIdHakmilik(), context);
//                path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
//                reportUtil.generateReport(gen5, params, values2, dokumenPath + path5, peng);
//                updatePathDokumen(reportUtil.getDMSPath(), h.getIdDokumen());
//                LOG.info("--Generated 3K--::");
//                Future<byte[]> report3k = executor.schedule(new CallableReport(reportUtil, gen5, params, values2, dokumenPath + path5, peng), 1, TimeUnit.MILLISECONDS);
//
//                if (kd5.getKod().equals("3K")) {
//                    updatePathDokumenDHDE(reportUtil.getDMSPath(), h.getIdDokumen());
//                }
                //save to mohon hakmilikLogic.
                InfoAudit ia = new InfoAudit();

//                 ia = doc.getInfoAudit();
                if (versiBaru.getDHDK4K() == null || versiBaru.getDHKK4K() == null) {
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }

                versiBaru.setDHDK4K(f);
                versiBaru.setDHKK4K(e);
//                versiBaru.setId2k(g);
//                versiBaru.setId3k(h);
                hmStrata.setId4k(String.valueOf(f.getIdDokumen()));
                manager.saveOrUpdate(versiBaru);

                manager.saveOrUpdate(hm);
            }

        }

        if (senaraiHakmilikVersi4k.size() > 0) {
            Versi4k versiBaru = senaraiHakmilikVersi4k.get(0);
            LOG.info("versiBaru.getIdHakmilikStrata() =" + versiBaru.getIdHakmilikStrata());
            List<HakmilikUrusan> hakmilikUrusanList = hakmilikUrusanService.findAllHakmilikUrusanByIdHakmilik(versiBaru.getIdHakmilikStrata());
            if (hakmilikUrusanList.size() > 0) {
                HakmilikUrusan hakmilikUrusan = hakmilikUrusanList.get(0);
                Hakmilik hmStrata = hakmilikService.findById(versiBaru.getIdHakmilikStrata());
                permohonan = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                ku = permohonan.getKodUrusan();
                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
                Pengguna pengguna = ctx.getUser();
                pguna = ctx.getUser();

                Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
                String dokumenPath = conf.getProperty("document.path");

                Dokumen e = null;
                Dokumen f = null;
                Dokumen g = null;
                Dokumen h = null;
                String idFolder = "";

                String idPermohonan = permohonan.getIdPermohonan();
                LOG.info("idPermohonan check = " + idPermohonan);
                LOG.info("hakmilikStrata check = " + versiBaru.getIdHakmilikStrata());
                idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
                String[] params = null;
                String[] params2 = null;
                String[] values = null;
                String[] values2 = null;

                String path4 = "";
                String path5 = "";

                String gen4 = "";
                String gen5 = "";

                KodDokumen kd4 = new KodDokumen();
                KodDokumen kd5 = new KodDokumen();

                ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

                Hakmilik hm = hakmilikDAO.findById(versiBaru.getIdHakmilikStrata());
                LOG.info("hp.getId() : " + versiBaru.getIdHakmilikStrata());

                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                params2 = new String[]{"p_id_hakmilik"};
                values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
                values2 = new String[]{idPermohonan.trim(), hm.getIdHakmilikInduk()};
                //                gen2 = "regBorangHMDHKE.rdf"; //DHKE report name

                gen4 = "REGB2Ka_MLK.rdf"; //DHDE report name
                gen5 = "REGB3Ka_MLK.rdf"; //DHDE report name

                kd4.setKod("2K");
                kd5.setKod("3K");

                LOG.info("hp.getId() : " + versiBaru.getIdHakmilikStrata());

                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                params2 = new String[]{"p_id_hakmilik"};
                values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
                values2 = new String[]{idPermohonan.trim(), hm.getIdHakmilikInduk()};

                gen4 = "REGB2Ka_MLK.rdf"; //DHDE report name

                kd4.setKod("2K");

                LOG.info("--Generating 2K--::");
                g = saveOrUpdateDokumenDHKK(fd, kd4, hm.getIdHakmilik(), context);
                path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                reportUtil.generateReport(gen4, params, values2, dokumenPath + path4, peng);
                updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
                Future<byte[]> report2k = executor.schedule(new CallableReport(reportUtil, gen4, params, values2, dokumenPath + path4, peng), 1, TimeUnit.MILLISECONDS);
                if (kd4.getKod().equals("2K")) {
                    updatePathDokumenDHDE(reportUtil.getDMSPath(), g.getIdDokumen());
                }
                LOG.info("--Generating 3K--::");
                h = saveOrUpdateDokumenDHKK(fd, kd5, hm.getIdHakmilik(), context);
                path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
                reportUtil.generateReport(gen5, params, values2, dokumenPath + path5, peng);
                updatePathDokumen(reportUtil.getDMSPath(), h.getIdDokumen());
                LOG.info("--Generated 3K--::");
                Future<byte[]> report3k = executor.schedule(new CallableReport(reportUtil, gen5, params, values2, dokumenPath + path5, peng), 1, TimeUnit.MILLISECONDS);

                if (kd5.getKod().equals("3K")) {
                    updatePathDokumenDHDE(reportUtil.getDMSPath(), h.getIdDokumen());
                }
                InfoAudit ia = new InfoAudit();

//                 ia = doc.getInfoAudit();
                if (versiBaru.getId2k() == null || versiBaru.getId3k() == null) {
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                manager.saveOrUpdate(versiBaru);

                manager.saveOrUpdate(hm);
            }

        }

//        calculateIdhakmilik(idHakmilik2, idMohon);
        addSimpleMessage("Geran Hakmilik telah berjaya dijana semula.");
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/jana_geran_strata_main.jsp");
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public KodUrusan getKu() {
        return ku;
    }

    public void setKu(KodUrusan ku) {
        this.ku = ku;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(List<HakmilikPermohonan> hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getKodSerah() {
        return kodSerah;
    }

    public void setKodSerah(String kodSerah) {
        this.kodSerah = kodSerah;
    }

    public String getIdPenyerah() {
        return idPenyerah;
    }

    public void setIdPenyerah(String idPenyerah) {
        this.idPenyerah = idPenyerah;
    }

    public KodPenyerah getPenyerahKod() {
        return penyerahKod;
    }

    public void setPenyerahKod(KodPenyerah penyerahKod) {
        this.penyerahKod = penyerahKod;
    }

    public KodJenisPengenalan getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;
    }

    public void setPenyerahJenisPengenalan(KodJenisPengenalan penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public KodNegeri getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(KodNegeri penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public ArrayList<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(ArrayList<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public String getPenyerahNegeriNama() {
        return kodNegeriDAO.findById(penyerahNegeri.getKod()).getNama();
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<Hakmilik> getSenaraiHakmilikKekal() {
        return senaraiHakmilikKekal;
    }

    public void setSenaraiHakmilikKekal(List<Hakmilik> senaraiHakmilikKekal) {
        this.senaraiHakmilikKekal = senaraiHakmilikKekal;
    }

    public List<Hakmilik> getSenaraiHakmilikSementara() {
        return senaraiHakmilikSementara;
    }

    public void setSenaraiHakmilikSementara(List<Hakmilik> senaraiHakmilikSementara) {
        this.senaraiHakmilikSementara = senaraiHakmilikSementara;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdSerah() {
        return idSerah;
    }

    public void setIdSerah(String idSerah) {
        this.idSerah = idSerah;
    }

    public Permohonan getPerserahan() {
        return perserahan;
    }

    public void setPerserahan(Permohonan perserahan) {
        this.perserahan = perserahan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getIdHakmilikDari() {
        return idHakmilikDari;
    }

    public void setIdHakmilikDari(String idHakmilikDari) {
        this.idHakmilikDari = idHakmilikDari;
    }

    public String getIdHakmilikHingga() {
        return idHakmilikHingga;
    }

    public void setIdHakmilikHingga(String idHakmilikHingga) {
        this.idHakmilikHingga = idHakmilikHingga;
    }

    public int getBilHakmilikSiri() {
        return bilHakmilikSiri;
    }

    public void setBilHakmilikSiri(int bilHakmilikSiri) {
        this.bilHakmilikSiri = bilHakmilikSiri;
    }

    public String getListHakmilikSiri() {
        return listHakmilikSiri;
    }

    public void setListHakmilikSiri(String listHakmilikSiri) {
        this.listHakmilikSiri = listHakmilikSiri;
    }

    public HakmilikLama getHakmilikLama() {
        return hakmilikLama;
    }

    public void setHakmilikLama(HakmilikLama hakmilikLama) {
        this.hakmilikLama = hakmilikLama;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public String getIdHakmilik2() {
        return idHakmilik2;
    }

    public void setIdHakmilik2(String idHakmilik2) {
        this.idHakmilik2 = idHakmilik2;
    }

    public List<HakmilikPermohonan> getSenaraiHP() {
        return senaraiHP;
    }

    public void setSenaraiHP(List<HakmilikPermohonan> senaraiHP) {
        this.senaraiHP = senaraiHP;
    }

    public String getIdHakmilikFirst() {
        return idHakmilikFirst;
    }

    public void setIdHakmilikFirst(String idHakmilikFirst) {
        this.idHakmilikFirst = idHakmilikFirst;
    }

    public FasaPermohonanService getFasaPermohonanservice() {
        return FasaPermohonanservice;
    }

    public void setFasaPermohonanservice(FasaPermohonanService FasaPermohonanservice) {
        this.FasaPermohonanservice = FasaPermohonanservice;
    }

    public Hakmilik getIdHakmilikFirst1() {
        return idHakmilikFirst1;
    }

    public void setIdHakmilikFirst1(Hakmilik idHakmilikFirst1) {
        this.idHakmilikFirst1 = idHakmilikFirst1;
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan() {
        return senaraiFasaPermohonan;
    }

    public void setSenaraiFasaPermohonan(List<FasaPermohonan> senaraiFasaPermohonan) {
        this.senaraiFasaPermohonan = senaraiFasaPermohonan;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public String getHakmilikInduk() {
        return hakmilikInduk;
    }

    public void setHakmilikInduk(String hakmilikInduk) {
        this.hakmilikInduk = hakmilikInduk;
    }

    public List<Versi4k> getSenaraiHakmilikVersi4k() {
        return senaraiHakmilikVersi4k;
    }

    public void setSenaraiHakmilikVersi4k(List<Versi4k> senaraiHakmilikVersi4k) {
        this.senaraiHakmilikVersi4k = senaraiHakmilikVersi4k;
    }

    public List<Versi4k> getSenaraiHakmilikVersiStrata() {
        return senaraiHakmilikVersiStrata;
    }

    public void setSenaraiHakmilikVersiStrata(List<Versi4k> senaraiHakmilikVersiStrata) {
        this.senaraiHakmilikVersiStrata = senaraiHakmilikVersiStrata;
    }

    public List<String> getSenaraiTingkat() {
        return senaraiTingkat;
    }

    public void setSenaraiTingkat(List<String> senaraiTingkat) {
        this.senaraiTingkat = senaraiTingkat;
    }

    public String getNoTingkat() {
        return noTingkat;
    }

    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    public List<String> getSenaraiBangunan() {
        return senaraiBangunan;
    }

    public void setSenaraiBangunan(List<String> senaraiBangunan) {
        this.senaraiBangunan = senaraiBangunan;
    }

    public String getNoBangunan() {
        return noBangunan;
    }

    public void setNoBangunan(String noBangunan) {
        this.noBangunan = noBangunan;
    }

    public String getHakmilikStrata() {
        return hakmilikStrata;
    }

    public void setHakmilikStrata(String hakmilikStrata) {
        this.hakmilikStrata = hakmilikStrata;
    }

}

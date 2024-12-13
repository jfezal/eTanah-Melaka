/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.model.AduanTindakan;
import etanah.model.Dokumen;
import etanah.model.KodCawangan;


import etanah.model.KodTindakanPenguatkuasaan;
import etanah.service.common.EnforcementService;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanTindakanDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.KodKlasifikasi;
import etanah.view.etanahActionBeanContext;
//import etanah.model.KodStatusBarangRampasan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.service.common.KandunganFolderService;
import etanah.model.FolderDokumen;
import etanah.dao.KodDokumenDAO;
import etanah.model.KandunganFolder;
import etanah.report.ReportUtil;

import java.text.ParseException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_borang7A")
public class Borang7AactionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(Borang7AactionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanTindakanDAO aduanTindakanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private KodTindakanPenguatkuasaan tindakan;
    private AduanTindakan aduanTindakan;
    private Dokumen dokumen;
    private Pengguna peng;
    private Pengguna pengguna;
    private String idPermohonan;
    private String tarikhTamat;
    private int tempohHari;
    private Boolean errorMsg = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
         if ("05".equals(conf.getProperty("kodNegeri")) && permohonan.getKodUrusan().getKod().matches("127")) {
            if (permohonan.getKeputusan()==null){
                getContext().getRequest().setAttribute("form", Boolean.FALSE);
                addSimpleError("Sila buat keputusan terlebih dahulu");
            }else if(!permohonan.getKeputusan().getKod().matches("RE")){
                getContext().getRequest().setAttribute("form", Boolean.FALSE);
                addSimpleError("Borang 7A tidak perlu disediakan");
            }else{
                getContext().getRequest().setAttribute("form", Boolean.TRUE);
            }
        }else{
             getContext().getRequest().setAttribute("form", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/maklumat_borang7A.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_borang7A.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            aduanTindakan = enforcementService.findRemediByIdmohon(idPermohonan);


        }
    }

    public Resolution simpan() throws ParseException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
 peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        cawangan = permohonan.getCawangan();

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        InfoAudit iaPermohonan = new InfoAudit();
        if (dokumen == null) {
            dokumen = new Dokumen();
            iaPermohonan.setTarikhMasuk(new Date());
            iaPermohonan.setDimasukOleh(peng);
        } else {
            iaPermohonan.setTarikhKemaskini(new Date());
            iaPermohonan.setDiKemaskiniOleh(peng);
        }

        KodDokumen kodDoc = new KodDokumen();
        kodDoc.setKod("7A");
        dokumen.setKodDokumen(kodDoc);
        dokumen.setTajuk("Remedi");
        dokumen.setNoVersi("1.0");
        dokumen.setInfoAudit(iaPermohonan);
        dokumenDAO.saveOrUpdate(dokumen);



        if (aduanTindakan == null) {
            aduanTindakan = new AduanTindakan();
            iaPermohonan.setDimasukOleh(peng);
            iaPermohonan.setTarikhMasuk(new Date());
        } else {
            iaPermohonan.setDiKemaskiniOleh(peng);
            iaPermohonan.setTarikhKemaskini(new java.util.Date());
        }
        aduanTindakan.setPermohonan(permohonan);
        aduanTindakan.setCawangan(cawangan);
        aduanTindakan.setDokumen(dokumen);
        tindakan = new KodTindakanPenguatkuasaan();
        tindakan.setKod("REM");
        aduanTindakan.setTindakan(tindakan);
        //tarikhMula = getContext().getRequest().getParameter("aduanTindakan.tarikhMula");
        aduanTindakan.setTarikhMula(new Date());
        tempohHari = Integer.parseInt(getContext().getRequest().getParameter("aduanTindakan.tempohHari"));
        aduanTindakan.setTempohHari(tempohHari);
         tarikhTamat = getContext().getRequest().getParameter("aduanTindakan.tarikhTamat");
        aduanTindakan.setTarikhTamat(sdf.parse(tarikhTamat));
        aduanTindakan.setInfoAudit(iaPermohonan);
        aduanTindakanDAO.saveOrUpdate(aduanTindakan);
        tx.commit();
         getContext().getRequest().setAttribute("form", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/maklumat_borang7A.jsp").addParameter("tab", "true");
    }

//        public void janaDokumen() throws Exception {
//
//        if (permohonan != null) {
//            String[] params = new String[]{"p_id_mohon"};
//            String[] values = new String[]{permohonan.getIdPermohonan()};
//            String path = "";
//            String dokumenPath = conf.getProperty("document.path");
//            Dokumen d = null;
//            KodDokumen kd = null;
//
//            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
//            String reportName = "";
//
//                    kd = kodDokumenDAO.findById("PKT");
//                    reportName = "ENF_Borang_7A.rdf";
//                    d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
//                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
//                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//
//        }
//    }

        public Resolution janaBorang7A() throws FileNotFoundException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idPermohonan);
        reportUtil.generateReport("ENF_Borang_7A.rdf",
                //                new String[]{"p_id_kew_dok"},
                new String[]{"p_id_mohon"},
                new String[]{idPermohonan},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public AduanTindakan getAduanTindakan() {
        return aduanTindakan;
    }

    public void setAduanTindakan(AduanTindakan aduanTindakan) {
        this.aduanTindakan = aduanTindakan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
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

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodTindakanPenguatkuasaan getTindakan() {
        return tindakan;
    }

    public void setTindakan(KodTindakanPenguatkuasaan tindakan) {
        this.tindakan = tindakan;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }
        public Boolean getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Boolean errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
        private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            logger.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            logger.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        logger.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getKod() + "-" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
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

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAnsuran;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.service.AnsuranService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.math.BigDecimal;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
public class ProsesSuratKeputusan implements StageListener {
    private static final Logger LOG = Logger.getLogger(ProsesSuratKeputusan.class);
    
    @Inject
    KodDokumenDAO kodDokumenDAO;
    
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;

    @Inject
    AnsuranService ansuranService;
    @Inject
    ReportUtil reportUtil;

    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    
    @Inject
    DokumenService dokumenService;

    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;

    @Inject
    SemakDokumenService semakDokumenService;

    @Inject
    KandunganFolderService kandunganFolderService;

    @Inject
    etanah.Configuration conf;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna pengguna = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        KodUrusan kuMohon = permohonan.getKodUrusan();
        HakmilikPermohonan hakmilikP = permohonan.getSenaraiHakmilik().get(0);  //assume 1 permohonan = 1 hakmilik
//        Hakmilik hakmilik = hakmilikP.getHakmilik();
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        String path = null;
        String[] params = null;
        String[] values = null;
        String reportName = null;
        KodDokumen kd = kodDokumenDAO.findById("SKPN"); // set kod dokumen for Surat Keputusan
        try{
            LOG.info("start");
            for (FasaPermohonan fp : permohonan.getSenaraiFasa()) {
                if(fp.getKeputusan() == null){
                    continue;
                }
//                LOG.debug("fp.nama :"+fp.getKeputusan().getNama());
                if("BACT".equals(kuMohon.getKod())){ // untuk urusan ansuran melaka
//                    LOG.debug("kodUrusan is BACT");
                    if("L".equals(fp.getKeputusan().getKod())){ // untuk keputusan lulus
                        reportName = "HSLBayaranAnsuran_MLK.rdf";
                    }else if("T".equals(fp.getKeputusan().getKod())){
                        reportName = "HSLSuratPenolakanBayaranAnsuranCukaiTanah.rdf";
                    }
                }else if("RBYA".equals(kuMohon.getKod())){ // utk urusan ansuran N9
                    if("L".equals(fp.getKeputusan().getKod())){ // untuk keputusan lulus
                        reportName = "HSLSuratAnsuran_NS.rdf";
                    }else if("T".equals(fp.getKeputusan().getKod())){
                        reportName = "HSLSuratTolak.rdf";
                    }
                }else{ // untuk urusan lain-lain selain ansuran
                    if("04".equals(conf.getProperty("kodNegeri"))){ // negeri melaka
//                      LOG.debug("kodUrusan not BACT");
                        if("L".equals(fp.getKeputusan().getKod())){ // untuk keputusan lulus
                            reportName = "hasilRayuanKurangCukaiML.rdf";
                            }else if("T".equals(fp.getKeputusan().getKod())){
                                reportName = "HSLSuratTolak.rdf";
                            }
                    }else if("05".equals(conf.getProperty("kodNegeri"))){ // negeri sembilan
//                      LOG.debug("kodUrusan not BACT");
                        if("L".equals(fp.getKeputusan().getKod())){ // untuk keputusan lulus
                            reportName = "hasilRayuanKurangCukai.rdf";
                        }else if("T".equals(fp.getKeputusan().getKod())){
                            reportName = "HSLSuratTolak.rdf";
                        }
                    }
                }
            }
            params = new String[]{"p_id_mohon"};
            values = new String[]{permohonan.getIdPermohonan()};

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            e = saveOrUpdateDokumen(fd, kd, hakmilikP.getHakmilik().getIdHakmilik(), pengguna);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
        }catch (Exception ex){
            LOG.error("Tiada keputusan sepadan (L,T):"+ex);
            ex.printStackTrace(); // for 
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
//        throw new UnsupportedOperationException("Not supported yet.");
        Permohonan permohonan = context.getPermohonan();
PermohonanAnsuran pa = ansuranService.findByIdPermohonan(permohonan);
        Transaksi trans = new Transaksi();
        KodTransaksi kodTransaksi = kodTransaksiDAO.findById("");
        Transaksi tran = new Transaksi();
        tran.setKodTransaksi(kodTransaksi);
        Akaun akaun = new Akaun();
        akaun.setBaki(BigDecimal.ZERO);
        akaun.setAmaunMatang(BigDecimal.TEN);
        akaun.setAnsuran('Y');
        trans.setKodTransaksi(kodTransaksi);
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna peng) {
        InfoAudit ia = new InfoAudit();
        KodKlasifikasi kodKlas = kodKlasifikasiDAO.findById(1); //kod klasifikasi : 1 = Am, 2 = Terhad, 3 = Sulit, 4 = Rahsia, 5 = Rahsia Besar
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("doc null");
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOG.debug("doc :"+doc.getIdDokumen());
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setKlasifikasi(kodKlas);
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
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

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

     @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

}

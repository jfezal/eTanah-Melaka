/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
public class KemaskiniJadualPetakValidation implements StageListener {

    @Inject
    StrataPtService strService;
    FasaPermohonan fasaPermohonan;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    FasaPermohonanDAO mohonFasaDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    Dokumen dokumen;
    String path;
    Pengguna pengguna;
    KodDokumen kd = null;
    FolderDokumen fd;
    private Hakmilik hakmilik;
    private String idHakmilik;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    CommonService comm;
    private Permohonan permohonan;
    private static final Logger LOG = Logger.getLogger(KemaskiniJadualPetakValidation.class);

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext sc) {
//       
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan p = sc.getPermohonan();
        this.pengguna = sc.getPengguna();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        PermohonanTandatanganDokumen ptd = new PermohonanTandatanganDokumen();

        String reportName;

        String path = "";

        fd = folderDokumenDAO.findById(sc.getPermohonan().getFolderDokumen().getFolderId());
        mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan");
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        if (mohonFasa != null) {
            if (!mohonFasa.getKeputusan().equals(null)) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    if (p.getKodUrusan().getKod().equals("RMHS1")) {

                        if (kodNegeri.equals("04")) {


                            t.add("STRSLulusPindaPermohonan_MLK.rdf");
                            t2.add("SLMP");
                            reportGen(p, t, t2);
                        } else {
//                            t.add("STRSLulusPindaPermohonan_NS.rdf");
//                            t2.add("SLMP");
                            t.add("STRSLulusPindaBorang_NS.rdf");
                            t2.add("SLMP");
                            reportGen(p, t, t2);
                        }
                    }
                } else {
                    if (p.getKodUrusan().getKod().equals("RMHS1")) {
                        if (kodNegeri.equals("04")) {
//                            t.add("STRSTolakWaranJUPEM_MLK.rdf");
//                            t2.add("STJWR");

                            t.add("STRSTolakPindaPermohonan_MLK.rdf");
                            t2.add("STMP");
                            reportGen(p, t, t2);
                        } else {
//                            t.add("STRSTolakPindaPermohonan_NS.rdf");
//                            t2.add("STMP");
                            t.add("STRSTolakPindaBorang_NS.rdf");
                            t2.add("STMP");
                            reportGen(p, t, t2);
                        }

                    }
                }
            }
        }
    }

    private void reportGen(Permohonan p, List<String> t, List<String> t2) {
        String dokumenPath = conf.getProperty("document.path");
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        Dokumen d = null;
        KodDokumen kd = null;

        for (int i = 0; i < t.size(); i++) {
            String gen = t.get(i);
            String code = t2.get(i);
            kd = kodDokumenDAO.findById(code);
            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(gen, params, values, dokumenPath + path, this.pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(this.pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(this.pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
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
            ia.setDiKemaskiniOleh(this.pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    @Override
    public String beforeComplete(StageContext sc, String string) {
        fasaPermohonan = strService.findFasaPermohonanByIdAliran(sc.getPermohonan().getIdPermohonan(), "keputusan");
        if (fasaPermohonan != null) {
            if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                for (PermohonanBangunan mohonBangunan : sc.getPermohonan().getPermohonanSebelum().getSenaraiBangunan()) {
                    for (BangunanTingkat bt : mohonBangunan.getSenaraiTingkat()) {
                        for (BangunanPetak bp : bt.getSenaraiPetak()) {
                            for (BangunanPetakAksesori bpa : bp.getSenaraiPetakAksesori()) {
                                strService.deleteAksesori(bpa);
                            }
                            strService.deletePetak(bp);
                        }
                        strService.deleteTgkt(bt);
                    }
                    strService.deleteBngn(mohonBangunan);
                }
                updateNewPetak(sc);
            }
        }

        return string;
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void updateNewPetak(StageContext sc) {
        for (PermohonanBangunan pb : sc.getPermohonan().getSenaraiBangunan()) {
            pb.setPermohonan(sc.getPermohonan().getPermohonanSebelum());
            strService.saveMohonBangunan(pb);
        }

    }
}

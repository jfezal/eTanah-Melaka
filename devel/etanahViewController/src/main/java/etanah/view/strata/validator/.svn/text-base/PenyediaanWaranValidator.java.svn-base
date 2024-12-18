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
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.NotisButiran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanDokumen;
import etanah.model.PermohonanSemakDokumen;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanWaranItem;
import etanah.model.Pihak;
import etanah.model.Waran;
import etanah.model.WaranPihak;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author ${user}
 */
public class PenyediaanWaranValidator implements StageListener {

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
    StrataPtService strService;
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
    private static final Logger LOG = Logger.getLogger(PenyediaanWaranValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(ctx.getPermohonan().getIdPermohonan(), "keputusan");
        if (mohonFasa != null) {
            //  System.out.println("-----getKeputusan-----"+mohonFasa.getKeputusan().getKod());
            if (mohonFasa.getKeputusan() != null) {
                return true;
            } else {
                ctx.addMessage("Sila masukkan keputusan");
                return false;
            }
        } else {
            ctx.addMessage("Sila masukkan keputusan");
            return false;
        }

    }

    @Override
    public void onGenerateReports(StageContext context) {
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan p = context.getPermohonan();
        this.pengguna = context.getPengguna();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        PermohonanTandatanganDokumen ptd = new PermohonanTandatanganDokumen();

        String reportName;

        String path = "";

        fd = folderDokumenDAO.findById(context.getPermohonan().getFolderDokumen().getFolderId());
        mohonFasa = strService.findMohonFasa(p.getIdPermohonan(), "keputusan");
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        ptd.setDiTandatangan(context.getPengguna().getIdPengguna());
        ptd.setCawangan(context.getPengguna().getKodCawangan());
        ptd.setInfoAudit(strService.getInfo(context.getPengguna()));
        ptd.setPermohonan(p);
        ptd.setKodDokumen(kodDokumenDAO.findById("KPWRN"));
        ptd = strService.saveDokumenTT(ptd);
        System.out.println("-----mohonFasa-----" + mohonFasa);
        if (mohonFasa != null) {
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    waran2(context.getPermohonan(), context.getPengguna());
                    if (p.getKodUrusan().getKod().equals("PWPN")) {
                        if (kodNegeri.equals("04")) {

                            t.add("STRKertasPertimbanganWaran_MLK.rdf");
                            t2.add("KPWRN");
                            t.add("STRB7AWaranPenahanan_MLK.rdf");
                            t2.add("STR7A");

                            t.add("STRSLulusWaran_MLK.rdf");
                            t2.add("SLWRN");
                            reportGen(p, t, t2);
                        } else {

                            t.add("STRB7AWaranPenahanan_NS.rdf");
                            t2.add("STR7A");

                            t.add("STRSLulusWaran_NS.rdf");
                            t2.add("SLWRN");
                            t.add("STRKertasPertimbanganWaran_NS.rdf");
                            t2.add("KPWRN");
                            reportGen(p, t, t2);
                        }
                        ptd.setDiTandatangan(context.getPengguna().getIdPengguna());
                        ptd.setCawangan(context.getPengguna().getKodCawangan());
                        ptd.setInfoAudit(strService.getInfo(context.getPengguna()));
                        ptd.setPermohonan(p);
                        ptd.setKodDokumen(kodDokumenDAO.findById("KPWRN"));
                        ptd = strService.saveDokumenTT(ptd);

                    }
                } else {
                    if (p.getKodUrusan().getKod().equals("PWPN")) {
                        if (kodNegeri.equals("04")) {

                            t.add("STRSTolakWaran_MLK.rdf");
                            t2.add("STWRN");
                            t.add("STRKertasPertimbanganWaran_MLK.rdf");
                            t2.add("KPWRN");
                            reportGen(p, t, t2);
                        } else {

                            t.add("STRSTolakWaran_NS.rdf");
                            t2.add("STWRN");
                            t.add("STRKertasPertimbanganWaran_NS.rdf");
                            t2.add("KPWRN");
                            reportGen(p, t, t2);
                        }

                    }
                }
            } else if (mohonFasa.getKeputusan() == null) {
                context.addMessage("Sila masukkan keputusan");
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

    public void waran2(Permohonan permohonan, Pengguna pengguna) {
        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        FasaPermohonan mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();
        if (mohonFasa != null) {
            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                List<HakmilikPermohonan> lstMohonhakmilik = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hm : lstMohonhakmilik) {
                    String idHakmilikS = hm.getHakmilik().getIdHakmilik();
                    Waran waran = comm.findWaran(permohonan.getIdPermohonan(), idHakmilikS);
                    if (waran != null) {
                        LOG.info("NOT NULL");
                    } else {

                        waran = new Waran();
                    }
                    waran.setIdPermohonan(permohonan);
                    LOG.info("Hakmilik : " + hm.getHakmilik().getIdHakmilik());
                    waran.setHakmilik(hm.getHakmilik());
                    waran.setCawangan(pengguna.getKodCawangan());
                    waran.setInfoAudit(ia);
                    waran.setTarikhWaranKeluar(mohonFasa.getInfoAudit().getTarikhMasuk());
                    Date date = mohonFasa.getInfoAudit().getTarikhMasuk();
                    c1.setTime(date);
                    c1.add(Calendar.DATE, 30);
                    waran.setTarikhAkhirSah(c1.getTime());
                    waran = strService.saveWaran(waran);
                    List<Pihak> lstPihak = strService.getPihakPM(hm.getHakmilik().getIdHakmilik());
                    for (Pihak p : lstPihak) {
                        WaranPihak waranPihak = comm.findWaranPihak(waran.getIdWaran(), p.getIdPihak());
                        if (waranPihak != null) {
                        } else {
                            waranPihak = new WaranPihak();
                        }
                        waranPihak.setCawangan(pengguna.getKodCawangan());
                        waranPihak.setInfoAudit(ia);
                        waranPihak.setWaran(waran);
                        waranPihak.setPihak(p);
                        waranPihak = strService.saveWaranPihak(waranPihak);
                    }
                    List<PermohonanWaranItem> listMohonWaran = new ArrayList<PermohonanWaranItem>();
                    LOG.info("HakmilikPermohonan : " + hm.getId());
                    listMohonWaran = comm.findSenaraiItemWaran(permohonan.getIdPermohonan(), hm.getId());
                    for (PermohonanWaranItem waranItem : listMohonWaran) {
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new Date());
                        ia.setDimasukOleh(waranItem.getInfoAudit().getDimasukOleh());
                        ia.setTarikhMasuk(waranItem.getInfoAudit().getTarikhMasuk());
                        waranItem.setInfoAudit(ia);
                        waranItem.setWaran(waran);
                        waranItem = comm.saveMohonWaranItem(waranItem);
                    }
                    waran = new Waran();
                }
            } else {
                //proposedOutcome = T;
                //call complete task?
                LOG.info("mohonFasa: keputusanpentadbirtanah : empty");
            }
        }

    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();

        mohonFasa = strService.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(), "keputusan");
        if (mohonFasa != null) {
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L") || mohonFasa.getKeputusan().getKod().equals("T")) {
                    permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                    LOG.debug("--------permohonan----:" + permohonan.getIdPermohonan());
                    if (permohonan != null) {
                        // KodKeputusan kodkpsn = mohonFasa.getKeputusan();
                        permohonan.setUlasan(mohonFasa.getUlasan());
                        permohonan.setKeputusan(mohonFasa.getKeputusan());
                        Pengguna peng = (Pengguna) context.getPengguna();
                        permohonan.setKeputusanOleh(peng);
                        permohonan.setTarikhKeputusan(new Date());
                        strService.updateMohon(permohonan);
                    }
                } else {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan keputusan");
                    return null;
                }
            } else {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan keputusan");
                return null;
            }
        } else {
            context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan keputusan");
            return null;
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
       // return proposedOutcome;
        return "back";
    }
}

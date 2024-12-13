/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.validator;

import java.util.List;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import org.hibernate.Session;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.dao.PenggunaDAO;
import etanah.model.InfoAudit;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import java.util.ArrayList;
import org.apache.log4j.Logger;
//import etanah.model.SejarahHakmilik;
import etanah.service.RegService;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikSebelum;
import etanah.service.SyerValidationService;
import etanah.service.HakmilikService;
import org.apache.commons.math.fraction.Fraction;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.common.PermohonanPihakService;
import etanah.model.Pihak;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.model.KodStatusHakmilik;
import etanah.model.HakmilikUrusan;
import etanah.service.common.HakmilikUrusanService;
import etanah.model.Akaun;
import etanah.model.KodAkaun;
import etanah.service.common.HakmilikPermohonanService;
import etanah.model.Dokumen;
import etanah.report.ReportUtil;
import java.io.File;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.service.common.DokumenService;
import etanah.model.KodDokumen;
import etanah.service.SemakDokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.model.KandunganFolder;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodStatusAkaunDAO;
import etanah.dao.KodStatusHakmilikDAO;
import org.hibernate.Transaction;
import etanah.sequence.GeneratorNoAkaun;
//import etanah.view.stripes.hasil.TransactionActionBean;
import etanah.model.KodCawangan;
import etanah.model.Transaksi;
import java.text.SimpleDateFormat;
import etanah.dao.KodTransaksiDAO;
import etanah.view.stripes.hasil.KutipanHasilManager;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.HakmilikLama;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.Notis;
import etanah.report.CallableReport;
import etanah.service.NotifikasiService;
import etanah.model.FolderDokumen;
import etanah.model.KodJabatan;
import etanah.model.KodLot;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.PermohonanPihak;
import etanah.service.common.FasaPermohonanService;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.SejarahDokumen;
import etanah.sequence.GeneratorIdHakmilik;
import etanah.sequence.GeneratorIdPerserahan;
//import etanah.service.ReportName;
import java.math.BigDecimal;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.PembetulanService;
import etanah.util.WORMUtil;
import etanah.view.etanahContextListener;
import etanah.view.etapp.ws.EtappLogService;
import etanah.view.stripes.hasil.UtilityQTFTActionBean;
//import etanah.view.lelong.validator.PermohoanBatalValidator;
import etanah.view.utility.ETappUtil;
import etanah.workflow.WorkFlowService;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.sourceforge.stripes.action.Resolution;
//import java.util.logging.Level;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
//import org.hibernate.Query;

/**
 * Listener to Generate Report
 *
 * @ Pendaftar for Urusan MH
 * @author khairil
 */
public class HsbmValidation implements StageListener {

    private static final Logger logger = Logger.getLogger(HsbmValidation.class);
//    private static final Logger syslog = Logger.getLogger("SYSLOG");
    @Inject
    RegService regService;
//    @Inject
//    AkaunService akService;
    @Inject
    UtilityQTFTActionBean qtft;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    HakmilikService hakmilikService_;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    SyerValidationService syerService;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    HakmilikUrusanService hakmilikService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    ReportUtil reportUtil2;
    @Inject
    ReportUtil reportUtil3;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    GeneratorNoAkaun genNoAkaun;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KutipanHasilManager manager;
    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    ETappUtil util;
    @Inject
    EtappLogService logservice;
    @Inject
    private KodStatusAkaunDAO kodStatusAkaunDAO;
//    @Inject
//    ReportName reportName;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    private GeneratorIdHakmilik idHakmilikGenerator;
    @Inject
    private KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    private HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO;
    @Inject
    private HakmilikSebelumPermohonanDAO hakmilikSblmPermohonanDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private PemohonService pemohonService;
    @Inject
    private HakmilikUrusanService hakmilikUrusanService;
    @Inject
    PembetulanService pService;
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    HakmilikSebelumPermohonanDAO HakmilikSblmDAO;
    @Inject
    HakmilikAsalPermohonanDAO HakmilikAsalDAO;
//    @Inject
//    TransactionActionBean trans;
    private Pengguna pengguna;
    private Transaksi transaksi;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    private static final String kod_transaksi = "61401";
    private String idTransaksi;
    SimpleDateFormat sdfoct = new SimpleDateFormat("dd/MM/yyyy");
    private Notis notis;
    private FolderDokumen folder;
    private Akaun akaun;
    private Hakmilik HK;
    private String Akaun;
    private String idHakmilik;
    private String idHakmilikSblm;
    private String idHakmilikAsal;
    private HakmilikPermohonan hp;
    private String kodUrusan;
    private BigDecimal bakiCukai;
    private BigDecimal CukaiSemasa;
    private BigDecimal bakiTotal;
    private List<HakmilikSebelumPermohonan> HakmilikSblm;
    private List<HakmilikAsalPermohonan> HakmilikAsal;
    private List<Akaun> akaunList;
    private List<HakmilikUrusan> hakmilikUrusanHmLama;
    private List<Pemohon> pemohonBermasalah;
    private List<PermohonanPihak> MohonPihakBermasalah;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    private List<HakmilikSebelumPermohonan> listHakmilikSblmPermohonan2;
    private List<HakmilikSebelumPermohonan> listHakmilikSblmPermohonan;
    private List<HakmilikAsalPermohonan> listHakmilikAsalPermohonan;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihakmasaalah;
    private List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
    Boolean flag;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        logger.info(" /* BEFORE COMPLETE */ ");
        logger.debug("Starting beforeComplete Listener");
        logger.debug("proposedOutcome :" + proposedOutcome);

        Permohonan permohonan = context.getPermohonan();
        KodCawangan kodcawangan = permohonan.getCawangan();
        Pengguna pengguna = context.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());

//        if (permohonan.getKodUrusan().equals("HKGHS")) {
//            blockingAlert(permohonan, info, peng, context);
//        }
        if (proposedOutcome.equals("D")) {
            Transaction tx = sessionProvider.get().beginTransaction();
            tx.begin();
            try {
                insertUrusan(permohonan, info, proposedOutcome);
                if (!permohonan.getKodUrusan().getKod().equals("HKTHK")) {
                    saveAkaun(permohonan, peng, info);

                }

                movePihak(permohonan, peng, info);
                moveMHANMHS(permohonan, info);
                if (permohonan.getKodUrusan().getKod().equals("HKTHK")
                        || permohonan.getKodUrusan().getKod().equals("HSTHK")
                        || permohonan.getKodUrusan().getKod().equals("HMSC")) {
                    updateHakmilikLama(context);
                }

                if (permohonan.getKodUrusan().getKod().equals("HTIR")) {
                    doJanaHakmilikBaru(permohonan, info);
                    //                   saveAkaun(permohonan, peng, info);
                }

                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                Throwable t = e;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                t.printStackTrace();
                context.addMessage("Pendaftaran Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                return null;
            }

            if (permohonan.getKodUrusan().getKod().equals("HKSTA")) {
                notifikasi(context);
            }
            updateVersi2(permohonan, info, peng);

        }

        //TODO : should return msg
        context.addMessage(" - Urusan telah selesai.");
        return proposedOutcome;
    }

    public void process(Permohonan permohonan) {
        logger.info("----------------------------------------PROCESS START----------------------------------------");
//        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Permohonan permohonanNew = permohonan;
        logger.info("idPermohonan : " + permohonanNew.getIdPermohonan());
        List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
        List<HakmilikAsalPermohonan> a_temp = new ArrayList<HakmilikAsalPermohonan>();
        String hakmilik_temp = "";
        List<HakmilikPermohonan> senaraiHakmilik = permohonanNew.getSenaraiHakmilik();
        String table = "sblm";
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            l_temp = hp.getSenaraiHakmilikSebelum();
        }

        for (HakmilikSebelumPermohonan h : l_temp) {
            listHakmilik.add(hakmilikDAO.findById(h.getHakmilikSejarah()));
        }

        if (listHakmilik.isEmpty()) {
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                a_temp = hp.getSenaraiHakmilikAsal();
            }

            for (HakmilikAsalPermohonan h : a_temp) {
                logger.info("h.getHakmilikSejarah() : " + h.getHakmilikSejarah());
                table = "asal";
                listHakmilik.add(hakmilikDAO.findById(h.getHakmilikSejarah()));
            }
        }
        if (!listHakmilik.isEmpty()) {
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                hakmilik_temp = hp.getHakmilik().getIdHakmilik();
            }
        }

        if (qtft.processTunggakan(listHakmilik, null, table)) {
//            addSimpleMessage("Process telah berjaya. Sila ke menu Pertanyaan Hakmilik untuk semakan transaksi.");
//            addSimpleError("PERHATIAN : Process telah berjaya. Sila ke menu Pertanyaan Hakmilik untuk semakan transaksi.")
            flag = true;
        }
//        else if (!hakmilik_temp.equals("")) {
////             processTunggakan2(listHakmilik, null, table, hakmilik_temp);
////            addSimpleError("Process tidak berjaya. Sila hubungi pentadbir sistem untuk maklumat lanjut.");
//        } else {
//
//        }
    }

    @Override
    public void afterComplete(StageContext context) {
        logger.info(" /* AFTER COMPLETE */ ");
        // TODO Auto-generated method stub
        Pengguna pengguna = context.getPengguna();
        Permohonan p = context.getPermohonan();
        InfoAudit ia = new InfoAudit();

        List<Permohonan> senaraiPermohonanTerlibat = new ArrayList<Permohonan>();
        StringBuilder sb = new StringBuilder();

        if (p.getKodUrusan().getKod().equals("HKGHS")) {
            process(p);
        }
        if (p.getKodUrusan().getKod().equals("HKSTK")) {
            process(p);
        }

        List<SejarahDokumen> listSejarah = new ArrayList<SejarahDokumen>();

        if (p != null && p.getKeputusan() != null
                && p.getKeputusan().getKod().equals("D")) {

            String idKumpulan = p.getIdKumpulan();
            if (StringUtils.isNotBlank(idKumpulan)) {
                senaraiPermohonanTerlibat = permohonanService.getPermohonanByIdKump(idKumpulan);
            } else {
                senaraiPermohonanTerlibat.add(p);
            }

            for (Permohonan pmohon : senaraiPermohonanTerlibat) {
                if (pmohon == null) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(pmohon.getIdPermohonan());
                List<HakmilikPermohonan> senaraiHakmilikTerlibat = pmohon.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                    if (hp == null || hp.getHakmilik() == null) {
                        continue;
                    }

                    //sejarah dokumen
                    SejarahDokumen sejarahDokumen = new SejarahDokumen();
                    ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new Date());

                    Hakmilik hm = hp.getHakmilik();

                    if (hp.getDokumen3() != null) {

                        sejarahDokumen.setIdDokumen(hp.getDokumen3().getIdDokumen());
                        sejarahDokumen.setInduk(hm.getDhde() != null ? hm.getDhde() : hp.getDokumen3());
                        sejarahDokumen.setKodDokumen(hp.getDokumen3().getKodDokumen());
                        sejarahDokumen.setNamaFizikal(hp.getDokumen3().getNamaFizikal());
                        sejarahDokumen.setNoVersi(String.valueOf(hm.getNoVersiDhde()));
                        sejarahDokumen.setFormat(hp.getDokumen3().getFormat());
                        sejarahDokumen.setInfoAudit(ia);

                        listSejarah.add(sejarahDokumen);

                    }
                }
            }

            dokumenService.saveOrUpdateSejarahDokumen(listSejarah);

            context.addMessage("Perserahan " + sb.toString() + " telah berjaya didaftarkan.");

            //integrate with HCAP
            //todo : save sign file to HCAP
            WORMUtil worm = etanahContextListener.newInstance(WORMUtil.class);
            List<Dokumen> senaraiDokumen = new ArrayList<Dokumen>();
            String docPath = conf.getProperty("document.path");
            List<HakmilikPermohonan> senaraiHm = p.getSenaraiHakmilik();
            for (HakmilikPermohonan hmp : senaraiHm) {
                Hakmilik hm = hmp.getHakmilik();
                if (hm == null) {
                    continue;
                }
                Dokumen d = hm.getDhde();

                if (d != null) {
                    String namaFizikalAsal = d.getNamaFizikal();
                    File dhde = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                            + namaFizikalAsal);
                    if (dhde != null) {
                        try {
                            int status = worm.put(dhde,
                                    hm.getIdHakmilik(),
                                    hm.getDaerah().getKod(), hm.getBandarPekanMukim().getbandarPekanMukim(),
                                    null,
                                    hm.getKodHakmilik().getKod(),
                                    String.valueOf(hm.getNoVersiDhde() != null ? hm.getNoVersiDhde() : 0),
                                    hm.getKodStatusHakmilik().getKod());
                            if (status == WORMUtil.SC_CREATED
                                    || status == WORMUtil.SC_CREATED_W_ERROR) {
                                // once successed create on WORM
                                // delete file on dms, update path dokumen
                                dhde.delete();
                                String path = worm.buildPath(hm.getDaerah().getKod(),
                                        hm.getBandarPekanMukim().getbandarPekanMukim(),
                                        null,
                                        hm.getKodHakmilik().getKod(),
                                        String.valueOf(hm.getNoVersiDhde() != null ? hm.getNoVersiDhde() : 0)).toString();
                                d.setNamaFizikal(path + File.separator + hm.getIdHakmilik());
                                senaraiDokumen.add(d);
                            }
                        } catch (IOException ex) {
                        }
                    }
                    File sign = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                            + namaFizikalAsal + ".sig");
                    if (sign.exists()) {
                        String filename = hm.getIdHakmilik() + ".sig";
                        try {
                            int status = worm.put(sign,
                                    filename,
                                    hm.getDaerah().getKod(), hm.getBandarPekanMukim().getbandarPekanMukim(),
                                    null,
                                    hm.getKodHakmilik().getKod(),
                                    String.valueOf(hm.getNoVersiDhde() != null ? hm.getNoVersiDhde() : 0),
                                    hm.getKodStatusHakmilik().getKod());

                            if (status == WORMUtil.SC_CREATED
                                    || status == WORMUtil.SC_CREATED_W_ERROR) {
                                // once successed create on WORM
                                // delete file on dms, update path dokumen
                                sign.delete();
                            }
                        } catch (IOException ex) {
                        }
                    }
                }
            }
            if (!senaraiDokumen.isEmpty()) {
                dokumenService.saveOrUpdate(senaraiDokumen);
            }
        }

        //penghantaran notifikasi kepada yg berkenaan
        if (p.getPermohonanSebelum() != null) {
            pengguna = context.getPengguna();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
            Permohonan integrasiPermohonan = p.getPermohonanSebelum();
            Notifikasi notifikasi = new Notifikasi();
            notifikasi.setTajuk("Maklumat Perserahan berjaya didaftarkan");
            notifikasi.setCawangan(integrasiPermohonan.getCawangan());
            StringBuilder mesej = new StringBuilder("ID Permohonan ")
                    .append(integrasiPermohonan.getIdPermohonan())
                    .append(" ( ")
                    .append(integrasiPermohonan.getKodUrusan().getNama())
                    .append(" ) ")
                    .append("telah di ")
                    .append(p.getKeputusan().getNama());

            FasaPermohonan fp = fasaPermohonanService.getCurrentStage(integrasiPermohonan.getIdPermohonan(), p.getIdStagePermohonanSebelum());
            if (fp != null && fp.getInfoAudit() != null) {
                Pengguna pg = fp.getInfoAudit().getDimasukOleh();
                notifikasi.setPengguna(pg);
            }

            notifikasi.setMesej(mesej.toString());
            notifikasi.setInfoAudit(ia);
            notifikasiService.save(notifikasi);
        }

        KodUrusan ku = p.getKodUrusan();
        if ("HSBM".equals(ku.getKod()) || "HKBM".equals(ku.getKod()) || "HKGHS".equals(ku.getKod()) || "HKSTK".equals(ku.getKod())) {
            //since there is no relationship between mohon and hakmilikPihak bcos didnt insert into mohon_pihak,
            //so , just assume all pemilik for this urusan is bangsa=KP
            List<Hakmilik> senaraiHm = new ArrayList<Hakmilik>();
            List<HakmilikPermohonan> senaraiHakmilikTerlibat = p.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                List<HakmilikPihakBerkepentingan> senaraiPemilik = hakmilikPihakKepentinganService
                        .findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hm);
                for (HakmilikPihakBerkepentingan pemilik : senaraiPemilik) {
                    if (pemilik == null || pemilik.getPihak() == null
                            || pemilik.getPihak().getBangsa() == null
                            || !"KP".equals(pemilik.getPihak().getBangsa().getKod())) {
                        continue;
                    }
                    hm.setMilikPersekutuan('Y');
                    String result = util.insertHakmilikFederal(hm);
                    if (result.equals("201")) {
                        logservice.insertLog("HTP", "1", "Hakmilik Federal =" + hm.getIdHakmilik());
                    } else {

                        logservice.insertLog("HTP", "2", "Hakmilik Federal =" + hm.getIdHakmilik());
                    }
                    senaraiHm.add(hm);
                    break;
                }
            }
            if (!senaraiHm.isEmpty()) {
                regService.simpanHakmilikList(senaraiHm);
            }
            Permohonan permohonan = context.getPermohonan();
            KodCawangan kodcawangan = permohonan.getCawangan();
            Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            updateAkaunLama(permohonan, info, peng);
        }
    }

    @Override
    public void onGenerateReports(StageContext context) {
        logger.info(" /* ON GENERATE REPORT */ ");
        Pengguna pengguna = context.getPengguna();
        Permohonan p = context.getPermohonan();
        List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        String keputusan = context.getPermohonan().getKeputusan().getKod();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        if (keputusan.equals("D")) {
            String dokumenPath = conf.getProperty("document.path");
            Dokumen e = null;
            Dokumen f = null;
            Dokumen g = null;
            Dokumen h = null;
            String idFolder = "";
            Permohonan permohonan = context.getPermohonan();
            String idPermohonan = permohonan.getIdPermohonan();

            KodUrusan ku = permohonan.getKodUrusan();

            idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
            String[] params = null;
            String[] values = null;
            String[] params2 = null;
            String[] values2 = null;
//        String path = "";
            String path2 = "";
            String path3 = "";
            String path4 = "";
            String path5 = "";
            String gen2 = "";
            String gen3 = "";
            String gen4 = "";
            String gen5 = "";
            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
            KodDokumen kd2 = new KodDokumen();
            KodDokumen kd3 = new KodDokumen();
            KodDokumen kd4 = new KodDokumen();
            KodDokumen kd5 = new KodDokumen();
            for (HakmilikPermohonan hakmilikPermohonan : hk) {
                logger.info("--> id hakmilik baru : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                /*TODO SET TARIKH DAFTAR AND TARIKH LUPUT FOR HSBM AND HKBM  AFTER PENDAFTAR DAFTAR*/
                //Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                String idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);

                if (hakmilik != null) {
                    if (hakmilik.getNoVersiDhde() == null) {
                        hakmilik.setNoVersiDhde(0);
                    }
                    if (hakmilik.getNoVersiDhke() == null) {
                        hakmilik.setNoVersiDhke(0);
                    }
                }

                /*SAVE UPDATE HAKMILIK BARU / CURRENT HAKMILIK */
//                if (!ku.getKod().equals("HSTHK") && !ku.getKod().equals("HKTHK") && !ku.getKod().equals("HSPS") && !ku.getKod().equals("HKGHS") && !ku.getKod().equals("HSSB") && !ku.getKod().equals("HKABS")) {
                if (idPermohonan.substring(4, 6).equals("MH")) {
                    if (!permohonan.getKodUrusan().getKod().equals("HKTHK")
                            && !permohonan.getKodUrusan().getKod().equals("HSTHK")
                            && !permohonan.getKodUrusan().getKod().equals("HKSTK")
                            && !permohonan.getKodUrusan().getKod().equals("HKGHS")) {
                        HakmilikSblm = regService.findIdHakmilikSebelumMohonByIdHakmilik(idHakmilik);
                        HakmilikAsal = regService.findIdHakmilikAsalMohonByIdHakmilik(idHakmilik);
                        hakmilik.setTarikhDaftar(new java.util.Date()); //tambah utk test HTIR tak store tarikh daftar
                        if (HakmilikSblm.size() > 0) {
                            for (HakmilikSebelumPermohonan hsp : HakmilikSblm) {
                                String idHakmilikSblm = hsp.getHakmilikSejarah();
                                if (idHakmilikSblm.equals(idHakmilik)) {
                                    if (idHakmilik.equals(idHakmilikSblm)) {
                                        hakmilik.setTarikhDaftar(new java.util.Date());
                                        if (hakmilik.getPegangan() != null) {
                                            if (hakmilik.getPegangan() == 'P') {
                                                Calendar calendar = new GregorianCalendar();
                                                calendar.setTime(hakmilik.getTarikhDaftar());
                                                /*CALC BASED ON DAY OF YEAR*/
                                                //calendar.add(Calendar.DAY_OF_YEAR, tempohPegangan * 365);
                                                calendar.add(Calendar.YEAR, hakmilik.getTempohPegangan() * 1);
                                                calendar.add(Calendar.DAY_OF_MONTH, -1);
                                                Date tarikhLuput = calendar.getTime();
                                                logger.debug("tarikhLuput : " + tarikhLuput);
                                            } else {
                                                hakmilik.setTempohPegangan(0);
                                                hakmilik.setTarikhLuput(null);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (permohonan.getKodUrusan().getKod().equals("HSPB")) {
                            if (HakmilikAsal.size() > 0) {
                                for (HakmilikAsalPermohonan hap : HakmilikAsal) {
                                    String idHakmilikAsal = hap.getHakmilikSejarah();
                                    if (!idHakmilik.equals(idHakmilikAsal)) {
                                        hakmilik.setTarikhDaftar(new java.util.Date());
//                                        if (hakmilik.getPegangan() != null) {
//                                            if (hakmilik.getPegangan() == 'P') {
//                                                Calendar calendar = new GregorianCalendar();
//                                                calendar.setTime(hakmilik.getTarikhDaftar());
//                                                /*CALC BASED ON DAY OF YEAR*/
//                                                //calendar.add(Calendar.DAY_OF_YEAR, tempohPegangan * 365);
//                                                if (hakmilik.getKodHakmilik().getKod().equals("PN") || hakmilik.getKodHakmilik().getKod().equals("PM") || hakmilik.getKodHakmilik().getKod().equals("HSM")) {
//                                                    calendar.add(Calendar.YEAR, hakmilik.getTempohPegangan() * 1);
//                                                    calendar.add(Calendar.DAY_OF_MONTH, -1);
//                                                    Date tarikhLuput = calendar.getTime();
//                                                    logger.debug("tarikhLuput : " + tarikhLuput);
//                                                }
//                                            } else {
//                                                hakmilik.setTempohPegangan(0);
//                                                hakmilik.setTarikhLuput(null);
//                                            }
//                                        }
                                    }
                                }
                            }
                        }
                        if (permohonan.getKodUrusan().getKod().equals("HKTK")) {
                            if (HakmilikAsal.size() > 0) {
                                for (HakmilikAsalPermohonan hap : HakmilikAsal) {
                                    String idHakmilikAsal = hap.getHakmilikSejarah();
                                    if (!idHakmilik.equals(idHakmilikAsal)) {
                                        hakmilik.setTarikhDaftar(new java.util.Date());

                                    }
                                }
                            }
                        }

                        if (permohonan.getKodUrusan().getKod().equals("HSBM")
                                || permohonan.getKodUrusan().getKod().equals("HKBM")) {
                            hakmilik.setTarikhDaftar(new java.util.Date());

                            if (hakmilik.getPegangan() != null) {
                                if (hakmilik.getPegangan() == 'P') {
                                    Calendar calendar = new GregorianCalendar();
                                    calendar.setTime(hakmilik.getTarikhDaftar());
                                    /*CALC BASED ON DAY OF YEAR*/
                                    //calendar.add(Calendar.DAY_OF_YEAR, tempohPegangan * 365);
                                    if (hakmilik.getKodHakmilik().getKod().equals("PN") || hakmilik.getKodHakmilik().getKod().equals("PM") || hakmilik.getKodHakmilik().getKod().equals("HSM")) {
                                        calendar.add(Calendar.YEAR, hakmilik.getTempohPegangan() * 1);
                                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                                        Date tarikhLuput = calendar.getTime();
                                        logger.debug("tarikhLuput : " + tarikhLuput);
// ni buang sebab pendaftar bising sebab tarikh pajakan walaupun sblom nie diorng request.kalau mintak lg aku sepak diorng
// tarikh comment out balik 22/1/2015 dari alor gajah
//                                        hakmilik.setTarikhLuput(tarikhLuput);

                                        //khas utk jasin je ni.jgn sepak aku
                                        if (permohonan.getCawangan().getKod().equals("02")) {
                                            hakmilik.setTarikhLuput(tarikhLuput);
                                        }

                                    }
                                } else {
                                    hakmilik.setTempohPegangan(0);
                                    hakmilik.setTarikhLuput(null);
                                }
                            }
                        }

                        if (permohonan.getKodUrusan().getKod().equals("HKABS")) {
                            hakmilik.setTarikhDaftar(new java.util.Date());
                            HakmilikSblm = regService.findIdHakmilikSebelumMohonByIdHakmilik(idHakmilik);
                            if (HakmilikSblm.size() > 0) {
                                for (HakmilikSebelumPermohonan hsp : HakmilikSblm) {
                                    Hakmilik hakmilik2 = hakmilikDAO.findById(hsp.getHakmilikSejarah());
                                    if (hakmilik.getPegangan() != null) {
                                        if (hakmilik.getPegangan() == 'P') {
                                            Calendar calendar = new GregorianCalendar();
                                            calendar.setTime(hakmilik.getTarikhDaftar());
                                            /*CALC BASED ON DAY OF YEAR*/
                                            //calendar.add(Calendar.DAY_OF_YEAR, tempohPegangan * 365);
                                            if (hakmilik.getKodHakmilik().getKod().equals("PN") || hakmilik.getKodHakmilik().getKod().equals("PM") || hakmilik.getKodHakmilik().getKod().equals("HSM")) {
                                                calendar.add(Calendar.YEAR, hakmilik.getTempohPegangan() * 1);
                                                calendar.add(Calendar.DAY_OF_MONTH, -1);
                                                Date tarikhLuput = calendar.getTime();
                                                logger.debug("tarikhLuput.. : " + tarikhLuput);

                                                if (hakmilik2 != null) {
                                                    hakmilik.setTarikhLuput(hakmilik2.getTarikhLuput());
                                                }
                                            }
                                        } else {
                                            hakmilik.setTempohPegangan(0);
                                            hakmilik.setTarikhLuput(null);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
                // new req : dhke , dhde generate on ptreg for HSTHK, HKTHK, HMSC
                // remove BMSTM from urusanConvert
                String[] urusanConvert = {"HSTHK", "HKTHK", "HMSC"};

                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};

                if (!ArrayUtils.contains(urusanConvert, ku.getKod())) {
                    /* GENERATE DHDE AND DHKE */

                    gen2 = "regBorangHMDHKE.rdf"; //DHKE report name
                    gen3 = "regBorangHMDHDE.rdf"; //DHDE report name

                    // generate DHKE hakmilik baru
                    kd2.setKod("DHKE");
                    e = saveOrUpdateDokumen(fd, kd2, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                    path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                    Future<byte[]> dhke = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
                    File sign = new File(dokumenPath + path2 + ".sig");
                    if (sign.exists()) {
                        sign.delete();
                    }

                    //generat DHDE hakmilik baru
                    kd3.setKod("DHDE");
                    f = saveOrUpdateDokumen(fd, kd3, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                    path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                    Future<byte[]> dhde = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
                    sign = new File(dokumenPath + path3 + ".sig");
                    if (sign.exists()) {
                        sign.delete();
                    }

                    logger.debug("Waiting for reports to complete...");
                    try {
                        dhke.get();
                    } catch (Exception ex) {
                        logger.debug(ex.getMessage(), ex);
                    }
                    try {
                        dhde.get();
                    } catch (Exception ex) {
                        logger.debug(ex.getMessage(), ex);
                    }
                    updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                    updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen());

                    //save to mohon hakmilikLogic.
                    hakmilikPermohonan.setDokumen2(e);
                    hakmilik.setDhke(e);
                    hakmilikPermohonan.setDokumen3(f);
                    hakmilik.setDhde(f);
                }
//untuk update tarikh jana geran
                if (ArrayUtils.contains(urusanConvert, ku.getKod())) {

                    List<HakmilikPermohonan> hmk = permohonan.getSenaraiHakmilik();
                    for (HakmilikPermohonan hp : hmk) {
                        Hakmilik hm = hp.getHakmilik();

                        if (hm == null) {
                            continue;
                        }

                        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                        values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
                        gen2 = "regBorangHMDHKE.rdf"; //DHKE report name
                        gen3 = "regBorangHMDHDE.rdf"; //DHDE report name

                        // generate DHKE hakmilik baru
                        kd2.setKod("DHKE");
                        e = saveOrUpdateDokumen(fd, kd2, hm.getIdHakmilik(), context);
                        path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                        Future<byte[]> dhke = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
                        File sign = new File(dokumenPath + path2 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        //generat DHDE hakmilik baru
                        kd3.setKod("DHDE");
                        f = saveOrUpdateDokumen(fd, kd3, hm.getIdHakmilik(), context);
                        path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                        Future<byte[]> dhde = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
                        sign = new File(dokumenPath + path3 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        logger.debug("Waiting for reports to complete...");
                        try {
                            dhke.get();
                        } catch (Exception ex) {
                            logger.debug(ex.getMessage(), ex);
                        }
                        try {
                            dhde.get();
                        } catch (Exception ex) {
                            logger.debug(ex.getMessage(), ex);
                        }
                        updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                        updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen());

                        //save to mohon hakmilikLogic.
                        hp.setDokumen2(e);
                        hm.setDhke(e);
                        hp.setDokumen3(f);
                        hm.setDhde(f);

                        hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
                        hakmilikDAO.save(hm);
                    }
                } else {
                    //generate notis 5F hakmilk baru
                    logger.info("::gen notis 5f :: ");
                    kd4.setKod("5F");
                    gen4 = "REG_Borang5F001.rdf";
                    g = saveOrUpdateDokumen(fd, kd4, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                    path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                    logger.info("::Path To save :: " + path4);
                    logger.info("::Report Name ::" + gen4);
                    reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
                    updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
                    hakmilikPermohonan.setDokumen4(g);
                    List<HakmilikPermohonan> hmlist = permohonan.getSenaraiHakmilik();
                    List<HakmilikPihakBerkepentingan> listTuanTanah;

                    for (HakmilikPermohonan hP : hmlist) {
                        listTuanTanah = hP.getHakmilik().getSenaraiPihakBerkepentingan();
                        notis = regService.findnotis5f(permohonan.getIdPermohonan());
                        if (notis == null) {
                            Notis newNotis = new Notis();
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());
                            KodNotis kod = new KodNotis();
                            kod.setKod("5F");
                            KodJabatan kj = new KodJabatan();
                            kj = kodJabatanDAO.findById("16");
//                                    notis.setTempohBulan(3);
                            newNotis.setDokumenNotis(g);
                            newNotis.setJabatan(kj);
                            newNotis.setPermohonan(permohonan);
                            newNotis.setKodNotis(kod);
                            newNotis.setHakmilikPermohonan(hP);
                            /* Save ID WARTA */
                            PermohonanRujukanLuar idWarta = new PermohonanRujukanLuar();
                            InfoAudit ia = new InfoAudit();
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            idWarta.setHakmilik(hakmilik);
                            idWarta.setPermohonan(permohonan);
                            idWarta.setCawangan(permohonan.getCawangan());

                            idWarta.setKodRujukan(kodRujukanDAO.findById("FL"));
                            // copy no fail from hakmilik to mohon_ruj_luar. will be use in 5F rdf report 
                            idWarta.setNoFail(hP.getHakmilik().getNoFail());
                            idWarta.setInfoAudit(ia);
                            permohonanRujukanLuarService.saveOrUpdate(idWarta);
                            logger.info("------------ idWarta : " + idWarta);
                            newNotis.setWarta(idWarta); // save id mohon_ruj_luar as id_warta in table notis 
                            newNotis.setInfoAudit(info);
                            regService.saveOrUpdateNotis(newNotis);
                        } else {
                            logger.info("NOTIS is nut nul");
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());
                            KodNotis kod = new KodNotis();
                            kod.setKod("5F");
                            KodJabatan kj = new KodJabatan();
                            kj = kodJabatanDAO.findById("16");
                            notis.setDokumenNotis(g);
                            notis.setJabatan(kj);
                            notis.setPermohonan(permohonan);
                            notis.setKodNotis(kod);
                            notis.setHakmilikPermohonan(hP);
                            /* Save ID WARTA */
                            PermohonanRujukanLuar idWarta = new PermohonanRujukanLuar();
                            if (notis.getWarta() != null) {
                                logger.info("warta is nut nul");
                                idWarta = permohonanRujukanLuarDAO.findById(notis.getWarta().getIdRujukan());
                            }
                            InfoAudit ia = new InfoAudit();
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            idWarta.setHakmilik(hakmilik);
                            idWarta.setPermohonan(permohonan);
                            idWarta.setCawangan(permohonan.getCawangan());

                            idWarta.setKodRujukan(kodRujukanDAO.findById("FL"));
                            // copy no fail from hakmilik to mohon_ruj_luar. will be use in 5F rdf report 
                            idWarta.setNoFail(hP.getHakmilik().getNoFail());
                            idWarta.setInfoAudit(ia);
                            permohonanRujukanLuarService.saveOrUpdate(idWarta);
                            logger.info(" id warta" + idWarta);
                            notis.setWarta(idWarta); // save id mohon_ruj_luar as id_warta in table notis 
                            notis.setInfoAudit(info);
                            regService.saveOrUpdateNotis(notis);
                        }
                    }
                }

                /* HAKMILIK ASAL / HAKMILIK SEBELUM  */
                if (!permohonan.getKodUrusan().getKod().equals("HSBM")
                        && !permohonan.getKodUrusan().getKod().equals("HKBM")
                        && !permohonan.getKodUrusan().getKod().equals("HSTHK")
                        && !permohonan.getKodUrusan().getKod().equals("HKTHK")) {
                    List<HakmilikAsalPermohonan> listHakmilikAsalPermohonan = hakmilikPermohonan.getSenaraiHakmilikAsal();
                    List<HakmilikSebelumPermohonan> listHakmilikSebelumPermohonan = hakmilikPermohonan.getSenaraiHakmilikSebelum();
                    logger.info("+ list Mohon_Hakmilik_Asal size : " + listHakmilikAsalPermohonan.size());
                    logger.info("+ list Mohon_Hakmilik_Sblm size : " + listHakmilikSebelumPermohonan.size());

                    /* FIND HAKMLIK ASAL */
                    for (int k = 0; k < listHakmilikAsalPermohonan.size(); k++) {

                        Hakmilik sejHakmilik = hakmilikDAO.findById(listHakmilikAsalPermohonan.get(k).getHakmilikSejarah());
                        if (sejHakmilik == null || (sejHakmilik.getKodStatusHakmilik() != null
                                && sejHakmilik.getKodStatusHakmilik().getKod().equals("B"))) {
                            // ignore "BATAL" : if "batal", assumed that data is inharited from "hakmilik lama"
                            continue;
                        }

                        kd5.setKod("DHDE");
                        params2 = new String[]{"p_id_mohon", "p_id_hakmilik"};
                        values2 = new String[]{idPermohonan.trim(), listHakmilikAsalPermohonan.get(k).getHakmilikSejarah()};
                        gen5 = "regBorangHMDHDE.rdf";
                        g = saveOrUpdateDokumen(fd, kd5, listHakmilikAsalPermohonan.get(k).getHakmilikSejarah(), context);
                        path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                        logger.info("::Path To save :: " + path5);
                        logger.info("::Report Name ::" + gen5);
                        reportUtil.generateReport(gen5, params2, values2, dokumenPath + path5, peng);
                        updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
                        logger.info("------------ g.idDokumen() : " + g.getIdDokumen());
                        Hakmilik hakmiliklama = hakmilikDAO.findById(listHakmilikAsalPermohonan.get(k).getHakmilikSejarah());
                        if (hakmiliklama != null) {
                            if (hakmiliklama.getNoVersiDhde() != null) { // update dhke version for "hakmilik batal"              
                            } else {
                                hakmiliklama.setNoVersiDhde(1);
                            }
                            hakmiliklama.setDhde(g);        // update dhke dokument id for "hakmilik batal"

                            InfoAudit ia = new InfoAudit();
                            ia.setTarikhKemaskini(new java.util.Date());
                            ia.setDiKemaskiniOleh(peng);
                            hakmiliklama.setInfoAudit(ia);
                            hakmilikDAO.save(hakmiliklama);
                        }

//            }
                    }

                    /* FIND HAKMLIK SEBELUM */
                    for (int m = 0; m < listHakmilikSebelumPermohonan.size(); m++) {

                        Hakmilik hm = hakmilikDAO.findById(listHakmilikSebelumPermohonan.get(m).getHakmilikSejarah());

                        if (hm == null || hm.getKodStatusHakmilik().getKod().equals("B")) {
                            // ignore "BATAL" : if "batal", assumed that data is inharited from "hakmilik lama"
                            continue;
                        }

                        kd5.setKod("DHDE");
                        params2 = new String[]{"p_id_mohon", "p_id_hakmilik"};
                        values2 = new String[]{idPermohonan.trim(), listHakmilikSebelumPermohonan.get(m).getHakmilikSejarah()};
                        gen5 = "regBorangHMDHDE.rdf";
                        g = saveOrUpdateDokumen(fd, kd5, listHakmilikSebelumPermohonan.get(m).getHakmilikSejarah(), context);
                        path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                        logger.info("::Path To save :: " + path5);
                        logger.info("::Report Name5 ::" + gen5);

                        reportUtil.generateReport(gen5, params2, values2, dokumenPath + path5, peng);
                        updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
                        Hakmilik hakmiliklama = hakmilikDAO.findById(listHakmilikSebelumPermohonan.get(m).getHakmilikSejarah());
                        if (hakmiliklama != null) {
                            hakmiliklama.setDhde(g);        // update dhke dokument id for "hakmilik batal"
                            if (hakmiliklama.getNoVersiDhde() != null) { // update dhke version for "hakmilik batal"
                                hakmiliklama.setNoVersiDhde(hakmiliklama.getNoVersiDhde() + 1);
                            } else {
                                hakmiliklama.setNoVersiDhde(1);
                            }

                            if (hakmiliklama.getNoVersiDhke() != null) {
                                hakmiliklama.setNoVersiDhke(hakmiliklama.getNoVersiDhke() + 1);
                            } else {
                                hakmiliklama.setNoVersiDhke(1);
                            }
                            InfoAudit ia = new InfoAudit();
                            ia.setTarikhKemaskini(new java.util.Date());
                            ia.setDiKemaskiniOleh(peng);
                            hakmiliklama.setInfoAudit(ia);
                            hakmilikDAO.save(hakmiliklama);
                        }
                    }
                }

                if (!p.getKodUrusan().getKod().equals("HKTHK")
                        && !p.getKodUrusan().getKod().equals("HSTHK")) {
                    logger.debug("saving dokumen : " + g.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikDAO.save(hakmilik);
                }
            }
        }
    }

    public void updateHakmilikLama(StageContext context) {
        // add by azri 11/11/2013 : to update table hakmilik_lama
        Pengguna pengguna = context.getPengguna();
        Permohonan p = context.getPermohonan();
        InfoAudit ia = new InfoAudit();
        List<HakmilikPermohonan> hk = p.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : hk) {
            HakmilikLama hl = regService.searchHakmiliklama(hp.getHakmilik().getIdHakmilik());
            if (hl != null) {
                // dhkk and dhdk need to set as 1
                hl.setVersiDhdk(String.valueOf(1));
                hl.setVersiDhkk(String.valueOf(1));

                hl.setPegawaiJilid14(pengguna.getNama());
                hl.setPenggunaJilid14(pengguna);
                hl.setTarikhJilid14(new java.util.Date());
                hl.setPenggunaJilid16(pengguna);
                hl.setPegawaiJilid16(pengguna.getNama());
                hl.setTarikhJilid16(new java.util.Date());
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                hl.setInfoAudit(ia);
                regService.simpanHakmilikLama(hl);
            } else {
                HakmilikLama hml = new HakmilikLama();
                hml.setVersiDhdk(String.valueOf(1));
                hml.setVersiDhkk(String.valueOf(1));
                hml.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                hml.setPenggunaJilid14(pengguna);
                hml.setPegawaiJilid14(pengguna.getNama());
                hml.setTarikhJilid14(new java.util.Date());
                hml.setPenggunaJilid16(pengguna);
                hml.setPegawaiJilid16(pengguna.getNama());
                hml.setTarikhJilid16(new java.util.Date());
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                hml.setInfoAudit(ia);
                regService.simpanHakmilikLama(hml);
            }
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, StageContext context) {
        logger.info(" /* SAVE OR UPDATE DOKUMEN */ ");
        Pengguna pengguna = context.getPengguna();
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
        KodKlasifikasi klasifikasi_SULIT = kodKlasifikasiDAO.findById(3);
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

    private void notifikasi(StageContext context) {
        logger.info(" /* NOTIFIKASI */ ");
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Kemasukan HKSTA Pendaftaran Selesai");
        n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("PPT"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        context.addMessage("Makluman kepada Pentadbir Tanah telah dihantar.");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        logger.info(" /* UPDATE PATH DOKUMEN */ ");
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private void movePihak(Permohonan permohonan, Pengguna pengguna, InfoAudit info) {
        logger.info(" /* MOVE PIHAK */ ");
        HK = new Hakmilik();
        //TODO: move mohon pihak into hakmilik pihak
        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        Fraction f = Fraction.ZERO;

        //to insert DHDE into hakmilik
        Dokumen dhde = null;
        boolean isDhde = false;
        boolean isDhke = false;
        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> senaraiKF = fd.getSenaraiKandungan();
        for (KandunganFolder kandunganFolder : senaraiKF) {
            if (kandunganFolder == null || kandunganFolder.getDokumen() == null) {
                continue;
            }
            if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("DHDE")) {
                dhde = kandunganFolder.getDokumen();
                isDhde = true;
            }
            if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("DHKE")) {
                isDhke = true;
            }
        }

        for (int k = 0; k < li.size(); k++) {
            HK = li.get(k).getHakmilik();
            logger.info("--> id hakmilik : " + HK.getIdHakmilik());
            //set status Daftar to Hakmilik
            List<HakmilikUrusan> lhu = hakmilikService.findListByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), HK.getIdHakmilik());
            HakmilikUrusan hu = lhu.get(0);
            if (hu == null) {
                continue;
            }
            KodStatusHakmilik ksh = new KodStatusHakmilik();
            if (permohonan.getKodUrusan().getKod().equals("HKP") || permohonan.getKodUrusan().getKod().equals("HSP")
                    || permohonan.getKodUrusan().getKod().equals("HKTKP") || permohonan.getKodUrusan().getKod().equals("HSTKP")) {
                ksh.setKod("P");
            } else {
                ksh.setKod("D");
            }

            HK.setKodStatusHakmilik(ksh);

            //copy cukai into cukai sebenar
            HK.setCukaiSebenar(HK.getCukai());
            regService.simpanHakmilik(HK);
            logger.info("Hakmilik " + HK.getIdHakmilik() + " untuk permohonan " + permohonan.getIdPermohonan() + " telah didaftarkan oleh " + pengguna.getIdPengguna());
            String idHakmilik = li.get(k).getHakmilik().getIdHakmilik();
            List<HakmilikPihakBerkepentingan> list = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(HK);
            logger.debug("Moving idHakmilik :" + idHakmilik);
            logger.debug("list :" + list.size());

            /* UPDATE HAKMILIK_PIHAK */
            List<HakmilikPihakBerkepentingan> list_tmp = new ArrayList<HakmilikPihakBerkepentingan>();
            for (HakmilikPihakBerkepentingan p : list) {
                if (p.getHakmilik().getIdHakmilik().equals(HK.getIdHakmilik())) {
                    continue;
                }
                Pihak pihak = p.getPihak();
                HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
                hpk.setJenis(p.getJenis());
                hpk.setPerserahan(hu);
                hpk.setHakmilik(HK);
                hpk.setPihak(pihak);
                hpk.setAktif('Y');
                hpk.setPihakCawangan(p.getPihakCawangan());
                hpk.setKaveatAktif('T');
                hpk.setSyerPembilang(p.getSyerPembilang());
                hpk.setSyerPenyebut(p.getSyerPenyebut());
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                hpk.setInfoAudit(info);
                //}
                list_tmp.add(hpk);
            }
            hakmilikPihakKepentinganService.update(list_tmp, HK, permohonan, info);
        }
        if (isDhde && dhde != null) {
            HK.setNoVersiDhde(HK.getNoVersiDhde() != null ? HK.getNoVersiDhde() + 1 : 1);
        }
        if (isDhke) {
            HK.setNoVersiDhke(HK.getNoVersiDhke() != null ? HK.getNoVersiDhke() + 1 : 1);
        }
        hakmilikDAO.saveOrUpdate(HK);
    }

    private void moveMHANMHS(Permohonan permohonan, InfoAudit info) {
        logger.info(" /* SAVE HAMILIK_ASAL and HAKMILIK_SBLM */ ");
        logger.info("::Start Moving mohon Hakmilik Asal and mohon Hakmilik Sebelum::");
        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        KodStatusHakmilik ksh = new KodStatusHakmilik();
        logger.debug("senarai hakmilik size :" + li.size());
        if (li.size() > 0) {
            for (int m = 0; m < li.size(); m++) {
                Hakmilik hk = li.get(m).getHakmilik(); // get hakmilik
                HakmilikPermohonan hp = li.get(m); // get mohon_hakmilik
                Long idMh = li.get(m).getId();
                logger.debug("idMh :" + idMh);
                List<HakmilikAsalPermohonan> lha = regService.searchMohonHakmilikAsalByID(idMh);
                List<HakmilikSebelumPermohonan> lhs = regService.searchMohonHakmilikSblmByID(idMh);
                logger.info("lha size :" + lha.size());
                logger.info("lhs size :" + lhs.size());
                if (lhs.size() > 0) {
                    for (HakmilikSebelumPermohonan mhs : lhs) {
//                            Hakmilik hakmilikSblm = hakmilikDAO.findById(String.valueOf(mhs.getHakmilik()));
                        List<Hakmilik> senaraiHakmilikSBlm = regService.searchHakmilikByidHakmilik(mhs.getHakmilikSejarah());
                        if (senaraiHakmilikSBlm.size() > 0) {
                            for (Hakmilik hakmilikSblm : senaraiHakmilikSBlm) {
                                if (hakmilikSblm != null) {
                                    logger.info("--> hakmiliklama.getIdHakmilik() 1 : " + hakmilikSblm.getIdHakmilik());
                                    if (hakmilikSblm.getTarikhBatal() == null) {
                                        hakmilikSblm.setTarikhBatal(new java.util.Date());
                                    }
                                    ksh.setKod("B"); // hakmilik lama batal
                                    hakmilikSblm.setKodStatusHakmilik(ksh);
                                    regService.simpanHakmilik(hakmilikSblm);
                                }
                            }
                        }
                    }
                }
                if (lha.size() > 0) {
                    for (HakmilikAsalPermohonan mha : lha) {
//                        Hakmilik hakmilikAsal = hakmilikDAO.findById(String.valueOf(mha.getHakmilik()));
                        List<Hakmilik> senaraiHakmilikAsal = regService.searchHakmilikByidHakmilik(mha.getHakmilikSejarah());
                        if (senaraiHakmilikAsal.size() > 0) {
                            for (Hakmilik hakmilikAsal : senaraiHakmilikAsal) {
                                if (hakmilikAsal != null) {
                                    logger.info("--> hakmiliklama.getIdHakmilik() 1 : " + hakmilikAsal.getIdHakmilik());
                                    if (hakmilikAsal.getTarikhBatal() == null) {
                                        hakmilikAsal.setTarikhBatal(new java.util.Date());
                                    }
                                    ksh.setKod("B"); // hakmilik lama batal
                                    hakmilikAsal.setKodStatusHakmilik(ksh);
                                    regService.simpanHakmilik(hakmilikAsal);
                                }
                            }
                        }
                    }
                }
                /* UPDATE HAKMILIK ASAL */
                if (!lha.isEmpty()) {
                    logger.info(" /* UPDATE HAKMILIK ASAL */ ");

                    for (int i = 0; i < lha.size(); i++) {
                        HakmilikAsal ha = new HakmilikAsal();
                        Hakmilik hakmiliklama = new Hakmilik();
                        if (!permohonan.getKodUrusan().getKod().equals("HSSTB")) {
                            String idHakmilik = lha.get(i).getHakmilikSejarah();  //id hakmiilik lama is in column sej_hakmilik
                            logger.info("--> id hakmilik 1 : " + idHakmilik);
                            ha.setHakmilik(hk);
                            if (lha.get(i).getHakmilikSejarah() != null) {
                                ha.setHakmilikAsal(lha.get(i).getHakmilikSejarah());
                            }
                            ha.setIdSebelum(lha.get(i).getIdHakmilikAsalPermohonan());
                            ha.setInfoAudit(info);
                            logger.debug("ha.hakmilik : " + ha.getHakmilik().getIdHakmilik());
                            logger.debug("ha.hakmilikasal : " + ha.getHakmilikAsal());
                            logger.debug("Saving Hakmilik Asal");
                            regService.simpanHakmilikAsal(ha);
                        }
                        //batal hakmili lama

                    }
                }

                /* UPDATE HAKMILIK SEBELUM */
                if (!lhs.isEmpty()) {
                    logger.info(" /* UPDATE HAKMILIK ASAL */ ");
                    for (int j = 0; j < lhs.size(); j++) {

                        HakmilikSebelum hs = new HakmilikSebelum();
                        String idHakmilik = lhs.get(j).getHakmilikSejarah(); //id hakmiilik lama is in column sej_hakmilik
                        if (lhs.size() > 0) {
                            List<Hakmilik> listHakmilikAsal = regService.searchHakmilikByidHakmilik(idHakmilik);
                            if (listHakmilikAsal.size() > 0) {
                                for (Hakmilik hakmilikb : listHakmilikAsal) {
                                    if (hakmilikb != null) {
                                        if (hakmilikb.getTarikhBatal() == null) {
                                            hakmilikb.setTarikhBatal(new java.util.Date());
                                        }
                                        ksh.setKod("B"); // hakmilik lama batal
                                        hakmilikb.setKodStatusHakmilik(ksh);
                                        if (permohonan.getKodUrusan().getKod().equals("HKABS") && permohonan.getKodUrusan().getKod().equals("HKABT")
                                                && permohonan.getKodUrusan().getKod().equals("HSSTA") && permohonan.getKodUrusan().getKod().equals("HKSTA")) {
                                            /*BATALKAN HAKMILIK LAMA DI TABLE HAKMILIK*/
                                            hakmilikb = lhs.get(j).getHakmilik();
                                            hakmilikb.setLuas(hp.getLuasTerlibat());
                                            hakmilikb.setCukai(hp.getCukaiBaru());
                                        }
                                        regService.simpanHakmilik(hakmilikb);
                                    }
                                }
                            }
                        }
//                        Hakmilik hakmilikb = hakmilikDAO.findById(idHakmilik);

                        hs.setHakmilik(hk);
                        if (lhs.get(j).getHakmilikSejarah() != null) {
                            hs.setHakmilikSebelum(lhs.get(j).getHakmilikSejarah());
                        }
                        hs.setIdSebelum(lhs.get(j).getIdHakmilikSebelumPermohonan());
                        hs.setInfoAudit(info);
//                            listTemp2.add(hs);
                        logger.debug("Saving Hakmilik Sebelum");
                        regService.simpanHakmilikSebelum(hs);

                    }
                }

            }
        }
    }

    private void insertUrusan(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
        logger.info(" /* INSERT URUSAN */ ");
        logger.info("insertUrusan :: start :: result :: " + proposedOutcome);
        if (proposedOutcome.equals("D")) {
            String idPermohonan = permohonan.getIdPermohonan();
            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                Hakmilik hm = hakmilikPermohonan.getHakmilik();
                if (hm == null) {
                    continue;
                }
                HakmilikUrusan hakmilikUrusan = hakmilikService.findByIdPerserahanIdHakmilik(idPermohonan, hm.getIdHakmilik());
                if (hakmilikUrusan == null) {
                    hakmilikUrusan = new HakmilikUrusan();
                }
                hakmilikUrusan.setInfoAudit(info);
//            hakmilikUrusan.setPermohonan(permohonan);
                hakmilikUrusan.setDaerah(hakmilikPermohonan.getHakmilik().getDaerah());
                hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
                hakmilikUrusan.setCawangan(permohonan.getCawangan());
                hakmilikUrusan.setHakmilik(hakmilikPermohonan.getHakmilik());
                hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                hakmilikUrusan.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
                hakmilikUrusan.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());
                hakmilikUrusan.setAktif('Y');
                hakmilikUrusanList.add(hakmilikUrusan);
            }
            hakmilikService.saveOrUpdate(hakmilikUrusanList); // save urusan in table hakmilik_urusan
        }
        logger.info("insertUrusan :: finish");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

//    private void checkPermohonanLama(Permohonan permohonan, Pengguna pengguna, InfoAudit info) throws Exception {
//        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
//        for (HakmilikPermohonan hakmilikPermohonan : li) {
//            List<HakmilikAsalPermohonan> liha = hakmilikPermohonan.getSenaraiHakmilikAsal();
//            logger.debug("list hakmilik asal size :" + liha.size());
//
//            if (liha.size() > 0) {
//                for (int m = 0; m < liha.size(); m++) {
//                    Hakmilik hk = liha.get(0).getHakmilik();
//                    idHakmilik = liha.get(0).getHakmilikSejarah();
//                    String kodNegeri = conf.getProperty("kodNegeri");
//
//                    List<HakmilikAsalPermohonan> senaraiHakmilikPermohonanAsal = regService.searchMohonHakmilikAsalByIDHakmilik(idHakmilik);
//                    List<HakmilikSebelumPermohonan> senaraiHakmilikPermohonanSblm = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);
//
//                    for (HakmilikAsalPermohonan mha : senaraiHakmilikPermohonanAsal) {
//                        idHakmilikAsal = mha.getHakmilikSejarah();
//                    }
//                    for (HakmilikSebelumPermohonan mhs : senaraiHakmilikPermohonanSblm) {
//                        idHakmilikSblm = mhs.getHakmilikSejarah();
//                    }
//                    if (idHakmilikSblm != null) {
//                        List<HakmilikPermohonan> senaraiHakmilikPermohonan = hakmilikPermohonanService.findByIdHakmilik(idHakmilikSblm);
//                        if (senaraiHakmilikPermohonan.size() > 0) {
//                            for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
//                                Permohonan mohon = permohonanDAO.findById(String.valueOf(hp.getPermohonan()));
//                                context.addMessage("Pendaftaran Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
//                            }
//                        }
//                    } else if (idHakmilikAsal != null) {
//
//                    }
//
//                }
//            }
//        }
//    }
    private void saveAkaun(Permohonan permohonan, Pengguna pengguna, InfoAudit info) {
        logger.info(":::start set Akaun for Hakmilik:::");
        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hakmilikPermohonan : li) {
            String idHakmilikBaru = hakmilikPermohonan.getHakmilik().getIdHakmilik();
            List<HakmilikPihakBerkepentingan> hp = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(hakmilikPermohonan.getHakmilik());
            List<HakmilikAsalPermohonan> senaraiHakmilikPermohonanAsal = regService.searchMohonHakmilikAsalByIDHakmilik(idHakmilikBaru);
            List<HakmilikSebelumPermohonan> senaraiHakmilikPermohonanSblm = regService.searchMohonHakmilikSblmByIDHakmilikNew(idHakmilikBaru);
            Akaun ak = new Akaun();
            List<Akaun> senaraiAkaun = regService.findAkaunByIdHakmilik(idHakmilik);
            if (senaraiAkaun.size() > 0) {
                ak = senaraiAkaun.get(0);
            }

            ak.setStatus(kodStatusAkaunDAO.findById("A"));
            if (senaraiHakmilikPermohonanSblm.size() > 0) {
                for (HakmilikSebelumPermohonan mhb : senaraiHakmilikPermohonanSblm) {
                    idHakmilikSblm = mhb.getHakmilikSejarah();
                    akaunList = regService.findAkaunByIdHakmilik(idHakmilikSblm);
                    Hakmilik hk = hakmilikService_.findHakmilik(idHakmilikSblm);

                    for (Akaun akaun1 : akaunList) {
                        if (!permohonan.getKodUrusan().getKod().equals("HSSTB")) {
                            if (!permohonan.getKodUrusan().getKod().equals("HKTHK")
                                    && !permohonan.getKodUrusan().getKod().equals("HSTHK")
                                    //                                    && !permohonan.getKodUrusan().getKod().equals("HSSB")
                                    && !permohonan.getKodUrusan().getKod().equals("HSSTB")
                                    && !permohonan.getKodUrusan().getKod().equals("HKBM")
                                    && !permohonan.getKodUrusan().getKod().equals("HSBM")
                                    && !permohonan.getKodUrusan().getKod().equals("HTIR")) {
                                ak.setNoAkaun(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                BigDecimal zero = new BigDecimal(0);
                                KodAkaun ka = new KodAkaun();
                                ka.setKod("AC");
                                ak.setKodAkaun(ka);
                                ak.setHakmilik(hakmilikPermohonan.getHakmilik());
                                ak.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
                                ak.setBaki(zero);
                                ak.setPemegang(akaun1.getPemegang());
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                ak.setInfoAudit(info);
                                List<PermohonanPihak> list = permohonanPihakService.getSenaraiPmohonPihakByHakmilik(permohonan.getIdPermohonan(), idHakmilikSblm);
//                                ak.setStatus(kodStatusAkaunDAO.findById("A"));
                                if (!permohonan.getKodUrusan().getKod().equals("HSSBB")
                                        && !permohonan.getKodUrusan().getKod().equals("HSSTB")
                                        && !permohonan.getKodUrusan().getKod().equals("HKSB")
                                        //                                        && !permohonan.getKodUrusan().getKod().equals("HSSB")
                                        && !permohonan.getKodUrusan().getKod().equals("HKBM")
                                        && !permohonan.getKodUrusan().getKod().equals("HSBM")
                                        && !permohonan.getKodUrusan().getKod().equals("HKSBB")
                                        && !permohonan.getKodUrusan().getKod().equals("HKSTB")) {
                                    ak.setPemegang(akaun1.getPemegang());
                                    ak.setAmaunMatang(akaun1.getAmaunMatang());
                                    ak.setBilCetakPenyata(akaun1.getBilCetakPenyata());
//                                    ak.setStatus(akaun1.getStatus());
                                }
                                logger.debug("::::saving akaun from hakmilik asal::::");
                                regService.saveOrUpdate(ak);

                                akaun1.setStatus(kodStatusAkaunDAO.findById("B"));
                                akaun1.setInfoAudit(info);
                                regService.saveOrUpdate(ak);

                                if (hk != null) {
                                    List<Akaun> listAkaun = hk.getSenaraiAkaun();
                                    listAkaun.add(ak);
                                    hk.setAkaun(listAkaun);
                                    regService.simpanHakmilik(hk);
                                    logger.info(":::finish set kew trans Hakmilik baru:::");
                                }
                            }
                        }

                    }
                }
            } else if (senaraiHakmilikPermohonanAsal.size() > 0) {
                for (HakmilikAsalPermohonan mha : senaraiHakmilikPermohonanAsal) {
                    idHakmilikAsal = mha.getHakmilikSejarah();
                    akaunList = regService.findAkaunByIdHakmilik(idHakmilikAsal);
                    Hakmilik hk = hakmilikService_.findHakmilik(idHakmilikAsal);
                    for (Akaun akaun1 : akaunList) {
                        if (!permohonan.getKodUrusan().getKod().equals("HSSTB")) {
                            if (!permohonan.getKodUrusan().getKod().equals("HKTHK")
                                    || !permohonan.getKodUrusan().getKod().equals("HSTHK")
                                    || !permohonan.getKodUrusan().getKod().equals("HSSTB")
                                    || !permohonan.getKodUrusan().getKod().equals("HKBM")
                                    || !permohonan.getKodUrusan().getKod().equals("HSBM")
                                    || !permohonan.getKodUrusan().getKod().equals("HTIR")) {
                                ak.setNoAkaun(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                BigDecimal zero = new BigDecimal(0);
                                KodAkaun ka = new KodAkaun();
                                ka.setKod("AC");
                                ak.setKodAkaun(ka);
                                ak.setPemegang(akaun1.getPemegang());
                                ak.setHakmilik(hakmilikPermohonan.getHakmilik());
                                ak.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
                                ak.setBaki(zero);
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                ak.setInfoAudit(info);
//                                List<PermohonanPihak> list = permohonanPihakService.getSenaraiPmohonPihakByHakmilik(permohonan.getIdPermohonan(), idHakmilikAsal);
//                                List<PermohonanPihak> list = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(permohonan.getIdPermohonan(), idHakmilikAsal);
//                                ak.setStatus(kodStatusAkaunDAO.findById("A"));
                                if (!permohonan.getKodUrusan().getKod().equals("HSSBB")
                                        || !permohonan.getKodUrusan().getKod().equals("HSSTB")
                                        || !permohonan.getKodUrusan().getKod().equals("HKSB")
                                        || !permohonan.getKodUrusan().getKod().equals("HKBM")
                                        || !permohonan.getKodUrusan().getKod().equals("HSBM")
                                        || !permohonan.getKodUrusan().getKod().equals("HKSBB")
                                        || !permohonan.getKodUrusan().getKod().equals("HKSTB")) {
                                    ak.setPemegang(akaun1.getPemegang());
                                    ak.setAmaunMatang(akaun1.getAmaunMatang());
                                    ak.setBilCetakPenyata(akaun1.getBilCetakPenyata());
//                                    ak.setStatus(akaun1.getStatus());
                                }
                                logger.debug("::::saving akaun from hakmilik asal::::");
                                regService.saveOrUpdate(ak);

                                if (hk != null) {
                                    List<Akaun> listAkaun = hk.getSenaraiAkaun();
                                    listAkaun.add(ak);
                                    hk.setAkaun(listAkaun);
                                    regService.simpanHakmilik(hk);
                                    logger.info(":::finish set kew trans Hakmilik baru:::");
                                }
                                akaun1.setStatus(kodStatusAkaunDAO.findById("B"));
                                akaun1.setInfoAudit(info);
                                regService.saveOrUpdate(akaun1);
                                regService.saveOrUpdate(ak);
                            }
                        }

                    }
                }
            } else {
                Hakmilik hk = hakmilikPermohonan.getHakmilik();
                ak.setNoAkaun(hk.getIdHakmilik());
                BigDecimal zero = new BigDecimal(0);
                KodAkaun ka = new KodAkaun();
                ka.setKod("AC");
                ak.setKodAkaun(ka);
                ak.setHakmilik(hk);
                ak.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
                ak.setBaki(zero);
                ak.setPemegang(hp.get(0).getPihak());
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                ak.setInfoAudit(info);
//                List<PermohonanPihak> list = permohonanPihakService.getSenaraiPmohonPihakByHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
//                ak.setStatus(kodStatusAkaunDAO.findById("A"));
                logger.debug("::::saving akaun from hakmilik asal::::");
                regService.saveOrUpdate(ak);
                List<Akaun> listAkaun = hk.getSenaraiAkaun();
                listAkaun.add(ak);
                hk.setAkaun(listAkaun);
                regService.simpanHakmilik(hk);
                logger.info(":::finish set kew trans Hakmilik baru:::");
            }
            if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("HSPB")
                    || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("HSPS")
                    || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("HKABS")) {
                ak.setStatus(kodStatusAkaunDAO.findById("A"));
                regService.saveOrUpdate(ak);
            }
        }
    }

    private void updateVersi2(Permohonan permohonan, InfoAudit info, Pengguna pengguna) {
        List<HakmilikPermohonan> senaraiHP = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan mohonHakmilik : senaraiHP) {
            Hakmilik hakmilik1 = mohonHakmilik.getHakmilik();
            hakmilik1.setNoVersiDhde(1);
            hakmilik1.setNoVersiDhke(1);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            hakmilikDAO.saveOrUpdate(hakmilik1);
        }
        if (permohonan.getKodUrusan().getKod().equals("HKGHS") || permohonan.getKodUrusan().getKod().equals("HKSTK")) {
            for (HakmilikPermohonan mohonHakmilik1 : senaraiHP) {
                Hakmilik hakmilik1 = mohonHakmilik1.getHakmilik();
                List<HakmilikSebelumPermohonan> senaraiHakmilikPermohonanSblm = regService.searchMohonHakmilikSblmByIDHakmilik(hakmilik1.getIdHakmilik());
                for (HakmilikSebelumPermohonan hps : senaraiHakmilikPermohonanSblm) {
                    Hakmilik hakiliksblm = hps.getHakmilik();
                    hakiliksblm.setNoVersiDhde(hakiliksblm.getNoVersiDhde() + 1);
                    hakiliksblm.setNoVersiDhke(hakiliksblm.getNoVersiDhke() + 1);
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    hakmilikDAO.saveOrUpdate(hakiliksblm);
                }
            }
        }
    }

    private void updateAkaunLama(Permohonan permohonan, InfoAudit info, Pengguna pengguna) {
        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hakmilikPermohonan : li) {
            List<HakmilikAsalPermohonan> liha = hakmilikPermohonan.getSenaraiHakmilikAsal();
            logger.debug("list hakmilik asal size :" + liha.size());
            List<HakmilikSebelumPermohonan> senaraiHakmilikPermohonanSblm = regService.searchMohonHakmilikSblmByIDHakmilikNew(hakmilikPermohonan.getHakmilik().getIdHakmilik());
//            if (liha.size() > 0) {
//                for (int m = 0; m < liha.size(); m++) {
//                    Hakmilik hk = liha.get(0).getHakmilik();
//                    idHakmilik = liha.get(0).getHakmilikSejarah();
//                    String kodNegeri = conf.getProperty("kodNegeri");

            List<HakmilikAsalPermohonan> senaraiHakmilikPermohonanAsal = regService.searchMohonHakmilikAsalByIDHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());

            if (senaraiHakmilikPermohonanAsal.size() > 0) {
                for (HakmilikAsalPermohonan mha : senaraiHakmilikPermohonanAsal) {
                    idHakmilikAsal = mha.getHakmilikSejarah();
                    akaunList = regService.findAkaunByIdHakmilik(idHakmilikAsal);
                    if (akaunList.size() > 0) {
                        for (Akaun akaun1 : akaunList) {
                            if (akaun1 != null) {
                                akaun1.setStatus(kodStatusAkaunDAO.findById("B"));
                                akaun1.getInfoAudit().setDiKemaskiniOleh(pengguna);
                                akaun1.getInfoAudit().setTarikhKemaskini(new Date());
                                info = akaun1.getInfoAudit();
                                info.setDiKemaskiniOleh(pengguna);
                                info.setTarikhKemaskini(new Date());
                                akaun1.setInfoAudit(info);
                                regService.saveOrUpdate(akaun1);
                            }
                        }
                    }
                }
            }
            if (senaraiHakmilikPermohonanSblm.size() > 0) {
                for (HakmilikSebelumPermohonan mhs : senaraiHakmilikPermohonanSblm) {
                    idHakmilikSblm = mhs.getHakmilikSejarah();
                    String idHakmilikSblmBaru = mhs.getHakmilik().getIdHakmilik();
                    if (idHakmilikSblm != idHakmilikSblmBaru) {
                        akaunList = regService.findAkaunByIdHakmilik(idHakmilikSblm);
                        if (akaunList.size() > 0) {
                            for (Akaun akaun1 : akaunList) {
                                if (akaun1 != null) {
                                    akaun1.setStatus(kodStatusAkaunDAO.findById("B"));
                                    akaun1.getInfoAudit().setDiKemaskiniOleh(pengguna);
                                    akaun1.getInfoAudit().setTarikhKemaskini(new Date());
                                    info = akaun1.getInfoAudit();
                                    info.setDiKemaskiniOleh(pengguna);
                                    info.setTarikhKemaskini(new Date());
                                    akaun1.setInfoAudit(info);
                                    regService.saveOrUpdate(akaun1);
                                }
                            }
                        }
                    }
                }
            }
//            for (HakmilikPermohonan hp1 : li) {
//                akaunList = regService.findAkaunByIdHakmilik(hp1.getHakmilik().getIdHakmilik());
//                if (akaunList.size() > 0) {
//                    for (Akaun akaun1 : akaunList) {
//                        akaun1.setStatus(kodStatusAkaunDAO.findById("A"));
//                        akaun1.getInfoAudit().setDiKemaskiniOleh(pengguna);
//                        akaun1.getInfoAudit().setTarikhKemaskini(new Date());
//                        info = akaun1.getInfoAudit();
//                        info.setDiKemaskiniOleh(pengguna);
//                        info.setTarikhKemaskini(new Date());
//                        akaun1.setInfoAudit(info);
//                        regService.saveOrUpdate(akaun1);
//                    }
//                }
//            }
        }
    }

    private void updateNoVersi(Permohonan permohonan, InfoAudit info, String proposedOutcome) throws Exception {
        logger.info(" /* UPDATE NO VERSI */ ");
        if (proposedOutcome.equals("D")) {
            List<HakmilikPermohonan> hp = new ArrayList();
            List<Hakmilik> lhk = new ArrayList();
            hp = permohonan.getSenaraiHakmilik();
            if (!permohonan.getKodUrusan().getKod().equals("HKGHS") || !permohonan.getKodUrusan().getKod().equals("HKSTK")) {
                for (HakmilikPermohonan hakmilikPermohonan : hp) {
                    Hakmilik hk = hakmilikPermohonan.getHakmilik();
                    hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
                    hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    hk.setInfoAudit(info);
                    lhk.add(hk);
                }
                regService.simpanHakmilikList(lhk);
            }
            if (permohonan.getKodUrusan().getKod().equals("HKGHS") || permohonan.getKodUrusan().getKod().equals("HKSTK")) {
                for (HakmilikPermohonan hakmilikPermohonan : hp) {
                    List<HakmilikSebelumPermohonan> SenaraiHakmilikSblm = regService.findIdHakmilikSebelumMohonByIdHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    for (HakmilikSebelumPermohonan hakmilikSblm : SenaraiHakmilikSblm) {
                        Hakmilik hk = hakmilikSblm.getHakmilik();
                        hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
                        hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        hk.setInfoAudit(info);
                        lhk.add(hk);
                    }
                }
                regService.simpanHakmilikList(lhk);
            }
        }
    }

    public String generate(KodCawangan caw, Pengguna pguna, Akaun acc) {
        logger.info(" /* GENERATE */ ");
        transaksi = new Transaksi();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        int year = Integer.parseInt(sdf.format(new java.util.Date()));
        transaksi.setCawangan(caw);
        transaksi.setKodTransaksi(kodTransaksiDAO.findById(kod_transaksi));
        transaksi.setAmaun(acc.getBaki());
        transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
        transaksi.setInfoAudit(ia);
        transaksi.setAkaunDebit(acc);
        transaksi.setUntukTahun(year);
        manager.saveTrans(transaksi);

        return idTransaksi;
    }

    private void doJanaHakmilikBaru(Permohonan permohonan, InfoAudit info) {
        logger.info("  /* JANA HAKMILIK BARU FROM HAKMILIK TERLIBAT */  ");
        Hakmilik hakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru = null;

        List<HakmilikPermohonan> hakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
        int totalHakmilik = 0;

        KodCawangan kodCaw = null;

        if (permohonan != null) {

            totalHakmilik = Integer.parseInt(permohonan.getCatatan());

            List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();

            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp != null && hp.getHakmilik() != null
                        && hp.getHakmilik().getIdHakmilik().contains("IR")) {
                    hakmilikPermohonan.add(hp);
                    if (kodCaw == null) {
                        kodCaw = hp.getHakmilik().getCawangan();
                    }
                }
            }
        }

        if (hakmilikPermohonan.size() > 0) {
            if (totalHakmilik > 0) {

                String kodNegeri = conf.getProperty("kodNegeri");

                String idPermohonan = idPerserahanGenerator.generate(
                        kodNegeri, kodCaw, kodUrusanDAO.findById("HTIR"));

                Permohonan p = new Permohonan();
                p.setStatus("TA");
                p.setIdPermohonan(idPermohonan);
                p.setCawangan(kodCaw);
                p.setKodUrusan(kodUrusanDAO.findById("HTIR"));
//        p.setFolderDokumen(fd);

                p.setInfoAudit(info);

                p.setPermohonanSebelum(permohonan);
                permohonanDAO.save(p);

                for (int i = 0; i < totalHakmilik; i++) {
                    HakmilikPermohonan hp = hakmilikPermohonan.get(0);
                    Hakmilik hakmilikTerpilih = hp.getHakmilik();
                    String id = hakmilikTerpilih.getIdHakmilik();
                    hakmilikTerpilih = hakmilikDAO.findById(id); // old hakmilik

                    logger.info(" /* SAVE NEW HAKMILIK in TABLE HAKMILIK */ ");
                    hakmilikBaru = new Hakmilik();
                    hakmilikBaru.setDaerah(hakmilikTerpilih.getDaerah());

                    if (hakmilikTerpilih.getPegangan() == 'S') {
                        hakmilikBaru.setKodHakmilik(kodHakmilikDAO.findById("GM"));
                    } else {
                        hakmilikBaru.setKodHakmilik(kodHakmilikDAO.findById("PM"));
                    }

                    hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());

                    logger.info("--> kod pekan : " + hakmilikTerpilih.getBandarPekanMukim().getKod());

                    /* GENERATE HAKMILIK BARU HERE */
                    String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                    KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
                    hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                    hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    hakmilikBaru.setCawangan(hakmilikTerpilih.getCawangan());
                    hakmilikBaru.setSeksyen(hakmilikTerpilih.getSeksyen());
                    hakmilikBaru.setLokasi(hakmilikTerpilih.getLokasi());
                    hakmilikBaru.setNoLitho(hakmilikTerpilih.getNoLitho());
                    hakmilikBaru.setSekatanKepentingan(hakmilikTerpilih.getSekatanKepentingan());
                    hakmilikBaru.setSyaratNyata(hakmilikTerpilih.getSyaratNyata());
                    hakmilikBaru.setPbt(hakmilikTerpilih.getPbt());
                    hakmilikBaru.setNoVersiDhde(0); // set DHDE version to zero will plus 1 after daftar urusan
                    hakmilikBaru.setNoVersiDhke(0); // set DHKE version to zero will plus 1 after daftar urusan
                    KodLot kl = new KodLot();
                    if (hakmilikTerpilih.getKodHakmilik().getKod().equals("PN")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("GRN")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("PM")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("GMM")) {
                        kl.setKod("1");
                    } else {
                        kl.setKod("2");
                    }
                    hakmilikBaru.setLot(kl);
                    hakmilikBaru.setLot(hakmilikTerpilih.getLot());
                    hakmilikBaru.setNoLot(hakmilikTerpilih.getNoLot());
                    hakmilikBaru.setCukai(hakmilikTerpilih.getCukai());
                    if (hakmilikTerpilih.getCukaiSebenar() != null) {
                        hakmilikBaru.setCukaiSebenar(hakmilikTerpilih.getCukaiSebenar());
                    }
                    hakmilikBaru.setLuas(hakmilikTerpilih.getLuas());
                    hakmilikBaru.setNoPelan(hakmilikTerpilih.getNoPelan());
                    hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());

                    hakmilikBaru.setKategoriTanah(hakmilikTerpilih.getKategoriTanah());
                    hakmilikBaru.setKegunaanTanah(hakmilikTerpilih.getKegunaanTanah());

                    logger.info("::start copy tarikh daftar asal dari mohon hakmilik sebelum");
                    if (hakmilikTerpilih.getTarikhLuput() != null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                        hakmilikBaru.setTarikhLuput(hakmilikTerpilih.getTarikhLuput());
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() != null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() == null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftar());
                    }

                    hakmilikBaru.setTempohPegangan(hakmilikTerpilih.getTempohPegangan());
                    hakmilikBaru.setPegangan(hakmilikTerpilih.getPegangan());
                    hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
                    hakmilikBaru.setRizab(hakmilikTerpilih.getRizab());
                    hakmilikBaru.setRizabNoWarta(hakmilikTerpilih.getRizabNoWarta());
                    hakmilikBaru.setRizabTarikhWarta(hakmilikTerpilih.getRizabTarikhWarta());
                    hakmilikBaru.setGsaNoWarta(hakmilikTerpilih.getGsaNoWarta());
                    hakmilikBaru.setGsaKawasan(hakmilikTerpilih.getGsaKawasan());
                    hakmilikBaru.setGsaTarikhWarta(hakmilikTerpilih.getGsaTarikhWarta());
                    hakmilikBaru.setPbtKawasan(hakmilikTerpilih.getPbtKawasan());
                    hakmilikBaru.setPbtNoWarta(hakmilikTerpilih.getPbtNoWarta());
                    hakmilikBaru.setPbtTarikhWarta(hakmilikTerpilih.getPbtTarikhWarta());
                    hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());

                    hakmilikBaru.setInfoAudit(info);

                    /* CREATE NEW MOHON_HAKMILIK  */
                    mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(p);

                    mohonHakmilikBaru.setInfoAudit(info);
                    hakmilikDAO.save(hakmilikBaru);
                    hakmilikPermohonanDAO.save(mohonHakmilikBaru);

                    Hakmilik hm = hp.getHakmilik();
                    hm = hakmilikDAO.findById(id);

                    logger.info("/* Start Copy Hakmilik Asal Untuk Urusan Selain HSSTB /HKSTB */ ");
                    logger.info("--> size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
                    logger.info("--> size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

                    List<HakmilikAsal> hmAsal = regService.cariHakmilikAsalDariHakmilik(hm.getIdHakmilik()); // check senarai hakmilik_asal bagi hakmilik lama
                    List<HakmilikSebelum> hmSblm = regService.cariHakmilikSebelumDariHakmilik(hm.getIdHakmilik()); // check senarai hakmilik_Sblm bagi hakmilik lama

                    /* REFER from Pn Safina(SME): check new hakmilik in hakmilik_asal. 
                     * If there's no record, insert table mohon_hakmilik_asal
                     * if there's record, insert table mohon_hakmilik_sebelum
                     */
                    if (hmAsal.isEmpty()) {
                        HakmilikAsalPermohonan hma = new HakmilikAsalPermohonan();
                        hma.setHakmilikPermohonan(mohonHakmilikBaru);
                        hma.setHakmilik(hakmilikBaru);
                        hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
                        hma.setInfoAudit(info);
                        hakmilikAsalPermohonanDAO.save(hma);
                    } else {

                        for (HakmilikAsal asal : hmAsal) {

                            // save in table mohon_hakmilik_asal
                            HakmilikAsalPermohonan hma = new HakmilikAsalPermohonan();
                            hma.setHakmilikPermohonan(mohonHakmilikBaru);
                            hma.setHakmilik(hakmilikBaru);
                            //hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(asal.getHakmilikAsal()).getIdHakmilik());
                            //hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(asal.getHakmilik().getIdHakmilik()).getIdHakmilik());
                            hma.setHakmilikSejarah(asal.getHakmilikAsal());
                            hma.setInfoAudit(info);
                            hakmilikAsalPermohonanDAO.save(hma);
                        }
                        if (!hmSblm.isEmpty()) {

                            for (HakmilikSebelum sblm : hmSblm) {
                                /* //save in table hakmilik_sblm
                                 HakmilikSebelum hsblm = new HakmilikSebelum();
                                 hsblm.setHakmilik(mohonHakmilikBaru.getHakmilik()); 
                                 hsblm.setHakmilikSebelum(sejarahHakmilikDAO.findById(sblm.getHakmilikSebelum().getIdHakmilik())); 
                                 hsblm.setInfoAudit(ia);
                                 regService.simpanHakmilikSebelum(hsblm);*/

                                // save in table mohon_hakmilik_sblm
                                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                hsp.setPermohonan(p);
                                hsp.setCawangan(pengguna.getKodCawangan());
                                hsp.setHakmilik(hakmilikBaru);
                                hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(sblm.getHakmilikSebelum()).getIdHakmilik());
                                hsp.setInfoAudit(info);
                                hakmilikSblmPermohonanDAO.save(hsp);
                            }
                        }

                        HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                        hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                        hsp.setPermohonan(p);
                        hsp.setCawangan(pengguna.getKodCawangan());
                        hsp.setHakmilik(hakmilikBaru);
                        hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
                        hsp.setInfoAudit(info);
                        hakmilikSblmPermohonanDAO.save(hsp);
                    }
//                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(hm);
                    for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                        if (hpk == null || hpk.getAktif() == 'T') {
                            continue;
                        }
                        HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                        hpb.setHakmilik(hakmilikBaru);
                        hpb.setCawangan(hakmilikBaru.getCawangan());
                        hpb.setPihakCawangan(hpk.getPihakCawangan());
                        hpb.setJenis(hpk.getJenis());
                        hpb.setSyerPembilang(hpk.getSyerPembilang());
                        hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                        hpb.setPerserahan(hpk.getPerserahan());
                        hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                        hpb.setKaveatAktif(hpk.getKaveatAktif());
                        hpb.setAktif(hpk.getAktif());
                        hpb.setPihak(hpk.getPihak());
                        hpb.setNama(hpk.getNama());
                        hpb.setAlamat1(hpk.getAlamat1());
                        hpb.setAlamat2(hpk.getAlamat2());
                        hpb.setAlamat3(hpk.getAlamat3());
                        hpb.setAlamat4(hpk.getAlamat4());
                        hpb.setAlamatSurat(hpk.getAlamatSurat());
                        hpb.setPoskod(hpk.getPoskod());
                        hpb.setNegeri(hpk.getNegeri());
                        hpb.setPihakKongsiBersama(hpk.getPihakKongsiBersama());
                        hpb.setNoPengenalan(hpk.getNoPengenalan());
                        hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                        hpb.setInfoAudit(info);
                        hakmilikPihakKepentinganService.save(hpb);
                    }

                    List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
                    for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                        if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                            continue;
                        }

                        HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                        hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                        hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                        hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                        hakmilikUrusanBaru.setAktif(hu.getAktif());
                        hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                        if (hu.getTarikhDaftar() != null) {
                            hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                        } else {
                            hakmilikUrusanBaru.setTarikhDaftar(new java.util.Date());
                        }
                        hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                        hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                        hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                        hakmilikUrusanBaru.setDaerah(hu.getDaerah());
                        hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                        hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                        hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                        hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                        hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                        hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                        hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                        hakmilikUrusanBaru.setInfoAudit(info);
                        hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

                        Permohonan p2 = permohonanDAO.findById(hu.getIdPerserahan());
                        if (p2 != null) {
                            List<Pemohon> senaraiPemohon = p2.getSenaraiPemohon();
                            for (Pemohon pmh : senaraiPemohon) {
                                Pemohon _pmh = new Pemohon();
                                _pmh.setPermohonan(p2);
                                _pmh.setHakmilik(hakmilikBaru);
                                _pmh.setAlamat(pmh.getAlamat());
                                _pmh.setAlamatSurat(pmh.getAlamatSurat());
                                _pmh.setCawangan(pmh.getCawangan());
                                _pmh.setDalamanNilai1(pmh.getDalamanNilai1());
                                _pmh.setDalamanNilai2(pmh.getDalamanNilai2());
                                _pmh.setJenis(pmh.getJenis());
                                _pmh.setJenisPemohon(pmh.getJenisPemohon());
                                _pmh.setJenisPengenalan(pmh.getJenisPengenalan());
                                _pmh.setJumlahPembilang(pmh.getJumlahPembilang());
                                _pmh.setJumlahPenyebut(pmh.getJumlahPenyebut());
                                _pmh.setNama(pmh.getNama());
                                _pmh.setNoPengenalan(pmh.getNoPengenalan());
                                _pmh.setPekerjaan(pmh.getPekerjaan());
                                _pmh.setPendapatan(pmh.getPendapatan());
                                _pmh.setPihak(pmh.getPihak());
                                _pmh.setPihakCawangan(pmh.getPihakCawangan());
                                _pmh.setSyerPembilang(pmh.getSyerPembilang());
                                _pmh.setSyerPenyebut(pmh.getSyerPenyebut());
                                _pmh.setWargaNegara(pmh.getWargaNegara());
                                _pmh.setInfoAudit(info);
                                pemohonService.saveOrUpdate(_pmh);
                            }

                            List<PermohonanPihak> senaraiPermohonanPihak = p2.getSenaraiPihak();
                            for (PermohonanPihak pp : senaraiPermohonanPihak) {
                                PermohonanPihak _pp = new PermohonanPihak();
                                _pp.setAlamat(pp.getAlamat());
                                _pp.setAlamatSurat(pp.getAlamatSurat());
                                _pp.setCawangan(pp.getCawangan());
                                _pp.setHakmilik(hakmilikBaru);
                                _pp.setInfoAudit(info);
                                _pp.setJenis(pp.getJenis());
                                _pp.setJenisPengenalan(pp.getJenisPengenalan());
                                _pp.setJumlahPembilang(pp.getJumlahPembilang());
                                _pp.setJumlahPenyebut(pp.getJumlahPenyebut());
                                _pp.setNama(pp.getNama());
                                _pp.setNoPengenalan(pp.getNoPengenalan());
                                _pp.setNoRujukan(pp.getNoRujukan());
                                _pp.setPermohonan(pp.getPermohonan());
                                _pp.setPihak(pp.getPihak());
                                _pp.setPihakCawangan(pp.getPihakCawangan());
                                _pp.setSyerBersama(pp.getSyerBersama());
                                _pp.setSyerPembilang(pp.getSyerPembilang());
                                _pp.setSyerPenyebut(pp.getSyerPenyebut());
                                _pp.setWargaNegara(pp.getWargaNegara());
                                permohonanPihakService.saveOrUpdate(_pp);
                            }
                        }

                    }
                }

                try {

                    WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                            p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                            p.getKodUrusan().getNama());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }

            }
        }
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        proposedOutcome = "back";
        return proposedOutcome;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public FolderDokumen getFolder() {
        return folder;
    }

    public void setFolder(FolderDokumen folder) {
        this.folder = folder;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public void setAkaun(String Akaun) {
        this.Akaun = Akaun;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public String getIdHakmilikSblm() {
        return idHakmilikSblm;
    }

    public void setIdHakmilikSblm(String idHakmilikSblm) {
        this.idHakmilikSblm = idHakmilikSblm;
    }

    public String getIdHakmilikAsal() {
        return idHakmilikAsal;
    }

    public void setIdHakmilikAsal(String idHakmilikAsal) {
        this.idHakmilikAsal = idHakmilikAsal;
    }
}

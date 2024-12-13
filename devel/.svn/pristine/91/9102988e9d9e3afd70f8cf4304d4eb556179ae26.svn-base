/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.validator;

import com.google.inject.Inject;
import etanah.SYSLOG;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.HakmilikWarisDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodPerhubunganHakmilikDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodStatusAkaunDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SejarahDokumenDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikWaris;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodWarganegara;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKlasifikasi;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakKemaskini;
import etanah.model.Pihak;
import etanah.service.SyerValidationService;
import etanah.service.common.PihakService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.daftar.KutipanDataService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math.fraction.Fraction;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import etanah.model.KodNegeri;
import etanah.model.KodNotis;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Notis;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.SejarahDokumen;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.common.HakmilikService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PermohonanAtasPihakBerkepentinganService;
import etanah.service.common.PermohonanService;
import etanah.service.ReportName;
import etanah.service.daftar.RegistrationModule;
import java.io.File;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Transaction;
import etanah.service.RegService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikWarisService;
import etanah.service.common.NotisService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.daftar.PembetulanService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.util.WORMUtil;
import etanah.view.etanahContextListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Query;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.common.PermohonanHubunganService;
import etanah.service.daftar.PermohonanPihakKemaskiniService;

/**
 *
 * @author md.nurfikri
 */
public class PendaftaranValidation implements StageListener {

    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    SyerValidationService syerService;
    @Inject
    KutipanDataService kutipanDataService;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikUrusanService hakmilikService;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikService hakmilikService2;
    @Inject
    PihakService pihakService;
    @Inject
    DokumenService dokumenService;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanAtasPihakBerkepentinganService permohonanAtasPihakBerkepentinganService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    KodPerhubunganHakmilikDAO kodPerhubunganHakmilikDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    ReportUtil reportUtil2;
    @Inject
    RegService regService;
    @Inject
    HakmilikWarisDAO hakmilikWarisDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportName reportName;
    @Inject
    HakmilikWarisService hakmilikWarisService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarServices;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendaftaranSuratKuasaService suratkuasaService; //added by:mohd.fairul
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    NotisService notisService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PermohonanPihakKemaskiniService PermohonanPihakKemaskiniService;
    @Inject
    PembetulanService pService;
    @Inject
    PermohonanAtasPerserahanService permohonanAtasPerserahanService;
    @Inject
    private KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    PermohonanHubunganService permohonanHubunganService;
    @Inject
    SejarahDokumenDAO sejarahDokumenDAO;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private PermohonanPihakKemaskini permohonanPihakKemaskini;
    private List<PermohonanPihakKemaskini> senaraiMohonPihakKkini;
    private List<PermohonanPihak> senaraiPermohonanPihakBaru;
    private List<PermohonanPihak> senaraiPermohonanPihakLama;
    private List<PermohonanHubungan> senaraiMohonHbgn;
    private String IdSasar;
    private List<String> idMohonLama;
    private static final Logger LOG = Logger.getLogger(PendaftaranValidation.class);
    private static final Logger syslog = SYSLOG.getLogger();
    private static String DAFTAR = "D";
    private static String TOLAK = "T";
    private static String GANTUNG = "G";
    private List<Permohonan> senaraiPermohonanBaru = new ArrayList<Permohonan>();
    private List<Permohonan> senaraiPermohonanXdaftar = new ArrayList<Permohonan>();
    private static final String[] URUSAN_TO_INSERT_ID_RUJ = {
        "PMG",
        "PMP",
        "PMPJK",
        "PHMMK",
        "PHMM",
        "416C",
        "GDPJK",
        "KVAK",
        "KVAS",
        "KVAT",
        "KVLK",
        "KVLP",
        "KVLS",
        "KVLT",
        "KVPK",
        "KVPP",
        "KVPPT",
        "KVPS",
        "KVPT",
        "KVSK",
        "KVSPT",
        "KVSS",
        "KVST",
        "PMHBE",
        "PNPHB",
        "PJKT",
        "GDPJ",
        "PNPBK",
        "PNPBA",
        "PLT",
        "PLK",
        "PLS",
        "PMHUK",
        "KVSPC",
        "LTK",
        //        "WAKAF",
        "JPGPJ"
    };
    private static final String[] URUSAN_HAKMILIK_BATAL = {"PMHHB", "RPBNB", "PRPMB"};
    private static final String[] URUSAN_BATAL_PERSERAHAN = {"CPB", "PMSE"};
    private static final String[] HAKMILIK_URUSAN_TAK_AKTIF = {"PMHHB", "PMTB", "PLB", "PMBKU", "PNPAB", "KVPB", "KVSB", "KVSMP", "KVSP",
        "KVSTB", "MGGB", "PEMB", "PJB", "PJKB", "GDB", "GDCEL", "GDPJB", "TENB",
        "TMAMB", "ISB", "JAGAB", "KVAB", "KVATB", "KVLB", "KVLTB",
        "PJKSB", "PJSB", "GDL", "GDPJL", "TENL", "ISL", "JMB", "JPB", "PHMB"};

    @Override
    public boolean beforeStart(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext ctx, String proposedOutcome) {
        LOG.debug("@BeforeComplete [ start ]");

        Permohonan permohonan = ctx.getPermohonan();
        List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
        if (permohonan != null) {
            String idKump = permohonan.getIdKumpulan();
            if (StringUtils.isNotBlank(idKump)) {
                senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKump);
            } else {
                senaraiPermohonan.add(permohonan);
            }
        } else {
            ctx.addMessage("ID Permohonan tidak dikenali.");
            return null;
        }

        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();

        for (Permohonan mohon : senaraiPermohonan) {
            if (mohon.getKeputusan() == null) {
                ctx.addMessage("Sila masukan keputusan untuk perserahan [" + mohon.getIdPermohonan() + "]");
                return null;
            }
            if (mohon.getKeputusan().getKod().equals(DAFTAR)) {
                List<HakmilikPermohonan> senaraiHakmilikTerlibat = mohon.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                    if (!ctx.isByPass()) {
                        Date d = mohon.getInfoAudit().getTarikhMasuk();
                        if (permohonanService.isPrevUrusanNotComplete(hp.getHakmilik().getIdHakmilik(), d)) {
                            ctx.addMessage("Terdapat urusan sebelum yang masih belum selesai.");
                            return null;
                        }
                    }
                }

                String kodUrusan = mohon.getKodUrusan().getKod();
                if (kodUrusan.equals("416C")) {
                    kodUrusan = "WAKAF";
                }
                String kodUrusanSblm = "";
                Permohonan permohonanSebelum = mohon.getPermohonanSebelum();
                if (permohonanSebelum != null) {
                    kodUrusanSblm = permohonanSebelum.getKodUrusan().getKod();
                }
                pengguna = ctx.getPengguna();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                RegistrationModule rm = RegistrationModule.valueOf(kodUrusan);
                int urusan = rm.getCode();
                LOG.debug("kod urusan = " + kodUrusan + "[" + urusan + "]");

                try {

                    insertUrusan(mohon, info);

                    switch (urusan) {
                        case 1: {
                            updatePihak2(mohon, info);
                            if (ArrayUtils.contains(URUSAN_BATAL_PERSERAHAN, kodUrusanSblm)) {
                                batalPerserahan(permohonanSebelum, info);
                            }
                            insertPihak3(mohon, info);
                            updateAkaunDipegang(mohon, info);
                            break;
                        }
                        case 2: {
                            insertPihak(mohon, info);
                            break;
                        }
                        case 3: {
                            updateUrusanBaru(mohon, info);
                            break;
                        }
                        case 4: {
                            updateInfoPihak(mohon, info);
                            break;
                        }
                        case 5: {
                            updatePihakNoBahagian(mohon, info);
                            break;
                        }
                        case 6: {
                            insertPihak2(mohon, info);
                            break;
                        }
                        case 7: {
                            updateUrusanBaru(mohon, info);
                            updatePihak2(mohon, info);
                            break;
                        }
                        case 8: {
                            insertPemegangAmanah(mohon, info);
                            break;
                        }
                        case 9: {
                            updateUrusanBaru(mohon, info);
                            batalPihakPM(mohon, info);
                            insertPihak(mohon, info);
                            break;
                        }
                        case 10: {
                            updInsAll(mohon, info);
                            break;
                        }
                        case 11: {
                            insertPihakIsmen(mohon, info);
                            break;
                        }
                        case 12: {
                            break;
                        }
                        case 13: {
                            updateUrusanBaru(mohon, info);
                            doAktifDeaktifPemilik(mohon, info);
                            break;
                        }
                        case 14: {
                            batalHakmilik(mohon, info);
                            break;
                        }
                        case 15: {
                            updateUrusanBaru(mohon, info);
                            updatePemegangAmanah(mohon, info);
                            break;
                        }
                        case 16: {
                            insertPihakPemilikanBersama(mohon, info);
                            break;
                        }
                        case 17: {
                            updateSWBaru(mohon, info);
                            break;
                        }
                        case 18: {
                            updateUrusanBaru(mohon, info);
                            hidupHakmilik(mohon, info);
                            updateUrusan(mohon, info);
                            break;
                        }
                        case 19: {
                            updateUrusan(mohon, info);
                            break;
                        }
                        case 20: {
                            updateUrusanBaru(mohon, info);
                            doAktifDeaktifPemilik(mohon, info);
                            updatePihak2(mohon, info);
                            break;
                        }
                        case 21: {
                            updatePihak2(mohon, info);
                            doUpdatePermohonanPihakUrusanLama(permohonan, info);
                            break;
                        }
                        case 22: {
                            tukarStatusHakmilikPihak(mohon, info);
                            tukarStatusHakmilikUrusan(mohon, info);
                            break;
                        }
                        case 23: {
                            updatePihak2(mohon, info);
//                            tukarStatusHakmilikUrusan(mohon, info);
                            break;
                        }

                    }

//          updateNoVersi(mohon, info);
                } catch (Exception ex) {
                    tx.rollback();
                    Throwable t = ex;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    ctx.addMessage("Pendaftaran perserahan " + mohon.getIdPermohonan()
                            + " Tidak Berjaya.Sila Hubungi Pentadbir Sistem.\r[ " + ex.toString() + " ]");
                    return null;
                }

            }
        }

        //new feature : no versi + 1 for berangkai !!
        List<String> senaraiHm = new ArrayList<String>();
        for (Permohonan p : senaraiPermohonan) {
            for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                if (senaraiHm.contains(hp.getHakmilik().getIdHakmilik())) {
                    continue;
                }
                senaraiHm.add(hp.getHakmilik().getIdHakmilik());
            }
        }

        InfoAudit ia = new InfoAudit();
        List<Hakmilik> hmUpdated = new ArrayList<Hakmilik>();
        for (String shm : senaraiHm) {
            if (StringUtils.isBlank(shm)) {
                continue;
            }
            Hakmilik hm = hakmilikDAO.findById(shm);
            if (hm == null) {
                continue;
            }
            ia = hm.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new Date());
            if (permohonan.getKeputusan().getKod().equals("D")) {
                hm.setNoVersiDhde(hm.getNoVersiDhde() + 1);
                hm.setNoVersiDhke(hm.getNoVersiDhke() + 1);
            } else if (permohonan.getKeputusan().getKod().equals("T")) {
                hm.setNoVersiDhde(hm.getNoVersiDhde() + 1);
                hm.setNoVersiDhke(hm.getNoVersiDhke() + 1);
            }
            hm.setInfoAudit(ia);
            hmUpdated.add(hm);
        }

        if (!hmUpdated.isEmpty()) {
            regService.saveHmGroup(hmUpdated);
        }

        LOG.info("commiting transaction..");
        tx.commit();
        LOG.debug("@BeforeComplete [ success ]");
        StringBuilder msg = new StringBuilder(" - Pendaftaran Urusan Berjaya.");
        if (permohonan.getPermohonanSebelum() != null) {
            msg.append(" Notifikasi telah dihantar ke modul sebelum.");
        }
        ctx.addMessage(msg.toString());
//    ctx.addMessage(" : Pendaftaran Urusan Berjaya.");
        return proposedOutcome;
//        return null;
    }

    @Override
    public void afterComplete(StageContext context) {
        // TODO Auto-generated method stub
        Permohonan permohonan = context.getPermohonan();
        pengguna = context.getPengguna();
        List<Permohonan> senaraiPermohonanTerlibat = new ArrayList<Permohonan>();
        StringBuilder sb = new StringBuilder();

        List<SejarahDokumen> listSejarah = new ArrayList<SejarahDokumen>();

        if (permohonan != null && permohonan.getKeputusan() != null
                && permohonan.getKeputusan().getKod().equals(DAFTAR)) {

            String idKumpulan = permohonan.getIdKumpulan();
            if (StringUtils.isNotBlank(idKumpulan)) {
                senaraiPermohonanTerlibat = permohonanService.getPermohonanByIdKump(idKumpulan);
            } else {
                senaraiPermohonanTerlibat.add(permohonan);
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
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new Date());

                    Hakmilik hm = hp.getHakmilik();
                    if (hp.getDokumen2() != null) {
                        hm.setDhke(hp.getDokumen2());
                    }
                    if (hp.getDokumen3() != null) {

                        sejarahDokumen.setIdDokumen(hp.getDokumen3().getIdDokumen());
                        sejarahDokumen.setInduk(hm.getDhde() != null ? hm.getDhde() : hp.getDokumen3());
                        sejarahDokumen.setKodDokumen(hp.getDokumen3().getKodDokumen());
                        sejarahDokumen.setNamaFizikal(hp.getDokumen3().getNamaFizikal());
                        sejarahDokumen.setNoVersi(String.valueOf(hm.getNoVersiDhde()));
                        sejarahDokumen.setFormat(hp.getDokumen3().getFormat());
                        sejarahDokumen.setInfoAudit(ia);

                        listSejarah.add(sejarahDokumen);

                        hm.setDhde(hp.getDokumen3());
                    }
                }

                try {
                    if (StringUtils.isNotBlank(idKumpulan)) {
                        berangkaiGantung(pmohon, idKumpulan);
                    }
                    calculateJumSyer(pmohon);
                } catch (Exception ex) {
                    LOG.error(ex);
                }
            }
            suratkuasaService.saveHakmilikUrusanSurat(permohonan, pengguna);
            dokumenService.saveOrUpdateSejarahDokumen(listSejarah);

            context.addMessage("Perserahan " + sb.toString() + " telah berjaya didaftarkan.");

            //integrate with HCAP
            //todo : save sign file to HCAP
            LOG.debug("WORM process.....");
            WORMUtil worm = etanahContextListener.newInstance(WORMUtil.class);
            List<Dokumen> senaraiDokumen = new ArrayList<Dokumen>();
            String docPath = conf.getProperty("document.path");
            List<HakmilikPermohonan> senaraiHm = permohonan.getSenaraiHakmilik();
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
                        LOG.debug("insert into WORM [dhde]");
                        try {
                            int status = worm.put(dhde,
                                    hm.getIdHakmilik(),
                                    hm.getDaerah().getKod(), hm.getBandarPekanMukim().getbandarPekanMukim(),
                                    null,
                                    hm.getKodHakmilik().getKod(),
                                    String.valueOf(hm.getNoVersiDhde() != null ? hm.getNoVersiDhde() : 0),
                                    hm.getKodStatusHakmilik().getKod());
                            LOG.debug("[status] =  " + status);
                            if (status == WORMUtil.SC_CREATED
                                    || status == WORMUtil.SC_CREATED_W_ERROR) {
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
                            ex.printStackTrace();
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
                                sign.delete();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            if (!senaraiDokumen.isEmpty()) {
                dokumenService.saveOrUpdate(senaraiDokumen);
            }
        }

        //penghantaran notifikasi kepada yg berkenaan
        if (permohonan.getPermohonanSebelum() != null) {
            InfoAudit ia = new InfoAudit();
            pengguna = context.getPengguna();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
            Permohonan integrasiPermohonan = permohonan.getPermohonanSebelum();
            Notifikasi notifikasi = new Notifikasi();
            notifikasi.setTajuk("Maklumat Perserahan berjaya didaftarkan");
            notifikasi.setCawangan(integrasiPermohonan.getCawangan());
            StringBuilder mesej = new StringBuilder("ID Permohonan ")
                    .append(integrasiPermohonan.getIdPermohonan())
                    .append(" ( ")
                    .append(integrasiPermohonan.getKodUrusan().getNama())
                    .append(" ) ")
                    .append("telah di ")
                    .append(permohonan.getKeputusan().getNama());

            FasaPermohonan fp = fasaPermohonanService.getCurrentStage(integrasiPermohonan.getIdPermohonan(), permohonan.getIdStagePermohonanSebelum());
            if (fp != null && fp.getInfoAudit() != null) {
                Pengguna p = fp.getInfoAudit().getDimasukOleh();
                notifikasi.setPengguna(p);
            }

            notifikasi.setMesej(mesej.toString());
            notifikasi.setInfoAudit(ia);
            notifikasiService.save(notifikasi);
        }

    }

    @Override
    public void onGenerateReports(StageContext context) {

        Permohonan p = context.getPermohonan();
        pengguna = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        boolean isBerangkai = false;

        String dokumenPath = conf.getProperty("document.path");
        String kodNegeri = conf.getProperty("kodNegeri");

        Map<String, FolderDokumen> mapFolder = new HashMap<String, FolderDokumen>();
        Map<String, HakmilikPermohonan> mapHakmilikPermohonan = new HashMap<String, HakmilikPermohonan>();
        FolderDokumen fd = new FolderDokumen();

        List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();

        List<String> senaraiHakmilik = new ArrayList<String>();

        String idKump = permohonan.getIdKumpulan();
        StringBuilder sb = new StringBuilder();

        if (StringUtils.isNotBlank(idKump)) {
            senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKump);
            isBerangkai = true;
        } else {
            senaraiPermohonan.add(permohonan);
        }

        for (Permohonan mohon : senaraiPermohonan) {
            if (mohon == null || mohon.getKeputusan() == null) {
                continue;
            }

            List<HakmilikPermohonan> senaraiHkamilikPermohonan = mohon.getSenaraiHakmilik();
            if (mohon.getKeputusan().getKod().equals(DAFTAR)) {
                for (HakmilikPermohonan hp : senaraiHkamilikPermohonan) {
                    if (hp == null) {
                        continue;
                    }
                    Hakmilik hm = hp.getHakmilik();
                    if (!senaraiHakmilik.contains(hm.getIdHakmilik())) {
                        senaraiHakmilik.add(hm.getIdHakmilik());
                        if (sb.length() > 0) {
                            sb.append(",");
                        }
                        sb.append(hm.getIdHakmilik());
                    }

                    mapHakmilikPermohonan.put(hm.getIdHakmilik(), hp);
                    mapFolder.put(hm.getIdHakmilik(), mohon.getFolderDokumen());
                }

                //Untuk urusan kaveat
                KodUrusan ku = mohon.getKodUrusan();

                if (ku.getKod().startsWith("KVS") || ku.getKod().startsWith("KVL")
                        || ku.getKod().startsWith("KVP")) {

                    String[] params = new String[]{"p_id_mohon"};
                    String[] values = new String[]{mohon.getIdPermohonan()};
                    String path = "";
                    String reportName = "";
                    boolean notisToGenerate = true;
                    String kod = conf.getProperty("kodNegeri");
                    KodDokumen kd = null;
                    Dokumen d = null;

                    if (ku.getKod().startsWith("KVS")
                            || ku.getKod().equals("KVLS") //fat sesi 3 : cug
                            || ku.getKod().equals("KVLT")
                            || ku.getKod().equals("KVLP")) {  // retest melacca -CUG
                        if (ku.getKod().equals("KVSMP") //ku.getKod().equals("KVSB") ditutup pada fat sesi 5 : cug
                                || ku.getKod().equals("KVSPC")) {
                            kd = kodDokumenDAO.findById("19C");
                            reportName = "REG_Notis19C.rdf";
                        } else if (ku.getKod().equals("KVSTB")
                                || ku.getKod().equals("KVSP")
                                || ku.getKod().equals("KVSB")) {
                            //nothing todo
                            notisToGenerate = false;
                        } else {
                            kd = kodDokumenDAO.findById("19A");
                            if (kod.equals("04")) {
                                //reportName = "REG_Notis19A_Pers_mlk.rdf";
                                reportName = "REG_Notis19A_mlk.rdf";
                            } else {
                                reportName = "REG_Notis19A_Pers.rdf";
                            }
                        }

                        if (notisToGenerate) {
                            fd = mohon.getFolderDokumen();
//                            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                            d = generateGeran(dokumenPath, fd, kd, reportName, params, values, sb.toString(), mohon.getIdPermohonan());
                            path = mohon.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            saveNotis(mohon, kd, d);
                            for (HakmilikPermohonan hp : senaraiHkamilikPermohonan) {
                                hp.setDokumen6(d);
                                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
                            }
                        }
                    } else if (ku.getKod().startsWith("KVP")) {
                        if (ku.getKod().equals("KVPB")) {
//                        kd = kodDokumenDAO.findById("19C");
//                        reportName = "REG_Notis19C.rdf";
//                        many labs - PD@29/09/2010
                            notisToGenerate = false;
                        } else {
                            /* Generate Notis 19A */
                            kd = kodDokumenDAO.findById("19A");
                            if (kod.equals("04")) {
                                reportName = "REG_Notis19A_mlk.rdf";
                            } else {
                                reportName = "REG_Notis19A_Pers.rdf";
//                reportName = "REG_Notis19A.rdf";
                            }
                            fd = mohon.getFolderDokumen();
                            d = generateGeran(dokumenPath, fd, kd, reportName, params, values, sb.toString(), mohon.getIdPermohonan());
                            path = mohon.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            saveNotis(mohon, kd, d);
                            for (HakmilikPermohonan hp : senaraiHkamilikPermohonan) {
                                hp.setDokumen6(d);
                                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
                            }
                            notisToGenerate = false;

                            /* Generate Notis 19F For Melaka */
//                            if (StringUtils.isNotBlank(kod) && kod.equals("04")) {
//
//                                KodDokumen notis19F = kodDokumenDAO.findById("19F");
//                                String reportName19F = "REG_Notis19F_MLK.rdf";
//                                fd = mohon.getFolderDokumen();
////                                d = saveOrUpdateDokumen(fd, notis19F, permohonan.getIdPermohonan());
//                                d = generateGeran(dokumenPath, fd, notis19F, reportName19F, params, values, sb.toString(), permohonan.getIdPermohonan());
//                                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                                reportUtil.generateReport(reportName19F, params, values, dokumenPath + path, pengguna);
//                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
//                                saveNotis(permohonan, notis19F, d);
//                                notisToGenerate = false;
//                            }
                        }

                        if (notisToGenerate) {
                            fd = mohon.getFolderDokumen();
//                            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                            d = generateGeran(dokumenPath, fd, kd, reportName, params, values, sb.toString(), mohon.getIdPermohonan());
                            path = mohon.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            saveNotis(mohon, kd, d);
                        }
                    }
                }

            } else {
                fd = mohon.getFolderDokumen();
                List<KandunganFolder> senaraiKandungan = fd.getSenaraiKandungan();
                List<String> senaraiDokumen = new ArrayList<String>();
                List<String> senaraiKandunganFolder = new ArrayList<String>();
                List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

                for (KandunganFolder kf : senaraiKandungan) {
                    if (kf == null || kf.getDokumen() == null) {
                        continue;
                    }
                    if (kf.getDokumen().getKodDokumen().getKod().equals("DHKE")
                            || kf.getDokumen().getKodDokumen().getKod().equals("DHDE")) {
                        senaraiDokumen.add(String.valueOf(kf.getDokumen().getIdDokumen()));
                        senaraiKandunganFolder.add(String.valueOf(kf.getIdKandunganFolder()));
                        senaraiKF.add(kf);
                    }
                }

                for (KandunganFolder kf : senaraiKF) {
                    senaraiKandungan.remove(kf);
                }

                for (HakmilikPermohonan hp : senaraiHkamilikPermohonan) {
                    hp.setDokumen2(null);
                    hp.setDokumen3(null);
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
                }

                if (senaraiDokumen.size() > 0) {
                    for (int i = 0; i < senaraiDokumen.size(); i++) {
                        String idDokumen = senaraiDokumen.get(i);
                        String idKandunganFolder = senaraiKandunganFolder.get(i);
                        dokumenService.deleteDokumenFolderDok(idDokumen, idKandunganFolder);
                    }
                }

            }
        }

        if (!senaraiHakmilik.isEmpty()) {

            String dhke = "";
            String dhde = "";
            String smkd = "";
            String bskdk = "";
            String bskkk = "";
            String psk = "";
            String[] params = null;
            String[] values = null;

            String[] TAKNAK_GENERATE_GERAN = {
                "CPB"
            };

            for (String idHakmilik : senaraiHakmilik) {
                if (StringUtils.isBlank(idHakmilik)) {
                    continue;
                }

                //Added by Aizuddin for urusan x generate geran
                if (ArrayUtils.contains(TAKNAK_GENERATE_GERAN, permohonan.getKodUrusan().getKod())) {
                    continue;
                }

                KodDokumen kod1 = kodDokumenDAO.findById("DHKE");
                KodDokumen kod2 = kodDokumenDAO.findById("DHDE");
                KodDokumen kod3 = kodDokumenDAO.findById("BSKDK");
                KodDokumen kod4 = kodDokumenDAO.findById("BSKKK");
                KodDokumen kod5 = kodDokumenDAO.findById("PSK");
                //if strata, not dhke/dhde. manual sign!!
                Hakmilik hm = hakmilikDAO.findById(idHakmilik);
                if (hm == null) {
                    continue;
                }
                if (StringUtils.isNotBlank(hm.getIdHakmilikInduk())) {
                    kod1 = kodDokumenDAO.findById("DHKK");
                    kod2 = kodDokumenDAO.findById("DHDK");
                    if (isBerangkai) {
//            dhke = reportName.getDHKEBerangkaiReportName();
//            dhde = reportName.getDHDEBerangkaiReportName();

                        if (kodNegeri.equals("05")) {//NEGERI SEMBILAN
                            dhke = "REGB4KDHKK_NSr.rdf"; // DHKK rdf name
                            dhde = "REGB4KDHDK_NSr.rdf"; // DHDK rdf name
//              dhke = "REGB4K_NS.rdf";
//              dhde = "REGB4K_NS.rdf";
                            bskdk = "UTILITIBSKDHDK_NS.rdf";
                            bskkk = "UTILITIBSKDHKK_NS.rdf";
                            psk = "UTILITIPSK_NS.rdf";
                        } else {                     //NEGERI MELAKA
                            dhke = "REGB4KDHKKr_MLK.rdf";
                            dhde = "REGB4KDHDKr_MLK.rdf";

                            bskdk = "UTILITIBSKDHDK_MLK.rdf";
                            bskkk = "UTILITIBSKDHKK_MLK.rdf";
                            psk = "UTILITIPSK_MLK.rdf";
                        }

                        params = new String[]{"p_id_mohon", "p_id_hakmilik", "p_kump"};
                        values = new String[]{permohonan.getIdPermohonan(), idHakmilik, idKump};
                    } else {
//                        dhke = "strBorang4K.rdf";
//                        dhde = "strBorang4K.rdf";
                        LOG.debug(":::KOD NEGERI : " + kodNegeri);
                        if (kodNegeri.equals("05")) {//NEGERI SEMBILAN
                            dhke = "REGB4KKK_NS.rdf"; // DHKK rdf name
                            dhde = "REGB4KDK_NS.rdf"; // DHDK rdf name
//              dhke = "REGB4K_NS.rdf";
//              dhde = "REGB4K_NS.rdf";
                            bskdk = "UTILITIBSKDHDK_NS.rdf";
                            bskkk = "UTILITIBSKDHKK_NS.rdf";
                            psk = "UTILITIPSK_NS.rdf";
                        } else {                     //NEGERI MELAKA
                            dhke = "REGB4KDHKK_MLK.rdf";
                            dhde = "REGB4KDHDK_MLK.rdf";

                            bskdk = "UTILITIBSKDHDK_MLK.rdf";
                            bskkk = "UTILITIBSKDHKK_MLK.rdf";
                            psk = "UTILITIPSK_MLK.rdf";
                        }
                        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                        values = new String[]{permohonan.getIdPermohonan(), idHakmilik};
                    }
                } else if (isBerangkai) {
                    dhke = reportName.getDHKEBerangkaiReportName();
                    dhde = reportName.getDHDEBerangkaiReportName();
                    params = new String[]{"p_id_hakmilik", "p_kump"};
                    values = new String[]{idHakmilik, idKump};

                    //Added by Aizuddin to generate Surat Mohon Kemuka Dokumen
//                        if (p.getKodUrusan().getKod().equalsIgnoreCase("JMB")
//                        ||p.getKodUrusan().getKod().equalsIgnoreCase("JPB")){
//                            smkd = "REGSuratMohonDHKK.rdf";
//                            params = new String[]{"p_id_mohon"};
//                            values = new String[]{permohonan.getIdPermohonan()};
//                        }
                } else {
                    dhke = reportName.getDHKEReportName();
                    dhde = reportName.getDHDEReportName();
                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                    values = new String[]{permohonan.getIdPermohonan(), idHakmilik};

                    //Added by Aizuddin to generate Surat Mohon Kemuka Dokumen
//                        if (p.getKodUrusan().getKod().equalsIgnoreCase("JMB")
//                        ||p.getKodUrusan().getKod().equalsIgnoreCase("JPB")){
//                            smkd = "REGSuratMohonDHKK.rdf";
//                            params = new String[]{"p_id_mohon", "p_id_hakmilik", "p_kump"};
//                            values = new String[]{permohonan.getIdPermohonan(), idHakmilik, idKump};
//                        }
                }

                if (ArrayUtils.contains(URUSAN_HAKMILIK_BATAL, permohonan.getKodUrusan().getKod())) {
                    KodStatusHakmilik kodSts = kodStatusHakmilikDAO.findById("T");
                    InfoAudit ia = new InfoAudit();
                    ia.setTarikhKemaskini(new Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    hm.setKodStatusHakmilik(kodSts);
                    hm.setInfoAudit(ia);
                    hakmilikService2.saveHakmilik(hm);
                }

                fd = mapFolder.get(idHakmilik);
//                if (isBerangkai) {
//                    dhke = reportName.getDHKEBerangkaiReportName();
//                    dhde = reportName.getDHDEBerangkaiReportName();
//                    params = new String[]{"p_id_mohon", "p_id_hakmilik", "p_kump"};
//                    values = new String[]{permohonan.getIdPermohonan(), idHakmilik, idKump};
//                } else {
//                    dhke = reportName.getDHKEReportName();
//                    dhde = reportName.getDHDEReportName();
//                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
//                    values = new String[]{permohonan.getIdPermohonan(), idHakmilik};
//                }

                Dokumen geranDHKE = generateGeran(dokumenPath, fd, kod1,
                        dhke, params, values, sb.toString(), idHakmilik);

                Dokumen geranDHDE = generateGeran(dokumenPath, fd, kod2,
                        dhde, params, values, sb.toString(), idHakmilik);

                if (StringUtils.isNotBlank(hm.getIdHakmilikInduk())) {
                    Dokumen geranBSKDHDK = generateGeran(dokumenPath, fd, kod3,
                            bskdk, params, values, sb.toString(), idHakmilik);
                    Dokumen geranBSKDHKK = generateGeran(dokumenPath, fd, kod4,
                            bskkk, params, values, sb.toString(), idHakmilik);
                    Dokumen geranPSK = generateGeran(dokumenPath, fd, kod5,
                            psk, params, values, sb.toString(), idHakmilik);
                }

//  unblock : simulasi 5/11/2014
//        String[] NOT_TO_PRINT_DHKE = {
//          "KVSB",
//          "KVSK",
//          "KVSMP",
//          //                "KVSP",
//          //          "KVSPC",      comment on 10/10/2013 as requested by cug
//          "KVSPT",
//          "KVSS",
//          "KVST",
//          "KVSTB"
//        };
                HakmilikPermohonan hp = mapHakmilikPermohonan.get(idHakmilik);
//        if (!ArrayUtils.contains(NOT_TO_PRINT_DHKE, hp.getPermohonan().getKodUrusan().getKod())) {
//          hp.setDokumen2(geranDHKE);
//        }
                hp.setDokumen2(geranDHKE);
                hp.setDokumen3(geranDHDE);

//                if (p.getKodUrusan().getKod().equalsIgnoreCase("JMB")
//                        ||p.getKodUrusan().getKod().equalsIgnoreCase("JPB")){
//                    KodDokumen kod3 = kodDokumenDAO.findById("SMKD");
//                    Dokumen suratKemukaDHKK = generateGeran(dokumenPath, fd, kod3,
//                            smkd, params, values, sb.toString(), idHakmilik);
//                    hp.setDokumen4(suratKemukaDHKK);
//                }
                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
            }

            String[] SINGLE_REPORT = {
                "JMB",
                "JPB",};
            //Added by Aizuddin to generate single report
            if (ArrayUtils.contains(SINGLE_REPORT, permohonan.getKodUrusan().getKod())) {
                smkd = "REGSuratMohonDHKK.rdf";
                params = new String[]{"p_id_mohon"};
                values = new String[]{permohonan.getIdPermohonan()};
                KodDokumen kod3 = kodDokumenDAO.findById("SMKD");
                Dokumen suratKemukaDHKK = generateGeran(dokumenPath, fd, kod3,
                        smkd, params, values, sb.toString(), permohonan.getIdPermohonan());
//                hp.setDokumen4(suratKemukaDHKK);
            }
        }
    }

    //Added by Aizuddin to save notis
    private void saveNotis(Permohonan permohonan, KodDokumen kd, Dokumen dokumen) {
//        Notis notis = null;
        InfoAudit ia = null;
        KodNotis kodNotis = kodNotisDAO.findById(kd.getKod());
        List<Notis> senaraiNotis = notisService.getSenaraiNotis(permohonan.getIdPermohonan());

        List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();

        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }

            if (senaraiNotis.isEmpty()) {
                Notis notis = new Notis();

                PermohonanRujukanLuar prl = permohonanRujukanLuarServices.findMohonRujukLuarIdHakmilikIdPermohonan(hp.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
                if (prl == null) {
                    prl = new PermohonanRujukanLuar();
                    InfoAudit ia2 = new InfoAudit();
                    ia2.setDimasukOleh(pengguna);
                    ia2.setTarikhMasuk(new Date());
                    prl.setInfoAudit(ia2);

                } else {
                    InfoAudit ia2 = prl.getInfoAudit();
                    ia2.setDiKemaskiniOleh(pengguna);
                    ia2.setTarikhKemaskini(new Date());
                    prl.setInfoAudit(ia2);
                }

                ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());

                prl.setPermohonan(permohonan);
                prl.setKodRujukan(kodRujukanDAO.findById("FL"));
                prl.setCawangan(pengguna.getKodCawangan());
                prl.setHakmilik(hp.getHakmilik());
//                if (permohonan.getKodUrusan().getKod().equals("KVSPC")
//                        && prl.getTarikhTamat() == null) {                
//                    Calendar tarikhTamat = Calendar.getInstance(); 
//                    tarikhTamat.add(Calendar.MONTH, 2);
//                    prl.setTarikhTamat(tarikhTamat.getTime());
//                    prl.setTarikhKuatkuasa(Calendar.getInstance().getTime());
//                    prl.setTempohBulan(2);
//                }
                notis.setWarta(prl);
                permohonanRujukanLuarServices.saveOrUpdate(prl);

                notis.setDokumenNotis(dokumen);
                notis.setPermohonan(permohonan);
                notis.setJabatan(permohonan.getKodUrusan().getJabatan());
                notis.setKodNotis(kodNotis);
                notis.setTarikhNotis(new Date());
                notis.setInfoAudit(ia);
                notisService.saveOrUpdate(notis);

            } else {
                for (Notis notis : senaraiNotis) {
                    if (notis == null || notis.getWarta() == null) {
                        continue;
                    }
                    if (!notis.getWarta().getHakmilik().getIdHakmilik().equals(hp.getHakmilik().getIdHakmilik())) {
                        continue;
                    }
                    ia = notis.getInfoAudit();
                    ia.setTarikhKemaskini(new Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    PermohonanRujukanLuar prl = notis.getWarta();
//                    if (prl != null) {
//                        if (permohonan.getKodUrusan().getKod().equals("KVSPC") &&
//                                prl.getTarikhTamat() == null) {
//                            Calendar tarikhTamat = Calendar.getInstance();
//                            tarikhTamat.add(Calendar.MONTH, 2);
//                            tarikhTamat.add(Calendar.DATE, -1);
//                            prl.setTarikhTamat(tarikhTamat.getTime());
//                            prl.setTarikhKuatkuasa(Calendar.getInstance().getTime());
//                            prl.setTempohBulan(2);
//                        }
//                        permohonanRujukanLuarServices.saveOrUpdate(prl);                
//                    }

                    notis.setDokumenNotis(dokumen);
                    notis.setPermohonan(permohonan);
                    notis.setJabatan(permohonan.getKodUrusan().getJabatan());
                    notis.setKodNotis(kodNotis);
                    notis.setTarikhNotis(new Date());
                    notis.setInfoAudit(ia);
                    notisService.saveOrUpdate(notis);

                }
            }
        }
    }

    //Added by Aizuddin to generate Notis
    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        permohonan = permohonanDAO.findById(id);
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            //no versi vdoc akan naik semasa push back
//            Double noVersi = Double.parseDouble(doc.getNoVersi());
//            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        LOG.debug("-----------TAJUK di PendaftaranValidation saveOrUpdateDokumen-------------");
        doc.setTajuk(permohonan.getKodUrusan().getKod() + "-" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf2 = kandunganFolderService.findByDokumen(doc, fd);
        if (kf2 == null) {
            kf2 = new KandunganFolder();
        } else {
            ia = kf2.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf2.setInfoAudit(ia);
        kf2.setFolder(fd);
        kf2.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf2);

        return doc;
    }

    private Dokumen generateGeran(String dokumenPath, FolderDokumen folder,
            KodDokumen kodDokumen, String namaReport, String[] params, String[] values, String id, String noRujukan) {

        Dokumen geran = null;
        List<KandunganFolder> senaraiKandungan = folder.getSenaraiKandungan();
        for (KandunganFolder kf : senaraiKandungan) {
            if (kf == null || kf.getDokumen() == null) {
                continue;
            }
            Dokumen d = kf.getDokumen();
            if (d.getKodDokumen() == null || d.getDalamanNilai1() == null) {
                continue;
            }

            if (d.getKodDokumen().getKod().equals(kodDokumen.getKod())
                    && d.getDalamanNilai1().equals(noRujukan)) {
                geran = d;
                File sign = new File(dokumenPath + d.getNamaFizikal() + ".sig");
                if (sign.exists()) {
                    sign.delete();
                }
                break;
            }

        }

        if (geran == null) {
            geran = saveDokumen(folder, kodDokumen, id, noRujukan);
        }

        String path = folder.getFolderId() + File.separator + String.valueOf(geran.getIdDokumen());
        path = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + path;

        reportUtil.generateReport(namaReport, params, values, path, pengguna);

//        try {
//            String path2 = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + reportUtil.getDMSPath();
//            PdfToImageExtractor pdfToImage = new PdfToImageExtractor("jpg",  String.valueOf(geran.getIdDokumen()), path2);
//            File imageDir = new File(pdfToImage.getOutputPath());
//            ImageToPdfConvertor con = new ImageToPdfConvertor(imageDir, pdfToImage.getOutputPath(), String.valueOf(geran.getIdDokumen()));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        updatePathDokumen(reportUtil.getDMSPath(), geran.getIdDokumen(), reportUtil.getContentType());
        return geran;
    }

    private Dokumen saveDokumen(FolderDokumen fd, KodDokumen kodDokumen, String id, String noRujukan) {

        LOG.debug("tajuk :" + kodDokumen.getKod());

        KodKlasifikasi SULIT = kodKlasifikasiDAO.findById(3);
        Dokumen geran = new Dokumen();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        geran.setBaru('Y');
        geran.setInfoAudit(ia);
        geran.setKlasifikasi(SULIT);
        geran.setTajuk(kodDokumen.getKod() + "-" + noRujukan);
        geran.setKodDokumen(kodDokumen);
        geran.setNoVersi("1");
        geran.setFormat("application/pdf");
        geran.setDalamanNilai1(noRujukan);
//        geran.setNoRujukan(noRujukan);
        dokumenService.saveOrUpdate(geran);

        KandunganFolder kf = new KandunganFolder();
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(geran);

        dokumenService.saveKandunganWithDokumen(kf);

        return geran;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        dokumenService.saveOrUpdate(d);
    }

    private void doUpdatePermohonanPihakUrusanLama(Permohonan permohonan, InfoAudit info) {

        List<PermohonanAtasPerserahan> senaraiUrusan = permohonan.getSenaraiPermohonanAtasPerserahan();
        List<PermohonanPihak> senaraiPihakToUpdate = new ArrayList<PermohonanPihak>();

        int bil = permohonanPihakService
                .getBilGroupByHakmilik(permohonan.getIdPermohonan()).intValue();
        if (info != null) {
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new Date());
        }

        for (PermohonanAtasPerserahan urusan : senaraiUrusan) {
            if (urusan == null) {
                continue;
            }
            HakmilikUrusan hakmilikUrusan = urusan.getUrusan();

            if (hakmilikUrusan != null) {
                Permohonan lama = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                if (lama != null) {
                    List<PermohonanPihak> senaraiPermohonanPihakLama = lama.getSenaraiPihak();
                    List<PermohonanPihak> senaraiPermohonanPihakBaru = permohonan.getSenaraiPihak();

                    for (PermohonanPihak p1 : senaraiPermohonanPihakLama) {
                        for (PermohonanPihak p2 : senaraiPermohonanPihakBaru) {
                            if (p1.getHakmilik().equals(p2.getHakmilik())) {
                                p1.setPihak(p2.getPihak());
                                p1.setNama(p2.getNama());
                                p1.setJenisPengenalan(p2.getJenisPengenalan());
                                p1.setNoPengenalan(p2.getNoPengenalan());
                                p1.setAlamat(p2.getAlamat());
                                p1.setWargaNegara(p2.getWargaNegara());
                                p1.setUmur(p2.getUmur());
                                p1.setInfoAudit(info);
                                senaraiPihakToUpdate.add(p1);
                            }
                        }
                    }
                }
            }
        }

        if (!senaraiPihakToUpdate.isEmpty()) {
            permohonanPihakService.saveOrUpdate(senaraiPihakToUpdate);
        }
    }

    private void updateInfoPihak(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("UpdateInfoPihak :: started");

        List<PermohonanPihakKemaskini> li = permohonan.getSenaraiPihakKemaskini();
//            List<Pemohon> pemohon = syerService.findPemohonByPermohonan(permohonan);
        List<Pemohon> pemohon = permohonan.getSenaraiPemohon();
        for (Pemohon pemohon1 : pemohon) {
            String idPihak = String.valueOf(pemohon1.getPihak().getIdPihak());
            HakmilikPihakBerkepentingan hp = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihakPMPPMG(pemohon1.getPihak(), pemohon1.getHakmilik(), pemohon1.getJenis().getKod());
            Pihak ph = pihakService.findById(idPihak);
            Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(permohonan, ph);
            List<HakmilikWaris> senaraiWarisSave = new ArrayList<HakmilikWaris>();

            LOG.info("hakmilik pihak =:" + hp);
//            LOG.info("hakmilik pihak =:" + hp.getJenis());

            if (hp != null) {
//                if ((hp.getJenis().getKod() == "PA") || (hp.getJenis().getKod() == "PP")) {
                List<HakmilikWaris> senaraiWaris1 = hp.getSenaraiWaris();

                if (senaraiWaris1 != null) {
                    List<HakmilikWaris> senaraiWaris = hp.getSenaraiWaris();
                    if (senaraiWaris != null) {
                        boolean warisEdit = false;
                        for (HakmilikWaris waris : senaraiWaris) {
                            if (waris == null) {
                                continue;
                            }
                            for (PermohonanPihakKemaskini kk : li) {
                                if (kk.getWarisTerlibat() == null) {
                                    continue;
                                }
                                if (kk.getWarisTerlibat().getIdWaris() == waris.getIdWaris()) {

                                    if (kk.getNamaMedan().equals("nama")) {
                                        warisEdit = true;
                                        waris.setNama(kk.getNilaiBaru());
                                    }
                                    if (kk.getNamaMedan().equals("nokp")) {
                                        warisEdit = true;
                                        waris.setNoPengenalan(kk.getNilaiBaru());
                                    }
                                    if (kk.getNamaMedan().equals("jeniskp")) {
                                        warisEdit = true;
                                        if (kk.getNilaiBaru() != null) {
                                            KodJenisPengenalan jenis = kodJenisPengenalanDAO.findById(kk.getNilaiBaru());
                                            waris.setJenisPengenalan(jenis);
                                        }
                                    }
                                    if (kk.getNamaMedan().equals("kodWarganegara")) {
                                        warisEdit = true;
                                        KodWarganegara kw = kodWarganegaraDAO.findById(kk.getNilaiBaru());
                                        waris.setWargaNegara(kw);
                                    }
                                }
                            }

                            if (warisEdit) {
                                senaraiWarisSave.add(waris);
                            }
                        }
                    }
                }
//                }
            }

            for (PermohonanPihakKemaskini permohonanPihakKemaskini : li) {
                if (permohonanPihakKemaskini.getPemohon() == null) {
                    continue;
                }
//                    LOG.debug("masuk for loop permohonanPihakKemaskini");
                if (permohonanPihakKemaskini.getNamaMedan().equals("nama")
                        && StringUtils.isNotBlank(permohonanPihakKemaskini.getNilaiBaru())) {
                    LOG.debug("masuk if nama");
                    ph.setNama(permohonanPihakKemaskini.getNilaiBaru());
                    if (hp != null) {
                        hp.setNama(permohonanPihakKemaskini.getNilaiBaru());
                    }
                    pmohon.setNama(permohonanPihakKemaskini.getNilaiBaru());
                }
                if (permohonanPihakKemaskini.getNamaMedan().equals("nokp")
                        && StringUtils.isNotBlank(permohonanPihakKemaskini.getNilaiBaru())) {
                    LOG.debug("masuk if nokp");
                    ph.setNoPengenalan(permohonanPihakKemaskini.getNilaiBaru());
                    if (hp != null) {
                        hp.setNoPengenalan(permohonanPihakKemaskini.getNilaiBaru());
                    }
                    pmohon.setNoPengenalan(permohonanPihakKemaskini.getNilaiBaru());
                }
                if (permohonanPihakKemaskini.getNamaMedan().equals("jeniskp")
                        && StringUtils.isNotBlank(permohonanPihakKemaskini.getNilaiBaru())) {
                    LOG.debug("masuk if jenisKP");
                    KodJenisPengenalan jenis = kodJenisPengenalanDAO.findById(permohonanPihakKemaskini.getNilaiBaru());
                    ph.setJenisPengenalan(jenis);
                    if (hp != null) {
                        hp.setJenisPengenalan(jenis);
                    }
                    pmohon.setJenisPengenalan(jenis);
                }
                if (permohonanPihakKemaskini.getNamaMedan().equals("kodWarganegara")
                        && StringUtils.isNotBlank(permohonanPihakKemaskini.getNilaiBaru())) {
                    LOG.debug("masuk if kodWarganegara");
                    KodWarganegara kw = kodWarganegaraDAO.findById(permohonanPihakKemaskini.getNilaiBaru());
                    ph.setWargaNegara(kw);
                    if (hp != null) {
                        hp.setWargaNegara(kw);
                    }
                    pmohon.setWargaNegara(kw);
                }
                if (permohonanPihakKemaskini.getNamaMedan().equals("rumahAlamat1")) {
                    LOG.debug("masuk if alamat1");
                    ph.setAlamat1(permohonanPihakKemaskini.getNilaiBaru());
                    if (hp != null) {
                        hp.setAlamat1(permohonanPihakKemaskini.getNilaiBaru());
                    }
                }
                if (permohonanPihakKemaskini.getNamaMedan().equals("rumahAlamat2")) {
                    LOG.debug("masuk if alamat2");

                    ph.setAlamat2(permohonanPihakKemaskini.getNilaiBaru());
                    if (hp != null) {
                        hp.setAlamat2(permohonanPihakKemaskini.getNilaiBaru());
                    }
                }
                if (permohonanPihakKemaskini.getNamaMedan().equals("rumahAlamat3")) {
                    LOG.debug("masuk if alamat3");
                    ph.setAlamat3(permohonanPihakKemaskini.getNilaiBaru());
                    if (hp != null) {
                        hp.setAlamat3(permohonanPihakKemaskini.getNilaiBaru());
                    }
                }
                if (permohonanPihakKemaskini.getNamaMedan().equals("rumahAlamat4")) {
                    LOG.debug("masuk if alamat4");
                    ph.setAlamat4(permohonanPihakKemaskini.getNilaiBaru());
                    if (hp != null) {
                        hp.setAlamat4(permohonanPihakKemaskini.getNilaiBaru());
                    }
                }
                if (permohonanPihakKemaskini.getNamaMedan().equals("rumahPoskod")) {
                    LOG.debug("masuk if rumahPoskod");
                    ph.setPoskod(permohonanPihakKemaskini.getNilaiBaru());
                    if (hp != null) {
                        hp.setPoskod(permohonanPihakKemaskini.getNilaiBaru());
                    }
                }
                if (permohonanPihakKemaskini.getNamaMedan().equals("rumahNegeri.kod")) {
                    LOG.debug("masuk if rumahNegeri");

                    if (permohonanPihakKemaskini.getNilaiBaru() != null) {
                        KodNegeri kn = kodNegeriDAO.findById(permohonanPihakKemaskini.getNilaiBaru());
                        if (kn != null) {
                            ph.setNegeri(kn);
                            if (hp != null) {
                                hp.setNegeri(kn);
                            }
                        }
                    } else {
                        ph.setNegeri(null);
                        hp.setNegeri(null);
                    }

                }
                LOG.info("permohon.getkodUrusan = " + permohonan.getKodUrusan().getKod());
                LOG.info("permohon.getkodUrusan = " + permohonan.getKodUrusan().getNama());
//                if (permohonan.getKodUrusan().getKod() == "TN") {

            }
            info.setDimasukOleh(info.getDimasukOleh());
            info.setTarikhMasuk(new java.util.Date());
            ph.setInfoAudit(info);
            if (hp != null) {
                hp.setInfoAudit(info);
            }
            pmohon.setInfoAudit(info);
            pihakService.saveOrUpdate(ph);
            if (hp != null) {
                hakmilikPihakKepentinganService.saveProvidedConn(hp);
            }
            pemohonService.saveOrUpdate(pmohon);

            if (!senaraiWarisSave.isEmpty()) {
                hakmilikWarisService.save(senaraiWarisSave);
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("TN")) {
            List<PermohonanAtasPerserahan> senaraiMAP = permohonanAtasPerserahanService.findSenaraiMohonAtasUrusanByIDPermohonan(permohonan.getIdPermohonan());
            LOG.info("senaraiMAP.size() =" + senaraiMAP.size());
            if (senaraiMAP.size() > 0) {
                for (PermohonanAtasPerserahan Map : senaraiMAP) {
                    LOG.info("Map =" + Map.getPermohonan().getIdPermohonan());
                    idMohonLama = new ArrayList<String>();
                    idMohonLama.add(Map.getPermohonanBaru().getIdPermohonan());
                }
                List<PermohonanPihakKemaskini> SenaraiPermohonanPihakKemaskini = permohonan.getSenaraiPihakKemaskini();
                LOG.info("SenaraiPermohonanPihakKemaskini.size = " + SenaraiPermohonanPihakKemaskini.size());
                for (PermohonanPihakKemaskini mohonKemasKini : SenaraiPermohonanPihakKemaskini) {
                    LOG.info("mohonKemasKini.getIdPermohonanLama() = " + mohonKemasKini.getIdPermohonanLama());
                    if (mohonKemasKini.getIdPermohonanLama() != null) {
                        if (mohonKemasKini.getPihak() != null) {
                            for (String idMLama : idMohonLama) {
                                Permohonan mohon = permohonanDAO.findById(idMLama);
                                LOG.info("mohon.getIdPermohonan() = " + mohon.getIdPermohonan());
                                LOG.info("mohonKemasKini.getPihak() = " + mohonKemasKini.getPihak().getIdPihak());
                                Pemohon permohonLama = pemohonService.findPemohonByPermohonanPihak(mohon, mohonKemasKini.getPihak());
//                                PermohonanAtasPerserahan map = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonan(mohon.getIdPermohonan());
//                                LOG.info("id urusan  = " + map.getIdAtasUrusan());
                                HakmilikUrusan hakmilikUrusan = hakmilikUrusanService.findByIdPerserahan(idMLama);
                                String idHakmilik = hakmilikUrusan.getHakmilik().getIdHakmilik();
                                PermohonanPihak mohonPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(mohon.getIdPermohonan(), idHakmilik, mohonKemasKini.getPihak().getIdPihak());
                                List<PermohonanPihak> senaraimohonPihak = permohonanPihakService.getSenaraiPmohonPihakByIdMohonIdPihak(mohon.getIdPermohonan(), mohonKemasKini.getPihak().getIdPihak());
                                String nama1 = mohonKemasKini.getNamaMedan();
                                LOG.info("nama = " + nama1);
                                String jeniskp1 = "jeniskp";
                                String nokpLama1 = "nokpLama";
                                String kodWarganegaraLama1 = "kodWarganegaraLama";
                                senaraiMohonPihakKkini = permohonan.getSenaraiPihakKemaskini();

                                if (mohonKemasKini.getJenis().getKod().equals("PM")) {
                                    if (permohonLama != null) {
                                        for (PermohonanPihakKemaskini mohonPihakKkini : senaraiMohonPihakKkini) {
                                            if (mohonPihakKkini.getNamaMedan().equals("nama")) {
                                                permohonLama.setNama(mohonPihakKkini.getNilaiBaru());
                                            }
                                            if (mohonPihakKkini.getNamaMedan().equals("jeniskp") && StringUtils.isNotBlank(mohonPihakKkini.getNilaiBaru())) {
                                                KodJenisPengenalan kj = kodJenisPengenalanDAO.findById(mohonPihakKkini.getNilaiBaru());
                                                permohonLama.setJenisPengenalan(kj);

                                            }
                                            if (mohonPihakKkini.getNamaMedan().equals("nokpLama")) {
                                                permohonLama.setNoPengenalan(mohonPihakKkini.getNilaiBaru());
                                            }
                                            if (mohonPihakKkini.getNamaMedan().equals("kodWarganegaraLama")) {
                                                permohonLama.getWargaNegara().setKod(mohonPihakKkini.getNilaiBaru());
                                                permohonLama.setInfoAudit(info);
                                                pemohonService.saveOrUpdate(permohonLama);
                                            }
                                        }
                                    }

                                } else if (mohonKemasKini.getJenis().getKod().equals("PG")) {
                                    for (PermohonanPihak mhnphk : senaraimohonPihak) {
                                        Permohonan permohonanLama = permohonanDAO.findById(mohonKemasKini.getIdPermohonanLama());
                                        for (PermohonanPihakKemaskini mohonPihakKkini : senaraiMohonPihakKkini) {
                                            if (permohonanLama.getKodUrusan().getKod().equals("GD")) {
                                                if (mohonPihakKkini.getNamaMedan().equals("nama")) {
                                                    mhnphk.setNama(mohonPihakKkini.getNilaiBaru());
                                                }
                                                if (mohonPihakKkini.getNamaMedan().equals("jeniskp") && StringUtils.isNotBlank(mohonPihakKkini.getNilaiBaru())) {
                                                    KodJenisPengenalan kj = kodJenisPengenalanDAO.findById(mohonPihakKkini.getNilaiBaru());
                                                    mhnphk.setJenisPengenalan(kj);
                                                }
                                                if (mohonPihakKkini.getNamaMedan().equals("nokpLama")) {
                                                    mhnphk.setNoPengenalan(mohonPihakKkini.getNilaiBaru());
                                                }
                                                if (mohonPihakKkini.getNamaMedan().equals("kodWarganegaraLama")) {
                                                    mhnphk.getWargaNegara().setKod(mohonPihakKkini.getNilaiBaru());
                                                }
                                            }
                                            if (permohonanLama.getKodUrusan().getKod().equals("GD")) {
                                                info.setDiKemaskiniOleh(pengguna);
                                                info.setTarikhKemaskini(new java.util.Date());
                                                mhnphk.setInfoAudit(info);
                                                permohonanPihakService.saveOrUpdate(mhnphk);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void updatePihakNoBahagian(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("UpdatePihakNoBahagian :: start");

        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        Fraction f = Fraction.ZERO;
//            Hakmilik hk = null;
//            if (li.size() > 0) {
//                hk = li.get(0).getHakmilik();
//            }
//            HakmilikUrusan hu = hakmilikService.findByIdPerserahan(permohonan.getIdPermohonan());
//            List<PermohonanPihak> list = permohonanPihakService.getSenaraiPmohonPihak(permohonan.getIdPermohonan());
        List<PermohonanPihak> list = permohonan.getSenaraiPihak();
        List<Pemohon> pemohon = permohonan.getSenaraiPemohon();
        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();

        for (HakmilikPermohonan hp : li) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hk = hp.getHakmilik();
            HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());

            for (PermohonanPihak p : list) {
                if (p.getHakmilik() == null
                        || !p.getHakmilik().getIdHakmilik().equals(hk.getIdHakmilik())) {
                    continue;
                }
                Pihak pihak = p.getPihak();
                HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();

                hpk.setJenis(p.getJenis());
                if (p.getJenis().getNama().contains("Kaveat")) {
                    hpk.setKaveatAktif('Y');
                } else {
                    hpk.setKaveatAktif('T');
                }
                hpk.setCawangan(hk.getCawangan());
                hpk.setPihak(pihak);
                hpk.setHakmilik(hk);
                hpk.setAktif('Y');
                hpk.setPerserahan(hu);
                hpk.setInfoAudit(info);
                hpk.setSyerPembilang(1);
                hpk.setSyerPenyebut(1);
                hpk.setJumlahPembilang(1);
                hpk.setJumlahPenyebut(1);
                hpk.setNama(p.getNama());
                hpk.setJenisPengenalan(p.getJenisPengenalan());
                hpk.setNoPengenalan(p.getNoPengenalan());
                hpk.setAlamat1(p.getAlamat().getAlamat1());
                hpk.setAlamat2(p.getAlamat().getAlamat2());
                hpk.setAlamat3(p.getAlamat().getAlamat3());
                hpk.setAlamat4(p.getAlamat().getAlamat4());
                hpk.setPoskod(p.getAlamat().getPoskod());
                hpk.setNegeri(p.getAlamat().getNegeri());

                hakmilikPihakBerkepentinganList.add(hpk);
            }

            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new Date());
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            hakmilikPihakKepentinganService.save(hakmilikPihakBerkepentinganList, hk, permohonan, info);

        }
        LOG.info("UpdatePihakNoBahagian :: finish");
    }

    private void insertPihakIsmen(Permohonan permohonan, InfoAudit info) {

        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        List<PermohonanPihak> list = permohonan.getSenaraiPihak();

        List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();

        for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilikTerlibat) {
            if (hakmilikPermohonan == null
                    || hakmilikPermohonan.getHakmilik() == null
                    || hakmilikPermohonan.getHubunganHakmilik() == null) {
                continue;
            }

            if (hakmilikPermohonan.getHubunganHakmilik().getKod().equals("LT")) {
                Hakmilik hk = hakmilikPermohonan.getHakmilik();
                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                if (hu == null) {
                    continue;
                }

                for (PermohonanPihak pp : list) {
                    if (pp == null || pp.getHakmilik().getIdHakmilik() == null
                            || !hk.getIdHakmilik().equals(pp.getHakmilik().getIdHakmilik())) {
                        continue;
                    }
                    Pihak pihak = pp.getPihak();
                    HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
                    hpk.setKaveatAktif('T');
                    hpk.setJenis(pp.getJenis());
                    hpk.setPihak(pp.getPihak());
                    hpk.setWargaNegara(pp.getWargaNegara());
                    hpk.setHakmilik(hk);
                    hpk.setCawangan(hk.getCawangan());
                    hpk.setAktif('Y');
                    hpk.setPerserahan(hu);
                    hpk.setSyerPembilang(pp.getSyerPembilang());
                    hpk.setSyerPenyebut(pp.getSyerPenyebut());
                    hpk.setJumlahPembilang(pp.getSyerPembilang());
                    hpk.setJumlahPenyebut(pp.getSyerPenyebut());
                    hpk.setInfoAudit(info);
                    hpk.setNama(pp.getNama());
                    hpk.setJenisPengenalan(pp.getJenisPengenalan());
                    hpk.setNoPengenalan(pp.getNoPengenalan());
                    hpk.setAlamat1(pp.getAlamat().getAlamat1());
                    hpk.setAlamat2(pp.getAlamat().getAlamat2());
                    hpk.setAlamat3(pp.getAlamat().getAlamat3());
                    hpk.setAlamat4(pp.getAlamat().getAlamat4());
                    hpk.setPoskod(pp.getAlamat().getPoskod());
                    hpk.setNegeri(pp.getAlamat().getNegeri());
                    hakmilikPihakBerkepentinganList.add(hpk);
                }
            }
        }
        hakmilikPihakKepentinganService.save(hakmilikPihakBerkepentinganList);
    }

    private void insertPihak(Permohonan permohonan, InfoAudit info) throws Exception {

        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        List<PermohonanPihak> list = permohonan.getSenaraiPihak();
        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        List<Pemohon> pemohon = permohonan.getSenaraiPemohon();

        KodUrusan ku = permohonan.getKodUrusan();
//        Hakmilik hk = null;
//        if (li.size() > 0) {
//            hk = li.get(0).getHakmilik();
//        }
        if (!permohonan.getKodUrusan().getKod().equals("PHMM")) {
            for (HakmilikPermohonan hakmilikPermohonan : li) {
                if (hakmilikPermohonan == null) {
                    continue;
                }
                Hakmilik hk = hakmilikPermohonan.getHakmilik();

                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                if (hu == null) {
                    continue;
                }

                Pihak pihakKongsi = null;

                for (PermohonanPihak pp : list) {
                    if (pp == null || pp.getHakmilik() == null) {
                        continue;
                    }
                    if (!pp.getHakmilik().getIdHakmilik().equals(hk.getIdHakmilik())) {
                        continue;
                    }
                    HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                    if (permohonan.getKodUrusan().getKod().startsWith("KV")) {
                        hp.setKaveatAktif('Y');
                    } else {
                        hp.setKaveatAktif('T');
                    }
                    hp.setJenis(pp.getJenis());
                    if (pp.getSyerBersama() != null && pp.getSyerBersama() == 'Y') {
                        if (pihakKongsi == null) {
                            pihakKongsi = pp.getPihak();
                        }
                        hp.setPihakKongsiBersama(pihakKongsi);
                    }
                    if (pp.getPihak() != null) {
                        hp.setPihak(pp.getPihak());
                    }
                    if (pp.getWargaNegara() != null) {
                        hp.setWargaNegara(pp.getWargaNegara());
                    }
                    hp.setHakmilik(hk);
                    hp.setAktif('Y');
                    hp.setCawangan(hk.getCawangan());
                    hp.setPerserahan(hu);
                    if (pp.getSyerPembilang() != null) {
                        hp.setSyerPembilang(pp.getSyerPembilang());
                    }
                    if (pp.getSyerPenyebut() != null) {
                        hp.setSyerPenyebut(pp.getSyerPenyebut());
                    }
                    if (pp.getSyerPembilang() != null) {
                        hp.setJumlahPembilang(pp.getSyerPembilang());
                    }
                    if (pp.getSyerPenyebut() != null) {
                        hp.setJumlahPenyebut(pp.getSyerPenyebut());
                    }
                    hp.setInfoAudit(info);
                    hp.setNama(pp.getNama());
                    if (pp.getJenisPengenalan() != null) {
                        hp.setJenisPengenalan(pp.getJenisPengenalan());
                    }
                    if (pp.getNoPengenalan() != null) {
                        hp.setNoPengenalan(pp.getNoPengenalan());
                    }
                    if (pp.getAlamat() != null) {
                        if (pp.getAlamat().getAlamat1() != null) {
                            hp.setAlamat1(pp.getAlamat().getAlamat1());
                        }
                        if (pp.getAlamat().getAlamat2() != null) {
                            hp.setAlamat2(pp.getAlamat().getAlamat2());
                        }
                        if (pp.getAlamat().getAlamat3() != null) {
                            hp.setAlamat3(pp.getAlamat().getAlamat3());
                        }
                        if (pp.getAlamat().getAlamat4() != null) {
                            hp.setAlamat4(pp.getAlamat().getAlamat4());
                        }
                        if (pp.getAlamat().getPoskod() != null) {
                            hp.setPoskod(pp.getAlamat().getPoskod());
                        }
                        if (pp.getAlamat().getNegeri() != null) {
                            hp.setNegeri(pp.getAlamat().getNegeri());
                        }
                    }
                    hp.setPenubuhanSyarikat(pp.getPenubuhanSyarikat());
                    hakmilikPihakBerkepentinganList.add(hp);
                }
            }
        } else if (permohonan.getKodUrusan().getKod().equals("PHMM")) {
            for (HakmilikPermohonan hakmilikPermohonan : li) {
                if (hakmilikPermohonan == null) {
                    continue;
                }
                Hakmilik hk = hakmilikPermohonan.getHakmilik();

                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                if (hu == null) {
                    continue;
                }

                Pihak pihakKongsi = null;

//                for (PermohonanPihak pp : list) {
//                    if (pp == null || pp.getHakmilik() == null) {
//                        continue;
//                    }
//                    if (!pp.getHakmilik().getIdHakmilik().equals(hk.getIdHakmilik())) {
//                        continue;
//                    }
                for (Pemohon phmn : pemohon) {
                    List<Pemohon> senaraiPemohonBelumWujud = new ArrayList<Pemohon>();
                    List<Pemohon> senaraiPemohonWujud = new ArrayList<Pemohon>();
                    if (phmn.getHakmilikPihak() != null) {
                        senaraiPemohonWujud.add(phmn);
                    } else {
                        senaraiPemohonBelumWujud.add(phmn);
                    }
                    if (senaraiPemohonWujud.size() > 0) {
                        for (Pemohon phmnWujud : senaraiPemohonWujud) {
                            Long idHpWujud = phmnWujud.getHakmilikPihak().getIdHakmilikPihakBerkepentingan();
                            HakmilikPihakBerkepentingan hakmilikPihakWujud = hakmilikPihakKepentinganService.findById(String.valueOf(idHpWujud));
                            hakmilikPihakWujud.setAktif('T');
                        }
                    } else if (senaraiPemohonBelumWujud.size() > 0) {
                        for (Pemohon phmnBelumWjud : senaraiPemohonBelumWujud) {
                            HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                            hp.setJenis(phmnBelumWjud.getJenis());
//                            if (pp.getSyerBersama() != null && phmnBelumWjud.getSyerBersama() == 'Y') {
//                                if (pihakKongsi == null) {
//                                    pihakKongsi = phmnBelumWjud.getPihak();
//                                }
//                                hp.setPihakKongsiBersama(pihakKongsi);
//                            }
                            hp.setPihak(phmnBelumWjud.getPihak());
                            hp.setWargaNegara(phmnBelumWjud.getWargaNegara());
                            hp.setHakmilik(hk);
                            hp.setAktif('T');
                            hp.setCawangan(hk.getCawangan());
                            hp.setPerserahan(hu);
                            hp.setSyerPembilang(phmnBelumWjud.getSyerPembilang());
                            hp.setSyerPenyebut(phmnBelumWjud.getSyerPenyebut());
                            hp.setJumlahPembilang(phmnBelumWjud.getSyerPembilang());
                            hp.setJumlahPenyebut(phmnBelumWjud.getSyerPenyebut());
                            hp.setInfoAudit(info);
                            hp.setNama(phmnBelumWjud.getNama());
                            hp.setJenisPengenalan(phmnBelumWjud.getJenisPengenalan());
                            hp.setNoPengenalan(phmnBelumWjud.getNoPengenalan());
                            hp.setAlamat1(phmnBelumWjud.getAlamat().getAlamat1());
                            hp.setAlamat2(phmnBelumWjud.getAlamat().getAlamat2());
                            hp.setAlamat3(phmnBelumWjud.getAlamat().getAlamat3());
                            hp.setAlamat4(phmnBelumWjud.getAlamat().getAlamat4());
                            hp.setPoskod(phmnBelumWjud.getAlamat().getPoskod());
                            hp.setNegeri(phmnBelumWjud.getAlamat().getNegeri());
                            hp.setPenubuhanSyarikat(phmnBelumWjud.getPenubuhanSyarikat());
                            hakmilikPihakBerkepentinganList.add(hp);
                        }
                    }
                }
            }
            if (list.get(0).getPermohonan().getIdKumpulan() != null) {
                List<Permohonan> senaraiPermohonan = permohonanService.findByIdKumpByKodrusanPHMM(list.get(0).getPermohonan().getIdKumpulan());
                String PHMMbaru = senaraiPermohonan.get(0).getIdPermohonan();
                List<PermohonanPihak> senaraiMohonPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(PHMMbaru);
                Pihak pihakKongsi = null;
                for (PermohonanPihak mohonPihak : senaraiMohonPihak) {
                    Hakmilik hk = mohonPihak.getHakmilik();
                    HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                    HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                    hp.setJenis(mohonPihak.getJenis());
                    if (mohonPihak.getSyerBersama() != null && mohonPihak.getSyerBersama() == 'Y') {
                        if (pihakKongsi == null) {
                            pihakKongsi = mohonPihak.getPihak();
                        }
                        hp.setPihakKongsiBersama(pihakKongsi);
                    }
                    hp.setPihak(mohonPihak.getPihak());
                    hp.setWargaNegara(mohonPihak.getWargaNegara());
                    hp.setHakmilik(hk);
                    hp.setAktif('T');
                    hp.setCawangan(hk.getCawangan());
                    hp.setPerserahan(hu);
                    hp.setSyerPembilang(mohonPihak.getSyerPembilang());
                    hp.setSyerPenyebut(mohonPihak.getSyerPenyebut());
                    hp.setJumlahPembilang(mohonPihak.getSyerPembilang());
                    hp.setJumlahPenyebut(mohonPihak.getSyerPenyebut());
                    hp.setInfoAudit(info);
                    hp.setNama(mohonPihak.getNama());
                    hp.setJenisPengenalan(mohonPihak.getJenisPengenalan());
                    hp.setNoPengenalan(mohonPihak.getNoPengenalan());
                    hp.setAlamat1(mohonPihak.getAlamat().getAlamat1());
                    hp.setAlamat2(mohonPihak.getAlamat().getAlamat2());
                    hp.setAlamat3(mohonPihak.getAlamat().getAlamat3());
                    hp.setAlamat4(mohonPihak.getAlamat().getAlamat4());
                    hp.setPoskod(mohonPihak.getAlamat().getPoskod());
                    hp.setNegeri(mohonPihak.getAlamat().getNegeri());
                    hp.setPenubuhanSyarikat(mohonPihak.getPenubuhanSyarikat());
                    hakmilikPihakBerkepentinganList.add(hp);
                }
            } else if (list.get(0).getPermohonan().getIdKumpulan() == null) {
                if (permohonan.getKodUrusan().getKod().equals("PHMM")) {
                    Pihak pihakKongsi = null;
                    List<PermohonanPihak> senaraiMohonPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(permohonan.getIdPermohonan());
                    for (PermohonanPihak mohonPihak : senaraiMohonPihak) {
                        Hakmilik hk = mohonPihak.getHakmilik();
                        HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                        HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                        hp.setJenis(mohonPihak.getJenis());
                        if (mohonPihak.getSyerBersama() != null && mohonPihak.getSyerBersama() == 'Y') {
                            if (pihakKongsi == null) {
                                pihakKongsi = mohonPihak.getPihak();
                            }
                            hp.setPihakKongsiBersama(pihakKongsi);
                        }
                        hp.setPihak(mohonPihak.getPihak());
                        hp.setWargaNegara(mohonPihak.getWargaNegara());
                        hp.setHakmilik(hk);
                        hp.setAktif('T');
                        hp.setCawangan(hk.getCawangan());
                        hp.setPerserahan(hu);
                        hp.setSyerPembilang(mohonPihak.getSyerPembilang());
                        hp.setSyerPenyebut(mohonPihak.getSyerPenyebut());
                        hp.setJumlahPembilang(mohonPihak.getSyerPembilang());
                        hp.setJumlahPenyebut(mohonPihak.getSyerPenyebut());
                        hp.setInfoAudit(info);
                        hp.setNama(mohonPihak.getNama());
                        hp.setJenisPengenalan(mohonPihak.getJenisPengenalan());
                        hp.setNoPengenalan(mohonPihak.getNoPengenalan());
                        hp.setAlamat1(mohonPihak.getAlamat().getAlamat1());
                        hp.setAlamat2(mohonPihak.getAlamat().getAlamat2());
                        hp.setAlamat3(mohonPihak.getAlamat().getAlamat3());
                        hp.setAlamat4(mohonPihak.getAlamat().getAlamat4());
                        hp.setPoskod(mohonPihak.getAlamat().getPoskod());
                        hp.setNegeri(mohonPihak.getAlamat().getNegeri());
                        hp.setPenubuhanSyarikat(mohonPihak.getPenubuhanSyarikat());
                        hakmilikPihakBerkepentinganList.add(hp);
                    }
                }
            }
        }
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new Date());

        if (permohonan.getKodUrusan().getKod().equals("PHMM")) {
            List<HakmilikPermohonan> senaraihakmilikPermohonan = regService.searchMohonHakmilikByIdMohon(permohonan.getIdPermohonan());
            for (HakmilikPermohonan hakmilikPermohon1 : senaraihakmilikPermohonan) {
                senaraiPermohonanPihakBaru = permohonanPihakService.getSenaraiPmohonPihakByIdMohonAndIdHakmilik(permohonan.getIdPermohonan(), hakmilikPermohon1.getHakmilik().getIdHakmilik());
                senaraiMohonHbgn = permohonanHubunganService.findMohonAtasUrusanByIDSumberAndIDHakmilik(permohonan.getIdPermohonan(), hakmilikPermohon1.getHakmilik().getIdHakmilik());
                for (PermohonanHubungan mohonHbgn : senaraiMohonHbgn) {
                    IdSasar = mohonHbgn.getPermohonanSasaran().getIdPermohonan();
                    for (PermohonanPihak mohonPihak : senaraiPermohonanPihakBaru) {
                        senaraiPermohonanPihakLama = permohonanPihakService.getSenaraiPmohonPihakByIdMohonAndIdHakmilik(IdSasar, hakmilikPermohon1.getHakmilik().getIdHakmilik());
                        for (PermohonanPihak mohonPihakLama : senaraiPermohonanPihakLama) {
                            mohonPihakLama.setNama(mohonPihak.getNama());
                            mohonPihakLama.setJenisPengenalan(mohonPihak.getJenisPengenalan());
                            mohonPihakLama.setNoPengenalan(mohonPihak.getNoPengenalan());
                            if (mohonPihak.getAlamat() != null) {
                                mohonPihakLama.setAlamat(mohonPihak.getAlamat());
                            }
                            mohonPihakLama.setPihak(mohonPihak.getPihak());
                            mohonPihakLama.setJenis(mohonPihak.getJenis());
                            mohonPihakLama.setHakmilik(mohonPihak.getHakmilik());
                            mohonPihakLama.setInfoAudit(ia);
                            permohonanPihakDAO.saveOrUpdate(mohonPihakLama);
                        }
//                    }
                    }
                }

            }
        }

        hakmilikPihakKepentinganService.save(hakmilikPihakBerkepentinganList);
    }

    private void insertPihak2(Permohonan permohonan, InfoAudit info) throws Exception {

        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();

        List<Pemohon> pemohon = permohonan.getSenaraiPemohon();
        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        List<PermohonanAtasPihakBerkepentingan> list = permohonanAtasPihakBerkepentinganService.findByPermohonanList(permohonan);
        List<PermohonanPihak> list2 = permohonan.getSenaraiPihak();
        if (!permohonan.getKodUrusan().getKod().equals("PHMM")) {
            for (HakmilikPermohonan hp : li) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hk = hp.getHakmilik();

                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                if (hu == null) {
                    continue;
                }

                for (PermohonanAtasPihakBerkepentingan pap : list) {
                    Hakmilik hm = pap.getHakmilik();
                    if (!hm.getIdHakmilik().equals(hk.getIdHakmilik())) {
                        continue;
                    }
                    HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
//                if (permohonan.getKodUrusan().getKod().startsWith("KV") && 
//                        !"KVSPC".equals(permohonan.getKodUrusan().getKod())) {
//                    hpk.setJenis(kodJenisPihakBerkepentinganDAO.findById("PKA"));
//                    hpk.setKaveatAktif('Y');
//                } 
//                else if (permohonan.getKodUrusan().getKod().equals("KVSPC")) {
//                    hpk.setJenis(kodJenisPihakBerkepentinganDAO.findById("PPK"));
//                    hpk.setKaveatAktif('T');
//                }else {
//                    hpk.setJenis(pap.getPihakBerkepentingan().getJenis());
//                    hpk.setKaveatAktif('T');
//                }
                    if (permohonan.getKodUrusan().getKod().startsWith("KV")
                            && !"KVSPC".equals(permohonan.getKodUrusan().getKod())) {
                        hpk.setKaveatAktif('Y');
                    } else {
                        hpk.setKaveatAktif('T');
                    }
                    hpk.setJenis(pap.getPihakBerkepentingan().getJenis());

                    hpk.setPihak(pap.getPihakBerkepentingan().getPihak());
                    hpk.setHakmilik(hk);
                    hpk.setCawangan(hk.getCawangan());
                    hpk.setAktif('Y');
                    hpk.setPerserahan(hu);
                    hpk.setNama(pap.getPihakBerkepentingan().getNama());
                    hpk.setJenisPengenalan(pap.getPihakBerkepentingan().getJenisPengenalan());
                    hpk.setNoPengenalan(pap.getPihakBerkepentingan().getNoPengenalan());
                    hpk.setAlamat1(pap.getPihakBerkepentingan().getAlamat1());
                    hpk.setAlamat2(pap.getPihakBerkepentingan().getAlamat2());
                    hpk.setAlamat3(pap.getPihakBerkepentingan().getAlamat3());
                    hpk.setAlamat4(pap.getPihakBerkepentingan().getAlamat4());
                    hpk.setPoskod(pap.getPihakBerkepentingan().getPoskod());
                    hpk.setNegeri(pap.getPihakBerkepentingan().getNegeri());
                    //TODO :: insert kaveat perserahan
//                    hpk.setPermohonan(permohonan);
                    hpk.setInfoAudit(info);
                    hakmilikPihakBerkepentinganList.add(hpk);
                }

                for (PermohonanPihak pp : list2) {
                    Hakmilik hm = pp.getHakmilik();
                    if (!hm.getIdHakmilik().equals(hk.getIdHakmilik())) {
                        continue;
                    }

                    HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
//                if (permohonan.getKodUrusan().getKod().startsWith("KV")) {
//                    hpk.setJenis(kodJenisPihakBerkepentinganDAO.findById("PKA"));
//                    hpk.setKaveatAktif('Y');
//                } else if (permohonan.getKodUrusan().getKod().equals("PNPA")) {
//                    hpk.setJenis(kodJenisPihakBerkepentinganDAO.findById("PA"));
//                    hpk.setKaveatAktif('T');
//                } else {
//                    hpk.setJenis(pp.getJenis());
//                    hpk.setKaveatAktif('T');
//                }
                    if (permohonan.getKodUrusan().getKod().startsWith("KV")) {
                        hpk.setKaveatAktif('Y');
                    } else {
                        hpk.setKaveatAktif('T');
                    }

                    hpk.setJenis(pp.getJenis());
                    hpk.setPihak(pp.getPihak());
                    hpk.setHakmilik(pp.getHakmilik());
                    hpk.setCawangan(hk.getCawangan());
                    hpk.setAktif('Y');
                    hpk.setPerserahan(hu);
                    hpk.setNama(pp.getNama());
                    hpk.setJenisPengenalan(pp.getJenisPengenalan());
                    hpk.setNoPengenalan(pp.getNoPengenalan());
                    if (pp.getAlamat() != null) {
                        hpk.setAlamat1(pp.getAlamat().getAlamat1());
                        hpk.setAlamat2(pp.getAlamat().getAlamat2());
                        hpk.setAlamat3(pp.getAlamat().getAlamat3());
                        hpk.setAlamat4(pp.getAlamat().getAlamat4());
                        hpk.setPoskod(pp.getAlamat().getPoskod());
                        hpk.setNegeri(pp.getAlamat().getNegeri());
                    }
                    //TODO :: insert kaveat perserahan
//                    hpk.setPermohonan(permohonan);
                    hpk.setPenubuhanSyarikat(pp.getPenubuhanSyarikat());
                    hpk.setInfoAudit(info);
                    hakmilikPihakBerkepentinganList.add(hpk);
                }

            }
            hakmilikPihakKepentinganService.save(hakmilikPihakBerkepentinganList);
        } else if (permohonan.getKodUrusan().getKod().equals("PHMM")) {
            for (HakmilikPermohonan hakmilikPermohonan : li) {
                if (hakmilikPermohonan == null) {
                    continue;
                }
                Hakmilik hk = hakmilikPermohonan.getHakmilik();

                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                if (hu == null) {
                    continue;
                }

                Pihak pihakKongsi = null;

//                for (PermohonanPihak pp : list) {
//                    if (pp == null || pp.getHakmilik() == null) {
//                        continue;
//                    }
//                    if (!pp.getHakmilik().getIdHakmilik().equals(hk.getIdHakmilik())) {
//                        continue;
//                    }
                for (Pemohon phmn : pemohon) {
                    List<Pemohon> senaraiPemohonBelumWujud = new ArrayList<Pemohon>();
                    List<Pemohon> senaraiPemohonWujud = new ArrayList<Pemohon>();
                    if (phmn.getHakmilikPihak() != null) {
                        senaraiPemohonWujud.add(phmn);
                    } else {
                        senaraiPemohonBelumWujud.add(phmn);
                    }
                    if (senaraiPemohonWujud.size() > 0) {
                        for (Pemohon phmnWujud : senaraiPemohonWujud) {
                            Long idHpWujud = phmnWujud.getHakmilikPihak().getIdHakmilikPihakBerkepentingan();
                            HakmilikPihakBerkepentingan hakmilikPihakWujud = hakmilikPihakKepentinganService.findById(String.valueOf(idHpWujud));
                            hakmilikPihakWujud.setAktif('T');
                        }
                    } else if (senaraiPemohonBelumWujud.size() > 0) {
                        for (Pemohon phmnBelumWjud : senaraiPemohonBelumWujud) {
                            HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                            hp.setJenis(phmnBelumWjud.getJenis());
//                            if (pp.getSyerBersama() != null && phmnBelumWjud.getSyerBersama() == 'Y') {
//                                if (pihakKongsi == null) {
//                                    pihakKongsi = phmnBelumWjud.getPihak();
//                                }
//                                hp.setPihakKongsiBersama(pihakKongsi);
//                            }
                            hp.setPihak(phmnBelumWjud.getPihak());
                            hp.setWargaNegara(phmnBelumWjud.getWargaNegara());
                            hp.setHakmilik(hk);
                            hp.setAktif('T');
                            hp.setCawangan(hk.getCawangan());
                            hp.setPerserahan(hu);
                            hp.setSyerPembilang(phmnBelumWjud.getSyerPembilang());
                            hp.setSyerPenyebut(phmnBelumWjud.getSyerPenyebut());
                            hp.setJumlahPembilang(phmnBelumWjud.getSyerPembilang());
                            hp.setJumlahPenyebut(phmnBelumWjud.getSyerPenyebut());
                            hp.setInfoAudit(info);
                            hp.setNama(phmnBelumWjud.getNama());
                            hp.setJenisPengenalan(phmnBelumWjud.getJenisPengenalan());
                            hp.setNoPengenalan(phmnBelumWjud.getNoPengenalan());
                            if (phmnBelumWjud.getAlamat() != null) {
                                if (phmnBelumWjud.getAlamat().getAlamat1() != null) {
                                    hp.setAlamat1(phmnBelumWjud.getAlamat().getAlamat1());
                                }
                                if (phmnBelumWjud.getAlamat().getAlamat2() != null) {
                                    hp.setAlamat2(phmnBelumWjud.getAlamat().getAlamat2());
                                }
                                if (phmnBelumWjud.getAlamat().getAlamat3() != null) {
                                    hp.setAlamat3(phmnBelumWjud.getAlamat().getAlamat3());
                                }
                                if (phmnBelumWjud.getAlamat().getAlamat4() != null) {
                                    hp.setAlamat4(phmnBelumWjud.getAlamat().getAlamat4());
                                }
                                if (phmnBelumWjud.getAlamat().getPoskod() != null) {
                                    hp.setPoskod(phmnBelumWjud.getAlamat().getPoskod());
                                }
                                if (phmnBelumWjud.getAlamat().getNegeri() != null) {
                                    hp.setNegeri(phmnBelumWjud.getAlamat().getNegeri());
                                }

                            }
                            hp.setPenubuhanSyarikat(phmnBelumWjud.getPenubuhanSyarikat());
                            hakmilikPihakBerkepentinganList.add(hp);
                        }
                    }
                }
            }
            List<Permohonan> senaraiPermohonan = permohonanService.findByIdKumpByKodrusanPHMM(list.get(0).getPermohonan().getIdKumpulan());
            String PHMMbaru = senaraiPermohonan.get(0).getIdPermohonan();
            LOG.info("PHMMbaru = " + PHMMbaru);
            List<PermohonanPihak> senaraiMohonPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(PHMMbaru);
            LOG.info("senaraiMohonPihak.size() = " + senaraiMohonPihak.size());
            Pihak pihakKongsi = null;
            for (PermohonanPihak mohonPihak : senaraiMohonPihak) {
                Hakmilik hk = mohonPihak.getHakmilik();
                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                hp.setJenis(mohonPihak.getJenis());
                if (mohonPihak.getSyerBersama() != null && mohonPihak.getSyerBersama() == 'Y') {
                    if (pihakKongsi == null) {
                        pihakKongsi = mohonPihak.getPihak();
                    }
                    hp.setPihakKongsiBersama(pihakKongsi);
                }
                hp.setPihak(mohonPihak.getPihak());
                hp.setWargaNegara(mohonPihak.getWargaNegara());
                hp.setHakmilik(hk);
                hp.setAktif('T');
                hp.setCawangan(hk.getCawangan());
                hp.setPerserahan(hu);
                hp.setSyerPembilang(mohonPihak.getSyerPembilang());
                hp.setSyerPenyebut(mohonPihak.getSyerPenyebut());
                hp.setJumlahPembilang(mohonPihak.getSyerPembilang());
                hp.setJumlahPenyebut(mohonPihak.getSyerPenyebut());
                hp.setInfoAudit(info);
                hp.setNama(mohonPihak.getNama());
                hp.setJenisPengenalan(mohonPihak.getJenisPengenalan());
                hp.setNoPengenalan(mohonPihak.getNoPengenalan());
                hp.setAlamat1(mohonPihak.getAlamat().getAlamat1());
                hp.setAlamat2(mohonPihak.getAlamat().getAlamat2());
                hp.setAlamat3(mohonPihak.getAlamat().getAlamat3());
                hp.setAlamat4(mohonPihak.getAlamat().getAlamat4());
                hp.setPoskod(mohonPihak.getAlamat().getPoskod());
                hp.setNegeri(mohonPihak.getAlamat().getNegeri());
                hp.setPenubuhanSyarikat(mohonPihak.getPenubuhanSyarikat());
                hakmilikPihakBerkepentinganList.add(hp);
            }
            hakmilikPihakKepentinganService.save(hakmilikPihakBerkepentinganList);
        }
    }

    private void updatePihak(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("UpdatePihak :: start");
        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        List<Pemohon> pemohon = permohonan.getSenaraiPemohon();

        KodUrusan ku = permohonan.getKodUrusan();
//            Fraction f = Fraction.ZERO;
//
//            //get portion from pemohon
////            HakmilikPihakBerkepentingan pmohon = null;
//            for (HakmilikPermohonan hp : li) {
//                if (hp == null) {
//                    continue;
//                }
//                Hakmilik hk = hp.getHakmilik();
//                for (Pemohon pemohon1 : pemohon) {
//                    HakmilikPihakBerkepentingan hm = syerService.findSyerPihakFromHakmilikPihak(pemohon1.getPihak().getIdPihak(),
//                            hk.getIdHakmilik());
//                    if (hm == null) {
//                        continue;
//                    }
////                pmohon = hm;
//                    Fraction p = new Fraction(hm.getSyerPembilang(), hm.getSyerPenyebut());
//                    f = f.add(p);
//                }
//            }

        List<PermohonanPihak> list = permohonan.getSenaraiPihak();
        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        List<Map<String, Object>> senaraiMap = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = null;
        HakmilikPihakBerkepentingan PA = null;

        for (HakmilikPermohonan hp : li) {
            if (hp == null) {
                continue;
            }

            Hakmilik hk = hp.getHakmilik();
            HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
            if (hu == null) {
                continue;
            }

            // this is new feature
            // to cater MH
            // to insert all pemohon to hakmilik_pihak
            KodJenisPihakBerkepentingan jenis = null;
            Pihak pihakKongsi = null;
            PA = new HakmilikPihakBerkepentingan();

            for (PermohonanPihak p : list) {
                if (p == null || p.getJenis().getKod().equals("WAR")) {
                    continue;
                }
                if (p.getHakmilik() == null
                        || !p.getHakmilik().getIdHakmilik().equals(hk.getIdHakmilik())) {
                    continue;
                }

                Pihak pihak = p.getPihak();
                HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(pihak, hk);
                //merge if pihak already exist
                //if not create new pihak
                if (hpk == null) {
                    LOG.debug("::pihak tidak wujud::");
                    hpk = new HakmilikPihakBerkepentingan();
                    hpk.setJenis(p.getJenis());
                    hpk.setWargaNegara(p.getWargaNegara());
                    hpk.setPihak(pihak);
                    hpk.setHakmilik(hk);
                    hpk.setCawangan(hk.getCawangan());
                    hpk.setAktif('Y');
                    hpk.setPerserahan(hu);
                    //TODO :: insert kaveat perserahan
//                    hpk.setPermohonan(permohonan);
                    hpk.setInfoAudit(info);
                    hpk.setSyerPembilang(p.getSyerPembilang());
                    hpk.setSyerPenyebut(p.getSyerPenyebut());
                    hpk.setJumlahPembilang(p.getSyerPembilang());
                    hpk.setJumlahPenyebut(p.getSyerPenyebut());
                    hpk.setPenubuhanSyarikat(p.getPenubuhanSyarikat());

                } else {
                    LOG.debug("::pihak wujud::");
                    Fraction nf = Fraction.ZERO;
                    Fraction af = Fraction.ZERO;
                    //if tuan tanah is pemohon + penerima syer
                    boolean flag = false;
                    for (Pemohon pemohon1 : pemohon) {
                        if (pemohon1.getPihak().getIdPihak() == pihak.getIdPihak()) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        //to make current pihak become new pihak
                        af = new Fraction(p.getSyerPembilang(), p.getSyerPenyebut());
                        hpk = new HakmilikPihakBerkepentingan();
                        hpk.setPihak(pihak);
                        hpk.setHakmilik(hk);
                        hpk.setCawangan(hk.getCawangan());
                        hpk.setAktif('Y');
                        hpk.setPerserahan(hu);
                    } else {
                        int c1 = hpk.getSyerPembilang(); //syer pembilang asal
                        int c2 = hpk.getSyerPenyebut();//syer penyebut asal
                        int n1 = p.getSyerPembilang(); //syer pembilang mohon
                        int n2 = p.getSyerPenyebut(); //syer penyebut mohon
                        // add current fraction with new fraction
                        //TODO : check for big fraction
                        Fraction cf = new Fraction(c1, c2);
                        if (n1 != 0 && n2 != 0) {
                            nf = new Fraction(n1, n2);
                        }
                        af = cf.add(nf);
                    }

                    hpk.setSyerPembilang(af.getNumerator());
                    hpk.setSyerPenyebut(af.getDenominator());
                    hpk.setJumlahPembilang(af.getNumerator());
                    hpk.setJumlahPenyebut(af.getDenominator());
                    hpk.setJenis(p.getJenis());
                    hpk.setPenubuhanSyarikat(p.getPenubuhanSyarikat());
//                    info = hpk.getInfoAudit();
                    info.setDiKemaskiniOleh(info.getDimasukOleh());
                    info.setTarikhKemaskini(new java.util.Date());
                    hpk.setInfoAudit(info);
                }

                if (p.getJenis().getKod().startsWith("KV")) {
                    hpk.setKaveatAktif('Y');
                } else {
                    hpk.setKaveatAktif('T');
                }

                if (hpk.getCawangan() == null) {
                    hpk.setCawangan(hk.getCawangan());
                }

                if (p.getSyerBersama() != null && p.getSyerBersama() == 'Y') {
                    if (pihakKongsi == null) {
                        pihakKongsi = p.getPihak();
                    }
                    hpk.setPihakKongsiBersama(pihakKongsi);
                }
                hpk.setNama(p.getNama());
                hpk.setJenisPengenalan(p.getJenisPengenalan());
                hpk.setNoPengenalan(p.getNoPengenalan());
                hpk.setAlamat1(p.getAlamat().getAlamat1());
                hpk.setAlamat2(p.getAlamat().getAlamat2());
                hpk.setAlamat3(p.getAlamat().getAlamat3());
                hpk.setAlamat4(p.getAlamat().getAlamat4());
                hpk.setPoskod(p.getAlamat().getPoskod());
                hpk.setNegeri(p.getAlamat().getNegeri());

                if (hpk.getJenis().getKod().equals("PA") || hpk.getJenis().getKod().equals("PP")) {
                    map = new HashMap<String, Object>();
                    LOG.debug("jenis = " + hpk.getJenis().getKod());
                    PA = hakmilikPihakKepentinganService.save(hpk);
                    LOG.debug("id mohon pihak = " + p.getIdPermohonanPihak());
                    map.put(String.valueOf(p.getIdPermohonanPihak()), hpk);
                    senaraiMap.add(map);
                } else {
                    hakmilikPihakBerkepentinganList.add(hpk);
                }
            }

            LOG.debug("size = " + senaraiMap.size());
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            hakmilikPihakKepentinganService.save(hakmilikPihakBerkepentinganList, pemohon, hk, permohonan, info);
        }

        for (PermohonanPihak pp : list) {
            if (pp == null || pp.getSenaraiHubungan().isEmpty()) {
                continue;
            }
            LOG.debug("pp.id mohon pihak =" + pp.getIdPermohonanPihak());
//                    PA = hakmilikPihakKepentinganService
//                            .findHakmilikPihakByIdPihakPMPPMG(pp.getPihak(), pp.getHakmilik(), "PA");

            for (Map<String, Object> map2 : senaraiMap) {
                if (map2 == null) {
                    continue;
                }
                PA = (HakmilikPihakBerkepentingan) map2.get(String.valueOf(pp.getIdPermohonanPihak()));
                if (PA != null) {
                    break;
                }
            }

            LOG.debug(PA != null ? "not null" : "null");

            List<PermohonanPihakHubungan> senaraiWaris = pp.getSenaraiHubungan();
            for (PermohonanPihakHubungan pph : senaraiWaris) {
                LOG.debug("senarai waris..");
                HakmilikWaris hw = new HakmilikWaris();
                hw.setInfoAudit(info);
                hw.setJenisPengenalan(pph.getJenisPengenalan());
                hw.setPemegangAmanah(PA);
                hw.setNama(pph.getNama());
                hw.setNoPengenalan(pph.getNoPengenalan());
                hw.setSyerPembilang(pph.getSyerPembilang());
                hw.setSyerPenyebut(pph.getSyerPenyebut());
                hw.setWargaNegara(pph.getWargaNegara());
                hw.setNegeri(pph.getNegeri());
                hw.setCawangan(pph.getCawangan());
                hakmilikWarisDAO.save(hw);
            }
        }

        String[] UPDATE_PIHAK_JMB = {
            "JMB",
            "JPB",};

        //Added by Aizuddin to update pihak JMB
        if (ArrayUtils.contains(UPDATE_PIHAK_JMB, permohonan.getKodUrusan().getKod())) {
//        if (StringUtils.equalsIgnoreCase(permohonan.getKodUrusan().getKod(), "JMB")) {
            LOG.info("UpdatePihak JMB:: start");
            for (HakmilikPermohonan hp : li) {
                Hakmilik h = hp.getHakmilik();
                List<HakmilikUrusan> listBatal = permohonanService.getUrusanDibatalkanIdPermohonan(permohonan.getIdPermohonan());
                for (HakmilikUrusan huBatal : listBatal) {
                    LOG.info("::::ID_Mohon JMGD " + huBatal.getIdPerserahan() + " ID Urusan " + huBatal.getIdUrusan());
                    Long idUrusanJMGD = huBatal.getIdUrusan();
                    String idMohonJMGD = huBatal.getIdPerserahan();
                    List<HakmilikUrusan> listSemuaUrusan = hakmilikService.findAllHakmilikUrusanByIdHakmilik(h.getIdHakmilik());
                    for (HakmilikUrusan huSemuaUrusan : listSemuaUrusan) {
                        if (StringUtils.isNotBlank(huSemuaUrusan.getIdPermohonanBatal())) {
                            if (huSemuaUrusan.getIdPermohonanBatal().equals(idMohonJMGD)) {
                                LOG.info("::::ID_Mohon Urusan Dibatalkan JMGD" + huSemuaUrusan.getIdPerserahan());
                                LOG.info("::::Set Aktif:::::::");
                                huSemuaUrusan.setAktif('Y');
                                hakmilikService.saveOrUpdate(huSemuaUrusan);
                                LOG.info("::::SAVING:::::::");
                            }
                        }
                    }
                    List<HakmilikPihakBerkepentingan> listSemuaHP = hakmilikPihakKepentinganService.findAllPihakBerkepentinganByIdHakmilik(h.getIdHakmilik());
                    for (HakmilikPihakBerkepentingan allPihak : listSemuaHP) {
                        if (allPihak.getPerserahanBatal() != null) {
                            LOG.info("::::Aktifkan Pemilik dibatalkan JMGD:::::::");
                            if (allPihak.getPerserahanBatal().equals(huBatal)) {
                                LOG.info("::::Pemilik Diaktifkan:::::::" + allPihak.getNama());
                                allPihak.setAktif('Y');
                                hakmilikPihakKepentinganService.save(allPihak);
                                LOG.info("::::Aktifkan Pemilik SAVE!!!:::::::");
                            }
                        }
                        if (allPihak.getPerserahan() != null) {
                            LOG.info("::::Batalkan Pemilik dibatalkan JMGD:::::::");
                            if (allPihak.getPerserahan().equals(huBatal)) {
                                LOG.info("::::Pemilik JMGD Dibatalkan:::::::" + allPihak.getNama());
                                allPihak.setAktif('T');
                                hakmilikPihakKepentinganService.save(allPihak);
                                LOG.info("::::Batalkan Pemilik SAVE!!!:::::::");
                            }
                        }

                    }
                }

            }
            LOG.info("UpdatePihak JMB:: finish");
        }

        //GDPJL Membatalkan Urusan GDPJ. Get Semua Pihak Terlibat Untuk Urusan GDPJ Dan Batalkan Pihak Tersebut.
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("GDPJL")) {
            List<PermohonanHubungan> senaraiHubungan = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), null);
            for (PermohonanHubungan ph : senaraiHubungan) {
                if (ph == null) {
                    continue;
                }
                HakmilikUrusan hurusan = hakmilikService.findByIdPerserahan(ph.getPermohonanSasaran().getIdPermohonan());
                if (hurusan == null) {
                    continue;
                }
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hurusan.getSenaraiPihakTerlibat();
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihakTemp = new ArrayList<HakmilikPihakBerkepentingan>();
                for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                    hpk.setAktif('T');
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    hpk.setInfoAudit(info);
                    senaraiHakmilikPihakTemp.add(hpk);
                }
                hakmilikPihakKepentinganService.save(senaraiHakmilikPihakTemp);
            }
        }

        LOG.info("UpdatePihak :: finish");
    }

    private void updatePihak2(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("updatePihak2 :: start");

        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();

        List<Pemohon> pemohon = permohonan.getSenaraiPemohon();

        KodUrusan ku = permohonan.getKodUrusan();

        List<PermohonanPihak> list = permohonan.getSenaraiPihak();
        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList1 = new ArrayList<HakmilikPihakBerkepentingan>();
        List<Map<String, Object>> senaraiMap = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = null;
        HakmilikPihakBerkepentingan PA = null;
        if (!permohonan.getKodUrusan().getKod().equals("PHMM") || !permohonan.getKodUrusan().getKod().equals("TN")) {
            for (HakmilikPermohonan hp : li) {
                if (hp == null) {
                    continue;
                }

                Hakmilik hk = hp.getHakmilik();
                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                if (hu == null) {
                    continue;
                }

                // this is new feature
                // to cater MH
                // to insert all pemohon to hakmilik_pihak
                KodJenisPihakBerkepentingan jenis = null;
                Pihak pihakKongsi = null;
                PA = new HakmilikPihakBerkepentingan();

                for (PermohonanPihak p : list) {
                    if (p == null || p.getJenis().getKod().equals("WAR")) {
                        continue;
                    }
                    if (p.getHakmilik() == null
                            || !p.getHakmilik().getIdHakmilik().equals(hk.getIdHakmilik())) {
                        continue;
                    }

                    Pihak pihak = p.getPihak();
                    if (!permohonan.getKodUrusan().getKod().equals("PHMM")) {
                        HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
                        hpk.setJenis(p.getJenis());
                        hpk.setPihak(pihak);
                        hpk.setWargaNegara(p.getWargaNegara());
                        hpk.setHakmilik(hk);
                        hpk.setCawangan(hk.getCawangan());
                        hpk.setAktif('Y');
                        hpk.setPerserahan(hu);
                        hpk.setInfoAudit(info);
                        hpk.setSyerPembilang(p.getSyerPembilang());
                        hpk.setSyerPenyebut(p.getSyerPenyebut());
                        hpk.setJumlahPembilang(p.getSyerPembilang());
                        hpk.setJumlahPenyebut(p.getSyerPenyebut());
                        hpk.setPenubuhanSyarikat(p.getPenubuhanSyarikat());
                        info.setDiKemaskiniOleh(info.getDimasukOleh());
                        info.setTarikhKemaskini(new java.util.Date());
                        hpk.setInfoAudit(info);
                        if (p.getIdPihakKongsi() != null) {
                            pihakKongsi = pihakDAO.findById(Long.parseLong(String.valueOf(p.getIdPihakKongsi())));
                        }

                        if (p.getJenis().getKod().startsWith("KV")) {
                            hpk.setKaveatAktif('Y');
                        } else {
                            hpk.setKaveatAktif('T');
                        }

                        if (hpk.getCawangan() == null) {
                            hpk.setCawangan(hk.getCawangan());
                        }

                        if (p.getSyerBersama() != null && p.getSyerBersama() == 'Y') {
                            if (pihakKongsi == null) {
                                pihakKongsi = p.getPihak();
                            }
                            hpk.setPihakKongsiBersama(pihakKongsi);
                        }
                        hpk.setNama(p.getNama());
                        hpk.setJenisPengenalan(p.getJenisPengenalan());
                        hpk.setNoPengenalan(p.getNoPengenalan());
                        hpk.setAlamat1(p.getAlamat() != null ? p.getAlamat().getAlamat1() : StringUtils.isNotBlank(pihak.getAlamat1()) ? pihak.getAlamat1() : null);
                        hpk.setAlamat2(p.getAlamat() != null ? p.getAlamat().getAlamat2() : StringUtils.isNotBlank(pihak.getAlamat2()) ? pihak.getAlamat2() : null);
                        hpk.setAlamat3(p.getAlamat() != null ? p.getAlamat().getAlamat3() : StringUtils.isNotBlank(pihak.getAlamat3()) ? pihak.getAlamat3() : null);
                        hpk.setAlamat4(p.getAlamat() != null ? p.getAlamat().getAlamat4() : StringUtils.isNotBlank(pihak.getAlamat4()) ? pihak.getAlamat4() : null);
                        hpk.setPoskod(p.getAlamat() != null ? p.getAlamat().getPoskod() : StringUtils.isNotBlank(pihak.getPoskod()) ? pihak.getPoskod() : null);
                        hpk.setNegeri(p.getAlamat() != null ? p.getAlamat().getNegeri() : pihak.getNegeri() != null ? pihak.getNegeri() : null);

                        if (hpk.getJenis().getKod().equals("PA") || hpk.getJenis().getKod().equals("PP")) {
                            map = new HashMap<String, Object>();
                            LOG.debug("jenis = " + hpk.getJenis().getKod());
                            PA = hakmilikPihakKepentinganService.save(hpk);
                            LOG.debug("id mohon pihak = " + p.getIdPermohonanPihak());
                            map.put(String.valueOf(p.getIdPermohonanPihak()), hpk);
                            senaraiMap.add(map);
                        } else {
                            hakmilikPihakBerkepentinganList.add(hpk);
                        }
                    }
                }

                LOG.debug("size = " + senaraiMap.size());
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                LOG.info("hakmilikPihakBerkepentinganList.size() = " + hakmilikPihakBerkepentinganList.size());
                LOG.info("pemohon.size() = " + pemohon.size());
                LOG.info("HK = " + hk);
                LOG.info("info = " + info);
                LOG.info("permohonan = " + permohonan);
                hakmilikPihakKepentinganService.save(hakmilikPihakBerkepentinganList, pemohon, hk, permohonan, info);
            }
        }
//        if (permohonan.getKodUrusan().getKod().equals("PHMM")) {
        String idKump = li.get(0).getPermohonan().getIdKumpulan();
        Hakmilik Hm = li.get(0).getHakmilik();
        HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), Hm.getIdHakmilik());

        if (idKump != null) {
            if (permohonan.getKodUrusan().getKod().equals("PHMM")) {
                List<Permohonan> senaraiMhn = permohonanService.findByIdKumpPHMM(idKump);
                List<Permohonan> senaraiListNoKump = permohonanService.findByIdKump(idKump);
                if (senaraiMhn.size() > 0) {
                    Permohonan mhn = senaraiMhn.get(0);
                    List<PermohonanPihak> Listmhnphk = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(mhn.getIdPermohonan(), Hm.getIdHakmilik());
                    if (permohonan.getKodUrusan().getKod().equals("PHMM") && (permohonan.getKumpulanNo() == mhn.getKumpulanNo())) {
                        List<Permohonan> listGDL = permohonanService.findByIdKumpGDL(idKump);
                        if (listGDL.size() > 0) {
                            for (PermohonanPihak mhnphk : Listmhnphk) {
                                HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                                hp.setJenis(mhnphk.getJenis());
                                hp.setPihak(mhnphk.getPihak());
                                hp.setWargaNegara(mhnphk.getWargaNegara());
                                hp.setHakmilik(mhnphk.getHakmilik());
                                hp.setAktif('T');
                                hp.setCawangan(mhnphk.getCawangan());
                                if (hu != null) {
                                    hp.setPerserahan(hu);
                                }
                                hp.setSyerPembilang(mhnphk.getSyerPembilang());
                                hp.setSyerPenyebut(mhnphk.getSyerPenyebut());
                                hp.setJumlahPembilang(mhnphk.getSyerPembilang());
                                hp.setJumlahPenyebut(mhnphk.getSyerPenyebut());
                                hp.setInfoAudit(info);
                                hp.setNama(mhnphk.getNama());
                                hp.setJenisPengenalan(mhnphk.getJenisPengenalan());
                                hp.setNoPengenalan(mhnphk.getNoPengenalan());
                                if (mhnphk.getAlamat() != null) {
                                    if (mhnphk.getAlamat().getAlamat1() != null) {
                                        hp.setAlamat1(mhnphk.getAlamat().getAlamat1());
                                    }
                                    if (mhnphk.getAlamat().getAlamat2() != null) {
                                        hp.setAlamat2(mhnphk.getAlamat().getAlamat2());
                                    }
                                    if (mhnphk.getAlamat().getAlamat3() != null) {
                                        hp.setAlamat3(mhnphk.getAlamat().getAlamat3());
                                    }
                                    if (mhnphk.getAlamat().getAlamat4() != null) {
                                        hp.setAlamat4(mhnphk.getAlamat().getAlamat4());
                                    }
                                    if (mhnphk.getAlamat().getPoskod() != null) {
                                        hp.setPoskod(mhnphk.getAlamat().getPoskod());
                                    }
                                    if (mhnphk.getAlamat().getNegeri() != null) {
                                        hp.setNegeri(mhnphk.getAlamat().getNegeri());
                                    }
                                }
                                hp.setPenubuhanSyarikat(mhnphk.getPenubuhanSyarikat());
                                hakmilikPihakBerkepentinganList.add(hp);
                                hakmilikPihakKepentinganService.save(hp);
                                List<PermohonanHubungan> senaraihbgn = permohonanHubunganService.findMohonAtasUrusanByIDSumberAndIDHakmilik(mhnphk.getPermohonan().getIdPermohonan(), mhnphk.getHakmilik().getIdHakmilik());
                                if (senaraihbgn.size() > 0) {
                                    for (PermohonanHubungan hbgn : senaraihbgn) {
                                        List<PermohonanPihak> Listmhpk1 = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hbgn.getPermohonanSasaran().getIdPermohonan(), hbgn.getHakmilik().getIdHakmilik());
                                        for (PermohonanPihak mhpk : Listmhpk1) {
                                            mhpk.setJenis(hp.getJenis());
                                            mhpk.setPihak(hp.getPihak());
                                            mhpk.setWargaNegara(hp.getWargaNegara());
                                            mhpk.setHakmilik(hp.getHakmilik());
                                            mhpk.setSyerPembilang(hp.getSyerPembilang());
                                            mhpk.setSyerPenyebut(hp.getSyerPenyebut());
                                            mhpk.setJumlahPembilang(hp.getSyerPembilang());
                                            mhpk.setJumlahPenyebut(hp.getSyerPenyebut());
                                            mhpk.setInfoAudit(info);
                                            mhpk.setNama(hp.getNama());
                                            mhpk.setJenisPengenalan(hp.getJenisPengenalan());
                                            mhpk.setNoPengenalan(hp.getNoPengenalan());
                                            permohonanPihakService.saveOrUpdate(mhnphk);
                                        }
                                    }
                                }
                            }
                        } else {
                            for (PermohonanPihak mhnphk : Listmhnphk) {
                                HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                                hp.setJenis(mhnphk.getJenis());
                                hp.setPihak(mhnphk.getPihak());
                                hp.setWargaNegara(mhnphk.getWargaNegara());
                                hp.setHakmilik(mhnphk.getHakmilik());
                                hp.setAktif('Y');
                                hp.setCawangan(mhnphk.getCawangan());
                                if (hu != null) {
                                    hp.setPerserahan(hu);
                                }
                                hp.setSyerPembilang(mhnphk.getSyerPembilang());
                                hp.setSyerPenyebut(mhnphk.getSyerPenyebut());
                                hp.setJumlahPembilang(mhnphk.getSyerPembilang());
                                hp.setJumlahPenyebut(mhnphk.getSyerPenyebut());
                                hp.setInfoAudit(info);
                                hp.setNama(mhnphk.getNama());
                                hp.setJenisPengenalan(mhnphk.getJenisPengenalan());
                                hp.setNoPengenalan(mhnphk.getNoPengenalan());
                                if (mhnphk.getAlamat() != null) {
                                    if (mhnphk.getAlamat().getAlamat1() != null) {
                                        hp.setAlamat1(mhnphk.getAlamat().getAlamat1());
                                    }
                                    if (mhnphk.getAlamat().getAlamat2() != null) {
                                        hp.setAlamat2(mhnphk.getAlamat().getAlamat2());
                                    }
                                    if (mhnphk.getAlamat().getAlamat3() != null) {
                                        hp.setAlamat3(mhnphk.getAlamat().getAlamat3());
                                    }
                                    if (mhnphk.getAlamat().getAlamat4() != null) {
                                        hp.setAlamat4(mhnphk.getAlamat().getAlamat4());
                                    }
                                    if (mhnphk.getAlamat().getPoskod() != null) {
                                        hp.setPoskod(mhnphk.getAlamat().getPoskod());
                                    }
                                    if (mhnphk.getAlamat().getNegeri() != null) {
                                        hp.setNegeri(mhnphk.getAlamat().getNegeri());
                                    }
                                }
                                hp.setPenubuhanSyarikat(mhnphk.getPenubuhanSyarikat());
                                hakmilikPihakBerkepentinganList.add(hp);
                                hakmilikPihakKepentinganService.save(hp);
                                List<PermohonanHubungan> senaraihbgn = permohonanHubunganService.findMohonAtasUrusanByIDSumberAndIDHakmilik(mhnphk.getPermohonan().getIdPermohonan(), mhnphk.getHakmilik().getIdHakmilik());
                                if (senaraihbgn.size() > 0) {
                                    for (PermohonanHubungan hbgn : senaraihbgn) {
                                        List<PermohonanPihak> Listmhpk1 = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hbgn.getPermohonanSasaran().getIdPermohonan(), hbgn.getHakmilik().getIdHakmilik());
                                        for (PermohonanPihak mhpk : Listmhpk1) {
                                            mhpk.setJenis(hp.getJenis());
                                            mhpk.setPihak(hp.getPihak());
                                            mhpk.setWargaNegara(hp.getWargaNegara());
                                            mhpk.setHakmilik(hp.getHakmilik());
                                            mhpk.setSyerPembilang(hp.getSyerPembilang());
                                            mhpk.setSyerPenyebut(hp.getSyerPenyebut());
                                            mhpk.setJumlahPembilang(hp.getSyerPembilang());
                                            mhpk.setJumlahPenyebut(hp.getSyerPenyebut());
                                            mhpk.setInfoAudit(info);
                                            mhpk.setNama(hp.getNama());
                                            mhpk.setJenisPengenalan(hp.getJenisPengenalan());
                                            mhpk.setNoPengenalan(hp.getNoPengenalan());
                                            permohonanPihakService.saveOrUpdate(mhnphk);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (permohonan.getKodUrusan().getKod().equals("GDL")) {
                List<Permohonan> listGDL = permohonanService.findByIdKumpGDL(idKump);
                for (Permohonan GDL : listGDL) {
                    List<Pemohon> senaraiPemohonT = pemohonService.findPemohonByIdPermohonan(GDL.getIdPermohonan());
                    for (Pemohon pemohonT : senaraiPemohonT) {
                        if (pemohonT.getHakmilikPihak() != null) { // yus add 21/4/2018    
                            HakmilikPihakBerkepentingan hmPihak = hakmilikPihakKepentinganService.findById(String.valueOf(pemohonT.getHakmilikPihak().getIdHakmilikPihakBerkepentingan()));
                            if (hmPihak != null) {
                                hmPihak.setAktif('T');
                            }
                            hakmilikPihakKepentinganService.save(hmPihak);
                        }// yus add 21/4/2018
                    }
                }
            }
        } else if (idKump == null) {
            if (permohonan.getKodUrusan().getKod().equals("PHMM")) {
                List<PermohonanPihak> Listmhnphk = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(permohonan.getIdPermohonan(), Hm.getIdHakmilik());
                for (PermohonanPihak mhnphk : Listmhnphk) {
                    HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                    hp.setJenis(mhnphk.getJenis());
                    hp.setPihak(mhnphk.getPihak());
                    hp.setWargaNegara(mhnphk.getWargaNegara());
                    hp.setHakmilik(mhnphk.getHakmilik());
                    hp.setAktif('Y');
                    hp.setCawangan(mhnphk.getCawangan());
                    if (hu != null) {
                        hp.setPerserahan(hu);
                    }
                    hp.setSyerPembilang(mhnphk.getSyerPembilang());
                    hp.setSyerPenyebut(mhnphk.getSyerPenyebut());
                    hp.setJumlahPembilang(mhnphk.getSyerPembilang());
                    hp.setJumlahPenyebut(mhnphk.getSyerPenyebut());
                    hp.setInfoAudit(info);
                    hp.setNama(mhnphk.getNama());
                    hp.setJenisPengenalan(mhnphk.getJenisPengenalan());
                    hp.setNoPengenalan(mhnphk.getNoPengenalan());
                    if (mhnphk.getAlamat() != null) {
                        if (mhnphk.getAlamat().getAlamat1() != null) {
                            hp.setAlamat1(mhnphk.getAlamat().getAlamat1());
                        }
                        if (mhnphk.getAlamat().getAlamat2() != null) {
                            hp.setAlamat2(mhnphk.getAlamat().getAlamat2());
                        }
                        if (mhnphk.getAlamat().getAlamat3() != null) {
                            hp.setAlamat3(mhnphk.getAlamat().getAlamat3());
                        }
                        if (mhnphk.getAlamat().getAlamat4() != null) {
                            hp.setAlamat4(mhnphk.getAlamat().getAlamat4());
                        }
                        if (mhnphk.getAlamat().getPoskod() != null) {
                            hp.setPoskod(mhnphk.getAlamat().getPoskod());
                        }
                        if (mhnphk.getAlamat().getNegeri() != null) {
                            hp.setNegeri(mhnphk.getAlamat().getNegeri());
                        }
                    }
                    hakmilikPihakBerkepentinganList.add(hp);
                    hakmilikPihakKepentinganService.save(hp);
                    List<PermohonanHubungan> senaraihbgn = permohonanHubunganService.findMohonAtasUrusanByIDSumberAndIDHakmilik(mhnphk.getPermohonan().getIdPermohonan(), mhnphk.getHakmilik().getIdHakmilik());
                    if (senaraihbgn.size() > 0) {
                        for (PermohonanHubungan hbgn : senaraihbgn) {
                            List<PermohonanPihak> Listmhpk1 = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hbgn.getPermohonanSasaran().getIdPermohonan(), hbgn.getHakmilik().getIdHakmilik());
                            for (PermohonanPihak mhpk : Listmhpk1) {
                                mhpk.setJenis(hp.getJenis());
                                mhpk.setPihak(hp.getPihak());
                                mhpk.setWargaNegara(hp.getWargaNegara());
                                mhpk.setHakmilik(hp.getHakmilik());
                                mhpk.setSyerPembilang(hp.getSyerPembilang());
                                mhpk.setSyerPenyebut(hp.getSyerPenyebut());
                                mhpk.setJumlahPembilang(hp.getSyerPembilang());
                                mhpk.setJumlahPenyebut(hp.getSyerPenyebut());
                                mhpk.setInfoAudit(info);
                                mhpk.setNama(hp.getNama());
                                mhpk.setJenisPengenalan(hp.getJenisPengenalan());
                                mhpk.setNoPengenalan(hp.getNoPengenalan());
                                permohonanPihakService.saveOrUpdate(mhnphk);
                            }
                        }
                    }
                }

            } else if (permohonan.getKodUrusan().getKod().equals("GDL")) {
                List<Pemohon> senaraiPemohonT = pemohonService.findPemohonByIdPermohonan(permohonan.getIdPermohonan());
                for (Pemohon pemohonT : senaraiPemohonT) {
                    if (pemohonT.getHakmilikPihak() != null) {
                        HakmilikPihakBerkepentingan hmPihak = hakmilikPihakKepentinganService.findById(String.valueOf(pemohonT.getHakmilikPihak().getIdHakmilikPihakBerkepentingan()));
                        if (hmPihak != null) {
                            hmPihak.setAktif('T');
                            hakmilikPihakKepentinganService.save(hmPihak);
                        }
                    } else {
                        List<PermohonanHubungan> senaraihbgn = permohonanHubunganService.findMohonAtasUrusanByIDSumberAndIDHakmilik(permohonan.getIdPermohonan(), Hm.getIdHakmilik());
                        if (senaraihbgn.size() > 0) {
                            for (PermohonanHubungan hbgn : senaraihbgn) {
                                List<PermohonanPihak> Listmhpk1 = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hbgn.getPermohonanSumber().getIdPermohonan(), hbgn.getHakmilik().getIdHakmilik());
                                for (PermohonanPihak mhpk : Listmhpk1) {
                                    HakmilikPihakBerkepentingan hmPihak = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(mhpk.getPihak(), mhpk.getHakmilik());
                                    if (hmPihak != null) {
                                        hmPihak.setAktif('T');
                                    }
                                    hakmilikPihakKepentinganService.save(hmPihak);
                                }
                            }
                        }
                    }
                }
            } else if (permohonan.getKodUrusan().getKod().equals("416C")) {
                List<HakmilikPihakBerkepentingan> listHP = hakmilikPihakKepentinganService.findPihakActiveByHakmilik(Hm);
                if (listHP.size() > 0) {
                    for (HakmilikPihakBerkepentingan hp : listHP) {
                        hp.setAktif('Y');
                        hakmilikPihakKepentinganService.save(hp);
                    }
                }

            }
        }

        for (PermohonanPihak pp : list) {
            if (pp == null || pp.getSenaraiHubungan().isEmpty()) {
                continue;
            }
            LOG.debug("pp.id mohon pihak =" + pp.getIdPermohonanPihak());
//                    PA = hakmilikPihakKepentinganService
//                            .findHakmilikPihakByIdPihakPMPPMG(pp.getPihak(), pp.getHakmilik(), "PA");

            for (Map<String, Object> map2 : senaraiMap) {
                if (map2 == null) {
                    continue;
                }
                PA = (HakmilikPihakBerkepentingan) map2.get(String.valueOf(pp.getIdPermohonanPihak()));
                if (PA != null) {
                    break;
                }
            }

            LOG.debug(PA != null ? "not null" : "null");

            List<PermohonanPihakHubungan> senaraiWaris = pp.getSenaraiHubungan();
            for (PermohonanPihakHubungan pph : senaraiWaris) {
                LOG.debug("senarai waris..");
                HakmilikWaris hw = new HakmilikWaris();
                hw.setInfoAudit(info);
                hw.setJenisPengenalan(pph.getJenisPengenalan());
                hw.setPemegangAmanah(PA);
                hw.setNama(pph.getNama());
                hw.setNoPengenalan(pph.getNoPengenalan());
                hw.setSyerPembilang(pph.getSyerPembilang());
                hw.setSyerPenyebut(pph.getSyerPenyebut());
                hw.setWargaNegara(pph.getWargaNegara());
                hw.setNegeri(pph.getNegeri());
                hw.setCawangan(pph.getCawangan());
                hakmilikWarisDAO.save(hw);
            }
        }

        String[] UPDATE_PIHAK_JMB = {
            "JMB",
            "JPB",};

        //Added by Aizuddin to update pihak JMB
        if (ArrayUtils.contains(UPDATE_PIHAK_JMB, permohonan.getKodUrusan().getKod())) {
//        if (StringUtils.equalsIgnoreCase(permohonan.getKodUrusan().getKod(), "JMB")) {
            LOG.info("UpdatePihak JMB:: start");
            for (HakmilikPermohonan hp : li) {
                Hakmilik h = hp.getHakmilik();
                List<HakmilikUrusan> listBatal = permohonanService.getUrusanDibatalkanIdPermohonan(permohonan.getIdPermohonan());
                for (HakmilikUrusan huBatal : listBatal) {
                    LOG.info("::::ID_Mohon JMGD " + huBatal.getIdPerserahan() + " ID Urusan " + huBatal.getIdUrusan());
                    Long idUrusanJMGD = huBatal.getIdUrusan();
                    String idMohonJMGD = huBatal.getIdPerserahan();
                    List<HakmilikUrusan> listSemuaUrusan = hakmilikService.findAllHakmilikUrusanByIdHakmilik(h.getIdHakmilik());
                    for (HakmilikUrusan huSemuaUrusan : listSemuaUrusan) {
                        if (StringUtils.isNotBlank(huSemuaUrusan.getIdPermohonanBatal())) {
                            if (huSemuaUrusan.getIdPermohonanBatal().equals(idMohonJMGD)) {
                                LOG.info("::::ID_Mohon Urusan Dibatalkan JMGD" + huSemuaUrusan.getIdPerserahan());
                                LOG.info("::::Set Aktif:::::::");
                                huSemuaUrusan.setAktif('Y');
                                hakmilikService.saveOrUpdate(huSemuaUrusan);
                                LOG.info("::::SAVING:::::::");
                            }
                        }
                    }
                    List<HakmilikPihakBerkepentingan> listSemuaHP = hakmilikPihakKepentinganService.findAllPihakBerkepentinganByIdHakmilik(h.getIdHakmilik());
                    for (HakmilikPihakBerkepentingan allPihak : listSemuaHP) {
                        if (allPihak.getPerserahanBatal() != null) {
                            LOG.info("::::Aktifkan Pemilik dibatalkan JMGD:::::::");
                            if (allPihak.getPerserahanBatal().equals(huBatal)) {
                                LOG.info("::::Pemilik Diaktifkan:::::::" + allPihak.getNama());
                                allPihak.setAktif('Y');
                                hakmilikPihakKepentinganService.save(allPihak);
                                LOG.info("::::Aktifkan Pemilik SAVE!!!:::::::");
                            }
                        }
                        if (allPihak.getPerserahan() != null) {
                            LOG.info("::::Batalkan Pemilik dibatalkan JMGD:::::::");
                            if (allPihak.getPerserahan().equals(huBatal)) {
                                LOG.info("::::Pemilik JMGD Dibatalkan:::::::" + allPihak.getNama());
                                allPihak.setAktif('T');
                                hakmilikPihakKepentinganService.save(allPihak);
                                LOG.info("::::Batalkan Pemilik SAVE!!!:::::::");
                            }
                        }

                    }
                }

            }
            LOG.info("UpdatePihak JMB:: finish");
        }

        //GDPJL Membatalkan Urusan GDPJ. Get Semua Pihak Terlibat Untuk Urusan GDPJ Dan Batalkan Pihak Tersebut.
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("GDPJL")) {
            List<PermohonanHubungan> senaraiHubungan = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), null);
            for (PermohonanHubungan ph : senaraiHubungan) {
                if (ph == null) {
                    continue;
                }
                HakmilikUrusan hurusan = hakmilikService.findByIdPerserahan(ph.getPermohonanSasaran().getIdPermohonan());
                if (hurusan == null) {
                    continue;
                }
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hurusan.getSenaraiPihakTerlibat();
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihakTemp = new ArrayList<HakmilikPihakBerkepentingan>();
                for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                    hpk.setAktif('T');
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    hpk.setInfoAudit(info);
                    senaraiHakmilikPihakTemp.add(hpk);
                }
                hakmilikPihakKepentinganService.save(senaraiHakmilikPihakTemp);
            }
        }

        LOG.info("UpdatePihak :: finish");
    }

    private void insertPihak3(Permohonan permohonan, InfoAudit info) throws Exception {

        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        List<Pemohon> pemohon = permohonan.getSenaraiPemohon();
        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PH30A")) {
            for (HakmilikPermohonan hp : li) {
                if (hp == null) {
                    continue;
                }

                Hakmilik hk = hp.getHakmilik();

                for (Pemohon pmh : pemohon) {
                    if (pmh == null) {
                        continue;
                    }

                    HakmilikPihakBerkepentingan hpkToDeactivate = hakmilikPihakKepentinganService.findById(String.valueOf(pmh.getHakmilikPihak().getIdHakmilikPihakBerkepentingan()));
                    InfoAudit ia = new InfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());

                    hpkToDeactivate.setAktif('T');
                    hpkToDeactivate.setInfoAudit(ia);

                    hakmilikPihakKepentinganService.save(hpkToDeactivate);
//                    LOG.info("hk.getIdHakmilik() =" + hk.getIdHakmilik());
//                    LOG.info("hpkToDeactivate.getPihak().getIdPihak() =" + hpkToDeactivate.getPihak().getIdPihak());
//                    LOG.info("hpkToDeactivate.getPerserahan().getIdPerserahan() =" + hpkToDeactivate.getPerserahan().getIdPerserahan());

                    if (hpkToDeactivate.getPerserahan() != null) { // yus add 20/03/2018

                        List<PermohonanPihak> senaraiMohonPihakToUpdate = regService.findMohonPihakByHakmilikAndIdMohonAndIdPihak(hk.getIdHakmilik(), hpkToDeactivate.getPerserahan().getIdPerserahan(), hpkToDeactivate.getPihak().getIdPihak());

                        for (PermohonanPihak pp : senaraiMohonPihakToUpdate) {
                            senaraiMohonPihakKkini = pService.findListbyIdMohonIdPemohon(pmh.getPermohonan().getIdPermohonan(), pmh.getIdPemohon());

                            for (PermohonanPihakKemaskini mohonPihakKkini : senaraiMohonPihakKkini) {
                                if (mohonPihakKkini.getNamaMedan().equals("nama")) {
                                    pp.setNama(mohonPihakKkini.getNilaiBaru());
                                }
                                if (mohonPihakKkini.getNamaMedan().equals("jeniskp") && StringUtils.isNotBlank(mohonPihakKkini.getNilaiBaru())) {
                                    KodJenisPengenalan kj = kodJenisPengenalanDAO.findById(mohonPihakKkini.getNilaiBaru());
                                    pp.setJenisPengenalan(kj);
                                }
                                pService.simpanMohonPihak(pp);

                            }
                        }
                    } //end yus add

                    HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
                    if (permohonan.getKodUrusan().getKod().startsWith("KV")) {
                        hpk.setKaveatAktif('Y');
                    } else {
                        hpk.setKaveatAktif('T');
                    }
                    hpk.setJenis(pmh.getJenis());
                    hpk.setPihak(pmh.getPihak());
                    hpk.setHakmilik(hk);
                    hpk.setAktif('Y');
                    hpk.setCawangan(hk.getCawangan());
                    hpk.setPerserahan(pmh.getHakmilikPihak().getPerserahan());

                    hpk.setSyerPembilang(pmh.getSyerPembilang());
                    hpk.setSyerPenyebut(pmh.getSyerPenyebut());
                    hpk.setJumlahPembilang(pmh.getSyerPembilang());
                    hpk.setJumlahPenyebut(pmh.getSyerPenyebut());
                    hpk.setInfoAudit(info);
                    hpk.setJenisPengenalan(pmh.getJenisPengenalan());

                    senaraiMohonPihakKkini = pService.findListbyIdMohonIdPemohon(pmh.getPermohonan().getIdPermohonan(), pmh.getIdPemohon());

                    for (PermohonanPihakKemaskini mohonPihakKkini : senaraiMohonPihakKkini) {
                        if (mohonPihakKkini.getNamaMedan().equals("nama")) {
                            hpk.setNama(mohonPihakKkini.getNilaiBaru());
                        }
                        if (mohonPihakKkini.getNamaMedan().equals("jeniskp") && StringUtils.isNotBlank(mohonPihakKkini.getNilaiBaru())) {
                            KodJenisPengenalan kj = kodJenisPengenalanDAO.findById(mohonPihakKkini.getNilaiBaru());
                            hpk.setJenisPengenalan(kj);
                        }

                    }

                    hpk.setNoPengenalan(pmh.getNoPengenalan());
                    if (pmh.getAlamat() != null) {
                        hpk.setAlamat1(pmh.getAlamat().getAlamat1());
                        hpk.setAlamat2(pmh.getAlamat().getAlamat2());
                        hpk.setAlamat3(pmh.getAlamat().getAlamat3());
                        hpk.setAlamat4(pmh.getAlamat().getAlamat4());
                        hpk.setPoskod(pmh.getAlamat().getPoskod());
                        hpk.setNegeri(pmh.getAlamat().getNegeri());
                    }
                    hakmilikPihakBerkepentinganList.add(hpk);
                }
            }
        }

        hakmilikPihakKepentinganService.save(hakmilikPihakBerkepentinganList);

    }

    private void updateAkaunDipegang(Permohonan permohonan, InfoAudit info) throws Exception {

        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PMT")) {
            for (HakmilikPermohonan hp : li) {
                if (hp == null) {
                    continue;
                }
                List<Akaun> ListAkaun = regService.findAkaunByIdHakmilik(hp.getHakmilik().getIdHakmilik());
                List<PermohonanPihak> senaraiMohonPihak = regService.findMohonPihakByHakmilikAndIdMohon(hp.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
                Pihak pihakDipegangBaru = new Pihak();

                for (PermohonanPihak pp : senaraiMohonPihak) {
                    pihakDipegangBaru.setIdPihak(pp.getPihak().getIdPihak());
                    break;
                }

                for (Akaun ac : ListAkaun) {
                    ac.setPemegang(pihakDipegangBaru);
                    ac.setInfoAudit(info);
                    regService.saveOrUpdate(ac);
                }
            }
        }
    }

    private void insertPihakPemilikanBersama(Permohonan permohonan, InfoAudit info) {

        if (permohonan != null) {

            List<PermohonanPihak> senaraiPihak = permohonan.getSenaraiPihak();

            List<Pemohon> senaraiPemohon = permohonan.getSenaraiPemohon();

            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();

            List<HakmilikPihakBerkepentingan> senaraiPihakUpdate = new ArrayList<HakmilikPihakBerkepentingan>();

            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                if (hakmilikPermohonan == null || hakmilikPermohonan.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hakmilikPermohonan.getHakmilik();

                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hm.getIdHakmilik());

                Pihak pihakUtama = null;

                for (Pemohon pemohon : senaraiPemohon) {
                    if (pemohon == null
                            || !pemohon.getHakmilik().getIdHakmilik().equals(hm.getIdHakmilik())) {
                        continue;
                    }

                    if (pihakUtama == null) {
                        pihakUtama = pemohon.getPihak();
                    }

                    HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(pemohon.getPihak(), hm);

                    if (hpk != null) {
                        hpk.setPihakKongsiBersama(pihakUtama);
                        hpk.setInfoAudit(info);
                        senaraiPihakUpdate.add(hpk);
                    }
                }

                for (PermohonanPihak pp : senaraiPihak) {
                    if (pp == null || pp.getHakmilik() == null
                            || !pp.getHakmilik().getIdHakmilik().equals(hm.getIdHakmilik())) {
                        continue;
                    }

                    HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
                    hpk.setPihak(pp.getPihak());
                    hpk.setPihakKongsiBersama(pihakUtama);
                    hpk.setWargaNegara(pp.getWargaNegara());
                    hpk.setAktif('Y');
                    hpk.setNama(pp.getNama());
                    hpk.setNoPengenalan(pp.getNoPengenalan());
                    hpk.setJenisPengenalan(pp.getJenisPengenalan());
                    hpk.setAlamat1(pp.getAlamat().getAlamat1());
                    hpk.setAlamat2(pp.getAlamat().getAlamat2());
                    hpk.setAlamat3(pp.getAlamat().getAlamat3());
                    hpk.setAlamat4(pp.getAlamat().getAlamat4());
                    hpk.setPoskod(pp.getAlamat().getPoskod());
                    hpk.setNegeri(pp.getAlamat().getNegeri());
                    hpk.setCawangan(hm.getCawangan());
                    hpk.setHakmilik(hm);
                    hpk.setJenis(pp.getJenis());
                    hpk.setKaveatAktif('T');
                    hpk.setPerserahan(hu);
                    hpk.setSyerPembilang(pp.getSyerPembilang());
                    hpk.setSyerPenyebut(pp.getSyerPenyebut());
                    hpk.setJumlahPembilang(pp.getSyerPembilang());
                    hpk.setJumlahPenyebut(pp.getSyerPenyebut());
                    hpk.setInfoAudit(info);
                    senaraiPihakUpdate.add(hpk);
                }
            }

            if (senaraiPihakUpdate.size() > 0) {
                hakmilikPihakKepentinganService.save(senaraiPihakUpdate);
            }
        }
    }

    private void insertUrusan(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("insertUrusan [ start ]");
        List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();

        KodUrusan kodUrusan = permohonan.getKodUrusan();
        boolean isKaveat = false;
        boolean isKVSPC = false;
        if (kodUrusan.getKod().equals("KVSS") || kodUrusan.getKod().equals("KVST")
                || kodUrusan.getKod().equals("KVSPT") || kodUrusan.getKod().equals("KVSK")) {
            isKaveat = true;
        } else if (kodUrusan.getKod().equals("KVSPC")) {
            isKVSPC = true;
        }
        List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
            if (hakmilikPermohonan == null || hakmilikPermohonan.getHakmilik() == null) {
                continue;
            }
            HakmilikUrusan hakmilikUrusan = new HakmilikUrusan();
            hakmilikUrusan.setInfoAudit(info);
            if (isKaveat) {
                //automatik 6 tahun
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, 6);
                hakmilikUrusan.setTarikhTamat(cal.getTime());
                hakmilikUrusan.setTempohTahun(6);
            } else if (isKVSPC) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, 2);

                PermohonanRujukanLuar mrl = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(hakmilikPermohonan.getPermohonan().getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());
                if (mrl != null) {
                    hakmilikUrusan.setTarikhTamat(mrl.getTarikhTamat());
                    hakmilikUrusan.setTempohBulan(2);
                } else {
                    hakmilikUrusan.setTarikhTamat(cal.getTime());
                    hakmilikUrusan.setTempohBulan(2);
                }
            }

            hakmilikUrusan.setDaerah(hakmilikPermohonan.getHakmilik().getDaerah());
            hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
            hakmilikUrusan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
            hakmilikUrusan.setHakmilik(hakmilikPermohonan.getHakmilik());
            hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
            hakmilikUrusan.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
            hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
            hakmilikUrusan.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());

            if (ArrayUtils.contains(HAKMILIK_URUSAN_TAK_AKTIF, permohonan.getKodUrusan().getKod())) {
                hakmilikUrusan.setAktif('T');
            } else {
                hakmilikUrusan.setAktif('Y');
            }
            hakmilikUrusan.setFolderDokumen(permohonan.getFolderDokumen());
            PermohonanRujukanLuar permohonanRujLuar = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hakmilikPermohonan.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());

            if (permohonanRujLuar != null) {

                if (!isKVSPC) {
                    hakmilikUrusan.setTarikhTamat(permohonanRujLuar.getTarikhTamat());
                    hakmilikUrusan.setTempohTahun(permohonanRujLuar.getTempohTahun());
                    hakmilikUrusan.setTempohBulan(permohonanRujLuar.getTempohBulan());
                    hakmilikUrusan.setTempohHari(permohonanRujLuar.getTempohHari());
                }
                hakmilikUrusan.setTarikhSidang(permohonanRujLuar.getTarikhSidang());
                hakmilikUrusan.setTarikhRujukan(permohonanRujLuar.getTarikhRujukan());
                hakmilikUrusan.setNoSidang(permohonanRujLuar.getNoSidang());
                hakmilikUrusan.setNoRujukan(permohonanRujLuar.getNoRujukan());
            }

            if (ArrayUtils.contains(URUSAN_TO_INSERT_ID_RUJ, permohonan.getKodUrusan().getKod())) {

                List<PermohonanHubungan> senaraiHubungan = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());

                for (PermohonanHubungan ph : senaraiHubungan) {
                    if (ph == null) {
                        continue;
                    }
                    HakmilikUrusan hurusan = hakmilikService.findByIdPerserahan(ph.getPermohonanSasaran().getIdPermohonan());
                    if (hurusan == null) {
                        continue;
                    }
                    hakmilikUrusan.setUrusanRujukan(hurusan);
                    if (ph.getHubunganPermohonan() != null) {
                        hurusan.setHubunganHakmilik(
                                kodPerhubunganHakmilikDAO.findById(ph.getHubunganPermohonan().getKod()));
                    }
                }
            }

            hakmilikUrusanList.add(hakmilikUrusan);
        }
        hakmilikService.saveOrUpdate(hakmilikUrusanList);
        LOG.info("insertUrusan [ finish ]");
    }

    /*
     * untuk urusan yg membatalkan urusan sebelum nya.
     * Cth : gadaian dan melepaskan gadaian
     */
    private void updateUrusan(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("updateUrusan :: start ::");
        List<PermohonanAtasPerserahan> permohonanAtasPerserahanList = permohonan.getSenaraiPermohonanAtasPerserahan();
        List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
        List<HakmilikPihakBerkepentingan> listTmp = new ArrayList<HakmilikPihakBerkepentingan>();
        HakmilikUrusan hu = hakmilikService.findByIdPerserahan(permohonan.getIdPermohonan());
        List<Long> SENARAI_URUSAN = new ArrayList<Long>();

        String kod = permohonan.getKodUrusan().getKod();

        for (PermohonanAtasPerserahan pas : permohonanAtasPerserahanList) {
            HakmilikUrusan hakmilikUrusan = pas.getUrusan();

            if (hakmilikUrusan != null) {
                if (kod.equalsIgnoreCase("JMGPJ") && hakmilikUrusan.getKodUrusan().getKod().startsWith("PJ")) {
                    //do nothing. testing by sptb, no need to batal this kind of urusan (pjt, pjbt, pjkt, pjkbt);
                    // just replace owner of those urusan.
                } else {
                    SENARAI_URUSAN.add(hakmilikUrusan.getIdUrusan());
                    hakmilikUrusan.setUrusanBatal(hu);
                    hakmilikUrusan.setTarikhBatal(permohonan.getInfoAudit().getTarikhMasuk());
                    hakmilikUrusan.setIdPermohonanBatal(permohonan.getIdPermohonan());
                    //                    hakmilikUrusan.setTarikhBatal(new java.util.Date());
                    //                    hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                    hakmilikUrusan.setAktif('T');
                    hakmilikUrusan.setFolderDokumenBatal(permohonan.getFolderDokumen());
                    info = hakmilikUrusan.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    hakmilikUrusan.setInfoAudit(info);
                    hakmilikUrusanList.add(hakmilikUrusan);
                }

                //to set pihak involved not active
                List<HakmilikPihakBerkepentingan> hpList = hakmilikPihakKepentinganService.findHakmilikPihakByIdUrusan(hakmilikUrusan);
                for (HakmilikPihakBerkepentingan h : hpList) {
                    h.setAktif('T');
                    h.setPerserahanBatal(hu);
                    info = h.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    h.setInfoAudit(info);
                    listTmp.add(h);
                }
                LOG.debug("listTmp ::" + listTmp.size());
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("KVSP")) {
            List<HakmilikUrusan> senaraiUrusan = hakmilikService.findUrusan(SENARAI_URUSAN, "KVSPC");
            for (HakmilikUrusan hakmilikUrusan : senaraiUrusan) {
                hakmilikUrusan.setAktif('T');
                hakmilikUrusan.setIdPermohonanBatal(permohonan.getIdPermohonan());
                hakmilikUrusan.setInfoAudit(info);
                hakmilikUrusanList.add(hakmilikUrusan);
            }
        }

        hakmilikService.saveOrUpdate(hakmilikUrusanList);
        hakmilikPihakKepentinganService.save(listTmp);
        LOG.info("updateUrusan :: finish");
    }

    private void updateUrusanBaru(Permohonan permohonan, InfoAudit info) {

        HakmilikUrusan hu = hakmilikService.findByIdPerserahan(permohonan.getIdPermohonan());

        List<HakmilikPihakBerkepentingan> listTmp = new ArrayList<HakmilikPihakBerkepentingan>();

        List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();

        List<PermohonanHubungan> senaraiHubungan = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), null);

        List<Long> SENARAI_URUSAN = new ArrayList<Long>();

        String kod = permohonan.getKodUrusan().getKod();

        for (PermohonanHubungan ph : senaraiHubungan) {
            if (ph == null) {
                continue;
            }
//            HakmilikUrusan hurusan = hakmilikService.findByIdPerserahan(ph.getPermohonanSasaran().getIdPermohonan());

            HakmilikUrusan hurusan = hakmilikService.findByIdPerserahanIdHakmilik(ph.getPermohonanSasaran().getIdPermohonan(), ph.getCatatan());
            if (hurusan == null) {
                continue;
            }

            if (kod.equalsIgnoreCase("JMGPJ") && hurusan.getKodUrusan().getKod().startsWith("PJ")) {
                //do nothing. testing by sptb, no need to batal this kind of urusan (pjt, pjbt, pjkt, pjkbt);
                // just replace owner of those urusan.
            } else {
                SENARAI_URUSAN.add(hurusan.getIdUrusan());
                hurusan.setUrusanBatal(hu);
                hurusan.setTarikhBatal(permohonan.getInfoAudit().getTarikhMasuk());
                hurusan.setIdPermohonanBatal(permohonan.getIdPermohonan());
                hurusan.setAktif('T');
                hurusan.setFolderDokumenBatal(permohonan.getFolderDokumen());
                if (hurusan.getCawangan() == null) {
                    hurusan.setCawangan(hurusan.getHakmilik().getCawangan());
                }
                if (hurusan.getDaerah() == null) {
                    hurusan.setDaerah(hurusan.getHakmilik().getDaerah());
                }
                if (hurusan.getInfoAudit() != null) {
                    info = hurusan.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    hurusan.setInfoAudit(info);
                }
                hakmilikUrusanList.add(hurusan);
            }

            List<HakmilikPihakBerkepentingan> hpList = hakmilikPihakKepentinganService.findHakmilikPihakByIdUrusan(hurusan);
            for (HakmilikPihakBerkepentingan h : hpList) {
                h.setAktif('T');
                h.setPerserahanBatal(hu);
                info = h.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                h.setInfoAudit(info);
                listTmp.add(h);
            }

        }

        if ("KVSP".equals(kod)) {
            List<HakmilikUrusan> senaraiUrusan = hakmilikService.findUrusan(SENARAI_URUSAN, "KVSPC");
            for (HakmilikUrusan hakmilikUrusan : senaraiUrusan) {
                hakmilikUrusan.setAktif('T');
                hakmilikUrusan.setIdPermohonanBatal(permohonan.getIdPermohonan());
                hakmilikUrusan.setUrusanBatal(hu);
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                hakmilikUrusan.setInfoAudit(info);
                hakmilikUrusanList.add(hakmilikUrusan);
            }
        } else if ("JMB".equals(kod)) {
            List<PermohonanAtasPerserahan> list = permohonan.getSenaraiPermohonanAtasPerserahan();
            for (PermohonanAtasPerserahan pap : list) {
                HakmilikUrusan hakmilikUrusan = pap.getUrusan();
                hakmilikUrusan.setAktif('T');
                hakmilikUrusan.setIdPermohonanBatal(permohonan.getIdPermohonan());
                hakmilikUrusan.setUrusanBatal(hu);
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                hakmilikUrusan.setInfoAudit(info);
                hakmilikUrusanList.add(hakmilikUrusan);
            }
        } else if ("GDL".equals(kod)) {
            List<PermohonanAtasPerserahan> list = permohonan.getSenaraiPermohonanAtasPerserahan();
            for (PermohonanAtasPerserahan pap : list) {
                HakmilikUrusan hakmilikUrusan = pap.getUrusan();
                hakmilikUrusan.setAktif('T');
                hakmilikUrusan.setIdPermohonanBatal(permohonan.getIdPermohonan());
                hakmilikUrusan.setUrusanBatal(hu);
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                hakmilikUrusan.setInfoAudit(info);
                hakmilikUrusanList.add(hakmilikUrusan);
            }
        }

        hakmilikService.saveOrUpdate(hakmilikUrusanList);
        hakmilikPihakKepentinganService.save(listTmp);
    }

//    private void updateNoVersi(Permohonan permohonan, InfoAudit infoAudit) throws Exception {
//        //todo check if there is no DHKE generate
//        LOG.debug("[ updateNoVersi start ]");
//        List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
//        List<Hakmilik> senaraiHakmilikUpdate = new ArrayList<Hakmilik>();
//
//        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
//            if (hp == null || hp.getHakmilik() == null) {
//                continue;
//            }
//            Hakmilik hm = hp.getHakmilik();
//            LOG.debug("hakmilik to update no versi [ " + hm.getIdHakmilik() + " ]");
//            infoAudit = hm.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(pengguna);
//            infoAudit.setTarikhKemaskini(new Date());
//            if (permohonan.getKeputusan().getKod().equals("D")) {
//                hm.setNoVersiDhde(hm.getNoVersiDhde() + 1);
//                hm.setNoVersiDhke(hm.getNoVersiDhke() + 1);
//            }
//            hm.setInfoAudit(infoAudit);
//            senaraiHakmilikUpdate.add(hm);
//        }
//
//        if (!senaraiHakmilikUpdate.isEmpty()) {
//            regService.simpanHakmilikList(senaraiHakmilikUpdate);
//        }
//        LOG.debug("[ updateNoVersi finish ]");
//    }
    private void batalPerserahan(Permohonan permohonan, InfoAudit info) {
        if (permohonan != null) {
            InfoAudit ia = new InfoAudit();
            ia = permohonan.getInfoAudit();
            ia.setTarikhKemaskini(new Date());
            ia.setDiKemaskiniOleh(pengguna);
            permohonan.setStatus("TK");
            permohonan.setInfoAudit(ia);
            permohonanService.saveOrUpdateConn(permohonan);
        }
    }

    private void insertPemegangAmanah(Permohonan permohonan, InfoAudit info) {

        HakmilikUrusan hu = hakmilikService.findByIdPerserahan(permohonan.getIdPermohonan());

        List<Pemohon> senaraiPemohon = permohonan.getSenaraiPemohon();
        for (Pemohon pemohon : senaraiPemohon) {
            if (pemohon == null || pemohon.getPihak() == null) {
                continue;
            }
            HakmilikPihakBerkepentingan hp = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(pemohon.getPihak(), pemohon.getHakmilik());
            if (hp == null) {
                continue;
            }
            hp.setAktif('T');
            hp.setInfoAudit(info);
            hp.setPerserahanBatal(hu);
            hakmilikPihakKepentinganService.save(hp);
        }

        List<PermohonanPihak> senaraiMohonPihak = permohonan.getSenaraiPihak();
        for (PermohonanPihak permohonanPihak : senaraiMohonPihak) {
            if (permohonanPihak == null
                    || permohonanPihak.getPihak() == null || (!permohonanPihak.getJenis().getKod().equals("PA"))) {
                continue;
            }
            HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
            hp.setAktif('Y');
            hp.setJenis(kodJenisPihakBerkepentinganDAO.findById("PA"));
            hp.setHakmilik(permohonanPihak.getHakmilik());
            hp.setCawangan(permohonanPihak.getHakmilik().getCawangan());
            hp.setKaveatAktif('T');
            hp.setPihak(permohonanPihak.getPihak());
            hp.setNama(permohonanPihak.getNama());
            hp.setAlamat1(permohonanPihak.getAlamat().getAlamat1());
            hp.setAlamat2(permohonanPihak.getAlamat().getAlamat2());
            hp.setAlamat3(permohonanPihak.getAlamat().getAlamat3());
            hp.setAlamat4(permohonanPihak.getAlamat().getAlamat4());
            hp.setPoskod(permohonanPihak.getAlamat().getPoskod());
            hp.setNegeri(permohonanPihak.getAlamat().getNegeri());
            hp.setNoPengenalan(permohonanPihak.getNoPengenalan());
            hp.setJenisPengenalan(permohonanPihak.getJenisPengenalan());
            hp.setSyerPembilang(permohonanPihak.getSyerPembilang());
            hp.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
            hp.setJumlahPembilang(permohonanPihak.getSyerPembilang());
            hp.setJumlahPenyebut(permohonanPihak.getSyerPenyebut());
            hp.setPerserahan(hu);
            hp.setInfoAudit(info);
            hakmilikPihakKepentinganService.saveProvidedConn(hp);

            if (permohonanPihak.getSenaraiHubungan().size() > 0) {
                for (PermohonanPihakHubungan waris : permohonanPihak.getSenaraiHubungan()) {
                    HakmilikWaris hw = new HakmilikWaris();
                    hw.setInfoAudit(info);
                    hw.setJenisPengenalan(waris.getJenisPengenalan());
                    hw.setPemegangAmanah(hp);
                    hw.setNama(waris.getNama());
                    hw.setNoPengenalan(waris.getNoPengenalan());
                    hw.setSyerPembilang(hp.getSyerPembilang());
                    hw.setSyerPenyebut(hp.getSyerPenyebut());
                    hw.setCawangan(hp.getCawangan());
                    hakmilikWarisDAO.save(hw);
                }
            }
        }
    }

    private void updatePemegangAmanah(Permohonan permohonan, InfoAudit info) {

        List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        HakmilikUrusan hakmilikUrusan = hakmilikService.findByIdPerserahan(permohonan.getIdPermohonan());

        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            List<PermohonanPihak> senaraiMohonPihak = permohonanPihakService.getSenaraiMohonPihakForMultipleHakmilik(permohonan.getIdPermohonan(), hm.getIdHakmilik());

            HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
            for (PermohonanPihak permohonanPihak : senaraiMohonPihak) {
                hpk.setPihak(permohonanPihak.getPihak());
                hpk.setAktif('Y');
                hpk.setJenis(permohonanPihak.getJenis());
                hpk.setWargaNegara(permohonanPihak.getWargaNegara());
                hpk.setInfoAudit(info);
                hpk.setHakmilik(hp.getHakmilik());
                hpk.setCawangan(hp.getHakmilik().getCawangan());
                hpk.setKaveatAktif('T');
                hpk.setJenisPengenalan(permohonanPihak.getJenisPengenalan());
                hpk.setNoPengenalan(permohonanPihak.getNoPengenalan());
                hpk.setAlamat1(permohonanPihak.getAlamat().getAlamat1());
                hpk.setAlamat2(permohonanPihak.getAlamat().getAlamat2());
                hpk.setAlamat3(permohonanPihak.getAlamat().getAlamat3());
                hpk.setAlamat4(permohonanPihak.getAlamat().getAlamat4());
                hpk.setPoskod(permohonanPihak.getAlamat().getPoskod());
                hpk.setNegeri(permohonanPihak.getAlamat().getNegeri());
                hpk.setNama(permohonanPihak.getNama());
                hpk.setPerserahan(hakmilikUrusan);
                hpk.setSyerPembilang(permohonanPihak.getSyerPembilang());
                hpk.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
                hpk.setJumlahPembilang(permohonanPihak.getSyerPembilang());
                hpk.setJumlahPenyebut(permohonanPihak.getSyerPenyebut());
                hpk.setInfoAudit(info);
                hakmilikPihakKepentinganService.saveProvidedConn(hpk);

            }
            List<PermohonanAtasPihakBerkepentingan> senaraiAtasPihak = permohonanAtasPihakBerkepentinganService.findPihakByIdMohonIdHakmilik(permohonan, hm);
            for (PermohonanAtasPihakBerkepentingan permohonanAtasPihak : senaraiAtasPihak) {
                HakmilikPihakBerkepentingan hpk2 = permohonanAtasPihak.getPihakBerkepentingan();
                hpk2.setAktif('T');
                info = hpk2.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                hpk2.setInfoAudit(info);
                hakmilikPihakKepentinganService.saveProvidedConn(hpk2);

                List<HakmilikWaris> senaraiWarisTmp = new ArrayList<HakmilikWaris>();
                List<HakmilikWaris> senarai2 = hakmilikWarisService.getSenaraiWarisByIdHakmilikPihak(String.valueOf(hpk2.getIdHakmilikPihakBerkepentingan()));
                for (HakmilikWaris hw : senarai2) {
                    hw.setInfoAudit(info);
                    hw.setPemegangAmanah(hpk);
                    senaraiWarisTmp.add(hw);
                }
                hakmilikWarisService.save(senaraiWarisTmp);
            }

        }
    }

    private void updateSW(Permohonan permohonan, InfoAudit info) {
        List<PermohonanAtasPerserahan> senaraiUrusan = permohonan.getSenaraiPermohonanAtasPerserahan();

        List<HakmilikUrusan> senaraiUrusan2 = new ArrayList<HakmilikUrusan>();

        for (PermohonanAtasPerserahan permohonanAtasPerserahan : senaraiUrusan) {
            if (permohonanAtasPerserahan == null || permohonanAtasPerserahan.getUrusan() == null) {
                continue;
            }
            if (permohonanAtasPerserahan.getUrusan().getKodUrusan().getKod().equals("SW")) {
//                HakmilikUrusan hu = hakmilikService.findById(permohonanAtasPerserahan.getUrusan().getIdUrusan());
                HakmilikUrusan hu = permohonanAtasPerserahan.getUrusan();
                hu.setAktif('Y');
                hu.setInfoAudit(info);
                senaraiUrusan2.add(hu);
            }
        }

        hakmilikService.saveOrUpdate(senaraiUrusan2);
    }

    private void updateSWBaru(Permohonan permohonan, InfoAudit info) {

        List<PermohonanHubungan> senaraiHubungan = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), null);

        List<HakmilikUrusan> senaraiUrusan = new ArrayList<HakmilikUrusan>();

        for (PermohonanHubungan ph : senaraiHubungan) {
            if (ph == null) {
                continue;
            }
            HakmilikUrusan hurusan = hakmilikService.findByIdPerserahan(ph.getPermohonanSasaran().getIdPermohonan());
            if (hurusan == null) {
                continue;
            }
            if (hurusan.getKodUrusan().getKod().equalsIgnoreCase("SW")) {
                hurusan.setAktif('Y');
                hurusan.setInfoAudit(info);
                senaraiUrusan.add(hurusan);
            }
        }
        hakmilikService.saveOrUpdate(senaraiUrusan);
    }

    private void batalPihakPM(Permohonan permohonan, InfoAudit info) {
        LOG.info("**************batalPihakPM start************");
//        HakmilikUrusan hu = hakmilikService.findByIdPerserahan(permohonan.getIdPermohonan());
//        LOG.info("hakmilikUrusan :: " + hu.getIdPerserahan());
        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            if (hp == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            if (hm == null) {
                continue;
            }

            HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hm.getIdHakmilik());
            if (hu == null) {
                continue;
            }
            LOG.info("hakmilikUrusan :: " + hu.getIdPerserahan());

            List<HakmilikPihakBerkepentingan> senaraiPB = hm.getSenaraiPihakBerkepentingan();
//                List<Pemohon> pemohon = syerService.findPemohonByPermohonan(permohonan);
            List<Pemohon> pemohon = permohonan.getSenaraiPemohon();
//                List<PermohonanPihak> ppList = permohonan.getSenaraiPihak();
            for (Pemohon pp : pemohon) {//only update pihak yg memohon sahaja
                Pihak p1 = pp.getPihak();
                LOG.info("mohon pihak :" + p1.getIdPihak());
                for (HakmilikPihakBerkepentingan hpk : senaraiPB) {
                    if (!hpk.getJenis().getKod().equalsIgnoreCase("PM")) {
                        continue;
                    }
                    if (hpk.getAktif() == 'T') {
                        continue;
                    }
                    Pihak p2 = hpk.getPihak();
                    LOG.info("hakmilik pihak :" + p2.getIdPihak());
                    if (!p2.equals(p1)) {
                        continue;
                    }
                    hpk.setPerserahanBatal(hu);
                    info = hpk.getInfoAudit();
                    info.setTarikhKemaskini(new Date());
                    info.setDiKemaskiniOleh(pengguna);
                    hpk.setInfoAudit(info);
                    hpk.setAktif('T');
                    hakmilikPihakKepentinganService.saveProvidedConn(hpk);
                }
            }
        }
        LOG.info("**************batalPihakPM finish************");
    }

    //batal certain urusan
    //replace old PB ke new PB
    //urusan still refer to old urusan
    private void updInsAll(Permohonan permohonan, InfoAudit info) {
        long start = System.currentTimeMillis();
        LOG.debug("***UpdInsAll start***");

//        List<PermohonanAtasPerserahan> senaraiT = permohonan.getSenaraiPermohonanAtasPerserahan();
        List<PermohonanHubungan> senaraiHubungan = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), null);

        List<HakmilikUrusan> senaraiUrusanBatal = new ArrayList<HakmilikUrusan>();
        List<HakmilikPihakBerkepentingan> senaraiPBKemaskini = new ArrayList<HakmilikPihakBerkepentingan>();
        List<PermohonanPihak> senaraiMohonPihak = permohonan.getSenaraiPihak();
        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        HakmilikUrusan hu = hakmilikService.findByIdPerserahan(permohonan.getIdPermohonan());

        for (PermohonanHubungan ph : senaraiHubungan) {
            if (ph == null) {
                continue;
            }
            HakmilikUrusan hurusan = hakmilikService.findByIdPerserahan(ph.getPermohonanSasaran().getIdPermohonan());

            List<HakmilikPihakBerkepentingan> hpList = hakmilikPihakKepentinganService.findHakmilikPihakByIdUrusan(hurusan);
            if (hurusan.getKodUrusan().getKod().equals("GDPJ")) {
                info = hurusan.getInfoAudit();
                hurusan.setUrusanBatal(hu);
                hurusan.setTarikhBatal(permohonan.getInfoAudit().getTarikhMasuk());
                hurusan.setIdPermohonanBatal(permohonan.getIdPermohonan());
                hurusan.setAktif('T');
                hurusan.setFolderDokumenBatal(permohonan.getFolderDokumen());
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                hurusan.setInfoAudit(info);
                senaraiUrusanBatal.add(hurusan);
            }

            for (HakmilikPihakBerkepentingan h : hpList) {
                info = h.getInfoAudit();
                h.setAktif('T');
                h.setPerserahanBatal(hu);
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                h.setInfoAudit(info);
                senaraiPBKemaskini.add(h);
            }

            for (PermohonanPihak pp : senaraiMohonPihak) {
                if (hurusan.getKodUrusan().getKod().equals("GD")) {
                    if (!pp.getJenis().getKod().equals("PG")) {
                        continue;
                    }
                }

                if (hurusan.getKodUrusan().getKod().equals("PJT")
                        || hurusan.getKodUrusan().getKod().equals("PJBT")) {
                    if (!pp.getJenis().getKod().equals("PJ")) {
                        continue;
                    }
                }

                if (hurusan.getKodUrusan().getKod().equals("PJKT")
                        || hurusan.getKodUrusan().getKod().equals("PJKBT")) {
                    if (!pp.getJenis().getKod().equals("PJK")) {
                        continue;
                    }
                }

                Pihak pihak = pp.getPihak();
                for (HakmilikPermohonan hp : senaraiHakmilik) {
                    if (!pp.getHakmilik().equals(hp.getHakmilik())) {
                        continue;
                    }
                    info = new InfoAudit();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new Date());
                    HakmilikPihakBerkepentingan h = new HakmilikPihakBerkepentingan();
                    h.setKaveatAktif('T');
                    h.setHakmilik(hp.getHakmilik());
                    h.setCawangan(hp.getHakmilik().getCawangan());
                    h.setAktif('Y');
                    h.setPihak(pihak);
                    h.setJenis(pp.getJenis());
                    h.setInfoAudit(info);
                    h.setPerserahan(hurusan);//refer old urusan
                    h.setSyerPembilang(pp.getSyerPembilang());
                    h.setSyerPenyebut(pp.getSyerPenyebut());
                    h.setJumlahPembilang(pp.getSyerPembilang());
                    h.setJumlahPenyebut(pp.getSyerPenyebut());
                    h.setNama(pp.getNama());
                    h.setJenisPengenalan(pp.getJenisPengenalan());
                    h.setNoPengenalan(pp.getNoPengenalan());
                    h.setAlamat1(pp.getAlamat().getAlamat1());
                    h.setAlamat2(pp.getAlamat().getAlamat2());
                    h.setAlamat3(pp.getAlamat().getAlamat3());
                    h.setAlamat4(pp.getAlamat().getAlamat4());
                    h.setPoskod(pp.getAlamat().getPoskod());
                    h.setNegeri(pp.getAlamat().getNegeri());
                    senaraiPBKemaskini.add(h);
                }
            }
        }
        hakmilikService.saveOrUpdate(senaraiUrusanBatal);
        hakmilikPihakKepentinganService.save(senaraiPBKemaskini);
        LOG.debug("***UpdInsAll finish***");
        LOG.debug("**** finish=" + (System.currentTimeMillis() - start));
    }

    private void doAktifDeaktifPemilik(Permohonan permohonan, InfoAudit info) {
//        List<PermohonanAtasPerserahan> senarai = permohonan.getSenaraiPermohonanAtasPerserahan();

        List<PermohonanHubungan> senaraiHubungan = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), null);

        for (PermohonanHubungan ph : senaraiHubungan) {
            if (ph == null) {
                continue;
            }
            HakmilikUrusan hurusan = hakmilikService.findByIdPerserahan(ph.getPermohonanSasaran().getIdPermohonan());
            if (hurusan == null) {
                continue;
            }

            Permohonan permohonanLama = permohonanService.findById(hurusan.getIdPerserahan());
            if (permohonanLama == null) {
                continue;
            }
            List<Pemohon> senaraiPemohon = permohonanLama.getSenaraiPemohon();
            List<HakmilikPermohonan> senaraiHakmilik = permohonanLama.getSenaraiHakmilik();
            List<HakmilikPihakBerkepentingan> senaraiHP = new ArrayList<HakmilikPihakBerkepentingan>();
            List<PermohonanPihak> senaraiMohonPihak = permohonanLama.getSenaraiPihak();
            //ex : pmtb -- > pembatalan pmt .. batalkan current pihak, aktifkan prev pihak
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hm.getIdHakmilik());
                for (Pemohon pmhn : senaraiPemohon) {
                    if (pmhn == null) {
                        continue;
                    }
                    List<HakmilikPihakBerkepentingan> senaraiHpk = hakmilikPihakKepentinganService.getHakmilikPihakTidakAktif(pmhn.getPihak(), hm, "PM");

                    if (senaraiHpk.isEmpty()) {
                        continue;
                    }

                    for (HakmilikPihakBerkepentingan hpk : senaraiHpk) {
                        hpk.setAktif('Y');
                        hpk.setPerserahanBatal(null);
                        hpk.setInfoAudit(info);
                        senaraiHP.add(hpk);
                    }
                }

                for (PermohonanPihak pp : senaraiMohonPihak) {
                    if (pp == null) {
                        continue;
                    }
                    String jenisPB = pp.getJenis().getKod();
                    HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihakPMPPMG(pp.getPihak(), hm, jenisPB);

                    if (hpk == null) {
                        continue;
                    }
                    hpk.setAktif('T');
                    hpk.setInfoAudit(info);
                    hpk.setPerserahanBatal(hu);
                    senaraiHP.add(hpk);
                }
            }
            hakmilikPihakKepentinganService.save(senaraiHP);
        }
    }

    private void aktifPemilikUrusanSblm(Permohonan permohonan, InfoAudit info) {
        List<PermohonanHubungan> senaraiHubungan = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), null);

        for (PermohonanHubungan ph : senaraiHubungan) {
            if (ph == null) {
                continue;
            }
            HakmilikUrusan hurusan = hakmilikService.findByIdPerserahan(ph.getPermohonanSasaran().getIdPermohonan());
            if (hurusan == null) {
                continue;
            }

            Permohonan permohonanLama = permohonanService.findById(hurusan.getIdPerserahan());
            if (permohonanLama == null) {
                continue;
            }
            List<Pemohon> senaraiPemohon = permohonanLama.getSenaraiPemohon();
            List<HakmilikPermohonan> senaraiHakmilik = permohonanLama.getSenaraiHakmilik();
            List<HakmilikPihakBerkepentingan> senaraiHP = new ArrayList<HakmilikPihakBerkepentingan>();
            List<PermohonanPihak> senaraiMohonPihak = permohonanLama.getSenaraiPihak();
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                for (Pemohon pmhn : senaraiPemohon) {
                    if (pmhn == null) {
                        continue;
                    }
                    List<HakmilikPihakBerkepentingan> senaraiHpk = hakmilikPihakKepentinganService.getHakmilikPihakTidakAktif(pmhn.getPihak(), hm, "PM");
                }

                for (PermohonanPihak pp : senaraiMohonPihak) {
                    if (pp == null) {
                        continue;
                    }
                    String jenisPB = pp.getJenis().getKod();
                    HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihakPMPPMG(pp.getPihak(), hm, jenisPB);

                    if (hpk == null) {
                        continue;
                    }
                    hpk.setAktif('Y');
                    hpk.setInfoAudit(info);
                    senaraiHP.add(hpk);
                }
            }
            hakmilikPihakKepentinganService.save(senaraiHP);
        }
    }

    private void batalHakmilik(Permohonan permohonan, InfoAudit info) {
        List<Hakmilik> senaraiTmp = new ArrayList<Hakmilik>();
        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            hm.setKodStatusHakmilik(kodStatusHakmilikDAO.findById("B"));
            hm.setInfoAudit(info);
            senaraiTmp.add(hm);
        }
        hakmilikService2.saveHakmilik(senaraiTmp);
    }

    private void hidupHakmilik(Permohonan permohonan, InfoAudit info) {
        List<Hakmilik> senaraiTmp = new ArrayList<Hakmilik>();
        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            hm.setKodStatusHakmilik(kodStatusHakmilikDAO.findById("D"));
            hm.setInfoAudit(info);
            senaraiTmp.add(hm);

            if (permohonan.getKodUrusan().getKod().equals("PMHHB")) {
                Akaun akaun = regService.getAkaunLama(hm.getIdHakmilik());
                if (akaun != null) {
                    akaun.setStatus(kodStatusAkaunDAO.findById("A"));
                    info = akaun.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new Date());
                    akaun.setInfoAudit(info);
                    regService.saveOrUpdate(akaun);
                }
            }
        }
        hakmilikService2.saveHakmilik(senaraiTmp);
    }

    /*Untuk Urusan Yang Menggunakan PermohonanAtasPerserahan. Cth: TMAMB */
    private void tukarStatusHakmilikPihak(Permohonan permohonan, InfoAudit info) {

        LOG.info("Start : tukarStatusHakmilikPihak() : Urusan :" + permohonan.getKodUrusan().getKod());

        List<PermohonanAtasPerserahan> senaraiPermohonanAtasPerserahan = permohonan.getSenaraiPermohonanAtasPerserahan();

        for (PermohonanAtasPerserahan pap : senaraiPermohonanAtasPerserahan) {
            if (pap == null) {
                continue;
            }

            LOG.info("ID PermohonanAtasPerserahan :" + pap.getIdAtasUrusan());
            HakmilikUrusan hakmilikUrusan = pap.getUrusan();
            if (hakmilikUrusan == null) {
                continue;
            }

            LOG.info("ID HakmilikUrusan(hakmilikUrusan :" + hakmilikUrusan.getIdUrusan());
            Permohonan permohonanLama = permohonanService.findById(hakmilikUrusan.getIdPerserahan());
            if (permohonanLama == null) {
                continue;
            }
            LOG.info("ID Permohonan Lama :" + permohonanLama.getIdPermohonan());
            List<HakmilikPihakBerkepentingan> senaraiHP = new ArrayList<HakmilikPihakBerkepentingan>();

            List<HakmilikPermohonan> senaraiHakmilik = permohonanLama.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                HakmilikUrusan hu = hakmilikService.findByIdPerserahanIdHakmilik(permohonanLama.getIdPermohonan(), hm.getIdHakmilik());
                if (hu == null) {
                    continue;
                }
                LOG.info("ID HakmilikUrusan(hu) :" + hu.getIdUrusan());

                List<Pemohon> senaraiPemohon = permohonanLama.getSenaraiPemohon();
                for (Pemohon pmhn : senaraiPemohon) {
                    if (pmhn == null) {
                        continue;
                    }

                    List<HakmilikPihakBerkepentingan> senaraiHpk = hakmilikPihakKepentinganService.getHakmilikPihakTidakAktif(pmhn.getPihak(), hm, "PM");
                    if (senaraiHpk.isEmpty()) {
                        continue;
                    }

                    for (HakmilikPihakBerkepentingan hpk : senaraiHpk) {
                        LOG.info("ID Hakmilik Pihak (Pemohon):" + hpk.getIdHakmilikPihakBerkepentingan());
                        hpk.setAktif('Y');
                        hpk.setPerserahanBatal(null);
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        hpk.setInfoAudit(info);

                        senaraiHP.add(hpk);
                    }
                }

                List<PermohonanPihak> senaraiMohonPihak = permohonanLama.getSenaraiPihak();
                for (PermohonanPihak pp : senaraiMohonPihak) {
                    if (pp == null) {
                        continue;
                    }
                    String jenisPB = pp.getJenis().getKod();
                    HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihakPMPPMG(pp.getPihak(), hm, jenisPB);

                    if (hpk == null) {
                        continue;
                    }
                    LOG.info("ID Hakmilik Pihak (PermohonanPihak):" + hpk.getIdHakmilikPihakBerkepentingan());
                    hpk.setAktif('T');
                    hpk.setPerserahanBatal(hu);
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    hpk.setInfoAudit(info);
                    senaraiHP.add(hpk);
                }
            }
            hakmilikPihakKepentinganService.save(senaraiHP);
        }
        LOG.info("End : tukarStatusHakmilikPihak() : Urusan :" + permohonan.getKodUrusan().getKod());
    }

    /*Membatalkan HakmilikUrusan*/
    private void tukarStatusHakmilikUrusan(Permohonan permohonan, InfoAudit info) {

        LOG.info("Start : tukarStatusHakmilikUrusan() : Urusan :" + permohonan.getKodUrusan().getKod());

        List<PermohonanAtasPerserahan> senaraiPermohonanAtasPerserahan = permohonan.getSenaraiPermohonanAtasPerserahan();

        for (PermohonanAtasPerserahan pap : senaraiPermohonanAtasPerserahan) {
            if (pap == null) {
                continue;
            }

            LOG.info("ID PermohonanAtasPerserahan :" + pap.getIdAtasUrusan());
            HakmilikUrusan hakmilikUrusan = pap.getUrusan();
            if (hakmilikUrusan == null) {
                continue;
            }
            LOG.info("ID HakmilikUrusan(hakmilikUrusan :" + hakmilikUrusan.getIdUrusan());

            hakmilikUrusan.setAktif('T');
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            hakmilikUrusan.setInfoAudit(info);
            hakmilikService.saveOrUpdate(hakmilikUrusan);
            LOG.info("Urusan :" + permohonan.getKodUrusan().getKod() + " Membatalkan Urusan :" + hakmilikUrusan.getKodUrusan().getKod());

        }
        LOG.info("End : tukarStatusHakmilikUrusan() : Urusan :" + permohonan.getKodUrusan().getKod());
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        LOG.debug("@beforeGenerateReports [ start ]");

        Permohonan permohonan = ctx.getPermohonan();
        List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
        if (permohonan != null) {
            String idKump = permohonan.getIdKumpulan();
            if (StringUtils.isNotBlank(idKump)) {
                senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKump);
            } else {
                senaraiPermohonan.add(permohonan);
            }
        } else {
            ctx.addMessage("ID Permohonan tidak dikenali.");
            return false;
        }

        for (Permohonan mohon : senaraiPermohonan) {
            if (mohon.getKeputusan() == null) {
                ctx.addMessage("Sila masukan keputusan untuk perserahan [" + mohon.getIdPermohonan() + "]");
                return false;
            }
            if (mohon.getKeputusan().getKod().equals(DAFTAR)) {
                List<HakmilikPermohonan> senaraiHakmilikTerlibat = mohon.getSenaraiHakmilik();
//                for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
//                    if (!ctx.isByPass()) {
//                        Date d = mohon.getInfoAudit().getTarikhMasuk();
//                        if (permohonanService.isPrevUrusanNotComplete(hp.getHakmilik().getIdHakmilik(), d)) {
//                            ctx.addMessage("Terdapat urusan sebelum yang masih belum selesai.");
//                            return false;
//                        }
//                    }
//                }

                String kodUrusan = mohon.getKodUrusan().getKod();
                pengguna = ctx.getPengguna();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                RegistrationModule rm = RegistrationModule.valueOf(kodUrusan);
                int urusan = rm.getCode();
                LOG.debug("kod urusan = " + kodUrusan + "[" + urusan + "]");

                try {
//                    if (mohon.getKodUrusan().getKod().equalsIgnoreCase("JMB")) {
//                        updatePihak(mohon, info);
//                    }
//                    if (mohon.getKodUrusan().getKod().equalsIgnoreCase("JPB")) {
//                        updatePihak(mohon, info);
//                    }
                } catch (Exception ex) {
                    Throwable t = ex;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    ctx.addMessage("Pendaftaran perserahan " + mohon.getIdPermohonan()
                            + " Tidak Berjaya.Sila Hubungi Pentadbir Sistem.\r[ " + ex.toString() + " ]");
                    return false;
                }

            }
        }
        LOG.debug("@beforeGenerateReports [ success ]");
        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        //increase no versi of VDOC if push back
        Permohonan permohonan = context.getPermohonan();
        if (permohonan != null) {
            List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
            List<Dokumen> senaraiDokumenToUpdate = new ArrayList<Dokumen>();

            String idKumpulan = permohonan.getIdKumpulan();

            if (StringUtils.isNotBlank(idKumpulan)) {
                senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKumpulan);
            } else {
                senaraiPermohonan.add(permohonan);
            }

            for (Permohonan p : senaraiPermohonan) {
                if (p == null || p.getFolderDokumen() == null) {
                    continue;
                }
                List<KandunganFolder> senaraiKandungan = p.getFolderDokumen().getSenaraiKandungan();
                for (KandunganFolder kf : senaraiKandungan) {
                    if (kf == null || kf.getDokumen() == null) {
                        continue;
                    }
                    Dokumen d = kf.getDokumen();
                    if (d.getKodDokumen() == null) {
                        continue;
                    }
                    if (d.getKodDokumen().getKod().equalsIgnoreCase("VDOC")) {
                        Double noVersi = Double.parseDouble(d.getNoVersi());
                        d.setNoVersi(String.valueOf(noVersi + 1));
                        senaraiDokumenToUpdate.add(d);
                    }
                }
            }

            if (!senaraiDokumenToUpdate.isEmpty()) {
                dokumenService.saveOrUpdate(senaraiDokumenToUpdate);
            }
        }
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        proposedOutcome = "back";
        return proposedOutcome;
    }

    private void berangkaiGantung(Permohonan permohonan, String idKump) {

        senaraiPermohonanBaru = new ArrayList<Permohonan>();
        senaraiPermohonanXdaftar = new ArrayList<Permohonan>();
        senaraiPermohonanXdaftar.clear();
        senaraiPermohonanBaru.clear();

        if (StringUtils.isNotBlank(idKump)) {
            List<Permohonan> senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKump);
            for (Permohonan mohon : senaraiPermohonan) {
                if (mohon.getKeputusan().equals(DAFTAR)) {
                    senaraiPermohonanBaru.add(mohon);
                }
                if (mohon.getKeputusan().equals(GANTUNG)) {
                    senaraiPermohonanXdaftar.add(mohon);
                }
            }
            int bilLama = senaraiPermohonanBaru.size();
            int bilBaru = senaraiPermohonanBaru.size();
            if (bilLama != bilLama) {
                for (Permohonan mohonXdaftar : senaraiPermohonanXdaftar) {
                    mohonXdaftar.setIdKumpulan(null);
                }
            }
        }
    }

    private void calculateJumSyer(Permohonan permohonan) {

        List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();

        for (HakmilikPermohonan hakmilik : senaraiHakmilikTerlibat) {
            if (hakmilik == null) {
                continue;
            }
            Hakmilik hm = hakmilik.getHakmilik();
            List<HakmilikPihakBerkepentingan> senaraiPihak = hm.getSenaraiPihakBerkepentingan();

            StringBuilder sb = new StringBuilder("SELECT hp.pihak.idPihak , hp.jenis.kod ")
                    .append("FROM etanah.model.HakmilikPihakBerkepentingan hp ")
                    .append("WHERE hp.aktif = 'Y' ")
                    .append("AND hp.hakmilik.idHakmilik = :idHakmilik ")
                    .append("GROUP BY hp.pihak.idPihak, hp.jenis.kod, hp.hakmilik.idHakmilik ");
//                    .append("HAVING COUNT(hp.pihak.idPihak) > 1 ");

            Query q = sessionProvider.get().createQuery(sb.toString())
                    .setParameter("idHakmilik", hm.getIdHakmilik());

            List<Object[]> params = q.list();

            for (Object[] obj : params) {
                Fraction jumSyer = Fraction.ZERO;
                Long idPihak = (Long) obj[0];
                String kod = (String) obj[1];
                List<HakmilikPihakBerkepentingan> pihakTerlibat = new ArrayList<HakmilikPihakBerkepentingan>();

                for (HakmilikPihakBerkepentingan hp : senaraiPihak) {
                    if (hp.getPihak().getIdPihak() == idPihak
                            && kod.equals(hp.getJenis().getKod())
                            && hp.getAktif() == 'Y') {
                        jumSyer = jumSyer.add(new Fraction(hp.getSyerPembilang(), hp.getSyerPenyebut()));
                    }
                }

                for (HakmilikPihakBerkepentingan hp : senaraiPihak) {
                    if (hp.getPihak().getIdPihak() == idPihak
                            && kod.equals(hp.getJenis().getKod())
                            && hp.getAktif() == 'Y') {
//                       hp.setJumlahPembilang(jumSyer.getNumerator());
//                       hp.setJumlahPenyebut(jumSyer.getDenominator());
//                       pihakTerlibat.add(hp);
                    }
                }

                hakmilikPihakKepentinganService.save(pihakTerlibat);
            }

        }

    }

    public PermohonanPihakKemaskini getPermohonanPihakKemaskini() {
        return permohonanPihakKemaskini;
    }

    public void setPermohonanPihakKemaskini(PermohonanPihakKemaskini permohonanPihakKemaskini) {
        this.permohonanPihakKemaskini = permohonanPihakKemaskini;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihakBaru() {
        return senaraiPermohonanPihakBaru;
    }

    public void setSenaraiPermohonanPihakBaru(List<PermohonanPihak> senaraiPermohonanPihakBaru) {
        this.senaraiPermohonanPihakBaru = senaraiPermohonanPihakBaru;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihakLama() {
        return senaraiPermohonanPihakLama;
    }

    public void setSenaraiPermohonanPihakLama(List<PermohonanPihak> senaraiPermohonanPihakLama) {
        this.senaraiPermohonanPihakLama = senaraiPermohonanPihakLama;
    }

    public List<Permohonan> getSenaraiPermohonanBaru() {
        return senaraiPermohonanBaru;
    }

    public void setSenaraiPermohonanBaru(List<Permohonan> senaraiPermohonanBaru) {
        this.senaraiPermohonanBaru = senaraiPermohonanBaru;
    }

    public List<Permohonan> getSenaraiPermohonanXdaftar() {
        return senaraiPermohonanXdaftar;
    }

    public void setSenaraiPermohonanXdaftar(List<Permohonan> senaraiPermohonanXdaftar) {
        this.senaraiPermohonanXdaftar = senaraiPermohonanXdaftar;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanCarianDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PortalPenggunaDAO;
import etanah.dao.PortalTransaksiDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikSebelum;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanCarian;
import etanah.model.PortalTransaksi;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.view.daftar.carian.ws.CarianWebServices;
import etanah.view.dokumen.ws.DMSWebServices;
import etanah.view.dokumen.ws.DokumenInfo;
import etanah.view.etanahContextListener;
import etanah.view.portal.ws.service.UtilCarianService;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class CarianPersendirianEbayarService {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    private etanah.Configuration conf;
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    @Inject
    PortalTransaksiDAO portalTransaksiDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    UAMPortal uamservice;
    @Inject
    TerimaBayaranServices bayar;
    @Inject
    PenggunaDAO pguna;
    @Inject
    CarianWebServices web;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DMSWebServices dms;
    @Inject
    PermohonanCarianDAO permohonanCarianDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    PortalPenggunaDAO portalPenggunaDAO;
    @Inject
    Permohonan permohonan;
    @Inject
    PermohonanDAO permohonanDAO;

    UtilCarianService utilCarianService = injector.getInstance(UtilCarianService.class);

    ;

    public String updateCarianAccount(String idHakmilik, String PAYMENT_TRANS_ID, String PAYMENT_DATETIME, BigDecimal bd, String idPengguna, String RECEIPT_NO, String namaPenyerah) throws ParseException {
//        PortalTransaksi portalTransaksi = utilCarianService.findByNofpx(PAYMENT_TRANS_ID);
        PortalTransaksi portalTransaksi = utilCarianService.findByNoresit(RECEIPT_NO, "CP");

        String noresit = "";
        try {
        } catch (Exception ex) {
        } finally {
        }
        if (portalTransaksi == null) {
            Session sess = injector.getProvider(Session.class).get();
            Transaction tx1 = sess.beginTransaction();
            tx1.begin();
            portalTransaksi = new PortalTransaksi();
            
            portalTransaksi.setAmaun(new BigDecimal(20));
            portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
            portalTransaksi.setIdPengguna(idPengguna);
            portalTransaksi.setJenisTrans("CP");
            portalTransaksi.setAmaun(bd);
            portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
            portalTransaksi.setNoResit(RECEIPT_NO);
            portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
            tx1.commit();
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna.findById("portal"));
            info.setTarikhMasuk(new Date());
            KodUrusan urusan = null;
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Date convertedDate = df.parse(PAYMENT_DATETIME);
            BayarValue ba = bayar.terimaBayaran(idHakmilik, "00", bd, PAYMENT_TRANS_ID, PAYMENT_TRANS_ID, "carian", convertedDate);
            noresit = ba.getIdKewdok();
            
            Hakmilik h = hakmilikDAO.findById(idHakmilik);
            if (StringUtils.isNotBlank(h.getIdHakmilikInduk())) {
                urusan = kodUrusanDAO.findById("CSHS");
            } else {
                urusan = kodUrusanDAO.findById("CSHM");
            }
            String idPermohonan = idPerserahanGenerator.generate(
                    conf.getProperty("kodNegeri"), h.getCawangan(), urusan);
            String[] idHakmiliks = {idHakmilik};
            PermohonanCarian carian = web.doInsertCarian(idHakmiliks, idPermohonan, pguna.findById("portal"), urusan, dokumenKewanganDAO.findById(noresit));
            Transaction tx = sess.beginTransaction();
            tx.begin();
            FolderDokumen fd = new FolderDokumen();
            fd.setInfoAudit(info);
            fd.setTajuk("carian persendirian online");
            folderDokumenDAO.save(fd);
            Dokumen dok = new Dokumen();
            dok.setKodDokumen(kodDokumenDAO.findById("CS"));
            dok.setInfoAudit(info);
            dok = saveOrUpdateDokumen(fd, kodDokumenDAO.findById("CS"), carian.getIdCarian());
            dokumenDAO.save(dok);
            Transaksi trans = new Transaksi();
            trans = uamservice.getTransaksiByNoResit(carian.getResit().getIdDokumenKewangan());
            String path = fd.getFolderId() + File.separator + String.valueOf(dok.getIdDokumen());
            String dokumenPath = conf.getProperty("document.path");
            if ("CSHS".equals(urusan.getKod())) {
                reportUtil.generateReport("regCatatanCarianHMSB4K.rdf", new String[]{"p_id_hakmilik", "p_carian"},
                        new String[]{idHakmilik, idPermohonan}, dokumenPath + path, pguna.findById("portal"));
            } else {
                reportUtil.generateReport("regCatatanCarianHMml.rdf", new String[]{"p_id_hakmilik", "p_carian", "p_trans"},
                        new String[]{idHakmilik, idPermohonan, String.valueOf(trans.getIdTransaksi())}, dokumenPath + path, pguna.findById("portal"));
            }

            updatePathDokumen(reportUtil.getDMSPath(), dok.getIdDokumen(), reportUtil.getContentType());
            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(dok);
            kf.setFolder(fd);
            kf.setInfoAudit(info);
            kandunganFolderDAO.save(kf);
            carian.setPenyerahNama(namaPenyerah);
            carian.setFolderDokumen(fd);
            carian.setTrans(ba.getTrans());
            permohonanCarianDAO.save(carian);
            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
            portalTransaksi.setAmaun(new BigDecimal(20));
            portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
            portalTransaksi.setHakmilik(hakmilik);
            portalTransaksi.setIdPengguna(idPengguna);
            portalTransaksi.setJenisTrans("CP");
            portalTransaksi.setMohon(carian.getIdCarian());
            portalTransaksi.setMohon(idPermohonan);
            portalTransaksi.setAmaun(bd);
            portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
            portalTransaksi.setNoResit(RECEIPT_NO);
            portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
            portalTransaksi.setTrhResit(convertedDate);
            portalTransaksi.setIdKewDok(noresit);
            portalTransaksi.setBilPaparan(1);
            portalTransaksi.setInfoAudit(info);
            save(portalTransaksi);
            tx.commit();
        }
        return noresit;
    }

    public String updateCarianPortalTrans(String idDokumen, String idmohon, String idHakmilik) throws ParseException {
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        String noresit = "";
        KodUrusan urusan = null;

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna.findById("portal"));
        info.setTarikhMasuk(new Date());
        
        Hakmilik h = hakmilikDAO.findById(idHakmilik);
            if (StringUtils.isNotBlank(h.getIdHakmilikInduk())) {
                urusan = kodUrusanDAO.findById("CSHS");
            } else {
                urusan = kodUrusanDAO.findById("CSHM");
            }

        permohonan = permohonanDAO.findById(idmohon);
        Dokumen dok = dokumenDAO.findById(Long.parseLong(idDokumen));
        PermohonanCarian carian = permohonanCarianDAO.findById(idmohon);

        noresit = carian.getResit().getIdDokumenKewangan();

        FolderDokumen fd = new FolderDokumen();
        fd.setInfoAudit(info);
        fd.setTajuk("carian persendirian online");
        folderDokumenDAO.save(fd);

        Transaksi trans = new Transaksi();
        trans = uamservice.getTransaksiByNoResit(carian.getResit().getIdDokumenKewangan());
        String path = fd.getFolderId() + File.separator + String.valueOf(dok.getIdDokumen());
        String dokumenPath = conf.getProperty("document.path");
        if ("CSHS".equals(urusan.getKod())) {
            reportUtil.generateReport("regCatatanCarianHMSB4K.rdf", new String[]{"p_id_hakmilik", "p_carian"},
                    new String[]{idHakmilik, idmohon}, dokumenPath + path, pguna.findById("portal"));
        } else {
            reportUtil.generateReport("regCatatanCarianHMml.rdf", new String[]{"p_id_hakmilik", "p_carian", "p_trans"},
                    new String[]{idHakmilik, idmohon, String.valueOf(trans.getIdTransaksi())}, dokumenPath + path, pguna.findById("portal"));
        }

        updatePathDokumen(reportUtil.getDMSPath(), Long.parseLong(idDokumen), reportUtil.getContentType());
        KandunganFolder kf = new KandunganFolder();
        kf.setDokumen(dok);
        kf.setFolder(fd);
        kf.setInfoAudit(info);
        kandunganFolderDAO.save(kf);
        tx.commit();

        return noresit;
    }

    @Transactional
    public PortalTransaksi save(PortalTransaksi p) {
        return portalTransaksiDAO.saveOrUpdate(p);
    }

    /*
     kew_dokumen
     cara_bayar EP
     kew_dokumen_bayar
     kew_trans
     */
    public Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;

        doc = new Dokumen();

        ia.setDimasukOleh(pguna.findById("portal"));
        ia.setTarikhMasuk(new java.util.Date());
        doc.setBaru('Y');
        doc.setNoVersi("1.0");

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd != null ? kd.getKod() : "" + "-" + id);

        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);

        return doc;
    }

    public void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenDAO.save(d);
    }

    public List<DokumenCarianPersendirian> senaraiDokumen(String idPengguna, String tarikhmula, String tarikhtamat) {
        List<DokumenCarianPersendirian> listDokumen = new ArrayList<DokumenCarianPersendirian>();

        String query;
        Session session = injector.getProvider(Session.class
        ).get();

        query = "SELECT pt FROM etanah.model.PortalTransaksi pt"
                + " where pt.idPengguna = :idPengguna and pt.bilPaparan <=3 and pt.jenisTrans = 'CP' order by pt.trhResit desc";
        Query q = session.createQuery(query);
        q.setMaxResults(200);
        q.setString(
                "idPengguna", idPengguna);
        for (int i = 0;
                i < q.list()
                .size(); i++) {
            DokumenCarianPersendirian dc = new DokumenCarianPersendirian();
            PortalTransaksi pt = (PortalTransaksi) q.list().get(i);
            dc.setIdPortalTransaksi(pt.getIdTransaksi());
            dc.setIdHakmilik(pt.getHakmilik().getIdHakmilik());
            dc.setBilPaparan(pt.getBilPaparan());
            dc.setTarikh(uamservice.formatDate(pt.getTrhResit()));
            listDokumen.add(dc);
        }
        return listDokumen;
    }
    
     public List<DokumenCarianPersendirian> senaraiDokumenIdHakmilik(String idPengguna, String tarikhmula, String tarikhtamat, String idHakmilik) {
        List<DokumenCarianPersendirian> listDokumen = new ArrayList<DokumenCarianPersendirian>();

        String query;
        Session session = injector.getProvider(Session.class
        ).get();

        query = "SELECT pt FROM etanah.model.PortalTransaksi pt"
                + " where pt.idPengguna = :idPengguna and pt.bilPaparan <=3 and pt.jenisTrans = 'CP' and pt.hakmilik.idHakmilik = :idHakmilik";
        Query q = session.createQuery(query);

        q.setString(
                "idPengguna", idPengguna);
        q.setString("idHakmilik", idHakmilik);
        for (int i = 0;
                i < q.list()
                .size(); i++) {
            DokumenCarianPersendirian dc = new DokumenCarianPersendirian();
            PortalTransaksi pt = (PortalTransaksi) q.list().get(i);
            dc.setIdPortalTransaksi(pt.getIdTransaksi());
            dc.setIdHakmilik(pt.getHakmilik().getIdHakmilik());
            dc.setBilPaparan(pt.getBilPaparan());
            dc.setTarikh(uamservice.formatDate(pt.getTrhResit()));
            listDokumen.add(dc);
        }
        return listDokumen;
    }

    public DokumenInfo
            getdokumencarian(Long id) {
        Session sess = injector.getProvider(Session.class
        ).get();

        Transaction tx = sess.beginTransaction();

        tx.begin();

        PortalTransaksi pp = portalTransaksiDAO.findById(id);
        long idDokumen = 0;
        PermohonanCarian carian = permohonanCarianDAO.findById(pp.getMohon());
        FolderDokumen fd = folderDokumenDAO.findById(carian.getFolderDokumen().getFolderId());
        List< KandunganFolder> listkf = uamservice.getSenaraiKandunganFolder(fd.getFolderId());
        for (KandunganFolder kf : listkf) {
            if (kf.getDokumen().getKodDokumen().getKod().equals("CS")) {
                idDokumen = kf.getDokumen().getIdDokumen();
                break;
            }
        }

        pp.setBilPaparan(pp.getBilPaparan() + 1);
        pp.getInfoAudit()
                .setDiKemaskiniOleh(pguna.findById("portal"));
        pp.getInfoAudit()
                .setTarikhKemaskini(new Date());
        save(pp);

        tx.commit();

        return dms.downloadDokumen(idDokumen);
    }

    List<CarianInfo> checkAccount(String accountNo, String idHakmilik) {
        idHakmilik = idHakmilik.toUpperCase();
        List<CarianInfo> list = new ArrayList<CarianInfo>();
        String statusAkaun = new String();
        Date tarikhMasuk = null;
        boolean flagBatal = false;
        List<HakmlikInfo> listHakmilikInfo = null;
        String query = "SELECT a FROM etanah.model.Akaun a"
                + " where a.noAkaun = :accountNo "
                + "or a.hakmilik.idHakmilik = :idHakmilik ";
        Session session = injector.getProvider(Session.class
        ).get();
        Query q = session.createQuery(query);

        q.setString(
                "accountNo", accountNo);
        q.setString(
                "idHakmilik", idHakmilik);

        for (int i = 0;
                i < q.list()
                .size(); i++) {
            listHakmilikInfo = new ArrayList<HakmlikInfo>();
            Akaun a = (Akaun) q.list().get(i);
            idHakmilik = a.getHakmilik().getIdHakmilik();
            accountNo = a.getNoAkaun();
            statusAkaun = a.getStatus().getKod();
            if(statusAkaun.equals("A")){
            break;
            }
        }

        if (statusAkaun.equals(
                "B")) {
            flagBatal = true;
        }
        if (!flagBatal) {
            listHakmilikInfo = new ArrayList<HakmlikInfo>();
            HakmlikInfo aci = new HakmlikInfo();
            aci.setNoAkaun(accountNo);
            aci.setIdHakmilik(idHakmilik);
            listHakmilikInfo.add(aci);
        }
        if (flagBatal) {
            listHakmilikInfo = findHakmilikValid(accountNo, idHakmilik);
            if (!listHakmilikInfo.isEmpty()) {
                CarianInfo aig = new CarianInfo();
                aig.setIdHakmilik(idHakmilik);
                aig.setNoAkaun(accountNo);
                aig.setAkaunBatal(true);
                list.add(aig);
            }
        }

        for (HakmlikInfo ahi : listHakmilikInfo) {
            CarianInfo aig = setCarianInfoByHakmilik(ahi.getIdHakmilik());
            list.add(aig);
        }
        return list;
    }

    private List<HakmlikInfo> findHakmilikValid(String accountNo, String idHakmilik) {
        List<HakmlikInfo> listHakmilik = new ArrayList<HakmlikInfo>();

        String query = "SELECT hs FROM etanah.model.HakmilikSebelum hs, etanah.model.Akaun ak"
                + " where hs.hakmilikSebelum = :idHakmilik "
                + " and hs.hakmilik.kodStatusHakmilik.kod = 'D' "
                + " and ak.hakmilik.idHakmilik = hs.hakmilik.idHakmilik "
                + " and ak.status.kod = 'A'";
        Session session = injector.getProvider(Session.class
        ).get();
        Query q = session.createQuery(query);

        q.setString(
                "idHakmilik", idHakmilik);
        for (int i = 0;
                i < q.list()
                .size(); i++) {
            HakmilikSebelum hakmilikSBLM = (HakmilikSebelum) q.list().get(i);
            HakmlikInfo info = new HakmlikInfo();
            info.setIdHakmilik(hakmilikSBLM.getHakmilik().getIdHakmilik());
            info.setNoAkaun(hakmilikSBLM.getHakmilik().getAkaunCukai().getNoAkaun());
            listHakmilik.add(info);

        }
        return listHakmilik;
    }

    private CarianInfo setCarianInfoByHakmilik(String idHakmilik) {
        CarianInfo ca = new CarianInfo();
        String query2 = "SELECT a FROM etanah.model.Akaun a"
                + " where a.noAkaun = :accountNo "
                + "or a.hakmilik.idHakmilik = :idHakmilik and a.status.kod ='A'";
        Session session1 = injector.getProvider(Session.class
        ).get();
        Query q2 = session1.createQuery(query2);

        q2.setString(
                "accountNo", idHakmilik);
        q2.setString(
                "idHakmilik", idHakmilik);
        Akaun aa = (Akaun) q2.uniqueResult();
        String alamat = trimAlamat(aa.getPemegang().getSuratAlamat1(), aa.getPemegang().getSuratAlamat2(),
                aa.getPemegang().getSuratAlamat3(), aa.getPemegang().getSuratAlamat4(), aa.getPemegang().getSuratPoskod());

        ca.setAlamat(alamat);
//        ca.setNamaPemilik(aa.getHakmilik().getSenaraiPihakBerkepentingan() != null ? aa.getHakmilik().getSenaraiPihakBerkepentingan().get(0).getNama() : "");

        ca.setNamaPemilik(
                !utilCarianService.listPB(idHakmilik).isEmpty() ? utilCarianService.listPB(idHakmilik).get(0).getNama() : "");
        ca.setBandarpekanmukim(aa.getHakmilik().getBandarPekanMukim().getNama());
        ca.setIdHakmilik(aa.getHakmilik().getIdHakmilik());
        ca.setJenisHakmilik(aa.getHakmilik().getKodHakmilik().getNama());
        ca.setNoHakmilik(aa.getHakmilik().getNoHakmilik());
        ca.setNoBangunan(aa.getHakmilik().getNoBangunan());
        ca.setNoTingkat(aa.getHakmilik().getNoTingkat());
        ca.setNoPetak(aa.getHakmilik().getNoPetak());
        ca.setKodDaerah(aa.getHakmilik().getDaerah().getKod());
        ca.setKodCaw(aa.getHakmilik().getCawangan().getKod());
        ca.setKodCawValue(aa.getCawangan().getName());

        return ca;
    }

    private String trimAlamat(String alamat1, String alamat2, String alamat3,
            String alamat4, String poskod) {
        String completeAddress = "";

        String fragments[] = {alamat1, alamat2, alamat3, alamat4, poskod};

        for (String frag : fragments) {
            if (frag != null && !(frag.equals(""))) {
                // remove comma (,) from the fragments if have
                if (frag.charAt(frag.length() - 1) == ',') {
                    frag = frag.substring(0, frag.length() - 1);
                }

                // trim it to make it nice
                frag.trim();

                // combines all
                if (completeAddress.equals("")) {
                    completeAddress += frag;
                } else {
                    completeAddress += ", " + frag;
                }
            }
        }

        return completeAddress;
    }

    List<CarianInfo> checkAccountByNoLot(String daerah, String bpm, String seksyen, String kodLot, Integer noLot) {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(7);
        df.setMinimumIntegerDigits(7);
        df.setGroupingUsed(false);
        Hakmilik ha = utilCarianService.cariHakmilikByNoLotPlot(daerah, bpm, seksyen, kodLot, df.format(noLot));
        if (ha != null) {
            return checkAccount(ha.getIdHakmilik(), ha.getIdHakmilik());
        } else {
            return null;
        }
    }

    List<CarianInfo> checkAccountByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik) {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(8);
        df.setMinimumIntegerDigits(8);
        df.setGroupingUsed(false);
        Hakmilik ha = utilCarianService.checkAccountByParam(daerah, bpm, seksyen, kodJenishakmilik, df.format(Integer.parseInt(noHakmilik)));
        if (ha != null) {
            return checkAccount(ha.getIdHakmilik(), ha.getIdHakmilik());
        } else {
            return null;
        }
    }

    List<CarianInfo> checkAccountStrataByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik, String noBangunan, String noTingkat, String noPetak) {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(8);
        df.setMinimumIntegerDigits(8);
        df.setGroupingUsed(false);
        Hakmilik ha = utilCarianService.checkAccountStrataByParam(daerah, bpm, seksyen, kodJenishakmilik, df.format(Integer.parseInt(noHakmilik)), noBangunan, noTingkat, noPetak);
        if (ha != null) {
            return checkAccount(ha.getIdHakmilik(), ha.getIdHakmilik());
        }
        return null;
    }

    List<CarianInfo> checkAccountStrataByNoLot(String daerah, String bpm, String seksyen, String kodLot, Integer noLot, String noBangunan, String noTingkat, String noPetak) {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(7);
        df.setMinimumIntegerDigits(7);
        df.setGroupingUsed(false);
        Hakmilik ha = utilCarianService.cariHakmilikStrataByNoLotPlot(daerah, bpm, seksyen, kodLot, df.format(noLot), noBangunan, noTingkat, noPetak);
        if (ha != null) {
            return checkAccount(ha.getIdHakmilik(), ha.getIdHakmilik());
        } else {
            return null;
        }
    }

}

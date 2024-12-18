/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.Configuration;
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
import etanah.dao.PortalPenggunaDAO;
import etanah.dao.PortalTransaksiDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.PermohonanCarian;
import etanah.model.PortalPengguna;
import etanah.model.PortalTransaksi;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.view.daftar.carian.ws.CarianWebServices;
import etanah.view.dokumen.ws.DMSWebServices;
import etanah.view.dokumen.ws.DokumenInfo;
import etanah.view.etanahContextListener;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class CarianPersendirianServices {

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

    public String updateCarianAccount(String idHakmilik, String PAYMENT_TRANS_ID, String PAYMENT_DATETIME, BigDecimal bd, String idPengguna, String TRANS_ID, String RECEIPT_NO) throws ParseException {
        PortalTransaksi portalTransaksi = new PortalTransaksi();
        portalTransaksi = portalTransaksiDAO.findById(Long.valueOf(TRANS_ID));
        String noresit = "";
        if (StringUtils.isEmpty(portalTransaksi.getNoFpx())) {
            PortalPengguna pp = portalPenggunaDAO.findById(idPengguna);
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna.findById("portal"));
            info.setTarikhMasuk(new Date());
            KodUrusan urusan = kodUrusanDAO.findById("CSHM");
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Date convertedDate = df.parse(PAYMENT_DATETIME);
            BayarValue ba = bayar.terimaBayaran(idHakmilik, "00", bd, PAYMENT_TRANS_ID, PAYMENT_TRANS_ID, "carian", convertedDate);
            noresit = ba.getIdKewdok();
            Session sess = injector.getProvider(Session.class).get();

            String idPermohonan = idPerserahanGenerator.generate(
                    conf.getProperty("kodNegeri"), pguna.findById("portal").getKodCawangan(), urusan);
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
            String path = fd.getFolderId() + File.separator + String.valueOf(dok.getIdDokumen());
            String dokumenPath = conf.getProperty("document.path");
            reportUtil.generateReport("regCatatanCarianHM.rdf", new String[]{"p_id_hakmilik", "p_carian"},
                    new String[]{idHakmilik, idPermohonan}, dokumenPath + path, pguna.findById("portal"));
            updatePathDokumen(reportUtil.getDMSPath(), dok.getIdDokumen(), reportUtil.getContentType());
            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(dok);
            kf.setFolder(fd);
            kf.setInfoAudit(info);
            kandunganFolderDAO.save(kf);
            carian.setPenyerahNama(pp.getNama());
//            carian.setPenyerahAlamat1(pp.getAlamat());
            carian.setFolderDokumen(fd);
            carian.setTrans(ba.getTrans());
            permohonanCarianDAO.save(carian);
            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);

            if (portalTransaksi == null) {
                portalTransaksi = new PortalTransaksi();
                portalTransaksi.setAmaun(new BigDecimal(20));
                portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
                portalTransaksi.setHakmilik(hakmilik);
                portalTransaksi.setIdPengguna(idPengguna);
                portalTransaksi.setJenisTrans("CR");
                portalTransaksi.setMohon(carian.getIdCarian());

            }
            portalTransaksi.setMohon(idPermohonan);
            portalTransaksi.setAmaun(bd);
            portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
            portalTransaksi.setNoResit(RECEIPT_NO);
            portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
            portalTransaksi.setTrhResit(convertedDate);
            portalTransaksi.setIdKewDok(noresit);
            portalTransaksi.setBilPaparan(0);
            portalTransaksi.setInfoAudit(info);
            save(portalTransaksi);
            tx.commit();
        }

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
        doc.setTajuk(kd.getKod() + "-" + id);

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
        Session session = injector.getProvider(Session.class).get();

        query = "SELECT pt FROM etanah.model.PortalTransaksi pt"
                + " where pt.idPengguna = :idPengguna and pt.bilPaparan <=3 and pt.jenisTrans = 'CP' ";
        Query q = session.createQuery(query);
        q.setString("idPengguna", idPengguna);
        for (int i = 0; i < q.list().size(); i++) {
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

    public DokumenInfo getdokumencarian(Long id) {
        Session sess = injector.getProvider(Session.class).get();

        Transaction tx = sess.beginTransaction();
        tx.begin();

        PortalTransaksi pp = portalTransaksiDAO.findById(id);
        long idDokumen = 0;
        PermohonanCarian carian = permohonanCarianDAO.findById(pp.getMohon());
        for (KandunganFolder kf : carian.getFolderDokumen().getSenaraiKandungan()) {
            if (kf.getDokumen().getKodDokumen().getKod().equals("CS")) {
                idDokumen = kf.getDokumen().getIdDokumen();
                break;
            }
        }
        pp.setBilPaparan(pp.getBilPaparan() + 1);
        pp.getInfoAudit().setDiKemaskiniOleh(pguna.findById("portal"));
        pp.getInfoAudit().setTarikhKemaskini(new Date());
        save(pp);
        tx.commit();
        return dms.downloadDokumen(idDokumen);
    }

}

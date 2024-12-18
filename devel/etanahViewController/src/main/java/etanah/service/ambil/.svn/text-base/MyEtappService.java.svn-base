/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ambil;

import com.Ostermiller.util.Base64;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.EtappPermohonanDAO;
import etanah.dao.InfoWartaDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanEtappDAO;
import etanah.dao.PermohonanEtappHakmilikDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Alamat;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.KodPeringkat;
import etanah.model.KodRujukan;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.ambil.InfoWarta;
import etanah.model.ambil.PermohonanEtapp;
import etanah.model.ambil.PermohonanEtappHakmilik;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.etapp.EtappHakmilikSambungan;
import etanah.model.etapp.EtappPermohonan;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etanahContextListener;
import etanah.view.etapp.EtappHakmilikSambunganForm;
import etanah.view.etapp.ws.form.MaklumatPermohonanSek4MyEtaPP;
import etanah.view.etapp.ws.form.MaklumatPermohonanSek8MyEtaPP;
import java.io.ByteArrayInputStream;
import java.io.File;
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
 * @author zipzap
 */
public class MyEtappService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Injector injector = etanahContextListener.getInjector();
    @Inject
    etanah.Configuration conf;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    InfoWartaDAO infoWartaDAO;
    @Inject
    KodPeringkatDAO kodPeringkatDAO;
    @Inject
    EtappPermohonanDAO etappPermohonanDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanEtappDAO permohonanEtappDAO;
    @Inject
    PermohonanEtappHakmilikDAO permohonanEtappHakmilikDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Transactional
    public InfoWarta saveInfoWarta(String noWarta, String tarikhWarta, Permohonan mohon, String kodPeringkat) throws ParseException {
        InfoWarta warta = new InfoWarta();
        KodPeringkat kod = kodPeringkatDAO.findById(kodPeringkat);
        warta.setFailJKPTG(noWarta);
        warta.setNoWarta(noWarta);
        warta.setPermohonan(mohon);
        warta.setTrhWarta(sdf.parse(tarikhWarta));
        warta.setInfoAudit(setIA());
        warta.setKodPeringkat(kod);
        infoWartaDAO.saveOrUpdate(warta);
        return warta;
    }

    public InfoAudit setIA() {
        InfoAudit infoAudit = new InfoAudit();
        Pengguna p = penggunaDAO.findById("portal");
        infoAudit.setTarikhMasuk(new Date());
        infoAudit.setDimasukOleh(p);
        return infoAudit;
    }

    public KodDokumen findKodDokumenByMyetappKod(String docTypeEtaPP) {
        String query = "SELECT h FROM etanah.model.KodDokumen h where h.kodMyEtapp = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", docTypeEtaPP);
        return (KodDokumen) q.uniqueResult();
    }

    public Dokumen uploadDokumen(String kodDokumenMyEtapp, String tajuk, String convertBacktoNormalString, String fileName, String contentType, String catatan, String idPermohonan) {
        Dokumen doc = new Dokumen();
        try {
            String dokumenPath = conf.getProperty("document.path");
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
            KodDokumen kod = findKodDokumenByMyetappKod(kodDokumenMyEtapp);

            doc.setTajuk(kod.getNama() + "-" + catatan);
            doc.setInfoAudit(setInfoAudit());
            doc.setKodDokumen(kod);
            doc.setKlasifikasi(klasifikasiAm);
            doc.setNoVersi("1.0");
            doc = dokumenDAO.saveOrUpdate(doc);

            DMSUtil dmsUtil = new DMSUtil();
            byte[] b = Base64.decodeToBytes(convertBacktoNormalString);
            FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            fileUtil.saveFile(fileName, in);
            String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(doc);
            kf.setFolder(permohonan.getFolderDokumen());
            kf.setInfoAudit(setInfoAudit());
            updateKandunganFolder(kf);
            updatePathDokumen(fizikalPath, doc, contentType);

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return doc;
    }

    public void createDokumen(KodDokumen kod, Permohonan permohonan) {
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        Dokumen doc = new Dokumen();
        doc.setTajuk(kod.getNama() + "-" + kod.getNama());
        doc.setInfoAudit(setInfoAudit());
        doc.setKodDokumen(kod);
        doc.setKlasifikasi(klasifikasiAm);
        doc.setNoVersi("1.0");
        doc = dokumenDAO.saveOrUpdate(doc);
        KandunganFolder kf = new KandunganFolder();
        kf.setDokumen(doc);
        kf.setFolder(permohonan.getFolderDokumen());
        kf.setInfoAudit(setInfoAudit());
        updateKandunganFolder(kf);
        tx.commit();
    }

    @Transactional
    private void updatePathDokumen(String namaFizikal, Dokumen d, String format) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
//        Session sess = injector.getProvider(Session.class).get();
//        Transaction tx = sess.beginTransaction();
//        tx.begin();

        d = dokumenDAO.saveOrUpdate(d);
//        if (d != null) {
//            tx.commit();
//        } else {
//            tx.rollback();
//        }
    }

    @Transactional
    private void updateKandunganFolder(KandunganFolder kf) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");

//        Session sess = injector.getProvider(Session.class).get();
//        Transaction tx = sess.beginTransaction();
//        tx.begin();
        kf = kandunganFolderDAO.saveOrUpdate(kf);
//        if (kf != null) {
//            tx.commit();
//        } else {
//            tx.rollback();
//        }
    }

    public InfoAudit setInfoAudit() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = penggunaDAO.findById("portal");
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        return ia;
    }

    public KodUOM findByKodUOMMyEtapp(String kod_luas_ambilEtaPP) {
        String query = "SELECT h FROM etanah.model.KodUOM h where h.kodMyeTapp = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod_luas_ambilEtaPP);
        return (KodUOM) q.uniqueResult();
    }

    public EtappPermohonan findByidPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.etapp.EtappPermohonan h where h.mohon.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (EtappPermohonan) q.uniqueResult();
    }

    public EtappPermohonan saveEtapp(MaklumatPermohonanSek4MyEtaPP maklumatPermohonanSek4Form, Permohonan mohon) throws ParseException {
        EtappPermohonan etapp = new EtappPermohonan();
        etapp.setMohon(mohon);
        etapp.setInfoAudit(setIA());
        etapp.setNoFail(maklumatPermohonanSek4Form.getNo_fail_jkptgEtaPP());
        KodDaerah daerah = kodDaerahDAO.findById(maklumatPermohonanSek4Form.getKod_daerah_pengambilanEtaPP());
        etapp.setDaerah(daerah);
        saveEtappPermohonan(etapp);
        savePemohonmaklumatPermohonan(maklumatPermohonanSek4Form, mohon);
        saveMohonAmbilmaklumatPermohonan(maklumatPermohonanSek4Form, mohon);
        saveMohonRujLuar(maklumatPermohonanSek4Form, mohon);
        return etapp;
    }

    @Transactional
    private void saveEtappPermohonan(EtappPermohonan etapp) {
        etappPermohonanDAO.saveOrUpdate(etapp);
    }

    @Transactional
    private void savePemohon(Pemohon pemohon) {
        pemohonDAO.saveOrUpdate(pemohon);
    }

    @Transactional
    private void saveMohonAmbil(PermohonanPengambilan mohonAmbil) {
        permohonanPengambilanDAO.saveOrUpdate(mohonAmbil);
    }

    private void savePemohonmaklumatPermohonan(MaklumatPermohonanSek4MyEtaPP a, Permohonan permohonan) {
        Pemohon pemohon = new Pemohon();
        pemohon.setPermohonan(permohonan);
        pemohon.setNama(a.getNama_agensiEtaPP());
        Alamat alamat = new Alamat();
        alamat.setAlamat1(a.getAlamat1EtaPP());
        alamat.setAlamat2(a.getAlamat2EtaPP());
        alamat.setAlamat3(a.getAlamat3EtaPP());
        alamat.setAlamat4(a.getAlamat4EtaPP());
        alamat.setPoskod(a.getPoskodEtaPP());
        if (StringUtils.isNotBlank(a.getKodNegeriEtaPP())) {
            KodNegeri negeri = kodNegeriDAO.findById(a.getKodNegeriEtaPP());
            alamat.setNegeri(negeri);
        }
        pemohon.setAlamat(alamat);
        pemohon.setInfoAudit(setIA());
        pemohon.setCawangan(permohonan.getCawangan());
        savePemohon(pemohon);
    }

    private void saveMohonAmbilmaklumatPermohonan(MaklumatPermohonanSek4MyEtaPP a, Permohonan permohonan) {
        PermohonanPengambilan mohonAmbil = new PermohonanPengambilan();
        mohonAmbil.setPermohonan(permohonan);
        mohonAmbil.setTujuanPermohonan(a.getTujuanEtaPP());
        mohonAmbil.setInfoAudit(setIA());
        saveMohonAmbil(mohonAmbil);
    }

    private void saveMohonRujLuar(MaklumatPermohonanSek4MyEtaPP maklumatPermohonanSek4Form, Permohonan mohon) throws ParseException {
        PermohonanRujukanLuar r = new PermohonanRujukanLuar();
        r.setCawangan(mohon.getCawangan());
        r.setPermohonan(mohon);
        KodRujukan ruj = new KodRujukan();
        ruj.setKod("NF");
        r.setKodRujukan(ruj);
        r.setNoRujukan(maklumatPermohonanSek4Form.getNo_rujukan_surat_kjpEtaPP());
        r.setTarikhRujukan(StringUtils.isNotBlank(maklumatPermohonanSek4Form.getTarikh_surat_kjpEtaPP()) ? sdf.parse(maklumatPermohonanSek4Form.getTarikh_surat_kjpEtaPP()) : null);
        savePermohonanRujukanLuar(r);
    }

    @Transactional
    private void savePermohonanRujukanLuar(PermohonanRujukanLuar ruj) {
        permohonanRujukanLuarDAO.saveOrUpdate(ruj);
    }

    public EtappPermohonan saveEtappSek8(MaklumatPermohonanSek8MyEtaPP maklumatPermohonan, Permohonan mohon) throws ParseException {
        EtappPermohonan etapp = new EtappPermohonan();
        etapp.setMohon(mohon);
        etapp.setInfoAudit(setIA());
        etapp.setNoFail(maklumatPermohonan.getNo_fail_jkptgEtaPP());
        KodDaerah daerah = kodDaerahDAO.findById(maklumatPermohonan.getKod_daerah_pengambilanEtaPP());
        etapp.setDaerah(daerah);
        saveEtappPermohonan(etapp);
        savePemohonmaklumatPermohonanSek8(maklumatPermohonan, mohon);
        saveMohonAmbilmaklumatPermohonanSek8(maklumatPermohonan, mohon);
        saveMohonRujLuarSek8(maklumatPermohonan, mohon);
        return etapp;
    }

    private void savePemohonmaklumatPermohonanSek8(MaklumatPermohonanSek8MyEtaPP a, Permohonan permohonan) {
        Pemohon pemohon = new Pemohon();
        pemohon.setPermohonan(permohonan);
        pemohon.setNama(a.getNama_agensiEtaPP());
        Alamat alamat = new Alamat();
        alamat.setAlamat1(a.getAlamat1EtaPP());
        alamat.setAlamat2(a.getAlamat2EtaPP());
        alamat.setAlamat3(a.getAlamat3EtaPP());
        alamat.setAlamat4(a.getAlamat4EtaPP());
        alamat.setPoskod(a.getPoskodEtaPP());
        KodNegeri negeri = kodNegeriDAO.findById(a.getKodNegeriEtaPP());
        alamat.setNegeri(negeri);
        pemohon.setAlamat(alamat);
        pemohon.setInfoAudit(setIA());
        pemohon.setCawangan(permohonan.getCawangan());
        savePemohon(pemohon);
    }

    private void saveMohonAmbilmaklumatPermohonanSek8(MaklumatPermohonanSek8MyEtaPP a, Permohonan permohonan) {
        PermohonanPengambilan mohonAmbil = new PermohonanPengambilan();
        mohonAmbil.setPermohonan(permohonan);
        mohonAmbil.setTujuanPermohonan(a.getTujuanEtaPP());
        mohonAmbil.setInfoAudit(setIA());
        saveMohonAmbil(mohonAmbil);
    }

    private void saveMohonRujLuarSek8(MaklumatPermohonanSek8MyEtaPP maklumatPermohonan, Permohonan mohon) throws ParseException {
        PermohonanRujukanLuar r = new PermohonanRujukanLuar();
        r.setCawangan(mohon.getCawangan());
        r.setPermohonan(mohon);
        KodRujukan ruj = new KodRujukan();
        ruj.setKod("NF");
        r.setKodRujukan(ruj);
        r.setNoRujukan(maklumatPermohonan.getNo_rujukan_surat_kjpEtaPP());
        r.setTarikhRujukan(StringUtils.isNotBlank(maklumatPermohonan.getTarikh_surat_kjpEtaPP()) ? sdf.parse(maklumatPermohonan.getTarikh_surat_kjpEtaPP()) : null);
        savePermohonanRujukanLuar(r);
    }

    public PermohonanEtapp findMohonEtappByidPermohonan(String idPermohonan) {
        return permohonanEtappDAO.findById(idPermohonan);
    }

    public void savePermohonanEtapp(PermohonanEtapp mohonEtapp) {
        permohonanEtappDAO.save(mohonEtapp);
    }

    public List<HakmilikPermohonan> findHakmilikByIdPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.HakmilikPermohonan h where h.permohonan.idPermohonan = :idPermohonan"
                + " and h.hakmilikAmbil = 'A'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findHakmilikByIdPermohonanKodCaw(String idPermohonan, String kodCaw) {
        String query = "SELECT h FROM etanah.model.HakmilikPermohonan h where h.permohonan.idPermohonan = :idPermohonan"
                + " and h.hakmilikAmbil = 'A' and h.hakmilik.cawangan.kod =:kodCaw";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    public InfoWarta findInfoWarta(String idPermohonan, String kodPeringkat) {
        String query = "SELECT h FROM etanah.model.ambil.InfoWarta h where h.permohonan.idPermohonan = :idPermohonan"
                + " and h.kodPeringkat.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kodPeringkat);
        return (InfoWarta) q.uniqueResult();
    }

    @Transactional
    public void savePermohonanEtappHakmilk(PermohonanEtappHakmilik mohonEtappHakmilik) {
        permohonanEtappHakmilikDAO.save(mohonEtappHakmilik);
    }

    public List<KandunganFolder> senaraiDokumen(String idPermohonan, String[] kodDokumen) {
        String query = "SELECT kf FROM etanah.model.KandunganFolder kf, etanah.model.Permohonan h where h.idPermohonan = :idPermohonan"
                + " and kf.folder.folderId = h.folderDokumen.folderId and kf.dokumen.kodDokumen.kod in (:kodDokumen)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setParameterList("kodDokumen", kodDokumen);
        return q.list();
    }

    public String simpanPelan(String idPermohonan, String pelan, String mimeType, String fileName) {
        Dokumen doc = new Dokumen();
        String fizikalPath = "";
        try {
            String dokumenPath = conf.getProperty("document.path");
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
            KodDokumen kod = kodDokumenDAO.findById("PB1");

            doc.setTajuk(kod.getNama() + "-" + fileName);
            doc.setInfoAudit(setInfoAudit());
            doc.setKodDokumen(kod);
            doc.setKlasifikasi(klasifikasiAm);
            doc.setNoVersi("1.0");
            doc = dokumenDAO.saveOrUpdate(doc);

            DMSUtil dmsUtil = new DMSUtil();
            byte[] b = Base64.decodeToBytes(pelan);
            FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            fileUtil.saveFile(fileName, in);
            fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(doc);
            kf.setFolder(permohonan.getFolderDokumen());
            kf.setInfoAudit(setInfoAudit());
            updateKandunganFolder(kf);
            updatePathDokumen(fizikalPath, doc, mimeType);

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return fizikalPath;
    }

    public String pelanPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<EtappHakmilikSambungan> findEtappHakmilikSambByIdMohon(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.etapp.EtappHakmilikSambungan h where h.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<EtappHakmilikSambunganForm> setHakmilikSambungan(String idPermohonan) {
        List<EtappHakmilikSambunganForm> list = new ArrayList<EtappHakmilikSambunganForm>();
        List<EtappHakmilikSambungan> l = findEtappHakmilikSambByIdMohon(idPermohonan);
        String status = "";
        for (EtappHakmilikSambungan e : l) {
            EtappHakmilikSambunganForm form = new EtappHakmilikSambunganForm();
            Hakmilik hakmilikBaru = findHakmilikBaruByHakmilikAsalAndNoLotBaru(e.getHakmilik(), e.getNoLot());
            if (hakmilikBaru != null) {
                String tarikhDaftar = sdf.format(hakmilikBaru.getTarikhDaftar());
                form.setHakmilikBaru(hakmilikBaru);
                status = "DAFTAR";
            } else {
                status = "BELUM DAFTAR";
            }
            form.setStatus(status);
            form.setHakmilik(e.getHakmilik());
            list.add(form);
        }
        return list;
    }

    private Hakmilik findHakmilikBaruByHakmilikAsalAndNoLotBaru(Hakmilik hakmilik, String noLot) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where "
                + "h.noLot = :noLot "
                + "and h.daerah.kod = :daerah "
                + "and h.bandarPekanMukim.kod = :bpm ";
        if(hakmilik.getSeksyen()!=null){
        query = query +  " and h.seksyen.kod = :seksyen";
        }
                
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noLot", noLot);
        q.setString("daerah", hakmilik.getDaerah().getKod());
        q.setInteger("bpm", hakmilik.getBandarPekanMukim().getKod());
           if(hakmilik.getSeksyen()!=null){
 q.setInteger("seksyen", hakmilik.getSeksyen().getKod());        }
       
        return (Hakmilik) q.uniqueResult();
    }

}

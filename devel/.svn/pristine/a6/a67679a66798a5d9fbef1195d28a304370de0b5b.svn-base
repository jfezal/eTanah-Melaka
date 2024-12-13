/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ambil;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AduanPerbincanganDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.NotaSiasatanDAO;
import etanah.dao.NotaSiasatanLengkapDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanHakmilikBaruDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.dao.PermohonanPengambilanHakmilikDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.StatusTanahLepasPengambilanDAO;
import etanah.model.AduanPerbincangan;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.StatusTanahLepasPengambilan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.InfoWarta;
import etanah.model.ambil.NotaSiasatan;
import etanah.model.ambil.NotaSiasatanLengkap;
import etanah.model.ambil.PermohonanHakmilikBaru;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.service.acq.service.BorangEFService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.util.StringUtils;
import etanah.view.MaklumatSek4Form;
import etanah.view.strata.SenaraiKertasSiasatanFoliAActionBean;
import etanah.view.stripes.pengambilan.sek8.KemasukanPelanPAForm;
import etanah.view.stripes.pengambilan.sek8.PermohonanUkurForm;
import etanah.view.stripes.pengambilan.share.form.BayaranPampasanTambahanForm;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import etanah.view.stripes.pengambilan.share.form.HakmilikTuntutan;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.FileBean;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zuraida
 */
public class PengambilanAPTService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanPengambilanHakmilikDAO permohonanPengambilanHakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    InfoWartaService infoWartaService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    NotaSiasatanDAO notaSiasatanDAO;
    @Inject
    NotaSiasatanLengkapDAO notaSiasatanLengkapDAO;
    @Inject
    AduanPerbincanganDAO aduanPerbincanganDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    StatusTanahLepasPengambilanDAO lepasPengambilanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    BorangEFService borangEFService;
    @Inject
    PermohonanHakmilikBaruDAO permohonanHakmilikBaruDAO;

    private MaklumatSek4Form formMaklumatSek4;

    public PermohonanPengambilanHakmilik findPermohonanPengambilanByIdMH(Long idMH) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.ambil.PermohonanPengambilanHakmilik m WHERE m.hakmilikPermohonan.id = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idMH);
        return (PermohonanPengambilanHakmilik) q.uniqueResult();

    }

    public HakmilikPerbicaraan findHakmilikPerbicaraanByIdMH(Long idMH) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();

    }

    public PermohonanPengambilanHakmilik findPermohonanPengambilanByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.ambil.PermohonanPengambilanHakmilik m WHERE m.hakmilikPermohonan.permohonan.idPermohonan = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return (PermohonanPengambilanHakmilik) q.uniqueResult();

    }

    public PermohonanPengambilan findPermohonanPengambilanByIdMH(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.ambil.PermohonanPengambilan m WHERE m.permohonan.idPermohonan = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return (PermohonanPengambilan) q.uniqueResult();

    }

    @Transactional
    public void saveOrUpdatehakmilikpermohonan(PermohonanPengambilanHakmilik p) {
        permohonanPengambilanHakmilikDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdateMohonHakmilik(HakmilikPermohonan p) {
        hakmilikPermohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdatePermohonanPengambilan(PermohonanPengambilan p) {
        permohonanPengambilanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdateHakmilikPerbicaraan(HakmilikPerbicaraan p) {
        hakmilikPerbicaraanDAO.saveOrUpdate(p);
    }

    public List<HakmilikPermohonan> findHakmilikPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return q.list();
    }

    public MaklumatSek4Form setValueMaklumatSek4Form(String idPermohonan) {
        formMaklumatSek4 = new MaklumatSek4Form();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            InfoWarta warta = infoWartaService.findByIdMohonAndKodNotis(idPermohonan);
            PermohonanPengambilan mohonAmbil = findPermohonanPengambilanByIdMH(idPermohonan);
            List<HakmilikPermohonan> listHakmilikPermohonan = hakmilikPermohonanService.findByIdPermohonanTDK(idPermohonan);
            List<HakmilikPermohonan> listHakmilikPermohonanPenuh = permohonan.getSenaraiHakmilik();

            formMaklumatSek4.setIdPermohonan(idPermohonan);
            formMaklumatSek4.setUrusan(permohonan.getKodUrusan().getNama());
            formMaklumatSek4.setTujuanPengambilan(mohonAmbil.getTujuanPermohonan() != null ? mohonAmbil.getTujuanPermohonan() : "");
            formMaklumatSek4.setBilHakmilik(listHakmilikPermohonanPenuh.size() + "");
            formMaklumatSek4.setBilHakmilikTDK(listHakmilikPermohonan.size() + "");
            formMaklumatSek4.setJumLuasAmbil(mohonAmbil.getLuasTerlibat() + "");
            formMaklumatSek4.setKodLuasAmbil(mohonAmbil.getKodUnitLuas().getNama() + "");
//        formMaklumatSek4.setSenaraiHakmilik(listHakmilikPermohonanPenuh);
            formMaklumatSek4.setTarikhWarta(warta.getTrhWarta() != null ? sdf.format(warta.getTrhWarta()) : null);
            formMaklumatSek4.setUrusan(permohonan.getKodUrusan().getNama());
        } else {
            formMaklumatSek4.setInvalidId(true);
        }
        return formMaklumatSek4;
    }

    public MaklumatSek4Form setValueMaklumatSek8Form(String idPermohonan) {
        formMaklumatSek4 = new MaklumatSek4Form();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        InfoWarta warta = null;
        if (permohonan != null) {
            if (permohonan.getKodUrusan().getKod().equals("SEK4")) {
                warta = infoWartaService.findByIdMohonAndKodNotis1(idPermohonan, "A");
            } else {
                warta = infoWartaService.findByIdMohonAndKodNotis1(idPermohonan, "D");
            }

            PermohonanPengambilan mohonAmbil = findPermohonanPengambilanByIdMH(idPermohonan);
            List<HakmilikPermohonan> listHakmilikPermohonan = hakmilikPermohonanService.findByIdPermohonanTDK(idPermohonan);
            List<HakmilikPermohonan> listHakmilikPermohonanPenuh = permohonan.getSenaraiHakmilik();

            formMaklumatSek4.setIdPermohonan(idPermohonan);
            formMaklumatSek4.setUrusan(permohonan.getKodUrusan().getNama());
            formMaklumatSek4.setTujuanPengambilan(mohonAmbil.getTujuanPermohonan());
            formMaklumatSek4.setBilHakmilik(listHakmilikPermohonanPenuh.size() + "");
            formMaklumatSek4.setBilHakmilikTDK(listHakmilikPermohonan.size() + "");
            formMaklumatSek4.setJumLuasAmbil(mohonAmbil.getLuasTerlibat() + "");
            formMaklumatSek4.setKodLuasAmbil(mohonAmbil.getKodUnitLuas().getNama() + "");
//        formMaklumatSek4.setSenaraiHakmilik(listHakmilikPermohonanPenuh);
            formMaklumatSek4.setTarikhWarta(warta.getTrhWarta() != null ? sdf.format(warta.getTrhWarta()) : null);
            formMaklumatSek4.setUrusan(permohonan.getKodUrusan().getNama());
        } else {
            formMaklumatSek4.setInvalidId(true);
        }
        return formMaklumatSek4;
    }

    public Pemohon findPermohonByIdMHN(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Pemohon m WHERE m.permohonan.idPermohonan = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return (Pemohon) q.uniqueResult();

    }

    public StatusTanahLepasPengambilan findLpsAmbilByIdMHN(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.StatusTanahLepasPengambilan m WHERE m.idPermohonan.idPermohonan = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return (StatusTanahLepasPengambilan) q.uniqueResult();

    }

    public PermohonanRujukanLuar findRujLuarByIdMHN(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.PermohonanRujukanLuar m WHERE m.permohonan.idPermohonan = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();

    }

    public List<PermohonanMahkamah> findMahkamahByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.PermohonanMahkamah m WHERE m.permohonan.idPermohonan = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return q.list();
    }

    @Transactional
    public void saveOrUpdateLpsAmbil(StatusTanahLepasPengambilan p) {
        lepasPengambilanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdateRujLuar(PermohonanRujukanLuar p) {
        permohonanRujukanLuarDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdatePemohon(Pemohon p) {
        pemohonDAO.saveOrUpdate(p);
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public PermohonanPengambilanHakmilikDAO getPermohonanPengambilanHakmilikDAO() {
        return permohonanPengambilanHakmilikDAO;
    }

    public void setPermohonanPengambilanHakmilikDAO(PermohonanPengambilanHakmilikDAO permohonanPengambilanHakmilikDAO) {
        this.permohonanPengambilanHakmilikDAO = permohonanPengambilanHakmilikDAO;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public PermohonanPengambilanDAO getPermohonanPengambilanDAO() {
        return permohonanPengambilanDAO;
    }

    public void setPermohonanPengambilanDAO(PermohonanPengambilanDAO permohonanPengambilanDAO) {
        this.permohonanPengambilanDAO = permohonanPengambilanDAO;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public InfoWartaService getInfoWartaService() {
        return infoWartaService;
    }

    public void setInfoWartaService(InfoWartaService infoWartaService) {
        this.infoWartaService = infoWartaService;
    }

    public HakmilikPermohonanService getHakmilikPermohonanService() {
        return hakmilikPermohonanService;
    }

    public void setHakmilikPermohonanService(HakmilikPermohonanService hakmilikPermohonanService) {
        this.hakmilikPermohonanService = hakmilikPermohonanService;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public StatusTanahLepasPengambilanDAO getLepasPengambilanDAO() {
        return lepasPengambilanDAO;
    }

    public void setLepasPengambilanDAO(StatusTanahLepasPengambilanDAO lepasPengambilanDAO) {
        this.lepasPengambilanDAO = lepasPengambilanDAO;
    }

    public PermohonanRujukanLuarDAO getPermohonanRujukanLuarDAO() {
        return permohonanRujukanLuarDAO;
    }

    public void setPermohonanRujukanLuarDAO(PermohonanRujukanLuarDAO permohonanRujukanLuarDAO) {
        this.permohonanRujukanLuarDAO = permohonanRujukanLuarDAO;
    }

    public MaklumatSek4Form getFormMaklumatSek4() {
        return formMaklumatSek4;
    }

    public void setFormMaklumatSek4(MaklumatSek4Form formMaklumatSek4) {
        this.formMaklumatSek4 = formMaklumatSek4;
    }

    @Transactional
    public void saveMohonTuntutKos(PermohonanTuntutanKos mohontuntutkos) {
        permohonanTuntutanKosDAO.save(mohontuntutkos);
    }

    @Transactional
    public void saveAduanPerbincangan(AduanPerbincangan aduanPerbincangan) {
        aduanPerbincanganDAO.save(aduanPerbincangan);
    }

    public NotaSiasatan findNotaSiasatanKodPeringkat(String idPermohonan, String notas) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.ambil.NotaSiasatan m WHERE m.permohonan.idPermohonan = :id "
                + "and m.kodPeringkat.kod = :notas";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        q.setParameter("notas", notas);
        return (NotaSiasatan) q.uniqueResult();
    }

    @Transactional
    public void saveNotaSiasatan(NotaSiasatan notas) {
        notaSiasatanDAO.save(notas);
    }

    @Transactional
    public void saveNotaSiasatanLengkap(NotaSiasatanLengkap nsl) {
        notaSiasatanLengkapDAO.save(nsl);
    }

    public Dokumen saveUploadDokumen(Permohonan permohonan, NotaSiasatanLengkap nsl, FileBean fileToBeUpload, String nsia, InfoAudit ia) throws IOException, Exception {
        Dokumen doc = new Dokumen();
        KodDokumen kd = new KodDokumen();
        KodKlasifikasi kodk = new KodKlasifikasi();
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        kd = kodDokumenDAO.findById(nsia);
        doc.setTajuk(kd.getNama());
        doc.setInfoAudit(ia);
        doc.setKodDokumen(kd);
        doc.setKlasifikasi(klasifikasiAm);
        doc.setNoVersi("1.0");
        doc = dokumenDAO.saveOrUpdate(doc);
        String fileName = fileToBeUpload.getFileName();
        DMSUtil dmsUtil = new DMSUtil();
        FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
        fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
        String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
        updatePathDokumen(fizikalPath, doc, fileToBeUpload.getContentType());

        return doc;
    }

    @Transactional
    private void updatePathDokumen(String namaFizikal, Dokumen d, String format) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    @Transactional
    public void saveHakmilikPerbicaraan(HakmilikPerbicaraan bicara) {
        hakmilikPerbicaraanDAO.save(bicara);
    }

    public HakmilikPermohonan findHakmilikPermohonanByIdMhnIdHM(String idPermohonan, String idHakmilik) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :id and m.hakmilik.idHakmilik = :idHM";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        q.setParameter("idHM", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public BayaranPampasanTambahanForm setValuePampasan(String idPermohonan) {
        BayaranPampasanTambahanForm form = new BayaranPampasanTambahanForm();
        PermohonanPengambilan mohonAmbil = findPermohonanPengambilanByIdMH(idPermohonan);
        PermohonanTuntutanKos mohontuntutkos = findPermohonanTuntutanKosIdMohon(idPermohonan);
        form.setDeposit50(mohonAmbil.getDeposit());
        form.setDeposit125(mohontuntutkos.getAmaunTuntutan());
        form.setDepoTotal(form.getDeposit50().add(form.getDeposit125()));
        form = setListHakmilikTuntutan(form, idPermohonan);

        return form;
    }

    private BayaranPampasanTambahanForm setListHakmilikTuntutan(BayaranPampasanTambahanForm f, String idPermohonan) {
        List<HakmilikTuntutan> list = new ArrayList<HakmilikTuntutan>();
        List<BorangEForm> listBorangE = borangEFService.findHakmilikAktifNotTDK(idPermohonan);
        BigDecimal s = new BigDecimal(BigInteger.ZERO);
        for (BorangEForm e : listBorangE) {
            BigDecimal ss = borangEFService.findJumlahTuntutan(e.getHm().getId());
            HakmilikTuntutan ht = new HakmilikTuntutan();
            ht.setHakmilik(e.getHm().getHakmilik());
            if (ss != null) {
                s = s.add(ss);
            } else {
                s = s.add(BigDecimal.ZERO);
            }

            ht.setTuntutan(ss);
            list.add(ht);
        }
        f.setTuntutanTotal(s);
        f.setListHakmilikTututan(list);
        return f;
    }

    public PermohonanTuntutanKos findPermohonanTuntutanKosIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<PermohonanUkurForm> setCheckList(String[] kodDokumen, Permohonan mohon) {
        List<PermohonanUkurForm> l = new ArrayList<PermohonanUkurForm>();
        l = setListDokumen(kodDokumen, mohon);
        return l;
    }

    private List<PermohonanUkurForm> setListDokumen(String[] kodDokumen, Permohonan mohon) {
        List<PermohonanUkurForm> l = new ArrayList<PermohonanUkurForm>();
        for (int i = 0; i < kodDokumen.length; i++) {
            PermohonanUkurForm e = new PermohonanUkurForm();
            KandunganFolder kf = findKFbyKodDokumenIdmohon(kodDokumen[i], mohon);
            e.setKodDokumen(kodDokumenDAO.findById(kodDokumen[i]));
            if (kf != null) {
                if (kf.getDokumen() != null) {
                    e.setDokumen(kf.getDokumen());

                }
                e.setFolderDokumenId(kf.getIdKandunganFolder());
            } else {

            }
            l.add(e);
        }
        return l;
    }

    private KandunganFolder findKFbyKodDokumenIdmohon(String string, Permohonan mohon) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.KandunganFolder m, etanah.model.FolderDokumen f,"
                + " etanah.model.Permohonan p WHERE m.folder.folderId = f.folderId"
                + " and f.folderId = p.folderDokumen.folderId and p.idPermohonan = :id and m.dokumen.kodDokumen = :kod";
        Query q = s.createQuery(query);
        q.setParameter("id", mohon.getIdPermohonan());
        q.setString("kod", string);
        return (KandunganFolder) q.uniqueResult();
    }

    public List<PermohonanPengambilanHakmilik> findHakmilikAktifNotTDK(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.ambil.PermohonanPengambilanHakmilik m, "
                + "etanah.model.HakmilikPermohonan mh "
                + "WHERE m.hakmilikPermohonan.id = mh.id and mh.permohonan.idPermohonan = :id "
                + "and mh.hakmilikAmbil <>'TA' and mh.hakmilik is not null";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return q.list();
    }

    @Transactional
    public void savePermohonanHakmilikBaru(PermohonanHakmilikBaru mhb) {
        permohonanHakmilikBaruDAO.save(mhb);
    }

    public List<KemasukanPelanPAForm> setListKemasukanPelanForm(String idPermohonan) {
        List<KemasukanPelanPAForm> list = new ArrayList<KemasukanPelanPAForm>();
        List<PermohonanPengambilanHakmilik> lphh = findHakmilikAktifNotTDK(idPermohonan);
        for (PermohonanPengambilanHakmilik p : lphh) {
            KemasukanPelanPAForm form = new KemasukanPelanPAForm();
            form.setPermohonanPengambilanHakmilik(p);
            List<PermohonanHakmilikBaru> listHakmilikBaru = findListPermohonanHakmilikBaru1(idPermohonan, p.getHakmilikPermohonan().getId());
            form.setListHakmilikBaru(listHakmilikBaru);
            list.add(form);
        }
        return list;
    }

    public List<PermohonanHakmilikBaru> findListPermohonanHakmilikBaru(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.ambil.PermohonanHakmilikBaru m, "
                + "etanah.model.HakmilikPermohonan mh "
                + "WHERE m.hakmilikPermohonan.id = mh.id and mh.permohonan.idPermohonan = :id ";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        return q.list();
    }

    public List<PermohonanHakmilikBaru> findListPermohonanHakmilikBaru1(String idPermohonan, Long idMH) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.ambil.PermohonanHakmilikBaru m, "
                + "etanah.model.HakmilikPermohonan mh "
                + "WHERE m.hakmilikPermohonan.id = mh.id and mh.permohonan.idPermohonan = :id and mh.id = :idmh ";
        Query q = s.createQuery(query);
        q.setParameter("id", idPermohonan);
        q.setParameter("idmh", idMH);
        return q.list();
    }
    
    public NotaSiasatanLengkap findNotaSiasatanLengkapByIdPB(Long idPB) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.ambil.NotaSiasatanLengkap m WHERE m.borangPerPB.id = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", idPB);
        return (NotaSiasatanLengkap) q.uniqueResult();

    }
}

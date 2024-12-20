/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.ImejLaporanStrataDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.NotisButirDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PelaksanaWaranDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDokumenDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanWaranItemDAO;
import etanah.dao.WaranDAO;
import etanah.dao.WaranPihakDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikAsalDAO;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.HakmilikSebelumDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.AduanPortal;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPetakAksesori;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.ImejLaporan;
import etanah.model.ImejLaporanStrata;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriBangunan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.Notis;
import etanah.model.NotisButiran;
import etanah.model.PelaksanaWaran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanPengguna;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PermohonanStrata;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanWaranItem;
import etanah.model.SejarahHakmilik;
import etanah.model.Waran;
import etanah.model.WaranPihak;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdHakmilik;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.BPelService;
import etanah.service.RegService;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.KandunganFolderService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.FileBean;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.gis.PelanGIS;
import etanah.model.strata.BadanPengurusan;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.PelupusanService;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author faidzal
 */
public class CommonService {
//@Inject
//NotisButiranDAO notisButiranDAO;

    @Inject
    NotisDAO notisDAO;
    @Inject
    PermohonanWaranItemDAO permohonanWaranItemDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    PermohonanRujukanLuarDokumenDAO permohonanRujukanLuarDokumenDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    private GeneratorIdHakmilik idHakmilikGenerator;
    @Inject
    private RegService regService;
    @Inject
    private HakmilikPihakKepentinganService hpkService;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    StrataPtService strataService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    GeneratorId genId;
    @Inject
    ImejLaporanStrataDAO imejLaporanStrataDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    PermohonanTuntutanButiranDAO permohonanTuntutanButiranDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PelaksanaWaranDAO pelaksanaWaranDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    private GeneratorIdPermohonan idGenMohon;
    @Inject
    WaranDAO waranDAO;
    @Inject
    StrataPtService strService;
    @Inject
    SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    WaranPihakDAO waranPihakDAO;
    @Inject
    HakmilikAsalDAO hakmilikAsalDAO;
    @Inject
    HakmilikSebelumDAO hakmilikSebelumDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikDAO hmDAO;
    @Inject
    private HakmilikSebelumPermohonanDAO hakmilikSblmPermohonanDAO;
    @Inject
    private HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO;
    @Inject
    NotisButirDAO notisButiranDAO;
    private static final Logger LOG = Logger.getLogger(CommonService.class);
    InfoAudit ia = new InfoAudit();
    Pengguna pengguna;
    private ArrayList<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<HakmilikSebelum> hakmiliksebelumlist = new ArrayList<HakmilikSebelum>();
    private ArrayList<HakmilikAsal> hakmilikAsallist = new ArrayList<HakmilikAsal>();
    private ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private ArrayList<HakmilikPetakAksesori> listHakmilikPetakAksesori = new ArrayList<HakmilikPetakAksesori>();
    private ArrayList<HakmilikPihakBerkepentingan> lhp = new ArrayList<HakmilikPihakBerkepentingan>();
    private List<KodDaerah> senaraiKodDaerah = new ArrayList();
    private List<KodHakmilik> senaraiKodHakmilik = new ArrayList();
    private String kodNegeri;
    private Permohonan permohonanSebelum;
    String path;
    FolderDokumen fd;
    public String negeri;

    public FolderDokumen getFolder(String idmohon) {
        String query = "SELECT b FROM etanah.model.FolderDokumen b where b.tajuk = :idmohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("tajuk", idmohon);
        return (FolderDokumen) q.uniqueResult();
    }

    public Dokumen saveDokumen(String dokumenPath, FileBean fileToBeUpload, Long id, InfoAudit ia, Permohonan permohonan) throws Exception {
//        try {
        Dokumen doc = new Dokumen();
        KodDokumen kd = new KodDokumen();
        KodKlasifikasi kodk = new KodKlasifikasi();
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        kd = kodDokumenDAO.findById("GLT");
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

//        updateFolderDoc(ia, doc, id);
        return doc;
//        } catch (Exception ex) {
//            Logger.getLogger(CommonService.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Transactional
    public void saveDokumen(Dokumen dok) {
        dokumenDAO.saveOrUpdate(dok);
    }

    public List<PermohonanRujukanLuar> findPermohonan(String id) throws Exception {
        String query = "Select p from etanah.model.PermohonanRujukanLuar p where p.permohonan.idPermohonan =:id";
        Query q = sessionProvider.get().createQuery(query);
        if (id != null) {
            q.setString("id", id);
        }
        LOG.debug("SQL Query: " + query);
        LOG.debug("qlist.size :" + q.list().size());

        return q.list();

    }

    public List<PermohonanRujukanLuarDokumen> findPermohonanLuarDok(Long id) throws Exception {
        String query = "Select p from etanah.model.PermohonanRujukanLuarDokumen p where p.permohonanRujukanLuar.idRujukan =:id";
        Query q = sessionProvider.get().createQuery(query);
        if (id != null) {
            q.setLong("id", id);
        }
        LOG.debug("SQL Query: " + query);
        LOG.debug("qlist.size :" + q.list().size());

        return q.list();

    }

    public List<Dokumen> findFolDok(String id, String key) throws Exception {
        String query = "Select d from etanah.model.Permohonan m,etanah.model.Dokumen d,etanah.model.KandunganFolder p "
                + "where m.idPermohonan =:id and"
                + " m.folderDokumen.folderId=p.folder.folderId and "
                + "p.dokumen.idDokumen=d.idDokumen ";
        if (key != null) {
            query = query + " and d.kodDokumen.nama LIKE :key ";
        }
        Query q = sessionProvider.get().createQuery(query);
        if (id != null) {
            q.setString("id", id);
        }
        if (key != null) {
            q.setString("key", "%" + key + "%");
        }
        LOG.debug("SQL Query: " + query);
        LOG.debug("qlist.size :" + q.list().size());

        return q.list();

    }

    public List<Dokumen> allDokumen() throws Exception {
        String query = "Select p from etanah.model.Dokumen p";
        Query q = sessionProvider.get().createQuery(query);

        LOG.debug("SQL Query: " + query);
        LOG.debug("qlist.size :" + q.list().size());

        return q.list();
    }

    public List<KandunganFolder> allSearchDokumen(String kw) throws Exception {
        String query = "Select p from etanah.model.KandunganFolder p where p.dokumen.kodDokumen.nama=:%kw%";
        Query q = sessionProvider.get().createQuery(query);
        if (kw != null) {
            q.setString("kw", kw);
        }
        LOG.debug("SQL Query: " + query);
        LOG.debug("qlist.size :" + q.list().size());

        return q.list();
    }

//      public PermohonanRujukanLuarDokumen
    @Transactional
    public void deleteDokumen(PermohonanRujukanLuarDokumen p) {
        permohonanRujukanLuarDokumenDAO.delete(p);
    }

    public PermohonanRujukanLuarDokumen findDokumen(String idDokumen, String idRujukan) {
        String query = "Select h from etanah.model.PermohonanRujukanLuarDokumen h where h.dokumen.idDokumen = :idDokumen" + " and h.permohonanRujukanLuar.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query).setString("idDokumen", idDokumen).setString("idRujukan", idRujukan);
        return (PermohonanRujukanLuarDokumen) q.uniqueResult();
    }
//    public PermohonanRujukanLuarDokumen findDokumen(String idD, String idR){
//        String query = "Select p  from etanah.model.PermohonanRujukanLuarDokumen p where p.permohonanRujukanLuar.dokumen.idDokumen=:idD && p.permohonanRujukanLuar.idRujukan =:idR";
//        Query q = sessionProvider.get().createQuery(query);
//        if(idD!=null && idR!=null){
//            q.setString("idD", idD);
//            q.setString("idR", idR);
//        }
//        LOG.debug("SQL Query: " + query);
//        //LOG.debug("qlist.size :" + q.list().size());
//
//        return (PermohonanRujukanLuarDokumen) q.uniqueResult();
//
//       }

    public Dokumen savePelanLokasi(String dokumenPath, Long idFolder, InfoAudit ia, Permohonan permohonan) throws Exception {

        //find Dokumen by noRujukan where noRujukan = idPermohonan
        Dokumen doc = findDokumenByNoRujukan(permohonan.getIdPermohonan());
        KodDokumen kd = kodDokumenDAO.findById("PLK");
        if (doc != null) {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new Date());
        } else {
            doc = new Dokumen();

        }
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        doc.setTajuk(kd.getNama());
        doc.setInfoAudit(ia);
        doc.setKodDokumen(kd);
        doc.setKlasifikasi(klasifikasiAm);
        doc.setNoVersi("1.0");
        doc.setNoRujukan(permohonan.getIdPermohonan());
        doc = dokumenDAO.saveOrUpdate(doc);

        PelanGIS p = strataService.findPelanByIdPermohonan(permohonan.getIdPermohonan());

        String fileName = p.getPelanTif();
        DMSUtil dmsUtil = new DMSUtil();
//        FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
//        fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
        String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
        updatePathDokumen(fizikalPath, doc, "image/tiff");
        updateFolderDoc(ia, doc, idFolder);

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
        LOG.info("Dokumen: " + d);
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    @Transactional
    public void updateFolderDoc(InfoAudit ia, Dokumen doc, Long id) {
        KandunganFolder kanFolder = new KandunganFolder();
        FolderDokumen fd;
        fd = folderDokumenDAO.findById(id);
        LOG.info("DOKUMEN: " + doc.getIdDokumen());
        kanFolder.setDokumen(doc);
        kanFolder.setFolder(fd);
        kanFolder.setCatatan("S");
        kanFolder.setInfoAudit(ia);
        kandunganFolderDAO.save(kanFolder);
    }

    @Transactional
    public void saveImej(ImejLaporanStrata imejStrata) {
        imejLaporanStrataDAO.save(imejStrata);
    }

    @Transactional
    public void saveImej(ImejLaporan imejLaporan) {
        imejLaporanDAO.save(imejLaporan);
    }

    List<ImejLaporanStrata> getListDoc(char pandanganImej, long idStrata) {
        String query = "SELECT b FROM etanah.model.ImejLaporanStrata b where b.permohonanStrata.idStrata = :idStrata and b.pandanganImej =:pandanganImej";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idStrata", idStrata);
        q.setCharacter("pandanganImej", pandanganImej);
        LOG.info("SIZE: " + pandanganImej + q.list().size());
        return q.list();
    }

    List<ImejLaporan> getListDocL(char pandanganImej, long idLaporan) {
        String query = "SELECT b FROM etanah.model.ImejLaporan b where b.laporanTanah.idLaporan = :idLaporan and b.pandanganImej =:pandanganImej";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLaporan", idLaporan);
        q.setCharacter("pandanganImej", pandanganImej);
        LOG.info("SIZE: " + pandanganImej + q.list().size());
        return q.list();
    }

    public void setBayaran(PermohonanTuntutanKos ptk, PermohonanTuntutan pt, Pengguna pengguna) {

        ptk = saveTuntutKos(ptk);
        pt = saveTuntut(pt);
        PermohonanTuntutanButiran ptb = new PermohonanTuntutanButiran();
        ptb = strataService.findTuntutButir(ptk.getIdKos(), pt.getIdTuntut());
        InfoAudit iAudit = new InfoAudit();
        if (ptb == null) {
            ptb = new PermohonanTuntutanButiran();
            iAudit = strataService.getInfo(pengguna);
        } else {
            iAudit = ptb.getInfoAudit();
            iAudit.setTarikhKemaskini(new Date());
        }
        LOG.info("IDPENGGUNA : " + pengguna);
        LOG.info("InfoAudit : " + iAudit.getTarikhMasuk().toString());
        ptb.setInfoAudit(iAudit);
        ptb.setPermohonanTuntutan(pt);
        ptb.setPermohonanTuntutanKos(ptk);
        saveTuntutButir(ptb);

    }

    @Transactional
    public PermohonanTuntutanButiran saveTuntutButir(PermohonanTuntutanButiran ptb) {
        ptb = permohonanTuntutanButiranDAO.saveOrUpdate(ptb);
        return ptb;
    }

    @Transactional
    public PermohonanTuntutanKos saveTuntutKos(PermohonanTuntutanKos ptk) {
        ptk = permohonanTuntutanKosDAO.saveOrUpdate(ptk);
        return ptk;
    }

    @Transactional
    public PermohonanTuntutan saveTuntut(PermohonanTuntutan pt) {
        pt = permohonanTuntutanDAO.saveOrUpdate(pt);
        return pt;
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        return stageId;
    }

    public PermohonanTuntutan findMohonTuntutByKod(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutan b where b.permohonan.idPermohonan = :idPermohonan and b.kodTransaksiTuntut.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);

        return (PermohonanTuntutan) q.uniqueResult();
    }

    public void generateHakmilikPHPCStrataN9(InfoAudit ia, Permohonan p, Pengguna pengguna) {

        kodNegeri = conf.getProperty("kodNegeri");

        permohonanSebelum = p.getPermohonanSebelum();

        HakmilikPermohonan idHm = strataService.findMohonHakmilikAsc(permohonanSebelum.getIdPermohonan());
        Hakmilik hmmohon = strataService.findInfoByIdHakmilik(idHm.getHakmilik().getIdHakmilik());
        SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(idHm.getHakmilik().getIdHakmilik());

        String jenisBgn = hmmohon.getNoBangunan();
        String hminduk = hmmohon.getIdHakmilikInduk();
        String tgkt = hmmohon.getNoTingkat();
        String idhakmilikAsal = hmmohon.getIdHakmilik();
        int idlength = idhakmilikAsal.length();

        int MaksPetak = strataService.findMaxPetakByIdHakmilik(hminduk, jenisBgn);
        int lengthMax = (MaksPetak + "").length();
        int legthReplace = idlength - lengthMax;

        LOG.info("Hakmilik Pohon :------ " + hmmohon.getIdHakmilik());
        LOG.info("Jenis Bngunan :------- " + jenisBgn);
        LOG.info("Hakmilik Induk :------ " + hminduk);
        LOG.info("Maks Petak :------ " + MaksPetak);
        LOG.info(" Petak :------ " + legthReplace);

        //Hakmilik hakmilikBaru = new Hakmilik();
        if (p.getKodUrusan().getKod().equals("HTSPB")) {
            String petak = (MaksPetak) + "";

            for (int i = 1; i < 3; i++) {
                String idHakmilikBaru = idhakmilikAsal.substring(0, legthReplace);

                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                Hakmilik hakmilikBaru = new Hakmilik();

                hakmilikBaru.setCawangan(hmmohon.getCawangan());
                hakmilikBaru.setDaerah(hmmohon.getDaerah());
                hakmilikBaru.setBandarPekanMukim(hmmohon.getBandarPekanMukim());
                hakmilikBaru.setSeksyen(null);
                hakmilikBaru.setKodHakmilik(hmmohon.getKodHakmilik());
                //mohon
                hakmilikBaru.setLot(hmmohon.getLot());
                hakmilikBaru.setNoLot(hmmohon.getNoLot());
                hakmilikBaru.setNoHakmilik(hmmohon.getNoHakmilik());
                hakmilikBaru.setLokasi(hmmohon.getLokasi());
                //hakmilikBaru.setKumpulan(hmmohon.getKumpulan());
                hakmilikBaru.setIdHakmilikInduk(hminduk);
                hakmilikBaru.setNoBangunan(jenisBgn);
                hakmilikBaru.setNoTingkat(tgkt);
                hakmilikBaru.setNoPetak((MaksPetak + i) + "");
                hakmilikBaru.setKategoriTanah(hmmohon.getKategoriTanah());
                hakmilikBaru.setKodKategoriBangunan(hmmohon.getKodKategoriBangunan());
                hakmilikBaru.setKodTanah(hmmohon.getKodTanah());
                hakmilikBaru.setMaklumatAtasTanah(hmmohon.getMaklumatAtasTanah());
                hakmilikBaru.setSyaratNyata(hmmohon.getSyaratNyata());
                hakmilikBaru.setKegunaanTanah(hmmohon.getKegunaanTanah());
                hakmilikBaru.setSekatanKepentingan(hmmohon.getSekatanKepentingan());
                hakmilikBaru.setTarikhDaftar(new Date());
                hakmilikBaru.setKodStatusHakmilik(hmmohon.getKodStatusHakmilik());
                hakmilikBaru.setPegangan(hmmohon.getPegangan());
                hakmilikBaru.setTempohPegangan(hmmohon.getTempohPegangan());
                hakmilikBaru.setTarikhLuput(hmmohon.getTarikhLuput());
                hakmilikBaru.setRizab(hmmohon.getRizab());
                hakmilikBaru.setLotBumiputera(hmmohon.getLotBumiputera());
                hakmilikBaru.setPbt(hmmohon.getPbt());
                hakmilikBaru.setMilikPersekutuan(hmmohon.getMilikPersekutuan());
                hakmilikBaru.setKodUnitLuas(hmmohon.getKodUnitLuas());
                //hakmilikBaru.setLuas(hmmohon.getLuas().divide(new BigDecimal("2"),2, BigDecimal.ROUND_HALF_UP));
                //temporary mesti baca dari mohon_bngn
                hakmilikBaru.setLuas(hmmohon.getLuas());
                //luas lama
                //uom lama
                hakmilikBaru.setNoLitho(hmmohon.getNoLitho());
                hakmilikBaru.setNoPelan(hmmohon.getNoPelan());
                hakmilikBaru.setNoPu(hmmohon.getNoPu());
                hakmilikBaru.setKodKadarCukai(hmmohon.getKodKadarCukai());
                hakmilikBaru.setCukai(hmmohon.getCukai());
                hakmilikBaru.setCukaiSebenar(hmmohon.getCukaiSebenar());
                //Ansuran
                //kod_sumber
                hakmilikBaru.setDhde(hmmohon.getDhde());
                hakmilikBaru.setNoVersiDhde(1);
                hakmilikBaru.setNoVersiDhke(1);
                hakmilikBaru.setNoFail(hmmohon.getNoFail());
                hakmilikBaru.setTarikhBatal(null);
                //dimasuk sume
                hakmilikBaru.setUPI(hmmohon.getUPI());
                hakmilikBaru.setRizabNoWarta(hmmohon.getRizabNoWarta());
                hakmilikBaru.setRizabKawasan(hmmohon.getRizabKawasan());
                hakmilikBaru.setRizabTarikhWarta(hmmohon.getRizabTarikhWarta());
                hakmilikBaru.setPbtNoWarta(hmmohon.getPbtNoWarta());
                hakmilikBaru.setPbtKawasan(hmmohon.getPbtKawasan());
                hakmilikBaru.setPbtTarikhWarta(hmmohon.getPbtTarikhWarta());
                hakmilikBaru.setGsaNoWarta(hmmohon.getGsaNoWarta());
                hakmilikBaru.setGsaKawasan(hmmohon.getGsaKawasan());
                hakmilikBaru.setGsaTarikhWarta(hmmohon.getGsaTarikhWarta());
                hakmilikBaru.setTarikhDaftarAsal(new Date());
                hakmilikBaru.setPelan(hmmohon.getPelan());
                //hakmilikBaru.setUnitSyer(hmmohon.getUnitSyer().divide(new BigDecimal("2"),2, BigDecimal.ROUND_HALF_UP));
                //temporary mesti baca dari mohon_bngn
                hakmilikBaru.setUnitSyer(hmmohon.getUnitSyer());
                hakmilikBaru.setBadanPengurusan(hmmohon.getBadanPengurusan());
                //status
                hakmilikBaru.setDhke(hmmohon.getDhke());
                hakmilikBaru.setNoBukuDaftarStrata(hmmohon.getNoBukuDaftarStrata());
                hakmilikBaru.setNoVersiIndeksStrata(hmmohon.getNoVersiIndeksStrata());
                hakmilikBaru.setNoSkim(hmmohon.getNoSkim());
                hakmilikBaru.setJumlahUnitSyer(hmmohon.getJumlahUnitSyer());
                hakmilikBaru.setKodKategoriBangunan(hmmohon.getKodKategoriBangunan());
                //lot tambah
                //id
                //version
                hakmilikBaru.setKodKegunaanBangunan(hmmohon.getKodKegunaanBangunan());

                int petakB = MaksPetak + i;
                String petakBaru = petakB + "";
                String s1 = idHakmilikBaru + "" + petakBaru;
                LOG.debug("--Petak Baru--:" + petakBaru);
                hakmilikBaru.setIdHakmilik(s1);
                hakmilikBaru.setInfoAudit(ia);
                // simpan rekod hakmilik baru
                strataService.simpanHakmilik(hakmilikBaru);

                LOG.debug("--Hakmilik baru berjaya disimpan --:" + s1);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                //hakmilikPermohonanDAO.saveOrUpdate(mohonHakmilikBaru);
                strataService.saveHakmilikPermohonan(mohonHakmilikBaru);

                List<HakmilikPetakAksesori> hpaLama = strataService.findIDforPetak(hmmohon.getIdHakmilik());

                if (hpaLama != null) {
                    for (HakmilikPetakAksesori HmPetakAksesori : hpaLama) {
                        HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                        hpa.setHakmilik(hakmilikBaru);
                        hpa.setCawangan(hakmilikBaru.getCawangan());
                        hpa.setKegunaaanPetak(HmPetakAksesori.getKegunaaanPetak());
                        hpa.setNama(HmPetakAksesori.getNama());
                        hpa.setLokasi(HmPetakAksesori.getLokasi());
                        hpa.setStatusHakmilik(HmPetakAksesori.getStatusHakmilik());
                        hpa.setInfoAudit(ia);
                        listHakmilikPetakAksesori.add(hpa);
                    }
                }

                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hmmohon);
                LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
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
                    hpb.setNoPengenalan(hpk.getNoPengenalan());
                    hpb.setPoskod(hpk.getPoskod());
                    hpb.setNegeri(hpk.getNegeri());

                    hpb.setInfoAudit(ia);
                    lhp.add(hpb);
                }


                /*INSERT INTO HAKMILIK SEBELUM*/
                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
//                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                hakmilikSebelumBaru.setInfoAudit(ia);
                hakmilikSebelumDAO.save(hakmilikSebelumBaru);


                /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                hsp.setHakmilikPermohonan(idHm);
                hsp.setPermohonan(idHm.getPermohonan());
                hsp.setCawangan(hmmohon.getCawangan());
                hsp.setHakmilik(hakmilikBaru);
                hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                hsp.setInfoAudit(ia);
                hakmilikSblmPermohonanDAO.save(hsp);

            } // End looping generate ID 

            // batalkan hakmilik asal          
            hmmohon.setTarikhBatal(new Date());
            KodStatusHakmilik ksh = new KodStatusHakmilik();
            ksh.setKod("B");
            hmmohon.setKodStatusHakmilik(ksh);

            hakmilikDAO.saveOrUpdate(hmmohon);

        }  //End for HTSPB

        if (p.getKodUrusan().getKod().equals("HTSPS")) {

            List<HakmilikPihakBerkepentingan> hpasal = strataService.findHakmilikPihakKodPBByIDHakmilik(hmmohon.getIdHakmilik(), "PM");
            int bilPemilik = 1;
            int pembahagi = hpasal.size();
            String petak = (MaksPetak) + "";
            for (HakmilikPihakBerkepentingan hpbaru : hpasal) {
                String idHakmilikBaru = idhakmilikAsal.substring(0, legthReplace);

                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                Hakmilik hakmilikBaru = new Hakmilik();

                hakmilikBaru.setCawangan(hmmohon.getCawangan());
                hakmilikBaru.setDaerah(hmmohon.getDaerah());
                hakmilikBaru.setBandarPekanMukim(hmmohon.getBandarPekanMukim());
                hakmilikBaru.setSeksyen(null);
                hakmilikBaru.setKodHakmilik(hmmohon.getKodHakmilik());
                //mohon
                hakmilikBaru.setLot(hmmohon.getLot());
                hakmilikBaru.setNoLot(hmmohon.getNoLot());
                hakmilikBaru.setNoHakmilik(hmmohon.getNoHakmilik());
                hakmilikBaru.setLokasi(hmmohon.getLokasi());
                //hakmilikBaru.setKumpulan(hmmohon.getKumpulan());
                hakmilikBaru.setIdHakmilikInduk(hminduk);
                hakmilikBaru.setNoBangunan(jenisBgn);
                hakmilikBaru.setNoTingkat(tgkt);
                hakmilikBaru.setNoPetak((MaksPetak + bilPemilik) + "");
                hakmilikBaru.setKategoriTanah(hmmohon.getKategoriTanah());
                hakmilikBaru.setKodKategoriBangunan(hmmohon.getKodKategoriBangunan());
                hakmilikBaru.setKodTanah(hmmohon.getKodTanah());
                hakmilikBaru.setMaklumatAtasTanah(hmmohon.getMaklumatAtasTanah());
                hakmilikBaru.setSyaratNyata(hmmohon.getSyaratNyata());
                hakmilikBaru.setKegunaanTanah(hmmohon.getKegunaanTanah());
                hakmilikBaru.setSekatanKepentingan(hmmohon.getSekatanKepentingan());
                hakmilikBaru.setTarikhDaftar(new Date());
                hakmilikBaru.setKodStatusHakmilik(hmmohon.getKodStatusHakmilik());
                hakmilikBaru.setPegangan(hmmohon.getPegangan());
                hakmilikBaru.setTempohPegangan(hmmohon.getTempohPegangan());
                hakmilikBaru.setTarikhLuput(hmmohon.getTarikhLuput());
                hakmilikBaru.setRizab(hmmohon.getRizab());
                hakmilikBaru.setLotBumiputera(hmmohon.getLotBumiputera());
                hakmilikBaru.setPbt(hmmohon.getPbt());
                hakmilikBaru.setMilikPersekutuan(hmmohon.getMilikPersekutuan());
                hakmilikBaru.setKodUnitLuas(hmmohon.getKodUnitLuas());
                //luas = pembahagi ;
                //hakmilikBaru.setLuas(hmmohon.getLuas().divide(new BigDecimal(pembahagi), 2, BigDecimal.ROUND_HALF_UP));
                hakmilikBaru.setLuas(hmmohon.getLuas());
                //luas lama
                //uom lama
                hakmilikBaru.setNoLitho(hmmohon.getNoLitho());
                hakmilikBaru.setNoPelan(hmmohon.getNoPelan());
                hakmilikBaru.setNoPu(hmmohon.getNoPu());
                hakmilikBaru.setKodKadarCukai(hmmohon.getKodKadarCukai());
                hakmilikBaru.setCukai(hmmohon.getCukai());
                hakmilikBaru.setCukaiSebenar(hmmohon.getCukaiSebenar());
                //Ansuran
                //kod_sumber
                hakmilikBaru.setDhde(hmmohon.getDhde());
                //hakmilikBaru.setNoVersiDhde(hmmohon.getNoVersiDhde());
                hakmilikBaru.setNoVersiDhde(1);
                hakmilikBaru.setNoVersiDhke(1);
                hakmilikBaru.setNoFail(hmmohon.getNoFail());
                hakmilikBaru.setTarikhBatal(null);
                //dimasuk sume
                hakmilikBaru.setUPI(hmmohon.getUPI());
                hakmilikBaru.setRizabNoWarta(hmmohon.getRizabNoWarta());
                hakmilikBaru.setRizabKawasan(hmmohon.getRizabKawasan());
                hakmilikBaru.setRizabTarikhWarta(hmmohon.getRizabTarikhWarta());
                hakmilikBaru.setPbtNoWarta(hmmohon.getPbtNoWarta());
                hakmilikBaru.setPbtKawasan(hmmohon.getPbtKawasan());
                hakmilikBaru.setPbtTarikhWarta(hmmohon.getPbtTarikhWarta());
                hakmilikBaru.setGsaNoWarta(hmmohon.getGsaNoWarta());
                hakmilikBaru.setGsaKawasan(hmmohon.getGsaKawasan());
                hakmilikBaru.setGsaTarikhWarta(hmmohon.getGsaTarikhWarta());
                hakmilikBaru.setTarikhDaftarAsal(new Date());
                hakmilikBaru.setPelan(hmmohon.getPelan());
                hakmilikBaru.setUnitSyer(hmmohon.getUnitSyer());
                //hakmilikBaru.setUnitSyer(hmmohon.getUnitSyer().divide(new BigDecimal(pembahagi), 2, BigDecimal.ROUND_HALF_UP));
                hakmilikBaru.setBadanPengurusan(hmmohon.getBadanPengurusan());
                //status
                hakmilikBaru.setDhke(hmmohon.getDhke());
                hakmilikBaru.setNoBukuDaftarStrata(hmmohon.getNoBukuDaftarStrata());
                hakmilikBaru.setNoVersiIndeksStrata(hmmohon.getNoVersiIndeksStrata());
                hakmilikBaru.setNoSkim(hmmohon.getNoSkim());
                hakmilikBaru.setJumlahUnitSyer(hmmohon.getJumlahUnitSyer());
                hakmilikBaru.setKodKategoriBangunan(hmmohon.getKodKategoriBangunan());
                //lot tambah
                //id
                //version
                hakmilikBaru.setKodKegunaanBangunan(hmmohon.getKodKegunaanBangunan());

                int petakB = MaksPetak + bilPemilik;
                String petakBaru = petakB + "";
                String s1 = idHakmilikBaru + "" + petakBaru;
                LOG.debug("--Petak Baru--:" + petakBaru);
                hakmilikBaru.setIdHakmilik(s1);
                hakmilikBaru.setInfoAudit(ia);
                // simpan rekod hakmilik baru
                strataService.simpanHakmilik(hakmilikBaru);

                LOG.debug("--Hakmilik baru berjaya disimpan --:" + s1);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                //hakmilikPermohonanDAO.saveOrUpdate(mohonHakmilikBaru);
                strataService.saveHakmilikPermohonan(mohonHakmilikBaru);

                List<HakmilikPetakAksesori> hpaLama = strataService.findIDforPetak(hmmohon.getIdHakmilik());

                if (hpaLama != null) {
                    for (HakmilikPetakAksesori HmPetakAksesori : hpaLama) {
                        HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                        hpa.setHakmilik(hakmilikBaru);
                        hpa.setCawangan(hakmilikBaru.getCawangan());
                        hpa.setKegunaaanPetak(HmPetakAksesori.getKegunaaanPetak());
                        hpa.setNama(HmPetakAksesori.getNama());
                        hpa.setLokasi(HmPetakAksesori.getLokasi());
                        hpa.setStatusHakmilik(HmPetakAksesori.getStatusHakmilik());
                        hpa.setInfoAudit(ia);
                        listHakmilikPetakAksesori.add(hpa);
                    }
                }

                if (hpbaru != null || hpbaru.getAktif() != 'T') {
                    HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                    hpb.setHakmilik(hakmilikBaru);
                    hpb.setCawangan(hakmilikBaru.getCawangan());
                    hpb.setPihakCawangan(hpbaru.getPihakCawangan());
                    hpb.setJenis(hpbaru.getJenis());
                    hpb.setSyerPembilang(1);
                    hpb.setSyerPenyebut(1);
                    hpb.setPerserahan(hpbaru.getPerserahan());
                    hpb.setPerserahanKaveat(hpbaru.getPerserahanKaveat());
                    hpb.setKaveatAktif(hpbaru.getKaveatAktif());
                    hpb.setAktif(hpbaru.getAktif());
                    hpb.setPihak(hpbaru.getPihak());
                    hpb.setNama(hpbaru.getNama());
                    hpb.setAlamat1(hpbaru.getAlamat1());
                    hpb.setAlamat2(hpbaru.getAlamat2());
                    hpb.setAlamat3(hpbaru.getAlamat3());
                    hpb.setAlamat4(hpbaru.getAlamat4());
                    hpb.setNoPengenalan(hpbaru.getNoPengenalan());
                    hpb.setPoskod(hpbaru.getPoskod());
                    hpb.setNegeri(hpbaru.getNegeri());

                    hpb.setInfoAudit(ia);
                    lhp.add(hpb);
                }

                /*INSERT INTO HAKMILIK SEBELUM*/
                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
//                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                hakmilikSebelumBaru.setInfoAudit(ia);
                hakmilikSebelumDAO.save(hakmilikSebelumBaru);


                /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                hsp.setHakmilikPermohonan(idHm);
                hsp.setPermohonan(idHm.getPermohonan());
                hsp.setCawangan(hmmohon.getCawangan());
                hsp.setHakmilik(hakmilikBaru);
                hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                hsp.setInfoAudit(ia);
                hakmilikSblmPermohonanDAO.save(hsp);

                bilPemilik = bilPemilik + 1;
            } // end generate id hakmilik baru     

            hmmohon.setTarikhBatal(new Date());
            KodStatusHakmilik ksh = new KodStatusHakmilik();
            ksh.setKod("B");
            hmmohon.setKodStatusHakmilik(ksh);

            hakmilikDAO.saveOrUpdate(hmmohon);
        }

        // batalkan hakmilik asal   
        if (!lhp.isEmpty()) {
            LOG.debug("---lhp list saving in Hakmilik Pihak---");
            regService.simpanHakmilikPihak(lhp);
        }
        if (!listHakmilikPetakAksesori.isEmpty()) {
            regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
        }

    }

    public void generateHakmilikPHPPStrataN9(InfoAudit ia, Permohonan p, Pengguna pengguna) {

        kodNegeri = conf.getProperty("kodNegeri");

        permohonanSebelum = p.getPermohonanSebelum();

        List<HakmilikPermohonan> hpmohon = strataService.findIdHakmilikByIdMH(permohonanSebelum.getIdPermohonan());
        //.findMohonHakmilikAsc(permohonanSebelum.getIdPermohonan());

        Hakmilik hmmohon = strataService.findInfoByIdHakmilik(hpmohon.get(0).getHakmilik().getIdHakmilik());

        String jenisBgn = hmmohon.getNoBangunan();
        String hminduk = hmmohon.getIdHakmilikInduk();
        String tgkt = hmmohon.getNoTingkat();
        String idhakmilikAsal = hmmohon.getIdHakmilik();
        int idlength = idhakmilikAsal.length();

        int MaksPetak = strataService.findMaxPetakByIdHakmilik(hminduk, jenisBgn);
        int lengthMax = (MaksPetak + "").length();
        int legthReplace = idlength - lengthMax;

        LOG.info("Hakmilik Pohon :------ " + hmmohon.getIdHakmilik());
        LOG.info("Jenis Bngunan :------- " + jenisBgn);
        LOG.info("Hakmilik Induk :------ " + hminduk);
        LOG.info("Maks Petak :------ " + MaksPetak);
        LOG.info(" Petak :------ " + legthReplace);

        //Hakmilik hakmilikBaru = new Hakmilik();
        if (p.getKodUrusan().getKod().equals("HTSC")) {
            String petak = (MaksPetak) + "";
            int i = 1;

            // for (int i = 1; i < 3; i++) {
            String idHakmilikBaru = idhakmilikAsal.substring(0, legthReplace);

            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
            Hakmilik hakmilikBaru = new Hakmilik();

            hakmilikBaru.setCawangan(hmmohon.getCawangan());
            hakmilikBaru.setDaerah(hmmohon.getDaerah());
            hakmilikBaru.setBandarPekanMukim(hmmohon.getBandarPekanMukim());
            hakmilikBaru.setSeksyen(null);
            hakmilikBaru.setKodHakmilik(hmmohon.getKodHakmilik());
            //mohon
            hakmilikBaru.setLot(hmmohon.getLot());
            hakmilikBaru.setNoLot(hmmohon.getNoLot());
            hakmilikBaru.setNoHakmilik(hmmohon.getNoHakmilik());
            hakmilikBaru.setLokasi(hmmohon.getLokasi());
            hakmilikBaru.setKumpulan(hmmohon.getKumpulan());
            hakmilikBaru.setIdHakmilikInduk(hminduk);
            hakmilikBaru.setNoBangunan(jenisBgn);
            hakmilikBaru.setNoTingkat(tgkt);
            hakmilikBaru.setNoPetak((MaksPetak + i) + "");
            hakmilikBaru.setKategoriTanah(hmmohon.getKategoriTanah());
            hakmilikBaru.setKodKategoriBangunan(hmmohon.getKodKategoriBangunan());
            hakmilikBaru.setKodTanah(hmmohon.getKodTanah());
            hakmilikBaru.setMaklumatAtasTanah(hmmohon.getMaklumatAtasTanah());
            hakmilikBaru.setSyaratNyata(hmmohon.getSyaratNyata());
            hakmilikBaru.setKegunaanTanah(hmmohon.getKegunaanTanah());
            hakmilikBaru.setSekatanKepentingan(hmmohon.getSekatanKepentingan());
            hakmilikBaru.setTarikhDaftar(new Date());
            hakmilikBaru.setKodStatusHakmilik(hmmohon.getKodStatusHakmilik());
            hakmilikBaru.setPegangan(hmmohon.getPegangan());
            hakmilikBaru.setTempohPegangan(hmmohon.getTempohPegangan());
            hakmilikBaru.setTarikhLuput(hmmohon.getTarikhLuput());
            hakmilikBaru.setRizab(hmmohon.getRizab());
            hakmilikBaru.setLotBumiputera(hmmohon.getLotBumiputera());
            hakmilikBaru.setPbt(hmmohon.getPbt());
            hakmilikBaru.setMilikPersekutuan(hmmohon.getMilikPersekutuan());
            hakmilikBaru.setKodUnitLuas(hmmohon.getKodUnitLuas());
            hakmilikBaru.setLuas(hmmohon.getLuas());
            //luas lama
            //uom lama
            hakmilikBaru.setNoLitho(hmmohon.getNoLitho());
            hakmilikBaru.setNoPelan(hmmohon.getNoPelan());
            hakmilikBaru.setNoPu(hmmohon.getNoPu());
            hakmilikBaru.setKodKadarCukai(hmmohon.getKodKadarCukai());
            hakmilikBaru.setCukai(hmmohon.getCukai());
            hakmilikBaru.setCukaiSebenar(hmmohon.getCukaiSebenar());
            //Ansuran
            //kod_sumber
            hakmilikBaru.setDhde(hmmohon.getDhde());
            hakmilikBaru.setNoVersiDhde(1);
            hakmilikBaru.setNoVersiDhke(1);
            hakmilikBaru.setNoFail(hmmohon.getNoFail());
            hakmilikBaru.setTarikhBatal(null);
            //dimasuk sume
            hakmilikBaru.setUPI(hmmohon.getUPI());
            hakmilikBaru.setRizabNoWarta(hmmohon.getRizabNoWarta());
            hakmilikBaru.setRizabKawasan(hmmohon.getRizabKawasan());
            hakmilikBaru.setRizabTarikhWarta(hmmohon.getRizabTarikhWarta());
            hakmilikBaru.setPbtNoWarta(hmmohon.getPbtNoWarta());
            hakmilikBaru.setPbtKawasan(hmmohon.getPbtKawasan());
            hakmilikBaru.setPbtTarikhWarta(hmmohon.getPbtTarikhWarta());
            hakmilikBaru.setGsaNoWarta(hmmohon.getGsaNoWarta());
            hakmilikBaru.setGsaKawasan(hmmohon.getGsaKawasan());
            hakmilikBaru.setGsaTarikhWarta(hmmohon.getGsaTarikhWarta());
            hakmilikBaru.setTarikhDaftarAsal(new Date());
            hakmilikBaru.setPelan(hmmohon.getPelan());
            hakmilikBaru.setUnitSyer(hmmohon.getUnitSyer());
            hakmilikBaru.setBadanPengurusan(hmmohon.getBadanPengurusan());
            //status
            hakmilikBaru.setDhke(hmmohon.getDhke());
            hakmilikBaru.setNoBukuDaftarStrata(hmmohon.getNoBukuDaftarStrata());
            hakmilikBaru.setNoVersiIndeksStrata(hmmohon.getNoVersiIndeksStrata());
            hakmilikBaru.setNoSkim(hmmohon.getNoSkim());
            hakmilikBaru.setJumlahUnitSyer(hmmohon.getJumlahUnitSyer());
            hakmilikBaru.setKodKategoriBangunan(hmmohon.getKodKategoriBangunan());
            //lot tambah
            //id
            //version
            hakmilikBaru.setKodKegunaanBangunan(hmmohon.getKodKegunaanBangunan());

            int petakB = MaksPetak + i;
            String petakBaru = petakB + "";
            String s1 = idHakmilikBaru + "" + petakBaru;
            LOG.debug("--Petak Baru--:" + petakBaru);
            hakmilikBaru.setIdHakmilik(s1);
            hakmilikBaru.setInfoAudit(ia);
            // simpan rekod hakmilik baru
            strataService.simpanHakmilik(hakmilikBaru);

            LOG.debug("--Hakmilik baru berjaya disimpan --:" + s1);

            /*INSERT INTO MOHON HAKMILIK*/
            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
            mohonHakmilikBaru.setPermohonan(p);
            mohonHakmilikBaru.setInfoAudit(ia);
            //hakmilikPermohonanDAO.saveOrUpdate(mohonHakmilikBaru);
            strataService.saveHakmilikPermohonan(mohonHakmilikBaru);

            List<HakmilikPetakAksesori> hpaLama = strataService.findIDforPetak(hmmohon.getIdHakmilik());

            if (hpaLama != null) {
                for (HakmilikPetakAksesori HmPetakAksesori : hpaLama) {
                    HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                    hpa.setHakmilik(hakmilikBaru);
                    hpa.setCawangan(hakmilikBaru.getCawangan());
                    hpa.setKegunaaanPetak(HmPetakAksesori.getKegunaaanPetak());
                    hpa.setNama(HmPetakAksesori.getNama());
                    hpa.setLokasi(HmPetakAksesori.getLokasi());
                    hpa.setStatusHakmilik(HmPetakAksesori.getStatusHakmilik());
                    hpa.setInfoAudit(ia);
                    listHakmilikPetakAksesori.add(hpa);
                }
            }

            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hmmohon);
            LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
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
                hpb.setNoPengenalan(hpk.getNoPengenalan());
                hpb.setPoskod(hpk.getPoskod());
                hpb.setNegeri(hpk.getNegeri());

                hpb.setInfoAudit(ia);
                lhp.add(hpb);
            }

            SejarahHakmilik hMasterTitle2 = sejarahHakmilikDAO.findById(hpmohon.get(0).getHakmilik().getIdHakmilik());

            kodNegeri = conf.getProperty("kodNegeri");

            for (HakmilikPermohonan idHm : hpmohon) {
                /*INSERT INTO HAKMILIK SEBELUM*/
                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(idHm.getHakmilik().getIdHakmilik());
                Hakmilik hmmohon2 = strataService.findInfoByIdHakmilik(idHm.getHakmilik().getIdHakmilik());

                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
//                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                if (kodNegeri.equals("05")) {
//                    LOG.info("p.getKodUrusan().getkod" + p.getKodUrusan().getKod().toString());
                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                }
                hakmilikSebelumBaru.setInfoAudit(ia);
                hakmilikSebelumDAO.save(hakmilikSebelumBaru);


                /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                hsp.setHakmilikPermohonan(idHm);
                hsp.setPermohonan(idHm.getPermohonan());
                hsp.setCawangan(hmmohon2.getCawangan());
                hsp.setHakmilik(hakmilikBaru);
                hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                hsp.setInfoAudit(ia);
                hakmilikSblmPermohonanDAO.save(hsp);

                // } // End looping generate ID 
                // batalkan hakmilik asal          
                hmmohon2.setTarikhBatal(new Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("B");
                hmmohon2.setKodStatusHakmilik(ksh);

                hakmilikDAO.saveOrUpdate(hmmohon2); // batalkan hakmilik asal          

            }

        }  //End for HTSC

        if (!lhp.isEmpty()) {
            LOG.debug("---lhp list saving in Hakmilik Pihak---");
            regService.simpanHakmilikPihak(lhp);
        }
        if (!listHakmilikPetakAksesori.isEmpty()) {
            regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
        }

    }

    //Added by ida for N9
    public void generateHakmilikStrataN9(InfoAudit ia, Permohonan p, Pengguna pengguna) {

        kodNegeri = conf.getProperty("kodNegeri");
        KodUrusan kodUrusanSblm = new KodUrusan();
        if (kodNegeri.equals("04")) {
            permohonanSebelum = p;
        } else {
            permohonanSebelum = p.getPermohonanSebelum();
            kodUrusanSblm = permohonanSebelum.getKodUrusan();
        }

        if (permohonanSebelum != null) {
            List<PermohonanStrata> listStrata = permohonanSebelum.getSenaraiStrata();
            Hakmilik hMasterTitle = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
            LOG.debug(":generate Hakmilik Strata:");
            LOG.debug("idPermohonanSebelum :" + permohonanSebelum.getIdPermohonan());
            List<PermohonanBangunan> senaraiBangunan = permohonanSebelum.getSenaraiBangunan();
            //List<PermohonanBangunan> senaraiBangunan = strService.findByIDMohonBlokWoProv(permohonanSebelum.getIdPermohonan());

            //TODO :  count the total of unit syer
            int totalUnitSyer = 0;
            LOG.debug(": COUNT BEGIN : ");
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                // List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                //ida update 28082013
                List<BangunanTingkat> senaraiTingkat;
                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    senaraiTingkat = strataService.findByIDBangunanTingkat(permohonanBangunan.getIdBangunan());
                } else {
                    senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                }

                for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                        if (bangunanPetak.getSyer() != null) {
                            LOG.debug(":: " + totalUnitSyer + " + " + bangunanPetak.getSyer());
                            totalUnitSyer = totalUnitSyer + bangunanPetak.getSyer();
                            LOG.debug(": Jumlah Syer = " + totalUnitSyer);
                        }
                    }
                }
            }

            LOG.debug(":Bangunan : " + senaraiBangunan.size());
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();

                // List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                //ida update 28082013 
                List<BangunanTingkat> senaraiTingkat;

                senaraiTingkat = strataService.findByIDBangunanTingkat(permohonanBangunan.getIdBangunan());

                LOG.debug(":Tingkat : " + senaraiTingkat.size());
                int countTingkat = 1;
                // Added to check LandParcel
                if (!senaraiTingkat.isEmpty()) {
                    LOG.debug("---Bangunans---");
                    for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                        List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();

                        LOG.debug(":Petak : " + senaraiPetak.size());
                        int countPetak = 1;
                        for (BangunanPetak bangunanPetak : senaraiPetak) {
                            String noBangunan = permohonanBangunan.getNama();

                            LOG.debug("---ID Bangunan---" + permohonanBangunan.getIdBangunan());
                            LOG.debug("---Bangunan name---" + permohonanBangunan.getNama());

                            String noTingkat = String.valueOf(countTingkat);
                            String noPetak = bangunanPetak.getNama();

                            // added by zadirul                            
                            KodUOM kodUOM = bangunanPetak.getLuasUom();
                            if (kodUOM == null) {
                                //set default to 'meter persegi' if Kod_UOM in bangunanPetak is null
                                kodUOM = kodUOMDAO.findById("M");
                            }

                            Hakmilik hakmilik = new Hakmilik();
                            hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilik.setDaerah(hMasterTitle.getDaerah());
                            hakmilik.setNoBangunan(noBangunan);
                            hakmilik.setNoTingkat(noTingkat);
                            hakmilik.setNoPetak(noPetak);

                            //----------- added by zadirul
                            hakmilik.setKodKategoriBangunan(kodKatBngn);
                            int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                            LOG.debug("---jumlah unit syor---saving in hakmilik---:" + jumlahunit);
                            hakmilik.setJumlahUnitSyer(jumlahunit);
                            hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                            hakmilik.setCukai(BigDecimal.ZERO);
                            hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                            LOG.info("----kodNegeri----" + conf.getProperty("kodNegeri"));
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
                                HakmilikPermohonan hmPmhon = strataService.findMohonHakmilik(permohonanSebelum.getIdPermohonan());
                                LOG.info("----idHakmilik----" + hmPmhon.getHakmilik().getIdHakmilik());
                                Hakmilik hm = hmDAO.findById(hmPmhon.getHakmilik().getIdHakmilik());
                                BadanPengurusan bdnUrus = strataService.findBdn(permohonanSebelum.getIdPermohonan());
//                                if (bdnUrus != null) {
//                                    LOG.debug("--BadanPengurusan Hakmilik Induk--:" + bdnUrus);
//                                    hm.setBadanPengurusan(bdnUrus);
//                                    hm.setNoSkim(listStrata.get(0).getNamaSkim());
//                                    if (bangunanPetak.getSyer() != null) {
//                                        BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
//                                        hm.setUnitSyer(unitSyer);
//                                    }
//                                    hm.setJumlahUnitSyer(jumlahunit);
//                                    hmDAO.saveOrUpdate(hm);
//                                }
                            }
                            LOG.debug("noBangunan : " + hakmilik.getNoBangunan());
                            LOG.debug("noBangunan substring 1 : " + hakmilik.getNoBangunan().substring(1));
                            String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                            Hakmilik hakmilikBaru = new Hakmilik();
                            if (!listStrata.isEmpty()) {
                                hakmilikBaru.setNoSkim(listStrata.get(0).getNamaSkim());
                            }
                            /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
 /*added to make 28 charaters only coz in hakmilik table coloumn having 28 only
                             actual hakmilik size is 39 characters
                             */
                            String khm = hMasterTitle.getKodHakmilik().getKod();
                            LOG.debug("--Kod Hakmilik--:" + khm);
                            String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                            LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                            hakmilikBaru.setIdHakmilik(s1);
                            hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                //  hakmilikBaru.setCawangan(pengguna.getKodCawangan());

                                //add by ida 25/06/2013
                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            }
                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                            hakmilikBaru.setLot(hMasterTitle.getLot());
                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                            ksh.setKod("T");
                            hakmilikBaru.setKodStatusHakmilik(ksh);
                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                            hakmilikBaru.setKodUnitLuas(kodUOM);
                            if (bangunanPetak.getLuas() != null) {
                                BigDecimal luasPetak = bangunanPetak.getLuas();
                                hakmilikBaru.setLuas(luasPetak);
                            } else {
                                hakmilikBaru.setLuas(BigDecimal.ZERO);
                            }
                            String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                            /*copy NO HAKMILIK*/
                            LOG.debug("noHakmilik :" + noHakmilik);
                            hakmilikBaru.setNoHakmilik(noHakmilik);
                            /*commented by murali @NS PAT 14-08-2012 */
                            //hakmilikBaru.setNoPelan(hMasterTitle.getNoPelan());
                            /*added by murali @NS PAT 14-08-2012 */
                            hakmilikBaru.setNoPelan("");
                            hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                            hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                            hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                            hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                            hakmilikBaru.setCukai(BigDecimal.ZERO); //added by murali @MLK PAT 26-09-2012
                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                hakmilikBaru.setNoVersiDhde(1);
                                hakmilikBaru.setNoVersiDhke(1);
                            } else {
                                hakmilikBaru.setNoVersiDhde(0);
                                hakmilikBaru.setNoVersiDhke(0);
                            }

                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                            if (kodNegeri.equals("04")) {
                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            }
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            }
                            LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
                            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                            if (bdn != null) {
                                LOG.debug("--BadanPengurusan--:" + bdn);
                                hakmilikBaru.setBadanPengurusan(bdn);
                            }
                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                            hakmilikBaru.setNoBangunan(noBangunan);
                            hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                            hakmilikBaru.setNoTingkat(noTingkat);
                            hakmilikBaru.setNoPetak(noPetak);
                            if (bangunanPetak.getSyer() != null) {
                                BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                LOG.debug("--unit syor saving in Hakmilik Baru:" + unitSyer);
                                hakmilikBaru.setUnitSyer(unitSyer);
                            }
                            /* modified by murali @NS 24-09-2012
                             LOG.debug("--Jumlah unity syor saving in Hakmilik Baru:" + totalUnitSyer);
                             hakmilikBaru.setJumlahUnitSyer(totalUnitSyer);
                             */
                            int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                            LOG.debug("---jumlah unit syor---saving in hakmilikBaru---:" + jumlahunit1);
                            hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                LOG.debug("---kod guna bangunan from mohon_bngn---:" + permohonanBangunan.getKodKegunaanBangunan());
                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                            }
                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                            hakmilikBaru.setInfoAudit(ia);

                            /*INSERT INTO MOHON HAKMILIK*/
                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                            mohonHakmilikBaru.setPermohonan(p);
                            mohonHakmilikBaru.setInfoAudit(ia);
                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                            listHakmilikBaru.add(hakmilikBaru);

                            List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                            for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                hpa.setHakmilik(hakmilikBaru);
                                hpa.setCawangan(hakmilikBaru.getCawangan());
                                hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                hpa.setNama(bangunanPetakAksesori.getNama());
                                hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                hpa.setInfoAudit(ia);
                                listHakmilikPetakAksesori.add(hpa);
                            }

                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                            LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
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

                                //Updated by ida 20/04/2013
                                if ("05".equals(conf.getProperty("kodNegeri"))) {
                                    hpb.setNama(hpk.getNama());
                                    hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                    hpb.setNoPengenalan(hpk.getNoPengenalan());
                                    hpb.setAlamat1(hpk.getAlamat1());
                                    hpb.setAlamat2(hpk.getAlamat2());
                                    hpb.setAlamat3(hpk.getAlamat3());
                                    hpb.setAlamat4(hpk.getAlamat4());
                                    hpb.setPoskod(hpk.getPoskod());
                                    hpb.setNegeri(hpk.getNegeri());
                                    hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                    hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                                    hpb.setWargaNegara(hpk.getWargaNegara());
                                }
                                hpb.setInfoAudit(ia);
                                lhp.add(hpb);
                            }
                            countPetak += 1;
                        }
                        countTingkat += 1;
                    }
                } //**************************** Untuk IDHakmilik Landed 
                else // if LandParcel go to else conditon - Kalau Tanah                    
                //ABC _ Test
                //******************** KOD NEGERI SEMBILAN  ************************     
                if ("05".equals(conf.getProperty("kodNegeri"))) {

                    senaraiTingkat = strataService.findByIDBangunanTingkatL(permohonanBangunan.getIdBangunan());
                    //Dapatkan bapa rows tingkat untuk L

                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat2 = 1;
                    if (!senaraiTingkat.isEmpty()) { //Jika senarai tingkat kosong

                        for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                            List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();

                            LOG.debug(":Petak : " + senaraiPetak.size());
                            int countPetak2 = 1;
                            for (BangunanPetak bangunanPetak : senaraiPetak) {
                                String noBangunan = permohonanBangunan.getNama();

                                LOG.debug("---ID Bangunan---" + permohonanBangunan.getIdBangunan());
                                LOG.debug("---Bangunan name---" + permohonanBangunan.getNama());

                                String noTingkat = String.valueOf(countTingkat);
                                String noPetak = bangunanPetak.getNama();

                                // added by zadirul                            
                                KodUOM kodUOM = bangunanPetak.getLuasUom();
                                if (kodUOM == null) {
                                    //set default to 'meter persegi' if Kod_UOM in bangunanPetak is null
                                    kodUOM = kodUOMDAO.findById("M");
                                }

                                Hakmilik hakmilik = new Hakmilik();
                                hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                hakmilik.setDaerah(hMasterTitle.getDaerah());
                                hakmilik.setNoBangunan("L");
                                hakmilik.setNoTingkat("0");
                                hakmilik.setNoPetak(noPetak);

                                //----------- added by zadirul
                                hakmilik.setKodKategoriBangunan(kodKatBngn);
                                int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                                LOG.debug("---jumlah unit syor---saving in hakmilik---:" + jumlahunit);
                                hakmilik.setJumlahUnitSyer(jumlahunit);
                                hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                hakmilik.setCukai(BigDecimal.ZERO);
                                hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                LOG.info("----kodNegeri----" + conf.getProperty("kodNegeri"));
                                if ("05".equals(conf.getProperty("kodNegeri"))) {
                                    LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
                                    HakmilikPermohonan hmPmhon = strataService.findMohonHakmilik(permohonanSebelum.getIdPermohonan());
                                    LOG.info("----idHakmilik----" + hmPmhon.getHakmilik().getIdHakmilik());
                                    Hakmilik hm = hmDAO.findById(hmPmhon.getHakmilik().getIdHakmilik());
                                    BadanPengurusan bdnUrus = strataService.findBdn(permohonanSebelum.getIdPermohonan());
//                                if (bdnUrus != null) {
//                                    LOG.debug("--BadanPengurusan Hakmilik Induk--:" + bdnUrus);
//                                    hm.setBadanPengurusan(bdnUrus);
//                                    hm.setNoSkim(listStrata.get(0).getNamaSkim());
//                                    if (bangunanPetak.getSyer() != null) {
//                                        BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
//                                        hm.setUnitSyer(unitSyer);
//                                    }
//                                    hm.setJumlahUnitSyer(jumlahunit);
//                                    hmDAO.saveOrUpdate(hm);
//                                }
                                }
                                LOG.debug("noBangunan : " + hakmilik.getNoBangunan());
                                LOG.debug("noBangunan substring 1 : " + hakmilik.getNoBangunan());
                                // String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);

                                String idHakmilikBaru = hMasterTitle.getIdHakmilik() + "000L" + noPetak;
                                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                Hakmilik hakmilikBaru = new Hakmilik();
                                if (!listStrata.isEmpty()) {
                                    hakmilikBaru.setNoSkim(listStrata.get(0).getNamaSkim());
                                }
                                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
 /*added to make 28 charaters only coz in hakmilik table coloumn having 28 only
                                     actual hakmilik size is 39 characters
                                 */
                                String khm = hMasterTitle.getKodHakmilik().getKod();
                                LOG.debug("--Kod Hakmilik--:" + khm);

//	String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                String s1 = hMasterTitle.getIdHakmilik() + "000L" + noPetak;

//String s1 = noHakmilikLama + "000L" + Integer.parseInt(noPetakL.substring(1));
                                LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                hakmilikBaru.setIdHakmilik(s1);
                                hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                                if ("05".equals(conf.getProperty("kodNegeri"))) {
                                    //  hakmilikBaru.setCawangan(pengguna.getKodCawangan());

                                    //add by ida 25/06/2013
                                    hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                }
                                hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                hakmilikBaru.setLot(hMasterTitle.getLot());
                                hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                KodStatusHakmilik ksh = new KodStatusHakmilik();
                                ksh.setKod("T");
                                hakmilikBaru.setKodStatusHakmilik(ksh);
                                hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                hakmilikBaru.setKodUnitLuas(kodUOM);
                                if (bangunanPetak.getLuas() != null) {
                                    BigDecimal luasPetak = bangunanPetak.getLuas();
                                    hakmilikBaru.setLuas(luasPetak);
                                } else {
                                    hakmilikBaru.setLuas(BigDecimal.ZERO);
                                }
                                // String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                String noHakmilik = hMasterTitle.getNoHakmilik();
                                /*copy NO HAKMILIK*/
                                LOG.debug("noHakmilik :" + noHakmilik);
                                hakmilikBaru.setNoHakmilik(noHakmilik);
                                /*commented by murali @NS PAT 14-08-2012 */
                                //hakmilikBaru.setNoPelan(hMasterTitle.getNoPelan());
                                /*added by murali @NS PAT 14-08-2012 */
                                hakmilikBaru.setNoPelan("");
                                hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                                hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                                hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                hakmilikBaru.setCukai(BigDecimal.ZERO); //added by murali @MLK PAT 26-09-2012
                                hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                hakmilikBaru.setNoVersiDhde(1);
                                hakmilikBaru.setNoVersiDhke(1);
                                hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());

                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());

                                LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
                                BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                if (bdn != null) {
                                    LOG.debug("--BadanPengurusan--:" + bdn);
                                    hakmilikBaru.setBadanPengurusan(bdn);
                                }
                                hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                hakmilikBaru.setNoBangunan(noBangunan);
                                hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                hakmilikBaru.setNoTingkat("0");
                                // String noPetak2 = "L"+bangunanPetak.getNama();
                                String noPetak2 = bangunanPetak.getNama();
                                hakmilikBaru.setNoPetak(noPetak2);
                                if (bangunanPetak.getSyer() != null) {
                                    BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                    LOG.debug("--unit syor saving in Hakmilik Baru:" + unitSyer);
                                    hakmilikBaru.setUnitSyer(unitSyer);
                                }
                                /* modified by murali @NS 24-09-2012
                                     LOG.debug("--Jumlah unity syor saving in Hakmilik Baru:" + totalUnitSyer);
                                     hakmilikBaru.setJumlahUnitSyer(totalUnitSyer);
                                 */
                                int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                                LOG.debug("---jumlah unit syor---saving in hakmilikBaru---:" + jumlahunit1);
                                hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                    LOG.debug("---kod guna bangunan from mohon_bngn---:" + permohonanBangunan.getKodKegunaanBangunan());
                                    hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                }
                                hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                hakmilikBaru.setInfoAudit(ia);

                                /*INSERT INTO MOHON HAKMILIK*/
                                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                mohonHakmilikBaru.setPermohonan(p);
                                mohonHakmilikBaru.setInfoAudit(ia);
                                listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                listHakmilikBaru.add(hakmilikBaru);

                                List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                    HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                    hpa.setHakmilik(hakmilikBaru);
                                    hpa.setCawangan(hakmilikBaru.getCawangan());
                                    hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                    hpa.setNama(bangunanPetakAksesori.getNama());
                                    hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                    hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                    hpa.setInfoAudit(ia);
                                    listHakmilikPetakAksesori.add(hpa);
                                }

                                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                                LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
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

                                    //Updated by ida 20/04/2013
                                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                                        hpb.setNama(hpk.getNama());
                                        hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                        hpb.setNoPengenalan(hpk.getNoPengenalan());
                                        hpb.setAlamat1(hpk.getAlamat1());
                                        hpb.setAlamat2(hpk.getAlamat2());
                                        hpb.setAlamat3(hpk.getAlamat3());
                                        hpb.setAlamat4(hpk.getAlamat4());
                                        hpb.setPoskod(hpk.getPoskod());
                                        hpb.setNegeri(hpk.getNegeri());
                                        hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                        hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                                        hpb.setWargaNegara(hpk.getWargaNegara());
                                    }
                                    hpb.setInfoAudit(ia);
                                    lhp.add(hpb);
                                }
                                countPetak2 += 1;
                            }
                            countTingkat2 += 1;
                        }

                        //END LANDED PARCEL
                    }
                } //******************** END NEGERI SEMBILAN  ************************//else end
                //**********************************************

            } // End for loop by counting mohon_bngn records

            /*
             if (kodUrusanSblm.equals("PBS") || kodUrusanSblm.equals("PBTS")) {

             List<PermohonanBangunan> senaraiBangunanProv = strService.findByIDMohonByProvisional(permohonanSebelum.getIdPermohonan());

             //TODO :  count the total of unit syer
             int totalUnitSyerP = 0;
             LOG.debug(": COUNT BEGIN : ");
             for (PermohonanBangunan permohonanBangunan : senaraiBangunanProv) {

             //Create ID Hakmilik baru
             String idHakmilikBaruP = hMasterTitle.getIdHakmilik() + "0" + permohonanBangunan.getNama();
             LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaruP);
             Hakmilik hakmilikBaru = new Hakmilik();

             LOG.debug("SAVING NEW ID HAKMILIK : " + idHakmilikBaruP);
             hakmilikBaru.setIdHakmilik(idHakmilikBaruP);
             hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
             hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
             hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
             hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
             hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
             hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
             hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
             hakmilikBaru.setLot(hMasterTitle.getLot());
             hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
             hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
             hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
             hakmilikBaru.setTarikhDaftar(new java.util.Date());
             KodStatusHakmilik ksh = new KodStatusHakmilik();
             ksh.setKod("T");
             hakmilikBaru.setKodStatusHakmilik(ksh);
             hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
             hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
             KodUOM kodUOM = kodUOMDAO.findById("M");

             hakmilikBaru.setKodUnitLuas(kodUOM);
             hakmilikBaru.setLuas(BigDecimal.ZERO);
             // String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
             String noHakmilik = hMasterTitle.getNoHakmilik();
             /*copy NO HAKMILIK
             LOG.debug("noHakmilik :" + noHakmilik);
             hakmilikBaru.setNoHakmilik(noHakmilik);

             hakmilikBaru.setNoPelan("");
             hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
             hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
             hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
             hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
             hakmilikBaru.setPbt(hMasterTitle.getPbt());
             hakmilikBaru.setCukai(BigDecimal.ZERO); //added by murali @MLK PAT 26-09-2012
             hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
             hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
             hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
             hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
             hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
             hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
             hakmilikBaru.setNoVersiDhde(1);
             hakmilikBaru.setNoVersiDhke(1);
             hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());

             hakmilikBaru.setCawangan(hMasterTitle.getCawangan());

             LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
             BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
             if (bdn != null) {
             LOG.debug("--BadanPengurusan--:" + bdn);
             hakmilikBaru.setBadanPengurusan(bdn);
             }
             hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
             hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
             hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
             hakmilikBaru.setNoBangunan(permohonanBangunan.getNama());
             hakmilikBaru.setKodKategoriBangunan(permohonanBangunan.getKodKategoriBangunan());
             hakmilikBaru.setNoTingkat("0");
             // String noPetak2 = "L"+bangunanPetak.getNama();
             hakmilikBaru.setNoPetak("0");
             if (permohonanBangunan.getSyerBlok() != null) {
             BigDecimal unitSyer = new BigDecimal(permohonanBangunan.getSyerBlok());
             LOG.debug("--unit syor saving in Hakmilik Baru:" + unitSyer);
             hakmilikBaru.setUnitSyer(unitSyer);
             }

             int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
             LOG.debug("---jumlah unit syor---saving in hakmilikBaru---:" + jumlahunit1);
             hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
             if (permohonanBangunan.getKodKegunaanBangunan() != null) {
             LOG.debug("---kod guna bangunan from mohon_bngn---:" + permohonanBangunan.getKodKegunaanBangunan());
             hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
             }
             hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
             hakmilikBaru.setInfoAudit(ia);

             strService.simpanHakmilik(hakmilikBaru);

             }


             } */
            if (!listHakmilikBaru.isEmpty()) {
                for (Hakmilik hakmilik : listHakmilikBaru) {
                    LOG.debug("--commenservice Hakmilik--:" + hakmilik.getIdHakmilik());
                    regService.simpanHakmilik(hakmilik);
                }
            }

            if (!listMohonHakmilikBaru.isEmpty()) {
                for (HakmilikPermohonan hp : listMohonHakmilikBaru) {
                    LOG.debug("--CS IdHakmilik--:" + hp.getHakmilik().getIdHakmilik());
                    LOG.debug("--CS IdPermohonan--:" + hp.getPermohonan().getIdPermohonan());
                    regService.simpanMohonHakmilik(hp);
                }
            }

            if (!lhp.isEmpty()) {
                LOG.debug("---lhp list saving in Hakmilik Pihak---");
                regService.simpanHakmilikPihak(lhp);
            }
            if (!listHakmilikPetakAksesori.isEmpty()) {
                regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
            }
        }

    }

    public void generateHakmilikStrataN9manual(InfoAudit ia, Permohonan p, Pengguna pengguna) {

        kodNegeri = conf.getProperty("kodNegeri");
        KodUrusan kodUrusanSblm = new KodUrusan();

        permohonanSebelum = p.getPermohonanSebelum();
        kodUrusanSblm = permohonanSebelum.getKodUrusan();

        if (permohonanSebelum != null) {
            List<PermohonanStrata> listStrata = permohonanSebelum.getSenaraiStrata();
            Hakmilik hMasterTitle = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
            LOG.debug(":generate Hakmilik Strata:");
            LOG.debug("idPermohonanSebelum :" + permohonanSebelum.getIdPermohonan());
            List<PermohonanBangunan> senaraiBangunan = permohonanSebelum.getSenaraiBangunan();
            //List<PermohonanBangunan> senaraiBangunan = strService.findByIDMohonBlokWoProv(permohonanSebelum.getIdPermohonan());

            //TODO :  count the total of unit syer
            int totalUnitSyer = 0;
            LOG.debug(": COUNT BEGIN : ");
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                totalUnitSyer = totalUnitSyer + permohonanBangunan.getSyerBlok();
            }

            LOG.debug(":Bangunan : " + senaraiBangunan.size());
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();

                List<BangunanTingkat> senaraiTingkat;

                senaraiTingkat = strataService.findByIDBangunanTingkat(permohonanBangunan.getIdBangunan());

                LOG.debug(":Tingkat : " + senaraiTingkat.size());
                int countTingkat = 1;
                if (!senaraiTingkat.isEmpty()) {
                    LOG.debug("---Bangunans---");
                    for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                        List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();

                        LOG.debug(":Petak : " + senaraiPetak.size());
                        int countPetak = 1;
                        for (BangunanPetak bangunanPetak : senaraiPetak) {
                            String noBangunan = permohonanBangunan.getNama();

                            LOG.debug("---ID Bangunan---" + permohonanBangunan.getIdBangunan());
                            LOG.debug("---Bangunan name---" + permohonanBangunan.getNama());

                            String noTingkat = bangunanTingkat.getNama();
                            String noPetak = bangunanPetak.getNama();

                            // added by zadirul
                            KodUOM kodUOM = bangunanPetak.getLuasUom();
                            if (kodUOM == null) {
                                //set default to 'meter persegi' if Kod_UOM in bangunanPetak is null
                                kodUOM = kodUOMDAO.findById("M");
                            }
                            String idHakmilikBaru = null;
                            if (noTingkat.startsWith("B")) {
                                idHakmilikBaru = hMasterTitle.getIdHakmilik() + StringUtils.leftPad(noBangunan, 3, '0')
                                        + StringUtils.leftPad(noTingkat, 3, '0') + String.format("%05d", Integer.parseInt(noPetak));
                            } else {
                                String notgkt = String.format("%03d", bangunanTingkat.getTingkat());
                                idHakmilikBaru = hMasterTitle.getIdHakmilik() + StringUtils.leftPad(noBangunan, 3, '0')
                                        + String.format("%03d", Integer.parseInt(notgkt)) + String.format("%05d", Integer.parseInt(noPetak));
                            }

                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                            Hakmilik hakmilikBaru = new Hakmilik();
                            if (!listStrata.isEmpty()) {
                                hakmilikBaru.setNoSkim(listStrata.get(0).getNamaSkim());
                            }
                            String khm = hMasterTitle.getKodHakmilik().getKod();
                            LOG.debug("--Kod Hakmilik--:" + khm);
                            hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                            hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            }
                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                            hakmilikBaru.setLot(hMasterTitle.getLot());
                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                            ksh.setKod("T");
                            hakmilikBaru.setKodStatusHakmilik(ksh);
                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                            hakmilikBaru.setKodUnitLuas(kodUOM);
                            if (bangunanPetak.getLuas() != null) {
                                BigDecimal luasPetak = bangunanPetak.getLuas();
                                hakmilikBaru.setLuas(luasPetak);
                            } else {
                                hakmilikBaru.setLuas(BigDecimal.ZERO);
                            }
                            String noHakmilik = hMasterTitle.getNoHakmilik();
                            hakmilikBaru.setNoHakmilik(noHakmilik);
                            hakmilikBaru.setNoPelan(bangunanPetak.getPabPetak());
                            hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                            hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                            hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                            hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                            hakmilikBaru.setCukai(BigDecimal.ZERO); //added by murali @MLK PAT 26-09-2012
                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                hakmilikBaru.setNoVersiDhde(1);
                                hakmilikBaru.setNoVersiDhke(1);
                            }

                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                            if (kodNegeri.equals("04")) {
                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            }
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            }
                            LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
                            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                            if (bdn != null) {
                                LOG.debug("--BadanPengurusan--:" + bdn);
                                hakmilikBaru.setBadanPengurusan(bdn);
                            }
                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                            hakmilikBaru.setNoBangunan(noBangunan);
                            hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                            hakmilikBaru.setNoTingkat(noTingkat);
                            hakmilikBaru.setNoPetak(noPetak);
                            if (bangunanPetak.getSyer() != null) {
                                BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                LOG.debug("--unit syor saving in Hakmilik Baru:" + unitSyer);
                                hakmilikBaru.setUnitSyer(unitSyer);
                            }

                            hakmilikBaru.setJumlahUnitSyer(totalUnitSyer);
                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                LOG.debug("---kod guna bangunan from mohon_bngn---:" + permohonanBangunan.getKodKegunaanBangunan());
                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                            }
                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                            hakmilikBaru.setMezanin(bangunanTingkat.getMezanin());
                            hakmilikBaru.setMenara(permohonanBangunan.getLain());
                            hakmilikBaru.setInfoAudit(ia);

                            /*INSERT INTO MOHON HAKMILIK*/
                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                            mohonHakmilikBaru.setPermohonan(p);
                            mohonHakmilikBaru.setInfoAudit(ia);
                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                            LOG.info("ID STRATA -- " + hakmilikBaru);
                            listHakmilikBaru.add(hakmilikBaru);

                            List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                            for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                hpa.setHakmilik(hakmilikBaru);
                                hpa.setCawangan(hakmilikBaru.getCawangan());
                                hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                hpa.setNama(bangunanPetakAksesori.getNama());
                                hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                hpa.setInfoAudit(ia);
                                listHakmilikPetakAksesori.add(hpa);
                            }

                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikPihakActiveByHakmilik(hMasterTitle);
                            LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                                if (hpk == null || hpk.getAktif() == 'T') {
                                    continue;
                                }

                                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                                LOG.info("hakmilikBaru--" + hakmilikBaru.getIdHakmilik());
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
                                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                hpb.setNoPengenalan(hpk.getNoPengenalan());
                                hpb.setAlamat1(hpk.getAlamat1());
                                hpb.setAlamat2(hpk.getAlamat2());
                                hpb.setAlamat3(hpk.getAlamat3());
                                hpb.setAlamat4(hpk.getAlamat4());
                                hpb.setPoskod(hpk.getPoskod());
                                hpb.setNegeri(hpk.getNegeri());
                                hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                                hpb.setWargaNegara(hpk.getWargaNegara());
                                hpb.setInfoAudit(ia);
                                lhp.add(hpb);
                            }
                            countPetak += 1;
                        }
                        countTingkat += 1;
                    }
                }
            }
            if (!listHakmilikBaru.isEmpty()) {
                for (Hakmilik hakmilik : listHakmilikBaru) {
                    Hakmilik hm = hakmilikDAO.findById(hakmilik.getIdHakmilik());
                    if (hm == null) {
                        LOG.debug("--commenservice Hakmilik--:" + hakmilik.getIdHakmilik());
                        regService.simpanHakmilik(hakmilik);
                    }
                }
            }

            if (!listMohonHakmilikBaru.isEmpty()) {
                for (HakmilikPermohonan hp : listMohonHakmilikBaru) {

                    HakmilikPermohonan hmp = strataService.findByIdHakmilikIdPermohonan(p.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
                    if (hmp == null) {
                        LOG.debug("--CS IdHakmilik--:" + hp.getHakmilik().getIdHakmilik());
                        LOG.debug("--CS IdPermohonan--:" + hp.getPermohonan().getIdPermohonan());
                        regService.simpanMohonHakmilik(hp);
                    }
                }
            }

            if (!lhp.isEmpty()) {
                LOG.debug("---lhp--" + lhp.size());
                for (HakmilikPihakBerkepentingan hpb : lhp) {
                    LOG.debug("---hpb---" + hpb.getHakmilik().getIdHakmilik());
                    LOG.debug("---lhp list saving in Hakmilik Pihak---");
                    regService.simpanHakmilikPihak(hpb);
                }
            }
            if (!listHakmilikPetakAksesori.isEmpty()) {
                regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
            }
        }

    }

    public void generateHakmilikStrataPBBSSN9(InfoAudit ia, Permohonan p, Pengguna pengguna) {

        kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            permohonanSebelum = p;
        } else {
            permohonanSebelum = p.getPermohonanSebelum();
        }

        if (permohonanSebelum != null) {
            List<PermohonanStrata> listStrata = permohonanSebelum.getSenaraiStrata();
            Hakmilik hMasterTitle = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
            LOG.debug(":generate Hakmilik Strata:");
            LOG.debug("idPermohonanSebelum :" + permohonanSebelum.getIdPermohonan());
            List<PermohonanBangunan> senaraiBangunan = permohonanSebelum.getSenaraiBangunan();

            //TODO :  count the total of unit syer
            int totalUnitSyer = 0;
            LOG.debug(": COUNT BEGIN : ");
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                // List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                //ida update 28082013
                List<BangunanTingkat> senaraiTingkat;
                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    senaraiTingkat = strataService.findByIDBangunanTingkat(permohonanBangunan.getIdBangunan());
                } else {
                    senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                }

                for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                        if (bangunanPetak.getSyer() != null) {
                            LOG.debug(":: " + totalUnitSyer + " + " + bangunanPetak.getSyer());
                            totalUnitSyer = totalUnitSyer + bangunanPetak.getSyer();
                            LOG.debug(": Jumlah Syer = " + totalUnitSyer);
                        }
                    }
                }
            }

            LOG.debug(":Bangunan : " + senaraiBangunan.size());
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();

                // List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                //ida update 28082013 
                List<BangunanTingkat> senaraiTingkat;

                senaraiTingkat = strataService.findByIDBangunanTingkat(permohonanBangunan.getIdBangunan());

                LOG.debug(":Tingkat : " + senaraiTingkat.size());
                int countTingkat = 1;
                // Added to check LandParcel
                if (!senaraiTingkat.isEmpty()) {
                    LOG.debug("---Bangunans---");
                    for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                        List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();

                        LOG.debug(":Petak : " + senaraiPetak.size());
                        int countPetak = 1;
                        for (BangunanPetak bangunanPetak : senaraiPetak) {
                            String noBangunan = permohonanBangunan.getNama();

                            LOG.debug("---ID Bangunan---" + permohonanBangunan.getIdBangunan());
                            LOG.debug("---Bangunan name---" + permohonanBangunan.getNama());

                            String noTingkat = String.valueOf(countTingkat);
                            String noPetak = bangunanPetak.getNama();

                            // added by zadirul                            
                            KodUOM kodUOM = bangunanPetak.getLuasUom();
                            if (kodUOM == null) {
                                //set default to 'meter persegi' if Kod_UOM in bangunanPetak is null
                                kodUOM = kodUOMDAO.findById("M");
                            }

                            Hakmilik hakmilik = new Hakmilik();
                            hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilik.setDaerah(hMasterTitle.getDaerah());
                            hakmilik.setNoBangunan(noBangunan);
                            hakmilik.setNoTingkat(noTingkat);
                            hakmilik.setNoPetak(noPetak);

                            //----------- added by zadirul
                            hakmilik.setKodKategoriBangunan(kodKatBngn);
                            int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                            LOG.debug("---jumlah unit syor---saving in hakmilik---:" + jumlahunit);
                            hakmilik.setJumlahUnitSyer(jumlahunit);
                            hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                            hakmilik.setCukai(BigDecimal.ZERO);
                            hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                            LOG.info("----kodNegeri----" + conf.getProperty("kodNegeri"));
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
                                HakmilikPermohonan hmPmhon = strataService.findMohonHakmilik(permohonanSebelum.getIdPermohonan());
                                LOG.info("----idHakmilik----" + hmPmhon.getHakmilik().getIdHakmilik());
                                Hakmilik hm = hmDAO.findById(hmPmhon.getHakmilik().getIdHakmilik());
                                BadanPengurusan bdnUrus = strataService.findBdn(permohonanSebelum.getIdPermohonan());
//                                if (bdnUrus != null) {
//                                    LOG.debug("--BadanPengurusan Hakmilik Induk--:" + bdnUrus);
//                                    hm.setBadanPengurusan(bdnUrus);
//                                    hm.setNoSkim(listStrata.get(0).getNamaSkim());
//                                    if (bangunanPetak.getSyer() != null) {
//                                        BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
//                                        hm.setUnitSyer(unitSyer);
//                                    }
//                                    hm.setJumlahUnitSyer(jumlahunit);
//                                    hmDAO.saveOrUpdate(hm);
//                                }
                            }
                            LOG.debug("noBangunan : " + hakmilik.getNoBangunan());
                            LOG.debug("noBangunan substring 1 : " + hakmilik.getNoBangunan().substring(1));
                            String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                            Hakmilik hakmilikBaru = new Hakmilik();
                            if (!listStrata.isEmpty()) {
                                hakmilikBaru.setNoSkim(listStrata.get(0).getNamaSkim());
                            }
                            /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
 /*added to make 28 charaters only coz in hakmilik table coloumn having 28 only
                             actual hakmilik size is 39 characters
                             */
                            String khm = hMasterTitle.getKodHakmilik().getKod();
                            LOG.debug("--Kod Hakmilik--:" + khm);
                            String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                            LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                            hakmilikBaru.setIdHakmilik(s1);
                            hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                //  hakmilikBaru.setCawangan(pengguna.getKodCawangan());

                                //add by ida 25/06/2013
                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            }
                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                            hakmilikBaru.setLot(hMasterTitle.getLot());
                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                            ksh.setKod("T");
                            hakmilikBaru.setKodStatusHakmilik(ksh);
                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                            hakmilikBaru.setKodUnitLuas(kodUOM);
                            if (bangunanPetak.getLuas() != null) {
                                BigDecimal luasPetak = bangunanPetak.getLuas();
                                hakmilikBaru.setLuas(luasPetak);
                            } else {
                                hakmilikBaru.setLuas(BigDecimal.ZERO);
                            }
                            String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                            /*copy NO HAKMILIK*/
                            LOG.debug("noHakmilik :" + noHakmilik);
                            hakmilikBaru.setNoHakmilik(noHakmilik);
                            /*commented by murali @NS PAT 14-08-2012 */
                            //hakmilikBaru.setNoPelan(hMasterTitle.getNoPelan());
                            /*added by murali @NS PAT 14-08-2012 */
                            hakmilikBaru.setNoPelan("");
                            hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                            hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                            hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                            hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                            hakmilikBaru.setCukai(BigDecimal.ZERO); //added by murali @MLK PAT 26-09-2012
                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                hakmilikBaru.setNoVersiDhde(1);
                                hakmilikBaru.setNoVersiDhke(1);
                            } else {
                                hakmilikBaru.setNoVersiDhde(0);
                                hakmilikBaru.setNoVersiDhke(0);
                            }

                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                            if (kodNegeri.equals("04")) {
                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            }
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            }
                            LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
                            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                            if (bdn != null) {
                                LOG.debug("--BadanPengurusan--:" + bdn);
                                hakmilikBaru.setBadanPengurusan(bdn);
                            }
                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                            hakmilikBaru.setNoBangunan(noBangunan);
                            hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                            hakmilikBaru.setNoTingkat(noTingkat);
                            hakmilikBaru.setNoPetak(noPetak);
                            if (bangunanPetak.getSyer() != null) {
                                BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                LOG.debug("--unit syor saving in Hakmilik Baru:" + unitSyer);
                                hakmilikBaru.setUnitSyer(unitSyer);
                            }
                            /* modified by murali @NS 24-09-2012
                             LOG.debug("--Jumlah unity syor saving in Hakmilik Baru:" + totalUnitSyer);
                             hakmilikBaru.setJumlahUnitSyer(totalUnitSyer);
                             */
                            int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                            LOG.debug("---jumlah unit syor---saving in hakmilikBaru---:" + jumlahunit1);
                            hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                LOG.debug("---kod guna bangunan from mohon_bngn---:" + permohonanBangunan.getKodKegunaanBangunan());
                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                            }
                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                            hakmilikBaru.setInfoAudit(ia);

                            /*INSERT INTO MOHON HAKMILIK*/
                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                            mohonHakmilikBaru.setPermohonan(p);
                            mohonHakmilikBaru.setInfoAudit(ia);
                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                            listHakmilikBaru.add(hakmilikBaru);

                            List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                            for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                hpa.setHakmilik(hakmilikBaru);
                                hpa.setCawangan(hakmilikBaru.getCawangan());
                                hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                hpa.setNama(bangunanPetakAksesori.getNama());
                                hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                hpa.setInfoAudit(ia);
                                listHakmilikPetakAksesori.add(hpa);
                            }

                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                            LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
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

                                //Updated by ida 20/04/2013
                                if ("05".equals(conf.getProperty("kodNegeri"))) {
                                    hpb.setNama(hpk.getNama());
                                    hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                    hpb.setNoPengenalan(hpk.getNoPengenalan());
                                    hpb.setAlamat1(hpk.getAlamat1());
                                    hpb.setAlamat2(hpk.getAlamat2());
                                    hpb.setAlamat3(hpk.getAlamat3());
                                    hpb.setAlamat4(hpk.getAlamat4());
                                    hpb.setPoskod(hpk.getPoskod());
                                    hpb.setNegeri(hpk.getNegeri());
                                    hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                    hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                                    hpb.setWargaNegara(hpk.getWargaNegara());
                                }
                                hpb.setInfoAudit(ia);
                                lhp.add(hpb);
                            }
                            countPetak += 1;
                        }
                        countTingkat += 1;
                    }
                } //**************************** Untuk IDHakmilik Landed 
                else // if LandParcel go to else conditon - Kalau Tanah                    
                //ABC _ Test
                //******************** KOD NEGERI SEMBILAN  ************************     
                if ("05".equals(conf.getProperty("kodNegeri"))) {

                    senaraiTingkat = strataService.findByIDBangunanTingkatL(permohonanBangunan.getIdBangunan());
                    //Dapatkan bapa rows tingkat untuk L

                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat2 = 1;
                    if (!senaraiTingkat.isEmpty()) { //Jika senarai tingkat kosong

                        for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                            List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();

                            LOG.debug(":Petak : " + senaraiPetak.size());
                            int countPetak2 = 1;
                            for (BangunanPetak bangunanPetak : senaraiPetak) {
                                String noBangunan = permohonanBangunan.getNama();

                                LOG.debug("---ID Bangunan---" + permohonanBangunan.getIdBangunan());
                                LOG.debug("---Bangunan name---" + permohonanBangunan.getNama());

                                String noTingkat = String.valueOf(countTingkat);
                                String noPetak = bangunanPetak.getNama();

                                // added by zadirul                            
                                KodUOM kodUOM = bangunanPetak.getLuasUom();
                                if (kodUOM == null) {
                                    //set default to 'meter persegi' if Kod_UOM in bangunanPetak is null
                                    kodUOM = kodUOMDAO.findById("M");
                                }

                                Hakmilik hakmilik = new Hakmilik();
                                hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                hakmilik.setDaerah(hMasterTitle.getDaerah());
                                hakmilik.setNoBangunan("L");
                                hakmilik.setNoTingkat("0");
                                hakmilik.setNoPetak(noPetak);

                                //----------- added by zadirul
                                hakmilik.setKodKategoriBangunan(kodKatBngn);
                                int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                                LOG.debug("---jumlah unit syor---saving in hakmilik---:" + jumlahunit);
                                hakmilik.setJumlahUnitSyer(jumlahunit);
                                hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                hakmilik.setCukai(BigDecimal.ZERO);
                                hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                LOG.info("----kodNegeri----" + conf.getProperty("kodNegeri"));
                                if ("05".equals(conf.getProperty("kodNegeri"))) {
                                    LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
                                    HakmilikPermohonan hmPmhon = strataService.findMohonHakmilik(permohonanSebelum.getIdPermohonan());
                                    LOG.info("----idHakmilik----" + hmPmhon.getHakmilik().getIdHakmilik());
                                    Hakmilik hm = hmDAO.findById(hmPmhon.getHakmilik().getIdHakmilik());
                                    BadanPengurusan bdnUrus = strataService.findBdn(permohonanSebelum.getIdPermohonan());
//                                if (bdnUrus != null) {
//                                    LOG.debug("--BadanPengurusan Hakmilik Induk--:" + bdnUrus);
//                                    hm.setBadanPengurusan(bdnUrus);
//                                    hm.setNoSkim(listStrata.get(0).getNamaSkim());
//                                    if (bangunanPetak.getSyer() != null) {
//                                        BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
//                                        hm.setUnitSyer(unitSyer);
//                                    }
//                                    hm.setJumlahUnitSyer(jumlahunit);
//                                    hmDAO.saveOrUpdate(hm);
//                                }
                                }
                                LOG.debug("noBangunan : " + hakmilik.getNoBangunan());
                                LOG.debug("noBangunan substring 1 : " + hakmilik.getNoBangunan());
                                // String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);

                                String idHakmilikBaru = hMasterTitle.getIdHakmilik() + "000L" + noPetak;
                                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                Hakmilik hakmilikBaru = new Hakmilik();
                                if (!listStrata.isEmpty()) {
                                    hakmilikBaru.setNoSkim(listStrata.get(0).getNamaSkim());
                                }
                                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
 /*added to make 28 charaters only coz in hakmilik table coloumn having 28 only
                                     actual hakmilik size is 39 characters
                                 */
                                String khm = hMasterTitle.getKodHakmilik().getKod();
                                LOG.debug("--Kod Hakmilik--:" + khm);

//	String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                String s1 = hMasterTitle.getIdHakmilik() + "000L" + noPetak;

//String s1 = noHakmilikLama + "000L" + Integer.parseInt(noPetakL.substring(1));
                                LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                hakmilikBaru.setIdHakmilik(s1);
                                hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                                if ("05".equals(conf.getProperty("kodNegeri"))) {
                                    //  hakmilikBaru.setCawangan(pengguna.getKodCawangan());

                                    //add by ida 25/06/2013
                                    hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                }
                                hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                hakmilikBaru.setLot(hMasterTitle.getLot());
                                hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                KodStatusHakmilik ksh = new KodStatusHakmilik();
                                ksh.setKod("T");
                                hakmilikBaru.setKodStatusHakmilik(ksh);
                                hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                hakmilikBaru.setKodUnitLuas(kodUOM);
                                if (bangunanPetak.getLuas() != null) {
                                    BigDecimal luasPetak = bangunanPetak.getLuas();
                                    hakmilikBaru.setLuas(luasPetak);
                                } else {
                                    hakmilikBaru.setLuas(BigDecimal.ZERO);
                                }
                                // String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                String noHakmilik = hMasterTitle.getNoHakmilik();
                                /*copy NO HAKMILIK*/
                                LOG.debug("noHakmilik :" + noHakmilik);
                                hakmilikBaru.setNoHakmilik(noHakmilik);
                                /*commented by murali @NS PAT 14-08-2012 */
                                //hakmilikBaru.setNoPelan(hMasterTitle.getNoPelan());
                                /*added by murali @NS PAT 14-08-2012 */
                                hakmilikBaru.setNoPelan("");
                                hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                                hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                                hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                hakmilikBaru.setCukai(BigDecimal.ZERO); //added by murali @MLK PAT 26-09-2012
                                hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                hakmilikBaru.setNoVersiDhde(1);
                                hakmilikBaru.setNoVersiDhke(1);
                                hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());

                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());

                                LOG.debug("--ID Mohon--SBLM:" + permohonanSebelum.getIdPermohonan());
                                BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                if (bdn != null) {
                                    LOG.debug("--BadanPengurusan--:" + bdn);
                                    hakmilikBaru.setBadanPengurusan(bdn);
                                }
                                hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                hakmilikBaru.setNoBangunan(noBangunan);
                                hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                hakmilikBaru.setNoTingkat("0");
                                // String noPetak2 = "L"+bangunanPetak.getNama();
                                String noPetak2 = bangunanPetak.getNama();
                                hakmilikBaru.setNoPetak(noPetak2);
                                if (bangunanPetak.getSyer() != null) {
                                    BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                    LOG.debug("--unit syor saving in Hakmilik Baru:" + unitSyer);
                                    hakmilikBaru.setUnitSyer(unitSyer);
                                }
                                /* modified by murali @NS 24-09-2012
                                     LOG.debug("--Jumlah unity syor saving in Hakmilik Baru:" + totalUnitSyer);
                                     hakmilikBaru.setJumlahUnitSyer(totalUnitSyer);
                                 */
                                int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                                LOG.debug("---jumlah unit syor---saving in hakmilikBaru---:" + jumlahunit1);
                                hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                    LOG.debug("---kod guna bangunan from mohon_bngn---:" + permohonanBangunan.getKodKegunaanBangunan());
                                    hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                }
                                hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                hakmilikBaru.setInfoAudit(ia);

                                /*INSERT INTO MOHON HAKMILIK*/
                                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                mohonHakmilikBaru.setPermohonan(p);
                                mohonHakmilikBaru.setInfoAudit(ia);
                                listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                listHakmilikBaru.add(hakmilikBaru);

                                List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                    HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                    hpa.setHakmilik(hakmilikBaru);
                                    hpa.setCawangan(hakmilikBaru.getCawangan());
                                    hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                    hpa.setNama(bangunanPetakAksesori.getNama());
                                    hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                    hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                    hpa.setInfoAudit(ia);
                                    listHakmilikPetakAksesori.add(hpa);
                                }

                                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                                LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
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

                                    //Updated by ida 20/04/2013
                                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                                        hpb.setNama(hpk.getNama());
                                        hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                        hpb.setNoPengenalan(hpk.getNoPengenalan());
                                        hpb.setAlamat1(hpk.getAlamat1());
                                        hpb.setAlamat2(hpk.getAlamat2());
                                        hpb.setAlamat3(hpk.getAlamat3());
                                        hpb.setAlamat4(hpk.getAlamat4());
                                        hpb.setPoskod(hpk.getPoskod());
                                        hpb.setNegeri(hpk.getNegeri());
                                        hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                        hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                                        hpb.setWargaNegara(hpk.getWargaNegara());
                                    }
                                    hpb.setInfoAudit(ia);
                                    lhp.add(hpb);
                                }
                                countPetak2 += 1;
                            }
                            countTingkat2 += 1;
                        }

                        //END LANDED PARCEL
                    }
                } //******************** END NEGERI SEMBILAN  ************************//else end
                //**********************************************

            } // End for loop by counting mohon_bngn records

            if (!listHakmilikBaru.isEmpty()) {
                for (Hakmilik hakmilik : listHakmilikBaru) {
                    LOG.debug("--commenservice Hakmilik--:" + hakmilik.getIdHakmilik());
                    regService.simpanHakmilik(hakmilik);
                }
            }

            if (!listMohonHakmilikBaru.isEmpty()) {
                for (HakmilikPermohonan hp : listMohonHakmilikBaru) {
                    LOG.debug("--CS IdHakmilik--:" + hp.getHakmilik().getIdHakmilik());
                    LOG.debug("--CS IdPermohonan--:" + hp.getPermohonan().getIdPermohonan());
                    regService.simpanMohonHakmilik(hp);
                }
            }

            if (!lhp.isEmpty()) {
                LOG.debug("---lhp list saving in Hakmilik Pihak---");
                regService.simpanHakmilikPihak(lhp);
            }
            if (!listHakmilikPetakAksesori.isEmpty()) {
                regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
            }
        }

    }

    public void generateHakmilikStrata(InfoAudit ia, Permohonan p, Pengguna pengguna) {

        kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            permohonanSebelum = p;
        } else {
            permohonanSebelum = p.getPermohonanSebelum();
        }

        if (permohonanSebelum != null) {
            List<PermohonanStrata> listStrata = permohonanSebelum.getSenaraiStrata();
            Hakmilik hMasterTitle = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
            List<PermohonanBangunan> senaraiBangunan = permohonanSebelum.getSenaraiBangunan();
            //TODO :  count the total of unit syer
            int totalUnitSyer = 0;
            LOG.debug(": COUNT BEGIN : ");
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                List<BangunanTingkat> senaraiTingkat;
                senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                        if (bangunanPetak.getSyer() != null) {
                            LOG.debug(":: " + totalUnitSyer + " + " + bangunanPetak.getSyer());
                            totalUnitSyer = totalUnitSyer + bangunanPetak.getSyer();
                            LOG.debug(": Jumlah Syer = " + totalUnitSyer);
                        }
                    }
                }
            }

            LOG.debug(":Bangunan : " + senaraiBangunan.size());
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                if (!permohonanBangunan.getNamaLain().isEmpty()) {
                    KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();
                    List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat = 1;
                    if (kodKatBngn.getKod().equals("B") || kodKatBngn.getKod().equals("P")) {
                        if (!senaraiTingkat.isEmpty()) {
                            LOG.debug("---Bangunans---");
                            for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                                List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                                int countPetak = 1;
                                for (BangunanPetak bangunanPetak : senaraiPetak) {
                                    String noBangunan = permohonanBangunan.getNama();
                                    String noTingkat = String.valueOf(bangunanTingkat.getTingkat());
                                    String noPetak = bangunanPetak.getNama();
                                    KodUOM kodUOM = bangunanPetak.getLuasUom();
                                    if (kodUOM == null) {
                                        kodUOM = kodUOMDAO.findById("M");
                                    }
                                    Hakmilik hakmilik = new Hakmilik();
                                    hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilik.setDaerah(hMasterTitle.getDaerah());
                                    hakmilik.setNoBangunan(noBangunan);
                                    hakmilik.setNoTingkat(noTingkat);
                                    hakmilik.setNoPetak(noPetak);
                                    hakmilik.setKodKategoriBangunan(kodKatBngn);
                                    int jumlahunit = 0;
                                    for (PermohonanBangunan mb : senaraiBangunan) {
                                        jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                                    }
                                    hakmilik.setJumlahUnitSyer(jumlahunit);
                                    hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                    hakmilik.setCukai(BigDecimal.ZERO);
                                    hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                    String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                                    LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                    Hakmilik hakmilikBaru = new Hakmilik();
                                    if (!listStrata.isEmpty()) {
                                        hakmilikBaru.setNoSkim(listStrata.get(0).getNamaSkim());
                                    }
                                    String khm = hMasterTitle.getKodHakmilik().getKod();
                                    String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                    LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                    hakmilikBaru.setIdHakmilik(s1);
                                    hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                                    hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                    hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                    hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                    hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                    hakmilikBaru.setLot(hMasterTitle.getLot());
                                    hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                    hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                    hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                                    hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                    hakmilikBaru.setKodSumber("ET");
                                    KodStatusHakmilik ksh = new KodStatusHakmilik();
                                    ksh.setKod("T");
                                    hakmilikBaru.setKodStatusHakmilik(ksh);
                                    hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                    hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                    hakmilikBaru.setKodUnitLuas(kodUOM);
                                    if (bangunanPetak.getLuas() != null) {
                                        BigDecimal luasPetak = bangunanPetak.getLuas();
                                        hakmilikBaru.setLuas(luasPetak);
                                    } else {
                                        hakmilikBaru.setLuas(BigDecimal.ZERO);
                                    }
                                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                    hakmilikBaru.setNoHakmilik(noHakmilik);
                                    hakmilikBaru.setNoPelan("");
                                    hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                                    hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                                    hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                    hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                    hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                    hakmilikBaru.setCukai(BigDecimal.ZERO);
                                    hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                    hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                    hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                    hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                    hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                    hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                    hakmilikBaru.setNoVersiDhde(0);
                                    hakmilikBaru.setNoVersiDhke(0);
                                    hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                                    if (kodNegeri.equals("04")) {
                                        hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                    }
                                    BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                    if (bdn != null) {
                                        hakmilikBaru.setBadanPengurusan(bdn);
                                    }
                                    hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                    hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                    hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                    hakmilikBaru.setNoBangunan(noBangunan);
                                    hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                    hakmilikBaru.setNoTingkat(bangunanTingkat.getNama());
                                    hakmilikBaru.setNoPetak(noPetak);
                                    hakmilikBaru.setMenara(bangunanTingkat.getLain());
                                    hakmilikBaru.setMezanin(bangunanTingkat.getMezanin());
                                    if (bangunanPetak.getSyer() != null) {
                                        BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                        hakmilikBaru.setUnitSyer(unitSyer);
                                    }
                                    int jumlahunit1 = 0;
                                    for (PermohonanBangunan mb2 : senaraiBangunan) {
                                        jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                                    }
                                    hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                    if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                        hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                    }
                                    hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                    hakmilikBaru.setInfoAudit(ia);
                                    /*INSERT INTO MOHON HAKMILIK*/
                                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                    mohonHakmilikBaru.setPermohonan(p);
                                    mohonHakmilikBaru.setInfoAudit(ia);
                                    listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                    listHakmilikBaru.add(hakmilikBaru);

                                    List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                    for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                        HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                        hpa.setHakmilik(hakmilikBaru);
                                        hpa.setCawangan(hakmilikBaru.getCawangan());
                                        hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                        hpa.setNama(bangunanPetakAksesori.getNama());
                                        hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                        hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                        hpa.setInfoAudit(ia);
                                        listHakmilikPetakAksesori.add(hpa);
                                    }

                                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
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
                                        if (kodNegeri.equals("04")) {
                                            hpb.setNama(hpk.getPihak().getNama());
                                            hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                            hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                            hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                            hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                            hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                            hpb.setPoskod(hpk.getPihak().getPoskod());
                                            hpb.setNegeri(hpk.getPihak().getNegeri());
                                        }
                                        hpb.setInfoAudit(ia);
                                        lhp.add(hpb);
                                    }
                                    countPetak += 1;
                                }
                                countTingkat += 1;
                            }
                        }
                    }
                } else {
                    KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();
                    List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat = 1;
                    if (kodKatBngn.getKod().equals("B") || kodKatBngn.getKod().equals("P")) {
                        if (!senaraiTingkat.isEmpty()) {
                            LOG.debug("---Bangunans---");
                            for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                                List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                                int countPetak = 1;
                                for (BangunanPetak bangunanPetak : senaraiPetak) {
                                    String noBangunan = permohonanBangunan.getNama();
                                    String noTingkat = String.valueOf(countTingkat);
                                    String noPetak = bangunanPetak.getNama();
                                    KodUOM kodUOM = bangunanPetak.getLuasUom();
                                    if (kodUOM == null) {
                                        kodUOM = kodUOMDAO.findById("M");
                                    }
                                    Hakmilik hakmilik = new Hakmilik();
                                    hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilik.setDaerah(hMasterTitle.getDaerah());
                                    hakmilik.setNoBangunan(noBangunan);
                                    hakmilik.setNoTingkat(noTingkat);
                                    hakmilik.setNoPetak(noPetak);
                                    hakmilik.setKodKategoriBangunan(kodKatBngn);
                                    int jumlahunit = 0;
                                    for (PermohonanBangunan mb : senaraiBangunan) {
                                        jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                                    }
                                    hakmilik.setJumlahUnitSyer(jumlahunit);
                                    hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                    hakmilik.setCukai(BigDecimal.ZERO);
                                    hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                    String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                                    LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                    Hakmilik hakmilikBaru = new Hakmilik();
                                    if (!listStrata.isEmpty()) {
                                        hakmilikBaru.setNoSkim(listStrata.get(0).getNamaSkim());
                                    }
                                    String khm = hMasterTitle.getKodHakmilik().getKod();
                                    String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                    LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                    hakmilikBaru.setIdHakmilik(s1);
                                    hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                                    hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                    hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                    hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                    hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                    hakmilikBaru.setLot(hMasterTitle.getLot());
                                    hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                    hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                    hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                                    hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                    hakmilikBaru.setKodSumber("ET");
                                    KodStatusHakmilik ksh = new KodStatusHakmilik();
                                    ksh.setKod("T");
                                    hakmilikBaru.setKodStatusHakmilik(ksh);
                                    hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                    hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                    hakmilikBaru.setKodUnitLuas(kodUOM);
                                    if (bangunanPetak.getLuas() != null) {
                                        BigDecimal luasPetak = bangunanPetak.getLuas();
                                        hakmilikBaru.setLuas(luasPetak);
                                    } else {
                                        hakmilikBaru.setLuas(BigDecimal.ZERO);
                                    }
                                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                    hakmilikBaru.setNoHakmilik(noHakmilik);
                                    hakmilikBaru.setNoPelan("");
                                    hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                                    hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                                    hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                    hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                    hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                    hakmilikBaru.setCukai(BigDecimal.ZERO);
                                    hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                    hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                    hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                    hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                    hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                    hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                    hakmilikBaru.setNoVersiDhde(0);
                                    hakmilikBaru.setNoVersiDhke(0);
                                    hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                                    if (kodNegeri.equals("04")) {
                                        hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                    }
                                    BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                    if (bdn != null) {
                                        hakmilikBaru.setBadanPengurusan(bdn);
                                    }
                                    hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                    hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                    hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                    hakmilikBaru.setNoBangunan(noBangunan);
                                    hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                    hakmilikBaru.setNoTingkat(noTingkat);
                                    hakmilikBaru.setNoPetak(noPetak);
                                    if (bangunanPetak.getSyer() != null) {
                                        BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                        hakmilikBaru.setUnitSyer(unitSyer);
                                    }
                                    int jumlahunit1 = 0;
                                    for (PermohonanBangunan mb2 : senaraiBangunan) {
                                        jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                                    }
                                    hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                    if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                        hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                    }
                                    hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                    hakmilikBaru.setInfoAudit(ia);
                                    /*INSERT INTO MOHON HAKMILIK*/
                                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                    mohonHakmilikBaru.setPermohonan(p);
                                    mohonHakmilikBaru.setInfoAudit(ia);
                                    listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                    listHakmilikBaru.add(hakmilikBaru);

                                    List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                    for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                        HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                        hpa.setHakmilik(hakmilikBaru);
                                        hpa.setCawangan(hakmilikBaru.getCawangan());
                                        hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                        hpa.setNama(bangunanPetakAksesori.getNama());
                                        hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                        hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                        hpa.setInfoAudit(ia);
                                        listHakmilikPetakAksesori.add(hpa);
                                    }

                                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
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
                                        if (kodNegeri.equals("04")) {
                                            hpb.setNama(hpk.getPihak().getNama());
                                            hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                            hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                            hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                            hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                            hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                            hpb.setPoskod(hpk.getPihak().getPoskod());
                                            hpb.setNegeri(hpk.getPihak().getNegeri());
                                        }
                                        hpb.setInfoAudit(ia);
                                        lhp.add(hpb);
                                    }
                                    countPetak += 1;
                                }
                                countTingkat += 1;
                            }
                        }
                    } else if ("04".equals(conf.getProperty("kodNegeri"))) {
                        LOG.debug("---LandParcels---");
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = kodUOMDAO.findById("M");
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("0");
                        hakmilik.setNoPetak("0");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                        String idHakmilikBaru = generateStrataLandParcel(kodNegeri, null, hakmilik);
                        LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                        Hakmilik hakmilikBaru = new Hakmilik();
                        if (!listStrata.isEmpty()) {
                            hakmilikBaru.setNoSkim(listStrata.get(0).getNamaSkim());
                        }
                        String khm = hMasterTitle.getKodHakmilik().getKod();
                        LOG.debug("SAVING NEW ID HAKMILIK : " + hakmilikBaru);
                        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                        hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                        hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                        hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                        hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                        hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                        hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                        hakmilikBaru.setLot(hMasterTitle.getLot());
                        hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                        hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                        hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                        hakmilikBaru.setTarikhDaftar(new java.util.Date());
                        hakmilikBaru.setKodSumber("ET");
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hakmilikBaru.setKodStatusHakmilik(ksh);
                        hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                        hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                        hakmilikBaru.setKodUnitLuas(kodUOM);
                        int syorblock = permohonanBangunan.getSyerBlok();
                        hakmilikBaru.setUnitSyer(BigDecimal.valueOf(syorblock));
                        String luas = permohonanBangunan.getUntukKegunaanLain();
                        BigDecimal luas1 = new BigDecimal(luas);
                        if (permohonanBangunan.getUntukKegunaanLain() != null) {
                            hakmilikBaru.setLuas(luas1);
                        } else {
                            hakmilikBaru.setLuas(BigDecimal.ZERO);
                        }
                        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 14, (idHakmilikBaru.length() - 14) + 8);
                        hakmilikBaru.setNoHakmilik(noHakmilik);
                        hakmilikBaru.setNoPelan("");
                        hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                        hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                        hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                        hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                        hakmilikBaru.setPbt(hMasterTitle.getPbt());
                        hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                        hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                        hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                        hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                        hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                        hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                        hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                        BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                        if (bdn != null) {
                            hakmilikBaru.setBadanPengurusan(bdn);
                        }
                        hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                        hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                        hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                        hakmilikBaru.setNoBangunan(noBangunan);
                        hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                        hakmilikBaru.setNoTingkat("0");
                        hakmilikBaru.setNoPetak("0");
                        hakmilikBaru.setNoVersiDhde(1);
                        hakmilikBaru.setNoVersiDhke(1);
                        int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                        hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                        hakmilikBaru.setInfoAudit(ia);
                        /*INSERT INTO MOHON HAKMILIK*/
                        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                        mohonHakmilikBaru.setPermohonan(p);
                        mohonHakmilikBaru.setInfoAudit(ia);
                        listMohonHakmilikBaru.add(mohonHakmilikBaru);
                        listHakmilikBaru.add(hakmilikBaru);
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
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
                            hpb.setInfoAudit(ia);
                            lhp.add(hpb);
                        }
                    }
                }
            }

            if (!listHakmilikBaru.isEmpty()) {
                for (Hakmilik hakmilik : listHakmilikBaru) {
                    regService.simpanHakmilik(hakmilik);
                }
            }

            if (!listMohonHakmilikBaru.isEmpty()) {
                for (HakmilikPermohonan hp : listMohonHakmilikBaru) {
                    regService.simpanMohonHakmilik(hp);
                }
            }

            if (!lhp.isEmpty()) {
                regService.simpanHakmilikPihak(lhp);
            }
            if (!listHakmilikPetakAksesori.isEmpty()) {
                regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
            }
        }
    }

    public void generateHakmilikStrata2(InfoAudit ia, Permohonan p, Pengguna pengguna) {

        kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            permohonanSebelum = p;
        } else {
            permohonanSebelum = p.getPermohonanSebelum();
        }

        if (permohonanSebelum != null) {
            List<PermohonanStrata> listStrata = permohonanSebelum.getSenaraiStrata();
            Hakmilik hMasterTitle = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
            List<PermohonanBangunan> senaraiBangunan = permohonanSebelum.getSenaraiBangunan();
            BadanPengurusan sbu = strService.findBdn(p.getIdPermohonan());
            //TODO :  count the total of unit syer
            int totalUnitSyer = 0;
            LOG.debug(": COUNT BEGIN : ");
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                List<BangunanTingkat> senaraiTingkat;
                senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                        if (bangunanPetak.getSyer() != null) {
                            LOG.debug(":: " + totalUnitSyer + " + " + bangunanPetak.getSyer());
                            totalUnitSyer = totalUnitSyer + bangunanPetak.getSyer();
                            LOG.debug(": Jumlah Syer = " + totalUnitSyer);
                        }
                    }
                }
            }

            LOG.debug(":Bangunan : " + senaraiBangunan.size());
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                if (!permohonanBangunan.getNamaLain().isEmpty()) {
                    KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();
                    List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat = 1;
                    if (kodKatBngn.getKod().equals("B")) {
                        if (!senaraiTingkat.isEmpty()) {
                            LOG.debug("---Bangunans---");
                            for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                                if (bangunanTingkat.getBilanganPetak() > 0) {
                                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                                    int countPetak = 1;
                                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                                        if (!bangunanPetak.getNama().equals("0")) {
                                            String noBangunan = permohonanBangunan.getNama();
                                            String noTingkat = String.valueOf(bangunanTingkat.getTingkat());
                                            String noPetak = bangunanPetak.getNama();
                                            BigDecimal cukai = bangunanPetak.getCukai();
                                            KodUOM kodUOM = bangunanPetak.getLuasUom();
                                            if (kodUOM == null) {
                                                kodUOM = kodUOMDAO.findById("M");
                                            }
                                            Hakmilik hakmilik = new Hakmilik();
                                            hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                            hakmilik.setDaerah(hMasterTitle.getDaerah());
                                            hakmilik.setNoBangunan(noBangunan);
                                            hakmilik.setNoTingkat(noTingkat);
                                            hakmilik.setNoPetak(noPetak);
                                            hakmilik.setKodKategoriBangunan(kodKatBngn);
                                            int jumlahunit = 0;
                                            for (PermohonanBangunan mb : senaraiBangunan) {
                                                jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                                            }
                                            hakmilik.setJumlahUnitSyer(jumlahunit);
                                            hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                            if (cukai == null) {
                                                hakmilik.setCukai(BigDecimal.ZERO);
                                                hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                            } else {
                                                hakmilik.setCukai(cukai);
                                                hakmilik.setCukaiSebenar(cukai);
                                            }
                                            String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                            Hakmilik hakmilikBaru = new Hakmilik();
                                            if (sbu != null) {
                                                hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                                            }
                                            String khm = hMasterTitle.getKodHakmilik().getKod();
                                            String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                            LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                            hakmilikBaru.setIdHakmilik(s1);
                                            hakmilikBaru.setNoFail(null);
                                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                            hakmilikBaru.setLot(hMasterTitle.getLot());
                                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                            hakmilikBaru.setKodSumber("ET");
                                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                                            ksh.setKod("T");
                                            hakmilikBaru.setKodStatusHakmilik(ksh);
                                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                            hakmilikBaru.setKodUnitLuas(kodUOM);
                                            if (bangunanPetak.getLuas() != null) {
                                                BigDecimal luasPetak = bangunanPetak.getLuas();
                                                hakmilikBaru.setLuas(luasPetak);
                                            } else {
                                                hakmilikBaru.setLuas(BigDecimal.ZERO);
                                            }
                                            String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                            hakmilikBaru.setNoHakmilik(noHakmilik);
                                            hakmilikBaru.setNoPelan(bangunanPetak.getPabPetak());
                                            hakmilikBaru.setNoPu(hMasterTitle.getNoLitho());
                                            if(bangunanPetak.getSekatanKepentingan() == null && bangunanPetak.getSyaratNyata() == null) {
                                                hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                                hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                            } else {
                                                hakmilikBaru.setSekatanKepentingan(bangunanPetak.getSekatan());
                                                hakmilikBaru.setSyaratNyata(bangunanPetak.getSyarat());
                                            }
                                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                            hakmilikBaru.setCukai(BigDecimal.ZERO);
                                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                            hakmilikBaru.setNoVersiDhde(1);
                                            hakmilikBaru.setNoVersiDhke(1);
                                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                                            if (kodNegeri.equals("04")) {
                                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                            }
                                            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                            if (bdn != null) {
                                                hakmilikBaru.setBadanPengurusan(bdn);
                                            }
                                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                            hakmilikBaru.setNoBangunan(noBangunan);
                                            hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                            hakmilikBaru.setNoTingkat(bangunanTingkat.getNama());
                                            hakmilikBaru.setNoPetak(noPetak);
                                            hakmilikBaru.setMenara(bangunanTingkat.getLain());
                                            hakmilikBaru.setMezanin(bangunanTingkat.getMezanin());
                                            if (bangunanPetak.getSyer() != null) {
                                                BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                                hakmilikBaru.setUnitSyer(unitSyer);
                                            }
                                            int jumlahunit1 = 0;
                                            for (PermohonanBangunan mb2 : senaraiBangunan) {
                                                jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                                            }
                                            hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                            if (bangunanPetak.getKodKegunaanBangunan() == null) {
                                                if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                                    hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                                }
                                            } else {
                                                hakmilikBaru.setKodKegunaanBangunan(bangunanPetak.getKodKegunaanBangunan());
                                            }
                                            if (bangunanPetak.getKodkelasTanah() != null){
                                                hakmilikBaru.setKodkelasTanah(bangunanPetak.getKodkelasTanah());
                                            }
                                            if(bangunanPetak.getKosRendah() != null){
                                                hakmilikBaru.setKosRendah(bangunanPetak.getKosRendah());
                                            }
                                            if(bangunanPetak.getKadar() != null){
                                                hakmilikBaru.setKadar(bangunanPetak.getKadar());
                                            }
                                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                            hakmilikBaru.setInfoAudit(ia);
                                            /*INSERT INTO MOHON HAKMILIK*/
                                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                            mohonHakmilikBaru.setPermohonan(p);
                                            mohonHakmilikBaru.setInfoAudit(ia);
                                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                            listHakmilikBaru.add(hakmilikBaru);

                                            List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                            for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                                HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                                hpa.setHakmilik(hakmilikBaru);
                                                hpa.setCawangan(hakmilikBaru.getCawangan());
                                                hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                                hpa.setNama(bangunanPetakAksesori.getNama());
                                                hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                                if (bangunanPetakAksesori.getLain() != null) {
                                                    hpa.setLuas(BigDecimal.valueOf(Double.valueOf(bangunanPetakAksesori.getLain())));
                                                }
                                                hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                                hpa.setInfoAudit(ia);
                                                listHakmilikPetakAksesori.add(hpa);
                                            }
                            
                            /*INSERT INTO HAKMILIK ASAL/SEBELUM*/
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                LOG.debug("start copy hakmilik asal/hakmilik sebelum for PHPC/PHPP");
                                LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hMasterTitle1.getSenaraiHakmilikAsal().size());
                                LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hMasterTitle1.getSenaraiHakmilikSebelum().size());
                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {                            
                                if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                    for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                        HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                        HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                        hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                        //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                        hap.setHakmilik(hakmilikBaru);
                                        hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                        hap.setInfoAudit(ia);
                                        hakmilikAsalPermohonanDAO.save(hap);
                                    }
                                 } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                    //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                    hap.setHakmilik(hakmilikBaru);
                                    hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                    hap.setInfoAudit(ia);
                                    hakmilikAsalPermohonanDAO.save(hap);
                                 }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                   if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                        for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                            HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                            HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                            hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                            //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                            hap.setHakmilik(hakmilikBaru);
                                            hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                            hap.setInfoAudit(ia);
                                            hakmilikAsalPermohonanDAO.save(hap);
                                        }
                                    } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                       HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                       hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                       //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                       hap.setHakmilik(hakmilikBaru);
                                       hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                       hap.setInfoAudit(ia);
                                       hakmilikAsalPermohonanDAO.save(hap);
                                    }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            }
                              
  
//                                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                                if (hpk == null || hpk.getAktif() == 'T') {
//                                                    continue;
//                                                }

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
                                                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                                hpb.setWargaNegara(hpk.getWargaNegara());
                                                hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                                hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                                                hpb.setAktif('Y');
                                                hpb.setPihak(hpk.getPihak());
                                                if (kodNegeri.equals("04")) {
                                                    hpb.setNama(hpk.getNama());
                                                    hpb.setAlamat1(hpk.getAlamat1());
                                                    hpb.setAlamat2(hpk.getAlamat2());
                                                    hpb.setAlamat3(hpk.getAlamat3());
                                                    hpb.setAlamat4(hpk.getAlamat4());
                                                    hpb.setNoPengenalan(hpk.getNoPengenalan());
                                                    hpb.setPoskod(hpk.getPoskod());
                                                    hpb.setNegeri(hpk.getNegeri());
                                                }
                                                hpb.setInfoAudit(ia);
                                                lhp.add(hpb);
                                            }
                                            countPetak += 1;
                                        }
                                    }
                                }
                                countTingkat += 1;
                            }
                        }
                    } else if (kodKatBngn.getKod().equals("P") || kodKatBngn.getKod().equals("PL")) {
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = null;
                        if (kodUOM == null) {
                            kodUOM = kodUOMDAO.findById("M");
                        }
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("1");
                        hakmilik.setNoPetak("1");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = 0;
                        for (PermohonanBangunan mb : senaraiBangunan) {
                            jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                        }
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                        hakmilik.setCukai(BigDecimal.ZERO);
                        hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                        String noBangunanP = StringUtils.leftPad(noBangunan, 3, '0');
                        String idHakmilikBaru = hMasterTitle.getIdHakmilik() + noBangunanP;
                        LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                        Hakmilik hakmilikBaru = new Hakmilik();
                        if (sbu != null) {
                            hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                        }
                        String khm = hMasterTitle.getKodHakmilik().getKod();
                        String s1 = idHakmilikBaru;
                        LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                        hakmilikBaru.setNoFail(null);
                        hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                        hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                        hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                        hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                        hakmilikBaru.setLot(hMasterTitle.getLot());
                        hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                        hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                        hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                        hakmilikBaru.setTarikhDaftar(new java.util.Date());
                        hakmilikBaru.setKodSumber("ET");
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hakmilikBaru.setKodStatusHakmilik(ksh);
                        hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                        hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                        hakmilikBaru.setKodUnitLuas(kodUOM);
                        hakmilikBaru.setLuas(BigDecimal.ZERO);

                        String noHakmilik = hMasterTitle.getNoHakmilik();
                        hakmilikBaru.setNoHakmilik(noHakmilik);
                        hakmilikBaru.setNoPelan("");
                        hakmilikBaru.setNoPu(hMasterTitle.getNoLitho());
                        hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                        hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                        hakmilikBaru.setPbt(hMasterTitle.getPbt());
                        hakmilikBaru.setCukai(BigDecimal.ZERO);
                        hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                        hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                        hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                        hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                        hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                        hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                        hakmilikBaru.setNoVersiDhde(1);
                        hakmilikBaru.setNoVersiDhke(1);

                        hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                        if (kodNegeri.equals("04")) {
                            hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                        }
                        BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                        if (bdn != null) {
                            hakmilikBaru.setBadanPengurusan(bdn);
                        }
                        hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                        hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                        hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                        hakmilikBaru.setNoBangunan(noBangunan);
                        hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                        hakmilikBaru.setNoTingkat("1");
                        hakmilikBaru.setNoPetak("1");
                        hakmilikBaru.setMenara(null);
                        hakmilikBaru.setMezanin(null);
                        hakmilikBaru.setUnitSyer(BigDecimal.valueOf(permohonanBangunan.getSyerBlok()));
                        int jumlahunit1 = 0;
                        for (PermohonanBangunan mb2 : senaraiBangunan) {
                            jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                        }
                        hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                        if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                            hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                        }
                        hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                        hakmilikBaru.setInfoAudit(ia);
                        /*INSERT INTO MOHON HAKMILIK*/
                        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                        mohonHakmilikBaru.setPermohonan(p);
                        mohonHakmilikBaru.setInfoAudit(ia);
                        listMohonHakmilikBaru.add(mohonHakmilikBaru);
                        listHakmilikBaru.add(hakmilikBaru);

                        listHakmilikPetakAksesori = null;
                            
                            /*INSERT INTO HAKMILIK ASAL/SEBELUM*/
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                LOG.debug("start copy hakmilik asal/hakmilik sebelum for PHPC/PHPP");
                                LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hMasterTitle1.getSenaraiHakmilikAsal().size());
                                LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hMasterTitle1.getSenaraiHakmilikSebelum().size());
                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {                            
                                if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                    for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                        HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                        HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                        hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                        //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                        hap.setHakmilik(hakmilikBaru);
                                        hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                        hap.setInfoAudit(ia);
                                        hakmilikAsalPermohonanDAO.save(hap);
                                    }
                                 } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                    //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                    hap.setHakmilik(hakmilikBaru);
                                    hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                    hap.setInfoAudit(ia);
                                    hakmilikAsalPermohonanDAO.save(hap);
                                 }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                   if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                        for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                            HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                            HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                            hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                            //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                            hap.setHakmilik(hakmilikBaru);
                                            hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                            hap.setInfoAudit(ia);
                                            hakmilikAsalPermohonanDAO.save(hap);
                                        }
                                    } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                       HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                       hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                       //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                       hap.setHakmilik(hakmilikBaru);
                                       hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                       hap.setInfoAudit(ia);
                                       hakmilikAsalPermohonanDAO.save(hap);
                                    }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            }
                              
  
//                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                            if (hpk == null || hpk.getAktif() == 'T') {
//                                continue;
//                            }

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
                            hpb.setAktif('Y');
                            hpb.setPihak(hpk.getPihak());
                            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                            hpb.setWargaNegara(hpk.getWargaNegara());
                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                            if (kodNegeri.equals("04")) {
                                hpb.setNama(hpk.getPihak().getNama());
                                hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                hpb.setPoskod(hpk.getPihak().getPoskod());
                                hpb.setNegeri(hpk.getPihak().getNegeri());
                            }
                            hpb.setInfoAudit(ia);
                            lhp.add(hpb);
                        }

                    } else {
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = kodUOMDAO.findById("M");
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("0");
                        hakmilik.setNoPetak("0");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());

                        List<BangunanTingkat> bt = strService.findByIDBangunan(permohonanBangunan.getIdBangunan());
                        List<BangunanPetak> bp = null;
                        if (bt.size() > 0) {
                            bp = strService.findByPetakByIDTingkat(bt.get(0).getIdTingkat());
                        }
                        for (BangunanPetak bpetak : bp) {
                            String noBangunanL = StringUtils.leftPad(bpetak.getNama(), 5, '0');
                            String idHakmilikBaru = hMasterTitle.getIdHakmilik() + noBangunanL;
                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                            Hakmilik hakmilikBaru = new Hakmilik();
                            if (sbu != null) {
                                hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                            }
                            String khm = hMasterTitle.getKodHakmilik().getKod();
                            LOG.debug("SAVING NEW ID HAKMILIK : " + hakmilikBaru);
                            hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                            hakmilikBaru.setNoFail(null);
                            hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                            hakmilikBaru.setLot(hMasterTitle.getLot());
                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                            hakmilikBaru.setKodSumber("ET");
                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                            ksh.setKod("T");
                            hakmilikBaru.setKodStatusHakmilik(ksh);
                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                            hakmilikBaru.setKodUnitLuas(kodUOM);
                            int syorblock = permohonanBangunan.getSyerBlok();
                            hakmilikBaru.setUnitSyer(BigDecimal.valueOf(bpetak.getSyer()));
                            hakmilikBaru.setLuas(bpetak.getLuas());
                            hakmilikBaru.setCukai(BigDecimal.ZERO);
                            hakmilikBaru.setCukaiSebenar(BigDecimal.ZERO);
                            hakmilikBaru.setNoHakmilik(hMasterTitle.getNoHakmilik());
                            hakmilikBaru.setNoPelan(bpetak.getPabPetak());
                            hakmilikBaru.setNoPu(hMasterTitle.getNoLitho());
                            hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                            hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                            if (bdn != null) {
                                hakmilikBaru.setBadanPengurusan(bdn);
                            }
                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                            hakmilikBaru.setNoBangunan(null);
                            hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                            hakmilikBaru.setNoTingkat(null);
                            hakmilikBaru.setNoPetak(bpetak.getNama());
                            hakmilikBaru.setNoVersiDhde(1);
                            hakmilikBaru.setNoVersiDhke(1);
                            int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                            hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                            }
                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                            hakmilikBaru.setInfoAudit(ia);
                            /*INSERT INTO MOHON HAKMILIK*/
                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                            mohonHakmilikBaru.setPermohonan(p);
                            mohonHakmilikBaru.setInfoAudit(ia);
                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                            listHakmilikBaru.add(hakmilikBaru);
                            
                            List<BangunanPetakAksesori> senaraiPetakAksesori = bpetak.getSenaraiPetakAksesori();
                                for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                     HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                     hpa.setHakmilik(hakmilikBaru);
                                     hpa.setCawangan(hakmilikBaru.getCawangan());
                                     hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                     hpa.setNama(bangunanPetakAksesori.getNama());
                                     hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                     if (bangunanPetakAksesori.getLain() != null) {
                                         hpa.setLuas(BigDecimal.valueOf(Double.valueOf(bangunanPetakAksesori.getLain())));
                                     }
                                     hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                     hpa.setInfoAudit(ia);
                                     listHakmilikPetakAksesori.add(hpa);
                            }
                            
                            /*INSERT INTO HAKMILIK ASAL/SEBELUM*/
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                LOG.debug("start copy hakmilik asal/hakmilik sebelum for PHPC/PHPP");
                                LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hMasterTitle1.getSenaraiHakmilikAsal().size());
                                LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hMasterTitle1.getSenaraiHakmilikSebelum().size());
                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {                            
                                if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                    for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                        HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                        HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                        hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                        //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                        hap.setHakmilik(hakmilikBaru);
                                        hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                        hap.setInfoAudit(ia);
                                        hakmilikAsalPermohonanDAO.save(hap);
                                    }
                                 } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                    //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                    hap.setHakmilik(hakmilikBaru);
                                    hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                    hap.setInfoAudit(ia);
                                    hakmilikAsalPermohonanDAO.save(hap);
                                 }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                   if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                        for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                            HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                            HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                            hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                            //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                            hap.setHakmilik(hakmilikBaru);
                                            hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                            hap.setInfoAudit(ia);
                                            hakmilikAsalPermohonanDAO.save(hap);
                                        }
                                    } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                       HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                       hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                       //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                       hap.setHakmilik(hakmilikBaru);
                                       hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                       hap.setInfoAudit(ia);
                                       hakmilikAsalPermohonanDAO.save(hap);
                                    }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            }
                              
                            
//                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                if (hpk == null || hpk.getAktif() == 'T') {
//                                    continue;
//                                }

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
                                hpb.setAktif('Y');
                                hpb.setPihak(hpk.getPihak());
                                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                hpb.setWargaNegara(hpk.getWargaNegara());
                                hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                                if (kodNegeri.equals("04")) {
                                    hpb.setNama(hpk.getPihak().getNama());
                                    hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                    hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                    hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                    hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                    hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                    hpb.setPoskod(hpk.getPihak().getPoskod());
                                    hpb.setNegeri(hpk.getPihak().getNegeri());
                                }
                                hpb.setInfoAudit(ia);
                                lhp.add(hpb);
                            }
                        }
                    }
                } else {
                    KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();
                    List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat = 1;
                    if (kodKatBngn.getKod().equals("B") || kodKatBngn.getKod().equals("P")) {
                        if (!senaraiTingkat.isEmpty()) {
                            LOG.debug("---Bangunans---");
                            for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                                List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                                int countPetak = 1;
                                for (BangunanPetak bangunanPetak : senaraiPetak) {
                                    String noBangunan = permohonanBangunan.getNama();
                                    String noTingkat = String.valueOf(countTingkat);
                                    String noPetak = bangunanPetak.getNama();
                                    KodUOM kodUOM = bangunanPetak.getLuasUom();
                                    if (kodUOM == null) {
                                        kodUOM = kodUOMDAO.findById("M");
                                    }
                                    Hakmilik hakmilik = new Hakmilik();
                                    hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilik.setDaerah(hMasterTitle.getDaerah());
                                    hakmilik.setNoBangunan(noBangunan);
                                    hakmilik.setNoTingkat(noTingkat);
                                    hakmilik.setNoPetak(noPetak);
                                    hakmilik.setKodKategoriBangunan(kodKatBngn);
                                    int jumlahunit = 0;
                                    for (PermohonanBangunan mb : senaraiBangunan) {
                                        jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                                    }
                                    hakmilik.setJumlahUnitSyer(jumlahunit);
                                    hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                    hakmilik.setCukai(BigDecimal.ZERO);
                                    hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                    String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                                    LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                    Hakmilik hakmilikBaru = new Hakmilik();
                                    if (sbu != null) {
                                        hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                                    }
                                    String khm = hMasterTitle.getKodHakmilik().getKod();
                                    String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                    LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                    hakmilikBaru.setIdHakmilik(s1);
                                    hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                                    hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                    hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                    hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                    hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                    hakmilikBaru.setLot(hMasterTitle.getLot());
                                    hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                    hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                    hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                                    hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                    hakmilikBaru.setKodSumber("ET");
                                    KodStatusHakmilik ksh = new KodStatusHakmilik();
                                    ksh.setKod("T");
                                    hakmilikBaru.setKodStatusHakmilik(ksh);
                                    hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                    hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                    hakmilikBaru.setKodUnitLuas(kodUOM);
                                    if (bangunanPetak.getLuas() != null) {
                                        BigDecimal luasPetak = bangunanPetak.getLuas();
                                        hakmilikBaru.setLuas(luasPetak);
                                    } else {
                                        hakmilikBaru.setLuas(BigDecimal.ZERO);
                                    }
                                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                    hakmilikBaru.setNoHakmilik(noHakmilik);
                                    hakmilikBaru.setNoPelan(bangunanPetak.getPabPetak());
                                    hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                                    hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                                    hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                    hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                    hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                    hakmilikBaru.setCukai(BigDecimal.ZERO);
                                    hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                    hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                    hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                    hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                    hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                    hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                    hakmilikBaru.setNoVersiDhde(1);
                                    hakmilikBaru.setNoVersiDhke(1);
                                    hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                                    if (kodNegeri.equals("04")) {
                                        hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                    }
                                    BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                    if (bdn != null) {
                                        hakmilikBaru.setBadanPengurusan(bdn);
                                    }
                                    hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                    hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                    hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                    hakmilikBaru.setNoBangunan(noBangunan);
                                    hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                    hakmilikBaru.setNoTingkat(noTingkat);
                                    hakmilikBaru.setNoPetak(noPetak);
                                    if (bangunanPetak.getSyer() != null) {
                                        BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                        hakmilikBaru.setUnitSyer(unitSyer);
                                    }
                                    int jumlahunit1 = 0;
                                    for (PermohonanBangunan mb2 : senaraiBangunan) {
                                        jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                                    }
                                    hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                    if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                        hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                    }
                                    hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                    hakmilikBaru.setInfoAudit(ia);
                                    /*INSERT INTO MOHON HAKMILIK*/
                                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                    mohonHakmilikBaru.setPermohonan(p);
                                    mohonHakmilikBaru.setInfoAudit(ia);
                                    listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                    listHakmilikBaru.add(hakmilikBaru);
                            
                            /*INSERT INTO HAKMILIK ASAL/SEBELUM*/
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                LOG.debug("start copy hakmilik asal/hakmilik sebelum for PHPC/PHPP");
                                LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hMasterTitle1.getSenaraiHakmilikAsal().size());
                                LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hMasterTitle1.getSenaraiHakmilikSebelum().size());
                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {                            
                                if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                    for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                        HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                        HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                        hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                        //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                        hap.setHakmilik(hakmilikBaru);
                                        hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                        hap.setInfoAudit(ia);
                                        hakmilikAsalPermohonanDAO.save(hap);
                                    }
                                 } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                    //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                    hap.setHakmilik(hakmilikBaru);
                                    hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                    hap.setInfoAudit(ia);
                                    hakmilikAsalPermohonanDAO.save(hap);
                                 }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                   if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                        for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                            HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                            HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                            hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                            //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                            hap.setHakmilik(hakmilikBaru);
                                            hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                            hap.setInfoAudit(ia);
                                            hakmilikAsalPermohonanDAO.save(hap);
                                        }
                                    } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                       HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                       hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                       //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                       hap.setHakmilik(hakmilikBaru);
                                       hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                       hap.setInfoAudit(ia);
                                       hakmilikAsalPermohonanDAO.save(hap);
                                    }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            }
                              
                                     
                                    List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                    for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                        HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                        hpa.setHakmilik(hakmilikBaru);
                                        hpa.setCawangan(hakmilikBaru.getCawangan());
                                        hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                        hpa.setNama(bangunanPetakAksesori.getNama());
                                        hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                        hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                        hpa.setInfoAudit(ia);
                                        listHakmilikPetakAksesori.add(hpa);
                                    }

//                                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                                    for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                        if (hpk == null || hpk.getAktif() == 'T') {
//                                            continue;
//                                        }

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
                                        hpb.setAktif('Y');
                                        hpb.setPihak(hpk.getPihak());
                                        hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                        hpb.setWargaNegara(hpk.getWargaNegara());
                                        hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                        hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                                        if (kodNegeri.equals("04")) {
                                            hpb.setNama(hpk.getPihak().getNama());
                                            hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                            hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                            hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                            hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                            hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                            hpb.setPoskod(hpk.getPihak().getPoskod());
                                            hpb.setNegeri(hpk.getPihak().getNegeri());
                                        }
                                        hpb.setInfoAudit(ia);
                                        lhp.add(hpb);
                                    }
                                    countPetak += 1;
                                }
                                countTingkat += 1;
                            }
                        }
                    } else if ("04".equals(conf.getProperty("kodNegeri"))) {
                        LOG.debug("---LandParcels---");
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = kodUOMDAO.findById("M");
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("0");
                        hakmilik.setNoPetak("0");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                        String idHakmilikBaru = generateStrataLandParcel(kodNegeri, null, hakmilik);
                        LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                        Hakmilik hakmilikBaru = new Hakmilik();
                        if (sbu != null) {
                            hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                        }
                        String khm = hMasterTitle.getKodHakmilik().getKod();
                        LOG.debug("SAVING NEW ID HAKMILIK : " + hakmilikBaru);
                        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                        hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                        hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                        hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                        hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                        hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                        hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                        hakmilikBaru.setLot(hMasterTitle.getLot());
                        hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                        hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                        hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                        hakmilikBaru.setKodSumber("ET");
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hakmilikBaru.setKodStatusHakmilik(ksh);
                        hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                        hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                        hakmilikBaru.setKodUnitLuas(kodUOM);
                        int syorblock = permohonanBangunan.getSyerBlok();
                        hakmilikBaru.setUnitSyer(BigDecimal.valueOf(syorblock));
                        String luas = permohonanBangunan.getUntukKegunaanLain();
                        BigDecimal luas1 = new BigDecimal(luas);
                        if (permohonanBangunan.getUntukKegunaanLain() != null) {
                            hakmilikBaru.setLuas(luas1);
                        } else {
                            hakmilikBaru.setLuas(BigDecimal.ZERO);
                        }
                        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 14, (idHakmilikBaru.length() - 14) + 8);
                        hakmilikBaru.setNoHakmilik(noHakmilik);
                        hakmilikBaru.setNoPelan("");
                        hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                        hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                        hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                        hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                        hakmilikBaru.setPbt(hMasterTitle.getPbt());
                        hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                        hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                        hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                        hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                        hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                        hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                        hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                        BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                        if (bdn != null) {
                            hakmilikBaru.setBadanPengurusan(bdn);
                        }
                        hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                        hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                        hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                        hakmilikBaru.setNoBangunan(noBangunan);
                        hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                        hakmilikBaru.setNoTingkat("0");
                        hakmilikBaru.setNoPetak("0");
                        hakmilikBaru.setNoVersiDhde(1);
                        hakmilikBaru.setNoVersiDhke(1);
                        int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                        hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                        hakmilikBaru.setInfoAudit(ia);
                        /*INSERT INTO MOHON HAKMILIK*/
                        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                        mohonHakmilikBaru.setPermohonan(p);
                        mohonHakmilikBaru.setInfoAudit(ia);
                        listMohonHakmilikBaru.add(mohonHakmilikBaru);
                        listHakmilikBaru.add(hakmilikBaru);
                            
                            /*INSERT INTO HAKMILIK ASAL/SEBELUM*/
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                LOG.debug("start copy hakmilik asal/hakmilik sebelum for PHPC/PHPP");
                                LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hMasterTitle1.getSenaraiHakmilikAsal().size());
                                LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hMasterTitle1.getSenaraiHakmilikSebelum().size());
                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {                            
                                if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                    for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                        HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                        HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                        hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                        //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                        hap.setHakmilik(hakmilikBaru);
                                        hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                        hap.setInfoAudit(ia);
                                        hakmilikAsalPermohonanDAO.save(hap);
                                    }
                                 } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                    //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                    hap.setHakmilik(hakmilikBaru);
                                    hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                    hap.setInfoAudit(ia);
                                    hakmilikAsalPermohonanDAO.save(hap);
                                 }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                   if (hMasterTitle1.getSenaraiHakmilikAsal().size() > 0) {
                                        for (int a = 0; a < hMasterTitle1.getSenaraiHakmilikAsal().size(); a++) {
                                            HakmilikAsal hma1 = (HakmilikAsal) hMasterTitle1.getSenaraiHakmilikAsal().get(a);
                                            HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                            hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                            //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                            hap.setHakmilik(hakmilikBaru);
                                            hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                                            hap.setInfoAudit(ia);
                                            hakmilikAsalPermohonanDAO.save(hap);
                                        }
                                    } else if (hMasterTitle1.getSenaraiHakmilikAsal().size() == 0) {
                                       HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                                       hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                       //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                                       hap.setHakmilik(hakmilikBaru);
                                       hap.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                       hap.setInfoAudit(ia);
                                       hakmilikAsalPermohonanDAO.save(hap);
                                    }

                                 if (hMasterTitle1.getSenaraiHakmilikAsal().size() != 0) {
                                     LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                                     HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                     hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                     hsp.setPermohonan(p);
                                     hsp.setCawangan(pengguna.getKodCawangan());
                                     hsp.setHakmilik(hakmilikBaru);
                                     hsp.setHakmilikSejarah(hMasterTitle1.getIdHakmilik());
                                     hsp.setInfoAudit(ia);
                                     hakmilikSblmPermohonanDAO.save(hsp);
                                 }
                            }
                              
                          
//                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                if (hpk == null || hpk.getAktif() == 'T') {
//                                    continue;
//                                }
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
                            hpb.setAktif('Y');
                            hpb.setPihak(hpk.getPihak());
                            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                            hpb.setWargaNegara(hpk.getWargaNegara());
                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                            if (kodNegeri.equals("04")) {
                                hpb.setNama(hpk.getPihak().getNama());
                                hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                hpb.setPoskod(hpk.getPihak().getPoskod());
                                hpb.setNegeri(hpk.getPihak().getNegeri());
                            }
                            hpb.setInfoAudit(ia);
                            lhp.add(hpb);
                        }
                    }
                }
            }

            if (!listHakmilikBaru.isEmpty()) {
                for (Hakmilik hakmilik : listHakmilikBaru) {
                    regService.simpanHakmilik(hakmilik);
                }
            }

            if (!listMohonHakmilikBaru.isEmpty()) {
                for (HakmilikPermohonan hp : listMohonHakmilikBaru) {
                    regService.simpanMohonHakmilik(hp);
                }
            }

            if (!lhp.isEmpty()) {
                regService.simpanHakmilikPihak(lhp);
            }
            if (listHakmilikPetakAksesori != null) {
                if (!listHakmilikPetakAksesori.isEmpty()) {
                    regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
                }
            }
            
            if (!hakmiliksebelumlist.isEmpty()) {
                regService.simpanHakmilikSebelum(hakmiliksebelumlist);
            }
        }
    }

    /* Added By Murali to Generate Landparcels */
    public String generateStrataLandParcel(String kodNegeri, KodCawangan caw, Object obj) {
        if (!(obj instanceof Hakmilik)) {
            throw new IllegalArgumentException("Parameter is not Hakmilik class");
        }
        Hakmilik kod = (Hakmilik) obj;

        String noBangunan = kod.getNoBangunan();
        LOG.debug("---noBangunan---" + noBangunan);
        String noHakmilikLama = kod.getIdHakmilik();
        NumberFormat formatterBangunan = new DecimalFormat("00");
        String id;
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            String noPetakL = kod.getNoPetak();
            id = noHakmilikLama + "000L" + Integer.parseInt(noPetakL.substring(1));
        } else {
            id = noHakmilikLama + "000L" + formatterBangunan.format(Integer.parseInt(noBangunan.substring(1)));
        }
        LOG.debug("---Generate IdHakmilik---" + id);
        System.out.println(id);
        return id;
    }
    
    public void generateHakmilikStrata3(InfoAudit ia, Permohonan p, Pengguna pengguna) {

        kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            permohonanSebelum = p;
        } else {
            permohonanSebelum = p.getPermohonanSebelum();
        }

        if (permohonanSebelum != null) {
            List<PermohonanStrata> listStrata = permohonanSebelum.getSenaraiStrata();
            Hakmilik masterTitle = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
            Hakmilik hMasterTitle = hakmilikDAO.findById(permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk());
            LOG.debug(": HAKMILIK INDUK : " +hMasterTitle);
            List<PermohonanBangunan> senaraiBangunan = permohonanSebelum.getSenaraiBangunan();
            
            BadanPengurusan sbu = strService.findBdn(p.getIdPermohonan());
            //TODO :  count the total of unit syer
            int totalUnitSyer = 0;
            LOG.debug(": COUNT BEGIN : ");
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                List<BangunanTingkat> senaraiTingkat;
                senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                        if (bangunanPetak.getSyer() != null) {
                            LOG.debug(":: " + totalUnitSyer + " + " + bangunanPetak.getSyer());
                            totalUnitSyer = totalUnitSyer + bangunanPetak.getSyer();
                            LOG.debug(": Jumlah Syer = " + totalUnitSyer);
                        }
                    }
                }
            }

            LOG.debug(":Bangunan : " + senaraiBangunan.size());
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                if (!permohonanBangunan.getNamaLain().isEmpty()) {
                    KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();
                    List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat = 1;
                    if (kodKatBngn.getKod().equals("B")) {
                        if (!senaraiTingkat.isEmpty()) {
                            LOG.debug("---Bangunans---");
                            for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                                if (bangunanTingkat.getBilanganPetak() > 0) {
                                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                                    int countPetak = 1;
                                    LOG.debug(":countPetak : " + countPetak);
                                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                                        if (!bangunanPetak.getNama().equals("0")) {
                                            String noBangunan = permohonanBangunan.getNama();
                                            String noTingkat = String.valueOf(bangunanTingkat.getTingkat());
                                            String noPetak = bangunanPetak.getNama();
                                            KodUOM kodUOM = bangunanPetak.getLuasUom();
                                            if (kodUOM == null) {
                                                kodUOM = kodUOMDAO.findById("M");
                                            }
                                            Hakmilik hakmilik = new Hakmilik();
                                            hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                            hakmilik.setDaerah(hMasterTitle.getDaerah());
                                            hakmilik.setNoBangunan(noBangunan);
                                            hakmilik.setNoTingkat(noTingkat);
                                            hakmilik.setNoPetak(noPetak);
                                            hakmilik.setKodKategoriBangunan(kodKatBngn);
                                            int jumlahunit = 0;
                                            for (PermohonanBangunan mb : senaraiBangunan) {
                                                jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                                            }
                                            hakmilik.setJumlahUnitSyer(jumlahunit);
                                            hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                            hakmilik.setCukai(BigDecimal.ZERO);
                                            hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                            String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                            Hakmilik hakmilikBaru = new Hakmilik();
                                            if (sbu != null) {
                                                hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                                            }
                                            String khm = hMasterTitle.getKodHakmilik().getKod();
                                            String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                            LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                            hakmilikBaru.setIdHakmilik(s1);
                                            hakmilikBaru.setNoFail(null);
                                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                            hakmilikBaru.setLot(hMasterTitle.getLot());
                                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                            }
//                                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                            hakmilikBaru.setKodSumber("ET");
                                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                                            ksh.setKod("T");
                                            hakmilikBaru.setKodStatusHakmilik(ksh);
                                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                            hakmilikBaru.setKodUnitLuas(kodUOM);
                                            if (bangunanPetak.getLuas() != null) {
                                                BigDecimal luasPetak = bangunanPetak.getLuas();
                                                hakmilikBaru.setLuas(luasPetak);
                                            } else {
                                                hakmilikBaru.setLuas(BigDecimal.ZERO);
                                            }
                                            String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                            hakmilikBaru.setNoHakmilik(noHakmilik);
                                            hakmilikBaru.setNoPelan(bangunanPetak.getPabPetak());
                                            hakmilikBaru.setNoPu(hMasterTitle.getNoLitho());
                                            hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                            hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                            hakmilikBaru.setCukai(BigDecimal.ZERO);
                                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                            hakmilikBaru.setNoVersiDhde(1);
                                            hakmilikBaru.setNoVersiDhke(1);
                                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                                            if (kodNegeri.equals("04")) {
                                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                            }
                                            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                            if (bdn != null) {
                                                hakmilikBaru.setBadanPengurusan(bdn);
                                            }
                                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                            hakmilikBaru.setNoBangunan(noBangunan);
                                            hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                            hakmilikBaru.setNoTingkat(bangunanTingkat.getNama());
                                            hakmilikBaru.setNoPetak(noPetak);
                                            hakmilikBaru.setMenara(bangunanTingkat.getLain());
                                            hakmilikBaru.setMezanin(bangunanTingkat.getMezanin());
                                            if (bangunanPetak.getSyer() != null) {
                                                BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                                hakmilikBaru.setUnitSyer(unitSyer);
                                            }
                                            int jumlahunit1 = 0;
                                            for (PermohonanBangunan mb2 : senaraiBangunan) {
                                                jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                                            }
                                            hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                            }
                                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                            hakmilikBaru.setInfoAudit(ia);
                                            /*INSERT INTO MOHON HAKMILIK*/
                                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                            mohonHakmilikBaru.setPermohonan(p);
                                            mohonHakmilikBaru.setInfoAudit(ia);
                                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                            listHakmilikBaru.add(hakmilikBaru);

                                            List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                            for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                                HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                                hpa.setHakmilik(hakmilikBaru);
                                                hpa.setCawangan(hakmilikBaru.getCawangan());
                                                hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                                hpa.setNama(bangunanPetakAksesori.getNama());
                                                hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                                if (bangunanPetakAksesori.getLain() != null) {
                                                    hpa.setLuas(BigDecimal.valueOf(Double.valueOf(bangunanPetakAksesori.getLain())));
                                                }
                                                hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                                hpa.setInfoAudit(ia);
                                                listHakmilikPetakAksesori.add(hpa);
                                            }
                                            
                                            /*INSERT INTO HAKMILIK SEBELUM*/
                                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                hakmilikSebelumBaru.setInfoAudit(ia);
                                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                                List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                                List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                                for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                    hakmilikSebelumBaru.setInfoAudit(ia);
                                                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                                }
                                            }

//                                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPAfterHTB(hMasterTitle.getIdHakmilik());
                                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                                if (hpk == null || hpk.getAktif() == 'T') {
//                                                    continue;
//                                                }

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
                                                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                                hpb.setWargaNegara(hpk.getWargaNegara());
                                                hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                                hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                                                hpb.setAktif('Y');
                                                hpb.setPihak(hpk.getPihak());
                                                if (kodNegeri.equals("04")) {
                                                    hpb.setNama(hpk.getPihak().getNama());
                                                    hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                                    hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                                    hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                                    hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                                    hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                                    hpb.setPoskod(hpk.getPihak().getPoskod());
                                                    hpb.setNegeri(hpk.getPihak().getNegeri());
                                                }
                                                hpb.setInfoAudit(ia);
                                                lhp.add(hpb);
                                            }
                                            countPetak += 1;
                                        }
                                    }
                                }
                                countTingkat += 1;
                            }
                        }
                    } else if (kodKatBngn.getKod().equals("P") || kodKatBngn.getKod().equals("PL")) {
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = null;
                        if (kodUOM == null) {
                            kodUOM = kodUOMDAO.findById("M");
                        }
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("1");
                        hakmilik.setNoPetak("1");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = 0;
                        for (PermohonanBangunan mb : senaraiBangunan) {
                            jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                        }
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                        hakmilik.setCukai(BigDecimal.ZERO);
                        hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                        String noBangunanP = StringUtils.leftPad(noBangunan, 3, '0');
                        String idHakmilikBaru = hMasterTitle.getIdHakmilik() + noBangunanP;
                        LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                        Hakmilik hakmilikBaru = new Hakmilik();
                        if (sbu != null) {
                            hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                        }
                        String khm = hMasterTitle.getKodHakmilik().getKod();
                        String s1 = idHakmilikBaru;
                        LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                        hakmilikBaru.setNoFail(null);
                        hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                        hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                        hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                        hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                        hakmilikBaru.setLot(hMasterTitle.getLot());
                        hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                        hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                        hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                        hakmilikBaru.setTarikhDaftar(new java.util.Date());
                        hakmilikBaru.setKodSumber("ET");
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hakmilikBaru.setKodStatusHakmilik(ksh);
                        hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                        hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                        hakmilikBaru.setKodUnitLuas(kodUOM);
                        hakmilikBaru.setLuas(BigDecimal.ZERO);

                        String noHakmilik = hMasterTitle.getNoHakmilik();
                        hakmilikBaru.setNoHakmilik(noHakmilik);
                        hakmilikBaru.setNoPelan("");
                        hakmilikBaru.setNoPu(hMasterTitle.getNoLitho());
                        hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                        hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                        hakmilikBaru.setPbt(hMasterTitle.getPbt());
                        hakmilikBaru.setCukai(BigDecimal.ZERO);
                        hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                        hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                        hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                        hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                        hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                        hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                        hakmilikBaru.setNoVersiDhde(1);
                        hakmilikBaru.setNoVersiDhke(1);

                        hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                        if (kodNegeri.equals("04")) {
                            hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                        }
                        BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                        if (bdn != null) {
                            hakmilikBaru.setBadanPengurusan(bdn);
                        }
                        hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                        hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                        hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                        hakmilikBaru.setNoBangunan(noBangunan);
                        hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                        hakmilikBaru.setNoTingkat("1");
                        hakmilikBaru.setNoPetak("1");
                        hakmilikBaru.setMenara(null);
                        hakmilikBaru.setMezanin(null);
                        hakmilikBaru.setUnitSyer(BigDecimal.valueOf(permohonanBangunan.getSyerBlok()));
                        int jumlahunit1 = 0;
                        for (PermohonanBangunan mb2 : senaraiBangunan) {
                            jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                        }
                        hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                        if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                            hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                        }
                        hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                        hakmilikBaru.setInfoAudit(ia);
                        /*INSERT INTO MOHON HAKMILIK*/
                        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                        mohonHakmilikBaru.setPermohonan(p);
                        mohonHakmilikBaru.setInfoAudit(ia);
                        listMohonHakmilikBaru.add(mohonHakmilikBaru);
                        listHakmilikBaru.add(hakmilikBaru);

                        listHakmilikPetakAksesori = null;
                        
                        /*INSERT INTO HAKMILIK SEBELUM*/
                        if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                           SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                           HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                           hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                           hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                           hakmilikSebelumBaru.setInfoAudit(ia);
                           hakmiliksebelumlist.add(hakmilikSebelumBaru);
                        } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                  List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                  List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                      for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                           SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                           HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                           hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                           hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                           hakmilikSebelumBaru.setInfoAudit(ia);
                                           hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                       }
                        }

//                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                            if (hpk == null || hpk.getAktif() == 'T') {
//                                continue;
//                            }

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
                            hpb.setAktif('Y');
                            hpb.setPihak(hpk.getPihak());
                            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                            hpb.setWargaNegara(hpk.getWargaNegara());
                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                            if (kodNegeri.equals("04")) {
                                hpb.setNama(hpk.getPihak().getNama());
                                hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                hpb.setPoskod(hpk.getPihak().getPoskod());
                                hpb.setNegeri(hpk.getPihak().getNegeri());
                            }
                            hpb.setInfoAudit(ia);
                            lhp.add(hpb);
                        }

                    } else {
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = kodUOMDAO.findById("M");
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("0");
                        hakmilik.setNoPetak("0");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());

                        List<BangunanTingkat> bt = strService.findByIDBangunan(permohonanBangunan.getIdBangunan());
                        List<BangunanPetak> bp = null;
                        if (bt.size() > 0) {
                            bp = strService.findByPetakByIDTingkat(bt.get(0).getIdTingkat());
                        }
                        for (BangunanPetak bpetak : bp) {
                            String noBangunanL = StringUtils.leftPad(bpetak.getNama(), 5, '0');
                            String idHakmilikBaru = hMasterTitle.getIdHakmilik() + noBangunanL;
                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                            Hakmilik hakmilikBaru = new Hakmilik();
                            if (sbu != null) {
                                hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                            }
                            String khm = hMasterTitle.getKodHakmilik().getKod();
                            LOG.debug("SAVING NEW ID HAKMILIK : " + hakmilikBaru);
                            hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                            hakmilikBaru.setNoFail(null);
                            hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                            hakmilikBaru.setLot(hMasterTitle.getLot());
                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                            hakmilikBaru.setKodSumber("ET");
                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                            ksh.setKod("T");
                            hakmilikBaru.setKodStatusHakmilik(ksh);
                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                            hakmilikBaru.setKodUnitLuas(kodUOM);
                            int syorblock = permohonanBangunan.getSyerBlok();
                            hakmilikBaru.setUnitSyer(BigDecimal.valueOf(bpetak.getSyer()));
                            hakmilikBaru.setLuas(bpetak.getLuas());
                            hakmilikBaru.setCukai(BigDecimal.ZERO);
                            hakmilikBaru.setCukaiSebenar(BigDecimal.ZERO);
                            hakmilikBaru.setNoHakmilik(hMasterTitle.getNoHakmilik());
                            hakmilikBaru.setNoPelan(bpetak.getPabPetak());
                            hakmilikBaru.setNoPu(hMasterTitle.getNoLitho());
                            hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                            hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                            if (bdn != null) {
                                hakmilikBaru.setBadanPengurusan(bdn);
                            }
                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                            hakmilikBaru.setNoBangunan(null);
                            hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                            hakmilikBaru.setNoTingkat(null);
                            hakmilikBaru.setNoPetak(bpetak.getNama());
                            hakmilikBaru.setNoVersiDhde(1);
                            hakmilikBaru.setNoVersiDhke(1);
                            int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                            hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                            }
                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                            hakmilikBaru.setInfoAudit(ia);
                            /*INSERT INTO MOHON HAKMILIK*/
                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                            mohonHakmilikBaru.setPermohonan(p);
                            mohonHakmilikBaru.setInfoAudit(ia);
                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                            listHakmilikBaru.add(hakmilikBaru);
                            
                            /*INSERT INTO HAKMILIK SEBELUM*/
                                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                hakmilikSebelumBaru.setInfoAudit(ia);
                                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                                List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                                List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                                for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                    hakmilikSebelumBaru.setInfoAudit(ia);
                                                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                                }
                                            }
                            
//                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                if (hpk == null || hpk.getAktif() == 'T') {
//                                    continue;
//                                }

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
                                hpb.setAktif('Y');
                                hpb.setPihak(hpk.getPihak());
                                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                hpb.setWargaNegara(hpk.getWargaNegara());
                                hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                                if (kodNegeri.equals("04")) {
                                    hpb.setNama(hpk.getPihak().getNama());
                                    hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                    hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                    hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                    hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                    hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                    hpb.setPoskod(hpk.getPihak().getPoskod());
                                    hpb.setNegeri(hpk.getPihak().getNegeri());
                                }
                                hpb.setInfoAudit(ia);
                                lhp.add(hpb);
                            }
                        }
                    }
                } else {
                    KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();
                    List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat = 1;
                    if (kodKatBngn.getKod().equals("B") || kodKatBngn.getKod().equals("P")) {
                        if (!senaraiTingkat.isEmpty()) {
                            LOG.debug("---Bangunans---");
                            for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                                List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                                int countPetak = 1;
                                for (BangunanPetak bangunanPetak : senaraiPetak) {
                                    String noBangunan = permohonanBangunan.getNama();
                                    String noTingkat = String.valueOf(countTingkat);
                                    String noPetak = bangunanPetak.getNama();
                                    KodUOM kodUOM = bangunanPetak.getLuasUom();
                                    if (kodUOM == null) {
                                        kodUOM = kodUOMDAO.findById("M");
                                    }
                                    Hakmilik hakmilik = new Hakmilik();
                                    hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilik.setDaerah(hMasterTitle.getDaerah());
                                    hakmilik.setNoBangunan(noBangunan);
                                    hakmilik.setNoTingkat(noTingkat);
                                    hakmilik.setNoPetak(noPetak);
                                    hakmilik.setKodKategoriBangunan(kodKatBngn);
                                    int jumlahunit = 0;
                                    for (PermohonanBangunan mb : senaraiBangunan) {
                                        jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                                    }
                                    hakmilik.setJumlahUnitSyer(jumlahunit);
                                    hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                    hakmilik.setCukai(BigDecimal.ZERO);
                                    hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                    String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                                    LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                    Hakmilik hakmilikBaru = new Hakmilik();
                                    if (sbu != null) {
                                        hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                                    }
                                    String khm = hMasterTitle.getKodHakmilik().getKod();
                                    String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                    LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                    hakmilikBaru.setIdHakmilik(s1);
                                    hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                                    hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                    hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                    hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                    hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                    hakmilikBaru.setLot(hMasterTitle.getLot());
                                    hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                    hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                    hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                                    hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                    hakmilikBaru.setKodSumber("ET");
                                    KodStatusHakmilik ksh = new KodStatusHakmilik();
                                    ksh.setKod("T");
                                    hakmilikBaru.setKodStatusHakmilik(ksh);
                                    hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                    hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                    hakmilikBaru.setKodUnitLuas(kodUOM);
                                    if (bangunanPetak.getLuas() != null) {
                                        BigDecimal luasPetak = bangunanPetak.getLuas();
                                        hakmilikBaru.setLuas(luasPetak);
                                    } else {
                                        hakmilikBaru.setLuas(BigDecimal.ZERO);
                                    }
                                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                    hakmilikBaru.setNoHakmilik(noHakmilik);
                                    hakmilikBaru.setNoPelan(bangunanPetak.getPabPetak());
                                    hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                                    hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                                    hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                    hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                    hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                    hakmilikBaru.setCukai(BigDecimal.ZERO);
                                    hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                    hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                    hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                    hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                    hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                    hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                    hakmilikBaru.setNoVersiDhde(1);
                                    hakmilikBaru.setNoVersiDhke(1);
                                    hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                                    if (kodNegeri.equals("04")) {
                                        hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                    }
                                    BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                    if (bdn != null) {
                                        hakmilikBaru.setBadanPengurusan(bdn);
                                    }
                                    hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                    hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                    hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                    hakmilikBaru.setNoBangunan(noBangunan);
                                    hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                    hakmilikBaru.setNoTingkat(noTingkat);
                                    hakmilikBaru.setNoPetak(noPetak);
                                    if (bangunanPetak.getSyer() != null) {
                                        BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                        hakmilikBaru.setUnitSyer(unitSyer);
                                    }
                                    int jumlahunit1 = 0;
                                    for (PermohonanBangunan mb2 : senaraiBangunan) {
                                        jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                                    }
                                    hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                    if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                        hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                    }
                                    hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                    hakmilikBaru.setInfoAudit(ia);
                                    /*INSERT INTO MOHON HAKMILIK*/
                                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                    mohonHakmilikBaru.setPermohonan(p);
                                    mohonHakmilikBaru.setInfoAudit(ia);
                                    listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                    listHakmilikBaru.add(hakmilikBaru);

                                    /*INSERT INTO HAKMILIK SEBELUM*/
                                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                hakmilikSebelumBaru.setInfoAudit(ia);
                                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                                List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                                List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                                for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                    hakmilikSebelumBaru.setInfoAudit(ia);
                                                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                                }
                                            }
                                    
                                    List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                    for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                        HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                        hpa.setHakmilik(hakmilikBaru);
                                        hpa.setCawangan(hakmilikBaru.getCawangan());
                                        hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                        hpa.setNama(bangunanPetakAksesori.getNama());
                                        hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                        hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                        hpa.setInfoAudit(ia);
                                        listHakmilikPetakAksesori.add(hpa);
                                    }

//                                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                                    for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                        if (hpk == null || hpk.getAktif() == 'T') {
//                                            continue;
//                                        }

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
                                        hpb.setAktif('Y');
                                        hpb.setPihak(hpk.getPihak());
                                        hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                        hpb.setWargaNegara(hpk.getWargaNegara());
                                        hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                        hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                                        if (kodNegeri.equals("04")) {
                                            hpb.setNama(hpk.getPihak().getNama());
                                            hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                            hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                            hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                            hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                            hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                            hpb.setPoskod(hpk.getPihak().getPoskod());
                                            hpb.setNegeri(hpk.getPihak().getNegeri());
                                        }
                                        hpb.setInfoAudit(ia);
                                        lhp.add(hpb);
                                    }
                                    countPetak += 1;
                                }
                                countTingkat += 1;
                            }
                        }
                    } else if ("04".equals(conf.getProperty("kodNegeri"))) {
                        LOG.debug("---LandParcels---");
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = kodUOMDAO.findById("M");
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("0");
                        hakmilik.setNoPetak("0");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                        String idHakmilikBaru = generateStrataLandParcel(kodNegeri, null, hakmilik);
                        LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                        Hakmilik hakmilikBaru = new Hakmilik();
                        if (sbu != null) {
                            hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                        }
                        String khm = hMasterTitle.getKodHakmilik().getKod();
                        LOG.debug("SAVING NEW ID HAKMILIK : " + hakmilikBaru);
                        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                        hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                        hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                        hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                        hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                        hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                        hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                        hakmilikBaru.setLot(hMasterTitle.getLot());
                        hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                        hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                        hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                        hakmilikBaru.setKodSumber("ET");
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hakmilikBaru.setKodStatusHakmilik(ksh);
                        hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                        hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                        hakmilikBaru.setKodUnitLuas(kodUOM);
                        int syorblock = permohonanBangunan.getSyerBlok();
                        hakmilikBaru.setUnitSyer(BigDecimal.valueOf(syorblock));
                        String luas = permohonanBangunan.getUntukKegunaanLain();
                        BigDecimal luas1 = new BigDecimal(luas);
                        if (permohonanBangunan.getUntukKegunaanLain() != null) {
                            hakmilikBaru.setLuas(luas1);
                        } else {
                            hakmilikBaru.setLuas(BigDecimal.ZERO);
                        }
                        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 14, (idHakmilikBaru.length() - 14) + 8);
                        hakmilikBaru.setNoHakmilik(noHakmilik);
                        hakmilikBaru.setNoPelan("");
                        hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                        hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                        hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                        hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                        hakmilikBaru.setPbt(hMasterTitle.getPbt());
                        hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                        hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                        hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                        hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                        hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                        hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                        hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                        BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                        if (bdn != null) {
                            hakmilikBaru.setBadanPengurusan(bdn);
                        }
                        hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                        hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                        hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                        hakmilikBaru.setNoBangunan(noBangunan);
                        hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                        hakmilikBaru.setNoTingkat("0");
                        hakmilikBaru.setNoPetak("0");
                        hakmilikBaru.setNoVersiDhde(1);
                        hakmilikBaru.setNoVersiDhke(1);
                        int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                        hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                        hakmilikBaru.setInfoAudit(ia);
                        /*INSERT INTO MOHON HAKMILIK*/
                        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                        mohonHakmilikBaru.setPermohonan(p);
                        mohonHakmilikBaru.setInfoAudit(ia);
                        listMohonHakmilikBaru.add(mohonHakmilikBaru);
                        listHakmilikBaru.add(hakmilikBaru);
                        
                        /*INSERT INTO HAKMILIK SEBELUM*/
                                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                hakmilikSebelumBaru.setInfoAudit(ia);
                                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                                List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                                List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                                for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                    hakmilikSebelumBaru.setInfoAudit(ia);
                                                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                                }
                                            }
                        
//                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                if (hpk == null || hpk.getAktif() == 'T') {
//                                    continue;
//                                }
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
                            hpb.setAktif('Y');
                            hpb.setPihak(hpk.getPihak());
                            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                            hpb.setWargaNegara(hpk.getWargaNegara());
                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                            if (kodNegeri.equals("04")) {
                                hpb.setNama(hpk.getPihak().getNama());
                                hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                hpb.setPoskod(hpk.getPihak().getPoskod());
                                hpb.setNegeri(hpk.getPihak().getNegeri());
                            }
                            hpb.setInfoAudit(ia);
                            lhp.add(hpb);
                        }
                    }
                }
            }

            if (!listHakmilikBaru.isEmpty()) {
                for (Hakmilik hakmilik : listHakmilikBaru) {
                    regService.simpanHakmilik(hakmilik);
                }
            }

            if (!listMohonHakmilikBaru.isEmpty()) {
                for (HakmilikPermohonan hp : listMohonHakmilikBaru) {
                    regService.simpanMohonHakmilik(hp);
                }
            }

            if (!lhp.isEmpty()) {
                regService.simpanHakmilikPihak(lhp);
            }
            if (listHakmilikPetakAksesori != null) {
                if (!listHakmilikPetakAksesori.isEmpty()) {
                    regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
                }
            }
            
            if (!hakmiliksebelumlist.isEmpty()) {
                regService.simpanHakmilikSebelum(hakmiliksebelumlist);
            }
        }
    }
    
    public void generateHakmilikStrata4(InfoAudit ia, Permohonan p, Pengguna pengguna) {

        kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            permohonanSebelum = p;
        } else {
            permohonanSebelum = p.getPermohonanSebelum();
        }

        if (permohonanSebelum != null) {
            List<PermohonanStrata> listStrata = permohonanSebelum.getSenaraiStrata();
            Hakmilik masterTitle = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
            Hakmilik hMasterTitle = hakmilikDAO.findById(permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk());
            LOG.debug(": HAKMILIK INDUK : " +hMasterTitle);
            List<PermohonanBangunan> senaraiBangunan = permohonanSebelum.getSenaraiBangunan();
            
            BadanPengurusan sbu = strService.findBdn(p.getIdPermohonan());
            //TODO :  count the total of unit syer
            int totalUnitSyer = 0;
            LOG.debug(": COUNT BEGIN : ");
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                List<BangunanTingkat> senaraiTingkat;
                senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                        if (bangunanPetak.getSyer() != null) {
                            LOG.debug(":: " + totalUnitSyer + " + " + bangunanPetak.getSyer());
                            totalUnitSyer = totalUnitSyer + bangunanPetak.getSyer();
                            LOG.debug(": Jumlah Syer = " + totalUnitSyer);
                        }
                    }
                }
            }

            LOG.debug(":Bangunan : " + senaraiBangunan.size());
            for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {
                if (!permohonanBangunan.getNamaLain().isEmpty()) {
                    KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();
                    List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat = 1;
                    if (kodKatBngn.getKod().equals("B")) {
                        if (!senaraiTingkat.isEmpty()) {
                            LOG.debug("---Bangunans---");
                            for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                                if (bangunanTingkat.getBilanganPetak() > 0) {
                                    List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                                    int countPetak = 1;
                                    LOG.debug(":countPetak : " + countPetak);
                                    for (BangunanPetak bangunanPetak : senaraiPetak) {
                                        if (!bangunanPetak.getNama().equals("0")) {
                                            String noBangunan = permohonanBangunan.getNama();
                                            String noTingkat = String.valueOf(bangunanTingkat.getTingkat());
                                            String noPetak = bangunanPetak.getNama();
                                            KodUOM kodUOM = bangunanPetak.getLuasUom();
                                            if (kodUOM == null) {
                                                kodUOM = kodUOMDAO.findById("M");
                                            }
                                            Hakmilik hakmilik = new Hakmilik();
                                            hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                            hakmilik.setDaerah(hMasterTitle.getDaerah());
                                            hakmilik.setNoBangunan(noBangunan);
                                            hakmilik.setNoTingkat(noTingkat);
                                            hakmilik.setNoPetak(noPetak);
                                            hakmilik.setKodKategoriBangunan(kodKatBngn);
                                            int jumlahunit = 0;
                                            for (PermohonanBangunan mb : senaraiBangunan) {
                                                jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                                            }
                                            hakmilik.setJumlahUnitSyer(jumlahunit);
                                            hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                            hakmilik.setCukai(BigDecimal.ZERO);
                                            hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                            String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                            Hakmilik hakmilikBaru = new Hakmilik();
                                            if (sbu != null) {
                                                hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                                            }
                                            String khm = hMasterTitle.getKodHakmilik().getKod();
                                            String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                            LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                            hakmilikBaru.setIdHakmilik(s1);
                                            hakmilikBaru.setNoFail(null);
                                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                            hakmilikBaru.setLot(hMasterTitle.getLot());
                                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
                                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                            }
//                                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                            hakmilikBaru.setKodSumber("ET");
                                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                                            ksh.setKod("T");
                                            hakmilikBaru.setKodStatusHakmilik(ksh);
                                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                            hakmilikBaru.setKodUnitLuas(kodUOM);
                                            if (bangunanPetak.getLuas() != null) {
                                                BigDecimal luasPetak = bangunanPetak.getLuas();
                                                hakmilikBaru.setLuas(luasPetak);
                                            } else {
                                                hakmilikBaru.setLuas(BigDecimal.ZERO);
                                            }
                                            String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                            hakmilikBaru.setNoHakmilik(noHakmilik);
                                            hakmilikBaru.setNoPelan(bangunanPetak.getPabPetak());
                                            hakmilikBaru.setNoPu(hMasterTitle.getNoLitho());
                                            hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                            hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                            hakmilikBaru.setCukai(BigDecimal.ZERO);
                                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                            hakmilikBaru.setNoVersiDhde(1);
                                            hakmilikBaru.setNoVersiDhke(1);
                                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                                            if (kodNegeri.equals("04")) {
                                                hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                            }
                                            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                            if (bdn != null) {
                                                hakmilikBaru.setBadanPengurusan(bdn);
                                            }
                                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                            hakmilikBaru.setNoBangunan(noBangunan);
                                            hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                            hakmilikBaru.setNoTingkat(bangunanTingkat.getNama());
                                            hakmilikBaru.setNoPetak(noPetak);
                                            hakmilikBaru.setMenara(bangunanTingkat.getLain());
                                            hakmilikBaru.setMezanin(bangunanTingkat.getMezanin());
                                            if (bangunanPetak.getSyer() != null) {
                                                BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                                hakmilikBaru.setUnitSyer(unitSyer);
                                            }
                                            int jumlahunit1 = 0;
                                            for (PermohonanBangunan mb2 : senaraiBangunan) {
                                                jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                                            }
                                            hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                            }
                                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                            hakmilikBaru.setInfoAudit(ia);
                                            /*INSERT INTO MOHON HAKMILIK*/
                                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                            mohonHakmilikBaru.setPermohonan(p);
                                            mohonHakmilikBaru.setInfoAudit(ia);
                                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                            listHakmilikBaru.add(hakmilikBaru);

                                            List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                            for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                                HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                                hpa.setHakmilik(hakmilikBaru);
                                                hpa.setCawangan(hakmilikBaru.getCawangan());
                                                hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                                hpa.setNama(bangunanPetakAksesori.getNama());
                                                hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                                if (bangunanPetakAksesori.getLain() != null) {
                                                    hpa.setLuas(BigDecimal.valueOf(Double.valueOf(bangunanPetakAksesori.getLain())));
                                                }
                                                hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                                hpa.setInfoAudit(ia);
                                                listHakmilikPetakAksesori.add(hpa);
                                            }
                                            
                                            /*INSERT INTO HAKMILIK SEBELUM*/
                                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                hakmilikSebelumBaru.setInfoAudit(ia);
                                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                                List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                                List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                                for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                    hakmilikSebelumBaru.setInfoAudit(ia);
                                                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                                }
                                            }

//                                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPAfterHTB(hMasterTitle.getIdHakmilik());
                                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                                if (hpk == null || hpk.getAktif() == 'T') {
//                                                    continue;
//                                                }

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
                                                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                                hpb.setWargaNegara(hpk.getWargaNegara());
                                                hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                                hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                                                hpb.setAktif('Y');
                                                hpb.setPihak(hpk.getPihak());
                                                if (kodNegeri.equals("04")) {
                                                    hpb.setNama(hpk.getPihak().getNama());
                                                    hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                                    hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                                    hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                                    hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                                    hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                                    hpb.setPoskod(hpk.getPihak().getPoskod());
                                                    hpb.setNegeri(hpk.getPihak().getNegeri());
                                                }
                                                hpb.setInfoAudit(ia);
                                                lhp.add(hpb);
                                            }
                                            countPetak += 1;
                                        }
                                    }
                                }
                                countTingkat += 1;
                            }
                        }
                    } else if (kodKatBngn.getKod().equals("P") || kodKatBngn.getKod().equals("PL")) {
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = null;
                        if (kodUOM == null) {
                            kodUOM = kodUOMDAO.findById("M");
                        }
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("1");
                        hakmilik.setNoPetak("1");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = 0;
                        for (PermohonanBangunan mb : senaraiBangunan) {
                            jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                        }
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                        hakmilik.setCukai(BigDecimal.ZERO);
                        hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                        String noBangunanP = StringUtils.leftPad(noBangunan, 3, '0');
                        String idHakmilikBaru = hMasterTitle.getIdHakmilik() + noBangunanP;
                        LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                        Hakmilik hakmilikBaru = new Hakmilik();
                        if (sbu != null) {
                            hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                        }
                        String khm = hMasterTitle.getKodHakmilik().getKod();
                        String s1 = idHakmilikBaru;
                        LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                        hakmilikBaru.setNoFail(null);
                        hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                        hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                        hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                        hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                        hakmilikBaru.setLot(hMasterTitle.getLot());
                        hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                        hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                        hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                        hakmilikBaru.setTarikhDaftar(new java.util.Date());
                        hakmilikBaru.setKodSumber("ET");
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hakmilikBaru.setKodStatusHakmilik(ksh);
                        hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                        hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                        hakmilikBaru.setKodUnitLuas(kodUOM);
                        hakmilikBaru.setLuas(BigDecimal.ZERO);

                        String noHakmilik = hMasterTitle.getNoHakmilik();
                        hakmilikBaru.setNoHakmilik(noHakmilik);
                        hakmilikBaru.setNoPelan("");
                        hakmilikBaru.setNoPu(hMasterTitle.getNoLitho());
                        hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                        hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                        hakmilikBaru.setPbt(hMasterTitle.getPbt());
                        hakmilikBaru.setCukai(BigDecimal.ZERO);
                        hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                        hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                        hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                        hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                        hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                        hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                        hakmilikBaru.setNoVersiDhde(1);
                        hakmilikBaru.setNoVersiDhke(1);

                        hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                        if (kodNegeri.equals("04")) {
                            hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                        }
                        BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                        if (bdn != null) {
                            hakmilikBaru.setBadanPengurusan(bdn);
                        }
                        hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                        hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                        hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                        hakmilikBaru.setNoBangunan(noBangunan);
                        hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                        hakmilikBaru.setNoTingkat("1");
                        hakmilikBaru.setNoPetak("1");
                        hakmilikBaru.setMenara(null);
                        hakmilikBaru.setMezanin(null);
                        hakmilikBaru.setUnitSyer(BigDecimal.valueOf(permohonanBangunan.getSyerBlok()));
                        int jumlahunit1 = 0;
                        for (PermohonanBangunan mb2 : senaraiBangunan) {
                            jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                        }
                        hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                        if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                            hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                        }
                        hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                        hakmilikBaru.setInfoAudit(ia);
                        /*INSERT INTO MOHON HAKMILIK*/
                        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                        mohonHakmilikBaru.setPermohonan(p);
                        mohonHakmilikBaru.setInfoAudit(ia);
                        listMohonHakmilikBaru.add(mohonHakmilikBaru);
                        listHakmilikBaru.add(hakmilikBaru);

                        listHakmilikPetakAksesori = null;
                        
                        /*INSERT INTO HAKMILIK SEBELUM*/
                        if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                           SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                           HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                           hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                           hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                           hakmilikSebelumBaru.setInfoAudit(ia);
                           hakmiliksebelumlist.add(hakmilikSebelumBaru);
                        } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                  List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                  List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                      for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                           SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                           HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                           hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                           hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                           hakmilikSebelumBaru.setInfoAudit(ia);
                                           hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                       }
                        }

//                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                            if (hpk == null || hpk.getAktif() == 'T') {
//                                continue;
//                            }

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
                            hpb.setAktif('Y');
                            hpb.setPihak(hpk.getPihak());
                            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                            hpb.setWargaNegara(hpk.getWargaNegara());
                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                            if (kodNegeri.equals("04")) {
                                hpb.setNama(hpk.getPihak().getNama());
                                hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                hpb.setPoskod(hpk.getPihak().getPoskod());
                                hpb.setNegeri(hpk.getPihak().getNegeri());
                            }
                            hpb.setInfoAudit(ia);
                            lhp.add(hpb);
                        }

                    } else {
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = kodUOMDAO.findById("M");
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("0");
                        hakmilik.setNoPetak("0");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());

                        List<BangunanTingkat> bt = strService.findByIDBangunan(permohonanBangunan.getIdBangunan());
                        List<BangunanPetak> bp = null;
                        if (bt.size() > 0) {
                            bp = strService.findByPetakByIDTingkat(bt.get(0).getIdTingkat());
                        }
                        for (BangunanPetak bpetak : bp) {
                            String noBangunanL = StringUtils.leftPad(bpetak.getNama(), 5, '0');
                            String idHakmilikBaru = hMasterTitle.getIdHakmilik() + noBangunanL;
                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                            Hakmilik hakmilikBaru = new Hakmilik();
                            if (sbu != null) {
                                hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                            }
                            String khm = hMasterTitle.getKodHakmilik().getKod();
                            LOG.debug("SAVING NEW ID HAKMILIK : " + hakmilikBaru);
                            hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                            hakmilikBaru.setNoFail(null);
                            hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                            hakmilikBaru.setLot(hMasterTitle.getLot());
                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                            hakmilikBaru.setKodSumber("ET");
                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                            ksh.setKod("T");
                            hakmilikBaru.setKodStatusHakmilik(ksh);
                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                            hakmilikBaru.setKodUnitLuas(kodUOM);
                            int syorblock = permohonanBangunan.getSyerBlok();
                            hakmilikBaru.setUnitSyer(BigDecimal.valueOf(bpetak.getSyer()));
                            hakmilikBaru.setLuas(bpetak.getLuas());
                            hakmilikBaru.setCukai(BigDecimal.ZERO);
                            hakmilikBaru.setCukaiSebenar(BigDecimal.ZERO);
                            hakmilikBaru.setNoHakmilik(hMasterTitle.getNoHakmilik());
                            hakmilikBaru.setNoPelan(bpetak.getPabPetak());
                            hakmilikBaru.setNoPu(hMasterTitle.getNoLitho());
                            hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                            hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                            if (bdn != null) {
                                hakmilikBaru.setBadanPengurusan(bdn);
                            }
                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                            hakmilikBaru.setNoBangunan(null);
                            hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                            hakmilikBaru.setNoTingkat(null);
                            hakmilikBaru.setNoPetak(bpetak.getNama());
                            hakmilikBaru.setNoVersiDhde(1);
                            hakmilikBaru.setNoVersiDhke(1);
                            int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                            hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                            if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                            }
                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                            hakmilikBaru.setInfoAudit(ia);
                            /*INSERT INTO MOHON HAKMILIK*/
                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                            mohonHakmilikBaru.setPermohonan(p);
                            mohonHakmilikBaru.setInfoAudit(ia);
                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                            listHakmilikBaru.add(hakmilikBaru);
                            
                            /*INSERT INTO HAKMILIK SEBELUM*/
                                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                hakmilikSebelumBaru.setInfoAudit(ia);
                                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                                List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                                List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                                for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                    hakmilikSebelumBaru.setInfoAudit(ia);
                                                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                                }
                                            }
                            
//                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                if (hpk == null || hpk.getAktif() == 'T') {
//                                    continue;
//                                }

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
                                hpb.setAktif('Y');
                                hpb.setPihak(hpk.getPihak());
                                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                hpb.setWargaNegara(hpk.getWargaNegara());
                                hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                                if (kodNegeri.equals("04")) {
                                    hpb.setNama(hpk.getPihak().getNama());
                                    hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                    hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                    hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                    hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                    hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                    hpb.setPoskod(hpk.getPihak().getPoskod());
                                    hpb.setNegeri(hpk.getPihak().getNegeri());
                                }
                                hpb.setInfoAudit(ia);
                                lhp.add(hpb);
                            }
                        }
                    }
                } else {
                    KodKategoriBangunan kodKatBngn = permohonanBangunan.getKodKategoriBangunan();
                    List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat = 1;
                    if (kodKatBngn.getKod().equals("B") || kodKatBngn.getKod().equals("P")) {
                        if (!senaraiTingkat.isEmpty()) {
                            LOG.debug("---Bangunans---");
                            for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                                List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();
                                int countPetak = 1;
                                for (BangunanPetak bangunanPetak : senaraiPetak) {
                                    String noBangunan = permohonanBangunan.getNama();
                                    String noTingkat = String.valueOf(countTingkat);
                                    String noPetak = bangunanPetak.getNama();
                                    KodUOM kodUOM = bangunanPetak.getLuasUom();
                                    if (kodUOM == null) {
                                        kodUOM = kodUOMDAO.findById("M");
                                    }
                                    Hakmilik hakmilik = new Hakmilik();
                                    hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilik.setDaerah(hMasterTitle.getDaerah());
                                    hakmilik.setNoBangunan(noBangunan);
                                    hakmilik.setNoTingkat(noTingkat);
                                    hakmilik.setNoPetak(noPetak);
                                    hakmilik.setKodKategoriBangunan(kodKatBngn);
                                    int jumlahunit = 0;
                                    for (PermohonanBangunan mb : senaraiBangunan) {
                                        jumlahunit = jumlahunit + permohonanBangunan.getSyerBlok();
                                    }
                                    hakmilik.setJumlahUnitSyer(jumlahunit);
                                    hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                                    hakmilik.setCukai(BigDecimal.ZERO);
                                    hakmilik.setCukaiSebenar(BigDecimal.ZERO);
                                    String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
                                    LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                                    Hakmilik hakmilikBaru = new Hakmilik();
                                    if (sbu != null) {
                                        hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                                    }
                                    String khm = hMasterTitle.getKodHakmilik().getKod();
                                    String s1 = idHakmilikBaru.substring(0, 25 + khm.length());
                                    LOG.debug("SAVING NEW ID HAKMILIK : " + s1);
                                    hakmilikBaru.setIdHakmilik(s1);
                                    hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                                    hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                                    hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                                    hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                                    hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                                    hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                                    hakmilikBaru.setLot(hMasterTitle.getLot());
                                    hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                                    hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                                    hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                                    hakmilikBaru.setTarikhDaftar(new java.util.Date());
                                    hakmilikBaru.setKodSumber("ET");
                                    KodStatusHakmilik ksh = new KodStatusHakmilik();
                                    ksh.setKod("T");
                                    hakmilikBaru.setKodStatusHakmilik(ksh);
                                    hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                                    hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                                    hakmilikBaru.setKodUnitLuas(kodUOM);
                                    if (bangunanPetak.getLuas() != null) {
                                        BigDecimal luasPetak = bangunanPetak.getLuas();
                                        hakmilikBaru.setLuas(luasPetak);
                                    } else {
                                        hakmilikBaru.setLuas(BigDecimal.ZERO);
                                    }
                                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 19, (idHakmilikBaru.length() - 19) + 8);
                                    hakmilikBaru.setNoHakmilik(noHakmilik);
                                    hakmilikBaru.setNoPelan(bangunanPetak.getPabPetak());
                                    hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                                    hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                                    hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                                    hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                                    hakmilikBaru.setPbt(hMasterTitle.getPbt());
                                    hakmilikBaru.setCukai(BigDecimal.ZERO);
                                    hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                                    hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                                    hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                                    hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                                    hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                                    hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                                    hakmilikBaru.setNoVersiDhde(1);
                                    hakmilikBaru.setNoVersiDhke(1);
                                    hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                                    if (kodNegeri.equals("04")) {
                                        hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                                    }
                                    BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                                    if (bdn != null) {
                                        hakmilikBaru.setBadanPengurusan(bdn);
                                    }
                                    hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                                    hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                                    hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                                    hakmilikBaru.setNoBangunan(noBangunan);
                                    hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                                    hakmilikBaru.setNoTingkat(noTingkat);
                                    hakmilikBaru.setNoPetak(noPetak);
                                    if (bangunanPetak.getSyer() != null) {
                                        BigDecimal unitSyer = new BigDecimal(bangunanPetak.getSyer());
                                        hakmilikBaru.setUnitSyer(unitSyer);
                                    }
                                    int jumlahunit1 = 0;
                                    for (PermohonanBangunan mb2 : senaraiBangunan) {
                                        jumlahunit1 = jumlahunit1 + mb2.getSyerBlok();
                                    }
                                    hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                                    if (permohonanBangunan.getKodKegunaanBangunan() != null) {
                                        hakmilikBaru.setKodKegunaanBangunan(permohonanBangunan.getKodKegunaanBangunan());
                                    }
                                    hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                                    hakmilikBaru.setInfoAudit(ia);
                                    /*INSERT INTO MOHON HAKMILIK*/
                                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                                    mohonHakmilikBaru.setPermohonan(p);
                                    mohonHakmilikBaru.setInfoAudit(ia);
                                    listMohonHakmilikBaru.add(mohonHakmilikBaru);
                                    listHakmilikBaru.add(hakmilikBaru);

                                    /*INSERT INTO HAKMILIK SEBELUM*/
                                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                hakmilikSebelumBaru.setInfoAudit(ia);
                                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                                List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                                List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                                for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                    hakmilikSebelumBaru.setInfoAudit(ia);
                                                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                                }
                                            }
                                    
                                    List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                                    for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                        HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                        hpa.setHakmilik(hakmilikBaru);
                                        hpa.setCawangan(hakmilikBaru.getCawangan());
                                        hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                        hpa.setNama(bangunanPetakAksesori.getNama());
                                        hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                        hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                        hpa.setInfoAudit(ia);
                                        listHakmilikPetakAksesori.add(hpa);
                                    }

//                                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                                    for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                        if (hpk == null || hpk.getAktif() == 'T') {
//                                            continue;
//                                        }

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
                                        hpb.setAktif('Y');
                                        hpb.setPihak(hpk.getPihak());
                                        hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                                        hpb.setWargaNegara(hpk.getWargaNegara());
                                        hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                                        hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                                        if (kodNegeri.equals("04")) {
                                            hpb.setNama(hpk.getPihak().getNama());
                                            hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                            hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                            hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                            hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                            hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                            hpb.setPoskod(hpk.getPihak().getPoskod());
                                            hpb.setNegeri(hpk.getPihak().getNegeri());
                                        }
                                        hpb.setInfoAudit(ia);
                                        lhp.add(hpb);
                                    }
                                    countPetak += 1;
                                }
                                countTingkat += 1;
                            }
                        }
                    } else if ("04".equals(conf.getProperty("kodNegeri"))) {
                        LOG.debug("---LandParcels---");
                        String noBangunan = permohonanBangunan.getNama();
                        KodUOM kodUOM = kodUOMDAO.findById("M");
                        Hakmilik hakmilik = new Hakmilik();
                        hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilik.setDaerah(hMasterTitle.getDaerah());
                        hakmilik.setNoBangunan(noBangunan);
                        hakmilik.setNoTingkat("0");
                        hakmilik.setNoPetak("0");
                        hakmilik.setKodKategoriBangunan(kodKatBngn);
                        int jumlahunit = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilik.setJumlahUnitSyer(jumlahunit);
                        hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                        String idHakmilikBaru = generateStrataLandParcel(kodNegeri, null, hakmilik);
                        LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                        Hakmilik hakmilikBaru = new Hakmilik();
                        if (sbu != null) {
                            hakmilikBaru.setNoSkim(String.valueOf(sbu.getIdBadan()));
                        }
                        String khm = hMasterTitle.getKodHakmilik().getKod();
                        LOG.debug("SAVING NEW ID HAKMILIK : " + hakmilikBaru);
                        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                        hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                        hakmilikBaru.setCawangan(hMasterTitle.getCawangan());
                        hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                        hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                        hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                        hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                        hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                        hakmilikBaru.setLot(hMasterTitle.getLot());
                        hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                        hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                        hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());
//                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                        hakmilikBaru.setKodSumber("ET");
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hakmilikBaru.setKodStatusHakmilik(ksh);
                        hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                        hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                        hakmilikBaru.setKodUnitLuas(kodUOM);
                        int syorblock = permohonanBangunan.getSyerBlok();
                        hakmilikBaru.setUnitSyer(BigDecimal.valueOf(syorblock));
                        String luas = permohonanBangunan.getUntukKegunaanLain();
                        BigDecimal luas1 = new BigDecimal(luas);
                        if (permohonanBangunan.getUntukKegunaanLain() != null) {
                            hakmilikBaru.setLuas(luas1);
                        } else {
                            hakmilikBaru.setLuas(BigDecimal.ZERO);
                        }
                        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 14, (idHakmilikBaru.length() - 14) + 8);
                        hakmilikBaru.setNoHakmilik(noHakmilik);
                        hakmilikBaru.setNoPelan("");
                        hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                        hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                        hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                        hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                        hakmilikBaru.setPbt(hMasterTitle.getPbt());
                        hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                        hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                        hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                        hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                        hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                        hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                        hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                        BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
                        if (bdn != null) {
                            hakmilikBaru.setBadanPengurusan(bdn);
                        }
                        hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                        hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                        hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                        hakmilikBaru.setNoBangunan(noBangunan);
                        hakmilikBaru.setKodKategoriBangunan(kodKatBngn);
                        hakmilikBaru.setNoTingkat("0");
                        hakmilikBaru.setNoPetak("0");
                        hakmilikBaru.setNoVersiDhde(1);
                        hakmilikBaru.setNoVersiDhke(1);
                        int jumlahunit1 = permohonanBangunan.getPermohonanSkim().getJumlahUnitSyer().intValue();
                        hakmilikBaru.setJumlahUnitSyer(jumlahunit1);
                        hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                        hakmilikBaru.setInfoAudit(ia);
                        /*INSERT INTO MOHON HAKMILIK*/
                        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                        mohonHakmilikBaru.setPermohonan(p);
                        mohonHakmilikBaru.setInfoAudit(ia);
                        listMohonHakmilikBaru.add(mohonHakmilikBaru);
                        listHakmilikBaru.add(hakmilikBaru);
                        
                        /*INSERT INTO HAKMILIK SEBELUM*/
                                            if (p.getKodUrusan().getKod().equals("PHPC")&& p.getKodUrusan().getKod().equals("PSBS") ) {
                                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                hakmilikSebelumBaru.setInfoAudit(ia);
                                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                                                List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                                                List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                                                for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                                    hakmilikSebelumBaru.setInfoAudit(ia);
                                                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                                }
                                            }
                        
//                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = strService.findHPBeforeHTB(hMasterTitle.getIdHakmilik());
                        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
//                                if (hpk == null || hpk.getAktif() == 'T') {
//                                    continue;
//                                }
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
                            hpb.setAktif('Y');
                            hpb.setPihak(hpk.getPihak());
                            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                            hpb.setWargaNegara(hpk.getWargaNegara());
                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                            if (kodNegeri.equals("04")) {
                                hpb.setNama(hpk.getPihak().getNama());
                                hpb.setAlamat1(hpk.getPihak().getAlamat1());
                                hpb.setAlamat2(hpk.getPihak().getAlamat2());
                                hpb.setAlamat3(hpk.getPihak().getAlamat3());
                                hpb.setAlamat4(hpk.getPihak().getAlamat4());
                                hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                                hpb.setPoskod(hpk.getPihak().getPoskod());
                                hpb.setNegeri(hpk.getPihak().getNegeri());
                            }
                            hpb.setInfoAudit(ia);
                            lhp.add(hpb);
                        }
                    }
                }
            }

            if (!listHakmilikBaru.isEmpty()) {
                for (Hakmilik hakmilik : listHakmilikBaru) {
                    regService.simpanHakmilik(hakmilik);
                }
            }

            if (!listMohonHakmilikBaru.isEmpty()) {
                for (HakmilikPermohonan hp : listMohonHakmilikBaru) {
                    regService.simpanMohonHakmilik(hp);
                }
            }

            if (!lhp.isEmpty()) {
                regService.simpanHakmilikPihak(lhp);
            }
            if (listHakmilikPetakAksesori != null) {
                if (!listHakmilikPetakAksesori.isEmpty()) {
                    regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
                }
            }
            
            if (!hakmiliksebelumlist.isEmpty()) {
                regService.simpanHakmilikSebelum(hakmiliksebelumlist);
            }
        }
    }

    public BadanPengurusan findBdn(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.strata.BadanPengurusan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (BadanPengurusan) q.uniqueResult();

    }

    public FolderDokumenDAO getFolderDokumenDAO() {
        return folderDokumenDAO;
    }

    public void setFolderDokumenDAO(FolderDokumenDAO folderDokumenDAO) {
        this.folderDokumenDAO = folderDokumenDAO;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public StrataPtService getStrataService() {
        return strataService;
    }

    public void setStrataService(StrataPtService strataService) {
        this.strataService = strataService;
    }

    List kandunganFolderByKodDoc(long folderId, String kodDoc) {
        String query = "SELECT b FROM etanah.model.KandunganFolder b where b.folder.folderId = :folderId and b.dokumen.kodDokumen.kod = :kodDoc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("folderId", folderId);
        q.setString("kodDoc", kodDoc);

        return q.list();
    }

    public List<Notis> findNotisByKod(String kod, String idPermohonan, Long id) {
        String query = "SELECT b FROM etanah.model.Notis b where b.kodNotis.kod = :kod";
        if (StringUtils.isNotBlank(idPermohonan)) {
            query += " and b.permohonan.idPermohonan = :idPermohonan";
        }
        if (id != null) {
            query += " and b.hakmilikPermohonan.id = :id";
        }

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        if (StringUtils.isNotBlank(idPermohonan)) {
            q.setString("idPermohonan", idPermohonan);
        }
        if (id != null) {
            q.setLong("id", id);
        }

        return q.list();
    }

    public void reportGen(Permohonan p, List<String> t, List<String> t2) {
        String dokumenPath = conf.getProperty("document.path");
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        Dokumen d = null;
        KodDokumen kd = null;
        LOG.info("ID PERMOHONAN : " + p.getIdPermohonan());

        for (int i = 0; i < t.size(); i++) {
            String gen = t.get(i);
            String code = t2.get(i);
            kd = kodDokumenDAO.findById(code);
            LOG.info("KOD DOKUMEN = " + code);
            d = saveOrUpdateDokumen(p.getFolderDokumen(), kd, p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            LOG.info("PATH = " + path);
            reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

        }
    }

    public Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        LOG.info("PENGGUNA = " + pengguna);
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc != null) {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        } else {
            LOG.debug("new Document");
            doc = new Dokumen();
            LOG.info("PENGGUNA : " + pengguna);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        if (id.contains("STR")) {
            String[] koddoc = {"4KDHK", "4ADHD", "4ADHK"};
            if (ArrayUtils.contains(koddoc, kd.getKod())) {
                doc.setTajuk(kd.getKod() + "-" + id);
            } else {
                doc.setTajuk(kd.getNama() + "-" + id);
            }
        } else {
            doc.setTajuk(kd.getKod() + "-" + id);
        }
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        LOG.info("====In saving Dokumen====");
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
        LOG.info("====In saving KandunganFolder====");
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    public void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan createPermohonanBaru(Permohonan permohonan, KodUrusan ku, Pengguna pengguna) throws WorkflowException, StaleObjectException, Exception {
        KodCawangan caw = permohonan.getCawangan();
        Permohonan p = new Permohonan();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        FolderDokumen fd = new FolderDokumen();
        long idFolder = Long.parseLong(tarikh);
//            fd = permohonan.getFolderDokumen();
        fd.setTajuk("TEST_" + tarikh); // TODO
        fd.setInfoAudit(ia);
        fd.setFolderId(idFolder);
        folderDokumenDAO.save(fd);
        p.setStatus("TA");
        if (ku.getJabatan().getKod().equals("20")) {
            //STRATA
            p.setIdPermohonan(idGenMohon.generate(conf.getProperty("kodNegeri"), caw, ku));
//            LOG.info(idGenMohon.generate(conf.getProperty("kodNegeri"), caw, ku));
        } else {
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
//            LOG.info(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
        }
        p.setCawangan(pengguna.getKodCawangan());
        p.setKodUrusan(ku);
        p.setFolderDokumen(fd);
        if (permohonan != null) {
            p.setPermohonanSebelum(permohonan);
            p.setPenyerahNama(permohonan.getPenyerahNama());
            p.setKodPenyerah(permohonan.getKodPenyerah());
            p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
            p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
            if (permohonan.getPenyerahAlamat3() != null) {
                p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
            }

            if (permohonan.getPenyerahAlamat4() != null) {
                p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
            }
            p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
            p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
            p.setIdWorkflow(p.getKodUrusan().getIdWorkflowIntegration());

        }
        p.setInfoAudit(ia);
        //permohonanDAO.save(p);
        savePermohonan(p);
        WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                p.getKodUrusan().getNama());
        return p;
    }

    public Permohonan createPermohonanBaru2(Permohonan permohonan, KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik) throws WorkflowException, StaleObjectException, Exception {
        //KodCawangan caw = permohonan.getCeawangan();
        KodCawangan caw = senaraiHakmilik.get(0).getCawangan();
        Permohonan p = new Permohonan();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        FolderDokumen fd = new FolderDokumen();
        long idFolder = Long.parseLong(tarikh);
//            fd = permohonan.getFolderDokumen();
        fd.setTajuk("TEST_" + tarikh); // TODO
        fd.setInfoAudit(ia);
        fd.setFolderId(idFolder);
        folderDokumenDAO.save(fd);
        p.setStatus("TA");

        if (ku.getJabatan().getKod().equals("20")) {
            //STRATA
            p.setIdPermohonan(idGenMohon.generate(conf.getProperty("kodNegeri"), caw, ku));
//            LOG.info(idGenMohon.generate(conf.getProperty("kodNegeri"), caw, ku));
        } else {
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
//            LOG.info(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
        }
        p.setCawangan(caw);
        p.setKodUrusan(ku);
        p.setFolderDokumen(fd);
        if (permohonan != null) {
            p.setPermohonanSebelum(permohonan);
            p.setPenyerahNama(permohonan.getPenyerahNama());
            p.setKodPenyerah(permohonan.getKodPenyerah());
            p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
            p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
            if (permohonan.getPenyerahAlamat3() != null) {
                p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
            }

            if (permohonan.getPenyerahAlamat4() != null) {
                p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
            }
            p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
            p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
            p.setIdWorkflow(p.getKodUrusan().getIdWorkflowIntegration());

        }
        p.setInfoAudit(ia);
        //permohonanDAO.save(p);
        savePermohonan(p);
        WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                p.getKodUrusan().getNama());
        return p;
    }

    public String getNegeri() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            negeri = "Melaka";
        } else {
            negeri = "Negeri Sembilan";
        }
        return negeri;
    }

    public List<PelaksanaWaran> getListPerlaksana(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PelaksanaWaran b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    @Transactional
    public PelaksanaWaran savePelaksana(PelaksanaWaran pelaksanaWaran) {
        pelaksanaWaran = pelaksanaWaranDAO.saveOrUpdate(pelaksanaWaran);
        return pelaksanaWaran;
    }

    /*
     * ExampleOne eo = new ExampleOne();
     String add = "http://bris4:9001/reports/rwservlet";
     InetAddress address = null;
     CharSequence s = add.subSequence(0, add.length());
    
    
     try {
     address = InetAddress.getByName(eo.sub(eo.getIntegerValue(add)));
     System.out.println("1: "+eo.replaceDNS(address.getHostAddress(), add));
     } catch (UnknownHostException e) {
     System.exit(2);
     }
     *
     */

    public String removeHttp(String s) {
//        String remove = "https://";
//        int i;
//        String result = "";
//        i = s.indexOf(remove);
//        if (i != -1) {
//            result = s.substring(0, i);
//            result = result + s.substring(i + remove.length());
//            s = result;
//        }
        String[] pp = s.split("//");
        return pp[pp.length - 1];
    }

    public String sub(String aa) {
        String result = "";
        int i;
        i = aa.indexOf(":");
        if (i != -1) {
            result = aa.substring(0, i);
//            result = result + aa.substring(i + aa.length());
            aa = result;
        }
        return aa;
    }

    public String replaceDNS(String rep, String ori) throws UnknownHostException {
        String a = "";
        CharSequence s = getIP(ori).subSequence(0, getIP(ori).length());
        CharSequence as = rep.subSequence(0, rep.length());
        a = ori.replace(s, as);
        LOG.info("dah ok : " + a);
        return a;
    }

    public String getIP(String dns) throws UnknownHostException {
        String ip = null;
        ip = (sub(removeHttp(dns)));
        return ip;
    }

    public List<Waran> findWaran(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Waran b where b.idPermohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Waran findWaran(String idPermohonan, String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Waran b where b.idPermohonan.idPermohonan = :idPermohonan "
                + "and b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (Waran) q.uniqueResult();
    }

    @Transactional
    public Waran saveWaran(Waran waran) {
        return waranDAO.saveOrUpdate(waran);
    }

    @Transactional
    public WaranPihak saveWaranPihak(WaranPihak waranPihak) {
        return waranPihakDAO.saveOrUpdate(waranPihak);
    }

//    public List<PermohonanWaranItem> findSenaraiItemWaran(String idPermohonan, long id) {
//        String query = "SELECT b FROM etanah.model.PermohonanWaranItem b where b.idPermohonan.idPermohonan = :idPermohonan and b.hakmilikPermohonan.id =:id";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("idPermohonan", idPermohonan);
//        q.setLong("id", id);
//        return q.list();
//    }
    public List<PermohonanWaranItem> findSenaraiItemWaran(String idPermohonan, Long id) {
        String query = "SELECT b FROM etanah.model.PermohonanWaranItem b where b.idPermohonan.idPermohonan = :idPermohonan and b.hakmilikPermohonan.id = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("id", id);
        return q.list();
    }

    @Transactional
    public PermohonanWaranItem saveMohonWaranItem(PermohonanWaranItem mohonWaranItem) {
        return permohonanWaranItemDAO.saveOrUpdate(mohonWaranItem);
    }

    @Transactional
    public Notis saveNotis(Notis notis1) {
        return notisDAO.saveOrUpdate(notis1);
    }

    @Transactional
    public NotisButiran saveNotisButiran(NotisButiran notisButiran1) {
        return notisButiranDAO.saveOrUpdate(notisButiran1);
    }

    public NotisButiran findNotiButiranByNotis(Notis notis1) {
        String query = "SELECT b FROM etanah.model.NotisButiran b where b.notis.idNotis = :idNotis";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idNotis", notis1.getIdNotis());
        return (NotisButiran) q.uniqueResult();
    }

    public WaranPihak findWaranPihak(long idWaran, long idPihak) {
        String query = "SELECT b FROM etanah.model.WaranPihak b where b.waran.idWaran = :idWaran "
                + "and b.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idWaran", idWaran);
        q.setLong("idPihak", idPihak);
        return (WaranPihak) q.uniqueResult();
    }

    Dokumen saveDokumen(String dokumenPath, FileBean fileToBeUpload, long folderId, InfoAudit ia, Permohonan permohonan, KodDokumen kodD) throws Exception {
        System.out.println("-----------savedok----------");
        Dokumen doc = new Dokumen();
        KodKlasifikasi kodk = new KodKlasifikasi();
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        doc.setTajuk(kodD.getNama());
        doc.setInfoAudit(ia);
        doc.setKodDokumen(kodD);
        doc.setKlasifikasi(klasifikasiAm);
        doc.setNoVersi("1.0");
        doc = dokumenDAO.saveOrUpdate(doc);
        String fileName = fileToBeUpload.getFileName();
        DMSUtil dmsUtil = new DMSUtil();
        FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
        fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
        String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
        updatePathDokumen(fizikalPath, doc, fileToBeUpload.getContentType());

        updateFolderDoc(ia, doc, folderId);
        return doc;
    }

    public List<PermohonanPengguna> findListBykod(String kod, String kodCaw) {
        String query = "SELECT b FROM etanah.model.PermohonanPengguna b where b.status.kod = :kod and b.kodCawangan.kod = :kodCaw ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    public List<PermohonanPengguna> findListBykod(String kod, String kodM, String idPengguna) {
        String query = "SELECT b FROM etanah.model.PermohonanPengguna b where (b.status.kod = :kod "
                + "or b.status.kod = :kodM) "
                + "and b.penyelia.idPengguna = :idPengguna";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        q.setString("kodM", kodM);
        q.setString("idPengguna", idPengguna);
        return q.list();
    }
    //added by zadirul for Pelan Lokasi GIS
    //find Dokumen by noRujukan where noRujukan = idPermohonan

    public Dokumen findDokumenByNoRujukan(String noRujukan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Dokumen d where d.noRujukan = :noRujukan");
        q.setString("noRujukan", noRujukan);
        return (Dokumen) q.uniqueResult();
    }

    @Transactional
    public void savePermohonan(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }
    //Afham - tmbh method baru utk carian pelan b1/b2 

    public Dokumen savePelanB1B2(String dokumenPath, Long idFolder, InfoAudit ia, Permohonan permohonan, String jenisDok, String jenisPelan, FileInputStream fis) throws Exception {

        //find Dokumen by noRujukan where noRujukan = idPermohonan
        Dokumen doc = new Dokumen();
        KodDokumen kd = kodDokumenDAO.findById(jenisDok);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        doc.setTajuk(kd.getNama());
        doc.setInfoAudit(ia);
        doc.setKodDokumen(kd);
        doc.setKlasifikasi(klasifikasiAm);
        doc.setNoVersi("1.0");
        doc.setNoRujukan(permohonan.getIdPermohonan());
        doc = dokumenDAO.saveOrUpdate(doc);

        PelanGIS p = pelupusanService.findPelanByIdPermohonanAndJenisPelan(permohonan.getIdPermohonan(), jenisPelan);

        String fileName = permohonan.getIdPermohonan() + "-" + p.getJenisPelan();
        DMSUtil dmsUtil = new DMSUtil();
        FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
        fileUtil.saveFile(fileName, fis);
        String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
        updatePathDokumen(fizikalPath, doc, "image/tiff");
        updateFolderDoc(ia, doc, idFolder);

        return doc;
    }

    //    added by Nurashidah for get Aduan task
//    find Aduan by status 
    public List<AduanPortal> searchNewAduanListByStatus(String status) {

        String query = "Select ad from etanah.model.AduanPortal ad WHERE status LIKE :status";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("status", status);

        return q.list();

    }
}

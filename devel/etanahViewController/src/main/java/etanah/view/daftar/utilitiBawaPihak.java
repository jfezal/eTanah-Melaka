package etanah.view.daftar;

import able.stripes.JSP;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import able.stripes.AbleActionBean;
import etanah.dao.AkaunDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanDokumenDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.WakilKuasaDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.DokumenKewanganSiri;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPerhubunganHakmilik;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import java.util.List;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.service.RegService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.HttpCache;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.WakilKuasa;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.model.KandunganFolder;
import etanah.service.KertasHakmilikService;
import etanah.service.AkaunService;
import etanah.view.stripes.hasil.KutipanHasilService;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.model.PermohonanDokumen;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanPihak;
import etanah.model.Pemohon;
import etanah.model.Transaksi;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.model.HakmilikUrusan;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PemohonService;

/**
 *
 * @author wan.fairul
 *
 */
@HttpCache(allow = false)
@UrlBinding("/daftar/bawaPihak")
public class utilitiBawaPihak extends AbleActionBean {

    private Permohonan permohonan;
    private Permohonan permohonan2;
    private PermohonanDokumen permohonanDokumen;
    private WakilKuasa wakilKuasa;
    private FolderDokumen folderDokumen;
    private List<KandunganFolder> kandunganFolderList;
    private KandunganFolder kandunganFolder;
    @Inject
    KertasHakmilikService kertasHakmilikService;
    @Inject
    PendaftaranSuratKuasaService pendaftaranSuratKuasaService;
    @Inject
    private PermohonanDokumenDAO permohonanDokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PendaftaranSuratKuasaService suratkuasaService;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private WakilKuasaDAO WakilKuasaDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FasaPermohonanService fService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    RegService regService;
    @Inject
    private HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    private PermohonanRujukanLuarDAO permohonanRujukanLuarDao;
    @Inject
    private PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    DokumenService dokumenService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    Akaun akaun;
    @Inject
    KutipanHasilService hasilService;
    @Inject
    AkaunService akaunService;
    @Inject
    private AkaunDAO akaunDAO;
//    @Inject
//    KodKlasifikasiDAO kodKlasifikasiDAO;
    private boolean flag = false;
    private boolean flag2 = false;
    private boolean flag3 = false;
    private String kodUrusan;
    private String kodNegeri;
    private String idHakmilik;
    private Pengguna pengguna;
    private String taskId;
    private String idHakmilikBaru;
    private String idPermohonan;
    private Hakmilik hb;
    private List<FasaPermohonan> fasapermohonan;
    private List<PermohonanDokumen> senaraiPermohonanDokumen;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakmilikPermohonanKemaskini;
    private static final Logger LOG = Logger.getLogger(UtilitiBetulHakmilik.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private boolean form = false;
    private String stage;
    private String wKuasa;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private Long idFolder;
    private List<KandunganFolder> ListFolderDokumen;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<Dokumen> listNoRuj = new ArrayList<Dokumen>();
    private List<Dokumen> listDokumen;
    private Dokumen dokumen;
    private String kodDok;
    private long idDok;
    private String idPermohonanAsal;
    private String noAkaun;
    private String noAkaun2;
    private String noAkaunBaru;
    private String idWakil;
    private String idWakilLama;
    private String idMh;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikUrusanService hUService;
    @Inject
    private PemohonService pemohonService;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Hakmilik getHb() {
        return hb;
    }

    public void setHb(Hakmilik hb) {
        this.hb = hb;
    }

    public String getIdHakmilikBaru() {
        return idHakmilikBaru;
    }

    public void setIdHakmilikBaru(String idHakmilikBaru) {
        this.idHakmilikBaru = idHakmilikBaru;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanKemaskini() {
        return hakmilikPermohonanKemaskini;
    }

    public void setHakmilikPermohonanKemaskini(List<HakmilikPermohonan> hakmilikPermohonanKemaskini) {
        this.hakmilikPermohonanKemaskini = hakmilikPermohonanKemaskini;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<FasaPermohonan> getFasapermohonan() {
        return fasapermohonan;
    }

    public void setFasapermohonan(List<FasaPermohonan> fasapermohonan) {
        this.fasapermohonan = fasapermohonan;
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public Permohonan getPermohonan2() {
        return permohonan2;
    }

    public void setPermohonan2(Permohonan permohonan2) {
        this.permohonan2 = permohonan2;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/utiliti/bawa_pihak.jsp");
    }

    public Resolution kemaskiniPenyerah() throws WorkflowException {

        idPermohonan = getContext().getRequest().getParameter("idMohon");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Permohonan mohon = permohonanDAO.findById(idPermohonan);

        InfoAudit ia = new InfoAudit();
        pengguna = ctx.getUser();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        String penyerahNama = getContext().getRequest().getParameter("permohonan.penyerahNama");
        String penyerahAlamat1 = getContext().getRequest().getParameter("permohonan.penyerahAlamat1");
        String penyerahAlamat2 = getContext().getRequest().getParameter("permohonan.penyerahAlamat2");
        String penyerahAlamat3 = getContext().getRequest().getParameter("permohonan.penyerahAlamat3");
        String penyerahAlamat4 = getContext().getRequest().getParameter("permohonan.penyerahAlamat4");
        String penyerahPoskod = getContext().getRequest().getParameter("permohonan.penyerahPoskod");

        if (mohon != null) {
            mohon.setPenyerahNama(penyerahNama);
            mohon.setPenyerahAlamat1(penyerahAlamat1);
            mohon.setPenyerahAlamat2(penyerahAlamat2);
            mohon.setPenyerahAlamat3(penyerahAlamat3);
            mohon.setPenyerahAlamat4(penyerahAlamat4);
            mohon.setPenyerahPoskod(penyerahPoskod);
            mohon.setInfoAudit(ia);
            permohonanService.saveOrUpdate(mohon);
        }

        return checkPermohonan();

    }

    public Resolution simpanWakilKuasa() throws WorkflowException {
        LOG.info("wakilKuasa.getIdWakil() : " + wakilKuasa.getIdWakil());
        LOG.info("wakilKuasa.getIdWakil() : " + permohonan.getIdPermohonan());
        idPermohonanAsal = permohonan.getIdPermohonan();

        InfoAudit ia = new InfoAudit();
        wakilKuasa = WakilKuasaDAO.findById(wakilKuasa.getIdWakil());
        if (wakilKuasa.getIdWakil() != null) {
            permohonan2 = permohonanService.findById(idPermohonanAsal);
            if (!permohonan2.getKodUrusan().getKod().equals("SWDB")) {
                permohonan = permohonanService.findById(wakilKuasa.getIdWakil());
                if (permohonan.getKodUrusan().getKod().equals("SW")) {
                    KodDokumen kd = kodDokumenDAO.findById("SWD");
                    LOG.info("KodDokumen : " + kd.getKod());
                    LOG.info("KodDokumen : " + kd.getNama());
                    LOG.info("idFolder : " + idFolder);

                    permohonan = permohonanService.findById(wakilKuasa.getIdWakil());
                    Dokumen d = new Dokumen();

                    ia = d.getInfoAudit();
                    Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    InfoAudit info = peng.getInfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());

                    d.setKodDokumen(kd);
                    d.setPermohonan(permohonan);
                    d.setTajuk("Surat Kuasa Wakil Daftar");
                    d.setNoRujukan(wakilKuasa.getIdWakil());
                    d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                    d.setNoVersi("1.0");
                    d.setInfoAudit(info);
                    dokumenDAO.saveOrUpdate(d);

                    if (!d.equals(null)) {
                        idDok = d.getIdDokumen();
                    }
                    LOG.info("id dokumen : " + d.getIdDokumen());
                }
                permohonan = permohonanService.findById(wakilKuasa.getIdWakil());
                if (permohonan.getKodUrusan().getKod().equals("SA")) {

                    KodDokumen kd = kodDokumenDAO.findById("SAB");
                    LOG.info("KodDokumen : " + kd.getKod());
                    LOG.info("KodDokumen : " + kd.getNama());
                    LOG.info("idFolder : " + idFolder);
                    permohonan = permohonanService.findById(wakilKuasa.getIdWakil());
                    Dokumen d = new Dokumen();

                    ia = d.getInfoAudit();
                    Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    InfoAudit info = peng.getInfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());

                    d.setKodDokumen(kd);
                    d.setPermohonan(permohonan);
                    d.setTajuk("Surat Amanah Baru");
                    d.setNoRujukan(wakilKuasa.getIdWakil());
                    d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                    d.setNoVersi("1.0");
                    d.setInfoAudit(info);
                    dokumenDAO.saveOrUpdate(d);

                    if (!d.equals(null)) {
                        idDok = d.getIdDokumen();
                    }
                }
                permohonan = permohonanService.findById(wakilKuasa.getIdWakil());
                if (permohonan.getKodUrusan().getKod().equals("SB")) {

                    KodDokumen kd = kodDokumenDAO.findById("SBD");
                    LOG.info("KodDokumen : " + kd.getKod());
                    LOG.info("KodDokumen : " + kd.getNama());
                    LOG.info("idFolder : " + idFolder);

                    permohonan = permohonanService.findById(wakilKuasa.getIdWakil());
                    Dokumen d = new Dokumen();
//                senaraiPermohonanDokumen = pendaftaranSuratKuasaService.findPermohonanDokumenByIdPermohonan(wakilKuasa.getIdWakil());

//                if (senaraiPermohonanDokumen.isEmpty()) {
                    ia = d.getInfoAudit();
                    Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    InfoAudit info = peng.getInfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());

                    d.setKodDokumen(kd);
                    d.setPermohonan(permohonan);
                    LOG.info("d.getpermohonan : " + d.getPermohonan().getIdPermohonan());
                    d.setTajuk("Surat Kebenaran Daftar");
                    d.setNoRujukan(wakilKuasa.getIdWakil());
                    d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                    d.setNoVersi("1.0");
                    d.setInfoAudit(info);
                    dokumenDAO.saveOrUpdate(d);
                    if (!d.equals(null)) {
                        idDok = d.getIdDokumen();
                    }
//                }
                }
            } else if (permohonan2.getKodUrusan().getKod().equals("SWDB")) {

                KodDokumen kd = kodDokumenDAO.findById("SWD");
                LOG.info("KodDokumen : " + kd.getKod());
                LOG.info("KodDokumen : " + kd.getNama());
                LOG.info("idFolder : " + idFolder);

                permohonan = permohonanService.findById(wakilKuasa.getIdWakil());
                Dokumen d = new Dokumen();
//                senaraiPermohonanDokumen = pendaftaranSuratKuasaService.findPermohonanDokumenByIdPermohonan(wakilKuasa.getIdWakil());

//                if (senaraiPermohonanDokumen.isEmpty()) {
                ia = d.getInfoAudit();
                Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                InfoAudit info = peng.getInfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());

                d.setKodDokumen(kd);
//                d.setPermohonan(permohonan);
//                LOG.info("d.getpermohonan : " + d.getPermohonan().getIdPermohonan());
                d.setTajuk("SW " + permohonan2.getIdPermohonan());
                d.setNoRujukan(wakilKuasa.getIdWakil());
                d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                d.setNoVersi("1.0");
                d.setInfoAudit(info);
                dokumenDAO.saveOrUpdate(d);
                if (!d.equals(null)) {
                    idDok = d.getIdDokumen();
                }
//                }
            }

//            if (!senaraiPermohonanDokumen.isEmpty()) {
//                addSimpleError(wakilKuasa.getIdWakil() + " Telah digunakan Untuk Permohonan Yang Lain.Sila Masukan Surat Kebenaran Yang Lain");
//            }
        }
        UpdateFolderDok(idPermohonanAsal);

        flag3 = true;

        return new JSP("daftar/utiliti/carian_wakilkuasa.jsp").addParameter("popup", "true");
    }

    private void UpdateFolderDok(String idPermohonan) {
        InfoAudit ia = new InfoAudit();

        idPermohonan = getContext().getRequest().getParameter("idMohon");
        permohonan = permohonanDAO.findById(idPermohonanAsal);
        LOG.info("idPermohonan : " + permohonan.getIdPermohonan());

        KodDokumen kd = kodDokumenDAO.findById("SAB");
        Dokumen dok = dokumenDAO.findById(idDok);
        permohonan = permohonanService.findById(idPermohonanAsal);
        idFolder = permohonan.getFolderDokumen().getFolderId();
        LOG.info("id Folder 3 : " + idFolder);
        FolderDokumen kandfol = kandunganFolderService.findFolder(idFolder);

        KandunganFolder kf = new KandunganFolder();
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        ia = peng.getInfoAudit();

        InfoAudit info = peng.getInfoAudit();

        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        kf.setInfoAudit(ia);
        kf.setDokumen(dok);
        kf.setFolder(kandfol);
        kandunganFolderService.saveOrUpdate(kf);
        idFolder = kf.getIdKandunganFolder();
        LOG.info("id Folder 3 : " + idFolder);

        addSimpleMessage("Kemasukan Surat Kuasa Berjaya");
    }

    public Resolution popapKemaskini() {

        idPermohonan = getContext().getRequest().getParameter("idMohon");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        permohonan = permohonanDAO.findById(idPermohonan);

        return new JSP("common/edit_penyerah_utiliti.jsp").addParameter("popup", "true");

    }

    public Resolution popapWakilKuasa() {

        idPermohonan = getContext().getRequest().getParameter("idMohon");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        permohonan = permohonanDAO.findById(idPermohonan);
        flag = true;

        return new JSP("daftar/utiliti/carian_wakilkuasa.jsp").addParameter("popup", "true");

    }

    public Resolution popapKemaskiniWakilKuasa() {

        idPermohonan = getContext().getRequest().getParameter("idMohon");
        idWakilLama = getContext().getRequest().getParameter("idWakil");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        permohonan = permohonanService.findById(idPermohonan);

        if (!permohonan.equals(null)) {
            idFolder = permohonan.getFolderDokumen().getFolderId();
            FolderDokumen folder = kandunganFolderService.findFolder(idFolder);
            senaraiKandungan = kandunganFolderService.findByIdFolder(folder);
            for (KandunganFolder kf : senaraiKandungan) {
//                kf.getDokumen().getIdDokumen();
                if (kf.getDokumen().getNoRujukan() != null) {
                    wakilKuasa = WakilKuasaDAO.findById(kf.getDokumen().getNoRujukan());
                    if (wakilKuasa != null) {
                        listNoRuj.add(kf.getDokumen());
                    }

                }

            }
        }
        return new JSP("daftar/utiliti/kemaskini_hakmilik_permohonan.jsp").addParameter("popup", "true");

    }

    public Resolution deletewakilkuasa() throws WorkflowException {

        idPermohonan = getContext().getRequest().getParameter("idMohon");
        idWakilLama = getContext().getRequest().getParameter("idWakil");
        long idDok = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        permohonan = permohonanService.findById(idPermohonan);

        kandunganFolder = kandunganFolderService.findByIdDokumen(idDok);
        senaraiPermohonanDokumen = pendaftaranSuratKuasaService.findPermohonanDokumenByIdPermohonan(idWakilLama);

        for (PermohonanDokumen pd : senaraiPermohonanDokumen) {
            pendaftaranSuratKuasaService.deleteTentukan(pd);
        }

        if (kandunganFolder != null) {
            kandunganFolderDAO.delete(kandunganFolder);
            if (kandunganFolder.getDokumen() != null) {
                dokumenService.delete(kandunganFolder.getDokumen());
            }
        }

        return checkPermohonan();

    }

    public Resolution checkWakilKuasa() throws WorkflowException {

        LOG.debug("-------------***PERMOHONAN***--------------" + wKuasa);

//        LOG.debug("-------------***ID-PERMOHONAN***--------------" + wKuasa);
        if (wKuasa == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan Wakil Kuasa");
            flag = true;
            return new JSP("daftar/utiliti/carian_wakilkuasa.jsp").addParameter("popup", "true");
        }

        wakilKuasa = WakilKuasaDAO.findById(wKuasa);
        if (wakilKuasa == null) {
            flag = true;
            addSimpleError("Permohonan " + wKuasa + " tidak wujud.");

        }
        if (wakilKuasa != null) {
            addSimpleMessage("Permohonan " + wKuasa + " wujud dan boleh disimpan");
            flag2 = true;

        }

        return new JSP("daftar/utiliti/carian_wakilkuasa.jsp").addParameter("popup", "true");

    }

    public Resolution checkPermohonan() throws WorkflowException {

        LOG.debug("-------------***PERMOHONAN***--------------" + permohonan);
//        if (permohonan == null) {
//            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
//            return showForm();
//        }
        //idPermohonan = permohonan.getIdPermohonan();
        LOG.debug("-------------***ID-PERMOHONAN***--------------" + idPermohonan);
        if (idPermohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return showForm();
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan == null) {
            System.out.print("Permohonan " + idPermohonan + " tidak wujud.");
            addSimpleError("Permohonan " + idPermohonan + " tidak wujud.");
            return showForm();
        } else {
            fasapermohonan = fService.findStatus(idPermohonan);
            if (fasapermohonan == null) {
                addSimpleError("Permohonan " + idPermohonan + " tidak wujud");
                return showForm();
            }
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            for (Task t : l) {
                stage = t.getSystemAttributes().getStage();
            }
            if (stage != null) {
                if (stage.equalsIgnoreCase("keputusan")) {
                    addSimpleError("Permohonan " + idPermohonan + " tidak dibolehkan untuk baiki perserahan hakmilik");
                    return showForm();
                } else {
                    hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                    form = true;
                }
            } else {
//                addSimpleError("Permohonan " + idPermohonan + " tidak wujud.");
//                return showForm();
                hakmilikPermohonanList = regService.searchMohonHakmilikByIdMohon(idPermohonan);
                form = true;
            }
            idPermohonanAsal = permohonan.getIdPermohonan();
        }
        permohonan = permohonanService.findById(idPermohonanAsal);
        if (!permohonan.equals(null)) {
            idFolder = permohonan.getFolderDokumen().getFolderId();
            FolderDokumen folder = kandunganFolderService.findFolder(idFolder);
            senaraiKandungan = kandunganFolderService.findByIdFolder(folder);
            for (KandunganFolder kf : senaraiKandungan) {
//                kf.getDokumen().getIdDokumen();
                if (kf.getDokumen().getNoRujukan() != null) {
                    wakilKuasa = WakilKuasaDAO.findById(kf.getDokumen().getNoRujukan());
                    if (wakilKuasa != null) {
                        listNoRuj.add(kf.getDokumen());
                    }

                }

            }
        }

        return showForm();

    }

    public Resolution cariPermohonan() {
        LOG.debug("edit hakmilik permohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.debug("-------------permohonan--------------" + permohonan);
        hakmilikPermohonanKemaskini = new ArrayList<HakmilikPermohonan>();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        if (StringUtils.isNotBlank(idHakmilik)) {
            LOG.debug("idHakmilik = " + idHakmilik);

            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                if (hp == null) {
                    continue;
                }
                if (hp.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                    LOG.debug("id hakmilik sama");
                    hakmilikPermohonanKemaskini.add(hp);
                }
            }
        } else {
            hakmilikPermohonanKemaskini = hakmilikPermohonanList;
        }
//        Hakmilik hm = hakmilikDAO.findById(idHakmilik);
//        akaun = hm.getAkaunCukai();
//        LOG.debug("no akaun" + akaun.getNoAkaun());
//        noAkaun = akaun.getNoAkaun();

        return showForm();
    }

    public Resolution janaPihak() {

//        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        hakmilikPermohonanList = regService.searchMohonHakmilikByIdMohon(permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = getContext().getRequest().getParameter("idHakmilikLama");
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            List<HakmilikUrusan> senaraiHakmilikUrusan = hUService.findHakmilikUrusanUrusniagaAktif(hp.getHakmilik().getIdHakmilik());
            for (HakmilikUrusan hu : senaraiHakmilikUrusan) {
//                List<PermohonanPihak> senaraiMohonPihak = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hu.getIdPerserahan(), hp.getHakmilik().getIdHakmilik());
//                List<Pemohon> senaraiPemohon = regService.findPemohonByIdPermohonanAndHakmilik(hu.getIdPerserahan(), hp.getHakmilik().getIdHakmilik());
                List<PermohonanPihak> PermohonanPihak = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hu.getIdPerserahan(), idHakmilik);
                List<Pemohon> pemohon = pemohonService.findPemohonByIdPermohonanAndHakmilik(hu.getIdPerserahan(), idHakmilik);
                List<Pemohon> pemohon2 = pemohonService.findPemohonByIdPermohonanAndHakmilik(hu.getIdPerserahan(), hp.getHakmilik().getIdHakmilik());
                List<PermohonanPihak> PermohonanPihak2 = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hu.getIdPerserahan(), hp.getHakmilik().getIdHakmilik());
                if (hu.getKodUrusan().getKod().equals("GD") || hu.getKodUrusan().getKod().equals("PMT")) {
                    if (pemohon2.size() >= 0) {
                        InfoAudit info = peng.getInfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        if (pemohon2.size() <= 0) {
                            for (Pemohon pmh : pemohon) {
                                Pemohon _pmh = new Pemohon();
                                _pmh.setPermohonan(pmh.getPermohonan());
                                _pmh.setHakmilik(hp.getHakmilik());
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
                        }
                    }
                    if (PermohonanPihak2.size() >= 0) {
                        InfoAudit info = peng.getInfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        if (PermohonanPihak2.size() <= 0) {
                            for (PermohonanPihak pp : PermohonanPihak) {
                                PermohonanPihak mohonPihakBaru = new PermohonanPihak();
                                mohonPihakBaru.setAlamat(pp.getAlamat());
                                mohonPihakBaru.setAlamatSurat(pp.getAlamatSurat());
                                mohonPihakBaru.setCawangan(pp.getCawangan());
                                mohonPihakBaru.setHakmilik(hp.getHakmilik());
                                mohonPihakBaru.setInfoAudit(info);
                                mohonPihakBaru.setJenis(pp.getJenis());
                                mohonPihakBaru.setJenisPengenalan(pp.getJenisPengenalan());
                                mohonPihakBaru.setJumlahPembilang(pp.getJumlahPembilang());
                                mohonPihakBaru.setJumlahPenyebut(pp.getJumlahPenyebut());
                                mohonPihakBaru.setNama(pp.getNama());
                                mohonPihakBaru.setNoPengenalan(pp.getNoPengenalan());
                                mohonPihakBaru.setNoRujukan(pp.getNoRujukan());
                                mohonPihakBaru.setPermohonan(pp.getPermohonan());
                                mohonPihakBaru.setPihak(pp.getPihak());
                                mohonPihakBaru.setPihakCawangan(pp.getPihakCawangan());
                                mohonPihakBaru.setSyerBersama(pp.getSyerBersama());
                                if (pp.getSyerPembilang() != null) {
                                    mohonPihakBaru.setSyerPembilang(pp.getSyerPembilang());
                                }
                                if (pp.getSyerPenyebut() != null) {
                                    mohonPihakBaru.setSyerPenyebut(pp.getSyerPenyebut());
                                }
                                mohonPihakBaru.setWargaNegara(pp.getWargaNegara());
                                permohonanPihakService.saveOrUpdate(mohonPihakBaru);
                            }
                        }
                    }

                } else if (hu.getKodUrusan().getKod().equals("KVST") || (hu.getKodUrusan().getKod().equals("KVSS"))) {
                    if (PermohonanPihak.size() >= 0) {
                        InfoAudit info = peng.getInfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        if (PermohonanPihak2.size() <= 0) {
                            for (PermohonanPihak pp : PermohonanPihak) {
                                PermohonanPihak mohonPihakBaru = new PermohonanPihak();
                                mohonPihakBaru.setAlamat(pp.getAlamat());
                                mohonPihakBaru.setAlamatSurat(pp.getAlamatSurat());
                                mohonPihakBaru.setCawangan(pp.getCawangan());
                                mohonPihakBaru.setHakmilik(hp.getHakmilik());
                                mohonPihakBaru.setInfoAudit(info);
                                mohonPihakBaru.setJenis(pp.getJenis());
                                mohonPihakBaru.setJenisPengenalan(pp.getJenisPengenalan());
                                mohonPihakBaru.setJumlahPembilang(pp.getJumlahPembilang());
                                mohonPihakBaru.setJumlahPenyebut(pp.getJumlahPenyebut());
                                mohonPihakBaru.setNama(pp.getNama());
                                mohonPihakBaru.setNoPengenalan(pp.getNoPengenalan());
                                mohonPihakBaru.setNoRujukan(pp.getNoRujukan());
                                mohonPihakBaru.setPermohonan(pp.getPermohonan());
                                mohonPihakBaru.setPihak(pp.getPihak());
                                mohonPihakBaru.setPihakCawangan(pp.getPihakCawangan());
                                mohonPihakBaru.setSyerBersama(pp.getSyerBersama());
                                if (pp.getSyerPembilang() != null) {
                                    mohonPihakBaru.setSyerPembilang(pp.getSyerPembilang());
                                }
                                if (pp.getSyerPenyebut() != null) {
                                    mohonPihakBaru.setSyerPenyebut(pp.getSyerPenyebut());
                                }
                                mohonPihakBaru.setWargaNegara(pp.getWargaNegara());
                                permohonanPihakService.saveOrUpdate(mohonPihakBaru);
                            }
                        }
                    }
                }
            }
        }
        addSimpleMessage("Kemaskini Berjaya");
        return showForm();
//        return new JSP("daftar/utiliti/kemaskini_hakmilik_permohonan.jsp").addParameter("popup", "true");
    }

    public Resolution saveAkaun() {

        String[] senaraiHakmilikBaru = getContext().getRequest().getParameterValues("idHakmilikBaru");
        idHakmilik = getContext().getRequest().getParameter("hakmilikLama");
        noAkaun = getContext().getRequest().getParameter("noAkaunBaru");
        noAkaunBaru = getContext().getRequest().getParameter("noAkauanLama");
//        permohonan = permohonanDAO.findById(idPermohonan);
        List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
        akaun = akaunService.findById(noAkaunBaru);

        if (akaun.getNoAkaun() != null) {
            addSimpleError("No Akaun Baru Yang Dimasukan Telah Wujud. Masukan Semula No Akaun Baru");
        } else {
            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            Akaun ak = new Akaun();
            ak.setNoAkaun(noAkaunBaru);
            akaunDAO.update(ak);

        }

        return new JSP("daftar/utiliti/kemaskini_hakmilik_permohonan.jsp").addParameter("popup", "true");
    }

    public Resolution saveWk() {

        idWakil = getContext().getRequest().getParameter("wakilBaru");

        LOG.info("777777777777777777777 " + permohonan.getIdPermohonan());
        idWakilLama = getContext().getRequest().getParameter("idWakil");
        LOG.info("777777777777777777777 " + idWakilLama);

        if (!permohonan.equals(null)) {

            String query = "SELECT distinct d.idDokumen "
                    + "FROM etanah.model.Permohonan p, etanah.model.Dokumen d, etanah.model.KandunganFolder f "
                    + "WHERE p.folderDokumen.folderId = f.folder.folderId "
                    + "AND p.idPermohonan = :idMohon "
                    + "AND d.idDokumen = f.dokumen.idDokumen "
                    + "AND d.noRujukan = :noRuj";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("idMohon", permohonan.getIdPermohonan());
            q.setString("noRuj", idWakilLama);
            Long idDokumen = (Long) q.uniqueResult();

            LOG.info("------------------------------------- " + idDokumen);
            wakilKuasa = WakilKuasaDAO.findById(idWakil);
            if (wakilKuasa.getIdWakil() != null) {
                Dokumen dok = dokumenDAO.findById(idDokumen);
                if (dok != null) {
                    dok.setNoRujukan(idWakil);
                    dokumenService.saveOrUpdate(dok);
                    addSimpleMessage(wakilKuasa.getIdWakil() + " Telah berjaya disimpan");
                }
            } else {
                addSimpleError(wakilKuasa.getIdWakil() + "tiada dalam pengkalan data");
            }
        }

        return new JSP("daftar/utiliti/kemaskini_hakmilik_permohonan.jsp").addParameter("popup", "true");
    }

    public Resolution saveHakmilik() {

        String[] senaraiHakmilikBaru = getContext().getRequest().getParameterValues("idHakmilikBaru");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("hakmilikLama");
        permohonan = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        List<HakmilikPermohonan> senaraiHakmilikTmp = new ArrayList<HakmilikPermohonan>();
        List<PermohonanRujukanLuar> PermohonanRujukanLuarTmp = new ArrayList<PermohonanRujukanLuar>();
        InfoAudit ia = new InfoAudit();
        LOG.debug("size = " + senaraiHakmilikBaru.length);

        if (senaraiHakmilikBaru.length > 0) {
            //           int i = 0;

            for (String id : senaraiHakmilikBaru) {
                LOG.debug("id hakmilik baru = " + id);
                if (StringUtils.isBlank(id)) {
                    continue;
                }
                Hakmilik hakmilik = hakmilikDAO.findById(id);
                if (hakmilik == null) {
                    continue;
                }

                HakmilikPermohonan hp = regService.searchMohonHakmilikObject(idHakmilik, idPermohonan);

//                HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
                ia = hp.getInfoAudit();
                ia.setTarikhKemaskini(new Date());
                ia.setDiKemaskiniOleh(pengguna);
//                LOG.debug("=============id hakmilik  ============ " + getIdHakmilik());
                LOG.debug("=============id hakmilik  ============ " + hp.getHakmilik().getIdHakmilik());
                hp.setHakmilik(hakmilik);
//                LOG.debug("=============id hakmilik  ============ " + getIdHakmilik());
                LOG.debug("=============id hakmilik  ============ " + hp.getHakmilik().getIdHakmilik());
                hp.setInfoAudit(ia);
                senaraiHakmilikTmp.add(hp);
//                i++;
                List<PermohonanRujukanLuar> senaraiMrl = regService.searchMohonRujLuarByIdMohonAndIdHakmilik(idPermohonan, idHakmilik);
//                Hakmilik hakmilikB = hakmilikDAO.findById(id);
                for (PermohonanRujukanLuar mrl : senaraiMrl) {
                    mrl.setHakmilik(hakmilik);
                    permohonanRujukanLuarService.saveOrUpdate(mrl);
//                    PermohonanRujukanLuarTmp.add(mrl);

                }
//                permohonanRujukanLuarService.saveOrUpdate(PermohonanRujukanLuarTmp);
            }

            hakmilikPermohonanService.saveHakmilikPermohonan(senaraiHakmilikTmp);

        }
//        List<PermohonanRujukanLuar> senaraiMrl = regService.searchMohonRujLuarByIdMohonAndIdHakmilik(idPermohonan, idHakmilik);
//        if (senaraiMrl.size() > 0) {
//            for (String hakmilikBaru : senaraiHakmilikBaru) {
//                Hakmilik hakmilikB = hakmilikDAO.findById(hakmilikBaru);
//                for (PermohonanRujukanLuar mrl : senaraiMrl) {
//                    mrl.setHakmilik(hakmilikB);
//                    permohonanRujukanLuarService.saveOrUpdate(mrl);
//                }
//
//            }
//        }
        form = true;
        addSimpleMessage("kemasukan Berjaya");
        return new JSP("daftar/utiliti/kemaskini_hakmilik_permohonan.jsp").addParameter("popup", "true");
    }

    public Resolution deleteHakmilik() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        idMh = getContext().getRequest().getParameter("idMh");
        LOG.debug("Deleting idHakmilik :" + idHakmilik);
        LOG.debug("Deleting idMh :" + idMh);

        if (idHakmilik != null) {
//            HakmilikPermohonan hp = regService.searchMohonHakmilikObject(idHakmilik, idPermohonan);
            HakmilikPermohonan hp = regService.searchMohonHakmilikByIdMhIdMohon(Long.parseLong(idMh), idPermohonan);

            if (hp != null) {
                regService.deleteMohonHakmilik(hp);
            }
        }

        msg = "Data Telah Berjaya diHapuskan";
        addSimpleMessage(msg);
        form = true;
        return showForm();
    }

    public Resolution simpanHakmilik() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        LOG.debug("IDHakmilik :" + idHakmilik);
        LOG.debug("IDHakmilik yg dicari : " + idHakmilikBaru);

        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            if (hp.getHakmilik().getIdHakmilik().equals(idHakmilikBaru)) {
                LOG.debug("id hakmilik sama");
                error = "Id hakmilik sama";
                addSimpleError(error);
                form = true;
                return showForm();
            }
        }

        if (idHakmilikBaru != null) {
            hb = hakmilikDAO.findById(idHakmilikBaru);

            if (hb == null) {
                error = "Tiada Hakmilik Dijumpai";
                addSimpleError(error);
                form = true;

            } else {

                LOG.debug("saving hakmilik");
                LOG.debug("idHakmilikBaru : " + idHakmilikBaru);
                Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                InfoAudit info = peng.getInfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp.setHakmilik(hb);
                hp.setPermohonan(permohonan);
                //set kod tangung ismen K = Lot menguasai
                KodPerhubunganHakmilik kph = new KodPerhubunganHakmilik();

                kph.setKod("K");
                hp.setHubunganHakmilik(kph);
                hp.setInfoAudit(info);
                regService.simpanMohonHakmilik(hp);

                msg = "Kemasukan Data Berjaya";
                addSimpleMessage(msg);
                form = true;
            }

        }
        return showForm();

    }

    public Resolution generateAkuanPenerimaan() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            List<Dokumen> doklist = dokumenService.findDokList(idPermohonan, "UNKN1");
            LOG.info("--- doklist.size() :" + doklist.size());
            if (doklist.size() > 1) {
//      DO SOMETHING
                LOG.info("more then one");
            } else {
                // Update Akuan Penerimaan Lama - Batal
                Dokumen d1 = dokumenService.findDok(idPermohonan, "UNKN1");
                if (d1 != null) {
                    /*  List<HakmilikPermohonan> senarai = p.getSenaraiHakmilik();
                     for (HakmilikPermohonan hp : senarai) {
                     if (hp.getDokumen1() != null && hp.getDokumen1().getIdDokumen() == d1.getIdDokumen()) {
                     hp.setDokumen1(null);
                     }
                     if (hp.getDokumen2() != null && hp.getDokumen2().getIdDokumen() == d1.getIdDokumen()) {
                     hp.setDokumen2(null);
                     }
                     if (hp.getDokumen3() != null && hp.getDokumen3().getIdDokumen() == d1.getIdDokumen()) {
                     hp.setDokumen3(null);
                     }
                     if (hp.getDokumen4() != null && hp.getDokumen4().getIdDokumen() == d1.getIdDokumen()) {
                     hp.setDokumen4(null);
                     }
                     if (hp.getDokumen5() != null && hp.getDokumen5().getIdDokumen() == d1.getIdDokumen()) {
                     hp.setDokumen5(null);
                     }
                     hakmilikPermohonanService.saveOrUpdateWithoutConnection(hp);
                     }
                     * */
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    d1.setInfoAudit(ia);
                    d1.setTajuk("Akuan Penerimaan (Batal) -" + permohonan.getIdPermohonan());
                    dokumenDAO.update(d1);
                }
            }
            // Generate Akuan Penerimaan Baru
            String gen1 = "";
            String code1 = "";
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;
            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                gen1 = "HSLResitAkuanPenerimaan002_MLK.rdf";
            } else {
                gen1 = "HSLResitAkuanPenerimaan002.rdf";
            }
            code1 = "UNKN1";
            kd = kodDokumenDAO.findById(code1);
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

        }
        form = true;
        return showForm();
    }

    public Resolution generateResitBayaran() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        String idDokumen = "";
        Dokumen d = null;
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            List<Dokumen> doklist = dokumenService.findDokList(idPermohonan, "RBY");
            LOG.info("--- doklist.size() :" + doklist.size());
            if (doklist.size() > 0) {
                //      DO SOMETHING
                Dokumen d1 = dokumenService.findDok(idPermohonan, "RBY");
                d = d1;
                if (d1 != null) {
                    System.out.println("-------------------------------------------------");
                    idDokumen = d1.getIdDokumen() + "";
                    System.out.println("idDokumen : " + idDokumen);
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    d1.setInfoAudit(ia);
                    d1.setPerihal("Jana Semula Dengan ID Hakmilik baru.");
//                    d1.setTajuk("Resit Bayaran (Batal) -" + permohonan.getIdPermohonan());
                    dokumenDAO.update(d1);
                }
                LOG.info("more then one");
            }
            // Generate Akuan Penerimaan Baru
            String gen1 = "";
            String path = "";
            String dokumenPath = conf.getProperty("document.path");

            String s = "SELECT DISTINCT a.dokumenKewangan.idDokumenKewangan FROM etanah.model.Transaksi a WHERE a.permohonan.idPermohonan = :id_mohon ";
            Query q = sessionProvider.get().createQuery(s);
            q.setString("id_mohon", permohonan.getIdPermohonan());
            String resitNo = (String) q.uniqueResult();

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                gen1 = "HSLResitUrusanTanah_MLK.rdf";
            } else {
                gen1 = "HSLResitUrusanTanah_MLK.rdf";
            }
//            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
            path = "RESIT" + File.separator + idDokumen;
            reportUtil.generateReport(gen1, new String[]{"p_id_kew_dok"},
                    new String[]{resitNo}, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

        }
        form = true;
        return showForm();
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
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
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
//        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk("Akuan Penerimaan (Baru) -" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
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
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public WakilKuasa getWakilKuasa() {
        return wakilKuasa;
    }

    public void setWakilKuasa(WakilKuasa wakilKuasa) {
        this.wakilKuasa = wakilKuasa;
    }

    public PendaftaranSuratKuasaService getSuratkuasaService() {
        return suratkuasaService;
    }

    public void setSuratkuasaService(PendaftaranSuratKuasaService suratkuasaService) {
        this.suratkuasaService = suratkuasaService;
    }

    public String getwKuasa() {
        return wKuasa;
    }

    public void setwKuasa(String wKuasa) {
        this.wKuasa = wKuasa;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public WakilKuasaDAO getWakilKuasaDAO() {
        return WakilKuasaDAO;
    }

    public void setWakilKuasaDAO(WakilKuasaDAO WakilKuasaDAO) {
        this.WakilKuasaDAO = WakilKuasaDAO;
    }

    public RegService getRegService() {
        return regService;
    }

    public void setRegService(RegService regService) {
        this.regService = regService;
    }

    public SemakDokumenService getSemakDokumenService() {
        return semakDokumenService;
    }

    public void setSemakDokumenService(SemakDokumenService semakDokumenService) {
        this.semakDokumenService = semakDokumenService;
    }

    public Long getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(Long idFolder) {
        this.idFolder = idFolder;
    }

    public List<Dokumen> getListDokumen() {
        return listDokumen;
    }

    public void setListDokumen(List<Dokumen> listDokumen) {
        this.listDokumen = listDokumen;
    }

    public List<KandunganFolder> getKandunganFolderList() {
        return kandunganFolderList;
    }

    public void setKandunganFolderList(List<KandunganFolder> kandunganFolderList) {
        this.kandunganFolderList = kandunganFolderList;
    }

    public KertasHakmilikService getKertasHakmilikService() {
        return kertasHakmilikService;
    }

    public void setKertasHakmilikService(KertasHakmilikService kertasHakmilikService) {
        this.kertasHakmilikService = kertasHakmilikService;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public KandunganFolderService getKandunganFolderService() {
        return kandunganFolderService;
    }

    public void setKandunganFolderService(KandunganFolderService kandunganFolderService) {
        this.kandunganFolderService = kandunganFolderService;
    }

    public KandunganFolder getKandunganFolder() {
        return kandunganFolder;
    }

    public void setKandunganFolder(KandunganFolder kandunganFolder) {
        this.kandunganFolder = kandunganFolder;
    }

    public String getKodDok() {
        return kodDok;
    }

    public void setKodDok(String kodDok) {
        this.kodDok = kodDok;
    }

    public long getIdDok() {
        return idDok;
    }

    public void setIdDok(long idDok) {
        this.idDok = idDok;
    }

    public String getIdPermohonanAsal() {
        return idPermohonanAsal;
    }

    public void setIdPermohonanAsal(String idPermohonanAsal) {
        this.idPermohonanAsal = idPermohonanAsal;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public List<Dokumen> getListNoRuj() {
        return listNoRuj;
    }

    public void setListNoRuj(List<Dokumen> listNoRuj) {
        this.listNoRuj = listNoRuj;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public AkaunDAO getAkaunDAO() {
        return akaunDAO;
    }

    public void setAkaunDAO(AkaunDAO akaunDAO) {
        this.akaunDAO = akaunDAO;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public String getNoAkaunBaru() {
        return noAkaunBaru;
    }

    public void setNoAkaunBaru(String noAkaunBaru) {
        this.noAkaunBaru = noAkaunBaru;
    }

    public String getIdWakil() {
        return idWakil;
    }

    public void setIdWakil(String idWakil) {
        this.idWakil = idWakil;
    }

    public String getIdWakilLama() {
        return idWakilLama;
    }

    public void setIdWakilLama(String idWakilLama) {
        this.idWakilLama = idWakilLama;
    }

    public PermohonanDokumen getPermohonanDokumen() {
        return permohonanDokumen;
    }

    public void setPermohonanDokumen(PermohonanDokumen permohonanDokumen) {
        this.permohonanDokumen = permohonanDokumen;
    }

    public PendaftaranSuratKuasaService getPendaftaranSuratKuasaService() {
        return pendaftaranSuratKuasaService;
    }

    public void setPendaftaranSuratKuasaService(PendaftaranSuratKuasaService pendaftaranSuratKuasaService) {
        this.pendaftaranSuratKuasaService = pendaftaranSuratKuasaService;
    }

    public PermohonanDokumenDAO getPermohonanDokumenDAO() {
        return permohonanDokumenDAO;
    }

    public void setPermohonanDokumenDAO(PermohonanDokumenDAO permohonanDokumenDAO) {
        this.permohonanDokumenDAO = permohonanDokumenDAO;
    }

    public DokumenService getDokumenService() {
        return dokumenService;
    }

    public void setDokumenService(DokumenService dokumenService) {
        this.dokumenService = dokumenService;
    }

    public List<PermohonanDokumen> getSenaraiPermohonanDokumen() {
        return senaraiPermohonanDokumen;
    }

    public void setSenaraiPermohonanDokumen(List<PermohonanDokumen> senaraiPermohonanDokumen) {
        this.senaraiPermohonanDokumen = senaraiPermohonanDokumen;
    }

    public List<KandunganFolder> getListFolderDokumen() {
        return ListFolderDokumen;
    }

    public void setListFolderDokumen(List<KandunganFolder> ListFolderDokumen) {
        this.ListFolderDokumen = ListFolderDokumen;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag2() {
        return flag2;
    }

    public void setFlag2(boolean flag2) {
        this.flag2 = flag2;
    }

    public boolean isFlag3() {
        return flag3;
    }

    public void setFlag3(boolean flag3) {
        this.flag3 = flag3;
    }

    public String getIdMh() {
        return idMh;
    }

    public void setIdMh(String idMh) {
        this.idMh = idMh;
    }

    public PermohonanRujukanLuarDAO getPermohonanRujukanLuarDao() {
        return permohonanRujukanLuarDao;
    }

    public void setPermohonanRujukanLuarDao(PermohonanRujukanLuarDAO permohonanRujukanLuarDao) {
        this.permohonanRujukanLuarDao = permohonanRujukanLuarDao;
    }

    public PermohonanRujukanLuarService getPermohonanRujukanLuarService() {
        return permohonanRujukanLuarService;
    }

    public void setPermohonanRujukanLuarService(PermohonanRujukanLuarService permohonanRujukanLuarService) {
        this.permohonanRujukanLuarService = permohonanRujukanLuarService;
    }
}

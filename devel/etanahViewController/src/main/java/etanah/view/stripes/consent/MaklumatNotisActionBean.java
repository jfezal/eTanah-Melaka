/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import etanah.dao.PermohonanPerbicaraanKehadiranDAO;
import etanah.model.PermohonanPerbicaraan;
import etanah.model.PermohonanPerbicaraanKehadiran;
import etanah.service.ConsentPtdService;
import java.text.ParseException;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodStatusTerima;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.util.FileUtil;
import etanah.util.DateUtil;
import etanah.view.etanahActionBeanContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/consent/maklumat_notis")
public class MaklumatNotisActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    ConsentPtdService consentService;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private PermohonanPerbicaraanKehadiran perbicaraanKehadiran;
    private String idPermohonan;
    private List<PermohonanPerbicaraanKehadiran> listNotis = new ArrayList<PermohonanPerbicaraanKehadiran>();
    private List<PermohonanPerbicaraanKehadiran> listSuratTangguh = new ArrayList<PermohonanPerbicaraanKehadiran>();
    private List<PermohonanPerbicaraanKehadiran> listSuratTangguhLama = new ArrayList<PermohonanPerbicaraanKehadiran>();
    private List<PenghantarNotis> listPenghantarNotis = new ArrayList<PenghantarNotis>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String idKehadiran;
    FileBean fileToBeUploaded;
    private static final Logger LOG = Logger.getLogger(MaklumatNotisActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private boolean button = false;
    private Pengguna pengguna;
    private PenghantarNotis penghantarNotis;
    private String namaPengahantar;
    private long idDokumen;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("inside showForm()");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_notis.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparan() {
        return new JSP("consent/maklumat_notis.jsp").addParameter("tab", "true");
    }

    public Resolution popupUpload() {
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        LOG.info("idKehadiran :" + idKehadiran);
        return new JSP("consent/muat_naik_notis.jsp").addParameter("popup", "true");
    }

    public Resolution popupPenghantarNotis() {
        return new JSP("consent/penghantarNotis_popup.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("inside rehydrate()");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            FasaPermohonan fasaPermohonan = new FasaPermohonan();

            if (permohonan.getKodUrusan().getKod().equals("BTADT")) {
                fasaPermohonan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage14");
            } else if (permohonan.getKodUrusan().getKod().equals("PMWNA") || permohonan.getKodUrusan().getKod().equals("PMWWA")) {
                fasaPermohonan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage19");
            } else {//all urusan adat
                fasaPermohonan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage6");
            }

            if (fasaPermohonan == null) {
                PermohonanPerbicaraan permohonanPerbicaraan = new PermohonanPerbicaraan();
                permohonanPerbicaraan = consentService.findMohonBicaraByIdMohon(idPermohonan);
                listNotis = consentService.getSenaraiPerbicaraanKehadiranByIdBicara(permohonanPerbicaraan.getIdPerbicaraan());

            } else {

                List<PermohonanPerbicaraan> listPerbicaraanTangguh = consentService.findSenaraiMohonBicaraTangguh(idPermohonan, "TANGGUH");
                listSuratTangguhLama = new ArrayList<PermohonanPerbicaraanKehadiran>();
                for (PermohonanPerbicaraan perBicara : listPerbicaraanTangguh) {
                    if (perBicara.getCatatan().equals("TANGGUH1")) {
                        listNotis = perBicara.getSenaraiKehadiran();
                    } else {
                        listSuratTangguhLama.addAll(perBicara.getSenaraiKehadiran());
                    }
                }

                PermohonanPerbicaraan permohonanSuratTangguh = new PermohonanPerbicaraan();
                permohonanSuratTangguh = consentService.findMohonBicaraNotTangguh(idPermohonan);

                if (permohonanSuratTangguh != null) {
                    listSuratTangguh = consentService.getSenaraiPerbicaraanKehadiranByIdBicara(permohonanSuratTangguh.getIdPerbicaraan());
                }
            }
        }
        listPenghantarNotis = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");
    }

    public Resolution simpan() throws ParseException {

        LOG.info("saving table mohon bicara hadir...");

        if (listSuratTangguh.size() > 0) {
            for (int i = 0; i < listSuratTangguh.size(); i++) {
                perbicaraanKehadiran = listSuratTangguh.get(i);
                KodStatusTerima kodterima = kodStatusTerimaDAO.findById(getContext().getRequest().getParameter("kodStatusTerima" + i));
                KodCaraPenghantaran kodHantar = kodCaraPenghantaranDAO.findById(getContext().getRequest().getParameter("kodCaraPenghantaran" + i));
                if (!getContext().getRequest().getParameter("namaPengahantar" + i).isEmpty()) {
                    PenghantarNotis penghantar = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
                    perbicaraanKehadiran.setPenghantarNotis(penghantar);
                }
                perbicaraanKehadiran.setCaraPenghantaran(kodHantar);
                perbicaraanKehadiran.setStatusTerima(kodterima);
                consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
            }
        } else {
            for (int i = 0; i < listNotis.size(); i++) {
                perbicaraanKehadiran = listNotis.get(i);
                KodStatusTerima kodterima = kodStatusTerimaDAO.findById(getContext().getRequest().getParameter("kodStatusTerima" + i));
                KodCaraPenghantaran kodHantar = kodCaraPenghantaranDAO.findById(getContext().getRequest().getParameter("kodCaraPenghantaran" + i));
                if (!getContext().getRequest().getParameter("namaPengahantar" + i).isEmpty()) {
                    PenghantarNotis penghantar = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
                    perbicaraanKehadiran.setPenghantarNotis(penghantar);
                }
                perbicaraanKehadiran.setCaraPenghantaran(kodHantar);
                perbicaraanKehadiran.setStatusTerima(kodterima);
                consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
            }
        }
        rehydrate();
        listPenghantarNotis = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_notis.jsp").addParameter("tab", "true");

    }

    public Resolution simpanBicaraHadir() throws ParseException {

        String namaPN = getContext().getRequest().getParameter("namaPN");
        String statusSampai = getContext().getRequest().getParameter("statusSampai");
        String caraHantar = getContext().getRequest().getParameter("caraHantar");
        String tarikhHantar = getContext().getRequest().getParameter("tarikhHantar");
        String tarikhTerima = getContext().getRequest().getParameter("tarikhTerima");
        String catatan = getContext().getRequest().getParameter("catatan");
        String row = getContext().getRequest().getParameter("row");

        if (listSuratTangguh.size() > 0) {

            perbicaraanKehadiran = new PermohonanPerbicaraanKehadiran();
            perbicaraanKehadiran = listSuratTangguh.get(Integer.parseInt(row));
            KodStatusTerima kodterima = kodStatusTerimaDAO.findById(statusSampai);
            KodCaraPenghantaran kodHantar = kodCaraPenghantaranDAO.findById(caraHantar);
            perbicaraanKehadiran.setCaraPenghantaran(kodHantar);
            perbicaraanKehadiran.setStatusTerima(kodterima);
            if (!namaPN.isEmpty()) {
                PenghantarNotis penghantar = penghantarNotisDAO.findById(Integer.parseInt(namaPN));
                perbicaraanKehadiran.setPenghantarNotis(penghantar);
            }
            if (!tarikhHantar.isEmpty()) {
                perbicaraanKehadiran.setTarikhHantar(sdf.parse(tarikhHantar));
            }
            if (!tarikhTerima.isEmpty()) {
                perbicaraanKehadiran.setTarikhTerima(sdf.parse(tarikhTerima));
            }
            perbicaraanKehadiran.setCatatanTerima(catatan);
            consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);

        } else {
            perbicaraanKehadiran = new PermohonanPerbicaraanKehadiran();
            perbicaraanKehadiran = listNotis.get(Integer.parseInt(row));
            KodStatusTerima kodterima = kodStatusTerimaDAO.findById(statusSampai);
            KodCaraPenghantaran kodHantar = kodCaraPenghantaranDAO.findById(caraHantar);

            perbicaraanKehadiran.setCaraPenghantaran(kodHantar);
            perbicaraanKehadiran.setStatusTerima(kodterima);

            if (!namaPN.isEmpty()) {
                PenghantarNotis penghantar = penghantarNotisDAO.findById(Integer.parseInt(namaPN));
                perbicaraanKehadiran.setPenghantarNotis(penghantar);
            }
            if (!tarikhHantar.isEmpty()) {
                perbicaraanKehadiran.setTarikhHantar(sdf.parse(tarikhHantar));
            }
            if (!tarikhTerima.isEmpty()) {
                perbicaraanKehadiran.setTarikhTerima(sdf.parse(tarikhTerima));
            }

            perbicaraanKehadiran.setCatatanTerima(catatan);
            consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
        }
        listPenghantarNotis = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");

        return new RedirectResolution("/consent/maklumat_notis?getSenaraiNotis").addParameter("tab", "true");
    }

    public Resolution getSenaraiNotis() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_notis.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPenghantarNotis() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = pengguna.getKodCawangan();
        penghantarNotis.setInfoAudit(infoAudit);
        penghantarNotis.setCawangan(caw);
        penghantarNotis.setAktif('Y');
        consentService.simpanPenghantarNotis(penghantarNotis);
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new JSP("consent/maklumat_notis.jsp").addParameter("tab", "true");
    }

    public Resolution processUploadDoc() {
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = idKehadiran;
        LOG.info("idKehadiran : " + fname);
        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator + du.getDateName(String.valueOf(du.getMonth() + 1)) + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
                Dokumen doc = new Dokumen();
                PermohonanPerbicaraanKehadiran ppk = perbicaraanKehadiranDAO.findById(Long.parseLong(fname));
                if (ppk.getBuktiTerima() != null) {
                    doc = ppk.getBuktiTerima();
                    ia = ppk.getBuktiTerima().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kd);
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = consentService.simpanDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                ia = ppk.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                ppk.setBuktiTerima(dokumenDAO.findById(idDoc));
                ppk.setInfoAudit(ia);
                consentService.simpanPerbicaraanKehadiran(ppk);
                addSimpleMessage("Muat naik fail berjaya.");
                rehydrate();
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        listPenghantarNotis = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");
        return new JSP("consent/muat_naik_notis.jsp").addParameter("popup", "true");
    }

    public Resolution popupScan() {

        LOG.info("idKehadiran :" + idKehadiran);

        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String fname = idKehadiran;
        LOG.info("idKehadiran : " + fname);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try {
            if (p != null && fname != null) {
                Dokumen doc = new Dokumen();
                PermohonanPerbicaraanKehadiran ppk = perbicaraanKehadiranDAO.findById(Long.parseLong(fname));
                if (ppk.getBuktiTerima() != null) {
                    doc = ppk.getBuktiTerima();
                    ia = ppk.getBuktiTerima().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                doc.setKodDokumen(kd);
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat("application/pdf/image/gif");
                doc.setInfoAudit(ia);
                idDokumen = consentService.simpanDokumen(doc);
                LOG.info("idDoc :" + idDokumen);
                // update at DasarTuntutanNotis
                ia = ppk.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                ppk.setBuktiTerima(dokumenDAO.findById(idDokumen));
                ppk.setInfoAudit(ia);
                consentService.simpanPerbicaraanKehadiran(ppk);
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        listPenghantarNotis = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");
        return new JSP("consent/scan_doc_notis.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_notis.jsp").addParameter("tab", "true");
    }

    public Resolution view() {

        String idHadir = getContext().getRequest().getParameter("idHadir");
        LOG.info("idKehadiran : " + idHadir);

//        List<PermohonanPerbicaraanKehadiran> listHadir = consentService.findMohonBicara

        PermohonanPerbicaraanKehadiran bicaraHadir = perbicaraanKehadiranDAO.findById(Long.parseLong(idHadir));
//        LOG.info("listNS : " + listHadir.size());
        if (bicaraHadir.getBuktiTerima() != null) {
            idDokumen = bicaraHadir.getBuktiTerima().getIdDokumen();
        }
        LOG.info("idDokumen : " + idDokumen);
        if (idDokumen == 0) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }

        Dokumen d = dokumenDAO.findById(idDokumen);
        if (d == null || d.getKodDokumen() == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }

        // classification checking
        Pengguna p = ((etanahActionBeanContext) getContext()).getUser();
        if (d.getKlasifikasi() != null && p.getTahapSekuriti().getKod() < d.getKlasifikasi().getKod()) {
            return new ErrorResolution(401, "Pengguna tidak boleh mencapai dokumen ini.");
        }

        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500,
                    "Konfigurasi \"document.path\" tidak dijumpai");
        }

        // log the view
        DokumenCapaian dc = new DokumenCapaian();
        dc.setDokumen(d);
        dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("PD"));
        dc.setAlasan("Paparan Dokumen");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new java.util.Date());
        dc.setInfoAudit(ia);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        dokumenCapaianDAO.save(dc);
        if (d.getBaru() == 'Y') {
            ia = d.getInfoAudit();
            ia.setTarikhKemaskini(new Date());
            ia.setDiKemaskiniOleh(pengguna);
            d.setInfoAudit(ia);
            d.setBaru('T');
            dokumenDAO.update(d);
        }
        tx.commit();

        String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
        File f = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            if (f != null && d.getKodDokumen().getKawalCapaian() == 'Y') {
                if (isDebug) {
                    LOG.debug("creating watermark..");
                }
                boolean createWatermark = true;

                if (d.getKodDokumen().getKod().equalsIgnoreCase("DHKE") || d.getKodDokumen().getKod().equalsIgnoreCase("DHDE") || d.getKodDokumen().getKod().equalsIgnoreCase("PB1KE") || d.getKodDokumen().getKod().equalsIgnoreCase("PB1DE") || d.getKodDokumen().getKod().equalsIgnoreCase("PB2KE") || d.getKodDokumen().getKod().equalsIgnoreCase("PB2DE")) {
                    File signFile = new File(path + ".sig");
                    if (signFile.exists()) {
                        createWatermark = false;
                    }
                }

                if (createWatermark) {
//                    f = createWatermark(fis, path);
//                    fis = new FileInputStream(f);
//                    pdf = FileUtil.createWaterMark(fis);
                    final InputStream is = fis;
                    final String format = d.getFormat();

                    ByteArrayOutputStream bous = FileUtil.createWaterMark(is);

                    InputStream inputStream = new ByteArrayInputStream(bous.toByteArray());

                    return new StreamingResolution(d.getFormat(), inputStream);


//                    return new StreamingResolution(d.getFormat()) {
//                        @Override
//                        public void stream(HttpServletResponse response) throws Exception {
//                            response.setContentType(format);
////                            response.setHeader("Content-disposition","attachment; filename=" + "eTanah.pdf" );
//
//                            ByteArrayOutputStream bous = FileUtil.createWaterMark(is);
//                            bous.writeTo(response.getOutputStream());
//
//                        }
//                    }.setFilename(f.getName());
                }
            }
        } catch (Exception e) {
            LOG.error(e);
            return new ErrorResolution(500,
                    "Fail " + path + " tidak dijumpai");
        }

        getContext().getResponse().setContentType("application/octet-stream");

        return new StreamingResolution(d.getFormat(), fis);

    }

    public Resolution updateNotis() throws ParseException {
        String idBicaraHadir = getContext().getRequest().getParameter("idKehadiran");
        String namaPN = getContext().getRequest().getParameter("namaPN");
        String statusSampai = getContext().getRequest().getParameter("statusSampai");
        String caraHantar = getContext().getRequest().getParameter("caraHantar");
        String tarikhHantar = getContext().getRequest().getParameter("tarikhHantar");
        String tarikhTerima = getContext().getRequest().getParameter("tarikhTerima");
        String catatan = getContext().getRequest().getParameter("catatan");

        LOG.debug("idKehadiran :" + idBicaraHadir);
        LOG.debug("namaPN :" + namaPN);
        LOG.debug("statusSampai :" + statusSampai);
        LOG.debug("caraHantar :" + caraHantar);
        LOG.debug("tarikhHantar :" + tarikhHantar);
        LOG.debug("tarikhTerima :" + tarikhTerima);
        LOG.debug("catatan :" + catatan);

        PermohonanPerbicaraanKehadiran bicaraHadir = perbicaraanKehadiranDAO.findById(Long.parseLong(idBicaraHadir));

        if (!statusSampai.isEmpty()) {
            KodStatusTerima kodterima = kodStatusTerimaDAO.findById(statusSampai);
            bicaraHadir.setStatusTerima(kodterima);
        }

        if (!caraHantar.isEmpty()) {
            KodCaraPenghantaran kodHantar = kodCaraPenghantaranDAO.findById(caraHantar);
            bicaraHadir.setCaraPenghantaran(kodHantar);

            if (!namaPN.isEmpty()) {
                if (caraHantar.equals("PN")) {
                    PenghantarNotis penghantar = penghantarNotisDAO.findById(Integer.parseInt(namaPN));
                    bicaraHadir.setPenghantarNotis(penghantar);
                }
            }
        }

        if (!tarikhHantar.isEmpty()) {
            bicaraHadir.setTarikhHantar(sdf.parse(tarikhHantar));
        }
        if (!tarikhTerima.isEmpty()) {
            bicaraHadir.setTarikhTerima(sdf.parse(tarikhTerima));
        }
        bicaraHadir.setCatatanTerima(catatan);
        consentService.simpanPerbicaraanKehadiran(bicaraHadir);
        listPenghantarNotis = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");

        return new StreamingResolution("text/plain", "1");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public String getNamaPengahantar() {
        return namaPengahantar;
    }

    public void setNamaPengahantar(String namaPengahantar) {
        this.namaPengahantar = namaPengahantar;
    }

    public String getIdKehadiran() {
        return idKehadiran;
    }

    public void setIdKehadiran(String idKehadiran) {
        this.idKehadiran = idKehadiran;
    }

    public List<PermohonanPerbicaraanKehadiran> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<PermohonanPerbicaraanKehadiran> listNotis) {
        this.listNotis = listNotis;
    }

    public List<PermohonanPerbicaraanKehadiran> getListSuratTangguh() {
        return listSuratTangguh;
    }

    public void setListSuratTangguh(List<PermohonanPerbicaraanKehadiran> listSuratTangguh) {
        this.listSuratTangguh = listSuratTangguh;
    }

    public List<PermohonanPerbicaraanKehadiran> getListSuratTangguhLama() {
        return listSuratTangguhLama;
    }

    public void setListSuratTangguhLama(List<PermohonanPerbicaraanKehadiran> listSuratTangguhLama) {
        this.listSuratTangguhLama = listSuratTangguhLama;
    }

    public List<PenghantarNotis> getListPenghantarNotis() {
        return listPenghantarNotis;
    }

    public void setListPenghantarNotis(List<PenghantarNotis> listPenghantarNotis) {
        this.listPenghantarNotis = listPenghantarNotis;
    }
}

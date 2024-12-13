/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenasihatUndangDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanService;
import etanah.service.PembangunanService;
import etanah.view.stripes.pembangunan.MuatNaikImejActionBean;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.util.DateUtil;
import etanah.view.etanahActionBeanContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mdizzat.mashrom
 * @author Dev Syaiful
 */
@UrlBinding("/pembangunan/kemasukkan_rekod_16H_PPCB")
public class MaklumatPenghantaran16HPPCBActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PembangunanService devServices;
    @Inject
    PermohonanService permohonanservice;
    @Inject
    NotisDAO notisDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    DasarTuntutanNotisDAO dasarNotisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    PenasihatUndangDAO penasihatUndangDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Hakmilik hakmilik;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPihakBerkepentingan hakmilikPB;
    private Notis notis, notis2, notis22, nn;
    private KodNotis kodNotis;
    private KodStatusTerima kodterime;
    private KodCaraPenghantaran kodHanta;
    private KodKlasifikasi kodKlasifikasi;
    private KodDokumen kodDokumen;
    private Dokumen doc, dokumen;
    private DateUtil dateUtil;
    private InfoAudit info;
    private String idPermohonan;
    private List<Notis> listNotis;
    private List<HakmilikPihakBerkepentingan> listHakmilikPihak;
    private List<KandunganFolder> listKandunganFolder;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private List<HakmilikPermohonan> senaraiMohonHakmilik = new ArrayList<HakmilikPermohonan>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(etanah.view.stripes.pembangunan.MaklumatPenghantaran16HPPCBActionBean.class);
    private String _PAGE = "pembangunan/pecahBahagian/rekod_penghantaran_16H.jsp";
    private String _PAGE_UPLD = "pembangunan/pecahBahagian/16H_upload_doc.jsp";
    private String _PAGE_SCAN = "pembangunan/pecahBahagian/16H_scan_doc.jsp";
    private String _PAGE_POPUP = "pembangunan/pecahBahagian/penghantarNotis_popup_16H.jsp";
    private String idNotis;
    private boolean btn = false;
    private boolean show = false;
    private PenghantarNotis penghantarNotis;
    private Pengguna pengguna;
    private long idDokumen;
    // for upload document
    FileBean fileToBeUploaded;

    @DefaultHandler
    public Resolution showForm() {
//        if (listNotis.size() == 0) {
//            show = true;
//            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
//        }
        return new JSP(_PAGE).addParameter("tab", "true");
    }

    public Resolution popupPenghantarNotis() {
        return new JSP(_PAGE_POPUP).addParameter("popup", "true");
    }

    public Resolution showForm4() {
//        btn = true;
        return new JSP(_PAGE).addParameter("tab", "true");
    }

    public Resolution showForm3() {
        LOG.info("------showForm3-----");
        if (listNotis.isEmpty()) {
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
            show = true;
            return new JSP(_PAGE).addParameter("tab", "true");
        }
        rehydrate();
//
        return new JSP(_PAGE).addParameter("tab", "true");
    }

    public void simpanNotis() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        listKandunganFolder = devServices.getListDokumen16H(permohonan.getFolderDokumen().getFolderId());
        LOG.info("######listKandunganFolder :" + listKandunganFolder.size());

        if (listKandunganFolder.size() != 0) {

            dokumen = new Dokumen();

            for (KandunganFolder kk : listKandunganFolder) {
                dokumen = kk.getDokumen();
            }

            LOG.info("Kod Jabatan : " + permohonan.getKodUrusan().getJabatan());

            kodNotis = new KodNotis();
            kodNotis = kodNotisDAO.findById("N2A");//default N2A
            info = pengguna.getInfoAudit();
//            LOG.info("kodNotis : " + kodNotis); 
            if (dokumen.getKodDokumen() != null) {

                if (dokumen.getKodDokumen().getKod().equals("N2A")) {

                    LOG.info("###### Kod Dokumen : " + dokumen.getKodDokumen().getKod());

                    //get senarai hakmilik permohonan"mohon_hakmilik"
                    senaraiMohonHakmilik = permohonan.getSenaraiHakmilik();


                    for (int u = 0; u < senaraiMohonHakmilik.size(); u++) {
                        LOG.info("###### For 1 Notis : senaraiMohonHakmilik : " + senaraiMohonHakmilik.size());
                        hakmilikPermohonan = senaraiMohonHakmilik.get(u);
                        LOG.info("###### Notis : getId : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());

                        listHakmilikPihak = devServices.senaraiPihakBerkepentingan2(hakmilikPermohonan.getHakmilik().getIdHakmilik());

                        for (int k = 0; k < listHakmilikPihak.size(); k++) {
                            LOG.info("###### For 2 Notis : listHakmilikPihak : " + listHakmilikPihak.size());
                            hakmilikPB = listHakmilikPihak.get(k);

                            LOG.info("###### Notis : getIdHakmilikPihakBerkepentingan : " + hakmilikPB.getIdHakmilikPihakBerkepentingan());
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());
                            notis2 = new Notis();
                            notis2.setPermohonan(permohonan);
                            notis2.setInfoAudit(info);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotis);
                            notis2.setDokumenNotis(dokumen);
//                        notis2.setHakmilikPermohonan(hakmilikPermohonan);
                            notis2.setHakmilikPihakBerkepentingan(hakmilikPB);
                            notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                            devServices.saveOrUpdate(notis2);
                        }
                    }
                    for (int j = 0; j < senaraiPermohonanPihak.size(); j++) {

                        LOG.info("senaraiPermohonanPihak name : " + senaraiPermohonanPihak.get(j).getPihak().getNama());

                        notis22 = new Notis();
                        notis22.setPermohonan(permohonan);
                        notis22.setInfoAudit(info);
                        notis22.setTarikhNotis(new java.util.Date());
//                        notis22.setKodNotis(kodNotis);
                        notis22.setPihak(senaraiPermohonanPihak.get(j));
                        notis22.setDokumenNotis(dokumen);
                        notis22.setJabatan(permohonan.getKodUrusan().getJabatan());
                        devServices.saveOrUpdate(notis22);
                    }
                } else {
                    LOG.info("lain-lain..");
                }
            }
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kodNotis = "";

        LOG.info("idPermohoann : " + idPermohonan);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);

            listNotis = devServices.getListNotis3(idPermohonan);

            LOG.info("listNotis : " + listNotis.size());
            if (listNotis.isEmpty()) {//(listNotis.size() == 0)
                LOG.info("Belum ade lg...");
                simpanNotis();
                listNotis = devServices.getListNotis3(idPermohonan);
            }
            for (Notis n : listNotis) {
                notis = n;
            }
        }
    }

    public Resolution simpan() {

        info = pengguna.getInfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        for (int i = 0; i < listNotis.size(); i++) {
            nn = listNotis.get(i);
            kodterime = new KodStatusTerima();
            kodHanta = new KodCaraPenghantaran();
            kodterime.setKod(getContext().getRequest().getParameter("kodPenyampaian" + i));
            kodHanta.setKod(getContext().getRequest().getParameter("kodPenghantaran" + i));
            penghantarNotis = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
            nn.setPenghantarNotis(penghantarNotis);
            nn.setKodCaraPenghantaran(kodHanta);
            nn.setKodStatusTerima(kodterime);
            nn.setInfoAudit(info);
            devServices.saveOrUpdate(nn);
//            lelongService.saveOrUpdate(nn);
        }
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new JSP(_PAGE).addParameter("tab", "true");
    }

    public Resolution simpanPenghantarNotis() {

        info = pengguna.getInfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = pengguna.getKodCawangan();
        penghantarNotis.setInfoAudit(info);
        penghantarNotis.setCawangan(caw);
        penghantarNotis.setAktif('Y');
        devServices.saveOrUpdate(penghantarNotis);
//        lelongService.saveOrUpdate(penghantarNotis);
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP(_PAGE).addParameter("tab", "true");
    }

    public Resolution popupUpload() {
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        LOG.info("idNotis :" + idNotis);
        return new JSP(_PAGE_UPLD).addParameter("popup", "true");
    }

    public Resolution findNoPengenalan() {
        String result = "";
        int idPenghantarNotis = (Integer.parseInt(getContext().getRequest().getParameter("idPenghantarNotis")));
        penghantarNotis = penghantarNotisDAO.findById(idPenghantarNotis);
        penghantarNotis = devServices.findPenghantarNotis(idPenghantarNotis);
//        pN = lelongService.findPenghantarNotis(idPenghantarNotis);
        result = penghantarNotis.getNoPengenalan();
        return new StreamingResolution("text/plain", result);
    }

    public Resolution processUploadDoc2() {
        LOG.info("######processUploadDoc2");
        String idNotis2 = getContext().getRequest().getParameter("idNotis");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        String dokumenPath = conf.getProperty("document.path");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        pengguna = ctx.getUser();
//        String fname = idNotis;
        String urusan = "notis";
//        String catatan = "idNotis Notis Penghantaran" + idNotis.toString();
        LOG.info("idNotis : " + idNotis2);
        LOG.info("pengguna : " + pengguna.getNama());
        
        kodKlasifikasi = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        kodDokumen = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        notis = notisDAO.findById(Long.parseLong(idNotis2));
        LOG.debug("######notis = " + notis.getBuktiPenerimaan());
        info = new InfoAudit();

        info.setTarikhMasuk(new Date());
        info.setDimasukOleh(pengguna);

        try {
            if (pengguna != null && idNotis2 != null && fileToBeUploaded != null) {
                String fileName = fileToBeUploaded.getFileName();
                String contentType = fileToBeUploaded.getContentType();
//                String kodDok = "N2A";
                LOG.debug("###fileName :" + fileName + " contentType :" + contentType);
                LOG.debug("######idPermohonan = " + idPermohonan);
                permohonan = permohonanDAO.findById(idPermohonan);
                DMSUtil dmsUtil = new DMSUtil();

                if (permohonan != null) {
//                    doc = devServices.saveDokumenNotis(dokumenPath, fileToBeUploaded, notis, info, permohonan,kodDokumen);
                    LOG.debug("###### doc.getIdDokumen() " + doc.getIdDokumen());
                    FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                    fileUtil.saveFile(fileName, fileToBeUploaded.getInputStream());
                    String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                    updatePathDokumen(fizikalPath, doc.getIdDokumen(), fileToBeUploaded.getContentType(), kodDokumen, pengguna,permohonan,urusan);

                    Notis dtn = notisDAO.findById(Long.parseLong(idNotis2));
                    if (dtn.getBuktiPenerimaan() != null) {
                        doc = dtn.getBuktiPenerimaan();
                        info = dtn.getBuktiPenerimaan().getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                    } else {
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());
                    }
                    dtn.setBuktiPenerimaan(dokumenDAO.findById(doc.getIdDokumen()));
                    dtn.setInfoAudit(info);
                    devServices.updateNotis(dtn);

                }
                LOG.info("simpanMuatNaik::finish");
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }

        rehydrate();
        return new JSP(_PAGE_UPLD).addParameter("popup", "true");
//        return new JSP(_PAGE).addParameter("tab", "true");
//        return new RedirectResolution(MaklumatPenghantaran16HPPCBActionBean.class, "showForm");
    }

    public Resolution processUploadDoc() {
        kodKlasifikasi = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        kodDokumen = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        info = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
//        Long idP = Long.parseLong(permohonan.getIdPermohonan());
//        notis = devServices.findNotisByIdPermohonan(permohonan.getIdPermohonan());
//        String fname = String.valueOf(notis.getIdNotis());
        String fname = getContext().getRequest().getParameter("idNotis");
//        String fname = idNotis;
        LOG.info("######idNotis : " + fname);
        LOG.info("######permohonan.getIdPermohonan() : " + permohonan.getIdPermohonan());
        dateUtil = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
        try {
            if (pengguna != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = pengguna.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + dateUtil.getYear() + File.separator
                        + dateUtil.getDateName(String.valueOf(dateUtil.getMonth() + 1))
                        + File.separator + dateUtil.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
                doc = new Dokumen();
                Notis dtn = notisDAO.findById(Long.parseLong(fname));
                if (dtn.getBuktiPenerimaan() != null) {
                    doc = dtn.getBuktiPenerimaan();
                    info = dtn.getBuktiPenerimaan().getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                } else {
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kodDokumen);
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasifikasi);
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(info);
                Long idDoc = devServices.simpanOrUpdateDokumen(doc);
//                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                info = dtn.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                dtn.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                dtn.setInfoAudit(info);
                devServices.updateNotis(dtn);
//                lelongService.updateNotis(dtn);
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        rehydrate();
//        return new JSP(_PAGE).addParameter("tab", "true");
        return new JSP(_PAGE_UPLD).addParameter("popup", "true");
    }

    public Resolution popupScan() {

        LOG.info("idNotis :" + idNotis);
        kodKlasifikasi = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        kodDokumen = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        info = new InfoAudit();
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
        try {
            if (pengguna != null && fname != null) {
                doc = new Dokumen();
                Notis dtn = notisDAO.findById(Long.parseLong(fname));
                if (dtn.getBuktiPenerimaan() != null) {
                    doc = dtn.getBuktiPenerimaan();
                    info = dtn.getBuktiPenerimaan().getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                } else {
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                }
                doc.setKodDokumen(kodDokumen);
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasifikasi);
                doc.setFormat("application/pdf/image/gif");
                doc.setInfoAudit(info);
                idDokumen = devServices.simpanOrUpdateDokumen(doc);
//                idDokumen = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDokumen);
                // update at DasarTuntutanNotis
                info = dtn.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                dtn.setBuktiPenerimaan(dokumenDAO.findById(idDokumen));
                dtn.setInfoAudit(info);
                devServices.updateNotis(dtn);
//                lelongService.updateNotis(dtn);
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP(_PAGE_SCAN).addParameter("popup", "true");
    }

    public Resolution view() {

        LOG.info("idNotis : " + idNotis);

        List<Notis> listNS = devServices.getNotis(Long.parseLong(idNotis));
//        List<Notis> listNS = lelongService.getNotis(Long.parseLong(idNotis));
        LOG.info("listNS : " + listNS.size());
        if (listNS.get(0).getBuktiPenerimaan() != null) {
            idDokumen = listNS.get(0).getBuktiPenerimaan().getIdDokumen();
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
        if (d.getKlasifikasi() != null
                && p.getTahapSekuriti().getKod() < d.getKlasifikasi().getKod()) {
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
                boolean createWatermark = true;

                if (d.getKodDokumen().getKod().equalsIgnoreCase("DHKE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("DHDE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1DE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2DE")) {
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

    public void updatePathDokumen(String namaFizikal, Long idDokumen, String format,KodDokumen kodDokumen, Pengguna pguna, Permohonan permohonan, String urusan) {
        System.out.println("updatePathDokumen");
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
//        kodDokumen = kodDokumenDAO.findById("BPN");

            d.setTajuk(kodDokumen.getNama());

//        if (!urusan.equalsIgnoreCase("notis")) {
//            InfoAudit ia = new InfoAudit();
//            ia.setTarikhMasuk(new Date());
//            ia.setDimasukOleh(pguna);
//            imejLaporan.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
//            imejLaporan.setCawangan(pguna.getKodCawangan());
//            imejLaporan.setDokumen(d);
//            imejLaporan.setLaporanTanah(laporanTanah);
//            imejLaporan.setCatatan(catatan);
//
//            imejLaporan.setInfoAudit(ia);
//            pembangunanService.saveImej(imejLaporan);
//        }
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public Resolution reload() {
        rehydrate();
        return new JSP(_PAGE_SCAN).addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
        return listHakmilikPihak;
    }

    public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
        this.listHakmilikPihak = listHakmilikPihak;
    }
}

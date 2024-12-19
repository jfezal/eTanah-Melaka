/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

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
import etanah.model.PenasihatUndang;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPihak;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanService;
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
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/kemasukkan_rekod_16H")
public class MaklumatPenghantaran16HActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanService permohonanservice;
    @Inject
    NotisDAO notisDAO;
    @Inject
    LelongService lelongService;
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
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Hakmilik hakmilik;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private Notis notis;
    private String idPermohonan;
    private List<Notis> listNotis;
    private List<KandunganFolder> listKandunganFolder;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(MaklumatPenghantaran16HActionBean.class);
    private String idNotis;
    private boolean btn = false;
    private boolean show = false;
    private boolean negori;
    private PenghantarNotis penghantarNotis;
    private Pengguna pengguna;
    private long idDokumen;
    private String kodNeg;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    private List<PenghantarNotis> senaraiPenghantarNotis;
    // for upload document
    FileBean fileToBeUploaded;

    @DefaultHandler
    public Resolution showForm() {
        if (listNotis.isEmpty()) {
            show = true;
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
        }
        return new JSP("lelong/rekod_penghantaran_16H.jsp").addParameter("tab", "true");
    }

    public Resolution popupPenghantarNotis() {
        return new JSP("lelong/penghantarNotis_popup_16H.jsp").addParameter("popup", "true");
    }

    public Resolution showForm4() {
        btn = true;
        return new JSP("lelong/rekod_penghantaran_16H.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        LOG.info("------showForm3-----");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
        } else {
            negori = false;
        }
        if (listNotis.isEmpty()) {
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
            show = true;
            return new JSP("lelong/rekod_penghantaran_16H.jsp").addParameter("tab", "true");
        }
        rehydrate();
        return new JSP("lelong/rekod_penghantaran_16H.jsp").addParameter("tab", "true");
    }

    public void simpanNotis() {

        listKandunganFolder = lelongService.getListDokumen16H(permohonan.getFolderDokumen().getFolderId());

        if (!listKandunganFolder.isEmpty()) {

            Dokumen dokumen = new Dokumen();

            for (KandunganFolder kk : listKandunganFolder) {
                dokumen = kk.getDokumen();
            }

            LOG.info("Kod Jabatan : " + permohonan.getKodUrusan().getJabatan());

            KodNotis kodNotis = new KodNotis();
            kodNotis = kodNotisDAO.findById("16H");
            LOG.info("kodNotis : " + kodNotis.getKod());
            if (dokumen.getKodDokumen() != null) {

                if (dokumen.getKodDokumen().getKod().equals("16H")) {

                    InfoAudit info = pengguna.getInfoAudit();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    LOG.info("kodNeg : " + kodNeg);
//                    if (kodNeg.equals("04")) {
//                        //melaka
//                        PenasihatUndang penUndang = lelongService.getALLPenUndg();
//                        Notis notis2 = new Notis();
//                        notis2.setPermohonan(permohonan);
//                        notis2.setInfoAudit(info);
//                        notis2.setTarikhNotis(new java.util.Date());
//                        notis2.setKodNotis(kodNotis);
//                        notis2.setDokumenNotis(dokumen);
//                        notis2.setPenasihatUndang(penUndang);
//                        notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
//                        lelongService.saveOrUpdate(notis2);
//                    }

                    List<PermohonanAtasPerserahan> listPAP = lelongService.getPermohonanAtasPerserahan(idPermohonan);
                    PermohonanAtasPerserahan pAP = listPAP.get(0);
                    if (pAP.getUrusan().getKodUrusan().getKod().equals("GD") || pAP.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                        List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihakPG(idPermohonan);
                        for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {

                            if (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PM")) {
                                PermohonanPihak pPihak = null;
                                long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                                for (PermohonanPihak pp : listPP) {
                                    if (pp.getPihak().getIdPihak() == idPihak) {
                                        pPihak = pp;
                                        break;
                                    }
                                }
                                LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                if (pPihak != null) {
                                    notis2.setPihak(pPihak);
                                }
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotis);
                                notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                notis2.setDokumenNotis(dokumen);
                                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                lelongService.saveOrUpdate(notis2);
                            }
                        }
                        Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
                        listPP = lelongService.getSenaraiPmohonPihakPM(idPermohonan);
                        if (!listPP.isEmpty()) {
                            for (PermohonanPihak hp : listPP) {
                                Long id = hp.getPihak().getIdPihak();
                                if (pihakMap.containsKey(id)) {
                                    continue;
                                } else {
                                    pihakMap.put(id, hp);
                                }
                            }
                            listPP = new ArrayList(pihakMap.values());
                            for (PermohonanPihak pp : listPP) {
                                HakmilikPihakBerkepentingan hb = null;
                                long idPihak = pp.getPihak().getIdPihak();
                                for (int k = 0; k < senaraiHakmilikPihak.size(); k++) {
                                    if (senaraiHakmilikPihak.get(k).getPihak().getIdPihak() == idPihak) {
                                        hb = senaraiHakmilikPihak.get(k);
                                        break;
                                    }
                                }
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                notis2.setPihak(pp);
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotis);
                                notis2.setHakmilikPihakBerkepentingan(hb);
                                notis2.setDokumenNotis(dokumen);
                                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                lelongService.saveOrUpdate(notis2);
                            }
                        }
                    }

                    if (pAP.getUrusan().getKodUrusan().getKod().equals("GDPJ") || pAP.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
                        List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(idPermohonan);
                        for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {

                            PermohonanPihak pPihak = null;
                            long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                            for (PermohonanPihak pp : listPP) {
                                if (pp.getPihak().getIdPihak() == idPihak) {
                                    pPihak = pp;
                                    break;
                                }
                            }
                            LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());
                            Notis notis2 = new Notis();
                            notis2.setPermohonan(permohonan);
                            notis2.setInfoAudit(info);
                            if (pPihak != null) {
                                notis2.setPihak(pPihak);
                            }
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotis);
                            notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                            notis2.setDokumenNotis(dokumen);
                            notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                            lelongService.saveOrUpdate(notis2);
                        }
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
        kodNeg = conf.getProperty("kodNegeri");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
        } else {
            negori = false;
        }
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);

            List<String> listIDHakmilik = new ArrayList<String>();
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                listIDHakmilik.add(hp.getHakmilik().getIdHakmilik());
            }
            LOG.info("listIDHakmilik : " + listIDHakmilik.size());
            List<HakmilikPihakBerkepentingan> listHP = lelongService.getHakmilikPihakALL(idPermohonan, listIDHakmilik);
            Map<Long, HakmilikPihakBerkepentingan> pihakMap = new HashMap<Long, HakmilikPihakBerkepentingan>();
            for (HakmilikPihakBerkepentingan hp : listHP) {
                Long id = hp.getPihak().getIdPihak();
                if (pihakMap.containsKey(id)) {
                    continue;
                } else {
                    pihakMap.put(id, hp);
                }
            }
            senaraiHakmilikPihak = new ArrayList(pihakMap.values());
            LOG.info("senaraiPermohonanPihak : " + senaraiHakmilikPihak.size());
            setSenaraiPenghantarNotis(lelongService.findPenghantarNotisAktif());
            listNotis = lelongService.getListNotis2(idPermohonan, "16H");
            LOG.info("listNotis : " + listNotis.size());
            if (listNotis.isEmpty()) {
                LOG.info("Belum ade lg...");
                simpanNotis();
                listNotis = lelongService.getListNotis2(idPermohonan, "16H");
            }
            for (Notis n : listNotis) {
                notis = n;
            }
        }
    }

    public Resolution simpan() {
        
        LOG.info("-- simpan 16H --");
        InfoAudit info = pengguna.getInfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        for (int i = 0; i < listNotis.size(); i++) {
            Notis nn = listNotis.get(i);
            KodStatusTerima kodterime = new KodStatusTerima();
            KodCaraPenghantaran kodHanta = new KodCaraPenghantaran();
            kodterime.setKod(getContext().getRequest().getParameter("kodPenyampaian" + i));
            kodHanta.setKod(getContext().getRequest().getParameter("kodPenghantaran" + i));
            String namaPenghantar = getContext().getRequest().getParameter("namaPengahantar" + i);
            
            LOG.info("kodHanta = " + kodHanta.getKod());
            LOG.info("namaPenghantar = " + namaPenghantar);
           
            if (!"".equals(namaPenghantar)) {
                PenghantarNotis peng = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
                nn.setPenghantarNotis(peng);
            } else {
                nn.setPenghantarNotis(null);
            }
            nn.setKodCaraPenghantaran(kodHanta);
            nn.setKodStatusTerima(kodterime);
            nn.setInfoAudit(info);
            lelongService.saveOrUpdate(nn);
        }
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new JSP("lelong/rekod_penghantaran_16H.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPenghantarNotis() {

        InfoAudit info = pengguna.getInfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = pengguna.getKodCawangan();
        penghantarNotis.setInfoAudit(info);
        penghantarNotis.setCawangan(caw);
        penghantarNotis.setAktif('Y');
        lelongService.saveOrUpdate(penghantarNotis);
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("lelong/rekod_penghantaran_16H.jsp").addParameter("tab", "true");
    }

    public Resolution popupUpload() {
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        LOG.info("idNotis :" + idNotis);
        return new JSP("lelong/16H_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution findNoPengenalan() {
        String result = "";
        int idPenghantarNotis = (Integer.parseInt(getContext().getRequest().getParameter("idPenghantarNotis")));
        PenghantarNotis pN = penghantarNotisDAO.findById(idPenghantarNotis);
        pN = lelongService.findPenghantarNotis(idPenghantarNotis);
        result = pN.getNoPengenalan();
        return new StreamingResolution("text/plain", result);
    }

    public Resolution processUploadDoc() {
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
                Dokumen doc = new Dokumen();
                Notis dtn = notisDAO.findById(Long.parseLong(fname));
                if (dtn.getBuktiPenerimaan() != null) {
                    doc = dtn.getBuktiPenerimaan();
                    ia = dtn.getBuktiPenerimaan().getInfoAudit();
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
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                dtn.setInfoAudit(ia);
                lelongService.updateNotis(dtn);
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        rehydrate();
        return new JSP("lelong/16H_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution popupScan() {
        LOG.info("idNotis :" + idNotis);
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try {
            if (p != null && fname != null) {
                Dokumen doc = new Dokumen();
                Notis dtn = notisDAO.findById(Long.parseLong(fname));
                if (dtn.getBuktiPenerimaan() != null) {
                    doc = dtn.getBuktiPenerimaan();
                    ia = dtn.getBuktiPenerimaan().getInfoAudit();
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
                idDokumen = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDokumen);
                // update at DasarTuntutanNotis
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setBuktiPenerimaan(dokumenDAO.findById(idDokumen));
                dtn.setInfoAudit(ia);
                lelongService.updateNotis(dtn);
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
        }
        return new JSP("lelong/16H_scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution view() {

        LOG.info("idNotis : " + idNotis);

        List<Notis> listNS = lelongService.getNotis(Long.parseLong(idNotis));
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

    public Resolution reload() {
        rehydrate();
        return new JSP("lelong/16H_scan_doc.jsp").addParameter("tab", "true");
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

    public String getKodNeg() {
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        this.kodNeg = kodNeg;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    /**
     * @return the senaraiPenghantarNotis
     */
    public List<PenghantarNotis> getSenaraiPenghantarNotis() {
        return senaraiPenghantarNotis;
    }

    /**
     * @param senaraiPenghantarNotis the senaraiPenghantarNotis to set
     */
    public void setSenaraiPenghantarNotis(List<PenghantarNotis> senaraiPenghantarNotis) {
        this.senaraiPenghantarNotis = senaraiPenghantarNotis;
    }

    public boolean isNegori() {
        return negori;
    }

    public void setNegori(boolean negori) {
        this.negori = negori;
    }
}

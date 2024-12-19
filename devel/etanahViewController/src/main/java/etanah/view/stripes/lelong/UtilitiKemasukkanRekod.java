/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanService;
import etanah.util.FileUtil;
import etanah.util.DateUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/Utiliti_kemasukan_rekod")
public class UtilitiKemasukkanRekod extends AbleActionBean {

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
    KodNotisDAO kodNotisDAO;
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
    private Permohonan permohonan;
    private Notis notis;
    private String idPermohonan;
    private List<KandunganFolder> listKandunganFolder;
    private List<Notis> listNotis;
    private List<String> kodPenyampaian;
    private List<String> kodPenghantaran;
    private List<String> tarikhDihantar;
    private List<String> tarikhTerima;
    private List<String> catatanTerima;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String idNotis;
    private boolean btn = false;
    FileBean fileToBeUploaded;
    private static final Logger LOG = Logger.getLogger(UtilitiKemasukkanRekod.class);
    private int bil;
    private boolean view = false;
    private FasaPermohonan fasaMohon;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/utiliti_kemasukan_rekod.jsp");
    }

    public Resolution popupUpload() {

        LOG.info("idNotis :" + idNotis);

        return new JSP("lelong/utiliti_kemasukan_rekod_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution find() {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                senaraiPermohonanPihak = lelongService.getSenaraiPmohonPihak(idPermohonan);
                LOG.info("senaraiPermohonanPihak : " + senaraiPermohonanPihak.size());
                listNotis = lelongService.getListNotis(idPermohonan, "SSL");
                if (listNotis.isEmpty()) {
                    LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());
                    long idFolder = permohonan.getFolderDokumen().getFolderId();

                    listKandunganFolder = lelongService.getListDokumen(idFolder);
                    LOG.info("listKandunganFolder : " + listKandunganFolder.size());
                    LOG.info("senaraiPermohonanPihak : " + senaraiPermohonanPihak.size());
                    Dokumen dokumen = new Dokumen();

                    for (KandunganFolder kk : listKandunganFolder) {
                        if (kk.getDokumen().getBaru() == 'Y' && kk.getDokumen().getKodDokumen().getKod().equals("SSL")) {
                            dokumen = kk.getDokumen();
                        }
                    }

                    if (dokumen.getKodDokumen() != null) {

                        LOG.info("dokumen : " + dokumen.getKodDokumen().getKod());
                        LOG.info("Kod Jabatan : " + permohonan.getKodUrusan().getJabatan());

                        KodNotis kodNotis = new KodNotis();
                        kodNotis = kodNotisDAO.findById("SSL");
                        LOG.info("kodNotis : " + kodNotis.getKod());

                        for (int j = 0; j < senaraiPermohonanPihak.size(); j++) {

                            if (dokumen.getKodDokumen().getKod().equals("SSL")) {

                                LOG.info("senaraiPermohonanPihak name : " + senaraiPermohonanPihak.get(j).getPihak().getNama());
                                Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                                InfoAudit info = p.getInfoAudit();

                                info.setDimasukOleh(p);
                                info.setTarikhMasuk(new java.util.Date());

                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotis);
                                notis2.setPihak(senaraiPermohonanPihak.get(j));
                                notis2.setDokumenNotis(dokumen);
                                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                lelongService.saveOrUpdate(notis2);


                            } else {
                                LOG.info("lain-lain..");
                            }
                        }
                    } else {
                        addSimpleError("Notis Belum Dicetak.Sila Cetak Notis Terlebih Dahulu..  ");
                        return new ForwardResolution("/WEB-INF/jsp/lelong/utiliti_kemasukan_rekod.jsp");
                    }
                }

            } else {
                addSimpleError("IdPermohonan Salah.Sila Masukkan IdPermohonan Sekali Lagi");
                return new ForwardResolution("/WEB-INF/jsp/lelong/utiliti_kemasukan_rekod.jsp");
            }
        }
        listNotis = lelongService.getListNotis(idPermohonan, "SSL");
        view = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/utiliti_kemasukan_rekod.jsp");
    }

    public Resolution simpan() throws ParseException {

        LOG.info("simpala");
        LOG.info("idPermohonan : " + idPermohonan);
        LOG.info("idNotis : " + idNotis);
        LOG.info("listNotisla : " + listNotis.size());

        for (int i = 0; i < listNotis.size(); i++) {

            Notis nn = listNotis.get(i);
            KodStatusTerima kodterime = new KodStatusTerima();
            KodCaraPenghantaran kodHanta = new KodCaraPenghantaran();
            kodterime.setKod(getContext().getRequest().getParameter("kodPenyampaian" + i));
            kodHanta.setKod(getContext().getRequest().getParameter("kodPenghantaran" + i));

            nn.setCatatanPenerimaan(getContext().getRequest().getParameter("catatanTerima" + i));
            nn.setTarikhHantar(sdf.parse(getContext().getRequest().getParameter("tarikhDihantar" + i)));
            nn.setTarikhTerima(sdf.parse(getContext().getRequest().getParameter("tarikhTerima" + i)));
//            nn.setPenghantarNotis(getContext().getRequest().getParameter("namaPenghantar" + i));
            nn.setKodCaraPenghantaran(kodHanta);
            nn.setKodStatusTerima(kodterime);
            lelongService.saveOrUpdate(nn);


        }
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new ForwardResolution("/WEB-INF/jsp/lelong/utiliti_kemasukan_rekod.jsp");
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
        return new JSP("lelong/utiliti_kemasukan_rekod_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution popupScan() {
//        idNotis = (String) getContext().getRequest().getSession().getAttribute("idNotis");
        LOG.info("idNotis :" + idNotis);
//        LOG.info("idDasar :" + noDasar);
//        LOG.info("idHakmilik :" + idHakmilik);
//        LOG.info("kodStatus :" + kodStatus);
        return new JSP("lelong/utiliti_kemasukan_rekod_scan_doc.jsp").addParameter("popup", "true");
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

    public List<String> getCatatanTerima() {
        return catatanTerima;
    }

    public void setCatatanTerima(List<String> catatanTerima) {
        this.catatanTerima = catatanTerima;
    }

    public List<String> getKodPenghantaran() {
        return kodPenghantaran;
    }

    public void setKodPenghantaran(List<String> kodPenghantaran) {
        this.kodPenghantaran = kodPenghantaran;
    }

    public List<String> getKodPenyampaian() {
        return kodPenyampaian;
    }

    public void setKodPenyampaian(List<String> kodPenyampaian) {
        this.kodPenyampaian = kodPenyampaian;
    }

    public List<String> getTarikhDihantar() {
        return tarikhDihantar;
    }

    public void setTarikhDihantar(List<String> tarikhDihantar) {
        this.tarikhDihantar = tarikhDihantar;
    }

    public List<String> getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(List<String> tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
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

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }
}

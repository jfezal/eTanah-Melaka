/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodStatusTerima;
import etanah.model.KodJabatan;
import etanah.model.KodKlasifikasi;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.service.common.EnforcementService;
import etanah.service.common.LelongService;
import etanah.util.DateUtil;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/notis_penyampaian")
public class NotisPenyampaianActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    NotisDAO notisDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    LelongService lelongService;
    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    private static final Logger LOG = Logger.getLogger(NotisPenyampaianActionBean.class);
    private Permohonan permohonan;
    private Pengguna pengguna;
    private String idPermohonan;
    private String idNotis;
    private Notis notis;
    private List<Notis> listNotis;
    private int bil;
    FileBean fileToBeUploaded;
    private long idDokumen;
    private PenghantarNotis penghantarNotis;
    private String noKadPengenalan;
    // added by murali
    private List<String> noKadList;
    private List<PenghantarNotis> penghantarNotisList;
    private String idAduan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/notis_penyampaian.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/cek_idMohon.jsp").addParameter("tab", "true");
    }

//    public Resolution semakIdAduan() {
//        String result = "";
//        idAduan = getContext().getRequest().getParameter("idAduan");
//        System.out.println("id aduan ialah : " + idAduan);
//        Permohonan idMohon = enforcementService.semakIdAduan(idAduan);
//        if (idMohon != null) {
//            result = "exist";
//        } else {
//            result = "notExist";
//        }
//
//        return new StreamingResolution("text/plain", result);
//    }

    public void listDetails() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idNotis = getContext().getRequest().getParameter("idNotis");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            System.out.println("id pemohon : " + permohonan.getIdPermohonan());

            listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
            //added by murali
            noKadList = new ArrayList<String>();
            for (int i = 0; i < listNotis.size(); i++) {
                Notis notis = new Notis();
                notis = listNotis.get(i);
                if (notis.getPenghantarNotis() != null) {
                    noKadList.add(notis.getPenghantarNotis().getNoPengenalan());
                } else {
                    noKadList.add("");
                }
            }
        } else {
            System.out.println("klu list notis null");

        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idNotis = getContext().getRequest().getParameter("idNotis");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);

        }
    }

    public Resolution simpan() throws ParseException {

        LOG.info("size list notis : " + listNotis.size());
        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = p.getInfoAudit();
        info.setDiKemaskiniOleh(p);
        info.setTarikhKemaskini(new java.util.Date());
        for (int i = 0; i < listNotis.size(); i++) {
            KodJabatan kodJabatan = new KodJabatan();
            kodJabatan.setKod("24");
            Notis n = listNotis.get(i);
            KodStatusTerima kodTerima = kodStatusTerimaDAO.findById(getContext().getRequest().getParameter("kodStatusTerima" + i));
            KodCaraPenghantaran kodHantar = kodCaraPenghantaranDAO.findById(getContext().getRequest().getParameter("kodCaraPenghantaran" + i));
            PenghantarNotis peng = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
            n.setPenghantarNotis(peng);
            n.setKodCaraPenghantaran(kodHantar);
            n.setKodStatusTerima(kodTerima);
            n.setJabatan(kodJabatan);
            n.setInfoAudit(info);
            enforcementService.simpanNotisPenguatkuasaan(n);
        }
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("penguatkuasaan/notis_penyampaian.jsp").addParameter("tab", "true");

    }

    public Resolution popupUpload() {
        LOG.info("idNotis :" + idNotis);
        return new JSP("penguatkuasaan/notis_siasatan_upload_doc.jsp").addParameter("popup", "true");
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
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }

        return new JSP("penguatkuasaan/notis_siasatan_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution popupScan() {

        LOG.info("idNotis ialah:" + idNotis);
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        //etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        //Pengguna p = ctx.getUser();
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        // try {
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
            System.out.println("sblm update notis");
            KodJabatan kodJabatan = new KodJabatan();
            kodJabatan.setKod("8");
            dtn.setJabatan(kodJabatan);
            lelongService.updateNotis(dtn);
            System.out.println("slps update notis");
        } else {
            LOG.error("parameter tidak mencukupi.");
        }
//  } catch (Exception ex) {
//  LOG.error("Fatal protocol violation: " + ex.getMessage());
//  ex.printStackTrace(); // comment this for production
//  }
        return new JSP("penguatkuasaan/notis_siasatan_scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        rehydrate();
        return new JSP("penguatkuasaan/notis_penyampaian.jsp").addParameter("tab", "true");
    }

    public Resolution findNoPengenalan() {
        String result = "";
        int idPenghantarNotis = (Integer.parseInt(getContext().getRequest().getParameter("idPenghantarNotis")));
        PenghantarNotis penghantarNotis = penghantarNotisDAO.findById(idPenghantarNotis);
        penghantarNotis = enforcementService.findNoKadPengenalan(idPenghantarNotis);
        result = penghantarNotis.getNoPengenalan();
        return new StreamingResolution("text/plain", result);
    }

    public Resolution popupPenghantarNotis() {
        return new JSP("penguatkuasaan/penghantarNotis_popup.jsp").addParameter("popup", "true");
    }

    public Resolution functionEros() {
        return new JSP("penguatkuasaan/jsperossuruhbuat.jsp");
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
        return new JSP("penguatkuasaan/notis_penyampaian.jsp").addParameter("tab", "true");
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getNoKadPengenalan() {
        return noKadPengenalan;
    }

    public void setNoKadPengenalan(String noKadPengenalan) {
        this.noKadPengenalan = noKadPengenalan;
    }

    public List<String> getNoKadList() {
        return noKadList;
    }

    public void setNoKadList(List<String> noKadList) {
        this.noKadList = noKadList;
    }

    public List<PenghantarNotis> getPenghantarNotisList() {
        return penghantarNotisList;
    }

    public void setPenghantarNotisList(List<PenghantarNotis> penghantarNotisList) {
        this.penghantarNotisList = penghantarNotisList;
    }

    public String getIdAduan() {
        return idAduan;
    }

    public void setIdAduan(String idAduan) {
        this.idAduan = idAduan;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.BarangRampasan;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasImej;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.EnforceService;
import etanah.service.common.KandunganFolderService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author khairul.hazwan
 */
   
@UrlBinding("/utiliti/upload_action")
public class UploadLtActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UploadLtActionBean.class);
    FileBean fileToBeUpload;
    @Inject
    EnforceService enforceService;
    @Inject
    etanah.Configuration conf;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KandunganFolderService kandunganFolderService;
    private String dokumenKod;
    private String idPermohonan;
    private String folderId;
    private String catatan;
    private String idLaporan;
    private String idHakmilik;
    private String idRujukan;
    private String idBarang;
    private String idDokumen;
    private Dokumen d;
    private KandunganFolder kf;
    private String idKertas;
    private String idOperasiAgensi;
    private String textLaporanPemantauan;
    private String jenisLaporanV2;
    private Hakmilik hakmilik;

    @DefaultHandler
    public Resolution popupUpload() {
        logger.info("popupUpload");

        folderId = getContext().getRequest().getParameter("folderId");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        dokumenKod = getContext().getRequest().getParameter("dokumenKod");
        idLaporan = getContext().getRequest().getParameter("idLaporan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        idBarang = getContext().getRequest().getParameter("idBarang");
        idDokumen = getContext().getRequest().getParameter("idDokumen");
        idKertas = getContext().getRequest().getParameter("idKertas");
        textLaporanPemantauan = getContext().getRequest().getParameter("textLaporanPemantauan");
        jenisLaporanV2 = getContext().getRequest().getParameter("jenisLaporanV2");


        if (getContext().getRequest().getParameter("prm") != null) {
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
        }
        if (StringUtils.isNotBlank(folderId)) {
            getContext().getRequest().setAttribute("folderId", folderId);
        } else {
            return new ErrorResolution(404, "Folder tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        } else {
            return new ErrorResolution(404, "Tiada Id Permohonan.");
        }

        if (StringUtils.isNotBlank(dokumenKod)) {
            getContext().getRequest().setAttribute("dokumenKod", dokumenKod);
        } else {
            return new ErrorResolution(404, "Kod Dokumen tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(idLaporan)) {
            getContext().getRequest().setAttribute("idLaporan", idLaporan);
        }

        if (StringUtils.isNotBlank(idBarang)) {
            getContext().getRequest().setAttribute("idBarang", idBarang);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        }

        if (StringUtils.isNotBlank(idDokumen)) {
            getContext().getRequest().setAttribute("idDokumen", idDokumen);
        }

        if (StringUtils.isNotBlank(idKertas)) {
            getContext().getRequest().setAttribute("idKertas", idKertas);
        }

        if (StringUtils.isNotBlank(textLaporanPemantauan)) {
            getContext().getRequest().setAttribute("textLaporanPemantauan", textLaporanPemantauan);
        }

        if (StringUtils.isNotBlank(jenisLaporanV2)) {
            getContext().getRequest().setAttribute("textLaporanPemantauan", jenisLaporanV2);
        }

        logger.info("value when open popup ::: folderId : " + folderId + " idPermohonan : " + idPermohonan + " dokumenKod : " + dokumenKod + " idHakmilik : " + idHakmilik + " idRujukan : "
                + idRujukan + " idBarang : " + idBarang + " idDokumen : " + idDokumen + " idKertas : " + idKertas + " jenisLaporanV2 : " + jenisLaporanV2);
        return new JSP("pembangunan/utiliti/popup_upload.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc() {
        logger.info("processUploadDoc");
        folderId = getContext().getRequest().getParameter("folderId");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        dokumenKod = getContext().getRequest().getParameter("dokumenKod");
        idLaporan = getContext().getRequest().getParameter("idLaporan");
        catatan = getContext().getRequest().getParameter("catatan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        idBarang = getContext().getRequest().getParameter("idBarang");
        idDokumen = getContext().getRequest().getParameter("idDokumen");
        idKertas = getContext().getRequest().getParameter("idKertas");
        textLaporanPemantauan = getContext().getRequest().getParameter("textLaporanPemantauan");
        jenisLaporanV2 = getContext().getRequest().getParameter("jenisLaporanV2");

        logger.info("folderId : " + folderId + " idPermohonan : " + idPermohonan + " dokumenKod : " + dokumenKod + " idHakmilik : " + idHakmilik + " idRujukan : " + idRujukan + " idBarang : " + idBarang + " idDokumen : " + idDokumen + " idKertas : " + idKertas + " jenisLaporanV2 : " + jenisLaporanV2);

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        KodDokumen kodDokumen = kodDokumenDAO.findById(dokumenKod);

        if (idDokumen != null && StringUtils.isNotBlank(idDokumen)) {
            logger.info("existing dokumen");
            d = dokumenDAO.findById(Long.parseLong(idDokumen));
            ia = d.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            logger.info("new dokumen");
            d = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        }
        d.setInfoAudit(ia);
        d.setTajuk(kodDokumen.getNama());
        d.setNoVersi("1.0");
        d.setKodDokumen(kodDokumen);
        d.setKlasifikasi(klasifikasiAm);

        kf = kandunganFolderService.findByDokumen(d, permohonan.getFolderDokumen());
        if (kf == null) {
            kf = new KandunganFolder();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        kf.setFolder(permohonan.getFolderDokumen());
        kf.setDokumen(d);
        kf.setInfoAudit(ia);

        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        kandunganFolderDAO.save(kf);

        if (dokumenKod.startsWith("I") && !dokumenKod.matches("IB")) {
            logger.info("laporan tanah");
            LaporanTanah laporanTanah;
            if (idLaporan.matches("")) {
                laporanTanah = new LaporanTanah();
                laporanTanah.setInfoAudit(ia);
                laporanTanah.setPermohonan(permohonan);
                laporanTanahDAO.saveOrUpdate(laporanTanah);

            } else {
                laporanTanah = laporanTanahDAO.findById(Long.parseLong(idLaporan));
            }
            ImejLaporan imejLaporan = new ImejLaporan();
            imejLaporan.setCawangan(peng.getKodCawangan());
            imejLaporan.setDokumen(d);
            if (dokumenKod.length() > 2) {
                imejLaporan.setPandanganImej(dokumenKod.charAt(2));
            }
//            if (StringUtils.isNotBlank(idHakmilik)) {
//                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
//                if (hakmilik != null) {
//                    imejLaporan.setHakmilik(hakmilik);
//                }
//            }
            if(idHakmilik != null){
                hakmilik = hakmilikDAO.findById(idHakmilik);
                imejLaporan.setHakmilik(hakmilik);
                 logger.info("Hakmilik" + hakmilik);
            }
            imejLaporan.setLaporanTanah(laporanTanah);
            imejLaporan.setInfoAudit(ia);
            imejLaporan.setCatatan(catatan);
            imejLaporanDAO.save(imejLaporan);
        }

        if (dokumenKod.matches("LPM")) {
            logger.info("laporan pemantauan --  start");
            PermohonanKertas kertas;
            if (idLaporan.matches("")) {
                kertas = new PermohonanKertas();
                kertas.setCawangan(peng.getKodCawangan());
                kertas.setPermohonan(permohonan);
                kertas.setKodDokumen(kodDokumenDAO.findById("LPM"));
                kertas.setInfoAudit(ia);
                kertas = enforceService.simpanPermohonanKertas2(kertas);
            } else {
                kertas = enforceService.findByIdKrts(idPermohonan, "LPM");
            }

            PermohonanKertasKandungan kertasKandungan;
            if (idRujukan.matches("") || idRujukan.equalsIgnoreCase("undefined")) {
                kertasKandungan = new PermohonanKertasKandungan();
                kertasKandungan.setCawangan(peng.getKodCawangan());
                kertasKandungan.setKertas(kertas);
                kertasKandungan.setBil(kertas.getSenaraiKandungan().size() + 1);
                kertasKandungan.setInfoAudit(ia);
                if (textLaporanPemantauan == null) {
                    textLaporanPemantauan = " ";
                }
                kertasKandungan.setKandungan(textLaporanPemantauan);
                kertasKandungan = enforceService.simpanPermohonanKertasKandungan2(kertasKandungan);
            } else {
                kertasKandungan = enforceService.findByIdKandungan(Long.parseLong(idRujukan));
            }

            PermohonanKertasImej kertasImej = new PermohonanKertasImej();
            kertasImej.setCawangan(peng.getKodCawangan());
            kertasImej.setKertasKandungan(kertasKandungan);
            kertasImej.setDokumen(d);
            if (catatan == null) {
                catatan = " ";
            }
            kertasImej.setCatatan(catatan);
            kertasImej.setInfoAudit(ia);
            enforceService.simpanPermohonanKertasImej(kertasImej);
            logger.info("laporan pemantauan -- end");
        }
        if (dokumenKod.matches("IB")) {
            BarangRampasan barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));
            ia = barangRampasan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            barangRampasan.setImej(d);
            barangRampasan.setInfoAudit(ia);
            barangRampasanDAO.saveOrUpdate(barangRampasan);
        }

        if (dokumenKod.matches("MDPP")) {
            Dokumen dok = dokumenDAO.findById(Long.parseLong(idDokumen));
            ia = dok.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            dok.setInfoAudit(ia);
            dokumenDAO.saveOrUpdate(dok);
        }

        if (dokumenKod.matches("KS")) {
            PermohonanKertas permohonanKertas;
            if (StringUtils.isBlank(idKertas)) {
                permohonanKertas = new PermohonanKertas();
                permohonanKertas.setTajuk("Draf Kertas Siasatan");
                permohonanKertas.setInfoAudit(ia);
                permohonanKertas.setCawangan(peng.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
            } else {
                permohonanKertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            }
            permohonanKertas.setDokumen(d);
            permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        }

        if (d != null) {
            getContext().getRequest().setAttribute("dokumenId", d.getIdDokumen());
            tx.commit();
        } else {
            tx.rollback();
        }
        //return new ErrorResolution(404, "Dokumen tidak ditentukan.");

        d = dokumenDAO.saveOrUpdate(d);
        kandunganFolderDAO.save(kf);

        String dokumenId = Long.toString(d.getIdDokumen());

        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();

                String contentType = fileToBeUpload.getContentType();

                logger.debug("content type = " + contentType);

                permohonan = permohonanDAO.findById(idPermohonan);

                DMSUtil dmsUtil = new DMSUtil();

                if (permohonan != null) {

                    FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                    fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                    String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                    updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), contentType, catatan, idRujukan);

                } else {
                    addSimpleMessage("Muat naik tidak berjaya.");
                    if (getContext().getRequest().getParameter("prm") != null) {
                        getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                        getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                    }
                    return new JSP("penguatkuasaan/popup_upload.jsp").addParameter("popup", "true");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                addSimpleMessage("Muat naik tidak berjaya.");
                if (getContext().getRequest().getParameter("prm") != null) {
                    getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                    getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                }
                return new JSP("penguatkuasaan/popup_upload.jsp").addParameter("popup", "true");
            }
        }
        addSimpleMessage("Muat naik fail berjaya.");
        if (getContext().getRequest().getParameter("prm") != null) {
            logger.info("get dokumen kod");
            getContext().getRequest().setAttribute("dokumenKod", getContext().getRequest().getParameter("dokumenKod"));
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
            getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
        }

        return new JSP("pembangunan/utiliti/popup_upload.jsp").addParameter("popup", "true");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format, String catatan, String idRujukan) {
        logger.info("updatePathDokumen");
        System.out.println("format : " + format);
        System.out.println("catatan : " + catatan);
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        if (StringUtils.isNotBlank(catatan)) {
            d.setTajuk(catatan);
        }
        System.out.println("id rujukan : " + idRujukan);
        if (StringUtils.isNotBlank(idRujukan)) {
            if (d.getKodDokumen().getKod().equalsIgnoreCase("LP")) {
                d.setDalamanNilai1(idRujukan);
            } else {
                d.setPerihal(idRujukan);
            }
        }
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getDokumenKod() {
        return dokumenKod;
    }

    public void setDokumenKod(String dokumenKod) {
        this.dokumenKod = dokumenKod;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(String idLaporan) {
        this.idLaporan = idLaporan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public Dokumen getD() {
        return d;
    }

    public void setD(Dokumen d) {
        this.d = d;
    }

    public KandunganFolder getKf() {
        return kf;
    }

    public void setKf(KandunganFolder kf) {
        this.kf = kf;
    }

    public String getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(String idKertas) {
        this.idKertas = idKertas;
    }

    public String getIdOperasiAgensi() {
        return idOperasiAgensi;
    }

    public void setIdOperasiAgensi(String idOperasiAgensi) {
        this.idOperasiAgensi = idOperasiAgensi;
    }

    public String getTextLaporanPemantauan() {
        return textLaporanPemantauan;
    }

    public void setTextLaporanPemantauan(String textLaporanPemantauan) {
        this.textLaporanPemantauan = textLaporanPemantauan;
    }

    public String getJenisLaporanV2() {
        return jenisLaporanV2;
    }

    public void setJenisLaporanV2(String jenisLaporanV2) {
        this.jenisLaporanV2 = jenisLaporanV2;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
       
}



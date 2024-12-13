/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.util.FileUtil;
import etanah.view.dokumen.FolderAction;
import etanah.model.KodDokumen;
import etanah.dao.KodDokumenDAO;
import etanah.model.KodKlasifikasi;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.model.BarangRampasan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.model.Hakmilik;
import etanah.model.ImejLaporan;
import etanah.model.LaporanTanah;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.dao.PermohonanDAO;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodRujukan;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.PermohonanRujukanLuar;
import java.io.File;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.view.etanahActionBeanContext;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/upload")
public class UploadActionBean extends AbleActionBean {

    FileBean fileToBeUpload;
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

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    @DefaultHandler
    public Resolution muatNaikForm1() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        String folderId = getContext().getRequest().getParameter("folderId");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenKod = getContext().getRequest().getParameter("dokumenKod");
        String idBarang = getContext().getRequest().getParameter("idBarang");
        String idLaporan = getContext().getRequest().getParameter("idLaporan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idKertas = getContext().getRequest().getParameter("idKertas");
        String idRujukan = getContext().getRequest().getParameter("idRujukan");
        String idOperasi = getContext().getRequest().getParameter("idOperasi");

        if (StringUtils.isNotBlank(folderId)) {
            getContext().getRequest().setAttribute("folderId", folderId);
        } else {
            return new ErrorResolution(404, "Folder tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(dokumenId)) {
            getContext().getRequest().setAttribute("dokumenId", dokumenId);
        } else {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
            KodDokumen kodDokumen = kodDokumenDAO.findById(dokumenKod);
            Dokumen d = new Dokumen();
            d.setInfoAudit(ia);
            d.setTajuk(kodDokumen.getNama());
            d.setNoVersi("1.0");
            d.setKodDokumen(kodDokumen);
            d.setKlasifikasi(klasifikasiAm);

            KandunganFolder kf = new KandunganFolder();
            kf.setFolder(permohonan.getFolderDokumen());
            kf.setDokumen(d);
            kf.setInfoAudit(ia);

            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();
            d = dokumenDAO.saveOrUpdate(d);
            kandunganFolderDAO.save(kf);

            if (dokumenKod.matches("IB")) {
                BarangRampasan barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));
                barangRampasan.setImej(d);
                barangRampasan.setInfoAudit(ia);
                barangRampasanDAO.saveOrUpdate(barangRampasan);
            }
            if (dokumenKod.startsWith("I") && !dokumenKod.matches("IB")) {
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
                if (StringUtils.isNotBlank(idHakmilik)) {
                    Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                    if (hakmilik != null) {
                        imejLaporan.setHakmilik(hakmilik);
                    }
                }
                imejLaporan.setLaporanTanah(laporanTanah);
                imejLaporan.setInfoAudit(ia);
                imejLaporanDAO.save(imejLaporan);
            }
            if (dokumenKod.matches("KS")) {
                PermohonanKertas permohonanKertas;
                if (idKertas.matches("")) {
                    permohonanKertas = new PermohonanKertas();
                    permohonanKertas.setTajuk("Hak Lalu Lalang Persendirian");
                    permohonanKertas.setInfoAudit(ia);
                    permohonanKertas.setCawangan(peng.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                } else {
                    permohonanKertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
                }
                permohonanKertas.setDokumen(d);
                permohonanKertasDAO.saveOrUpdate(permohonanKertas);
            }
            if (dokumenKod.matches("LP")) {
                PermohonanRujukanLuar permohonanRujukanLuar;
                if (idRujukan.matches("")) {
                    permohonanRujukanLuar = new PermohonanRujukanLuar();
                    permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujukanLuar.setInfoAudit(ia);
                    permohonanRujukanLuar.setPermohonan(permohonan);
                    KodRujukan kodRujukan =  new KodRujukan();
                    kodRujukan.setKod("NF");
                    permohonanRujukanLuar.setKodRujukan(kodRujukan);
                    KodAgensi kodAgensi = new KodAgensi();
                    kodAgensi.setKod("0302");
                    permohonanRujukanLuar.setAgensi(kodAgensi);
                } else {
                    permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
                }
                permohonanRujukanLuar.setDokumen(d);
                permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
            }
            if (dokumenKod.matches("KMD")) {
                PermohonanRujukanLuar permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
                permohonanRujukanLuar.setDokumen(d);
                permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
            }
            if (dokumenKod.matches("LO")) {
                if (idOperasi.matches("")) {
                    OperasiPenguatkuasaan operasiPenguatkuasaan = new OperasiPenguatkuasaan();
                    operasiPenguatkuasaan.setCawangan(peng.getKodCawangan());
                    operasiPenguatkuasaan.setPermohonan(permohonan);
                    operasiPenguatkuasaan.setInfoAudit(ia);
                    operasiPenguatkuasaanDAO.save(operasiPenguatkuasaan);
                }
            }
            if (d != null) {
                getContext().getRequest().setAttribute("dokumenId", d.getIdDokumen());
                tx.commit();
            } else {
                tx.rollback();
            }
            //return new ErrorResolution(404, "Dokumen tidak ditentukan.");
        }
        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        } else {
            return new ErrorResolution(404, "Tiada Id Permohonan.");
        }
        return new JSP("pengambilan/upload.jsp").addParameter("popup", "true");
    }

    public Resolution processUpload() {
        Logger.getLogger(UploadActionBean.class).info("simpanMuatNaik::start");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String folderId = getContext().getRequest().getParameter("folderId");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenPath = conf.getProperty("document.path");
        String catatan =getContext().getRequest().getParameter("catatan");
        if (fileToBeUpload != null) {
            try {
                System.out.println("no null::" + fileToBeUpload.getContentType());
                dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + folderId;
                FileUtil fileUtil = new FileUtil(dokumenPath);
                fileUtil.saveFile(dokumenId, fileToBeUpload.getInputStream());
                String fizikalPath = folderId + File.separator + dokumenId;
                updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), fileToBeUpload.getContentType(), catatan);
            } catch (Exception ex) {
                Logger.getLogger(UploadActionBean.class).error(ex);
            }
        }
        addSimpleMessage("Muat naik fail berjaya.");
        return new JSP("pengambilan/upload.jsp").addParameter("popup", "true");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format, String catatan) {
        System.out.println("updatePathDokumen");
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        if (catatan!=null) d.setTajuk(catatan);
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public Resolution reload1() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        return new RedirectResolution(KemasukanAduanActionBean.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
    }
}

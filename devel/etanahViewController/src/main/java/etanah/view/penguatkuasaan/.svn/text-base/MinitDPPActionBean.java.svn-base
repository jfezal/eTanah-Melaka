/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.EnforceService;
import etanah.service.common.KandunganFolderService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.util.bean.ParseException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Siti Fariza Hanim
 */
@UrlBinding("/penguatkuasaan/minitDPP")
public class MinitDPPActionBean extends AbleActionBean {

    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    FileBean fileToBeUploaded;
    private static final Logger LOG = Logger.getLogger(MinitDPPActionBean.class);
    private List<Dokumen> senaraiMinit;
    private Dokumen dokumen;
    private KodDokumen kodDokumen;
    private String idDokumen;
    private String catatanMinit;
    private String tajuk;
    private String perihal;
    private Permohonan permohonan;
    private String idPermohonan;
    private String recordCount;
    private Long idOperasi;
    private String kod;
    private KandunganFolder kf;
    private String namaFizikal;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private AduanOrangKenaSyak aduan;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/minitDPP.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/minitDPP.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------rehydrate--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiMinit = enforceService.findByIdDokumen("MDPP");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {

                if (senaraiMinit != null) {
                    System.out.println("senaraiMinit" + senaraiMinit.size());
                    recordCount = String.valueOf(senaraiMinit.size());
//                    senaraiMinit = enforceService.findByIdDokumen("MDPP");
                    System.out.println("mana record");
                }
            } catch (Exception ex) {
                LOG.error(ex);
                ex.printStackTrace();
            }

        }

    }

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiOksIp = enforceService.getListAduanOrangkenaSyak(idPermohonan);
        if (senaraiOksIp.size() != 0) {
                idOperasi = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                System.out.println("id operasi : " + idOperasi);

            }

        int row = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
        for (int i = 0; i < row; i++) {
            String dokumen1 = (getContext().getRequest().getParameter("idDokumen" + i));
            LOG.info("THIS IS ID DOKUMEN ->" + dokumen1);
            if (!StringUtils.isEmpty(dokumen1)) {
                dokumen = dokumenDAO.findById(Long.parseLong(dokumen1));
                if (dokumen != null) {
                    dokumen.setInfoAudit(disLaporanTanahService.findAudit(dokumen, "update", peng));
                } else {
                    dokumen = new Dokumen();
                    dokumen.setInfoAudit(disLaporanTanahService.findAudit(dokumen, "new", peng));
                }
            } else {
                dokumen = new Dokumen();
                dokumen.setInfoAudit(disLaporanTanahService.findAudit(dokumen, "new", peng));
            }

            tajuk = getContext().getRequest().getParameter("tajuk" + i);
            System.out.println("aaaaaaaa");
            catatanMinit = getContext().getRequest().getParameter("catatanMinit" + i);
            System.out.println("bbbbbbb");
            kod = getContext().getRequest().getParameter("kod" + i);
            System.out.println("ccccccc");


            LOG.info("tajuk : " + tajuk);
            //dokumen.setInfoAudit(ia);
            dokumen.setTajuk(tajuk);
            LOG.info("catatanMinit : " + catatanMinit);
            dokumen.setCatatanMinit(catatanMinit);
            kod = "MDPP";
            dokumen.setKodDokumen(kodDokumenDAO.findById(kod));
            dokumen.setPerihal(permohonan.getIdPermohonan()+ idOperasi );
            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
            dokumen.setNoVersi("1.0");
            dokumen.setKlasifikasi(klasifikasiAm);
//            dokumen.setImej(d);

            enforceService.simpanDokumen(dokumen);

        }

        boolean flag = true;

        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/minitDPP.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(MinitDPPActionBean.class, "locate");
    }

    public Resolution deleteDokumen() {
        idDokumen = getContext().getRequest().getParameter("idDokumen");

        if(idDokumen != null){
            dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
        }
        enforceService.deleteDokumen(dokumen);
        addSimpleMessage("Maklumat telah berjaya dihapuskan.");
        rehydrate();
        return showForm();
    }

    public Resolution popupUpload() {

        idDokumen = getContext().getRequest().getParameter("idDokumen");
        LOG.info("idDokumen :" + idDokumen);
        return new JSP("penguatkuasaan/minitDPP_upload.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        System.out.println("nnnnnn");
        idDokumen = getContext().getRequest().getParameter("idDokumen");
        System.out.println("mmmmmmm");
        kodDokumen = kodDokumenDAO.findById("MDPP"); // FIXME : MDPP = MinitDPP
        dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
        System.out.println("idDokumen" + idDokumen);
        String dokumenPath = conf.getProperty("document.path");
        String folderId = Long.toString(permohonan.getFolderDokumen().getFolderId());
        System.out.println("ccccccc");
        InfoAudit ia = new InfoAudit();

        if (idDokumen != null && StringUtils.isNotBlank(idDokumen)) {
            LOG.info("existing dokumen");
            System.out.println("zzzzzz");
            ia = dokumen.getInfoAudit();
            ia.setDiKemaskiniOleh(p);
            ia.setTarikhKemaskini(new java.util.Date());
            dokumen.setInfoAudit(ia);
        }
//            else {
//            LOG.info("new dokumen");
//            System.out.println("ppppp");
//            dokumen = new Dokumen();
//            ia.setDimasukOleh(p);
//            ia.setTarikhMasuk(new java.util.Date());
//            System.out.println("lllll");
//        }



        kf = kandunganFolderService.findByDokumen(dokumen, permohonan.getFolderDokumen());
        System.out.println("permohonan.getFolderDokumen1111" + permohonan.getFolderDokumen());
        System.out.println("dokumennnnnnnnnn" + dokumen);
        if (kf == null) {
            kf = new KandunganFolder();
            ia.setDimasukOleh(p);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(p);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setFolder(permohonan.getFolderDokumen());
        System.out.println("permohonan.getFolderDokumen2222222" + permohonan.getFolderDokumen());
        kf.setDokumen(dokumen);
        kf.setInfoAudit(ia);
        System.out.println("kfffffffff");

        dokumen = dokumenDAO.saveOrUpdate(dokumen);
        kandunganFolderDAO.save(kf);

//        String dokumenId = Long.toString(dokumen.getIdDokumen());
        idDokumen = getContext().getRequest().getParameter("idDokumen");
        dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
        System.out.println("3333333333" + idDokumen);
        if (fileToBeUploaded != null) {
            try {

                DMSUtil dmsUtil = new DMSUtil();
                String fileName = fileToBeUploaded.getFileName();
                System.out.println("no null::" + fileToBeUploaded.getContentType());
                dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + folderId;
                FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                fileUtil.saveFile(fileName, fileToBeUploaded.getInputStream());
                String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;

                if (idDokumen != null && StringUtils.isNotBlank(idDokumen)) {
                    LOG.info("existing dokumen");
                    dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
                    ia = dokumen.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    dokumen.setInfoAudit(ia);
                } else {
                    LOG.info("new dokumen");
                    dokumen = new Dokumen();
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }


                dokumen.setNamaFizikal(namaFizikal);

                enforceService.editDokumen(dokumen);

                updatePathDokumen(fizikalPath, Long.parseLong(idDokumen), fileToBeUploaded.getContentType());
                System.out.println("fizikalPath" + fizikalPath);
            } catch (Exception ex) {
                Logger.getLogger(UploadAction1.class).error(ex);
            }
        }
        addSimpleMessage("Muat naik fail berjaya.");
        return new JSP("penguatkuasaan/minitDPP_upload.jsp").addParameter("popup", "true");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        System.out.println("updatePathDokumen");
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        d = dokumenDAO.saveOrUpdate(d);


        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public Resolution deleteSelected1() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idDokumen = getContext().getRequest().getParameter("idDokumen");
        dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            dokumen.setNamaFizikal(null);
            InfoAudit ia = dokumen.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            dokumen.setInfoAudit(ia);
            enforceService.editDokumen(dokumen);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(MinitDPPActionBean.class, "locate");
    }

    public String getCatatanMinit() {
        return catatanMinit;
    }

    public void setCatatanMinit(String catatanMinit) {
        this.catatanMinit = catatanMinit;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
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

    public List<Dokumen> getSenaraiMinit() {
        return senaraiMinit;
    }

    public void setSenaraiMinit(List<Dokumen> senaraiMinit) {
        this.senaraiMinit = senaraiMinit;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public Long getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(Long idOperasi) {
        this.idOperasi = idOperasi;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public KandunganFolder getKf() {
        return kf;
    }

    public void setKf(KandunganFolder kf) {
        this.kf = kf;
    }

    public String getNamaFizikal() {
        return namaFizikal;
    }

    public void setNamaFizikal(String namaFizikal) {
        this.namaFizikal = namaFizikal;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public AduanOrangKenaSyak getAduan() {
        return aduan;
    }

    public void setAduan(AduanOrangKenaSyak aduan) {
        this.aduan = aduan;
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.DokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.OperasiAgensi;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import org.hibernate.Session;
import com.google.inject.Inject;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodDokumenDAO;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.BarangRampasan;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodKlasifikasi;
import etanah.model.Permohonan;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_laporan_polis")
public class MaklumatLaporanPolisActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatLaporanPolisActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    DokumenService dokumenService;
    private Permohonan permohonan;
    private String idPermohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private String kodNegeri;
    private List<OperasiPenguatkuasaan> listOp;
    private Pengguna pengguna;
    private Boolean opFlag = false;
    private String noRujukan;
    private String tarikhRujukan;
    private String jam;
    private String minit;
    private String ampm;
    private String idOperasi;
    private OperasiAgensi operasiAgensi;
    private KandunganFolder kf;
    private String lokasi;
    private String idLaporanPolis;
    private String idDokumen;
    private List<KandunganFolder> listDokumen;
    private KandunganFolder folderDokumen;
    private String idOperasiAgensi;
    private List<AduanOrangKenaSyak> senaraiOksIp;

    @DefaultHandler
    public Resolution showForm() {
        if (opFlag == false) {
            addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_polis.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_polis.jsp").addParameter("tab", "true");
    }

    public Resolution popupViewLaporanOperasi() {
        idOperasiAgensi = getContext().getRequest().getParameter("idOperasiAgensi");
        operasiAgensi = enforcementService.findOperasiByIdOPAgensi(Long.parseLong(idOperasiAgensi));
        return new JSP("penguatkuasaan/view_laporan_polis_detail.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("-------------rehydrate------------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                    listOp = enforceService.findListLaporanOperasi(idPermohonan);
                    System.out.println("listOp size : " + listOp.size());
                    if (listOp.size() != 0) {
                        System.out.println("opFlag true");
                        opFlag = true;

                        //get senarai dokumen
                        listDokumen = enforcementService.getListDokumen(permohonan.getFolderDokumen().getFolderId(), "LP");
                        System.out.println("size list dokumen : " + listDokumen.size());
                        if (listDokumen.size() != 0) {
                            folderDokumen = listDokumen.get(0);
                        }
                    } else {
                        if (permohonan.getPermohonanSebelum() != null) {
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            opFlag = true;
                            System.out.println("opFlag ---- : " + opFlag);
                            listDokumen = enforcementService.getListDokumen(permohonan.getPermohonanSebelum().getFolderDokumen().getFolderId(), "LP");
                            System.out.println("size list dokumen (id sebelum): " + listDokumen.size());


                            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(idPermohonan);
                            logger.info("size senaraiOksIp : " + senaraiOksIp.size());

                            if (senaraiOksIp.size() != 0) {
                                Long idOpIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                                logger.info("id operasi : " + idOpIP);
                                listOp = enforcementService.findListLaporanOperasi(idOpIP);

                            }
                        }
                    }

                    if (listOp.size() == 0) {
                        if (permohonan.getPermohonanSebelum() != null) {
                            Permohonan p = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                            String idPermohonanAsal = p.getIdPermohonan();
                            logger.info("p :::" + p.getIdPermohonan());
                            if (p != null) {
                                if (p.getPermohonanSebelum() != null) {
                                    Permohonan p1 = permohonanDAO.findById(p.getPermohonanSebelum().getIdPermohonan());
                                    if (p1 != null) {
                                        logger.info("p1 :::" + p1.getIdPermohonan());
                                        idPermohonanAsal = p1.getIdPermohonan();
                                    }
                                }
                            }
                            logger.info("::: idPermohonanAsal : " + idPermohonanAsal);
                            listOp = enforceService.findListLaporanOperasi(idPermohonanAsal);
                            logger.info("size senaraiOperasiPenguatkuasaan : " + listOp.size());
                        }
                    }
                }
            }
        }
    }

    public Resolution addRecord() {
        idOperasi = getContext().getRequest().getParameter("idOp");
        logger.info("::add record - id OP : " + idOperasi);
        return new JSP("penguatkuasaan/popup_tambah_laporan_polis.jsp").addParameter("popup", "true");
    }

    public Resolution editRecord() {
        logger.info("--------------editRecord--------------");
        idOperasiAgensi = getContext().getRequest().getParameter("idOperasiAgensi");
        idDokumen = getContext().getRequest().getParameter("idDokumen");

        if (idOperasiAgensi != null) {
            operasiAgensi = enforcementService.findOperasiByIdOPAgensi(Long.parseLong(idOperasiAgensi));
            if (operasiAgensi != null) {
                lokasi = operasiAgensi.getAlamatAgensi();
                try {
                    if (operasiAgensi.getTarikhRujukan() != null) {
                        tarikhRujukan = sdf1.format(operasiAgensi.getTarikhRujukan()).substring(0, 10);
                        jam = sdf1.format(operasiAgensi.getTarikhRujukan()).substring(11, 13);
                        if (jam.startsWith("0")) {
                            jam = sdf1.format(operasiAgensi.getTarikhRujukan()).substring(12, 13);
                        }
                        minit = sdf1.format(operasiAgensi.getTarikhRujukan()).substring(14, 16);
                        ampm = sdf1.format(operasiAgensi.getTarikhRujukan()).substring(17, 19);
                        System.out.println("jam : " + jam + "minit : " + minit + "ampm : " + ampm);
                    }

                    noRujukan = operasiAgensi.getNoRujukan();

                } catch (Exception e) {
                    logger.error(e);
                    e.printStackTrace();
                }
            }
        }

//        if (idDokumen != null) {
//            Dokumen d = dokumenService.findById(Long.parseLong(idDokumen));
//            if (d != null) {
//                try {
//                    if (d.getTarikhDokumen() != null) {
//                        tarikhRujukan = sdf1.format(d.getTarikhDokumen()).substring(0, 10);
//                        jam = sdf1.format(d.getTarikhDokumen()).substring(11, 13);
//                        if (jam.startsWith("0")) {
//                            jam = sdf1.format(d.getTarikhDokumen()).substring(12, 13);
//                        }
//                        minit = sdf1.format(d.getTarikhDokumen()).substring(14, 16);
//                        ampm = sdf1.format(d.getTarikhDokumen()).substring(17, 19);
//                        System.out.println("jam : " + jam + "minit : " + minit + "ampm : " + ampm);
//                    }
//
//                    noRujukan = d.getNoRujukan();
//
//                } catch (Exception e) {
//                    logger.error(e);
//                    e.printStackTrace();
//                }
//
//            }
//        }
//        logger.info("::edit record - id OP : " + idLaporanPolis + " id dokumen : " + idDokumen);

        return new JSP("penguatkuasaan/popup_tambah_laporan_polis.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {
        logger.info("--------------simpan--------------");
        idOperasi = getContext().getRequest().getParameter("idOperasi");
        idOperasiAgensi = getContext().getRequest().getParameter("idOperasiAgensi");

        if (idOperasi != null && StringUtils.isNotBlank(idOperasi)) {
            logger.info("id operasi: " + idOperasi);
            operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));

            InfoAudit ia = new InfoAudit();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(pengguna);

            KodAgensi kodAgensi = kodAgensiDAO.findById("0302"); //0302 = POLIS DIRAJA MALAYSIA (PDRM)
            String noRujukan = new String();
            noRujukan = getContext().getRequest().getParameter("noRujukan");
            operasiAgensi = new OperasiAgensi();

            operasiAgensi.setOperasi(operasiPenguatkuasaan);
            operasiAgensi.setCawangan(pengguna.getKodCawangan());
            operasiAgensi.setInfoAudit(ia);
            operasiAgensi.setAgensi(kodAgensi);
            operasiAgensi.setNoRujukan(noRujukan);
            try {
                if (tarikhRujukan != null) {
                    tarikhRujukan = tarikhRujukan + " " + jam + ":" + minit + " " + ampm;
                    operasiAgensi.setTarikhRujukan(sdf1.parse(tarikhRujukan));
                }

            } catch (Exception e) {
                logger.error(e);
            }

            operasiAgensi.setAlamatAgensi(lokasi);
            enforceService.updateOperasiAgensi(operasiAgensi);


//            Dokumen d = new Dokumen();
//            KodDokumen kodDokumen = kodDokumenDAO.findById("LP");//LP = laporan polis
//            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
//
//            d.setInfoAudit(ia);
//            d.setTajuk(kodDokumen.getNama());
//            d.setNoVersi("1.0");
//            d.setKodDokumen(kodDokumen);
//            d.setKlasifikasi(klasifikasiAm);
////            d.setPerihal(Long.toString(operasiAgensi.getIdOperasiAgensi()));
//            d.setDalamanNilai1(Long.toString(operasiAgensi.getIdOperasiAgensi()));
//            dokumenService.saveOrUpdate(d);
//
////            logger.info("id dokumen (default for agensi) : " + d.getIdDokumen());
//
//            kf = kandunganFolderService.findByDokumen(d, permohonan.getFolderDokumen());
//            if (kf == null) {
//                kf = new KandunganFolder();
//                ia.setDimasukOleh(pengguna);
//                ia.setTarikhMasuk(new java.util.Date());
//            } else {
//                ia = kf.getInfoAudit();
//                ia.setDiKemaskiniOleh(pengguna);
//                ia.setTarikhKemaskini(new java.util.Date());
//            }
//
//            kf.setFolder(permohonan.getFolderDokumen());
//            kf.setDokumen(d);
//            kf.setInfoAudit(ia);
//
//            dokumenService.saveKandunganWithDokumen(kf);

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_polis.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskini() {
        logger.info("--------------kemaskini--------------");
        idLaporanPolis = getContext().getRequest().getParameter("idLaporanPolis");
        idOperasiAgensi = getContext().getRequest().getParameter("idOperasiAgensi");
        idDokumen = getContext().getRequest().getParameter("idDokumen");

        InfoAudit ia = new InfoAudit();

        if (idOperasiAgensi != null && StringUtils.isNotBlank(idOperasiAgensi)) {
            operasiAgensi = enforcementService.findOperasiByIdOPAgensi(Long.parseLong(idOperasiAgensi));
            if (operasiAgensi != null) {
                operasiAgensi.setAlamatAgensi(lokasi);

                ia = operasiAgensi.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());

                operasiAgensi.setInfoAudit(ia);
                operasiAgensi.setNoRujukan(noRujukan);//no report polis
                try {
                    if (tarikhRujukan != null) {
                        tarikhRujukan = tarikhRujukan + " " + jam + ":" + minit + " " + ampm;
                        operasiAgensi.setTarikhRujukan(sdf1.parse(tarikhRujukan));
                    }

                } catch (Exception e) {
                    logger.error(e);
                }
                enforceService.updateOperasiAgensi(operasiAgensi);
            }
        }

//        if (idDokumen != null && StringUtils.isNotBlank(idDokumen)) {
//            Dokumen d = dokumenService.findById(Long.parseLong(idDokumen));
//            if (d != null) {
//                d.setNoRujukan(noRujukan);//no report polis
//
//                try {
//                    if (tarikhRujukan != null) {
//                        tarikhRujukan = tarikhRujukan + " " + jam + ":" + minit + " " + ampm;
//                        d.setTarikhDokumen(sdf1.parse(tarikhRujukan));
//                    }
//
//                } catch (Exception e) {
//                    logger.error(e);
//                }
//
//                dokumenService.saveOrUpdate(d);
//            }
//        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_polis.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRecord() {
        logger.info("--------------deleteRecord--------------");
        idOperasiAgensi = getContext().getRequest().getParameter("idOperasiAgensi");

        try {
            if (idOperasiAgensi != null && StringUtils.isNotBlank(idOperasiAgensi)) {
                logger.info("id Op agensi : " + idOperasiAgensi);
                operasiAgensi = enforcementService.findOperasiByIdOPAgensi(Long.parseLong(idOperasiAgensi));

                if (operasiAgensi != null) {

                    List<BarangRampasan> barangRampasanList = operasiAgensi.getSenaraiBarangRampasan();
                    for (int i = 0; i < barangRampasanList.size(); i++) {
                        BarangRampasan barang = barangRampasanDAO.findById(barangRampasanList.get(i).getIdBarang());

                        if (barang != null) {
                            barang.setOperasiAgensi(null);
                            enforceService.updateBarangRampasan(barang);
                        }
                    }
                    enforceService.deleteOperasiAgensi(operasiAgensi);
                }
            }

            //Delete image from dokumen
            listDokumen = enforcementService.getListDokumen(permohonan.getFolderDokumen().getFolderId(), "LP");
            if (listDokumen.size() != 0) {
                for (int i = 0; i < listDokumen.size(); i++) {
                    logger.info("::id Dokumen to delete : " + listDokumen.get(i).getDokumen().getIdDokumen());
                    Dokumen d = dokumenService.findById(listDokumen.get(i).getDokumen().getIdDokumen());

                    if (d != null) {
                        logger.info("id in   : " + d.getDalamanNilai1());
                        if (idOperasiAgensi.equalsIgnoreCase(d.getDalamanNilai1())) {
                            dokumenService.delete(d);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        return new RedirectResolution(MaklumatLaporanPolisActionBean.class, "locate");
    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));

        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            if (dok1 != null) {
                InfoAudit ia = dok1.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                dok1.setInfoAudit(ia);
            }
            dok1.setNamaFizikal(null);
            dokumenService.saveOrUpdate(dok1);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatLaporanPolisActionBean.class, "locate");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(MaklumatLaporanPolisActionBean.class, "locate");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public Boolean getOpFlag() {
        return opFlag;
    }

    public void setOpFlag(Boolean opFlag) {
        this.opFlag = opFlag;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public OperasiAgensi getOperasiAgensi() {
        return operasiAgensi;
    }

    public void setOperasiAgensi(OperasiAgensi operasiAgensi) {
        this.operasiAgensi = operasiAgensi;
    }

    public String getIdLaporanPolis() {
        return idLaporanPolis;
    }

    public void setIdLaporanPolis(String idLaporanPolis) {
        this.idLaporanPolis = idLaporanPolis;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public List<KandunganFolder> getListDokumen() {
        return listDokumen;
    }

    public void setListDokumen(List<KandunganFolder> listDokumen) {
        this.listDokumen = listDokumen;
    }

    public KandunganFolder getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(KandunganFolder folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public KandunganFolder getKf() {
        return kf;
    }

    public void setKf(KandunganFolder kf) {
        this.kf = kf;
    }

    public String getIdOperasiAgensi() {
        return idOperasiAgensi;
    }

    public void setIdOperasiAgensi(String idOperasiAgensi) {
        this.idOperasiAgensi = idOperasiAgensi;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }
}

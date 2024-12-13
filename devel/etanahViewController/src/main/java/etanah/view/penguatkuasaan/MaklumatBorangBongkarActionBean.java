/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import org.hibernate.Session;
import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.BongkarKehadiranDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodRujukanDAO;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.BarangRampasan;
import etanah.model.BongkarKehadiran;
import etanah.model.InfoAudit;
import etanah.model.Permohonan;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.Pengguna;
import etanah.model.PermohonanRujukanLuar;
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
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.util.StringUtil;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/borang_bongkar")
public class MaklumatBorangBongkarActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatBorangBongkarActionBean.class);
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
    KodAgensiDAO kodAgensiDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    BongkarKehadiranDAO bongkarKehadiranDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
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
    private String tarikhBongkar;
    private String tempatBongkar;
    private String jam;
    private String minit;
    private String ampm;
    private String idOperasi;
    private List<BarangRampasan> senaraiKenderaanOks;
    private List<BarangRampasan> senaraiBarangOks;
    private String idOks;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujLuar;
    private String rowCount;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Boolean statusIP = Boolean.FALSE;
    private List<BongkarKehadiran> listBongkarKehadiran = new ArrayList<BongkarKehadiran>();
    private String rowCountOrangHadir;
    private BongkarKehadiran bongkarKehadiran;

    @DefaultHandler
    public Resolution showForm() {
        if (opFlag == false) {
            addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_borang_bongkar.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_borang_bongkar.jsp").addParameter("tab", "true");
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
                    } else {
                        if (permohonan.getPermohonanSebelum() != null) {
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            opFlag = true;
                            System.out.println("opFlag ---- : " + opFlag);

                            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(idPermohonan);
                            logger.info("size senaraiOksIp : " + senaraiOksIp.size());

                            if (senaraiOksIp.size() != 0) {
                                Long idOpIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                                logger.info("id operasi : " + idOpIP);
                                listOp = enforcementService.findListLaporanOperasi(idOpIP);
                                statusIP = true;

                            }
                        }
                    }
                }
            }
        }
    }

    public Resolution popupViewBorangBongkar() {
        logger.info("--------------popupViewLaporanOperasi--------------");

        idOks = getContext().getRequest().getParameter("idOks");
        logger.info("::view record - id OKS : " + idOks);
        senaraiBarangOks = enforcementService.findBarangRampasanOks(Long.parseLong(idOks), "B");
        senaraiKenderaanOks = enforcementService.findBarangRampasanOks(Long.parseLong(idOks), "K");
        senaraiPermohonanRujLuar = enforcementService.findSenaraiAnggota(idPermohonan, "0302", idOks);
        aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));
        listBongkarKehadiran = enforcementService.findSenaraiBongkarKehadiran(aduanOrangKenaSyak.getIdOrangKenaSyak());

        logger.info("::size record kenderaan oks -: " + senaraiKenderaanOks.size());
        logger.info("::size record barang oks -: " + senaraiBarangOks.size());
        logger.info("::size record senaraiPermohonanRujLuar -: " + senaraiPermohonanRujLuar.size());
        return new JSP("penguatkuasaan/popup_view_borang_bongkar.jsp").addParameter("popup", "true");
    }

    public Resolution popupTambahBorangBongkar() {
        idOks = getContext().getRequest().getParameter("idOks");
        logger.info("::add record - id OKS : " + idOks);

        senaraiBarangOks = enforcementService.findBarangRampasanOks(Long.parseLong(idOks), "B");
        senaraiKenderaanOks = enforcementService.findBarangRampasanOks(Long.parseLong(idOks), "K");
        senaraiPermohonanRujLuar = enforcementService.findSenaraiAnggota(idPermohonan, "0302", idOks);
        aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));

        if (aduanOrangKenaSyak != null) {
            if (aduanOrangKenaSyak.getTarikhBongkar() != null) {
                tarikhBongkar = sdf.format(aduanOrangKenaSyak.getTarikhBongkar());
                tarikhBongkar = sdf1.format(aduanOrangKenaSyak.getTarikhBongkar()).substring(0, 10);
                jam = sdf1.format(aduanOrangKenaSyak.getTarikhBongkar()).substring(11, 13);
                if (jam.startsWith("0")) {
                    jam = sdf1.format(aduanOrangKenaSyak.getTarikhBongkar()).substring(12, 13);
                }
                minit = sdf1.format(aduanOrangKenaSyak.getTarikhBongkar()).substring(14, 16);
                ampm = sdf1.format(aduanOrangKenaSyak.getTarikhBongkar()).substring(17, 19);
            }
            tempatBongkar = aduanOrangKenaSyak.getTempatBongkar();
            listBongkarKehadiran = enforcementService.findSenaraiBongkarKehadiran(aduanOrangKenaSyak.getIdOrangKenaSyak());
        }

        rowCount = String.valueOf(senaraiPermohonanRujLuar.size());
        rowCountOrangHadir = String.valueOf(listBongkarKehadiran.size());

        logger.info("::size record kenderaan oks -: " + senaraiKenderaanOks.size());
        logger.info("::size record barang oks -: " + senaraiBarangOks.size());
        logger.info("::size record senaraiPermohonanRujLuar -: " + senaraiPermohonanRujLuar.size());
        logger.info("::size record listBongkarKehadiran -: " + listBongkarKehadiran.size());

        return new JSP("penguatkuasaan/popup_tambah_borang_bongkar.jsp").addParameter("popup", "true");
    }

    public Resolution editRecord() {
        logger.info("--------------editRecord--------------");

        senaraiBarangOks = enforcementService.findBarangRampasanOks(Long.parseLong(idOks), "B");
        senaraiKenderaanOks = enforcementService.findBarangRampasanOks(Long.parseLong(idOks), "K");
        senaraiPermohonanRujLuar = enforcementService.findSenaraiAnggota(idPermohonan, "0302", idOks);
        listBongkarKehadiran = enforcementService.findSenaraiBongkarKehadiran(Long.parseLong(idOks));

        logger.info("::size record kenderaan oks -: " + senaraiKenderaanOks.size());
        logger.info("::size record barang oks -: " + senaraiBarangOks.size());
        logger.info("::size record senaraiPermohonanRujLuar -: " + senaraiPermohonanRujLuar.size());
        logger.info("::edit record - id OP : " + idOperasi);
        logger.info("::size record listBongkarKehadiran -: " + listBongkarKehadiran.size());

        return new JSP("penguatkuasaan/popup_tambah_borang_bongkar.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() throws Exception {
        logger.info("--------------simpan : id oks --------------" + idOks);

        InfoAudit ia = new InfoAudit();
        aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));
        if (aduanOrangKenaSyak != null) {
            ia = aduanOrangKenaSyak.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

            aduanOrangKenaSyak.setTempatBongkar(tempatBongkar);

            if (StringUtils.isNotBlank(tarikhBongkar) && StringUtils.isNotBlank(jam) && StringUtils.isNotBlank(minit) && StringUtils.isNotBlank(ampm)) {
                tarikhBongkar = tarikhBongkar + " " + jam + ":" + minit + " " + ampm;
                logger.info("tarikhBongkar :" + tarikhBongkar);
            }

            if (StringUtils.isNotBlank(tarikhBongkar)) {
                aduanOrangKenaSyak.setTarikhBongkar(sdf1.parse(tarikhBongkar));
            }
            enforceService.simpanAduanOrangKenaSyak(aduanOrangKenaSyak);
        }

        senaraiPermohonanRujLuar = enforcementService.findSenaraiAnggota(idPermohonan, "0302", idOks);
        if (StringUtils.isNotBlank(rowCount)) {
            for (int i = 0; i < Integer.parseInt(rowCount); i++) {
                if (senaraiPermohonanRujLuar.size() != 0 && i < senaraiPermohonanRujLuar.size()) {
                    Long id = senaraiPermohonanRujLuar.get(i).getIdRujukan();
                    if (id != null) {
                        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(id);
                        ia = permohonanRujukanLuar.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        System.out.println("create new anggota if list not empty");
                        permohonanRujukanLuar = new PermohonanRujukanLuar();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                } else {
                    System.out.println("create new anggota if list empty");
                    permohonanRujukanLuar = new PermohonanRujukanLuar();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }

                permohonanRujukanLuar.setAgensi(kodAgensiDAO.findById("0302"));//0302 = POLIS DIRAJA MALAYSIA (PDRM)
                permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("BB"));//BB = Borang Bongkar
                permohonanRujukanLuar.setInfoAudit(ia);
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(pengguna.getKodCawangan());
                permohonanRujukanLuar.setNoRujukan(idOks);
                String namaAnggota = getContext().getRequest().getParameter("namaAnggota" + i);
                permohonanRujukanLuar.setNamaPenyedia(namaAnggota);
                enforceService.simpanRujukan(permohonanRujukanLuar);

            }
        }

        listBongkarKehadiran = enforcementService.findSenaraiBongkarKehadiran(Long.parseLong(idOks));
        if (StringUtils.isNotBlank(rowCountOrangHadir)) {
            for (int i = 0; i < Integer.parseInt(rowCountOrangHadir); i++) {
                if (listBongkarKehadiran.size() != 0 && i < listBongkarKehadiran.size()) {
                    Long id = listBongkarKehadiran.get(i).getIdBongkarKehadiran();
                    if (id != null) {
                        bongkarKehadiran = bongkarKehadiranDAO.findById(id);
                        ia = bongkarKehadiran.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        System.out.println("create new bongkar kehadiran if list not empty");
                        bongkarKehadiran = new BongkarKehadiran();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                } else {
                    System.out.println("create new bongkar kehadiran if list empty");
                    bongkarKehadiran = new BongkarKehadiran();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }

                String namaKehadiran = getContext().getRequest().getParameter("namaKehadiran" + i);
                String noPengenalanKehadiran = getContext().getRequest().getParameter("noPengenalanKehadiran" + i);
                String hubunganKehadiran = getContext().getRequest().getParameter("hubunganKehadiran" + i);
                String jenisPengenalan = getContext().getRequest().getParameter("jenisPengenalan" + i);

                System.out.println("id oks :::::::::::::::::::" + idOks);

                bongkarKehadiran.setAduanOrangKenaSyak(aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks)));
                bongkarKehadiran.setNama(namaKehadiran);
                bongkarKehadiran.setNoPengenalan(noPengenalanKehadiran);
                bongkarKehadiran.setHubungan(hubunganKehadiran);
                if (StringUtils.isNotBlank(jenisPengenalan)) {
                    bongkarKehadiran.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalan));
                }
                bongkarKehadiran.setNama(namaKehadiran);
                bongkarKehadiran.setInfoAudit(ia);
                enforcementService.simpanBongkarKehadiran(bongkarKehadiran);

            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_borang_bongkar.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRecord() {
        logger.info("--------------deleteRecord: id oks --------------" + idOks);
        try {
            InfoAudit ia = new InfoAudit();
            aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));
            if (aduanOrangKenaSyak != null) {
                ia = aduanOrangKenaSyak.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());

                aduanOrangKenaSyak.setTempatBongkar(null);
                aduanOrangKenaSyak.setTarikhBongkar(null);
            }

            senaraiPermohonanRujLuar = enforcementService.findSenaraiAnggota(idPermohonan, "0302", idOks);
            logger.info("::size record senaraiPermohonanRujLuar -: " + senaraiPermohonanRujLuar.size());

            for (int i = 0; i < senaraiPermohonanRujLuar.size(); i++) {
                permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(senaraiPermohonanRujLuar.get(i).getIdRujukan());
                enforcementService.deleteMesy(permohonanRujukanLuar);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        return new RedirectResolution(MaklumatBorangBongkarActionBean.class, "locate");
    }

    public Resolution deleteAnggota() {
        String idRujukan;
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        System.out.println("id masa delete : " + idRujukan);
        try {
            if (idRujukan != null) {
                permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
            }
            enforcementService.deleteMesy(permohonanRujukanLuar);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/popup_tambah_borang_bongkar.jsp").addParameter("popup", "true");
    }

    public Resolution deleteKehadiran() {
        String idBongkarKehadiran;
        idBongkarKehadiran = getContext().getRequest().getParameter("idBongkarKehadiran");
        System.out.println("id masa delete : " + idBongkarKehadiran);
        try {
            if (StringUtils.isNotBlank(idBongkarKehadiran)) {
                bongkarKehadiran = bongkarKehadiranDAO.findById(Long.parseLong(idBongkarKehadiran));
            }
            enforcementService.deleteKehadiran(bongkarKehadiran);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/popup_tambah_borang_bongkar.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(MaklumatBorangBongkarActionBean.class, "locate");
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

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
    }

    public String getIdOks() {
        return idOks;
    }

    public void setIdOks(String idOks) {
        this.idOks = idOks;
    }

    public List<BarangRampasan> getSenaraiBarangOks() {
        return senaraiBarangOks;
    }

    public void setSenaraiBarangOks(List<BarangRampasan> senaraiBarangOks) {
        this.senaraiBarangOks = senaraiBarangOks;
    }

    public String getTarikhBongkar() {
        return tarikhBongkar;
    }

    public void setTarikhBongkar(String tarikhBongkar) {
        this.tarikhBongkar = tarikhBongkar;
    }

    public String getTempatBongkar() {
        return tempatBongkar;
    }

    public void setTempatBongkar(String tempatBongkar) {
        this.tempatBongkar = tempatBongkar;
    }

    public List<BarangRampasan> getSenaraiKenderaanOks() {
        return senaraiKenderaanOks;
    }

    public void setSenaraiKenderaanOks(List<BarangRampasan> senaraiKenderaanOks) {
        this.senaraiKenderaanOks = senaraiKenderaanOks;
    }

    public List<PermohonanRujukanLuar> getSenaraiPermohonanRujLuar() {
        return senaraiPermohonanRujLuar;
    }

    public void setSenaraiPermohonanRujLuar(List<PermohonanRujukanLuar> senaraiPermohonanRujLuar) {
        this.senaraiPermohonanRujLuar = senaraiPermohonanRujLuar;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public Boolean getStatusIP() {
        return statusIP;
    }

    public void setStatusIP(Boolean statusIP) {
        this.statusIP = statusIP;
    }

    public List<BongkarKehadiran> getListBongkarKehadiran() {
        return listBongkarKehadiran;
    }

    public void setListBongkarKehadiran(List<BongkarKehadiran> listBongkarKehadiran) {
        this.listBongkarKehadiran = listBongkarKehadiran;
    }

    public String getRowCountOrangHadir() {
        return rowCountOrangHadir;
    }

    public void setRowCountOrangHadir(String rowCountOrangHadir) {
        this.rowCountOrangHadir = rowCountOrangHadir;
    }
}

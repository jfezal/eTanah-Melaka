/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.dao.OperasiPenguatkuasaanPasukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanSaksiDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.Alamat;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaanPasukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanSaksi;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/keterangan_saksi")
public class KeteranganSaksiActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KeteranganSaksiActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanSaksiDAO permohonanSaksiDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    OperasiPenguatkuasaanPasukanDAO operasiPenguatkuasaanPasukanDAO;
    private KodJenisPengenalan jenisPengenalan;
    private PermohonanSaksi permohonanSaksi;
    private String idSaksi;
    private Permohonan permohonan;
    private String nama;
    private KodNegeri negeri;
    private KodCawangan cawangan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String noPengenalan;
    private String negeri1;
    private String idPermohonan;
    private List<PermohonanSaksi> senaraiPermohonanSaksi;
    private String kodNegeriOrg;
    private String noTelefon;
    private String email;
    private String pekerjaan;
    private String kod;
    private String kp;
    private Pengguna pguna;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private String idOp;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private Boolean opFlag = false;
    private List<OperasiPenguatkuasaan> listOp;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Long idOpBasedOnIdIP;
    private Boolean idIP = false;
    private String idOks;
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukan;
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukanSaksi;
    private OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan;
    private String pasukan;
    private List<PermohonanSaksi> senaraiPermohonanSaksiDalaman;
    private String kodNegeri;

    @DefaultHandler
    public Resolution showForm() {
        if (opFlag == false) {
            addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
        }

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        }
        return new JSP("/penguatkuasaan/maklumat_saksi.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_saksi.jsp").addParameter("tab", "true");
    }

    public Resolution saksiPopup() {
        return new JSP("penguatkuasaan/popup_maklumat_saksi.jsp").addParameter("popup", "true");
    }

    public Resolution addRecord() {
        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::add record - id OP : " + idOp);
        return new JSP("penguatkuasaan/popup_maklumat_saksi.jsp").addParameter("popup", "true");
    }

    public Resolution addRecordSaksiOks() {
        idOks = getContext().getRequest().getParameter("idOks");
        idSaksi = getContext().getRequest().getParameter("idSaksi");

        if (StringUtils.isNotBlank(idSaksi)) {
            permohonanSaksi = permohonanSaksiDAO.findById(Long.parseLong(idSaksi));
            System.out.println("saksi dah wujud");
            if (permohonanSaksi != null) {
                nama = permohonanSaksi.getNama();
                noPengenalan = permohonanSaksi.getNoPengenalan();
                alamat1 = permohonanSaksi.getAlamat1();
                alamat2 = permohonanSaksi.getAlamat2();
                alamat3 = permohonanSaksi.getAlamat3();
                alamat4 = permohonanSaksi.getAlamat4();
                poskod = permohonanSaksi.getPoskod();
                noTelefon = permohonanSaksi.getNoTelefon();
                email = permohonanSaksi.getEmail();
                pekerjaan = permohonanSaksi.getPekerjaan();

                if (permohonanSaksi.getJenisPengenalan() != null) {
                    kp = permohonanSaksi.getJenisPengenalan().getKod();
                }

                if (permohonanSaksi.getNegeri() != null) {
                    kodNegeriOrg = permohonanSaksi.getNegeri().getKod();
                }

            }
        }
        getContext().getRequest().setAttribute("addSaksiOks", Boolean.TRUE);
        logger.info("::addRecordSaksiOks - id Oks : " + idOks);
        return new JSP("penguatkuasaan/popup_maklumat_saksi.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");
//        idSaksi = getContext().getRequest().getParameter("idSaksi");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            System.out.println("current id mohon : " + permohonan.getIdPermohonan());
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                    listOp = enforceService.findListLaporanOperasi(idPermohonan);
                    logger.info("listOp size : " + listOp.size());
                    if (listOp.size() != 0) {
                        opFlag = true;
                    } else {
                        if (permohonan.getPermohonanSebelum() != null) {
                            System.out.println("id current :" + permohonan.getIdPermohonan() + " id sebelum : " + permohonan.getPermohonanSebelum().getIdPermohonan());
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            opFlag = true;
                            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(permohonan.getIdPermohonan());
                            logger.info("size senaraiOksIp : " + senaraiOksIp.size());

                            if (!senaraiOksIp.isEmpty()) {
                                idIP = true;
                                idOpBasedOnIdIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                                logger.info("id operasi : " + idOpBasedOnIdIP);
                                logger.info("status idIP : " + idIP);

                                if (StringUtils.isNotBlank(idOpBasedOnIdIP.toString())) {
                                    senaraiPermohonanSaksi = enforceService.findByIDSaksi(permohonan.getIdPermohonan(), idOpBasedOnIdIP, "L");
                                    operasiPenguatkuasaan = enforceService.findByIdOp(idOpBasedOnIdIP);

                                    if (operasiPenguatkuasaan != null) {
                                        senaraiPasukan = enforceService.getSenaraiPasukan(operasiPenguatkuasaan.getIdOperasi());
                                        senaraiPasukanSaksi = enforcementService.findListByIdPasukan(operasiPenguatkuasaan.getIdOperasi());
                                    }

                                    senaraiPermohonanSaksiDalaman = enforceService.findByIDSaksi(permohonan.getIdPermohonan(), idOpBasedOnIdIP, "D");

//                                    if (senaraiPermohonanSaksi.isEmpty() && senaraiPermohonanSaksiDalaman.isEmpty()) {
//                                        if (permohonan.getPermohonanSebelum() != null) {
//                                            //1) find mohon saksi for sebahagian kompaun dakwa (create new permohonan) 3 layer permohonan
//
//                                            permohonan = permohonan.getPermohonanSebelum();
//                                            senaraiPermohonanSaksi = enforceService.findByIDSaksi(permohonan.getIdPermohonan(), idOpBasedOnIdIP, "L");
//                                            senaraiPermohonanSaksiDalaman = enforceService.findByIDSaksi(permohonan.getIdPermohonan(), idOpBasedOnIdIP, "D");
//                                        }
//                                    }
                                    if (senaraiPermohonanSaksi.isEmpty() && senaraiPermohonanSaksiDalaman.isEmpty()) {
                                        if (permohonan.getPermohonanSebelum() != null) {
                                            Permohonan p = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                                            if (permohonan != null) {
                                                System.out.println(":::::::::: " + permohonan.getIdPermohonan());
                                                senaraiPermohonanSaksi = enforceService.findByIDSaksi(p.getIdPermohonan(), idOpBasedOnIdIP, "L");
                                                senaraiPermohonanSaksiDalaman = enforceService.findByIDSaksi(p.getIdPermohonan(), idOpBasedOnIdIP, "D");
                                            }
                                        }
                                    }

                                }
                                logger.info("------------size senaraiSaksiLuar-------------- : " + senaraiPermohonanSaksi.size());
                                logger.info("-----size senaraiPermohonanSaksiDalaman-------- : " + senaraiPermohonanSaksiDalaman.size());
                            }
                        }
                    }
                } else {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                }

            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }
//            senaraiPermohonanSaksi = enforceService.findByIDSaksi(idPermohonan);


        }
    }

    public Resolution refreshpage() {
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");
    }

    public Resolution refreshPageSaksiOks() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        rehydrate();
//        return new JSP("penguatkuasaan/maklumat_saksi.jsp").addParameter("tab", "true");
        return new RedirectResolution(KeteranganSaksiActionBean.class, "locate");
    }

    public Resolution simpan() {
        logger.info("---------simpan---------");
        idOp = getContext().getRequest().getParameter("idOp");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {

                logger.info("::simpan record - id OP : " + idOp);
                operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOp));
            } else {
                //for Melaka, only pass idPermohonan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
            }

        } else {
            //for N9, pass idPermohonan & kategoriTindakan
            operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (permohonanSaksi == null) {
            permohonanSaksi = new PermohonanSaksi();

        }
        System.out.println("masuk sini3");
        permohonanSaksi = new PermohonanSaksi();
        permohonanSaksi.setPermohonan(permohonan);
        permohonanSaksi.setCawangan(pguna.getKodCawangan());
        permohonanSaksi.setInfoAudit(ia);
        permohonanSaksi.setNama(nama);
        permohonanSaksi.setNoPengenalan(noPengenalan);
        permohonanSaksi.setNoTelefon(noTelefon);
        permohonanSaksi.setAlamat1(alamat1);
        permohonanSaksi.setAlamat2(alamat2);
        permohonanSaksi.setAlamat3(alamat3);
        permohonanSaksi.setAlamat4(alamat4);
        permohonanSaksi.setPoskod(poskod);
        KodNegeri kodNegeri = new KodNegeri();
        kodNegeri.setKod(kodNegeriOrg);
        permohonanSaksi.setNegeri(kodNegeri);
        KodJenisPengenalan kod1 = new KodJenisPengenalan();
        kod1.setKod(kp);
        permohonanSaksi.setJenisPengenalan(kod1);
        permohonanSaksi.setEmail(email);
        permohonanSaksi.setPekerjaan(pekerjaan);
        permohonanSaksi.setOperasiPenguatkuasaan(operasiPenguatkuasaan);
        enforceService.simpanSaksi(permohonanSaksi);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new JSP("penguatkuasaan/maklumat_saksi.jsp").addParameter("tab", "true");
//        rehydrate();
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");
    }

    public Resolution edit() {
        System.out.println("masuk sini");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idSaksi = getContext().getRequest().getParameter("idSaksi");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        permohonanSaksi = permohonanSaksiDAO.findById(Long.parseLong(idSaksi));
//        permohonanSaksi = enforceService.findSaksiByIdSaksi(Long.parseLong(idSaksi));
        System.out.println("id permohonan:" + idPermohonan);
        permohonanSaksi.setNama(nama);
        permohonanSaksi.setNoPengenalan(noPengenalan);
        permohonanSaksi.setAlamat1(alamat1);
        permohonanSaksi.setAlamat2(alamat2);
        permohonanSaksi.setAlamat3(alamat3);
        permohonanSaksi.setAlamat4(alamat4);
        permohonanSaksi.setPoskod(poskod);
        KodNegeri kodNegeri = new KodNegeri();
        kodNegeri.setKod(kodNegeriOrg);
        permohonanSaksi.setNegeri(kodNegeri);
        permohonanSaksi.setNoTelefon(noTelefon);
        KodJenisPengenalan kod1 = new KodJenisPengenalan();
        kod1.setKod(kp);
        permohonanSaksi.setJenisPengenalan(kod1);
        permohonanSaksi.setEmail(email);
        permohonanSaksi.setPekerjaan(pekerjaan);

//        if (ia == null) {
//            ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
//            permohonanSaksi.setInfoAudit(ia);
//        } else {
        ia.setDiKemaskiniOleh(pguna);
        ia.setTarikhKemaskini(new java.util.Date());
        permohonanSaksi.setInfoAudit(ia);
        permohonanSaksi.setCawangan(pguna.getKodCawangan());
//        }
        System.out.println("saveEdit");
        enforceService.updateSaksi(permohonanSaksi);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new ForwardResolution("penguatkuasaan/maklumat_orang_disyaki.jsp").addParameter("tab", "true");
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");
    }

    public Resolution deleteSaksi() {

        idSaksi = getContext().getRequest().getParameter("idSaksi");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        permohonanSaksi = enforceService.findSaksiByIdSaksi(Long.parseLong(idSaksi));

        if (permohonanSaksi != null) {
            ia = pguna.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanSaksi.setInfoAudit(ia);
            permohonanSaksi.setPermohonan(permohonan);
            permohonanSaksi.setNoTelefon(noTelefon);
            permohonanSaksi.setCawangan(cawangan);
            permohonanSaksi.setNoPengenalan(noPengenalan);
            permohonanSaksi.setNama(nama);
            enforceService.deleteAll(permohonanSaksi);
        }
        return new RedirectResolution(DraftSiasatanActionBean.class, "locate");

    }

    public Resolution deleteSaksiOks() {

        idSaksi = getContext().getRequest().getParameter("idSaksi");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        permohonanSaksi = enforceService.findSaksiByIdSaksi(Long.parseLong(idSaksi));
        if (permohonanSaksi != null) {
            ia = pguna.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanSaksi.setInfoAudit(ia);
            permohonanSaksi.setPermohonan(permohonan);
            permohonanSaksi.setNoTelefon(noTelefon);
            permohonanSaksi.setCawangan(cawangan);
            permohonanSaksi.setNoPengenalan(noPengenalan);
            permohonanSaksi.setNama(nama);
            enforceService.deleteAll(permohonanSaksi);
        }
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        return new RedirectResolution(KeteranganSaksiActionBean.class, "locate");

    }

    public Resolution editSaksi() {
        idSaksi = getContext().getRequest().getParameter("idSaksi");
        permohonanSaksi = permohonanSaksiDAO.findById(Long.parseLong(idSaksi));

        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::add record - id OP : " + idOp);

        nama = permohonanSaksi.getNama();
        noPengenalan = permohonanSaksi.getNoPengenalan();
        alamat1 = permohonanSaksi.getAlamat1();
        alamat2 = permohonanSaksi.getAlamat2();
        alamat3 = permohonanSaksi.getAlamat3();
        alamat4 = permohonanSaksi.getAlamat4();
        poskod = permohonanSaksi.getPoskod();
        noTelefon = permohonanSaksi.getNoTelefon();
        email = permohonanSaksi.getEmail();
        pekerjaan = permohonanSaksi.getPekerjaan();

        return new JSP("penguatkuasaan/popup_edit_saksi.jsp").addParameter("popup", "true");
    }

    public Resolution editSaksiOks() {
        idSaksi = getContext().getRequest().getParameter("idSaksi");
        permohonanSaksi = permohonanSaksiDAO.findById(Long.parseLong(idSaksi));

        nama = permohonanSaksi.getNama();
        noPengenalan = permohonanSaksi.getNoPengenalan();
        alamat1 = permohonanSaksi.getAlamat1();
        alamat2 = permohonanSaksi.getAlamat2();
        alamat3 = permohonanSaksi.getAlamat3();
        alamat4 = permohonanSaksi.getAlamat4();
        poskod = permohonanSaksi.getPoskod();
        noTelefon = permohonanSaksi.getNoTelefon();
        email = permohonanSaksi.getEmail();
        pekerjaan = permohonanSaksi.getPekerjaan();

        getContext().getRequest().setAttribute("addSaksiOks", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_edit_saksi.jsp").addParameter("popup", "true");
    }

    public Resolution viewSaksi() {
        idSaksi = getContext().getRequest().getParameter("idSaksi");
        permohonanSaksi = permohonanSaksiDAO.findById(Long.parseLong(idSaksi));
        return new JSP("penguatkuasaan/popup_view_saksi.jsp").addParameter("popup", "true");
    }

    public Resolution simpanSaksiOks() {
        logger.info("---------simpanSaksiOks---------");
        idOp = getContext().getRequest().getParameter("idOp");
        idOks = getContext().getRequest().getParameter("idOks");
        idSaksi = getContext().getRequest().getParameter("idSaksi");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                logger.info("::simpanSaksiOks - id OP : " + idOpBasedOnIdIP);
                operasiPenguatkuasaan = enforceService.findByIdOp(idOpBasedOnIdIP);
            } else {
                //for Melaka, only pass idPermohonan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
            }

        } else {
            //for N9, pass idPermohonan & kategoriTindakan
            operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (StringUtils.isNotBlank(idSaksi)) {
            permohonanSaksi = permohonanSaksiDAO.findById(Long.parseLong(idSaksi));
            System.out.println("saksi dah wujud");
        }

        if (StringUtils.isNotBlank(idOks)) {
            aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));
        }

        if (permohonanSaksi == null) {
            permohonanSaksi = new PermohonanSaksi();
        } else {
            ia = permohonanSaksi.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());

            aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(permohonanSaksi.getAduanOrangKenaSyak().getIdOrangKenaSyak());
        }
        permohonanSaksi.setPermohonan(permohonan);
        permohonanSaksi.setCawangan(pguna.getKodCawangan());
        permohonanSaksi.setInfoAudit(ia);
        permohonanSaksi.setNama(nama);
        permohonanSaksi.setNoPengenalan(noPengenalan);
        permohonanSaksi.setNoTelefon(noTelefon);
        permohonanSaksi.setAlamat1(alamat1);
        permohonanSaksi.setAlamat2(alamat2);
        permohonanSaksi.setAlamat3(alamat3);
        permohonanSaksi.setAlamat4(alamat4);
        permohonanSaksi.setPoskod(poskod);
        KodNegeri kodNegeri = new KodNegeri();
        kodNegeri.setKod(kodNegeriOrg);
        permohonanSaksi.setNegeri(kodNegeri);
        KodJenisPengenalan kod1 = new KodJenisPengenalan();
        kod1.setKod(kp);
        permohonanSaksi.setJenisPengenalan(kod1);
        permohonanSaksi.setEmail(email);
        permohonanSaksi.setPekerjaan(pekerjaan);
        permohonanSaksi.setOperasiPenguatkuasaan(operasiPenguatkuasaan);
        permohonanSaksi.setAduanOrangKenaSyak(aduanOrangKenaSyak);
        permohonanSaksi.setStatusSaksi('L');
        enforceService.simpanSaksi(permohonanSaksi);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_saksi.jsp").addParameter("tab", "true");
    }

    public Resolution simpanSaksiPasukan() {
        logger.info("-------- simpan saksi dari senarai pasukan --------");
        String idOperasiPenguatkuasaanPasukan = getContext().getRequest().getParameter("idPasukan");

        idOks = getContext().getRequest().getParameter("idOks");

        if (StringUtils.isNotBlank(idOks)) {
            System.out.println("id oks : " + idOks);
            aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));
        }

        try {
            if (idOperasiPenguatkuasaanPasukan != null) {
                operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukanDAO.findById(Long.parseLong(idOperasiPenguatkuasaanPasukan));
            }

            if (operasiPenguatkuasaanPasukan != null) {
                InfoAudit ia = operasiPenguatkuasaanPasukan.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());

                operasiPenguatkuasaanPasukan.setSaksi("Y");
                enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);

                permohonanSaksi = new PermohonanSaksi();
                permohonanSaksi.setPermohonan(permohonan);
                permohonanSaksi.setCawangan(pguna.getKodCawangan());
                permohonanSaksi.setInfoAudit(ia);
                permohonanSaksi.setNama(operasiPenguatkuasaanPasukan.getNama());
                permohonanSaksi.setNoPengenalan(operasiPenguatkuasaanPasukan.getNoKp());
                permohonanSaksi.setAlamat4(operasiPenguatkuasaanPasukan.getTempatKerja());
                permohonanSaksi.setOperasiPenguatkuasaan(operasiPenguatkuasaanPasukan.getIdOperasiPenguatkuasaan());
                permohonanSaksi.setStatusSaksi('D');
                permohonanSaksi.setAduanOrangKenaSyak(aduanOrangKenaSyak);
                enforceService.simpanSaksi(permohonanSaksi);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        return new RedirectResolution(KeteranganSaksiActionBean.class, "locate");

    }

    public Resolution checkExistingRecordSaksi() {
        logger.info("--------checkExistingRecordSaksi--------");
        String result = "";
        String idPasukan = getContext().getRequest().getParameter("idPasukan");

        idOks = getContext().getRequest().getParameter("idOks");

        if (StringUtils.isNotBlank(idOks)) {
            System.out.println("id oks : " + idOks);
        }

        if (idPasukan != null) {
            operasiPenguatkuasaanPasukan = enforcementService.findByIdPasukan(Long.parseLong(idPasukan));
            if (operasiPenguatkuasaanPasukan != null) {
                logger.info("----------record saksi exist-------");
                nama = operasiPenguatkuasaanPasukan.getNama();
                noPengenalan = operasiPenguatkuasaanPasukan.getNoKp();
                System.out.println("nama :" + nama);
                System.out.println("noPengenalan :" + noPengenalan);

                List<PermohonanSaksi> senaraiSaksiDalaman = enforceService.checkSaksiDalaman(nama.toLowerCase(), noPengenalan.trim(), Long.parseLong(idOks));
                logger.info("----------check senaraiSaksiDalaman-------" + senaraiSaksiDalaman.size());

                if (senaraiSaksiDalaman.size() != 0) {
                    result = "exist";
                } else {
                    result = "not exist";
                }
            }
        }



        System.out.println("result saksi dalaman : " + result);

        return new StreamingResolution("text/plain", result);
    }

    public Resolution deleteSaksiDalaman() {
        logger.info("--------deleteSaksi--------");

        String idPasukan = getContext().getRequest().getParameter("idPasukan");
        operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukanDAO.findById(Long.parseLong(idPasukan));

        if (operasiPenguatkuasaanPasukan != null) {
            InfoAudit ia = operasiPenguatkuasaanPasukan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());

            operasiPenguatkuasaanPasukan.setSaksi("T");
            enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        return new RedirectResolution(KeteranganSaksiActionBean.class, "locate");

    }

    public String getIdSaksi() {
        return idSaksi;
    }

    public void setIdSaksi(String idSaksi) {
        this.idSaksi = idSaksi;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public PermohonanSaksi getPermohonanSaksi() {
        return permohonanSaksi;
    }

    public void setPermohonanSaksi(PermohonanSaksi permohonanSaksi) {
        this.permohonanSaksi = permohonanSaksi;
    }

    public List<PermohonanSaksi> getSenaraiPermohonanSaksi() {
        return senaraiPermohonanSaksi;
    }

    public void setSenaraiPermohonanSaksi(List<PermohonanSaksi> senaraiPermohonanSaksi) {
        this.senaraiPermohonanSaksi = senaraiPermohonanSaksi;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getNegeri1() {
        return negeri1;
    }

    public void setNegeri1(String negeri1) {
        this.negeri1 = negeri1;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getKodNegeriOrg() {
        return kodNegeriOrg;
    }

    public void setKodNegeriOrg(String kodNegeriOrg) {
        this.kodNegeriOrg = kodNegeriOrg;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKp() {
        return kp;
    }

    public void setKp(String kp) {
        this.kp = kp;
    }

    public String getIdOp() {
        return idOp;
    }

    public void setIdOp(String idOp) {
        this.idOp = idOp;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public Boolean getOpFlag() {
        return opFlag;
    }

    public void setOpFlag(Boolean opFlag) {
        this.opFlag = opFlag;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public Long getIdOpBasedOnIdIP() {
        return idOpBasedOnIdIP;
    }

    public void setIdOpBasedOnIdIP(Long idOpBasedOnIdIP) {
        this.idOpBasedOnIdIP = idOpBasedOnIdIP;
    }

    public Boolean getIdIP() {
        return idIP;
    }

    public void setIdIP(Boolean idIP) {
        this.idIP = idIP;
    }

    public String getIdOks() {
        return idOks;
    }

    public void setIdOks(String idOks) {
        this.idOks = idOks;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukan() {
        return senaraiPasukan;
    }

    public void setSenaraiPasukan(List<OperasiPenguatkuasaanPasukan> senaraiPasukan) {
        this.senaraiPasukan = senaraiPasukan;
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukanSaksi() {
        return senaraiPasukanSaksi;
    }

    public void setSenaraiPasukanSaksi(List<OperasiPenguatkuasaanPasukan> senaraiPasukanSaksi) {
        this.senaraiPasukanSaksi = senaraiPasukanSaksi;
    }

    public OperasiPenguatkuasaanPasukan getOperasiPenguatkuasaanPasukan() {
        return operasiPenguatkuasaanPasukan;
    }

    public void setOperasiPenguatkuasaanPasukan(OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan) {
        this.operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukan;
    }

    public String getPasukan() {
        return pasukan;
    }

    public void setPasukan(String pasukan) {
        this.pasukan = pasukan;
    }

    public List<PermohonanSaksi> getSenaraiPermohonanSaksiDalaman() {
        return senaraiPermohonanSaksiDalaman;
    }

    public void setSenaraiPermohonanSaksiDalaman(List<PermohonanSaksi> senaraiPermohonanSaksiDalaman) {
        this.senaraiPermohonanSaksiDalaman = senaraiPermohonanSaksiDalaman;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}

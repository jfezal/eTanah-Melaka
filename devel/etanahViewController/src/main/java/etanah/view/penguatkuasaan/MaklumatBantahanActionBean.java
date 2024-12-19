/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJantinaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanPihakMembantahDAO;
import etanah.dao.PihakDAO;
import etanah.model.InfoAudit;
import etanah.model.KodJantina;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihakMembantah;
import etanah.model.Pihak;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ctzainal modify by sitifariza.hanim (14042011)
 */
@UrlBinding("/penguatkuasaan/maklumat_bantahan")
public class MaklumatBantahanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatBantahanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakMembantahDAO permohonanPihakMembantahDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJantinaDAO kodJantinaDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private PermohonanPihakMembantah permohonanPihakMembantah;
    private Permohonan permohonan;
    private String noPengenalan;
    private String nama;
    private String jantina;
    private String idPermohonan;
    private Pengguna pguna;
    private String jenisPengenalan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String noTelefon1;
    private String noTelefon2;
    private String kaitan;
    private String tarikhBantahan;
    private String catatan;
    private String kategoriPihakMembantah;
    private String kategoriPembantah;
    private Pihak pihak;
    private String jantinaPihak;
    private Boolean flag = false;
    private Boolean flagSearch = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String kodNgri;
    private List<PermohonanPihakMembantah> listPihakMembantah = new ArrayList<PermohonanPihakMembantah>();
    private String idMohonPihakBantah;
    private String idPihak;
    private String statusView;
    private String statusCarian;
    private String namaPihak;
    private String noPengenalanPihak;
    private String jenisPengenalanPihak;
    private String alamatPihak;
    private String noTelefon1Pihak;
    private String noTelefon2Pihak;
    private String poskodPihak;
    private String negeriPihak;
    private String jantinaPihakCarian;
    private Boolean noEdit = Boolean.FALSE;
    private List<PermohonanPihakMembantah> listPihak = new ArrayList<PermohonanPihakMembantah>();
    private String statusWaris;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_senarai_bantahan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_senarai_bantahan.jsp").addParameter("tab", "true");
    }

    public Resolution redirectPage() {
        return new JSP("penguatkuasaan/maklumat_bantahan.jsp").addParameter("tab", "true");
    }

    public Resolution semakNoPengenalan() {
        String result = "";
        noPengenalan = getContext().getRequest().getParameter("noPengenalan");
        pihak = enforceService.semakNoPengenalan(noPengenalan);
        if (pihak != null) {
            result = "exist";
        } else {
            result = "notExist";
        }

        return new StreamingResolution("text/plain", result);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            listPihakMembantah = enforceService.findSenaraiPihakPembantah(idPermohonan);
            listPihak = enforceService.findPihakPembantah(idPermohonan);

//            try {
//                permohonanPihakMembantah = enforceService.findPermohonanPihakMembantahByIdpermohonan(idPermohonan);
//                permohonan = permohonanDAO.findById(idPermohonan);
//
//                if (permohonanPihakMembantah != null) {
//                    kategoriPembantah = permohonanPihakMembantah.getKategoriPihakMembantah();
//                    nama = permohonanPihakMembantah.getNama();
//                    noPengenalan = permohonanPihakMembantah.getNoPengenalan();
//                    jenisPengenalan = permohonanPihakMembantah.getJenisPengenalan().getKod();
//                    alamat1 = permohonanPihakMembantah.getAlamat1();
//                    alamat2 = permohonanPihakMembantah.getAlamat2();
//                    alamat3 = permohonanPihakMembantah.getAlamat3();
//                    alamat4 = permohonanPihakMembantah.getAlamat4();
//                    poskod = permohonanPihakMembantah.getPoskod();
//                    kodNgri = permohonanPihakMembantah.getNegeri().getKod();
//                    jantina = permohonanPihakMembantah.getJantina().getKod();
//                    noTelefon1 = permohonanPihakMembantah.getNoTelefon1();
//                    noTelefon2 = permohonanPihakMembantah.getNoTelefon2();
//                    kaitan = permohonanPihakMembantah.getKaitan();
//                    tarikhBantahan = sdf.format(permohonanPihakMembantah.getTarikhBantahan());
//                    catatan = permohonanPihakMembantah.getCatatan();
//                }
//            } catch (Exception ex) {
//                logger.error(ex);
//            }
        }
    }

    //To search ic number in table Pihak
    public Resolution searchNoPengenalan() {
        logger.info("::::: searchNoPengenalan");
        noPengenalan = getContext().getRequest().getParameter("noPengenalan");
        System.out.println("no pengenalan : " + noPengenalan);

        try {
            List<Pihak> senaraiPihak = enforceService.senaraiPihakByNoPengenalan(noPengenalan);

            if (senaraiPihak.size() > 1) {
                addSimpleError("No kad pengenalan tidak unik.");
                statusCarian = "TW";
                return new JSP("penguatkuasaan/popup_tambah_pembantah.jsp").addParameter("popup", "true");
            } else if (senaraiPihak.isEmpty()) {
                addSimpleError("Sila Masukan No Pengenalan Yang Betul.");
                statusCarian = "TW";
                return new JSP("penguatkuasaan/popup_tambah_pembantah.jsp").addParameter("popup", "true");
            } else if (senaraiPihak.size() == 1) {
                statusCarian = "W";
                pihak = senaraiPihak.get(0);
                namaPihak = pihak.getNama();
                jenisPengenalanPihak = pihak.getJenisPengenalan().getNama();
                noPengenalanPihak = pihak.getNoPengenalan();
                noTelefon1Pihak = pihak.getNoTelefon1();
                noTelefon2Pihak = pihak.getNoTelefon2();
                jantinaPihakCarian = pihak.getKodJantina();
                if (StringUtils.isNotBlank(pihak.getAlamat1())) {
                    alamatPihak = pihak.getAlamat1();
                }
                if (StringUtils.isNotBlank(pihak.getAlamat2())) {
                    alamatPihak += "," + pihak.getAlamat2();
                }
                if (StringUtils.isNotBlank(pihak.getAlamat3())) {
                    alamatPihak += "," + pihak.getAlamat3();
                }
                if (StringUtils.isNotBlank(pihak.getAlamat4())) {
                    alamatPihak += "," + pihak.getAlamat4();
                }
                poskodPihak = pihak.getPoskod();
                if (pihak.getNegeri() != null) {
                    negeriPihak = pihak.getNegeri().getNama();
                }
                idPihak = Long.toString(pihak.getIdPihak());
                System.out.println("idPihak : " + idPihak);
            }
        } catch (Exception e) {
            logger.error(e.getCause());
        }

        return new JSP("penguatkuasaan/popup_tambah_pembantah.jsp").addParameter("popup", "true");

    }

    //to save Lain-lain inside Permohonan Pihak Membantah table
    public Resolution simpan() throws ParseException {

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());

        if (permohonanPihakMembantah == null) {
            permohonanPihakMembantah = new PermohonanPihakMembantah();
        }

        permohonanPihakMembantah.setNama(nama);
        permohonanPihakMembantah.setInfoAudit(ia);
        permohonanPihakMembantah.setPermohonan(permohonan);
        KodJenisPengenalan kp = new KodJenisPengenalan();
        kp.setKod(jenisPengenalan);
        permohonanPihakMembantah.setJenisPengenalan(kp);
        permohonanPihakMembantah.setNoPengenalan(noPengenalan);
        permohonanPihakMembantah.setAlamat1(alamat1);
        permohonanPihakMembantah.setAlamat2(alamat2);
        permohonanPihakMembantah.setAlamat3(alamat3);
        permohonanPihakMembantah.setAlamat4(alamat4);
        permohonanPihakMembantah.setPoskod(poskod);

        KodJantina jantina1 = new KodJantina();
        jantina1.setKod(jantina);
        permohonanPihakMembantah.setJantina(jantina1);
        permohonanPihakMembantah.setNoTelefon1(noTelefon1);
        permohonanPihakMembantah.setNoTelefon2(noTelefon2);
        permohonanPihakMembantah.setKaitan(kaitan);
        if (tarikhBantahan != null) {
            System.out.println("ada tarikh bantahan");
            permohonanPihakMembantah.setTarikhBantahan(sdf.parse(tarikhBantahan));
        }

        if (kodNgri != null) {
            KodNegeri kn1 = new KodNegeri();
            kn1.setKod(kodNgri);
            permohonanPihakMembantah.setNegeri(kn1);
        }
        permohonanPihakMembantah.setCatatan(catatan);
        permohonanPihakMembantah.setCawangan(peng.getKodCawangan());
        permohonanPihakMembantah.setKategoriPihakMembantah(kategoriPembantah);
        permohonanPihakMembantah.setStatusWaris(statusWaris);
        enforceService.simpanPermohonanPihakMembantah(permohonanPihakMembantah);

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new JSP("penguatkuasaan/maklumat_bantahan.jsp").addParameter("tab", "true");
    }

    //save pihak berkepentingan inside Permohonan Pihak Membantah table
    public Resolution simpanPihakBerkepentingan() throws ParseException {

        noPengenalan = getContext().getRequest().getParameter("noPengenalan");
        pihak = enforceService.semakNoPengenalan(noPengenalan);
        if (pihak != null) {
            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());

            permohonanPihakMembantah = new PermohonanPihakMembantah();

            permohonanPihakMembantah.setNama(pihak.getNama());
            permohonanPihakMembantah.setInfoAudit(ia);
            permohonanPihakMembantah.setPermohonan(permohonan);
            permohonanPihakMembantah.setJenisPengenalan(pihak.getJenisPengenalan());
            permohonanPihakMembantah.setNoPengenalan(pihak.getNoPengenalan());
            permohonanPihakMembantah.setAlamat1(pihak.getAlamat1());
            permohonanPihakMembantah.setAlamat2(pihak.getAlamat2());
            permohonanPihakMembantah.setAlamat3(pihak.getAlamat3());
            permohonanPihakMembantah.setAlamat4(pihak.getAlamat4());
            permohonanPihakMembantah.setPoskod(pihak.getPoskod());
            permohonanPihakMembantah.setNegeri(pihak.getNegeri() != null ? pihak.getNegeri() : null);


            if (pihak.getKodJantina() != null) {
                KodJantina jantinaPihak1 = new KodJantina();
                jantinaPihak1.setKod(pihak.getKodJantina());
                permohonanPihakMembantah.setJantina(jantinaPihak1);


            } else {
                KodJantina jantinaPihakBerkepentingan = new KodJantina();
                jantinaPihakBerkepentingan.setKod(jantinaPihak);
                permohonanPihakMembantah.setJantina(jantinaPihakBerkepentingan);

            }


            permohonanPihakMembantah.setNoTelefon1(pihak.getNoTelefon1());
            permohonanPihakMembantah.setNoTelefon2(pihak.getNoTelefon2());
            permohonanPihakMembantah.setKaitan(kaitan);
            if (tarikhBantahan != null) {
                permohonanPihakMembantah.setTarikhBantahan(sdf.parse(tarikhBantahan));
            }

            permohonanPihakMembantah.setCatatan(catatan);
            permohonanPihakMembantah.setCawangan(peng.getKodCawangan());
            permohonanPihakMembantah.setKategoriPihakMembantah(kategoriPembantah);
            permohonanPihakMembantah.setStatusWaris(statusWaris);
            enforceService.simpanPermohonanPihakMembantah(permohonanPihakMembantah);



        } else {
            addSimpleError("Object Pihak null.");
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/maklumat_bantahan.jsp").addParameter("tab", "true");

    }

    public Resolution addRecord() {
        logger.info("--------------addRecord--------------");
        idMohonPihakBantah = getContext().getRequest().getParameter("idMohonPihakBantah");
        statusView = getContext().getRequest().getParameter("statusView");
        logger.info("::addRecord - idMohonPihakBantah : " + idMohonPihakBantah);
        logger.info("::addRecord - statusView : " + statusView);

        if (StringUtils.isNotBlank(idMohonPihakBantah)) {
            permohonanPihakMembantah = permohonanPihakMembantahDAO.findById(Long.parseLong(idMohonPihakBantah));

            if (permohonanPihakMembantah != null) {
                kategoriPembantah = permohonanPihakMembantah.getKategoriPihakMembantah();
                if (kategoriPembantah != null) {
                    if (kategoriPembantah.equalsIgnoreCase("P")) {
                        if (permohonanPihakMembantah.getPihak() != null) {
                            idPihak = Long.toString(permohonanPihakMembantah.getPihak().getIdPihak());
                            pihak = pihakDAO.findById(Long.parseLong(idPihak));
                            if (pihak != null) {
                                namaPihak = pihak.getNama();
                                jenisPengenalanPihak = pihak.getJenisPengenalan().getNama();
                                noPengenalanPihak = pihak.getNoPengenalan();
                                noTelefon1Pihak = pihak.getNoTelefon1();
                                noTelefon2Pihak = pihak.getNoTelefon2();
                                if (StringUtils.isNotBlank(pihak.getAlamat1())) {
                                    alamatPihak = pihak.getAlamat1();
                                }
                                if (StringUtils.isNotBlank(pihak.getAlamat2())) {
                                    alamatPihak += "," + pihak.getAlamat2();
                                }
                                if (StringUtils.isNotBlank(pihak.getAlamat3())) {
                                    alamatPihak += "," + pihak.getAlamat3();
                                }
                                if (StringUtils.isNotBlank(pihak.getAlamat4())) {
                                    alamatPihak += "," + pihak.getAlamat4();
                                }
                                poskodPihak = pihak.getPoskod();
                                if (pihak.getNegeri() != null) {
                                    negeriPihak = pihak.getNegeri().getNama();
                                }

                                if (StringUtils.isNotBlank(pihak.getKodJantina())) {
                                    jantinaPihakCarian = pihak.getKodJantina();
                                    noEdit = true;
                                } else {
                                    jantinaPihakCarian = permohonanPihakMembantah.getJantina().getKod();
                                }
                            }
                        }

                    } else {
                        nama = permohonanPihakMembantah.getNama();
                        jenisPengenalan = permohonanPihakMembantah.getJenisPengenalan().getKod();
                        noPengenalan = permohonanPihakMembantah.getNoPengenalan();
                        alamat1 = permohonanPihakMembantah.getAlamat1();
                        alamat2 = permohonanPihakMembantah.getAlamat2();
                        alamat3 = permohonanPihakMembantah.getAlamat3();
                        alamat4 = permohonanPihakMembantah.getAlamat4();
                        poskod = permohonanPihakMembantah.getPoskod();
                        if (permohonanPihakMembantah.getNegeri() != null) {
                            kodNgri = permohonanPihakMembantah.getNegeri().getKod();
                        }

                        if (permohonanPihakMembantah.getJantina() != null) {
                            jantina = permohonanPihakMembantah.getJantina().getKod();
                        }
                        noTelefon1 = permohonanPihakMembantah.getNoTelefon1();
                        noTelefon2 = permohonanPihakMembantah.getNoTelefon2();
                    }
                }

                kaitan = permohonanPihakMembantah.getKaitan();
                catatan = permohonanPihakMembantah.getCatatan();
                if (permohonanPihakMembantah.getTarikhBantahan() != null) {
                    tarikhBantahan = sdf.format(permohonanPihakMembantah.getTarikhBantahan());
                }
                statusWaris = permohonanPihakMembantah.getStatusWaris();
            }

        }
        return new JSP("penguatkuasaan/popup_tambah_pembantah.jsp").addParameter("popup", "true");
    }

    public Resolution simpanBantahan() {
        logger.info("--------------simpanBantahan--------------");
        idMohonPihakBantah = getContext().getRequest().getParameter("idMohonPihakBantah");
        logger.info("::simpanBantahan - idMohonPihakBantah : " + idMohonPihakBantah);

        InfoAudit ia = new InfoAudit();

        if (StringUtils.isNotBlank(idMohonPihakBantah)) {
            permohonanPihakMembantah = permohonanPihakMembantahDAO.findById(Long.parseLong(idMohonPihakBantah));
        }

        if (permohonanPihakMembantah != null) {
            ia = permohonanPihakMembantah.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonanPihakMembantah = new PermohonanPihakMembantah();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        }

        System.out.println("kategoriPembantah ::::::" + kategoriPembantah);

        if (StringUtils.isNotBlank(kategoriPembantah)) {
            if (kategoriPembantah.equalsIgnoreCase("L")) {
                logger.info("status kategoriPembantah ::::::" + kategoriPembantah);
                permohonanPihakMembantah.setNama(nama);
                KodJenisPengenalan kp = new KodJenisPengenalan();
                kp.setKod(jenisPengenalan);
                permohonanPihakMembantah.setJenisPengenalan(kp);
                permohonanPihakMembantah.setNoPengenalan(noPengenalan);
                permohonanPihakMembantah.setAlamat1(alamat1);
                permohonanPihakMembantah.setAlamat2(alamat2);
                permohonanPihakMembantah.setAlamat3(alamat3);
                permohonanPihakMembantah.setAlamat4(alamat4);
                permohonanPihakMembantah.setPoskod(poskod);

                KodJantina jantina1 = new KodJantina();
                jantina1.setKod(jantina);
                permohonanPihakMembantah.setJantina(jantina1);
                permohonanPihakMembantah.setNoTelefon1(noTelefon1);
                permohonanPihakMembantah.setNoTelefon2(noTelefon2);
                permohonanPihakMembantah.setKaitan(kaitan);
                if (kodNgri != null) {
                    KodNegeri kn1 = new KodNegeri();
                    kn1.setKod(kodNgri);
                    permohonanPihakMembantah.setNegeri(kn1);
                }
            } else {
                if (StringUtils.isNotBlank(idPihak)) {
                    pihak = pihakDAO.findById(Long.parseLong(idPihak));
                    if (pihak != null) {
                        logger.info("status kategoriPembantah ::::::" + kategoriPembantah);
                        permohonanPihakMembantah.setPihak(pihak);
                        permohonanPihakMembantah.setNama(pihak.getNama());
                        permohonanPihakMembantah.setJenisPengenalan(pihak.getJenisPengenalan());
                        permohonanPihakMembantah.setNoPengenalan(pihak.getNoPengenalan());
                        permohonanPihakMembantah.setAlamat1(pihak.getAlamat1());
                        permohonanPihakMembantah.setAlamat2(pihak.getAlamat2());
                        permohonanPihakMembantah.setAlamat3(pihak.getAlamat3());
                        permohonanPihakMembantah.setAlamat4(pihak.getAlamat4());
                        permohonanPihakMembantah.setPoskod(pihak.getPoskod());
                        permohonanPihakMembantah.setNegeri(pihak.getNegeri() != null ? pihak.getNegeri() : null);


                        if (pihak.getKodJantina() != null) {
                            KodJantina jantinaPihak1 = new KodJantina();
                            jantinaPihak1.setKod(pihak.getKodJantina());
                            permohonanPihakMembantah.setJantina(jantinaPihak1);
                        } else {
                            KodJantina jantinaPihakBerkepentingan = new KodJantina();
                            jantinaPihakBerkepentingan.setKod(jantinaPihak);
                            permohonanPihakMembantah.setJantina(jantinaPihakBerkepentingan);
                        }
                        permohonanPihakMembantah.setNoTelefon1(pihak.getNoTelefon1());
                        permohonanPihakMembantah.setNoTelefon2(pihak.getNoTelefon2());

                    }
                }
            }
        }

        permohonanPihakMembantah.setInfoAudit(ia);
        permohonanPihakMembantah.setPermohonan(permohonan);
        permohonanPihakMembantah.setKaitan(kaitan);
        if (tarikhBantahan != null) {
            try {
                permohonanPihakMembantah.setTarikhBantahan(sdf.parse(tarikhBantahan));
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(MaklumatBantahanActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        permohonanPihakMembantah.setCatatan(catatan);
        permohonanPihakMembantah.setCawangan(pguna.getKodCawangan());
        permohonanPihakMembantah.setKategoriPihakMembantah(kategoriPembantah);
        permohonanPihakMembantah.setStatusWaris(statusWaris);
        enforceService.simpanPermohonanPihakMembantah(permohonanPihakMembantah);


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatBantahanActionBean.class, "locate");

    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(MaklumatBantahanActionBean.class, "locate");
    }

    public Resolution deleteRecord() {
        idMohonPihakBantah = getContext().getRequest().getParameter("id");
        logger.info(":::: delete" + idMohonPihakBantah);


        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            if (StringUtils.isNotBlank(idMohonPihakBantah)) {
                permohonanPihakMembantah = permohonanPihakMembantahDAO.findById(Long.parseLong(idMohonPihakBantah));
                if (permohonanPihakMembantah != null) {
                    permohonanPihakMembantahDAO.delete(permohonanPihakMembantah);
                }
            }

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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatBantahanActionBean.class, "locate");
    }

    public String getKodNgri() {
        return kodNgri;
    }

    public void setKodNgri(String kodNgri) {
        this.kodNgri = kodNgri;
    }

    public String getJantinaPihak() {
        return jantinaPihak;
    }

    public void setJantinaPihak(String jantinaPihak) {
        this.jantinaPihak = jantinaPihak;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlagSearch() {
        return flagSearch;
    }

    public void setFlagSearch(Boolean flagSearch) {
        this.flagSearch = flagSearch;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getJantina() {
        return jantina;
    }

    public void setJantina(String jantina) {
        this.jantina = jantina;
    }

    public String getKategoriPembantah() {
        return kategoriPembantah;
    }

    public void setKategoriPembantah(String kategoriPembantah) {
        this.kategoriPembantah = kategoriPembantah;
    }

    public PermohonanPihakMembantah getPermohonanPihakMembantah() {
        return permohonanPihakMembantah;
    }

    public void setPermohonanPihakMembantah(PermohonanPihakMembantah permohonanPihakMembantah) {
        this.permohonanPihakMembantah = permohonanPihakMembantah;
    }

    public String getKategoriPihakMembantah() {
        return kategoriPihakMembantah;
    }

    public void setKategoriPihakMembantah(String kategoriPihakMembantah) {
        this.kategoriPihakMembantah = kategoriPihakMembantah;
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

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getKaitan() {
        return kaitan;
    }

    public void setKaitan(String kaitan) {
        this.kaitan = kaitan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getNoTelefon2() {
        return noTelefon2;
    }

    public void setNoTelefon2(String noTelefon2) {
        this.noTelefon2 = noTelefon2;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getTarikhBantahan() {
        return tarikhBantahan;
    }

    public void setTarikhBantahan(String tarikhBantahan) {
        this.tarikhBantahan = tarikhBantahan;
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

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PermohonanPihakMembantah> getListPihakMembantah() {
        return listPihakMembantah;
    }

    public void setListPihakMembantah(List<PermohonanPihakMembantah> listPihakMembantah) {
        this.listPihakMembantah = listPihakMembantah;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getIdMohonPihakBantah() {
        return idMohonPihakBantah;
    }

    public void setIdMohonPihakBantah(String idMohonPihakBantah) {
        this.idMohonPihakBantah = idMohonPihakBantah;
    }

    public String getStatusView() {
        return statusView;
    }

    public void setStatusView(String statusView) {
        this.statusView = statusView;
    }

    public String getStatusCarian() {
        return statusCarian;
    }

    public void setStatusCarian(String statusCarian) {
        this.statusCarian = statusCarian;
    }

    public String getNamaPihak() {
        return namaPihak;
    }

    public void setNamaPihak(String namaPihak) {
        this.namaPihak = namaPihak;
    }

    public String getNoPengenalanPihak() {
        return noPengenalanPihak;
    }

    public void setNoPengenalanPihak(String noPengenalanPihak) {
        this.noPengenalanPihak = noPengenalanPihak;
    }

    public String getJenisPengenalanPihak() {
        return jenisPengenalanPihak;
    }

    public void setJenisPengenalanPihak(String jenisPengenalanPihak) {
        this.jenisPengenalanPihak = jenisPengenalanPihak;
    }

    public String getAlamatPihak() {
        return alamatPihak;
    }

    public void setAlamatPihak(String alamatPihak) {
        this.alamatPihak = alamatPihak;
    }

    public String getNoTelefon1Pihak() {
        return noTelefon1Pihak;
    }

    public void setNoTelefon1Pihak(String noTelefon1Pihak) {
        this.noTelefon1Pihak = noTelefon1Pihak;
    }

    public String getNoTelefon2Pihak() {
        return noTelefon2Pihak;
    }

    public void setNoTelefon2Pihak(String noTelefon2Pihak) {
        this.noTelefon2Pihak = noTelefon2Pihak;
    }

    public String getPoskodPihak() {
        return poskodPihak;
    }

    public void setPoskodPihak(String poskodPihak) {
        this.poskodPihak = poskodPihak;
    }

    public String getNegeriPihak() {
        return negeriPihak;
    }

    public void setNegeriPihak(String negeriPihak) {
        this.negeriPihak = negeriPihak;
    }

    public String getJantinaPihakCarian() {
        return jantinaPihakCarian;
    }

    public void setJantinaPihakCarian(String jantinaPihakCarian) {
        this.jantinaPihakCarian = jantinaPihakCarian;
    }

    public Boolean getNoEdit() {
        return noEdit;
    }

    public void setNoEdit(Boolean noEdit) {
        this.noEdit = noEdit;
    }

    public List<PermohonanPihakMembantah> getListPihak() {
        return listPihak;
    }

    public void setListPihak(List<PermohonanPihakMembantah> listPihak) {
        this.listPihak = listPihak;
    }

    public String getStatusWaris() {
        return statusWaris;
    }

    public void setStatusWaris(String statusWaris) {
        this.statusWaris = statusWaris;
    }
    
}

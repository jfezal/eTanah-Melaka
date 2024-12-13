/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.Dokumen;
import etanah.model.KodBangsa;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.WarisOrangKenaSyak;
import etanah.service.common.KandunganFolderService;
import etanah.util.FileUtil;
import java.io.File;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.KodCawangan;
import etanah.model.Alamat;
import etanah.model.KandunganFolder;
import etanah.model.KodJantina;
import etanah.model.KodWarganegara;
import etanah.model.KodWarnaKP;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.Pengguna;
import etanah.service.BPelService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.service.common.LelongService;
import etanah.util.DMSUtil;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.FileBean;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nurshahida.radzi modify by sitifariza.hanim 18/4/2011 modified by
 * ctzainal 15june 2011
 */
@UrlBinding("/penguatkuasaan/maklumat_orang_disyaki")
public class MaklumatOrangDisyakiActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatOrangDisyakiActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EnforcementService enforcementService;
    FileBean fileToBeUploaded;
    @Inject
    EnforceService enforceService;
    @Inject
    LelongService lelongService;
    @Inject
    KandunganFolderService kandunganFolderService;
    private Permohonan permohonan;
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    private KodNegeri negeri;
    private KodCawangan cawangan;
    private Alamat alamat;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String noPengenalan;
    private String negeri1;
    private String idOrangKenaSyak;
    private String idPermohonan;
    private String kodNegeriOrg;
    private String catatan;
    private String penyerahNoPengenalan;
    private String penyerahNama;
    private String nama;
    private List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak;
    private List<AduanOrangKenaSyak> senaraiOrangKenaSyak;
    private Pengguna pguna;
    private String keterangan;
    private String senaraiHitam;
    private String tempatOksDitahan;
    private String tempatDireman;
    private String tarikhDitahan;
    private String tarikhDiBebas;
    private String diBebas;
    private KodJenisPengenalan kodJenisPengenalan;
    private String kp;
    private String kw;
    private String kj;
    private String kwg;
    private String noTelefon1;
    private KodWarnaKP kodWarnaKP;
    private KodJantina kodJantina;
    private KodWarganegara kodWarganegara;
    private String namaMajikan;
    private String noTelMajikan;
    private String noFaksMajikan;
    private String alamat1Majikan;
    private String alamat2Majikan;
    private String alamat3Majikan;
    private String alamat4Majikan;
    private String poskodMajikan;
    private KodNegeri kodNegeriMajikan;
    private WarisOrangKenaSyak warisOrangKenaSyak;
    private String namaWaris;
    private String alamat1Waris;
    private String alamat2Waris;
    private String alamat3Waris;
    private String alamat4Waris;
    private String poskodWaris;
    private KodNegeri kodNegeriWaris;
    private String hubungan;
    private String noPengenalanWaris;
    private KodJenisPengenalan jenisPengenalanWaris;
    private String kodBangsaOKS;
    private String kerja;
    private String tarikhLahir;
    private String hari;
    private String bulan;
    private String tahun;
    private String hariReadonly;
    private String bulanReadonly;
    private String tahunReadonly;
    private KodJantina kodJantinaWaris;
    private KodWarganegara kodWarganegaraWaris;
    private String noTelWaris;
    private String noTelBimbitWaris;
    private String kngri; /* kod negeri waris*/

    private String kp1; /*jenis pengenalan waris*/

    private String kjt; /*kod jantina*/

    private String kodNMajikan; /*kod negeri majikan*/

    private List<KodBangsa> senaraiBangsa;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private String idDok;
    private Dokumen d;
    private KandunganFolder kf;
    private Boolean opFlag = false;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private List<OperasiPenguatkuasaan> listOp;
    private String idOp;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Boolean statusIP = Boolean.FALSE;
    private String kodNegeriUrusan;
    private Boolean insertOKS = Boolean.FALSE;
    private String stageId;
    private String taskId;
    private Pengguna pengguna;

    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }
            }
        }
        return new JSP("penguatkuasaan/maklumat_orang_disyaki.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
            }
        }
        return new JSP("penguatkuasaan/maklumat_orang_disyaki_view.jsp").addParameter("tab", "true");
    }

    public Resolution hakMilikPopup() {
        //idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::add record - id OP : " + idOp);
        return new JSP("penguatkuasaan/popup_maklumat_orang_disyaki.jsp").addParameter("popup", "true");
    }

    public Resolution editOrangKenaSyak() {
        idOrangKenaSyak = getContext().getRequest().getParameter("idOrangKenaSyak");
        aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOrangKenaSyak));

        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::add record - id OP : " + idOp);

        if (aduanOrangKenaSyak.getTarikhlahir() != null) {
            System.out.println("Tarikh Lahir : " + aduanOrangKenaSyak.getTarikhlahir());
            hari = sdf1.format(aduanOrangKenaSyak.getTarikhlahir()).substring(0, 2);
            System.out.println("hari" + hari);
            if (hari.startsWith("0")) {
                System.out.println("hari ada 0");
                hari = sdf1.format(aduanOrangKenaSyak.getTarikhlahir()).substring(1, 2);
                System.out.println("value hari ada 0 : " + hari);
            }
            bulan = sdf1.format(aduanOrangKenaSyak.getTarikhlahir()).substring(3, 5);
            System.out.println("bulan" + bulan);
            if (bulan.startsWith("0")) {
                System.out.println("bulan ada 0");
                bulan = sdf1.format(aduanOrangKenaSyak.getTarikhlahir()).substring(4, 5);
                System.out.println("value bulan ada 0 : " + bulan);
            }
            tahun = sdf1.format(aduanOrangKenaSyak.getTarikhlahir()).substring(6, 10);
            System.out.println("hantar value bulan :" + bulan);
            System.out.println("tahun" + tahun);


        }


        warisOrangKenaSyak = enforceService.findWarisOKS((aduanOrangKenaSyak.getIdOrangKenaSyak()));
        return new JSP("penguatkuasaan/popup_edit_OKS.jsp").addParameter("popup", "true");
    }

    public Resolution viewOrangKenaSyak() {
        idOrangKenaSyak = getContext().getRequest().getParameter("idOrangKenaSyak");
        aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOrangKenaSyak));
        warisOrangKenaSyak = enforceService.findWarisOKS((aduanOrangKenaSyak.getIdOrangKenaSyak()));
        return new JSP("penguatkuasaan/popup_view_OKS.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));


        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            aduanOrangKenaSyak = enforceService.findByIdDokumen(id);
            aduanOrangKenaSyak.setDokumen(null);
            enforceService.editOrangKenaSyak(aduanOrangKenaSyak);

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
        return new RedirectResolution(MaklumatOrangDisyakiActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        kodNegeriUrusan = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        idOrangKenaSyak = getContext().getRequest().getParameter("idOrangKenaSyak");

        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            logger.info("-------------stageId----- :" + stageId);
        } else {
            stageId = "terima_kpsn_ks";
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiBangsa = enforceService.senaraiBangsa();
            aduanOrangKenaSyak = new AduanOrangKenaSyak();
            aduanOrangKenaSyak.setPermohonan(permohonan);

            List<AduanOrangKenaSyak> list = enforceService.findByID(idPermohonan);
            if (list != null && list.size() == 1) {
                aduanOrangKenaSyak = list.get(0);

            } else if (idOrangKenaSyak != null && list != null) {
                for (AduanOrangKenaSyak lokasi : list) {
                    if (String.valueOf(lokasi.getIdOrangKenaSyak()).equals(idOrangKenaSyak)) {
                        aduanOrangKenaSyak = lokasi;

                        break;
                    }
                }
            } else {
                aduanOrangKenaSyak = null;
            }
            if (permohonan == null) {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return;
            }
            logger.debug("idPermohonan :" + idPermohonan);
            senaraiAduanOrangKenaSyak = enforceService.findByID(idPermohonan);

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                    listOp = enforceService.findListLaporanOperasi(idPermohonan);
                    System.out.println("listOp size : " + listOp.size());
                    if (listOp.size() != 0) {
                        System.out.println("opFlag true");
                        opFlag = true;
                        System.out.println("opFlag ---- : " + opFlag);
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

                            if (stageId.equalsIgnoreCase("terima_kpsn_ks") || stageId.equalsIgnoreCase("keputusan_kompaundakwa")) {
                                insertOKS = true;
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
                } else {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                }

                logger.info("::::::: stage id :" + stageId + " :::: status insertOKS :" + insertOKS);
            }
        }
    }

    public Resolution refreshpage() {
        return new RedirectResolution(MaklumatOrangDisyakiActionBean.class, "locate");
    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        if (insertOKS) {
            permohonan = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
        }

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (StringUtils.isNotEmpty(hariReadonly)) {
            tarikhLahir = hariReadonly + "/" + bulanReadonly + "/" + tahunReadonly;
        } else {
            tarikhLahir = hari + "/" + bulan + "/" + tahun;
        }
        Alamat al = new Alamat();
        KodNegeri kn = new KodNegeri();

        idOp = getContext().getRequest().getParameter("idOp");

        if (aduanOrangKenaSyak == null) {
            aduanOrangKenaSyak = new AduanOrangKenaSyak();
        }
        aduanOrangKenaSyak = new AduanOrangKenaSyak();
        aduanOrangKenaSyak.setPermohonan(permohonan);
        aduanOrangKenaSyak.setCawangan(pguna.getKodCawangan());
        aduanOrangKenaSyak.setInfoAudit(ia);
        aduanOrangKenaSyak.setNama(nama);
        aduanOrangKenaSyak.setNoPengenalan(noPengenalan);
        aduanOrangKenaSyak.setCatatan(catatan);
        aduanOrangKenaSyak.setKeterangan(keterangan);
        aduanOrangKenaSyak.setSenaraiHitam(senaraiHitam);
        aduanOrangKenaSyak.setDiBebas(diBebas);
        aduanOrangKenaSyak.setTempatDireman(tempatDireman);
        aduanOrangKenaSyak.setTempatOksDitahan(tempatOksDitahan);
        aduanOrangKenaSyak.setPekerjaan(kerja);

        //Added by latifah 13/7/2012 - new implementation of multiple operasi
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {

                logger.info("::simpan record - id OP : " + idOp);
                operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOp));
                aduanOrangKenaSyak.setOperasiPenguatkuasaan(operasiPenguatkuasaan);
            }
        } else if ("05".equals(conf.getProperty("kodNegeri"))) { //Added Hafifi 03/10/2013
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                if (operasiPenguatkuasaan != null) {
                    aduanOrangKenaSyak.setOperasiPenguatkuasaan(operasiPenguatkuasaan);
                }
            }
        }




        if (kodBangsaOKS != null) {
            KodBangsa kb = new KodBangsa();
            kb.setKod(kodBangsaOKS);
            aduanOrangKenaSyak.setKodBangsa(kb);
        }

        if (kp != null) {
            KodJenisPengenalan kod1 = new KodJenisPengenalan();
            kod1.setKod(kp);
            aduanOrangKenaSyak.setKodJenisPengenalan(kod1);
        }
        if (kj != null) {
            KodJantina kj1 = new KodJantina();
            kj1.setKod(kj);
            aduanOrangKenaSyak.setKodJantina(kj1);
        }

        if (kwg != null) {
            KodWarganegara kwg1 = new KodWarganegara();
            kwg1.setKod(kwg);
            aduanOrangKenaSyak.setKodWarganegara(kwg1);
        }
        if (kodNMajikan != null) {
            KodNegeri knmajikan = new KodNegeri();
            knmajikan.setKod(kodNMajikan);
            aduanOrangKenaSyak.setKodNegeriMajikan(knmajikan);
        }

        if (kw != null) {
            KodWarnaKP kw1 = new KodWarnaKP();
            kw1.setKod(kw);
            aduanOrangKenaSyak.setKodWarnaKP(kw1);
        }

        aduanOrangKenaSyak.setAlamat1Majikan(alamat1Majikan);
        aduanOrangKenaSyak.setAlamat2Majikan(alamat2Majikan);
        aduanOrangKenaSyak.setAlamat3Majikan(alamat3Majikan);
        aduanOrangKenaSyak.setAlamat4Majikan(alamat4Majikan);
        aduanOrangKenaSyak.setPoskodMajikan(poskodMajikan);
        aduanOrangKenaSyak.setNoFaksMajikan(noFaksMajikan);
        aduanOrangKenaSyak.setNoTelMajikan(noTelMajikan);
        aduanOrangKenaSyak.setNamaMajikan(namaMajikan);
        aduanOrangKenaSyak.setNoTelefon1(noTelefon1);

        if (tarikhLahir != null) {
            try {
                aduanOrangKenaSyak.setTarikhlahir(dateF.parse(tarikhLahir));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }
        if (tarikhDiBebas != null) {
            try {
                aduanOrangKenaSyak.setTarikhDiBebas(dateF.parse(tarikhDiBebas));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }

        if (tarikhDitahan != null) {
            try {
                aduanOrangKenaSyak.setTarikhDitahan(dateF.parse(tarikhDitahan));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }

        if (tarikhSenaraiHitam != null) {
            try {
                aduanOrangKenaSyak.setTarikhSenaraiHitam(dateF.parse(tarikhSenaraiHitam));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }

        alamat1 = getContext().getRequest().getParameter("alamat1");
        al.setAlamat1(alamat1);
        alamat2 = getContext().getRequest().getParameter("alamat2");
        al.setAlamat2(alamat2);
        alamat3 = getContext().getRequest().getParameter("alamat3");
        al.setAlamat3(alamat3);
        alamat4 = getContext().getRequest().getParameter("alamat4");
        al.setAlamat4(alamat4);
        String negeri2 = getContext().getRequest().getParameter("negeri1");
        kn.setKod(negeri2);
        al.setNegeri(kn);
        poskod = getContext().getRequest().getParameter("poskod");
        al.setPoskod(poskod);
        aduanOrangKenaSyak.setAlamat(al);

        //added to cater new data for OKS (sek 425 & sek 426 MLK)
        if (insertOKS) {
            logger.info("::: update column status IP & id KS");
            aduanOrangKenaSyak.setStatusIp("Y");
            aduanOrangKenaSyak.setPermohonanAduanOrangKenaSyak(permohonanDAO.findById(idPermohonan));
            aduanOrangKenaSyak.setStatusOrangKenaSyak("1");
        }
        enforceService.simpanAduanOrangDisyaki(aduanOrangKenaSyak);


        warisOrangKenaSyak = enforceService.findWarisOKS(aduanOrangKenaSyak.getIdOrangKenaSyak());
        if (warisOrangKenaSyak == null) {
            warisOrangKenaSyak = new WarisOrangKenaSyak();
        }
        warisOrangKenaSyak.setInfoAudit(ia);
        warisOrangKenaSyak.setCawangan(pguna.getKodCawangan());
        warisOrangKenaSyak.setNama(namaWaris);
        warisOrangKenaSyak.setAlamat1(alamat1Waris);
        warisOrangKenaSyak.setAlamat2(alamat2Waris);
        warisOrangKenaSyak.setAlamat3(alamat3Waris);
        warisOrangKenaSyak.setAlamat4(alamat4Waris);
        warisOrangKenaSyak.setPoskod(poskodWaris);
        /*kod negeri waris */
        if (kngri != null) {
            KodNegeri kn1 = new KodNegeri();
            kn1.setKod(kngri);
            warisOrangKenaSyak.setNegeri(kn1);
        }
        warisOrangKenaSyak.setAduanOrangKenaSyak(aduanOrangKenaSyak);
        warisOrangKenaSyak.setHubungan(hubungan);
        warisOrangKenaSyak.setNoPengenalan(noPengenalanWaris);

        /*kod jenis pengenalan waris*/
        if (kp1 != null) {
            KodJenisPengenalan kod1 = new KodJenisPengenalan();
            kod1.setKod(kp1);
            warisOrangKenaSyak.setJenisPengenalan(kod1);
        }
        warisOrangKenaSyak.setNoTelefon(noTelWaris);
        warisOrangKenaSyak.setNoTelefonBimbit(noTelBimbitWaris);
        /*kod jantina waris*/
        if (kjt != null) {
            KodJantina kj1 = new KodJantina();
            kj1.setKod(kjt);
            warisOrangKenaSyak.setKodJantina(kj1);
        }

        enforceService.simpanMaklumatWaris(warisOrangKenaSyak);

        if (StringUtils.isNotBlank(idOp)) {
            logger.info("::multipleOp true : add");
            getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/maklumat_orang_disyaki.jsp").addParameter("tab", "true");
    }

    public Resolution edit() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idOrangKenaSyak = getContext().getRequest().getParameter("idOrangKenaSyak");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        if (StringUtils.isNotBlank(hariReadonly) && StringUtils.isNotBlank(bulanReadonly) && StringUtils.isNotBlank(tahunReadonly)) {
            tarikhLahir = hariReadonly + "/" + bulanReadonly + "/" + tahunReadonly;
        } else {
            tarikhLahir = hari + "/" + bulan + "/" + tahun;
        }
        aduanOrangKenaSyak = enforceService.findAduanOrangKenaSyakByIdaduanOrangKenaSyak(Long.parseLong(idOrangKenaSyak));
        System.out.println("id permohonan:" + idPermohonan);


        try {
            Alamat alamat00 = new Alamat();
            alamat00.setAlamat1(aduanOrangKenaSyak.getAlamat().getAlamat1());
            alamat00.setAlamat2(aduanOrangKenaSyak.getAlamat().getAlamat2());
            alamat00.setAlamat3(aduanOrangKenaSyak.getAlamat().getAlamat3());
            alamat00.setAlamat4(aduanOrangKenaSyak.getAlamat().getAlamat4());
            alamat00.setPoskod(aduanOrangKenaSyak.getAlamat().getPoskod());
            if (kodNegeriOrg != null) {
                KodNegeri kodNegeri = new KodNegeri();
                kodNegeri.setKod(kodNegeriOrg);
                alamat00.setNegeri(kodNegeri);
            }

            aduanOrangKenaSyak.setAlamat(alamat00);
        } catch (Exception e) {
            logger.error(e);
        }


        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new Date());
            aduanOrangKenaSyak.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        if (kp != null) {
            KodJenisPengenalan kod1 = new KodJenisPengenalan();
            kod1.setKod(kp);
            aduanOrangKenaSyak.setKodJenisPengenalan(kod1);
        }

        if (kwg != null) {
            KodWarganegara kwg1 = new KodWarganegara();
            kwg1.setKod(kwg);
            aduanOrangKenaSyak.setKodWarganegara(kwg1);
        }

        if (kj != null) {
            KodJantina kj1 = new KodJantina();
            kj1.setKod(kj);
            aduanOrangKenaSyak.setKodJantina(kj1);
        }

        if (kw != null) {
            KodWarnaKP kw1 = new KodWarnaKP();
            kw1.setKod(kw);
            aduanOrangKenaSyak.setKodWarnaKP(kw1);
        }

        if (kodNMajikan != null) {
            KodNegeri kodNegeriMajikan1 = new KodNegeri();
            kodNegeriMajikan1.setKod(kodNMajikan);
            aduanOrangKenaSyak.setKodNegeriMajikan(kodNegeriMajikan1);
        }

        if (kodBangsaOKS != null) {
            KodBangsa kb = new KodBangsa();
            kb.setKod(kodBangsaOKS);
            aduanOrangKenaSyak.setKodBangsa(kb);
        }
        if (tarikhLahir != null) {
            try {
                aduanOrangKenaSyak.setTarikhlahir(dateF.parse(tarikhLahir));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }

        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::edit record - id OP : " + idOp);

        //Added by latifah 13/7/2012 - new implementation of multiple operasi
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {

                logger.info("::simpan record - id OP : " + idOp);
                operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOp));
                aduanOrangKenaSyak.setOperasiPenguatkuasaan(operasiPenguatkuasaan);
            }
        }

        enforceService.editOrangKenaSyak(aduanOrangKenaSyak);

        warisOrangKenaSyak = enforceService.findWarisOKS(aduanOrangKenaSyak.getIdOrangKenaSyak());

        if (warisOrangKenaSyak == null) {
            warisOrangKenaSyak = new WarisOrangKenaSyak();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = warisOrangKenaSyak.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        namaWaris = getContext().getRequest().getParameter("warisOrangKenaSyak.nama");
        hubungan = getContext().getRequest().getParameter("warisOrangKenaSyak.hubungan");
        alamat1Waris = getContext().getRequest().getParameter("warisOrangKenaSyak.alamat1");
        alamat2Waris = getContext().getRequest().getParameter("warisOrangKenaSyak.alamat2");
        alamat3Waris = getContext().getRequest().getParameter("warisOrangKenaSyak.alamat3");
        alamat4Waris = getContext().getRequest().getParameter("warisOrangKenaSyak.alamat4");
        poskodWaris = getContext().getRequest().getParameter("warisOrangKenaSyak.poskod");
        noPengenalanWaris = getContext().getRequest().getParameter("warisOrangKenaSyak.noPengenalan");
        noTelWaris = getContext().getRequest().getParameter("warisOrangKenaSyak.noTelefon");
        noTelBimbitWaris = getContext().getRequest().getParameter("warisOrangKenaSyak.noTelefonBimbit");

        warisOrangKenaSyak.setNama(namaWaris);
        warisOrangKenaSyak.setHubungan(hubungan);
        warisOrangKenaSyak.setAlamat1(alamat1Waris);
        warisOrangKenaSyak.setAlamat2(alamat2Waris);
        warisOrangKenaSyak.setAlamat3(alamat3Waris);
        warisOrangKenaSyak.setAlamat4(alamat4Waris);
        warisOrangKenaSyak.setPoskod(poskodWaris);
        warisOrangKenaSyak.setNoPengenalan(noPengenalanWaris);
        warisOrangKenaSyak.setNoTelefon(noTelWaris);
        warisOrangKenaSyak.setNoTelefonBimbit(noTelBimbitWaris);

        /*kod negeri waris*/
        if (kngri != null) {
            KodNegeri kodNegeri = new KodNegeri();
            kodNegeri.setKod(kngri);
            warisOrangKenaSyak.setNegeri(kodNegeri);
        }

        /*kod jenis pengenalan waris*/
        if (kp1 != null) {
            KodJenisPengenalan kod1 = new KodJenisPengenalan();
            kod1.setKod(kp1);
            warisOrangKenaSyak.setJenisPengenalan(kod1);
        }
        /*jantina waris*/
        if (kjt != null) {
            KodJantina kj1 = new KodJantina();
            kj1.setKod(kjt);
            warisOrangKenaSyak.setKodJantina(kj1);
        }

        warisOrangKenaSyak.setCawangan(pguna.getKodCawangan());
        warisOrangKenaSyak.setInfoAudit(ia);
        warisOrangKenaSyak.setAduanOrangKenaSyak(aduanOrangKenaSyak);

        enforceService.editWarisOrangKenaSyak(warisOrangKenaSyak);

        if (idOp != null && StringUtils.isNotBlank(idOp)) {
            logger.info("::multipleOp true : edit");
            getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(MaklumatOrangDisyakiActionBean.class, "locate");
    }

    public Resolution deleteOrangKenaSyak() {
        idOrangKenaSyak = getContext().getRequest().getParameter("idOrangKenaSyak");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        aduanOrangKenaSyak = enforceService.findAduanOrangKenaSyakByIdaduanOrangKenaSyak(Long.parseLong(idOrangKenaSyak));
        warisOrangKenaSyak = enforceService.findWarisOKS(aduanOrangKenaSyak.getIdOrangKenaSyak());

        if (warisOrangKenaSyak != null) {
            enforceService.deleteAll(warisOrangKenaSyak);
        }
        if (aduanOrangKenaSyak != null) {
            enforceService.deleteAll(aduanOrangKenaSyak);
        }
        addSimpleMessage("Maklumat telah berjaya dihapuskan.");
        return new RedirectResolution(MaklumatOrangDisyakiActionBean.class, "locate");
    }

    public Resolution processUploadDoc() {
        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idOrangKenaSyak = getContext().getRequest().getParameter("idOrangKenaSyak");
        catatan = getContext().getRequest().getParameter("catatan");
        idDok = getContext().getRequest().getParameter("idDok");
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("OKS"); // FIXME : OKS = Orang Kena Syak
        String dokumenPath = conf.getProperty("document.path");

        if (insertOKS) {
            permohonan = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
        }

        String folderId = Long.toString(permohonan.getFolderDokumen().getFolderId());
        logger.info("id dokumen : " + idDok);

        InfoAudit ia = new InfoAudit();

        if (idDok != null && StringUtils.isNotBlank(idDok)) {
            logger.info("existing dokumen");
            d = dokumenDAO.findById(Long.parseLong(idDok));
            ia = d.getInfoAudit();
            ia.setDiKemaskiniOleh(p);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            logger.info("new dokumen");
            d = new Dokumen();
            ia.setDimasukOleh(p);
            ia.setTarikhMasuk(new java.util.Date());
        }

        if (StringUtils.isNotBlank(catatan)) {
            d.setTajuk(catatan);
        } else {
            d.setTajuk(kd.getNama());
        }
        d.setInfoAudit(ia);

        d.setNoVersi("1.0");
        d.setKodDokumen(kd);
        d.setKlasifikasi(kk);

        kf = kandunganFolderService.findByDokumen(d, permohonan.getFolderDokumen());
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
        kf.setDokumen(d);
        kf.setInfoAudit(ia);

        d = dokumenDAO.saveOrUpdate(d);
        kandunganFolderDAO.save(kf);

        String dokumenId = Long.toString(d.getIdDokumen());

        if (fileToBeUploaded != null) {
            try {

                DMSUtil dmsUtil = new DMSUtil();
                String fileName = fileToBeUploaded.getFileName();
                System.out.println("no null::" + fileToBeUploaded.getContentType());
                dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + folderId;
                FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                fileUtil.saveFile(fileName, fileToBeUploaded.getInputStream());
                String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;

                AduanOrangKenaSyak dtn = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOrangKenaSyak));
                if (dtn.getDokumen() != null) {
                    d = dtn.getDokumen();
                    ia = dtn.getDokumen().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setDokumen(dokumenDAO.findById(Long.parseLong(dokumenId)));
                dtn.setInfoAudit(ia);

                enforceService.editOrangKenaSyak(dtn);

                updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), fileToBeUploaded.getContentType());
            } catch (Exception ex) {
                Logger.getLogger(UploadAction1.class).error(ex);
            }
        }
        addSimpleMessage("Muat naik fail berjaya.");
        return new JSP("penguatkuasaan/orang_kena_syak_upload.jsp").addParameter("popup", "true");
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

    public Resolution popupUpload() {
        logger.info("idOrangKenaSyak :" + idOrangKenaSyak);
        idDok = getContext().getRequest().getParameter("idDokumen");
        logger.info("idDok :" + idDok);
        return new JSP("penguatkuasaan/orang_kena_syak_upload.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        rehydrate();
        return new RedirectResolution(MaklumatOrangDisyakiActionBean.class, "locate");
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the senaraiOrangKenaSyak
     */
    public List<AduanOrangKenaSyak> getSenaraiOrangKenaSyak() {
        return senaraiOrangKenaSyak;
    }

    /**
     * @param senaraiOrangKenaSyak the senaraiOrangKenaSyak to set
     */
    public void setSenaraiOrangKenaSyak(List<AduanOrangKenaSyak> senaraiOrangKenaSyak) {
        this.senaraiOrangKenaSyak = senaraiOrangKenaSyak;
    }

    /**
     * @return the negeri1
     */
    public String getNegeri1() {
        return negeri1;
    }

    /**
     * @param negeri1 the negeri1 to set
     */
    public void setNegeri1(String negeri1) {
        this.negeri1 = negeri1;
    }

    public String getKodNegeriOrg() {
        return kodNegeriOrg;
    }

    public void setKodNegeriOrg(String kodNegeriOrg) {
        this.kodNegeriOrg = kodNegeriOrg;
    }

    public String getSenaraiHitam() {
        return senaraiHitam;
    }

    public void setSenaraiHitam(String senaraiHitam) {
        this.senaraiHitam = senaraiHitam;
    }

    public String getTarikhSenaraiHitam() {
        return tarikhSenaraiHitam;
    }

    public void setTarikhSenaraiHitam(String tarikhSenaraiHitam) {
        this.tarikhSenaraiHitam = tarikhSenaraiHitam;
    }

    public String getKp() {
        return kp;
    }

    public void setKp(String kp) {
        this.kp = kp;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getTempatOksDitahan() {
        return tempatOksDitahan;
    }

    public void setTempatOksDitahan(String tempatOksDitahan) {
        this.tempatOksDitahan = tempatOksDitahan;
    }

    public String getKodNMajikan() {
        return kodNMajikan;
    }

    public void setKodNMajikan(String kodNMajikan) {
        this.kodNMajikan = kodNMajikan;
    }

    public String getKodBangsaOKS() {
        return kodBangsaOKS;
    }

    public void setKodBangsaOKS(String kodBangsaOKS) {
        this.kodBangsaOKS = kodBangsaOKS;
    }

    public String getKerja() {
        return kerja;
    }

    public void setKerja(String kerja) {
        this.kerja = kerja;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getKjt() {
        return kjt;
    }

    public void setKjt(String kjt) {
        this.kjt = kjt;
    }

    public String getKp1() {
        return kp1;
    }

    public void setKp1(String kp1) {
        this.kp1 = kp1;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getKngri() {
        return kngri;
    }

    public void setKngri(String kngri) {
        this.kngri = kngri;
    }

    public String getAlamat1Waris() {
        return alamat1Waris;
    }

    public void setAlamat1Waris(String alamat1Waris) {
        this.alamat1Waris = alamat1Waris;
    }

    public String getAlamat2Waris() {
        return alamat2Waris;
    }

    public void setAlamat2Waris(String alamat2Waris) {
        this.alamat2Waris = alamat2Waris;
    }

    public String getAlamat3Waris() {
        return alamat3Waris;
    }

    public void setAlamat3Waris(String alamat3Waris) {
        this.alamat3Waris = alamat3Waris;
    }

    public String getAlamat4Waris() {
        return alamat4Waris;
    }

    public void setAlamat4Waris(String alamat4Waris) {
        this.alamat4Waris = alamat4Waris;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public KodJenisPengenalan getJenisPengenalanWaris() {
        return jenisPengenalanWaris;
    }

    public void setJenisPengenalanWaris(KodJenisPengenalan jenisPengenalanWaris) {
        this.jenisPengenalanWaris = jenisPengenalanWaris;
    }

    public KodJantina getKodJantinaWaris() {
        return kodJantinaWaris;
    }

    public void setKodJantinaWaris(KodJantina kodJantinaWaris) {
        this.kodJantinaWaris = kodJantinaWaris;
    }

    public KodNegeri getKodNegeriWaris() {
        return kodNegeriWaris;
    }

    public void setKodNegeriWaris(KodNegeri kodNegeriWaris) {
        this.kodNegeriWaris = kodNegeriWaris;
    }

    public KodWarganegara getKodWarganegaraWaris() {
        return kodWarganegaraWaris;
    }

    public void setKodWarganegaraWaris(KodWarganegara kodWarganegaraWaris) {
        this.kodWarganegaraWaris = kodWarganegaraWaris;
    }

    public String getNamaWaris() {
        return namaWaris;
    }

    public void setNamaWaris(String namaWaris) {
        this.namaWaris = namaWaris;
    }

    public String getNoPengenalanWaris() {
        return noPengenalanWaris;
    }

    public void setNoPengenalanWaris(String noPengenalanWaris) {
        this.noPengenalanWaris = noPengenalanWaris;
    }

    public String getNoTelBimbitWaris() {
        return noTelBimbitWaris;
    }

    public void setNoTelBimbitWaris(String noTelBimbitWaris) {
        this.noTelBimbitWaris = noTelBimbitWaris;
    }

    public String getNoTelWaris() {
        return noTelWaris;
    }

    public void setNoTelWaris(String noTelWaris) {
        this.noTelWaris = noTelWaris;
    }

    public String getPoskodWaris() {
        return poskodWaris;
    }

    public void setPoskodWaris(String poskodWaris) {
        this.poskodWaris = poskodWaris;
    }

    public WarisOrangKenaSyak getWarisOrangKenaSyak() {
        return warisOrangKenaSyak;
    }

    public void setWarisOrangKenaSyak(WarisOrangKenaSyak warisOrangKenaSyak) {
        this.warisOrangKenaSyak = warisOrangKenaSyak;
    }
//    private String kwg;

    public String getKwg() {
        return kwg;
    }

    public void setKwg(String kwg) {
        this.kwg = kwg;
    }

    public String getKj() {
        return kj;
    }

    public void setKj(String kj) {
        this.kj = kj;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getAlamat1Majikan() {
        return alamat1Majikan;
    }

    public void setAlamat1Majikan(String alamat1Majikan) {
        this.alamat1Majikan = alamat1Majikan;
    }

    public String getAlamat2Majikan() {
        return alamat2Majikan;
    }

    public void setAlamat2Majikan(String alamat2Majikan) {
        this.alamat2Majikan = alamat2Majikan;
    }

    public String getAlamat3Majikan() {
        return alamat3Majikan;
    }

    public void setAlamat3Majikan(String alamat3Majikan) {
        this.alamat3Majikan = alamat3Majikan;
    }

    public String getAlamat4Majikan() {
        return alamat4Majikan;
    }

    public void setAlamat4Majikan(String alamat4Majikan) {
        this.alamat4Majikan = alamat4Majikan;
    }

    public KodJantina getKodJantina() {
        return kodJantina;
    }

    public void setKodJantina(KodJantina kodJantina) {
        this.kodJantina = kodJantina;
    }

    public KodNegeri getKodNegeriMajikan() {
        return kodNegeriMajikan;
    }

    public void setKodNegeriMajikan(KodNegeri kodNegeriMajikan) {
        this.kodNegeriMajikan = kodNegeriMajikan;
    }

    public KodWarganegara getKodWarganegara() {
        return kodWarganegara;
    }

    public void setKodWarganegara(KodWarganegara kodWarganegara) {
        this.kodWarganegara = kodWarganegara;
    }

    public KodWarnaKP getKodWarnaKP() {
        return kodWarnaKP;
    }

    public void setKodWarnaKP(KodWarnaKP kodWarnaKP) {
        this.kodWarnaKP = kodWarnaKP;
    }

    public String getNamaMajikan() {
        return namaMajikan;
    }

    public void setNamaMajikan(String namaMajikan) {
        this.namaMajikan = namaMajikan;
    }

    public String getNoFaksMajikan() {
        return noFaksMajikan;
    }

    public void setNoFaksMajikan(String noFaksMajikan) {
        this.noFaksMajikan = noFaksMajikan;
    }

    public String getNoTelMajikan() {
        return noTelMajikan;
    }

    public void setNoTelMajikan(String noTelMajikan) {
        this.noTelMajikan = noTelMajikan;
    }

    public String getPoskodMajikan() {
        return poskodMajikan;
    }

    public void setPoskodMajikan(String poskodMajikan) {
        this.poskodMajikan = poskodMajikan;
    }

    public String getDiBebas() {
        return diBebas;
    }

    public void setDiBebas(String diBebas) {
        this.diBebas = diBebas;
    }

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public String getTarikhDiBebas() {
        return tarikhDiBebas;
    }

    public void setTarikhDiBebas(String tarikhDiBebas) {
        this.tarikhDiBebas = tarikhDiBebas;
    }

    public String getTarikhDitahan() {
        return tarikhDitahan;
    }

    public void setTarikhDitahan(String tarikhDitahan) {
        this.tarikhDitahan = tarikhDitahan;
    }

    public String getTempatDireman() {
        return tempatDireman;
    }

    public void setTempatDireman(String tempatDireman) {
        this.tempatDireman = tempatDireman;
    }
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    String tarikhSenaraiHitam;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getIdOrangKenaSyak() {
        return idOrangKenaSyak;
    }

    public void setIdOrangKenaSyak(String idOrangKenaSyak) {
        this.idOrangKenaSyak = idOrangKenaSyak;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodNegeri getKodNegeri() {
        return negeri;
    }

    public void setKodNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public List<AduanOrangKenaSyak> getSenaraiAduanOrangKenaSyak() {
        return senaraiAduanOrangKenaSyak;
    }

    public void setSenaraiAduanOrangKenaSyak(List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak) {
        this.senaraiAduanOrangKenaSyak = senaraiAduanOrangKenaSyak;
    }

    public List<KodBangsa> getSenaraiBangsa() {
        return senaraiBangsa;
    }

    public void setSenaraiBangsa(List<KodBangsa> senaraiBangsa) {
        this.senaraiBangsa = senaraiBangsa;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public String getIdDok() {
        return idDok;
    }

    public void setIdDok(String idDok) {
        this.idDok = idDok;
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

    public String getIdOp() {
        return idOp;
    }

    public void setIdOp(String idOp) {
        this.idOp = idOp;
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

    public String getBulanReadonly() {
        return bulanReadonly;
    }

    public void setBulanReadonly(String bulanReadonly) {
        this.bulanReadonly = bulanReadonly;
    }

    public String getHariReadonly() {
        return hariReadonly;
    }

    public void setHariReadonly(String hariReadonly) {
        this.hariReadonly = hariReadonly;
    }

    public String getTahunReadonly() {
        return tahunReadonly;
    }

    public void setTahunReadonly(String tahunReadonly) {
        this.tahunReadonly = tahunReadonly;
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

    public String getKodNegeriUrusan() {
        return kodNegeriUrusan;
    }

    public void setKodNegeriUrusan(String kodNegeriUrusan) {
        this.kodNegeriUrusan = kodNegeriUrusan;
    }

    public Boolean getInsertOKS() {
        return insertOKS;
    }

    public void setInsertOKS(Boolean insertOKS) {
        this.insertOKS = insertOKS;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}

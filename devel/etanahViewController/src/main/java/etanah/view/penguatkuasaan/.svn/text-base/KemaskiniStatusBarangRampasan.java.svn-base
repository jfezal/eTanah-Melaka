/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.KompaunDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.BarangRampasan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKeputusan;
import etanah.model.KodNegeri;
import etanah.model.KodStatusBarangRampasan;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.Kompaun;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.SenaraiTugasanActionBean;
import etanah.view.stripes.jenisKuasaActionBean;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/kemaskini_status_barang_rampasan")
public class KemaskiniStatusBarangRampasan extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KemaskiniStatusBarangRampasan.class);
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private BarangRampasanDAO barangRampasanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    PermohonanTuntutanButiranDAO permohonanTuntutanButiranDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idBarang;
    private List<KodStatusBarangRampasan> senaraiKodStatus;
    private String status;
    private Pengguna pengguna;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private String kodNegeriPermohonan;
    private List<BarangRampasan> senaraiBarangRampasan;
    private List<OperasiPenguatkuasaan> listOp;
    private Boolean opFlag = false;
    private BarangRampasan barang;
    private String tarikhTuntut;
    private String tarikhDilepas;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private String taskId;
    private String messageAgihTugasan = "";
    private Boolean keputusanLH = false;
    private Boolean keputusanRM = false;
    private String stageId;
    IWorkflowContext ctxOnBehalf = null;
    private String nextStage;
    private PermohonanNota permohonanNota;
    private boolean statusNotaExist = Boolean.TRUE;
    private boolean stageLucutHak = Boolean.FALSE;
    private boolean stageRujukMahkamah = Boolean.FALSE;
    private String nilaiJualan;
    private PermohonanTuntutanKos ptk;
    private PermohonanTuntutan pt;
    private PermohonanTuntutanButiran ptb;
    private List<Kompaun> senaraiJualan;
    private Kompaun kompaun;
    private List<PermohonanTuntutanBayar> senaraiPermohonanTuntutanBayar;
    private String namaPenerima;
    private String alamatPenerima1;
    private String alamatPenerima2;
    private String alamatPenerima3;
    private String alamatPenerima4;
    private String noPengenalanPenerimaLain;
    private String noPengenalanPenerimaBaru;
    private KodJenisPengenalan kodPengenalanPenerima;

    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }

                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/kemaskini_status_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                getContext().getRequest().setAttribute("viewMultipleOp", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("view", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("view", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/kemaskini_status_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }

                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_tuntutan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }

                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_dilepaskan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }

                getContext().getRequest().setAttribute("viewMultipleOp", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("view", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("view", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_tuntutan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }

                getContext().getRequest().setAttribute("addKeputusanBarangRampasan", Boolean.TRUE);
            }
        }
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_tuntutan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }

                getContext().getRequest().setAttribute("viewKeputusanBarangRampasan", Boolean.TRUE);
            }
        }
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_tuntutan.jsp").addParameter("tab", "true");
    }

    public Resolution formJualan() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }

                getContext().getRequest().setAttribute("editBayaranJualan", Boolean.TRUE);
            }
        }
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_tuntutan.jsp").addParameter("tab", "true");
    }

    public Resolution viewBayaranJualan() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }

                getContext().getRequest().setAttribute("viewBayaranJualan", Boolean.TRUE);
            }
        }
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_tuntutan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        } else {
            stageId = "syor_pelupusan";
        }
        logger.info("-------------stageId :: ------------------" + stageId);

        senaraiKodStatus = enforceService.getSenaraiKodStatusBarangRampasan();

        logger.info("-------------rehydrate------------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeriPermohonan = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    listOp = enforceService.findListLaporanOperasi(idPermohonan);
                    System.out.println("listOp size : " + listOp.size());
                    if (listOp.size() != 0) {
                        System.out.println("opFlag true");
                        opFlag = true;
                        System.out.println("opFlag ---- : " + opFlag);
                    } else {
                        if (permohonan.getPermohonanSebelum() != null) {
                            System.out.println(":::::::;;" + permohonan.getPermohonanSebelum().getIdPermohonan());
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            opFlag = true;
                            System.out.println("opFlag ---- : " + opFlag);

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
                } else {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                }

            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }
            if (operasiPenguatkuasaan != null) {
                senaraiBarangRampasan = enforceService.findByIDOperasi(operasiPenguatkuasaan.getIdOperasi());
//                tarikhTuntut = barang.getTarikhTuntut();
                logger.info("-------------size senarai barang rampasan ------------------ :" + senaraiBarangRampasan.size());


            }


            permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            logger.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
            if (permohonanNota != null) {
                logger.info("::: kandungan nota :" + permohonanNota.getNota());
                statusNotaExist = false;
            }

            //checking stage id : for new id permohonan created after made decision lucuthak or rujuk mahkamah

            //1- Lucuthak
            ArrayList<String> senaraiStageTerlibatLucutHak = new ArrayList<String>();
            senaraiStageTerlibatLucutHak.add("syor_pelupusan");
            senaraiStageTerlibatLucutHak.add("semak_pelupusan");
            senaraiStageTerlibatLucutHak.add("arah_pelupusan");
            senaraiStageTerlibatLucutHak.add("maklum_arahan_pelupusan");
            senaraiStageTerlibatLucutHak.add("terima_arahan_pelupusan");
            senaraiStageTerlibatLucutHak.add("kemasukan_pelupusan");
            senaraiStageTerlibatLucutHak.add("maklum_keputusan_pelupusan");
            //for sek 425 & 425A
            senaraiStageTerlibatLucutHak.add("terima_arahan_pelupusan1");
            senaraiStageTerlibatLucutHak.add("terima_arahan_pelupusan2");
            senaraiStageTerlibatLucutHak.add("sedia_rekod_pelupusan");
            senaraiStageTerlibatLucutHak.add("maklum_kpsn_pelupusan");



            for (Object kod : senaraiStageTerlibatLucutHak) {
                if (kod.toString().equalsIgnoreCase(stageId)) {
                    stageLucutHak = true;
                }
            }

            //2- Rujuk Mahkamah
            ArrayList<String> senaraiStageTerlibatRujukMahkamah = new ArrayList<String>();
            senaraiStageTerlibatRujukMahkamah.add("surat_tuntut_brg_rampasan");
            senaraiStageTerlibatRujukMahkamah.add("rujuk_pelepasan_sementara");
            senaraiStageTerlibatRujukMahkamah.add("buka_ks_brg_rampas");
            senaraiStageTerlibatRujukMahkamah.add("peraku_srt_brg_rampas");
            senaraiStageTerlibatRujukMahkamah.add("kpsn_mahkamah");
            senaraiStageTerlibatRujukMahkamah.add("kemaskini_kpsn_mahkamah2");
            senaraiStageTerlibatRujukMahkamah.add("laksana_perintah_mahkamah");
            senaraiStageTerlibatRujukMahkamah.add("terima_lepas_barangan");
            senaraiStageTerlibatRujukMahkamah.add("sedia_srt_serahan");
            senaraiStageTerlibatRujukMahkamah.add("periksa_brg_lepas");
            senaraiStageTerlibatRujukMahkamah.add("imbas_kemaskini_brg");
            //for sek 425 & 425A
             senaraiStageTerlibatRujukMahkamah.add("imbas_srt_rampasan");
            senaraiStageTerlibatRujukMahkamah.add("rujuk_mahkamah");
            senaraiStageTerlibatRujukMahkamah.add("cetak_afidavit");
            senaraiStageTerlibatRujukMahkamah.add("sahkan_srt_iringan");
            senaraiStageTerlibatRujukMahkamah.add("imbas_kpsn_mahkamah");
            senaraiStageTerlibatRujukMahkamah.add("masuk_kpsn_mahkamah");
            senaraiStageTerlibatRujukMahkamah.add("kpsn_mahkamah");
            senaraiStageTerlibatRujukMahkamah.add("arah_lepas_rampasan");
            senaraiStageTerlibatRujukMahkamah.add("terima_arahan_pelepasan");
            senaraiStageTerlibatRujukMahkamah.add("sedia_srt_pelepasan");
            senaraiStageTerlibatRujukMahkamah.add("imbas_dokumen");
            senaraiStageTerlibatRujukMahkamah.add("kemaskini_inventori");

            for (Object kod : senaraiStageTerlibatRujukMahkamah) {
                if (kod.toString().equalsIgnoreCase(stageId)) {
                    stageRujukMahkamah = true;
                }
            }
            logger.info(":::::: stageLucutHak:::::: " + stageLucutHak);
            logger.info(":::::: stageRujukMahkamah:::::: " + stageRujukMahkamah);


            senaraiJualan = enforceService.findKompaunByKodStatusTerima(idPermohonan, "BP");
            logger.info("------------size senaraiJualan-------------- : " + senaraiJualan.size());
            if (senaraiJualan.size() != 0) {
                if (senaraiJualan.get(0).getAmaun() != null) {
                    nilaiJualan = senaraiJualan.get(0).getAmaun().toString();
                }
            }
            for (int i = 0; i < senaraiJualan.size(); i++) {
                Kompaun k = senaraiJualan.get(i);
                if (k.getPermohonanTuntutanKos() != null) {
                    senaraiPermohonanTuntutanBayar = enforcementService.getSenaraiPtb(k.getPermohonanTuntutanKos().getIdKos());
                    System.out.println("size senaraiPermohonanTuntutanBayar :" + senaraiPermohonanTuntutanBayar.size());
                }
            }

            if (permohonan != null) {
                namaPenerima = permohonan.getNamaPenerima();
                alamatPenerima1 = permohonan.getAlamatPenerima1();
                alamatPenerima2 = permohonan.getAlamatPenerima2();
                alamatPenerima3 = permohonan.getAlamatPenerima3();
                alamatPenerima4 = permohonan.getAlamatPenerima4();
                if (permohonan.getKodPengenalanPenerima() != null) {
                    if (permohonan.getKodPengenalanPenerima().getKod().equalsIgnoreCase("B")) {
                        noPengenalanPenerimaBaru = permohonan.getNoPengenalanPenerima();
                    } else {
                        noPengenalanPenerimaLain = permohonan.getNoPengenalanPenerima();
                    }
                }

            }

        }


    }

    public Resolution simpan() {
        logger.info("-------------updateStatus---------------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        try {
            System.out.println("list size masa simpan : " + senaraiBarangRampasan.size());
            for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                idBarang = getContext().getRequest().getParameter("idBarang" + i);
                String statusBarang = getContext().getRequest().getParameter("status" + i);
//                tarikhTuntut = getContext().getRequest().getParameter("tarikhTuntut" + i);
//                tarikhDilepas = getContext().getRequest().getParameter("tarikhDilepas" + i);
                System.out.println("idBarang value : " + idBarang);
                System.out.println("status value : " + statusBarang);
                if (idBarang != null) {
                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    KodStatusBarangRampasan kodStatus = new KodStatusBarangRampasan();
                    kodStatus.setKod(statusBarang);
                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setStatus(kodStatus);
//                    if (tarikhTuntut != null) {
//                        try {
//                            barang.setTarikhTuntut(sdf.parse(tarikhTuntut));
//                        } catch (ParseException ex) {
//                            System.out.println("Got error" + ex);
//                        }
//                    }

//                    if (tarikhDilepas != null) {
//                        try {
//                            barang.setTarikhDilepas(sdf.parse(tarikhDilepas));
//                        } catch (ParseException ex) {
//                            System.out.println("Got error" + ex);
//                        }
//                    }
                    enforceService.updateBarangRampasan(barang);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(KemaskiniStatusBarangRampasan.class, "locate");
    }

    public Resolution simpanStatusBarang() {
        logger.info("------------simpanStatusBarang--------------");
        for (int i = 0; i < listOp.size(); i++) {
            List<BarangRampasan> listBarangRampasan = new ArrayList<BarangRampasan>();
            listBarangRampasan = listOp.get(i).getSenaraiBarangRampasan();
            logger.info("::::::::::size listBarangRampasan::::::::::  " + listBarangRampasan.size());

            for (int j = 0; j < listBarangRampasan.size(); j++) {
                idBarang = getContext().getRequest().getParameter("idBarang" + i + j);
                String statusBarang = getContext().getRequest().getParameter("status" + i + j);
//                tarikhTuntut = getContext().getRequest().getParameter("tarikhTuntut" + i + j);
//                tarikhDilepas = getContext().getRequest().getParameter("tarikhDilepas" + i + j);
                System.out.println("pilihBarang [" + i + j + "]: " + idBarang);
                System.out.println("status value [" + i + j + "]: " + statusBarang);

                if (StringUtils.isNotBlank(idBarang)) {
                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    KodStatusBarangRampasan kodStatus = new KodStatusBarangRampasan();
                    kodStatus.setKod(statusBarang);
                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setStatus(kodStatus);
//                    if (tarikhTuntut != null) {
//                        try {
//                            barang.setTarikhTuntut(sdf.parse(tarikhTuntut));
//                        } catch (ParseException ex) {
//                            System.out.println("Got error" + ex);
//                        }
//                    }
//                    if (tarikhDilepas != null) {
//                        try {
//                            barang.setTarikhTuntut(sdf.parse(tarikhDilepas));
//                        } catch (ParseException ex) {
//                            System.out.println("Got error" + ex);
//                        }
//                    }

                    enforceService.updateBarangRampasan(barang);
                }

            }
        }
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/kemaskini_status_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanStatusBarangTuntutan() {
        logger.info("------------simpanStatusBarang--------------");
        for (int i = 0; i < listOp.size(); i++) {
            List<BarangRampasan> listBarangRampasan = new ArrayList<BarangRampasan>();
            listBarangRampasan = listOp.get(i).getSenaraiBarangRampasan();
            logger.info("::::::::::size listBarangRampasan::::::::::  " + listBarangRampasan.size());

            for (int j = 0; j < listBarangRampasan.size(); j++) {
                idBarang = getContext().getRequest().getParameter("idBarang" + i + j);
                String statusBarang = getContext().getRequest().getParameter("status" + i + j);
                tarikhTuntut = getContext().getRequest().getParameter("tarikhTuntut" + i + j);
                System.out.println("tarikhTuntut [" + i + j + "]: " + tarikhTuntut);
//                tarikhDilepas = getContext().getRequest().getParameter("tarikhDilepas" + i + j);
                System.out.println("pilihBarang [" + i + j + "]: " + idBarang);
                System.out.println("status value [" + i + j + "]: " + statusBarang);

                if (StringUtils.isNotBlank(idBarang)) {
                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    KodStatusBarangRampasan kodStatus = new KodStatusBarangRampasan();
                    kodStatus.setKod(statusBarang);
                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setStatus(kodStatus);
                    if (StringUtils.isNotEmpty(tarikhTuntut)) {
                        try {
                            barang.setTarikhTuntut(sdf.parse(tarikhTuntut));
                        } catch (ParseException ex) {
                            System.out.println("Got error" + ex);
                        }
                    } else {
                        barang.setTarikhTuntut(null);
                    }
//                    if (tarikhDilepas != null) {
//                        try {
//                            barang.setTarikhTuntut(sdf.parse(tarikhDilepas));
//                        } catch (ParseException ex) {
//                            System.out.println("Got error" + ex);
//                        }
//                    }

                    enforceService.updateBarangRampasan(barang);
                }

            }
        }
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_tuntutan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanStatusBarangDilepaskan() {
        logger.info("------------simpanStatusBarang--------------");
        for (int i = 0; i < listOp.size(); i++) {
            List<BarangRampasan> listBarangRampasan = new ArrayList<BarangRampasan>();
            listBarangRampasan = listOp.get(i).getSenaraiBarangRampasan();
            logger.info("::::::::::size listBarangRampasan::::::::::  " + listBarangRampasan.size());

            for (int j = 0; j < listBarangRampasan.size(); j++) {
                idBarang = getContext().getRequest().getParameter("idBarang" + i + j);
                String statusBarang = getContext().getRequest().getParameter("status" + i + j);
//                tarikhTuntut = getContext().getRequest().getParameter("tarikhTuntut" + i + j);
                tarikhDilepas = getContext().getRequest().getParameter("tarikhDilepas" + i + j);
                System.out.println("pilihBarang [" + i + j + "]: " + idBarang);
                System.out.println("status value [" + i + j + "]: " + statusBarang);

                if (StringUtils.isNotBlank(idBarang)) {
                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    KodStatusBarangRampasan kodStatus = new KodStatusBarangRampasan();
                    kodStatus.setKod(statusBarang);
                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setStatus(kodStatus);
//                    if (tarikhTuntut != null) {
//                        try {
//                            barang.setTarikhTuntut(sdf.parse(tarikhTuntut));
//                        } catch (ParseException ex) {
//                            System.out.println("Got error" + ex);
//                        }
//                    }
                    if (tarikhDilepas != null) {
                        try {
                            barang.setTarikhDilepas(sdf.parse(tarikhDilepas));
                        } catch (ParseException ex) {
                            System.out.println("Got error" + ex);
                        }
                    } else {
                        barang.setTarikhDilepas(null);
                    }

                    enforceService.updateBarangRampasan(barang);
                }

            }
        }
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_dilepaskan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKeputusan() {

        logger.info("------------simpanKeputusan--------------");


        for (int i = 0; i < listOp.size(); i++) {
            List<BarangRampasan> listBarangRampasan = new ArrayList<BarangRampasan>();
            listBarangRampasan = listOp.get(i).getSenaraiBarangRampasan();
            logger.info("::::::::::size listBarangRampasan::::::::::  " + listBarangRampasan.size());

            for (int j = 0; j < listBarangRampasan.size(); j++) {
                idBarang = getContext().getRequest().getParameter("idBarang" + i + j);
                String statusKeputusan = getContext().getRequest().getParameter("statusKeputusan" + i + j);
                System.out.println("statusKeputusan value [" + i + j + "]: " + statusKeputusan);

                if (StringUtils.isNotBlank(idBarang)) {
                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    KodKeputusan kodKpsn = new KodKeputusan();
                    kodKpsn.setKod(statusKeputusan);
                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setKeputusan(kodKpsn);
                    enforceService.updateBarangRampasan(barang);
                }
            }
        }


        getContext().getRequest().setAttribute("addKeputusanBarangRampasan", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_tuntutan.jsp").addParameter("tab", "true");
    }

    public Resolution createPermohonan() throws WorkflowException, StaleObjectException, Exception {
        logger.info("------------simpanKeputusan--------------");


        for (int i = 0; i < listOp.size(); i++) {
            List<BarangRampasan> listBarangRampasan = new ArrayList<BarangRampasan>();
            listBarangRampasan = listOp.get(i).getSenaraiBarangRampasan();
            logger.info("::::::::::size listBarangRampasan::::::::::  " + listBarangRampasan.size());

            for (int j = 0; j < listBarangRampasan.size(); j++) {
                idBarang = getContext().getRequest().getParameter("idBarang" + i + j);
                String statusKeputusan = getContext().getRequest().getParameter("statusKeputusan" + i + j);
                System.out.println("statusKeputusan value [" + i + j + "]: " + statusKeputusan);

                if (StringUtils.isNotBlank(idBarang)) {
                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));

                    if (barang.getKeputusan() != null) {
                        if (barang.getKeputusan().getKod().equalsIgnoreCase("LH")) {
                            keputusanLH = true;
                        } else if (barang.getKeputusan().getKod().equalsIgnoreCase("RM")) {
                            keputusanRM = true;
                        }
                    }


                }

            }
        }
        //1)Initiate task for new id permohonan

        if (keputusanLH == true) {
            createPermohonan(permohonan, pengguna, "LH");
        }

        if (keputusanRM == true) {
            createPermohonan(permohonan, pengguna, "RM");
        }


        //2)Skip stage to buka kes based on selected user(ketua pegawai penyiasat)

        messageAgihTugasan = "Penghantaran Berjaya. \n";
        List<Permohonan> listPermohonanBaru = enforceService.findIdPerserahan(permohonan.getIdPermohonan());
        if (!listPermohonanBaru.isEmpty()) {
            for (Permohonan p : listPermohonanBaru) {
                messageAgihTugasan += p.getIdPermohonan() + " : Permohonan untuk keputusan ";
                List<Task> l = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                for (Task t : l) {
                    if (t.getSystemAttributes().getStage().equalsIgnoreCase("syor_pelupusan")) {
                        messageAgihTugasan += "Lucuthak . \n";
                    }

                    if (t.getSystemAttributes().getStage().equalsIgnoreCase("surat_tuntut_brg_rampasan")) {
                        messageAgihTugasan += "Rujuk Mahkamah . \n";
                    }
                }

            }


        }


        System.out.println("messageAgihTugasan :" + messageAgihTugasan);

        messageAgihTugasan = messageAgihTugasan.replace("\n", "<br>");

        List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);

        if (l.isEmpty()) {
            try {
                Thread.sleep(5000);
                l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
        if (l != null) {
            for (Task t : l) {
                taskId = t.getSystemAttributes().getTaskId();
                if (StringUtils.isNotBlank(taskId)) {
                    try {
                        System.out.println("task id :::: " + taskId);
                        WorkFlowService.withdrawTask(taskId);
                        logger.info("Pembatalan Berjaya :: " + idPermohonan);
                    } catch (StaleObjectException ex) {
                        logger.error(ex);
                    }
                }
            }
        }



        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", messageAgihTugasan);
    }

    public void createPermohonan(Permohonan permohonan, Pengguna pguna, String keputusan) throws WorkflowException, StaleObjectException {
        logger.info(":::::createPermohonan");
        InfoAudit ia = new InfoAudit();

        Permohonan permohonanBaru = new Permohonan();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());


        String idPermohonanBaru = idPermohonanGenerator.generate(
                conf.getProperty("kodNegeri"), pguna.getKodCawangan(), permohonan.getKodUrusan());

        //create id mohon baru
        permohonanBaru.setStatus("TA");
        permohonanBaru.setIdPermohonan(idPermohonanBaru);
        permohonanBaru.setCawangan(pguna.getKodCawangan());
        permohonanBaru.setKodUrusan(permohonan.getKodUrusan());
        permohonanBaru.setCaraPermohonan(permohonan.getCaraPermohonan());
        permohonanBaru.setSebab(permohonan.getSebab());
        permohonanBaru.setPenyerahNama(permohonan.getPenyerahNama());
        permohonanBaru.setPenyerahJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
        permohonanBaru.setPenyerahNoPengenalan(permohonan.getPenyerahNoPengenalan());
        permohonanBaru.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
        permohonanBaru.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
        permohonanBaru.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
        permohonanBaru.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
        permohonanBaru.setPenyerahPoskod(permohonan.getPenyerahPoskod());
        permohonanBaru.setPenyerahNegeri(permohonan.getPenyerahNegeri());
        permohonanBaru.setPenyerahNoTelefon1(permohonan.getPenyerahNoTelefon1());
        permohonanBaru.setPenyerahEmail(permohonan.getPenyerahEmail());
        permohonanBaru.setPermohonanSebelum(permohonan);
        permohonanBaru.setInfoAudit(ia);

        enforceService.savePermohonan(permohonanBaru);

        if (permohonanBaru.getFolderDokumen() == null) {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk(permohonan.getIdPermohonan());
            fd.setInfoAudit(ia);
            enforceService.simpanFolderDokumen(fd);
            permohonanBaru.setFolderDokumen(fd);
        }

        enforceService.savePermohonan(permohonanBaru);


        initiateTask(permohonanBaru, pguna, keputusan);
    }

    public void initiateTask(Permohonan permohonanBaru, Pengguna pengguna, String keputusan) throws WorkflowException, StaleObjectException {
        logger.info("-------initiateTask------- :::: " + permohonanBaru.getIdPermohonan());

        //initiate tugasan
        try {
            if (permohonanBaru.getKodUrusan().getKePTG() == 'Y') {
                WorkFlowService.initiateTask(permohonanBaru.getKodUrusan().getIdWorkflow(),
                        permohonanBaru.getIdPermohonan(), permohonanBaru.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                        permohonanBaru.getKodUrusan().getNama());
            } else if (permohonanBaru.getKodUrusan().getKePTG() == 'T') {
                WorkFlowService.initiateTask(permohonanBaru.getKodUrusan().getIdWorkflow(),
                        permohonanBaru.getIdPermohonan(), permohonanBaru.getCawangan().getKod(), pengguna.getIdPengguna(),
                        permohonanBaru.getKodUrusan().getNama());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        logger.info("------------- SKIP STAGE ---------------");

        ctxOnBehalf = WorkFlowService.authenticate("hafizalkuasa1"); //pptd
        if (ctxOnBehalf != null) {
            System.out.println("ctxOnBehalf : " + ctxOnBehalf);
            System.out.println("id mohon : " + permohonanBaru.getIdPermohonan());

            List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru.getIdPermohonan());
            logger.info("1) Task FOund(size)::" + l.size());
            if (l.isEmpty()) {
                try {
                    Thread.sleep(5000);
                    l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru.getIdPermohonan());
                } catch (Exception ex) {
                    logger.error(ex);
                }
            }
            logger.info("2) Task FOund (size)::" + l.size());
            for (Task t : l) {
                stageId = t.getSystemAttributes().getStage();
                taskId = t.getSystemAttributes().getTaskId();
                WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                logger.debug("Claim Found Task::" + taskId);

                nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, keputusan); // 


                logger.debug("stage id ::::::::::::::::" + stageId);
                logger.debug("next stage ::::::::::::::::" + nextStage);
                logger.debug("Tugasan dihantar ke : " + nextStage);
            }
        }
    }

    public Resolution simpanBayaranJualan() {
        logger.info("------------simpanBayaranJualan--------------");

        InfoAudit ia = new InfoAudit();
        try {

            if (permohonan != null) {
                ia = permohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                permohonan.setNamaPenerima(namaPenerima);
                permohonan.setAlamatPenerima1(alamatPenerima1);
                permohonan.setAlamatPenerima2(alamatPenerima2);
                permohonan.setAlamatPenerima3(alamatPenerima3);
                permohonan.setAlamatPenerima4(alamatPenerima4);
                if (kodPengenalanPenerima != null) {
                    permohonan.setKodPengenalanPenerima(kodPengenalanPenerima);
                    if (kodPengenalanPenerima.getKod().equalsIgnoreCase("B")) {
                        permohonan.setNoPengenalanPenerima(noPengenalanPenerimaBaru);
                    } else {
                        permohonan.setNoPengenalanPenerima(noPengenalanPenerimaLain);
                    }
                }
                permohonan.setInfoAudit(ia);
                enforceService.savePermohonan(permohonan);

            }

            if (senaraiJualan.size() != 0) {
                for (int i = 0; i < senaraiJualan.size(); i++) {
                    kompaun = senaraiJualan.get(i);
                    if (kompaun != null) {
                        ia = kompaun.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());

                        if (ptk != null) {
                            ia = ptk.getInfoAudit();
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new java.util.Date());

                            ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());

                            if (ptb != null) {
                                pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                            }

                        }

                    }

                }

            } else {

                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                /* insert into table PermohonanTuntutKos*/
                ptk = new PermohonanTuntutanKos();

                /* insert into table Kompaun*/
                kompaun = new Kompaun();

                pt = new PermohonanTuntutan();
                ptb = new PermohonanTuntutanButiran();
            }


            nilaiJualan = getContext().getRequest().getParameter("nilaiJualan");
            System.out.println("nilaiJualan" + nilaiJualan);
            BigDecimal amaunJualan = new BigDecimal(nilaiJualan);

            /* save table PermohonanTuntutKos*/

            ptk.setCawangan(pengguna.getKodCawangan());
            ptk.setPermohonan(permohonan);
            ptk.setItem("BAYARAN JUALAN");
            ptk.setAmaunTuntutan(BigDecimal.ZERO);
            KodTransaksi kt = new KodTransaksi();
            kt.setKod("20027");
            ptk.setKodTransaksi(kt);
            KodTuntut ktu = new KodTuntut();
            ktu.setKod("K01");
            ptk.setKodTuntut(ktu);
            ptk.setInfoAudit(ia);
            ptk.setAmaunTuntutan(amaunJualan);
            enforceService.simpanBayaran(ptk);


            kompaun.setPermohonan(permohonan);
            kompaun.setInfoAudit(ia);
            kompaun.setCawangan(pengguna.getKodCawangan());
            kompaun.setAmaun(amaunJualan);
            kompaun.setStatusTerima(kodStatusTerimaDAO.findById("BP")); //BP = Bayaran Pelupusan
//                    kompaun.setTempohHari(Integer.parseInt(tempohHari));
            kompaun.setPermohonanTuntutanKos(ptk);
            kompaun.setNoRujukan("Tiada Data");
            kompaun.setIsuKepada("Tiada Data");
            enforcementService.simpanKompaun(kompaun);

//                    Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(c1);
//                    cal.add(Calendar.DATE, kompaun.getTempohHari());
//
//                    String tarikhAkhirBayar = sdf.format(cal.getTime());
//                    System.out.println("tarikh akhir bayar : " + tarikhAkhirBayar);

            KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
            kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");
            pt.setCawangan(pengguna.getKodCawangan());
            pt.setPermohonan(permohonan);
            pt.setInfoAudit(ia);
            pt.setKodTransaksiTuntut(kodTransTuntut);
            pt.setTarikhTuntutan(new java.util.Date());
//                    pt.setTempoh(Integer.parseInt(tempohHari));
//                    pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
            enforceService.simpanPermohonanTuntutan(pt);

            ptb.setPermohonanTuntutan(pt);
            ptb.setPermohonanTuntutanKos(ptk);
            ptb.setInfoAudit(ia);
            enforceService.simpanPermohonanTuntutanButiran(ptb);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //update status barang rampasan
        logger.info("------------simpanStatusBarang--------------");
        for (int i = 0; i < listOp.size(); i++) {
            List<BarangRampasan> listBarangRampasan = new ArrayList<BarangRampasan>();
            listBarangRampasan = listOp.get(i).getSenaraiBarangRampasan();
            logger.info("::::::::::size listBarangRampasan::::::::::  " + listBarangRampasan.size());

            for (int j = 0; j < listBarangRampasan.size(); j++) {

                idBarang = getContext().getRequest().getParameter("idBarang" + i + j);
                String statusBarang = getContext().getRequest().getParameter("status" + i + j);
                System.out.println("status value [" + i + j + "]: " + statusBarang);
                String pilihBarang = getContext().getRequest().getParameter("pilihBarang" + i + j);
                System.out.println("pilihBarang [" + i + j + "]: " + pilihBarang);

                if (StringUtils.isNotBlank(pilihBarang)) {
                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setDilepasNoKP("Y");
                    enforceService.updateBarangRampasan(barang);
                } else {
                    //if didn't tick at checkbox
                    System.out.println("id barang set to T : " + idBarang);
                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setDilepasNoKP("T");
                    enforceService.updateBarangRampasan(barang);
                }

                if (StringUtils.isNotBlank(idBarang)) {
                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    KodStatusBarangRampasan kodStatus = new KodStatusBarangRampasan();
                    kodStatus.setKod(statusBarang);
                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setStatus(kodStatus);
                    enforceService.updateBarangRampasan(barang);
                }

            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("editBayaranJualan", Boolean.TRUE);
        return new JSP("penguatkuasaan/kemaskini_status_tarikh_tuntutan.jsp").addParameter("tab", "true");
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/tawaran_kompaun_muktamad_oks.jsp").addParameter("tab", "true");
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public List<KodStatusBarangRampasan> getSenaraiKodStatus() {
        return senaraiKodStatus;
    }

    public void setSenaraiKodStatus(List<KodStatusBarangRampasan> senaraiKodStatus) {
        this.senaraiKodStatus = senaraiKodStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getKodNegeriPermohonan() {
        return kodNegeriPermohonan;
    }

    public void setKodNegeriPermohonan(String kodNegeriPermohonan) {
        this.kodNegeriPermohonan = kodNegeriPermohonan;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public BarangRampasan getBarang() {
        return barang;
    }

    public void setBarang(BarangRampasan barang) {
        this.barang = barang;
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

    public String getTarikhTuntut() {
        return tarikhTuntut;
    }

    public void setTarikhTuntut(String tarikhTuntut) {
        this.tarikhTuntut = tarikhTuntut;
    }

    public String getTarikhDilepas() {
        return tarikhDilepas;
    }

    public void setTarikhDilepas(String tarikhDilepas) {
        this.tarikhDilepas = tarikhDilepas;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMessageAgihTugasan() {
        return messageAgihTugasan;
    }

    public void setMessageAgihTugasan(String messageAgihTugasan) {
        this.messageAgihTugasan = messageAgihTugasan;
    }

    public Boolean getKeputusanLH() {
        return keputusanLH;
    }

    public void setKeputusanLH(Boolean keputusanLH) {
        this.keputusanLH = keputusanLH;
    }

    public Boolean getKeputusanRM() {
        return keputusanRM;
    }

    public void setKeputusanRM(Boolean keputusanRM) {
        this.keputusanRM = keputusanRM;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    public PermohonanNota getPermohonanNota() {
        return permohonanNota;
    }

    public void setPermohonanNota(PermohonanNota permohonanNota) {
        this.permohonanNota = permohonanNota;
    }

    public boolean isStatusNotaExist() {
        return statusNotaExist;
    }

    public void setStatusNotaExist(boolean statusNotaExist) {
        this.statusNotaExist = statusNotaExist;
    }

    public boolean isStageLucutHak() {
        return stageLucutHak;
    }

    public void setStageLucutHak(boolean stageLucutHak) {
        this.stageLucutHak = stageLucutHak;
    }

    public boolean isStageRujukMahkamah() {
        return stageRujukMahkamah;
    }

    public void setStageRujukMahkamah(boolean stageRujukMahkamah) {
        this.stageRujukMahkamah = stageRujukMahkamah;
    }

    public String getNilaiJualan() {
        return nilaiJualan;
    }

    public void setNilaiJualan(String nilaiJualan) {
        this.nilaiJualan = nilaiJualan;
    }

    public PermohonanTuntutanKos getPtk() {
        return ptk;
    }

    public void setPtk(PermohonanTuntutanKos ptk) {
        this.ptk = ptk;
    }

    public PermohonanTuntutan getPt() {
        return pt;
    }

    public void setPt(PermohonanTuntutan pt) {
        this.pt = pt;
    }

    public PermohonanTuntutanButiran getPtb() {
        return ptb;
    }

    public void setPtb(PermohonanTuntutanButiran ptb) {
        this.ptb = ptb;
    }

    public List<Kompaun> getSenaraiJualan() {
        return senaraiJualan;
    }

    public void setSenaraiJualan(List<Kompaun> senaraiJualan) {
        this.senaraiJualan = senaraiJualan;
    }

    public Kompaun getKompaun() {
        return kompaun;
    }

    public void setKompaun(Kompaun kompaun) {
        this.kompaun = kompaun;
    }

    public List<PermohonanTuntutanBayar> getSenaraiPermohonanTuntutanBayar() {
        return senaraiPermohonanTuntutanBayar;
    }

    public void setSenaraiPermohonanTuntutanBayar(List<PermohonanTuntutanBayar> senaraiPermohonanTuntutanBayar) {
        this.senaraiPermohonanTuntutanBayar = senaraiPermohonanTuntutanBayar;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getAlamatPenerima1() {
        return alamatPenerima1;
    }

    public void setAlamatPenerima1(String alamatPenerima1) {
        this.alamatPenerima1 = alamatPenerima1;
    }

    public String getAlamatPenerima2() {
        return alamatPenerima2;
    }

    public void setAlamatPenerima2(String alamatPenerima2) {
        this.alamatPenerima2 = alamatPenerima2;
    }

    public String getAlamatPenerima3() {
        return alamatPenerima3;
    }

    public void setAlamatPenerima3(String alamatPenerima3) {
        this.alamatPenerima3 = alamatPenerima3;
    }

    public String getAlamatPenerima4() {
        return alamatPenerima4;
    }

    public void setAlamatPenerima4(String alamatPenerima4) {
        this.alamatPenerima4 = alamatPenerima4;
    }

    public String getNoPengenalanPenerimaLain() {
        return noPengenalanPenerimaLain;
    }

    public void setNoPengenalanPenerimaLain(String noPengenalanPenerimaLain) {
        this.noPengenalanPenerimaLain = noPengenalanPenerimaLain;
    }

    public String getNoPengenalanPenerimaBaru() {
        return noPengenalanPenerimaBaru;
    }

    public void setNoPengenalanPenerimaBaru(String noPengenalanPenerimaBaru) {
        this.noPengenalanPenerimaBaru = noPengenalanPenerimaBaru;
    }

    public KodJenisPengenalan getKodPengenalanPenerima() {
        return kodPengenalanPenerima;
    }

    public void setKodPengenalanPenerima(KodJenisPengenalan kodPengenalanPenerima) {
        this.kodPengenalanPenerima = kodPengenalanPenerima;
    }
}

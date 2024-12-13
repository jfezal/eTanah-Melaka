/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KompaunDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PenguatkuasaanBarangJualDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.Alamat;
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
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.PenguatkuasaanBarangJual;
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
import java.util.HashSet;
import java.util.Iterator;
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
@UrlBinding("/penguatkuasaan/pembeli_barang_rampasan")
public class PembelianBarangRampasanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PembelianBarangRampasanActionBean.class);
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
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    KodUOMDAO kodUomDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PenguatkuasaanBarangJualDAO penguatkuasaanBarangJualDAO;
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
    private String nilaiJualan;
    private PermohonanTuntutanKos ptk;
    private PermohonanTuntutan pt;
    private PermohonanTuntutanButiran ptb;
    private List<Kompaun> senaraiJualan;
    private Kompaun kompaun;
    private List<PermohonanTuntutanBayar> senaraiPermohonanTuntutanBayar;
    private String namaPembeli;
    private String alamatPembeli1;
    private String alamatPembeli2;
    private String alamatPembeli3;
    private String alamatPembeli4;
    private String poskodPembeli;
    private String noPengenalanPembeliLain;
    private String noPengenalanPembeliBaru;
    private KodJenisPengenalan kodPengenalanPembeli;
    private KodNegeri kodNegeriPembeli;
    private List<BarangRampasan> senaraiBarangBelumDijual = new ArrayList<BarangRampasan>();
    private String idOp;
    private String idPermohonanAsal;
    private List<String> listIdKompaunBerkaitan;
    private String idKompaun;
    private List<Kompaun> senaraiPembelian;
    private Pemohon pemohon;
    private PenguatkuasaanBarangJual penguatkuasaanBarangJual;
    private String kuantiti;
    private String kuantitiUnit;
    private List<Pemohon> senaraiPembeli;
    private List<PenguatkuasaanBarangJual> senaraiJualanBarang;
    private String statusBarang;
    private String unitBarang;
    private String recordCount;
    private String kuantitiBarang;
    private List<PenguatkuasaanBarangJual> senaraiJualanBarangPemohon;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/pembelian_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/pembelian_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution viewBayaranJualan() {
        getContext().getRequest().setAttribute("viewBayaran", Boolean.TRUE);
        return new JSP("penguatkuasaan/pembelian_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution popupTambahBarang() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_pembelian_barang.jsp").addParameter("tab", "true");
    }

    public Resolution popupEditBarang() {
        String id = getContext().getRequest().getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            pemohon = pemohonDAO.findById(Long.parseLong(id));

            if (pemohon != null) {
                namaPembeli = pemohon.getNama();
                kodPengenalanPembeli = pemohon.getJenisPengenalan();
                if (pemohon.getJenisPengenalan() != null) {
                    if (pemohon.getJenisPengenalan().getKod().equalsIgnoreCase("B")) {
                        noPengenalanPembeliBaru = pemohon.getNoPengenalan();
                    } else {
                        noPengenalanPembeliLain = pemohon.getNoPengenalan();
                    }
                }

                if (pemohon.getAlamat() != null) {
                    alamatPembeli1 = pemohon.getAlamat().getAlamat1();
                    alamatPembeli2 = pemohon.getAlamat().getAlamat2();
                    alamatPembeli3 = pemohon.getAlamat().getAlamat3();
                    alamatPembeli4 = pemohon.getAlamat().getAlamat4();
                    poskodPembeli = pemohon.getAlamat().getPoskod();
                    if (pemohon.getAlamat().getNegeri() != null) {
                        kodNegeriPembeli = pemohon.getAlamat().getNegeri();
                    }


                }

                senaraiJualanBarangPemohon = enforcementService.findBarangPemohon(pemohon.getIdPemohon());
                recordCount = String.valueOf(senaraiJualanBarangPemohon.size());
            }


        }


        logger.info("::: size senaraiJualanBarangPemohon : " + senaraiJualanBarangPemohon);
        logger.info("::: recordCount : " + recordCount);
        getContext().getRequest().setAttribute("editPembelian", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_pembelian_barang.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!findBarang"})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        } else {
            stageId = "syor_pelupusan";
        }
        logger.info("-------------stageId :: ------------------" + stageId);

        logger.info("-------------rehydrate------------------");
        kodNegeriPermohonan = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
//            if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                listOp = enforceService.findListLaporanOperasi(idPermohonan);
                idPermohonanAsal = permohonan.getIdPermohonan();
                System.out.println("listOp size : " + listOp.size());
                if (listOp.size() != 0) {
                    System.out.println("opFlag true");
                    opFlag = true;
                    System.out.println("opFlag ---- : " + opFlag);
                } else {
                    if (permohonan.getPermohonanSebelum() != null) {
                        System.out.println(":::::::;;" + permohonan.getPermohonanSebelum().getIdPermohonan());
                        idPermohonanAsal = permohonan.getPermohonanSebelum().getIdPermohonan();
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
                        idPermohonanAsal = p.getIdPermohonan();
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

            senaraiPembeli = enforcementService.findListPembeli(idPermohonan, "PML");
            logger.info("::::::: size senaraiPembeli : " + senaraiPembeli.size());

            senaraiJualanBarang = enforcementService.findPembelianBarang(idPermohonan);
            logger.info("::::::: size senaraiJualanBarang : " + senaraiJualanBarang.size());





            ArrayList senaraiTuntutKos = new ArrayList<String>();


            for (int j = 0; j < senaraiJualanBarang.size(); j++) {
                if (senaraiJualanBarang.get(j).getKos() != null) {
                    PermohonanTuntutanKos ptk = permohonanTuntutanKosDAO.findById(senaraiJualanBarang.get(j).getKos().getIdKos());
                    if (ptk != null) {
                        senaraiTuntutKos.add(ptk.getIdKos());
                    }
                }

            }
            if (!senaraiTuntutKos.isEmpty()) {
                senaraiPermohonanTuntutanBayar = enforcementService.findSenaraiPtb(senaraiTuntutKos);
                System.out.println("size senaraiPermohonanTuntutanBayar :" + senaraiPermohonanTuntutanBayar.size());
            }




        }


    }

    public Resolution simpan() {
        logger.info("-------------simpan---------------");
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());

        try {
            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));

            /* insert into table pemohon*/
            pemohon = new Pemohon();
            pemohon.setNama(namaPembeli);
            if (kodPengenalanPembeli != null) {
                pemohon.setJenisPengenalan(kodPengenalanPembeli);
                if (kodPengenalanPembeli.getKod().equalsIgnoreCase("B")) {
                    pemohon.setNoPengenalan(noPengenalanPembeliBaru);
                } else {
                    pemohon.setNoPengenalan(noPengenalanPembeliLain);
                }
            }

            Alamat al = new Alamat();
            al.setAlamat1(alamatPembeli1);
            al.setAlamat2(alamatPembeli2);
            al.setAlamat3(alamatPembeli3);
            al.setAlamat4(alamatPembeli4);
            al.setPoskod(poskodPembeli);
            if (kodNegeriPembeli != null) {
                al.setNegeri(kodNegeriPembeli);
            }
            pemohon.setAlamat(al);
            pemohon.setJenis(kodJenisPihakBerkepentinganDAO.findById("PML"));
            pemohon.setInfoAudit(info);
            pemohon.setCawangan(pengguna.getKodCawangan());
            pemohon.setPermohonan(permohonan);
            enforcementService.simpanMaklumatPihak(pemohon);

            for (int i = 0; i < rowCount; i++) {
                idBarang = getContext().getRequest().getParameter("pilihBarang" + i);
                logger.info("idBarang value : " + idBarang);

                if (idBarang != null) {

                    nilaiJualan = getContext().getRequest().getParameter("nilaiJualan" + i);
                    kuantitiBarang = getContext().getRequest().getParameter("kuantitiBarang" + i);
                    String catatan = getContext().getRequest().getParameter("catatan" + i);
                    kuantitiUnit = getContext().getRequest().getParameter("kuantitiUnit" + i);
                    logger.info("nilaiJualan value [" + i + "]: " + nilaiJualan);
                    logger.info("kuantitiBarang value [" + i + "]: " + kuantitiBarang);
                    BigDecimal amaunJualan = new BigDecimal(nilaiJualan);


                    barang = barangRampasanDAO.findById(Long.parseLong(idBarang));

                    /* insert into table PermohonanTuntutKos*/
                    ptk = new PermohonanTuntutanKos();

                    pt = new PermohonanTuntutan();
                    ptb = new PermohonanTuntutanButiran();

                    ptk.setCawangan(pengguna.getKodCawangan());
                    ptk.setPermohonan(permohonan);
                    ptk.setItem(barang.getItem());
                    KodTransaksi kt = new KodTransaksi();

                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                        kt.setKod("20088");
                    } else {
                        kt.setKod("20089");
                    }
                    ptk.setKodTransaksi(kt);
                    KodTuntut ktu = new KodTuntut();
                    ktu.setKod("K01");
                    ptk.setKodTuntut(ktu);
                    ptk.setInfoAudit(info);
                    ptk.setAmaunTuntutan(amaunJualan);
                    if (StringUtils.isNotBlank(kuantitiBarang)) {
                        ptk.setKuantiti(Integer.parseInt(kuantitiBarang));
                    }
                    enforceService.simpanBayaran(ptk);




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
                    pt.setInfoAudit(info);
                    pt.setKodTransaksiTuntut(kodTransTuntut);
                    pt.setTarikhTuntutan(new java.util.Date());
//                    pt.setTempoh(Integer.parseInt(tempohHari));
//                    pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                    enforceService.simpanPermohonanTuntutan(pt);

                    ptb.setPermohonanTuntutan(pt);
                    ptb.setPermohonanTuntutanKos(ptk);
                    ptb.setInfoAudit(info);
                    enforceService.simpanPermohonanTuntutanButiran(ptb);

                    penguatkuasaanBarangJual = new PenguatkuasaanBarangJual();
                    penguatkuasaanBarangJual.setKos(ptk);
                    penguatkuasaanBarangJual.setPermohonan(permohonan);
                    penguatkuasaanBarangJual.setRampasan(barang);
                    penguatkuasaanBarangJual.setInfoAudit(info);
                    penguatkuasaanBarangJual.setAmaun(amaunJualan);
                    penguatkuasaanBarangJual.setOperasi(barang.getOperasi());
                    penguatkuasaanBarangJual.setCatatan(catatan);
                    penguatkuasaanBarangJual.setPemohon(pemohon);
                    if (barang.getKodKategoriItemRampasan() != null) {
                        if (barang.getKodKategoriItemRampasan().getKod().equalsIgnoreCase("K")) {
                            //for kenderaan
                            penguatkuasaanBarangJual.setKuantiti(Integer.parseInt("1"));
                            penguatkuasaanBarangJual.setUnitKuantiti(kodUomDAO.findById("JBU"));
                        } else {
                            //for bukan kenderaan
                            if (StringUtils.isNotBlank(kuantitiBarang)) {
                                penguatkuasaanBarangJual.setKuantiti(Integer.parseInt(kuantitiBarang));
                            }
                            if (StringUtils.isNotBlank(kuantitiUnit)) {
                                penguatkuasaanBarangJual.setUnitKuantiti(kodUomDAO.findById(kuantitiUnit));
                            }
                        }
                    }


                    enforcementService.simpanPembelianBarang(penguatkuasaanBarangJual);

                }





            }








        } catch (Exception e) {
            e.printStackTrace();

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(PembelianBarangRampasanActionBean.class, "locate");
    }

    public Resolution editBarangBelian() {
        logger.info("::::: editBarangBelian :::::");
        String id = getContext().getRequest().getParameter("idPemohonEdit");

        InfoAudit info = new InfoAudit();
        if (StringUtils.isNotBlank(id)) {
            pemohon = pemohonDAO.findById(Long.parseLong(id));

            if (pemohon != null) {
                pemohon.setNama(namaPembeli);

                Alamat al = new Alamat();
                if (pemohon.getAlamat() != null) {
                    al = pemohon.getAlamat();
                }
                al.setAlamat1(alamatPembeli1);
                al.setAlamat2(alamatPembeli2);
                al.setAlamat3(alamatPembeli3);
                al.setAlamat4(alamatPembeli4);
                al.setPoskod(poskodPembeli);
                if (kodNegeriPembeli != null) {
                    al.setNegeri(kodNegeriPembeli);
                }
                pemohon.setAlamat(al);

                if (kodPengenalanPembeli != null) {
                    pemohon.setJenisPengenalan(kodPengenalanPembeli);
                    if (kodPengenalanPembeli.getKod().equalsIgnoreCase("B")) {
                        pemohon.setNoPengenalan(noPengenalanPembeliBaru);
                    } else {
                        pemohon.setNoPengenalan(noPengenalanPembeliLain);
                    }
                }

                info = pemohon.getInfoAudit();
                info.setTarikhKemaskini(new java.util.Date());
                info.setDiKemaskiniOleh(pengguna);

                pemohon.setInfoAudit(info);
                enforcementService.simpanMaklumatPihak(pemohon);

                senaraiJualanBarangPemohon = enforcementService.findBarangPemohon(pemohon.getIdPemohon());

                if (StringUtils.isNotBlank(recordCount)) {
                    for (int i = 1; i <= Integer.parseInt(recordCount); i++) {

                        if (senaraiJualanBarangPemohon.size() != 0 && i <= senaraiJualanBarangPemohon.size()) {
                            Long idP = senaraiJualanBarangPemohon.get(i - 1).getIdBarangJual();
                            if (idP != null) {
                                penguatkuasaanBarangJual = penguatkuasaanBarangJualDAO.findById(idP);
                                info = penguatkuasaanBarangJual.getInfoAudit();
                                info.setDiKemaskiniOleh(pengguna);
                                info.setTarikhKemaskini(new java.util.Date());

                                if (penguatkuasaanBarangJual.getKos() != null) {
                                    ptk = permohonanTuntutanKosDAO.findById(penguatkuasaanBarangJual.getKos().getIdKos());
                                    ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());
                                    if (ptb != null) {
                                        pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                                    }
                                }

                            }
                        } else {
                            penguatkuasaanBarangJual = new PenguatkuasaanBarangJual();
                            ptk = new PermohonanTuntutanKos();
                            pt = new PermohonanTuntutan();
                            ptb = new PermohonanTuntutanButiran();

                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());
                        }

                        nilaiJualan = getContext().getRequest().getParameter("nilaiJualan" + i);
                        kuantitiBarang = getContext().getRequest().getParameter("kuantitiBarang" + i);
                        String catatan = getContext().getRequest().getParameter("catatan" + i);
                        kuantitiUnit = getContext().getRequest().getParameter("kuantitiUnit" + i);
                        logger.info("nilaiJualan value [" + i + "]: " + nilaiJualan);
                        logger.info("kuantitiBarang value [" + i + "]: " + kuantitiBarang);
                        BigDecimal amaunJualan = new BigDecimal(nilaiJualan);

                        ptk.setCawangan(pengguna.getKodCawangan());
                        ptk.setPermohonan(permohonan);
                        ptk.setItem(barang.getItem());
                        KodTransaksi kt = new KodTransaksi();

                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                            kt.setKod("20088");
                        } else {
                            kt.setKod("20089");
                        }
                        ptk.setKodTransaksi(kt);
                        KodTuntut ktu = new KodTuntut();
                        ktu.setKod("K01");
                        ptk.setKodTuntut(ktu);
                        ptk.setInfoAudit(info);
                        ptk.setAmaunTuntutan(amaunJualan);
                        enforceService.simpanBayaran(ptk);


                        penguatkuasaanBarangJual.setPermohonan(permohonan);
                        penguatkuasaanBarangJual.setRampasan(barang);
                        penguatkuasaanBarangJual.setInfoAudit(info);
                        penguatkuasaanBarangJual.setAmaun(amaunJualan);
                        penguatkuasaanBarangJual.setOperasi(barang.getOperasi());
                        penguatkuasaanBarangJual.setCatatan(catatan);
                        penguatkuasaanBarangJual.setPemohon(pemohon);
                        penguatkuasaanBarangJual.setKos(ptk);
                        if (StringUtils.isNotBlank(kuantitiBarang)) {
                            penguatkuasaanBarangJual.setKuantiti(Integer.parseInt(kuantitiBarang));
                        }
                        if (StringUtils.isNotBlank(kuantitiUnit)) {
                            penguatkuasaanBarangJual.setUnitKuantiti(kodUomDAO.findById(kuantitiUnit));
                        }
                        enforcementService.simpanPembelianBarang(penguatkuasaanBarangJual);

                        KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                        kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");
                        pt.setCawangan(pengguna.getKodCawangan());
                        pt.setPermohonan(permohonan);
                        pt.setInfoAudit(info);
                        pt.setKodTransaksiTuntut(kodTransTuntut);
                        pt.setTarikhTuntutan(new java.util.Date());
                        enforceService.simpanPermohonanTuntutan(pt);

                        ptb.setPermohonanTuntutan(pt);
                        ptb.setPermohonanTuntutanKos(ptk);
                        ptb.setInfoAudit(info);
                        enforceService.simpanPermohonanTuntutanButiran(ptb);

                    }
                }
            }
        }


        getContext().getRequest().setAttribute("editPembelian", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(PembelianBarangRampasanActionBean.class, "locate");
    }

    public Resolution refreshPage() {
        rehydrate();
//        return new JSP("penguatkuasaan/maklumat_barang_tahanan.jsp").addParameter("tab", "true");
        return new RedirectResolution(PembelianBarangRampasanActionBean.class, "locate");
    }

    public Resolution findBarang() {
        logger.info(":::::findPengguna");
        String id = getContext().getRequest().getParameter("id");
        int i = 0;
        int j = 0;
        int total = 0;
        if (StringUtils.isNotBlank(id)) {
            barang = barangRampasanDAO.findById(Long.parseLong(id));
            j = barang.getKuantiti();
            if (barang != null) {
                unitBarang = barang.getKuantitiUnit();
                if (barang.getKodKategoriItemRampasan() != null) {
                    if (barang.getKodKategoriItemRampasan().getKod().equalsIgnoreCase("K")) {
                        unitBarang = "1";
                    }
                }
                List<PenguatkuasaanBarangJual> list = new ArrayList<PenguatkuasaanBarangJual>();
                list = enforcementService.findJumlahBarang(barang.getIdBarang());
                for (PenguatkuasaanBarangJual p : list) {
                    i += p.getKuantiti();
                }
            }
            total = j - i;
            statusBarang = String.valueOf(total);
            logger.info("::: jumlah statusBarang :" + statusBarang);
            logger.info("::: jumlah barang (kkuasa_brg_jual) :" + i);
            logger.info("::: jumlah barang (op_brg_rampas) :" + j);
            logger.info("::: unitBarang :" + unitBarang);

        }



        return new JSP("penguatkuasaan/popup_pembelian_barang.jsp").addParameter("popup", "true");
    }

    public Resolution deleteBarang() {
        String id = getContext().getRequest().getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            penguatkuasaanBarangJual = penguatkuasaanBarangJualDAO.findById(Long.parseLong(id));
            if (penguatkuasaanBarangJual != null) {
                if (penguatkuasaanBarangJual.getKos() != null) {
                    ptk = permohonanTuntutanKosDAO.findById(penguatkuasaanBarangJual.getKos().getIdKos());
                    if (ptk != null) {
                        ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());
                        if (ptb != null) {
                            pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                            if (pt != null) {
                                enforcementService.deletePermohonanTuntutanButiran(ptb);
                                enforcementService.deletePermohonanTuntutan(pt);
                            }
                        }
                    }
                }


                enforcementService.deletePembeliBarangJualan(penguatkuasaanBarangJual);
                enforceService.deleteBayaran(ptk);
            }
        }

        return new JSP("penguatkuasaan/popup_pembelian_barang.jsp").addParameter("popup", "true");
    }

    public Resolution deletePemohon() {
        String id = getContext().getRequest().getParameter("id"); //id pemohon
        if (StringUtils.isNotBlank(id)) {
            List<PenguatkuasaanBarangJual> listPemohonPembeli = enforcementService.findBarangPemohon(Long.parseLong(id));

            for (int j = 0; j < listPemohonPembeli.size(); j++) {
                if (listPemohonPembeli.get(j).getKos() != null) {
                    ptk = permohonanTuntutanKosDAO.findById(listPemohonPembeli.get(j).getKos().getIdKos());
                    if (ptk != null) {
                        ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());
                        if (ptb != null) {
                            pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                            if (pt != null) {
                                enforcementService.deletePermohonanTuntutanButiran(ptb);
                                enforcementService.deletePermohonanTuntutan(pt);
                            }
                        }
                    }

//                    if (listPemohonPembeli.get(j).getRampasan() != null) {
//                        BarangRampasan b = barangRampasanDAO.findById(listPemohonPembeli.get(j).getRampasan().getIdBarang());
//                        if (b != null) {
//                            b.setStatusJual(null);
//                            b.setAmaunJual(null);;
//                            b.setKuantiti(b.getKuantiti() + listPemohonPembeli.get(j).getKuantiti());
//                            enforceService.simpanBarangRampasan(b);
//                        }
//
//                    }

                    Pemohon p = listPemohonPembeli.get(j).getPemohon();
                    if (p != null) {
                        enforcementService.deletePembeliBarangJualan(listPemohonPembeli.get(j));
                        enforceService.deleteBayaran(ptk);
                    }
                }

            }


        }
        return new JSP("penguatkuasaan/popup_pembelian_barang.jsp").addParameter("popup", "true");
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

    public String getAlamatPembeli1() {
        return alamatPembeli1;
    }

    public void setAlamatPembeli1(String alamatPembeli1) {
        this.alamatPembeli1 = alamatPembeli1;
    }

    public String getAlamatPembeli2() {
        return alamatPembeli2;
    }

    public void setAlamatPembeli2(String alamatPembeli2) {
        this.alamatPembeli2 = alamatPembeli2;
    }

    public String getAlamatPembeli3() {
        return alamatPembeli3;
    }

    public void setAlamatPembeli3(String alamatPembeli3) {
        this.alamatPembeli3 = alamatPembeli3;
    }

    public String getAlamatPembeli4() {
        return alamatPembeli4;
    }

    public void setAlamatPembeli4(String alamatPembeli4) {
        this.alamatPembeli4 = alamatPembeli4;
    }

    public String getPoskodPembeli() {
        return poskodPembeli;
    }

    public void setPoskodPembeli(String poskodPembeli) {
        this.poskodPembeli = poskodPembeli;
    }

    public KodNegeri getKodNegeriPembeli() {
        return kodNegeriPembeli;
    }

    public void setKodNegeriPembeli(KodNegeri kodNegeriPembeli) {
        this.kodNegeriPembeli = kodNegeriPembeli;
    }

    public List<BarangRampasan> getSenaraiBarangBelumDijual() {
        return senaraiBarangBelumDijual;
    }

    public void setSenaraiBarangBelumDijual(List<BarangRampasan> senaraiBarangBelumDijual) {
        this.senaraiBarangBelumDijual = senaraiBarangBelumDijual;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNoPengenalanPembeliLain() {
        return noPengenalanPembeliLain;
    }

    public void setNoPengenalanPembeliLain(String noPengenalanPembeliLain) {
        this.noPengenalanPembeliLain = noPengenalanPembeliLain;
    }

    public String getNoPengenalanPembeliBaru() {
        return noPengenalanPembeliBaru;
    }

    public void setNoPengenalanPembeliBaru(String noPengenalanPembeliBaru) {
        this.noPengenalanPembeliBaru = noPengenalanPembeliBaru;
    }

    public KodJenisPengenalan getKodPengenalanPembeli() {
        return kodPengenalanPembeli;
    }

    public void setKodPengenalanPembeli(KodJenisPengenalan kodPengenalanPembeli) {
        this.kodPengenalanPembeli = kodPengenalanPembeli;
    }

    public String getIdOp() {
        return idOp;
    }

    public void setIdOp(String idOp) {
        this.idOp = idOp;
    }

    public String getIdPermohonanAsal() {
        return idPermohonanAsal;
    }

    public void setIdPermohonanAsal(String idPermohonanAsal) {
        this.idPermohonanAsal = idPermohonanAsal;
    }

    public List<String> getListIdKompaunBerkaitan() {
        return listIdKompaunBerkaitan;
    }

    public void setListIdKompaunBerkaitan(List<String> listIdKompaunBerkaitan) {
        this.listIdKompaunBerkaitan = listIdKompaunBerkaitan;
    }

    public String getIdKompaun() {
        return idKompaun;
    }

    public void setIdKompaun(String idKompaun) {
        this.idKompaun = idKompaun;
    }

    public List<Kompaun> getSenaraiPembelian() {
        return senaraiPembelian;
    }

    public void setSenaraiPembelian(List<Kompaun> senaraiPembelian) {
        this.senaraiPembelian = senaraiPembelian;
    }

    public PenguatkuasaanBarangJual getPenguatkuasaanBarangJual() {
        return penguatkuasaanBarangJual;
    }

    public void setPenguatkuasaanBarangJual(PenguatkuasaanBarangJual penguatkuasaanBarangJual) {
        this.penguatkuasaanBarangJual = penguatkuasaanBarangJual;
    }

    public String getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(String kuantiti) {
        this.kuantiti = kuantiti;
    }

    public String getKuantitiUnit() {
        return kuantitiUnit;
    }

    public void setKuantitiUnit(String kuantitiUnit) {
        this.kuantitiUnit = kuantitiUnit;
    }

    public List<Pemohon> getSenaraiPembeli() {
        return senaraiPembeli;
    }

    public void setSenaraiPembeli(List<Pemohon> senaraiPembeli) {
        this.senaraiPembeli = senaraiPembeli;
    }

    public List<PenguatkuasaanBarangJual> getSenaraiJualanBarang() {
        return senaraiJualanBarang;
    }

    public void setSenaraiJualanBarang(List<PenguatkuasaanBarangJual> senaraiJualanBarang) {
        this.senaraiJualanBarang = senaraiJualanBarang;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getStatusBarang() {
        return statusBarang;
    }

    public void setStatusBarang(String statusBarang) {
        this.statusBarang = statusBarang;
    }

    public String getUnitBarang() {
        return unitBarang;
    }

    public void setUnitBarang(String unitBarang) {
        this.unitBarang = unitBarang;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public String getKuantitiBarang() {
        return kuantitiBarang;
    }

    public void setKuantitiBarang(String kuantitiBarang) {
        this.kuantitiBarang = kuantitiBarang;
    }

    public List<PenguatkuasaanBarangJual> getSenaraiJualanBarangPemohon() {
        return senaraiJualanBarangPemohon;
    }

    public void setSenaraiJualanBarangPemohon(List<PenguatkuasaanBarangJual> senaraiJualanBarangPemohon) {
        this.senaraiJualanBarangPemohon = senaraiJualanBarangPemohon;
    }
}

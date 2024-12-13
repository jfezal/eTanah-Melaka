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
@UrlBinding("/penguatkuasaan/maklumat_pembeli_barang_rampasan")
public class MaklumatPembeliBarangRampasanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatPembeliBarangRampasanActionBean.class);
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
    private ArrayList<String> senaraiBarangJualan = new ArrayList<String>();
    private List<String> listIdKompaunBerkaitan;
    private String idKompaun;
    private List<Kompaun> senaraiPembelian;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pembeli_barang.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pembeli_barang.jsp").addParameter("tab", "true");
    }

    public Resolution viewBayaranJualan() {
        getContext().getRequest().setAttribute("viewBayaran", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pembeli_barang.jsp").addParameter("tab", "true");
    }

    public Resolution popupTambahBarang() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_barang.jsp").addParameter("tab", "true");
    }

    public Resolution popupEditBarang() {
        idKompaun = getContext().getRequest().getParameter("idKompaun");
        if (StringUtils.isNotBlank(idKompaun)) {
            String[] idsArray = idKompaun.trim().split("\\s*,\\s*");
            logger.info("length : " + idsArray.length);

            if (idsArray.length > 0) {
                kompaun = kompaunDAO.findById(Long.parseLong(idsArray[1]));
                if (kompaun != null) {
                    namaPembeli = kompaun.getIsuKepada();
                    kodPengenalanPembeli = kompaun.getJenisPengenalan();
                    if (kompaun.getJenisPengenalan() != null) {
                        if (kompaun.getJenisPengenalan().getKod().equalsIgnoreCase("B")) {
                            noPengenalanPembeliBaru = kompaun.getNoPengenalan();
                        } else {
                            noPengenalanPembeliLain = kompaun.getNoPengenalan();
                        }
                    }

                    if (kompaun.getAlamat() != null) {
                        alamatPembeli1 = kompaun.getAlamat().getAlamat1();
                        alamatPembeli2 = kompaun.getAlamat().getAlamat2();
                        alamatPembeli3 = kompaun.getAlamat().getAlamat3();
                        alamatPembeli4 = kompaun.getAlamat().getAlamat4();
                        poskodPembeli = kompaun.getAlamat().getPoskod();
                        if (kompaun.getAlamat().getNegeri() != null) {
                            kodNegeriPembeli = kompaun.getAlamat().getNegeri();
                        }


                    }
                }
                String id = "";
                List<Long> ids = new ArrayList<Long>();
                listIdKompaunBerkaitan = new ArrayList<String>();
                for (int i = 1; i < idsArray.length; i++) {
                    System.out.println("" + idsArray[i]);
                    listIdKompaunBerkaitan.add(idsArray[i]);
                    ids.add(Long.parseLong(idsArray[i]));
                    if (id.equalsIgnoreCase("")) {
                        id = idsArray[i];
                    } else {
                        id += "," + idsArray[i];
                    }

                }

                logger.info(":::::::id ::::" + id);
                idKompaun = id;

                senaraiPembelian = new ArrayList<Kompaun>();
                if (ids.size() != 0) {
                    senaraiPembelian = enforcementService.getSenaraiPembelian(ids);
                }
            }
        }


        logger.info("size listIdKompaunBerkaitan : " + listIdKompaunBerkaitan);
        logger.info("size senaraiPembelian : " + senaraiPembelian);
        getContext().getRequest().setAttribute("editPembelian", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_barang.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
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
            if ("04".equals(conf.getProperty("kodNegeri"))) {
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

                senaraiJualan = enforceService.findKompaunByKodStatusTerima(idPermohonan, "BJ");
                logger.info("::::::: size senarai jualan : " + senaraiJualan.size());


                ArrayList senaraiTuntutKos = new ArrayList<String>();


                for (int j = 0; j < senaraiJualan.size(); j++) {
                    if (senaraiJualan.get(j).getPermohonanTuntutanKos() != null) {
                        PermohonanTuntutanKos ptk = permohonanTuntutanKosDAO.findById(senaraiJualan.get(j).getPermohonanTuntutanKos().getIdKos());
                        if (ptk != null) {
                            senaraiTuntutKos.add(ptk.getIdKos());
                        }
                    }

                }
                if (!senaraiTuntutKos.isEmpty()) {
                    senaraiPermohonanTuntutanBayar = enforcementService.findSenaraiPtb(senaraiTuntutKos);
                    System.out.println("size senaraiPermohonanTuntutanBayar :" + senaraiPermohonanTuntutanBayar.size());
                }



//                String senaraiItemJualan[], nama, no;
//
//                for (int i = 0; i < senaraiJualan.size(); i++) {
//                    Kompaun k = senaraiJualan.get(i);
//
//                    if (senaraiBarangJualan.size() == 0) {
//                        senaraiBarangJualan.add(k.getIsuKepada() + "," + k.getNoPengenalan() + "," + k.getAmaun() + "," + barangRampasanDAO.findById(Long.parseLong(k.getRujukan1())).getItem());
//                    } else {
//                        for (String a : senaraiBarangJualan) {
//                            senaraiItemJualan = a.split(",");
//                            logger.info("length senaraiItemJualan : " + senaraiItemJualan.length);
//                            if (senaraiItemJualan.length > 1) {
//                                nama = senaraiItemJualan[0];
//                                no = senaraiItemJualan[1];
//                                logger.info("nama  : " + nama + " no pengenalan : " + no);
//
//                                if (k.getIsuKepada().equalsIgnoreCase(nama) && k.getNoPengenalan().equalsIgnoreCase(no)) {
//                                } else {
//                                    senaraiBarangJualan.add(k.getIsuKepada() + "," + k.getNoPengenalan() + "," + k.getAmaun() + "," + barangRampasanDAO.findById(Long.parseLong(k.getRujukan1())).getItem());
//                                }
//                            }
//                        }
//
//
//                    }
//                }


                HashSet hsKod = new HashSet();


                for (int i = 0; i < senaraiJualan.size(); i++) {
                    Kompaun k = senaraiJualan.get(i);

                    hsKod.add(k.getIsuKepada() + "," + k.getNoPengenalan());
                }

                Iterator it = hsKod.iterator();
                while (it.hasNext()) {
                    String value = (String) it.next();
                    senaraiBarangJualan.add(value);
                }

                logger.info("senaraiBarangJualan2  : " + senaraiBarangJualan.size());

            }




        }


    }

    public Resolution simpan() {
        logger.info("-------------simpan---------------");
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());

        try {
            for (int i = 0; i < listOp.size(); i++) {
            }


            for (int i = 0; i < listOp.size(); i++) {
                List<BarangRampasan> listBarangRampasan = new ArrayList<BarangRampasan>();
                listBarangRampasan = listOp.get(i).getSenaraiBarangRampasan();
                logger.info("::::::::::size listBarangRampasan::::::::::  " + listBarangRampasan.size());

                for (int j = 0; j < listBarangRampasan.size(); j++) {
                    idBarang = getContext().getRequest().getParameter("pilihBarang" + i + j);
                    logger.info("idBarang value : " + idBarang);

                    if (idBarang != null) {

                        nilaiJualan = getContext().getRequest().getParameter("nilaiJualan" + i + j);
                        logger.info("nilaiJualan value [" + i + j + "]: " + nilaiJualan);
                        BigDecimal amaunJualan = new BigDecimal(nilaiJualan);


                        barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                        info = barang.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        barang.setInfoAudit(info);
                        barang.setStatusJual("Y");
                        barang.setAmaunJual(amaunJualan);

                        enforceService.updateBarangRampasan(barang);


                        /* insert into table PermohonanTuntutKos*/
                        ptk = new PermohonanTuntutanKos();

                        /* insert into table Kompaun*/
                        kompaun = new Kompaun();

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
                        enforceService.simpanBayaran(ptk);

                        kompaun.setRujukan1(idBarang);
                        kompaun.setPermohonan(permohonan);
                        kompaun.setInfoAudit(info);
                        kompaun.setCawangan(pengguna.getKodCawangan());
                        kompaun.setAmaun(amaunJualan);
                        kompaun.setStatusTerima(kodStatusTerimaDAO.findById("BJ")); //BJ = Bayaran Jualan
//                    kompaun.setTempohHari(Integer.parseInt(tempohHari));
                        kompaun.setPermohonanTuntutanKos(ptk);
                        kompaun.setNoRujukan("Tiada Data");
                        kompaun.setIsuKepada(namaPembeli);

                        Alamat al = new Alamat();
                        al.setAlamat1(alamatPembeli1);
                        al.setAlamat2(alamatPembeli2);
                        al.setAlamat3(alamatPembeli3);
                        al.setAlamat4(alamatPembeli4);
                        al.setPoskod(poskodPembeli);
                        if (kodNegeriPembeli != null) {
                            al.setNegeri(kodNegeriPembeli);
                        }
                        kompaun.setAlamat(al);

                        if (kodPengenalanPembeli != null) {
                            kompaun.setJenisPengenalan(kodPengenalanPembeli);
                            if (kodPengenalanPembeli.getKod().equalsIgnoreCase("B")) {
                                kompaun.setNoPengenalan(noPengenalanPembeliBaru);
                            } else {
                                kompaun.setNoPengenalan(noPengenalanPembeliLain);
                            }
                        }

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

                    }



                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(MaklumatPembeliBarangRampasanActionBean.class, "locate");
    }

    public Resolution editBarangBelian() {
        idKompaun = getContext().getRequest().getParameter("idKompaun");
        InfoAudit info = new InfoAudit();
        if (StringUtils.isNotBlank(idKompaun)) {
            String[] idsArray = idKompaun.trim().split("\\s*,\\s*");
            logger.info("length : " + idsArray.length);

            for (int i = 0; i < idsArray.length; i++) {
                nilaiJualan = getContext().getRequest().getParameter("nilaiJualan" + i);
                logger.info("nilaiJualan value [" + i + "]: " + nilaiJualan);
                logger.info("idKompaun[" + i + "]: " + idsArray[i]);
                BigDecimal amaunJualan = BigDecimal.ZERO;
                if (StringUtils.isNotBlank(nilaiJualan)) {
                    amaunJualan = new BigDecimal(nilaiJualan);
                }

                kompaun = kompaunDAO.findById(Long.parseLong(idsArray[i]));
                if (kompaun != null) {
                    kompaun.setIsuKepada(namaPembeli);
                    kompaun.setAmaun(amaunJualan);

                    Alamat al = new Alamat();
                    if (kompaun.getAlamat() != null) {
                        al = kompaun.getAlamat();
                    }
                    al.setAlamat1(alamatPembeli1);
                    al.setAlamat2(alamatPembeli2);
                    al.setAlamat3(alamatPembeli3);
                    al.setAlamat4(alamatPembeli4);
                    al.setPoskod(poskodPembeli);
                    if (kodNegeriPembeli != null) {
                        al.setNegeri(kodNegeriPembeli);
                    }
                    kompaun.setAlamat(al);

                    if (kodPengenalanPembeli != null) {
                        kompaun.setJenisPengenalan(kodPengenalanPembeli);
                        if (kodPengenalanPembeli.getKod().equalsIgnoreCase("B")) {
                            kompaun.setNoPengenalan(noPengenalanPembeliBaru);
                        } else {
                            kompaun.setNoPengenalan(noPengenalanPembeliLain);
                        }
                    }

                    info = kompaun.getInfoAudit();
                    info.setTarikhKemaskini(new java.util.Date());
                    info.setDiKemaskiniOleh(pengguna);

                    kompaun.setInfoAudit(info);
                    enforcementService.simpanKompaun(kompaun);

                    //update amoun at op_brg_rampas
                    if (StringUtils.isNotBlank(kompaun.getRujukan1())) {
                        barang = barangRampasanDAO.findById(Long.parseLong(kompaun.getRujukan1()));
                        if (barang != null) {
                            info = barang.getInfoAudit();
                            info.setTarikhKemaskini(new java.util.Date());
                            info.setDiKemaskiniOleh(pengguna);

                            barang.setAmaunJual(amaunJualan);
                            barang.setInfoAudit(info);
                            enforceService.updateBarangRampasan(barang);
                        }
                    }

                    if (kompaun.getPermohonanTuntutanKos() != null) {
                        ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());
                        if (ptk != null) {
                            info = ptk.getInfoAudit();
                            info.setTarikhKemaskini(new java.util.Date());
                            info.setDiKemaskiniOleh(pengguna);

                            ptk.setAmaunTuntutan(amaunJualan);
                            ptk.setInfoAudit(info);
                            enforceService.simpanBayaran(ptk);
                        }
                    }
                }
            }
        }
        getContext().getRequest().setAttribute("editPembelian", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(MaklumatPembeliBarangRampasanActionBean.class, "locate");
    }

    public Resolution refreshPage() {
        rehydrate();
//        return new JSP("penguatkuasaan/maklumat_barang_tahanan.jsp").addParameter("tab", "true");
        return new RedirectResolution(MaklumatPembeliBarangRampasanActionBean.class, "locate");
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

    public ArrayList<String> getSenaraiBarangJualan() {
        return senaraiBarangJualan;
    }

    public void setSenaraiBarangJualan(ArrayList<String> senaraiBarangJualan) {
        this.senaraiBarangJualan = senaraiBarangJualan;
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
}

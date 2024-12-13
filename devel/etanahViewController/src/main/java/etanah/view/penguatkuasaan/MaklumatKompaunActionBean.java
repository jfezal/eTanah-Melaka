/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.PermohonanDAO;
import etanah.model.KodTransaksiTuntut;
import etanah.model.Permohonan;
import etanah.dao.KompaunDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.InfoAudit;
import etanah.model.Kompaun;
import etanah.service.BPelService;
import etanah.service.EnforceService;

import etanah.model.Pengguna;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanSaksiDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.BarangRampasan;
import etanah.model.KodTransaksi;
import etanah.model.KodTuntut;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.PermohonanSaksi;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_kompaun")
public class MaklumatKompaunActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatKompaunActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    PermohonanTuntutanButiranDAO permohonanTuntutanButiranDAO;
    @Inject
    PermohonanSaksiDAO permohonanSaksiDAO;
    private Pengguna pengguna;
    private String idPermohonan;
    private Permohonan permohonan;
    private List<BarangRampasan> senaraiBarangRampasan;
    private List<BarangRampasan> listBarangRampasan;
    private List<BarangRampasan> listItemBarang;
    private List<Kompaun> senaraiKompaun;
    private List<Kompaun> senaraiBayaranPelupusan;
    private Kompaun kompaun;
    private String tempohKompaun;
    private String pilih;
    private String rowCount;
    private String recordCount;
    private List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak;
    private PermohonanTuntutanBayar mohonTuntutBayar;
    private String noRujukan;
    private AduanOrangKenaSyak oks;
    private String orangKenaSyak;
    private String jumlahKompaun;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private String stageId;
    private String taskId;
    private PermohonanTuntutan pt;
    private PermohonanTuntutanButiran ptb;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Boolean opFlag = false;
    private List<OperasiPenguatkuasaan> listOp;
    private String idOp;
    private List<BarangRampasan> listBarangForKompaun;
    private List<BarangRampasan> listBarangInKompaun;
    private List<PermohonanSaksi> senaraiPermohonanSaksi;
    private PermohonanSaksi saksi;
    private String idSaksi;
    private String statusDakwaanKompaun;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Long idOpBasedOnIdIP;
    private Boolean idIP = false;
    private PermohonanTuntutanKos ptk;
    private List<Kompaun> senaraiKompaunIP;
    private List<AduanOrangKenaSyak> senaraiOKSForKompaun;
    private List<AduanOrangKenaSyak> senaraiOKSAlreadyKompaun;

    @DefaultHandler
    public Resolution showForm() {
        //this is syor kompaun for NS (with barang rampasan) //added 21/10/2011 by latifah
        //used for sek 425 Melaka
        if (("04".equals(conf.getProperty("kodNegeri")))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                if (stageId.equalsIgnoreCase("maklum_keputusan_kompaun")) { // for muktamad kompaun
                    logger.info("------------showForm1 : muktamadKompaun for Melaka--------------");
//                    getContext().getRequest().setAttribute("muktamadKompaunMLK", Boolean.TRUE);
                } else if (stageId.equalsIgnoreCase("terima_arahan_pelupusan2")) { //for muktamad pelupusan
                    logger.info("------------showForm1 : bayaranPelupusan for Melaka--------------");
                    getContext().getRequest().setAttribute("bayaranPelupusan", Boolean.TRUE);
                }
            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
            }

        } else {
            logger.info("------------showForm1 : syorKompaun for NS--------------");
            getContext().getRequest().setAttribute("syorKompaunNS", Boolean.TRUE);

        }
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        logger.info("------------showForm2 : muktamadKompaun --------------");
        return new JSP("penguatkuasaan/tawaran_kompaun_muktamad.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        logger.info("------------showForm3 : view syor kompaun --------------");
        getContext().getRequest().setAttribute("syorKompaun", Boolean.TRUE);
        return new JSP("penguatkuasaan/view_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        logger.info("------------showForm4 : view muktamad kompaun --------------");
        getContext().getRequest().setAttribute("muktamadKompaun", Boolean.TRUE);
        return new JSP("penguatkuasaan/view_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        //this is muktamad kompaun for Melaka (with barang rampasan) -- Melaka don't have syor kompaun //added 21/10/2011 by latifah
        logger.info("------------showForm5 : muktamadKompaun for Melaka--------------");
        getContext().getRequest().setAttribute("muktamadKompaunMLK", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        //this is view muktamad kompaun for Melaka (with barang rampasan) -- Melaka don't have syor kompaun //added 21/10/2011 by latifah
        logger.info("------------showForm6 : view muktamadKompaun for Melaka--------------");
        getContext().getRequest().setAttribute("muktamadKompaunMLK", Boolean.TRUE);
        return new JSP("penguatkuasaan/view_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        //this is for add bayaran pelupusan for Melaka
        logger.info("------------showForm7 : add bayaran pelupusan for Melaka--------------");
        getContext().getRequest().setAttribute("bayaranPelupusan", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm8() {
        //this is for view bayaran pelupusan for Melaka
        logger.info("------------showForm8 : view bayaran pelupusan for Melaka--------------");
        getContext().getRequest().setAttribute("bayaranPelupusan", Boolean.TRUE);
        return new JSP("penguatkuasaan/view_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm9() {
        //this is for view maklumat kompaun for Melaka (multiple operasi)
        logger.info("------------showForm9 : view kompaun for Melaka--------------");
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        return new JSP("penguatkuasaan/view_kompaun.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        pilih = getContext().getRequest().getParameter("pilih");
        tempohKompaun = getContext().getRequest().getParameter("tempohKompaun");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            senaraiPermohonanSaksi = new ArrayList<PermohonanSaksi>();
            senaraiKompaunIP = new ArrayList<Kompaun>();
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    listOp = enforceService.findListLaporanOperasi(permohonan.getIdPermohonan());
                    logger.info("listOp size : " + listOp.size());

                    senaraiBarangRampasan = enforceService.findBarangKompaun(permohonan.getIdPermohonan());
                    logger.info("size senaraiBarangRampasan for multiple op: " + senaraiBarangRampasan.size());
                    if (listOp.size() != 0) {
                        opFlag = true;
                    } else {
                        if (permohonan.getPermohonanSebelum() != null) {
                            logger.info("find list listOp by id permohonan sebelum");
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            opFlag = true;
                            logger.info("listOp size by id permohonan sebelum: " + listOp.size());

                            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(permohonan.getIdPermohonan());
                            logger.info("size senaraiOksIp : " + senaraiOksIp.size());

                            if (!senaraiOksIp.isEmpty()) {
                                idIP = true;
                                idOpBasedOnIdIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                                logger.info("id operasi : " + idOpBasedOnIdIP);
                                logger.info("status idIP : " + idIP);

                                if (StringUtils.isNotBlank(idOpBasedOnIdIP.toString())) {
                                    senaraiPermohonanSaksi = enforceService.findByIDSaksi(permohonan.getIdPermohonan(), idOpBasedOnIdIP, "L");
                                }
                                logger.info("------------size senaraiSaksiLuar-------------- : " + senaraiPermohonanSaksi.size());

                                senaraiKompaunIP = enforcementService.findKompaunIP(permohonan.getIdPermohonan());
                                logger.info("------------size senaraiKompaunIP-------------- : " + senaraiKompaunIP.size());

                                listOp = enforcementService.findListLaporanOperasi(idOpBasedOnIdIP);
                            }

//                            senaraiBarangRampasan = enforceService.findBarangKompaun(idOpBasedOnIdIP);
                            senaraiBarangRampasan = enforceService.findBarangKompaunIP(idOpBasedOnIdIP, permohonan.getIdPermohonan());
                            senaraiOKSForKompaun = enforcementService.getListAduanOrangkenaSyak(permohonan.getIdPermohonan());
                            senaraiOKSAlreadyKompaun = enforcementService.getListOKSKompaun(permohonan.getIdPermohonan());
                            logger.info("------------size senaraiBarangRampasan-------------- : " + senaraiBarangRampasan.size());
                            logger.info("------------size senaraiOKSForKompaun-------------- : " + senaraiOKSForKompaun.size());
                            logger.info("------------size senaraiOKSAlreadyKompaun-------------- : " + senaraiOKSAlreadyKompaun.size());


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
                senaraiBarangRampasan = enforceService.findBarangKompaun(operasiPenguatkuasaan.getIdOperasi());
                System.out.println("size senaraiBarangRampasan (amaunKompaunSyor is null): " + senaraiBarangRampasan.size());
                listBarangRampasan = enforceService.findBarangRampasan(operasiPenguatkuasaan.getIdOperasi());
                System.out.println("size listBarangRampasan (amaunKompaunSyor is not null): " + listBarangRampasan.size());
                rowCount = String.valueOf(senaraiBarangRampasan.size());
                recordCount = String.valueOf(listBarangRampasan.size());
                senaraiAduanOrangKenaSyak = enforceService.findByID(idPermohonan);


            }

            senaraiKompaun = enforcementService.findKompaunByID(idPermohonan);
            logger.info("------------size senaraiKompaun-------------- : " + senaraiKompaun.size());

            senaraiBayaranPelupusan = enforceService.findKompaunByKodStatusTerima(idPermohonan, "BP");
            logger.info("------------size senaraiBayaranPelupusan-------------- : " + senaraiBayaranPelupusan.size());
        }

        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            logger.info("-------------stageId----- :" + stageId);
        }
    }

    public Resolution simpanSyorKompaun() {
        logger.info("------------simpanSyorKompaun--------------" + senaraiBarangRampasan.size());
        kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(orangKenaSyak));
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            if (kompaun == null) {
                System.out.println("new ptk");

                /* insert into table PermohonanTuntutKos*/
                PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
                ptk.setCawangan(pengguna.getKodCawangan());
                ptk.setPermohonan(permohonan);
                ptk.setItem("BAYARAN KOMPAUN");
                ptk.setAmaunTuntutan(BigDecimal.ZERO);
                KodTransaksi kt = new KodTransaksi();
                kt.setKod("20027");
                ptk.setKodTransaksi(kt);
                KodTuntut ktu = new KodTuntut();
                ktu.setKod("K01");
                ptk.setKodTuntut(ktu);
                InfoAudit ip = new InfoAudit();
                ip.setDimasukOleh(pengguna);
                ip.setTarikhMasuk(new Date());
                ptk.setInfoAudit(ip);
                permohonanTuntutanKosDAO.save(ptk);

                System.out.println("new kompaun");

                /* insert into table Kompaun*/
                kompaun = new Kompaun();
                kompaun.setPermohonan(permohonan);
                kompaun.setCawangan(pengguna.getKodCawangan());
                kompaun.setAmaun(BigDecimal.ZERO);
                kompaun.setNoRujukan("SK");
                kompaun.setOrangKenaSyak(oks);
                kompaun.setIsuKepada(oks.getNama());
                kompaun.setNoPengenalan(oks.getNoPengenalan());
                kompaun.setTempohHari(Integer.parseInt(tempohKompaun));
                kompaun.setPermohonanTuntutanKos(ptk);

                InfoAudit ia = kompaun.getInfoAudit();
                if (ia == null) {
                    ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new Date());
                    kompaun.setInfoAudit(ia);
                } else {
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());
                }
                kompaunDAO.save(kompaun);

                //update column no rujukan by set no rujukan = id kompaun

                kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
                kompaunDAO.saveOrUpdate(kompaun);

                Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
                Calendar cal = Calendar.getInstance();
                cal.setTime(c1);
                cal.add(Calendar.DATE, kompaun.getTempohHari());

                String tarikhAkhirBayar = sdf.format(cal.getTime());
                System.out.println("tarikh akhir bayar : " + tarikhAkhirBayar);

                /* insert into table PermohonanTuntutan */
                KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");

                pt = new PermohonanTuntutan();
                pt.setCawangan(pengguna.getKodCawangan());
                pt.setPermohonan(permohonan);
                pt.setInfoAudit(ip);
                pt.setKodTransaksiTuntut(kodTransTuntut);
                pt.setTarikhTuntutan(new java.util.Date());
                pt.setTempoh(kompaun.getTempohHari());
                pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                enforceService.simpanPermohonanTuntutan(pt);

                /* insert into table PermohonanTuntutanButiran */
                ptb = new PermohonanTuntutanButiran();
                ptb.setPermohonanTuntutan(pt);
                ptb.setPermohonanTuntutanKos(ptk);
                ptb.setInfoAudit(ip);
                enforceService.simpanPermohonanTuntutanButiran(ptb);

                for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                    pilih = getContext().getRequest().getParameter("pilih" + i);
                    System.out.println("pilih value : " + pilih);
                    if (pilih != null) {
                        BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(pilih));
                        InfoAudit info = new InfoAudit();
                        info = barang.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        barang.setInfoAudit(info);
                        barang.setKompaun(kompaun);
                        enforceService.updateBarangRampasan(barang);

                    }
                }

            } else {
                /* IF kompaun is already exist, then only update table barang rampasan since user only can add new barang rampasan to list kompaun*/
                for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                    pilih = getContext().getRequest().getParameter("pilih" + i);
                    System.out.println("pilih value : " + pilih);
                    if (pilih != null) {
                        BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(pilih));
                        InfoAudit info = new InfoAudit();
                        info = barang.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        barang.setInfoAudit(info);
                        barang.setKompaun(kompaun);
                        enforceService.updateBarangRampasan(barang);
                    }
                }

            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("syorKompaunNS", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");


    }

    public Resolution simpanMuktamadKompaun() {
        logger.info("------------simpanMuktamadKompaun--------------");
        System.out.println("id oks : " + orangKenaSyak);
        kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        listItemBarang = enforceService.findBarangRampasanByIdKompaun(idKompaun);
        System.out.println("listItemBarang :" + listItemBarang.size());

        jumlahKompaun = getContext().getRequest().getParameter("jumMuktamad");
        System.out.println("jum : " + jumlahKompaun);
        BigDecimal total = new BigDecimal(jumlahKompaun);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (kompaun != null) {
            kompaun.setTempohHari(Integer.parseInt(tempohKompaun));
            kompaunDAO.save(kompaun);

            for (int i = 0; i < listItemBarang.size(); i++) {
                String amaun = getContext().getRequest().getParameter("amaunKompaun" + i);
                BigDecimal amounKompaun = new BigDecimal(amaun);
                System.out.println("list item barang : " + listItemBarang.get(i).getIdBarang() + "kompaun : " + amounKompaun);
                BarangRampasan barang = barangRampasanDAO.findById(listItemBarang.get(i).getIdBarang());
                System.out.println("barang id : " + barang.getIdBarang());
                InfoAudit ia = new InfoAudit();
                ia = barang.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                barang.setInfoAudit(ia);
                barang.setKompaun(kompaun);
                barang.setAmaunKompaun(amounKompaun);
                enforceService.updateBarangRampasan(barang);
            }

            /* update table PermohonanTuntutKos*/
            System.out.println("kompaun : " + kompaun.getPermohonanTuntutanKos().getIdKos());
            PermohonanTuntutanKos ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());
            System.out.println("mohon tuntut kos : " + ptk);

            if (ptk != null) {
                ptk.setAmaunTuntutan(total);
                InfoAudit ip = ptk.getInfoAudit();
                ip.setDiKemaskiniOleh(pengguna);
                ip.setTarikhKemaskini(new java.util.Date());
                ptk.setInfoAudit(ip);
                permohonanTuntutanKosDAO.saveOrUpdate(ptk);
            }

            /* update table kompaun*/
            InfoAudit ia = kompaun.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new Date());
            kompaun.setAmaun(total);
            kompaun.setInfoAudit(ia);
            kompaunDAO.saveOrUpdate(kompaun);
        }

        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return showForm2();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/tawaran_kompaun_muktamad.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMuktamadKompaunMelaka() {
        if (StringUtils.isNotBlank(idOp)) {
            logger.info("::multipleOp true : simpanMuktamadKompaunMelaka : idOp " + idOp);
            senaraiBarangRampasan = enforceService.findBarangKompaun(Long.parseLong(idOp));
        }
        logger.info("------------simpanMuktamadKompaunMelaka--------------" + senaraiBarangRampasan.size());
        kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(orangKenaSyak));
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            if (kompaun == null) {
                System.out.println("new ptk");

                /* insert into table PermohonanTuntutKos*/
                PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();

                BigDecimal total = new BigDecimal(getContext().getRequest().getParameter("jumCaraBayar"));
                System.out.println("total : " + total);
                ptk.setCawangan(pengguna.getKodCawangan());
                ptk.setPermohonan(permohonan);
                ptk.setItem("BAYARAN KOMPAUN");
                ptk.setAmaunTuntutan(total);
                KodTransaksi kt = new KodTransaksi();
                kt.setKod("20027");
                ptk.setKodTransaksi(kt);
                KodTuntut ktu = new KodTuntut();
                ktu.setKod("K01");
                ptk.setKodTuntut(ktu);
                InfoAudit ip = new InfoAudit();
                ip.setDimasukOleh(pengguna);
                ip.setTarikhMasuk(new Date());
                ptk.setInfoAudit(ip);
                permohonanTuntutanKosDAO.save(ptk);

                pt = new PermohonanTuntutan();
                ptb = new PermohonanTuntutanButiran();

                System.out.println("new kompaun");

                /* insert into table Kompaun*/
                kompaun = new Kompaun();
                kompaun.setPermohonan(permohonan);
                kompaun.setCawangan(pengguna.getKodCawangan());
                kompaun.setAmaun(total);
                kompaun.setNoRujukan("MK");//MK = muktamad kompaun
                kompaun.setOrangKenaSyak(oks);
                kompaun.setIsuKepada(oks.getNama());
                kompaun.setNoPengenalan(oks.getNoPengenalan());
                kompaun.setTempohHari(Integer.parseInt(tempohKompaun));
                kompaun.setPermohonanTuntutanKos(ptk);

                InfoAudit ia = kompaun.getInfoAudit();
                if (ia == null) {
                    ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new Date());
                    kompaun.setInfoAudit(ia);
                } else {
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());
                }
                kompaunDAO.save(kompaun);

                //update column no rujukan by set no rujukan = id kompaun

                kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
                kompaunDAO.saveOrUpdate(kompaun);

                System.out.println("size ================== senaraiBarangRampasan : " + senaraiBarangRampasan.size());
                for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                    pilih = getContext().getRequest().getParameter("pilih" + i);
                    System.out.println("pilih value (if block): " + pilih);
                    if (pilih != null) {
                        String amaunMuktamadKompaun = getContext().getRequest().getParameter("amaunMuktamadKompaun" + i);
                        System.out.println("amaunMuktamadKompaun :" + amaunMuktamadKompaun);
                        BigDecimal amounKompaun = new BigDecimal(amaunMuktamadKompaun);
                        BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(pilih));
                        InfoAudit info = new InfoAudit();
                        info = barang.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        barang.setInfoAudit(info);
                        barang.setAmaunKompaunSyor(amounKompaun);
                        barang.setAmaunKompaun(amounKompaun);
                        barang.setKompaun(kompaun);
                        enforceService.updateBarangRampasan(barang);

                    }
                }

                Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
                Calendar cal = Calendar.getInstance();
                cal.setTime(c1);
                cal.add(Calendar.DATE, kompaun.getTempohHari());

                String tarikhAkhirBayar = sdf.format(cal.getTime());
                logger.info("tarikh akhir bayar : " + tarikhAkhirBayar);

                pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                pt.setTempoh(Integer.parseInt(tempohKompaun));


                KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");
                pt.setCawangan(pengguna.getKodCawangan());
                pt.setPermohonan(permohonan);
                pt.setInfoAudit(ia);
                pt.setKodTransaksiTuntut(kodTransTuntut);
                pt.setTarikhTuntutan(new java.util.Date());

                enforceService.simpanPermohonanTuntutan(pt);

                ptb.setPermohonanTuntutan(pt);
                ptb.setPermohonanTuntutanKos(ptk);
                ptb.setInfoAudit(ia);
                enforceService.simpanPermohonanTuntutanButiran(ptb);

            } else {
                /* IF kompaun is already exist, then only update table barang rampasan since user only can add new barang rampasan to list kompaun*/
                for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                    pilih = getContext().getRequest().getParameter("pilih" + i);
                    System.out.println("pilih value (else block): " + pilih);
                    if (pilih != null) {
                        String amaun = getContext().getRequest().getParameter("amaunMuktamadKompaun" + i);
                        BigDecimal amounKompaun = new BigDecimal(amaun);
                        BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(pilih));
                        InfoAudit info = new InfoAudit();
                        info = barang.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        barang.setInfoAudit(info);
                        barang.setKompaun(kompaun);
                        barang.setAmaunKompaunSyor(amounKompaun);
                        barang.setAmaunKompaun(amounKompaun);
                        enforceService.updateBarangRampasan(barang);

                        BigDecimal amounKompaunTemp = kompaun.getAmaun();

                        amounKompaunTemp = amounKompaunTemp.add(amounKompaun);
                        System.out.println("amounKompaunTemp ========= " + amounKompaunTemp);

                        kompaun.setAmaun(amounKompaunTemp);

                        InfoAudit ia = kompaun.getInfoAudit();
                        if (ia != null) {
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new Date());
                            kompaun.setInfoAudit(ia);
                        }
                        kompaunDAO.saveOrUpdate(kompaun);

                        if (kompaun.getPermohonanTuntutanKos() != null) {
                            PermohonanTuntutanKos ptk = kompaun.getPermohonanTuntutanKos();
                            BigDecimal amounTemp = ptk.getAmaunTuntutan();
                            amounTemp = amounTemp.add(amounKompaun);

                            System.out.println("amounTemp ptk========= " + amounTemp);
                            ptk.setAmaunTuntutan(amounTemp);
                            ia = ptk.getInfoAudit();
                            if (ia != null) {
                                ia.setDiKemaskiniOleh(pengguna);
                                ia.setTarikhKemaskini(new Date());
                                ptk.setInfoAudit(ia);
                            }
                            permohonanTuntutanKosDAO.saveOrUpdate(ptk);

                        }


                    }
                }


            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        if (idOp != null && StringUtils.isNotBlank(idOp)) {
            logger.info("::multipleOp true : add");
            getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("muktamadKompaunMLK", Boolean.TRUE);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        logger.info("------------simpanSyorKompaunOKS--------------" + senaraiPermohonanSaksi.size());
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            for (int i = 0; i < senaraiPermohonanSaksi.size(); i++) {
                Long idSaksi = senaraiPermohonanSaksi.get(i).getIdSaksi();
                logger.info("idSaksi : " + idSaksi);
                InfoAudit info = new InfoAudit();
                kompaun = enforcementService.findKompaunByNoRujukan(noRujukan);
                kompaun = enforcementService.findKompaunByIdSaksi(idSaksi);
                if (kompaun == null) {
                    kompaun = new Kompaun();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());

                } else {

                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                }
                kompaun.setInfoAudit(info);
                kompaun.setPermohonan(permohonan);
                kompaun.setCawangan(pengguna.getKodCawangan());
                kompaun.setAmaun(BigDecimal.ZERO);

//                    kompaun.setNoRujukan(saksi.getIdSaksi());
                kompaun.setOrangKenaSyak(oks);
                kompaun.setIsuKepada(oks.getNama());
                kompaun.setNoPengenalan(oks.getNoPengenalan());

                kompaunDAO.save(kompaun);

                //update column no rujukan by set no rujukan = id kompaun

                kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
                kompaunDAO.saveOrUpdate(kompaun);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");


    }

    public Resolution popup() {
        logger.debug("--------popup : syor kompaun NS--------");
        getContext().getRequest().setAttribute("syorKompaunNS", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun_syor.jsp").addParameter("popup", "true");
    }

    public Resolution popupMuktamadKompaun() {
        logger.debug("--------popupMuktamadKompaun--------");
        getContext().getRequest().setAttribute("muktamadKompaunMLK", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun_syor.jsp").addParameter("popup", "true");
    }

    public Resolution popupMuktamadPelupusan() {
        logger.debug("--------popupMuktamadPelupusan--------");
        getContext().getRequest().setAttribute("bayaranPelupusan", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun_syor.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        logger.debug("refreshpage2........");
        return new RedirectResolution(MaklumatKompaunActionBean.class, "locate");
    }

    public Resolution refreshPageMuktamadkompaun() {
        logger.debug("--------refreshPageMuktamadkompaun--------");
        return new JSP("penguatkuasaan/tawaran_kompaun_muktamad.jsp").addParameter("tab", "true");
    }

    public Resolution refreshPageMuktamadkompaunMelaka() {
        logger.debug("--------refreshPageMuktamadkompaunMelaka--------");
        getContext().getRequest().setAttribute("muktamadKompaunMLK", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution refreshPageBayaranPelupusan() {
        logger.debug("--------refreshPageBayaranPelupusan--------");
        getContext().getRequest().setAttribute("bayaranPelupusan", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution viewMuktamadKompaunDetail() {
        logger.debug("view muktamad kompaun detail");
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        orangKenaSyak = getContext().getRequest().getParameter("oks");
        listItemBarang = enforceService.findBarangRampasanByIdKompaun(idKompaun);
        if (listItemBarang != null) {
            kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        }
        return new JSP("penguatkuasaan/popup_view_muktamad_kompaun.jsp").addParameter("popup", "true");
    }

    public Resolution viewMuktamadKompaun() {
        logger.debug("-----------view muktamad kompaun------------ NS");
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        orangKenaSyak = getContext().getRequest().getParameter("oks");
        listItemBarang = enforceService.findBarangRampasanByIdKompaun(idKompaun);
        if (listItemBarang != null) {
            kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        }
        getContext().getRequest().setAttribute("syorKompaunNS", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_view_muktamad_kompaun_detail.jsp").addParameter("popup", "true");
    }

    public Resolution viewMuktamadKompaunMelaka() {
        logger.debug("------------------viewMuktamadKompaunMelaka---------------");
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        orangKenaSyak = getContext().getRequest().getParameter("oks");
        listItemBarang = enforceService.findBarangRampasanByIdKompaun(idKompaun);
        if (listItemBarang != null) {
            kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        }
        getContext().getRequest().setAttribute("muktamadKompaunMLK", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_view_muktamad_kompaun_detail.jsp").addParameter("popup", "true");
    }

    public Resolution viewMuktamadBayaranPelupusan() {
        logger.debug("------------------viewMuktamadBayaranPelupusan---------------");
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        orangKenaSyak = getContext().getRequest().getParameter("oks");
        listItemBarang = enforceService.findBarangRampasanByIdKompaun(idKompaun);
        if (listItemBarang != null) {
            kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        }
        getContext().getRequest().setAttribute("bayaranPelupusan", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_view_muktamad_kompaun_detail.jsp").addParameter("popup", "true");
    }

    public Resolution viewSyorKompaunDetail() {
        logger.debug("view syor kompaun detail");
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        orangKenaSyak = getContext().getRequest().getParameter("oks");
        listItemBarang = enforceService.findBarangRampasanByIdKompaun(idKompaun);
        if (listItemBarang != null) {
            kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        }
        return new JSP("penguatkuasaan/popup_view_syor_kompaun.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSingle() {
        logger.debug("----------delete kompaun------------");
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        kompaun = enforcementService.findKompaunByIdKompaun(idKompaun);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            PermohonanTuntutanKos ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());
            ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());

            if (ptb != null) {
                pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
            }
            permohonanTuntutanButiranDAO.delete(ptb);
            permohonanTuntutanDAO.delete(pt);
            permohonanTuntutanKosDAO.delete(ptk);
            kompaunDAO.delete(kompaun);



            /*update table barang rampasan. set amounKompaunSyor & kompaun to null*/

            listItemBarang = enforceService.findBarangRampasanByIdKompaun(idKompaun);
            for (int i = 0; i < listItemBarang.size(); i++) {
                BarangRampasan barang = barangRampasanDAO.findById(listItemBarang.get(i).getIdBarang());
                InfoAudit info = new InfoAudit();
                info = barang.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                barang.setInfoAudit(info);
                barang.setAmaunKompaunSyor(null);
                barang.setAmaunKompaun(null);
                barang.setKompaun(null);
                enforceService.updateBarangRampasan(barang);
            }

            if (kompaun != null) {
                oks = kompaun.getOrangKenaSyak();
                if (oks != null) {
                    oks.setAmaunKompaunSyor(null);
                    enforceService.simpanAduanOrangDisyaki(oks);
                }
            }


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(MaklumatKompaunActionBean.class, "locate");
    }

    public Resolution simpanBayaranPelupusan() {
        logger.info("------------simpanBayaranPelupusan--------------" + senaraiBarangRampasan.size());
        kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(orangKenaSyak));
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            if (kompaun == null) {
                System.out.println("new ptk");

                /* insert into table PermohonanTuntutKos*/
                PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();

                BigDecimal total = new BigDecimal(getContext().getRequest().getParameter("jumCaraBayar"));
                System.out.println("total : " + total);
                ptk.setCawangan(pengguna.getKodCawangan());
                ptk.setPermohonan(permohonan);
                ptk.setItem("BAYARAN PELUPUSAN");
                ptk.setAmaunTuntutan(total);
                KodTransaksi kt = new KodTransaksi();
                kt.setKod("20027");
                ptk.setKodTransaksi(kt);
                KodTuntut ktu = new KodTuntut();
                ktu.setKod("K01");
                ptk.setKodTuntut(ktu);
                InfoAudit ip = new InfoAudit();
                ip.setDimasukOleh(pengguna);
                ip.setTarikhMasuk(new Date());
                ptk.setInfoAudit(ip);
                permohonanTuntutanKosDAO.save(ptk);

                System.out.println("new kompaun");

                /* insert into table Kompaun*/
                kompaun = new Kompaun();
                kompaun.setPermohonan(permohonan);
                kompaun.setCawangan(pengguna.getKodCawangan());
                kompaun.setAmaun(total);
                kompaun.setNoRujukan("BP"); //BP = Bayaran Pelupusan
                kompaun.setOrangKenaSyak(oks);
                kompaun.setIsuKepada(oks.getNama());
                kompaun.setNoPengenalan(oks.getNoPengenalan());
                kompaun.setTempohHari(Integer.parseInt(tempohKompaun));
                kompaun.setStatusTerima(kodStatusTerimaDAO.findById("BP")); //BP = Bayaran Pelupusan
                kompaun.setPermohonanTuntutanKos(ptk);

                InfoAudit ia = kompaun.getInfoAudit();
                if (ia == null) {
                    ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new Date());
                    kompaun.setInfoAudit(ia);
                } else {
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());
                }
                kompaunDAO.save(kompaun);

                //update column no rujukan by set no rujukan = id kompaun

                kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
                kompaunDAO.saveOrUpdate(kompaun);

                for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                    pilih = getContext().getRequest().getParameter("pilih" + i);
                    System.out.println("pilih value (if block): " + pilih);
                    if (pilih != null) {
                        String amaunMuktamadKompaun = getContext().getRequest().getParameter("amaunMuktamadKompaun" + i);
                        System.out.println("amaunMuktamadKompaun :" + amaunMuktamadKompaun);
                        BigDecimal amounKompaun = new BigDecimal(amaunMuktamadKompaun);
                        BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(pilih));
                        InfoAudit info = new InfoAudit();
                        info = barang.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        barang.setInfoAudit(info);
                        barang.setAmaunKompaunSyor(amounKompaun);
                        barang.setAmaunKompaun(amounKompaun);
                        barang.setKompaun(kompaun);
                        enforceService.updateBarangRampasan(barang);

                    }
                }

            } else {
                /* IF kompaun is already exist, then only update table barang rampasan since user only can add new barang rampasan to list kompaun*/
                for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                    pilih = getContext().getRequest().getParameter("pilih" + i);
                    System.out.println("pilih value (else block): " + pilih);
                    if (pilih != null) {
                        String amaun = getContext().getRequest().getParameter("amaunMuktamadKompaun" + i);
                        BigDecimal amounKompaun = new BigDecimal(amaun);
                        BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(pilih));
                        InfoAudit info = new InfoAudit();
                        info = barang.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        barang.setInfoAudit(info);
                        barang.setKompaun(kompaun);
                        barang.setAmaunKompaunSyor(amounKompaun);
                        barang.setAmaunKompaun(amounKompaun);
                        enforceService.updateBarangRampasan(barang);
                    }
                }

            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("bayaranPelupusan", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution addRecord() {
        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::add record - id OP : " + idOp);

        if (StringUtils.isNotBlank(idOp)) {
            senaraiAduanOrangKenaSyak = enforceService.findOksByIdOp(Long.parseLong(idOp));

            senaraiBarangRampasan = enforceService.findBarangKompaun(Long.parseLong(idOp));
            System.out.println("size senaraiBarangRampasan (amaunKompaunSyor is null): " + senaraiBarangRampasan.size());
            listBarangRampasan = enforceService.findBarangRampasan(Long.parseLong(idOp));
            System.out.println("size listBarangRampasan (amaunKompaunSyor is not null): " + listBarangRampasan.size());
            rowCount = String.valueOf(senaraiBarangRampasan.size());
            recordCount = String.valueOf(listBarangRampasan.size());

            System.out.println("senaraiBarangRampasan : " + senaraiBarangRampasan.size());
            System.out.println("senaraiAduanOrangKenaSyak : " + senaraiAduanOrangKenaSyak.size());
        }
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun_syor.jsp").addParameter("popup", "true");
    }

    public Resolution addRecordIP() {
        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::add record - id OP : " + idOp);

        if (StringUtils.isNotBlank(idOp)) {
            senaraiAduanOrangKenaSyak = enforceService.findOksByIdOp(Long.parseLong(idOp));
            senaraiOKSForKompaun = enforcementService.getListAduanOrangkenaSyak(idPermohonan);

            senaraiBarangRampasan = enforceService.findBarangKompaunIP(Long.parseLong(idOp), permohonan.getIdPermohonan());
            System.out.println("size senaraiBarangRampasan (amaunKompaunSyor is null): " + senaraiBarangRampasan.size());
            listBarangRampasan = enforceService.findBarangRampasan(Long.parseLong(idOp));
            System.out.println("size listBarangRampasan (amaunKompaunSyor is not null): " + listBarangRampasan.size());
            rowCount = String.valueOf(senaraiBarangRampasan.size());
            recordCount = String.valueOf(listBarangRampasan.size());

            System.out.println("senaraiBarangRampasan : " + senaraiBarangRampasan.size());
            System.out.println("senaraiAduanOrangKenaSyak : " + senaraiAduanOrangKenaSyak.size());
            System.out.println("senaraiOKSForKompaun : " + senaraiOKSForKompaun.size());
        }
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun_syor.jsp").addParameter("popup", "true");
    }

    public Resolution simpanMuktamadKompaunMultipleOp() {
        if (StringUtils.isNotBlank(idOp)) {
            logger.info("::multipleOp true : simpanMuktamadKompaunMelaka : idOp " + idOp);
            senaraiBarangRampasan = enforceService.findBarangKompaun(Long.parseLong(idOp));
        }
        logger.info("------------simpanMuktamadKompaunMultipleOp--------------");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {

            logger.info("size ================== senaraiBarangRampasan : " + senaraiBarangRampasan.size());
            for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                pilih = getContext().getRequest().getParameter("pilih" + i);
                System.out.println("pilih value (if block): " + pilih);
                if (pilih != null) {
                    String amaunMuktamadKompaun = getContext().getRequest().getParameter("amaunMuktamadKompaun" + i);
                    System.out.println("amaunMuktamadKompaun :" + amaunMuktamadKompaun);
                    BigDecimal amounKompaun = new BigDecimal(amaunMuktamadKompaun);
                    BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(pilih));

                    if (barang.getAduanOrangKenaSyak() != null) {
                        kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(barang.getAduanOrangKenaSyak().getIdOrangKenaSyak());
                        oks = aduanOrangKenaSyakDAO.findById(barang.getAduanOrangKenaSyak().getIdOrangKenaSyak());

                        if (kompaun == null) {
                            System.out.println("new ptk");

                            /* insert into table PermohonanTuntutKos*/
                            PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();

                            BigDecimal total = new BigDecimal(getContext().getRequest().getParameter("jumCaraBayar"));
                            System.out.println("total : " + total);
                            ptk.setCawangan(pengguna.getKodCawangan());
                            ptk.setPermohonan(permohonan);
                            ptk.setItem("BAYARAN KOMPAUN[" + oks.getNama() + "]");
                            ptk.setAmaunTuntutan(amounKompaun);
                            KodTransaksi kt = new KodTransaksi();
                            kt.setKod("20027");
                            ptk.setKodTransaksi(kt);
                            KodTuntut ktu = new KodTuntut();
                            ktu.setKod("K01");
                            ptk.setKodTuntut(ktu);
                            InfoAudit ip = new InfoAudit();
                            ip.setDimasukOleh(pengguna);
                            ip.setTarikhMasuk(new Date());
                            ptk.setInfoAudit(ip);
                            permohonanTuntutanKosDAO.save(ptk);

                            pt = new PermohonanTuntutan();
                            ptb = new PermohonanTuntutanButiran();

                            System.out.println("new kompaun");

                            /* insert into table Kompaun*/
                            kompaun = new Kompaun();
                            kompaun.setPermohonan(permohonan);
                            kompaun.setCawangan(pengguna.getKodCawangan());
                            kompaun.setAmaun(amounKompaun);
                            kompaun.setNoRujukan("MK");//MK = muktamad kompaun
                            kompaun.setOrangKenaSyak(oks);
                            kompaun.setIsuKepada(oks.getNama());
                            kompaun.setNoPengenalan(oks.getNoPengenalan());
                            kompaun.setTempohHari(Integer.parseInt(tempohKompaun));
                            kompaun.setPermohonanTuntutanKos(ptk);

                            InfoAudit ia = kompaun.getInfoAudit();
                            if (ia == null) {
                                ia = new InfoAudit();
                                ia.setDimasukOleh(pengguna);
                                ia.setTarikhMasuk(new Date());
                                kompaun.setInfoAudit(ia);
                            } else {
                                ia.setDiKemaskiniOleh(pengguna);
                                ia.setTarikhKemaskini(new Date());
                            }
                            kompaunDAO.save(kompaun);

                            //update column no rujukan by set no rujukan = id kompaun

                            kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
                            kompaunDAO.saveOrUpdate(kompaun);

                            Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(c1);
                            cal.add(Calendar.DATE, kompaun.getTempohHari());

                            String tarikhAkhirBayar = sdf.format(cal.getTime());
                            logger.info("tarikh akhir bayar : " + tarikhAkhirBayar);

                            pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                            pt.setTempoh(Integer.parseInt(tempohKompaun));


                            KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                            kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");
                            pt.setCawangan(pengguna.getKodCawangan());
                            pt.setPermohonan(permohonan);
                            pt.setInfoAudit(ia);
                            pt.setKodTransaksiTuntut(kodTransTuntut);
                            pt.setTarikhTuntutan(new java.util.Date());

                            enforceService.simpanPermohonanTuntutan(pt);

                            ptb.setPermohonanTuntutan(pt);
                            ptb.setPermohonanTuntutanKos(ptk);
                            ptb.setInfoAudit(ia);
                            enforceService.simpanPermohonanTuntutanButiran(ptb);

                        } else {
                            //update column amoun for kkuasa_kompaun
                            BigDecimal amounKompaunTemp = kompaun.getAmaun();
                            amounKompaunTemp = amounKompaunTemp.add(amounKompaun);
                            System.out.println("amounKompaunTemp ========= " + amounKompaunTemp);
                            kompaun.setAmaun(amounKompaunTemp);

                            InfoAudit ia = kompaun.getInfoAudit();
                            if (ia != null) {
                                ia.setDiKemaskiniOleh(pengguna);
                                ia.setTarikhKemaskini(new Date());
                                kompaun.setInfoAudit(ia);
                            }
                            kompaunDAO.saveOrUpdate(kompaun);

                            if (kompaun.getPermohonanTuntutanKos() != null) {
                                //update column amoun for mohon_tuntut_kos
                                PermohonanTuntutanKos ptk = kompaun.getPermohonanTuntutanKos();
                                BigDecimal amounTemp = ptk.getAmaunTuntutan();
                                amounTemp = amounTemp.add(amounKompaun);

                                System.out.println("amounTemp ptk========= " + amounTemp);
                                ptk.setAmaunTuntutan(amounTemp);
                                ia = ptk.getInfoAudit();
                                if (ia != null) {
                                    ia.setDiKemaskiniOleh(pengguna);
                                    ia.setTarikhKemaskini(new Date());
                                    ptk.setInfoAudit(ia);
                                }
                                permohonanTuntutanKosDAO.saveOrUpdate(ptk);

                            }
                        }
                    }

                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setAmaunKompaunSyor(amounKompaun);
                    barang.setAmaunKompaun(amounKompaun);
                    barang.setKompaun(kompaun);
                    enforceService.updateBarangRampasan(barang);
                }
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        if (idOp != null && StringUtils.isNotBlank(idOp)) {
            logger.info("::multipleOp true : add");
            getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("muktamadKompaunMLK", Boolean.TRUE);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMuktamadKompaunOks() {
        senaraiOKSForKompaun = enforcementService.getListAduanOrangkenaSyak(idPermohonan);
        logger.info("::::::::::::::size senaraiOKSForKompaun : " + senaraiOKSForKompaun.size());
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        InfoAudit ia = new InfoAudit();

        try {
            for (int i = 0; i < senaraiOKSForKompaun.size(); i++) {
                System.out.println(":::::::::::::kemasukan data :::::::::::::::::: " + i);
                pilih = getContext().getRequest().getParameter("pilih" + i);
                System.out.println("pilih value (if block): " + pilih);
                if (StringUtils.isNotBlank(pilih)) {
                    String amaunMuktamadKompaun = getContext().getRequest().getParameter("amaunMuktamadKompaun" + i);
                    System.out.println("amaunMuktamadKompaun :" + amaunMuktamadKompaun);
                    BigDecimal amounKompaun = new BigDecimal(amaunMuktamadKompaun);

                    oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(pilih));

                    if (oks != null) {
                        ia = oks.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());

                        oks.setInfoAudit(ia);
                        oks.setAmaunKompaunSyor(amounKompaun);
                        enforceService.simpanAduanOrangDisyaki(oks);

                        kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(oks.getIdOrangKenaSyak());

                        if (kompaun == null) {
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            System.out.println("new ptk ,pt, ptb");
                            /* insert into table PermohonanTuntutKos*/
                            ptk = new PermohonanTuntutanKos();
                            pt = new PermohonanTuntutan();
                            ptb = new PermohonanTuntutanButiran();

                            System.out.println("new kompaun");
                            /* insert into table Kompaun*/
                            kompaun = new Kompaun();
                        } else {
                            ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());

                            ia = kompaun.getInfoAudit();
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new java.util.Date());

                            ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());

                            if (ptb != null) {
                                pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                            }

                        }

                        ptk.setCawangan(pengguna.getKodCawangan());
                        ptk.setPermohonan(permohonan);
                        ptk.setItem("BAYARAN KOMPAUN[" + oks.getNama() + "]");
                        ptk.setAmaunTuntutan(amounKompaun);
                        KodTransaksi kt = new KodTransaksi();
                        kt.setKod("20027");
                        ptk.setKodTransaksi(kt);
                        KodTuntut ktu = new KodTuntut();
                        ktu.setKod("K01");
                        ptk.setKodTuntut(ktu);
                        InfoAudit ip = new InfoAudit();
                        ip.setDimasukOleh(pengguna);
                        ip.setTarikhMasuk(new Date());
                        ptk.setInfoAudit(ip);
                        permohonanTuntutanKosDAO.save(ptk);

                        kompaun.setPermohonan(permohonan);
                        kompaun.setCawangan(pengguna.getKodCawangan());
                        kompaun.setAmaun(amounKompaun);
                        kompaun.setNoRujukan("MK");//MK = muktamad kompaun
                        kompaun.setIsuKepada(oks.getNama());
                        kompaun.setNoPengenalan(oks.getNoPengenalan());
                        kompaun.setTempohHari(Integer.parseInt(tempohKompaun));
                        kompaun.setPermohonanTuntutanKos(ptk);
                        kompaun.setInfoAudit(ia);
                        kompaun.setOrangKenaSyak(oks);
                        kompaunDAO.save(kompaun);

                        //update column no rujukan by set no rujukan = id kompaun

                        kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
                        kompaunDAO.saveOrUpdate(kompaun);

                        Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(c1);
                        cal.add(Calendar.DATE, kompaun.getTempohHari());

                        String tarikhAkhirBayar = sdf.format(cal.getTime());
                        logger.info("tarikh akhir bayar : " + tarikhAkhirBayar);

                        pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                        pt.setTempoh(Integer.parseInt(tempohKompaun));


                        KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                        kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");
                        pt.setCawangan(pengguna.getKodCawangan());
                        pt.setPermohonan(permohonan);
                        pt.setInfoAudit(ia);
                        pt.setKodTransaksiTuntut(kodTransTuntut);
                        pt.setTarikhTuntutan(new java.util.Date());

                        enforceService.simpanPermohonanTuntutan(pt);

                        ptb.setPermohonanTuntutan(pt);
                        ptb.setPermohonanTuntutanKos(ptk);
                        ptb.setInfoAudit(ia);
                        enforceService.simpanPermohonanTuntutanButiran(ptb);

                        senaraiBarangRampasan = enforceService.findBarangOks(oks.getIdOrangKenaSyak());
                        logger.info("::::::::::::::jumlah barang rampasan : " + senaraiBarangRampasan.size() + " untuk :" + oks.getNama());
                        for (int j = 0; j < senaraiBarangRampasan.size(); j++) {
                            BarangRampasan barangRampasan = senaraiBarangRampasan.get(j);
                            if (barangRampasan != null) {
                                ia = barangRampasan.getInfoAudit();
                                ia.setDiKemaskiniOleh(pengguna);
                                ia.setTarikhKemaskini(new java.util.Date());
                                barangRampasan.setInfoAudit(ia);
                                barangRampasan.setAmaunKompaunSyor(amounKompaun);
                                barangRampasan.setAmaunKompaun(amounKompaun);
                                barangRampasan.setKompaun(kompaun);
                                enforceService.updateBarangRampasan(barangRampasan);
                            }
                        }
                    }
                }
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution viewMuktamadKompaunOp() {
        logger.debug("------------------viewMuktamadKompaunOp---------------");

        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::viewMuktamadKompaunOp - id OP : " + idOp);

        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        orangKenaSyak = getContext().getRequest().getParameter("oks");
        listItemBarang = enforceService.findBarangRampasanByIdKompaun(idKompaun);
        if (listItemBarang != null) {
            kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
            listBarangRampasan = enforceService.findBarangRampasan(Long.parseLong(idOp));
            recordCount = String.valueOf(listBarangRampasan.size());
            oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(orangKenaSyak));
            System.out.println("listBarangRampasan Op : " + listBarangRampasan.size());
        }

        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_view_muktamad_kompaun_detail.jsp").addParameter("popup", "true");
    }

    public Resolution refreshPageMuktamadkompaunMelakaOp() {
        logger.debug("--------refreshPageMuktamadkompaunMelakaOp--------");
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    //bayaran komppaun saksi luar
    public Resolution simpanKompaunSaksi() {
        for (int i = 0; i < listOp.size(); i++) {
            logger.info("------------simpanKompaunSaksi--------------");
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            InfoAudit ia = new InfoAudit();
            try {
                String totalSaksi = getContext().getRequest().getParameter("totalSaksi" + i);
                System.out.println("totalSaksi ::: " + totalSaksi);
                if (StringUtils.isNotBlank(totalSaksi)) {
                    if (!totalSaksi.equalsIgnoreCase("0")) {
                        logger.info("size ================== senaraiPermohonanSaksi : " + senaraiPermohonanSaksi.size());
                        for (int j = 0; j < senaraiPermohonanSaksi.size(); j++) {
                            pilih = getContext().getRequest().getParameter("pilih" + i + j);
                            String pilihTemp = getContext().getRequest().getParameter("pilihTemp" + i + j);
                            tempohKompaun = getContext().getRequest().getParameter("tempohKompaun" + i);
                            System.out.println("tempohKompaun : " + tempohKompaun);
                            System.out.println("pilih value (if block): " + pilih);
                            if (pilih != null) {
                                System.out.println("checked pilih");
                                String kompaunSaksi = getContext().getRequest().getParameter("kompaunSaksi" + i + j);
                                System.out.println("kompaunSaksi :" + kompaunSaksi);
                                BigDecimal amounKompaun = new BigDecimal(kompaunSaksi);
                                saksi = permohonanSaksiDAO.findById(Long.parseLong(pilih));

                                saksi.setStatusDakwaanKompaun("Y");
                                enforceService.updateSaksi(saksi);

                                kompaun = enforcementService.findKompaunByIdSaksi(saksi.getIdSaksi());

                                if (kompaun == null) {
                                    ia.setDimasukOleh(pengguna);
                                    ia.setTarikhMasuk(new java.util.Date());
                                    System.out.println("new ptk ,pt, ptb");
                                    /* insert into table PermohonanTuntutKos*/
                                    ptk = new PermohonanTuntutanKos();
                                    pt = new PermohonanTuntutan();
                                    ptb = new PermohonanTuntutanButiran();

                                    System.out.println("new kompaun");
                                    /* insert into table Kompaun*/
                                    kompaun = new Kompaun();
                                } else {
                                    ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());

                                    ia = kompaun.getInfoAudit();
                                    ia.setDiKemaskiniOleh(pengguna);
                                    ia.setTarikhKemaskini(new java.util.Date());

                                    ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());

                                    if (ptb != null) {
                                        pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                                    }

                                }

                                ptk.setCawangan(pengguna.getKodCawangan());
                                ptk.setPermohonan(permohonan);
                                ptk.setItem("BAYARAN KOMPAUN[" + saksi.getNama() + "]");
                                ptk.setAmaunTuntutan(amounKompaun);
                                KodTransaksi kt = new KodTransaksi();
                                kt.setKod("20027");
                                ptk.setKodTransaksi(kt);
                                KodTuntut ktu = new KodTuntut();
                                ktu.setKod("K01");
                                ptk.setKodTuntut(ktu);
                                InfoAudit ip = new InfoAudit();
                                ip.setDimasukOleh(pengguna);
                                ip.setTarikhMasuk(new Date());
                                ptk.setInfoAudit(ip);
                                permohonanTuntutanKosDAO.save(ptk);

                                kompaun.setPermohonan(permohonan);
                                kompaun.setCawangan(pengguna.getKodCawangan());
                                kompaun.setAmaun(amounKompaun);
                                kompaun.setNoRujukan("MK");//MK = muktamad kompaun
                                kompaun.setIsuKepada(saksi.getNama());
                                kompaun.setNoPengenalan(saksi.getNoPengenalan());
                                kompaun.setTempohHari(Integer.parseInt(tempohKompaun));
                                kompaun.setPermohonanTuntutanKos(ptk);
                                kompaun.setSaksi(saksi);
                                kompaun.setInfoAudit(ia);
                                kompaunDAO.save(kompaun);

                                //update column no rujukan by set no rujukan = id kompaun

                                kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
                                kompaunDAO.saveOrUpdate(kompaun);

                                Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(c1);
                                cal.add(Calendar.DATE, kompaun.getTempohHari());

                                String tarikhAkhirBayar = sdf.format(cal.getTime());
                                logger.info("tarikh akhir bayar : " + tarikhAkhirBayar);

                                pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                                pt.setTempoh(Integer.parseInt(tempohKompaun));


                                KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                                kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");
                                pt.setCawangan(pengguna.getKodCawangan());
                                pt.setPermohonan(permohonan);
                                pt.setInfoAudit(ia);
                                pt.setKodTransaksiTuntut(kodTransTuntut);
                                pt.setTarikhTuntutan(new java.util.Date());

                                enforceService.simpanPermohonanTuntutan(pt);

                                ptb.setPermohonanTuntutan(pt);
                                ptb.setPermohonanTuntutanKos(ptk);
                                ptb.setInfoAudit(ia);
                                enforceService.simpanPermohonanTuntutanButiran(ptb);

                            } else {
                                System.out.println("uncheck pilih ::: " + pilihTemp);
                                //PENDING : need to delete kompaun, ptk,ptb,pt & set null to statusKompaunDakwa at mohon_saksi
                                saksi = permohonanSaksiDAO.findById(Long.parseLong(pilihTemp));
                                kompaun = enforcementService.findKompaunByIdSaksi(saksi.getIdSaksi());

                                if (kompaun != null) {
                                    ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());

                                    ia = kompaun.getInfoAudit();
                                    ia.setDiKemaskiniOleh(pengguna);
                                    ia.setTarikhKemaskini(new java.util.Date());

                                    ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());

                                    if (ptb != null) {
                                        pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                                    }

                                    permohonanTuntutanButiranDAO.delete(ptb);
                                    System.out.println("delete pbt");
                                    permohonanTuntutanDAO.delete(pt);
                                    System.out.println("delete pt");
                                    permohonanTuntutanKosDAO.delete(ptk);
                                    System.out.println("delete ptk");
                                    kompaunDAO.delete(kompaun);
                                    System.out.println("delete kompaun");



                                    saksi.setStatusDakwaanKompaun(null);
                                    System.out.println("update null status dakwa");
                                    enforceService.updateSaksi(saksi);


                                    //  ptb,pt,ptk,kompaun, mohonsaksi set null

                                }

                            }
                        }
                    }

                }


                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                Throwable t = e;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                t.printStackTrace();
                addSimpleError(t.getMessage());
            }
        }

        rehydrate();
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public String getTempohKompaun() {
        return tempohKompaun;
    }

    public void setTempohKompaun(String tempohKompaun) {
        this.tempohKompaun = tempohKompaun;
    }

    public String getPilih() {
        return pilih;
    }

    public void setPilih(String pilih) {
        this.pilih = pilih;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public List<BarangRampasan> getListBarangRampasan() {
        return listBarangRampasan;
    }

    public void setListBarangRampasan(List<BarangRampasan> listBarangRampasan) {
        this.listBarangRampasan = listBarangRampasan;
    }

    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }

    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }

    public Kompaun getKompaun() {
        return kompaun;
    }

    public void setKompaun(Kompaun kompaun) {
        this.kompaun = kompaun;
    }

    public PermohonanTuntutanBayar getMohonTuntutBayar() {
        return mohonTuntutBayar;
    }

    public void setMohonTuntutBayar(PermohonanTuntutanBayar mohonTuntutBayar) {
        this.mohonTuntutBayar = mohonTuntutBayar;
    }

    public List<AduanOrangKenaSyak> getSenaraiAduanOrangKenaSyak() {
        return senaraiAduanOrangKenaSyak;
    }

    public void setSenaraiAduanOrangKenaSyak(List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak) {
        this.senaraiAduanOrangKenaSyak = senaraiAduanOrangKenaSyak;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public AduanOrangKenaSyak getOks() {
        return oks;
    }

    public void setOks(AduanOrangKenaSyak oks) {
        this.oks = oks;
    }

    public String getOrangKenaSyak() {
        return orangKenaSyak;
    }

    public void setOrangKenaSyak(String orangKenaSyak) {
        this.orangKenaSyak = orangKenaSyak;
    }

    public String getJumlahKompaun() {
        return jumlahKompaun;
    }

    public void setJumlahKompaun(String jumlahKompaun) {
        this.jumlahKompaun = jumlahKompaun;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public List<BarangRampasan> getListItemBarang() {
        return listItemBarang;
    }

    public void setListItemBarang(List<BarangRampasan> listItemBarang) {
        this.listItemBarang = listItemBarang;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
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

    public List<Kompaun> getSenaraiBayaranPelupusan() {
        return senaraiBayaranPelupusan;
    }

    public void setSenaraiBayaranPelupusan(List<Kompaun> senaraiBayaranPelupusan) {
        this.senaraiBayaranPelupusan = senaraiBayaranPelupusan;
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

    public List<BarangRampasan> getListBarangForKompaun() {
        return listBarangForKompaun;
    }

    public void setListBarangForKompaun(List<BarangRampasan> listBarangForKompaun) {
        this.listBarangForKompaun = listBarangForKompaun;
    }

    public List<BarangRampasan> getListBarangInKompaun() {
        return listBarangInKompaun;
    }

    public void setListBarangInKompaun(List<BarangRampasan> listBarangInKompaun) {
        this.listBarangInKompaun = listBarangInKompaun;
    }

    public String getIdOp() {
        return idOp;
    }

    public void setIdOp(String idOp) {
        this.idOp = idOp;
    }

    public List<PermohonanSaksi> getSenaraiPermohonanSaksi() {
        return senaraiPermohonanSaksi;
    }

    public void setSenaraiPermohonanSaksi(List<PermohonanSaksi> senaraiPermohonanSaksi) {
        this.senaraiPermohonanSaksi = senaraiPermohonanSaksi;
    }

    public PermohonanSaksi getSaksi() {
        return saksi;
    }

    public void setSaksi(PermohonanSaksi saksi) {
        this.saksi = saksi;
    }

    public String getIdSaksi() {
        return idSaksi;
    }

    public void setIdSaksi(String idSaksi) {
        this.idSaksi = idSaksi;
    }

    public String getStatusDakwaanKompaun() {
        return statusDakwaanKompaun;
    }

    public void setStatusDakwaanKompaun(String statusDakwaanKompaun) {
        this.statusDakwaanKompaun = statusDakwaanKompaun;
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

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public List<Kompaun> getSenaraiKompaunIP() {
        return senaraiKompaunIP;
    }

    public void setSenaraiKompaunIP(List<Kompaun> senaraiKompaunIP) {
        this.senaraiKompaunIP = senaraiKompaunIP;
    }

    public List<AduanOrangKenaSyak> getSenaraiOKSForKompaun() {
        return senaraiOKSForKompaun;
    }

    public void setSenaraiOKSForKompaun(List<AduanOrangKenaSyak> senaraiOKSForKompaun) {
        this.senaraiOKSForKompaun = senaraiOKSForKompaun;
    }

    public List<AduanOrangKenaSyak> getSenaraiOKSAlreadyKompaun() {
        return senaraiOKSAlreadyKompaun;
    }

    public void setSenaraiOKSAlreadyKompaun(List<AduanOrangKenaSyak> senaraiOKSAlreadyKompaun) {
        this.senaraiOKSAlreadyKompaun = senaraiOKSAlreadyKompaun;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodTransaksiTuntut;
import etanah.model.Permohonan;
import etanah.dao.KompaunDAO;
import etanah.model.InfoAudit;
import etanah.model.Kompaun;
import etanah.service.EnforceService;

import etanah.model.Pengguna;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodTransaksi;
import etanah.model.KodTuntut;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import etanah.service.BPelService;
import java.math.BigDecimal;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_perintah_denda")
public class MaklumatPerintahDendaActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatPerintahDendaActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    private etanah.Configuration conf;
    private Pengguna pengguna;
    private String idPermohonan;
    private Permohonan permohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPihakBerkepentingan> hakMilikPihakBerkepentinganList;
    private String tempohDenda;
    private String amaun;
    private String pilihPihak;
    private Kompaun kompaun;
    private String idKompaun;
    private List<Kompaun> senaraiKompaun;
    private PermohonanPihak mohonPihak;
    private String noRujukan;
    private String stageId;
    private Date tarikhBayar;
    private Date tarikhBayar1;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private Pihak pihak;
    private List<HakmilikPermohonan> senaraiTanahMilik;
    private PermohonanTuntutanKos ptk;
    private PermohonanTuntutan pt;
    private PermohonanTuntutanButiran ptb;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String jumlahDendaTambahan;
    private String kodNegeri;
    private List<HakmilikPihakBerkepentingan> senaraiHakMilikPihakBerkepentingan;
    private PermohonanTuntutanBayar pembayaranDenda;
    private PermohonanTuntutanBayar pembayaranDendaTambahan;
    private List<Kompaun> senaraiDenda;
    private List<Kompaun> senaraiDendaTambahan;
    private Boolean statusAnsuranFlag = Boolean.FALSE;
    private Long idKos;
    private List<PermohonanTuntutanBayar> senaraiPermohonanTuntutanBayar;
    private Boolean statusBayaranRemediFlag = Boolean.FALSE;

    @DefaultHandler
    public Resolution showForm() {
        logger.info("------------showForm1 : perintah denda edit- -------------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        logger.info("------------showForm2 : perintah denda view --------------");
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        logger.info("------------showForm2 : status bayaran denda--------------");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {

            for (int i = 0; i < senaraiKompaun.size(); i++) {
                kompaun = senaraiKompaun.get(i);
                if (kompaun.getResit() == null) {
                    PermohonanTuntutanBayar permohonanTuntutanBayar = enforceService.findMohonTuntutBayar(kompaun.getPermohonanTuntutanKos().getIdKos());
                    if (permohonanTuntutanBayar != null) {
                        kompaun.setResit(permohonanTuntutanBayar.getDokumenKewangan());
                        kompaunDAO.save(kompaun);
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

        if (statusAnsuranFlag == false) {
            getContext().getRequest().setAttribute("status", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("statusPembayaranAnsuranRemedi", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        logger.info("------------showForm4 : remedi edit- -------------");
        getContext().getRequest().setAttribute("editRemedi", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        logger.info("------------showForm5 : remedi view--------------");
        getContext().getRequest().setAttribute("viewRemedi", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        logger.info("------------showForm6 : status bayaran denda & denda tambahan--------------");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
            for (int i = 0; i < senaraiDenda.size(); i++) {
                kompaun = senaraiDenda.get(i);
                if (kompaun.getResit() == null) {
                    pembayaranDenda = enforceService.findMohonTuntutBayar(kompaun.getPermohonanTuntutanKos().getIdKos());
                    if (pembayaranDenda != null) {
                        kompaun.setResit(pembayaranDenda.getDokumenKewangan());
                        kompaunDAO.save(kompaun);
                    }
                } else {
                    pembayaranDenda = enforceService.findMohonTuntutBayar(kompaun.getPermohonanTuntutanKos().getIdKos());
                }

            }

            for (int i = 0; i < senaraiDendaTambahan.size(); i++) {
                kompaun = senaraiDendaTambahan.get(i);
                if (kompaun.getResit() == null) {
                    pembayaranDendaTambahan = enforceService.findMohonTuntutBayar(kompaun.getPermohonanTuntutanKos().getIdKos());
                    if (pembayaranDendaTambahan != null) {
                        kompaun.setResit(pembayaranDendaTambahan.getDokumenKewangan());
                        kompaunDAO.save(kompaun);
                    }
                } else {
                    pembayaranDendaTambahan = enforceService.findMohonTuntutBayar(kompaun.getPermohonanTuntutanKos().getIdKos());
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
        getContext().getRequest().setAttribute("statusPembayaranDenda", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        getContext().getRequest().setAttribute("statusPembayaranAnsuranRemedi", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService service = new BPelService();


        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            logger.info("-------------stageId----- :" + stageId);
        } else {
            stageId = "sedia_perintah_denda_tamb";
            logger.info("-------------stageId----- :" + stageId);
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            hakmilikPermohonanList = enforceService.findSenaraiMohonHakmilik(idPermohonan);
            logger.info("hakmilikPermohonan size : " + hakmilikPermohonanList.size());

            senaraiTanahMilik = enforceService.findSenaraiTanahMilik(idPermohonan);
            logger.info("size senarai tanah milik : " + senaraiTanahMilik.size());

            if (hakmilikPermohonanList.size() != 0) {
                for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                    if (hakmilikPermohonanList.get(i).getHakmilik() != null) {
                        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                            hakmilikPermohonan = hp;
                            hakmilik = hp.getHakmilik();
                        }
                    }
                }

            }

            if (hakmilikPermohonan != null) {
                if (hakmilikPermohonan.getHakmilik() != null) {
                    hakMilikPihakBerkepentinganList = enforceService.findSenaraiPihak(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    logger.info("-------------hakMilikPihakBerkepentinganList size ----- :" + hakMilikPihakBerkepentinganList.size());

                    senaraiHakMilikPihakBerkepentingan = enforceService.findSenaraiPihakBerkepentingan(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    logger.info("-------------senaraiHakMilikPihakBerkepentingan size ----- :" + senaraiHakMilikPihakBerkepentingan.size());
                }

            }

            if (stageId.equalsIgnoreCase("arah_tindakan3") || stageId.equalsIgnoreCase("kpsn_enkuiri_b7e") || stageId.equalsIgnoreCase("maklum_bayaran_denda") || stageId.equalsIgnoreCase("arah_tindakan")
                    || stageId.equalsIgnoreCase("sedia_perintah_denda") || stageId.equalsIgnoreCase("sahkan_srt_denda") || stageId.equalsIgnoreCase("maklum_byrn_denda") || stageId.equalsIgnoreCase("sedia_laporan2")
                    || stageId.equalsIgnoreCase("arah_pemantauan1") || stageId.equalsIgnoreCase("semak_laporan2")
                    || stageId.equalsIgnoreCase("peraku_perintah_denda") || stageId.equalsIgnoreCase("hantar_perintah_denda") || stageId.equalsIgnoreCase("maklum_bayaran_denda") || stageId.equalsIgnoreCase("arah_tindakan3")) {
                senaraiKompaun = enforceService.findKompaunByKodStatusTerima(idPermohonan, "DD"); //DD = Denda
                logger.info("-------------senaraiKompaun DD ------- : " + senaraiKompaun.size());
            } else if (stageId.equalsIgnoreCase("peraku_srt_remedi") || stageId.equalsIgnoreCase("sedia_perintah_kos_remedi")
                    || stageId.equalsIgnoreCase("kemasukan_byrn_remedi") || stageId.equalsIgnoreCase("maklum_byrn_remedi") || stageId.equalsIgnoreCase("sahkan_srt_remedi")
                    || stageId.equalsIgnoreCase("sedia_surat_kos_remedi") || stageId.equalsIgnoreCase("maklum_bayaran_kos_remedi")) {
                senaraiKompaun = enforceService.findKompaunByKodStatusTerima(idPermohonan, "BR"); //BR = Bayaran Remedi
                logger.info("-------------senaraiKompaun BR ------- : " + senaraiKompaun.size());
            } else {
                hakMilikPihakBerkepentinganList = enforceService.findListPihakAktif(idPermohonan, "DT");
//                logger.info("--------hakMilikPihakBerkepentinganList size --------- : " + hakMilikPihakBerkepentinganList.size());
                senaraiKompaun = enforceService.findKompaunByKodStatusTerima(idPermohonan, "DT"); //DT = Denda Tambahan
                logger.info("-------------senaraiKompaun DT ------- : " + senaraiKompaun.size());

            }

            /* Remarks : stage id for NS
             *         - sedia_perintah_denda
             *         - sahkan_srt_denda
             *         - maklum_byrn_denda
             *         - bukti_penyampaian_denda (updated) 22/5/2012
             *         - maklum_bayaran_denda
             *         - arah_tindakan
             * 
             *           stage id for MLK
             *         - perakuan_srt_denda
             *         - maklum_bayaran_denda
             *         - sedia_perintah_denda (updated) 22/5/2012
             *         - sahkan_srt_denda
             *         - maklum_byrn_denda
             */

            for (int i = 0; i < senaraiKompaun.size(); i++) {
                Kompaun k = senaraiKompaun.get(i);
                if (k.getRayuan() != null && !Character.isWhitespace(k.getRayuan()) && k.getRayuan() == 'Y') {
                    statusAnsuranFlag = true;
                    idKos = k.getPermohonanTuntutanKos().getIdKos();
                }
//                if (StringUtils.isNotBlank(Character.toString(k.getRayuan()))) {
//                    System.out.println("rayuan :" + k.getRayuan());
//                    if (k.getRayuan() == 'Y') {
//                        statusAnsuranFlag = true;
//                        idKos = k.getPermohonanTuntutanKos().getIdKos();
//                    }
//                }

                System.out.println("id kos : " + k.getPermohonanTuntutanKos().getIdKos());
                if (statusAnsuranFlag == false) {
                    if (k.getResit() != null) {
                        if (k.getStatusTerima() != null) {
                            if (!k.getStatusTerima().getKod().equalsIgnoreCase("BR")) {
                                PermohonanTuntutanBayar permohonanTuntutanBayar = enforceService.findMohonTuntutBayar(k.getPermohonanTuntutanKos().getIdKos());
                                if (permohonanTuntutanBayar != null) {
                                    tarikhBayar = permohonanTuntutanBayar.getTarikhBayar();
                                    jumlahDendaTambahan = permohonanTuntutanBayar.getAmaun().toString();
                                }
                            } else {
                                statusBayaranRemediFlag = true;
                                System.out.println("id kew dok BR : " + k.getResit().getIdDokumenKewangan());
                                senaraiPermohonanTuntutanBayar = enforcementService.getSenaraiPtb(k.getPermohonanTuntutanKos().getIdKos(), k.getResit().getIdDokumenKewangan());
                                System.out.println("size senaraiPermohonanTuntutanBayar (BR - remedi biasa):" + senaraiPermohonanTuntutanBayar.size());
                            }
                        }

                    }
                } else {
                    senaraiPermohonanTuntutanBayar = enforcementService.getSenaraiPtb(idKos);
                    System.out.println("size senaraiPermohonanTuntutanBayar :" + senaraiPermohonanTuntutanBayar.size());
                }


                tempohDenda = Integer.toString(k.getTempohHari());
                amaun = k.getAmaun().toString();
                System.out.println("amaun : " + amaun);


            }

            /*get list of denda & denda tambahan with bayaran*/

            senaraiDenda = enforceService.findKompaunByKodStatusTerima(idPermohonan, "DD"); //DD = Denda
            senaraiDendaTambahan = enforceService.findKompaunByKodStatusTerima(idPermohonan, "DT"); //DT = Denda Tambahan

        }
    }

    public Resolution simpanDenda() {
        logger.info("------------simpanDenda--------------");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        InfoAudit ia = new InfoAudit();

        BigDecimal amaunDenda = new BigDecimal(amaun);
        System.out.println("amaunDenda : " + amaunDenda);

        if (!(hakMilikPihakBerkepentinganList.isEmpty())) {
            hakmilikPihakBerkepentingan = hakMilikPihakBerkepentinganList.get(0);

            if (hakmilikPihakBerkepentingan != null) {
                pihak = pihakDAO.findById(hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                logger.info("::: id pihak : " + pihak.getIdPihak());
            }
        }

        try {
            if (senaraiKompaun.size() != 0) {
                for (int i = 0; i < senaraiKompaun.size(); i++) {
                    kompaun = senaraiKompaun.get(i);
                    if (kompaun != null) {
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

            logger.info("------------simpan : PermohonanTuntutKos --------------");

            ptk.setCawangan(pengguna.getKodCawangan());
            ptk.setPermohonan(permohonan);
            ptk.setItem("BAYARAN DENDA");
            ptk.setAmaunTuntutan(amaunDenda);
            KodTransaksi kt = new KodTransaksi();
            //Hafifi 19/2/2014
            if (conf.getProperty("kodNegeri").equalsIgnoreCase("05")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    kt.setKod("20044");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                    kt.setKod("20027");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    kt.setKod("20028");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    kt.setKod("20029");
                }
            } else {
                kt.setKod("20027");
            }

            ptk.setKodTransaksi(kt);
            KodTuntut ktu = new KodTuntut();
            ktu.setKod("K01");
            ptk.setKodTuntut(ktu);
            ptk.setInfoAudit(ia);
            permohonanTuntutanKosDAO.saveOrUpdate(ptk);

            logger.info("------------simpan : Kompaun --------------");

            kompaun.setPermohonan(permohonan);
            kompaun.setCawangan(pengguna.getKodCawangan());
            kompaun.setAmaun(amaunDenda);
            kompaun.setNoRujukan(hakmilikPihakBerkepentingan.getHakmilik().getIdHakmilik());
            kompaun.setIsuKepada(pihak.getNama());
            kompaun.setNoPengenalan(pihak.getNoPengenalan());
            kompaun.setPermohonanTuntutanKos(ptk);
            kompaun.setRujukan1(Long.toString(pihak.getIdPihak()));
            if (stageId.equalsIgnoreCase("kpsn_enkuiri_b7e") || stageId.equalsIgnoreCase("sedia_perintah_denda")) {
                //stage id : bukti_penyampaian_denda for NS. sedia_perintah_denda for MLK
                kompaun.setStatusTerima(kodStatusTerimaDAO.findById("DD")); //DD = Denda
                kompaun.setTempohHari(Integer.parseInt(tempohDenda));
            } else {
                kompaun.setStatusTerima(kodStatusTerimaDAO.findById("DT")); //DT = Denda Tambahan
            }
            kompaun.setInfoAudit(ia);
            kompaunDAO.saveOrUpdate(kompaun);


            if (stageId.equalsIgnoreCase("kpsn_enkuiri_b7e") || stageId.equalsIgnoreCase("sedia_perintah_denda")) {
                Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
                Calendar cal = Calendar.getInstance();
                cal.setTime(c1);
                cal.add(Calendar.DATE, kompaun.getTempohHari());

                String tarikhAkhirBayar = sdf.format(cal.getTime());
                logger.info("tarikh akhir bayar : " + tarikhAkhirBayar);

                pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                pt.setTempoh(Integer.parseInt(tempohDenda));
            }


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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution simpanDendaTanahMilik() {
        logger.info("------------simpanDendaTanahMilik--------------");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        InfoAudit ia = new InfoAudit();

        BigDecimal amaunDenda = new BigDecimal(amaun);
        System.out.println("amaunDenda : " + amaunDenda);

        try {
            if (senaraiKompaun.size() != 0) {
                for (int i = 0; i < senaraiKompaun.size(); i++) {
                    kompaun = senaraiKompaun.get(i);
                    if (kompaun != null) {
                        ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());
                        if (ptk != null) {
                            ia = ptk.getInfoAudit();
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new java.util.Date());
                        }

                        ia = kompaun.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());

                    }

                }

            } else {

                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());

                logger.info("------------simpan : PermohonanTuntutKos --------------");
                /* insert into table PermohonanTuntutKos*/
                ptk = new PermohonanTuntutanKos();

                logger.info("------------simpan : Kompaun --------------");
                /* insert into table Kompaun*/

                kompaun = new Kompaun();
            }

            ptk.setCawangan(pengguna.getKodCawangan());
            ptk.setPermohonan(permohonan);
            ptk.setItem("BAYARAN DENDA");
            ptk.setAmaunTuntutan(amaunDenda);
            KodTransaksi kt = new KodTransaksi();
            //Hafifi 19/2/2014
            if (conf.getProperty("kodNegeri").equalsIgnoreCase("05")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    kt.setKod("20044");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                    kt.setKod("20027");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    kt.setKod("20028");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    kt.setKod("20029");
                }
            } else {
                kt.setKod("20027");
            }
            ptk.setKodTransaksi(kt);
            KodTuntut ktu = new KodTuntut();
            ktu.setKod("K01");
            ptk.setKodTuntut(ktu);
            ptk.setInfoAudit(ia);
            permohonanTuntutanKosDAO.saveOrUpdate(ptk);

            kompaun.setPermohonan(permohonan);
            kompaun.setCawangan(pengguna.getKodCawangan());
            kompaun.setAmaun(amaunDenda);
            kompaun.setNoRujukan("Tiada Data");
            kompaun.setIsuKepada("Tiada Data");
            kompaun.setNoPengenalan("Tiada Data");
            if (tempohDenda != null && !tempohDenda.isEmpty()) {
                kompaun.setTempohHari(Integer.parseInt(tempohDenda));
            }

            kompaun.setPermohonanTuntutanKos(ptk);
            if (stageId.equalsIgnoreCase("sedia_perintah_denda") || stageId.equalsIgnoreCase("perakuan_srt_denda")) {
                //stage id : sedia_perintah_denda for NS. perakuan_srt_denda for MLK
                kompaun.setStatusTerima(kodStatusTerimaDAO.findById("DD")); //DD = Denda
            } else {
                kompaun.setStatusTerima(kodStatusTerimaDAO.findById("DT")); //DT = Denda Tambahan
            }
            kompaun.setInfoAudit(ia);
            kompaunDAO.saveOrUpdate(kompaun);

            //update column no rujukan by set no rujukan = id kompaun

            kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
            kompaunDAO.saveOrUpdate(kompaun);

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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution simpanRemedi() {
        logger.info("------------simpanRemedi--------------");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        InfoAudit ia = new InfoAudit();

        BigDecimal amaunDenda = new BigDecimal(amaun);
        System.out.println("amaunRemedi : " + amaunDenda);

        if (!(hakMilikPihakBerkepentinganList.isEmpty())) {
            hakmilikPihakBerkepentingan = hakMilikPihakBerkepentinganList.get(0);

            if (hakmilikPihakBerkepentingan != null) {
                pihak = pihakDAO.findById(hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                logger.info("::: id pihak : " + pihak.getIdPihak());
            }
        }

        try {
            if (senaraiKompaun.size() != 0) {
                for (int i = 0; i < senaraiKompaun.size(); i++) {
                    kompaun = senaraiKompaun.get(i);
                    if (kompaun != null) {
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

            logger.info("------------simpan : PermohonanTuntutKos --------------");

            ptk.setCawangan(pengguna.getKodCawangan());
            ptk.setPermohonan(permohonan);
            ptk.setItem("BAYARAN REMEDI");
            ptk.setAmaunTuntutan(amaunDenda);
            KodTransaksi kt = new KodTransaksi();
            kt.setKod("79501"); // 79501 = Deposit/Wang Amanah
            ptk.setKodTransaksi(kt);
            KodTuntut ktu = new KodTuntut();
            ktu.setKod("K01");
            ptk.setKodTuntut(ktu);
            ptk.setInfoAudit(ia);
            permohonanTuntutanKosDAO.saveOrUpdate(ptk);

            logger.info("------------simpan : Kompaun --------------");

            kompaun.setPermohonan(permohonan);
            kompaun.setCawangan(pengguna.getKodCawangan());
            kompaun.setAmaun(amaunDenda);
            kompaun.setNoRujukan(hakmilikPihakBerkepentingan.getHakmilik().getIdHakmilik());
            kompaun.setIsuKepada(pihak.getNama());
            kompaun.setNoPengenalan(pihak.getNoPengenalan());
            kompaun.setPermohonanTuntutanKos(ptk);
            kompaun.setStatusTerima(kodStatusTerimaDAO.findById("BR")); //BR = Bayaran Remedi
            kompaun.setTempohHari(Integer.parseInt(tempohDenda));
            kompaun.setInfoAudit(ia);
            kompaun.setRujukan1(Long.toString(pihak.getIdPihak()));
            kompaunDAO.saveOrUpdate(kompaun);


            Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
            Calendar cal = Calendar.getInstance();
            cal.setTime(c1);
            cal.add(Calendar.DATE, kompaun.getTempohHari());

            String tarikhAkhirBayar = sdf.format(cal.getTime());
            logger.info("tarikh akhir bayar : " + tarikhAkhirBayar);

            pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
            pt.setTempoh(Integer.parseInt(tempohDenda));


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
        getContext().getRequest().setAttribute("editRemedi", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution popupTambah() {
        return new JSP("penguatkuasaan/popup_tambah_denda.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {
        logger.info("------------simpan--------------");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        BigDecimal amaunDenda = new BigDecimal(amaun);
        System.out.println("amaunDenda : " + amaunDenda);
        try {
            for (int i = 0; i < hakMilikPihakBerkepentinganList.size(); i++) {
                pilihPihak = getContext().getRequest().getParameter("pilihPihak" + i);
                System.out.println("pilihPihak value : " + pilihPihak);
                if (pilihPihak != null) {
                    logger.info("------------simpan : PermohonanTuntutKos --------------");
                    /* insert into table PermohonanTuntutKos*/
                    ptk = new PermohonanTuntutanKos();
                    ptk.setCawangan(pengguna.getKodCawangan());
                    ptk.setPermohonan(permohonan);
                    ptk.setItem("BAYARAN DENDA");
                    ptk.setAmaunTuntutan(amaunDenda);
                    KodTransaksi kt = new KodTransaksi();
                    //Hafifi 19/2/2014
                    if (conf.getProperty("kodNegeri").equalsIgnoreCase("05")) {
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                            kt.setKod("20044");
                        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                            kt.setKod("20027");
                        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                            kt.setKod("20028");
                        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                            kt.setKod("20029");
                        }
                    } else {
                        kt.setKod("20027");
                    }
                    ptk.setKodTransaksi(kt);
                    KodTuntut ktu = new KodTuntut();
                    ktu.setKod("K01");
                    ptk.setKodTuntut(ktu);
                    ptk.setInfoAudit(ia);
                    permohonanTuntutanKosDAO.save(ptk);

                    logger.info("------------simpan : Kompaun --------------");
                    /* insert into table Kompaun*/
                    pihak = pihakDAO.findById(Long.parseLong(pilihPihak));

                    kompaun = new Kompaun();
                    kompaun.setPermohonan(permohonan);
                    kompaun.setCawangan(pengguna.getKodCawangan());
                    kompaun.setAmaun(amaunDenda);
                    kompaun.setNoRujukan(pilihPihak);
                    kompaun.setIsuKepada(pihak.getNama());
                    kompaun.setNoPengenalan(pihak.getNoPengenalan());
                    kompaun.setTempohHari(Integer.parseInt(tempohDenda));
                    kompaun.setPermohonanTuntutanKos(ptk);
                    if (stageId.equalsIgnoreCase("sedia_perintah_denda") || stageId.equalsIgnoreCase("perakuan_srt_denda")) {
                        //stage id : sedia_perintah_denda for NS. perakuan_srt_denda for MLK
                        kompaun.setStatusTerima(kodStatusTerimaDAO.findById("DD")); //DD = Denda
                    } else {
                        kompaun.setStatusTerima(kodStatusTerimaDAO.findById("DT")); //DT = Denda Tambahan
                    }
                    kompaun.setInfoAudit(ia);
                    kompaunDAO.save(kompaun);

                    logger.info("------------simpan : PermohonanPihak --------------");
                    /* insert into table PermohonanPihak (mohon_pihak)*/
                    KodJenisPihakBerkepentingan kodPihak = kodJenisPihakBerkepentinganDAO.findById("PMB");//sementara

                    mohonPihak = new PermohonanPihak();
                    mohonPihak.setInfoAudit(ia);
                    mohonPihak.setPermohonan(permohonan);
                    mohonPihak.setPihak(pihak);
                    mohonPihak.setJenis(kodPihak);
                    mohonPihak.setHakmilik(hakmilik);
                    mohonPihak.setCawangan(pengguna.getKodCawangan());
                    if (stageId.equalsIgnoreCase("sedia_perintah_denda")) {
                        mohonPihak.setNoRujukan("DD"); //DD = Denda
                    } else {
                        mohonPihak.setNoRujukan("DT"); //DT = Denda Tambahan
                    }

                    permohonanPihakDAO.saveOrUpdate(mohonPihak);

                    //update column no rujukan by set no rujukan = idPermohonanPihak
                    System.out.println("mohonPihak.getIdPermohonanPihak() : " + mohonPihak.getIdPermohonanPihak());
                    kompaun.setNoRujukan(String.valueOf(mohonPihak.getIdPermohonanPihak()));
                    kompaunDAO.saveOrUpdate(kompaun);
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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution refreshPage() {
        logger.debug("----------refreshPage-------------");
        return new RedirectResolution(MaklumatPerintahDendaActionBean.class, "locate");
    }

    public Resolution delete() {
        logger.debug("----------delete kompaun----------");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            idKompaun = getContext().getRequest().getParameter("idKompaun");
            noRujukan = getContext().getRequest().getParameter("noRujukan");
            kompaun = enforcementService.findKompaunByIdKompaun(Long.parseLong(idKompaun));

            ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());
            PermohonanPihak pp = permohonanPihakDAO.findById(Long.parseLong(noRujukan));

            permohonanTuntutanKosDAO.delete(ptk);
            kompaunDAO.delete(kompaun);
            permohonanPihakDAO.delete(pp);

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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatPerintahDendaActionBean.class, "locate");
    }

    public List<HakmilikPihakBerkepentingan> getHakMilikPihakBerkepentinganList() {
        return hakMilikPihakBerkepentinganList;
    }

    public void setHakMilikPihakBerkepentinganList(List<HakmilikPihakBerkepentingan> hakMilikPihakBerkepentinganList) {
        this.hakMilikPihakBerkepentinganList = hakMilikPihakBerkepentinganList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public String getAmaun() {
        return amaun;
    }

    public void setAmaun(String amaun) {
        this.amaun = amaun;
    }

    public String getIdKompaun() {
        return idKompaun;
    }

    public void setIdKompaun(String idKompaun) {
        this.idKompaun = idKompaun;
    }

    public Kompaun getKompaun() {
        return kompaun;
    }

    public void setKompaun(Kompaun kompaun) {
        this.kompaun = kompaun;
    }

    public PermohonanPihak getMohonPihak() {
        return mohonPihak;
    }

    public void setMohonPihak(PermohonanPihak mohonPihak) {
        this.mohonPihak = mohonPihak;
    }

    public String getPilihPihak() {
        return pilihPihak;
    }

    public void setPilihPihak(String pilihPihak) {
        this.pilihPihak = pilihPihak;
    }

    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }

    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }

    public String getTempohDenda() {
        return tempohDenda;
    }

    public void setTempohDenda(String tempohDenda) {
        this.tempohDenda = tempohDenda;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Date getTarikhBayar() {
        return tarikhBayar;
    }

    public void setTarikhBayar(Date tarikhBayar) {
        this.tarikhBayar = tarikhBayar;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<HakmilikPermohonan> getSenaraiTanahMilik() {
        return senaraiTanahMilik;
    }

    public void setSenaraiTanahMilik(List<HakmilikPermohonan> senaraiTanahMilik) {
        this.senaraiTanahMilik = senaraiTanahMilik;
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

    public PermohonanTuntutanKos getPtk() {
        return ptk;
    }

    public void setPtk(PermohonanTuntutanKos ptk) {
        this.ptk = ptk;
    }

    public String getJumlahDendaTambahan() {
        return jumlahDendaTambahan;
    }

    public void setJumlahDendaTambahan(String jumlahDendaTambahan) {
        this.jumlahDendaTambahan = jumlahDendaTambahan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakMilikPihakBerkepentingan() {
        return senaraiHakMilikPihakBerkepentingan;
    }

    public void setSenaraiHakMilikPihakBerkepentingan(List<HakmilikPihakBerkepentingan> senaraiHakMilikPihakBerkepentingan) {
        this.senaraiHakMilikPihakBerkepentingan = senaraiHakMilikPihakBerkepentingan;
    }

    public Date getTarikhBayar1() {
        return tarikhBayar1;
    }

    public void setTarikhBayar1(Date tarikhBayar1) {
        this.tarikhBayar1 = tarikhBayar1;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PermohonanTuntutanBayar getPembayaranDenda() {
        return pembayaranDenda;
    }

    public void setPembayaranDenda(PermohonanTuntutanBayar pembayaranDenda) {
        this.pembayaranDenda = pembayaranDenda;
    }

    public PermohonanTuntutanBayar getPembayaranDendaTambahan() {
        return pembayaranDendaTambahan;
    }

    public void setPembayaranDendaTambahan(PermohonanTuntutanBayar pembayaranDendaTambahan) {
        this.pembayaranDendaTambahan = pembayaranDendaTambahan;
    }

    public List<Kompaun> getSenaraiDenda() {
        return senaraiDenda;
    }

    public void setSenaraiDenda(List<Kompaun> senaraiDenda) {
        this.senaraiDenda = senaraiDenda;
    }

    public List<Kompaun> getSenaraiDendaTambahan() {
        return senaraiDendaTambahan;
    }

    public void setSenaraiDendaTambahan(List<Kompaun> senaraiDendaTambahan) {
        this.senaraiDendaTambahan = senaraiDendaTambahan;
    }

    public Boolean getStatusAnsuranFlag() {
        return statusAnsuranFlag;
    }

    public void setStatusAnsuranFlag(Boolean statusAnsuranFlag) {
        this.statusAnsuranFlag = statusAnsuranFlag;
    }

    public Long getIdKos() {
        return idKos;
    }

    public void setIdKos(Long idKos) {
        this.idKos = idKos;
    }

    public List<PermohonanTuntutanBayar> getSenaraiPermohonanTuntutanBayar() {
        return senaraiPermohonanTuntutanBayar;
    }

    public void setSenaraiPermohonanTuntutanBayar(List<PermohonanTuntutanBayar> senaraiPermohonanTuntutanBayar) {
        this.senaraiPermohonanTuntutanBayar = senaraiPermohonanTuntutanBayar;
    }

    public Boolean getStatusBayaranRemediFlag() {
        return statusBayaranRemediFlag;
    }

    public void setStatusBayaranRemediFlag(Boolean statusBayaranRemediFlag) {
        this.statusBayaranRemediFlag = statusBayaranRemediFlag;
    }
}

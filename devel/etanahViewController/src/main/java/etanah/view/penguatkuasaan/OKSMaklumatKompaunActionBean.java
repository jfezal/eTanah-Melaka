/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.KompaunDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.InfoAudit;
import etanah.model.Kompaun;
import etanah.model.PermohonanTuntutan;
import etanah.service.EnforceService;

import etanah.model.Pengguna;
import java.text.ParseException;
import java.util.Calendar;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
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
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Dokumen;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.Notis;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/oks_maklumat_kompaun")
public class OKSMaklumatKompaunActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(OKSMaklumatKompaunActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
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
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    private etanah.Configuration conf;
    private Pengguna pengguna;
    private String idPermohonan;
    private Permohonan permohonan;
    private List<Kompaun> senaraiKompaun;
    private List<Kompaun> listKompaunOks;
    private Kompaun kompaun;
    private String tempohHari;
    private String pilih;
    private String rowCount;
    private String recordCount;
    private List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak;
    private List<AduanOrangKenaSyak> listOrangKenaSyak;
    private PermohonanTuntutanBayar mohonTuntutBayar;
    private String noRujukan;
    private AduanOrangKenaSyak oks;
    private String orangKenaSyak;
    private String jumlahKompaun;
    private String nama;
    private String noPengenalan;
    private String idKompaun;
    private String amaunKompaunSyor;
    private PermohonanTuntutanKos ptk;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private String muktamadKompaun;
    private String pilihOks;
    private String tempohBayarKompaun;
    private PermohonanTuntutan pt;
    private PermohonanTuntutanButiran ptb;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        logger.info("------------showForm1 : syorKompaun --------------");
        return new JSP("penguatkuasaan/tawaran_kompaun_oks.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        logger.info("------------showForm2 : muktamadKompaun --------------");
        return new JSP("penguatkuasaan/tawaran_kompaun_muktamad_oks.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        logger.info("------------showForm3 : view syor kompaun --------------");
        getContext().getRequest().setAttribute("syorKompaun", Boolean.TRUE);
        return new JSP("penguatkuasaan/view_kompaun_oks.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        logger.info("------------showForm3 : view syor kompaun --------------");
        getContext().getRequest().setAttribute("muktamadKompaun", Boolean.TRUE);
        return new JSP("penguatkuasaan/view_kompaun_oks.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        logger.info("------------showForm5 : muktamad kompaun tanpa syor kompaun sek 427 --------------");
        return new JSP("penguatkuasaan/muktamad_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        logger.info("------------showForm6 : view muktamad kompaun tanpa syor kompaun sek 427 --------------");
        return new JSP("penguatkuasaan/muktamad_kompaun_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //for Melaka, only pass idPermohonan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }

            senaraiAduanOrangKenaSyak = enforceService.findByID(idPermohonan);
            rowCount = String.valueOf(senaraiAduanOrangKenaSyak.size());

            listOrangKenaSyak = enforceService.findByID(idPermohonan);
            recordCount = String.valueOf(listOrangKenaSyak.size());
            logger.info("listOrangKenaSyak : " + listOrangKenaSyak.size());

            senaraiKompaun = enforcementService.findKompaunByID(idPermohonan);
        }
    }

    public Resolution simpan() {
        logger.info("------------simpanSyorKompaunOKS--------------" + senaraiAduanOrangKenaSyak.size());
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            for (int i = 0; i < senaraiAduanOrangKenaSyak.size(); i++) {
                Long idOks = senaraiAduanOrangKenaSyak.get(i).getIdOrangKenaSyak();
                logger.info("idOks : " + idOks);
                pilihOks = getContext().getRequest().getParameter("pilihOks" + i);
                logger.info("pilihOks : " + pilihOks);
                if (pilihOks != null) {
                    amaunKompaunSyor = getContext().getRequest().getParameter("amaunKompaunSyor" + i);
                    System.out.println("amaunKompaunSyor" + amaunKompaunSyor);
                    BigDecimal amaunSyor = new BigDecimal(amaunKompaunSyor);
                    InfoAudit info = new InfoAudit();
                    if (idOks != null && amaunKompaunSyor != null) {

                        oks = aduanOrangKenaSyakDAO.findById(idOks);

                        info = oks.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        oks.setInfoAudit(info);
                        oks.setAmaunKompaunSyor(amaunSyor);
                        enforceService.simpanAduanOrangDisyaki(oks);
                    }
                    kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(idOks);
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
                    kompaun.setNoRujukan("KOMPAUN OKS");
                    kompaun.setOrangKenaSyak(oks);
                    kompaun.setIsuKepada(oks.getNama());
                    kompaun.setNoPengenalan(oks.getNoPengenalan());

                    kompaunDAO.save(kompaun);

                    //update column no rujukan by set no rujukan = id kompaun

                    kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
                    kompaunDAO.saveOrUpdate(kompaun);
                }
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/tawaran_kompaun_oks.jsp").addParameter("tab", "true");


    }

    public Resolution simpanMuktamadKompaun() {
        logger.info("------------simpanMuktamadKompaun--------------");
        idKompaun = getContext().getRequest().getParameter("idKompaun");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();


        InfoAudit ip = new InfoAudit();
        try {

            for (int i = 0; i < senaraiKompaun.size(); i++) {
                Long id = senaraiKompaun.get(i).getIdKompaun();
                kompaun = kompaunDAO.findById(id);

                muktamadKompaun = getContext().getRequest().getParameter("muktamadKompaun" + i);
                tempohHari = getContext().getRequest().getParameter("tempohHari" + i);
                System.out.println("amaunKompaunSyor" + muktamadKompaun);
                BigDecimal amaunMuktamad = new BigDecimal(muktamadKompaun);

                if (kompaun != null) {
                    if (kompaun.getPermohonanTuntutanKos() != null) {
                        ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());

                        ip = ptk.getInfoAudit();
                        ip.setDiKemaskiniOleh(pengguna);
                        ip.setTarikhKemaskini(new java.util.Date());

                        ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());

                        if (ptb != null) {
                            pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                        }


                    } else {
                        ptk = new PermohonanTuntutanKos();
                        ip.setDimasukOleh(pengguna);
                        ip.setTarikhMasuk(new Date());

                        pt = new PermohonanTuntutan();
                        ptb = new PermohonanTuntutanButiran();

                    }

                    /* save table PermohonanTuntutKos*/

                    ptk.setCawangan(pengguna.getKodCawangan());
                    ptk.setPermohonan(permohonan);
                    ptk.setItem("BAYARAN KOMPAUN");
                    ptk.setAmaunTuntutan(BigDecimal.ZERO);
                    KodTransaksi kt = new KodTransaksi();
                    if(conf.getProperty("kodNegeri").equalsIgnoreCase("05")){
                        kt.setKod("76199");
                    }else{
                        kt.setKod("20027");
                    }
                    
                    ptk.setKodTransaksi(kt);
                    KodTuntut ktu = new KodTuntut();
                    ktu.setKod("K01");
                    ptk.setKodTuntut(ktu);
                    ptk.setInfoAudit(ip);
                    ptk.setAmaunTuntutan(amaunMuktamad);
                    permohonanTuntutanKosDAO.saveOrUpdate(ptk);

                    System.out.println("amaun : " + kompaun.getAmaun());
                    InfoAudit ia = new InfoAudit();
                    ia = kompaun.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    kompaun.setInfoAudit(ia);
                    kompaun.setAmaun(amaunMuktamad);
                    kompaun.setTempohHari(Integer.parseInt(tempohHari));
                    kompaun.setPermohonanTuntutanKos(ptk);
                    enforceService.updateKompaun(kompaun);

                    Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(c1);
                    cal.add(Calendar.DATE, kompaun.getTempohHari());

                    String tarikhAkhirBayar = sdf.format(cal.getTime());
                    System.out.println("tarikh akhir bayar : " + tarikhAkhirBayar);

                    KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                    kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");
                    pt.setCawangan(pengguna.getKodCawangan());
                    pt.setPermohonan(permohonan);
                    pt.setInfoAudit(ip);
                    pt.setKodTransaksiTuntut(kodTransTuntut);
                    pt.setTarikhTuntutan(new java.util.Date());
                    pt.setTempoh(Integer.parseInt(tempohHari));
                    pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                    enforceService.simpanPermohonanTuntutan(pt);

                    ptb.setPermohonanTuntutan(pt);
                    ptb.setPermohonanTuntutanKos(ptk);
                    ptb.setInfoAudit(ip);
                    enforceService.simpanPermohonanTuntutanButiran(ptb);
                }
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/tawaran_kompaun_muktamad_oks.jsp").addParameter("tab", "true");
    }

    private void createNotis() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        KodNotis kod = new KodNotis();
        kod = kodNotisDAO.findById("B9");

        KodKlasifikasi kodKlasifikasi = new KodKlasifikasi();
        kodKlasifikasi.setKod(1);

        Dokumen dokumen = new Dokumen();
        KodDokumen dokNotis = new KodDokumen();

        dokNotis.setKod(kod.getKod());
        dokumen.setTajuk(kod.getNama());
        dokumen.setKlasifikasi(kodKlasifikasi);

        dokumen.setNoVersi("1.0");
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new java.util.Date());
        iaPermohonan.setDimasukOleh(pengguna);
        dokumen.setInfoAudit(iaPermohonan);
        dokumenDAO.saveOrUpdate(dokumen);
        
        Notis notis = new Notis();
        notis.setPermohonan(permohonan);
        notis.setDokumenNotis(dokumen);
        notis.setInfoAudit(ia);
        notis.setJabatan(permohonan.getKodUrusan().getJabatan());
        notis.setKodNotis(kod);
        notis.setTarikhNotis(new java.util.Date());
        
        enforcementService.simpanNotisPenguatkuasaan(notis);
    }

    public Resolution refreshpage() {
        logger.debug("refreshpage........");
        return new RedirectResolution(OKSMaklumatKompaunActionBean.class, "locate");
    }

    public Resolution refreshpage2() {
        logger.debug("refreshpage2........");
        return new JSP("penguatkuasaan/tawaran_kompaun_muktamad_oks.jsp").addParameter("tab", "true");
    }

    public Resolution viewMuktamadKompaunDetail() {
        logger.debug("view muktamad kompaun detail");
        idKompaun = getContext().getRequest().getParameter("idKompaun");
        kompaun = kompaunDAO.findById(Long.parseLong(idKompaun));
        idKompaun = Long.toString(kompaun.getIdKompaun());
        return new JSP("penguatkuasaan/popup_view_muktamad_kompaun_oks.jsp").addParameter("popup", "true");
    }

    public Resolution viewMuktamadKompaun() {
        logger.debug("view muktamad kompaun");
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        orangKenaSyak = getContext().getRequest().getParameter("oks");
        listKompaunOks = enforceService.findKompaunByIdMohonOKS(idPermohonan);
        if (listKompaunOks != null) {
            kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        }
        return new JSP("penguatkuasaan/popup_view_muktamad_kompaun_detail_oks.jsp").addParameter("popup", "true");
    }

    public Resolution viewSyorKompaunDetail() {
        logger.debug("view syor kompaun detail");
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        orangKenaSyak = getContext().getRequest().getParameter("oks");
        listKompaunOks = enforceService.findKompaunByIdMohonOKS(idPermohonan);
        if (listKompaunOks != null) {
            kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(Long.parseLong(orangKenaSyak));
        }
        return new JSP("penguatkuasaan/popup_view_syor_kompaun_oks.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSingle() {
        logger.debug("delete kompaun");
        Long idKompaun = Long.parseLong(getContext().getRequest().getParameter("idKompaun"));
        kompaun = enforcementService.findKompaunByIdKompaun(idKompaun);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            PermohonanTuntutanKos ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());
            permohonanTuntutanKosDAO.delete(ptk);
            kompaunDAO.delete(kompaun);

            /*update table barang rampasan. set amounKompaunSyor & kompaun to null*/

//            listItemBarang = enforceService.findBarangRampasanByIdKompaun(idKompaun);
//            for (int i = 0; i < listItemBarang.size(); i++) {
//                BarangRampasan barang = barangRampasanDAO.findById(listItemBarang.get(i).getIdBarang());
//                InfoAudit info = new InfoAudit();
//                info = barang.getInfoAudit();
//                info.setDiKemaskiniOleh(pengguna);
//                info.setTarikhKemaskini(new java.util.Date());
//                barang.setInfoAudit(info);
//                barang.setAmaunKompaunSyor(null);
//                barang.setAmaunKompaun(null);
//                barang.setKompaun(null);
//                enforceService.updateBarangRampasan(barang);
//            }


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
        return new RedirectResolution(OKSMaklumatKompaunActionBean.class, "locate");
    }

    public Resolution simpanMuktamadKompaunOKS() throws ParseException {
        logger.info("------------simpanMuktamadKompaunOKS--------------size oks : " + senaraiAduanOrangKenaSyak.size());
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
            for (int i = 0; i < senaraiAduanOrangKenaSyak.size(); i++) {
                Long idOks = senaraiAduanOrangKenaSyak.get(i).getIdOrangKenaSyak();
                System.out.println("idOks 111" + idOks);
                muktamadKompaun = getContext().getRequest().getParameter("muktamadKompaun" + i);
                tempohBayarKompaun = getContext().getRequest().getParameter("tempohBayarKompaun" + i);
                System.out.println("tempoh bayar kompaun:" + tempohBayarKompaun);
                System.out.println("muktamadKompaun" + muktamadKompaun);
                BigDecimal amaunMuktamad = new BigDecimal(muktamadKompaun);
                System.out.println("idOks 222: " + idOks);
                InfoAudit info = new InfoAudit();
                if (idOks != null && muktamadKompaun != null) {

                    oks = aduanOrangKenaSyakDAO.findById(idOks);

                    info = oks.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    oks.setInfoAudit(info);
                    oks.setAmaunKompaunSyor(amaunMuktamad);
                    oks.setTempohBayarKompaun(Integer.parseInt(tempohBayarKompaun));
                    enforceService.simpanAduanOrangDisyaki(oks);
                }
                kompaun = enforcementService.findKompaunByIdaduanOrangKenaSyak(idOks);

                if (kompaun == null) {
                    //save to mohon_tuntut_kos : parent table
                    ptk = new PermohonanTuntutanKos();

                    pt = new PermohonanTuntutan();
                    ptb = new PermohonanTuntutanButiran();

                    kompaun = new Kompaun();

                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());

                } else {

                    if (kompaun.getPermohonanTuntutanKos() != null) {
                        ptk = permohonanTuntutanKosDAO.findById(kompaun.getPermohonanTuntutanKos().getIdKos());
                        System.out.println("mohon tuntut kos : " + ptk);

                        ptb = enforceService.findPermohonanTuntutanButiran(ptk.getIdKos());

                        if (ptb != null) {
                            pt = permohonanTuntutanDAO.findById(ptb.getPermohonanTuntutan().getIdTuntut());
                        }
                    }
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());

                }

                ptk.setCawangan(pengguna.getKodCawangan());
                ptk.setPermohonan(permohonan);
                ptk.setItem("BAYARAN KOMPAUN");
                ptk.setAmaunTuntutan(amaunMuktamad);
                KodTransaksi kt = new KodTransaksi();
                if(conf.getProperty("kodNegeri").equalsIgnoreCase("05")){
                        kt.setKod("76199");
                    }else{
                        kt.setKod("20027");
                    }
                ptk.setKodTransaksi(kt);
                KodTuntut ktu = new KodTuntut();
                ktu.setKod("K01");
                ptk.setKodTuntut(ktu);
                ptk.setInfoAudit(info);
                permohonanTuntutanKosDAO.save(ptk);

                kompaun.setInfoAudit(info);
                kompaun.setPermohonan(permohonan);
                kompaun.setCawangan(pengguna.getKodCawangan());
                kompaun.setAmaun(BigDecimal.ZERO);
                kompaun.setNoRujukan("KOMPAUN OKS");
                kompaun.setAmaun(amaunMuktamad);
                kompaun.setOrangKenaSyak(oks);
                kompaun.setIsuKepada(oks.getNama());
                kompaun.setNoPengenalan(oks.getNoPengenalan());
                kompaun.setPermohonanTuntutanKos(ptk);
                kompaun.setTempohHari(oks.getTempohBayarKompaun());

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

                pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                pt.setTempoh(Integer.parseInt(tempohBayarKompaun));

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
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/muktamad_kompaun.jsp").addParameter("tab", "true");
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

    public String getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(String tempohHari) {
        this.tempohHari = tempohHari;
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

    public List<AduanOrangKenaSyak> getListOrangKenaSyak() {
        return listOrangKenaSyak;
    }

    public void setListOrangKenaSyak(List<AduanOrangKenaSyak> listOrangKenaSyak) {
        this.listOrangKenaSyak = listOrangKenaSyak;
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

    public List<Kompaun> getListKompaunOks() {
        return listKompaunOks;
    }

    public void setListKompaunOks(List<Kompaun> listKompaunOks) {
        this.listKompaunOks = listKompaunOks;
    }

    public String getAmaunKompaunSyor() {
        return amaunKompaunSyor;
    }

    public void setAmaunKompaunSyor(String amaunKompaunSyor) {
        this.amaunKompaunSyor = amaunKompaunSyor;
    }

    public String getIdKompaun() {
        return idKompaun;
    }

    public void setIdKompaun(String idKompaun) {
        this.idKompaun = idKompaun;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public String getMuktamadKompaun() {
        return muktamadKompaun;
    }

    public void setMuktamadKompaun(String muktamadKompaun) {
        this.muktamadKompaun = muktamadKompaun;
    }

    public String getPilihOks() {
        return pilihOks;
    }

    public void setPilihOks(String pilihOks) {
        this.pilihOks = pilihOks;
    }

    public String getTempohBayarKompaun() {
        return tempohBayarKompaun;
    }

    public void setTempohBayarKompaun(String tempohBayarKompaun) {
        this.tempohBayarKompaun = tempohBayarKompaun;
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
}

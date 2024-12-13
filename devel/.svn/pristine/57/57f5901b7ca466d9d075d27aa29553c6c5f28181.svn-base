/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKegunaanRuangUdaraDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPermitButirDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodKadarBayaran;
import etanah.model.KodKegunaanRuangUdara;
import etanah.model.KodTransaksi;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPermitButir;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.StrataPtService;
import etanah.service.common.PermohonanService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.lang.Long;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.Comparator;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author User
 */
@UrlBinding("/strata/permit_RuangUdara")
public class PermitRuangUdaraActionBean extends BasicTabActionBean {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PermohonanPermitButirDAO permohonanPermitButirDAO;
    @Inject
    KodKegunaanRuangUdaraDAO kodKegunaanRuangUdaraDAO;
    private List<PermohonanTuntutanKos> senaraiMohonTuntutanKos;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private Permohonan permohonan;
    InfoAudit infoAudit = new InfoAudit();
    private PermohonanPermitButir permohanPermit;
    private String noBlok;
    private String noTingkat;
    private String noPetak;
    private String jenisStrukTambah;
    private Integer bilStrukTambah;
    private String dibinaOleh;
    private String kedudukanStrukTambah;
    private Integer bilPermit;
    private String noLot;
    private List<PermohonanPermitButir> permohanPermitList;
    private static final Logger LOG = Logger.getLogger(PermitRuangUdaraActionBean.class);
    private BigDecimal jumlahBayaran;
    int p = 0;
    private KodCawangan kodCawangan;
    private Pengguna pengguna;
    private String idPermit;
    private List<KodKegunaanRuangUdara> senaraikodGunaRuangUdara;
    private Pemohon pemohan;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "g_sedialaporan");
        if(mohonFasa.getKeputusan() != null && mohonFasa.getKeputusan().getKod().equals("DI")){
            getContext().getRequest().setAttribute("DI", Boolean.TRUE);
        }else{
            getContext().getRequest().setAttribute("DI", Boolean.FALSE);
        }
        return new JSP("strata/Ruang_Udara/permit_RuangUdara.jsp").addParameter("tab", "true");
    }

    public Resolution showEdit() {
        LOG.info("showEditkod===");
        // getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        idPermit = getContext().getRequest().getParameter("id");
//         long id = 2l;
        LOG.info("=====id" + idPermit);
        permohanPermit = permohonanPermitButirDAO.findById(Long.parseLong(idPermit));
        if (permohanPermit != null) {
            noBlok = permohanPermit.getNoBlok();
            noTingkat = permohanPermit.getNoTingkat();
            noPetak = permohanPermit.getNoPetak();
            dibinaOleh = permohanPermit.getDibinaOleh();
            bilStrukTambah = permohanPermit.getBilStrukTambah();
            jenisStrukTambah = permohanPermit.getJenisStrukTambah();
            bilPermit = permohanPermit.getBilPermit();
        }
        return new JSP("strata/Ruang_Udara/kemaskini_PermitRuangUdara.jsp").addParameter("popup", "true");
    }

    public Resolution deletePermit() {
        LOG.info("Deletekod===");
        // getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        String id = getContext().getRequest().getParameter("id");
//         long id = 2l;
        LOG.info("=====id" + id);
        permohanPermit = permohonanPermitButirDAO.findById(Long.parseLong(id));
        permohonanPermitButirDAO.delete(permohanPermit);
        strService.deletePermit(permohanPermit);
        rehydrate();
        return new JSP("strata/Ruang_Udara/permit_RuangUdara.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPermitRuang() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String error = "";
        String msg = "";
        permohonan = permohonanService.findById(idPermohonan);
        PermohonanPermitButir permohanPermitTemp = new PermohonanPermitButir();
        long id_mh = permohonan.getSenaraiHakmilik().get(0).getId();
        LOG.info("id_mh : " + id_mh);
        String noblok = permohanPermit.getNoBlok();
        String notingkat = permohanPermit.getNoTingkat();
        String nopetak = permohanPermit.getNoPetak();
        String jenisStruk = permohanPermit.getJenisStrukTambah();
        LOG.info(":: noBlok = " + noblok + " noTingkat = " + notingkat + " noPetak = " + nopetak + " Jenis Struk = " + jenisStruk);

        if (noblok != null) {

            PermohonanPermitButir permitbutir = strService.findPermitButirByBlok(id_mh, noblok, notingkat, nopetak, jenisStruk);

            //check if no_blok, no_tingkat, no_petak and jenis_struk_tambah already exist
            if (permitbutir != null) {
                error = "Maklumat Jenis struktur tambahan : " + jenisStruk + " bagi Blok " + noblok + " Tingkat " + notingkat + " Petak " + nopetak + " telah wujud.";
                LOG.info(error);
                addSimpleError(error);
            } else {

                if (permohanPermit.getIdMpermitBtr() == null) {
                    System.out.println("-----permohanPermit.getIdMpermitBtr()--if----" + permohanPermit.getIdMpermitBtr());
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    permohanPermitTemp.setInfoAudit(infoAudit);
                    HakmilikPermohonan hp1 = permohonan.getSenaraiHakmilik().get(0);
                    LOG.info("HakmilikPermohonan : " + hp1.getId());
//            hp1.getHakmilik().setNoLot(permohanPermit.getHakmilikPermohonan().getHakmilik().getNoLot());
                    permohanPermitTemp.setHakmilikPermohonan(hp1);
//            permohanPermitTemp.getHakmilikPermohonan().getHakmilik().setNoLot(permohanPermit.getHakmilikPermohonan().getHakmilik().getNoLot());
                    permohanPermitTemp.setNoBlok(permohanPermit.getNoBlok());
                    permohanPermitTemp.setNoTingkat(permohanPermit.getNoTingkat());
                    if (permohanPermit.getJenisStrukTambah().equals("Bumbung")) {
                        LOG.info("----IF : jenis Struktur Tambah = Bumkbung----");
                        permohanPermitTemp.setNoPetak("-");
                    } else {
                        LOG.info("---ELSE : jenis Struktur Tambah not equal to Bumbung---");
                        permohanPermitTemp.setNoPetak(permohanPermit.getNoPetak());
                    }
                    permohanPermitTemp.setJenisStrukTambah(permohanPermit.getJenisStrukTambah());
                    permohanPermitTemp.setBilStrukTambah(permohanPermit.getBilStrukTambah());
                    permohanPermitTemp.setDibinaOleh(permohanPermit.getDibinaOleh());
                    permohanPermitTemp.setKedudukanStrukTambah(kedudukanStrukTambah);
                    permohanPermitTemp.setBilPermit(permohanPermit.getBilPermit());
                    strService.SimpanPermohonanPermitButir(permohanPermitTemp);

//        if ((simpanjln != null) && (simpanlaluan != null) && (!simpanjln.equals("")) && (!simpanlaluan.equals(""))) {
//            laporanTanah.setStrukturTambahanKedudukanTerlunjur("Simpanan jalan dan simpanan laluan");
//
//        } else if ((simpanjln != null) && (!simpanjln.equals(""))) {
//            laporanTanah.setStrukturTambahanKedudukanTerlunjur(simpanjln);
//        } else if ((simpanlaluan != null) && (!simpanlaluan.equals(""))) {
//            laporanTanah.setStrukturTambahanKedudukanTerlunjur(simpanlaluan);
//
//        }

                } else {

//           permohanPermitTemp = permohonanPermitButirDAO.findById(permohanPermit.getIdMpermitBtr());
                    LOG.info("-----permohanPermit.getIdMpermitBtr()--else----" + permohanPermitTemp.getIdMpermitBtr());
                    infoAudit = permohanPermitTemp.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    permohanPermitTemp.setInfoAudit(infoAudit);

                    HakmilikPermohonan hp1 = permohonan.getSenaraiHakmilik().get(0);
                    hp1.getHakmilik().setNoLot(permohanPermit.getHakmilikPermohonan().getHakmilik().getNoLot());
                    permohanPermitTemp.setHakmilikPermohonan(hp1);
                    permohanPermitTemp.getHakmilikPermohonan().getHakmilik().setNoLot(permohanPermit.getHakmilikPermohonan().getHakmilik().getNoLot());
                    permohanPermitTemp.setNoBlok(permohanPermit.getNoBlok());
                    permohanPermitTemp.setNoTingkat(permohanPermit.getNoTingkat());
                    permohanPermitTemp.setNoPetak(permohanPermit.getNoPetak());
                    permohanPermitTemp.setBilPermit(permohanPermit.getBilPermit());

                    strService.SimpanPermohonanPermitButir(permohanPermitTemp);
                }


                System.out.println("--------permohanPermit.getNoTingkat()-------" + permohanPermit.getNoTingkat());
                HakmilikPermohonan hp = new HakmilikPermohonan();
                if ((permohonan != null) && (!permohonan.getSenaraiHakmilik().isEmpty())) {
                    hp = permohonan.getSenaraiHakmilik().get(0);
                }
                permohanPermitList = strService.getSumOfPermitButirByIdHakmilikPermohonan(hp.getId());

                Comparator c = new Comparator<PermohonanPermitButir>() {
                    @Override
                    public int compare(PermohonanPermitButir ppb1, PermohonanPermitButir ppb2) {

                        String namaBlok1 = ppb1.getNoBlok();
                        String namaBlok2 = ppb2.getNoBlok();
                        return Integer.parseInt(namaBlok1)
                                - Integer.parseInt(namaBlok2);
                    }
                };

                Collections.sort(permohanPermitList, c);

                if ((permohonan != null) && (!permohonan.getSenaraiHakmilik().isEmpty())) {
                    hp = permohonan.getSenaraiHakmilik().get(0);
                }
                p = 0;
                for (PermohonanPermitButir ppb : permohanPermitList) {
                    p = p + ppb.getBilPermit();
                }
                KodKadarBayaran kkb;
                kkb = strService.findkodKadarBayar(permohonan.getKodUrusan().getKod());
                Double d = Double.parseDouble(String.valueOf(p));
                BigDecimal b = new BigDecimal(d);
                jumlahBayaran = (kkb.getKadar()).multiply(b).multiply(new BigDecimal(21));
                LOG.info("--------IN SAVING : PermohonanTuntutKos-------");
                simpanMohonTuntutKos(jumlahBayaran);
                permohanPermit = new PermohonanPermitButir();
                addSimpleMessage("Maklumat berjaya Disimpan");
            }
        } else {
            error = "Nombor Blok tidak wujud";
            addSimpleError(error);
            LOG.info("noBlok is null!!");

        }
        return new JSP("strata/Ruang_Udara/permit_RuangUdara.jsp").addParameter("tab", "true");
//        return new RedirectResolution(PermitRuangUdaraActionBean.class, "showForm").addParameter("error", error);
    }

    public Resolution updatePermit() {
        LOG.info("showEditkod===");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanService.findById(idPermohonan);
        LOG.info("=====id :" + idPermit);
        PermohonanPermitButir permohonanPermit = permohonanPermitButirDAO.findById(Long.parseLong(idPermit));
        if (permohonanPermit != null) {
            LOG.info("===permohonanPermit not null===");
            infoAudit = permohonanPermit.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            permohonanPermit.setInfoAudit(infoAudit);

            HakmilikPermohonan hp1 = permohonan.getSenaraiHakmilik().get(0);
            hp1.getHakmilik().setNoLot(permohonanPermit.getHakmilikPermohonan().getHakmilik().getNoLot());
            permohonanPermit.setHakmilikPermohonan(hp1);
            permohonanPermit.getHakmilikPermohonan().getHakmilik().setNoLot(permohonanPermit.getHakmilikPermohonan().getHakmilik().getNoLot());
            permohonanPermit.setJenisStrukTambah(jenisStrukTambah);
            if (jenisStrukTambah.equals("Bumbung")) {
                LOG.info("----IF : jenis Struktur Tambah = Bumkbung----");
                permohonanPermit.setNoPetak("Bumbung");
            } else {
                LOG.info("---ELSE : jenis Struktur Tambah not equal to Bumbung---");
                permohonanPermit.setNoPetak(noPetak);
            }
            permohonanPermit.setNoBlok(noBlok);
            permohonanPermit.setNoTingkat(noTingkat);
            permohonanPermit.setBilPermit(bilPermit);
            permohonanPermit.setDibinaOleh(dibinaOleh);
            permohonanPermit.setBilStrukTambah(bilStrukTambah);
            LOG.info("---IN UPDATE PERMOHONANPERMITBUTIR---");
            strService.SimpanPermohonanPermitButir(permohonanPermit);           
            UpdateMohonTuntutKos();
            rehydrate();
            addSimpleMessage("Kemaskini Permit Ruang Udara Telah Berjaya.");
        }
        return new JSP("strata/Ruang_Udara/permit_RuangUdara.jsp").addParameter("tab", "true");
    }

    private void simpanMohonTuntutKos(BigDecimal jumlahBayaran) {
        LOG.info("Jumlah Bayaran : " + jumlahBayaran);
        LOG.info("Permohonan : " + permohonan.getIdPermohonan());
        permohonanTuntutanKos = strService.findMohontuntutkos(permohonan.getIdPermohonan());



        if (permohonanTuntutanKos != null) {
            LOG.info("Find Permohonan Tuntut Kos : " + String.valueOf(permohonanTuntutanKos.getIdKos()) + " " + permohonanTuntutanKos.getItem());
            infoAudit.setTarikhKemaskini(new Date());
            infoAudit.setTarikhMasuk(permohonanTuntutanKos.getInfoAudit().getTarikhMasuk());
            infoAudit.setDimasukOleh(permohonanTuntutanKos.getInfoAudit().getDimasukOleh());
            infoAudit.setDiKemaskiniOleh(pengguna);
            LOG.info("tarikh kemaskini : " + infoAudit.getTarikhKemaskini().toString());
            LOG.info("tarikh masuk : " + infoAudit.getTarikhMasuk().toString());
        } else {
            permohonanTuntutanKos = new PermohonanTuntutanKos();
            infoAudit.setTarikhMasuk(new Date());
            infoAudit.setDimasukOleh(pengguna);
            LOG.info("tarikh masuk : " + infoAudit.getTarikhMasuk().toString());
        }
        permohonanTuntutanKos.setPermohonan(permohonan);
        permohonanTuntutanKos.setItem("Bayaran Permit ruang udara");
        permohonanTuntutanKos.setAmaunTuntutan(jumlahBayaran);
        permohonanTuntutanKos.setInfoAudit(infoAudit);
        permohonanTuntutanKos.setCatatan(noPetak);
        permohonanTuntutanKos.setCawangan(strService.getkodCawangan(pengguna.getKodCawangan().getKod()));
        permohonanTuntutanKos.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
        permohonanTuntutanKos.setKodTuntut(null);
        strService.simpanBayaran(permohonanTuntutanKos);        
        LOG.info("::Simpan Mohon Tuntut Kos Berjaya::");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("----Rehydrate()----:");
        permohanPermitList = new ArrayList<PermohonanPermitButir>();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraikodGunaRuangUdara = kodKegunaanRuangUdaraDAO.findAll();
        HakmilikPermohonan hp = new HakmilikPermohonan();
        if ((permohonan != null) && (!permohonan.getSenaraiHakmilik().isEmpty())) {
            hp = permohonan.getSenaraiHakmilik().get(0);
            LOG.info("----hp----:" + hp);
            LOG.info("----hp.getId()----:" + hp.getId());
        }
        permohanPermitList = strService.getSumOfPermitButirByIdHakmilikPermohonan(hp.getId());
        LOG.info("----permohanPermitList----:" + permohanPermitList.size());

        pemohan = strService.findById(idPermohonan);

        Comparator c = new Comparator<PermohonanPermitButir>() {
            @Override
            public int compare(PermohonanPermitButir ppb1, PermohonanPermitButir ppb2) {

                String namaBlok1 = ppb1.getNoBlok();
                String namaBlok2 = ppb2.getNoBlok();
                return Integer.parseInt(namaBlok1)
                        - Integer.parseInt(namaBlok2);
            }
        };

        Collections.sort(permohanPermitList, c);

        p = 0;
        for (PermohonanPermitButir ppb : permohanPermitList) {
            if (ppb.getBilPermit() != null) {
                p = p + ppb.getBilPermit();
            }
        }
        KodKadarBayaran kkb;
        kkb = strService.findkodKadarBayar(permohonan.getKodUrusan().getKod());
        if (kkb != null) {
            LOG.info("KOD KADAR BAYAR : " + kkb.getKod());
            Double d = Double.parseDouble(String.valueOf(p));
            BigDecimal b = new BigDecimal(d);

            jumlahBayaran = (kkb.getKadar()).multiply(b).multiply(new BigDecimal(21));
        } else {
            jumlahBayaran = new BigDecimal(0);
        }

        kedudukanStrukTambah = "Simpanan Jalan";
        
        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "g_sedialaporan");
        if(mohonFasa.getKeputusan() != null && mohonFasa.getKeputusan().getKod().equals("DI")){
            getContext().getRequest().setAttribute("DI", Boolean.TRUE);
        }else{
            getContext().getRequest().setAttribute("DI", Boolean.FALSE);
        }
    }

    public void UpdateMohonTuntutKos() {       
        HakmilikPermohonan hp = new HakmilikPermohonan();
        if ((permohonan != null) && (!permohonan.getSenaraiHakmilik().isEmpty())) {
            hp = permohonan.getSenaraiHakmilik().get(0);
        }
        permohanPermitList = strService.getSumOfPermitButirByIdHakmilikPermohonan(hp.getId());

        Comparator c = new Comparator<PermohonanPermitButir>() {
            @Override
            public int compare(PermohonanPermitButir ppb1, PermohonanPermitButir ppb2) {

                String namaBlok1 = ppb1.getNoBlok();
                String namaBlok2 = ppb2.getNoBlok();
                return Integer.parseInt(namaBlok1)
                        - Integer.parseInt(namaBlok2);
            }
        };

        Collections.sort(permohanPermitList, c);

        if ((permohonan != null) && (!permohonan.getSenaraiHakmilik().isEmpty())) {
            hp = permohonan.getSenaraiHakmilik().get(0);
        }
        p = 0;
        for (PermohonanPermitButir ppb : permohanPermitList) {
            p = p + ppb.getBilPermit();
        }
        KodKadarBayaran kkb;
        kkb = strService.findkodKadarBayar(permohonan.getKodUrusan().getKod());
        Double d = Double.parseDouble(String.valueOf(p));
        BigDecimal b = new BigDecimal(d);
        jumlahBayaran = (kkb.getKadar()).multiply(b).multiply(new BigDecimal(21));
        simpanMohonTuntutKos(jumlahBayaran);       
    }

    public List<PermohonanPermitButir> getPermohanPermitList() {
        return permohanPermitList;
    }

    public void setPermohanPermitList(List<PermohonanPermitButir> permohanPermitList) {
        this.permohanPermitList = permohanPermitList;
    }

    public BigDecimal getJumlahBayaran() {
        return jumlahBayaran;
    }

    public void setJumlahBayaran(BigDecimal jumlahBayaran) {
        this.jumlahBayaran = jumlahBayaran;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public Integer getBilPermit() {
        return bilPermit;
    }

    public void setBilPermit(Integer bilPermit) {
        this.bilPermit = bilPermit;
    }

    public Integer getBilStrukTambah() {
        return bilStrukTambah;
    }

    public void setBilStrukTambah(Integer bilStrukTambah) {
        this.bilStrukTambah = bilStrukTambah;
    }

    public String getDibinaOleh() {
        return dibinaOleh;
    }

    public void setDibinaOleh(String dibinaOleh) {
        this.dibinaOleh = dibinaOleh;
    }

    public String getJenisStrukTambah() {
        return jenisStrukTambah;
    }

    public void setJenisStrukTambah(String jenisStrukTambah) {
        this.jenisStrukTambah = jenisStrukTambah;
    }

    public String getKedudukanStrukTambah() {
        return kedudukanStrukTambah;
    }

    public void setKedudukanStrukTambah(String kedudukanStrukTambah) {
        this.kedudukanStrukTambah = kedudukanStrukTambah;
    }

    public String getNoBlok() {
        return noBlok;
    }

    public void setNoBlok(String noBlok) {
        this.noBlok = noBlok;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoPetak() {
        return noPetak;
    }

    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public String getNoTingkat() {
        return noTingkat;
    }

    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    public PermohonanPermitButir getPermohanPermit() {
        return permohanPermit;
    }

    public void setPermohanPermit(PermohonanPermitButir permohanPermit) {
        this.permohanPermit = permohanPermit;
    }

    public List<KodKegunaanRuangUdara> getSenaraikodGunaRuangUdara() {
        return senaraikodGunaRuangUdara;
    }

    public void setSenaraikodGunaRuangUdara(List<KodKegunaanRuangUdara> senaraikodGunaRuangUdara) {
        this.senaraikodGunaRuangUdara = senaraikodGunaRuangUdara;
    }

    public String getIdPermit() {
        return idPermit;
    }

    public void setIdPermit(String idPermit) {
        this.idPermit = idPermit;
    }

    public Pemohon getPemohan() {
        return pemohan;
    }

    public void setPemohan(Pemohon pemohan) {
        this.pemohan = pemohan;
    }
}

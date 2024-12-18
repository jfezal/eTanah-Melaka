/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import net.sourceforge.stripes.action.ForwardResolution;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
//import com.thoughtworks.xstream.converters.Converter;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PermohonanDAO;
//import etanah.dao.HakmilikPihakBerkepentingan;
import etanah.dao.PenggunaDAO;
import etanah.model.*;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.util.List;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.KodCawangan;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.PelupusanService;
import java.math.BigDecimal;
import etanah.service.common.PengambilanDepositService;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/depositMelaka")
public class DepositMelakaActionBean extends AbleActionBean {

    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PelupusanService plpservice;
    @Inject
    LaporanPelukisPelanService serviceMohonTuntut;
    @Inject
    PengambilanDepositService service;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    private static final Logger LOG = Logger.getLogger(DepositMelakaActionBean.class);
    PermohonanTuntutanKos mohontuntut;
    private String idPermohonan;
    private String idHakmilik;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private List<PermohonanTuntutanKos> mohonTuntutList;
    private List<HakmilikPermohonan> hp;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPermohonan hakMohon;
    private LaporanPelukisPelanService serviceMohon;
    private String nilaiTanah;
    private BigDecimal nilaitanah;
    private BigDecimal nilaiT;
    private BigDecimal nilaiBangunan;
    private BigDecimal nilaiPecah;
    private BigDecimal nilaiMudarat;
    private BigDecimal nilaiDll;
    private BigDecimal amaun50;
    private BigDecimal amaun75;
    private BigDecimal amaun;
    private BigDecimal a1;
    private BigDecimal a2;
    private BigDecimal kira1;
    private BigDecimal kira2;
    private BigDecimal baki;
    List<PermohonanTuntutanKos> senaraiDeposit;
    private String kod = "58007";
    List<CaraBayaran> senaraiCaraPembayaran;
    private List<CaraBayaran> caraBayaranList;
    private List<DokumenKewanganBayaran> dokumenKewanganBayaranList;
    private List<Transaksi> transaksiList;
    private String totalNilaian;
    private String totalTanah;
    private String totalBangunan;
    private String totalPecah;
    private String totalMudarat;
    private String totalDll;
    private String totalDeposit;
    private String catatan;
    private BigDecimal sumNilaian = new BigDecimal(0.0000);
    private BigDecimal sumT = new BigDecimal(0.0000);
    private BigDecimal sumBangunan = new BigDecimal(0.0000);
    private BigDecimal sumPecah = new BigDecimal(0.0000);
    private BigDecimal sumMudarat = new BigDecimal(0.0000);
    private BigDecimal sumDll = new BigDecimal(0.0000);
    private BigDecimal sumDeposit = new BigDecimal(0.0000);
    private DecimalFormat df = new DecimalFormat("#,##0.00");
    private List<String> nilaijpph = new ArrayList<String>();
    private FasaPermohonan fp;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/AkuanDeposit_Melaka.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = permohonan.getCawangan();
        LOG.info("id hakmilik : " + idHakmilik);
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            fp = plpservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, "B13BayarDeposit");
            caraBayaranList = new ArrayList<CaraBayaran>();
            dokumenKewanganBayaranList = new ArrayList<DokumenKewanganBayaran>();
            transaksiList = new ArrayList<Transaksi>();
            senaraiDeposit = service.findByIDMohon(idPermohonan);
            List<HakmilikPermohonan> hpList = permohonan.getSenaraiHakmilik();

            for (HakmilikPermohonan hMohon : hpList) {
                List<PermohonanTuntutanKos> tuntutKosList = service.findByIDMohonTuntutIDMHList(idPermohonan, hMohon.getId());
                if (tuntutKosList.isEmpty()) {
                    PermohonanTuntutanKos tuntutKos = new PermohonanTuntutanKos();
                    InfoAudit info = peng.getInfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    tuntutKos.setInfoAudit(info);
                    tuntutKos.setPermohonan(p);
                    tuntutKos.setCawangan(cawangan);
                    tuntutKos.setHakmilikPermohonan(hMohon);
                    tuntutKos.setAmaunTuntutan(BigDecimal.ZERO);
                    tuntutKos.setItem("Deposit 125%");
                    KodTransaksi kodTransaksi = kodTransaksiDAO.findById(kod);
                    tuntutKos.setKodTransaksi(kodTransaksi);
                    plpservice.simpanPermohonanTuntutanKos(tuntutKos);
                }
            }

            for (int i = 0; i < senaraiDeposit.size(); i++) {
                if (senaraiDeposit.get(i).getTanah() != null) {
                    sumT = sumT.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getTanah()))));
                }
                if (senaraiDeposit.get(i).getBangunan() != null) {
                    sumBangunan = sumBangunan.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getBangunan()))));
                }
                if (senaraiDeposit.get(i).getPecahPisah() != null) {
                    sumPecah = sumPecah.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getPecahPisah()))));
                }
                if (senaraiDeposit.get(i).getMudarat() != null) {
                    sumMudarat = sumMudarat.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getMudarat()))));
                }
                if (senaraiDeposit.get(i).getLainLain() != null) {
                    sumDll = sumDll.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getLainLain()))));
                }
                if (senaraiDeposit.get(i).getCatatan() != null) {
                    catatan = senaraiDeposit.get(i).getCatatan();
                }
            }
            totalTanah = String.valueOf(sumT);
            totalBangunan = String.valueOf(sumBangunan);
            totalPecah = String.valueOf(sumPecah);
            totalMudarat = String.valueOf(sumMudarat);
            totalDll = String.valueOf(sumDll);

            for (int i = 0; i < senaraiDeposit.size(); i++) {
                if (senaraiDeposit.get(i).getAmaunSebenar() != null) {
                    sumNilaian = sumNilaian.add(senaraiDeposit.get(i).getAmaunSebenar());
                }
                sumDeposit = sumDeposit.add(senaraiDeposit.get(i).getAmaunTuntutan());
            }
//            df.format(sumDeposit);
            totalNilaian = String.valueOf(sumNilaian);
            totalDeposit = String.valueOf(sumDeposit);

            for (int i = 0; i < senaraiDeposit.size(); i++) {
                nilaitanah = senaraiDeposit.get(i).getAmaunSebenar();
                LOG.info("nilaitanah : " + nilaitanah);
            }

            hp = new ArrayList<HakmilikPermohonan>();
            hp = plpservice.getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
            hakmilikPermohonan = plpservice.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

            int year = Calendar.getInstance().get(Calendar.YEAR);
            for (PermohonanTuntutanKos mt : senaraiDeposit) {
                LOG.info("mt.getIdKos : " + mt.getIdKos());
//                CaraBayaran caraBayaran = service.findCaraBayarbyIdKos(mt.getIdKos());
                caraBayaranList = service.findCaraBayarbyIdCaraBayar(idPermohonan, year);
//                caraBayaranList.add(caraBayaran);
                DokumenKewanganBayaran dokumenKewanganBayaran = new DokumenKewanganBayaran();
                for (CaraBayaran caraBayaran : caraBayaranList) {
                    if (caraBayaran != null) {
                        dokumenKewanganBayaran = service.getDokumenKewanganBayaranbyCaraBayaran(caraBayaran.getIdCaraBayaran());
                    }
                }
                dokumenKewanganBayaranList.add(dokumenKewanganBayaran);
                
                if (dokumenKewanganBayaran != null & dokumenKewanganBayaran.getDokumenKewangan() != null) {
                    transaksiList  = service.getListTranskasiByidKewanganBayaran(dokumenKewanganBayaran.getDokumenKewangan().getIdDokumenKewangan());
                }

            }
        }
        senaraiDeposit = service.findByIDMohon(idPermohonan);
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(DepositMelakaActionBean.class, "locate");
    }

    public Resolution reload() {
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/AkuanDeposit_Melaka.jsp").addParameter("tab", "true");
    }

    public Resolution reloadEdit() {
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/AkuanDeposit_Melaka.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.info("id hakmilik : " + idHakmilik);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        mohontuntut = service.findByIDMohonTuntutMelaka(idPermohonan, kod);
        hakmilikPermohonan = plpservice.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        List<HakmilikPermohonan> hpList = permohonan.getSenaraiHakmilik();

        Permohonan p = permohonanDAO.findById(idPermohonan);
        cawangan = p.getCawangan();
//            nilaitanah = new BigDecimal(nilaiTanah);

        senaraiDeposit = service.findByIDMohon(idPermohonan);
        sumNilaian = new BigDecimal(0.00);
        sumDeposit = new BigDecimal(0.00);

        for (int i = 0; i < senaraiDeposit.size(); i++) {
            String nilaitanah2 = getContext().getRequest().getParameter("nilaiTanah" + i);
//            nilaitanah = new BigDecimal(nilaitanah2.trim().replaceAll(",", ""));

            String nilaiSand = getContext().getRequest().getParameter("nilaiT" + i);
            LOG.info("nilaiSand : " + nilaiSand);
            if (!nilaiSand.isEmpty()) {
                nilaiT = new BigDecimal(nilaiSand.trim().replaceAll(",", ""));
            } else {
                nilaiT = BigDecimal.ZERO;
            }

            String nilaiB = getContext().getRequest().getParameter("nilaiBangunan" + i);
            if (!nilaiB.isEmpty()) {
                nilaiBangunan = new BigDecimal(nilaiB.trim().replaceAll(",", ""));
            } else {
                nilaiBangunan = BigDecimal.ZERO;
            }

            String nilaiP = getContext().getRequest().getParameter("nilaiPecah" + i);
            if (!nilaiP.isEmpty()) {
                nilaiPecah = new BigDecimal(nilaiP.trim().replaceAll(",", ""));
            } else {
                nilaiPecah = BigDecimal.ZERO;
            }

            String nilaiM = getContext().getRequest().getParameter("nilaiMudarat" + i);
            if (!nilaiM.isEmpty()) {
                nilaiMudarat = new BigDecimal(nilaiM.trim().replaceAll(",", ""));
            } else {
                nilaiMudarat = BigDecimal.ZERO;
            }

            String nilaiD = getContext().getRequest().getParameter("nilaiDll" + i);
            if (!nilaiD.isEmpty()) {
                nilaiDll = new BigDecimal(nilaiD.trim().replaceAll(",", ""));
            } else {
                nilaiDll = BigDecimal.ZERO;
            }


            BigDecimal totalOverall = nilaiT.add(nilaiBangunan.add(nilaiPecah.add(nilaiMudarat.add(nilaiDll))));
            LOG.info("totalOverall - " + totalOverall);

            a1 = BigDecimal.valueOf(1.25);
            a2 = BigDecimal.valueOf(0.25);
            amaun = totalOverall.multiply(a1);

            mohontuntut = service.findByIDMohonTuntutKod2(idPermohonan, kod);
            if (idPermohonan != null) {
                if (senaraiDeposit.isEmpty()) {
//                        mohontuntut = new PermohonanTuntutanKos();
//                        InfoAudit info = peng.getInfoAudit();
//                        info.setDimasukOleh(peng);
//                        info.setTarikhMasuk(new java.util.Date());
//                        mohontuntut.setInfoAudit(info);
//                        mohontuntut.setPermohonan(p);
//                        mohontuntut.setCawangan(cawangan);
//                        KodTransaksi kodTransaksi = kodTransaksiDAO.findById(kod);
//                        mohontuntut.setKodTransaksi(kodTransaksi);
//                        mohontuntut.setItem("Deposit 125%");
//                        mohontuntut.setAmaunSebenar(nilaitanah);
//                        mohontuntut.setAmaunTuntutan(getAmaun());
//                        mohontuntut.setHakmilikPermohonan(hakmilikPermohonan);
//                        plpservice.simpanPermohonanTuntutanKos(mohontuntut);
                } else {
                    InfoAudit info = peng.getInfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    senaraiDeposit.get(i).setInfoAudit(info);
                    senaraiDeposit.get(i).setPermohonan(p);
                    senaraiDeposit.get(i).setCawangan(cawangan);
                    KodTransaksi kodTransaksi = kodTransaksiDAO.findById(kod);
                    senaraiDeposit.get(i).setKodTransaksi(kodTransaksi);
                    senaraiDeposit.get(i).setItem("Deposit 125%");
                    senaraiDeposit.get(i).setAmaunSebenar(totalOverall);
                    senaraiDeposit.get(i).setTanah(nilaiT.intValue());
                    senaraiDeposit.get(i).setBangunan(nilaiBangunan.intValue());
                    senaraiDeposit.get(i).setPecahPisah(nilaiPecah.intValue());
                    senaraiDeposit.get(i).setMudarat(nilaiMudarat.intValue());
                    senaraiDeposit.get(i).setLainLain(nilaiDll.intValue());
                    senaraiDeposit.get(i).setAmaunTuntutan(getAmaun());
//                        mohontuntut.setHakmilikPermohonan(hMohon);
                    plpservice.simpanPermohonanTuntutanKos(senaraiDeposit.get(i));
                }
                for (PermohonanTuntutanKos kos : senaraiDeposit) {
                    kos.setCatatan(catatan);
                    plpservice.simpanPermohonanTuntutanKos(kos);
                }

                sumT = new BigDecimal(0.0000);
                sumBangunan = new BigDecimal(0.0000);
                sumPecah = new BigDecimal(0.0000);
                sumMudarat = new BigDecimal(0.0000);
                sumDll = new BigDecimal(0.0000);

                sumT = sumT.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getTanah()))));
                totalTanah = String.valueOf(sumT);
                LOG.info("totalTanah : " + totalTanah);

                sumBangunan = sumBangunan.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getBangunan()))));
                totalBangunan = String.valueOf(sumBangunan);

                sumPecah = sumPecah.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getPecahPisah()))));
                totalPecah = String.valueOf(sumPecah);

                sumMudarat = sumMudarat.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getMudarat()))));
                totalMudarat = String.valueOf(sumMudarat);

                sumDll = sumDll.add(BigDecimal.valueOf(Long.parseLong(String.valueOf(senaraiDeposit.get(i).getLainLain()))));
                totalDll = String.valueOf(sumDll);

                sumNilaian = sumNilaian.add(senaraiDeposit.get(i).getAmaunSebenar());
                totalNilaian = String.valueOf(sumNilaian);
                LOG.info("tn : " + totalNilaian);

                sumDeposit = sumDeposit.add(senaraiDeposit.get(i).getAmaunTuntutan());
//            df.format(sumDeposit);
                totalDeposit = String.valueOf(sumDeposit);
                LOG.info("td : " + totalDeposit);
            }
        }

//        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/AkuanDeposit_Melaka.jsp").addParameter("tab", "true");
    }

    /**
     * @return the a1
     */
    public BigDecimal getA1() {
        return a1;
    }

    /**
     * @param a1 the a1 to set
     */
    public void setA1(BigDecimal a1) {
        this.a1 = a1;
    }

    /**
     * @return the a2
     */
    public BigDecimal getA2() {
        return a2;
    }

    /**
     * @param a2 the a2 to set
     */
    public void setA2(BigDecimal a2) {
        this.a2 = a2;
    }

    /**
     * @return the amaun50
     */
    public BigDecimal getAmaun50() {
        return amaun50;
    }

    /**
     * @param amaun50 the amaun50 to set
     */
    public void setAmaun50(BigDecimal amaun50) {
        this.amaun50 = amaun50;
    }

    /**
     * @return the kira1
     */
    public BigDecimal getKira1() {
        return kira1;
    }

    /**
     * @param kira1 the kira1 to set
     */
    public void setKira1(BigDecimal kira1) {
        this.kira1 = kira1;
    }

    /**
     * @return the kira2
     */
    public BigDecimal getKira2() {
        return kira2;
    }

    /**
     * @param kira2 the kira2 to set
     */
    public void setKira2(BigDecimal kira2) {
        this.kira2 = kira2;
    }

    /**
     * @return the amaun75
     */
    public BigDecimal getAmaun75() {
        return amaun75;
    }

    /**
     * @param amaun75 the amaun75 to set
     */
    public void setAmaun75(BigDecimal amaun75) {
        this.amaun75 = amaun75;
    }

    /**
     * @return the baki
     */
    public BigDecimal getBaki() {
        return baki;
    }

    /**
     * @param baki the baki to set
     */
    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public BigDecimal getNilaitanah() {
        return nilaitanah;
    }

    public void setNilaitanah(BigDecimal nilaitanah) {
        this.nilaitanah = nilaitanah;
    }

    public String getNilaiTanah() {
        return nilaiTanah;
    }

    public void setNilaiTanah(String nilaiTanah) {
        this.nilaiTanah = nilaiTanah;
    }

    public LaporanPelukisPelanService getServiceMohon() {
        return serviceMohon;
    }

    public void setServiceMohon(LaporanPelukisPelanService serviceMohon) {
        this.serviceMohon = serviceMohon;
    }

    public List<PermohonanTuntutanKos> getMohonTuntutList() {
        return mohonTuntutList;
    }

    public void setMohonTuntutList(List<PermohonanTuntutanKos> mohonTuntutList) {
        this.mohonTuntutList = mohonTuntutList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanTuntutanKos getMohontuntut() {
        return mohontuntut;
    }

    public void setMohontuntut(PermohonanTuntutanKos mohontuntut) {
        this.mohontuntut = mohontuntut;
    }

    public List<PermohonanTuntutanKos> getSenaraiDeposit() {
        return senaraiDeposit;
    }

    public void setSenaraiDeposit(List<PermohonanTuntutanKos> senaraiDeposit) {
        this.senaraiDeposit = senaraiDeposit;
    }

    public List<CaraBayaran> getSenaraiCaraPembayaran() {
        return senaraiCaraPembayaran;
    }

    public void setSenaraiCaraPembayaran(List<CaraBayaran> senaraiCaraPembayaran) {
        this.senaraiCaraPembayaran = senaraiCaraPembayaran;
    }

    public List<CaraBayaran> getCaraBayaranList() {
        return caraBayaranList;
    }

    public void setCaraBayaranList(List<CaraBayaran> caraBayaranList) {
        this.caraBayaranList = caraBayaranList;
    }

    public List<DokumenKewanganBayaran> getDokumenKewanganBayaranList() {
        return dokumenKewanganBayaranList;
    }

    public void setDokumenKewanganBayaranList(List<DokumenKewanganBayaran> dokumenKewanganBayaranList) {
        this.dokumenKewanganBayaranList = dokumenKewanganBayaranList;
    }

    public List<Transaksi> getTransaksiList() {
        return transaksiList;
    }

    public void setTransaksiList(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public List<HakmilikPermohonan> getHp() {
        return hp;
    }

    public void setHp(List<HakmilikPermohonan> hp) {
        this.hp = hp;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public BigDecimal getSumNilaian() {
        return sumNilaian;
    }

    public void setSumNilaian(BigDecimal sumNilaian) {
        this.sumNilaian = sumNilaian;
    }

    public BigDecimal getSumDeposit() {
        return sumDeposit;
    }

    public void setSumDeposit(BigDecimal sumDeposit) {
        this.sumDeposit = sumDeposit;
    }

    public String getTotalNilaian() {
        return totalNilaian;
    }

    public void setTotalNilaian(String totalNilaian) {
        this.totalNilaian = totalNilaian;
    }

    public String getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(String totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public HakmilikPermohonan getHakMohon() {
        return hakMohon;
    }

    public void setHakMohon(HakmilikPermohonan hakMohon) {
        this.hakMohon = hakMohon;
    }

    public List<String> getNilaijpph() {
        return nilaijpph;
    }

    public void setNilaijpph(List<String> nilaijpph) {
        this.nilaijpph = nilaijpph;
    }

    public String getTotalBangunan() {
        return totalBangunan;
    }

    public void setTotalBangunan(String totalBangunan) {
        this.totalBangunan = totalBangunan;
    }

    public String getTotalDll() {
        return totalDll;
    }

    public void setTotalDll(String totalDll) {
        this.totalDll = totalDll;
    }

    public String getTotalMudarat() {
        return totalMudarat;
    }

    public void setTotalMudarat(String totalMudarat) {
        this.totalMudarat = totalMudarat;
    }

    public String getTotalPecah() {
        return totalPecah;
    }

    public void setTotalPecah(String totalPecah) {
        this.totalPecah = totalPecah;
    }

    public String getTotalTanah() {
        return totalTanah;
    }

    public void setTotalTanah(String totalTanah) {
        this.totalTanah = totalTanah;
    }

    public BigDecimal getNilaiBangunan() {
        return nilaiBangunan;
    }

    public void setNilaiBangunan(BigDecimal nilaiBangunan) {
        this.nilaiBangunan = nilaiBangunan;
    }

    public BigDecimal getNilaiDll() {
        return nilaiDll;
    }

    public void setNilaiDll(BigDecimal nilaiDll) {
        this.nilaiDll = nilaiDll;
    }

    public BigDecimal getNilaiMudarat() {
        return nilaiMudarat;
    }

    public void setNilaiMudarat(BigDecimal nilaiMudarat) {
        this.nilaiMudarat = nilaiMudarat;
    }

    public BigDecimal getNilaiPecah() {
        return nilaiPecah;
    }

    public void setNilaiPecah(BigDecimal nilaiPecah) {
        this.nilaiPecah = nilaiPecah;
    }

    public BigDecimal getNilaiT() {
        return nilaiT;
    }

    public void setNilaiT(BigDecimal nilaiT) {
        this.nilaiT = nilaiT;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public BigDecimal getSumBangunan() {
        return sumBangunan;
    }

    public void setSumBangunan(BigDecimal sumBangunan) {
        this.sumBangunan = sumBangunan;
    }

    public BigDecimal getSumDll() {
        return sumDll;
    }

    public void setSumDll(BigDecimal sumDll) {
        this.sumDll = sumDll;
    }

    public BigDecimal getSumMudarat() {
        return sumMudarat;
    }

    public void setSumMudarat(BigDecimal sumMudarat) {
        this.sumMudarat = sumMudarat;
    }

    public BigDecimal getSumPecah() {
        return sumPecah;
    }

    public void setSumPecah(BigDecimal sumPecah) {
        this.sumPecah = sumPecah;
    }

    public BigDecimal getSumT() {
        return sumT;
    }

    public void setSumT(BigDecimal sumT) {
        this.sumT = sumT;
    }

    public FasaPermohonan getFp() {
        return fp;
    }

    public void setFp(FasaPermohonan fp) {
        this.fp = fp;
    }
}
